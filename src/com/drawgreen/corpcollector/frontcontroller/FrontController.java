package com.drawgreen.corpcollector.frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.command.EmailCheckCommand;
import com.drawgreen.corpcollector.command.EmailSendCommand;
import com.drawgreen.corpcollector.command.FindIdCommand;
import com.drawgreen.corpcollector.command.FindPwCommand;
import com.drawgreen.corpcollector.command.IdCheckCommand;
import com.drawgreen.corpcollector.command.LoginCommand;
import com.drawgreen.corpcollector.command.LogoutCommand;
import com.drawgreen.corpcollector.command.SignUpCommand;
import com.drawgreen.corpcollector.command.UpdatePwCommand;

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
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String viewPage = null;
		Command command = null;
		
		String com = request.getServletPath();
		StringTokenizer tokenizer = new StringTokenizer(com, "/");
		
		while(tokenizer.hasMoreTokens())
			com = tokenizer.nextToken();
		
		System.out.println(com);
		
		if(com.equals("IdCheck.do")) {
			command = new IdCheckCommand();
			command.execute(request, response);
		} 
		else if(com.equals("SignUp.do")) {
			command = new SignUpCommand();
			command.execute(request, response);
		} 
		else if(com.equals("EmailSend.do")) {
			command = new EmailSendCommand();
			command.execute(request, response);
		}
		else if(com.equals("EmailCheck.do")) {
			command = new EmailCheckCommand();
			command.execute(request, response);
		}
		else if(com.equals("Login.do")) {
			command = new LoginCommand();
			command.execute(request, response);
		}
		else if(com.equals("Logout.do")) {
			command = new LogoutCommand();
			command.execute(request, response);
		}
		else if(com.equals("FindId.do")) {
			command = new FindIdCommand();
			command.execute(request, response);
			viewPage = "findID_result.jsp";
		}
		else if(com.equals("FindPw.do")) {
			command = new FindPwCommand();
			command.execute(request, response);
			viewPage = "findPW_result.jsp";
		}
		else if(com.equals("UpdatePw.do")) {
			command = new UpdatePwCommand();
			command.execute(request, response);
		}

		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	}
}
