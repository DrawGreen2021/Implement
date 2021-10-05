package com.drawgreen.corpcollector.command.member;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.MemberDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class LoginCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean loginCheck = dao.login(id, pw, request, response);
		PrintWriter out;
		try {
			out = response.getWriter();
			
			if (loginCheck) {
				HttpSession httpSession = request.getSession();
				MemberDTO dto = (MemberDTO)httpSession.getAttribute("MemberDTO");
				
				out.println("<script>");
				out.println("alert('"+dto.getName()+"님 환영합니다.');");
				out.println("location.href='../index.jsp';");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('아이디와 비밀번호를 다시 확인해 주세요.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
