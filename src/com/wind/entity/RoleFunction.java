package com.wind.entity;

/**
 * RoleFunction entity. @author MyEclipse Persistence Tools
 */
public class RoleFunction{

	private Integer id;
	private Integer functionId;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public RoleFunction() {
	}

	/** full constructor */
	public RoleFunction(Integer functionId, Integer roleId) {
		this.functionId = functionId;
		this.roleId = roleId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
