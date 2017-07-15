package com.wind.entity;

import java.util.Date;

public class Casestore {
	private int casestoreId;
	private int casetypeId;
	private String testModule;
	private int createrId;
	private int updateId;
	private String createTime;
	private String updateTime;
	

	private String functions;
	private String remark;

	// Constructors

	/** default constructor */
	public Casestore() {
	}

	/** minimal constructor */
	public Casestore(int casestoreId) {
		this.casestoreId = casestoreId;
	}

	/** full constructor */
	public Casestore(int casestoreId, int casetypeId, Integer createrId,int updateId,
			String createTime, String updateTime, String functions, String remark) {
		this.casestoreId = casestoreId;
		this.casetypeId = casetypeId;
		this.createrId = createrId;
		this.updateId=updateId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.functions = functions;
		this.remark = remark;
	}

	// Property accessors

	public int getCasestoreId() {
		return this.casestoreId;
	}

	public void setCasestoreId(int casestoreId) {
		this.casestoreId = casestoreId;
	}

	public int getCasetypeId() {
		return casetypeId;
	}

	public void setCasetypeId(int casetypeId) {
		this.casetypeId = casetypeId;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(int createrId) {
		this.createrId = createrId;
	}

	

	public int getUpdateId() {
		return updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	public String getFunctions() {
		return this.functions;
	}

	public void setFunctions(String functions) {
		this.functions = functions;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTestModule() {
		return testModule;
	}

	public void setTestModule(String testModule) {
		this.testModule = testModule;
	}
}
