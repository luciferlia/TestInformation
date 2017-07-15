package com.wind.action;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.wind.entity.Case;
import com.wind.entity.Caseresult;
import com.wind.entity.Casestore;
import com.wind.entity.Casetype;
import com.wind.entity.Function;
import com.wind.entity.PageBean;
import com.wind.entity.Plan;
import com.wind.entity.PlanCasestore;
import com.wind.entity.Plantail;
import com.wind.entity.Project;
import com.wind.entity.ProjectUser;
import com.wind.entity.Role;
import com.wind.entity.TestCase;
import com.wind.entity.User;
import com.wind.from.PlantailForm;
import com.wind.from.ProjectForm;
import com.wind.from.ShowResultForm;
import com.wind.permission.po.PermissionForm;
import com.wind.util.ReadExcel;
import com.wind.util.ServiceConfig;
import com.wind.util.WriteExcel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 我的任务业务逻辑实现界面
 * 
 */
public class TaskAction extends ServiceConfig {

	private static final long serialVersionUID = 1L;
	private Plantail plantail;
	private int id;
	private String fileFileName;
	private File file;
	private int line;
	private int rowNum;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Plantail getPlantail() {
		return plantail;
	}

	public void setPlantail(Plantail plantail) {
		this.plantail = plantail;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private PageBean pageBean;// 获取翻页类型变量

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	private int page = 1;// 当前页

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private PermissionForm pf;

	public PermissionForm getPf() {
		return pf;
	}

	public void setPf(PermissionForm pf) {
		this.pf = pf;
	}

	private int tag;// 标识

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	/**
	 * 分页显示项目基本信息
	 * 
	 * @return
	 */
	public String showMyProject() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Role r = (Role) session.getAttribute("role");
			List<ProjectForm> list = new ArrayList<ProjectForm>();

			List<Function> function = roleFunctionService.findFidByRid(r);
			String hql = "";
			for (Function f : function) {
				if (f.getDescription().equals("查看所有项目") && f.getTag().equals("showAll")) {
					hql = "from Project p order by p.customer,p.projectName desc";
					break;
				} else if (f.getDescription().equals("查看自己的项目") && f.getTag().equals("showMy")) {
					/*
					 * hql =
					 * "from Project p where p.id in(select pu.project.id from ProjectUser pu where pu.user.userId='"
					 * + u.getUserId() +
					 * "') order by p.customer,p.projectName desc";
					 */
					hql = "from Project p where p.id in(select pt.plan.project.id from Plantail pt where pt.user.userId='"
							+ u.getUserId() + "') order by p.customer,p.projectName desc";

					break;
				}
			}
			if (!hql.equals("")) {
				this.pageBean = pageService.queryForPage(hql, 10, page);
				for (Object line : pageBean.getList()) {
					ProjectForm pf = new ProjectForm();
					pf.setId(((Project) line).getId());
					pf.setProjectName(((Project) line).getProjectName());
					pf.setCustomer(((Project) line).getCustomer());
					list.add(pf);
				}
				pageBean.setList(list);
				message = "";
			} else {
				message = "你没有操作项目的权限，请联系管理员开通";
			}
			return "showMyProject";

		} catch (Exception e) {
			addExceptionLog(e, "显示项目异常");
			return "error";
		}
	}

	/**
	 * 根据项目编号查看项目计划信息
	 * 
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public String showMasterTask() throws ParseException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Role r = (Role) session.getAttribute("role");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date currentTime = sdf.parse(sdf.format(new Date()));
			System.out.println("系统当前时间为：" + currentTime);// 获取当前系统时间

			final String hql = "from Plantail pt where pt.plan.project.id='" + id + "' and pt.user.userId ='"
					+ u.getUserId() + "'";
			this.pageBean = pageService.queryForPage(hql, 20, page);
			List<PlantailForm> ptfs = new ArrayList<PlantailForm>();
			for (Object line : pageBean.getList()) {
				PlantailForm ptf = new PlantailForm();
				Plantail plantail = ((Plantail) line);
				Plan plan = planService.findById(plantail.getPlan().getPlanId());
				Project project = projectService.findById(plan.getProject().getId());
				Casetype casetype = casetypeService.findById(plan.getCasetype().getId());
				plan.setProject(project);
				plan.setCasetype(casetype);
				plantail.setPlan(plan);
				plantail.setUser(u);
				if (plantail.getFinishDegree() == null || plantail.getFinishDegree().equals("")) {
					plantail.setFinishDegree("0%");
				}
				List<PlanCasestore> pcs = getCasestore(plan);
				for (PlanCasestore pc : pcs) {

					if (pc.getTester().equals(u.getUserId() + "")) {
						List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.user.userId='"
								+ u.getUserId() + "' and tc.casestoreId='" + pc.getCasestore().getCasestoreId()
								+ "' and tc.planId='" + plan.getPlanId() + "'");
						if (tcs.size() > 0) {
							if (tcs.get(0).getStatus() == 1) {
								plantail.setState("不可分配");
								pc.setTester("my");
							} else {
								plantail.setState("可分配");
							}
						} else {
							plantail.setState("可分配");
						}
					} else {
						List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.casestoreId='"
								+ pc.getCasestore().getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");
						for (TestCase tc : tcs) {
							if (tc.getStatus() == 1) {
								plantail.setState("不可分配");
								pc.setTester("other");
								break;
							} else {
								plantail.setState("可分配");
								pc.setTester("可分配");

							}
						}
						if (tcs.size() == 0) {
							plantail.setState("可分配");
							pc.setTester("可分配");
						}

					}

				}
				for (PlanCasestore p : pcs) {
					if (!p.getTester().equals("my") && !p.getTester().equals("other")) {
						plantail.setState("可分配");
						break;
					}
				}
				ptf.setPlantail(plantail);
				ptf.setPlancasestores(pcs);
				ptfs.add(ptf);
				setPlanId(plan.getPlanId());
			}
			pageBean.setList(ptfs);
			setPermissionForm(rolePermissionService.getUserPermission("mytask.jsp"));

		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}

		return "showMasterTask";
	}

	/**
	 * 本方法主要是获取测试人员工作台上的所需时间
	 * 
	 * @return 需要执行的时间
	 */
	@SuppressWarnings("unchecked")
	public double getTesterUseTime(int userId) {
		double total = 0;// 所有需要执行用例的时间
		// 查找测试人员所有未执行的用例
		// System.out.println(userId);
		String hql = "from Case c where c.caseId in(select cr.cases.caseId from Caseresult cr where cr.result is null  and cr.user.userId='"
				+ userId + "')";
		// String hql = "from Caseresult cr where cr.result is null and
		// cr.user.userId='" + userId + "'";
		List<Case> crs = hqlService.findByHql(hql);
		// System.out.println("大小：" + crs.size());
		for (Case c : crs) {
			// 根据查找到的用例继续查找用例对应的耗时
			// Case c=caseService.findById(cr.getCases().getCaseId());
			if (c.getAdvidceTime() != null && c.getAdvidceTime() != "") {
				// 计算所有符合条件用例的总耗时
				double caseTime = Double.parseDouble(c.getAdvidceTime());
				total = total + caseTime;
			}

		}
		total = total / (60 * 8);
		return total;
	}

	/**
	 * 根据计划编号获取该计划中的测试内容
	 * 
	 * @param plan
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlanCasestore> getCasestore(Plan plan) {
		User user = (User) getSession().getAttribute("user");
		List<PlanCasestore> pcs = hqlService
				.findByHql("from PlanCasestore pc where pc.plan.planId='" + plan.getPlanId() + "'");
		for (PlanCasestore pc : pcs) {
			pc.setCasestore(casestoreService.findById(pc.getCasestore().getCasestoreId()));
			pc.setPlan(plan);
			String userIds = pc.getTester();
			if (userIds != null && userIds.contains(",")) {
				String str[] = userIds.split(",");
				for (int i = 0; i < str.length; i++) {
					if (str[i].equals(user.getUserId() + "")) {
						pc.setTester(str[i]);
					} else {
						pc.setTester("");
					}
				}

			}

		}
		return pcs;
	}

	private PlantailForm plantailForm;

	public PlantailForm getPlantailForm() {
		return plantailForm;
	}

	public void setPlantailForm(PlantailForm plantailForm) {
		this.plantailForm = plantailForm;
	}

	private int planId;

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	/**
	 * 主测的未分配任务详情显示
	 * 
	 * @throws ParseException
	 */
	public String showmTaskDetail() throws ParseException {
		try {
			PlantailForm ptf = new PlantailForm();
			System.out.println("回显跟踪id：" + id);
			Plantail plantail = plantailService.findById(id);
			Plan plan = planService.findById(plantail.getPlan().getPlanId());
			Project project = projectService.findById(plan.getProject().getId());
			Casetype casetype = casetypeService.findById(plan.getCasetype().getId());
			plan.setProject(project);
			plan.setCasetype(casetype);
			plantail.setPlan(plan);
			plantail.setUser(userService.findUserById(plantail.getUser().getUserId()));
			plantail.setState(plantail.getState());
			ptf.setPlantail(plantail);
			ptf.setPlancasestores(getCasestore(plan));
			setPlantailForm(ptf);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showmTaskDetail";
	}

	/**
	 * 根据用户角色权限，获取相应的人员信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showUserMsg() {
		try {
			long start = System.currentTimeMillis();
			HttpServletResponse out = ServletActionContext.getResponse();
			HttpSession session = getSession();
			Role role = (Role) session.getAttribute("role");
			User u = (User) session.getAttribute("user");
			// double totalTime=(double) session.getAttribute("total");
			List<Function> functions = roleFunctionService.findFidByRid(role);
			Plan plan = planService.findById(id);
			ArrayList<String> list = new ArrayList<String>();
			for (Function function : functions) {
				if (function.getDescription().equals("显示所有人员")) {
					final String hq = "from User";
					List<User> users = hqlService.findByHql(hq);
					for (User line : users) {
						Role r = getRoleByUid(line.getUserId());
						double totalTime = getTesterUseTime(line.getUserId());
						// 从项目人员中筛选出主测需要分配给的成员（除了软测测试经理和软测主测）
						if ((r.getRoleName().contains("经理"))) {
						} else {
							list.add(line.getName() + "_" + line.getUserId() + "_" + totalTime);
						}
					}
					break;
				}
				if (function.getDescription().equals("显示同组人员")) {
					Project project = projectService.findById(plan.getProject().getId());
					final String hq = "from User u where u.groupName ='" + u.getGroupName() + "'";
					List<User> users = hqlService.findByHql(hq);
					for (User line : users) {
						double totalTime = getTesterUseTime(line.getUserId());
						List<Integer> userIds = projectUserService.findUidByPid(project.getId());
						int i = 0;
						for (int userId : userIds) {
							if (userId == line.getUserId()) {
								i = 1;
								break;
							}
						}
						if (i == 1) {
							i = 0;
						} else {
							Role r = getRoleByUid(line.getUserId());
							// 从项目人员中筛选出主测需要分配给的成员（除了软测测试经理和软测主测）
							if ((r.getRoleName().contains("经理")) || (r.getRoleName().contains("主测"))
									|| (r.getRoleName().contains("组长"))) {
							} else {

								list.add(line.getName() + "_" + line.getUserId() + "_" + totalTime);
							}
						}

					}
					break;
				}

				if (function.getDescription().equals("显示项目内成员")) {
					Project project = projectService.findById(plan.getProject().getId());
					List<User> users = new ArrayList<User>();
					ProjectUser pu = new ProjectUser();
					List<Integer> userIds = projectUserService.findUidByPid(project.getId());
					for (int uid : userIds) {
						pu.setProject(project);
						pu.setUser(userService.findUserById(uid));
						// System.out.println("项目人员：" + pu.getUser().getName());
						double totalTime = getTesterUseTime(uid);
						users.add(pu.getUser());
						List<Role> ur = hqlService.findByHql(
								"from Role r where r.roleId in(select ur.roleId from UserRole ur where ur.userId='"
										+ pu.getUser().getUserId() + "')");
						if (!ur.get(0).getRoleName().contains("经理") && !ur.get(0).getRoleName().contains("主测")) {
							// if (pu.getUser().getStatus() == 0)

							list.add(pu.getUser().getName() + "_" + pu.getUser().getUserId() + "_" + totalTime);
						}

					}
					break;
				}
			}
			if (list.size() > 0) {
				out.setCharacterEncoding("UTF-8");
				out.getWriter().write(list.toString());
			} else {
				out.setCharacterEncoding("UTF-8");
				out.getWriter().write("fail");
			}
			long end = System.currentTimeMillis();
			System.out.println("处理人员信息耗时：" + (end - start) + "ms");
		} catch (Exception e) {
			addExceptionLog(e, "显示人员信息异常");
			return "error";
		}
		return null;

	}

	private String name;// 测试内容

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 根据计划编号获取该该用例所拥有的级别和用例编号
	 */
	@SuppressWarnings("unchecked")
	public String showCaseLevel() {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();
			String casestoreName = new String(name.getBytes("ISO-8859-1"), "UTF-8").split(",")[0];
			Casestore ct = casestoreService.findByName(name.split(",")[0].trim());
			if (ct == null) {
				ct = casestoreService.findByName(casestoreName.trim());
			}
			// System.out.println(casestoreName);
			// System.out.println(name);
			List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.casestoreId='" + ct.getCasestoreId()
					+ "' and tc.planId='" + planId + "'");
			String username = "";
			for (TestCase tc : tcs) {
				User u = userService.findUserById(tc.getUser().getUserId());
				if (username == "")
					username = "," + u.getName() + "-" + tc.getCaseNum() + " ";
				else
					username = username + u.getName() + "-" + tc.getCaseNum() + " ";
			}
			List<Case> cases = caseService.findByCasestoreId(ct.getCasestoreId());
			HashSet<String> level = new HashSet<String>();
			String str = "";
			String totalTime = getCaseTestTime(ct.getCasestoreId());
			for (Case c : cases) {

				if (c.getLevel() == null || c.getLevel().equals("")) {
					level.add("其他");
				} else {
					level.add(c.getLevel().toLowerCase());
				}

			}
			// System.out.println(level.size());
			for (Iterator<String> it = level.iterator(); it.hasNext();) {
				// System.out.println("aaa:"+it.next());
				String l = it.next();
				String total = getLevelCaseTime(ct.getCasestoreId(), l);
				str = str + l + "_" + total + ",";

			}
			if (username != "") {
				str = str + "1," + cases.size() + "," + totalTime + username;
			} else {
				str = str + "1," + cases.size() + "," + totalTime + ",未分配";
			}

			out.setCharacterEncoding("UTF-8");
			out.getWriter().write(str);
		} catch (Exception e) {
			addExceptionLog(e, "获取用例等级异常");
			return "error";
		}
		return null;
	}

	/**
	 * 获取测试内容中所有用例总耗时
	 * 
	 * @param casestoreId
	 *            测试内容编号
	 * @return
	 */
	public String getCaseTestTime(int casestoreId) {
		List<Case> cases = caseService.findByCasestoreId(casestoreId);
		double totalTime = 0;
		for (Case c : cases) {
			if (c.getAdvidceTime() != null && !c.getAdvidceTime().isEmpty() && c.getAdvidceTime() != ""
					&& !c.getAdvidceTime().equals("")) {
				// System.out.println(c.getAdvidceTime());
				totalTime += Double.parseDouble(c.getAdvidceTime());
			}

		}
		return new DecimalFormat("0.00").format(totalTime / (60 * 8));
	}

	/**
	 * 获取测试内容中相关等级所需的耗时
	 * 
	 * @param casestoreId
	 *            测试内容编号
	 * @param level
	 *            用例等级
	 */
	public String getLevelCaseTime(int casestoreId, String level) {
		List<Case> cases = caseService.findByCasestoreId(casestoreId);
		double total = 0;
		if (level.equals("其他")) {
			for (Case c : cases) {
				if (c.getLevel() == null || c.getLevel() == "") {
					if (c.getAdvidceTime() != null && c.getAdvidceTime() != "")
						total += Double.parseDouble(c.getAdvidceTime());
				}
			}
		} else {
			for (Case c : cases) {
				if (c.getLevel().toLowerCase().equals(level.toLowerCase())) {
					if (c.getAdvidceTime() != null && c.getAdvidceTime() != "")
						total += Double.parseDouble(c.getAdvidceTime());
				}
			}
		}

		return new DecimalFormat("0.00").format(total / (60 * 8));
	}

	private int start;
	private int end;
	private String checkId;
	private String module;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	private String arrsum;

	public String getArrsum() {
		return arrsum;
	}

	public void setArrsum(String arrsum) {
		this.arrsum = arrsum;
	}

	/**
	 * 主测组长分配任务
	 * 
	 * @throws ParseException
	 */
	public String deliverTask() {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();
			Role r = (Role) getSession().getAttribute("role");
			List<Function> functions = roleFunctionService.findFidByRid(r);
			boolean bflag = false;
			for (Function f : functions) {
				if (f.getDescription().equals("分配计划")) {
					bflag = true;
					break;
				}
			}
			if (bflag) {
				String jsonStr = ServletActionContext.getRequest().getParameter("params");
				System.out.println(jsonStr);
				// JSONArray arr = JSONArray.fromObject(jsonStr);
				JSONArray arr = JSONArray.fromObject(jsonStr);
				// System.out.println(arr);
				Object[] os = arr.toArray();
				System.out.println("长度：" + os.length);
				for (int i = 0; i < os.length; i++) {
					JSONObject obj = JSONObject.fromObject(os[i]);
					String planId = obj.getString("planId");
					// String person = obj.getString("person");
					String testcon = obj.getString("testcon");
					// String testnum = obj.getString("testnum");
					String starttime = obj.getString("starttime");
					String endtime = obj.getString("endtime");
					String remark = obj.getString("remark");
					String sumall = obj.getString("arrsum");
					System.out.println("PlanId:" + planId);
					System.out.println("testcontent:" + testcon);
					System.out.println("startTime:" + starttime);
					System.out.println("endTime:" + endtime);
					System.out.println("remark:" + remark);
					System.out.println("sumall:" + sumall);
					setModule(testcon + ",");
					HttpServletRequest request = ServletActionContext.getRequest();
					HttpSession session = request.getSession();
					User user = (User) session.getAttribute("user");
					Plan plan = planService.findById(Integer.parseInt(planId));
					Plantail pt = new Plantail();
					// pt.setCaseCount(testnum);
					pt.setStartTime(starttime);
					pt.setEndTime(endtime);
					pt.setRemark(remark);
					pt.setPlan(plan);
					setPlantail(pt);

					// P1-其他-,001,092,4/
					// if (sumall == "" || sumall.equals("")) {// 一个人分多个模块
					// User u =
					// userService.findUserById(Integer.parseInt(person));
					// writeCaseresult(u, plan);
					// checkPlanDeliver(u, plan);
					// } else {// 多个人分一个模块，或一个人分一个模块
					String level = "";
					String s[] = sumall.split("/");
					String uid = "";
					Casestore ct = null;
					for (int j = 0; j < s.length; j++) {// 遍历分配给多少个人

						if (s[j].contains("-")) {// 通过用例等级分配任务
							level = s[j].split(",")[0];
							int userId = Integer.parseInt(s[j].split(",")[3]);
							User u = userService.findUserById(userId);
							ct = writeCaseresult(u, plan, level);
							checkPlanDeliver(u, plan);
							uid = uid + userId + ",";
						} else {// 通过用例编号分配任务
							String ss[] = s[j].split(",");
							User u = userService.findUserById(Integer.parseInt(ss[3]));
							ct = writeCaseresult(u, plan, Integer.parseInt(ss[1]), Integer.parseInt(ss[2]));
							checkPlanDeliver(u, plan);
							uid = uid + ss[3] + ",";
						}
					}
					if (ct != null) {
						writePlanCasestore(plan, ct, uid);
					}

					// }

				}
				// out.getWriter().write("success");
			} else {
				// out.getWriter().write("fail");
			}
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}

		return "success";
	}

	/**
	 * 将测试任务分配给自己测试
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deliverMy() {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();
			User user = (User) getSession().getAttribute("user");
			Role role = (Role) getSession().getAttribute("role");
			List<Function> functions = roleFunctionService.findFidByRid(role);
			int i = 0;
			for (Function f : functions) {
				if (f.getDescription().equals("分配计划") && f.getTag().equals("add")) {
					i = 1;
					break;
				}
			}
			if (i == 1) {
				Casestore ct = casestoreService.findByName(name.trim());
				if (ct == null) {
					ct = casestoreService.findByName(new String(name.getBytes("ISO-8859-1"), "UTF-8"));
				}
				List<Case> cases = caseService.findByCasestoreId(ct.getCasestoreId());
				Plantail plantail = plantailService.findByPidAndUid(planId, user.getUserId());
				int num = caseresultService.findByPidAndUid(planId, user.getUserId()).size();
				plantail.setCaseCount((cases.size() + num) + "");
				plantailService.update(plantail);
				List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.user.userId='" + user.getUserId()
						+ "' and tc.casestoreId='" + ct.getCasestoreId() + "' and tc.planId='" + planId + "'");
				if (tcs.size() > 0) {
					TestCase tc = tcs.get(0);
					String caseNum = caseresultService.findByPidAndUid(planId, user.getUserId()).size() + "";
					tc.setCaseNum(caseNum + cases.size());
					testCaseService.updateTestCase(tc);
				} else {
					TestCase tc = new TestCase();
					tc.setId(0);
					tc.setCasestoreId(ct.getCasestoreId());
					tc.setStatus(0);
					tc.setUser(user);
					tc.setCaseNum(cases.size() + "");
					tc.setTestTime(plantail.getStartTime());
					tc.setPlanId(planId);
					testCaseService.saveTestCase(tc);

				}
				for (Case c : cases) {
					saveCaseResult(c, planService.findById(planId), user, ct);
				}
				out.getWriter().write("success");
			} else {
				out.getWriter().write("fail");
			}

		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}

		return null;
	}

	/**
	 * 验证计划中的内容是否可分配
	 */
	@SuppressWarnings("unchecked")
	public String checkPlanContent() {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();
			String request = "";
			User u = (User) getSession().getAttribute("user");
			Plan plan = planService.findById(planId);
			List<PlanCasestore> pcs = getCasestore(plan);
			for (PlanCasestore pc : pcs) {

				if (pc.getTester().equals(u.getUserId() + "")) {
					List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.user.userId='" + u.getUserId()
							+ "' and tc.casestoreId='" + pc.getCasestore().getCasestoreId() + "' and tc.planId='"
							+ plan.getPlanId() + "'");
					if (tcs.size() > 0) {
						if (tcs.get(0).getStatus() == 1) {
							request = "fail";
							pc.setTester("1");
						} else {
							request = "success";
						}
					} else {
						request = "success";
					}
				} else {
					List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.casestoreId='"
							+ pc.getCasestore().getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");
					for (TestCase tc : tcs) {
						if (tc.getStatus() == 1) {
							request = "fail";
							pc.setTester("");
							break;
						} else {
							request = "success";
							pc.setTester("可分配");
						}
					}
					if (tcs.size() == 0) {
						request = "success";
						pc.setTester("可分配");
					}

				}
				if (request.equals("success"))
					break;
			}
			out.getWriter().write(request);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}

		return null;
	}

	/**
	 * 添加用户关联的计划表
	 * 
	 * @param u
	 * @param plan
	 */
	public void checkPlanDeliver(User u, Plan plan) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Plantail plant = plantailService.findByPidAndUid(plan.getPlanId(), u.getUserId());
		int caseNum = hqlService.findByHql("from Caseresult cr where cr.user.userId='" + u.getUserId()
				+ "' and cr.plan.planId='" + plan.getPlanId() + "'").size();
		if (plant != null) {

			plant.setFinishDegree("0.0%");
			plant.setFinishState("0");
			plant.setRemark(plantail.getRemark());
			plant.setStartTime(plantail.getStartTime());
			plant.setEndTime(plantail.getEndTime());
			plant.setCaseCount(caseNum + "");
			plant.setReceiverTime(sdf.format(new Date()));
			plantailService.update(plant);
		} else {
			Plantail pt = new Plantail();
			pt.setId(0);
			pt.setUser(u);
			pt.setFinishDegree("0.0%");
			pt.setFinishState("0");
			pt.setRemark(plantail.getRemark());
			pt.setStartTime(plantail.getStartTime());
			pt.setEndTime(plantail.getEndTime());
			pt.setPlan(plan);
			pt.setCaseCount(caseNum + "");
			pt.setReceiverTime(sdf.format(new Date()));
			plantailService.save(pt);
		}
	}

	/**
	 * 将分配的用例写入结果集中（分配给同一个人）
	 */
	@SuppressWarnings("unchecked")
	public void writeCaseresult(User u, Plan plan) {
		if (module.contains(",")) {
			String str[] = module.split(",");
			// 将用例分配给需要测的人
			for (int i = 0; i < str.length; i++) {
				Casestore ct = casestoreService.findByName(str[i].trim());
				if (ct != null) {
					List<Case> cases = caseService.findByCasestoreId(ct.getCasestoreId());// 根据测试内容查找其所有用例
					List<TestCase> tcs = hqlService.findByHql(
							"from TestCase tc where tc.user.userId='" + u.getUserId() + "' and tc.casestoreId='"
									+ ct.getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");
					if (tcs.size() > 0) {
						TestCase tc = tcs.get(0);
						int caseNum = caseresultService.findByPidAndUid(plan.getPlanId(), u.getUserId()).size();
						tc.setCaseNum((caseNum + cases.size()) + "");
						testCaseService.updateTestCase(tc);
					} else {
						TestCase tc = new TestCase();
						tc.setId(0);
						tc.setCasestoreId(ct.getCasestoreId());
						tc.setStatus(0);
						tc.setUser(u);
						tc.setCaseNum(cases.size() + "");
						tc.setTestTime(plantail.getStartTime());
						tc.setPlanId(plan.getPlanId());
						testCaseService.saveTestCase(tc);

					}
					for (Case c : cases) {
						Caseresult caseresult = caseresultService.findByPidAndCid(plan.getPlanId(), c.getCaseId());
						if (caseresult != null) {
							List<TestCase> testCases = hqlService.findByHql("from TestCase tc where tc.user.userId='"
									+ caseresult.getUser().getUserId() + "' and tc.casestoreId='" + ct.getCasestoreId()
									+ "' and tc.planId='" + plan.getPlanId() + "'");
							if (testCases.size() > 0) {
								TestCase testCase = testCases.get(0);
								testCase.setCaseNum(Integer.parseInt(testCase.getCaseNum()) - 1 + "");
								if ((Integer.parseInt(testCase.getCaseNum()) - 1) == 0) {
									testCaseService.deleteTestCase(testCase);
								} else {
									testCaseService.updateTestCase(testCase);
								}
							}
							int userId = caseresult.getUser().getUserId();
							Plantail pt = plantailService.findByPidAndUid(plan.getPlanId(), userId);
							pt.setCaseCount((Integer.parseInt(pt.getCaseCount()) - 1) + "");
							plantailService.update(pt);
							caseresult.setUser(u);
							caseresultService.update(caseresult);

						} else {
							List<TestCase> testCases = hqlService.findByHql(
									"from TestCase tc where tc.user.userId='" + u.getUserId() + "' and tc.casestoreId='"
											+ ct.getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");
							Caseresult cr = new Caseresult();
							cr.setId(0);
							cr.setCases(c);
							cr.setPlan(plan);
							cr.setUser(u);
							cr.setCasestoreId(testCases.get(0).getId());
							caseResultService.save(cr);
						}
					}
					List<PlanCasestore> pcs = hqlService.findByHql("from PlanCasestore pc where pc.plan.planId='"
							+ plan.getPlanId() + "' and pc.casestore.casestoreId='" + ct.getCasestoreId() + "'");
					// 根据测试内容分配给谁测试
					for (PlanCasestore pc : pcs) {
						pc.setTester(u.getUserId() + ",");
						planService.updatePlanCasestore(pc);
					}
				}

			}

		}
	}

	/**
	 * 将分配的用例写入结果集中（分配给多个人）
	 */
	@SuppressWarnings("unchecked")
	public Casestore writeCaseresult(User u, Plan plan, String level) {
		String str[] = module.split(",");
		Casestore ct = casestoreService.findByName(str[0].trim());
		if (ct != null) {
			String s[] = level.split("-");
			for (int i = 0; i < s.length; i++) {
				List<Case> cases = null;
				if (s[i].equals("其他")) {
					cases = hqlService.findByHql("from Case c where c.casestoreId='" + ct.getCasestoreId()
							+ "' and c.level in('" + "" + "'','" + null + "')");

				} else {
					cases = hqlService.findByHql("from Case c where c.casestoreId='" + ct.getCasestoreId()
							+ "' and c.level='" + s[i].trim() + "'");
				}
				if (cases != null) {
					List<TestCase> tcs = hqlService.findByHql(
							"from TestCase tc where tc.user.userId='" + u.getUserId() + "' and tc.casestoreId='"
									+ ct.getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");
					if (tcs.size() > 0) {
						TestCase tc = tcs.get(0);
						int caseNum = caseresultService.findByPidAndUid(plan.getPlanId(), u.getUserId()).size();
						tc.setCaseNum((caseNum + cases.size()) + "");
						testCaseService.updateTestCase(tc);
					} else {
						TestCase tc = new TestCase();
						tc.setId(0);
						tc.setCasestoreId(ct.getCasestoreId());
						tc.setStatus(0);
						tc.setUser(u);
						tc.setCaseNum(cases.size() + "");
						tc.setTestTime(plantail.getStartTime());
						tc.setPlanId(plan.getPlanId());
						testCaseService.saveTestCase(tc);

					}
					for (Case c : cases) {
						saveCaseResult(c, plan, u, ct);
					}
				}
			}

		}
		return ct;
	}

	/**
	 * 将分配的用例写入结果集中（分配给多个人）
	 */
	@SuppressWarnings("unchecked")
	public Casestore writeCaseresult(User u, Plan plan, int start, int end) {
		String str[] = module.split(",");
		Casestore ct = casestoreService.findByName(str[0].trim());
		if (ct != null) {
			List<Case> cases = caseService.findByCasestoreId(ct.getCasestoreId());
			List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.user.userId='" + u.getUserId()
					+ "' and tc.casestoreId='" + ct.getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");
			if (tcs.size() > 0) {
				TestCase tc = tcs.get(0);
				int caseNum = caseresultService.findByPidAndUid(plan.getPlanId(), u.getUserId()).size();
				tc.setCaseNum((caseNum + (end - start + 1)) + "");
				testCaseService.updateTestCase(tc);
			} else {
				TestCase tc = new TestCase();
				tc.setId(0);
				tc.setCasestoreId(ct.getCasestoreId());
				tc.setStatus(0);
				tc.setUser(u);
				tc.setCaseNum((end - start + 1) + "");
				tc.setTestTime(plantail.getStartTime());
				tc.setPlanId(plan.getPlanId());
				testCaseService.saveTestCase(tc);

			}
			for (int i = start - 1; i < end; i++) {
				saveCaseResult(cases.get(i), plan, u, ct);
			}
		}
		return ct;
	}

	/**
	 * 修改和保存用例结果
	 */
	@SuppressWarnings("unchecked")
	public void saveCaseResult(Case c, Plan plan, User user, Casestore ct) {
		Caseresult cr = caseresultService.findByPidAndCid(plan.getPlanId(), c.getCaseId());
		if (cr != null) {// 更新
			List<TestCase> testCases = hqlService.findByHql(
					"from TestCase tc where tc.user.userId='" + cr.getUser().getUserId() + "' and tc.casestoreId='"
							+ ct.getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");
			if (testCases.size() > 0) {
				TestCase testCase = testCases.get(0);
				List<Caseresult> crs = hqlService
						.findByHql("from Caseresult cr where cr.casestoreId='" + testCase.getId() + "'");
				if (crs.size() == 0) {
					testCaseService.deleteTestCase(testCase);
				} else {
					testCase.setCaseNum(crs.size() - 1 + "");
					if ((crs.size() - 1) == 0) {
						testCaseService.deleteTestCase(testCase);
					} else {
						testCaseService.updateTestCase(testCase);
					}
				}

			}
			int userId = cr.getUser().getUserId();
			Plantail pt = plantailService.findByPidAndUid(plan.getPlanId(), userId);
			if (pt != null) {
				pt.setCaseCount((Integer.parseInt(pt.getCaseCount()) - 1) + "");
				plantailService.update(pt);
			}
			cr.setUser(user);
			caseresultService.update(cr);
		} else {// 添加
			List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.user.userId='" + user.getUserId()
					+ "' and tc.casestoreId='" + ct.getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");

			Caseresult caseresult = new Caseresult();
			caseresult.setId(0);
			caseresult.setCasestoreId(tcs.get(0).getId());
			caseresult.setCases(c);
			caseresult.setPlan(plan);
			caseresult.setUser(user);
			caseresultService.save(caseresult);
		}
	}

	/**
	 * 将人员写入到计划测试内容中（用来识别该测试内容在谁手上）
	 * 
	 * @param plan
	 * @param ct
	 * @param userId
	 */
	@SuppressWarnings("unchecked")
	public void writePlanCasestore(Plan plan, Casestore ct, String userId) {
		List<PlanCasestore> pcs = hqlService.findByHql("from PlanCasestore pc where pc.plan.planId='" + plan.getPlanId()
				+ "' and pc.casestore.casestoreId='" + ct.getCasestoreId() + "'");
		// 根据测试内容分配给谁测试
		for (PlanCasestore pc : pcs) {
			pc.setTester(userId);
			planService.updatePlanCasestore(pc);
		}
	}

	/**
	 * 查看主测的已分配任务(历史任务),并分页显示
	 * 
	 * @return
	 * @throws ParseException
	 */
	/*
	 * public String showMassignedTask() throws ParseException { try {
	 * HttpServletRequest request = ServletActionContext.getRequest();
	 * HttpSession session = request.getSession(); User u = (User)
	 * session.getAttribute("user"); Role r = (Role)
	 * session.getAttribute("role"); System.out.println("登录人姓名：" + u.getName());
	 * System.out.println("登录人角色：" + r.getRoleName());
	 * 
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); final String
	 * hql = "from Plantail pt where pt.user.userId ='" + u.getUserId() +
	 * "' and pt.state=0"; this.pageBean = pageService.queryForPage(hql, 1000,
	 * page); List<Plantail> pts = new ArrayList<Plantail>(); for (Object line :
	 * pageBean.getList()) { System.out.println("跟踪id：" + ((Plantail)
	 * line).getId()); // 1.根据跟踪表中的计划id查找计划 Plan plan =
	 * planService.findById(((Plantail) line).getPlan().getPlanId());
	 * 
	 * // 2.再根据计划表中项目id查找项目 System.out.println("计划id:" + plan.getPlanId());
	 * System.out.println("项目id:" + plan.getProject().getId()); Project project
	 * = projectService.findById(plan.getProject().getId()); Casetype casetype =
	 * casetypeService.findById(plan.getCasetype().getId()); Casestore casestore
	 * = casestoreService.findById(plan.getCasestore().getCasestoreId());
	 * 
	 * // 3.将查找到的项目放入计划表中 plan.setProject(project); plan.setCasetype(casetype);
	 * plan.setCasestore(casestore);
	 * plan.setVersionReleasetime(plan.getVersionReleasetime());
	 * plan.setStartTime(plan.getStartTime());
	 * plan.setEndTime(plan.getEndTime());
	 * 
	 * // 4.再将查找后的计划放入跟踪表中 Plantail pt = new Plantail(); pt.setId(((Plantail)
	 * line).getId()); pt.setPlan(plan);
	 * pt.setUser(userService.findUserById(((Plantail)
	 * line).getUser().getUserId())); pt.setState("已分配"); pts.add(pt); }
	 * pageBean.setList(pts); } catch (Exception e) { addExceptionLog(e, "异常");
	 * return "error"; } return "showMassignedTask"; }
	 */

	@SuppressWarnings("unchecked")
	public Role getRoleByUid(int uid) {
		List<Role> roles = hqlService.findByHql(
				"from Role r where r.roleId in(select ur.roleId from UserRole ur where ur.userId='" + uid + "')");
		return roles.get(0);
	}

	private PermissionForm permissionForm;

	public PermissionForm getPermissionForm() {
		return permissionForm;
	}

	public void setPermissionForm(PermissionForm permissionForm) {
		this.permissionForm = permissionForm;
	}

	/**
	 * 验证用户是否有测试用例可测
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String checkTestCase() {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();
			User user = (User) getSession().getAttribute("user");
			Role role = (Role) getSession().getAttribute("role");
			Casestore ct = casestoreService.findByName(name.trim());
			if (ct == null) {
				ct = casestoreService.findByName(new String(name.getBytes("ISO-8859-1"), "UTF-8"));
			}
			List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.casestoreId='" + ct.getCasestoreId()
					+ "' and tc.planId='" + planId + "' and tc.user.userId='" + user.getUserId() + "'");
			// List<Caseresult> crs = caseresultService.findByPidAndUid(planId,
			// user.getUserId());
			System.out.println("aaaa");
			if (tcs.size() > 0) {
				out.getWriter().write("success");
			} else {

				List<Function> functions = roleFunctionService.findFidByRid(role);
				int i = 0;
				for (Function f : functions) {
					if (f.getDescription().equals("分配计划") && f.getTag().equals("add")) {
						i = 1;
						break;
					}
				}
				if (i == 1) {
					out.getWriter().write("fail");
				} else {
					out.getWriter().write("no");
				}

			}

		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}

		return null;
	}

	/**
	 * 系统测试人员执行用例界面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String testCase() {
		try {
			// 获取用户登录信息
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			// planId值计划ID;name指测试内容
			System.out.println("%%%%%%%" + planId + name + u.getUserId());
			Casestore cs = casestoreService.findByName(name);
			List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.user.userId='" + u.getUserId()
					+ "' and tc.casestoreId='" + cs.getCasestoreId() + "' and tc.planId='" + planId + "'");
			// 查找用例结果表;
			for (TestCase tc : tcs) {
				final String hql = "from Caseresult cr where cr.plan.planId='" + planId + "' and cr.user.userId='"
						+ u.getUserId() + "'and cr.casestoreId='" + tc.getId() + "'order by cr.cases.caseId asc";
				this.pageBean = pageService.queryForPage(hql, 10, page);
				for (Object obj : pageBean.getList()) {
					((Caseresult) obj).setCases(caseService.findById(((Caseresult) obj).getCases().getCaseId()));
					((Caseresult) obj).setPlan(planService.findById(planId));
					((Caseresult) obj).setUser(u);
				}
			}
			// 设置权限
			setPermissionForm(rolePermissionService.getUserPermission("testcase.jsp"));
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showCase";
	}

	/**
	 * 保存用例测试结果
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String SaveCaseResult() {
		try {
			User user = (User) getSession().getAttribute("user");
			String jsonStr = ServletActionContext.getRequest().getParameter("params");
			System.out.println(jsonStr);
			// JSONArray arr = JSONArray.fromObject(jsonStr);
			JSONArray arr = JSONArray.fromObject(jsonStr);
			System.out.println(arr);
			Object[] os = arr.toArray();
			System.out.println("长度：" + os.length);
			for (int i = 0; i < os.length; i++) {
				JSONObject obj = JSONObject.fromObject(os[i]);
				String caseId = obj.getString("caseid").toString();// 用例编号
				String planId = obj.getString("ids").toString();// 计划编号
				String remark = obj.getString("mark").toString();// 备注
				String result = obj.getString("res").toString();// 测试结果
				Caseresult caseresult = caseResultService.findByPidAndCid(Integer.parseInt(planId),
						Integer.parseInt(caseId));
				caseresult.setResult(result);
				caseresult.setRemark(remark);
				caseResultService.update(caseresult);// 将测试用例的结果和备注更新到用例结果集中
				TestCase tc = testCaseService.findById(caseresult.getCasestoreId());
				if (tc.getStatus() == 0) {
					tc.setStatus(1);
					testCaseService.updateTestCase(tc);
				}
				// 将任务完成度更新到计划跟踪表中
				Plantail plantail = plantailService.findByPidAndUid(Integer.parseInt(planId), user.getUserId());

				List<Caseresult> caseresults = caseResultService.findByPidAndUid(Integer.parseInt(planId),
						user.getUserId());
				int tag = 0;
				for (Caseresult cr : caseresults) {
					// System.out.println("--" + cr.getResult() + "--");
					if (cr.getResult() != "" && cr.getResult() != null && !cr.getResult().equals("")) {
						tag = tag + 1;
					}
				}
				// System.out.println(tag);
				plantail.setFinishCase(tag + "");
				if (!plantail.getCaseCount().equals("0")) {
					float num = (float) tag / Integer.parseInt(plantail.getCaseCount()) * 100;
					DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
					String finsh = df.format(num);// 返回的是String类型
					plantail.setFinishDegree(finsh + "%");
					if (finsh.equals("100")) {
						plantail.setFinishState("1");

					} else {
						plantail.setFinishState("0");
					}

				} else {
					plantail.setFinishDegree("0%");
					plantail.setFinishState("0");
				}

				plantailService.update(plantail);
			}
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "success";
	}

	/**
	 * 根据用户所测的用例条数，将测试完成度写入计划跟踪表中
	 * 
	 * @param user
	 * @param planId
	 */
	public void addPlanpail(User user, String planId) {
		// 将任务完成度更新到计划跟踪表中
		Plantail plantail = plantailService.findByPidAndUid(Integer.parseInt(planId), user.getUserId());
		List<Caseresult> caseresults = caseResultService.findByPidAndUid(Integer.parseInt(planId), user.getUserId());
		int tag = 0;
		for (Caseresult cr : caseresults) {
			System.out.println("--" + cr.getResult() + "--");
			if (cr.getResult() != "" && cr.getResult() != null && !cr.getResult().equals("")) {
				tag = tag + 1;
			}
		}
		// System.out.println(tag);
		plantail.setFinishCase(tag + "");
		// System.out.println(plantail.getCaseCount());
		if (!plantail.getCaseCount().equals("0")) {
			double d = tag / Double.parseDouble(plantail.getCaseCount());
			java.math.BigDecimal b = new java.math.BigDecimal(d);
			double finsh = b.setScale(4, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
			plantail.setFinishDegree(finsh * 100 + "%");
			if (finsh == 1) {
				plantail.setFinishState("1");
			} else {
				plantail.setFinishState("0");
			}

		} else {
			plantail.setFinishDegree("0%");
			plantail.setFinishState("0");
		}

		plantailService.update(plantail);
	}

	public HttpSession getSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 导入用例测试结果
	 */
	public String importCaseResult() {
		try {
			String directory = "/upload";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			System.out.println("aaa" + targetDirectory);
			System.out.println("bbb" + fileFileName);
			System.out.println("as:" + rowNum);
			File target = new File(targetDirectory, fileFileName);
			// 如果文件已经存在，则删除原有文件m
			if (target.exists()) {
				target.delete();
			}
			// 复制file对象，实现上传
			try {
				FileUtils.copyFile(file, target);
			} catch (IOException e) {
				e.printStackTrace();
			}

			ArrayList<Case> list_case = new ArrayList<Case>();
			if (target.getAbsolutePath().endsWith("xlsx")) {

				list_case = ReadExcel.write2007Case(ReadExcel.read2007Excel(target, num), rowNum);
			} else {
				list_case = ReadExcel.write2003Case(ReadExcel.readExcel(target, num), rowNum);
			}

			if (list_case.size() > 0) {
				for (Case c : list_case) {
					List<Case> cases = caseService.findByNum(c.getNum());
					Caseresult cas = caseResultService.findByPidAndCid(planId, cases.get(0).getCaseId());
					cas.setRemark(c.getRemark());
					cas.setResult(c.getTestResult());
					caseResultService.update(cas);
					// System.out.println(c.getNum());
				}
				User user = (User) getSession().getAttribute("user");
				addPlanpail(user, planId + "");

				// JOptionPane.showMessageDialog(null, "导入完成");
			} else {
				// JOptionPane.showMessageDialog(null, "导入失败，请选择正确的文件格式");
			}
		} catch (HeadlessException e) {
			addExceptionLog(e, "异常");
			return "error";
		}

		return "uploadSuccess";
	}

	/**
	 * 显示工作进度
	 * 
	 * @return
	 * @throws ParseException
	 */

	@SuppressWarnings("unchecked")
	public String showTakSchedule() throws ParseException {
		try {
			// 获取登录人的信息
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Role r = (Role) session.getAttribute("role");
			System.out.println("登录人姓名：" + u.getName());
			System.out.println("登录人角色：" + r.getRoleName());
			// 设置权限
			List<Function> function = roleFunctionService.findFidByRid(r);
			String hql = "";
			for (Function f : function) {
				if (f.getDescription().equals("查看所有项目的任务进度") && f.getTag().equals("showAll")) {
					hql = "from Plantail pt where pt.plan.planId in(select p.planId from Plan p where p.project.id='"
							+ id + "') and pt.finishState is not null and pt.finishState='0' and pt.caseCount <> '0' order by pt.plan.version,pt.plan.startTime asc";
					break;
				} else if (f.getDescription().equals("查看自己参与项目的任务进度") && f.getTag().equals("showMy")) {
					hql = "from Plantail pt where pt.plan.planId in(select pt.plan.planId from Plantail pt where pt.user.userId='"
							+ u.getUserId() + "' and pt.plan.planId in(select p.planId from Plan p where p.project.id='"
							+ id + "') ) and pt.finishState is not null and pt.finishState='0' and pt.caseCount <> '0' order by pt.plan.version,pt.plan.startTime asc";
					break;
				}
			}
			if (!hql.equals("")) {
				List<PlantailForm> ptfs = new ArrayList<PlantailForm>();
				this.pageBean = pageService.queryForPage(hql, 15, page);
				List<String> list = new ArrayList<String>();
				for (Object plant : pageBean.getList()) {
					PlantailForm ptf = new PlantailForm();
					// 根据计划id获取测试内容
					String name = getUserAndCasestore(((Plantail) plant).getPlan().getPlanId(),
							((Plantail) plant).getUser().getUserId());
					Plantail plantail = plantailService.findByPidAndUid(((Plantail) plant).getPlan().getPlanId(),
							((Plantail) plant).getUser().getUserId());
					Plan plan = planService.findById(plantail.getPlan().getPlanId());
					Project project = projectService.findById(plan.getProject().getId());
					plan.setProject(project);
					Casetype casetype = casetypeService.findById(plan.getCasetype().getId());
					plan.setCasetype(casetype);
					plantail.setPlan(plan);
					plantail.setUser(userService.findUserById(plantail.getUser().getUserId()));
					// 任务状态
					if (plantail.getFinishDegree() == null || plantail.getFinishDegree() == ""
							|| plantail.getFinishDegree().equals("")) {
						plantail.setFinishDegree("0.0%");
						plantail.setState("未分配");
					} else if (plantail.getFinishDegree() == "0.0%" || plantail.getFinishDegree().equals("0.0%")) {
						plantail.setState("未进行");
					} else if (plantail.getFinishDegree() == "100%" || plantail.getFinishDegree().equals("100%")) {
						plantail.setState("已完成");
					} else {
						plantail.setState("进行中");
					}
					// 已执行条数
					if (plantail.getFinishCase() == "" || plantail.getFinishCase() == null) {
						plantail.setFinishCase("0");
					}
					ptf.setPlantail(plantail);
					ptf.setCasestoreName(name);
					// 问题用例条数
					List<Caseresult> caseresults = caseresultService.findByPidAndUid(
							((Plantail) plant).getPlan().getPlanId(), ((Plantail) plant).getUser().getUserId());
					int issueCount = 0;// 问题用例数量
					for (Caseresult cr : caseresults) {
						if (cr.getResult() == null || cr.getResult().equals("") || cr.getResult() == "") {
						} else if (cr.getResult().equalsIgnoreCase("fail") || cr.getResult().equalsIgnoreCase("nt")
								|| cr.getResult().equalsIgnoreCase("na")
								|| cr.getResult().equalsIgnoreCase("tcIssue")) {
							issueCount = issueCount + 1;
						}
					}
					ptf.setIssueCount(issueCount + "");
					ptfs.add(ptf);
				}
				pageBean.setList(ptfs);
				message = "";
			} else {
				pageBean.setList(null);
				message = "你没有操作项目的权限，请联系管理员开通";
			}
			return "showTakSchedule";
		} catch (

		Exception e)

		{
			addExceptionLog(e, "异常");
			return "error";
		}

	}

	/**
	 * 根据计划id查找对应的测试内容信息放入list集合里面
	 * 
	 * @param id
	 *            计划id
	 * @param list
	 *            list集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getUserAndCasestore(int id, int userId) {
		List<PlanCasestore> pcs = hqlService.findByHql("from PlanCasestore pc where pc.plan.planId='" + id + "'");
		String casestoreName = "";
		for (PlanCasestore pc : pcs) {
			String str[] = pc.getTester().split(",");
			for (int i = 0; i < str.length; i++) {
				if (str[i].equals(userId + "")) {
					Casestore ct = casestoreService.findById(pc.getCasestore().getCasestoreId());
					casestoreName = casestoreName + ct.getTestModule() + ",";
					break;
				}
				// set.add(pc.getPlan().getPlanId() + "," + str[i].trim());
			}
		}

		return casestoreName;
	}

	/**
	 * 显示历史任务
	 * 
	 * @return
	 */
	public String showHistoryTask() {
		try {
			// 获取登录人的信息
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Role r = (Role) session.getAttribute("role");
			System.out.println("登录人姓名：" + u.getName());
			System.out.println("登录人角色：" + r.getRoleName());
			// 设置权限
			List<Function> function = roleFunctionService.findFidByRid(r);
			String hql = "";
			for (Function f : function) {
				if (f.getDescription().equals("查看所有项目的任务进度") && f.getTag().equals("showAll")) {
					hql = "from Plantail pt where pt.plan.planId in(select p.planId from Plan p where p.project.id='"
							+ id + "') and pt.finishState is not null and pt.finishState='1' and pt.caseCount <> '0' order by pt.plan.version,pt.plan.startTime asc";
					break;
				} else if (f.getDescription().equals("查看自己参与项目的任务进度") && f.getTag().equals("showMy")) {
					hql = "from Plantail pt where pt.plan.planId in(select pt.plan.planId from Plantail pt where pt.user.userId='"
							+ u.getUserId() + "' and pt.plan.planId in(select p.planId from Plan p where p.project.id='"
							+ id + "') ) and pt.finishState is not null and pt.finishState='1' and pt.caseCount <> '0' order by pt.plan.version,pt.plan.startTime asc";
					break;
				}
			}
			if (!hql.equals("")) {
				List<PlantailForm> ptfs = new ArrayList<PlantailForm>();
				this.pageBean = pageService.queryForPage(hql, 15, page);
				List<String> list = new ArrayList<String>();
				for (Object plant : pageBean.getList()) {
					PlantailForm ptf = new PlantailForm();
					// 根据计划id获取测试内容
					String name = getUserAndCasestore(((Plantail) plant).getPlan().getPlanId(),
							((Plantail) plant).getUser().getUserId());
					Plantail plantail = plantailService.findByPidAndUid(((Plantail) plant).getPlan().getPlanId(),
							((Plantail) plant).getUser().getUserId());
					Plan plan = planService.findById(plantail.getPlan().getPlanId());
					Project project = projectService.findById(plan.getProject().getId());
					plan.setProject(project);
					Casetype casetype = casetypeService.findById(plan.getCasetype().getId());
					plan.setCasetype(casetype);
					plantail.setPlan(plan);
					plantail.setUser(userService.findUserById(plantail.getUser().getUserId()));
					// 任务状态
					if (plantail.getFinishDegree() == null || plantail.getFinishDegree() == ""
							|| plantail.getFinishDegree().equals("")) {
						plantail.setFinishDegree("0.0%");
						plantail.setState("未分配");
					} else if (plantail.getFinishDegree() == "0.0%" || plantail.getFinishDegree().equals("0.0%")) {
						plantail.setState("未进行");
					} else if (plantail.getFinishDegree() == "100%" || plantail.getFinishDegree().equals("100%")) {
						plantail.setState("已完成");
					} else {
						plantail.setState("进行中");
					}
					// 已执行条数
					if (plantail.getFinishCase() == "" || plantail.getFinishCase() == null) {
						plantail.setFinishCase("0");
					}
					ptf.setPlantail(plantail);
					ptf.setCasestoreName(name);
					// 问题用例条数
					List<Caseresult> caseresults = caseresultService.findByPidAndUid(
							((Plantail) plant).getPlan().getPlanId(), ((Plantail) plant).getUser().getUserId());
					int issueCount = 0;// 问题用例数量
					for (Caseresult cr : caseresults) {
						if (cr.getResult() == null || cr.getResult().equals("") || cr.getResult() == "") {
						} else if (cr.getResult().equalsIgnoreCase("fail") || cr.getResult().equalsIgnoreCase("nt")
								|| cr.getResult().equalsIgnoreCase("na")
								|| cr.getResult().equalsIgnoreCase("tcIssue")) {
							issueCount = issueCount + 1;
						}
					}
					ptf.setIssueCount(issueCount + "");
					ptfs.add(ptf);
				}
				pageBean.setList(ptfs);
				message = "";
			} else {
				pageBean.setList(null);
				message = "你没有操作项目的权限，请联系管理员开通";
			}
			return "showHistoryTask";
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}

	}

	/**
	 * 显示测试结果
	 * 
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public String showTestResult() throws ParseException {
		try {
			// 查找登录人对应的跟踪id;根据跟踪表里的完成度判断任务是否执行。如果执行，则显示
			System.out.println("%%%%%%%%%%%%" + userId);
			final String hql = "from Plantail pt where pt.plan.planId='" + id + "' and pt.user.userId ='" + userId
					+ "'order by pt.plan.project.projectName,pt.plan.version asc";
			if (!hql.equals("")) {
				this.pageBean = pageService.queryForPage(hql, 20, page);
				List<ShowResultForm> srfs = new ArrayList<ShowResultForm>();
				List<String> list = new ArrayList<String>();
				for (Object line : pageBean.getList()) {

					// 根据计划id查找对应的测试内容信息
					List<PlanCasestore> pcs = hqlService.findByHql("from PlanCasestore pc where pc.plan.planId='"
							+ ((Plantail) line).getPlan().getPlanId() + "'");
					HashSet<String> set = new HashSet<String>();
					for (PlanCasestore pc : pcs) {
						String str[] = pc.getTester().split(",");
						for (int i = 0; i < str.length; i++) {
							set.add(pc.getPlan().getPlanId() + "," + str[i].trim());
						}
					}

					// 根据计划id查找对应的测试内容信息，并获取其对应的计划id，测试人员及测试内容，并存放在list列表里
					for (Iterator<String> it = set.iterator(); it.hasNext();) {
						String casestoreId = "";
						String planId = "";
						String testerId = it.next().split(",")[1];
						for (PlanCasestore pc : pcs) {
							planId = pc.getPlan().getPlanId() + "";
							String str[] = pc.getTester().split(",");
							for (int i = 0; i < str.length; i++) {
								// System.out.println(str[i]);
								// System.out.println(userId);
								if (str[i].trim().equals(testerId.toString())) {
									casestoreId += casestoreService.findById(pc.getCasestore().getCasestoreId())
											.getTestModule() + ",";
									break;
								}
							}
						}
						if (testerId.equals(userId + "")) {
							list.add(planId + "-" + casestoreId + "-" + testerId);
							System.out.println(planId + "-" + casestoreId + "-" + testerId);
						}

					}
				}
				// 遍历list里的信息，根据对应的计划id，测试人员及测试内容查找对应的跟踪信息
				for (String line : list) {
					int totalCount = 0;// 需执行的用例总条数
					int passCount = 0;// pass个数
					int failCount = 0;// fail个数
					int ntCount = 0;// ntCount个数
					int naCount = 0;// naCount个数
					int tcIssueCount = 0;// naCount个数
					ShowResultForm srf = new ShowResultForm();
					String str[] = line.split("-");
					Plantail plantail = plantailService.findByPidAndUid(Integer.parseInt(str[0]),
							Integer.parseInt(str[2]));
					// 根据计划id和用户id查找所有的测试结果项，并统计pass、fail、nt、na和tcIssueCount的个数
					List<Caseresult> caseresults = caseresultService.findByPidAndUid(Integer.parseInt(str[0]),
							Integer.parseInt(str[2]));
					for (Caseresult cr : caseresults) {
						totalCount++;
						System.out.println("-" + cr.getResult() + "-");
						if (cr.getResult() == null || cr.getResult().equals("") || cr.getResult() == "") {
						} else if (cr.getResult().equalsIgnoreCase("pass")) {
							passCount = passCount + 1;
						} else if (cr.getResult().equalsIgnoreCase("fail")) {
							failCount = failCount + 1;
						} else if (cr.getResult().equalsIgnoreCase("nt")) {
							ntCount = ntCount + 1;
						} else if (cr.getResult().equalsIgnoreCase("na")) {
							naCount = naCount + 1;
						} else {
							tcIssueCount = tcIssueCount + 1;
						}
					}
					Plan plan = planService.findById(plantail.getPlan().getPlanId());
					Project project = projectService.findById(plan.getProject().getId());
					plan.setProject(project);
					Casetype casetype = casetypeService.findById(plan.getCasetype().getId());
					plan.setCasetype(casetype);
					plantail.setPlan(plan);
					plantail.setUser(userService.findUserById(plantail.getUser().getUserId()));
					if (plantail.getFinishDegree() != null) {

						srf.setPlan(plan);
						srf.setPlantail(plantail);
						srf.setCasestoreName(str[1]);
						srf.setTotalCount(totalCount + "");
						srf.setPass(passCount + "");
						srf.setFail(failCount + "");
						srf.setNaCount(naCount + "");
						srf.setNtCount(ntCount + "");
						srf.setTcIssueCount(tcIssueCount + "");
						srf.setFinishCase(passCount + failCount + naCount + ntCount + tcIssueCount + "");
						srfs.add(srf);
					}
				}
				pageBean.setList(srfs);
				message = "";
			} else {
				pageBean.setList(null);
				message = "该测试内容还未分配出去，暂时没有测试结果信息";
			}
		} catch (

		Exception e)

		{
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showTestResult";

	}

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 显示每条用例结果
	 * 
	 * @return
	 */
	public String showCaseResult() {
		try {
			System.out.println("计划id：" + planId);
			final String hql = "from Caseresult cr where cr.plan.planId='" + planId + "' and cr.user.userId ='" + userId
					+ "' and cr.result not in('" + null + "','" + "" + "') order by cr.cases.caseId asc";
			if (!hql.equals("")) {
				this.pageBean = pageService.queryForPage(hql, 10, page);
				List<Caseresult> crs = new ArrayList<Caseresult>();
				System.out.println("&&&&&&&&&&&&&&");
				for (Object obj : pageBean.getList()) {
					if (((Caseresult) obj).getResult().equalsIgnoreCase("pass")) {
						pageBean.setList(null);
						message = "已经测完的用例中没有出现问题用例";
					} else {
						((Caseresult) obj).setCases(caseService.findById(((Caseresult) obj).getCases().getCaseId()));
						((Caseresult) obj).setPlan(planService.findById(planId));
						crs.add((Caseresult) obj);
					}
				}
				pageBean.setList(crs);
				message = "";
			} else {
				pageBean.setList(null);
				message = "已经测完的用例中没有出现问题用例";
			}
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showCaseResult";
	}

	private int caseId;// 用例编号

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	private Case cases = null;// 具体用例对象

	public Case getCases() {
		return cases;
	}

	public void setCases(Case cases) {
		this.cases = cases;
	}

	private Caseresult caseresult;

	public Caseresult getCaseresult() {
		return caseresult;
	}

	public void setCaseresult(Caseresult caseresult) {
		this.caseresult = caseresult;
	}

	/**
	 * 显示每条用例详情
	 * 
	 * @return
	 */
	public String showDetailCase() {
		try {
			System.out.println("计划ID:" + planId + "---用例ID:" + caseId);
			caseresult = caseResultService.findByPidAndCid(planId, caseId);
			cases = caseService.findById(caseId);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showDetailCase";
	}

	public String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String versionParam() {

		return "versionParam";
	}

	/**
	 * 显示版本结果统计
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String showVersionResult() throws IOException {
		try {
			int pass = 0;
			int fail = 0;
			int ntCount = 0;
			int naCount = 0;
			int tcIssueCount = 0;
			System.out.println("%%%%" + projectName + "**" + version);
			List<Caseresult> crs = hqlService.findByHql("from Caseresult cr where cr.plan.project.projectName='"
					+ projectName + "' and cr.plan.version='" + version + "' ");
			for (Caseresult cr : crs) {
				if (cr.getResult() == null || cr.getResult().equals("") || cr.getResult() == "") {
				} else if (cr.getResult().equalsIgnoreCase("pass")) {
					pass = pass + 1;
				} else if (cr.getResult().equalsIgnoreCase("fail")) {
					fail = fail + 1;
				} else if (cr.getResult().equalsIgnoreCase("nt")) {
					ntCount = ntCount + 1;
				} else if (cr.getResult().equalsIgnoreCase("na")) {
					naCount = naCount + 1;
				} else {
					tcIssueCount = tcIssueCount + 1;
				}
			}

			HttpServletResponse out = ServletActionContext.getResponse();
			ArrayList<String> list = new ArrayList<String>();
			list.add("Pass" + "_" + pass);
			list.add("Fail" + "_" + fail);
			list.add("NA" + "_" + naCount);
			list.add("NT" + "_" + ntCount);
			list.add("TC Issue" + "_" + tcIssueCount);
			System.out.println(pass + "");
			out.setCharacterEncoding("UTF-8");
			out.getWriter().write(list.toString());
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return null;
	}

	private String casetypeName;
	private String tester;
	private String phase;

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getTester() {
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getCasetypeName() {
		return casetypeName;
	}

	public void setCasetypeName(String casetypeName) {
		this.casetypeName = casetypeName;
	}

	private String casestoreName;

	public String getCasestoreName() {
		return casestoreName;
	}

	public void setCasestoreName(String casestoreName) {
		this.casestoreName = casestoreName;
	}

	public String moduleParam() {

		return "moduleParam";
	}

	/**
	 * 显示模块结果统计
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public String showModuleResult() throws IOException {
		try {
			int pass = 0;
			int fail = 0;
			int ntCount = 0;
			int naCount = 0;
			int tcIssueCount = 0;
			System.out.println(casetypeName);
			String casetName = new String(casetypeName.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println(casetName);
			System.out
					.println(hqlService.findByHql("from Casetype ct where ct.casetypeName='" + casetName + "'").size());
			List<Plan> plans = hqlService
					.findByHql("from Plan p where p.project.id in(select p.id from Project p where p.projectName='"
							+ projectName + "') and p.version='" + version
							+ "' and p.casetype.id in(select ct.id from Casetype ct where ct.casetypeName='" + casetName
							+ "')");
			System.out.println(plans.size());
			if (plans.size() > 0) {
				List<Caseresult> crs = hqlService.findByHql("from Caseresult cr where cr.plan.planId='"
						+ plans.get(0).getPlanId() + "' and cr.user.id in(select u.userId from User u where u.name='"
						+ new String(tester.getBytes("ISO-8859-1"), "UTF-8") + "')");
				System.out.println(crs.size());
				for (Caseresult cr : crs) {
					if (cr.getResult() == null || cr.getResult().equals("") || cr.getResult() == "") {
					} else if (cr.getResult().equalsIgnoreCase("pass")) {
						pass = pass + 1;
					} else if (cr.getResult().equalsIgnoreCase("fail")) {
						fail = fail + 1;
					} else if (cr.getResult().equalsIgnoreCase("nt")) {
						ntCount = ntCount + 1;
					} else if (cr.getResult().equalsIgnoreCase("na")) {
						naCount = naCount + 1;
					} else {
						tcIssueCount = tcIssueCount + 1;
					}
				}
				HttpServletResponse out = ServletActionContext.getResponse();
				ArrayList<String> list = new ArrayList<String>();
				System.out.println("Pass" + "_" + pass);
				System.out.println("Fail" + "_" + fail);
				list.add("Pass" + "_" + pass);
				list.add("Fail" + "_" + fail);
				list.add("NA" + "_" + naCount);
				list.add("NT" + "_" + ntCount);
				list.add("TC Issue" + "_" + tcIssueCount);
				out.setCharacterEncoding("UTF-8");
				out.getWriter().write(list.toString());
			}

		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return null;
	}

	public void addExceptionLog(Exception e, String str) {
		// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		exceptionLogService.addLogMsg(str + ":" + sw.getBuffer().toString());
	}

	private InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String fileName;

	/**
	 * 导出用例测试结果
	 * 
	 * @return
	 * @throws IOException
	 */
	public String exportCaseResult() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			WriteExcel w = new WriteExcel();
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

			String str[] = { "分类", "子模块", "测试项", "用例编号", "重要等级", "测试内容", "预置条件", "测试步骤", "预期结果", "测试结果", "建议耗时(min)",
					"备注" };
			ArrayList<String> title = new ArrayList<String>();
			for (int t = 0; t < str.length; t++) {
				title.add(str[t]);
			}
			list.add(title);
			// 1.查找
			System.out.println("计划id：" + planId);
			List<Caseresult> cr = caseResultService.findByPidAndUid(planId, u.getUserId());
			String moduleName = "";
			for (Caseresult crs : cr) {
				ArrayList<String> l = new ArrayList<String>();

				/*
				 * l.add(projectService.findById(planService.findById(crs.
				 * getPlan().getPlanId()).getProject().getId())
				 * .getProjectName()); // 项目名称
				 * l.add(planService.findById(crs.getPlan().getPlanId()).
				 * getPhase());// 测试阶段
				 * l.add(casetypeService.findById(planService.findById(crs.
				 * getPlan().getPlanId()).getCasetype().getId())
				 * .getCasetypeName());// 测试项
				 * l.add(casestoreService.findById(caseService.findById(crs.
				 * getCases().getCaseId()).getCasestoreId())
				 * .getTestModule());// 测试模块
				 */
				l.add(caseService.findById(crs.getCases().getCaseId()).getClassification());// 分类
				l.add(caseService.findById(crs.getCases().getCaseId()).getCaseName());// 子模块
				l.add(caseService.findById(crs.getCases().getCaseId()).getTestItem());// 测试项
				l.add(caseService.findById(crs.getCases().getCaseId()).getNum());// 用例编号
				l.add(caseService.findById(crs.getCases().getCaseId()).getLevel());// 重要等级
				l.add(caseService.findById(crs.getCases().getCaseId()).getDescription());// 测试内容
				l.add(caseService.findById(crs.getCases().getCaseId()).getEnvironment());// 预置条件
				l.add(caseService.findById(crs.getCases().getCaseId()).getStep());// 测试步骤
				l.add(caseService.findById(crs.getCases().getCaseId()).getExpectResult());// 预期结果
				// l.add(crs.getCases().getTestResult());// 测试结果
				l.add(crs.getResult());
				// l.add(caseService.findById(crs.getCases().getCaseId()).getAuxiliaryTool());//
				// 辅助工具
				l.add(caseService.findById(crs.getCases().getCaseId()).getAdvidceTime());// 建议耗时
				l.add(caseService.findById(crs.getCases().getCaseId()).getRemark());
				list.add(l);
				moduleName = casetypeService
						.findById(planService.findById(crs.getPlan().getPlanId()).getCasetype().getId())
						.getCasetypeName();
			}
			// FileSystemView fsv = FileSystemView.getFileSystemView();
			// System.out.println("&&&&&");
			// File f = fsv.getHomeDirectory();
			String directory = "/upload";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			File target = new File(targetDirectory);
			if (!target.exists()) {
				target.mkdirs();
			}
			String saveFilePath = target.getPath() + "\\"
					+ projectService.findById(planService.findById(planId).getProject().getId()).getProjectName() + "-"
					+ planService.findById(planId).getPhase() + "-" + moduleName + ".xls";
			System.out.println(saveFilePath);

			w.writeExcel(list, saveFilePath, moduleName);

			fileName = new String(
					(projectService.findById(planService.findById(planId).getProject().getId()).getProjectName() + "-"
							+ planService.findById(planId).getPhase() + "-" + moduleName + ".xls").getBytes("GBK"),
					"ISO-8859-1");
			fileInputStream = new FileInputStream(new File(saveFilePath));
			File file = new File(saveFilePath);
			if (file.exists()) {
				boolean bflag = file.delete();
				System.out.println("asaaa:" + file.getAbsolutePath());
				System.out.println(bflag);
			}
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "exportCaseResult";
	}

}
