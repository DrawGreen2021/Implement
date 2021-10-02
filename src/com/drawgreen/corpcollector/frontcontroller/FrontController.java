package com.drawgreen.corpcollector.frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.command.EmailCheckCommand;
import com.drawgreen.corpcollector.command.EmailSendCommand;
import com.drawgreen.corpcollector.command.IdCheckCommand;
import com.drawgreen.corpcollector.command.LoginCommand;
import com.drawgreen.corpcollector.command.SignUpCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	@SuppressWarnings("unused")
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String viewPage = null;
		Command command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		System.out.println(com);
		
		if(com.equals("/IdCheck.do")) {
			command = new IdCheckCommand();
			command.execute(request, response);
		} 
		else if(com.equals("/member/SignUp.do")) {
			command = new SignUpCommand();
			command.execute(request, response);
		} 
		else if(com.equals("/EmailSend.do")) {
			command = new EmailSendCommand();
			command.execute(request, response);
		}
		else if(com.equals("/EmailCheck.do")) {
			command = new EmailCheckCommand();
			command.execute(request, response);
		}
		else if(com.equals("/member/Login.do")) {
			command = new LoginCommand();
			command.execute(request, response);
		}

		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	}
}
