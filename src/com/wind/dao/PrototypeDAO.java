package com.wind.dao;

import java.util.List;

import com.wind.entity.Prototype;

public interface PrototypeDAO {
	void save(Prototype prototype);
	 void delete(int id);
	 void update(Prototype prototype);
	 Prototype findById(int id);
	 List<Prototype> findAll();
}
