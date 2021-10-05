package com.drawgreen.corpcollector.command.findCorp;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.GreenCorpDAO;
import com.drawgreen.corpcollector.dto.CorpDTO;
import com.drawgreen.corpcollector.dto.GreenCorpDTO;

public class FindCorpCommand implements Command{

	@Override
	public <E> void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String corpType = request.getParameter("corpType");
		String keyword = request.getParameter("keyword");
		
		GreenCorpDAO dao = GreenCorpDAO.getInstance();
		ArrayList<E> corpList = null;
		
		if (keyword.length() == 0 || keyword == null) {
			if (corpType.equals("greenCompany")) {
				corpList = dao.getGreenCorpList();
				
			}
			else {
				
			}
			
		}
		else {
			if (corpType.equals("greenCompany")) {
				dao.getGreenCorpList(keyword);
			}
			else {
				
			}
		}
	}
	
}
