package com.wind.service;

import java.util.List;

import com.wind.entity.Exceptionlog;

public interface ExceptionLogService {
	void addLogMsg(String msg);//����log��Ϣ
	void deleteLogMsg(Exceptionlog e);//ɾ������log��Ϣ
	List<Exceptionlog> findAllLogMsg();//�ҳ����е�log
	List<Exceptionlog> findByTime(String data);//����log������ʱ���ѯ
	
}
