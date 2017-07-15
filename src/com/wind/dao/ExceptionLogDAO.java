package com.wind.dao;

import java.util.List;

import com.wind.entity.Exceptionlog;

public interface ExceptionLogDAO {
	void save(Exceptionlog entity);
	void delete(Exceptionlog entity);
	List<Exceptionlog> findAll();
	List<Exceptionlog> findByTime(String data);
}
