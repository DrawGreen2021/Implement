package com.drawgreen.corpcollector.command.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FavoriteCorpDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class AddFavoriteCorpCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession httpSession = request.getSession();
		MemberDTO dto = null;
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			dto = (MemberDTO) httpSession.getAttribute("MemberDTO");
		
			String user_id = dto.getId();
			String corp_serialNum_str = request.getParameter("serial_number");
			int corp_serialNum = Integer.parseInt(corp_serialNum_str);
			String corpType = request.getParameter("corpType");
			
			// 관심기업 등록 여부 확인
			FavoriteCorpDAO dao = FavoriteCorpDAO.getInstance();
			boolean isRegistered = dao.isRegistered(user_id, corp_serialNum, corpType);
			
			// 등록이 안 되어 있으면 DB에 적재, 이미 기록이 있으면 삭제
			if (isRegistered == false) {
				String corpName = request.getParameter("corpName");
				dao.addFavoriteCorp(user_id, corp_serialNum, corpType, corpName);
				out.print("addFavoriteCorp");
			}
			else {
				dao.deleteFavoriteCorp(user_id, corp_serialNum, corpType);
				out.print("deleteFavoriteCorp");
			}	
		} catch (Exception e) {
			// 로그인이 안 됐을 경우
			out.print("not-login");
		}
		
	}

}
