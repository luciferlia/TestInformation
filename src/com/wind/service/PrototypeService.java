package com.wind.service;

import java.util.List;

import com.wind.entity.Prototype;

public interface PrototypeService {
	void save(Prototype prototype);
	 void delete(int id);
	 void update(Prototype prototype);
	 Prototype findById(int id);
	 List<Prototype> findAll();
}
