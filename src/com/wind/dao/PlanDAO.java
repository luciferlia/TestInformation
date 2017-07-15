package com.wind.dao;

import java.util.List;

import com.wind.entity.Plan;
import com.wind.entity.Project;


public interface PlanDAO {
	public java.lang.Integer save(Plan plan);
	 void delete(int id);
	 void update(Plan plan);
	 Plan findById(int id);
	 List<Plan> findAll();
	 Plan findByProjectName(String name);
	 List<Plan> findPlanByProjectId(int id);
	 List<Plan> findByPidAndVName(int pid,String versionName);
	
}
