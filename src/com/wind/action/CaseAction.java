package com.wind.action;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.wind.entity.Case;
import com.wind.entity.Caseresult;
import com.wind.entity.Casestore;
import com.wind.entity.Casetype;
import com.wind.entity.Customercase;
import com.wind.entity.PageBean;
import com.wind.entity.PlanCasestore;
import com.wind.entity.User;
import com.wind.from.CaseStoreFrom;
import com.wind.from.CaseTypeFrom;
import com.wind.permission.po.PermissionForm;
import com.wind.util.ReadExcel;
import com.wind.util.ServiceConfig;
import com.wind.util.WriteExcel;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CaseAction extends ServiceConfig {
	
	private static final long serialVersionUID = 1L;
	private Casestore cs;

	public Casestore getCs() {
		return cs;
	}

	public void setCs(Casestore cs) {
		this.cs = cs;
	}

	private PermissionForm permissionForm;

	public PermissionForm getPermissionForm() {
		return permissionForm;
	}

	public void setPermissionForm(PermissionForm permissionForm) {
		this.permissionForm = permissionForm;
	}

	/**
	 * �鿴���������з���
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showCaseType() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Casetype> cases = ctService.findAll();
			List<CaseTypeFrom> list = new ArrayList<CaseTypeFrom>();
			for (Casetype line : cases) {
				if (line.getCasetypeName().contains("�ͻ�����")) {

				} else {
					CaseTypeFrom c = new CaseTypeFrom();
					User u = userService.findUserById(line.getCreateId());
					User u1 = userService.findUserById(line.getUpdateId());
					c.setId(line.getId());
					c.setCreateName(u.getName());
					c.setUpdateName(u1.getName());
					c.setCasetypeName(line.getCasetypeName());
					c.setCreateTime(line.getCreateTime().split(" ")[0]);
					c.setUpdateTime(line.getUpdateTime().split(" ")[0]);
					list.add(c);
				}
			}
			setPermissionForm(rolePermissionService.getUserPermission("alluse.jsp"));
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("caseType", list);
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "showSuc";

	}

	private int id;// ���

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * ������������id����ʾ�÷����µ�����������
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showCasestore() {
		try {
			List<Casestore> list = csService.findAll();
			List<CaseStoreFrom> cs = new ArrayList<CaseStoreFrom>();
			for (Casestore line : list) {
				double totalTime = 0.0;// �ܺ�ʱ
				double adviceTime = 0.0;// ÿ��������ʱ
				double p1TotalTime = 0.0;// p1�ȼ��ܺ�ʱ
				double p2TotalTime = 0.0;// p2�ȼ��ܺ�ʱ
				double p3TotalTime = 0.0;// p3�ȼ��ܺ�ʱ
				double p4TotalTime = 0.0;// p4�ȼ��ܺ�ʱ
				int p1num = 0;// p1�ȼ���������
				int p2num = 0;// p2�ȼ���������
				int p3num = 0;// p3�ȼ���������
				int p4num = 0;// p4�ȼ���������

				if (line.getCasetypeId() == id) {
					User u = userService.findUserById(line.getCreaterId());
					User u1 = userService.findUserById(line.getUpdateId());
					CaseStoreFrom c = new CaseStoreFrom();
					c.setCaseNum(caseService.findByCasestoreId(line.getCasestoreId()).size() + "");
					c.setCasestoreId(line.getCasestoreId());
					c.setCreateName(u.getName());
					c.setUpdateName(u1.getName());
					c.setCreateTime(line.getCreateTime().split(" ")[0]);
					c.setFunctions(line.getFunctions());
					c.setRemark(line.getRemark());
					c.setTestModule(line.getTestModule());
					// �������Ԥ���ܺ�ʱ
					String hql = "from Case c where c.casestoreId='" + c.getCasestoreId() + "'";
					List<Case> cases = hqlService.findByHql(hql);
					for (Case ca : cases) {
						if (ca.getAdvidceTime() == null || ca.getAdvidceTime() == ""
								|| ca.getAdvidceTime().equals("")) {
						} else {
							adviceTime = Double.parseDouble(ca.getAdvidceTime());
						}
						totalTime = totalTime + adviceTime;
					}
					// ��С��Ϊ��λ��������λС��
					java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
					if ((df.format(totalTime / (60))).equals(".00")) {
						c.setTotalTime("0");
					} else {
						c.setTotalTime(df.format(totalTime / (60)));
					}
					// ɸѡ��P1�ȼ�����,��ȡ�������������ܺ�ʱ
					String p1level = "P1";
					final String p1hql = "from Case c where c.level='" + p1level + "' and c.casestoreId='"
							+ c.getCasestoreId() + "'";
					List<Case> p1cases = hqlService.findByHql(p1hql);
					for (Case p1ca : p1cases) {
						p1num++;
						if (p1ca.getAdvidceTime() == null || p1ca.getAdvidceTime() == ""
								|| p1ca.getAdvidceTime().equals("")) {

						} else {
							adviceTime = Double.parseDouble(p1ca.getAdvidceTime());
						}
						p1TotalTime = p1TotalTime + adviceTime;
					}
					c.setP1TotalTime(p1num + "----" + df.format(p1TotalTime / (60)));
					// ɸѡ��P2�ȼ�����,��ȡ�������������ܺ�ʱ
					String p2level = "P2";
					final String p2hql = "from Case c where c.level='" + p2level + "' and c.casestoreId='"
							+ c.getCasestoreId() + "'";
					List<Case> p2cases = hqlService.findByHql(p2hql);
					for (Case p2ca : p2cases) {
						p2num++;
						if (p2ca.getAdvidceTime() == null || p2ca.getAdvidceTime() == ""
								|| p2ca.getAdvidceTime().equals("")) {

						} else {
							adviceTime = Double.parseDouble(p2ca.getAdvidceTime());
						}
						p2TotalTime = p2TotalTime + adviceTime;
					}
					c.setP2TotalTime(p2num + "----" + df.format(p2TotalTime / (60)));

					// ɸѡ��P3�ȼ�����,��ȡ�������������ܺ�ʱ
					String p3level = "P3";
					final String p3hql = "from Case c where c.level='" + p3level + "' and c.casestoreId='"
							+ c.getCasestoreId() + "'";
					List<Case> p3cases = hqlService.findByHql(p3hql);
					for (Case p3ca : p3cases) {
						p3num++;
						if (p3ca.getAdvidceTime() == null || p3ca.getAdvidceTime() == ""
								|| p3ca.getAdvidceTime().equals("")) {

						} else {
							adviceTime = Double.parseDouble(p3ca.getAdvidceTime());
						}
						p3TotalTime = p3TotalTime + adviceTime;
					}

					c.setP3TotalTime(p3num + "----" + df.format(p3TotalTime / (60)));
					// ɸѡ��P4�ȼ�����,��ȡ�������������ܺ�ʱ
					String p4level = "P4";
					final String p4hql = "from Case c where c.level='" + p4level + "' and c.casestoreId='"
							+ c.getCasestoreId() + "'";
					List<Case> p4cases = hqlService.findByHql(p4hql);
					for (Case p4ca : p4cases) {
						p4num++;
						if (p4ca.getAdvidceTime() == null || p4ca.getAdvidceTime() == ""
								|| p4ca.getAdvidceTime().equals("")) {

						} else {
							adviceTime = Double.parseDouble(p4ca.getAdvidceTime());
						}
						p4TotalTime = p4TotalTime + adviceTime;
					}
					c.setP4TotalTime(p4num + "----" + df.format(p4TotalTime / (60)));

					c.setUpdateTime(line.getUpdateTime().split(" ")[0]);
					cs.add(c);
				}
			}
			setPermissionForm(rolePermissionService.getUserPermission("detailuse.jsp"));
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("CaseStore", cs);
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "showCaseStroeSuc";

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

	private int casestoreId;// ��������

	public int getCasestoreId() {
		return casestoreId;
	}

	public void setCasestoreId(int casestoreId) {
		this.casestoreId = casestoreId;
	}

	/**
	 * ����������id���鿴���������µ���������
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String caseDetail() {
		try {
			final String hql = "from Case where casestoreId='" + id + "' order by num asc";
			this.pageBean = pageService.queryForPage(hql, 20, page);
			setLevel("");
			Set<String> set=new HashSet<String>();
			for(Object line:pageBean.getList()){
				set.add(((Case)line).getLevel());
			}
			List<String> l=new ArrayList<String>();
			for(Iterator<String> it=set.iterator();it.hasNext();){
				l.add(it.next());
			}
			setList(l);
			// ��ȡȨ��
			setPermissionForm(rolePermissionService.getUserPermission("usecase.jsp"));
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "showCaseDetailSuc";

	}

	private String level;// ��������

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * ͨ����������ɸѡ���ü��������
	 * 
	 * @return
	 */
	public String selectCase() {
		try {
			final String hql = "from Case c where c.level='" + level + "' and c.casestoreId='" + id
					+ "' order by num asc";
			this.pageBean = pageService.queryForPage(hql, 20, page);
			setLevel(level);
			
			//System.out.println("aaaa"+level);
			// ��ȡȨ��
			setPermissionForm(rolePermissionService.getUserPermission("usecase.jsp"));
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "showCaseDetailSuc";
	}

	private List<String> list;
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * �����ж�����������������Ӧ�ķ�ҳ��ʾ����
	 * 
	 * @return
	 */
	public String selectCase2() {
		try {
			if (level == "" || level == null || level.equals("")) {
				final String hql = "from Case where casestoreId='" + id + "' order by num asc";
				this.pageBean = pageService.queryForPage(hql, 20, page);
				// ��ȡȨ��
				setPermissionForm(rolePermissionService.getUserPermission("usecase.jsp"));
				return "showCaseDetailSuc";
			} else {
				return selectCase();
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
	}

	private String casetype;// ��ȡ�������ͱ���

	public String getCasetype() {
		return casetype;
	}

	public void setCasetype(String casetype) {
		this.casetype = casetype;
	}

	/**
	 * ʵ������������͵ķ���
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String addType() throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Casetype cst = new Casetype();
			cst.setCasetypeName(casetype);
			cst.setCreateTime(sdf.format(new Date()));
			cst.setUpdateTime(sdf.format(new Date()));
			cst.setCreateId(u.getUserId());
			cst.setUpdateId(u.getUserId());
			ctService.save(cst);
			if (tag == 1) {
				return "cusPool";
			} else {
				return "addTypeSuc";
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}

	}

	private String testModule;// ��ȡ����ģ��ı���

	public String getTestModule() {
		return testModule;
	}

	public void setTestModule(String testModule) {
		this.testModule = testModule;
	}

	/**
	 * �������ģ�鷽����ͨ����������id������ģ����ӵ�ָ������������
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String addStore() throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Casestore c = new Casestore();
			System.out.println("casetype:" + id);
			c.setCasetypeId(id);
			c.setCreaterId(u.getUserId());
			c.setUpdateId(u.getUserId());
			c.setCreateTime(sdf.format(new Date()));
			c.setUpdateTime(sdf.format(new Date()));
			c.setTestModule(testModule);
			csService.save(c);
			if (tag == 1) {
				return "addcus";
			} else {
				return "addStoreSuc";
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
	}

	private Case cases = null;// ������������

	public Case getCases() {
		return cases;
	}

	public void setCases(Case cases) {
		this.cases = cases;
	}

	/**
	 * �����������������ģ���ڸ�Ŀ¼���������
	 * 
	 * @return
	 */
	public String addCase() {
		try {
			Case c = new Case();
			c.setNum(cases.getNum());
			c.setCaseName(cases.getCaseName());
			c.setAdvidceTime(cases.getAdvidceTime());
			c.setAuxiliaryTool(cases.getAuxiliaryTool());
			c.setCaseState(cases.getCaseState());
			c.setCasestoreId(id);
			c.setClassification(cases.getClassification());
			c.setDescription(cases.getDescription());
			c.setEnvironment(cases.getEnvironment());
			c.setExpectResult(cases.getExpectResult());
			c.setLevel(cases.getLevel());
			c.setTestItem(cases.getTestItem());
			c.setStep(cases.getStep());
			c.setRemark(cases.getRemark());
			caseService.save(c);
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "addCaseSuc";
	}

	public String pram() {
		return "pram";
	}

	/**
	 * ������������id��ɾ���������ͼ����µ���������
	 * 
	 * @return
	 */
	public String deleteCaseType() {

		try {
			List<Casestore> ct = csService.findByCaseTypeId(id);
			for (Casestore line : ct) {
				List<Case> cases = caseService.findByCasestoreId(line.getCasestoreId());
				for (Case l : cases) {
					caseService.delete(l.getCaseId());
				}
				csService.delete(line.getCasestoreId());
			}
			ctService.delete(id);
			if (tag == 1) {
				return "cusPool";
			} else {
				return "delTypeSuc";
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
	}

	private int typeId;// �������͵ı��

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * ����������id��ɾ���������⼰���µ���������
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteCasestore() {
		try {
			System.out.println(id);
			System.out.println(typeId);
			List<Case> cases = caseService.findByCasestoreId(id);
			System.out.println("��С��" + cases.size());
			// ɾ��������ݱ�
			for (Case line : cases) {
				List<Caseresult> crs = hqlService
						.findByHql("from Caseresult c where c.cases.caseId='" + line.getCaseId() + "'");
				for (Caseresult cr : crs) {
					caseResultService.delete(cr.getId());
				}
				caseService.delete(line.getCaseId());
			}
			List<PlanCasestore> pcs = hqlService
					.findByHql("from PlanCasestore pc where pc.casestore.casestoreId='" + id + "'");
			for (PlanCasestore pc : pcs) {
				planService.deletePlanCasestore(pc.getId());
			}
			csService.delete(id);
			if (tag == 1) {
				return "delcus";
			} else {
				return "delStoreSuc";
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
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
	 * ɾ���û���ѡ������
	 * 
	 * @return
	 */
	public String deleteCase() {
		try {
			// String id=request.getParameter("checkedIds");
			// //��ȡǰ̨��������ŵ�ѡ�еĸ�ѡ���value
			String checkedId[] = checkedIds.split(",");// ���зָ�浽����
			String temp = "";
			// System.out.println("������ID:"+typeId);
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					// System.out.println(checkedId[i]);
					temp = checkedId[i]; // �������
					// System.out.println("��ţ�"+temp);
					caseService.delete(Integer.parseInt(temp));
				}
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}

		return "delSuc";
	}

	private int caseId;// �������

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	/**
	 * �޸�����ǰ�Ļ���
	 * 
	 * @return
	 */
	public String updateCasePre() {
		cases = caseService.findById(caseId);
		return "updCasePre";
	}

	private int tag;// ��ʶ
	private int planId;// �ƻ����
	private Caseresult caseresult;

	public Caseresult getCaseresult() {
		return caseresult;
	}

	public void setCaseresult(Caseresult caseresult) {
		this.caseresult = caseresult;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	/**
	 * ��������Id�鿴��������ϸ
	 * 
	 * @return
	 */
	public String showCase() {

		try {
			System.out.println("");
			cases = caseService.findById(caseId);
			List<Case> casess=caseService.findByCasestoreId(typeId);
			startCaseId=casess.get(0).getCaseId();
			endCaseId=casess.get(casess.size()-1).getCaseId();
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "showCase";
	}

	public String showTestCase() {
		try {
			User user = getUserSession();
			caseresult = caseResultService.findByPidAndCid(planId, caseId);
			cases = caseService.findById(caseId);
			List<Caseresult> caseresults = caseResultService.findByPidAndUid(planId, user.getUserId());
			startCaseId = caseresults.get(0).getCases().getCaseId();
			endCaseId = caseresults.get(caseresults.size() - 1).getCases().getCaseId();
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "showCase";
	}

	private int endCaseId;
	private int startCaseId;

	public int getEndCaseId() {
		return endCaseId;
	}

	public void setEndCaseId(int endCaseId) {
		this.endCaseId = endCaseId;
	}

	public int getStartCaseId() {
		return startCaseId;
	}

	public void setStartCaseId(int startCaseId) {
		this.startCaseId = startCaseId;
	}

	/**
	 * �����һ������
	 * 
	 * @return
	 */
	public String nextCase() {
		try {
			System.out.println(typeId);
			System.out.println(caseId);
			List<Case> casess = caseService.findByCasestoreId(typeId);
			for (int i = 0; i < casess.size(); i++) {
				endCaseId = casess.get(casess.size() - 1).getCaseId();
				if (casess.get(i).getCaseId() == caseId) {
					if (i != casess.size() - 1) {
						int cid = casess.get(i + 1).getCaseId();
						cases = caseService.findById(cid);
						break;
					}

				}
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "testSuc";

	}
	
	/**
	 * �����һ����������
	 * 
	 * @return
	 */
	public String nextTestCase() {
		try {
			User user = getUserSession();
			System.out.println(planId);
			System.out.println(caseId);
			List<Caseresult> caseresults = caseResultService.findByPidAndUid(planId, user.getUserId());
			for (int i = 0; i < caseresults.size(); i++) {
				endCaseId = caseresults.get(caseresults.size() - 1).getCases().getCaseId();
				if (caseresults.get(i).getCases().getCaseId() == caseId) {
					if (i != caseresults.size() - 1) {
						int cid = caseresults.get(i + 1).getCases().getCaseId();
						cases = caseService.findById(cid);
						caseresult = caseResultService.findByPidAndCid(planId, cid);
						break;
					}

				}
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "testSuc";

	}

	/**
	 * �����һ������
	 * 
	 * @return
	 */
	public String upCase() {
		try {
			
			List<Case> casess = caseService.findByCasestoreId(typeId);
			for (int i = 0; i < casess.size(); i++) {
				startCaseId = casess.get(0).getCaseId();
				if (casess.get(i).getCaseId() == caseId) {
					if (i != 0) {
						int cid = casess.get(i - 1).getCaseId();
						cases = caseService.findById(cid);
						break;
					}

				}
			}
			
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "testSuc";
	}
	
	/**
	 * �����һ����������
	 * 
	 * @return
	 */
	public String upTestCase() {
		try {
			User user = getUserSession();
			List<Caseresult> caseresults = caseResultService.findByPidAndUid(planId, user.getUserId());
			for (int i = 0; i < caseresults.size(); i++) {
				startCaseId = caseresults.get(0).getCases().getCaseId();
				if (caseresults.get(i).getCases().getCaseId() == caseId) {
					if (i != 0) {
						int cid = caseresults.get(i - 1).getCases().getCaseId();
						cases = caseService.findById(cid);
						caseresult = caseResultService.findByPidAndCid(planId, cid);
						break;
					}

				}
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "testSuc";
	}

	public User getUserSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		return u;
	}

	/**
	 * �޸�����
	 * 
	 * @return
	 */
	public String updateCase() {
		try {
			Case c = caseService.findById(cases.getCaseId());
			c.setCaseName(cases.getCaseName());
			c.setAdvidceTime(cases.getAdvidceTime());
			c.setAuxiliaryTool(cases.getAuxiliaryTool());
			c.setClassification(cases.getClassification());
			c.setDescription(cases.getDescription());
			c.setEnvironment(cases.getEnvironment());
			c.setExpectResult(cases.getExpectResult());
			c.setLevel(cases.getLevel());
			c.setStep(cases.getStep());
			c.setTestItem(cases.getTestItem());
			caseService.update(c);
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "updateCase";
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * �޸�����������
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String updateCasestore() throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Casestore cs = csService.findById(id);
			cs.setTestModule(content);
			cs.setUpdateId(u.getUserId());
			cs.setUpdateTime(sdf.format(new Date()));
			csService.update(cs);
			if (tag == 1) {
				return "delcus";
			} else {
				return "updateCasestore";
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
	}

	/**
	 * �޸�����������
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String updateCaseType() throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			System.out.println(id);
			System.out.println(content);
			Casetype ct = ctService.findById(id);
			ct.setUpdateId(u.getUserId());
			ct.setUpdateTime(sdf.format(new Date()));
			ct.setCasetypeName(content);
			ctService.update(ct);
			if (tag == 1) {
				return "cusPool";
			} else {
				return "updateCaseType";
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
	}

	/**
	 * ��������
	 * 
	 * @return
	 * @throws IOException
	 */
	public String exportCase() throws IOException {
		try {
			WriteExcel w = new WriteExcel();

			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

			String str[] = { "����", "��ģ��", "������", "�������", "��Ҫ�ȼ�", "��������", "Ԥ������", "���Բ���", "Ԥ�ڽ��", "���Խ��", "��ע",
					"�����ʱ(min)" };
			ArrayList<String> title = new ArrayList<String>();
			for (int t = 0; t < str.length; t++) {
				title.add(str[t]);
			}
			list.add(title);

			List<Case> cs = caseService.findByCasestoreId(typeId);
			/*
			 * String checkedId[] = checkedIds.split(",");// ���зָ�浽���� String
			 * temp = "";
			 */
			for (Case cases : cs) {
				ArrayList<String> l = new ArrayList<String>();
				// cases = caseService.findById(Integer.parseInt(temp));
				l.add(cases.getClassification());// ����
				l.add(cases.getCaseName());// ��ģ��
				l.add(cases.getTestItem());// ������
				l.add(cases.getNum());// �������
				l.add(cases.getLevel());// ��Ҫ�ȼ�
				l.add(cases.getDescription());// ��������
				l.add(cases.getEnvironment());// Ԥ������
				l.add(cases.getStep());// ���Բ���
				l.add(cases.getExpectResult());// Ԥ�ڽ��
				l.add(cases.getTestResult());// ���Խ��
				l.add(cases.getRemark());// ��ע
				l.add(cases.getAdvidceTime());// �����ʱ
				list.add(l);

			}

			String directory = "/upload";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			File target = new File(targetDirectory);
			// FileSystemView fsv = FileSystemView.getFileSystemView();

			// File f = fsv.getHomeDirectory();
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String saveFilePath = target.getPath() + "\\" + csService.findById(typeId).getTestModule() + ".xls";
			System.out.println(saveFilePath);

			w.writeExcel(list, saveFilePath, csService.findById(typeId).getTestModule());
			fileName = new String((csService.findById(typeId).getTestModule() + ".xls").getBytes("GBK"), "ISO-8859-1");
			fileInputStream = new FileInputStream(new File(saveFilePath));
			File file = new File(saveFilePath);
			if (file.exists()) {
				boolean bflag = file.delete();
				System.out.println("asaaa:" + file.getAbsolutePath());
				System.out.println(bflag);
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "downloadSuc";
	}

	private File file;
	private String fileFileName;
	private String fileContentType;
	private int line;
	private int rowNum;

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

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * ��������
	 * 
	 * @return
	 */
	public String importCase() {
		try {
			System.out.println("�������ţ�" + typeId);
			int casestoreId = typeId;
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

			ArrayList<Case> list_case = new ArrayList<Case>();
			if (target.getAbsolutePath().endsWith("xlsx")) {

				list_case = ReadExcel.write2007Case(ReadExcel.read2007Excel(target, line), rowNum);
			} else {
				list_case = ReadExcel.write2003Case(ReadExcel.readExcel(target, line), rowNum);
			}
			System.out.println(list_case.size());
			if (list_case.size() > 0) {
				for (Case c : list_case) {
					if (caseService.findByNum(c.getNum()).size() == 0) {
						System.out.println(c.getNum());
						c.setCasestoreId(typeId);// ����������������
						caseService.save(c);
					} else {
						Case cas = caseService.findByNum(c.getNum()).get(0);
						cas.setClassification(c.getClassification());
						cas.setCaseName(c.getCaseName());
						cas.setTestItem(c.getTestItem());
						cas.setAdvidceTime(c.getAdvidceTime());
						cas.setRemark(c.getRemark());
						cas.setLevel(c.getLevel());
						cas.setExpectResult(c.getExpectResult());
						cas.setStep(c.getStep());
						cas.setEnvironment(c.getEnvironment());
						cas.setDescription(c.getDescription());
						cas.setTestResult(c.getTestResult());
						caseService.update(cas);
					}
				}
				// JOptionPane.showMessageDialog(null, "�������");
			} else {
				// JOptionPane.showMessageDialog(null, "����ʧ�ܣ���ѡ����ȷ���ļ���ʽ");
			}
		} catch (HeadlessException e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}

		return "delSuc";
	}

	/**
	 * ���ƻ�������1����
	 */

	@SuppressWarnings("unchecked")
	public String showCusCasePool() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Casetype> cases = ctService.findAll();
			List<CaseTypeFrom> list = new ArrayList<CaseTypeFrom>();
			for (Casetype line : cases) {
				if (line.getCasetypeName().contains("�ͻ�����")) {
					CaseTypeFrom c = new CaseTypeFrom();
					c.setId(line.getId());
					c.setCreateName(userService.findUserById(line.getCreateId()).getName());
					c.setUpdateName(userService.findUserById(line.getUpdateId()).getName());
					c.setCasetypeName(line.getCasetypeName());
					c.setCreateTime(line.getCreateTime().split(" ")[0]);
					c.setUpdateTime(line.getUpdateTime().split(" ")[0]);
					list.add(c);
				}
			}
			setPermissionForm(rolePermissionService.getUserPermission("cusCasePool.jsp"));
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("caseType", list);
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "showCusCasePool";
	}

	/**
	 * ���ƻ�������2����
	 */
	@SuppressWarnings("unchecked")
	public String showCusCase() {
		try {
			List<Casestore> list = csService.findAll();
			List<CaseStoreFrom> cs = new ArrayList<CaseStoreFrom>();
			for (Casestore line : list) {
				if (line.getCasetypeId() == id) {
					CaseStoreFrom c = new CaseStoreFrom();
					c.setCasestoreId(line.getCasestoreId());
					c.setCreateName(userService.findUserById(line.getCreaterId()).getName());
					c.setUpdateName(userService.findUserById(line.getUpdateId()).getName());
					c.setCreateTime(line.getCreateTime().split(" ")[0]);
					c.setFunctions(line.getFunctions());
					c.setRemark(line.getRemark());
					c.setTestModule(line.getTestModule());
					c.setUpdateTime(line.getUpdateTime().split(" ")[0]);
					cs.add(c);
				}
			}
			setPermissionForm(rolePermissionService.getUserPermission("cusCase.jsp"));
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("CaseStore", cs);
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "showCusCase";
	}

	/**
	 * ���ƻ�������3����
	 */
	public String showcusCaseDetail() {
		try {
			final String hql = "from Customercase cc where cc.casestore.casestoreId='" + id + "'";
			this.pageBean = pageService.queryForPage(hql, 20, page);
			setPermissionForm(rolePermissionService.getUserPermission("cusCaseDetail.jsp"));
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}
		return "showcusCaseDetail";
	}

	private String code = null;
	private String num;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String findAllCaseType() {
		List<Casetype> caseTypes = ctService.findAll();
		name = "";
		num = "";
		for (Casetype c : caseTypes) {
			num += c.getId() + "_";
			name += c.getCasetypeName() + "_";
		}
		code = num + "|" + name + "|caseType";
		return "success";
	}

	public String findAllCasestore() {
		List<Casestore> casestores = csService.findByCaseTypeId(typeId);
		name = "";
		num = "";
		for (Casestore cs : casestores) {
			num += cs.getCasestoreId() + "_";
			name += cs.getTestModule() + "_";
		}
		code = num + "|" + name + "|caseStore";
		return "success";
	}

	public String showCaseNum() {
		num = "";
		name = "";
		List<Case> cases = caseService.findByCasestoreId(casestoreId);
		num += cases.size() + "_";
		code = num + "|" + name + "|case";
		return "success";
	}

	public String uploadCase() {
		try {
			System.out.println("�������ţ�" + id);
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
			Customercase c = new Customercase();
			c.setId(0);
			c.setCuscaseName(target.getName());
			c.setFileUrl(target.getAbsolutePath());
			c.setCasestore(csService.findById(id));
			System.out.println("�ļ�·����" + c.getFileUrl());
			caseService.uploadCase(c);
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}

		return "uploadSuc";
	}

	private InputStream fileInputStream;
	private String fileName;
	private int cuscaseId;

	public int getCuscaseId() {
		return cuscaseId;
	}

	public void setCuscaseId(int cuscaseId) {
		this.cuscaseId = cuscaseId;
	}

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

	public String downloadCase() throws UnsupportedEncodingException {
		try {
			Customercase c = caseService.findCusById(cuscaseId);
			System.out.println(c.getFileUrl());
			File file = new File(c.getFileUrl());
			System.out.println(file.getName());
			try {
				fileName = new String(c.getCuscaseName().getBytes("GBK"), "ISO-8859-1");
				fileInputStream = new FileInputStream(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			addExceptionLog(e, "�쳣");
			return "error";
		}

		return "downloadSuc";
	}

	public void addExceptionLog(Exception e, String str) {
		// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		exceptionLogService.addLogMsg(str + ":" + sw.getBuffer().toString());
	}

	/**
	 * ɾ���û���ѡ�Ŀͻ�����
	 * 
	 * @return
	 */
	public String deletecusCase() {
		// String id=request.getParameter("checkedIds");
		// //��ȡǰ̨��������ŵ�ѡ�еĸ�ѡ���value
		String checkedId[] = checkedIds.split(",");// ���зָ�浽����
		String temp = "";
		// System.out.println("������ID:"+typeId);
		for (int i = 0; i < checkedId.length; i++) {
			if (!checkedId[i].equals("")) {
				// System.out.println(checkedId[i]);
				temp = checkedId[i]; // �������
				System.out.println("��ţ�" + temp);
				caseService.deleteCusCase(Integer.parseInt(temp));
			}
		}
		return "delcusSuc";
	}

	/**
	 * ��ģ����������
	 * 
	 * @return
	 */
	public String exportModule() {

		try {
			WriteExcel w = new WriteExcel();
			String checkedId[] = checkedIds.split(",");// ���зָ�浽����
			String temp = "";
			// System.out.println("������ID:"+typeId);
			String directory = "/upload";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			File target = new File(targetDirectory);
			String saveFilePath = target.getPath() + "\\" + ctService.findById(typeId).getCasetypeName() + ".xls";
			System.out.println(saveFilePath);
			WritableWorkbook workbook = w.createWorkbook(saveFilePath);
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					// System.out.println(checkedId[i]);
					temp = checkedId[i]; // �������
					System.out.println("��ţ�" + temp);// ��������
					ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

					String str[] = { "�������", "������", "����", "��ģ��", "��Ҫ�ȼ�", "��������", "Ԥ������", "���Բ���", "Ԥ�ڽ��", "���Խ��", "��ע",
							"�����ʱ" };
					ArrayList<String> title = new ArrayList<String>();
					for (int t = 0; t < str.length; t++) {
						title.add(str[t]);
					}
					list.add(title);

					Casestore cs = csService.findById(Integer.parseInt(temp));
					List<Case> cases = caseService.findByCasestoreId(Integer.parseInt(temp));
					for (Case ca : cases) {
						ArrayList<String> l = new ArrayList<String>();
						// cases = caseService.findById(Integer.parseInt(temp));
						l.add(ca.getNum());// �������
						l.add(ca.getTestItem());// ������
						l.add(ca.getClassification());// ����
						l.add(ca.getCaseName());// ��ģ��
						l.add(ca.getLevel());// ��Ҫ�ȼ�
						l.add(ca.getDescription());// ��������
						l.add(ca.getEnvironment());// Ԥ������
						l.add(ca.getStep());// ���Բ���
						l.add(ca.getExpectResult());// Ԥ�ڽ��
						l.add(ca.getTestResult());// ���Խ��
						l.add(ca.getRemark());// ��ע
						l.add(ca.getAdvidceTime());// �����ʱ
						list.add(l);
					}
					WritableSheet sheet = w.createSheet(workbook, cs.getTestModule(), i);
					w.writeExcel(workbook, sheet, list);

				}
			}
			w.close();
			fileName = new String((ctService.findById(typeId).getCasetypeName() + ".xls").getBytes("GBK"),
					"ISO-8859-1");
			fileInputStream = new FileInputStream(new File(saveFilePath));
			File file = new File(saveFilePath);
			if (file.exists()) {
				boolean bflag = file.delete();
				System.out.println("asaaa:" + file.getAbsolutePath());
				System.out.println(bflag);
			}
		} catch (Exception e) {
			addExceptionLog(e, "��������ģ���쳣");
			return "error";
		}

		return "downloadSuc";
	}
}
