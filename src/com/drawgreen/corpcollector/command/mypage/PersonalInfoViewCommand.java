package com.drawgreen.corpcollector.command.mypage;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.MemberDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class PersonalInfoViewCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		MemberDAO dao = MemberDAO.getInstance();
		
		HashMap<String, Object> personalInfo = dao.getPersonalInfo(user.getId());
		request.setAttribute("personalInfo", personalInfo);
	}

}
