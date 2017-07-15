package com.wind.serviceImpl;

import java.util.List;
import com.wind.dao.PrototypeDAO;
import com.wind.entity.Prototype;
import com.wind.service.PrototypeService;

public class PrototypeServiceImpl implements PrototypeService {
	private PrototypeDAO prototypeDAO;
	public void setPrototypeDAO(PrototypeDAO prototypeDAO) {
		this.prototypeDAO = prototypeDAO;
	}
	
	@Override
	public void save(Prototype prototype) {
//		if(prototypeDAO.findById(prototype.getId())!=null){
			prototypeDAO.save(prototype);
//	}
	}
	@Override
	public void delete(int id) {
		if(prototypeDAO.findById(id)!=null){
			prototypeDAO.delete(id);
		}
	}

	@Override
	public void update(Prototype prototype) {
		if(prototypeDAO.findById(prototype.getId())!=null){
			prototypeDAO.update(prototype);
		}
	}

	@Override
	public Prototype findById(int id) {
		
		return prototypeDAO.findById(id);
	}

	@Override
	public List<Prototype> findAll() {
		return prototypeDAO.findAll();
	}

}
