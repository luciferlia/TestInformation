package com.wind.entity;

import java.util.Date;

/**
 * Casetype entity. @author MyEclipse Persistence Tools
 */
public class Casetype{

	private int id;//����������
	private String casetypeName;//������������
	private int createId;//������id
	private int updateId;//�޸���id
	private String createTime;//����ʱ��
	private String updateTime;//�޸�ʱ��
	
	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCasetypeName() {
		return this.casetypeName;
	}

	public void setCasetypeName(String casetypeName) {
		this.casetypeName = casetypeName;
	}

	public int getCreateId() {
		return this.createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	

	public int getUpdateId() {
		return updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	

}
