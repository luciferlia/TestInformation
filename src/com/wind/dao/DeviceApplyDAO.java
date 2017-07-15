package com.wind.dao;

import java.util.List;

import com.wind.entity.Deviceapply;

public interface DeviceApplyDAO {
	void save(Deviceapply deviceapply);

	void delete(int id);

	void update(Deviceapply deviceapply);

	Deviceapply findById(int id);

	List<Deviceapply> findAll();

	List<Deviceapply> findBydeviceId(int id);
}
