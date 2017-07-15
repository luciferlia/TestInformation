package com.wind.permission.service;

import java.util.ArrayList;
import java.util.List;

import com.wind.entity.Module;
import com.wind.permission.dao.ModuleDAO;
import com.wind.permission.dao.RoleMenuDAO;

public class ModuleServiceImpl implements ModuleService {
	private RoleMenuDAO roleMenuDAO;
	private ModuleDAO moduleDAO;
	
	public void setRoleMenuDAO(RoleMenuDAO roleMenuDAO) {
		this.roleMenuDAO = roleMenuDAO;
	}

	

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}



	//浏览可进入权限
	@Override
	public List<Module> findModule(List<Integer> rid) {
		List<Module> ms=new ArrayList<Module>();
		for(Integer i:rid){
			ms.addAll(moduleDAO.findAll());
		}
		return ms;

	}

	//新增权限
	@Override
	public void newModule(Module m) {
		moduleDAO.save(m);

	}

	//查询单个权限
	@Override
	public Module findSingleModule(Integer id) {
		
		return moduleDAO.findById(id);
	}
	//修改权限
	@Override
	public void modifyModule(Module m) {
		moduleDAO.update(m);

	}
	
	//浏览全部权限
	@Override
	public List<Module> operateModule() {
		
		return moduleDAO.findAll();
	}

	@Override
	public void removeModule(Module p) {
		moduleDAO.delete(p);

	}



	@Override
	public List<Module> findAll() {
		
		return moduleDAO.findAll();
	}

}
