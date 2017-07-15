package com.wind.from;

import java.util.List;

import com.wind.entity.Plan;
import com.wind.entity.PlanCasestore;

public class PlanForm {
	private Plan plan;
	private String tag;// 表示该计划是否可编辑
	private String status;// 计划状态

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private List<PlanCasestore> planCasestores;// 用来存放计划和用例库的中间实体

	public List<PlanCasestore> getPlanCasestores() {
		return planCasestores;
	}

	public void setPlanCasestores(List<PlanCasestore> planCasestores) {
		this.planCasestores = planCasestores;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

}
