package com.wind.entity;

/**
 * AbstractProjectoutsourcing entity provides the base persistence definition of
 * the Projectoutsourcing entity. @author MyEclipse Persistence Tools
 */

public class Projectoutsourcing {

	// Fields

	private Integer id;
	private Project project;
	private String projectName;
	private String company;
	private String humanName;
	private String phone;
	private String EMail;

	// Constructors

	/** default constructor */
	public Projectoutsourcing() {
	}

	/** full constructor */
	public Projectoutsourcing(Project project, String projectName, String company, String humanName,
			String phone, String EMail) {
		this.project = project;
		this.projectName = projectName;
		this.company = company;
		this.humanName = humanName;
		this.phone = phone;
		this.EMail = EMail;
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

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getHumanName() {
		return this.humanName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

}