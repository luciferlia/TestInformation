package com.wind.dao;

import java.util.List;

import com.wind.entity.ProjectUser;

public interface ProjectUserDAO {

	
	List<Integer> findUidByPid(int pid);
	void save(ProjectUser entity);
	void delete(ProjectUser entity);
	void update(ProjectUser entity);
	ProjectUser findById(int id);
	List<Integer> findPidByUid(int uid);
	ProjectUser findByPidAndUid(int pid,int uid);
}
