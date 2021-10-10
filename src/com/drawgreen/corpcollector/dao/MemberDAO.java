package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.dto.MemberDTO;

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
	
	private MemberDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class InnerInstance_MemberDAO {
		private static final MemberDAO memberDAO = new MemberDAO();
	}
	
	public static MemberDAO getInstance() {
		return InnerInstance_MemberDAO.memberDAO;
	}
	
	public boolean idCheck(String id) {
		if(id == null || id.length() == 0) throw new NullPointerException("아이디가 없습니다.");
		
		String query = "SELECT IF(count(*)=1, 'true', 'false') AS result"
                + " FROM members"
                + " WHERE id = ?";
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			
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

	
	public boolean insertMember(String id, String pw, String name, String email, String birth, String gender) {
		String query = "INSERT INTO members values (?, ?, ?, ?, ?, ?)";
		boolean singUpCheck = false;
		try {
			rootConnection = DriverManager.getConnection(url, rootId, rootPw);
			PreparedStatement preparedStatement = rootConnection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pw);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, email);
			preparedStatement.setDate(5, java.sql.Date.valueOf(birth));
			preparedStatement.setString(6, gender);
			
			int resultNum = preparedStatement.executeUpdate();
			
			if (resultNum > 0) {
				singUpCheck = true;
			}
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
		return singUpCheck;
	}
	
	
	public boolean login(String id, String pw, HttpServletRequest request, HttpServletResponse response) {
		if(id == null || id.length() == 0) throw new NullPointerException("아이디가 없습니다.");
		
		String query = "SELECT * FROM members WHERE id=? AND password=?";
		boolean loginCheck = false;
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pw);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				String nickname = resultSet.getString("nickname");
				String email = resultSet.getString("email");
				String birth = resultSet.getDate("birth").toString();
				String gender = resultSet.getString("gender");
				MemberDTO dto = new MemberDTO(id, pw, nickname, email, birth, gender);
				
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("MemberDTO", dto);
				
				loginCheck = true;
			}
            
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
		
		return loginCheck;
	}
	
	public String findID(String name, String email) {
		String user_id = "";
		String query = "SELECT id FROM members WHERE nickname=? AND email=?";
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				user_id = resultSet.getString("id");
			
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
		
		return user_id;
	}
	
	public boolean findPw(String id, String name, String email) {
		boolean passwordCheck = false;
		String query = "SELECT password FROM members "
				+ "WHERE id=? AND nickname=? AND email=?";
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.first()) {
				passwordCheck = true;
			}
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
		
		return passwordCheck;
	}
	
	public boolean updatePW(String id, String pw) {
		boolean updateCheck = false;
		String query = "UPDATE members SET password=? "
				+ "WHERE id=?";
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pw);
			preparedStatement.setString(2, id);
			
			int result = preparedStatement.executeUpdate();
			if(result != 0)
				updateCheck = true;
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
		
		return updateCheck;
	}
}
