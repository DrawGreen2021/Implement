package com.drawgreen.corpcollector.command.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.MemberDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class UpdatePersonalInfoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw")==null?"":request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String birth = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
		String gender = request.getParameter("gender");
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updatePersonalInfo(user.getId(), id, name, email, birth, gender);
		
		user.setId(id);
		user.setName(name);
		user.setEmail(email);
		user.setBirth(birth);
		user.setGender(gender);
		
		if (!pw.equals(user.getPw()) && !pw.equals("")) {
			dao.updatePW(id, pw);
			user.setPw(pw);
		}
			
	}

}
