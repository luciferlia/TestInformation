package com.wind.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractDevice entity provides the base persistence definition of the Device
 * entity. @author MyEclipse Persistence Tools
 */

public class Device {

	// Fields

	private Integer deviceId;
	private String deviceName;
	private String deviceCategory;
	private String deviceType;
	private String state;
	private Integer managerId;
	private Integer deviceCount;
	private Integer remainingCount;
	private String version;
	private String protocol;
	private String isconsum;
	private String remark;
	private Set deviceapplies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Device() {
	}

	/** full constructor */
	public Device(String deviceName, String deviceCategory, String deviceType, String state, Integer managerId,
			Integer deviceCount, Integer remainingCount, String version, String protocol, String isconsum,
			String remark, Set deviceapplies) {
		this.deviceName = deviceName;
		this.deviceCategory = deviceCategory;
		this.deviceType = deviceType;
		this.state = state;
		this.managerId = managerId;
		this.deviceCount = deviceCount;
		this.remainingCount = remainingCount;
		this.version = version;
		this.protocol = protocol;
		this.isconsum = isconsum;
		this.remark = remark;
		this.deviceapplies = deviceapplies;
	}

	// Property accessors

	public Integer getRemainingCount() {
		return remainingCount;
	}

	public void setRemainingCount(Integer remainingCount) {
		this.remainingCount = remainingCount;
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

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
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

	public Set getDeviceapplies() {
		return this.deviceapplies;
	}

	public void setDeviceapplies(Set deviceapplies) {
		this.deviceapplies = deviceapplies;
	}

}