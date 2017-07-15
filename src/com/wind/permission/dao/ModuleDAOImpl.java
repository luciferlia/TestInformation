package com.wind.permission.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.entity.Module;

public class ModuleDAOImpl extends HibernateDaoSupport implements ModuleDAO {

	@Override
	public void save(Module entity) {
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
	public void delete(Module entity) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		try{
//			session.delete(entity);
//			t.commit();
//		}catch(Exception e){
//			t.rollback();
//		}finally{
//			session.close();
//		}
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public Module update(Module entity) {
		this.getHibernateTemplate().update(entity);
		return null;
	}

	@Override
	public Module findById(Integer id) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="from Permission p where p.permission_id=?";
//		Query q = session.createQuery(hql);
//		q.setParameter(0, id);
//		Permission p=(Permission)q.uniqueResult();
//		t.commit();
		Module p=this.getHibernateTemplate().get(Module.class, id);
		return p;
	}

	@Override
	public List<Module> findAll() {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="from Permission";
//		Query q = session.createQuery(hql);
//		List<Permission> ps=q.list();
//		t.commit();
		String hql="from Module";
		List<Module> list=this.getHibernateTemplate().find(hql);
		return list;
	}

}
