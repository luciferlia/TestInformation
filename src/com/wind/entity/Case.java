package com.wind.entity;

import java.util.HashSet;
import java.util.Set;

public class Case {

	/**
	 * AbstractCase entity provides the base persistence definition of the Case
	 * entity. @author MyEclipse Persistence Tools
	 */

	

		// Fields

		private int caseId;//�������
		private int casestoreId;//��������
		private String num;

		private String caseName;//��������
		private String testItem;//������
		private String classification;//����
		private String testType;//��������
		private String description;//��������
		private String environment;//����Ҫ��
		private String auxiliaryTool;//��������
		private String step;//��������
		private String expectResult;//Ԥ�ڽ��
		private String testResult;//���Խ��
		private String caseState;//����״̬
		private String level;//��Ҫ�ȼ�
		private String advidceTime;//���Խ����ʱ  ��λ������
		private String remark;//��ע

		// Constructors

		/** default constructor */
		public Case() {
		}

		/** minimal constructor */
		public Case(int caseId) {
			this.caseId = caseId;
		}

		/** full constructor */
		public Case(int caseId, int casestoreId, String caseName, String testItem, String classification,
				String testType, String description, String environment, String auxiliaryTool, String step,
				String expectResult, String testResult, String caseState, String level, String advidceTime, String remark) {
			this.caseId = caseId;
			this.casestoreId= casestoreId;
			this.caseName = caseName;
			this.testItem = testItem;
			this.classification = classification;
			this.testType = testType;
			this.description = description;
			this.environment = environment;
			this.auxiliaryTool = auxiliaryTool;
			this.step = step;
			this.expectResult = expectResult;
			this.testResult = testResult;
			this.caseState = caseState;
			this.level = level;
			this.advidceTime = advidceTime;
			this.remark = remark;
		}

		// Property accessors

		public int getCaseId() {
			return this.caseId;
		}

		public void setCaseId(int caseId) {
			this.caseId = caseId;
		}
		
		
		

		public String getNum() {
			return num;
		}

		public void setNum(String num) {
			this.num = num;
		}

		public String getCaseName() {
			return this.caseName;
		}

		public void setCaseName(String caseName) {
			this.caseName = caseName;
		}

		public String getTestItem() {
			return this.testItem;
		}

		public void setTestItem(String testItem) {
			this.testItem = testItem;
		}

		public String getClassification() {
			return this.classification;
		}

		public void setClassification(String classification) {
			this.classification = classification;
		}

		public String getTestType() {
			return this.testType;
		}

		public void setTestType(String testType) {
			this.testType = testType;
		}

		public String getDescription() {
			return this.description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getEnvironment() {
			return this.environment;
		}

		public void setEnvironment(String environment) {
			this.environment = environment;
		}

		public String getAuxiliaryTool() {
			return this.auxiliaryTool;
		}

		public void setAuxiliaryTool(String auxiliaryTool) {
			this.auxiliaryTool = auxiliaryTool;
		}

		public String getStep() {
			return this.step;
		}

		public void setStep(String step) {
			this.step = step;
		}

		public String getExpectResult() {
			return this.expectResult;
		}

		public void setExpectResult(String expectResult) {
			this.expectResult = expectResult;
		}

		public String getTestResult() {
			return this.testResult;
		}

		public void setTestResult(String testResult) {
			this.testResult = testResult;
		}

		public String getCaseState() {
			return this.caseState;
		}

		public void setCaseState(String caseState) {
			this.caseState = caseState;
		}

		public String getLevel() {
			return this.level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public String getAdvidceTime() {
			return this.advidceTime;
		}

		public void setAdvidceTime(String advidceTime) {
			this.advidceTime = advidceTime;
		}

		public String getRemark() {
			return this.remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
		public int getCasestoreId() {
			return casestoreId;
		}

		public void setCasestoreId(int casestoreId) {
			this.casestoreId = casestoreId;
		}
	
}
