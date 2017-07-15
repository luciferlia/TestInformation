package com.wind.service;

import java.util.List;

import com.wind.entity.Policy;

public interface PolicyService {
	List<Policy> findAllPolicy(String hql);
	void addPolicy(Policy entiey);
	void updatePolicy(Policy entity);
	Policy findById(int id);
	void deletePolicy(int id);
}
