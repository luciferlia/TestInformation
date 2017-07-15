package com.wind.from;

import java.sql.Timestamp;

/**
 * AbstractPrototype entity provides the base persistence definition of the
 * Prototype entity. @author MyEclipse Persistence Tools
 */

public class PrototypeForm{

	// Fields

	private Integer id;
	private String prototypeName;
	private String prototypeType;
	private String prototypeCount;
	private String borrowCount;
	public String getBorrowCount() {
		return borrowCount;
	}

	public void setBorrowCount(String borrowCount) {
		this.borrowCount = borrowCount;
	}

	private String manager;
	private String borrower;
	private String borrowTime;
	private String returnTime;
	private String state;
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	

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

}