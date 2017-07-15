package com.wind.dao;

import java.util.List;

public interface PageDAO {

	 List queryForPage(final String hql,final int offset,final int length);
	 int getAllRowCount(String hql);
}
