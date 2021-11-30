package com.drawgreen.corpcollector.command.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.MemberDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class ResignMembershipCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean resigned = dao.deleteMember(user.getId());
		
		if (resigned) {
			out.print("resignOk");
		} else {
			out.print("error");
		}
	}

}
