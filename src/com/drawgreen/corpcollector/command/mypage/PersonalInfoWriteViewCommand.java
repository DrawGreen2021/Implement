package com.drawgreen.corpcollector.command.mypage;

import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;

public class PersonalInfoWriteViewCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String birth_str = request.getParameter("birth");
		StringTokenizer tokenizer = new StringTokenizer(birth_str, "-");
		int birth_year = Integer.parseInt(tokenizer.nextToken());
		int birth_month = Integer.parseInt(tokenizer.nextToken());
		int birth_day = Integer.parseInt(tokenizer.nextToken());
		String gender = request.getParameter("gender");
		
		HashMap<String, Object> personalInfo = new HashMap<String, Object>();
		personalInfo.put("id", id);
		personalInfo.put("nickname", nickname);
		personalInfo.put("email", email);
		personalInfo.put("birth_year", birth_year);
		personalInfo.put("birth_month", birth_month);
		personalInfo.put("birth_day", birth_day);
		personalInfo.put("gender", gender);
		
		request.setAttribute("personalInfo", personalInfo);
	}

}
