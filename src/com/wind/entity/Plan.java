package com.wind.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Plan entity. @author MyEclipse Persistence Tools
 */

public class Plan implements java.io.Serializable {

	// Fields

	private Integer planId;
	private Casetype casetype;
	private Project project;
	private String phase;
	private String version;
	private String versionReleasetime;
	private String caseCount;
	private String humanInput;
	private String humanCount;
	private String prototypeCount;
	private String prototypeReuse;
	private String startTime;
	private String endTime;
	private String remark;
	private String versionType;
	private String humanNeed;
	private String versionPath;
	private String versionPlan;
	private String filePath;
	private String loadTool;
	private String bugPath;
	private String testArrange;
	private String implementation;
	private String versionEndtime;
	private String prototypeType;
	private String updateVersion;
	private Integer status;
	private Set plantails = new HashSet(0);
	private Set caseresults = new HashSet(0);
	private Set planCasestores = new HashSet(0);

	// Constructors

	/** default constructor */
	public Plan() {
	}

	/** full constructor */
	public Plan(Casetype casetype, Project project, String phase, String version, String versionReleasetime,
			String caseCount, String humanInput, String humanCount, String prototypeCount, String prototypeReuse,
			String startTime, String endTime, String remark, String versionType, String humanNeed, String versionPath,
			String versionPlan, String filePath, String loadTool, String bugPath, String testArrange,
			String implementation, String versionEndtime, String prototypeType, String updateVersion, Integer status,
			Set plantails, Set caseresults, Set planCasestores) {
		this.casetype = casetype;
		this.project = project;
		this.phase = phase;
		this.version = version;
		this.versionReleasetime = versionReleasetime;
		this.caseCount = caseCount;
		this.humanInput = humanInput;
		this.humanCount = humanCount;
		this.prototypeCount = prototypeCount;
		this.prototypeReuse = prototypeReuse;
		this.startTime = startTime;
		this.endTime = endTime;
		this.remark = remark;
		this.versionType = versionType;
		this.humanNeed = humanNeed;
		this.versionPath = versionPath;
		this.versionPlan = versionPlan;
		this.filePath = filePath;
		this.loadTool = loadTool;
		this.bugPath = bugPath;
		this.testArrange = testArrange;
		this.implementation = implementation;
		this.versionEndtime = versionEndtime;
		this.prototypeType = prototypeType;
		this.updateVersion = updateVersion;
		this.status = status;
		this.plantails = plantails;
		this.caseresults = caseresults;
		this.planCasestores = planCasestores;
	}

	// Property accessors

	public Integer getPlanId() {
		return this.planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Casetype getCasetype() {
		return this.casetype;
	}

	public void setCasetype(Casetype casetype) {
		this.casetype = casetype;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public String getVersionType() {
		return this.versionType;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}

	public String getHumanNeed() {
		return this.humanNeed;
	}

	public void setHumanNeed(String humanNeed) {
		this.humanNeed = humanNeed;
	}

	public String getVersionPath() {
		return this.versionPath;
	}

	public void setVersionPath(String versionPath) {
		this.versionPath = versionPath;
	}

	public String getVersionPlan() {
		return this.versionPlan;
	}

	public void setVersionPlan(String versionPlan) {
		this.versionPlan = versionPlan;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getLoadTool() {
		return this.loadTool;
	}

	public void setLoadTool(String loadTool) {
		this.loadTool = loadTool;
	}

	public String getBugPath() {
		return this.bugPath;
	}

	public void setBugPath(String bugPath) {
		this.bugPath = bugPath;
	}

	public String getTestArrange() {
		return this.testArrange;
	}

	public void setTestArrange(String testArrange) {
		this.testArrange = testArrange;
	}

	public String getImplementation() {
		return this.implementation;
	}

	public void setImplementation(String implementation) {
		this.implementation = implementation;
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

	public String getUpdateVersion() {
		return this.updateVersion;
	}

	public void setUpdateVersion(String updateVersion) {
		this.updateVersion = updateVersion;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getPlantails() {
		return this.plantails;
	}

	public void setPlantails(Set plantails) {
		this.plantails = plantails;
	}

	public Set getCaseresults() {
		return this.caseresults;
	}

	public void setCaseresults(Set caseresults) {
		this.caseresults = caseresults;
	}

	public Set getPlanCasestores() {
		return this.planCasestores;
	}

	public void setPlanCasestores(Set planCasestores) {
		this.planCasestores = planCasestores;
	}

}