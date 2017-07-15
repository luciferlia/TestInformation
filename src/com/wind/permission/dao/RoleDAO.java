package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Role;

public interface RoleDAO {
		// 新增数据
		public void save(Role entity);

		// 删除数据
		public void delete(Role entity);

		// 修改数据
		public Role update(Role entity);

		// 通过id查询数据
		public Role findById(Integer id);

		// 查询所有数据
		public List<Role> findAll();

}
