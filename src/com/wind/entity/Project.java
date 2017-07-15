package com.wind.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */
public class Project {

	private Integer id;
	private String projectId;// 项目编号
	private String projectName;// 项目名称
	private String testPhase;// 项目阶段
	private String buildTime;// 创建时间
	private String customer;// 客户名称
	private String remark;// 备注
	private String state;// 项目状态
	private String level;// 项目级别
	private String policyName;// 策略名称


	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	private Set projectUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(String projectId, String projectName, String testPhase, String buildTime, String customer,
			String remark, String state, String level, String policyName, Set projectUsers) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.testPhase = testPhase;
		this.buildTime = buildTime;
		this.customer = customer;
		this.remark = remark;
		this.state = state;
		this.level = level;
		this.policyName = policyName;
		this.projectUsers = projectUsers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTestPhase() {
		return this.testPhase;
	}

	public void setTestPhase(String testPhase) {
		this.testPhase = testPhase;
	}

	public String getBuildTime() {
		return this.buildTime;
	}

	public void setBuildTime(String d) {
		this.buildTime = d;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Set getProjectUsers() {
		return this.projectUsers;
	}

	public void setProjectUsers(Set projectUsers) {
		this.projectUsers = projectUsers;
	}

}
