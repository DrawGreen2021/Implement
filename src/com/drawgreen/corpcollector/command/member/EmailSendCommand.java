package com.drawgreen.corpcollector.command.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drawgreen.corpcollector.command.Command;
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
			msg.setSubject("안녕하세요. CorpCollector의 인증메일입니다.", "UTF-8");
			// 메일 내용
			msg.setText("인증 번호 :" + AuthenticationKey );

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
}
