package com.wind.permission.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.entity.Function;
import com.wind.entity.Role;
import com.wind.entity.RoleFunction;
import com.wind.entity.RoleMenu;

public class RoleFunctionDAOImpl extends HibernateDaoSupport implements RoleFunctionDAO {

	@Override
	public void save(RoleFunction entity) {
		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.save(entity);
			t.commit();
		}catch(Exception e){
			t.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void delete(RoleFunction entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(RoleFunction entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public List<Integer> findFunctionByRid(Integer i) {
		String hql="select rm.functionId from RoleFunction rm where rm.roleId="+i+"";
		@SuppressWarnings("unchecked")
		List<Integer> functionIds=this.getHibernateTemplate().find(hql);
		return functionIds;
	}

	@Override
	public List<Function> findFidByRid(Role r) {
		String hql="from Function f where f.functionId in(select rm.functionId from RoleFunction rm where rm.roleId='"+r.getRoleId()+"')";
		@SuppressWarnings("unchecked")
		List<Function> functions=this.getHibernateTemplate().find(hql);
		return functions;
	}

	@Override
	public List<RoleFunction> findByRid(int rid) {
		String hql="from RoleFunction rm where rm.roleId="+rid+"";
		@SuppressWarnings("unchecked")
		List<RoleFunction> functions=this.getHibernateTemplate().find(hql);
		return functions;
	}

	@Override
	public RoleFunction findByRidAndFid(int rid, int fid) {
		String hql="from RoleFunction rf where rf.roleId="+rid+" and rf.functionId='"+fid+"'";
		List<RoleFunction> rfs=this.getHibernateTemplate().find(hql);
		if(rfs.size()>0){
			return rfs.get(0);
		}
		return null;
	}

	@Override
	public List<RoleFunction> findByFid(int fid) {
		String hql="from RoleFunction rm where rm.functionId="+fid+"";
		@SuppressWarnings("unchecked")
		List<RoleFunction> functions=this.getHibernateTemplate().find(hql);
		return functions;
	}

}
