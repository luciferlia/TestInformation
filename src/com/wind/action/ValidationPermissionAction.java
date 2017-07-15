package com.wind.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.wind.entity.Plan;
import com.wind.entity.Role;
import com.wind.util.ServiceConfig;

public class ValidationPermissionAction extends ServiceConfig{
	
	private static final long serialVersionUID = 1L;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@SuppressWarnings("unchecked")
	public String validation() throws IOException{
		HttpServletResponse out=ServletActionContext.getResponse();
		System.out.println("项目编号："+id);
		final String hql = "from Plan p where p.project.id='" + id + "' order by p.version,p.startTime asc";
		List<Plan> plans=hqlService.findByHql(hql);
		System.out.println(plans.size());
		if(plans.size()>0){
			
			result="success";
		}else{
			if(rolePermissionService.getRolePermission("添加项目计划", "Myproject.jsp")==null){
				result="fail";
			}else{
				//System.out.println("aaaaaaaaaaaaa");
				result= "success";
			}
			
			
		}
		out.getWriter().write(result);
		return null;
	}
	
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private int uid;
	
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String checkRole(){
		HttpServletResponse response=ServletActionContext.getResponse();
		try{
			Role role=userService.findRoleByUid(uid);
			if(role.getRoleName().contains("测试经理")){
				result="fail";
			}else{
				result="success";
			}
			
			response.getWriter().write(result);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
}
