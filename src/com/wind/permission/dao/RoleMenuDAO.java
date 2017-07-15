package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Menu;
import com.wind.entity.Module;
import com.wind.entity.Role;
import com.wind.entity.RoleMenu;

public interface RoleMenuDAO {
		// ��������
		public void save(RoleMenu entity);

		// ɾ������
		public void delete(RoleMenu entity);

		// ͨ������һ���ֶν��в�ѯ
		public RoleMenu findByRidAndMid(int rid,int mid);

		// ͨ����ɫid��ѯ�˵�id
		public List<Integer> findMenuHad(Integer i);

		// ͨ��ģ��id�ͽ�ɫid��ѯ����
		public List<Menu> findFInRM(Integer rid, Module pf);

		// ͨ�����������ֶν��в�ѯ
		public List<RoleMenu> findByRid(int rid);

		// ͨ����ɫid��ѯ����id
		public List<Integer> findFidByRid(Role r);
		
		public List<RoleMenu> findByMid(int mid);

}
