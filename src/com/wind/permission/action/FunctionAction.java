package com.wind.permission.action;

import com.wind.entity.Function;
import com.wind.permission.service.FunctionService;
import com.wind.util.ServiceConfig;

public class FunctionAction extends ServiceConfig{

	private static final long serialVersionUID = 1L;
	
	
	private Function function;
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
	
	//���Ȩ��
	public String addFunction(){
		functionService.save(function);
		if(functionService.findById(function.getFunctionId())==null){
			msg="����ҳ��Ȩ��ʧ�ܣ�";
			System.out.println("����ҳ��Ȩ��ʧ�ܣ�");
		}else{
			msg="����ҳ��Ȩ�޳ɹ���";
			System.out.println("����ҳ��Ȩ�޳ɹ���");
		}
		return "success";
	}
	
	private int id;//Ȩ�ޱ��
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	//�޸�Ȩ��
	public String updateFunction(){
		
		if(id!=0){
			Function f=functionService.findById(id);
			f.setDescription(function.getDescription());
			f.setName(function.getName());
			functionService.update(f);
		}
		return "success";
	}
	
	//ɾ��Ȩ��
	public String deleteFunction(){
		if(id!=0){
			Function f=new Function();
			f.setFunctionId(id);
			setFunction(f);
			functionService.delete(f);
		}
		return "success";
	}
}
