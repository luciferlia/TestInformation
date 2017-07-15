package com.wind.permission.service;

import java.util.List;

import com.wind.entity.Function;
import com.wind.entity.Role;
import com.wind.entity.RoleFunction;

public interface RoleFunctionService {
	// 新增数据
	public void save(RoleFunction entity);

	// 删除数据
	public void delete(RoleFunction entity);

	public void update(RoleFunction entity);

	// 通过角色id查询功能id
	public List<Integer> findFunctionByRid(Integer i);

	// 通过角色查询功能id
	public List<Function> findFidByRid(Role r);
	//通过角色id和权限id查找角色权限
	public RoleFunction findRF(int rid,int fid);
}
