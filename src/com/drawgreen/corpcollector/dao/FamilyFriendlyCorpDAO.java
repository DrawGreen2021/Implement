package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.drawgreen.corpcollector.dto.FamilyFriendlyCorpDTO;

public class FamilyFriendlyCorpDAO implements CorpDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String userId = "general_user_id";
	private String userPw = "general_user_password"; 
	private String url = "jdbc:mysql://corpcollector.ciqetekukvwo.ap-northeast-2.rds.amazonaws.com:3306/Corp";
	private int allRowCount;
	private int pageRowCount;
	// 키워드 검색 결과에 해당하는 연번을 저장할 리스트
	private ArrayList<Integer> serialNums;
	// 키워드 값을 저장할 변수
	private String beforeKeyword;

	private FamilyFriendlyCorpDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			pageRowCount = 10;
			allRowCount = getRowCount("가족친화인증기업");
			serialNums = new ArrayList<Integer>();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static class InnerInstance_CorpDAO {
		private static final FamilyFriendlyCorpDAO familyFriendlyCorpDAO = new FamilyFriendlyCorpDAO();
	}

	public static FamilyFriendlyCorpDAO getInstance() {
		return InnerInstance_CorpDAO.familyFriendlyCorpDAO;
	}

	public int getAllRowCount() {
		return allRowCount;
	}

	public void setAllRowCount(int allRowCount) {
		this.allRowCount = allRowCount;
	}
	
	// 전체 테이블 행 개수 가져오기
	@Override
	public int getRowCount(String corpType) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		String query = "SELECT count(*) FROM "+corpType;
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			rowCount = resultSet.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null) connection.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(resultSet!=null) resultSet.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return rowCount;
	}

	// 검색 키워드가 없다면 연번으로 행 개수만큼 불러오기
	public ArrayList<FamilyFriendlyCorpDTO> getCorpList(int page) {
		ArrayList<FamilyFriendlyCorpDTO> familyFriendlyCorpDTOs = new ArrayList<FamilyFriendlyCorpDTO>();
		String query = "SELECT * FROM 가족친화인증기업 WHERE 연번 BETWEEN ? AND ?";

		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 1 + (page * pageRowCount - pageRowCount));
			preparedStatement.setInt(2, page*pageRowCount < allRowCount?
					page*pageRowCount:allRowCount);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("기업명");
				String division = resultSet.getString("분류");
				String city_state = resultSet.getString("시도");

				FamilyFriendlyCorpDTO dto = new FamilyFriendlyCorpDTO(serial_number, company_name, division, city_state);
				familyFriendlyCorpDTOs.add(dto);
				//System.out.println(dto.getCity_sate());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return familyFriendlyCorpDTOs;
	}
	
	// 검색어가 있을 경우 기업 리스트 받아오기
	public ArrayList<FamilyFriendlyCorpDTO> getCorpList(String keyword, int page) {
		// TODO Auto-generated method stub
		ArrayList<FamilyFriendlyCorpDTO> familyFriendlyCorpDTOs = new ArrayList<FamilyFriendlyCorpDTO>();

		// 검색어가 달라졌으면 해당 연번 다시 select
		if (beforeKeyword == null) {
			beforeKeyword = keyword;
			serialNums = getSerialNumQuery(keyword, serialNums);
		} else if (!beforeKeyword.equals(keyword)) {
			serialNums.clear();
			serialNums = getSerialNumQuery(keyword, serialNums);
		}
		
		if (serialNums.size() == 0) {
			return null;
		}

		String getCorpListQuery = "SELECT * FROM 가족친화인증기업 " + "WHERE 연번 IN(";
		// 0~9, 10~19 ...
		int startNum = page * pageRowCount - pageRowCount;
		int lastNum = page * pageRowCount;

		StringBuilder builder = new StringBuilder(getCorpListQuery);

		builder.append(serialNums.get(startNum));
		for (int i = startNum+1; i < lastNum && i < serialNums.size(); i++) {
			builder.append(",");
			builder.append(serialNums.get(i));	
		}
		builder.append(")");
		getCorpListQuery = builder.toString();

		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(getCorpListQuery);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("기업명");
				String division = resultSet.getString("분류");
				String city_state = resultSet.getString("시도");

				FamilyFriendlyCorpDTO dto = new FamilyFriendlyCorpDTO(serial_number, company_name, division, city_state);
				familyFriendlyCorpDTOs.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			familyFriendlyCorpDTOs = null;
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return familyFriendlyCorpDTOs;
	}

	// 검색 키워드가 존재하는 행의 연번 알아오기
	public ArrayList<Integer> getSerialNumQuery(String keyword, ArrayList<Integer> serialNums) {

		// 키워드 공백으로 분리
		StringTokenizer tokenizer = new StringTokenizer(keyword);

		String query = "SELECT 연번 FROM familyFriendlyCorp_view " + "WHERE text REGEXP('";
		StringBuffer buffer = new StringBuffer(query);
		buffer.append(tokenizer.nextToken() + "'");
		while (tokenizer.hasMoreTokens()) {
			buffer.append("|'");
			buffer.append(tokenizer.nextToken() + "'");
		}
		buffer.append(")");
		query = buffer.toString();

		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				serialNums.add(resultSet.getInt("연번"));

		} catch (Exception e) {
			// TODO: handle exception
		}

		return serialNums;
	}

	public int getRowCount_byKeyword() {
		return serialNums.size();
	}

	@Override
	public ArrayList<Integer> getSerialNums() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
