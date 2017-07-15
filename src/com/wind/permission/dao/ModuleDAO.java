package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Module;

public interface ModuleDAO {
		// ��������
		public void save(Module entity);

		// ɾ������
		public void delete(Module entity);

		// �޸�����
		public Module update(Module entity);

		// ͨ��id��ѯ����
		public Module findById(Integer id);

		// ��ѯ��������
		public List<Module> findAll();

}
