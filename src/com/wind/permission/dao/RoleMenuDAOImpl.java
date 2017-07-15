package com.wind.permission.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wind.entity.Menu;
import com.wind.entity.Module;
import com.wind.entity.Role;
import com.wind.entity.RoleMenu;

public class RoleMenuDAOImpl extends HibernateDaoSupport implements RoleMenuDAO {

	@Override
	public void save(RoleMenu entity) {
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
	public void delete(RoleMenu entity) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		try{
//			session.delete(entity);
//			t.commit();
//		}catch(Exception e){
//			t.rollback();
//		}finally{
//			session.close();
//		}
		this.getHibernateTemplate().delete(entity);

	}

	// 通过角色id查询模块
	@Override
	public List<Integer> findMenuHad(Integer i) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="select rm.menu.Permission from RoleMenu rm where rm.role.roleId=?";
//		Query q = session.createQuery(hql);
//		q.setParameter(0, i);
//		List<Permission> permissions=q.list();
//		t.commit();
		String hql="select rm.menu.id from RoleMenu rm where rm.role.roleId="+i+"";
		List<Integer> permissions=this.getHibernateTemplate().find(hql);
		return permissions;
	}

	@Override
	public List<Menu> findFInRM(Integer rid, Module pf) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="select menu from RoleMenu rm where rm.role.roleId=? and rm.menu.permission.permissionId=?";
//		Query q = session.createQuery(hql);
//		q.setParameter(0, rid);
//		q.setParameter(0, pf.getPermissionId());
//		List<Menu> menus=q.list();
//		t.commit();
		String hql="select menu from RoleMenu rm where rm.role.roleId="+rid+" and rm.menu.module.moduleId='"+pf.getModuleId()+"'";
		List<Menu> menus=this.getHibernateTemplate().find(hql);
		return menus;
	}

	

	@Override
	public List<Integer> findFidByRid(Role r) {
//		SessionFactory sessionFactory=this.getHibernateTemplate().getSessionFactory();
//		Session session=sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		
//		String hql="select menu.id from RoleMenu rm where rm.role.roleId=?";
//		Query q = session.createQuery(hql);
//		q.setParameter(0, r.getRoleId());
//		List<Integer> menuIds=q.list();
//		t.commit();
		String hql="select rm.menu.id from RoleMenu rm where rm.role.roleId="+r.getRoleId()+"";
		List<Integer> menuIds=this.getHibernateTemplate().find(hql);
		return menuIds;
	}

	@Override
	public List<RoleMenu> findByRid(int rid) {
		
		String hql="from RoleMenu rm where rm.role.roleId="+rid+"";
		List<RoleMenu> roleMenuIds=this.getHibernateTemplate().find(hql);
		return roleMenuIds;
	}

	@Override
	public RoleMenu findByRidAndMid(int rid, int mid) {
		String hql="from RoleMenu rm where rm.role.roleId="+rid+" and rm.menu.id='"+mid+"'";
		List<RoleMenu> rms=this.getHibernateTemplate().find(hql);
		if(rms.size()>0){
			return rms.get(0);
		}
		return null;
	}

	@Override
	public List<RoleMenu> findByMid(int mid) {
		String hql="from RoleMenu rm where rm.menu.id="+mid+"";
		List<RoleMenu> roleMenuIds=this.getHibernateTemplate().find(hql);
		return roleMenuIds;
	}

}
