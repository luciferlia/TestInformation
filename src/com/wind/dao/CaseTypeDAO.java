package com.wind.dao;

import java.util.List;

import com.wind.entity.Casetype;

public interface CaseTypeDAO {
	Integer save(Casetype cst);
	 void delete(int id);
	 void update(Casetype cst);
	 Casetype findById(int id);
	 List<Casetype> findAll();
}
