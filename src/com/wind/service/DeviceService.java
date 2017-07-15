package com.wind.service;

import java.util.List;

import com.wind.entity.Device;


public interface DeviceService {
	void save(Device device);
	 void delete(int id);
	 void update(Device device);
	 Device findById(int id);
	 List<Device> findAll();
}
