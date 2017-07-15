package com.wind.permission.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.entity.UserRole;

public class UserRoleDAOImpl extends HibernateDaoSupport implements UserRoleDAO{

	@Override
	public void save(UserRole entity) {
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
	public void delete(UserRole entity) {
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
	public List<UserRole> findByProperty(String propertyName, Object value) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="from UserRole ur where ur.'"+propertyName+"'=?";
//		Query q = session.createQuery(hql);
//		q.setParameter(0, value);
//		List<UserRole> urs=q.list();
//		t.commit();
		String hql="from UserRole ur where ur.'"+propertyName+"'='"+value+"'";
		List<UserRole> urs=this.getHibernateTemplate().find(hql);
		return urs;
	}

	@Override
	public List<Integer> findRoleIdByUid(Integer uid) {
		
		/*SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		String hql="from UserRole ur where ur.userId=?";
		Query q = session.createQuery(hql);
		q.setParameter(0, uid);
		List<Role> roles=q.list();
		t.commit();*/
		String hql="select ur.roleId from UserRole ur where ur.userId='"+uid+"'";
		@SuppressWarnings("unchecked")
		List<Integer> roles=this.getHibernateTemplate().find(hql);
		return roles;
	}

	

	@Override
	public List<UserRole> findByRid(int rid) {
		String hql="from UserRole ur where ur.roleId='"+rid+"'";
		@SuppressWarnings("unchecked")
		List<UserRole> userRoles=this.getHibernateTemplate().find(hql);
		return userRoles;
	}

	@Override
	public List<UserRole> findAll() {
		String hql="from UserRole";
		@SuppressWarnings("unchecked")
		List<UserRole> userRoles=this.getHibernateTemplate().find(hql);
		return userRoles;
	}

	@Override
	public List<UserRole> findByUid(int uid) {
		String hql="from UserRole ur where ur.userId='"+uid+"'";
		@SuppressWarnings("unchecked")
		List<UserRole> userRoles=this.getHibernateTemplate().find(hql);
		return userRoles;
	}

	@Override
	public void update(UserRole entity) {
		this.getHibernateTemplate().update(entity);
		
	}

	@Override
	public UserRole findById(int id) {
		return this.getHibernateTemplate().get(UserRole.class, id);
	}

}
