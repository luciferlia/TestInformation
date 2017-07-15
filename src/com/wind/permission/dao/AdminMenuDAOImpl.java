package com.wind.permission.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.entity.AdminMenu;
import com.wind.entity.Menu;

public class AdminMenuDAOImpl extends HibernateDaoSupport implements AdminMenuDAO {

	@Override
	public void save(AdminMenu entity) {
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
	public void delete(AdminMenu entity) {
		this.getHibernateTemplate().delete(entity);

	}

	@Override
	public AdminMenu update(AdminMenu entity) {
		this.getHibernateTemplate().update(entity);
		return null;
	}

	@Override
	public AdminMenu findById(Integer id) {
		return this.getHibernateTemplate().get(AdminMenu.class, id);
	}

	@Override
	public List<AdminMenu> findByProperty(String propertyName, Object value) {
		String hql="from AdminMenu m where m.'"+propertyName+"'='"+value+"'";
		@SuppressWarnings("unchecked")
		List<AdminMenu> list=this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<AdminMenu> findAll() {
		String hql="from AdminMenu";
		@SuppressWarnings("unchecked")
		List<AdminMenu> list=this.getHibernateTemplate().find(hql);
		return list;
	}

}
