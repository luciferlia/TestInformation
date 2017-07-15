package com.wind.dao;

import java.util.List;

import com.wind.entity.Caseresult;

public interface CaseResultDAO {
	void save(Caseresult entity);
	 void delete(Caseresult entity);
	 void update(Caseresult entity);
	 Caseresult findById(int id);
	 List findByProperty(String hql);
}
