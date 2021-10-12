package com.drawgreen.corpcollector.command.findCorp;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.CorpDAO;
import com.drawgreen.corpcollector.dao.FamilyFriendlyCorpDAO;
import com.drawgreen.corpcollector.dao.GreenCorpDAO;
import com.drawgreen.corpcollector.dao.SocialCorpDAO;
import com.drawgreen.corpcollector.dao.TalentDevelopmentCorpDAO;
import com.drawgreen.corpcollector.dao.YouthFriendlyCorpDAO;
import com.drawgreen.corpcollector.dto.CorpDTO;
import com.drawgreen.corpcollector.util.Pager;

public class FindCorpCommand implements Command {
	Pager pager = new Pager();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		String page_str = request.getParameter("page");
		int page = Integer.parseInt(page_str);

		CorpDAO dao = null;
		ArrayList<? extends CorpDTO> corpList = null;
		int rowCount = 0;
		String corpType = request.getParameter("corpType");

		if (corpType.equals("greenCorp")) {
			dao = GreenCorpDAO.getInstance();
		} else if (corpType.equals("talentDevelopmentCorp")) {
			dao = TalentDevelopmentCorpDAO.getInstance();
		} else if (corpType.equals("socialCorp")) {
			dao = SocialCorpDAO.getInstance();
		}else if (corpType.equals("familyFriendlyCorp")) {
			dao = FamilyFriendlyCorpDAO.getInstance();
		} else if (corpType.equals("youthFriendlyCorp")) {
			dao = YouthFriendlyCorpDAO.getInstance();
		}

		// 키워드 없을 때
		if (keyword.equals("")) {
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
			request.setAttribute("corpList", corpList);
			pager.setNumbers(page, rowCount, request, response);
		} else {
			request.setAttribute("corpList", "noResult");
		}

	}

}
