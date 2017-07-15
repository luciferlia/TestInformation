package com.wind.service;

import java.util.List;

import com.wind.entity.Caseresult;

public interface CaseResultService {

	public java.lang.Integer save(Caseresult caseresult);

	void delete(int id);

	void update(Caseresult caseresult);

	Caseresult findById(int id);

	List<Caseresult> findAll();

	List<Caseresult> findByPlanId(int id);
	Caseresult findByPidAndCid(int planId,int caseId);
	List<Caseresult> findByPidAndUid(int planId,int userId);
}
