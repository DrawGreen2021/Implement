package com.drawgreen.corpcollector.command.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FeedbackPostDAO;
import com.drawgreen.corpcollector.dao.NoticePostDAO;
import com.drawgreen.corpcollector.dao.PostDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class DeletePostCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String board_number_str = request.getParameter("board_number");
		int board_number = Integer.parseInt(board_number_str);
		String boardName = request.getParameter("boardName");
		
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		
		String nextPage = "";
		PostDAO dao = null;
		if (boardName.equals("공지사항")) {
			nextPage = "notice.jsp";
			dao = NoticePostDAO.getInstance();
		}
		else {
			nextPage = "feedback.jsp";
			dao = FeedbackPostDAO.getInstance();
		}
		
		boolean deleteOk = dao.deletePost(board_number, user.getId());
		
		if (deleteOk) {
			dao.resetBoardId();
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글이 삭제되었습니다.'); ");
				out.println("location.href='"+nextPage+"';");
				out.println("</script>");
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
