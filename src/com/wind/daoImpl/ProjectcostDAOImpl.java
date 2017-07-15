package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.ProjectcostDAO;
import com.wind.entity.Projectcost;
/*
 * 项目成本DAO层接口实现
 */
public class ProjectcostDAOImpl extends HibernateDaoSupport implements ProjectcostDAO {

	@Override
	public void save(Projectcost projectcost) {
		this.getHibernateTemplate().save(projectcost);

	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));

	}

	@Override
	public void update(Projectcost projectcost) {
		this.getHibernateTemplate().update(projectcost);

	}

	@Override
	public Projectcost findById(int id) {
		Projectcost projectcost=this.getHibernateTemplate().get(Projectcost.class, id);
		return projectcost;
	}

	@Override
	public List<Projectcost> findAll() {
		String queryString="from Projectcost";
		@SuppressWarnings("unchecked")
		List<Projectcost> list=this.getHibernateTemplate().find(queryString);
		return list;
	}


}
