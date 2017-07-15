package com.wind.permission.service;

import java.util.List;

import com.wind.entity.Function;
import com.wind.entity.Role;
import com.wind.entity.RoleFunction;

public interface RoleFunctionService {
	// ��������
	public void save(RoleFunction entity);

	// ɾ������
	public void delete(RoleFunction entity);

	public void update(RoleFunction entity);

	// ͨ����ɫid��ѯ����id
	public List<Integer> findFunctionByRid(Integer i);

	// ͨ����ɫ��ѯ����id
	public List<Function> findFidByRid(Role r);
	//ͨ����ɫid��Ȩ��id���ҽ�ɫȨ��
	public RoleFunction findRF(int rid,int fid);
}
