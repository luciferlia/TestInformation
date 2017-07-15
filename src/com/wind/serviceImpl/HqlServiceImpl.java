package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.HqlDAO;
import com.wind.service.HqlService;

public class HqlServiceImpl implements HqlService {
	private HqlDAO hqlDAO;
	
	public void setHqlDAO(HqlDAO hqlDAO) {
		this.hqlDAO = hqlDAO;
	}

	@Override
	public List findByHql(String hql) {
		
		return hqlDAO.findByHql(hql);
	}

}
