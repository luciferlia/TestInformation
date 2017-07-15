package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.PlanDAO;
import com.wind.dao.ProjectDAO;
import com.wind.dao.ProjectcostDAO;
import com.wind.entity.Plan;
import com.wind.entity.Project;
import com.wind.entity.Projectcost;
import com.wind.service.PlanService;
import com.wind.service.ProjectService;
import com.wind.service.ProjectcostService;
/*
 * 项目成本Service层接口实现
 */
public class ProjectcostServiceImpl implements ProjectcostService {
	private ProjectcostDAO projectcostDAO;
	
	public void setProjectcostDAO(ProjectcostDAO projectcostDAO) {
		this.projectcostDAO = projectcostDAO;
	}

	@Override
	public void save(Projectcost projectcost) {
		if(projectcostDAO.findById(projectcost.getId())==null){
			projectcostDAO.save(projectcost);
		}

	}

	@Override
	public void delete(int id) {
		if(projectcostDAO.findById(id)!=null){
			projectcostDAO.delete(id);
		}

	}

	@Override
	public void update(Projectcost projectcost) {
		if(projectcostDAO.findById(projectcost.getId())!=null){
			projectcostDAO.update(projectcost);
		}

	}

	@Override
	public Projectcost findById(int id) {
		
		return projectcostDAO.findById(id);
	}

	@Override
	public List<Projectcost> findAll() {
		
		return projectcostDAO.findAll();
	}


}
