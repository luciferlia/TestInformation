package com.wind.service;

import java.util.List;

import com.wind.entity.Device;
import com.wind.entity.Sim;


public interface SimService {
	void save(Sim sim);
	 void delete(int id);
	 void update(Sim sim);
	 Sim findById(int id);
	 List<Sim> findAll();
}
