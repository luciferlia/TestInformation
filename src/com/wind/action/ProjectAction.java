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
	 * 显示项目基本信息
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
			addExceptionLog(e, "显示项目异常");
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
	 * 分页显示项目基本信息
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
				if (f.getDescription().equals("查看所有项目") && f.getTag().equals("showAll")) {
					hql = "from Project p order by p.customer,p.projectName desc";
					break;
				} else if (f.getDescription().equals("查看自己的项目") && f.getTag().equals("showMy")) {
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
						if (r.getRoleName().contains("测试经理") || r.getRoleName().contains("部门经理")) {
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
						pf.setState("新建立");
					} else if (((Project) line).getState().equals("1")) {
						pf.setState("进行中");
					} else {
						pf.setState("已完成");
					}

					list.add(pf);
				}
				setPf(rolePermissionService.getUserPermission("Myproject.jsp"));
				pageBean.setList(list);
				policypools = policypoolService.findAll();
				message = "";
				return "showAll2";
			} else {
				message = "您没有查看项目的权限，请联系管理员开通";
				return "showAll2";
			}

		} catch (Exception e) {
			addExceptionLog(e, "显示项目异常");
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
	 * 根据项目id，显示该项目详情
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
				if (r.getRoleName().contains("测试经理")) {
					pf.setVpmName(userService.findUserById(uid).getName());
				}
				if (r.getRoleName().contains("硬件主测")) {
					pf.setHmaster(userService.findUserById(uid).getName());
				}
				if (r.getRoleName().contains("软件主测")) {
					pf.setSmaster(userService.findUserById(uid).getName());
				}
				if (r.getRoleName().contains("硬测组长")) {
					pf.setHtester(userService.findUserById(uid).getName());
				}
				if (r.getRoleName().contains("软测组长")) {
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
			addExceptionLog(e, "显示项目详情异常");
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
	 * 显示项目计划详情
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
			addExceptionLog(e, "显示项目计划详情异常");
			return "error";
		}
	}

	public Project project;// 获取项目类型变量

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	/*搜索*/
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
		exceptionLogService.addLogMsg("显示所有用户信息异常"+sw.getBuffer().toString());
		return "error";
		}
	}
	
	/**
	 * 实现添加项目的方法
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
			addExceptionLog(e, "添加项目异常");
			return "error";
		}
	}

	/**
	 * 实现修改项目信息的方法
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
			addExceptionLog(e, "修改项目异常");
			return "error";
		}
	}

	private int id;// 获取项目id变量

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 实现删除项目的方法
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
			addExceptionLog(e, "删除项目异常");
			return "error";
		}
	}

	private String checkedIds;// 获取复选id类型变量

	public String getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	/**
	 * 批量删除项目计划
	 * 
	 * @return
	 */
	public String deleteSelectedProject() {
		try {
			String checkedId[] = checkedIds.split(",");// 进行分割存到数组
			String temp = "";
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					System.out.println(checkedId[i]);
					temp = checkedId[i]; // 保存更改
					int deleteId = Integer.parseInt(temp);
					// 删除与计划相关联的表及计划表
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
					// 删除项目人员表
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
			addExceptionLog(e, "批量删除项目异常");
			return "error";
		}
	}

	public void addExceptionLog(Exception e, String str) {
		// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		exceptionLogService.addLogMsg(str + ":" + sw.getBuffer().toString());
	}
}
