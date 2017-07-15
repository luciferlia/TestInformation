package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.LogSettingDAO;
import com.wind.entity.Logsetting;
import com.wind.entity.Logsettingdetail;

public class LogSettingDAOImpl extends HibernateDaoSupport implements LogSettingDAO {

	@Override
	public void save(Logsetting entity) {
		this.getHibernateTemplate().save(entity);

	}

	@Override
	public void delete(Logsetting entity) {
		this.getHibernateTemplate().delete(entity);

	}

	@Override
	public Logsetting findById(int id) {
		
		return this.getHibernateTemplate().get(Logsetting.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Logsetting> findAll() {
		String hql="from Logsetting";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void saveLog(Logsettingdetail entity) {
		this.getHibernateTemplate().save(entity);

	}

	@Override
	public void deleteLog(Logsettingdetail entity) {
		this.getHibernateTemplate().delete(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Logsettingdetail> findByLid(int id) {
		String hql="from Logsettingdetail l where l.logsetting.id='"+id+"'";
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Logsetting> findByName(String name) {
		String hql="from Logsetting l where l.name='"+name+"'";
		return this.getHibernateTemplate().find(hql);
	}

}
