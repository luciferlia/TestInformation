package com.wind.from;

import java.util.List;

import com.wind.entity.Plan;
import com.wind.entity.PlanCasestore;
import com.wind.entity.Plantail;

public class ShowResultForm {
	private Plan plan;
	private Plantail plantail;
	private List<PlanCasestore> plancasestore;
	private String pass;
	private String fail;
	private String finishCase;
	private String ntCount;
	private String naCount;
	private String tcIssueCount;
	private String moduleFail;
	private String casestoreName;
	private String totalCount;

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getCasestoreName() {
		return casestoreName;
	}

	public void setCasestoreName(String casestoreName) {
		this.casestoreName = casestoreName;
	}

	public String getModuleFail() {
		return moduleFail;
	}

	public void setModuleFail(String moduleFail) {
		this.moduleFail = moduleFail;
	}

	public String getNtCount() {
		return ntCount;
	}

	public void setNtCount(String ntCount) {
		this.ntCount = ntCount;
	}

	public String getNaCount() {
		return naCount;
	}

	public void setNaCount(String naCount) {
		this.naCount = naCount;
	}

	public String getTcIssueCount() {
		return tcIssueCount;
	}

	public void setTcIssueCount(String tcIssueCount) {
		this.tcIssueCount = tcIssueCount;
	}

	public String getFinishCase() {
		return finishCase;
	}

	public void setFinishCase(String finishCase) {
		this.finishCase = finishCase;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Plantail getPlantail() {
		return plantail;
	}

	public void setPlantail(Plantail plantail) {
		this.plantail = plantail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFail() {
		return fail;
	}

	public void setFail(String fail) {
		this.fail = fail;
	}

	public List<PlanCasestore> getPlancasestore() {
		return plancasestore;
	}

	public void setPlancasestore(List<PlanCasestore> plancasestore) {
		this.plancasestore = plancasestore;
	}

}
