package com.drawgreen.corpcollector.dao;

import java.sql.DriverManager;
import java.util.ArrayList;

import com.drawgreen.corpcollector.dto.GreenCorpDTO;


public class GreenCorpDAO extends CorpDAO {
	private boolean allSearched;
	private int pageRowCount;
	
	@Override
	public int getRowCount(String corpType, String field, String keyword) {
		// TODO Auto-generated method stub
		return super.getRowCount(corpType, field, keyword);
	}

	private GreenCorpDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			allSearched = false;
			pageRowCount = 10;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class InnerInstance_CorpDAO {
		private static final GreenCorpDAO greenCorpDAO = new GreenCorpDAO();
	}

	public static GreenCorpDAO getInstance() {
		return InnerInstance_CorpDAO.greenCorpDAO;
	}
	
	public boolean isAllSearched() {
		return allSearched;
	}

	public void setAllSearched(boolean allSearched) {
		this.allSearched = allSearched;
	}

	public ArrayList<GreenCorpDTO> getCorpList(int page) {
		ArrayList<GreenCorpDTO> greenCorpDTOs = new ArrayList<GreenCorpDTO>();
		String query = "SELECT * FROM 녹색기업 WHERE 연번 BETWEEN ? AND ?";

		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 1+(page*pageRowCount-pageRowCount));
			preparedStatement.setInt(2, page*pageRowCount);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String location = resultSet.getString("소재지");
				String sector = resultSet.getString("업종");
				String site = resultSet.getString("사이트주소");

				GreenCorpDTO dto = new GreenCorpDTO(serial_number, company_name, location, sector, site);
				greenCorpDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

		return greenCorpDTOs;
	}

	public ArrayList<GreenCorpDTO> getCorpList(String field, String keyword, int page) {
		// TODO Auto-generated method stub
		ArrayList<GreenCorpDTO> greenCorpDTOs = new ArrayList<GreenCorpDTO>();
		String query = "SELECT * FROM( " + "SELECT * FROM 녹색기업 WHERE "+field
				+ " LIKE ? ORDER BY 연번)search " 
				+ "LIMIT ?,?";

		try {
			connection = DriverManager.getConnection(url, userId, userPw);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+keyword+"%");
			preparedStatement.setInt(2, 1+(page*pageRowCount-pageRowCount));
			preparedStatement.setInt(3, page*pageRowCount);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int serial_number = resultSet.getInt("연번");
				String company_name = resultSet.getString("업체명");
				String location = resultSet.getString("소재지");
				String sector = resultSet.getString("업종");
				String site = resultSet.getString("사이트주소");

				GreenCorpDTO dto = new GreenCorpDTO(serial_number, company_name, location, sector, site);
				greenCorpDTOs.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

		return greenCorpDTOs;
	}

}
