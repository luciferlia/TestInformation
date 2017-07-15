package com.wind.entity;

/**
 * TestCase entity. @author MyEclipse Persistence Tools
 */

public class TestCase implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Integer casestoreId;
	private String caseNum;
	private Integer status;
	private String testTime;
	private Integer planId;

	// Constructors

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	/** default constructor */
	public TestCase() {
	}

	/** full constructor */
	public TestCase(User user, Integer casestoreId, String caseNum, Integer status, String testTime) {
		this.user = user;
		this.casestoreId = casestoreId;
		this.caseNum = caseNum;
		this.status = status;
		this.testTime = testTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCasestoreId() {
		return this.casestoreId;
	}

	public void setCasestoreId(Integer casestoreId) {
		this.casestoreId = casestoreId;
	}

	public String getCaseNum() {
		return this.caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTestTime() {
		return this.testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

}