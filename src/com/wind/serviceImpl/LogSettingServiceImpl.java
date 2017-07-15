package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.LogSettingDAO;
import com.wind.entity.Logsetting;
import com.wind.entity.Logsettingdetail;
import com.wind.service.LogSettingService;

public class LogSettingServiceImpl implements LogSettingService {
	private LogSettingDAO logSettingDAO;
	public void setLogSettingDAO(LogSettingDAO logSettingDAO) {
		this.logSettingDAO = logSettingDAO;
	}

	@Override
	public void save(Logsetting entity) {
		logSettingDAO.save(entity);

	}

	@Override
	public void delete(Logsetting entity) {
		logSettingDAO.delete(entity);

	}

	@Override
	public Logsetting findById(int id) {
		
		return logSettingDAO.findById(id);
	}

	@Override
	public List<Logsetting> findAll() {
		
		return logSettingDAO.findAll();
	}

	@Override
	public void saveLog(Logsettingdetail entity) {
		logSettingDAO.saveLog(entity);

	}

	@Override
	public void deleteLog(Logsettingdetail entity) {
		logSettingDAO.deleteLog(entity);

	}

	@Override
	public List<Logsettingdetail> findByLid(int id) {
		
		return logSettingDAO.findByLid(id);
	}

	@Override
	public List<Logsetting> findByName(String name) {
		
		return logSettingDAO.findByName(name);
	}

}
