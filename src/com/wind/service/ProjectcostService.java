package com.wind.service;

import java.util.List;

import com.wind.entity.Projectcost;

/*
 * ��Ŀ�ɱ�Service��ӿ�
 */
public interface ProjectcostService {

	void save(Projectcost projectcost);
	 void delete(int id);
	 void update(Projectcost projectcost);
	 Projectcost findById(int id);
	 List<Projectcost> findAll();
}
