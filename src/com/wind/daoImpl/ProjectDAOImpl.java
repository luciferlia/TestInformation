package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.ProjectDAO;
import com.wind.entity.Plan;
import com.wind.entity.Project;

public class ProjectDAOImpl extends HibernateDaoSupport implements ProjectDAO {

	@Override
	public java.lang.Integer save(Project project) {
		return (java.lang.Integer)getHibernateTemplate().save(project);

	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));

	}

	@Override
	public void update(Project project) {
		this.getHibernateTemplate().update(project);

	}

	@Override
	public Project findById(int id) {
		Project project=this.getHibernateTemplate().get(Project.class, id);
		return project;
	}

	@Override
	public List<Project> findAll() {
		String queryString="from Project";
		@SuppressWarnings("unchecked")
		List<Project> list=this.getHibernateTemplate().find(queryString);
		return list;
	}

	@Override
	public Project findByName(String name) {
		//System.out.println("Ãû³Æ£º"+name);
		//System.out.println("from Project p where p.projectName='"+name+"'");
		String queryString="from Project p where p.projectName='"+name+"'";
		@SuppressWarnings("unchecked")
		List<Project> list=this.getHibernateTemplate().find(queryString);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Plan> findByPhase(String name,String phase) {
		// TODO Auto-generated method stub
		String queryString="from Plan p where p.phase='"+phase+"' and p.projectName='"+name+"'";
		@SuppressWarnings("unchecked")
		List<Plan> list=this.getHibernateTemplate().find(queryString);
		
		return list;		
	}

}
