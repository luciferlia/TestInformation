package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.AdminDAO;
import com.wind.entity.Admin;
import com.wind.entity.User;

public class AdminDAOImpl extends HibernateDaoSupport implements AdminDAO {

	@Override
	public void save(Admin admin) {
		this.getHibernateTemplate().save(admin);	
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));

	}

	@Override
	public void update(Admin admin) {
		this.getHibernateTemplate().update(admin);

	}

	@Override
	public Admin findById(int id) {
		Admin admin=this.getHibernateTemplate().get(Admin.class, id);
		return admin;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findAll() {
		String queryString = "from Admin";
		List<Admin> list =this.getHibernateTemplate().find(queryString);
		return list;
	}

	@Override
	public Admin adminLogin(String username, String password) {
		String queryString = "from Admin a where a.admin_name='"+username+"' and a.password='"+password+"'";
		@SuppressWarnings("unchecked")
		List<Admin> list =this.getHibernateTemplate().find(queryString);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
