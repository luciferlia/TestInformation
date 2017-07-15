package com.wind.dao;

import java.util.List;

import com.wind.entity.Projectoutsourcing;


public interface ProjectoutsourcingDAO {
	void save(Projectoutsourcing projectoutsourcing);
	 void delete(int id);
	 void update(Projectoutsourcing projectoutsourcing);
	 Projectoutsourcing findById(int id);
	 List<Projectoutsourcing> findAll();
}
