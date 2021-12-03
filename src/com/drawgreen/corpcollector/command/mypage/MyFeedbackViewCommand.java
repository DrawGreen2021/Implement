package com.drawgreen.corpcollector.command.mypage;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FeedbackPostDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;
import com.drawgreen.corpcollector.dto.PostDTO;
import com.drawgreen.corpcollector.util.Pager;

public class MyFeedbackViewCommand implements Command{
	private Pager pager = new Pager();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		String page_str = request.getParameter("page");
		int page = Integer.parseInt(page_str);
		
		FeedbackPostDAO dao = FeedbackPostDAO.getInstance();
		ArrayList<PostDTO> postList = dao.getPostList_forMyPage(user.getId(), page);
		int rowCount = dao.getRowCount_forMyPage(user.getId());
		
		request.setAttribute("postList", postList);
		pager.setNumbers(page, rowCount, request, response);
	}

}
