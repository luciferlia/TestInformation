package com.wind.permission.action;

import com.wind.entity.Menu;
import com.wind.util.ServiceConfig;

public class MenuAction extends ServiceConfig{
	
	
	private static final long serialVersionUID = 1L;

	
	
	private Menu menu;
	private int moduleId;
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	//�����˵�
	public String addMenu(){
		menuService.newMenu(menu, moduleId);
		if(menuService.findSingleMenu(menu.getId())==null){
			System.out.println("�����˵�ʧ�ܣ�");
		}else{
			System.out.println("�����˵��ɹ���");
		}
		
		return "success";
	}
	
	
	//�޸Ĳ˵�
	public String editMenu(){
		Menu m=menuService.findSingleMenu(menu.getId());
		m.setName(menu.getName());
		menuService.modifyMenu(m);
		return "success";
	}
	
	private int id;//�˵����
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String deleteMenu(){
		Menu m=new Menu();
		m.setId(id);
		setMenu(m);
		menuService.removeMenu(menu);
		return "success";
	}

}
