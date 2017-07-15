package com.wind.entity;

/**
 * PlanCasestore entity. @author MyEclipse Persistence Tools
 */

public class PlanCasestore implements java.io.Serializable {

	// Fields

	private Integer id;
	private Casestore casestore;
	private Plan plan;
	private String tester;

	// Constructors

	/** default constructor */
	public PlanCasestore() {
	}

	/** minimal constructor */
	public PlanCasestore(Casestore casestore, Plan plan) {
		this.casestore = casestore;
		this.plan = plan;
	}

	/** full constructor */
	public PlanCasestore(Casestore casestore, Plan plan, String tester) {
		this.casestore = casestore;
		this.plan = plan;
		this.tester = tester;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Casestore getCasestore() {
		return this.casestore;
	}

	public void setCasestore(Casestore casestore) {
		this.casestore = casestore;
	}

	public Plan getPlan() {
		return this.plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getTester() {
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	
}