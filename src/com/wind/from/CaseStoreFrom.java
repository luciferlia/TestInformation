package com.wind.from;

public class CaseStoreFrom {

	private int casestoreId;
	private String testModule;
	private String createName;
	private String updateName;
	private String createTime;
	private String updateTime;
	private String caseNum;// 用例总数
	private String totalTime;// 总耗时
	private String p1TotalTime;// p1等级耗时
	private String p2TotalTime;// p2等级耗时
	private String p3TotalTime;// p3等级耗时
	private String p4TotalTime;// p4等级耗时

	public String getP1TotalTime() {
		return p1TotalTime;
	}

	public void setP1TotalTime(String p1TotalTime) {
		this.p1TotalTime = p1TotalTime;
	}

	public String getP2TotalTime() {
		return p2TotalTime;
	}

	public void setP2TotalTime(String p2TotalTime) {
		this.p2TotalTime = p2TotalTime;
	}

	public String getP3TotalTime() {
		return p3TotalTime;
	}

	public void setP3TotalTime(String p3TotalTime) {
		this.p3TotalTime = p3TotalTime;
	}

	public String getP4TotalTime() {
		return p4TotalTime;
	}

	public void setP4TotalTime(String p4TotalTime) {
		this.p4TotalTime = p4TotalTime;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	private String functions;
	private String remark;

	public int getCasestoreId() {
		return casestoreId;
	}

	public void setCasestoreId(int casestoreId) {
		this.casestoreId = casestoreId;
	}

	public String getTestModule() {
		return testModule;
	}

	public void setTestModule(String testModule) {
		this.testModule = testModule;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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

	public String getFunctions() {
		return functions;
	}

	public void setFunctions(String functions) {
		this.functions = functions;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

}
