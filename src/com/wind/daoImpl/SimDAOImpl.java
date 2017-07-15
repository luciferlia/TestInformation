package com.wind.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.SimDAO;
import com.wind.entity.Sim;

public class SimDAOImpl extends HibernateDaoSupport implements SimDAO {

	@Override
	public void save(Sim sim) {
		//this.getHibernateTemplate().save(sim);
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction t=session.beginTransaction();
		try{
			session.save(sim);
			t.commit();
		}catch(Exception e){
			t.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));
	}

	@Override
	public void update(Sim sim) {
		this.getHibernateTemplate().update(sim);
	}

	@Override
	public Sim findById(int id) {
		Sim sim = (Sim) this.getHibernateTemplate().get(Sim.class, id);
		return sim;
	}

	@Override
	public List<Sim> findAll() {
		String queryString = "from Sim";
		@SuppressWarnings("unchecked")
		List<Sim> list = (List<Sim>) this.getHibernateTemplate().find(queryString);	
		return list;
	}

}
