package com.wind.dao;

import java.util.List;

import com.wind.entity.Policypool;

public interface PolicypoolDAO {
	Integer save(Policypool p);
	 void delete(int id);
	 void update(Policypool entity);
	 Policypool findById(int id);
	 List<Policypool> findAll();
	 List<Policypool> findByCustomer(String customer);
}
