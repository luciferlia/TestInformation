package com.wind.dao;

import java.util.List;

import com.wind.entity.Projectcost;

/*
 * ��Ŀ�ɱ�DAO��ӿ�
 */
public interface ProjectcostDAO {
	
	 void save(Projectcost projectcost);
	 void delete(int id);
	 void update(Projectcost projectcost);
	 Projectcost findById(int id);
	 List<Projectcost> findAll();
}
