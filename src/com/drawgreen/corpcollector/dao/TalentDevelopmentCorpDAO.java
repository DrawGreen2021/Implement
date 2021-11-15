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

import com.drawgreen.corpcollector.dto.RecentSearchDTO;
import com.drawgreen.corpcollector.dto.TalentDevelopmentCorpDTO;


public class TalentDevelopmentCorpDAO implements CorpDAO{
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int allRowCount;
	private int pageRowCount;
	// 키워드 검색 결과에 해당하는 연번을 저장할 리스트
	private ArrayList<Integer> serialNums;
	// 키워드 값을 저장할 변수
	private String beforeKeyword;

	private TalentDevelopmentCorpDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/DrawGreen");
			pageRowCount = 10;
			allRowCount = getRowCount("인재육성형중소기업");
			serialNums = new ArrayList<Integer>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class InnerInstance_CorpDAO {
		private static final TalentDevelopmentCorpDAO talentDevelopmentCorpDAO = new TalentDevelopmentCorpDAO();
	}

	public static TalentDevelopmentCorpDAO getInstance() {
		return InnerInstance_CorpDAO.talentDevelopmentCorpDAO;
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
	public ArrayList<TalentDevelopmentCorpDTO> getCorpList(int page) {
		ArrayList<TalentDevelopmentCorpDTO> talentDevelopmentCorpDTOs = new ArrayList<TalentDevelopmentCorpDTO>();
		String query = "SELECT * FROM 인재육성형중소기업 WHERE 연번 BETWEEN ? AND ?";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 1+(page*pageRowCount-pageRowCount));
			preparedStatement.setInt(2, page*pageRowCount < allRowCount?
					page*pageRowCount:allRowCount);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String representative = resultSet.getString("대표자명");
				String address = resultSet.getString("소재지");
				String main_product = resultSet.getString("업종");

				TalentDevelopmentCorpDTO dto = new TalentDevelopmentCorpDTO(serial_number, company_name, representative, address, main_product);
				talentDevelopmentCorpDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}

		return talentDevelopmentCorpDTOs;
	}

	// 검색어가 있을 경우 기업 리스트 받아오기
	public ArrayList<TalentDevelopmentCorpDTO> getCorpList(String keyword, int page) {
		// TODO Auto-generated method stub
		ArrayList<TalentDevelopmentCorpDTO> talentDevelopmentCorpDTOs = new ArrayList<TalentDevelopmentCorpDTO>();
		
		// 검색어가 달라졌으면 해당 연번 다시 select
		if (beforeKeyword == null) {
			beforeKeyword = keyword;
			serialNums = setSerialNum(keyword, serialNums);
		}
		else if (!beforeKeyword.equals(keyword)) {
			serialNums.clear();
			serialNums = setSerialNum(keyword, serialNums);
		}
		
		if (serialNums.size() == 0) {
			return null;
		}
			
		String getCorpListQuery = "SELECT * FROM 인재육성형중소기업 "
				+ "WHERE 연번 IN(";
		// 0~9, 10~19 ...
		int startNum = page*pageRowCount-pageRowCount;
		int lastNum = page*pageRowCount;
		
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
				String representative = resultSet.getString("대표자명");
				String address = resultSet.getString("소재지");
				String main_product = resultSet.getString("업종");

				TalentDevelopmentCorpDTO dto = new TalentDevelopmentCorpDTO(serial_number, company_name, representative, address, main_product);
				talentDevelopmentCorpDTOs.add(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			talentDevelopmentCorpDTOs = null;
		} finally {
			closing();
		}

		return talentDevelopmentCorpDTOs;
	}
	
	// 검색 키워드가 존재하는 행의 연번 알아오기
	public ArrayList<Integer> setSerialNum(String keyword, ArrayList<Integer> serialNums) {
		
		// 키워드 공백으로 분리
		StringTokenizer tokenizer = new StringTokenizer(keyword);
		
		String query = "SELECT 연번 FROM talentDevelopmentCorp_view "
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
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
				serialNums.add(resultSet.getInt("연번"));
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closing();
		}
		
		return serialNums;
	}
	
	public int getRowCount_byKeyword() {
		return serialNums.size();
	}

	@Override
	public ArrayList<Integer> getSerialNums() {
		// TODO Auto-generated method stub
		return serialNums;
	}

	@Override
	public LinkedHashMap<String, Object> getInfo(int serial_num) {
		// TODO Auto-generated method stub
		LinkedHashMap<String, Object> corpInfo = new LinkedHashMap<String, Object>();
		
		String query = "SELECT * FROM 인재육성형중소기업 WHERE 연번 = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, serial_num);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			corpInfo.put("연번", resultSet.getInt("연번"));
			corpInfo.put("사업자번호", resultSet.getString("사업자번호"));
			corpInfo.put("업체명", resultSet.getString("업체명"));
			corpInfo.put("대표자명", resultSet.getString("대표자명"));
			corpInfo.put("소재지", resultSet.getString("소재지"));
			corpInfo.put("업종", resultSet.getString("업종"));
			corpInfo.put("선정일", resultSet.getTimestamp("선정일"));
			corpInfo.put("유효기간 종료일", resultSet.getTimestamp("유효기간 종료일"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return corpInfo;
	}

	@Override
	public ArrayList<RecentSearchDTO> getRecentRecords(String user_id) {
		// TODO Auto-generated method stub
		ArrayList<RecentSearchDTO> recentRecords = new ArrayList<RecentSearchDTO>();
		
		String query = "SELECT t.연번, t.업체명, t.소재지, t.업종, r.search_date FROM 인재육성형중소기업 t, 최근검색기업 r " + 
				" WHERE t.연번 IN (SELECT talentDevelopmentCorp_id FROM 최근검색기업 " + 
				" WHERE user_id = ? AND talentDevelopmentCorp_id IS NOT NULL) AND r.talentDevelopmentCorp_id = t.연번";
		
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
				
				RecentSearchDTO dto = new RecentSearchDTO(serial_number, company_name, location, sector, "TalentDevelopmentCorp", "인재육성형중소기업", search_date);
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
