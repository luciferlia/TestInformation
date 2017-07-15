package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.PlanCasestoreDAO;
import com.wind.entity.PlanCasestore;

public class PlanCasestoreDAOImpl extends HibernateDaoSupport implements PlanCasestoreDAO {

	@Override
	public void save(PlanCasestore entity) {
		this.getHibernateTemplate().save(entity);

	}

	@Override
	public void delete(PlanCasestore entity) {
		this.getHibernateTemplate().delete(entity);

	}

	@Override
	public void update(PlanCasestore entity) {
		this.getHibernateTemplate().update(entity);

	}

	@Override
	public PlanCasestore findById(int id) {

		return this.getHibernateTemplate().get(PlanCasestore.class, id);
	}

	@Override
	public List findByProperty(String hql) {
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
