package com.drawgreen.corpcollector.command.mypage;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FavoriteCorpDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;
import com.drawgreen.corpcollector.util.Pager;

public class FavoriteCorpViewCommand implements Command{
	Pager pager = new Pager();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		String page_str = request.getParameter("page");
		int page = Integer.parseInt(page_str);
		
		FavoriteCorpDAO dao = FavoriteCorpDAO.getInstance();
		LinkedHashMap<String, LinkedHashMap<Integer, String>> favCorpMap = dao.getFavoriteSerialNums_forMyPage(user.getId(), page);
		int rowCount = dao.getRowCount(user.getId(), page);
		
		request.setAttribute("favCorpMap", favCorpMap);
		pager.setNumbers(page, rowCount, request, response);
	}

}
