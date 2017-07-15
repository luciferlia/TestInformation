package com.wind.entity;

/**
 * RoleMenu entity. @author MyEclipse Persistence Tools
 */
public class RoleMenu{
	private Integer id;
	private Menu menu;
	private Role role;

	// Constructors

	/** default constructor */
	public RoleMenu() {
	}

	/** full constructor */
	public RoleMenu(Menu menu, Role role) {
		this.menu = menu;
		this.role = role;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
