package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.drawgreen.corpcollector.dto.InterCorpDTO;
import com.drawgreen.corpcollector.dto.RecentSearchDTO;

public class InterCorpDAO implements CorpDAO {
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int allRowCount;
	// 키워드 검색 결과에 해당하는 연번을 저장할 리스트
	private ArrayList<Integer> serialNums;
	// 키워드 값을 저장할 변수
	private String beforeKeyword;

	private InterCorpDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/DrawGreen");
			allRowCount = getRowCount("Inter_corp");
			serialNums = new ArrayList<Integer>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class InnerInstance_CorpDAO {
		private static final InterCorpDAO interCorpDAO = new InterCorpDAO();
	}

	public static InterCorpDAO getInstance() {
		return InnerInstance_CorpDAO.interCorpDAO;
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

	// 검색 키워드가 없다면 1페이지 행 개수만큼 불러오기
	@Override
	public ArrayList<InterCorpDTO> getCorpList(int page) {
		ArrayList<InterCorpDTO> interCorpDTOs = new ArrayList<InterCorpDTO>();
		String query = "SELECT * FROM Inter_corp " + "LIMIT ?, ?";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, page * pageRowCount - pageRowCount);
			preparedStatement.setInt(2, pageRowCount);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String location = resultSet.getString("소재지");
				String sector = resultSet.getString("업종");
				String corpType = resultSet.getString("기업유형");

				InterCorpDTO dto = new InterCorpDTO(serial_number, company_name, location, sector, corpType);
				interCorpDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		return interCorpDTOs;
	}

	// 검색어가 있을 경우 기업 리스트 받아오기
	@Override
	public ArrayList<InterCorpDTO> getCorpList(String keyword, int page) {
		ArrayList<InterCorpDTO> interCorpDTOs = new ArrayList<InterCorpDTO>();

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

		String getCorpListQuery = "SELECT * FROM Inter_corp WHERE 연번 IN(";
		// 0~9, 10~19 ...
		int startNum = page * pageRowCount - pageRowCount;
		int lastNum = page * pageRowCount;

		StringBuilder builder = new StringBuilder(getCorpListQuery);

		builder.append(serialNums.get(startNum));
		for (int i = startNum + 1; i < lastNum && i < serialNums.size(); i++) {
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
				String corpType = resultSet.getString("기업유형");

				InterCorpDTO dto = new InterCorpDTO(serial_number, company_name, location, sector, corpType);
				interCorpDTOs.add(dto);
			}
		} catch (Exception e) {
			interCorpDTOs = null;
		} finally {
			closing();
		}
		return interCorpDTOs;
	}

	// 검색 키워드가 존재하는 행의 기업명 알아오기
	@Override
	public ArrayList<Integer> setSerialNum(String keyword, ArrayList<Integer> serialNums) {
		// 키워드 공백으로 분리
		StringTokenizer tokenizer = new StringTokenizer(keyword);

		String query = "SELECT 연번 FROM interCorp_view " + "WHERE text REGEXP('";
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
			e.printStackTrace();
		} finally {
			closing();
		}

		return serialNums;
	}

	@Override
	public int getRowCount_byKeyword() {
		// TODO Auto-generated method stub
		return serialNums.size();
	}

	@Override
	public ArrayList<Integer> getSerialNums() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 통합 검색의 경우, 기존 테이블의 연번 가져오기
	public int getOriginalSerialNum(String corpName, String location, String sector, String tableName) {

		String query = "SELECT 연번 FROM " + tableName + " WHERE 업체명 = ? AND 소재지 = ? AND 업종 = ?";
		int original_serial_num = 0;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, corpName);
			preparedStatement.setString(2, location);
			preparedStatement.setString(3, sector);

			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			original_serial_num = resultSet.getInt(1);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}

		return original_serial_num;
	}

	@Override
	public LinkedHashMap<String, Object> getInfo(int serial_num) {
		LinkedHashMap<String, Object> corpInfo = new LinkedHashMap<String, Object>();
		
		String query = "SELECT 업체명, 소재지, 업종, 기업유형 FROM Inter_corp WHERE 연번 = ?";
		String corpName = "";
		String location ="";
		String sector="";
		String tableName = "";
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, serial_num);
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			corpName = resultSet.getString("업체명");
			location = resultSet.getString("소재지");
			sector = resultSet.getString("업종");
			tableName = resultSet.getString("기업유형");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		int original_serial_num = getOriginalSerialNum(corpName, location, sector, tableName);
		String corpType = getCorpType(tableName);
		CorpDAO dao = null;
		
		if (corpType.equals("greenCorp")) {
			dao = GreenCorpDAO.getInstance();
		} else if (corpType.equals("talentDevelopmentCorp")) {
			dao = TalentDevelopmentCorpDAO.getInstance();
		} else if (corpType.equals("socialCorp")) {
			dao = SocialCorpDAO.getInstance();
		} else if (corpType.equals("familyFriendlyCorp")) {
			dao = FamilyFriendlyCorpDAO.getInstance();
		} else if (corpType.equals("youthFriendlyCorp")) {
			dao = YouthFriendlyCorpDAO.getInstance();
		}
		
		corpInfo = dao.getInfo(original_serial_num);
		
		return corpInfo;
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

	@Override
	public ArrayList<RecentSearchDTO> getRecentRecords(String user_id) {
		// TODO Auto-generated method stub
		return null;
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
