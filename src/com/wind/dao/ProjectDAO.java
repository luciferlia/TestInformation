package com.wind.dao;

import java.util.List;

import com.wind.entity.Plan;
import com.wind.entity.Project;


public interface ProjectDAO {
	public java.lang.Integer save(Project project);
	 void delete(int id);
	 void update(Project project);
	 Project findById(int id);
	 List<Project> findAll();
	 Project findByName(String name);
	 List<Plan> findByPhase(String name,String phase);
}
