package com.drawgreen.corpcollector.command.community;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.NoticePostDAO;
import com.drawgreen.corpcollector.dao.PostDAO;

public class LoadPostCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String board_number_str = request.getParameter("board_number");
		int board_number = Integer.parseInt(board_number_str);
		PostDAO dao = null;
		HashMap<String, Object> post = null;
		
		String boardName = request.getParameter("boardName");
		if (boardName.equals("공지사항")) {
			dao = NoticePostDAO.getInstance();
		} else {
			
		}
		
		post = dao.getPost(board_number);
		request.setAttribute("post", post);
	}

}
