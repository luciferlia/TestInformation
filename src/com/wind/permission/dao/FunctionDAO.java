package com.wind.permission.dao;

import java.util.List;

import com.wind.entity.Function;

public interface FunctionDAO {

	
	// ��������
			public void save(Function entity);

			// ɾ������
			public void delete(Function entity);

			// �޸�����
			public Function update(Function entity);

			// ͨ��id��ѯ����
			public Function findById(Integer id);

			// ��ѯ��������
			public List<Function> findAll();
			//����Ȩ����������ҳ�����ƻ�ȡ��Ȩ��
			public Function findByNameAndJsp(String name,String jsp);
			//����Ȩ����������ҳ�����ƻ�ȡ��Ȩ��
			public List<Function> findByJsp(String jsp);
}
