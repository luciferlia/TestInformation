package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.AdminMenu;

public interface AdminMenuDAO {
		// ��������
		public void save(AdminMenu entity);

		// ɾ������
		public void delete(AdminMenu entity);

		// �޸�����
		public AdminMenu update(AdminMenu entity);

		// ͨ��id��ѯ����
		public AdminMenu findById(Integer id);

		// ͨ������һ���ֶβ�ѯ����
		public List<AdminMenu> findByProperty(String propertyName, Object value);

		// ��ѯ��������
		public List<AdminMenu> findAll();
}
