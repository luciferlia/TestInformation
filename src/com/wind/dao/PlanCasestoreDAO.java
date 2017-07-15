package com.wind.dao;

import java.util.List;

import com.wind.entity.PlanCasestore;

public interface PlanCasestoreDAO {
	void save(PlanCasestore entity);

	void delete(PlanCasestore entity);

	void update(PlanCasestore entity);

	PlanCasestore findById(int id);

	List findByProperty(String hql);
}
