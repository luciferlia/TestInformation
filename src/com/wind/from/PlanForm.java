package com.wind.from;

import java.util.List;

import com.wind.entity.Plan;
import com.wind.entity.PlanCasestore;

public class PlanForm {
	private Plan plan;
	private String tag;// ��ʾ�üƻ��Ƿ�ɱ༭
	private String status;// �ƻ�״̬

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private List<PlanCasestore> planCasestores;// ������żƻ�����������м�ʵ��

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
