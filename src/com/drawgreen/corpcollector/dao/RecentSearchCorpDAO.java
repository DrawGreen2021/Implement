package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	private RecentSearchCorpDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
		String select_query = "INSERT INTO 최근검색기업(user_id,"+corpType+"_id, corpName, search_date) "
				+ "VALUES(?,?,?,?)";
		
		try {
			connection = DriverManager.getConnection(url, rootId, rootPw);
			preparedStatement = connection.prepareStatement(select_query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, serial_num);
			preparedStatement.setString(3, corpName);
			preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
			
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
}
