package com.wind.permission.service;

import java.util.List;

import com.wind.entity.AdminMenu;
import com.wind.entity.Menu;
import com.wind.entity.Module;

public interface MenuService {
		// ��������
		public void newMenu(Menu f, Integer mid);

		// �����ִ�й���
		public List<Menu> findMenu(List<Integer> rid, Module m);

		// ɾ������
		public void removeMenu(Menu f);

		// ���鹦��
		public Menu findSingleMenu(int m);

		// �޸Ĺ���
		public void modifyMenu(Menu f);

		// ���ȫ������
		public List<Menu> findMByPId(Module m);
		public List<Menu> findMenuByRid(int id);
		public List<Menu> finAll();
		public List<AdminMenu> findAllAdminMenu(); 

}
