package com.drawgreen.corpcollector.command.mypage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FavoriteCorpDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class ResetFavoriteCorpListCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		MemberDTO user = (MemberDTO) httpSession.getAttribute("MemberDTO");
		
		FavoriteCorpDAO dao = FavoriteCorpDAO.getInstance();
		boolean resetOk = dao.resetCorpList(user.getId());
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
			if (resetOk) {
				out.print("resetOk");
			} else out.print("not-reset");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
