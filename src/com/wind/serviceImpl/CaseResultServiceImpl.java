package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.CaseResultDAO;
import com.wind.entity.Caseresult;
import com.wind.service.CaseResultService;

public class CaseResultServiceImpl implements CaseResultService {

	private CaseResultDAO caseresultDAO;

	public void setCaseresultDAO(CaseResultDAO caseresultDAO) {
		this.caseresultDAO = caseresultDAO;
	}

	public java.lang.Integer save(Caseresult caseresult) {
		// if(caseresultDAO.findById(caseresult.getId())==null){
		caseresultDAO.save(caseresult);
		// }
		return null;
	}

	@Override
	public void delete(int id) {
		if (caseresultDAO.findById(id) != null) {
			caseresultDAO.delete(caseresultDAO.findById(id));
		}

	}

	@Override
	public void update(Caseresult caseresult) {
		if (caseresultDAO.findById(caseresult.getId()) != null) {
			caseresultDAO.update(caseresult);
		}

	}

	@Override
	public Caseresult findById(int id) {

		return caseresultDAO.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Caseresult> findAll() {

		return caseresultDAO.findByProperty("from Caseresult");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Caseresult> findByPlanId(int id) {
		// TODO Auto-generated method stub
		return caseresultDAO.findByProperty("from Caseresult c where c.plan.planId='" + id + "'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Caseresult findByPidAndCid(int planId, int caseId) {
		List<Caseresult> caseresults=caseresultDAO.findByProperty("from Caseresult c where c.plan.planId='"+planId+"' and c.cases.caseId='"+caseId+"'");
		if(caseresults.size()>0){
			return caseresults.get(0);
		}
		return null;
	}

	/**
	 * 根据计划编号和用户编号查找所有的用例结果集
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Caseresult> findByPidAndUid(int planId, int userId) {
		List<Caseresult> caseresults=caseresultDAO.findByProperty("from Caseresult cr where cr.plan.planId='"+planId+"' and cr.user.userId='"+userId+"'");
		return caseresults;
	}

}
