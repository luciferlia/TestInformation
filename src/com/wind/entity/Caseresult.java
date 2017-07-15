package com.wind.entity;

/**
 * Caseresult entity. @author MyEclipse Persistence Tools
 */

public class Caseresult implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer casestoreId;
	private Case cases;
	private Plan plan;
	private User user;
	private String result;
	private String remark;

	// Constructors

	/** default constructor */
	public Caseresult() {
	}

	/** full constructor */
    public Caseresult(Integer casestoreId, Case cases, Plan plan, User user, String result, String remark) {
        this.casestoreId = casestoreId;
        this.cases = cases;
        this.plan = plan;
        this.user = user;
        this.result = result;
        this.remark = remark;
    }

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getCasestoreId() {
		return casestoreId;
	}

	public void setCasestoreId(Integer casestoreId) {
		this.casestoreId = casestoreId;
	}

	public Case getCases() {
        return this.cases;
    }

	public void setCases(Case cases) {
        this.cases = cases;
    }

	public Plan getPlan() {
		return this.plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}