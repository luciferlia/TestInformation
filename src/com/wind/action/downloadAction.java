package com.wind.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class downloadAction extends ActionSupport {

	// ��ȡ�����ļ���Ŀ¼
	private String inputPath;
	// �����ļ����ļ���
	private String fileName;
	// ��ȡ�����ļ���������
	private InputStream inputStream;
	// �����ļ�������
	private String conetntType;

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	// ����InputStream������
	public InputStream getInputStream() throws FileNotFoundException {
		String path = ServletActionContext.getServletContext().getRealPath(inputPath);
		return new BufferedInputStream(new FileInputStream(path + "\\" + fileName));

	}
}