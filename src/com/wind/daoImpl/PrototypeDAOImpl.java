package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.PrototypeDAO;
import com.wind.entity.Prototype;

public class PrototypeDAOImpl extends HibernateDaoSupport implements PrototypeDAO {

	@Override
	public void save(Prototype prototype) {
		this.getHibernateTemplate().save(prototype);
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));
	}

	@Override
	public void update(Prototype prototype) {
		this.getHibernateTemplate().update(prototype);
	}

	@Override
	public Prototype findById(int id) {
		Prototype prototype = (Prototype) this.getHibernateTemplate().get(Prototype.class, id);
		return prototype;
	}

	@Override
	public List<Prototype> findAll() {
		String queryString = "from Prototype";
		@SuppressWarnings("unchecked")
		List<Prototype> list = (List<Prototype>) this.getHibernateTemplate().find(queryString);	
		return list;
	}

}
