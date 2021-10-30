package com.drawgreen.corpcollector.command.member;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;

public class LogoutCommand implements Command{
	String[] loginRequiredPages = {"community/feedback_Write.jsp", "community/notice_Write.jsp",
			"myPage/favoriteCorp.jsp", "myPage/myFeedback.jsp", "myPage/personalInformation_write.jsp",
			"myPage/personalInformation.jsp", "myPage/recentSearch.jsp"};
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String beforeUrl = request.getParameter("beforeUrl");
		String URI = request.getParameter("URI");
		String contextPath = request.getParameter("contextPath");
		
		String page = URI.substring(contextPath.length()+1);

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("MemberDTO");

		PrintWriter out;
		try {
			out = response.getWriter();
			
			if (Arrays.asList(loginRequiredPages).contains(page)) {
				out.println("<script>");
				out.println("alert('로그아웃 되었습니다.');");
				out.println("location.href='../index.jsp';");
				out.println("</script>");
			}
			else {
				out.println("<script>");
				out.println("alert('로그아웃 되었습니다.');");
				out.println("location.href='"+beforeUrl+"';");
				out.println("</script>");
			}
			out.close();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		
	}

}
