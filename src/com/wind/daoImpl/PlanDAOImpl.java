package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.PlanDAO;
import com.wind.dao.ProjectDAO;
import com.wind.entity.Plan;
import com.wind.entity.Project;

public class PlanDAOImpl extends HibernateDaoSupport implements PlanDAO {
	@Override
	public java.lang.Integer save(Plan plan) {
		return (java.lang.Integer) getHibernateTemplate().save(plan);

	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));

	}

	@Override
	public void update(Plan plan) {
		this.getHibernateTemplate().update(plan);

	}

	@Override
	public Plan findById(int id) {
		Plan plan=this.getHibernateTemplate().get(Plan.class, id);
		return plan;
	}

	@Override
	public List<Plan> findAll() {
		String queryString="from Plan";
		@SuppressWarnings("unchecked")
		List<Plan> list=this.getHibernateTemplate().find(queryString);
		return list;
	}

	@Override
	public Plan findByProjectName(String name) {
		String queryString="from Plan p where p.project.projectName='"+name+"'";
		@SuppressWarnings("unchecked")
		List<Plan> list=this.getHibernateTemplate().find(queryString);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Plan> findPlanByProjectId(int id) {
		String queryString="from Plan p where p.project.id='"+id+"'";
		@SuppressWarnings("unchecked")
		List<Plan> list=this.getHibernateTemplate().find(queryString);
		return list;
	}

	@Override
	public List<Plan> findByPidAndVName(int pid, String versionName) {
		String queryString="from Plan p where p.project.id='"+pid+"' and p.version='"+versionName+"'";
		@SuppressWarnings("unchecked")
		List<Plan> list=this.getHibernateTemplate().find(queryString);
		return list;
	}

}
