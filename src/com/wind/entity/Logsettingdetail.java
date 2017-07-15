package com.wind.entity;

/**
 * Logsettingdetail entity. @author MyEclipse Persistence Tools
 */

public class Logsettingdetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Logsetting logsetting;
	private String columnName;
	private String columnText;
	private String oldValue;
	private String newValue;

	// Constructors

	/** default constructor */
	public Logsettingdetail() {
	}

	/** full constructor */
	public Logsettingdetail(Logsetting logsetting, String columnName, String columnText, String oldValue,
			String newValue) {
		this.logsetting = logsetting;
		this.columnName = columnName;
		this.columnText = columnText;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Logsetting getLogsetting() {
		return this.logsetting;
	}

	public void setLogsetting(Logsetting logsetting) {
		this.logsetting = logsetting;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnText() {
		return this.columnText;
	}

	public void setColumnText(String columnText) {
		this.columnText = columnText;
	}

	public String getOldValue() {
		return this.oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return this.newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

}