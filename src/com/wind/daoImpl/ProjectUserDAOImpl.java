package com.wind.daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.dao.ProjectUserDAO;
import com.wind.dao.ProjectcostDAO;
import com.wind.entity.ProjectUser;
import com.wind.entity.Projectcost;

public class ProjectUserDAOImpl extends HibernateDaoSupport implements ProjectUserDAO {

	@Override
	public List<Integer> findUidByPid(int pid) {
		String hql="select pu.user.userId from ProjectUser pu where pu.project.id='"+pid+"'";
		@SuppressWarnings("unchecked")
		List<Integer> pus=(List<Integer>)this.getHibernateTemplate().find(hql);
		return pus;
	}

	@Override
	public void save(ProjectUser entity) {
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(ProjectUser entity) {
		this.getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void update(ProjectUser entity) {
		this.getHibernateTemplate().update(entity);
		
	}

	@Override
	public ProjectUser findById(int id) {
		ProjectUser pu=this.getHibernateTemplate().get(ProjectUser.class, id);
		return pu;
	}

	@Override
	public List<Integer> findPidByUid(int uid) {
		String hql="select pu.project.id from ProjectUser pu where pu.user.userId='"+uid+"'";
		@SuppressWarnings("unchecked")
		List<Integer> pus=(List<Integer>)this.getHibernateTemplate().find(hql);
		return pus;
	}

	@Override
	public ProjectUser findByPidAndUid(int pid, int uid) {
		String hql="from ProjectUser pu where pu.user.userId='"+uid+"' and pu.project.id='"+pid+"'";
		@SuppressWarnings("unchecked")
		List<ProjectUser> pus=(List<ProjectUser>)this.getHibernateTemplate().find(hql);
		if(pus.size()>0)
			return pus.get(0);
		
		return null;
	}

	
}
