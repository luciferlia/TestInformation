package com.wind.entity;

/**
 * SelectType entity. @author MyEclipse Persistence Tools
 */

public class SelectType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String type;

	// Constructors

	/** default constructor */
	public SelectType() {
	}

	/** full constructor */
	public SelectType(String name, String type) {
		this.name = name;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}