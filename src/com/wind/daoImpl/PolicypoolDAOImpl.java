package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.PolicypoolDAO;
import com.wind.entity.Casetype;
import com.wind.entity.Policypool;

public class PolicypoolDAOImpl extends HibernateDaoSupport implements PolicypoolDAO {

	@Override
	public Integer save(Policypool entity) {
		
		return (Integer) this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));

	}

	@Override
	public void update(Policypool entity) {
		this.getHibernateTemplate().update(entity);

	}

	@Override
	public Policypool findById(int id) {
		return this.getHibernateTemplate().get(Policypool.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Policypool> findAll() {
		String queryString="from Policypool";
		return this.getHibernateTemplate().find(queryString);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Policypool> findByCustomer(String customer) {
		// TODO Auto-generated method stub
		String queryString = "from Policypool pp where pp.policyName='" + customer + "'";
		return this.getHibernateTemplate().find(queryString);
	}

}
