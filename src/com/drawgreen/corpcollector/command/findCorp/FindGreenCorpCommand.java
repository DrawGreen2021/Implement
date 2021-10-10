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
	ServletContext context;
	Pager pager = new Pager();
	
	public FindGreenCorpCommand(ServletContext context) {
		this.context = context;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword")==null?"":request.getParameter("keyword");
		String page_str = request.getParameter("page");
		int page = Integer.parseInt(page_str);
		String corpType = (String) context.getAttribute("greenCorp");
		
		
		GreenCorpDAO dao = GreenCorpDAO.getInstance();
		ArrayList<GreenCorpDTO> corpList = null;
		int rowCount = 0;
		
		// 초기 리스트 불러오기
		if(!dao.isAllSearched()) {
			corpList = dao.getCorpList(page);
			rowCount = dao.getRowCount(corpType);
			context.setAttribute("allGreenRows", rowCount);

			dao.setAllSearched(true);
		}
		// 키워드 없이 검색 버튼을 눌렀을 때
		else if(keyword.equals("")) {
			corpList = dao.getCorpList(page);
			rowCount = (int) context.getAttribute("allGreenRows");
		}
		// 키워드가 있는 경우
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
