package com.wind.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.PageDAO;

public class PageDAOImpl extends HibernateDaoSupport implements PageDAO{
	

	@Override
	public List queryForPage(final String hql, final int offset, final int length) {
		List list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List list=query.list();
				return list;
			}
		});
		return list;
	}
	@Override
	public int getAllRowCount(String hql) {
		
		return getHibernateTemplate().find(hql).size();
	}
	
}
