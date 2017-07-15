package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.CaseTypeDAO;
import com.wind.entity.Casetype;

public class CaseTypeDAOImpl extends HibernateDaoSupport implements CaseTypeDAO {

	@Override
	public Integer save(Casetype cst) {
		return (Integer) this.getHibernateTemplate().save(cst);

	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));

	}

	@Override
	public void update(Casetype cst) {
		this.getHibernateTemplate().update(cst);

	}

	@Override
	public Casetype findById(int id) {
		
		return this.getHibernateTemplate().get(Casetype.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Casetype> findAll() {
		String queryString="from Casetype";
		return this.getHibernateTemplate().find(queryString);
	}

}
