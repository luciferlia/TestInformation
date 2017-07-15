package com.wind.dao;

import java.util.List;

import com.wind.entity.Casestore;


public interface CaseStoreDAO {

	void save(Casestore cs);
	 void delete(int id);
	 void update(Casestore cs);
	 Casestore findById(int id);
	 List<Casestore> findAll();
	 List<Casestore> findByCaseTypeId(int id);
}
