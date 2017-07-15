package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.ProjectoutsourcingDAO;
import com.wind.entity.Projectoutsourcing;

public class ProjectoutsourcingDAOImpl extends HibernateDaoSupport implements ProjectoutsourcingDAO {

	@Override
	public void save(Projectoutsourcing projectoutsourcing) {
		this.getHibernateTemplate().save(projectoutsourcing);

	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));

	}

	@Override
	public void update(Projectoutsourcing projectoutsourcing) {
		this.getHibernateTemplate().update(projectoutsourcing);

	}

	@Override
	public Projectoutsourcing findById(int id) {
		Projectoutsourcing projectoutsourcing=this.getHibernateTemplate().get(Projectoutsourcing.class, id);
		return projectoutsourcing;
	}

	@Override
	public List<Projectoutsourcing> findAll() {
		String queryString="from Projectoutsourcing";
		@SuppressWarnings("unchecked")
		List<Projectoutsourcing> list=this.getHibernateTemplate().find(queryString);
		return list;
	}


}
