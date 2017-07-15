package com.wind.entity;

import java.util.HashSet;
import java.util.Set;

public class Case {

	/**
	 * AbstractCase entity provides the base persistence definition of the Case
	 * entity. @author MyEclipse Persistence Tools
	 */

	

		// Fields

		private int caseId;//用例编号
		private int casestoreId;//用例库编号
		private String num;

		private String caseName;//用例名称
		private String testItem;//测试项
		private String classification;//分类
		private String testType;//测试类型
		private String description;//用例描述
		private String environment;//环境要求
		private String auxiliaryTool;//辅助工具
		private String step;//操作步骤
		private String expectResult;//预期结果
		private String testResult;//测试结果
		private String caseState;//用例状态
		private String level;//重要等级
		private String advidceTime;//测试建议耗时  单位：分钟
		private String remark;//备注

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
