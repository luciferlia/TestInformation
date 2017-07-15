package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.AdminDAO;
import com.wind.entity.Admin;
import com.wind.service.AdminService;

public class AdminServiceImpl implements AdminService {

	private AdminDAO adminDAO;
	

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Override
	public Admin findAdminById(int id) {
		
		return adminDAO.findById(id);
	}

	@Override
	public List<Admin> findAll() {
		
		return adminDAO.findAll();
	}

	@Override
	public void saveAdmin(Admin admin) {
		adminDAO.save(admin);
		
	}

	@Override
	public void deleteAdmin(int id) {
		adminDAO.delete(id);
		
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminDAO.update(admin);
		
	}

	@Override
	public Admin adminLogin(String username, String password) {
		
		return adminDAO.adminLogin(username, password);
	}

}
