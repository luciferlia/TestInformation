package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Role;
import com.wind.entity.UserRole;

public interface UserRoleDAO {
		// 新增数据
		public void save(UserRole entity);

		// 删除数据
		public void delete(UserRole entity);
		public void update(UserRole entity);
		// 通过表中一个字段进行查询
		public List<UserRole> findByProperty(String propertyName, Object value);

		// 通过用户id查找角色ID
		public List<Integer> findRoleIdByUid(Integer uid);
		
		public List<UserRole> findByRid(int rid);
		
		public List<UserRole> findAll();
		
		public List<UserRole> findByUid(int uid);
		public UserRole findById(int id);
}
