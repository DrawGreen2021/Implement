package com.drawgreen.corpcollector.command.findCorp;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.GreenCorpDAO;
import com.drawgreen.corpcollector.dto.GreenCorpDTO;
import com.drawgreen.corpcollector.util.Pager;

public class FindGreenCorpCommand implements Command{
	ServletContext context;
	Pager pager = new Pager();
	
	public FindGreenCorpCommand(ServletContext context) {
		this.context = context;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String keyword = request.getParameter("keyword")==null?"":request.getParameter("keyword");  
		String field = request.getParameter("field")==null?"":request.getParameter("field");
		String page_str = request.getParameter("page")==null?"1":request.getParameter("page");
		int page = Integer.parseInt(page_str);
		String corpType = (String) context.getAttribute("greenCorp");
		
		System.out.println("page: "+page);
		GreenCorpDAO dao = GreenCorpDAO.getInstance();
		ArrayList<GreenCorpDTO> corpList = null;
		int rowCount = 0;
		
		if(!dao.isAllSearched()) {
			corpList = dao.getCorpList(page);
			rowCount = dao.getRowCount(corpType);
			context.setAttribute("allGreenRows", rowCount);

			dao.setAllSearched(true);
		}
		else if(keyword.equals("")) {
			corpList = dao.getCorpList(page);
			rowCount = (int) context.getAttribute("allGreenRows");
		}
		else {
			corpList = dao.getCorpList(field, keyword, page);
			rowCount = dao.getRowCount(corpType, field, keyword);
		}
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("GreeenCorpList", corpList);
		httpSession.setAttribute("rowCount", rowCount);

		pager.setNumbers(page, rowCount, request, response);
	}
	
}
