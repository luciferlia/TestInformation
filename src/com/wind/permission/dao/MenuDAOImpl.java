package com.wind.permission.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.entity.Menu;

public class MenuDAOImpl extends HibernateDaoSupport implements MenuDAO {

	@Override
	public void save(Menu entity) {
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
	public void delete(Menu entity) {
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
	public Menu update(Menu entity) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		try{
//			session.update(entity);
//			t.commit();
//		}catch(Exception e){
//			t.rollback();
//		}finally{
//			session.close();
//		}
		this.getHibernateTemplate().update(entity);
		return null;
	}

	@Override
	public Menu findById(Integer id) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="from Menu m where m.id=?";
//		Query q = session.createQuery(hql);
//		q.setParameter(0, id);
//		Menu m=(Menu)q.uniqueResult();
//		t.commit();
		
		return this.getHibernateTemplate().get(Menu.class, id);
	}

	@Override
	public List<Menu> findByProperty(String propertyName, Object value) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="from Menu m where m.'"+propertyName+"'=?";
//		Query q = session.createQuery(hql);
//		q.setParameter(0, value);
//		List<Menu> ms=q.list();
//		t.commit();
		String hql="from Menu m where m.'"+propertyName+"'='"+value+"'";
		@SuppressWarnings("unchecked")
		List<Menu> list=this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Menu> findAll() {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="from Menu";
//		Query q = session.createQuery(hql);
//		List<Menu> ms=q.list();
//		t.commit();
		String hql="from Menu";
		@SuppressWarnings("unchecked")
		List<Menu> list=this.getHibernateTemplate().find(hql);
		return list;
	}

}
