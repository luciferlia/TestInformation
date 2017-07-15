package com.wind.dao;

import java.util.List;

import com.wind.entity.Policy;

public interface PolicyDAO {
	void save(Policy entity);
	 void delete(int id);
	 void update(Policy entity);
	 Policy findById(int id);
	 List findByProperty(String hql);
}
