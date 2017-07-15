package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.PlantailDAO;
import com.wind.entity.Plantail;
import com.wind.service.PlantailService;

public class PlantailServiceImpl implements PlantailService {

	private PlantailDAO plantailDAO;

	public void setPlantailDAO(PlantailDAO plantailDAO) {
		this.plantailDAO = plantailDAO;
	}

	@Override
	public Integer save(Plantail plantail) {
		// if(planDAO.findById(plan.getPlanId())==null){
		plantailDAO.save(plantail);
		// }
		return null;

	}

	@Override
	public void delete(int id) {
		if (plantailDAO.findById(id) != null) {
			plantailDAO.delete(plantailDAO.findById(id));
		}

	}

	@Override
	public void update(Plantail plantail) {
		if (plantailDAO.findById(plantail.getId()) != null) {
			plantailDAO.update(plantail);
		}

	}

	@Override
	public Plantail findById(int id) {

		return plantailDAO.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Plantail> findAll() {

		return plantailDAO.findByProperty("from Plantail");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Plantail> findByPlanId(int id) {
		// TODO Auto-generated method stub
		return plantailDAO.findByProperty("from Plantail pt where pt.plan.planId ='" + id + "'");
	}

	/**
	 * 根据计划编号和用户编号查找该计划跟踪表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Plantail findByPidAndUid(int planId, int userId) {
		List<Plantail> plantails = plantailDAO.findByProperty(
				"from Plantail p where p.plan.planId='" + planId + "' and p.user.userId='" + userId + "'");
		if(plantails.size()>0){
			return plantails.get(0);
		}
		return null;
	}

}
