package com.wind.service;

import java.util.List;

import com.wind.entity.Exceptionlog;

public interface ExceptionLogService {
	void addLogMsg(String msg);//保存log信息
	void deleteLogMsg(Exceptionlog e);//删除单个log信息
	List<Exceptionlog> findAllLogMsg();//找出所有的log
	List<Exceptionlog> findByTime(String data);//根据log发生的时间查询
	
}
