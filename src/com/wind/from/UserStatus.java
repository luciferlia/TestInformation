package com.wind.from;

public class UserStatus {

	private int status;//用户状态
	private String name;
	private String projectName;//所在项目
	private String testModule;//测试模块
	private String finsh;//完成度
	private String groupName;//所属组
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getTestModule() {
		return testModule;
	}
	public void setTestModule(String testModule) {
		this.testModule = testModule;
	}
	public String getFinsh() {
		return finsh;
	}
	public void setFinsh(String finsh) {
		this.finsh = finsh;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
