package com.drawgreen.corpcollector.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.dao.MemberDAO;

public class SignUpCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String birth = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
		String gender = request.getParameter("gender");
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(id, pw, name, email, birth, gender);
	}
}
