package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.CaseDAO;
import com.wind.dao.CustomercaseDAO;
import com.wind.entity.Case;
import com.wind.entity.Customercase;
import com.wind.service.CaseService;

public class CaseServiceImpl implements CaseService {

	private CaseDAO caseDAO;
	private CustomercaseDAO customercaseDAO;

	public void setCustomercaseDAO(CustomercaseDAO customercaseDAO) {
		this.customercaseDAO = customercaseDAO;
	}

	public void setCaseDAO(CaseDAO caseDAO) {
		this.caseDAO = caseDAO;
	}

	@Override
	public void save(Case cs) {
		if (caseDAO.findById(cs.getCaseId()) == null) {
			caseDAO.save(cs);
			System.out.println("添加用例成功");
		} else {
			System.out.println("添加用例失败");
		}

	}

	@Override
	public void delete(int id) {
		if (caseDAO.findById(id) != null) {
			caseDAO.delete(id);
		}

	}

	@Override
	public void update(Case cs) {
		if (caseDAO.findById(cs.getCaseId()) != null) {
			caseDAO.update(cs);
		}

	}

	@Override
	public Case findById(int id) {

		return caseDAO.findById(id);
	}

	@Override
	public List<Case> findAll() {

		return caseDAO.findAll();
	}

	@Override
	public List<Case> findByCasestoreId(int id) {

		return caseDAO.findByCasestoreId(id);
	}

	@Override
	public List<Case> findByNum(String propertyName) {
		// TODO Auto-generated method stub
		return caseDAO.findByNum(propertyName);
	}

	@Override
	public void uploadCase(Customercase entity) {
		if (customercaseDAO.findById(entity.getId()) == null) {
			customercaseDAO.save(entity);
		}
	}

	@Override
	public Customercase findCusById(int cid) {

		return customercaseDAO.findById(cid);
	}

	@Override
	public void deleteCusCase(int cid) {
		// TODO Auto-generated method stub
		if (customercaseDAO.findById(cid) != null) {
			customercaseDAO.delete(cid);
		}
	}

}
