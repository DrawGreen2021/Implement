package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class MemberDAO {
	Connection connection = null;
	Connection rootConnection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	private String userId = "general_user_id";
	private String userPw = "general_user_password"; 
	private String rootId = "drawgreen";
	private String rootPw = "drawgreen2021"; 
	private String url = "jdbc:mysql://corpcollector.ciqetekukvwo.ap-northeast-2.rds.amazonaws.com:3306/Member";
	
	public MemberDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean idCheck(String id) {
		if(id == null || id.length() == 0) throw new NullPointerException("아이디가 없습니다.");
		
		String query = "select if(count(*)=1, 'true', 'false') as result"
                + " from members"
                + " where id = ?";
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
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
	
	public void insertMember(String id, String pw, String name, String email, String birth, String gender) {
		String query = "INSERT INTO members values (?, ?, ?, ?, ?, ?)";
		
		try {
			rootConnection = DriverManager.getConnection(url, rootId, rootPw);
			PreparedStatement preparedStatement = rootConnection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pw);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, email);
			preparedStatement.setDate(5, java.sql.Date.valueOf(birth));
			preparedStatement.setString(6, gender);
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rootConnection != null) rootConnection.close();
				if (preparedStatement != null) preparedStatement.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

}
