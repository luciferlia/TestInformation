package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Function;
import com.wind.entity.Role;
import com.wind.entity.RoleFunction;

public interface RoleFunctionDAO {

	// 新增数据
	public void save(RoleFunction entity);

	// 删除数据
	public void delete(RoleFunction entity);

	public void update(RoleFunction entity);

	// 通过角色id查询功能id
	public List<Integer> findFunctionByRid(Integer i);

	// 通过角色查询功能id
	public List<Function> findFidByRid(Role r);
	//通过角色Id查询其所有数据
	public List<RoleFunction> findByRid(int rid);
	public RoleFunction findByRidAndFid(int rid,int fid);
	public List<RoleFunction> findByFid(int fid);
}
