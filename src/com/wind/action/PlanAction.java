package com.wind.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opensymphony.xwork2.ActionContext;
import com.wind.entity.Case;
import com.wind.entity.Caseresult;
import com.wind.entity.Casestore;
import com.wind.entity.Casetype;
import com.wind.entity.Logsetting;
import com.wind.entity.Logsettingdetail;
import com.wind.entity.PageBean;
import com.wind.entity.Plan;
import com.wind.entity.PlanCasestore;
import com.wind.entity.Plantail;
import com.wind.entity.Policy;
import com.wind.entity.Policypool;
import com.wind.entity.Project;
import com.wind.entity.TestCase;
import com.wind.entity.User;
import com.wind.from.CaseNum;
import com.wind.from.PlanForm;
import com.wind.from.ShowCaseForm;
import com.wind.permission.po.PermissionForm;
import com.wind.util.ReadExcel;
import com.wind.util.ServiceConfig;
import com.wind.util.WriteExcel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/plan")
public class PlanAction extends ServiceConfig {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Plan> plans;

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	@SuppressWarnings("unchecked")
	public String showAllPlan() {
		plans = planService.findAll();
		Map requestList = (Map) ActionContext.getContext().getSession();
		requestList.put("plans", plans);
		return "showAllPlan";
	}

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private PageBean pageBean;

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

	private List<Policy> policy;

	public List<Policy> getPolicy() {
		return policy;
	}

	public void setPolicy(List<Policy> policy) {
		this.policy = policy;
	}

	private List<ShowCaseForm> showCaseForms;
	private PermissionForm pf;

	public PermissionForm getPf() {
		return pf;
	}

	public void setPf(PermissionForm pf) {
		this.pf = pf;
	}

	public List<ShowCaseForm> getShowCaseForms() {
		return showCaseForms;
	}

	public void setShowCaseForms(List<ShowCaseForm> showCaseForms) {
		this.showCaseForms = showCaseForms;
	}

	/**
	 * 根据项目Id，查看该项目下的所有计划,并分页显示
	 * 
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public String showPlan() throws ParseException {
		try {
			final String hql = "from Plan p where p.project.id='" + id + "' order by p.version,p.startTime asc";
			this.pageBean = pageService.queryForPage(hql, 100, page);
			getCaseMsg();// 获取用例级联信息
			System.out.println("项目id：" + id);
			// 如果计划存在，弹出计划界面；如果计划不存在，弹出对应的策略模板界面
			if (pageBean.getList().size() > 0) {
				User u = getUserSession();
				List<PlanForm> planForms = new ArrayList<PlanForm>();
				for (Object line : pageBean.getList()) {
					PlanForm planForm = new PlanForm();

					((Plan) line).setProject(projectService.findById(((Plan) line).getProject().getId()));
					((Plan) line).setCasetype(caseTypeServie.findById(((Plan) line).getCasetype().getId()));
					List<PlanCasestore> planCasestores = getCasestore((Plan) line);
					// 判断测试内容是否可分配
					for (PlanCasestore pc : planCasestores) {
						// pc.setCasestore(casestoreService.findById(pc.getCasestore().getCasestoreId()));
						if (pc.getTester().equals(u.getUserId() + "")) {
							List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.user.userId='"
									+ u.getUserId() + "' and tc.casestoreId='" + pc.getCasestore().getCasestoreId()
									+ "' and tc.planId='" + ((Plan) line).getPlanId() + "'");
							if (tcs.size() > 0) {
								if (tcs.get(0).getStatus() == 1) {
									pc.setTester("my");
								}
							}
						} else {
							List<TestCase> tcs = hqlService.findByHql(
									"from TestCase tc where tc.casestoreId='" + pc.getCasestore().getCasestoreId()
											+ "' and tc.planId='" + ((Plan) line).getPlanId() + "'");
							for (TestCase tc : tcs) {
								if (tc.getStatus() == 1) {
									pc.setTester("ather");
									break;
								} else {
									pc.setTester("可分配");

								}
							}
							if (tcs.size() == 0) {
								pc.setTester("可分配");
							}

						}

					}

					planForm.setPlan((Plan) line);
					planForm.setPlanCasestores(planCasestores);
					// 通过计划状态判断计划的完成情况（四种情况：未进行、进行中、完成、延迟）
					if (((Plan) line).getStatus() == null || ((Plan) line).getStatus() == 0) {
						planForm.setStatus("未进行");
					} else if (((Plan) line).getStatus() == 1) {
						planForm.setStatus("进行中");
					} else {
						planForm.setStatus("已完成");
					}
					planForms.add(planForm);
				}
				pageBean.setList(planForms);
				setPf(rolePermissionService.getUserPermission("p_plan.jsp"));
				return "showPlanSuc";
			} else {
				return showPolicyPlan();
			}
		} catch (Exception e) {
			addExceptionLog(e, "显示项目计划异常");
			return "error";
		}

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

	private String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * 显示策略模板界面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showPolicyPlan() {
		// 根据客户名称从策略库中选择对应的客户策略模板
		Project project = projectService.findById(id);
		// System.out.println("客户名称：" + project.getCustomer());
		List<Policypool> policypool = hqlService
				.findByHql("from Policypool pp where pp.policyName='" + project.getPolicyName() + "'");
		System.out.println("大小：" + policypool.size());
		for (Policypool pp : policypool) {
			// System.out.println("" + pp.getPolicyName());
			policy = hqlService.findByHql("from Policy policy where policy.policypool.id='" + pp.getId() + "'");

		}
		projectName = project.getProjectName();
		for (Policy p : policy) {
			p.setCasetype(caseTypeServie.findById(p.getCasetype().getId()));
			p.setPolicypool(policypoolService.findById(p.getPolicypool().getId()));
		}
		if (project.getPolicyName().contains("3T"))
			return "show3TPolicyedit";

		if (project.getPolicyName().contains("IPD"))
			return "showIPDPolicyedit";

		if (project.getPolicyName().contains("自定义"))
			return "showAutoPolicyedit";
		return null;
	}

	/**
	 * 获取用例级联信息
	 */
	public void getCaseMsg() {
		// 获取级联操作的数据
		List<Casetype> caseTypes = caseTypeServie.findAll();
		List<ShowCaseForm> scfs = new ArrayList<ShowCaseForm>();
		for (Casetype ct : caseTypes) {
			ShowCaseForm scf = new ShowCaseForm();
			scf.setCaseType(ct.getCasetypeName());
			scf.setId(ct.getId());
			// System.out.println(ct.getCasetypeName());
			List<Casestore> caseStores = casestoreService.findByCaseTypeId(ct.getId());
			List<CaseNum> caseNums = new ArrayList<CaseNum>();
			for (Casestore cs : caseStores) {
				// System.out.println(cs.getTestModule());
				double totalTime = 0.0;// 总耗时
				double adviceTime = 0.0;// 每条用例耗时
				// 统计用例条数
				CaseNum cn = new CaseNum();
				int n = caseService.findByCasestoreId(cs.getCasestoreId()).size();
				cn.setCaseStore(cs.getTestModule());
				cn.setNum(n);
				// System.out.println(n);
				// 统计预估时间
				List<Case> cases = caseService.findByCasestoreId(cs.getCasestoreId());
				for (Case ca : cases) {
					if (ca.getAdvidceTime() == null || ca.getAdvidceTime() == "" || ca.getAdvidceTime().equals("")) {
					} else {
						adviceTime = Double.parseDouble(ca.getAdvidceTime());
					}
					totalTime = totalTime + adviceTime;
				}
				// 以小数为单位，保留两位小数
				java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
				if ((df.format(totalTime / (60))).equals(".00")) {
					cn.setCaseTime("0");
				} else {
					cn.setCaseTime(df.format(totalTime / (60 * 8)));
				}
				caseNums.add(cn);
			}
			scf.setCaseNum(caseNums);
			scfs.add(scf);
		}
		setShowCaseForms(scfs);
	}

