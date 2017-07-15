package com.wind.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wind.dao.ExceptionLogDAO;
import com.wind.entity.Exceptionlog;
import com.wind.service.ExceptionLogService;

public class ExceptionLogServiceImpl implements ExceptionLogService {
	private ExceptionLogDAO exceptionLogDAO;
	public void setExceptionLogDAO(ExceptionLogDAO exceptionLogDAO) {
		this.exceptionLogDAO = exceptionLogDAO;
	}

	/**
	 * ����쳣log��Ϣ
	 */
	@Override
	public void addLogMsg(String msg) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		Exceptionlog e=new Exceptionlog();
		e.setId(0);
		e.setLogName(msg);
		e.setStartTime(sdf.format(new Date()));
		exceptionLogDAO.save(e);
	}

	/**
	 * ɾ�������쳣log��Ϣ
	 */
	@Override
	public void deleteLogMsg(Exceptionlog e) {
		exceptionLogDAO.delete(e);

	}

	/**
	 * �������ݿ������в������쳣log��Ϣ
	 */
	@Override

public List<Exceptionlog> findAllLogMsg() {
		
		return exceptionLogDAO.findAll();
	}

	
	/**
	 * ���ݷ����쳣��ʱ���ȡ�쳣log��Ϣ
	 */
	@Override
	public List<Exceptionlog> findByTime(String data) {
		
		return exceptionLogDAO.findByTime(data);
	}

}
