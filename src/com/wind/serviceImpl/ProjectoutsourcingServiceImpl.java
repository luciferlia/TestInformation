package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.ProjectoutsourcingDAO;
import com.wind.entity.Projectoutsourcing;
import com.wind.service.ProjectoutsourcingService;

public class ProjectoutsourcingServiceImpl implements ProjectoutsourcingService {
	private ProjectoutsourcingDAO projectoutsourcingDAO;
	
	public void setProjectoutsourcingDAO(ProjectoutsourcingDAO projectoutsourcingDAO) {
		this.projectoutsourcingDAO = projectoutsourcingDAO;
	}

	@Override
	public void save(Projectoutsourcing projectoutsourcing) {
		if(projectoutsourcingDAO.findById(projectoutsourcing.getId())==null){
			projectoutsourcingDAO.save(projectoutsourcing);
		}

	}

	@Override
	public void delete(int id) {
		if(projectoutsourcingDAO.findById(id)!=null){
			projectoutsourcingDAO.delete(id);
		}

	}

	@Override
	public void update(Projectoutsourcing projectoutsourcing) {
		if(projectoutsourcingDAO.findById(projectoutsourcing.getId())!=null){
			projectoutsourcingDAO.update(projectoutsourcing);
		}

	}

	@Override
	public Projectoutsourcing findById(int id) {
		
		return projectoutsourcingDAO.findById(id);
	}

	@Override
	public List<Projectoutsourcing> findAll() {
		
		return projectoutsourcingDAO.findAll();
	}


}
