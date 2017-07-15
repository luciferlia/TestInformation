package com.wind.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Module entity. @author MyEclipse Persistence Tools
 */
public class Module{

	private Integer moduleId;
	private String moduleName;
	private String description;
	private String url;
	private Set menus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Module() {
	}

	/** full constructor */
	public Module(String moduleName, String description, String url, Set menus) {
		this.moduleName = moduleName;
		this.description = description;
		this.url = url;
		this.menus = menus;
	}

	// Property accessors

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getMenus() {
		return this.menus;
	}

	public void setMenus(Set menus) {
		this.menus = menus;
	}
}
