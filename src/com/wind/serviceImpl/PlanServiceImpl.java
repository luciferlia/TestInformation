package com.wind.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.wind.dao.PlanCasestoreDAO;
import com.wind.dao.PlanDAO;
import com.wind.dao.ProjectUserDAO;
import com.wind.dao.UserDAO;
import com.wind.entity.Plan;
import com.wind.entity.PlanCasestore;
import com.wind.entity.Role;
import com.wind.entity.UserRole;
import com.wind.permission.dao.RoleDAO;
import com.wind.permission.dao.UserRoleDAO;
import com.wind.service.PlanService;

public class PlanServiceImpl implements PlanService {

	private PlanDAO planDAO;
	private RoleDAO roleDAO;
	private ProjectUserDAO projectUserDAO;
	private UserRoleDAO userRoleDAO;
	private UserDAO userDAO;
	private PlanCasestoreDAO planCasestoreDAO;

	public void setPlanCasestoreDAO(PlanCasestoreDAO planCasestoreDAO) {
		this.planCasestoreDAO = planCasestoreDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setProjectUserDAO(ProjectUserDAO projectUserDAO) {
		this.projectUserDAO = projectUserDAO;
	}

	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	public void setPlanDAO(PlanDAO planDAO) {
		this.planDAO = planDAO;
	}

	@Override
	public java.lang.Integer save(Plan plan) {
		// if(planDAO.findById(plan.getPlanId())==null){
		return planDAO.save(plan);
		// }

	}

	@Override
	public void delete(int id) {
		if (planDAO.findById(id) != null) {
			planDAO.delete(id);
		}

	}

	@Override
	public void update(Plan plan) {
		if (planDAO.findById(plan.getPlanId()) != null) {
			planDAO.update(plan);
		}

	}

	@Override
	public Plan findById(int id) {

		return planDAO.findById(id);
	}

	@Override
	public List<Plan> findAll() {

		return planDAO.findAll();
	}

	@Override
	public Plan findByProjectName(String name) {

		return planDAO.findByProjectName(name);
	}

	@Override
	public List<Plan> findPlanByProjectId(int id) {

		return planDAO.findPlanByProjectId(id);
	}

	@Override
	public List<Plan> findByPidAndVersionName(int pid, String versionName) {

		return planDAO.findByPidAndVName(pid, versionName);
	}

	/**
	 * 根据项目id，查找出该项目中的角色
	 */
	@Override
	public List<Role> findRoleByPid(int pid) {
		List<Role> roles = new ArrayList<Role>();
		List<Integer> userIds = projectUserDAO.findUidByPid(pid);
		for (int userId : userIds) {
			List<UserRole> userRoles = userRoleDAO.findByUid(userId);
			for (UserRole ur : userRoles) {
				Role role = roleDAO.findById(ur.getRoleId());
				roles.add(role);
			}
		}

		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public void savePlanCasestore(PlanCasestore entity) {
		planCasestoreDAO.save(entity);

	}

	@Override
	public void deletePlanCasestore(int id) {
		PlanCasestore pc = planCasestoreDAO.findById(id);
		if (pc != null)
			planCasestoreDAO.delete(pc);

	}

	@Override
	public void updatePlanCasestore(PlanCasestore entity) {
		planCasestoreDAO.update(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlanCasestore> findByPlanId(int id) {
		// TODO Auto-generated method stub
		return planCasestoreDAO.findByProperty("from PlanCasestore pct where pct.plan.planId ='" + id + "'");
	}

}
