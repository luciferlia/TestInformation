package com.wind.serviceImpl;

import java.util.List;

import com.wind.dao.DeviceApplyDAO;
import com.wind.entity.Deviceapply;
import com.wind.service.DeviceApplyService;

public class DeviceApplyServiceImpl implements DeviceApplyService {
	private DeviceApplyDAO deviceApplyDAO;

	public void setDeviceApplyDAO(DeviceApplyDAO deviceApplyDAO) {
		this.deviceApplyDAO = deviceApplyDAO;
	}

	@Override
	public void save(Deviceapply deviceapply) {
		// if(deviceDAO.findById(device.getDeviceId())!=null){
		deviceApplyDAO.save(deviceapply);
		// }
	}

	@Override
	public void delete(int id) {
		if (deviceApplyDAO.findById(id) != null) {
			deviceApplyDAO.delete(id);
		}
	}

	@Override
	public void update(Deviceapply deviceapply) {
		if (deviceApplyDAO.findById(deviceapply.getDeviceId()) != null) {
			deviceApplyDAO.update(deviceapply);
		}
	}

	@Override
	public Deviceapply findById(int id) {

		return deviceApplyDAO.findById(id);
	}

	@Override
	public List<Deviceapply> findAll() {
		return deviceApplyDAO.findAll();
	}

	@Override
	public List<Deviceapply> showDeviceapply(int id) {

		return deviceApplyDAO.findBydeviceId(id);
	}

}
