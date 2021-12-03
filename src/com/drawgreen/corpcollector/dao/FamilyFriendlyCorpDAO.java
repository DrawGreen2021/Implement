package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.drawgreen.corpcollector.dto.FamilyFriendlyCorpDTO;
import com.drawgreen.corpcollector.dto.RecentSearchDTO;

public class FamilyFriendlyCorpDAO implements CorpDAO {
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int allRowCount;
	// 키워드 검색 결과에 해당하는 연번을 저장할 리스트
	private ArrayList<Integer> serialNums;
	// 키워드 값을 저장할 변수
	private String beforeKeyword;

	private FamilyFriendlyCorpDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/DrawGreen");
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
	
	@Override
	public int getAllRowCount() {
		return allRowCount;
	}
	
	@Override
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
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			rowCount = resultSet.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return rowCount;
	}

	// 검색 키워드가 없다면 연번으로 행 개수만큼 불러오기
	@Override
	public ArrayList<FamilyFriendlyCorpDTO> getCorpList(int page) {
		ArrayList<FamilyFriendlyCorpDTO> familyFriendlyCorpDTOs = new ArrayList<FamilyFriendlyCorpDTO>();
		String query = "SELECT * FROM 가족친화인증기업 WHERE 연번 BETWEEN ? AND ?";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 1 + (page * pageRowCount - pageRowCount));
			preparedStatement.setInt(2, page*pageRowCount < allRowCount?
					page*pageRowCount:allRowCount);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String division = resultSet.getString("분류");
				String city_state = resultSet.getString("소재지");

				FamilyFriendlyCorpDTO dto = new FamilyFriendlyCorpDTO(serial_number, company_name, division, city_state);
				familyFriendlyCorpDTOs.add(dto);
				//System.out.println(dto.getCity_sate());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}

		return familyFriendlyCorpDTOs;
	}
	
	// 검색어가 있을 경우 기업 리스트 받아오기
	@Override
	public ArrayList<FamilyFriendlyCorpDTO> getCorpList(String keyword, int page) {
		// TODO Auto-generated method stub
		ArrayList<FamilyFriendlyCorpDTO> familyFriendlyCorpDTOs = new ArrayList<FamilyFriendlyCorpDTO>();

		// 검색어가 달라졌으면 해당 연번 다시 select
		if (beforeKeyword == null) {
			beforeKeyword = keyword;
			serialNums = setSerialNum(keyword, serialNums);
		} else if (!beforeKeyword.equals(keyword)) {
			serialNums.clear();
			serialNums = setSerialNum(keyword, serialNums);
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
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(getCorpListQuery);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String division = resultSet.getString("분류");
				String city_state = resultSet.getString("소재지");

				FamilyFriendlyCorpDTO dto = new FamilyFriendlyCorpDTO(serial_number, company_name, division, city_state);
				familyFriendlyCorpDTOs.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			familyFriendlyCorpDTOs = null;
		} finally {
			closing();
		}

		return familyFriendlyCorpDTOs;
	}

	// 검색 키워드가 존재하는 행의 연번 알아오기
	@Override
	public ArrayList<Integer> setSerialNum(String keyword, ArrayList<Integer> serialNums) {

		// 키워드 공백으로 분리
		StringTokenizer tokenizer = new StringTokenizer(keyword);

		String query = "SELECT 연번 FROM familyFriendlyCorp_view WHERE text REGEXP('";
		StringBuffer buffer = new StringBuffer(query);
		buffer.append(tokenizer.nextToken() + "'");
		while (tokenizer.hasMoreTokens()) {
			buffer.append("|'");
			buffer.append(tokenizer.nextToken() + "'");
		}
		buffer.append(")");
		query = buffer.toString();

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				serialNums.add(resultSet.getInt("연번"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}

		return serialNums;
	}
	
	@Override
	public int getRowCount_byKeyword() {
		return serialNums.size();
	}

	@Override
	public ArrayList<Integer> getSerialNums() {
		// TODO Auto-generated method stub
		return serialNums;
	}

	// 상세 기업 페이지에서 관련 정보를 가져올 때, 해당 레코드 정보 반환
	@Override
	public LinkedHashMap<String, Object> getInfo(int serial_num) {
		// TODO Auto-generated method stub
		LinkedHashMap<String, Object> corpInfo = new LinkedHashMap<String, Object>();
		
		String query = "SELECT * FROM 가족친화인증기업 WHERE 연번 = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, serial_num);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			corpInfo.put("연번", resultSet.getInt("연번"));
			corpInfo.put("업체명", resultSet.getString("업체명"));
			corpInfo.put("분류", resultSet.getString("분류"));
			corpInfo.put("소재지", resultSet.getString("소재지"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return corpInfo;
	}

	// 최근 검색 기업과 연관된 정보 가져오기
	@Override
	public ArrayList<RecentSearchDTO> getRecentRecords(String user_id) {
		// TODO Auto-generated method stub
		ArrayList<RecentSearchDTO> recentRecords = new ArrayList<RecentSearchDTO>();
		
		String query = "SELECT f.연번, f.업체명, f.소재지, f.업종, r.search_date FROM 가족친화인증기업 f, 최근검색기업 r " + 
				" WHERE f.연번 IN (SELECT familyFriendlyCorp_id FROM 최근검색기업 " + 
				" WHERE user_id = ? AND familyFriendlyCorp_id IS NOT NULL) AND r.familyFriendlyCorp_id = f.연번"
				+ " AND user_id = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setString(2, user_id);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String location = resultSet.getString("소재지");
				String sector = resultSet.getString("업종");
				Timestamp search_date = resultSet.getTimestamp("search_date");
				
				RecentSearchDTO dto = new RecentSearchDTO(serial_number, company_name, location, sector, "familyFriendlyCorp", "가족친화인증기업", search_date);
				recentRecords.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return recentRecords;
		} finally {
			closing();
		}
		
		return recentRecords;
	}

	@Override
	public void closing() {
		try {
			if (connection != null) connection.close();
			if (preparedStatement != null) preparedStatement.close();
			if (resultSet!=null) resultSet.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
