package com.drawgreen.corpcollector.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Mail extends Authenticator{

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication("drawgreen@corpcollector.or.kr","T!36KySrGqMeEJm");
	}
	
}
