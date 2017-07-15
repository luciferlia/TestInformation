package com.wind.from;

import java.util.List;

public class ShowCaseForm {
	private String caseType;
	private List<CaseNum> caseNum;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public List<CaseNum> getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(List<CaseNum> caseNum) {
		this.caseNum = caseNum;
	}

}
