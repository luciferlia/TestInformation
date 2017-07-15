package com.wind.entity;

import java.sql.Timestamp;

/**
 * AbstractPrototype entity provides the base persistence definition of the
 * Prototype entity. @author MyEclipse Persistence Tools
 */

public class Prototype {

	// Fields

	private Integer id;
	private String prototypeName;
	private String prototypeType;
	private String prototypeCount;
	private Integer managerId;
	private Integer borrowerId;
	private String borrowTime;
	private String returnTime;
	private String state;
	private String borrowCount;

	// Constructors

	/** default constructor */
	public Prototype() {
	}

	/** full constructor */
	public Prototype(String prototypeName, String prototypeType, String prototypeCount, Integer managerId,
			Integer borrowerId, String borrowTime, String returnTime, String state, String borrowCount) {
		this.prototypeName = prototypeName;
		this.prototypeType = prototypeType;
		this.prototypeCount = prototypeCount;
		this.managerId = managerId;
		this.borrowerId = borrowerId;
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
		this.state = state;
		this.borrowCount = borrowCount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrototypeName() {
		return this.prototypeName;
	}

	public void setPrototypeName(String prototypeName) {
		this.prototypeName = prototypeName;
	}

	public String getPrototypeType() {
		return this.prototypeType;
	}

	public void setPrototypeType(String prototypeType) {
		this.prototypeType = prototypeType;
	}

	public String getPrototypeCount() {
		return this.prototypeCount;
	}

	public void setPrototypeCount(String prototypeCount) {
		this.prototypeCount = prototypeCount;
	}

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getBorrowerId() {
		return this.borrowerId;
	}

	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}

	

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBorrowCount() {
		return this.borrowCount;
	}

	public void setBorrowCount(String borrowCount) {
		this.borrowCount = borrowCount;
	}

}