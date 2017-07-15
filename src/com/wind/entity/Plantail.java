package com.wind.entity;

/**
 * Plantail entity. @author MyEclipse Persistence Tools
 */

public class Plantail implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Plan plan;
	private String state;
	private String receiverTime;
	private String finishDegree;
	private String finishState;
	private String caseCount;
	private String startTime;
	private String endTime;
	private String remark;
	private String finishCase;

	// Constructors

	/** default constructor */
	public Plantail() {
	}

	/** full constructor */
	public Plantail(User user, Plan plan, String state, String receiverTime, String finishDegree, String finishState,
			String caseCount, String startTime, String endTime, String remark, String finishCase) {
		this.user = user;
		this.plan = plan;
		this.state = state;
		this.receiverTime = receiverTime;
		this.finishDegree = finishDegree;
		this.finishState = finishState;
		this.caseCount = caseCount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.remark = remark;
		this.finishCase = finishCase;
	}

	// Property accessors

	public String getFinishCase() {
		return finishCase;
	}

	public void setFinishCase(String finishCase) {
		this.finishCase = finishCase;
	}

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

	public Plan getPlan() {
		return this.plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFinishDegree() {
		return this.finishDegree;
	}

	public void setFinishDegree(String finishDegree) {
		this.finishDegree = finishDegree;
	}

	public String getFinishState() {
		return this.finishState;
	}

	public void setFinishState(String finishState) {
		this.finishState = finishState;
	}

	public String getCaseCount() {
		return this.caseCount;
	}

	public void setCaseCount(String caseCount) {
		this.caseCount = caseCount;
	}

	public String getReceiverTime() {
		return receiverTime;
	}

	public void setReceiverTime(String receiverTime) {
		this.receiverTime = receiverTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}