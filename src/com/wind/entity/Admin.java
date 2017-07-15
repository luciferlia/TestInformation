package com.wind.entity;

public class Admin {
	private int admin_id;//管理员编号
	private String admin_name;//管理员名称
	private String password;//密码
	private String permission;//权限
	public Admin(int admin_id, String admin_name, String password, String permission) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.password = password;
		this.permission = permission;
	}
	public Admin() {
		super();
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
}
