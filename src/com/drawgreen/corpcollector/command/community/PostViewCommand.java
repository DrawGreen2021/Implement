package com.drawgreen.corpcollector.command.community;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FeedbackPostDAO;
import com.drawgreen.corpcollector.dao.MemberDAO;
import com.drawgreen.corpcollector.dao.NoticePostDAO;
import com.drawgreen.corpcollector.dao.PostDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class PostViewCommand implements Command{
	
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
			dao = FeedbackPostDAO.getInstance();
		}
		
		// 비공개 글이라면 작성자 또는 관리자만 볼 수 있음
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		
		// 글 비공개 설정이 되어있지 않은 경우
		if (dao.isAccessible(board_number)) {
			// 조회수 증가
			dao.updateHits(board_number);
			post = dao.getPost(board_number);
			request.setAttribute("post", post);
		} 
		// 글 비공개 설정이 되어 있으나 관리자 또는 글을 쓴 사람 본인일 경우
		else if (user != null) {
			if(dao.isWriter(user.getId(), board_number) 
						|| MemberDAO.getInstance().isAdmin(user.getId())) {
				// 조회수 증가
				dao.updateHits(board_number);
				post = dao.getPost(board_number);
				request.setAttribute("post", post);
			} else {
				request.setAttribute("post", null);
			}
		} else {
			request.setAttribute("post", null);
		}
		
	}
	
}
