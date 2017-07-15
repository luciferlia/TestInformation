package com.wind.dao;

import com.wind.entity.Customercase;

public interface CustomercaseDAO {
	void save(Customercase entity);

	void delete(int id);

	Customercase findById(int id);
}
