package com.wind.dao;

import java.util.List;

/**
 * ��дͨ�����ݿ��ѯDAO
 * @author huangmingliang
 *
 */
public interface HqlDAO {
	List findByHql(String hql);
}
