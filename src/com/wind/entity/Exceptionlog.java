package com.wind.entity;

/**
 * Exceptionlog entity. @author MyEclipse Persistence Tools
 */

public class Exceptionlog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String logName;
	private String startTime;

	// Constructors

	/** default constructor */
	public Exceptionlog() {
	}

	/** full constructor */
	public Exceptionlog(String logName, String startTime) {
		this.logName = logName;
		this.startTime = startTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogName() {
		return this.logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

}