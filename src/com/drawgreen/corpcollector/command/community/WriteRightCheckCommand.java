package com.drawgreen.corpcollector.command.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.MemberDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class WriteRightCheckCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String boardName = request.getParameter("boardName");
		if (user == null)
			out.print("not-login");
		if (boardName.equals("공지사항")) {
			MemberDAO dao = MemberDAO.getInstance();
			boolean isAdmin = dao.isAdmin(user.getId());
			if (isAdmin) {
				out.print("accessible");
			} else out.print("inaccessible");
		} else {
			out.print("accessible");
		}
		
	}

}
