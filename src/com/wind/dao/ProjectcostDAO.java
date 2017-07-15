package com.wind.dao;

import java.util.List;

import com.wind.entity.Projectcost;

/*
 * 项目成本DAO层接口
 */
public interface ProjectcostDAO {
	
	 void save(Projectcost projectcost);
	 void delete(int id);
	 void update(Projectcost projectcost);
	 Projectcost findById(int id);
	 List<Projectcost> findAll();
}
