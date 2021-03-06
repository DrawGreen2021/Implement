package com.drawgreen.corpcollector.command.findCorp;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.CorpDAO;
import com.drawgreen.corpcollector.dao.FamilyFriendlyCorpDAO;
import com.drawgreen.corpcollector.dao.FavoriteCorpDAO;
import com.drawgreen.corpcollector.dao.GreenCorpDAO;
import com.drawgreen.corpcollector.dao.InterCorpDAO;
import com.drawgreen.corpcollector.dao.SocialCorpDAO;
import com.drawgreen.corpcollector.dao.TalentDevelopmentCorpDAO;
import com.drawgreen.corpcollector.dao.YouthFriendlyCorpDAO;
import com.drawgreen.corpcollector.dto.CorpDTO;
import com.drawgreen.corpcollector.dto.InterCorpDTO;
import com.drawgreen.corpcollector.dto.MemberDTO;
import com.drawgreen.corpcollector.util.Pager;

public class FindCorpCommand implements Command {
	private Pager pager = new Pager();
	private boolean isUnifiedSearch = false;
	
	@SuppressWarnings("unchecked")
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
		} else if (corpType.equals("familyFriendlyCorp")) {
			dao = FamilyFriendlyCorpDAO.getInstance();
		} else if (corpType.equals("youthFriendlyCorp")) {
			dao = YouthFriendlyCorpDAO.getInstance();
		} else {
			dao = InterCorpDAO.getInstance();
			isUnifiedSearch = true;
		}
		
		FavoriteCorpDAO favoriteCorpDAO = FavoriteCorpDAO.getInstance();
		
		// ????????? ??????
		HttpSession httpSession = request.getSession();
		boolean loginOk = false;
		int[] favoriteNums = null;
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		if (user != null)
			loginOk = true;

		// ????????? ?????? ???
		if (keyword.equals("")) {
			corpList = dao.getCorpList(page);
			rowCount = dao.getAllRowCount();
			if (isUnifiedSearch && loginOk && corpList != null) {
				// ?????? ?????? ??? ?????? ?????? ????????????
				favoriteNums = favoriteCorpDAO.getFavoriteSerialNums(user.getId(), (ArrayList<InterCorpDTO>)corpList);
			}
			else if (loginOk && corpList != null)
				favoriteNums = favoriteCorpDAO.getFavoirteSerialNums(page, corpType, user.getId(), rowCount);
		}
		// ????????? ?????? ???
		else {
			corpList = dao.getCorpList(keyword, page);
			rowCount = dao.getRowCount_byKeyword();
			if (isUnifiedSearch && loginOk && corpList != null) {
				// ?????? ?????? ??? ?????? ?????? ????????????
				favoriteNums = favoriteCorpDAO.getFavoriteSerialNums(user.getId(), (ArrayList<InterCorpDTO>)corpList);
			}
			else if (loginOk && corpList != null)
				favoriteNums = favoriteCorpDAO.getFavoirteSerialNums(page, corpType, user.getId(), dao.getSerialNums());
		}

		// ?????? ????????? null??? ???????????? request??? corpList ?????? ??? ????????? ?????? ??????
		if (corpList != null) {
			request.setAttribute("corpList", corpList);
			if (loginOk)
				request.setAttribute("favoriteNums", favoriteNums);
			else request.setAttribute("favoriteNums", null);
			pager.setNumbers(page, rowCount, request, response);
		} else {
			request.setAttribute("corpList", "noResult");
		}
		
	}

}
