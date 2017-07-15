package com.wind.permission.service;

import java.util.List;

import com.wind.entity.Function;
import com.wind.entity.RoleFunction;
import com.wind.entity.RoleMenu;
import com.wind.permission.dao.FunctionDAO;
import com.wind.permission.dao.RoleFunctionDAO;

public class FunctionServiceImpl implements FunctionService {
	
	private FunctionDAO functionDAO;
	private RoleFunctionDAO roleFunctionDAO;
	
	public void setRoleFunctionDAO(RoleFunctionDAO roleFunctionDAO) {
		this.roleFunctionDAO = roleFunctionDAO;
	}

	public void setFunctionDAO(FunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}

	@Override
	public void save(Function entity) {
		functionDAO.save(entity);
	}

	@Override
	public void delete(Function entity) {
		for (RoleFunction roleFunction : roleFunctionDAO.findByFid(entity.getFunctionId())) {
			roleFunctionDAO.delete(roleFunction);
		}
		
		functionDAO.delete(entity);

	}

	@Override
	public Function update(Function entity) {
		functionDAO.update(entity);
		return null;
	}

	@Override
	public Function findById(Integer id) {
		
		return functionDAO.findById(id);
	}

	@Override
	public List<Function> findAll() {
		return functionDAO.findAll();
	}

	@Override
	public Function findByNameAndJsp(String name, String jsp) {
		
		return functionDAO.findByNameAndJsp(name, jsp);
	}

}
