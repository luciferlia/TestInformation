package com.wind.entity;

public class UserList {
	//��ǰ�û���session id
		private String sessionId;
		//��ǰ�û���ip��ַ
		private String ip;
		//��ǰ�û���һ�η��ʵ�ʱ��
		private String firstTime;
		public UserList() {
			
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getFirstTime() {
			return firstTime;
		}
		public void setFirstTime(String firstTime) {
			this.firstTime = firstTime;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
}
