package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Menu;
import com.wind.entity.Module;
import com.wind.entity.Role;
import com.wind.entity.RoleMenu;

public interface RoleMenuDAO {
		// 新增数据
		public void save(RoleMenu entity);

		// 删除数据
		public void delete(RoleMenu entity);

		// 通过表中一个字段进行查询
		public RoleMenu findByRidAndMid(int rid,int mid);

		// 通过角色id查询菜单id
		public List<Integer> findMenuHad(Integer i);

		// 通过模块id和角色id查询功能
		public List<Menu> findFInRM(Integer rid, Module pf);

		// 通过表中两个字段进行查询
		public List<RoleMenu> findByRid(int rid);

		// 通过角色id查询功能id
		public List<Integer> findFidByRid(Role r);
		
		public List<RoleMenu> findByMid(int mid);

}
