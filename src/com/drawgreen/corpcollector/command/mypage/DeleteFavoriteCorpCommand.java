package com.drawgreen.corpcollector.command.mypage;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FavoriteCorpDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class DeleteFavoriteCorpCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String[] talent_ids_str = request.getParameterValues("favCorp_select_talent");
		int[] talent_ids = talent_ids_str == null?
				null:Arrays.stream(talent_ids_str).mapToInt(Integer::parseInt).toArray();
		
		String[] green_ids_str = request.getParameterValues("favCorp_select_green");
		int[] green_ids = green_ids_str== null?
				null:Arrays.stream(talent_ids_str).mapToInt(Integer::parseInt).toArray();
		
		String[] social_ids_str = request.getParameterValues("favCorp_select_social");
		int[] social_ids = social_ids_str == null?
				null:Arrays.stream(social_ids_str).mapToInt(Integer::parseInt).toArray();
		
		String[] family_ids_str = request.getParameterValues("favCorp_select_family");
		int[] family_ids = family_ids_str == null?
				null:Arrays.stream(family_ids_str).mapToInt(Integer::parseInt).toArray();
		
		String[] youth_ids_str = request.getParameterValues("favCorp_select_youth");
		int[] youth_ids = youth_ids_str == null?
				null:Arrays.stream(youth_ids_str).mapToInt(Integer::parseInt).toArray();
		
		HashMap<String, int[]> idMap = new HashMap<String, int[]>();
		idMap.put("talentDevelopmentCorp_id", talent_ids);
		idMap.put("greenCorp_id", green_ids);
		idMap.put("socialCorp_id", social_ids);
		idMap.put("familyFriendlyCorp_id", family_ids);
		idMap.put("youthFriendlyCorp_id", youth_ids);
		
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		FavoriteCorpDAO dao = FavoriteCorpDAO.getInstance();
		dao.deleteFavCorp(user.getId(), idMap);
	}

}
