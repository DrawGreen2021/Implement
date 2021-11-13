package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import com.drawgreen.corpcollector.dto.MemberDTO;

public class MemberDAO {
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private MemberDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/DrawGreen");
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
			connection = dataSource.getConnection();
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
			closing();
		}
		
		return false;
	}

	
	public boolean insertMember(String id, String pw, String name, String email, String birth, String gender) {
		String query = "INSERT INTO members values (?, ?, ?, ?, ?, ?)";
		boolean singUpCheck = false;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
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
			closing();
		}
		return singUpCheck;
	}
	
	
	public boolean login(String id, String pw, HttpServletRequest request, HttpServletResponse response) {
		if(id == null || id.length() == 0) throw new NullPointerException("아이디가 없습니다.");
		
		String query = "SELECT * FROM members WHERE id=? AND password=?";
		boolean loginCheck = false;
		try {
			connection = dataSource.getConnection();
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
			closing();
		}
		
		return loginCheck;
	}
	
	public String findID(String name, String email) {
		String user_id = "";
		String query = "SELECT id FROM members WHERE nickname=? AND email=?";
		
		try {
			connection = dataSource.getConnection();
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
			closing();
		}
		
		return user_id;
	}
	
	public boolean findPw(String id, String name, String email) {
		boolean passwordCheck = false;
		String query = "SELECT password FROM members "
				+ "WHERE id=? AND email=?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, email);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.first()) {
				passwordCheck = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return passwordCheck;
	}
	
	public boolean updatePW(String id, String pw) {
		boolean updateCheck = false;
		String query = "UPDATE members SET password=? "
				+ "WHERE id=?";
		
		try {
			connection = dataSource.getConnection();
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
			closing();
		}
		
		return updateCheck;
	}
	
	public boolean isAdmin(String id) {
		boolean isAdmin = false;
		String query = "SELECT level FROM 관리자 WHERE id = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next() && resultSet.getInt(1) == 1) {
				isAdmin = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return isAdmin;
	}
	
	public HashMap<String, Object> getPersonalInfo(String id) {
		HashMap<String, Object> personalInfo = new HashMap<String, Object>();
		
		String query = "SELECT * FROM members WHERE id = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			personalInfo.put("id", resultSet.getString("id"));
			personalInfo.put("nickname", resultSet.getString("nickname"));
			personalInfo.put("email", resultSet.getString("email"));
			personalInfo.put("birth", resultSet.getDate("birth"));
			personalInfo.put("gender", resultSet.getString("gender"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return personalInfo;
	}
	
	public void updatePersonalInfo(String origin_id, String id, String name, String email, String birth, String gender) {
		String query = "UPDATE members SET id=?, nickname=?, email=?, birth=?, gender=? "
				+ "WHERE id=?";

		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setDate(4, java.sql.Date.valueOf(birth));
			preparedStatement.setString(5, gender);
			preparedStatement.setString(6, origin_id);
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
	}
	
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
