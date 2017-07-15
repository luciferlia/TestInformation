package com.wind.dao;

import java.util.List;

import com.wind.entity.Plantail;

public interface PlantailDAO {
	void save(Plantail entity);
	 void delete(Plantail entity);
	 void update(Plantail entity);
	 Plantail findById(int id);
	 List findByProperty(String hql);
}
