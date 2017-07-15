package com.wind.serviceImpl;
import java.util.List;

import com.wind.dao.UserDAO;
import com.wind.entity.Role;
import com.wind.entity.User;
import com.wind.entity.UserRole;
import com.wind.permission.dao.RoleDAO;
import com.wind.permission.dao.UserRoleDAO;
import com.wind.service.UserService;
public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	private UserRoleDAO userRoleDAO;
	private RoleDAO roleDAO;
	
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	public void setUserDAO(UserDAO userDAO){
		this.userDAO=userDAO;
	}
	public void saveUser(User user) {
		//if(userDAO.findById(user.getId())==null)
			userDAO.save(user);
	}
	
	public void deleteUser(int id) {
		if(userDAO.findById(id)!=null)
			userDAO.delete(id);
		
	}
	public void updateUser(User user) {
		if(userDAO.findById(user.getUserId())!=null)
			userDAO.update(user);
	}
	public User findUserById(int id) {
		return userDAO.findById(id);
	}
	public List<User> findAll() {
		return userDAO.findAll();
	}
	
	public User Login(String name,String pwd){
		return userDAO.login(name, pwd);
	}
	@Override
	public List<Integer> getRole(int id) {
		List<Integer> roleId=userRoleDAO.findRoleIdByUid(id);
		
		return roleId;

	}
	@Override
	public void addUserRole(int uid,int rid) {
		List<UserRole> urs=userRoleDAO.findByUid(uid);
		boolean bflag=false;
		for(UserRole ur:urs){
			if(ur.getRoleId()==rid||ur.getRoleId().equals(rid)){
				bflag=true;
				break;
			}else{
				ur.setRoleId(rid);
				userRoleDAO.update(ur);
				bflag=true;
			}
		}
		if(bflag==false){
			UserRole ur=new UserRole();
			ur.setRoleId(rid);
			ur.setUserId(uid);
			userRoleDAO.save(ur);
		}
		
	}
	@Override
	public Role findRoleByUid(int uid) {
		List<Integer> roleIds=userRoleDAO.findRoleIdByUid(uid);
		if(roleIds.size()>0){
			return roleDAO.findById(roleIds.get(0));
		}
		return null;
	}
	//删除用户的角色
	@Override
	public void deleteUserRole(int uid) {
		List<UserRole> urs=userRoleDAO.findByUid(uid);
		for(UserRole ur:urs){
			userRoleDAO.delete(ur);
		}
		
	}
	@Override
	public User checkName(String name) {
		
		return userDAO.checkName(name);
	}
	
	
	
	
	
}
