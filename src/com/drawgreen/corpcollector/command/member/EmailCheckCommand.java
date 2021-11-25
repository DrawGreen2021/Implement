package com.drawgreen.corpcollector.command.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.util.ServerLogin;

public class EmailCheckCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String email_auth_code = request.getParameter("email_auth_code");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean emailAuthNumCheck = checkAuthEmail(email_auth_code);
		
		if (emailAuthNumCheck) {
			out.print("authenticated");
		} else {
			out.print("not-authenticated");
		}
	}
	
	public boolean checkAuthEmail(String email_auth_code) {
		ServerLogin serverLogin = ServerLogin.getInstance();
		
		if (email_auth_code.equals(serverLogin.getAuthEamilCode())) {
			return true;
		} else {
			return false;
		}
		
	}
}
