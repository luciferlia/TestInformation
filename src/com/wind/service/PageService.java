package com.wind.service;

import com.wind.entity.PageBean;

public interface PageService {
	PageBean queryForPage(final String hql,int pageSize,int curretpage);
	PageBean queryForUserPage(final String hql,int pageSize,int curretpage);
	PageBean queryForProjectPage(final String hql,int pageSize,int curretpage);
}
