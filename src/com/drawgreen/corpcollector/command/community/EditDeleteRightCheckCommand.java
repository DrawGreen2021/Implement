package com.drawgreen.corpcollector.command.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FeedbackPostDAO;
import com.drawgreen.corpcollector.dao.MemberDAO;
import com.drawgreen.corpcollector.dao.NoticePostDAO;
import com.drawgreen.corpcollector.dao.PostDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class EditDeleteRightCheckCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String board_number_str = request.getParameter("board_number");
		int board_number = Integer.parseInt(board_number_str);
		String boardName = request.getParameter("boardName");
		
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PostDAO dao = null;
		if (boardName.equals("공지사항")) {
			dao = NoticePostDAO.getInstance();
		}
		else {
			dao = FeedbackPostDAO.getInstance();
		}
		
		boolean isWriter = false;
		
		
		if (user == null) {
			out.print("not-login");
		} else {
			isWriter = dao.isWriter(user.getId(), board_number);
			
			if (isWriter) {
				out.print("accessible");
			} else out.print("inaccessible");
		}
	}

}
