package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.CaseTypeDAO;
import com.wind.dao.HqlDAO;
import com.wind.entity.Casetype;
import com.wind.service.CaseTypeService;

public class CaseTypeServiceImpl implements CaseTypeService {
	private CaseTypeDAO ctDAO;
	private HqlDAO hqlDAO;
	
	public void setHqlDAO(HqlDAO hqlDAO) {
		this.hqlDAO = hqlDAO;
	}

	public void setCtDAO(CaseTypeDAO ctDAO) {
		this.ctDAO = ctDAO;
	}

	@Override
	public Integer save(Casetype cst) {
		if(ctDAO.findById(cst.getId())==null){
			return ctDAO.save(cst);
		}
		return null;

	}

	@Override
	public void delete(int id) {
		if(ctDAO.findById(id)!=null){
			ctDAO.delete(id);
		}

	}

	@Override
	public void update(Casetype cst) {
		if(ctDAO.findById(cst.getId())!=null){
			ctDAO.update(cst);
		}

	}

	@Override
	public Casetype findById(int id) {
		
		return ctDAO.findById(id);
	}

	@Override
	public List<Casetype> findAll() {
		return ctDAO.findAll();
	}

	@Override
	public Casetype findByName(String name) {
		List<Casetype> cts=hqlDAO.findByHql("from Casetype ct where ct.casetypeName='"+name+"'");
		if(cts.size()>0){
			return cts.get(0);
		}
		return null;
	}

}
