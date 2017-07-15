package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.ProjectUserDAO;
import com.wind.entity.ProjectUser;
import com.wind.service.ProjectUserService;

public class ProjectUserServiceImpl implements ProjectUserService{
	private ProjectUserDAO projectUserDAO;
	
	public void setProjectUserDAO(ProjectUserDAO projectUserDAO) {
		this.projectUserDAO = projectUserDAO;
	}

	@Override
	public List<Integer> findUidByPid(int pid) {
		
		return projectUserDAO.findUidByPid(pid);
	}

	@Override
	public void save(ProjectUser entity) {
		if(projectUserDAO.findById(entity.getId())==null){
			projectUserDAO.save(entity);
		}
		
	}

	@Override
	public void delete(ProjectUser entity) {
		if(projectUserDAO.findById(entity.getId())!=null){
			projectUserDAO.delete(entity);
		}
		
	}

	@Override
	public void update(ProjectUser entity) {
		if(projectUserDAO.findById(entity.getId())!=null){
			projectUserDAO.update(entity);
		}
		
	}

	@Override
	public ProjectUser findById(int id) {
		
		return projectUserDAO.findById(id);
	}

	@Override
	public List<Integer> findPidByUid(int uid) {
		
		return projectUserDAO.findPidByUid(uid);
	}

	@Override
	public ProjectUser findByPidAndUid(int pid, int uid) {

		return projectUserDAO.findByPidAndUid(pid, uid);
	}

}
