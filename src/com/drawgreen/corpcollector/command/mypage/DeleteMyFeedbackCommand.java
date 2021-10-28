package com.drawgreen.corpcollector.command.mypage;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.FeedbackPostDAO;

public class DeleteMyFeedbackCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String[] board_numbers_str = request.getParameterValues("myfeedback_select");
		int[] board_numbers = board_numbers_str == null?
				null:Arrays.stream(board_numbers_str).mapToInt(Integer::parseInt).toArray();
		
		FeedbackPostDAO dao = FeedbackPostDAO.getInstance();
		dao.deleteMyFeedback(board_numbers);
	}

}
