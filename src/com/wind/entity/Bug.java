package com.wind.entity;

import java.sql.Timestamp;

/**
 * Bug entity. @author MyEclipse Persistence Tools
 */
public class Bug  {

	private int bugId;
	private Case cases;
	private String name;
	private String version;
	private String state;
	private String priority;
	private String description;
	private String finder;
	private Timestamp findTime;
	private String solver;
	private Timestamp updateTime;
	private String targetVersion;
	private Timestamp planFinishtime;
	private String severity;
	private String repeatability;
	private String bugVersion;

	// Constructors

	/** default constructor */
	public Bug() {
	}

	/** minimal constructor */
	public Bug(int bugId) {
		this.bugId = bugId;
	}

	/** full constructor */
    public Bug(int bugId, Case cases, String name, String version, String state, String priority, String description, String finder, Timestamp findTime, String solver, Timestamp updateTime, String targetVersion, Timestamp planFinishtime, String severity, String repeatability, String bugVersion) {
        this.bugId = bugId;
        this.cases = cases;
        this.name = name;
        this.version = version;
        this.state = state;
        this.priority = priority;
        this.description = description;
        this.finder = finder;
        this.findTime = findTime;
        this.solver = solver;
        this.updateTime = updateTime;
        this.targetVersion = targetVersion;
        this.planFinishtime = planFinishtime;
        this.severity = severity;
        this.repeatability = repeatability;
        this.bugVersion = bugVersion;
    }

	// Property accessors

	public int getBugId() {
		return this.bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public Case getCases() {
        return this.cases;
    }

	public void setCases(Case cases) {
        this.cases = cases;
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinder() {
		return this.finder;
	}

	public void setFinder(String finder) {
		this.finder = finder;
	}

	public Timestamp getFindTime() {
		return this.findTime;
	}

	public void setFindTime(Timestamp findTime) {
		this.findTime = findTime;
	}

	public String getSolver() {
		return this.solver;
	}

	public void setSolver(String solver) {
		this.solver = solver;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getTargetVersion() {
		return this.targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	public Timestamp getPlanFinishtime() {
		return this.planFinishtime;
	}

	public void setPlanFinishtime(Timestamp planFinishtime) {
		this.planFinishtime = planFinishtime;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getRepeatability() {
		return this.repeatability;
	}

	public void setRepeatability(String repeatability) {
		this.repeatability = repeatability;
	}

	public String getBugVersion() {
		return this.bugVersion;
	}

	public void setBugVersion(String bugVersion) {
		this.bugVersion = bugVersion;
	}
   
}
