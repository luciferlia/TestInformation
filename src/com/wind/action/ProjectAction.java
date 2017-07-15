package com.wind.action;

import java.awt.Window.Type;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.wind.entity.Caseresult;
import com.wind.entity.Function;
import com.wind.entity.PageBean;
import com.wind.entity.Plan;
import com.wind.entity.PlanCasestore;
import com.wind.entity.Plantail;
import com.wind.entity.Policypool;
import com.wind.entity.Project;
import com.wind.entity.ProjectUser;
import com.wind.entity.Role;
import com.wind.entity.User;
import com.wind.from.ProjectForm;
import com.wind.permission.po.PermissionForm;
import com.wind.util.ServiceConfig;

public class ProjectAction extends ServiceConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ��ʾ��Ŀ������Ϣ
	 * 
	 * @return
	 */
	public String showAllProject() {
		try {
			final String hql = "from Project p order by p.customer,p.projectName desc";
			this.pageBean = pageService.queryForPage(hql, 5, page);
			List<ProjectForm> list = new ArrayList<ProjectForm>();
			for (Object line : pageBean.getList()) {
				ProjectForm pf = new ProjectForm();
				pf.setId(((Project) line).getId());
				pf.setProjectName(((Project) line).getProjectName());
				pf.setCustomer(((Project) line).getCustomer());
				pf.setPolicyName(((Project) line).getPolicyName());
				pf.setLevel(((Project) line).getLevel());
				pf.setTestPhase(((Project) line).getTestPhase());
				list.add(pf);
			}
			pageBean.setList(list);
			return "showAll";
		} catch (Exception e) {
			addExceptionLog(e, "��ʾ��Ŀ�쳣");
			return "error";
		}
	}

	private PageBean pageBean;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	private int page = 1;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private List<Policypool> policypools;

	public List<Policypool> getPolicypools() {
		return policypools;
	}

	public void setPolicypools(List<Policypool> policypools) {
		this.policypools = policypools;
	}

	private PermissionForm pf;

	public PermissionForm getPf() {
		return pf;
	}

	public void setPf(PermissionForm pf) {
		this.pf = pf;
	}

	/**
	 * ��ҳ��ʾ��Ŀ������Ϣ
	 * 
	 * @return
	 */
	public String showProject() {
		try {
			String hql = "";
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Role r1 = (Role) session.getAttribute("role");
			List<ProjectForm> list = new ArrayList<ProjectForm>();

			List<Function> function = roleFunctionService.findFidByRid(r1);
			for (Function f : function) {
				if (f.getDescription().equals("�鿴������Ŀ") && f.getTag().equals("showAll")) {
					hql = "from Project p order by p.customer,p.projectName desc";
					break;
				} else if (f.getDescription().equals("�鿴�Լ�����Ŀ") && f.getTag().equals("showMy")) {
					hql = "from Project p where p.id in(select pu.project.id from ProjectUser pu where pu.user.userId='"
							+ u.getUserId() + "') order by p.customer,p.projectName desc";
					break;
				}
			}

			if (!hql.equals("")) {
				this.pageBean = pageService.queryForPage(hql, 10, page);
				hql = "";
				for (Object line : pageBean.getList()) {
					ProjectForm pf = new ProjectForm();
					List<Integer> userIds = projectUserService.findUidByPid(((Project) line).getId());
					for (Integer uid : userIds) {
						Role r = userService.findRoleByUid(uid);
						if (r.getRoleName().contains("���Ծ���") || r.getRoleName().contains("���ž���")) {
							pf.setVpmName(userService.findUserById(uid).getName());
						}
					}
					pf.setId(((Project) line).getId());
					pf.setProjectName(((Project) line).getProjectName());
					pf.setCustomer(((Project) line).getCustomer());
					pf.setPolicyName(((Project) line).getPolicyName());
					pf.setLevel(((Project) line).getLevel());
					pf.setTestPhase(((Project) line).getTestPhase());
					if (((Project) line).getState().equals("0")) {
						pf.setState("�½���");
					} else if (((Project) line).getState().equals("1")) {
						pf.setState("������");
					} else {
						pf.setState("�����");
					}

					list.add(pf);
				}
				setPf(rolePermissionService.getUserPermission("Myproject.jsp"));
				pageBean.setList(list);
				policypools = policypoolService.findAll();
				message = "";
				return "showAll2";
			} else {
				message = "��û�в鿴��Ŀ��Ȩ�ޣ�����ϵ����Ա��ͨ";
				return "showAll2";
			}

		} catch (Exception e) {
			addExceptionLog(e, "��ʾ��Ŀ�쳣");
			return "error";
		}
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Project p;

	public Project getP() {
		return p;
	}

	public void setP(Project p) {
		this.p = p;
	}

	/**
	 * ������Ŀid����ʾ����Ŀ����
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String projectDetail() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			p = projectService.findById(id);
			ProjectForm pf = new ProjectForm();
			List<Integer> userIds = projectUserService.findUidByPid(p.getId());
			for (Integer uid : userIds) {
				Role r = userService.findRoleByUid(uid);
				if (r.getRoleName().contains("���Ծ���")) {
					pf.setVpmName(userService.findUserById(uid).getName());
				}
				if (r.getRoleName().contains("Ӳ������")) {
					pf.setHmaster(userService.findUserById(uid).getName());
				}
				if (r.getRoleName().contains("�������")) {
					pf.setSmaster(userService.findUserById(uid).getName());
				}
				if (r.getRoleName().contains("Ӳ���鳤")) {
					pf.setHtester(userService.findUserById(uid).getName());
				}
				if (r.getRoleName().contains("����鳤")) {
					pf.setStester(userService.findUserById(uid).getName());
				}
			}
			pf.setId(p.getId());
			pf.setProjectName(p.getProjectName());
			pf.setCustomer(p.getCustomer());
			pf.setLevel(p.getLevel());
			pf.setTestPhase(p.getTestPhase());
			pf.setBuildTime(p.getBuildTime());
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("project", pf);
			return "p_detail";
		} catch (Exception e) {
			addExceptionLog(e, "��ʾ��Ŀ�����쳣");
			return "error";
		}
	}

	private String phase;
	private List<Plan> plans;

	public List<Plan> getPlan() {
		return plans;
	}

	public void setPlan(List<Plan> plans) {
		this.plans = plans;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	/**
	 * ��ʾ��Ŀ�ƻ�����
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String plan() {
		try {
			System.out.println(name + "," + phase);
			plans = projectService.findByPhase(name, phase);
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("plans", plans);
			return "plan";
		} catch (Exception e) {
			addExceptionLog(e, "��ʾ��Ŀ�ƻ������쳣");
			return "error";
		}
	}

	public Project project;// ��ȡ��Ŀ���ͱ���

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	/*����*/
	private String content;
	private String type;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String Search()
	{
		try{
			if( content == null || content == "")
			{
				final String hql="from Project p order by p.customer,p.projectName desc";
				this.pageBean=pageService.queryForProjectPage(hql, 15, page);
			}
			else
			{
				if(type.equals("project_Name"))
				{
					final String hql="from Project p where project_Name like '%" +	content + "%'";
					this.pageBean=pageService.queryForProjectPage(hql, 10, page);
				}
				if(type.equals("customer"))
				{
					final String hql="from Project p where customer like '%" +content +"%'";
					this.pageBean=pageService.queryForProjectPage(hql, 10, page);
				}
			}
			return "success";
		}
		catch (Exception e)
		{
		StringWriter sw=new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		exceptionLogService.addLogMsg("��ʾ�����û���Ϣ�쳣"+sw.getBuffer().toString());
		return "error";
		}
	}
	
	/**
	 * ʵ�������Ŀ�ķ���
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String addProject() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Project p = new Project();
			p.setProjectName(project.getProjectName());
			p.setCustomer(project.getCustomer());
			p.setPolicyName(project.getPolicyName());
			p.setLevel(project.getLevel());
			p.setTestPhase("EVT");
			p.setState("0");
			p.setBuildTime(sdf.format(new Date()));
			ProjectUser pu = new ProjectUser();
			pu.setId(0);
			pu.setProject(p);
			pu.setUser(u);
			pu.setState("0");
			projectService.save(p);
			projectUserService.save(pu);
			return "addProject";
		} catch (Exception e) {
			addExceptionLog(e, "�����Ŀ�쳣");
			return "error";
		}
	}

	/**
	 * ʵ���޸���Ŀ��Ϣ�ķ���
	 * 
	 * @return
	 */
	public String updateProject() {
		try {
			Project p = projectService.findById(project.getId());
			System.out.println("&&&&" + project.getId());
			p.setProjectName(project.getProjectName());
			p.setCustomer(project.getCustomer());
			p.setPolicyName(project.getPolicyName());
			p.setLevel(project.getLevel());
			p.setTestPhase(project.getTestPhase());
			projectService.update(p);
			return "updateProject";
		} catch (Exception e) {
			addExceptionLog(e, "�޸���Ŀ�쳣");
			return "error";
		}
	}

	private int id;// ��ȡ��Ŀid����

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * ʵ��ɾ����Ŀ�ķ���
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteProject() {
		try {
			String hql = "from ProjectUser pu where pu.project.projectId='" + id + "'";
			List<ProjectUser> proUser = hqlService.findByHql(hql);
			for (ProjectUser pu : proUser) {
				projectUserService.delete(pu);
			}
			projectService.delete(id);
			return "deleteProject";
		} catch (Exception e) {
			addExceptionLog(e, "ɾ����Ŀ�쳣");
			return "error";
		}
	}

	private String checkedIds;// ��ȡ��ѡid���ͱ���

	public String getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	/**
	 * ����ɾ����Ŀ�ƻ�
	 * 
	 * @return
	 */
	public String deleteSelectedProject() {
		try {
			String checkedId[] = checkedIds.split(",");// ���зָ�浽����
			String temp = "";
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					System.out.println(checkedId[i]);
					temp = checkedId[i]; // �������
					int deleteId = Integer.parseInt(temp);
					// ɾ����ƻ�������ı��ƻ���
					String planhql = "from Plan p where p.project.id='" + deleteId + "'";
					@SuppressWarnings("unchecked")
					List<Plan> plan = hqlService.findByHql(planhql);
					for (Plan p : plan) {
						List<PlanCasestore> pct = planService.findByPlanId(p.getPlanId());
						for (PlanCasestore line : pct) {
							planService.deletePlanCasestore(line.getId());
						}
						List<Plantail> plantail = plantailService.findByPlanId(p.getPlanId());
						for (Plantail line : plantail) {
							plantailService.delete(line.getId());
						}
						List<Caseresult> caseresult = caseresultService.findByPlanId(p.getPlanId());
						for (Caseresult line : caseresult) {
							caseresultService.delete(line.getId());
						}
						planService.delete(p.getPlanId());
					}
					// ɾ����Ŀ��Ա��
					String hql = "from ProjectUser pu where pu.project.id='" + deleteId + "'";
					@SuppressWarnings("unchecked")
					List<ProjectUser> proUser = hqlService.findByHql(hql);
					for (ProjectUser pu : proUser) {
						projectUserService.delete(pu);
					}
					projectService.delete(Integer.parseInt(temp));
				}
			}
			return "deleteSelectedProject";
		} catch (Exception e) {
			addExceptionLog(e, "����ɾ����Ŀ�쳣");
			return "error";
		}
	}

	public void addExceptionLog(Exception e, String str) {
		// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		exceptionLogService.addLogMsg(str + ":" + sw.getBuffer().toString());
	}
}
