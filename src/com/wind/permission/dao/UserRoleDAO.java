package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Role;
import com.wind.entity.UserRole;

public interface UserRoleDAO {
		// ��������
		public void save(UserRole entity);

		// ɾ������
		public void delete(UserRole entity);
		public void update(UserRole entity);
		// ͨ������һ���ֶν��в�ѯ
		public List<UserRole> findByProperty(String propertyName, Object value);

		// ͨ���û�id���ҽ�ɫID
		public List<Integer> findRoleIdByUid(Integer uid);
		
		public List<UserRole> findByRid(int rid);
		
		public List<UserRole> findAll();
		
		public List<UserRole> findByUid(int uid);
		public UserRole findById(int id);
}
