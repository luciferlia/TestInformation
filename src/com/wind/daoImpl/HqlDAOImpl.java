package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.HqlDAO;

public class HqlDAOImpl extends HibernateDaoSupport implements HqlDAO {

	@Override
	public List findByHql(String hql) {
		
		return this.getHibernateTemplate().find(hql);
	}

}
