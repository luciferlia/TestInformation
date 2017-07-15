package com.wind.permission.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.entity.Function;

public class FunctionDAOImpl extends HibernateDaoSupport implements FunctionDAO {

	@Override
	public void save(Function entity) {
		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.save(entity);
			t.commit();
		}catch(Exception e){
			t.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void delete(Function entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public Function update(Function entity) {
		this.getHibernateTemplate().update(entity);
		return null;
	}

	@Override
	public Function findById(Integer id) {
		return this.getHibernateTemplate().get(Function.class, id);
	}

	@Override
	public List<Function> findAll() {
		String hql="from Function";
		@SuppressWarnings("unchecked")
		List<Function> list=this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Function findByNameAndJsp(String name, String jsp) {
		String hql="from Function f where f.name='"+name+"' and f.jsp='"+jsp+"'";
		List<Function> f=this.getHibernateTemplate().find(hql);
		if(f.size()>0){
			return f.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findByJsp(String jsp) {
		String hql="from Function f where f.jsp='"+jsp+"'";
		List<Function> f=this.getHibernateTemplate().find(hql);
		
		return f;
	}

}
