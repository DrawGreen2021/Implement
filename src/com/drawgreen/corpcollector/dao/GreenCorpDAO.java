package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.drawgreen.corpcollector.dto.GreenCorpDTO;

public class GreenCorpDAO implements CorpDAO{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	private String userId = "general_user_id";
	private String userPw = "general_user_password"; 
	private String url = "jdbc:mysql://corpcollector.ciqetekukvwo.ap-northeast-2.rds.amazonaws.com:3306/Corp";
	
	private GreenCorpDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class InnerInstance_CorpDAO {
		private static final GreenCorpDAO greenCorpDAO = new GreenCorpDAO();
	}
	
	public static GreenCorpDAO getInstance() {
		return InnerInstance_CorpDAO.greenCorpDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<GreenCorpDTO> getCorpList(String keyword) {
		// TODO Auto-generated method stub
		ArrayList<GreenCorpDTO> greenCorpDTOs = new ArrayList<GreenCorpDTO>();
		String query = "SELECET * FROM Corp.'녹색기업' "
				+ "WHERE * LIKE '%?%'";
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, keyword);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String location = resultSet.getString("소재지");
				String sector = resultSet.getString("업종");
				String note = resultSet.getString("비고");
				String region = resultSet.getString("지역구분");
				String site = resultSet.getString("사이트주소");
				
				GreenCorpDTO dto = new GreenCorpDTO(serial_number, company_name, location, sector, note, region, site);
				greenCorpDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null) connection.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(resultSet!=null) resultSet.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return greenCorpDTOs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<GreenCorpDTO> getCorpList() {
		// TODO Auto-generated method stub
		ArrayList<GreenCorpDTO> greenCorpDTOs = new ArrayList<GreenCorpDTO>();
		String query = "SELECET * FROM Corp.'녹색기업'";
		
		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String location = resultSet.getString("소재지");
				String sector = resultSet.getString("업종");
				String note = resultSet.getString("비고");
				String region = resultSet.getString("지역구분");
				String site = resultSet.getString("사이트주소");
				
				GreenCorpDTO dto = new GreenCorpDTO(serial_number, company_name, location, sector, note, region, site);
				greenCorpDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(connection!=null) connection.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(resultSet!=null) resultSet.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return greenCorpDTOs;
	}

}
