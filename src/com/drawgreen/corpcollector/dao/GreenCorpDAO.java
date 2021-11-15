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

import com.drawgreen.corpcollector.dto.GreenCorpDTO;
import com.drawgreen.corpcollector.dto.RecentSearchDTO;

public class GreenCorpDAO implements CorpDAO {
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int allRowCount;
	// 키워드 검색 결과에 해당하는 연번을 저장할 리스트
	private ArrayList<Integer> serialNums;
	// 키워드 값을 저장할 변수
	private String beforeKeyword;

	private GreenCorpDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/DrawGreen");
			allRowCount = getRowCount("녹색기업");
			serialNums = new ArrayList<Integer>();
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
		String query = "SELECT count(*) FROM " + corpType;

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
	public ArrayList<GreenCorpDTO> getCorpList(int page) {
		ArrayList<GreenCorpDTO> greenCorpDTOs = new ArrayList<GreenCorpDTO>();
		String query = "SELECT * FROM 녹색기업 WHERE 연번 BETWEEN ? AND ?";

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
			closing();
		}
		return greenCorpDTOs;
	}

	// 검색어가 있을 경우 기업 리스트 받아오기
	@Override
	public ArrayList<GreenCorpDTO> getCorpList(String keyword, int page) {
		// TODO Auto-generated method stub
		ArrayList<GreenCorpDTO> greenCorpDTOs = new ArrayList<GreenCorpDTO>();

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

		String getCorpListQuery = "SELECT * FROM 녹색기업 " + "WHERE 연번 IN(";
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
			closing();
		}

		return greenCorpDTOs;
	}

	// 검색 키워드가 존재하는 행의 연번 알아오기
	@Override
	public ArrayList<Integer> setSerialNum(String keyword, ArrayList<Integer> serialNums) {

		// 키워드 공백으로 분리
		StringTokenizer tokenizer = new StringTokenizer(keyword);

		String query = "SELECT 연번 FROM greenCorp_view " + "WHERE text REGEXP('";
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
		return serialNums;
	}
	
	// 상세 기업 페이지에서 관련 정보를 가져올 때, 해당 레코드 정보 반환
	@Override
	public LinkedHashMap<String, Object> getInfo(int serial_num) {
		LinkedHashMap<String, Object> corpInfo = new LinkedHashMap<String, Object>();
		
		String query = "SELECT * FROM 녹색기업 WHERE 연번 = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, serial_num);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			corpInfo.put("연번", resultSet.getInt("연번"));
			corpInfo.put("업체명", resultSet.getString("업체명"));
			corpInfo.put("소재지", resultSet.getString("소재지"));
			corpInfo.put("업종", resultSet.getString("업종"));
			corpInfo.put("최초지정", resultSet.getTimestamp("최초지정"));
			corpInfo.put("현지정시작일자", resultSet.getTimestamp("현지정시작일자"));
			corpInfo.put("현지정종료일자", resultSet.getTimestamp("현지정종료일자"));
			corpInfo.put("비고", resultSet.getString("비고"));
			corpInfo.put("지역구분", resultSet.getString("지역구분"));
			corpInfo.put("사이트주소", resultSet.getString("사이트주소"));
			
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
		
		String query = "SELECT g.연번, g.업체명, g.소재지, g.업종, r.search_date FROM 녹색기업 g, 최근검색기업 r " + 
				" WHERE g.연번 IN (SELECT greenCorp_id FROM 최근검색기업 " + 
				" WHERE user_id = ? AND greenCorp_id IS NOT NULL) AND r.greenCorp_id = g.연번";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String location = resultSet.getString("소재지");
				String sector = resultSet.getString("업종");
				Timestamp search_date = resultSet.getTimestamp("search_date");
				
				RecentSearchDTO dto = new RecentSearchDTO(serial_number, company_name, location, sector, "greenCrop", "녹색기업", search_date);
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
