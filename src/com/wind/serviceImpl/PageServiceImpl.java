package com.wind.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.wind.dao.PageDAO;
import com.wind.entity.PageBean;
import com.wind.entity.Project;
import com.wind.entity.Role;
import com.wind.entity.User;
import com.wind.entity.UserRole;
import com.wind.from.PageCount;
import com.wind.from.UserRoleForm;
import com.wind.permission.dao.RoleDAO;
import com.wind.permission.dao.UserRoleDAO;
import com.wind.service.PageService;

public class PageServiceImpl implements PageService{
	private PageDAO pageDAO;

	public void setPageDAO(PageDAO pageDAO) {
		this.pageDAO = pageDAO;
	}
	private UserRoleDAO userRoleDAO;
	private RoleDAO roleDAO;
	
	

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	@Override
	public PageBean queryForPage(final String hql,int pageSize, int curretpage) {
		//final String hql="from User";
		int allRow=pageDAO.getAllRowCount(hql);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		final int offset=PageBean.countOffset(pageSize, curretpage);
		final int length=pageSize;
		final int currentPage=PageBean.countCurrentPage(curretpage);
		List list=pageDAO.queryForPage(hql, offset, length);
		List<PageCount> count=new ArrayList<PageCount>();
		for(int i=0;i<totalPage;i++){
			PageCount p=new PageCount();
			p.setCount(i+1);
			count.add(p);
		}
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setCount(count);
		return pageBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean queryForUserPage(String hql, int pageSize, int curretpage) {
		int allRow=pageDAO.getAllRowCount(hql);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		final int offset=PageBean.countOffset(pageSize, curretpage);
		final int length=pageSize;
		final int currentPage=PageBean.countCurrentPage(curretpage);
		List<User> users=pageDAO.queryForPage(hql, offset, length);
		
		List<UserRole> urs=userRoleDAO.findAll();
		List<UserRoleForm> urfs=new ArrayList<UserRoleForm>();
		for(User u:users){
			UserRoleForm urf=new UserRoleForm();
			boolean bflag=true;
			for(UserRole ur:urs){
				if(u.getUserId()==ur.getUserId()||ur.getUserId().equals(u.getUserId())){
					Role r=roleDAO.findById(ur.getRoleId());
					if(r.getRoleName().equals("超级管理员")){
						bflag=false;
					}else{
						urf.setRoleName(r.getRoleName());
					}
					
					break;
				}
			}
			if(bflag){
				urf.setUser(u);
				urfs.add(urf);
			}
		}
		
		List<PageCount> count=new ArrayList<PageCount>();
		for(int i=0;i<totalPage;i++){
			PageCount p=new PageCount();
			p.setCount(i+1);
			count.add(p);
		}
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(urfs);
		pageBean.setCount(count);
		return pageBean;
	}
	
	@Override
	public PageBean queryForProjectPage(final String hql,int pageSize, int curretpage) {
		//final String hql="from User";
		int allRow=pageDAO.getAllRowCount(hql);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		final int offset=PageBean.countOffset(pageSize, curretpage);
		final int length=pageSize;
		final int currentPage=PageBean.countCurrentPage(curretpage);
		List<Project> list=pageDAO.queryForPage(hql, offset, length);
		List<PageCount> count=new ArrayList<PageCount>();
		for(int i=0;i<totalPage;i++){
			PageCount p=new PageCount();
			p.setCount(i+1);
			count.add(p);
		}
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setCount(count);
		return pageBean;
	}
}
