package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.SelectTypeDAO;
import com.wind.entity.SelectType;

public class SelectTypeDAOImpl extends HibernateDaoSupport implements SelectTypeDAO {

	@Override
	public void save(SelectType entity) {
		this.getHibernateTemplate().save(entity);

	}

	@Override
	public void delete(SelectType entity) {
		this.getHibernateTemplate().delete(entity);

	}

	@Override
	public void update(SelectType entity) {
		this.getHibernateTemplate().update(entity);

	}

	@Override
	public SelectType findById(int id) {
		
		return this.getHibernateTemplate().get(SelectType.class, id);
	}

	@Override
	public List findHql(String hql) {
		
		return this.getHibernateTemplate().find(hql);
	}

}
