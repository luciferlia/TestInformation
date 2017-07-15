package com.wind.permission.service;

import java.util.List;

import com.wind.entity.Module;

public interface ModuleService {
		// ����ɽ���Ȩ��
		public List<Module> findModule(List<Integer> rid);
		public List<Module> findAll();
		// ����Ȩ��
		public void newModule(Module m);

		// ����Ȩ��
		public Module findSingleModule(Integer id);

		// �޸�Ȩ��
		public void modifyModule(Module m);

		// ���ȫ��Ȩ��
		public List<Module> operateModule();

		// ɾ��Ȩ��
		public void removeModule(Module m);

}
