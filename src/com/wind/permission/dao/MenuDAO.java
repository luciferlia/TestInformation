package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Menu;

public interface MenuDAO {
		// ��������
		public void save(Menu entity);

		// ɾ������
		public void delete(Menu entity);

		// �޸�����
		public Menu update(Menu entity);

		// ͨ��id��ѯ����
		public Menu findById(Integer id);

		// ͨ������һ���ֶβ�ѯ����
		public List<Menu> findByProperty(String propertyName, Object value);

		// ��ѯ��������
		public List<Menu> findAll();
}
