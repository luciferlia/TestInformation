package com.wind.permission.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.wind.entity.Function;
import com.wind.entity.Menu;
import com.wind.entity.Module;
import com.wind.entity.Role;
import com.wind.entity.RoleFunction;
import com.wind.entity.RoleMenu;
import com.wind.from.RoleFunctionForm;
import com.wind.from.RoleMenuForm;
import com.wind.util.ServiceConfig;

public class RoleAction extends ServiceConfig{
	
	private static final long serialVersionUID = 1L;
	
	private Role role;
	private List<Module> modules;
	
	
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 新增角色
	 * @return
	 */
	public String addRole(){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			role.setBuildTime(sdf.format(new Date()));
			roleService.save(role);
			if(roleService.findById(role.getRoleId())==null){
				msg="新增角色失败!!";
				System.out.println("新增角色失败!!");
			}else{
				msg="新增角色成功！！";
				System.out.println("新增角色成功！！");
			}
			
		}catch(Exception e){
			System.out.println("保存失败");
		}
		
		return "SaveRoleSuc";
		
	}
	
	private List<Role> roles;
	
	
	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	private List<Menu> menus;
	private List<Function> functions;
	

	public List<Menu> getMenus() {
		return menus;
	}


	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}


	public List<Function> getFunctions() {
		return functions;
	}


	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}


	/**
	 * 查询所有的角色,菜单和页面权限
	 * @return
	 */
	public String showAllRole(){
		roles=roleService.findAll();
		menus=menuService.finAll();
		functions=functionService.findAll();
		modules=moduleService.operateModule();
		return "showRole";
	}
	
	/**
	 * 修改角色信息
	 * @return
	 */
	public String updateRole(){
		Role r=roleService.findById(role.getRoleId());
		r.setDescription(role.getDescription());
		r.setRoleName(role.getRoleName());
		roleService.update(r);
		
		return "updateRoleSuc";
	}
	private int id;//角色编号
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String deleteRole(){
		Role r=roleService.findById(id);
		roleService.deleteRole(r);
		
		if(roleService.findById(id)==null){
			msg="删除角色成功！";
			System.out.println("删除角色成功！");
		}else{
			msg="删除角色失败！";
			System.out.println("删除角色失败！");
		}
		
		return "deleteRole";
		
	}
	
	/**
	 * 通过角色Id查找出其所有的菜单和权限
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String rolePowerPre(){
		//System.out.println("id:"+id);
		List<RoleMenu> rms=roleService.findRMByRid(id);
		List<RoleFunction> rfs=roleService.findRFByRid(id);
		List<RoleMenuForm> rmfs=new ArrayList<RoleMenuForm>();
		List<RoleFunctionForm> rffs=new ArrayList<RoleFunctionForm>();
		menus=menuService.finAll();
		functions=functionService.findAll();
		for(Menu m:menus){
			RoleMenuForm rmf=new RoleMenuForm();
			rmf.setId(m.getId());
			rmf.setModule(moduleService.findSingleModule(m.getModule().getModuleId()));
			rmf.setName(m.getName());
			rmf.setUrl(m.getUrl());
			rmf.setCheck(false);
			for(RoleMenu rm:rms){
				if(m.getId()==rm.getMenu().getId()||m.getId().equals(rm.getMenu().getId())){
					rmf.setCheck(true);
					break;
				}
			}
			rmfs.add(rmf);
		}
		
		for(Function f:functions){
			RoleFunctionForm rff=new RoleFunctionForm();
			rff.setFunctionId(f.getFunctionId());
			rff.setDescription(f.getDescription());
			rff.setJsp(f.getJsp());
			rff.setName(f.getName());
			rff.setUrl(f.getUrl());
			rff.setCheck(false);
			for(RoleFunction rf:rfs){
				if(f.getFunctionId()==rf.getFunctionId()||f.getFunctionId().equals(rf.getFunctionId())){
					rff.setCheck(true);
				}
			}
			rffs.add(rff);
		}
		
		Map request = (Map)ActionContext.getContext().get("request");
		request.put("roleMenus", rmfs);
		request.put("roleFunctions", rffs);	
		return "rolePowerPre";
	}
	
	private String checkedIds;
	
	public String getCheckedIds() {
		return checkedIds;
	}
	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}
	private String checkedIds2;
	
	
	
	public String getCheckedIds2() {
		return checkedIds2;
	}

	public void setCheckedIds2(String checkedIds2) {
		this.checkedIds2 = checkedIds2;
	}

	public String rolePower(){
		List<RoleMenu> rms=roleService.findRMByRid(id);
		List<RoleFunction> rfs=roleService.findRFByRid(id);
		//获取菜单id
		 String checkedId[]=checkedIds.split(",");//进行分割存到数组
	        int temp;
	        for(int i =0;i<checkedId.length;i++){
	            if(!checkedId[i].equals("")){
	                temp=Integer.parseInt(checkedId[i]);   //保存更改
	               // System.out.println("序号："+temp);
	                boolean state=false;
	                for(RoleMenu rm:rms){
	                	if(rm.getMenu().getId()==temp||rm.getMenu().getId().equals(temp)){
	                		state=true;
	                		break;
	                	}
	                }
	                
	                if(state==false){
	                	//System.out.println("需要添加的编号："+temp);
	                	//添加
	                	RoleMenu rm=new RoleMenu();
	                	rm.setMenu(menuService.findSingleMenu(temp));
	                	rm.setRole(roleService.findById(id));
	                	roleService.addRoleMenu(rm);
	                }
	            }
	        }
	        
	        for(RoleMenu rm:rms){
	        	boolean state=false;
	        	int temp1=0;
	        	for(int i =0;i<checkedId.length;i++){
	        		 if(!checkedId[i].equals("")){
	        			 temp1=Integer.parseInt(checkedId[i]);
	        			 if(rm.getMenu().getId()==temp1||rm.getMenu().getId().equals(temp1)){
		                		state=true;
		                		break;
		                	}
	        		 }
	        	}
	        	if(state==false){
                	//System.out.println("需要删除的编号："+rm.getMenu().getId());
                	//删除
                	roleService.deleteRoleMenu(id,rm.getMenu().getId());
                	
                }
	        }
			//System.out.println("---------");
	        //获取权限id
	        String checkedId2[]=checkedIds2.split(",");//进行分割存到数组
	        int temp2;
	        for(int i =0;i<checkedId2.length;i++){
	            if(!checkedId2[i].equals("")){
	                temp2=Integer.parseInt(checkedId2[i]);   //保存更改
	               // System.out.println("序号："+temp2);
	                boolean state=false;
	                for(RoleFunction rf:rfs){
	                	if(rf.getFunctionId()==temp2||rf.getFunctionId().equals(temp2)){
	                		state=true;
	                		break;
	                	}
	                }
	                if(state==false){
	                	//System.out.println("需要添加的编号："+temp2);
	                	//添加
	                	RoleFunction rf=new RoleFunction();
	                	rf.setFunctionId(temp2);
	                	rf.setRoleId(id);
	                	roleFunctionService.save(rf);
	                }
	            }
	        }
	        
	        for(RoleFunction rf:rfs){
	        	boolean state=false;
	        	int temp1=0;
	        	for(int i =0;i<checkedId2.length;i++){
	        		 if(!checkedId2[i].equals("")){
	        			 temp1=Integer.parseInt(checkedId2[i]);
	        			 if(rf.getFunctionId()==temp1||rf.getFunctionId().equals(temp1)){
		                		state=true;
		                		break;
		                	}
	        		 }
	        	}
	        	if(state==false){
                	//System.out.println("需要删除的编号："+rf.getFunctionId());
                	//删除
	        		RoleFunction rff=roleFunctionService.findRF(id, rf.getFunctionId());
	        		if(rff!=null){
	        			roleFunctionService.delete(rff);
	        		}
                	
                }
	        }
	        
	        
		return "rolePowerSuc";
		
	}
	
}
