package com.wind.entity;

/**
 * ProjectUser entity. @author MyEclipse Persistence Tools
 */
public class ProjectUser {

	private Integer id;
	private User user;//用户
	private Project project;//项目
	private String state;//项目状态

	// Constructors

	/** default constructor */
	public ProjectUser() {
	}

	/** full constructor */
	public ProjectUser(User user, Project project, String state) {
		this.user = user;
		this.project = project;
		this.state = state;
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

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
