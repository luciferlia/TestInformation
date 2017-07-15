package com.wind.service;

import java.util.List;

import com.wind.entity.Case;
import com.wind.entity.Customercase;

public interface CaseService {
	void save(Case cs);

	void delete(int id);

	void update(Case cs);

	Case findById(int id);

	List<Case> findAll();

	List<Case> findByCasestoreId(int id);

	// 通过表中一个字段查询数据
	List<Case> findByNum(String propertyName);

	void uploadCase(Customercase entity);

	Customercase findCusById(int cid);

	void deleteCusCase(int cid);

}
