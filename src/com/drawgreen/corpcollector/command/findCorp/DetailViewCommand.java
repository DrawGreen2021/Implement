package com.drawgreen.corpcollector.command.findCorp;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.CorpDAO;
import com.drawgreen.corpcollector.dao.FamilyFriendlyCorpDAO;
import com.drawgreen.corpcollector.dao.FavoriteCorpDAO;
import com.drawgreen.corpcollector.dao.GreenCorpDAO;
import com.drawgreen.corpcollector.dao.InterCorpDAO;
import com.drawgreen.corpcollector.dao.RecentSearchCorpDAO;
import com.drawgreen.corpcollector.dao.SocialCorpDAO;
import com.drawgreen.corpcollector.dao.TalentDevelopmentCorpDAO;
import com.drawgreen.corpcollector.dao.YouthFriendlyCorpDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class DetailViewCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String corpType = request.getParameter("corpType");
		String serial_num_str = request.getParameter("serial_num");
		int serial_num = Integer.parseInt(serial_num_str);
		
		CorpDAO dao = null;
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
		}
		
		HashMap<String, Object> corpInfo = dao.getInfo(serial_num);
		FavoriteCorpDAO favoriteCorpDAO = FavoriteCorpDAO.getInstance();
		if (corpType.equals("interCorp"))
			corpType = favoriteCorpDAO.getCorpType(request.getParameter("tableName"));
		request.setAttribute("corpInfo", corpInfo);
		request.setAttribute("corpType", corpType);
		
		// 로그인 했을 경우, 관심기업인지 아닌지 판단
		HttpSession httpSession = request.getSession();
		MemberDTO dto = (MemberDTO) httpSession.getAttribute("MemberDTO");
		if (dto != null) {
			RecentSearchCorpDAO recentSearchCorpDAO = RecentSearchCorpDAO.getInstance();
			boolean isRegistered = favoriteCorpDAO.isRegistered(dto.getId(), serial_num, corpType);
			recentSearchCorpDAO.insertSearchCorp(serial_num, dto.getId(), corpType, (String)corpInfo.get("업체명"));
			request.setAttribute("isRegistered", isRegistered);
			
		} else request.setAttribute("isRegistered", false);
		
	}

}
