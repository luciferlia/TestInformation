package com.wind.service;

import java.util.List;

import com.wind.entity.Casestore;

public interface CasestoreService {
	void save(Casestore cs);
	 void delete(int id);
	 void update(Casestore cs);
	 Casestore findById(int id);
	 List<Casestore> findAll();
	 List<Casestore> findByCaseTypeId(int id);
	 Casestore findByName(String name);
}
