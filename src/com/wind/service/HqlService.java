package com.wind.service;

import java.util.List;

/**
 * 自定义查询语句
 * @author huangmingliang
 *
 */
public interface HqlService {
	List findByHql(String hql);
}
