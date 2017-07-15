package com.wind.action;

import java.io.IOException;
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

import org.apache.struts2.ServletActionContext;

import com.wind.entity.Casestore;
import com.wind.entity.Menu;
import com.wind.entity.Module;
import com.wind.entity.PageBean;
import com.wind.entity.Plantail;
import com.wind.entity.Project;
import com.wind.entity.Role;
import com.wind.entity.SelectType;
import com.wind.entity.User;
import com.wind.from.MenuForm;
import com.wind.from.UserRoleForm;
import com.wind.from.UserStatus;
import com.wind.util.EmailUtils;
import com.wind.util.MD5;
import com.wind.util.ServiceConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class UserAction extends ServiceConfig {
	private User user;// �û�����

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * ������Ա��Ϣ
	 * 
	 * @return
	 */
	public String addPerson() {
		try {
			user.setState("1");
			user.setStatus(0);
			user.setFlag(false);
			String psw = MD5.getEncryptedPwd(user.getPassword());
			System.out.println(psw);
			user.setPassword(psw);
			this.userService.saveUser(user);
			if (userService.findUserById(user.getUserId()) == null) {
				message = "�û�����ʧ�ܣ�";
				return "fail";
			} else {
				message = "�û�����ɹ�";
				return "success_save";
			}
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�����Ա�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * �޸��û���Ϣ���û�Ȩ��
	 * 
	 * @return
	 */
	public String updateUserMsg() {
		try {
			User u = userService.findUserById(user.getUserId());
			u.setName(user.getName());
			u.setNum(user.getNum());
			u.setPosition(user.getPosition());
			userService.updateUser(u);
			if (roleId != 0) {
				userService.addUserRole(user.getUserId(), roleId);
				setRoleId(0);
			}

			return "addPowerSuc";
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�޸��û���Ϣ��Ȩ���쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	private List<SelectType> selectTypes;
	private SelectType selectType;

	public SelectType getSelectType() {
		return selectType;
	}

	public void setSelectType(SelectType selectType) {
		this.selectType = selectType;
	}

	public List<SelectType> getSelectTypes() {
		return selectTypes;
	}

	public void setSelectTypes(List<SelectType> selectTypes) {
		this.selectTypes = selectTypes;
	}

	/**
	 * �����Աǰ�Ļ���
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addPersionPre() {
		selectTypes = selectTypeService.findByHql("from SelectType");
		return "apPer";
	}

	/**
	 * ��ʾ���з���
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showGroup() {
		selectTypes = selectTypeService.findByHql("from SelectType st where st.type='1'");

		return "showGroup";
	}

	/**
	 * ��ʾ����ְλ
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showPosition() {
		selectTypes = selectTypeService.findByHql("from SelectType st where st.type='2'");

		return "showPosition";
	}

	/**
	 * ��ӷ����ְλ
	 * 
	 * @return
	 */
	public String addSelectType() {
		try {
			SelectType st = new SelectType();
			st.setId(0);
			st.setName(selectType.getName());
			st.setType(selectType.getType());
			selectTypeService.addType(st);
			if (selectType.getType().equals("1")) {
				return "type1";
			} else {
				return "type2";
			}
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("��ӷ����ְλ��Ϣ�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	/**
	 * �޸ķ����ְλ����
	 * 
	 * @return
	 */
	public String updateSelectType() {
		try {
			SelectType st = selectTypeService.findById(selectType.getId());
			st.setName(selectType.getName());
			selectTypeService.updateType(st);
			if (selectType.getType().equals("1")) {
				return "type1";
			} else {
				return "type2";
			}
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�޸ķ����ְλ��Ϣ�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	/**
	 * ɾ�������ְλ
	 * 
	 * @return
	 */
	public String deleteSelectType() {
		try {
			selectTypeService.deleteType(selectTypeService.findById(id));
			if (selectType.getType().equals("1")) {
				return "type1";
			} else {
				return "type2";
			}
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("ɾ�������ְλ��Ϣ�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private List<Module> ms;

	public List<Module> getMs() {
		return ms;
	}

	public void setMs(List<Module> ms) {
		this.ms = ms;
	}

	/**
	 * �û���¼����ʵ��
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String login() {
		try {
			if (password != null && username != null && password != "" && username != "") {
				HttpServletRequest request = ServletActionContext.getRequest();
				final HttpSession session = request.getSession();
				User users = userService.checkName(username);
				if (users != null) {
					if (users.getState().equals("1")) { // ��ȡ�û�״̬1��ʾ�û�����
						boolean b = false;
						boolean bf = false;
						if (users.getPassword().equals("123456")) {
							b = users.getPassword().equals(password);
						} else {
							b = MD5.validPassword(password, users.getPassword());
							bf = MD5.validPassword("123456", users.getPassword());
						}
						if (b) {
							password = "";
							username = "";
							User u = users;
							session.setAttribute("user", u);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							u.setLoginTime(sdf.format(new Date()));
							u.setFlag(true);
							userService.updateUser(u);
							// �����û�id��ȡ���û��Ľ�ɫ
							List<Integer> roleId = userService.getRole(u.getUserId());
							if (roleId.size() > 0) {
								Role r = roleService.findById(roleId.get(0));// ��ȡ��ɫ��Ϣ
								session.setAttribute("role", r);
								ms = moduleService.findModule(roleId);
								// session.setAttribute("ps", ps);

								// �����û��Ľ�ɫid��ȡ���û���ӵ�еĲ˵�Ȩ��
								List<Menu> mes = menuService.findMenuByRid(r.getRoleId());
								List<MenuForm> mfs = new ArrayList<MenuForm>();

								System.out.println(ms.size());
								for (Module m : ms) {
									List<Menu> me = new ArrayList<Menu>();
									MenuForm mf = new MenuForm();
									for (Menu line : mes) {
										if (m.getModuleId() == line.getModule().getModuleId()
												|| m.getModuleId().equals(line.getModule().getModuleId())) {

											me.add(line);
										}
									}
									mf.setMs(me);
									mf.setModule(m.getModuleName());
									mf.setDescription(m.getDescription());
									mf.setUrl(m.getUrl());
									mfs.add(mf);
								}
								session.setAttribute("menu", mfs);
							}
							// ��ȡ�û���¼log��Ϣ������û����������������5���Ժ��˺Ž�������
							if (session.getAttribute("login") != null)
								session.removeAttribute("login");
							if (u.getLoginTime() == null || u.getLoginTime() == "" || u.getPassword().equals("123456")
									|| bf) {// ˵����һ�ε�½��ϵͳ��Ҫ��������
								message = "���ǵ�һ�ε�½ϵͳ����������룡";
								return "fail";
							} else {
								message = "";
								System.out.println("��¼�ɹ�");
								return SUCCESS;
							}

						} else {
							if (session.getAttribute("login") == null) {
								session.setAttribute("login", 4);
								message = "�û������������,�㻹���Գ���4��";
								System.out.println("ԭ�������벻��ȷ,�㻹���Գ���4��");
							} else {
								int num = (int) session.getAttribute("login");
								if (num == 1) {
									// message="ԭ�������벻��ȷ�����˻��ѱ�����������ϵ����Ա����";
									users.setState("2");
									userService.updateUser(users);
									session.removeAttribute("login");
									message = "�û������������,���û�������������ϵ����Ա����!";

								} else {
									message = "�û������������,�㻹���Գ���" + (num - 1) + "��";
									session.setAttribute("login", num - 1);
								}

							}
							return "input";
						}
					} else if (users.getState().equals("0")) { // �û�״̬Ϊ0����ʾ���û��ѱ�ע��������ʹ��
						message = "���û���ע����";
						return "input";
					} else if (users.getState().equals("2")) { // �û�״̬Ϊ2,��ʾ���û��ѱ���������Ҫ����Ա���������ʹ��
						message = "���û�������������ϵ����Ա������";
						return "input";
					} else {
						message = "�û������ã�";
						return "input";
					}
				} else {
					message = "�û������ڣ�";
					return "input";
				}
			} else {
				return "input";
			}

		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("��¼��Ϣ�쳣��" + sw.getBuffer().toString());
			return "error";
		}
	}

	/**
	 * �޸��û�����
	 * 
	 * @return
	 * @throws IOException
	 */
	public String updatePwd() throws IOException {
		try {
			setMessage("");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			String jsonStr = ServletActionContext.getRequest().getParameter("params");
			System.out.println(jsonStr);
			JSONArray arr = JSONArray.fromObject(jsonStr);
			System.out.println(arr);
			Object[] os = arr.toArray();
			System.out.println("���ȣ�" + os.length);
			for (int i = 0; i < os.length; i++) {
				JSONObject obj = JSONObject.fromObject(os[i]);
				String pwd = obj.getString("pwd").toString();
				String newPwd = obj.getString("newPwd").toString();
				boolean bf = false;
				if (user.getPassword().equals("123456")) {
					bf = user.getPassword().equals(pwd);
				} else {
					bf = MD5.validPassword(pwd, user.getPassword());
				}
				// !user.getPassword().equals(pwd)&&user.getPassword()!=pwd
				if (!bf) {
					if (session.getAttribute("upwd") == null) {
						session.setAttribute("upwd", 4);
						result = "ԭ�������벻��ȷ,�㻹���Գ���4��";
						System.out.println("ԭ�������벻��ȷ,�㻹���Գ���4��");
					} else {
						int num = (int) session.getAttribute("upwd");
						if (num == 1) {
							// message="ԭ�������벻��ȷ�����˻��ѱ�����������ϵ����Ա����";
							user.setState("2");
							userService.updateUser(user);
							session.removeAttribute("upwd");
							result = "fail";
						} else {
							result = "ԭ�������벻��ȷ,�㻹���Գ���" + (num - 1) + "��";
							System.out.println("ԭ�������벻��ȷ,�㻹���Գ���" + (num - 1) + "��");
							session.setAttribute("upwd", num - 1);
						}

					}

				} else {
					if (newPwd.equals("123456")) {
						result = "���벻�����óɳ�ʼ���룡";
						System.out.println("���벻�����óɳ�ʼ���룡");
					} else {
						user.setPassword(MD5.getEncryptedPwd(newPwd));
						userService.updateUser(user);
						result = "success";
					}

					if (session.getAttribute("upwd") != null) {
						session.removeAttribute("upwd");
					}

				}
			}
			/*
			 * HttpServletResponse out=ServletActionContext.getResponse();
			 * out.getWriter().write(msg);
			 */
			return "success";
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�޸�������Ϣ�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	// ɾ��
	public String deleteUser() {
		try {
			String checkedId[] = checkedIds.split(",");// ���зָ�浽����
			String temp = "";
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					System.out.println(temp + "%%%%%%%%%");
					temp = checkedId[i]; // �������
					User u = userService.findUserById(Integer.parseInt(temp));
					u.setState("0");
					userService.updateUser(u);
				}
			}

			return "success_d";
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("ɾ���û���Ϣ�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// ����
	public String update() {
		try {
			if (userService.findUserById(user.getUserId()) != null) {
				setUser(user);
				userService.updateUser(user);
				return "success_u";
			}
			return INPUT;
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�޸��û���Ϣ�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	private PageBean pageBean;// ��ҳʵ��

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

	public List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	private String type;// �������
	private String content;// ����

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private int tag;

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	/**
	 * ��ҳ��ʾ�����û���Ϣ
	 * 
	 * @return
	 */
	public String query() {
		try {
			List<Role> rs = roleService.findAll();
			List<Role> list = new ArrayList<Role>();
			for (Role r : rs) {
				// System.out.println("��ɫ����"+r.getRoleName());
				if (!r.getRoleName().equals("��������Ա")) {
					// System.out.println("��ɫ����"+r.getRoleName());
					list.add(r);
				}
			}
			setRoles(list);
			if (tag == 1) {
				setContent("");
				setTag(0);
			}
			if (content == null || content == "") {
				final String hql = "from User where state in(1,2)";
				this.pageBean = pageService.queryForUserPage(hql, 15, page);

			} else {
				if (type.equals("num")) {

					final String hql = "from User where state in(1,2) and num like '%" + content + "%'";
					this.pageBean = pageService.queryForUserPage(hql, 5, page);
				} else if (type.equals("name")) {

					final String hql = "from User where state in(1,2) and name like '%" + content + "%'";
					this.pageBean = pageService.queryForUserPage(hql, 5, page);
				} else if (type.equals("position")) {

					final String hql = "from User where state in(1,2) and position like '%" + content + "%'";
					this.pageBean = pageService.queryForUserPage(hql, 15, page);

				}

			}
			return "success_s";
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("��ʾ�����û���Ϣ�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	/**
	 * �޸��û�ǰ�Ļ���
	 * 
	 * @return
	 */
	public String updatePre() {
		// System.out.println(id);
		user = userService.findUserById(id);
		return "updatePre";
	}

	/**
	 * ��ʾ�ҵĻ�����Ϣ
	 * 
	 * @return
	 */
	public String myShow() {
		user = userService.findUserById(id);
		// System.out.println(id);
		return "show";
	}

	public String showAllUser() {
		try {
			roles = roleService.findAll();
			if (tag == 1) {
				setContent("");
				setTag(0);
			}
			if (content == null || content == "") {

				final String hql = "from User";
				this.pageBean = pageService.queryForUserPage(hql, 15, page);

			} else {
				if (type.equals("num")) {
					final String hql = "from User where num like '%" + content + "%'";
					this.pageBean = pageService.queryForUserPage(hql, 15, page);
				} else if (type.equals("name")) {
					final String hql = "from User where name like '%" + content + "%'";
					this.pageBean = pageService.queryForUserPage(hql, 15, page);
				} else if (type.equals("position")) {
					final String hql = "from User where position like '%" + content + "%'";
					this.pageBean = pageService.queryForUserPage(hql, 15, page);
				}
			}

			for (Object user : pageBean.getList()) {
				if (((UserRoleForm) user).getUser().getState().equals("0")) {
					((UserRoleForm) user).getUser().setState("��ע��");
				} else if (((UserRoleForm) user).getUser().getState().equals("1")) {
					((UserRoleForm) user).getUser().setState("����");
				} else if (((UserRoleForm) user).getUser().getState().equals("2")) {
					((UserRoleForm) user).getUser().setState("������");
				} else {
					((UserRoleForm) user).getUser().setState("������");
				}
			}

			return "showUser";
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("��ʾ�����û���Ϣ�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	private String checkedIds;
	private String checkedIds1;

	public String getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	public String getCheckedIds1() {
		return checkedIds1;
	}

	public void setCheckedIds1(String checkedIds1) {
		this.checkedIds1 = checkedIds1;
	}

	public String addPower() {
		try {
			String checkedId[] = checkedIds.split(",");// ���зָ�浽����
			String checkedId1[] = checkedIds1.split(",");// ���зָ�浽����
			int temp;
			int temp1;
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					temp = Integer.parseInt(checkedId[i]); // �������
					// System.out.println("��Ա��ţ�"+temp);
					for (int j = 0; j < checkedId1.length; j++) {
						if (!checkedId1[j].equals("")) {
							temp1 = Integer.parseInt(checkedId1[j]); // �������
							// System.out.println("��ɫ��ţ�"+temp);
							userService.addUserRole(temp, temp1);
						}
					}
				}
			}

			return "addPowerSuc";
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("���Ȩ���쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	/**
	 * �˳���¼
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.removeAttribute("menu");
			session.removeAttribute("role");
			if (session.getAttribute("upwd") != null)
				session.removeAttribute("upwd");
			message = "";
			return "input";
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�˳�ϵͳ�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String logLogin() throws IOException {
		String jsonStr = ServletActionContext.getRequest().getParameter("params");
		JSONArray arr = JSONArray.fromObject(jsonStr);
		System.out.println(arr);

		Object[] os = arr.toArray();
		System.out.println("���ȣ�" + os.length);
		System.out.println();
		for (int i = 0; i < os.length; i++) {
			JSONObject obj = JSONObject.fromObject(os[i]);
			System.out.println(obj);
			String username = obj.getString("username").toString();
			String pwd = obj.getString("password").toString();
			System.out.println(username);
			System.out.println(pwd);
		}

		return "input";
	}

	private String state;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * �޸��Ñ�״̬
	 * 
	 * @return
	 */
	public String updateUser() {
		try {
			User user = userService.findUserById(userId);
			if (user != null) {
				user.setState(state);
				userService.updateUser(user);
			}
			return "updateUseSuc";
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�޸��û�״̬�쳣��" + sw.getBuffer().toString());
			return "error";
		}

	}

	@SuppressWarnings("unchecked")
	public String sendMsg() {
		try {
			System.out.println(username);
			List<User> users = hqlService.findByHql("from User u where u.username='" + username + "'");
			if (users.size() > 0) {
				String code = EmailUtils.getRandomStr(9);
				users.get(0).setCode(code);
				userService.updateUser(users.get(0));
				boolean bflag = EmailUtils.sendResetPasswordEmail(users.get(0), code);
				if (bflag) {
					message = "У�����ѷ��͵����" + users.get(0).getEmail() + "��";
				} else {
					message = "�ʼ�����ʧ��";
					return "fail";
				}
			} else {
				message = "��������û�����������������";
				return "fail";
			}
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�޸��û�״̬�쳣��" + sw.getBuffer().toString());
			return "error";
		}

		return "send";
	}

	/**
	 * ���·���У����
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String sendMsg1() {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();
			String result = "";
			System.out.println(username);
			List<User> users = hqlService.findByHql("from User u where u.username='" + username + "'");
			if (users.size() > 0) {
				String code = EmailUtils.getRandomStr(9);
				users.get(0).setCode(code);
				userService.updateUser(users.get(0));
				boolean bflag = EmailUtils.sendResetPasswordEmail(users.get(0), code);
				if (bflag) {
					result = "У�����ѷ��͵����" + users.get(0).getEmail() + "��,ע�����";
				} else {
					result = "�ʼ�����ʧ��";
				}
			} else {
				result = "��������û�����������������";
			}
			out.getWriter().write(result);
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�޸��û�״̬�쳣��" + sw.getBuffer().toString());
			return "error";
		}

		return null;
	}

	private String code;
	private String newPwd;

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * �����û�����
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String SettingPwd() {
		try {
			HttpServletResponse out = ServletActionContext.getResponse();
			List<User> users = hqlService.findByHql("from User u where u.username='" + username + "'");
			String result = "";
			if (users.size() > 0) {
				if (code.equals(users.get(0).getCode())) {

					users.get(0).setPassword(MD5.getEncryptedPwd(newPwd));
					users.get(0).setCode("");
					userService.updateUser(users.get(0));
					result = "success";
				} else {
					result = "fail";
				}

			} else {
				result = "�û���������";
			}
			out.getWriter().write(result);
		} catch (Exception e) {
			// �����쳣��Ϣ�������쳣��Ϣд�뵽���ݿ��У�������ڵ��Է���
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("�޸��û�״̬�쳣��" + sw.getBuffer().toString());
			return "error";
		}
		return null;
	}

	private List<UserStatus> userStatus;

	public List<UserStatus> getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(List<UserStatus> userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * ��ȡ��Ա��æµ״̬�����æµ�У���ʾ��������Ŀ����Ϣ��������ʾ��
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String HumanStatus() {
		long startTime = System.currentTimeMillis(); // ��ȡ��ʼʱ��
		List<User> users = userService.findAll();
		List<UserStatus> list = new ArrayList<UserStatus>();
		for (User user : users) {
			UserStatus us = new UserStatus();
			us.setName(user.getName());
			if (user.getStatus() == 0) {// �û�Ϊ����״̬���ɷ�������
				us.setStatus(0);
			} else {// �û�Ϊæµ״̬�����ɷ�������
				us.setStatus(1);
				us.setGroupName(user.getGroupName());// ������
				List<Plantail> plantails = hqlService.findByHql("from Plantail pt where pt.user.userId='"
						+ user.getUserId() + "' and pt.state in(1,2) and pt.finishState='0'");
				// ��ɶ�
				if (plantails.get(0).getFinishDegree() == null) {
					us.setFinsh("0.0%");
				} else {
					us.setFinsh(plantails.get(0).getFinishDegree());
				}
				us.setTestModule(((Casestore) hqlService.findByHql(
						"from Casestore ct where ct.casestoreId in(select pc.casestore.casestoreId where PlanCasestore pc where pc.plan.planId='"
								+ plantails.get(0).getPlan().getPlanId() + "' and pc.tester='" + user.getUserId()
								+ "')")).getTestModule());
				us.setProjectName(((Project) hqlService
						.findByHql("from Project p where p.id in(select p.project.id from Plan p where p.planId='"
								+ plantails.get(0).getPlan().getPlanId() + "')")
						.get(0)).getProjectName());
			}
			list.add(us);
		}
		setUserStatus(list);
		long endTime = System.currentTimeMillis(); // ��ȡ����ʱ��
		System.out.println("��������ʱ�䣺 " + (endTime - startTime) + "ms");
		return "showStatus";
	}
}