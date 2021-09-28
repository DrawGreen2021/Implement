package com.drawgreen.corpcollector.command;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.dao.MemberDAO;

public class JoinCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		//SimpleDateFormat transFormat = new SimpleDateFormat("yyyymmdd");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email1") + "@" + request.getParameterValues("email2");
		String birth = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
		String gender = request.getParameter("gender");
		
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.insertMember(id, pw, name, email, birth, gender);
	}
}
