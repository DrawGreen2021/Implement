package com.drawgreen.corpcollector.command.community;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.NoticePostDAO;
import com.drawgreen.corpcollector.dto.PostDTO;
import com.drawgreen.corpcollector.util.Pager;

public class SearchPostCommand implements Command{
	Pager pager = new Pager();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		String page_str = request.getParameter("page");
		int page = Integer.parseInt(page_str);
		
		NoticePostDAO dao = NoticePostDAO.getInstance();
		ArrayList<PostDTO> postList = null;
		int rowCount = 0;
		
		// 키워드 없을 때
		if (keyword.equals("")) {
			postList = dao.getPostList(page);
			rowCount = dao.getAllRowCount();
		} 
		// 키워드 있을 때
		else {
			postList = dao.getPostList(keyword, page);
			rowCount = dao.getRowCount_byKeyword();
		}
		
		if (postList != null) {
			request.setAttribute("postList", postList);
			pager.setNumbers(page, rowCount, request, response);
		} else {
			request.setAttribute("postList", "noResult");
		}
	}

}
