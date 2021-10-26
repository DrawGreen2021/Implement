package com.drawgreen.corpcollector.command.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FeedbackPostDAO;
import com.drawgreen.corpcollector.dao.NoticePostDAO;
import com.drawgreen.corpcollector.dao.PostDAO;

public class UpdatePostCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String private_Writing = request.getParameter("private_Writing");
		String private_Writer = request.getParameter("private_Writer");
		String content = request.getParameter("content");
		String boardName = request.getParameter("boardName");
		String board_number_str = request.getParameter("board_number");
		int board_number = Integer.parseInt(board_number_str);
		
		String nextPage = "PostView.do?board_number="+board_number+"&boardName="+boardName;
		PostDAO dao = null;
		if (boardName.equals("공지사항")) {
			dao = NoticePostDAO.getInstance();
		}
		else {
			dao = FeedbackPostDAO.getInstance();
		}
		
		boolean updateOk = dao.updatePost(board_number, title, private_Writing, private_Writer, content);
		
		if (updateOk) {
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글이 수정되었습니다.'); ");
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
