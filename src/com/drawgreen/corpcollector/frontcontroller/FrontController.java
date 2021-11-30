package com.drawgreen.corpcollector.frontcontroller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.command.community.LoadPostCommand;
import com.drawgreen.corpcollector.command.community.PostViewCommand;
import com.drawgreen.corpcollector.command.community.WriteRightCheckCommand;
import com.drawgreen.corpcollector.command.community.SearchPostCommand;
import com.drawgreen.corpcollector.command.community.UpdatePostCommand;
import com.drawgreen.corpcollector.command.community.DeletePostCommand;
import com.drawgreen.corpcollector.command.community.EditDeleteRightCheckCommand;
import com.drawgreen.corpcollector.command.community.WritePostCommand;
import com.drawgreen.corpcollector.command.findCorp.DetailViewCommand;
import com.drawgreen.corpcollector.command.findCorp.FindCorpCommand;
import com.drawgreen.corpcollector.command.information.CorpNewsViewCommand;
import com.drawgreen.corpcollector.command.member.EmailCheckCommand;
import com.drawgreen.corpcollector.command.member.EmailSendCommand;
import com.drawgreen.corpcollector.command.member.FindIdCommand;
import com.drawgreen.corpcollector.command.member.FindPwCommand;
import com.drawgreen.corpcollector.command.member.IdCheckCommand;
import com.drawgreen.corpcollector.command.member.LoginCommand;
import com.drawgreen.corpcollector.command.member.LogoutCommand;
import com.drawgreen.corpcollector.command.member.SignUpCommand;
import com.drawgreen.corpcollector.command.member.UpdatePwCommand;
import com.drawgreen.corpcollector.command.mypage.AddFavoriteCorpCommand;
import com.drawgreen.corpcollector.command.mypage.AddFavoriteCorpInMainCommand;
import com.drawgreen.corpcollector.command.mypage.DeleteFavoriteCorpCommand;
import com.drawgreen.corpcollector.command.mypage.DeleteMyFeedbackCommand;
import com.drawgreen.corpcollector.command.mypage.FavoriteCorpViewCommand;
import com.drawgreen.corpcollector.command.mypage.MyFeedbackViewCommand;
import com.drawgreen.corpcollector.command.mypage.PersonalInfoViewCommand;
import com.drawgreen.corpcollector.command.mypage.PersonalInfoWriteViewCommand;
import com.drawgreen.corpcollector.command.mypage.RecentSearchViewCommand;
import com.drawgreen.corpcollector.command.mypage.ResetFavoriteCorpListCommand;
import com.drawgreen.corpcollector.command.mypage.ResignMembershipCommand;
import com.drawgreen.corpcollector.command.mypage.UpdatePersonalInfoCommand;
import com.drawgreen.corpcollector.command.service.DevProcessViewCommand;

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
		
		/*----- 서비스 소개 -----*/
		else if(com.equals("DevProcessView.do")) {
			command = new DevProcessViewCommand();
			command.execute(request, response);
			viewPage = "developmentProcess.jsp";
		}
		
		/*----- 기업 찾기 -----*/
		else if(com.equals("FindCorp.do")) {
			String servletPath = request.getServletPath();
			String corpType = request.getParameter("corpType");
			
			command = new FindCorpCommand();
			command.execute(request, response);
			
			if(servletPath.equals("/FindCorp.do")) {
				switch (corpType) {
				case "greenCorp":
					viewPage = "findCorp/greenCorp.jsp";
					break;
				case "talentDevelopmentCorp":
					viewPage = "findCorp/talentDevelopmentCorp.jsp";
					break;
				case "socialCorp":
					viewPage = "findCorp/socialCorp.jsp";
					break;
				case "familyFriendlyCorp":
					viewPage = "findCorp/familyFriendlyCorp.jsp";
					break;
				case "youthFriendlyCorp":
					viewPage = "findCorp/youthFriendlyCorp.jsp";
					break;
				default:
					viewPage = "findCorp/findCorp_main.jsp";
					break;
				}
			}
			else {
				switch (corpType) {
				case "greenCorp":
					viewPage = "greenCorp.jsp";
					break;
				case "talentDevelopmentCorp":
					viewPage = "talentDevelopmentCorp.jsp";
					break;
				case "socialCorp":
					viewPage = "socialCorp.jsp";
					break;
				case "familyFriendlyCorp":
					viewPage = "familyFriendlyCorp.jsp";
					break;
				case "youthFriendlyCorp":
					viewPage = "youthFriendlyCorp.jsp";
					break;
				default:
					viewPage = "findCorp_main.jsp";
					break;
				}
			}
			
		}
		
		else if(com.equals("DetailView.do")) {
			String servletPath = request.getServletPath();
			
			command = new DetailViewCommand();
			command.execute(request, response);
			
			if(servletPath.equals("/DetailView.do"))
				viewPage = "findCorp/detailedCorpInfo.jsp";
			else viewPage = "detailedCorpInfo.jsp";
		}
		
		/*----- 정보나눔 기능 -----*/
		else if(com.equals("CorpNewsView.do")) {
			command = new CorpNewsViewCommand();
			command.execute(request, response);
			viewPage = "corp_News.jsp";
		}
		
		/*----- 커뮤니티 기능 -----*/
		else if(com.equals("WriteRightCheck.do")) {
			command = new WriteRightCheckCommand();
			command.execute(request, response);
		}
		else if(com.equals("EditDeleteRightCheck.do")) {
			command = new EditDeleteRightCheckCommand();
			command.execute(request, response);
		}
		else if(com.equals("WritePost.do")) {
			command = new WritePostCommand();
			command.execute(request, response);
		}
		else if(com.equals("UpdatePost.do")) {
			command = new UpdatePostCommand();
			command.execute(request, response);
		}
		else if(com.equals("DeletePost.do")) {
			command = new DeletePostCommand();
			command.execute(request, response);
		}
		else if(com.equals("SearchPost.do")) {
			command = new SearchPostCommand();
			command.execute(request, response);
			String boardName = request.getParameter("boardName");
			switch (boardName) {
			case "공지사항":
				viewPage = "notice.jsp";
				break;
			case "고객후기":
				viewPage = "feedback.jsp";
				break;
			}
		}
		else if(com.equals("PostView.do")) {
			command = new PostViewCommand();
			command.execute(request, response);
			String boardName = request.getParameter("boardName");
			switch (boardName) {
			case "공지사항":
				viewPage = "notice_Page.jsp";
				break;
			case "고객후기":
				viewPage = "feedback_Page.jsp";
				break;
			}
		}
		else if(com.equals("LoadPost.do")) {
			command = new LoadPostCommand();
			command.execute(request, response);
			String boardName = request.getParameter("boardName");
			switch (boardName) {
			case "공지사항":
				viewPage = "notice_Write.jsp";
				break;
			case "고객후기":
				viewPage = "feedback_Write.jsp";
				break;
			}
		}
		
		/*----- 마이페이지 기능 -----*/ 
		else if(com.equals("AddFavoriteCorp.do")) {
			command = new AddFavoriteCorpCommand();
			command.execute(request, response);
		}
		else if(com.equals("AddFavoriteCorp_InMain.do")) {
			command = new AddFavoriteCorpInMainCommand();
			command.execute(request, response);
		}
		else if(com.equals("PersonalInfoView.do")) {
			command = new PersonalInfoViewCommand();
			command.execute(request, response);
			viewPage = "personalInformation.jsp";
		}
		else if(com.equals("PersonalInfoWriteView.do")) {
			command = new PersonalInfoWriteViewCommand();
			command.execute(request, response);
			viewPage = "personalInformation_write.jsp";
		}
		else if(com.equals("UpdatePersonalInfo.do")) {
			command = new UpdatePersonalInfoCommand();
			command.execute(request, response);
			viewPage = "PersonalInfoView.do";
		}
		else if(com.equals("ResignMembership.do")) {
			command = new ResignMembershipCommand();
			command.execute(request, response);
		}
		else if(com.equals("FavoriteCorpView.do")) {
			command = new FavoriteCorpViewCommand();
			command.execute(request, response);
			viewPage = "favoriteCorp.jsp";
		}
		else if(com.equals("DeleteFavoriteCorp.do")) {
			command = new DeleteFavoriteCorpCommand();
			command.execute(request, response);
			viewPage = "FavoriteCorpView.do?page=1";
		}
		else if(com.equals("ResetFavoriteCorpList.do")) {
			command = new ResetFavoriteCorpListCommand();
			command.execute(request, response);
		}
		else if(com.equals("RecentSearchView.do")) {
			command = new RecentSearchViewCommand();
			command.execute(request, response);
			viewPage = "recentSearch.jsp";
		}
		else if(com.equals("MyFeedbackView.do")) {
			command = new MyFeedbackViewCommand();
			command.execute(request, response);
			viewPage = "myFeedback.jsp";
		}
		else if(com.equals("DeleteMyFeedback.do")) {
			command = new DeleteMyFeedbackCommand();
			command.execute(request, response);
			viewPage = "MyFeedbackView.do?page=1";
		}

		if (viewPage != null) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	}
}
