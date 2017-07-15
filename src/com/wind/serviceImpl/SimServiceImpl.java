package com.wind.serviceImpl;

import java.util.List;
import com.wind.dao.DeviceDAO;
import com.wind.dao.SimDAO;
import com.wind.entity.Device;
import com.wind.entity.Sim;
import com.wind.service.DeviceService;
import com.wind.service.SimService;

public class SimServiceImpl implements SimService {
	private SimDAO simDAO;
	
	public void setSimDAO(SimDAO simDAO) {
		this.simDAO = simDAO;
	}
	@Override
	public void save(Sim sim) {
//		if(simDAO.findById(sim.getId())!=null){
			simDAO.save(sim);
//	}
	}
	@Override
	public void delete(int id) {
		if(simDAO.findById(id)!=null){
			simDAO.delete(id);
		}
	}

	@Override
	public void update(Sim sim) {
		if(simDAO.findById(sim.getId())!=null){
			simDAO.update(sim);
		}
	}

	@Override
	public Sim findById(int id) {
		
		return simDAO.findById(id);
	}

	@Override
	public List<Sim> findAll() {
		return simDAO.findAll();
	}

}
