package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Function;
import com.wind.entity.Role;
import com.wind.entity.RoleFunction;

public interface RoleFunctionDAO {

	// ��������
	public void save(RoleFunction entity);

	// ɾ������
	public void delete(RoleFunction entity);

	public void update(RoleFunction entity);

	// ͨ����ɫid��ѯ����id
	public List<Integer> findFunctionByRid(Integer i);

	// ͨ����ɫ��ѯ����id
	public List<Function> findFidByRid(Role r);
	//ͨ����ɫId��ѯ����������
	public List<RoleFunction> findByRid(int rid);
	public RoleFunction findByRidAndFid(int rid,int fid);
	public List<RoleFunction> findByFid(int fid);
}
