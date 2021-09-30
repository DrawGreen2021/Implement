package com.drawgreen.corpcollector.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.dao.MemberDAO;

public class IdCheckCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MemberDAO dao = MemberDAO.getInstance();
        String id = request.getParameter("id");
        PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        boolean checkID = dao.idCheck(id);

        if(checkID) {
            out.print("not-usable");
        } else {
            out.print("usable");
        }
	}

}
