package com.drawgreen.corpcollector.command.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.RecentSearchCorpDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;
import com.drawgreen.corpcollector.dto.RecentSearchDTO;
import com.drawgreen.corpcollector.util.Pager;

public class RecentSearchViewCommand implements Command{
	Pager pager = new Pager();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		String page_str = request.getParameter("page");
		int page = Integer.parseInt(page_str);
		
		RecentSearchCorpDAO dao = RecentSearchCorpDAO.getInstance();
		List<RecentSearchDTO> corpList = dao.getCorpList(user.getId());
		corpList = dao.setCorpCount(page, corpList);
		int rowCount = corpList.size();
		
		request.setAttribute("corpList", corpList);
		pager.setNumbers(page, rowCount, request, response);
	}

}
