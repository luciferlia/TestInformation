package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.CaseDAO;
import com.wind.entity.Case;

public class CaseDAOImpl extends HibernateDaoSupport implements CaseDAO{

	@Override
	public void save(Case cs) {
		this.getHibernateTemplate().save(cs);
		
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));
		
	}

	@Override
	public void update(Case cs) {
		this.getHibernateTemplate().update(cs);
		
	}

	@Override
	public Case findById(int id) {
		
		return this.getHibernateTemplate().get(Case.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Case> findAll() {
		String queryString ="from Case";
		return this.getHibernateTemplate().find(queryString);
	}

	@Override
	public List<Case> findByCasestoreId(int id) {
		String queryString ="from Case where casestoreId='"+id+"' order by num asc";
		@SuppressWarnings("unchecked")
		List<Case> list=this.getHibernateTemplate().find(queryString);
		return list;
	}

	@Override
	public List<Case> findByNum(String num) {
		String queryString ="from Case where num='"+num+"'";
		@SuppressWarnings("unchecked")
		List<Case> list=this.getHibernateTemplate().find(queryString);
		return list;
	}

}
