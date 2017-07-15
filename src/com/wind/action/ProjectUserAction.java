package com.wind.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wind.entity.Project;
import com.wind.entity.ProjectUser;
import com.wind.entity.Role;
import com.wind.entity.User;
import com.wind.from.ProjectUserForm;
import com.wind.from.UserRoleForm;
import com.wind.util.ServiceConfig;

public class ProjectUserAction extends ServiceConfig{
	
	private static final long serialVersionUID = 1L;

	private List<ProjectUser> projectUsers;
	private Project project;
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<ProjectUser> getProjectUsers() {
		return projectUsers;
	}

	public void setProjectUsers(List<ProjectUser> projectUsers) {
		this.projectUsers = projectUsers;
	}
	
	private int id;//��Ŀid

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private List<User> users;
	private List<ProjectUserForm> pufs;
	private List<UserRoleForm> urfs;
	
	public List<UserRoleForm> getUrfs() {
		return urfs;
	}
	public void setUrfs(List<UserRoleForm> urfs) {
		this.urfs = urfs;
	}
	public List<ProjectUserForm> getPufs() {
		return pufs;
	}

	public void setPufs(List<ProjectUserForm> pufs) {
		this.pufs = pufs;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	/**
	 * �����Ŀ��Ա����ʾ����������鳤
	 * ��������⣬����ʾͬ���Ա
	 * @return
	 */
	public String showUser(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User me=(User) session.getAttribute("user");
			Role role=(Role)session.getAttribute("role");
			System.out.println("��Ŀid��"+id);
			List<Integer> userIds=projectUserService.findUidByPid(id);
			Project p=projectService.findById(id);
			setProject(p);
			List<ProjectUserForm> us=new ArrayList<ProjectUserForm>();
			for(int uid:userIds){
				ProjectUserForm puf=new ProjectUserForm();
				User u=userService.findUserById(uid);
				Role r=userService.findRoleByUid(uid);
				puf.setUser(u);
				if(r.getRoleName()==null){
					puf.setRole("��");
				}else{
					puf.setRole(r.getRoleName());
				}
				
				us.add(puf);
			}
			setPufs(us);
			List<User> uses=userService.findAll();//�ҵ����е��û�
			int i=0;
			List<UserRoleForm> uus=new ArrayList<UserRoleForm>();
			for(User u:uses){
				UserRoleForm urf=new UserRoleForm();
				if(role.getRoleName().contains("����")){
					if(u.getGroupName().equals(me.getGroupName())){
						
						for(int uid:userIds){
							if(u.getUserId()==uid){
								i=1;
								break;
							}
						}
						if(i==0){
							Role r=userService.findRoleByUid(u.getUserId());
							if(r.getRoleName()!=null&&!r.getRoleName().contains("���Ծ���")&&!r.getRoleName().contains("����")&&!r.getRoleName().contains("�鳤")){
								urf.setUser(u);
								urf.setRoleName(r.getRoleName());
								uus.add(urf);
							}
							
						}else{
							i=0;
						}
					}
				}else{
					Role r=userService.findRoleByUid(u.getUserId());
					if(r.getRoleName()!=null&&(r.getRoleName().contains("����")||r.getRoleName().contains("�鳤"))){
						for(int uid:userIds){
							if(u.getUserId()==uid){
								i=1;
								break;
							}
						}
						if(i==0){
							urf.setUser(u);
							urf.setRoleName(r.getRoleName());
							uus.add(urf);
						}else{
							i=0;
						}
					}
				}
				
			}
			setUrfs(uus);
			return "success";
		} catch (Exception e) {
			addExceptionLog(e, "��ʾ��Ŀ��Ա�쳣");
			return "error";
		}
	}
	private String checkedIds;
	
	public String getCheckedIds() {
		return checkedIds;
	}
	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}
	
	/**
	 * �����Ŀ���Ա
	 * @return
	 */
	public String addUser(){
		 try {
			String checkedId[]=checkedIds.split(",");//���зָ�浽����
			    String temp="";
			    //System.out.println("������ID:"+typeId);
			    for(int i =0;i<checkedId.length;i++){
			        if(!checkedId[i].equals("")){
			            //System.out.println(checkedId[i]);
			            temp=checkedId[i];   //�������
			            ProjectUser pu=new ProjectUser();
			            pu.setId(0);
			            pu.setProject(projectService.findById(id));
			            pu.setState("0");
			            pu.setUser(userService.findUserById(Integer.parseInt(temp)));
			           // System.out.println("��ţ�"+temp);
			            projectUserService.save(pu);
			        }
			    }
			return "addUserSuc";
		} catch (NumberFormatException e) {
			addExceptionLog(e, "�����Ŀ��Ա�쳣");
			return "error";
		}
	}
	
	private int uid;
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * ɾ����Ŀ�еĳ�Ա
	 * @return
	 */
	public String deleteUser(){
		
		try {
			Role r=userService.findRoleByUid(uid);
			if(!r.getRoleName().equals("���Ծ���")){
				ProjectUser pu=projectUserService.findByPidAndUid(id, uid);
				
				if(pu!=null){
					projectUserService.delete(pu);
				}
			}
			
			return "deleteUserSuc";
		} catch (Exception e) {
			addExceptionLog(e, "ɾ����Ŀ��Ա�쳣");
			return "error";
		}
	}
	
	public void addExceptionLog(Exception e,String str){
		//�����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
		 StringWriter sw = new StringWriter();
		 e.printStackTrace(new PrintWriter(sw));
		 exceptionLogService.addLogMsg(str+":"+sw.getBuffer().toString());
	}
}
