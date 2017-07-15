package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.PolicyDAO;
import com.wind.entity.Policy;
import com.wind.service.PolicyService;

public class PolicyServiceImpl implements PolicyService {
	private PolicyDAO policyDAO;
	
	public void setPolicyDAO(PolicyDAO policyDAO) {
		this.policyDAO = policyDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Policy> findAllPolicy(String hql) {
		
		return policyDAO.findByProperty(hql);
	}

	@Override
	public void addPolicy(Policy entiey) {
		if(policyDAO.findById(entiey.getId())==null){
			policyDAO.save(entiey);
		}
		
		
	}

	@Override
	public void updatePolicy(Policy entity) {
		if(policyDAO.findById(entity.getId())!=null){
			policyDAO.update(entity);
		}
		
		
	}

	@Override
	public Policy findById(int id) {
		
		return policyDAO.findById(id);
	}

	@Override
	public void deletePolicy(int id) {
		if(policyDAO.findById(id)!=null){
			policyDAO.delete(id);
		}
	}

}
