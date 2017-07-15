package com.wind.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Policypool entity. @author MyEclipse Persistence Tools
 */

public class Policypool implements java.io.Serializable {

	// Fields

	private Integer id;
	private User UpdateUser;
	private User CreateUser;
	private String policyName;
	private String createTime;
	private String updateTime;
	private String remark;
	private Set policies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Policypool() {
	}

	/** full constructor */
	public Policypool(User UpdateUser, User CreateUser, String policyName, String createTime,
			String updateTime, String remark, Set policies) {
		this.UpdateUser = UpdateUser;
		this.CreateUser = CreateUser;
		this.policyName = policyName;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.remark = remark;
		this.policies = policies;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public User getUpdateUser() {
		return UpdateUser;
	}

	public void setUpdateUser(User updateUser) {
		UpdateUser = updateUser;
	}

	public User getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(User createUser) {
		CreateUser = createUser;
	}

	public String getPolicyName() {
		return this.policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getPolicies() {
		return this.policies;
	}

	public void setPolicies(Set policies) {
		this.policies = policies;
	}

}