package com.wind.permission.service;

import java.util.List;

import com.wind.entity.Module;

public interface ModuleService {
		// 浏览可进入权限
		public List<Module> findModule(List<Integer> rid);
		public List<Module> findAll();
		// 新增权限
		public void newModule(Module m);

		// 单查权限
		public Module findSingleModule(Integer id);

		// 修改权限
		public void modifyModule(Module m);

		// 浏览全部权限
		public List<Module> operateModule();

		// 删除权限
		public void removeModule(Module m);

}
