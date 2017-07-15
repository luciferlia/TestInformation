package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.PolicyDAO;
import com.wind.dao.PolicypoolDAO;
import com.wind.entity.Policy;
import com.wind.entity.Policypool;
import com.wind.service.PolicypoolService;

public class PolicypoolServiceImpl implements PolicypoolService {

	private PolicypoolDAO policypoolDAO;
	private PolicyDAO policyDAO;
	
	public PolicyDAO getPolicyDAO() {
		return policyDAO;
	}

	public void setPolicyDAO(PolicyDAO policyDAO) {
		this.policyDAO = policyDAO;
	}

	public void setPolicypoolDAO(PolicypoolDAO policypoolDAO) {
		this.policypoolDAO = policypoolDAO;
	}

	@Override
	public Integer save(Policypool p) {
		if(policypoolDAO.findById(p.getId())==null){
			policypoolDAO.save(p);}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(int id) {
		if(policypoolDAO.findById(id)!=null){
			List<Policy> ps=policyDAO.findByProperty("from Policy p where p.policypool.id='"+id+"'");
			for(Policy p:ps){
				policyDAO.delete(p.getId());
			}
			policypoolDAO.delete(id);
		}
	}

	@Override
	public void update(Policypool p) {
		if(policypoolDAO.findById(p.getId())!=null){
			policypoolDAO.update(p);
		}
		

	}

	@Override
	public List<Policypool> findAll() {
		return policypoolDAO.findAll();
	}

	@Override
	public Policypool findById(int id) {
		return policypoolDAO.findById(id);
	}

}
