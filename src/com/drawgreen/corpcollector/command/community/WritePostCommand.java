package com.drawgreen.corpcollector.command.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FeedbackPostDAO;
import com.drawgreen.corpcollector.dao.NoticePostDAO;
import com.drawgreen.corpcollector.dao.PostDAO;

public class WritePostCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String private_Writing = request.getParameter("private_Writing");
		String writer = request.getParameter("writer");
		String writer_id = request.getParameter("writer_id");
		String private_Writer = request.getParameter("private_Writer");
		String content = request.getParameter("content");
		String boardName = request.getParameter("boardName");
		
		String nextPage = "";
		PostDAO dao = null;
		if (boardName.equals("공지사항")) {
			nextPage = "notice.jsp";
			dao = NoticePostDAO.getInstance();
		}
		else if (boardName.equals("고객후기")){
			nextPage = "feedback.jsp";
			dao = FeedbackPostDAO.getInstance();
		}
		
		boolean writeOk = dao.writePost(title, private_Writing, writer, writer_id, private_Writer, content);
		
		if (writeOk) {
			dao.resetBoardId();
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글이 등록되었습니다.'); ");
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
