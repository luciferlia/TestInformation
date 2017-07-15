package com.wind.service;

import java.util.List;

import com.wind.entity.SelectType;

public interface SelectTypeService {
	void addType(SelectType entity);
	void deleteType(SelectType entity);
	void updateType(SelectType entity);
	SelectType findById(int id);
	List findByHql(String hql);
}	
