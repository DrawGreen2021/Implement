package com.drawgreen.corpcollector.util;

import java.util.Random;

public class AutoCodeMaker {
	String authCode = null;
	
	private AutoCodeMaker() {
		super();
	}

	private static class InnerInstance_AutoCodeMaker {
		private static final AutoCodeMaker autoCodeMaker = new AutoCodeMaker();
	}
	
	public static AutoCodeMaker getInstance() {
		return InnerInstance_AutoCodeMaker.autoCodeMaker;
	}

	public String MakeAuthCode() {
		
		StringBuffer temp = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = random.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (random.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (random.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((random.nextInt(10)));
				break;
			}
		}
		
		authCode = temp.toString();
		
		return authCode;
	}
}
