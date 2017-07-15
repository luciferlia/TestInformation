package com.wind.permission.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.entity.Role;

public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO {

	@Override
	public void save(Role entity) {
		//this.getHibernateTemplate().save(entity);
		
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		try{
//			session.save(entity);
//			t.commit();
//		}catch(Exception e){
//			t.rollback();
//		}finally{
//			session.close();
//		}
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(Role entity) {
		
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
	public Role update(Role entity) {
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
	public Role findById(Integer id) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="from Role r where r.role_id=?";
//		Query q = session.createQuery(hql);
//		q.setParameter(0, id);
//		Role u=(Role)q.uniqueResult();
//		t.commit();
		String hql="from Role r where r.roleId='"+id+"'";
		List<Role> list=this.getHibernateTemplate().find(hql);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Role> findAll() {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="from Role";
//		Query q = session.createQuery(hql);
//		List<Role> roles=q.list();
//		t.commit();
		String hql="from Role";
		List<Role> list=this.getHibernateTemplate().find(hql);
		return list;
	}

}
