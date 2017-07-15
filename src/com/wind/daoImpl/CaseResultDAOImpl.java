package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.CaseResultDAO;
import com.wind.entity.Caseresult;

public class CaseResultDAOImpl extends HibernateDaoSupport implements CaseResultDAO {

	@Override
	public void save(Caseresult entity) {
		this.getHibernateTemplate().save(entity);

	}

	@Override
	public void delete(Caseresult entity) {
		this.getHibernateTemplate().delete(entity);

	}

	@Override
	public void update(Caseresult entity) {
		this.getHibernateTemplate().update(entity);

	}

	@Override
	public Caseresult findById(int id) {
		
		return this.getHibernateTemplate().get(Caseresult.class, id);
	}

	
	@Override
	public List findByProperty(String hql) {
		List list=this.getHibernateTemplate().find(hql);
		return list;
	}

}
