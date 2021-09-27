package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MemberDAO {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String rootId = "drawgreen"; //root
	String rootPw = "drawgreen2021"; //mysql
	String url = "jdbc:mysql://corpcollector.ciqetekukvwo.ap-northeast-2.rds.amazonaws.com:3306/Member";
	
	public MemberDAO(String userid, String userpw) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//grantUser(connection, preparedStatement, userid, userpw);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	//일반 사용자마다 DB접근권한 부여
	public void grantUser(Connection connection, PreparedStatement preparedStatement, String userid, String userpw) {
		
		try {
			connection = DriverManager.getConnection(url, rootId, rootPw);
			if (connection != null) System.out.println("연결 성공");
			
			String query = "CREATE USER '?'@'%' identified by '?'";
					//+ "GRANT select, insert, update, delete ON Member.* TO ?@'%' IDENTIFIED BY '?';"
					//+ "GRANT select ON Corp.* TO ?@'%' IDENTIFIED BY '?';";
					//+ "GRANT select, insert, update, delete ON Community.* TO ?@'%' IDENTIFIED BY '?';";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, userpw);
//			preparedStatement.setString(3, userid);
//			preparedStatement.setString(4, userpw);
//			preparedStatement.setString(5, userid);
//			preparedStatement.setString(6, userpw);
			boolean resultNum = preparedStatement.execute();
			
			if(resultNum == false)
				System.out.println("DB에 회원 계정 생성 및 권한 부여 성공");
			else
				System.out.println("DB에 회원 계정 생성 및 권한 부여 성공");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(connection!=null) connection.close();
				if(preparedStatement!=null) preparedStatement.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}*/
}
