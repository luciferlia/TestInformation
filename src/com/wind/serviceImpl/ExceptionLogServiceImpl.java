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
	 * 添加异常log信息
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
	 * 删除单个异常log信息
	 */
	@Override
	public void deleteLogMsg(Exceptionlog e) {
		exceptionLogDAO.delete(e);

	}

	/**
	 * 查找数据库中所有产生的异常log信息
	 */
	@Override

public List<Exceptionlog> findAllLogMsg() {
		
		return exceptionLogDAO.findAll();
	}

	
	/**
	 * 根据发生异常的时间获取异常log信息
	 */
	@Override
	public List<Exceptionlog> findByTime(String data) {
		
		return exceptionLogDAO.findByTime(data);
	}

}
