package com.wind.from;

/**
 * AbstractDevice entity provides the base persistence definition of the Device
 * entity. @author MyEclipse Persistence Tools
 */

public class DeviceForm {

	// Fields

	private Integer deviceId;
	private String deviceName;
	private String deviceCategory;
	private String deviceType;
	private String state;
	private String manager;
	private Integer deviceCount;
	private Integer remainingCount;
	private String version;
	private String protocol;
	private String isconsum;
	private String remark;
	private Integer borrowCount;

	public Integer getBorrowCount() {
		return borrowCount;
	}

	public void setBorrowCount(Integer borrowCount) {
		this.borrowCount = borrowCount;
	}

	public Integer getRemainingCount() {
		return remainingCount;
	}

	public void setRemainingCount(Integer remainingCount) {
		this.remainingCount = remainingCount;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceCategory() {
		return this.deviceCategory;
	}

	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getDeviceCount() {
		return this.deviceCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getIsconsum() {
		return this.isconsum;
	}

	public void setIsconsum(String isconsum) {
		this.isconsum = isconsum;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}