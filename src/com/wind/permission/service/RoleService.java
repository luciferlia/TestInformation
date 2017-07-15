package com.wind.permission.service;

import java.util.List;

import com.wind.entity.Role;
import com.wind.entity.RoleFunction;
import com.wind.entity.RoleMenu;

public interface RoleService {
			// 新增数据
			public void save(Role entity);

			// 删除数据
			public void deleteRole(Role entity);

			// 修改数据
			public Role update(Role entity);

			// 通过id查询数据
			public Role findById(Integer id);

			// 查询所有数据
			public List<Role> findAll();
			
			public List<RoleMenu> findRMByRid(int id);
			public List<RoleFunction> findRFByRid(int id);
			//为角色添加菜单权限
			public void addRoleMenu(RoleMenu rm);
			//为角色删除菜单权限
			public void deleteRoleMenu(int rid,int mid);
}	
