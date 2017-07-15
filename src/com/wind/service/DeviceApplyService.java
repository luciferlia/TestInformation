package com.wind.service;

import java.util.List;

import com.wind.entity.Deviceapply;

public interface DeviceApplyService {
	void save(Deviceapply deviceapply);

	void delete(int id);

	void update(Deviceapply deviceapply);

	Deviceapply findById(int id);

	List<Deviceapply> findAll();

	List<Deviceapply> showDeviceapply(int id);

}
