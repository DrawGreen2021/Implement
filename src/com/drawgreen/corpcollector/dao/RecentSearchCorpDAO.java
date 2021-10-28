package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.drawgreen.corpcollector.dto.RecentSearchDTO;

public class RecentSearchCorpDAO {
	private Connection connection = null;
	private Connection rootConnection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String userId = "general_user_id";
	private String userPw = "general_user_password"; 
	private String rootId = "drawgreen";
	private String rootPw = "drawgreen2021"; 
	private String url = "jdbc:mysql://corpcollector.ciqetekukvwo.ap-northeast-2.rds.amazonaws.com:3306/Member";
	private int pageRowCount;
	
	private RecentSearchCorpDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			pageRowCount = 10;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private static class InnerClass_RecentSearchCorpDAO {
		private static final RecentSearchCorpDAO recentSearchCorpDAO = new RecentSearchCorpDAO();
	}
	
	public static RecentSearchCorpDAO getInstance() {
		return InnerClass_RecentSearchCorpDAO.recentSearchCorpDAO;
	}
	
	
	// 최근 검색 기업으로 등록
	public void insertSearchCorp(int serial_num, String user_id, String corpType, String corpName) {
		// 기존에 같은 아이디로 같은 기업을 본 기록이 있는지 검사
		boolean isDuplicated = isDuplicated(serial_num, user_id, corpType);
		if (isDuplicated) {
			deleteRecord(serial_num, user_id, corpType);
		}
		String select_query = "INSERT INTO 최근검색기업(user_id,"+corpType+"_id, corpName, search_date) "
				+ "VALUES(?,?,?,?)";
		
		try {
			rootConnection = DriverManager.getConnection(url, rootId, rootPw);
			preparedStatement = rootConnection.prepareStatement(select_query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, serial_num);
			preparedStatement.setString(3, corpName);
			preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (preparedStatement != null) preparedStatement.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	// 기존에 같은 아이디로 같은 기업을 본 기록이 있는지 검사
	public boolean isDuplicated(int serial_num, String user_id, String corpType) {
		String query = "SELECT IF(count(*)=1, 'true', 'false') AS result FROM 최근검색기업 "
				+ "WHERE user_id = ? AND "+corpType+"_id = ?";
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, serial_num);
			
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			String result = resultSet.getString(1);
			
			return Boolean.parseBoolean(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (preparedStatement != null) preparedStatement.close();
				if (resultSet!=null) resultSet.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return false;
	}
	
	// 중복일 경우, 해당 기록 삭제
	public void deleteRecord(int serial_num, String user_id, String corpType) {
		String query = "DELETE FROM 최근검색기업 WHERE user_id = ? AND "+corpType+"_id = ?";
		
		try {
			rootConnection = DriverManager.getConnection(url, rootId, rootPw);
			preparedStatement = rootConnection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, serial_num);
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
				if (preparedStatement != null) preparedStatement.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	// 최근 검색 기업 페이지에 표시할 기업 리스트 가져오기
	public List<RecentSearchDTO> getCorpList(String user_id) {
		FamilyFriendlyCorpDAO familyFriendlyCorpDAO = FamilyFriendlyCorpDAO.getInstance();
		GreenCorpDAO greenCorpDAO = GreenCorpDAO.getInstance();
		SocialCorpDAO socialCorpDAO = SocialCorpDAO.getInstance();
		TalentDevelopmentCorpDAO talentDevelopmentCorpDAO = TalentDevelopmentCorpDAO.getInstance();
		YouthFriendlyCorpDAO youthFriendlyCorpDAO = YouthFriendlyCorpDAO.getInstance();
		
		ArrayList<RecentSearchDTO> family_records = familyFriendlyCorpDAO.getRecentRecords(user_id);
		ArrayList<RecentSearchDTO> green_records = greenCorpDAO.getRecentRecords(user_id);
		ArrayList<RecentSearchDTO> social_records = socialCorpDAO.getRecentRecords(user_id);
		ArrayList<RecentSearchDTO> talent_records = talentDevelopmentCorpDAO.getRecentRecords(user_id);
		ArrayList<RecentSearchDTO> youth_records = youthFriendlyCorpDAO.getRecentRecords(user_id);
		
		List<RecentSearchDTO> recentRecords = new ArrayList<RecentSearchDTO>();
		
		recentRecords.addAll(family_records);
		recentRecords.addAll(green_records);
		recentRecords.addAll(social_records);
		recentRecords.addAll(talent_records);
		recentRecords.addAll(youth_records);
		
		Collections.sort(recentRecords, new CompareTime());
		
		return recentRecords;
	}
	
	public ArrayList<RecentSearchDTO> setCorpCount(int page, List<RecentSearchDTO> corpList) {
		ArrayList<RecentSearchDTO> corpListFor1page = new ArrayList<RecentSearchDTO>();
		
		int startNum = page * pageRowCount - pageRowCount;
		int lastNum = page * pageRowCount;
		
		for (int i = startNum; i < lastNum && i < corpList.size() ; i++) {
			corpListFor1page.add(corpList.get(i));
		}
		
		return corpListFor1page;
	}
	
	// ArrayList 정렬 용도 - 최근 검색 날짜 순으로 정렬
	static class CompareTime implements Comparator<RecentSearchDTO>{

		@Override
		public int compare(RecentSearchDTO o1, RecentSearchDTO o2) {
			// TODO Auto-generated method stub
			return o2.getSearch_date().compareTo(o1.getSearch_date());
		}
		
	}
}
