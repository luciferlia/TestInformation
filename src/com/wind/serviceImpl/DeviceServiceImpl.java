package com.wind.serviceImpl;

import java.util.List;
import com.wind.dao.DeviceDAO;
import com.wind.entity.Device;
import com.wind.service.DeviceService;

public class DeviceServiceImpl implements DeviceService {
	private DeviceDAO deviceDAO;
	

	public void setDeviceDAO(DeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}
	@Override
	public void save(Device device) {
//		if(deviceDAO.findById(device.getDeviceId())!=null){
			deviceDAO.save(device);
//	}
	}
	@Override
	public void delete(int id) {
		if(deviceDAO.findById(id)!=null){
			deviceDAO.delete(id);
		}
	}

	@Override
	public void update(Device device) {
		if(deviceDAO.findById(device.getDeviceId())!=null){
			deviceDAO.update(device);
		}
	}

	@Override
	public Device findById(int id) {
		
		return deviceDAO.findById(id);
	}

	@Override
	public List<Device> findAll() {
		return deviceDAO.findAll();
	}

}
