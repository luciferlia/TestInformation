package com.wind.dao;

import java.util.List;

/**
 * 编写通用数据库查询DAO
 * @author huangmingliang
 *
 */
public interface HqlDAO {
	List findByHql(String hql);
}
