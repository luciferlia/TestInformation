package com.wind.from;

import com.wind.entity.Casetype;
import com.wind.entity.Policypool;

/**
 * Policy entity. @author MyEclipse Persistence Tools
 */

public class PolicyForm {

	// Fields

	private Integer id;
	private Casetype casetype;
	private Policypool policypool;
	private String phase;
	private String version;
	private String versionReleasetime;
	private String testContent;
	private String caseCount;
	private String humanInput;
	private String humanCount;
	private String prototypeCount;
	private String prototypeReuse;
	private String startTime;
	private String endTime;
	private String remark;
	private String versionEndtime;
	private String prototypeType;
	private String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Casetype getCasetype() {
		return this.casetype;
	}

	public void setCasetype(Casetype casetype) {
		this.casetype = casetype;
	}

	public Policypool getPolicypool() {
		return this.policypool;
	}

	public void setPolicypool(Policypool policypool) {
		this.policypool = policypool;
	}

	public String getPhase() {
		return this.phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionReleasetime() {
		return this.versionReleasetime;
	}

	public void setVersionReleasetime(String versionReleasetime) {
		this.versionReleasetime = versionReleasetime;
	}

	public String getTestContent() {
		return this.testContent;
	}

	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}

	public String getCaseCount() {
		return this.caseCount;
	}

	public void setCaseCount(String caseCount) {
		this.caseCount = caseCount;
	}

	public String getHumanInput() {
		return this.humanInput;
	}

	public void setHumanInput(String humanInput) {
		this.humanInput = humanInput;
	}

	public String getHumanCount() {
		return this.humanCount;
	}

	public void setHumanCount(String humanCount) {
		this.humanCount = humanCount;
	}

	public String getPrototypeCount() {
		return this.prototypeCount;
	}

	public void setPrototypeCount(String prototypeCount) {
		this.prototypeCount = prototypeCount;
	}

	public String getPrototypeReuse() {
		return this.prototypeReuse;
	}

	public void setPrototypeReuse(String prototypeReuse) {
		this.prototypeReuse = prototypeReuse;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVersionEndtime() {
		return this.versionEndtime;
	}

	public void setVersionEndtime(String versionEndtime) {
		this.versionEndtime = versionEndtime;
	}

	public String getPrototypeType() {
		return this.prototypeType;
	}

	public void setPrototypeType(String prototypeType) {
		this.prototypeType = prototypeType;
	}

}