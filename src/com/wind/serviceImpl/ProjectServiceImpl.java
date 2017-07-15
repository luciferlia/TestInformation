package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.ProjectDAO;
import com.wind.entity.Plan;
import com.wind.entity.Project;
import com.wind.service.ProjectService;

public class ProjectServiceImpl implements ProjectService {
	private ProjectDAO projectDAO;
	
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Override
	public java.lang.Integer save(Project project) {
//		if(projectDAO.findById(project.getId())==null){
			return projectDAO.save(project);
//		}

	}

	@Override
	public void delete(int id) {
		if(projectDAO.findById(id)!=null){
			projectDAO.delete(id);
		}

	}

	@Override
	public void update(Project project) {
		if(projectDAO.findById(project.getId())!=null){
			projectDAO.update(project);
		}

	}

	@Override
	public Project findById(int id) {
		
		return projectDAO.findById(id);
	}

	@Override
	public List<Project> findAll() {
		
		return projectDAO.findAll();
	}

	@Override
	public Project findByName(String name) {
		
		return projectDAO.findByName(name);
	}

	@Override
	public List<Plan> findByPhase(String name,String phase) {
		
		return projectDAO.findByPhase(name,phase);
	}

}
