package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Menu;

public interface MenuDAO {
		// 新增数据
		public void save(Menu entity);

		// 删除数据
		public void delete(Menu entity);

		// 修改数据
		public Menu update(Menu entity);

		// 通过id查询数据
		public Menu findById(Integer id);

		// 通过表中一个字段查询数据
		public List<Menu> findByProperty(String propertyName, Object value);

		// 查询所有数据
		public List<Menu> findAll();
}
