package com.wind.service;

import java.util.List;

import com.wind.entity.Admin;


public interface AdminService {
	void saveAdmin(Admin admin);
	void deleteAdmin(int id);
	void updateAdmin(Admin admin);
	Admin findAdminById(int id);
	List<Admin> findAll();
	public Admin adminLogin(String username,String password);
}
