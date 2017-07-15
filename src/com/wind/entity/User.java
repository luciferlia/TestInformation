package com.wind.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String num;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String department;
	private String groupName;
	private String position;
	private String phone;
	private String email;
	private String state;
	private String loginTime;
	private String code;
	private Integer status;
	private Boolean flag;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Boolean flag) {
		this.flag = flag;
	}

	/** full constructor */
	public User(String num, String username, String password, String name, String sex, String department,
			String groupName, String position, String phone, String email, String state, String loginTime,
			String code, Integer status, Boolean flag) {
		this.num = num;
		this.username = username;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.department = department;
		this.groupName = groupName;
		this.position = position;
		this.phone = phone;
		this.email = email;
		this.state = state;
		this.loginTime = loginTime;
		this.code = code;
		this.status = status;
		this.flag = flag;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getFlag() {
		return this.flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
}