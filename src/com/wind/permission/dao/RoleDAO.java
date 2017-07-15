package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Role;

public interface RoleDAO {
		// ��������
		public void save(Role entity);

		// ɾ������
		public void delete(Role entity);

		// �޸�����
		public Role update(Role entity);

		// ͨ��id��ѯ����
		public Role findById(Integer id);

		// ��ѯ��������
		public List<Role> findAll();

}
