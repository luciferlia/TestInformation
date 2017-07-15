package com.wind.service;

import java.util.List;

import com.wind.entity.Projectoutsourcing;

public interface ProjectoutsourcingService {

	void save(Projectoutsourcing projectoutsourcing);
	 void delete(int id);
	 void update(Projectoutsourcing projectoutsourcing);
	 Projectoutsourcing findById(int id);
	 List<Projectoutsourcing> findAll();
}
