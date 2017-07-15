package com.wind.serviceImpl;

import com.wind.dao.TestCaseDAO;
import com.wind.entity.TestCase;
import com.wind.service.TestCaseService;

public class TestCaseServiceImpl implements TestCaseService {
	private TestCaseDAO testCaseDAO;
	
	public void setTestCaseDAO(TestCaseDAO testCaseDAO) {
		this.testCaseDAO = testCaseDAO;
	}

	@Override
	public void saveTestCase(TestCase entity) {
		testCaseDAO.saveTestCase(entity);

	}

	@Override
	public void updateTestCase(TestCase entity) {
		testCaseDAO.updateTestCase(entity);

	}

	@Override
	public void deleteTestCase(TestCase entity) {
		testCaseDAO.deleteTestCase(entity);

	}

	@Override
	public TestCase findById(int id) {
		
		return testCaseDAO.findById(id);
	}

}
