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
	 * ������ĿId���鿴����Ŀ�µ����мƻ�,����ҳ��ʾ
	 * 
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public String showPlan() throws ParseException {
		try {
			final String hql = "from Plan p where p.project.id='" + id + "' order by p.version,p.startTime asc";
			this.pageBean = pageService.queryForPage(hql, 100, page);
			getCaseMsg();// ��ȡ����������Ϣ
			System.out.println("��Ŀid��" + id);
			// ����ƻ����ڣ������ƻ����棻����ƻ������ڣ�������Ӧ�Ĳ���ģ�����
			if (pageBean.getList().size() > 0) {
				User u = getUserSession();
				List<PlanForm> planForms = new ArrayList<PlanForm>();
				for (Object line : pageBean.getList()) {
					PlanForm planForm = new PlanForm();

					((Plan) line).setProject(projectService.findById(((Plan) line).getProject().getId()));
					((Plan) line).setCasetype(caseTypeServie.findById(((Plan) line).getCasetype().getId()));
					List<PlanCasestore> planCasestores = getCasestore((Plan) line);
					// �жϲ��������Ƿ�ɷ���
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
									pc.setTester("�ɷ���");

								}
							}
							if (tcs.size() == 0) {
								pc.setTester("�ɷ���");
							}

						}

					}

					planForm.setPlan((Plan) line);
					planForm.setPlanCasestores(planCasestores);
					// ͨ���ƻ�״̬�жϼƻ��������������������δ���С������С���ɡ��ӳ٣�
					if (((Plan) line).getStatus() == null || ((Plan) line).getStatus() == 0) {
						planForm.setStatus("δ����");
					} else if (((Plan) line).getStatus() == 1) {
						planForm.setStatus("������");
					} else {
						planForm.setStatus("�����");
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
			addExceptionLog(e, "��ʾ��Ŀ�ƻ��쳣");
			return "error";
		}

	}

	/**
	 * ���ݼƻ���Ż�ȡ�üƻ��еĲ�������
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
	 * ��ʾ����ģ�����
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showPolicyPlan() {
		// ���ݿͻ����ƴӲ��Կ���ѡ���Ӧ�Ŀͻ�����ģ��
		Project project = projectService.findById(id);
		// System.out.println("�ͻ����ƣ�" + project.getCustomer());
		List<Policypool> policypool = hqlService
				.findByHql("from Policypool pp where pp.policyName='" + project.getPolicyName() + "'");
		System.out.println("��С��" + policypool.size());
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

		if (project.getPolicyName().contains("�Զ���"))
			return "showAutoPolicyedit";
		return null;
	}

	/**
	 * ��ȡ����������Ϣ
	 */
	public void getCaseMsg() {
		// ��ȡ��������������
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
				double totalTime = 0.0;// �ܺ�ʱ
				double adviceTime = 0.0;// ÿ��������ʱ
				// ͳ����������
				CaseNum cn = new CaseNum();
				int n = caseService.findByCasestoreId(cs.getCasestoreId()).size();
				cn.setCaseStore(cs.getTestModule());
				cn.setNum(n);
				// System.out.println(n);
				// ͳ��Ԥ��ʱ��
				List<Case> cases = caseService.findByCasestoreId(cs.getCasestoreId());
				for (Case ca : cases) {
					if (ca.getAdvidceTime() == null || ca.getAdvidceTime() == "" || ca.getAdvidceTime().equals("")) {
					} else {
						adviceTime = Double.parseDouble(ca.getAdvidceTime());
					}
					totalTime = totalTime + adviceTime;
				}
				// ��С��Ϊ��λ��������λС��
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
	 * ���ݼƻ�id���Ըüƻ����л��Բ���
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updatePlanPre() {
		try {
			PlanForm pf = new PlanForm();
			plan = planService.findById(id);
			System.out.println("��ĿID:" + projectId);
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
			addExceptionLog(e, "�޸���Ŀ�ƻ������쳣");
			return "error";
		}

	}

	private String testContent;// ������

	public String getTestContent() {
		return testContent;
	}

	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}

	/**
	 * �����޸ģ��༭���ƻ���Ϣ
	 * 
	 * @return
	 */
	public String updatePlan() {
		try {
			Logsetting ls = addLogSetting("�޸�", planService.findById(plan.getPlanId()));
			Plan p = planService.findById(plan.getPlanId());
			getCommend(ls, plan, p, testContent);
			plan.setProject(projectService.findById(plan.getProject().getId()));
			plan.setCasetype(caseTypeServie.findByName(plan.getCasetype().getCasetypeName()));
			updateSaveCasestore(testContent, plan);
			planService.update(plan);

			return "uPlanSuc";
		} catch (Exception e) {
			addExceptionLog(e, "�޸���Ŀ�ƻ��쳣");
			return "error";
		}

	}

	/**
	 * �޸ĺͱ���ƻ��������Ĳ�������
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
						if (pcs.size() == 0) {// ����
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
			// ɾ���޸ĵ�
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
					System.out.println("��ţ�" + pc.getId());
					planService.deletePlanCasestore(pc.getId());
				}

			}
		} else {
			Casestore ct = casestoreService.findByName(testContent);
			if (ct != null) {
				List<PlanCasestore> pcs = hqlService.findByHql("from PlanCasestore pc where pc.plan.planId='"
						+ plan.getPlanId() + "' and pc.casestore.casestoreId='" + ct.getCasestoreId() + "'");
				if (pcs.size() == 0) {// ����
					PlanCasestore pc = new PlanCasestore();
					pc.setId(0);
					pc.setPlan(plan);
					pc.setCasestore(ct);
					planService.savePlanCasestore(pc);
				}
			}

			// ɾ���޸ĵ�
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

	private Plan plan;// ��ȡ�ƻ����ͱ���

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
	 * ʵ����Ӽƻ��ķ���
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
				String status = obj.getString("status").toString();// ��Ŀ�׶�
				String version = obj.getString("version").toString();// ����汾
				String test = obj.getString("test").toString();// ��������
				String modaul = obj.getString("modaul").toString();// ����ģ��
				String pushtime = obj.getString("pushtime").toString();// �汾����ʱ��
				// String poptime = obj.getString("poptime").toString();//
				// �汾����ʱ��
				String count = obj.getString("count").toString();// ��������
				String humannum = obj.getString("humannum").toString();// ����Ͷ��
				String humancount = obj.getString("humancount").toString();// ��������
				String pcount = obj.getString("pcount").toString();// ���Ի�����
				String preuse = obj.getString("preuse").toString();// ���Ի�����
				// String ptype = obj.getString("ptype").toString();// ���Ի�����
				String startime = obj.getString("startime").toString();// ��ʼʱ��
				String endtime = obj.getString("endtime").toString();// ����ʱ��
				String remark = obj.getString("remark").toString();// ��ע
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
				saveLog("���", p, test, modaul);
			}
			return "success";
		} catch (Exception e) {
			addExceptionLog(e, "�����Ŀ�ƻ��쳣");
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
	 * ɾ����ѡ�ļƻ���Ϣ
	 */
	@SuppressWarnings("unchecked")
	public String deletePlan() throws ParseException {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();
			String message = "";
			Plan p = planService.findById(id);
			if (p.getVersionPath() == null || p.getVersionPath() == "" || p.getVersionPath().equals("")) {
				Logsetting ls = addLogSetting("�h��", planService.findById(id));
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
			addExceptionLog(e, "ɾ����Ŀ�ƻ��쳣");
			return "error";
		}

	}

	private String versionName;// �汾����

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * �����༭�ƻ����޸ĺͱ��棩
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
			System.out.println("���ȣ�" + os.length);
			for (int i = 0; i < os.length; i++) {

				JSONObject obj = JSONObject.fromObject(os[i]);
				String planId = obj.getString("planId").toString();// �ƻ����
				String status = obj.getString("status").toString();// ��Ŀ�׶�
				String version = obj.getString("version").toString();// ����汾
				String test = obj.getString("test").toString();// ��������
				String modaul = obj.getString("modaul").toString();// ����ģ��
				String pushtime = obj.getString("pushtime").toString();// �汾����ʱ��
				// String poptime = obj.getString("poptime").toString();//
				// �汾����ʱ��
				String count = obj.getString("count").toString();// ��������
				String humannum = obj.getString("humannum").toString();// ����Ͷ��
				String humancount = obj.getString("humancount").toString();// ��������
				String pcount = obj.getString("pcount").toString();// ���Ի�����
				String preuse = obj.getString("preuse").toString();// ���Ի�����
				// String ptype = obj.getString("ptype").toString();// ���Ի���������
				String startime = obj.getString("startime").toString();// ��ʼʱ��
				String endtime = obj.getString("endtime").toString();// ����ʱ��
				String remark = obj.getString("remark").toString();// ��ע
				System.out.println("��Ŀ���:" + id);
				if (!planId.equals("")) {
					Plan p = planService.findById(Integer.parseInt(planId));
					Logsetting ls = addLogSetting("�޸�", p);
					String s = findCasestore(p);
					addLogSettingDetail1(ls, "testModule", "��������", test, s);
					addLogSettingDetail1(ls, "phase", "��Ŀ�׶�", status.toUpperCase(), p.getPhase());

					p.setPhase(status.toUpperCase());
					addLogSettingDetail1(ls, "version", "����汾", version, p.getVersion());
					p.setVersion(version);
					addLogSettingDetail1(ls, "caseType", "���Է���", modaul,
							caseTypeServie.findById(p.getCasetype().getId()).getCasetypeName());
					p.setCasetype(caseTypeServie.findByName(modaul));
					addLogSettingDetail1(ls, "versionReleasetime", "�汾����ʱ��", pushtime, p.getVersionReleasetime());
					p.setVersionReleasetime(pushtime);
					// p.setVersionEndtime(poptime);
					addLogSettingDetail1(ls, "caseCount", "��������", count, p.getCaseCount());
					p.setCaseCount(count);
					addLogSettingDetail1(ls, "humanCount", "����Ͷ��", humancount, p.getHumanCount());
					p.setHumanCount(humancount);
					addLogSettingDetail1(ls, "humanInput", "��������", humannum, p.getHumanInput());
					p.setHumanInput(humannum);
					addLogSettingDetail1(ls, "prototypeCount", "���Ի�����", pcount, p.getPrototypeCount());
					p.setPrototypeCount(pcount);
					addLogSettingDetail1(ls, "prototypeReuse", "���Ի�����", preuse, p.getPrototypeReuse());
					p.setPrototypeReuse(preuse);
					// addLogSettingDetail1(ls, "prototypeTyp", "��������", ptype,
					// p.getPrototypeType());
					// p.setPrototypeType(ptype);
					addLogSettingDetail1(ls, "startTime", "��ʼʱ��", startime, p.getStartTime());
					p.setStartTime(startime);
					addLogSettingDetail1(ls, "endTime", "����ʱ��", endtime, p.getEndTime());
					p.setEndTime(endtime);
					addLogSettingDetail1(ls, "remark", "��ע", remark, p.getRemark());
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
					saveLog("���", p, test, modaul);
				}

			}

			return "success";
		} catch (Exception e) {
			addExceptionLog(e, "�����༭�ƻ��쳣");
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
			System.out.println("�ƻ���ţ�" + planId);
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
			addExceptionLog(e, "ɾ���ƻ��쳣");
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
	 * ������Ŀid������汾�Ų�����Ӧ�ļƻ�
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String teamtask() {
		try {
			// ���ݼƻ�id�Ͱ汾���Ҷ�Ӧ�İ汾�ƻ�������ʾ
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
			// ����汾�ƻ����ڣ�����汾�ƻ��鿴���棻��֮������汾�ƻ������ڣ�����汾�ƻ��༭���棻
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
			addExceptionLog(e, "��ȡ�ƻ���Ϣ�쳣");
			return "error";
		}
	}

	/**
	 * ��ȡ�û���Session
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	/**
	 * ���û����浽�ƻ����ٱ���
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
			addExceptionLog(e, "������Ա�����쳣");
		}

	}

	/**
	 * ��ӻ�༭�ܼƻ�
	 * 
	 * @return
	 */
	public String addWeekPlan() {
		try {
			// HttpServletResponse out =ServletActionContext.getResponse();
			// ͨ��json���鴫ֵ����д�����ݿ�
			String jsonStr = ServletActionContext.getRequest().getParameter("params");
			JSONArray arr = JSONArray.fromObject(jsonStr);
			// System.out.println("################" + arr);
			Object[] os = arr.toArray();
			// System.out.println("@@@@@@@@@@@@@@" + os.length);
			for (int i = 0; i < os.length; i++) {
				JSONObject obj = JSONObject.fromObject(os[i]);
				String planId = obj.getString("planId").toString();// ��Ŀ�ƻ�id
				String versionType = obj.getString("versionType").toString();// �汾����
				// System.out.println(planId + "+++++++++" + versionType);
				String road = obj.getString("road").toString();// �汾·��
				String bug = obj.getString("bug").toString();// bug·��
				String remark = obj.getString("remark").toString();// ��ע
				if (Integer.parseInt(planId) != 0) {
					Plan p = planService.findById(Integer.parseInt(planId));
					// ����汾�ƻ������ݣ��޸��ܼƻ�
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
			addExceptionLog(e, "����ܼƻ��쳣");
			return "error";
		}

	}

	/**
	 * ����ƻ����л���
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
							pc.setTester("�ɷ���");
						}
					} else {
						pc.setTester("�ɷ���");
					}
				} else {
					List<TestCase> tcs = hqlService.findByHql("from TestCase tc where tc.casestoreId='"
							+ pc.getCasestore().getCasestoreId() + "' and tc.planId='" + plan.getPlanId() + "'");
					for (TestCase tc : tcs) {
						if (tc.getStatus() == 1) {
							pc.setTester("ather");
							break;
						} else {
							pc.setTester("�ɷ���");
						}
					}
					if (tcs.size() == 0) {
						pc.setTester("�ɷ���");
					}

				}

				if (pc.getTester().equals("�ɷ���")) {
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
			addExceptionLog(e, "�޸���Ŀ�ƻ������쳣");
			return "error";
		}
	}

	/**
	 * ��ȡ��ǰ�û���Ϣ
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
	 * ��֤�üƻ��Ƿ��Ѿ���������
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
			addExceptionLog(e, "��֤�ƻ��Ƿ񱻷����쳣");
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
	 * ������Ŀ��ŵ�������Ŀ�ƻ�
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportPlan() {
		try {
			String result = "";
			HttpServletResponse out = ServletActionContext.getResponse();
			System.out.println("��Ŀ��ţ�" + projectId);
			List<Plan> plans = planService.findPlanByProjectId(projectId);
			System.out.println(plans.size());
			if (plans.size() > 0) {
				WriteExcel w = new WriteExcel();
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
				String str[] = { "��Ŀ�׶�", "����汾", "�汾����ʱ��", "��������", "��������", "��������", "����Ͷ�루��.�죩", "��������", "���Ի�����",
						"���Ի�����", "��ʼʱ��", "����ʱ��", "��ע" };
				ArrayList<String> title = new ArrayList<String>();
				for (int t = 0; t < str.length; t++) {
					title.add(str[t]);
				}
				list.add(title);
				for (Plan plan : plans) {
					System.out.println("�ƻ���ţ�" + plan.getPlanId());
					ArrayList<String> l = new ArrayList<String>();
					// l.add(projectService.findById(projectId).getProjectName());//
					// ��Ŀ����
					l.add(plan.getPhase());// ��Ŀ�׶�
					l.add(plan.getVersion());// ����汾
					l.add(plan.getVersionReleasetime());// �汾����ʱ��
					// l.add(plan.getVersionEndtime());// �汾����ʱ��
					l.add(caseTypeServie.findById(plan.getCasetype().getId()).getCasetypeName());// ������
					List<PlanCasestore> pcs = hqlService
							.findByHql("from PlanCasestore pc where pc.plan.planId='" + plan.getPlanId() + "'");
					String testModule = "";
					for (PlanCasestore pc : pcs) {
						testModule = testModule
								+ casestoreService.findById(pc.getCasestore().getCasestoreId()).getTestModule() + ",";
					}
					l.add(testModule);// ��������
					l.add(plan.getCaseCount());// ��������
					l.add(plan.getHumanInput());// ����Ͷ��
					l.add(plan.getHumanCount());// ��������
					// l.add(plan.getHumanNeed());// ��ȱ����
					l.add(plan.getPrototypeCount());// ���Ի�����
					l.add(plan.getPrototypeReuse());// ���Ի�����
					// l.add(plan.getPrototypeType());// ��������
					l.add(plan.getStartTime());// ��ʼʱ��
					l.add(plan.getEndTime());
					// ����ʱ��
					/*
					 * l.add(plan.getVersionPath());// �汾·��
					 * l.add(plan.getVersionPlan());// �汾�ƻ�
					 * l.add(plan.getFilePath());// �鵵·��
					 * l.add(plan.getLoadTool());// ���ع���·��
					 * l.add(plan.getBugPath());// bug��·��
					 */ l.add(plan.getRemark());// ��ע
					list.add(l);
				}
				// FileSystemView fsv = FileSystemView.getFileSystemView();
				//
				// File f=fsv.getHomeDirectory();
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("yyyyMMddHHmmss");
				// System.out.println("·����"+f.getPath());

				String directory = "/upload";
				String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
				File target = new File(targetDirectory);
				String projectName = projectService.findById(projectId).getProjectName();

				// for(int i=0;i<20;i++){ w.setColumnSize(i, 15); }

				w.writeExcel2(list, target.getPath() + "\\" + projectName + "��Ŀ�ƻ�.xls", projectName + "��Ŀ�ƻ�");
				setFile(new File(target.getPath() + "\\" + projectName + "��Ŀ�ƻ�.xls"));
				result = "success";

			} else {
				result = "fail";
			}

			out.getWriter().write(result);
		} catch (Exception e) {
			addExceptionLog(e, "������Ŀ�ƻ��쳣");
			return "error";
		}

		return null;
	}

	/**
	 * �����������������ص�����
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

	private int num;// ���ڼ���sheet
	private int rowNum;// �ӵڼ��п�ʼ��
	private File file;// ѡ����ļ�
	private String fileFileName;// ѡ����ļ�����
	private String fileContentType;// ѡ����ļ�����

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
	 * ������Ŀ�ƻ�
	 * 
	 * @return
	 */
	public String importPlan() {
		try {
			String directory = "/upload";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			File target = new File(targetDirectory, fileFileName);
			// ����ļ��Ѿ����ڣ���ɾ��ԭ���ļ�
			if (target.exists()) {
				target.delete();
			}
			// ����file����ʵ���ϴ�
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
				System.out.println("������Ŀ�ƻ��ɹ�");
				return "imporPlanSuc";
			} else {
				System.out.println("������Ŀ�ƻ�ʧ��");
				System.out.println(result);
				return "importPlanFail";
			}
		} catch (Exception e) {
			addExceptionLog(e, "����ƻ��쳣");
			return "error";
		}

	}

	@SuppressWarnings("unchecked")
	public String realExcel(List<String[]> strs, int pid) {
		String result = "";

		for (String[] sstr : strs) {
			for (String sst : sstr) {
				String s[] = sst.split(":");
				if (s[0].contains("�׶�") || s[0].contains("����汾") || s[0].contains("��������") || s[0].contains("��������")) {
					if (s.length == 1) {
						result = "��Ŀ�׶λ�����汾��������������ݲ����п�";
						return result;
					}
				}

				if (s[0].contains("��������") && caseTypeServie.findByName(s[1]) == null) {

					result = s[1] + "�����ݿ������ݲ���";
					return result;

				}

				if (s[0].contains("��������")) {
					for (int i = 0; i < s[1].split(",").length; i++) {
						if (s[1].split(",")[i] != "" && casestoreService.findByName(s[1].split(",")[i]) == null) {
							result = s[1].split(",")[i] + "�����ݿ������ݲ���";
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
					if (name.contains("�׶�")) {
						p.setPhase(content);
					}
					if (name.contains("����汾")) {
						p.setVersion(content);
					}
					if (name.contains("����ʱ��")) {
						p.setVersionReleasetime(content);

					}
					if (name.contains("�汾����ʱ��")) {
						p.setVersionEndtime(content);

					}
					if (name.contains("��������")) {

						p.setCasetype(caseTypeServie.findByName(content));
					}
					if (name.contains("��������")) {
						testContent = content;
					}
					if (name.contains("����")) {
						p.setCaseCount(content);
					}
					if (name.contains("����Ͷ��")) {
						p.setHumanInput(content);
					}
					if (name.contains("����")) {
						p.setHumanCount(content);
					}

					if (name.contains("��ȱ")) {
						p.setHumanNeed(content);
					}
					if (name.contains("����")) {
						p.setPrototypeCount(content);
					}
					if (name.contains("����")) {
						p.setPrototypeReuse(content);
					}
					if (name.contains("��������")) {
						p.setPrototypeType(content);
					}
					if (name.contains("��ʼ")) {
						p.setStartTime(content);
					}
					if (name.contains("����")) {
						p.setEndTime(content);
					}
					if (name.contains("��ע")) {
						p.setRemark(content);
					}

				}
			}

			if (p.getPhase() != null && p.getVersion() != null) {
				List<Plan> plans = hqlService.findByHql("from Plan p where p.phase='" + p.getPhase()
						+ "' and p.version='" + p.getVersion() + "' and p.project.id='" + pid + "' and p.casetype.id='"
						+ p.getCasetype().getId() + "'");
				if (plans.size() > 0) {
					// ���¼ƻ�

					Plan plan = plans.get(0);
					Logsetting ls = addLogSetting("����", plan);
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
					// ��Ӽƻ�
					saveLog("���", p, testContent, caseTypeServie.findById(p.getCasetype().getId()).getCasetypeName());
					p.setProject(projectService.findById(pid));
					planService.save(p);
					if (testContent != "")
						updateSaveCasestore(testContent, p);
				}
			} else {
				result = "��ѡ����ȷ��ʽ���ļ�";
				return result;
			}

		}
		result = "success";
		return result;
	}

	public void addExceptionLog(Exception e, String str) {
		// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		exceptionLogService.addLogMsg(str + ":" + sw.getBuffer().toString());
	}

	/**
	 * ���������־�ķ���
	 * 
	 * @param type
	 *            ��������
	 * @param plan
	 *            �ƻ�
	 * @param test
	 *            ��������
	 * @param casetype
	 *            ���Է���
	 */
	public void saveLog(String type, Plan plan, String test, String casetype) {
		try {
			Logsetting ls = addLogSetting(type, plan);
			if (logSettingService.findById(ls.getId()) != null) {
				addLogSettingDetail(ls, "testModule", "��������", test, "");
				addLogSettingDetail(ls, "testType", "���Է���", casetype, "");// �����־���÷���
				getNameAndValue(ls, plan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��Ӽƻ�����������־����
	 * 
	 * @param type
	 *            ��������
	 * @return
	 */
	public Logsetting addLogSetting(String type, Plan plan) {
		User user = getUserSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Logsetting ls = new Logsetting();
		ls.setId(0);
		ls.setTableName("plan");
		ls.setName("�ƻ�����");
		ls.setType(type);
		ls.setUser(user);
		ls.setPrimyKey(plan.getPlanId());
		ls.setTime(sdf.format(new Date()));
		logSettingService.save(ls);
		return ls;
	}

	/**
	 * �����־��ϸ
	 * 
	 * @param ls
	 *            ��־
	 * @param name
	 *            �ֶ���
	 * @param text
	 *            �ֶ���˼
	 * @param content1
	 *            ���ֶ�����
	 * @param content2
	 *            ���ֶ�����
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
	 * ��ȡ��Ӧ�ֶε���˼
	 * 
	 * @param name
	 * @return
	 */
	public String getCommend(String name) {
		String content = "";
		switch (name.toLowerCase()) {
		case "phase":
			content = "��Ŀ�׶�";
			break;
		case "version":
			content = "����汾";
			break;
		case "versionreleasetime":
			content = "�汾����ʱ��";
			break;
		case "casecount":
			content = "��������";
			break;
		case "humaninput":
			content = "����Ͷ��";
			break;
		case "humancount":
			content = "��������";
			break;
		case "prototypecount":
			content = "���Ի�����";
			break;
		case "prototypereuse":
			content = "���Ի�����";
			break;
		case "prototypetype":
			content = "��������";
			break;
		case "starttime":
			content = "��ʼʱ��";
			break;
		case "endtime":
			content = "����ʱ��";
			break;
		case "remark":
			content = "��ע";
			break;
		default:
		}
		return content;
	}

	/**
	 * 
	 * @param ls
	 * @param plan
	 *            �¼ƻ�
	 * @param plan1
	 *            �ɼƻ�
	 * @return
	 */
	public List<String> getCommend(Logsetting ls, Plan plan, Plan plan1, String testModule) {
		try {
			if (plan1 == null) {// �h���ƻ�
				addLogSettingDetail(ls, "testModule", "��������", "", findCasestore(plan));
				Field[] field = plan.getClass().getDeclaredFields();
				// String
				// str[]={"��Ŀ�׶�","����汾","�汾����ʱ��","��������","��������","��������","����Ͷ��","��������","���Ի�����","���Ի�����","��������","��ʼʱ��","����ʱ��","��ע"};
				for (int j = 0; j < field.length; j++) { // ������������
					String name = field[j].getName(); // ��ȡ���Ե�����

					// System.out.println("attribute name:"+name);
					name = name.substring(0, 1).toUpperCase() + name.substring(1); // �����Ե����ַ���д�����㹹��get��set����
					String type1 = field[j].getGenericType().toString(); // ��ȡ���Ե�����
					if (name.equalsIgnoreCase("casetype")) {
						addLogSettingDetail(ls, name, "���Է���", "",
								caseTypeServie.findById(plan.getCasetype().getId()).getCasetypeName());
					}
					if (type1.equals("class java.lang.String")) { // ���type�������ͣ���ǰ�����"class
																	// "�����������
						Method m = plan.getClass().getMethod("get" + name);
						String value = (String) m.invoke(plan); // ����getter������ȡ����ֵ
						if (value != null) {
							addLogSettingDetail(ls, name, getCommend(name), "", value);
						}
					}
				}

			} else {// �޸ļƻ�

				String s = findCasestore(plan1);
				addLogSettingDetail1(ls, "testModule", "��������", testModule, s);
				addLogSettingDetail1(ls, "phase", "��Ŀ�׶�", plan.getPhase(), plan1.getPhase());

				addLogSettingDetail1(ls, "version", "����汾", plan.getVersion(), plan1.getVersion());

				addLogSettingDetail1(ls, "caseType", "���Է���", plan.getCasetype().getCasetypeName(),
						caseTypeServie.findById(plan1.getCasetype().getId()).getCasetypeName());
				addLogSettingDetail1(ls, "versionReleasetime", "�汾����ʱ��", plan.getVersionReleasetime(),
						plan1.getVersionReleasetime());
				addLogSettingDetail1(ls, "caseCount", "��������", plan.getCaseCount(), plan1.getCaseCount());
				addLogSettingDetail1(ls, "humanCount", "����Ͷ��", plan.getHumanCount(), plan1.getHumanCount());
				addLogSettingDetail1(ls, "humanInput", "��������", plan.getHumanInput(), plan1.getHumanInput());
				addLogSettingDetail1(ls, "prototypeCount", "���Ի�����", plan.getPrototypeCount(),
						plan1.getPrototypeCount());
				addLogSettingDetail1(ls, "prototypeReuse", "���Ի�����", plan.getPrototypeReuse(),
						plan1.getPrototypeReuse());
				// addLogSettingDetail1(ls, "prototypeTyp", "��������",
				// plan.getPrototypeType(), plan1.getPrototypeType());
				addLogSettingDetail1(ls, "startTime", "��ʼʱ��", plan.getStartTime(), plan1.getStartTime());
				addLogSettingDetail1(ls, "endTime", "����ʱ��", plan.getEndTime(), plan1.getEndTime());
				addLogSettingDetail1(ls, "remark", "��ע", plan.getRemark(), plan1.getRemark());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * ��ȡ�ƻ����ֶ����ƣ���ӵ���־��ϸ��
	 * 
	 * @param ls
	 *            ��־����
	 * @param plan
	 *            �ƻ�
	 */
	public void getNameAndValue(Logsetting ls, Plan plan) {
		try {
			Field[] field = plan.getClass().getDeclaredFields();
			// String
			// str[]={"��Ŀ�׶�","����汾","�汾����ʱ��","��������","��������","��������","����Ͷ��","��������","���Ի�����","���Ի�����","��������","��ʼʱ��","����ʱ��","��ע"};
			for (int j = 0; j < field.length; j++) { // ������������
				String name = field[j].getName(); // ��ȡ���Ե�����

				// System.out.println("attribute name:"+name);
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // �����Ե����ַ���д�����㹹��get��set����
				String type1 = field[j].getGenericType().toString(); // ��ȡ���Ե�����
				if (type1.equals("class java.lang.String")) { // ���type�������ͣ���ǰ�����"class
																// "�����������
					Method m = plan.getClass().getMethod("get" + name);
					String value = (String) m.invoke(plan); // ����getter������ȡ����ֵ
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
	 * ���ݼƻ���Ż�ȡ�üƻ����еĲ�������
	 * 
	 * @param plan
	 *            �ƻ�
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
