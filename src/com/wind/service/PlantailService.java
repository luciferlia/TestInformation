package com.wind.service;

import java.util.List;

import com.wind.entity.Case;
import com.wind.entity.Plantail;

public interface PlantailService {

	public java.lang.Integer save(Plantail plantail);

	void delete(int id);

	void update(Plantail plantail);

	Plantail findById(int id);

	List<Plantail> findAll();

	List<Plantail> findByPlanId(int id);
	
	Plantail findByPidAndUid(int planId,int userId);
}
