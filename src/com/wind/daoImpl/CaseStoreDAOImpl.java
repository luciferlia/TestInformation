package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.CaseStoreDAO;
import com.wind.entity.Casestore;

public class CaseStoreDAOImpl extends HibernateDaoSupport implements CaseStoreDAO {

	@Override
	public void save(Casestore cs) {
		this.getHibernateTemplate().save(cs);
		
	}

	@Override
	public void update(Casestore cs) {
		this.getHibernateTemplate().update(cs);
		
	}

	@Override
	public Casestore findById(int id) {
		
		return this.getHibernateTemplate().get(Casestore.class, id);
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));
		
	}

	@Override
	public List<Casestore> findAll() {
		String queryString="from Casestore";
		@SuppressWarnings("unchecked")
		List<Casestore> list=this.getHibernateTemplate().find(queryString);
		return list;
	}

	@Override
	public List<Casestore> findByCaseTypeId(int id) {
		String queryString ="from Casestore where casetypeId='"+id+"'";
		@SuppressWarnings("unchecked")
		List<Casestore> list=this.getHibernateTemplate().find(queryString);
		return list;
	}

}
