package com.wind.from;

import com.wind.entity.Module;

public class RoleMenuForm {
	private Integer id;//菜单Id
	private String name;//菜单名称
	private Module module;//所属菜单
	private String url;//菜单链接
	private boolean check;//是否选中
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
	
	
	
}
