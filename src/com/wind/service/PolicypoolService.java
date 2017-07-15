package com.wind.service;

import java.util.List;

import com.wind.entity.Policypool;

public interface PolicypoolService {
	Integer save(Policypool p);
	 void delete(int id);
	 void update(Policypool p);
	 Policypool findById(int id);
	 List<Policypool> findAll();

}
