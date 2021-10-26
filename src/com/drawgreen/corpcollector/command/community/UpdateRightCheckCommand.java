package com.drawgreen.corpcollector.command.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.MemberDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class UpdateRightCheckCommand implements Command{

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
		
		boolean isAdmin = false;
		
		
		if (user == null) {
			out.print("not-login");
		} else {
			MemberDAO dao = MemberDAO.getInstance();
			isAdmin = dao.updateRightCheck(user.getId(), board_number, boardName);
			
			if (isAdmin) {
				out.print("accessible");
			} else out.print("inaccessible");
		}
	}

}
