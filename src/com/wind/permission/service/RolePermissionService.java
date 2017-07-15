package com.wind.permission.service;

import com.wind.permission.po.PermissionForm;

public interface RolePermissionService {
	public String getRolePermission(String name,String jsp);
	public PermissionForm getUserPermission(String jsp);
}
