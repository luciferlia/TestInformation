package com.wind.service;

import java.util.List;

import com.wind.entity.Casetype;

public interface CaseTypeService {
	Integer save(Casetype cst);
	 void delete(int id);
	 void update(Casetype cst);
	 Casetype findById(int id);
	 List<Casetype> findAll();
	 Casetype findByName(String name);
}
