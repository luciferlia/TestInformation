package com.wind.entity;

/**
 * Adminmenu entity. @author MyEclipse Persistence Tools
 */
public class AdminMenu{

	private Integer id;
	private Module module;
	private String name;
	private String url;

	// Constructors

	/** default constructor */
	public AdminMenu() {
	}

	/** full constructor */
	public AdminMenu(Module module, String name, String url) {
		this.module = module;
		this.name = name;
		this.url = url;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
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

}
