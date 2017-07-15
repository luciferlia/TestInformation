package com.wind.daoImpl;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.UserDAO;
import com.wind.entity.User;
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO{
	public void save(User user) {
		this.getHibernateTemplate().save(user);		
	}
	public void delete(int id) {
			this.getHibernateTemplate().delete(findById(id));
	}	
	public void update(User user){
			this.getHibernateTemplate().update(user);
	}
	public User findById(int id) {
			User user = (User) this.getHibernateTemplate().get(User.class, id);
			return user;
	}	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
			String queryString = "from User";
			List<User> list =this.getHibernateTemplate().find(queryString);
			return list;
	}
	
	@Override
	public User login(String name, String pwd) {
		String queryString = "from User u where u.username='"+name+"' and u.password='"+pwd+"'";
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) this.getHibernateTemplate().find(queryString);
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public User checkName(String name) {
		String queryString = "from User u where u.username='"+name+"'";
		List<User> users = (List<User>) this.getHibernateTemplate().find(queryString);
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
	
	
}