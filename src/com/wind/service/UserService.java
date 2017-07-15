package com.wind.service;
import java.util.List;

import com.wind.entity.PageBean;
import com.wind.entity.Role;
import com.wind.entity.User;
public interface UserService {
	void saveUser(User user);
	void deleteUser(int id);
	void updateUser(User user);
	User findUserById(int id);
	User Login(String name,String pwd);
	User checkName(String name);
	List<User> findAll();
	// 查看用户所属角色
	public List<Integer> getRole(int u);
	
	public void addUserRole(int uid,int rid);
	
	public Role findRoleByUid(int uid);
	void deleteUserRole(int uid);
}
