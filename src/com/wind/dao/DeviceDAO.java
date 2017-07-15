package com.wind.dao;

import java.util.List;

import com.wind.entity.Device;

public interface DeviceDAO {
	void save(Device device);

	void delete(int id);

	void update(Device device);

	Device findById(int id);

	List<Device> findAll();
}
