package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.ExceptionLogDAO;
import com.wind.entity.Exceptionlog;

public class ExceptionLogDAOImpl extends HibernateDaoSupport implements ExceptionLogDAO {

	@Override
	public void save(Exceptionlog entity) {
		this.getHibernateTemplate().save(entity);

	}

	@Override
	public void delete(Exceptionlog entity) {
		this.getHibernateTemplate().delete(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exceptionlog> findAll() {
		String hql="from Exceptionlog";
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exceptionlog> findByTime(String data) {
		String hql="from Exceptionlog e where e.startTime='"+data+"'";
		return this.getHibernateTemplate().find(hql);
	}

}
