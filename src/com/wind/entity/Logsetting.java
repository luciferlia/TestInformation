package com.wind.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Logsetting entity. @author MyEclipse Persistence Tools
 */

public class Logsetting implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String tableName;
	private String name;
	private String type;
	private String time;
	private Integer primyKey;
	private Set logsettingdetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public Logsetting() {
	}

	/** full constructor */
	public Logsetting(User user, String tableName, String name, String type, String time, Integer primyKey,
			Set logsettingdetails) {
		this.user = user;
		this.tableName = tableName;
		this.name = name;
		this.type = type;
		this.time = time;
		this.primyKey = primyKey;
		this.logsettingdetails = logsettingdetails;
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

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getPrimyKey() {
		return this.primyKey;
	}

	public void setPrimyKey(Integer primyKey) {
		this.primyKey = primyKey;
	}

	public Set getLogsettingdetails() {
		return this.logsettingdetails;
	}

	public void setLogsettingdetails(Set logsettingdetails) {
		this.logsettingdetails = logsettingdetails;
	}

}