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
	private User user;// 用户对象

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
	 * 增加人员信息
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
				message = "用户保存失败！";
				return "fail";
			} else {
				message = "用户保存成功";
				return "success_save";
			}
		} catch (Exception e) {
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("添加人员异常：" + sw.getBuffer().toString());
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
	 * 修改用户信息或用户权限
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
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("修改用户信息或权限异常：" + sw.getBuffer().toString());
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
	 * 添加人员前的回显
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addPersionPre() {
		selectTypes = selectTypeService.findByHql("from SelectType");
		return "apPer";
	}

	/**
	 * 显示所有分组
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showGroup() {
		selectTypes = selectTypeService.findByHql("from SelectType st where st.type='1'");

		return "showGroup";
	}

	/**
	 * 显示所有职位
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showPosition() {
		selectTypes = selectTypeService.findByHql("from SelectType st where st.type='2'");

		return "showPosition";
	}

	/**
	 * 添加分组或职位
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
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("添加分组或职位信息异常：" + sw.getBuffer().toString());
			return "error";
		}

	}

	/**
	 * 修改分组或职位名称
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
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("修改分组或职位信息异常：" + sw.getBuffer().toString());
			return "error";
		}

	}

	/**
	 * 删除分组或职位
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
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("删除分组或职位信息异常：" + sw.getBuffer().toString());
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
	 * 用户登录功能实现
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
					if (users.getState().equals("1")) { // 获取用户状态1表示用户可用
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
							// 根据用户id获取改用户的角色
							List<Integer> roleId = userService.getRole(u.getUserId());
							if (roleId.size() > 0) {
								Role r = roleService.findById(roleId.get(0));// 获取角色信息
								session.setAttribute("role", r);
								ms = moduleService.findModule(roleId);
								// session.setAttribute("ps", ps);

								// 根据用户的角色id获取该用户所拥有的菜单权限
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
							// 获取用户登录log信息，如果用户连续输入错误密码5次以后，账号将被锁定
							if (session.getAttribute("login") != null)
								session.removeAttribute("login");
							if (u.getLoginTime() == null || u.getLoginTime() == "" || u.getPassword().equals("123456")
									|| bf) {// 说明第一次登陆该系统需要重置密码
								message = "您是第一次登陆系统，请更改密码！";
								return "fail";
							} else {
								message = "";
								System.out.println("登录成功");
								return SUCCESS;
							}

						} else {
							if (session.getAttribute("login") == null) {
								session.setAttribute("login", 4);
								message = "用户名或密码错误,你还可以尝试4次";
								System.out.println("原密码输入不正确,你还可以尝试4次");
							} else {
								int num = (int) session.getAttribute("login");
								if (num == 1) {
									// message="原密码输入不正确，该账户已被锁定，请联系管理员解锁";
									users.setState("2");
									userService.updateUser(users);
									session.removeAttribute("login");
									message = "用户名或密码错误,该用户被锁定，请联系管理员解锁!";

								} else {
									message = "用户名或密码错误,你还可以尝试" + (num - 1) + "次";
									session.setAttribute("login", num - 1);
								}

							}
							return "input";
						}
					} else if (users.getState().equals("0")) { // 用户状态为0，表示该用户已被注销，不可使用
						message = "该用户已注销！";
						return "input";
					} else if (users.getState().equals("2")) { // 用户状态为2,表示该用户已被锁定，需要管理员解锁后才能使用
						message = "该用户被锁定，请联系管理员解锁！";
						return "input";
					} else {
						message = "用户不可用！";
						return "input";
					}
				} else {
					message = "用户不存在！";
					return "input";
				}
			} else {
				return "input";
			}

		} catch (Exception e) {
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("登录信息异常：" + sw.getBuffer().toString());
			return "error";
		}
	}

	/**
	 * 修改用户密码
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
			System.out.println("长度：" + os.length);
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
						result = "原密码输入不正确,你还可以尝试4次";
						System.out.println("原密码输入不正确,你还可以尝试4次");
					} else {
						int num = (int) session.getAttribute("upwd");
						if (num == 1) {
							// message="原密码输入不正确，该账户已被锁定，请联系管理员解锁";
							user.setState("2");
							userService.updateUser(user);
							session.removeAttribute("upwd");
							result = "fail";
						} else {
							result = "原密码输入不正确,你还可以尝试" + (num - 1) + "次";
							System.out.println("原密码输入不正确,你还可以尝试" + (num - 1) + "次");
							session.setAttribute("upwd", num - 1);
						}

					}

				} else {
					if (newPwd.equals("123456")) {
						result = "密码不能设置成初始密码！";
						System.out.println("密码不能设置成初始密码！");
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
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("修改密码信息异常：" + sw.getBuffer().toString());
			return "error";
		}

	}

	// 删除
	public String deleteUser() {
		try {
			String checkedId[] = checkedIds.split(",");// 进行分割存到数组
			String temp = "";
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					System.out.println(temp + "%%%%%%%%%");
					temp = checkedId[i]; // 保存更改
					User u = userService.findUserById(Integer.parseInt(temp));
					u.setState("0");
					userService.updateUser(u);
				}
			}

			return "success_d";
		} catch (Exception e) {
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("删除用户信息异常：" + sw.getBuffer().toString());
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

	// 更新
	public String update() {
		try {
			if (userService.findUserById(user.getUserId()) != null) {
				setUser(user);
				userService.updateUser(user);
				return "success_u";
			}
			return INPUT;
		} catch (Exception e) {
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("修改用户信息异常：" + sw.getBuffer().toString());
			return "error";
		}

	}

	private PageBean pageBean;// 分页实体

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

	private String type;// 搜索类别
	private String content;// 内容

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
	 * 分页显示所有用户信息
	 * 
	 * @return
	 */
	public String query() {
		try {
			List<Role> rs = roleService.findAll();
			List<Role> list = new ArrayList<Role>();
			for (Role r : rs) {
				// System.out.println("角色名："+r.getRoleName());
				if (!r.getRoleName().equals("超级管理员")) {
					// System.out.println("角色名："+r.getRoleName());
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
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("显示所有用户信息异常：" + sw.getBuffer().toString());
			return "error";
		}

	}

	/**
	 * 修改用户前的回显
	 * 
	 * @return
	 */
	public String updatePre() {
		// System.out.println(id);
		user = userService.findUserById(id);
		return "updatePre";
	}

	/**
	 * 显示我的基本信息
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
					((UserRoleForm) user).getUser().setState("已注销");
				} else if (((UserRoleForm) user).getUser().getState().equals("1")) {
					((UserRoleForm) user).getUser().setState("正常");
				} else if (((UserRoleForm) user).getUser().getState().equals("2")) {
					((UserRoleForm) user).getUser().setState("已锁定");
				} else {
					((UserRoleForm) user).getUser().setState("不可用");
				}
			}

			return "showUser";
		} catch (Exception e) {
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("显示所有用户信息异常：" + sw.getBuffer().toString());
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
			String checkedId[] = checkedIds.split(",");// 进行分割存到数组
			String checkedId1[] = checkedIds1.split(",");// 进行分割存到数组
			int temp;
			int temp1;
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					temp = Integer.parseInt(checkedId[i]); // 保存更改
					// System.out.println("人员序号："+temp);
					for (int j = 0; j < checkedId1.length; j++) {
						if (!checkedId1[j].equals("")) {
							temp1 = Integer.parseInt(checkedId1[j]); // 保存更改
							// System.out.println("角色序号："+temp);
							userService.addUserRole(temp, temp1);
						}
					}
				}
			}

			return "addPowerSuc";
		} catch (Exception e) {
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("添加权限异常：" + sw.getBuffer().toString());
			return "error";
		}

	}

	/**
	 * 退出登录
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
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("退出系统异常：" + sw.getBuffer().toString());
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
		System.out.println("长度：" + os.length);
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
	 * 修改用戶状态
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
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("修改用户状态异常：" + sw.getBuffer().toString());
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
					message = "校验码已发送到你的" + users.get(0).getEmail() + "中";
				} else {
					message = "邮件发送失败";
					return "fail";
				}
			} else {
				message = "您输入的用户名有误，请重新输入";
				return "fail";
			}
		} catch (Exception e) {
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("修改用户状态异常：" + sw.getBuffer().toString());
			return "error";
		}

		return "send";
	}

	/**
	 * 从新发送校验码
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
					result = "校验码已发送到你的" + users.get(0).getEmail() + "中,注意查收";
				} else {
					result = "邮件发送失败";
				}
			} else {
				result = "您输入的用户名有误，请重新输入";
			}
			out.getWriter().write(result);
		} catch (Exception e) {
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("修改用户状态异常：" + sw.getBuffer().toString());
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
	 * 重设用户密码
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
				result = "用户名不存在";
			}
			out.getWriter().write(result);
		} catch (Exception e) {
			// 捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			exceptionLogService.addLogMsg("修改用户状态异常：" + sw.getBuffer().toString());
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
	 * 获取人员的忙碌状态，如果忙碌中，显示其所在项目的信息，否则显示无
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String HumanStatus() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		List<User> users = userService.findAll();
		List<UserStatus> list = new ArrayList<UserStatus>();
		for (User user : users) {
			UserStatus us = new UserStatus();
			us.setName(user.getName());
			if (user.getStatus() == 0) {// 用户为空闲状态，可分配任务
				us.setStatus(0);
			} else {// 用户为忙碌状态，不可分配任务
				us.setStatus(1);
				us.setGroupName(user.getGroupName());// 所在组
				List<Plantail> plantails = hqlService.findByHql("from Plantail pt where pt.user.userId='"
						+ user.getUserId() + "' and pt.state in(1,2) and pt.finishState='0'");
				// 完成度
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
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
		return "showStatus";
	}
}