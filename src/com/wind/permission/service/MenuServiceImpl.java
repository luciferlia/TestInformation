package com.wind.permission.service;

import java.util.ArrayList;
import java.util.List;

import com.wind.entity.AdminMenu;
import com.wind.entity.Menu;
import com.wind.entity.Module;
import com.wind.entity.RoleMenu;
import com.wind.permission.dao.AdminMenuDAO;
import com.wind.permission.dao.MenuDAO;
import com.wind.permission.dao.ModuleDAO;
import com.wind.permission.dao.RoleMenuDAO;

public class MenuServiceImpl implements MenuService {
	private RoleMenuDAO roleMenuDAO;
	private MenuDAO menuDAO;
	private ModuleDAO moduleDAO;
	private AdminMenuDAO adminMenuDAO;
	
	public void setAdminMenuDAO(AdminMenuDAO adminMenuDAO) {
		this.adminMenuDAO = adminMenuDAO;
	}

	public void setRoleMenuDAO(RoleMenuDAO roleMenuDAO) {
		this.roleMenuDAO = roleMenuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	
	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	//新增菜单
	@Override
	public void newMenu(Menu m, Integer mid) {
		Module ml = new Module();
		ml = moduleDAO.findById(mid);
		// 设置当前菜单所属权限id
		m.setModule(ml);
		menuDAO.save(m);


	}
	
	// 浏览可执行菜单
	@Override
	public List<Menu> findMenu(List<Integer> rid, Module p) {
		List<Menu> listMenu = new ArrayList<Menu>();
		for (Integer i : rid) {
			listMenu.addAll(roleMenuDAO.findFInRM(i,p));
		}
		return listMenu;

	}
	//删除菜单
	@Override
	public void removeMenu(Menu f) {
		// 删除功能时将其在关联表中的所有数据删除
		
		for (RoleMenu roleMenu : roleMenuDAO.findByMid(f.getId())) {
			roleMenuDAO.delete(roleMenu);
		}
		menuDAO.delete(f);
				


	}
	// 单查菜单
	@Override
	public Menu findSingleMenu(int m) {
		// TODO Auto-generated method stub
		return menuDAO.findById(m);
	}
	// 修改菜单
	@Override
	public void modifyMenu(Menu f) {
		menuDAO.update(f);

	}
	
	// 浏览全部菜单
	@Override
	public List<Menu> findMByPId(Module m) {
		return menuDAO.findByProperty("module.id",m.getModuleId());
	}

	@Override
	public List<Menu> findMenuByRid(int id) {
		List<Integer> is=roleMenuDAO.findMenuHad(id);
		List<Menu> ms=new ArrayList<Menu>();
		for(int line:is){
			Menu m=menuDAO.findById(line);
			ms.add(m);
		}
		
		return ms;
	}

	@Override
	public List<Menu> finAll() {
		
		return menuDAO.findAll();
	}

	@Override
	public List<AdminMenu> findAllAdminMenu() {
		
		return adminMenuDAO.findAll();
	}

}
