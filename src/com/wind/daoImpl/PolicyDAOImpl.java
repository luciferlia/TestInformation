package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.PolicyDAO;
import com.wind.entity.Policy;

public class PolicyDAOImpl extends HibernateDaoSupport implements PolicyDAO {

	@Override
	public void save(Policy entity) {
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));

	}

	@Override
	public void update(Policy entity) {
		this.getHibernateTemplate().update(entity);

	}

	@Override
	public Policy findById(int id) {
	
		return this.getHibernateTemplate().get(Policy.class, id);
	}

	@Override
	public List findByProperty(String hql) {
		List list=this.getHibernateTemplate().find(hql);
		return list;
	}

}
