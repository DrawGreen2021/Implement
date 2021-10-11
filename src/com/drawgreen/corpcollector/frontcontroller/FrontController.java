package com.drawgreen.corpcollector.frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.command.findCorp.FindCorpCommand;
import com.drawgreen.corpcollector.command.findCorp.FindFamilyFriendlyCorpCommand;
import com.drawgreen.corpcollector.command.findCorp.FindGreenCorpCommand;
import com.drawgreen.corpcollector.command.findCorp.FindTalentDevelopmentCorpCommand;
import com.drawgreen.corpcollector.command.findCorp.FindYouthFriendlyCorpCommand;
import com.drawgreen.corpcollector.command.member.EmailCheckCommand;
import com.drawgreen.corpcollector.command.member.EmailSendCommand;
import com.drawgreen.corpcollector.command.member.FindIdCommand;
import com.drawgreen.corpcollector.command.member.FindPwCommand;
import com.drawgreen.corpcollector.command.member.IdCheckCommand;
import com.drawgreen.corpcollector.command.member.LoginCommand;
import com.drawgreen.corpcollector.command.member.LogoutCommand;
import com.drawgreen.corpcollector.command.member.SignUpCommand;
import com.drawgreen.corpcollector.command.member.UpdatePwCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean flag = true;
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
		
		/*----- 회원 관리 -----*/
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
		
		/*----- 기업 찾기 -----*/
		else if(com.equals("FindCorp.do")) {
			String servletPath = request.getServletPath();
			String url[] = request.getRequestURI().split("/");
			String path = url[url.length-1];
			
			if(flag) {
				command = new FindCorpCommand();
				command.execute(request, response);
				
				if(servletPath.equals("/FindCorp.do")) {
					viewPage = "findCorp/greenCorp.jsp";
				} else {
					viewPage = "greenCorp.jsp";
				}
				flag = false;
			}
			else {
				flag = true;
			}
			
		}
		
		else if(com.equals("FindGreenCorp.do")) {
			if(flag) {
				command = new FindGreenCorpCommand();
				command.execute(request, response);
				
				String servletPath = request.getServletPath();
				if(servletPath.equals("/FindGreenCorp.do")) {
					viewPage = "findCorp/greenCorp.jsp";
				} else {
					viewPage = "greenCorp.jsp";
				}
				flag = false;
			}
			else flag = true;
		}
		
		else if(com.equals("FindTalentDevelopmentCorp.do")) {
			if(flag) {
				command = new FindTalentDevelopmentCorpCommand();
				command.execute(request, response);
				
				String servletPath = request.getServletPath();
				if(servletPath.equals("/FindTalentDevelopmentCorp.do")) {
					viewPage = "findCorp/talentDevelopmentCorp.jsp";
				} else {
					viewPage = "talentDevelopmentCorp.jsp";
				}
				flag = false;
			}
			else {
				flag = true;
			}
		}
		
		else if(com.equals("FindFamilyFriendlyCorp.do")) {
			command = new FindFamilyFriendlyCorpCommand();
			command.execute(request, response);
			
			String servletPath = request.getServletPath();
			if(servletPath.equals("/FindFamilyFriendlyCorp.do")) {
				viewPage = "findCorp/familyFriendlyCorp.jsp";
			} else {
				viewPage = "familyFriendlyCorp.jsp";
			}
		}
		
		else if(com.equals("FindYouthFriendlyCorp.do")) {
			command = new FindYouthFriendlyCorpCommand();
			command.execute(request, response);
			
			String servletPath = request.getServletPath();
			if(servletPath.equals("/FindYouthFriendlyCorp.do")) {
				viewPage = "findCorp/youthFriendlyCorp.jsp";
			} else {
				viewPage = "youthFriendlyCorp.jsp";
			}
		}

		if (viewPage != null) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	}
}
