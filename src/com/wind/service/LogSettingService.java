package com.wind.service;

import java.util.List;

import com.wind.entity.Logsetting;
import com.wind.entity.Logsettingdetail;

public interface LogSettingService {
	void save(Logsetting entity);
	void delete(Logsetting entity);
	Logsetting findById(int id);
	List<Logsetting> findAll();
	void saveLog(Logsettingdetail entity);
	void deleteLog(Logsettingdetail entity);
	List<Logsettingdetail> findByLid(int id);
	List<Logsetting> findByName(String name);
}
