package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Function;

public interface FunctionDAO {

	
	// 新增数据
			public void save(Function entity);

			// 删除数据
			public void delete(Function entity);

			// 修改数据
			public Function update(Function entity);

			// 通过id查询数据
			public Function findById(Integer id);

			// 查询所有数据
			public List<Function> findAll();
			//根据权限名和所在页面名称获取该权限
			public Function findByNameAndJsp(String name,String jsp);
			//根据权限名和所在页面名称获取该权限
			public List<Function> findByJsp(String jsp);
}
