package com.wind.service;

import java.util.List;

import com.wind.entity.Plan;
import com.wind.entity.PlanCasestore;
import com.wind.entity.Role;

public interface PlanService {

	public java.lang.Integer save(Plan plan);

	void delete(int id);

	void update(Plan plan);

	Plan findById(int id);

	List<Plan> findAll();

	Plan findByProjectName(String name);

	List<Plan> findPlanByProjectId(int id);

	List<Plan> findByPidAndVersionName(int pid, String versionName);

	List<Role> findRoleByPid(int pid);

	List<PlanCasestore> findByPlanId(int id);

	void savePlanCasestore(PlanCasestore entity);

	void deletePlanCasestore(int id);

	void updatePlanCasestore(PlanCasestore entity);
}
