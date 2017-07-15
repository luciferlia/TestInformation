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
	 * 添加策略库
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
			addExceptionLog(e, "添加客户异常");
			return "error";
		}

	}

	private int id;// 策略库Id

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
	 * 删除该策略库，并且删除该名下所有策略
	 * 
	 * @return
	 */
	public String delPolicyPool() {
		try {
			Policypool ppd = policypoolService.findById(id);
			System.out.println("策略库ID：" + id);
			if (ppd != null) {
				policypoolService.delete(id);
			}
			return "addPolicyPool";
		} catch (Exception e) {
			addExceptionLog(e, "删除客户异常");
			return "error";
		}

	}

	/**
	 * 根据策略库编号，修改该策略库名称
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
			addExceptionLog(e, "删除客户异常");
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
	 * 显示所有策略库的信息
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
			addExceptionLog(e, "显示所有客户异常");
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
	 * 根据策略库编号，显示所有策略模板
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
					double totalTime = 0.0;// 总耗时
					double adviceTime = 0.0;// 每条用例耗时
					// System.out.println(cs.getTestModule());
					// 统计用例条数
					CaseNum cn = new CaseNum();
					int n = caseService.findByCasestoreId(cs.getCasestoreId()).size();
					cn.setCaseStore(cs.getTestModule());
					cn.setNum(n);
					// 统计预估时间
					List<Case> cases = caseService.findByCasestoreId(cs.getCasestoreId());
					for (Case ca : cases) {
						if (ca.getAdvidceTime() == null || ca.getAdvidceTime() == ""
								|| ca.getAdvidceTime().equals("")) {
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
			if (policypoolService.findById(id).getPolicyName().contains("3T")) {
				setPermissionForm(rolePermissionService.getUserPermission("policy.jsp"));
				return "show3TPolicy";
			}

			if (policypoolService.findById(id).getPolicyName().contains("IPD")) {
				setPermissionForm(rolePermissionService.getUserPermission("IPD.jsp"));
				return "showIPDPolicy";
			}

			if (policypoolService.findById(id).getPolicyName().contains("自定义")) {
				setPermissionForm(rolePermissionService.getUserPermission("Auto.jsp"));
				return "showAutoPolicy";
			}

		} catch (Exception e) {
			addExceptionLog(e, "显示策略异常");
			return "error";
		}
		return null;
	}

	/**
	 * 添加策略功能实现
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
			System.out.println("长度：" + os.length);
			for (int i = 0; i < os.length; i++) {

				JSONObject obj = JSONObject.fromObject(os[i]);
				String policyId = obj.getString("policyId").toString();// 策略库编号
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
				System.out.println("策略库编号:" + id);
				if (policyId.equals("") || policyId == null) {
					// 增加策略
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
					// 修改策略
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
			addExceptionLog(e, "添加策略异常");
			return "error";
		}
	}

	private int policyId;// 策略编号

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	/**
	 * 根据策略编号，删除该策略
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
					System.out.println("删除成功");
					result = "success";
				} else {
					result = "fail";
				}
			} else {
				result = "fail";
			}
			out.getWriter().write(result);
		} catch (Exception e) {
			addExceptionLog(e, "删除策略异常");
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
	 * 导出项目客户的策略
	 * 
	 * @return
	 * @throws RowsExceededException
	 * @throws IOException
	 */
	public String exportPolicy() throws RowsExceededException {
		try {
			String result = "";
			HttpServletResponse out = ServletActionContext.getResponse();
			System.out.println("策略库编号：" + policypoolId);
			List<Policy> policys = policyService
					.findAllPolicy("from Policy p where p.policypool.id='" + policypoolId + "'");
			if (policys.size() > 0) {
				WriteExcel w = new WriteExcel();
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
				String str[] = { "项目阶段", "软件版本", "版本发布时间", "测试类型", "测试内容", "用例条数", "人力投入（人.天）", "人数安排", "测试机需求",
						"测试机复用", "开始时间", "结束时间", "备注" };
				ArrayList<String> title = new ArrayList<String>();
				for (int t = 0; t < str.length; t++) {
					title.add(str[t]);
				}
				list.add(title);
				String policyName = policypoolService.findById(policypoolId).getPolicyName();
				for (Policy policy : policys) {
					ArrayList<String> l = new ArrayList<String>();
					// l.add(policyName);// 客户名称
					l.add(policy.getPhase());// 项目阶段
					l.add(policy.getVersion());// 软件版本
					l.add(policy.getVersionReleasetime());// 版本发布时间
					// l.add(policy.getVersionEndtime());// 版本结束时间
					l.add(caseTypeService.findById(policy.getCasetype().getId()).getCasetypeName());// 测试项
					l.add(policy.getTestContent());// 测试内容
					l.add(policy.getCaseCount());// 用例条数
					l.add(policy.getHumanInput());// 人力投入
					l.add(policy.getHumanCount());// 人数安排
					l.add(policy.getPrototypeCount());// 测试机需求
					l.add(policy.getPrototypeReuse());// 测试机复用
					// l.add(policy.getPrototypeType());// 测试机复用类型
					l.add(policy.getStartTime());// 开始时间
					l.add(policy.getEndTime());// 结束时间
					l.add(policy.getRemark());// 备注
					list.add(l);
				}
				// FileSystemView fsv = FileSystemView.getFileSystemView();
				// File f=fsv.getHomeDirectory();
				// SimpleDateFormat sdf = new
				// SimpleDateFormat("yyyyMMddHHmmss");
				// System.out.println("路径："+f.getPath());
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
			addExceptionLog(e, "导出策略异常");
			return "error";
		}
	}

	/**
	 * 导出用例后将用例下载到本地
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
			addExceptionLog(e, "下载策略异常");
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
	 * 导入策略
	 * 
	 * @return
	 */
	public String importPolicy() {
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
			result = realExcel(strs, policypoolId);

			return "imporPolicySuc";
		} catch (Exception e) {
			addExceptionLog(e, "导入策略异常");
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

				if (s[0].contains("测试类型") && caseTypeService.findByName(s[1]) == null) {

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
			Policy p = new Policy();
			p.setId(0);
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
					/*
					 * if (name.contains("版本结束时间")) {
					 * p.setVersionEndtime(content);
					 * 
					 * }
					 */
					if (name.contains("测试类型")) {

						p.setCasetype(caseTypeService.findByName(content));
					}
					if (name.contains("测试内容")) {
						// p.setCasestore(casestoreService.findByName(content));
						p.setTestContent(content);
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
					if (name.contains("测试机需求") || name.contains("样机需求")) {
						p.setPrototypeCount(content);
					}
					if (name.contains("测试机复用") || name.contains("样机复用")) {
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
				List<Policy> policys = hqlService
						.findByHql("from Policy p where p.phase='" + p.getPhase() + "' and p.version='" + p.getVersion()
								+ "' and p.testContent='" + p.getTestContent() + "' and p.policypool.id='" + pid + "'");
				if (policys.size() > 0) {
					// 更新计划
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
					// 添加计划
					p.setPolicypool(policypoolService.findById(pid));
					policyService.addPolicy(p);
				}
			} else {
				result = "请选择正确格式的文件";
				return result;
			}

		}
		result = "导入项目策略成功";
		return result;

	}

	public void addExceptionLog(Exception e, String str) {
		// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		exceptionLogService.addLogMsg(str + ":" + sw.getBuffer().toString());
	}

}
