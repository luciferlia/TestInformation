package com.wind.from;

import java.util.List;

import com.wind.entity.AdminMenu;
import com.wind.entity.Menu;

public class MenuForm {
	private List<Menu> ms;
	private List<AdminMenu> ams;
	private String module;
	private String description;
	private String url;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Menu> getMs() {
		return ms;
	}
	public void setMs(List<Menu> ms) {
		this.ms = ms;
	}
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<AdminMenu> getAms() {
		return ams;
	}
	public void setAms(List<AdminMenu> ams) {
		this.ams = ams;
	}
	
}
