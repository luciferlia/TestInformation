package com.wind.dao;

import java.util.List;

import com.wind.entity.Sim;

public interface SimDAO {
	void save(Sim sim);
	 void delete(int id);
	 void update(Sim sim);
	 Sim findById(int id);
	 List<Sim> findAll();
}
