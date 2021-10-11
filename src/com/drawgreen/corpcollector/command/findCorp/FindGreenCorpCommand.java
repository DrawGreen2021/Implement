package com.drawgreen.corpcollector.command.findCorp;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.GreenCorpDAO;
import com.drawgreen.corpcollector.dto.GreenCorpDTO;
import com.drawgreen.corpcollector.util.Pager;

public class FindGreenCorpCommand implements Command{
	Pager pager = new Pager();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword")==null?"":request.getParameter("keyword");
		String page_str = request.getParameter("page");
		int page = Integer.parseInt(page_str);
		
		GreenCorpDAO dao = GreenCorpDAO.getInstance();
		ArrayList<GreenCorpDTO> corpList = null;
		int rowCount = 0;
		
		// 키워드 없을 때
		if(keyword.equals("")) {
			corpList = dao.getCorpList(page);
			rowCount = dao.getAllRowCount();
		}
		// 키워드가 있을 때
		else {
			corpList = dao.getCorpList(keyword, page);
			rowCount = dao.getRowCount_byKeyword();
		}
		
		// 검색 결과가 null이 아니라면 request에 corpList 저장 후 페이지 번호 설정
		if (corpList != null) {
			request.setAttribute("GreeenCorpList", corpList);
			pager.setNumbers(page, rowCount, request, response);
		}	
		else {
			request.setAttribute("GreeenCorpList", "noResult");
		}
			
	}
	
}
