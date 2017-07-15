package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.SelectTypeDAO;
import com.wind.entity.SelectType;
import com.wind.service.SelectTypeService;

public class SelectTypeServiceImpl implements SelectTypeService {
	private SelectTypeDAO selectTypeDAO;
	
	public void setSelectTypeDAO(SelectTypeDAO selectTypeDAO) {
		this.selectTypeDAO = selectTypeDAO;
	}

	@Override
	public void addType(SelectType entity) {
		if(selectTypeDAO.findById(entity.getId())==null){
			selectTypeDAO.save(entity);
		}

	}

	@Override
	public void deleteType(SelectType entity) {
		if(selectTypeDAO.findById(entity.getId())!=null){
			selectTypeDAO.delete(entity);
		}

	}

	@Override
	public void updateType(SelectType entity) {
		if(selectTypeDAO.findById(entity.getId())!=null){
			selectTypeDAO.update(entity);
		}

	}

	@Override
	public List findByHql(String hql) {
		
		return selectTypeDAO.findHql(hql);
	}

	@Override
	public SelectType findById(int id) {
		
		return selectTypeDAO.findById(id);
	}

}
