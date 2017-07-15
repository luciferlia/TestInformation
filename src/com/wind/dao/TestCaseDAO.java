package com.wind.dao;

import com.wind.entity.TestCase;

public interface TestCaseDAO {
	void saveTestCase(TestCase entity);
	void updateTestCase(TestCase entity);
	void deleteTestCase(TestCase entity);
	TestCase findById(int id);
}
