package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FavoriteCorpDAO {
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
	
	private FavoriteCorpDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			pageRowCount = 10;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class InnerClass_FavoriteCorpDAO {
		private static final FavoriteCorpDAO favoriteCorpDAO = new FavoriteCorpDAO();
	}
	
	public static FavoriteCorpDAO getInstance() {
		return InnerClass_FavoriteCorpDAO.favoriteCorpDAO;
	}
	
	// 관심 기업 등록 여부 확인
	public boolean isRegistered(String user_id, int corp_serialNum, String corpType) {
		
		String query = "SELECT IF(count(*)=1, 'true', 'false') AS result "
				+ "FROM 관심기업 "
				+ "WHERE user_id = ? AND "+corpType+"_id=?";
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, corp_serialNum);
			
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
	
	// 관심 기업 등록
	public void addFavoriteCorp(String user_id, int corp_serialNum, String corpType, String CorpName) {
		
		String query = "INSERT INTO 관심기업 (user_id,"+corpType+"_id,corpName) "
				+ "VALUES(?,?,?)";
		
		try {
			rootConnection = DriverManager.getConnection(url, rootId, rootPw);
			preparedStatement = rootConnection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, corp_serialNum);
			preparedStatement.setString(3, CorpName);
			
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
	
	// 관심 기업 삭제
	public void deleteFavoriteCorp(String user_id, int corp_serialNum, String corpType) {
		String query = "DELETE FROM 관심기업 WHERE user_id=? AND "+corpType+"_id=?";
		
		try {
			rootConnection = DriverManager.getConnection(url, rootId, rootPw);
			preparedStatement = rootConnection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, corp_serialNum);
			
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
	
	// 키워드가 없을 경우 관심기업 아이디 불러오기
	public int[] getFavoirteSerialNums(int page, String corpType, String user_id, int allRowCount) {
		int[] favoriteSerialNums = new int[pageRowCount];
		
		// 관심 기업에서 유저 아이디가 현재 접속 중인 아이디이고, 기업 아이디가 페이지에 표시된 연번 사이에 있는 리스트
		String query = "SELECT "+corpType+"_id FROM 관심기업 "
				+ "WHERE user_id = ? AND "+corpType+"_id BETWEEN ? AND ? "
						+ "ORDER BY "+corpType+"_id ASC";
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, 1 + (page * pageRowCount - pageRowCount));
			preparedStatement.setInt(3, page*pageRowCount < allRowCount?
					page*pageRowCount:allRowCount);
			
			resultSet = preparedStatement.executeQuery();
			
			// 관심 기업 아이디를 배열에 저장
			while(resultSet.next()) {
				int corp_id = resultSet.getInt(corpType+"_id");
				favoriteSerialNums[corp_id % pageRowCount - 1] = corp_id;
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			favoriteSerialNums = null;
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
		
		return favoriteSerialNums;
	}
	
	// 키워드가 있을 경우 관심기업 아이디 불러오기
	public int[] getFavoirteSerialNums(int page, String corpType, String user_id, ArrayList<Integer> serialNums) {
		int[] favoriteSerialNums = new int[pageRowCount];
		
		// 관심 기업에서 유저 아이디가 현재 접속 중인 아이디이고, 기업 아이디가 페이지에 표시된 연번 사이에 있는 리스트
		String query = "SELECT "+corpType+"_id FROM 관심기업 "
				+ "WHERE user_id = ? AND "+corpType+"_id IN(";
		int startNum = page * pageRowCount - pageRowCount;
		int lastNum = page * pageRowCount;
		
		StringBuilder builder = new StringBuilder(query);

		builder.append(serialNums.get(startNum));
		for (int i = startNum+1; i < lastNum && i < serialNums.size(); i++) {
			builder.append(",");
			builder.append(serialNums.get(i));	
		}
		builder.append(") ORDER BY "+corpType+"_id ASC");
		query = builder.toString();
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			resultSet = preparedStatement.executeQuery();
			
			// serialNums 인덱스에 맞춰서 배열 값 저장
			while(resultSet.next()) {
				int corp_id = resultSet.getInt(corpType+"_id"); 
				favoriteSerialNums[serialNums.indexOf(corp_id) % pageRowCount] = corp_id;
			}
		} catch (Exception e) {
			// TODO: handle exception
			favoriteSerialNums = null;
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
		
		return favoriteSerialNums;
	}
	
	// 기업 테이블명 가져오기
	public String getTableName(String corpType) {
		String tableName = "";
		
		switch (corpType) {
		case "greenCorp":
			tableName = "녹색기업";
			break;
		case "talentDevelopmentCorp":
			tableName = "인재육성형중소기업";
			break;
		case "socialCorp":
			tableName = "사회적기업";
			break;
		case "familyFriendlyCorp":
			tableName = "가족친화인증기업";
			break;
		case "youthFriendlyCorp":
			tableName = "청년친화강소기업";
			break;
		default:
			break;
		}
		
		return tableName;
	}
}
