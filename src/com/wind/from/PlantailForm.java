package com.wind.from;

import java.util.List;

import com.wind.entity.PlanCasestore;
import com.wind.entity.Plantail;
import com.wind.entity.TestCase;

public class PlantailForm {
	private Plantail plantail;
	private List<PlanCasestore> plancasestores;
	private String casestoreName;
	private List<TestCase> testcase;
	private String issueCount;// 问题用例总数量

	public String getIssueCount() {
		return issueCount;
	}

	public void setIssueCount(String issueCount) {
		this.issueCount = issueCount;
	}

	public List<TestCase> getTestcase() {
		return testcase;
	}

	public void setTestcase(List<TestCase> testcase) {
		this.testcase = testcase;
	}

	public String getCasestoreName() {
		return casestoreName;
	}

	public void setCasestoreName(String casestoreName) {
		this.casestoreName = casestoreName;
	}

	public Plantail getPlantail() {
		return plantail;
	}

	public void setPlantail(Plantail plantail) {
		this.plantail = plantail;
	}

	public List<PlanCasestore> getPlancasestores() {
		return plancasestores;
	}

	public void setPlancasestores(List<PlanCasestore> plancasestores) {
		this.plancasestores = plancasestores;
	}

}
