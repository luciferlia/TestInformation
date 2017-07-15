package com.wind.entity;

import java.sql.Timestamp;

/**
 * AbstractDeviceapply entity provides the base persistence definition of the
 * Deviceapply entity. @author MyEclipse Persistence Tools
 */

public class Deviceapply {

	// Fields

	private Integer id;
	private Integer deviceId;
	private Integer borrowId;
	private Integer borrowCount;
	private String borrowTime;
	private String returnTime;
	private String remark;

	// Constructors

	/** default constructor */
	public Deviceapply() {
	}

	/** full constructor */
	public Deviceapply(Integer deviceId, Integer borrowId, Integer borrowCount, String borrowTime,
			String returnTime, String remark) {
		this.deviceId = deviceId;
		this.borrowId = borrowId;
		this.borrowCount = borrowCount;
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getBorrowCount() {
		return this.borrowCount;
	}

	public void setBorrowCount(Integer borrowCount) {
		this.borrowCount = borrowCount;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}