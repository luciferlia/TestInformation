package com.wind.permission.service;

import java.util.List;

import com.wind.entity.Function;
import com.wind.entity.Role;
import com.wind.entity.RoleFunction;
import com.wind.permission.dao.RoleFunctionDAO;

public class RoleFunctionServiceImpl implements RoleFunctionService {
	
	private RoleFunctionDAO roleFunctionDAO;

	

	public void setRoleFunctionDAO(RoleFunctionDAO roleFunctionDAO) {
		this.roleFunctionDAO = roleFunctionDAO;
	}

	@Override
	public void save(RoleFunction entity) {
		roleFunctionDAO.save(entity);

	}

	@Override
	public void delete(RoleFunction entity) {
		roleFunctionDAO.delete(entity);

	}

	@Override
	public void update(RoleFunction entity) {
		roleFunctionDAO.update(entity);
	}

	@Override
	public List<Integer> findFunctionByRid(Integer i) {
		
		return roleFunctionDAO.findFunctionByRid(i);
	}

	@Override
	public List<Function> findFidByRid(Role r) {
		
		return roleFunctionDAO.findFidByRid(r);
	}

	@Override
	public RoleFunction findRF(int rid, int fid) {
		
		return roleFunctionDAO.findByRidAndFid(rid, fid);
	}

}
