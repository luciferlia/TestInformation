package com.wind.permission.service;

import java.util.List;

import com.wind.entity.Role;
import com.wind.entity.RoleFunction;
import com.wind.entity.RoleMenu;
import com.wind.entity.UserRole;
import com.wind.permission.dao.RoleDAO;
import com.wind.permission.dao.RoleFunctionDAO;
import com.wind.permission.dao.RoleMenuDAO;
import com.wind.permission.dao.UserRoleDAO;

public class RoleServiceImpl implements RoleService {
	
	private RoleDAO roleDAO;
	private RoleFunctionDAO roleFunctionDAO;
	private UserRoleDAO userRoleDAO;
	private RoleMenuDAO roleMenuDAO;

	public void setRoleFunctionDAO(RoleFunctionDAO roleFunctionDAO) {
		this.roleFunctionDAO = roleFunctionDAO;
	}

	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	public void setRoleMenuDAO(RoleMenuDAO roleMenuDAO) {
		this.roleMenuDAO = roleMenuDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public void save(Role entity) {
		roleDAO.save(entity);

	}

	@Override
	public void deleteRole(Role entity) {
		//删除角色关联的所有用户
		List<UserRole> userRoles=userRoleDAO.findByRid(entity.getRoleId());
		for(UserRole ur:userRoles){
			userRoleDAO.delete(ur);
		}
		//删除角色关联的所有菜单
		List<RoleMenu> roleMenus=roleMenuDAO.findByRid(entity.getRoleId());
		for(RoleMenu rm:roleMenus){
			roleMenuDAO.delete(rm);
		}
		//删除角色关联的所有权限
		List<RoleFunction> roleFunctions=roleFunctionDAO.findByRid(entity.getRoleId());
		for(RoleFunction rf:roleFunctions){
			roleFunctionDAO.delete(rf);
		}
		//删除角色
		roleDAO.delete(entity);

	}

	@Override
	public Role update(Role entity) {
		
		return roleDAO.update(entity);
	}

	@Override
	public Role findById(Integer id) {
		
		return roleDAO.findById(id);
	}

	@Override
	public List<Role> findAll() {
		
		return roleDAO.findAll();
	}

	@Override
	public List<RoleMenu> findRMByRid(int rid) {
		
		return roleMenuDAO.findByRid(rid);
	}

	@Override
	public List<RoleFunction> findRFByRid(int rid) {
		
		return roleFunctionDAO.findByRid(rid);
	}

	@Override
	public void addRoleMenu(RoleMenu rm) {
		roleMenuDAO.save(rm);
		
	}

	@Override
	public void deleteRoleMenu(int rid,int mid) {
		RoleMenu rm=roleMenuDAO.findByRidAndMid(rid, mid);
		roleMenuDAO.delete(rm);
		
	}

}
