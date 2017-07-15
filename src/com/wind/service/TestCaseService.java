package com.wind.service;

import com.wind.entity.TestCase;

public interface TestCaseService {
	void saveTestCase(TestCase entity);
	void updateTestCase(TestCase entity);
	void deleteTestCase(TestCase entity);
	TestCase findById(int id);
}
