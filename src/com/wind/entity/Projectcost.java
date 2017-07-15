package com.wind.entity;

/**
 * AbstractProjectcost entity provides the base persistence definition of the
 * Projectcost entity. @author MyEclipse Persistence Tools
 */

public class Projectcost {

	// Fields

	private Integer id;
	private Project project;
	private String projectName;
	private String customer;
	private String total;
	private String testManager;
	private String testerCount;
	private String cost;
	private String startTime;
	private String planTime;
	private String actualTime;
	private String state;

	// Constructors

	/** default constructor */
	public Projectcost() {
	}

	/** full constructor */
	public Projectcost(Project project, String projectName, String customer, String total, String testManager,
			String testerCount, String cost, String startTime, String planTime, String actualTime, String state) {
		this.project = project;
		this.projectName = projectName;
		this.customer = customer;
		this.total = total;
		this.testManager = testManager;
		this.testerCount = testerCount;
		this.cost = cost;
		this.startTime = startTime;
		this.planTime = planTime;
		this.actualTime = actualTime;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTestManager() {
		return this.testManager;
	}

	public void setTestManager(String testManager) {
		this.testManager = testManager;
	}

	public String getTesterCount() {
		return this.testerCount;
	}

	public void setTesterCount(String testerCount) {
		this.testerCount = testerCount;
	}

	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getPlanTime() {
		return this.planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getActualTime() {
		return this.actualTime;
	}

	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}