package com.drawgreen.corpcollector.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.drawgreen.corpcollector.dto.NewsDTO;

public class NewsDAO {
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int allRowCount;
	private int pageRowCount;
	
	private NewsDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/DrawGreen");
			allRowCount = getRowCount("관련기사");
			pageRowCount = 10;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class InnerInstance_NewsDAO {
		private static final NewsDAO newsDAO = new NewsDAO();
	}
	
	public static NewsDAO getInstance() {
		return InnerInstance_NewsDAO.newsDAO;
	}
		
	public int getAllRowCount() {
		return allRowCount;
	}

	public void setAllRowCount(int allRowCount) {
		this.allRowCount = allRowCount;
	}

	public int getRowCount(String tableName) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		String query = "SELECT count(*) FROM " + tableName;

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
	
	public ArrayList<NewsDTO> getNewsList(int page) {
		ArrayList<NewsDTO> newsList = new ArrayList<NewsDTO>();
		String query = "SELECT * FROM 관련기사 LIMIT ?, ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, (page-1)*pageRowCount);
			preparedStatement.setInt(2, pageRowCount);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String title = resultSet.getString("title");
				String source = resultSet.getString("source");
				String link = resultSet.getString("link");
				String subTitle = resultSet.getString("subtitle");
				
				NewsDTO news = new NewsDTO(title, source, link, subTitle);
				newsList.add(news);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closing();
		}
		
		return newsList;
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
