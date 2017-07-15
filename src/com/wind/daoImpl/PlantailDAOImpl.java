package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.PlantailDAO;
import com.wind.entity.Plantail;

public class PlantailDAOImpl extends HibernateDaoSupport implements PlantailDAO {

	@Override
	public void save(Plantail entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(Plantail entity) {
		this.getHibernateTemplate().delete(entity);

	}

	@Override
	public void update(Plantail entity) {
		this.getHibernateTemplate().update(entity);

	}

	@Override
	public Plantail findById(int id) {
		Plantail p=this.getHibernateTemplate().get(Plantail.class, id);
		return p;
	}

	

	@Override
	public List findByProperty(String hql) {
		List list=this.getHibernateTemplate().find(hql);
		return list;
	}

}
