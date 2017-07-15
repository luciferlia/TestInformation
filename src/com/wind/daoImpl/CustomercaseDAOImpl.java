package com.wind.daoImpl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.CustomercaseDAO;
import com.wind.entity.Customercase;

public class CustomercaseDAOImpl extends HibernateDaoSupport implements CustomercaseDAO {

	@Override
	public void save(Customercase entity) {
		this.getHibernateTemplate().save(entity);

	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));

	}

	@Override
	public Customercase findById(int id) {
		Customercase c = this.getHibernateTemplate().get(Customercase.class, id);
		return c;
	}

}
