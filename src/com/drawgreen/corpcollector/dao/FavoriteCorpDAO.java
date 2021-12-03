package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.drawgreen.corpcollector.dto.InterCorpDTO;

public class FavoriteCorpDAO {
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int pageRowCount;
	
	private FavoriteCorpDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/DrawGreen");
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
			connection = dataSource.getConnection();
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
			closing();
		}
		
		return false;
	}
	
	// 관심 기업 등록
	public void addFavoriteCorp(String user_id, int corp_serialNum, String corpType, String CorpName) {
		
		String query = "INSERT INTO 관심기업 (user_id,"+corpType+"_id,corpName) "
				+ "VALUES(?,?,?)";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, corp_serialNum);
			preparedStatement.setString(3, CorpName);
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
	}
	
	// 관심 기업 삭제
	public void deleteFavoriteCorp(String user_id, int corp_serialNum, String corpType) {
		String query = "DELETE FROM 관심기업 WHERE user_id=? AND "+corpType+"_id=?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, corp_serialNum);
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
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
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, 1 + (page * pageRowCount - pageRowCount));
			preparedStatement.setInt(3, page*pageRowCount < allRowCount?
					page*pageRowCount:allRowCount);
			
			resultSet = preparedStatement.executeQuery();
			
			// 관심 기업 아이디를 배열에 저장
			while(resultSet.next()) {
				int corp_id = resultSet.getInt(corpType+"_id");
				favoriteSerialNums[(corp_id - 1) % pageRowCount] = corp_id;
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			favoriteSerialNums = null;
		} finally {
			closing();
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
			connection = dataSource.getConnection();
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
			closing();
		}
		
		return favoriteSerialNums;
	}

	// 통합 검색일 경우, 관심 기업 불러오기
	public int[] getFavoriteSerialNums(String user_id, ArrayList<InterCorpDTO> corpList) {
		int[] favoriteSerialNums = new int[pageRowCount];
		
		String corpType = null;
		try {
			connection = dataSource.getConnection();
			
			for (int i = 0; i < corpList.size(); i++) {
				InterCorpDTO dto = corpList.get(i);
				corpType = getCorpType(dto.getCorpType());
				String query = "select i.연번 from " + 
						"(select 업체명, 소재지, 업종 from "+dto.getCorpType()+" sub" + 
						" where 업체명 = ? and 소재지 = ? and 업종 = ? and 연번 IN" + 
						" (select IFNULL("+corpType+"_id,0) from 관심기업 where user_id = ? and sub.연번 = "+corpType+"_id))origin, " + 
						"Inter_corp i where i.업체명 = origin.업체명 and i.소재지 = origin.소재지 and i.업종 = origin.업종;";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, dto.getCompany_name());
				preparedStatement.setString(2, dto.getLocation());
				preparedStatement.setString(3, dto.getSector());
				preparedStatement.setString(4, user_id);
				
				resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next())
					favoriteSerialNums[i] = resultSet.getInt(1);
				else continue;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return favoriteSerialNums;
	}
	
	// 기업 테이블명 가져오기
	public String getCorpType(String tableName) {
		String corpType = "";

		switch (tableName) {
		case "녹색기업":
			corpType = "greenCorp";
			break;
		case "인재육성형중소기업":
			corpType = "talentDevelopmentCorp";
			break;
		case "사회적기업":
			corpType = "socialCorp";
			break;
		case "가족친화인증기업":
			corpType = "familyFriendlyCorp";
			break;
		case "청년친화강소기업":
			corpType = "youthFriendlyCorp";
			break;
		default:
			break;
		}

		return corpType;
	}
	
	// 마이페이지 - 관심기업에 표시할 관심기업 연번 및 업체명 얻어오기
	public LinkedHashMap<String, LinkedHashMap<Integer, String>> getFavoriteCorpMap(String user_id, int page) {
		
		LinkedHashMap<String, LinkedHashMap<Integer, String>> favCorpMap 
			= new LinkedHashMap<String, LinkedHashMap<Integer, String>>();
		
		LinkedHashMap<Integer, String> familyFriendlyCorp = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> greenCorp = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> socialCorp = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> talentDevelopmentCorp = new LinkedHashMap<Integer, String>();
		LinkedHashMap<Integer, String> youthFriendlyCorp = new LinkedHashMap<Integer, String>();
		
		String query = "SELECT * FROM 관심기업 WHERE user_id = ? LIMIT ?,?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setInt(2, (page-1)*pageRowCount);
			preparedStatement.setInt(3, pageRowCount);
			
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next())
				return null;
			do {
				if (resultSet.getInt("familyFriendlyCorp_id")!=0)
					familyFriendlyCorp.put(resultSet.getInt("familyFriendlyCorp_id"), resultSet.getString("corpName"));
				else if (resultSet.getInt("greenCorp_id")!=0)
					greenCorp.put(resultSet.getInt("greenCorp_id"), resultSet.getString("corpName"));
				else if (resultSet.getInt("socialCorp_id")!=0)
					socialCorp.put(resultSet.getInt("socialCorp_id"), resultSet.getString("corpName"));
				else if (resultSet.getInt("talentDevelopmentCorp_id")!=0)
					talentDevelopmentCorp.put(resultSet.getInt("talentDevelopmentCorp_id"), resultSet.getString("corpName"));
				else if (resultSet.getInt("youthFriendlyCorp_id")!=0)
					youthFriendlyCorp.put(resultSet.getInt("youthFriendlyCorp_id"), resultSet.getString("corpName"));
					
			} while (resultSet.next());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		favCorpMap.put("familyFriendlyCorp", familyFriendlyCorp);
		favCorpMap.put("greenCorp", greenCorp);
		favCorpMap.put("socialCorp", socialCorp);
		favCorpMap.put("talentDevelopmentCorp", talentDevelopmentCorp);
		favCorpMap.put("youthFriendlyCorp", youthFriendlyCorp);
		
		return favCorpMap;
	}
	
	// 사용자가 등록한 관심기업 개수 가져오기
	public int getRowCount(String user_id) {
		String query = "SELECT count(*) FROM 관심기업 WHERE user_id = ?";
		int count = 0;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return count;
	}
	
	// 관심 기업 테이블에서 기업 정보 삭제
	public void deleteFavCorp(String user_id, HashMap<String, int[]> idMap) {
		
		String query = "DELETE FROM 관심기업 "
				+ "WHERE user_id = ? AND (";
		String[] inQuery = new String[5];
		Arrays.fill(inQuery, "");
		StringBuilder builder = null;
		
		Set<Entry<String, int[]>> set = idMap.entrySet();
		Iterator<Entry<String, int[]>> iterator = set.iterator();
		for (int i = 0; i < idMap.size(); i++) {
			Entry<String, int[]> entry = iterator.next();
			if (entry.getValue() != null) {
				builder = new StringBuilder(inQuery[i]);
				builder.append(entry.getKey()+" IN("+entry.getValue()[0]);
				for (int j = 1; j < entry.getValue().length; j++) {
					builder.append(","+entry.getValue()[j]);
				}
				builder.append(")");
				inQuery[i] = builder.toString();
			}
		}
		
		builder = new StringBuilder(query);
		boolean isFirst = true;

		for (int i = 0; i < inQuery.length; i++) {
			if (!inQuery[i].equals("") && isFirst) {
				builder.append(inQuery[i]);
				isFirst = false;
			} else if (!inQuery[i].equals("")) {
				builder.append(" OR "+inQuery[i]);
			}
		}
		builder.append(")");
		query = builder.toString();
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
	}
	
	// 사용자의 관심 기업 목록 초기화
	public boolean resetCorpList(String user_id) {
		boolean resetOk = false;
		
		String query = "DELETE FROM 관심기업 WHERE user_id = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			
			int resultNum = preparedStatement.executeUpdate();
			
			if(resultNum > 0) {
				resetOk = true;
			} else resetOk = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return resetOk;
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
