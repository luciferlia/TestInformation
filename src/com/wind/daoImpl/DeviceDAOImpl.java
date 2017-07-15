package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.DeviceDAO;
import com.wind.entity.Device;

public class DeviceDAOImpl extends HibernateDaoSupport implements DeviceDAO {

	@Override
	public void save(Device device) {
		this.getHibernateTemplate().save(device);
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));
	}

	@Override
	public void update(Device device) {
		this.getHibernateTemplate().update(device);
	}

	@Override
	public Device findById(int id) {
		Device device = (Device) this.getHibernateTemplate().get(Device.class, id);
		return device;
	}

	@Override
	public List<Device> findAll() {
		String queryString = "from Device";
		@SuppressWarnings("unchecked")
		List<Device> list = (List<Device>) this.getHibernateTemplate().find(queryString);	
		return list;
	}

}
