package com.drawgreen.corpcollector.command;


import java.io.IOException;
import java.io.PrintWriter;

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
		boolean signUpCheck = dao.insertMember(id, pw, name, email, birth, gender);
		
		if (signUpCheck) {
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>");
				out.println("alert('가입되었습니다. 메인 페이지에서 다시 로그인해 주세요.'); ");
				out.println("location.href='../index.jsp';");
				out.println("</script>");
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
