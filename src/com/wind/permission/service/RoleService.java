package com.wind.permission.service;

import java.util.List;

import com.wind.entity.Role;
import com.wind.entity.RoleFunction;
import com.wind.entity.RoleMenu;

public interface RoleService {
			// ��������
			public void save(Role entity);

			// ɾ������
			public void deleteRole(Role entity);

			// �޸�����
			public Role update(Role entity);

			// ͨ��id��ѯ����
			public Role findById(Integer id);

			// ��ѯ��������
			public List<Role> findAll();
			
			public List<RoleMenu> findRMByRid(int id);
			public List<RoleFunction> findRFByRid(int id);
			//Ϊ��ɫ��Ӳ˵�Ȩ��
			public void addRoleMenu(RoleMenu rm);
			//Ϊ��ɫɾ���˵�Ȩ��
			public void deleteRoleMenu(int rid,int mid);
}	
