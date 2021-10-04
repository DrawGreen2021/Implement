package com.drawgreen.corpcollector.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.dao.MemberDAO;

public class UpdatePwCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean updateCheck = dao.updatePW(id, pw);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			
			if(updateCheck) {
				out.println("<script>");
				out.println("alert('비밀번호 수정이 완료되었습니다.');");
				out.println("location.href='login_main.jsp';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('비밀번호 수정에 이상이 생겼습니다.');");
				out.println("location.href='../index.jsp';");
				out.println("</script>");
			}
			
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
