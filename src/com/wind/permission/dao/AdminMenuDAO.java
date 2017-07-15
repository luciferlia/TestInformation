package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.AdminMenu;

public interface AdminMenuDAO {
		// 新增数据
		public void save(AdminMenu entity);

		// 删除数据
		public void delete(AdminMenu entity);

		// 修改数据
		public AdminMenu update(AdminMenu entity);

		// 通过id查询数据
		public AdminMenu findById(Integer id);

		// 通过表中一个字段查询数据
		public List<AdminMenu> findByProperty(String propertyName, Object value);

		// 查询所有数据
		public List<AdminMenu> findAll();
}
