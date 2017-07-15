package com.wind.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.wind.entity.Case;
import com.wind.entity.Casestore;
import com.wind.entity.Casetype;
import com.wind.entity.Policy;
import com.wind.entity.Policypool;
import com.wind.entity.User;
import com.wind.from.CaseNum;
import com.wind.from.ShowCaseForm;
import com.wind.permission.po.PermissionForm;
import com.wind.util.ReadExcel;
import com.wind.util.ServiceConfig;
import com.wind.util.WriteExcel;

import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PolicyAction extends ServiceConfig {

	private static final long serialVersionUID = 1L;
	private String customerName;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * ��Ӳ��Կ�
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String addPolicyPool() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Policypool p = new Policypool();
			p.setId(0);
			p.setCreateUser(u);
			p.setCreateTime(sdf.format(new Date()));
			p.setUpdateTime(sdf.format(new Date()));
			p.setPolicyName(customerName);
			p.setUpdateUser(u);
			policypoolService.save(p);
			return "addPolicyPool";
		} catch (Exception e) {
			addExceptionLog(e, "��ӿͻ��쳣");
			return "error";
		}

	}

	private int id;// ���Կ�Id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Policypool ps;

	public Policypool getps() {
		return ps;
	}

	/**
	 * ɾ���ò��Կ⣬����ɾ�����������в���
	 * 
	 * @return
	 */
	public String delPolicyPool() {
		try {
			Policypool ppd = policypoolService.findById(id);
			System.out.println("���Կ�ID��" + id);
			if (ppd != null) {
				policypoolService.delete(id);
			}
			return "addPolicyPool";
		} catch (Exception e) {
			addExceptionLog(e, "ɾ���ͻ��쳣");
			return "error";
		}

	}

	/**
	 * ���ݲ��Կ��ţ��޸ĸò��Կ�����
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String updatePolicyPool() {
		try {
			Policypool p = policypoolService.findById(id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			p.setUpdateTime(sdf.format(new Date()));
			p.setPolicyName(customerName);
			p.setUpdateUser(u);
			policypoolService.update(p);
			return "updatePolicyPool";
		} catch (Exception e) {
			addExceptionLog(e, "ɾ���ͻ��쳣");
			return "error";
		}
	}

	private List<Policypool> policypools;

	public List<Policypool> getPolicypools() {
		return policypools;
	}

	public void setPolicypools(List<Policypool> policypools) {
		this.policypools = policypools;
	}

	private PermissionForm permissionForm;

	public PermissionForm getPermissionForm() {
		return permissionForm;
	}

	public void setPermissionForm(PermissionForm permissionForm) {
		this.permissionForm = permissionForm;
	}

	/**
	 * ��ʾ���в��Կ����Ϣ
	 * 
	 * @return
	 */
	public String showPolicyPool() {
		try {
			List<Policypool> ppos = policypoolService.findAll();
			for (Policypool ppo : ppos) {
				ppo.setCreateUser(userService.findUserById(ppo.getCreateUser().getUserId()));
				ppo.setUpdateUser(userService.findUserById(ppo.getUpdateUser().getUserId()));
			}

			setPolicypools(ppos);
			setPermissionForm(rolePermissionService.getUserPermission("client.jsp"));
			return "showSuc";
		} catch (Exception e) {
			addExceptionLog(e, "��ʾ���пͻ��쳣");
			return "error";
		}
	}

	private List<Policy> policys;

	public List<Policy> getPolicys() {
		return policys;
	}

	public void setPolicys(List<Policy> policys) {
		this.policys = policys;
	}

	private List<ShowCaseForm> showCaseForms;

	public List<ShowCaseForm> getShowCaseForms() {
		return showCaseForms;
	}

	public void setShowCaseForms(List<ShowCaseForm> showCaseForms) {
		this.showCaseForms = showCaseForms;
	}

	/**
	 * ���ݲ��Կ��ţ���ʾ���в���ģ��
	 * 
	 * @return
	 */
	public String showPolicy() {
		try {

			String hql = "from Policy p where p.policypool.id='" + id + "'order by p.version,p.startTime asc";
			policys = policyService.findAllPolicy(hql);
			for (Policy p : policys) {
				p.setCasetype(caseTypeService.findById(p.getCasetype().getId()));
				p.setPolicypool(policypoolService.findById(p.getPolicypool().getId()));
			}
			List<Casetype> caseTypes = caseTypeService.findAll();
			List<ShowCaseForm> scfs = new ArrayList<ShowCaseForm>();
			for (Casetype ct : caseTypes) {
				ShowCaseForm scf = new ShowCaseForm();
				scf.setCaseType(ct.getCasetypeName());
				// System.out.println(ct.getCasetypeName());
				List<Casestore> caseStores = casestoreService.findByCaseTypeId(ct.getId());
				List<CaseNum> caseNums = new ArrayList<CaseNum>();
				for (Casestore cs : caseStores) {
					double totalTime = 0.0;// �ܺ�ʱ
					double adviceTime = 0.0;// ÿ��������ʱ
					// System.out.println(cs.getTestModule());
					// ͳ����������
					CaseNum cn = new CaseNum();
					int n = caseService.findByCasestoreId(cs.getCasestoreId()).size();
					cn.setCaseStore(cs.getTestModule());
					cn.setNum(n);
					// ͳ��Ԥ��ʱ��
					List<Case> cases = caseService.findByCasestoreId(cs.getCasestoreId());
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
			if (policypoolService.findById(id).getPolicyName().contains("3T")) {
				setPermissionForm(rolePermissionService.getUserPermission("policy.jsp"));
				return "show3TPolicy";
			}

			if (policypoolService.findById(id).getPolicyName().contains("IPD")) {
				setPermissionForm(rolePermissionService.getUserPermission("IPD.jsp"));
				return "showIPDPolicy";
			}

			if (policypoolService.findById(id).getPolicyName().contains("�Զ���")) {
				setPermissionForm(rolePermissionService.getUserPermission("Auto.jsp"));
				return "showAutoPolicy";
			}

		} catch (Exception e) {
			addExceptionLog(e, "��ʾ�����쳣");
			return "error";
		}
		return null;
	}

	/**
	 * ��Ӳ��Թ���ʵ��
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String addPolicy() throws ParseException {
		try {
			String jsonStr = ServletActionContext.getRequest().getParameter("params");
			System.out.println(jsonStr);
			JSONArray arr = JSONArray.fromObject(jsonStr);
			System.out.println(arr);
			Object[] os = arr.toArray();
			System.out.println("���ȣ�" + os.length);
			for (int i = 0; i < os.length; i++) {

				JSONObject obj = JSONObject.fromObject(os[i]);
				String policyId = obj.getString("policyId").toString();// ���Կ���
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
				System.out.println("���Կ���:" + id);
				if (policyId.equals("") || policyId == null) {
					// ���Ӳ���
					Policy p = new Policy();
					p.setId(0);
					p.setCaseCount(count);
					p.setTestContent(test);
					p.setCasetype(caseTypeService.findByName(modaul));
					p.setEndTime(endtime);
					p.setHumanCount(humancount);
					p.setHumanInput(humannum);
					p.setPhase(status.toUpperCase());
					p.setPolicypool(policypoolService.findById(id));
					p.setPrototypeCount(pcount);
					p.setPrototypeReuse(preuse);
					p.setRemark(remark);
					p.setStartTime(startime);
					p.setVersion(version);
					p.setVersionReleasetime(pushtime);
					// p.setVersionEndtime(poptime);
					// p.setPrototypeType(ptype);
					policyService.addPolicy(p);

				} else {
					// �޸Ĳ���
					Policy p = policyService.findById(Integer.parseInt(policyId));
					p.setCaseCount(count);
					p.setTestContent(test);
					p.setCasetype(caseTypeService.findByName(modaul));
					p.setEndTime(endtime);
					p.setHumanCount(humancount);
					p.setHumanInput(humannum);
					p.setPhase(status.toUpperCase());
					p.setPolicypool(policypoolService.findById(id));
					p.setPrototypeCount(pcount);
					p.setPrototypeReuse(preuse);
					p.setRemark(remark);
					p.setStartTime(startime);
					p.setVersion(version);
					p.setVersionReleasetime(pushtime);
					// p.setVersionEndtime(poptime);
					// p.setPrototypeType(ptype);
					policyService.updatePolicy(p);
				}
			}
			return "success";
		} catch (NumberFormatException e) {
			addExceptionLog(e, "��Ӳ����쳣");
			return "error";
		}
	}

	private int policyId;// ���Ա��

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	/**
	 * ���ݲ��Ա�ţ�ɾ���ò���
	 * 
	 * @return
	 */
	public String deletePolicy() {
		String result = "";
		HttpServletResponse out = ServletActionContext.getResponse();
		try {
			if (policyId != 0) {
				policyService.deletePolicy(policyId);
				if (policyService.findById(policyId) == null) {
					System.out.println("ɾ���ɹ�");
					result = "success";
				} else {
					result = "fail";
				}
			} else {
				result = "fail";
			}
			out.getWriter().write(result);
		} catch (Exception e) {
			addExceptionLog(e, "ɾ�������쳣");
			return "error";
		}

		return null;
	}

	private int policypoolId;

	public int getPolicypoolId() {
		return policypoolId;
	}

	public void setPolicypoolId(int policypoolId) {
		this.policypoolId = policypoolId;
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
	 * ������Ŀ�ͻ��Ĳ���
	 * 
	 * @return
	 * @throws RowsExceededException
	 * @throws IOException
	 */
	public String exportPolicy() throws RowsExceededException {
		try {
			String result = "";
			HttpServletResponse out = ServletActionContext.getResponse();
			System.out.println("���Կ��ţ�" + policypoolId);
			List<Policy> policys = policyService
					.findAllPolicy("from Policy p where p.policypool.id='" + policypoolId + "'");
			if (policys.size() > 0) {
				WriteExcel w = new WriteExcel();
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
				String str[] = { "��Ŀ�׶�", "����汾", "�汾����ʱ��", "��������", "��������", "��������", "����Ͷ�루��.�죩", "��������", "���Ի�����",
						"���Ի�����", "��ʼʱ��", "����ʱ��", "��ע" };
				ArrayList<String> title = new ArrayList<String>();
				for (int t = 0; t < str.length; t++) {
					title.add(str[t]);
				}
				list.add(title);
				String policyName = policypoolService.findById(policypoolId).getPolicyName();
				for (Policy policy : policys) {
					ArrayList<String> l = new ArrayList<String>();
					// l.add(policyName);// �ͻ�����
					l.add(policy.getPhase());// ��Ŀ�׶�
					l.add(policy.getVersion());// ����汾
					l.add(policy.getVersionReleasetime());// �汾����ʱ��
					// l.add(policy.getVersionEndtime());// �汾����ʱ��
					l.add(caseTypeService.findById(policy.getCasetype().getId()).getCasetypeName());// ������
					l.add(policy.getTestContent());// ��������
					l.add(policy.getCaseCount());// ��������
					l.add(policy.getHumanInput());// ����Ͷ��
					l.add(policy.getHumanCount());// ��������
					l.add(policy.getPrototypeCount());// ���Ի�����
					l.add(policy.getPrototypeReuse());// ���Ի�����
					// l.add(policy.getPrototypeType());// ���Ի���������
					l.add(policy.getStartTime());// ��ʼʱ��
					l.add(policy.getEndTime());// ����ʱ��
					l.add(policy.getRemark());// ��ע
					list.add(l);
				}
				// FileSystemView fsv = FileSystemView.getFileSystemView();
				// File f=fsv.getHomeDirectory();
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("yyyyMMddHHmmss");
				// System.out.println("·����"+f.getPath());
				String directory = "/upload";
				String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
				File target = new File(targetDirectory);
				w.writeExcel2(list, target.getPath() + "\\" + policyName + ".xls", policyName);
				setFile(new File(target.getPath() + "\\" + policyName + ".xls"));
				result = "success";
			} else {
				result = "fail";
			}
			out.getWriter().write(result);
			return null;
		} catch (Exception e) {
			addExceptionLog(e, "���������쳣");
			return "error";
		}
	}

	/**
	 * �����������������ص�����
	 * 
	 * @return
	 */
	public String downloadPolicy() {
		try {
			fileName = new String(file.getName().getBytes("GBK"), "ISO-8859-1");
			fileInputStream = new FileInputStream(file);
			if (file.exists()) {
				System.out.println(file.delete());
			}
		} catch (IOException e) {
			addExceptionLog(e, "���ز����쳣");
			return "error";
		}
		return "downloadSuc";
	}

	private File file;
	private String fileFileName;
	private String fileContentType;
	private String result;
	private int num;
	private int rowNum;

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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * �������
	 * 
	 * @return
	 */
	public String importPolicy() {
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
			result = realExcel(strs, policypoolId);

			return "imporPolicySuc";
		} catch (Exception e) {
			addExceptionLog(e, "��������쳣");
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

				if (s[0].contains("��������") && caseTypeService.findByName(s[1]) == null) {

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
			Policy p = new Policy();
			p.setId(0);
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
					/*
					 * if (name.contains("�汾����ʱ��")) {
					 * p.setVersionEndtime(content);
					 * 
					 * }
					 */
					if (name.contains("��������")) {

						p.setCasetype(caseTypeService.findByName(content));
					}
					if (name.contains("��������")) {
						// p.setCasestore(casestoreService.findByName(content));
						p.setTestContent(content);
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
					if (name.contains("���Ի�����") || name.contains("��������")) {
						p.setPrototypeCount(content);
					}
					if (name.contains("���Ի�����") || name.contains("��������")) {
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
				List<Policy> policys = hqlService
						.findByHql("from Policy p where p.phase='" + p.getPhase() + "' and p.version='" + p.getVersion()
								+ "' and p.testContent='" + p.getTestContent() + "' and p.policypool.id='" + pid + "'");
				if (policys.size() > 0) {
					// ���¼ƻ�
					Policy policy = policys.get(0);
					policy.setVersionReleasetime(p.getVersionReleasetime());
					// policy.setVersionEndtime(p.getVersionEndtime());
					policy.setCaseCount(p.getCaseCount());
					policy.setHumanInput(p.getHumanInput());
					policy.setHumanCount(p.getHumanCount());
					policy.setStartTime(p.getStartTime());
					policy.setEndTime(p.getEndTime());
					policy.setRemark(p.getRemark());
					policy.setPrototypeCount(p.getPrototypeCount());
					policy.setPrototypeReuse(p.getPrototypeReuse());
					// policy.setPrototypeType(p.getPrototypeType());
					policyService.updatePolicy(policy);
				} else {
					// ��Ӽƻ�
					p.setPolicypool(policypoolService.findById(pid));
					policyService.addPolicy(p);
				}
			} else {
				result = "��ѡ����ȷ��ʽ���ļ�";
				return result;
			}

		}
		result = "������Ŀ���Գɹ�";
		return result;

	}

	public void addExceptionLog(Exception e, String str) {
		// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		exceptionLogService.addLogMsg(str + ":" + sw.getBuffer().toString());
	}

}
