package com.drawgreen.corpcollector.command.member;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;

public class LogoutCommand implements Command{
	String[] loginRequiredPages = {"community/feedback_Write.jsp", "community/notice_Write.jsp",
			"community/feedback_Page.jsp", "community/notice_Page.jsp",
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
			
			// 이전 페이지가 로그인이 필요한 페이지였다면 인덱스 페이지로 이동
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
