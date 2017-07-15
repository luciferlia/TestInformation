package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Module;

public interface ModuleDAO {
		// 新增数据
		public void save(Module entity);

		// 删除数据
		public void delete(Module entity);

		// 修改数据
		public Module update(Module entity);

		// 通过id查询数据
		public Module findById(Integer id);

		// 查询所有数据
		public List<Module> findAll();

}
