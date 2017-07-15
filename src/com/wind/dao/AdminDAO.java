package com.wind.dao;

import java.util.List;

import com.wind.entity.Admin;

public interface AdminDAO {
	 void save(Admin admin);
	 void delete(int id);
	 void update(Admin admin);
	 Admin findById(int id);
	 List<Admin> findAll();
	 Admin adminLogin(String username,String password);
	 
}
