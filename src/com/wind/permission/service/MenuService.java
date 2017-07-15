package com.wind.permission.service;

import java.util.List;

import com.wind.entity.AdminMenu;
import com.wind.entity.Menu;
import com.wind.entity.Module;

public interface MenuService {
		// 新增功能
		public void newMenu(Menu f, Integer mid);

		// 浏览可执行功能
		public List<Menu> findMenu(List<Integer> rid, Module m);

		// 删除功能
		public void removeMenu(Menu f);

		// 单查功能
		public Menu findSingleMenu(int m);

		// 修改功能
		public void modifyMenu(Menu f);

		// 浏览全部功能
		public List<Menu> findMByPId(Module m);
		public List<Menu> findMenuByRid(int id);
		public List<Menu> finAll();
		public List<AdminMenu> findAllAdminMenu(); 

}
