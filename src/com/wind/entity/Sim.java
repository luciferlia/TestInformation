package com.wind.entity;

/**
 * AbstractSim entity provides the base persistence definition of the Sim
 * entity. @author MyEclipse Persistence Tools
 */

public class Sim {

	// Fields

	private Integer id;
	private Integer managerId;
	private String phone;
	private String operator;
	private String puk;
	private String servicePassword;
	private String description;
	private String gprs;
	private String message;
	private String callPhone;
	private Integer borrowerId;
	private String borrowTime;
	private String returnTime;
	private String state;
	private String ICCID;

	/** default constructor */
	public Sim() {
	}

	/** full constructor */
	public Sim(Integer managerId, String phone, String operator, String puk, String servicePassword, String description,
			String gprs, String message, String callPhone, Integer borrowerId, String borrowTime, String returnTime,
			String state, String ICCID) {
		this.managerId = managerId;
		this.phone = phone;
		this.operator = operator;
		this.puk = puk;
		this.servicePassword = servicePassword;
		this.description = description;
		this.gprs = gprs;
		this.message = message;
		this.callPhone = callPhone;
		this.borrowerId = borrowerId;
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
		this.state = state;
		this.ICCID = ICCID;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPuk() {
		return this.puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	public String getServicePassword() {
		return this.servicePassword;
	}

	public void setServicePassword(String servicePassword) {
		this.servicePassword = servicePassword;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGprs() {
		return this.gprs;
	}

	public void setGprs(String gprs) {
		this.gprs = gprs;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCallPhone() {
		return callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public Integer getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(Integer borrowerId) {
		this.borrowerId = borrowerId;
	}

	public String getBorrowTime() {
		return this.borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getReturnTime() {
		return this.returnTime;
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

	public String getICCID() {
		return ICCID;
	}

	public void setICCID(String iCCID) {
		ICCID = iCCID;
	}
}