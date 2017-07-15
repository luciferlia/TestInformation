package com.wind.dao;

import java.util.List;

import com.wind.entity.SelectType;

public interface SelectTypeDAO {
	void save(SelectType entity);
	void delete(SelectType entity);
	void update(SelectType entity);
	SelectType findById(int id);
	List findHql(String hql);
}
