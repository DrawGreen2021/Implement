package com.drawgreen.corpcollector.command.information;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.NewsDAO;
import com.drawgreen.corpcollector.dto.NewsDTO;
import com.drawgreen.corpcollector.util.Pager;

public class CorpNewsViewCommand implements Command{
	Pager pager = new Pager();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String page_str = request.getParameter("page");
		int page = Integer.parseInt(page_str);
		
		NewsDAO dao = NewsDAO.getInstance();
		ArrayList<NewsDTO> newsList = dao.getNewsList(page);
		int rowCount = dao.getAllRowCount();
		
		request.setAttribute("newsList", newsList);
		pager.setNumbers(page, rowCount, request, response);
	}

}
