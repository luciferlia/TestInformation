package com.wind.daoImpl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.TestCaseDAO;
import com.wind.entity.TestCase;

public class TestCaseDAOImpl extends HibernateDaoSupport implements TestCaseDAO {

	@Override
	public void saveTestCase(TestCase entity) {
		this.getHibernateTemplate().save(entity);

	}

	@Override
	public void updateTestCase(TestCase entity) {
		this.getHibernateTemplate().update(entity);

	}

	@Override
	public void deleteTestCase(TestCase entity) {
		this.getHibernateTemplate().delete(entity);

	}

	@Override
	public TestCase findById(int id) {
		
		return this.getHibernateTemplate().get(TestCase.class, id);
	}

}
