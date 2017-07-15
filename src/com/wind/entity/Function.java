package com.wind.entity;

/**
 * Function entity. @author MyEclipse Persistence Tools
 */
public class Function{

	private Integer functionId;
	private String name;
	private String url;
	private String jsp;
	private String description;
	private String tag;

	// Constructors

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	/** default constructor */
	public Function() {
	}

	/** full constructor */
	public Function(String name, String url, String jsp, String description) {
		this.name = name;
		this.url = url;
		this.jsp = jsp;
		this.description = description;
	}

	// Property accessors

	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJsp() {
		return this.jsp;
	}

	public void setJsp(String jsp) {
		this.jsp = jsp;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
