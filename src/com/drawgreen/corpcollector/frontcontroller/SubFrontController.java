package com.drawgreen.corpcollector.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.command.findCorp.FindGreenCorpCommand;
import com.drawgreen.corpcollector.command.findCorp.FindTalentDevelopmentCorpCommand;
import com.drawgreen.corpcollector.command.findCorp.FindYouthFriendlyCorpCommand;

/**
 * Servlet implementation class SubFrontController
 */
@WebServlet("/FindCorp")
public class SubFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static boolean flag = true;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionFind(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionFind(request, response);
	}

	private void actionFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서브 컨트롤러 실행");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String viewPage = null;
		Command command = null;
		
		String corpType = request.getParameter("corpType");
		
		
		/*-------기업 찾기--------*/
		if(corpType.equals("greenCorp")) {
			command = new FindGreenCorpCommand();
			command.execute(request, response);
			viewPage = "findCorp/greenCorp.jsp";
		}
		
		else if(corpType.equals("talentDevelopmentCorp")) {
			command = new FindTalentDevelopmentCorpCommand();
			command.execute(request, response);
			viewPage = "findCorp/talentDevelopmentCorp.jsp";
		}
		
		else if(corpType.equals("socialCorp")) {
			
		}
		
		else if(corpType.equals("familyFriendlyCorp")) {
			command = new FindGreenCorpCommand();
			command.execute(request, response);
			viewPage = "findCorp/familyFriendlyCorp.jsp";
		}
		
		else if(corpType.equals("youthFriendlyCorp")) {
			command = new FindYouthFriendlyCorpCommand();
			command.execute(request, response);
			viewPage = "findCorp/youthFriendlyCorp.jsp";
		}
		
		
		
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
