package com.wind.from;

/**
 * AbstractProject entity provides the base persistence definition of the
 * Project entity. @author MyEclipse Persistence Tools
 */

public class ProjectForm {

	// Fields

	private Integer id;
	private String projectId;
	private String projectName;
	private String vpmName;
	private String testPhase;
	private String testType;
	private String testModule;
	private String hmaster;
	private String htester;
	private String smaster;
	private String stester;
	private String buildTime;
	private String customer;
	private String remark;
	private String state;
	private String level;
	private String policyName;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getVpmName() {
		return vpmName;
	}

	public void setVpmName(String vpmName) {
		this.vpmName = vpmName;
	}

	public String getHmaster() {
		return hmaster;
	}

	public void setHmaster(String hmaster) {
		this.hmaster = hmaster;
	}

	public String getHtester() {
		return htester;
	}

	public void setHtester(String htester) {
		this.htester = htester;
	}

	public String getSmaster() {
		return smaster;
	}

	public void setSmaster(String smaster) {
		this.smaster = smaster;
	}

	public String getStester() {
		return stester;
	}

	public void setStester(String stester) {
		this.stester = stester;
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

	public String getTestType() {
		return this.testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTestModule() {
		return this.testModule;
	}

	public void setTestModule(String testModule) {
		this.testModule = testModule;
	}

	public String getBuildTime() {
		return this.buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
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

}