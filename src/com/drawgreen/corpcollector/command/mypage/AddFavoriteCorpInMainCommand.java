package com.drawgreen.corpcollector.command.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FavoriteCorpDAO;
import com.drawgreen.corpcollector.dao.InterCorpDAO;
import com.drawgreen.corpcollector.dto.MemberDTO;

public class AddFavoriteCorpInMainCommand implements Command{

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
			
			// 원래 있던 기업 테이블의 연번 받아오기
			InterCorpDAO interCorpDAO = InterCorpDAO.getInstance();
			String corpName = request.getParameter("corpName");
			String location = request.getParameter("location");
			String sector = request.getParameter("sector");
			String tableName = request.getParameter("tableName");
			int corp_serialNum = interCorpDAO.getOriginalSerialNum(corpName, location, sector, tableName);
			
			// tableName을 매개변수로 corpType 가져오기
			FavoriteCorpDAO favoriteCorpDAO = FavoriteCorpDAO.getInstance();
			String corpType = favoriteCorpDAO.getCorpType(tableName);
			
			// 관심기업 등록 여부 확인
			boolean isRegistered = favoriteCorpDAO.isRegistered(user_id, corp_serialNum, corpType);
			
			// 등록이 안 되어 있으면 DB에 적재, 이미 기록이 있으면 삭제
			if (isRegistered == false) {
				favoriteCorpDAO.addFavoriteCorp(user_id, corp_serialNum, corpType, corpName);
				out.print("addFavoriteCorp");
			}
			else {
				favoriteCorpDAO.deleteFavoriteCorp(user_id, corp_serialNum, corpType);
				out.print("deleteFavoriteCorp");
			}	
		} catch (Exception e) {
			// 로그인이 안 됐을 경우
			out.print("not-login");
		}
	}

}
