package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.DeviceApplyDAO;
import com.wind.entity.Deviceapply;

public class DeviceApplyDAOImpl extends HibernateDaoSupport implements DeviceApplyDAO {

	@Override
	public void save(Deviceapply deviceapply) {
		this.getHibernateTemplate().save(deviceapply);
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));
	}

	@Override
	public void update(Deviceapply deviceapply) {
		this.getHibernateTemplate().update(deviceapply);
	}

	@Override
	public Deviceapply findById(int id) {
		Deviceapply deviceapply = (Deviceapply) this.getHibernateTemplate().get(Deviceapply.class, id);
		return deviceapply;
	}

	@Override
	public List<Deviceapply> findAll() {
		String queryString = "from Deviceapply";
		@SuppressWarnings("unchecked")
		List<Deviceapply> list = (List<Deviceapply>) this.getHibernateTemplate().find(queryString);
		return list;
	}

	@Override
	public List<Deviceapply> findBydeviceId(int id) {
		String queryString = "from Deviceapply da where da.deviceId='" + id + "'";
		List<Deviceapply> list = (List<Deviceapply>) this.getHibernateTemplate().find(queryString);
		return list;
	}

}
