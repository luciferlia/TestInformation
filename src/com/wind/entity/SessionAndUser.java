package com.wind.entity;

import javax.servlet.http.HttpSession;

public class SessionAndUser {
  private String      userID;
  private String      sid;
  private HttpSession session;
  
  public String getUserID() {
    return userID;
  }
  
  public void setUserID(String userID) {
    this.userID = userID;
  }
  
  public String getSid() {
    return sid;
  }
  
  public void setSid(String sid) {
    this.sid = sid;
  }
  
  public HttpSession getSession() {
    return session;
  }
  
  public void setSession(HttpSession session) {
    this.session = session;
  }
  
}
