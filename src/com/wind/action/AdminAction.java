package com.wind.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wind.entity.Admin;
import com.wind.entity.AdminMenu;
import com.wind.entity.Module;
import com.wind.entity.User;
import com.wind.from.MenuForm;
import com.wind.permission.service.MenuService;
import com.wind.permission.service.ModuleService;
import com.wind.service.AdminService;
import com.wind.service.ExceptionLogService;
import com.wind.service.HqlService;
import com.wind.service.UserService;
import com.wind.util.ServiceConfig;

public class AdminAction extends ServiceConfig{
	
	private static final long serialVersionUID = 1L;
	private String username;//�û���
	private String password;//����
	
	private String message;//��Ϣ
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String Login(){
		try{
			Admin admin=adminService.adminLogin(username, password);
			
			if(admin!=null){
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				List<MenuForm> mfs=new ArrayList<MenuForm>();
				List<Module> ms=moduleService.findAll();
				List<AdminMenu> ams=menuService.findAllAdminMenu();
				System.out.println(ms.size());
				for(Module m:ms){
					List<AdminMenu> me=new ArrayList<AdminMenu>();
					MenuForm mf=new MenuForm();
					System.out.println(m.getModuleName());
					for(AdminMenu line:ams){
						System.out.println(line.getName());
						if(m.getModuleId()==line.getModule().getModuleId()||m.getModuleId().equals(line.getModule().getModuleId())){
							
							me.add(line);
						}
					}
					mf.setAms(me);
					mf.setModule(m.getModuleName());
					mf.setDescription(m.getDescription());
					mf.setUrl(m.getUrl());
					mfs.add(mf);
				}
				session.setAttribute("menu", mfs);
				
				
				message="";
				return "success";
			}else{
				message="�û������������";
				 return "input";
			}
		}catch(Exception e){
			//�����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			 StringWriter sw = new StringWriter();
			 e.printStackTrace(new PrintWriter(sw));
			 exceptionLogService.addLogMsg("����Ա��¼�쳣��"+sw.getBuffer().toString());
			return "error";
		}
		
		
	}
	
	/**
	 * �˳���¼
	 * @return
	 * @throws Exception
	 */
	public String loginOut() throws Exception {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.removeAttribute("admin");
			session.removeAttribute("menu");
			message="";
			return "input";
		}catch(Exception e){
			//�����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			 StringWriter sw = new StringWriter();
			 e.printStackTrace(new PrintWriter(sw));
			 exceptionLogService.addLogMsg("�˳���¼�쳣��"+sw.getBuffer().toString());
			return "error";
		}
		
	}
	
	
	/**
	 * ��֤�û����Ƿ����
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String validateName() throws IOException{
		try{
			HttpServletResponse out=ServletActionContext.getResponse();
			String result="";
			List<User> users=hqlService.findByHql("from User u where u.username='"+username+"'");
			System.out.println("d:"+users.size());
			if(users.size()>0){
				result="fail";
			}else{
				result="success";
			}
			out.getWriter().write(result);
			return null;
		}catch(Exception e){
			//�����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			 StringWriter sw = new StringWriter();
			 e.printStackTrace(new PrintWriter(sw));
			 exceptionLogService.addLogMsg("��֤�û����Ƿ�����쳣��"+sw.getBuffer().toString());
			return "error";
		}
		
	}
	private String num;
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	/**
	 * ��֤�û������Ƿ����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String validateNum(){
		try{
			HttpServletResponse out=ServletActionContext.getResponse();
			
			String result="";
			List<User> users=hqlService.findByHql("from User u where u.num='"+num+"'");
			if(users.size()>0){
				result="fail";
			}else{
				result="success";
			}
			out.getWriter().write(result);
			return null;
		}catch(Exception e){
			//�����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			 StringWriter sw = new StringWriter();
			 e.printStackTrace(new PrintWriter(sw));
			 exceptionLogService.addLogMsg("��֤�����Ƿ�����쳣��"+sw.getBuffer().toString());
			return "error";
		}
		
	}
}