	private int projectId;
	private Casetype casetype;
	private PlanForm planForm;

	public PlanForm getPlanForm() {
		return planForm;
	}

	public void setPlanForm(PlanForm planForm) {
		this.planForm = planForm;
	}

	public Casetype getCasetype() {
		return casetype;
	}

	public void setCasetype(Casetype casetype) {
		this.casetype = casetype;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * 根据计划id，对该计划进行回显操作
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updatePlanPre() {
		try {
			PlanForm pf = new PlanForm();
			plan = planService.findById(id);
			System.out.println("项目ID:" + projectId);
			plan.setProject(projectService.findById(plan.getProject().getId()));
			plan.setCasetype(caseTypeServie.findById(plan.getCasetype().getId()));
			pf.setPlan(plan);
			List<PlanCasestore> pcs = hqlService
					.findByHql("from PlanCasestore pc where pc.plan.planId='" + plan.getPlanId() + "'");
			for (PlanCasestore pc : pcs) {
				pc.setPlan(plan);
				pc.setCasestore(casestoreService.findById(pc.getCasestore().getCasestoreId()));
			}
			pf.setPlanCasestores(pcs);
			getCaseMsg();
			setPlanForm(pf);
			return "uPlanPre";
		} catch (Exception e) {
			addExceptionLog(e, "修改项目计划回显异常");
			return "error";
		}

	}

	private String testContent;// 测试项

	public String getTestContent() {
		return testContent;
	}

	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}

	/**
	 * 单项修改（编辑）计划信息
	 * 
	 * @return
	 */
	public String updatePlan() {
		try {
			Logsetting ls = addLogSetting("修改", planService.findById(plan.getPlanId()));
			Plan p = planService.findById(plan.getPlanId());
			getCommend(ls, plan, p, testContent);
			plan.setProject(projectService.findById(plan.getProject().getId()));
			plan.setCasetype(caseTypeServie.findByName(plan.getCasetype().getCasetypeName()));
			updateSaveCasestore(testContent, plan);
			planService.update(plan);

			return "uPlanSuc";
		} catch (Exception e) {
			addExceptionLog(e, "修改项目计划异常");
			return "error";
		}

	}

	/**
	 * 修改和保存计划所关联的测试内容
	 */
	@SuppressWarnings("unchecked")
	public void updateSaveCasestore(String testContent, Plan plan) {
		if (testContent.contains(",")) {
			String str[] = testContent.split(",");
			for (int i = 0; i < str.length; i++) {
				if (str[i] != null && str[i] != "") {
					Casestore ct = casestoreService.findByName(str[i]);
					if (ct != null) {
						List<PlanCasestore> pcs = hqlService.findByHql("from PlanCasestore pc where pc.plan.planId='"
								+ plan.getPlanId() + "' and pc.casestore.casestoreId='" + ct.getCasestoreId() + "'");
						if (pcs.size() == 0) {// 增加
							PlanCasestore pc = new PlanCasestore();
							pc.setId(0);
							pc.setPlan(plan);
							pc.setCasestore(ct);
							pc.setTester(getUserSession().getUserId() + ",");
							planService.savePlanCasestore(pc);
						}
					}

				}

			}
			// 删除修改的
			List<PlanCasestore> pcs = hqlService
					.findByHql("from PlanCasestore pc where pc.plan.planId='" + plan.getPlanId() + "'");
			for (PlanCasestore pc : pcs) {
				int i = 0;
				for (int j = 0; j < str.length; j++) {
					Casestore ct = casestoreService.findByName(str[j]);
					if (ct != null) {
						if (pc.getCasestore().getCasestoreId() == ct.getCasestoreId()) {
							i = 1;
							break;
						}
					}
				}
				if (i == 0) {
					System.out.println("编号：" + pc.getId());
					planService.deletePlanCasestore(pc.getId());
				}

			}
		} else {
			Casestore ct = casestoreService.findByName(testContent);
			if (ct != null) {
				List<PlanCasestore> pcs = hqlService.findByHql("from PlanCasestore pc where pc.plan.planId='"
						+ plan.getPlanId() + "' and pc.casestore.casestoreId='" + ct.getCasestoreId() + "'");
				if (pcs.size() == 0) {// 增加
					PlanCasestore pc = new PlanCasestore();
					pc.setId(0);
					pc.setPlan(plan);
					pc.setCasestore(ct);
					planService.savePlanCasestore(pc);
				}
			}

			// 删除修改的
			List<PlanCasestore> pcs = hqlService
					.findByHql("from PlanCasestore pc where pc.plan.planId='" + plan.getPlanId() + "'");
			for (PlanCasestore pc : pcs) {
				if (ct != null) {
					if (pc.getCasestore().getCasestoreId() != ct.getCasestoreId()) {
						planService.deletePlanCasestore(pc.getId());
					}
				}

			}
		}

	}

