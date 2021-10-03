package com.drawgreen.corpcollector.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.dao.MemberDAO;

public class FindIdCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		MemberDAO dao = MemberDAO.getInstance();
		String id = dao.findID(name, email);
		
		if (id == null || id.equals("")) {
			request.setAttribute("errorMsg", "아이디가 존재하지 않습니다.");
		}
		else {
			request.setAttribute("nickname", name);
			request.setAttribute("found_ID", id);
		}
		
	}

}
