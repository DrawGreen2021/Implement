package com.drawgreen.corpcollector.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.dao.MemberDAO;

public class FindPwCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean passwordCheck = dao.findPw(id, name, email);

		if (passwordCheck) {
			request.setAttribute("passwordCheck", passwordCheck);
			request.setAttribute("user_id", id);
		} else {
			request.setAttribute("errorMsg", "해당하는 정보를 찾을 수 없습니다.");
		}
	}

}