	private Plan plan;// 获取计划类型变量

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	/**
	 * 实现添加计划的方法
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String addPlan() {
		try {
			String jsonStr = ServletActionContext.getRequest().getParameter("params");
			JSONArray arr = JSONArray.fromObject(jsonStr);
			Object[] os = arr.toArray();
			for (int i = 0; i < os.length; i++) {
				JSONObject obj = JSONObject.fromObject(os[i]);
				String status = obj.getString("status").toString();// 项目阶段
				String version = obj.getString("version").toString();// 软件版本
				String test = obj.getString("test").toString();// 测试内容
				String modaul = obj.getString("modaul").toString();// 测试模块
				String pushtime = obj.getString("pushtime").toString();// 版本发布时间
				// String poptime = obj.getString("poptime").toString();//
				// 版本结束时间
				String count = obj.getString("count").toString();// 用例条数
				String humannum = obj.getString("humannum").toString();// 人力投入
				String humancount = obj.getString("humancount").toString();// 人数安排
				String pcount = obj.getString("pcount").toString();// 测试机需求
				String preuse = obj.getString("preuse").toString();// 测试机复用
				// String ptype = obj.getString("ptype").toString();// 测试机复用
				String startime = obj.getString("startime").toString();// 开始时间
				String endtime = obj.getString("endtime").toString();// 结束时间
				String remark = obj.getString("remark").toString();// 备注
				Plan p = new Plan();
				p.setPlanId(0);
				p.setProject(projectService.findById(id));
				p.setPhase(status);
				p.setVersion(version);
				p.setCaseCount(count);
				p.setCasetype(caseTypeServie.findByName(modaul));
				if (!pushtime.equals("") || pushtime != null) {
					p.setVersionReleasetime(pushtime);
				}
				// p.setVersionEndtime(poptime);
				p.setHumanCount(humancount);
				p.setHumanInput(humannum);
				p.setPrototypeCount(pcount);
				p.setPrototypeReuse(preuse);
				// p.setPrototypeType(ptype);
				p.setStartTime(startime);
				p.setEndTime(endtime);
				p.setRemark(remark);
				p.setStatus(0);
				planService.save(p);
				updateSaveCasestore(test, p);
				SavePlantail(p, "0");
				saveLog("添加", p, test, modaul);
			}
			return "success";
		} catch (Exception e) {
			addExceptionLog(e, "添加项目计划异常");
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
	 * 删除所选的计划信息
	 */
	@SuppressWarnings("unchecked")
	public String deletePlan() throws ParseException {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();
			String message = "";
			Plan p = planService.findById(id);
			if (p.getVersionPath() == null || p.getVersionPath() == "" || p.getVersionPath().equals("")) {
				Logsetting ls = addLogSetting("刪除", planService.findById(id));
				getCommend(ls, planService.findById(id), null, "");
				List<Plantail> plantail = plantailService.findByPlanId(id);
				for (Plantail line : plantail) {
					plantailService.delete(line.getId());
				}
				List<Caseresult> caseresult = caseresultService.findByPlanId(id);
				for (Caseresult line : caseresult) {
					caseresultService.delete(line.getId());
				}
				List<PlanCasestore> pcs = hqlService
						.findByHql("from PlanCasestore pc where pc.plan.planId='" + id + "'");
				for (PlanCasestore pc : pcs) {
					planService.deletePlanCasestore(pc.getId());
				}
				planService.delete(id);
				message = "success";

			} else {
				message = "fail";
			}
			out.getWriter().write(message);
			return null;
		} catch (Exception e) {
			addExceptionLog(e, "删除项目计划异常");
			return "error";
		}

	}

	private String versionName;// 版本名称

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * 批量编辑计划（修改和保存）
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String editPlan() {
		try {
			String jsonStr = ServletActionContext.getRequest().getParameter("params");
			System.out.println(jsonStr);
			JSONArray arr = JSONArray.fromObject(jsonStr);
			System.out.println(arr);
			Object[] os = arr.toArray();
			System.out.println("长度：" + os.length);
			for (int i = 0; i < os.length; i++) {

				JSONObject obj = JSONObject.fromObject(os[i]);
				String planId = obj.getString("planId").toString();// 计划编号
				String status = obj.getString("status").toString();// 项目阶段
				String version = obj.getString("version").toString();// 软件版本
				String test = obj.getString("test").toString();// 测试内容
				String modaul = obj.getString("modaul").toString();// 测试模块
				String pushtime = obj.getString("pushtime").toString();// 版本发布时间
				// String poptime = obj.getString("poptime").toString();//
				// 版本结束时间
				String count = obj.getString("count").toString();// 用例条数
				String humannum = obj.getString("humannum").toString();// 人力投入
				String humancount = obj.getString("humancount").toString();// 人数安排
				String pcount = obj.getString("pcount").toString();// 测试机需求
				String preuse = obj.getString("preuse").toString();// 测试机复用
				// String ptype = obj.getString("ptype").toString();// 测试机复用类型
				String startime = obj.getString("startime").toString();// 开始时间
				String endtime = obj.getString("endtime").toString();// 结束时间
				String remark = obj.getString("remark").toString();// 备注
				System.out.println("项目编号:" + id);
				if (!planId.equals("")) {
					Plan p = planService.findById(Integer.parseInt(planId));
					Logsetting ls = addLogSetting("修改", p);
					String s = findCasestore(p);
					addLogSettingDetail1(ls, "testModule", "测试内容", test, s);
					addLogSettingDetail1(ls, "phase", "项目阶段", status.toUpperCase(), p.getPhase());

					p.setPhase(status.toUpperCase());
					addLogSettingDetail1(ls, "version", "软件版本", version, p.getVersion());
					p.setVersion(version);
					addLogSettingDetail1(ls, "caseType", "测试分类", modaul,
							caseTypeServie.findById(p.getCasetype().getId()).getCasetypeName());
					p.setCasetype(caseTypeServie.findByName(modaul));
					addLogSettingDetail1(ls, "versionReleasetime", "版本发布时间", pushtime, p.getVersionReleasetime());
					p.setVersionReleasetime(pushtime);
					// p.setVersionEndtime(poptime);
					addLogSettingDetail1(ls, "caseCount", "用例条数", count, p.getCaseCount());
					p.setCaseCount(count);
					addLogSettingDetail1(ls, "humanCount", "人力投入", humancount, p.getHumanCount());
					p.setHumanCount(humancount);
					addLogSettingDetail1(ls, "humanInput", "人数安排", humannum, p.getHumanInput());
					p.setHumanInput(humannum);
					addLogSettingDetail1(ls, "prototypeCount", "测试机需求", pcount, p.getPrototypeCount());
					p.setPrototypeCount(pcount);
					addLogSettingDetail1(ls, "prototypeReuse", "测试机复用", preuse, p.getPrototypeReuse());
					p.setPrototypeReuse(preuse);
					// addLogSettingDetail1(ls, "prototypeTyp", "复用类型", ptype,
					// p.getPrototypeType());
					// p.setPrototypeType(ptype);
					addLogSettingDetail1(ls, "startTime", "开始时间", startime, p.getStartTime());
					p.setStartTime(startime);
					addLogSettingDetail1(ls, "endTime", "结束时间", endtime, p.getEndTime());
					p.setEndTime(endtime);
					addLogSettingDetail1(ls, "remark", "备注", remark, p.getRemark());
					p.setRemark(remark);
					planService.update(p);
					updateSaveCasestore(test, p);
				} else {
					Plan p = new Plan();
					p.setPlanId(0);
					p.setProject(projectService.findById(id));
					p.setPhase(status.toUpperCase());
					p.setVersion(version);
					p.setCaseCount(count);
					p.setCasetype(caseTypeServie.findByName(modaul));
					p.setVersionReleasetime(pushtime);
					// p.setVersionEndtime(poptime);
					p.setHumanCount(humancount);
					p.setHumanInput(humannum);
					p.setPrototypeCount(pcount);
					p.setPrototypeReuse(preuse);
					// p.setPrototypeType(ptype);
					p.setStartTime(startime);
					p.setEndTime(endtime);
					p.setRemark(remark);
					planService.save(p);
					SavePlantail(p, "0");
					updateSaveCasestore(test, p);
					saveLog("添加", p, test, modaul);
				}

			}

			return "success";
		} catch (Exception e) {
			addExceptionLog(e, "批量编辑计划异常");
			return "error";
		}

	}

	private int planId;

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	@SuppressWarnings("unchecked")
	public String delPlan() {

		try {
			String message = "";
			HttpServletResponse out = ServletActionContext.getResponse();
			System.out.println("计划编号：" + planId);
			if (planId != 0) {
				Plan p = planService.findById(planId);
				System.out.println(p.getVersionPath());
				if (p.getVersionPath() == null || p.getVersionPath() == "" || p.getVersionPath().equals("")) {
					List<PlanCasestore> pcs = hqlService
							.findByHql("from PlanCasestore pc where pc.plan.planId='" + planId + "'");
					System.out.println(pcs.size());
					for (PlanCasestore pc : pcs) {
						planService.deletePlanCasestore(pc.getId());
					}
					List<Plantail> pts = plantailService.findByPlanId(planId);
					for (Plantail pt : pts) {
						plantailService.delete(pt.getId());
					}

					planService.delete(planId);
					if (planService.findById(planId) == null) {
						message = "success";
					}

				} else {
					message = "fail";
				}
			}
			System.out.println(message);
			out.getWriter().write(message);
			return null;
		} catch (Exception e) {
			addExceptionLog(e, "删除计划异常");
			return "error";
		}

	}

	private List<PlanForm> planForms;

	public List<PlanForm> getPlanForms() {
		return planForms;
	}

	public void setPlanForms(List<PlanForm> planForms) {
		this.planForms = planForms;
	}

	/**
	 * 根据项目id和软件版本号查找相应的计划
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String teamtask() {
		try {
			// 根据计划id和版本查找对应的版本计划，并显示
			plans = planService.findByPidAndVersionName(id, versionName);
			List<PlanForm> pfs = new ArrayList<PlanForm>();
			PlanForm pf = null;
			for (Plan plan : plans) {
				pf = new PlanForm();
				List<PlanCasestore> planCasestores = hqlService
						.findByHql("from PlanCasestore pc where pc.plan.planId='" + plan.getPlanId() + "'");
				for (PlanCasestore pc : planCasestores) {
					pc.setCasestore(casestoreService.findById(pc.getCasestore().getCasestoreId()));
				}
				pf.setPlanCasestores(planCasestores);
				plan.setCasetype(caseTypeServie.findById(plan.getCasetype().getId()));
				plan.setProject(projectService.findById(plan.getProject().getId()));
				pf.setPlan(plan);
				pfs.add(pf);
			}
			setPlanForms(pfs);
			// 如果版本计划存在，进入版本计划查看界面；反之，如果版本计划不存在，进入版本计划编辑界面；
			if (pf.getPlan().getVersionType() == null || pf.getPlan().getVersionType() == ""
					|| pf.getPlan().getVersionPath() == null || pf.getPlan().getVersionPath() == ""
					|| pf.getPlan().getBugPath() == null || pf.getPlan().getBugPath() == ""
					|| pf.getPlan().getRemark() == null || pf.getPlan().getRemark() == "") {

				return "teamtask";

			} else {
				return "teamtask2";
			}

		} catch (Exception e)

		{
			addExceptionLog(e, "获取计划信息异常");
			return "error";
		}
	}

	/**
	 * 获取用户的Session
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	/**
	 * 将用户保存到计划跟踪表中
	 */
	@SuppressWarnings("unchecked")
	public void SavePlantail(Plan p, String state) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			HttpSession session = getSession();
			User user = (User) session.getAttribute("user");
			Plantail pt = new Plantail();
			pt.setId(0);
			pt.setPlan(p);
			pt.setUser(user);
			pt.setState(state);
			pt.setCaseCount(p.getCaseCount());
			pt.setStartTime(p.getStartTime());
			pt.setEndTime(p.getEndTime());
			pt.setReceiverTime(sdf.format(new Date()));
			Plantail plantail = plantailService.findByPidAndUid(p.getPlanId(), user.getUserId());
			if (plantail == null) {
				plantailService.save(pt);

			} else {
				plantail.setCaseCount(p.getCaseCount());
				plantail.setStartTime(p.getStartTime());
				plantail.setEndTime(p.getEndTime());
				plantail.setReceiverTime(sdf.format(new Date()));
				plantailService.update(plantail);
			}
			
			if(state.equals("0")){
				List<PlanCasestore> pcs = hqlService
						.findByHql("from PlanCasestore pc where pc.plan.planId='" + p.getPlanId() + "'");
				for (PlanCasestore pc : pcs) {
					pc.setTester(user.getUserId() + ",");
					planService.updatePlanCasestore(pc);
				}
			}
			
		} catch (Exception e) {
			addExceptionLog(e, "保存人员跟踪异常");
		}

	}

	/**
	 * 添加或编辑周计划
	 * 
	 * @return
	 */
	public String addWeekPlan() {
		try {
			// HttpServletResponse out =ServletActionContext.getResponse();
			// 通过json数组传值，并写入数据库
			String jsonStr = ServletActionContext.getRequest().getParameter("params");
			JSONArray arr = JSONArray.fromObject(jsonStr);
			// System.out.println("################" + arr);
			Object[] os = arr.toArray();
			// System.out.println("@@@@@@@@@@@@@@" + os.length);
			for (int i = 0; i < os.length; i++) {
				JSONObject obj = JSONObject.fromObject(os[i]);
				String planId = obj.getString("planId").toString();// 项目计划id
				String versionType = obj.getString("versionType").toString();// 版本类型
				// System.out.println(planId + "+++++++++" + versionType);
				String road = obj.getString("road").toString();// 版本路径
				String bug = obj.getString("bug").toString();// bug路径
				String remark = obj.getString("remark").toString();// 备注
				if (Integer.parseInt(planId) != 0) {
					Plan p = planService.findById(Integer.parseInt(planId));
					// 如果版本计划有内容，修改周计划
					/*
					 * if (p.getVersionType() != null || p.getVersionType() !=
					 * "" || p.getVersionPath() != null || p.getVersionPath() !=
					 * "" || p.getBugPath() != null || p.getBugPath() != "" ||
					 * p.getRemark() != null || p.getRemark() != "") { if
					 * (caseresultService.findByPlanId(p.getPlanId()).size() ==
					 * 0) { p.setVersionType(versionType); p.setBugPath(bug);
					 * p.setRemark(remark); p.setVersionPath(road);
					 * SavePlantail(p, "1"); p.setStatus(1);
					 * planService.update(p); }
					 * 
					 * } else {
					 */
					p.setVersionType(versionType);
					p.setBugPath(bug);
					p.setRemark(remark);
					p.setVersionPath(road);
					if (bug != null && bug != "") {
						SavePlantail(p, "1");
						p.setStatus(1);
					}
					planService.update(p);
					// }
					// out.getWriter().write("success");
				}

			}
			return "success";
		} catch (Exception e) {
			addExceptionLog(e, "添加周计划异常");
			return "error";
		}

	}

	/**
	 * 分配计划进行回显
	 */
	@SuppressWarnings("unchecked")
	public String showDeliverPre() {
		try {
			PlanForm pf = new PlanForm();
			plan = planService.findById(id);
			plan.setProject(projectService.findById(plan.getProject().getId()));
			plan.setCasetype(caseTypeServie.findById(plan.getCasetype().getId()));

			List<PlanCasestore> pcs = getCasestore(plan);
			List<PlanCasestore> pccs = new ArrayList<PlanCasestore>();
			User u = getUserSession();
			int caseNum = 0;
			for (PlanCasestore pc : pcs) {
				if (pc.getTester().equals(u.getUserId() + "")) {
					List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.user.userId='" + u.getUserId()
							+ "' and tc.casestoreId='" + pc.getCasestore().getCasestoreId() + "' and tc.planId='"
							+ plan.getPlanId() + "'");
					if (tcs.size() > 0) {
						if (tcs.get(0).getStatus() == 1) {
							pc.setTester("my");
						} else {
							pc.setTester("可分配");
						}
					} else {
						pc.setTester("可分配");
					}
				} else {
					List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.casestoreId='"
							+ pc.getCasestore().getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");
					for (TestCase tc : tcs) {
						if (tc.getStatus() == 1) {
							pc.setTester("ather");
							break;
						} else {
							pc.setTester("可分配");
						}
					}
					if (tcs.size() == 0) {
						pc.setTester("可分配");
					}

				}

				if (pc.getTester().equals("可分配")) {
					caseNum = caseNum + caseService.findByCasestoreId(pc.getCasestore().getCasestoreId()).size();
					pccs.add(pc);
					System.out.println(pc.getCasestore().getTestModule() + ":" + caseNum);
				}
			}
			plan.setCaseCount(caseNum + "");
			pf.setPlan(plan);
			pf.setPlanCasestores(pccs);
			getCaseMsg();
			setPlanForm(pf);
			return "showDeliverPre";
		} catch (Exception e) {
			addExceptionLog(e, "修改项目计划回显异常");
			return "error";
		}
	}

	/**
	 * 获取当前用户信息
	 * 
	 * @return
	 */
	public User getUserSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		return u;
	}

	/**
	 * 验证该计划是否已经被分配了
	 * 
	 * @return
	 * @throws IOException
	 */
	public String valiPlan() throws IOException {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();

			String result = "";
			List<Caseresult> caseresults = caseresultService.findByPlanId(planId);
			if (caseresults.size() > 0) {
				result = "fail";
			} else {
				result = "success";
			}
			out.getWriter().write(result);
			return null;
		} catch (Exception e) {
			addExceptionLog(e, "验证计划是否被分配异常");
			return "error";
		}

	}

	private InputStream fileInputStream;
	private String fileName;

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

	/**
	 * 根据项目编号导出该项目计划
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportPlan() {
		try {
			String result = "";
			HttpServletResponse out = ServletActionContext.getResponse();
			System.out.println("项目编号：" + projectId);
			List<Plan> plans = planService.findPlanByProjectId(projectId);
			System.out.println(plans.size());
			if (plans.size() > 0) {
				WriteExcel w = new WriteExcel();
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
				String str[] = { "项目阶段", "软件版本", "版本发布时间", "测试类型", "测试内容", "用例条数", "人力投入（人.天）", "人数安排", "测试机需求",
						"测试机复用", "开始时间", "结束时间", "备注" };
				ArrayList<String> title = new ArrayList<String>();
				for (int t = 0; t < str.length; t++) {
					title.add(str[t]);
				}
				list.add(title);
				for (Plan plan : plans) {
					System.out.println("计划编号：" + plan.getPlanId());
					ArrayList<String> l = new ArrayList<String>();
					// l.add(projectService.findById(projectId).getProjectName());//
					// 项目名称
					l.add(plan.getPhase());// 项目阶段
					l.add(plan.getVersion());// 软件版本
					l.add(plan.getVersionReleasetime());// 版本发布时间
					// l.add(plan.getVersionEndtime());// 版本结束时间
					l.add(caseTypeServie.findById(plan.getCasetype().getId()).getCasetypeName());// 测试项
					List<PlanCasestore> pcs = hqlService
							.findByHql("from PlanCasestore pc where pc.plan.planId='" + plan.getPlanId() + "'");
					String testModule = "";
					for (PlanCasestore pc : pcs) {
						testModule = testModule
								+ casestoreService.findById(pc.getCasestore().getCasestoreId()).getTestModule() + ",";
					}
					l.add(testModule);// 测试内容
					l.add(plan.getCaseCount());// 用例条数
					l.add(plan.getHumanInput());// 人力投入
					l.add(plan.getHumanCount());// 人数安排
					// l.add(plan.getHumanNeed());// 需缺人力
					l.add(plan.getPrototypeCount());// 测试机需求
					l.add(plan.getPrototypeReuse());// 测试机复用
					// l.add(plan.getPrototypeType());// 复用类型
					l.add(plan.getStartTime());// 开始时间
					l.add(plan.getEndTime());
					// 结束时间
					/*
					 * l.add(plan.getVersionPath());// 版本路径
					 * l.add(plan.getVersionPlan());// 版本计划
					 * l.add(plan.getFilePath());// 归档路径
					 * l.add(plan.getLoadTool());// 下载工具路径
					 * l.add(plan.getBugPath());// bug库路径
					 */ l.add(plan.getRemark());// 备注
					list.add(l);
				}
				// FileSystemView fsv = FileSystemView.getFileSystemView();
				//
				// File f=fsv.getHomeDirectory();
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("yyyyMMddHHmmss");
				// System.out.println("路径："+f.getPath());

				String directory = "/upload";
				String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
				File target = new File(targetDirectory);
				String projectName = projectService.findById(projectId).getProjectName();

				// for(int i=0;i<20;i++){ w.setColumnSize(i, 15); }

				w.writeExcel2(list, target.getPath() + "\\" + projectName + "项目计划.xls", projectName + "项目计划");
				setFile(new File(target.getPath() + "\\" + projectName + "项目计划.xls"));
				result = "success";

			} else {
				result = "fail";
			}

			out.getWriter().write(result);
		} catch (Exception e) {
			addExceptionLog(e, "导出项目计划异常");
			return "error";
		}

		return null;
	}

	/**
	 * 导出用例后将用例下载到本地
	 * 
	 * @return
	 */
	public String downloadPlan() {
		try {
			fileName = new String(file.getName().getBytes("GBK"), "ISO-8859-1");
			fileInputStream = new FileInputStream(file);
			if (file.exists()) {
				System.out.println(file.delete());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "downloadSuc";
	}

	private int num;// 读第几个sheet
	private int rowNum;// 从第几行开始读
	private File file;// 选择的文件
	private String fileFileName;// 选择的文件名称
	private String fileContentType;// 选择的文件类型

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 导入项目计划
	 * 
	 * @return
	 */
	public String importPlan() {
		try {
			String directory = "/upload";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			File target = new File(targetDirectory, fileFileName);
			// 如果文件已经存在，则删除原有文件
			if (target.exists()) {
				target.delete();
			}
			// 复制file对象，实现上传
			try {
				FileUtils.copyFile(file, target);
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<String[]> strs = new ArrayList<String[]>();
			if (target.getAbsolutePath().endsWith("xlsx")) {

				strs = ReadExcel.write2007(ReadExcel.read2007Excel(target, num), rowNum);
			} else {
				strs = ReadExcel.write2003(ReadExcel.readExcel(target, num), rowNum);
			}
			result = realExcel(strs, projectId);
			if (result == "success") {
				System.out.println("导入项目计划成功");
				return "imporPlanSuc";
			} else {
				System.out.println("导入项目计划失败");
				System.out.println(result);
				return "importPlanFail";
			}
		} catch (Exception e) {
			addExceptionLog(e, "导入计划异常");
			return "error";
		}

	}

	@SuppressWarnings("unchecked")
	public String realExcel(List<String[]> strs, int pid) {
		String result = "";

		for (String[] sstr : strs) {
			for (String sst : sstr) {
				String s[] = sst.split(":");
				if (s[0].contains("阶段") || s[0].contains("软件版本") || s[0].contains("测试类型") || s[0].contains("测试内容")) {
					if (s.length == 1) {
						result = "项目阶段或软件版本或测试项或测试内容不能有空";
						return result;
					}
				}

				if (s[0].contains("测试类型") && caseTypeServie.findByName(s[1]) == null) {

					result = s[1] + "与数据库中数据不符";
					return result;

				}

				if (s[0].contains("测试内容")) {
					for (int i = 0; i < s[1].split(",").length; i++) {
						if (s[1].split(",")[i] != "" && casestoreService.findByName(s[1].split(",")[i]) == null) {
							result = s[1].split(",")[i] + "与数据库中数据不符";
							return result;
						}
					}

				}

			}
		}

		for (String[] str : strs) {
			String testContent = "";
			Plan p = new Plan();
			p.setPlanId(0);
			for (String s : str) {
				String name = s.split(":")[0];
				if (s.split(":").length > 1) {
					String content = s.split(":")[1];
					if (name.contains("阶段")) {
						p.setPhase(content);
					}
					if (name.contains("软件版本")) {
						p.setVersion(content);
					}
					if (name.contains("发布时间")) {
						p.setVersionReleasetime(content);

					}
					if (name.contains("版本结束时间")) {
						p.setVersionEndtime(content);

					}
					if (name.contains("测试类型")) {

						p.setCasetype(caseTypeServie.findByName(content));
					}
					if (name.contains("测试内容")) {
						testContent = content;
					}
					if (name.contains("用例")) {
						p.setCaseCount(content);
					}
					if (name.contains("人力投入")) {
						p.setHumanInput(content);
					}
					if (name.contains("人数")) {
						p.setHumanCount(content);
					}

					if (name.contains("需缺")) {
						p.setHumanNeed(content);
					}
					if (name.contains("需求")) {
						p.setPrototypeCount(content);
					}
					if (name.contains("复用")) {
						p.setPrototypeReuse(content);
					}
					if (name.contains("复用类型")) {
						p.setPrototypeType(content);
					}
					if (name.contains("开始")) {
						p.setStartTime(content);
					}
					if (name.contains("结束")) {
						p.setEndTime(content);
					}
					if (name.contains("备注")) {
						p.setRemark(content);
					}

				}
			}

			if (p.getPhase() != null && p.getVersion() != null) {
				List<Plan> plans = hqlService.findByHql("from Plan p where p.phase='" + p.getPhase()
						+ "' and p.version='" + p.getVersion() + "' and p.project.id='" + pid + "' and p.casetype.id='"
						+ p.getCasetype().getId() + "'");
				if (plans.size() > 0) {
					// 更新计划

					Plan plan = plans.get(0);
					Logsetting ls = addLogSetting("更新", plan);
					getCommend(ls, p, plan, testContent);
					plan.setVersionReleasetime(p.getVersionReleasetime());
					plan.setVersionEndtime(p.getVersionEndtime());
					plan.setCaseCount(p.getCaseCount());
					plan.setHumanInput(p.getHumanInput());
					plan.setHumanCount(p.getHumanCount());
					plan.setStartTime(p.getStartTime());
					plan.setEndTime(p.getEndTime());
					plan.setRemark(p.getRemark());
					plan.setPrototypeCount(p.getPrototypeCount());
					plan.setPrototypeReuse(p.getPrototypeReuse());
					// plan.setPrototypeType(p.getPrototypeType());
					plan.setHumanNeed(p.getHumanNeed());
					planService.update(plan);
					if (testContent != "")
						updateSaveCasestore(testContent, plan);
				} else {
					// 添加计划
					saveLog("添加", p, testContent, caseTypeServie.findById(p.getCasetype().getId()).getCasetypeName());
					p.setProject(projectService.findById(pid));
					planService.save(p);
					if (testContent != "")
						updateSaveCasestore(testContent, p);
				}
			} else {
				result = "请选择正确格式的文件";
				return result;
			}

		}
		result = "success";
		return result;
	}

	public void addExceptionLog(Exception e, String str) {
		// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		exceptionLogService.addLogMsg(str + ":" + sw.getBuffer().toString());
	}

	/**
	 * 保存操作日志的方法
	 * 
	 * @param type
	 *            操作类型
	 * @param plan
	 *            计划
	 * @param test
	 *            测试内容
	 * @param casetype
	 *            测试分类
	 */
	public void saveLog(String type, Plan plan, String test, String casetype) {
		try {
			Logsetting ls = addLogSetting(type, plan);
			if (logSettingService.findById(ls.getId()) != null) {
				addLogSettingDetail(ls, "testModule", "测试内容", test, "");
				addLogSettingDetail(ls, "testType", "测试分类", casetype, "");// 添加日志设置方法
				getNameAndValue(ls, plan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加计划管理的相关日志设置
	 * 
	 * @param type
	 *            操作类型
	 * @return
	 */
	public Logsetting addLogSetting(String type, Plan plan) {
		User user = getUserSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Logsetting ls = new Logsetting();
		ls.setId(0);
		ls.setTableName("plan");
		ls.setName("计划管理");
		ls.setType(type);
		ls.setUser(user);
		ls.setPrimyKey(plan.getPlanId());
		ls.setTime(sdf.format(new Date()));
		logSettingService.save(ls);
		return ls;
	}

	/**
	 * 添加日志明细
	 * 
	 * @param ls
	 *            日志
	 * @param name
	 *            字段名
	 * @param text
	 *            字段意思
	 * @param content1
	 *            新字段内容
	 * @param content2
	 *            旧字段内容
	 */
	public void addLogSettingDetail(Logsetting ls, String name, String text, String content1, String content2) {
		Logsettingdetail lsd = new Logsettingdetail();
		lsd.setId(0);
		lsd.setLogsetting(ls);
		lsd.setColumnName(name);
		lsd.setColumnText(text);
		lsd.setNewValue(content1);
		lsd.setOldValue(content2);
		logSettingService.saveLog(lsd);
	}

	/**
	 * 获取相应字段的意思
	 * 
	 * @param name
	 * @return
	 */
	public String getCommend(String name) {
		String content = "";
		switch (name.toLowerCase()) {
		case "phase":
			content = "项目阶段";
			break;
		case "version":
			content = "软件版本";
			break;
		case "versionreleasetime":
			content = "版本发布时间";
			break;
		case "casecount":
			content = "用例条数";
			break;
		case "humaninput":
			content = "人力投入";
			break;
		case "humancount":
			content = "人数安排";
			break;
		case "prototypecount":
			content = "测试机需求";
			break;
		case "prototypereuse":
			content = "测试机复用";
			break;
		case "prototypetype":
			content = "复用类型";
			break;
		case "starttime":
			content = "开始时间";
			break;
		case "endtime":
			content = "结束时间";
			break;
		case "remark":
			content = "备注";
			break;
		default:
		}
		return content;
	}

	/**
	 * 
	 * @param ls
	 * @param plan
	 *            新计划
	 * @param plan1
	 *            旧计划
	 * @return
	 */
	public List<String> getCommend(Logsetting ls, Plan plan, Plan plan1, String testModule) {
		try {
			if (plan1 == null) {// 刪除计划
				addLogSettingDetail(ls, "testModule", "测试内容", "", findCasestore(plan));
				Field[] field = plan.getClass().getDeclaredFields();
				// String
				// str[]={"项目阶段","软件版本","版本发布时间","测试类型","测试内容","用例条数","人力投入","人数安排","测试机需求","测试机复用","复用类型","开始时间","结束时间","备注"};
				for (int j = 0; j < field.length; j++) { // 遍历所有属性
					String name = field[j].getName(); // 获取属性的名字

					// System.out.println("attribute name:"+name);
					name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
					String type1 = field[j].getGenericType().toString(); // 获取属性的类型
					if (name.equalsIgnoreCase("casetype")) {
						addLogSettingDetail(ls, name, "测试分类", "",
								caseTypeServie.findById(plan.getCasetype().getId()).getCasetypeName());
					}
					if (type1.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
																	// "，后面跟类名
						Method m = plan.getClass().getMethod("get" + name);
						String value = (String) m.invoke(plan); // 调用getter方法获取属性值
						if (value != null) {
							addLogSettingDetail(ls, name, getCommend(name), "", value);
						}
					}
				}

			} else {// 修改计划

				String s = findCasestore(plan1);
				addLogSettingDetail1(ls, "testModule", "测试内容", testModule, s);
				addLogSettingDetail1(ls, "phase", "项目阶段", plan.getPhase(), plan1.getPhase());

				addLogSettingDetail1(ls, "version", "软件版本", plan.getVersion(), plan1.getVersion());

				addLogSettingDetail1(ls, "caseType", "测试分类", plan.getCasetype().getCasetypeName(),
						caseTypeServie.findById(plan1.getCasetype().getId()).getCasetypeName());
				addLogSettingDetail1(ls, "versionReleasetime", "版本发布时间", plan.getVersionReleasetime(),
						plan1.getVersionReleasetime());
				addLogSettingDetail1(ls, "caseCount", "用例条数", plan.getCaseCount(), plan1.getCaseCount());
				addLogSettingDetail1(ls, "humanCount", "人力投入", plan.getHumanCount(), plan1.getHumanCount());
				addLogSettingDetail1(ls, "humanInput", "人数安排", plan.getHumanInput(), plan1.getHumanInput());
				addLogSettingDetail1(ls, "prototypeCount", "测试机需求", plan.getPrototypeCount(),
						plan1.getPrototypeCount());
				addLogSettingDetail1(ls, "prototypeReuse", "测试机复用", plan.getPrototypeReuse(),
						plan1.getPrototypeReuse());
				// addLogSettingDetail1(ls, "prototypeTyp", "复用类型",
				// plan.getPrototypeType(), plan1.getPrototypeType());
				addLogSettingDetail1(ls, "startTime", "开始时间", plan.getStartTime(), plan1.getStartTime());
				addLogSettingDetail1(ls, "endTime", "结束时间", plan.getEndTime(), plan1.getEndTime());
				addLogSettingDetail1(ls, "remark", "备注", plan.getRemark(), plan1.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 获取计划的字段名称，添加到日志详细中
	 * 
	 * @param ls
	 *            日志设置
	 * @param plan
	 *            计划
	 */
	public void getNameAndValue(Logsetting ls, Plan plan) {
		try {
			Field[] field = plan.getClass().getDeclaredFields();
			// String
			// str[]={"项目阶段","软件版本","版本发布时间","测试类型","测试内容","用例条数","人力投入","人数安排","测试机需求","测试机复用","复用类型","开始时间","结束时间","备注"};
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字

				// System.out.println("attribute name:"+name);
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type1 = field[j].getGenericType().toString(); // 获取属性的类型
				if (type1.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
																// "，后面跟类名
					Method m = plan.getClass().getMethod("get" + name);
					String value = (String) m.invoke(plan); // 调用getter方法获取属性值
					if (value != null) {
						String content = getCommend(name);
						if (content != "") {
							addLogSettingDetail(ls, name, content, value, "");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据计划编号获取该计划当中的测试内容
	 * 
	 * @param plan
	 *            计划
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findCasestore(Plan plan) {
		String content = "";
		List<PlanCasestore> pcs = hqlService
				.findByHql("from PlanCasestore pc where pc.plan.planId='" + plan.getPlanId() + "'");
		if (pcs.size() > 0) {
			for (PlanCasestore pc : pcs) {
				Casestore c = casestoreService.findById(pc.getCasestore().getCasestoreId());
				content = content + c.getTestModule() + ",";
			}
		}
		return content;
	}

	public void addLogSettingDetail1(Logsetting ls, String name, String text, String content1, String content2) {
		if (!content1.equals(content2)) {
			addLogSettingDetail(ls, name, text, content1, content2);
		}
	}
}
