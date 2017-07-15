package com.wind.entity;

/**
 * Customercase entity. @author MyEclipse Persistence Tools
 */

public class Customercase implements java.io.Serializable {

	// Fields

	private Integer id;
	private Casestore casestore;
	private String cuscaseName;
	private String fileUrl;

	// Constructors

	/** default constructor */
	public Customercase() {
	}

	/** full constructor */
	public Customercase(Casestore casestore, String cuscaseName, String fileUrl) {
		this.casestore = casestore;
		this.cuscaseName = cuscaseName;
		this.fileUrl = fileUrl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Casestore getCasestore() {
		return this.casestore;
	}

	public void setCasestore(Casestore casestore) {
		this.casestore = casestore;
	}

	public String getCuscaseName() {
		return this.cuscaseName;
	}

	public void setCuscaseName(String cuscaseName) {
		this.cuscaseName = cuscaseName;
	}

	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}