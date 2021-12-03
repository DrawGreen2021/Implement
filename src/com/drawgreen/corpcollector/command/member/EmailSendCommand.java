package com.drawgreen.corpcollector.command.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
import com.drawgreen.corpcollector.dao.MemberDAO;
import com.drawgreen.corpcollector.util.AutoCodeMaker;
import com.drawgreen.corpcollector.util.Mail;
import com.drawgreen.corpcollector.util.ServerLogin;


public class EmailSendCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String signUpReq_str = request.getParameter("signUp")==null?
				"false":request.getParameter("signUp");
		String updatePer_str = request.getParameter("updatePerInfo")==null?
				"false":request.getParameter("updatePerInfo");
		boolean signUpReq = Boolean.parseBoolean(signUpReq_str);
		boolean updatePerReq = Boolean.parseBoolean(updatePer_str);
		if (signUpReq || updatePerReq) {
			MemberDAO dao = MemberDAO.getInstance();
			boolean emailDupCheck = dao.emailCheck(email);
			
			if (emailDupCheck)
				out.print("duplicated");
			else returnSedingResult(email, out);
		} else returnSedingResult(email, out);
		
	}
	
	public void returnSedingResult(String email, PrintWriter out) {
		boolean emailSendCheck = emailSend(email);
		
		if(emailSendCheck) {
			out.print("connectable");
		} else {
			out.print("not-connectable");
		}
	}
	
	public boolean emailSend(String email) {
		//인증코드 생성
		AutoCodeMaker autoCodeMaker = AutoCodeMaker.getInstance();
		String AuthenticationKey = autoCodeMaker.MakeAuthCode();
				
		// mail server 설정
		String host = "mw-002.cafe24.com";
		String user = ServerLogin.getHostID(); // 서버 관리자 아웃룩 계정
		
		// 메일 받을 주소
		String to_email = email;
		System.out.println("inputedEmail : " + email);

		// SMTP 서버 정보 설정
		Properties prop = new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.auth", "true");
		
		Authenticator auth = new Mail();
		Session session = Session.getInstance(prop, auth);
		
		// email 전송
		try {
			session.setDebug(true);
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			// 메일 제목
			msg.setSubject("안녕하세요. CorpCollector의 인증메일입니다.");
			// 메일 내용
			String mailContent = makeMailContent(AuthenticationKey);
			msg.setContent(mailContent, "text/html; charset=UTF-8");

			Transport.send(msg);
			System.out.println("이메일 전송 : " + AuthenticationKey);
			ServerLogin serverLogin = ServerLogin.getInstance();
			serverLogin.setAuthEamilCode(AuthenticationKey);
			
			return true;

		} catch (Exception e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}
		
		return false;
	}
	
	private String makeMailContent(String authCode) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<table style=\"border: solid 1px gray; padding: 10px 15px; margin: 0 auto; font-size: 12pt;\">");
		buffer.append("<tr>");
		buffer.append("<td>안녕하세요! CorpCollector입니다.<br>");
		buffer.append("아래의 코드를 인증코드 란에 입력해주세요.</td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td><br><br>");
		buffer.append("<div style=\"text-align: center; font-weight: bold; font-size: 20pt;"
				+ "	padding: 10px; background-color: black; color: white; margin-bottom: 10px;\">");
		buffer.append("인증코드: "+authCode+"</div>");
		buffer.append("</td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		
		return buffer.toString();
	}
}
