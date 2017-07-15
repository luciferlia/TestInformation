package com.wind.dao;
import java.util.List;

import com.wind.entity.User;
public interface UserDAO {
	 void save(User user);
	 void delete(int id);
	 void update(User user);
	 User findById(int id);
	 List<User> findAll();
	 User login(String name,String pwd);
	 User checkName(String name);
}