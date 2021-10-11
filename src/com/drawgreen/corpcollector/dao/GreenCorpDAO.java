package com.drawgreen.corpcollector.dao;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.drawgreen.corpcollector.dto.GreenCorpDTO;


public class GreenCorpDAO extends CorpDAO {
	private boolean allSearched;
	private int pageRowCount;
	//키워드 검색 결과에 해당하는 연번을 저장할 배열
	private ArrayList<Integer> serialNums = new ArrayList<Integer>();
	//키워드 값을 저장할 변수
	private String beforeKeyword;

	private GreenCorpDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			allSearched = false;
			pageRowCount = 10;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class InnerInstance_CorpDAO {
		private static final GreenCorpDAO greenCorpDAO = new GreenCorpDAO();
	}

	public static GreenCorpDAO getInstance() {
		return InnerInstance_CorpDAO.greenCorpDAO;
	}
	
	public boolean isAllSearched() {
		return allSearched;
	}

	public void setAllSearched(boolean allSearched) {
		this.allSearched = allSearched;
	}
	
	// 검색 키워드가 없다면 연번으로 행 개수만큼 불러오기
	public ArrayList<GreenCorpDTO> getCorpList(int page) {
		ArrayList<GreenCorpDTO> greenCorpDTOs = new ArrayList<GreenCorpDTO>();
		String query = "SELECT * FROM 녹색기업 WHERE 연번 BETWEEN ? AND ?";

		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 1+(page*pageRowCount-pageRowCount));
			preparedStatement.setInt(2, page*pageRowCount);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String location = resultSet.getString("소재지");
				String sector = resultSet.getString("업종");
				String site = resultSet.getString("사이트주소");

				GreenCorpDTO dto = new GreenCorpDTO(serial_number, company_name, location, sector, site);
				greenCorpDTOs.add(dto);
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

		return greenCorpDTOs;
	}

	public ArrayList<GreenCorpDTO> getCorpList(String keyword, int page) {
		// TODO Auto-generated method stub
		ArrayList<GreenCorpDTO> greenCorpDTOs = new ArrayList<GreenCorpDTO>();
		
		//검색어가 달라졌으면 해당 연번 다시 select
		if (beforeKeyword == null) {
			beforeKeyword = keyword;
			serialNums = getSerialNumQuery(keyword, serialNums);
		}
		else if (!beforeKeyword.equals(keyword)) {
			serialNums.clear();
			serialNums = getSerialNumQuery(keyword, serialNums);
		}
			
		
		String getCorpListQuery = "SELECT * FROM 녹색기업 "
				+ "WHERE 연번 IN(";
		// 0~9, 10~19 ...
		int startNum = page*pageRowCount-pageRowCount;
		int lastNum = page*pageRowCount;
		
		StringBuilder builder = new StringBuilder(getCorpListQuery);
		
		for (int i = startNum; i < lastNum && i < serialNums.size(); i++) {
			builder.append(serialNums.get(i));
			
			if (i < lastNum-1)
				builder.append(",");
		}
		builder.append(")");
		getCorpListQuery = builder.toString();
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(getCorpListQuery);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String location = resultSet.getString("소재지");
				String sector = resultSet.getString("업종");
				String site = resultSet.getString("사이트주소");

				GreenCorpDTO dto = new GreenCorpDTO(serial_number, company_name, location, sector, site);
				greenCorpDTOs.add(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			greenCorpDTOs = null;
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

		return greenCorpDTOs;
	}
	
	// 검색 키워드가 존재하는 행의 연번 알아오기
	public ArrayList<Integer> getSerialNumQuery(String keyword, ArrayList<Integer> serialNums) {
		
		//키워드 공백으로 분리
		StringTokenizer tokenizer = new StringTokenizer(keyword);
		
		String query = "SELECT 연번 FROM greenCorp_view "
				+ "WHERE text REGEXP('";
		StringBuffer buffer = new StringBuffer(query);
		buffer.append(tokenizer.nextToken()+"'");
		while(tokenizer.hasMoreTokens()) {
			buffer.append("|'");
			buffer.append(tokenizer.nextToken()+"'");
		}
		buffer.append(")");
		query = buffer.toString();
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
				serialNums.add(resultSet.getInt("연번"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return serialNums;
	}
	
	public int getRowCount_byKeyword() {
		return serialNums.size();
	}
}