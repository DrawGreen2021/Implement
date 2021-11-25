package com.drawgreen.corpcollector.util;

public class ServerLogin {
	
	private static String hostID = "drawgreen@corpcollector.or.kr";
	private static String hostPW = "T!36KySrGqMeEJm";
	private String authEamilCode = "";
	
	private ServerLogin() {}
	
	private static class InnerInstance_serverLogin {
        private static final ServerLogin serverLogin = new ServerLogin();
    }
	
	public static String getHostID() {
		return hostID;
	}
	public static void setHostID(String hostID) {
		ServerLogin.hostID = hostID;
	}
	public static String getHostPW() {
		return hostPW;
	}
	public static void setHostPW(String hostPW) {
		ServerLogin.hostPW = hostPW;
	}
	public String getAuthEamilCode() {
		return authEamilCode;
	}
	public void setAuthEamilCode(String authEamilCode) {
		this.authEamilCode = authEamilCode;
	}
	public static ServerLogin getInstance(){	
		return InnerInstance_serverLogin.serverLogin;
	}
}
