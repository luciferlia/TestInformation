package com.wind.from;

import java.sql.Timestamp;

/**
 * AbstractDeviceapply entity provides the base persistence definition of the
 * Deviceapply entity. @author MyEclipse Persistence Tools
 */

public class DeviceapplyForm {

	// Fields

	private Integer id;
	private String deviceName;
	private String borrower;
	private Integer borrowCount;
	private String borrowTime;
	private String returnTime;
	private String remark;
	private String deviceType;
	private Integer deviceCount;
	private String manager;
	private String isconsum;
	private String state;
	// Property accessors

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getIsconsum() {
		return isconsum;
	}

	public void setIsconsum(String isconsum) {
		this.isconsum = isconsum;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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