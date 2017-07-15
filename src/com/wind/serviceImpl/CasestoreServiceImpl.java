package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.CaseStoreDAO;
import com.wind.dao.HqlDAO;
import com.wind.entity.Casestore;
import com.wind.service.CasestoreService;

public class CasestoreServiceImpl implements CasestoreService{
	private CaseStoreDAO casestoreDAO;
	private HqlDAO hqlDAO;
	

	public HqlDAO getHqlDAO() {
		return hqlDAO;
	}

	public void setHqlDAO(HqlDAO hqlDAO) {
		this.hqlDAO = hqlDAO;
	}

	public void setCasestoreDAO(CaseStoreDAO casestoreDAO) {
		this.casestoreDAO = casestoreDAO;
	}

	@Override
	public void save(Casestore cs) {
		if(casestoreDAO.findById(cs.getCasestoreId())==null){
			casestoreDAO.save(cs);
		}
		
	}

	@Override
	public void delete(int id) {
		if(casestoreDAO.findById(id)!=null){
			casestoreDAO.delete(id);
		}
		
	}

	@Override
	public void update(Casestore cs) {
		if(casestoreDAO.findById(cs.getCasestoreId())!=null){
			casestoreDAO.update(cs);
		}
		
	}

	@Override
	public Casestore findById(int id) {
		
		return casestoreDAO.findById(id);
	}

	@Override
	public List<Casestore> findAll() {
		
		return casestoreDAO.findAll();
	}

	@Override
	public List<Casestore> findByCaseTypeId(int id) {
		
		return casestoreDAO.findByCaseTypeId(id);
	}

	@Override
	public Casestore findByName(String name) {
		List<Casestore> ct=hqlDAO.findByHql("from Casestore ct where ct.testModule='"+name+"'");
		if(ct.size()>0){
			return ct.get(0);
		}
		return null;
	}
}
