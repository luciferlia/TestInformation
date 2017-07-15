package com.wind.action;

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
import com.wind.entity.Device;
import com.wind.entity.Deviceapply;
import com.wind.entity.PageBean;
import com.wind.entity.Prototype;
import com.wind.entity.Sim;
import com.wind.entity.User;
import com.wind.from.DeviceForm;
import com.wind.from.DeviceapplyForm;
import com.wind.from.PrototypeForm;
import com.wind.from.SimForm;
import com.wind.util.ServiceConfig;

public class ResourceAction extends ServiceConfig{
	
	private static final long serialVersionUID = 1L;
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

	private String checkedIds;// 获取复选id类型变量

	public String getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	private int id;// 定义编号id类型变量

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * =======================设备资源管理============================================
	 */
	public int borrowCount = 0;// 借出设备数量
	private Device device;// 获取设备类型变量

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	/**
	 * 设备资源管理——显示所有设备信息(资源管理者权限)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showAllDevice() {
		try {
			final String hql = "from Device d order by d.deviceId desc";
			this.pageBean = pageService.queryForPage(hql, 10, page);
			List<DeviceForm> list = new ArrayList<DeviceForm>();
			for (Object line : pageBean.getList()) {
				System.out.println(((Device) line).getDeviceName());
				DeviceForm df = new DeviceForm();
				User u = userService.findUserById(((Device) line).getManagerId());
				df.setDeviceId(((Device) line).getDeviceId());
				df.setDeviceName(((Device) line).getDeviceName());
				df.setDeviceType(((Device) line).getDeviceType());
				df.setVersion(((Device) line).getVersion());
				df.setProtocol(((Device) line).getProtocol());
				df.setDeviceCount(((Device) line).getDeviceCount());
				df.setIsconsum(((Device) line).getIsconsum());
				df.setRemark(((Device) line).getRemark());
				df.setManager(u.getName());
				list.add(df);
			}
			pageBean.setList(list);
			return "showAllDevice";
		} catch (Exception e) {
			addExceptionLog(e, "显示设备异常");
			return "error";
		}
	}

	/**
	 * 设备资源管理——添加设备基本信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String addDevice() throws ParseException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Device d = new Device();
			System.out.println("id" + device.getDeviceId());
			d.setDeviceId(device.getDeviceId());
			d.setDeviceName(device.getDeviceName());
			d.setDeviceType(device.getDeviceType());
			d.setVersion(device.getVersion());
			d.setProtocol(device.getProtocol());
			d.setDeviceCount(device.getDeviceCount());
			d.setManagerId(u.getUserId());
			d.setState(device.getState());
			d.setIsconsum(device.getIsconsum());
			d.setRemark(device.getRemark());
			deviceService.save(d);
			return "addDevice";
		} catch (Exception e) {
			addExceptionLog(e, "添加设备异常");
			return "error";
		}
	}

	/**
	 * 设备资源管理——删除设备基本信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String deleteDevice() {
		try {
			System.out.println("删除的设备id：" + id);
			Device d = deviceService.findById(id);
			deviceService.delete(d.getDeviceId());
			return "deleteDevice";
		} catch (Exception e) {
			addExceptionLog(e, "删除设备异常");
			return "error";
		}
	}

	/**
	 * 设备资源管理——批量删除设备基本信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String deleteSelectedDevice() {
		try {
			String checkedId[] = checkedIds.split(",");// 进行分割存到数组
			String temp = "";
			// System.out.println("设备ID:"+typeId);
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					System.out.println(checkedId[i]);
					temp = checkedId[i]; // 保存更改
					System.out.println("序号：" + temp);
					deviceService.delete(Integer.parseInt(temp));
				}
			}
		} catch (NumberFormatException e) {
			addExceptionLog(e, "批量删除设备异常");
			return "error";
		}
		return "deleteSelectedDevice";
	}

	/**
	 * 设备资源管理——修改设备基本信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String updateDevice() {
		try {
			System.out.println("d:" + device.getDeviceId());
			Device d = deviceService.findById(device.getDeviceId());
			d.setDeviceName(device.getDeviceName());
			d.setDeviceType(device.getDeviceType());
			d.setDeviceCount(device.getDeviceCount());
			d.setVersion(device.getVersion());
			d.setProtocol(device.getProtocol());
			d.setIsconsum(device.getIsconsum());
			d.setRemark(device.getRemark());
			deviceService.update(d);
		} catch (Exception e) {
			addExceptionLog(e, "修改设备异常");
			return "error";
		}
		return "updateDevice";
	}

	/**
	 * 查看设备借用情况(资源管理者权限)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showDeviceBorrow() {
		try {
			final String hql = "from Deviceapply d where d.deviceId='" + id + "'";
			this.pageBean = pageService.queryForPage(hql, 10, page);
			ArrayList<DeviceapplyForm> dafs = new ArrayList<DeviceapplyForm>();
			for (Object line : pageBean.getList()) {
				DeviceapplyForm daf = new DeviceapplyForm();
				User u = userService.findUserById(((Deviceapply) line).getBorrowId());
				Device d = deviceService.findById(((Deviceapply) line).getDeviceId());
				daf.setBorrower(u.getName());
				daf.setId(((Deviceapply) line).getId());
				daf.setDeviceName(d.getDeviceName());
				daf.setDeviceType(d.getDeviceType());
				daf.setDeviceCount(d.getDeviceCount());
				daf.setBorrowCount(((Deviceapply) line).getBorrowCount());
				daf.setBorrowTime(((Deviceapply) line).getBorrowTime());
				daf.setState(d.getState());
				dafs.add(daf);
			}
			pageBean.setList(dafs);
		} catch (Exception e) {
			addExceptionLog(e, "显示设备借用异常");
			return "error";
		}
		return "showDeviceBorrow";
	}

	/**
	 * 修改设备借用信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String updateDeviceState() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Device d = deviceService.findById(device.getDeviceId());
			d.setDeviceName(device.getDeviceName());
			d.setDeviceType(device.getDeviceType());
			d.setManagerId(u.getUserId());
			d.setState(device.getState());
			deviceService.update(d);
		} catch (Exception e) {
			addExceptionLog(e, "修改设备借用状态异常");
			return "error";
		}
		return "updateDeviceState";
	}

	/**
	 * 显示所有设备信息(用户查看)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showAllDevice2() throws NumberFormatException {
		try {
			final String hql = "from Device d order by d.deviceId desc";
			this.pageBean = pageService.queryForPage(hql, 10, page);
			List<DeviceForm> list = new ArrayList<DeviceForm>();
			for (Object line : pageBean.getList()) {
				DeviceForm df = new DeviceForm();
				User u = userService.findUserById(((Device) line).getManagerId());
				df.setDeviceId(((Device) line).getDeviceId());
				df.setDeviceName(((Device) line).getDeviceName());
				df.setDeviceType(((Device) line).getDeviceType());
				df.setProtocol(((Device) line).getProtocol());
				df.setVersion(((Device) line).getVersion());
				df.setDeviceCount(((Device) line).getDeviceCount());
				List<Deviceapply> das = deviceApplyService.showDeviceapply(df.getDeviceId());
				DeviceapplyForm daf = new DeviceapplyForm();
				for (Deviceapply line1 : das) {
					daf.setBorrowCount(line1.getBorrowCount());
					df.setRemainingCount(df.getDeviceCount() - daf.getBorrowCount());
				}
				df.setIsconsum(((Device) line).getIsconsum());
				df.setManager(u.getName());
				df.setState(((Device) line).getState());
				list.add(df);
			}
			pageBean.setList(list);
		} catch (Exception e) {
			addExceptionLog(e, "显示所有设备异常");
			return "error";
		}
		return "showAllDevice2";
	}

	private String isconsum;// 耗材类型变量

	public String getIsconsum() {
		return isconsum;
	}

	public void setIsconsum(String isconsum) {
		this.isconsum = isconsum;
	}

	/**
	 * 通过是否耗材筛选出该设备信息
	 * 
	 * @return
	 */
	public String selectDevice() {
		try {
			final String hql = "from Device d where d.isconsum='" + isconsum + "' order by d.deviceId desc";
			this.pageBean = pageService.queryForPage(hql, 10, page);
		} catch (Exception e) {
			addExceptionLog(e, "显示设备异常");
			return "error";
		}
		return "showAllDevice2";
	}

	/**
	 * 根据判断是否耗材进行相应的分页显示设备信息
	 * 
	 * @return
	 */
	public String selectDevice2() {
		try {
			if (isconsum == "" || isconsum == null || isconsum.equals("")) {
				final String hql = "from Device d order by d.deviceId desc";
				this.pageBean = pageService.queryForPage(hql, 10, page);
				return "showAllDevice2";
			} else {
				return selectDevice();
			}
		} catch (Exception e) {
			addExceptionLog(e, "显示设备异常");
			return "error";
		}
	}

	/**
	 * 设备申请——显示设备详情信息(用户查看)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showDetailDevice() {
		try {
			Device d = deviceService.findById(id);
			User u = userService.findUserById(d.getManagerId());
			DeviceForm df = new DeviceForm();
			df.setDeviceName(d.getDeviceName());
			df.setDeviceType(d.getDeviceType());
			df.setVersion(d.getVersion());
			df.setProtocol(d.getProtocol());
			df.setDeviceCount(d.getDeviceCount());
			List<Deviceapply> das = deviceApplyService.showDeviceapply(id);
			DeviceapplyForm daf = new DeviceapplyForm();
			for (Deviceapply line1 : das) {
				daf.setBorrowCount(line1.getBorrowCount());
				df.setRemainingCount(df.getDeviceCount() - daf.getBorrowCount());
			}
			df.setManager(u.getName());
			df.setIsconsum(d.getIsconsum());
			df.setState(d.getState());
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("devices", df);
		} catch (Exception e) {
			addExceptionLog(e, "显示设备详情异常");
			return "error";
		}
		return "showDetailDevice";
	}

	/**
	 * 设备申请——设备资源申请实现(用户申请)
	 * 
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public String deviceApply() throws ParseException {
		try {
			Device d = deviceService.findById(id);
			List<Deviceapply> das = deviceApplyService.showDeviceapply(id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(sdf.format(new Date()));
			DeviceForm df = new DeviceForm();
			DeviceapplyForm daf = new DeviceapplyForm();
			df.setDeviceId(d.getDeviceId());
			df.setDeviceName(d.getDeviceName());
			df.setDeviceType(d.getDeviceType());
			df.setVersion(d.getVersion());
			df.setProtocol(d.getProtocol());
			df.setDeviceCount(d.getDeviceCount());
			for (Deviceapply line1 : das) {
				daf.setBorrowCount(line1.getBorrowCount());
			}
			df.setBorrowCount(daf.getBorrowCount());
			df.setIsconsum(d.getIsconsum());
			df.setState(d.getState());
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("devices", df);
		} catch (Exception e) {
			addExceptionLog(e, "显示设备借用情况异常");
			return "error";
		}
		return "deviceApply";
	}

	/**
	 * 刷新申请后的设备资源信息(用户申请)
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String updateDeviceApply() throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(sdf.format(new Date()));
			Device d = deviceService.findById(id);
			System.out.println("id:%%%%%" + id);
			DeviceForm df = new DeviceForm();
			// System.out.println("数量：" + device.getBorrowCount());
			deviceService.update(d);
		} catch (Exception e) {
			addExceptionLog(e, "修改设备申请异常");
			return "error";
		}
		return "updateDeviceApply";
	}

	/**
	 * =======================设备资源管理============================================
	 */
	private Sim sim;// 获取sim卡类型变量

	public Sim getSim() {
		return sim;
	}

	public void setSim(Sim sim) {
		this.sim = sim;
	}

	/**
	 * 显示所有的sim信息(资源管理者权限)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showAllSim() {
		try {
			final String hql = "from Sim s order by s.id desc";
			this.pageBean = pageService.queryForPage(hql, 10, page);
			List<SimForm> list = new ArrayList<SimForm>();
			for (Object line : pageBean.getList()) {
				SimForm s = new SimForm();
				User u = userService.findUserById(((Sim) line).getManagerId());
				s.setId(((Sim) line).getId());
				s.setPhone(((Sim) line).getPhone());
				s.setICCID(((Sim) line).getICCID());
				s.setOperator(((Sim) line).getOperator());
				s.setPuk(((Sim) line).getPuk());
				s.setServicePassword(((Sim) line).getServicePassword());
				s.setDescription(((Sim) line).getDescription());
				s.setGprs(((Sim) line).getGprs());
				s.setMessage(((Sim) line).getMessage());
				s.setCallPhone(((Sim) line).getCallPhone());
				s.setManager(u.getName());
				list.add(s);
			}
			pageBean.setList(list);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showAllSim";
	}

	/**
	 * 添加sim卡信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String addSim() throws ParseException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Sim s = new Sim();
			System.out.println("sim卡:" + sim.getId());
			s.setId(sim.getId());
			s.setPhone(sim.getPhone());
			s.setICCID(sim.getICCID());
			s.setOperator(sim.getOperator());
			s.setPuk(sim.getPuk());
			s.setServicePassword(sim.getServicePassword());
			s.setDescription(sim.getDescription());
			s.setGprs(sim.getGprs());
			s.setMessage(sim.getMessage());
			s.setCallPhone(sim.getCallPhone());
			s.setManagerId(u.getUserId());
			simService.save(s);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "addSim";
	}

	/**
	 * 修改sim卡信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String updateSim() {
		try {
			Sim s = simService.findById(sim.getId());
			s.setPhone(sim.getPhone());
			s.setOperator(sim.getOperator());
			s.setICCID(sim.getICCID());
			s.setPuk(sim.getPuk());
			s.setServicePassword(sim.getServicePassword());
			s.setDescription(sim.getDescription());
			s.setGprs(sim.getGprs());
			s.setMessage(sim.getMessage());
			s.setCallPhone(sim.getCallPhone());
			simService.update(s);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "updateSim";
	}

	/**
	 * 删除SIM卡信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String deleteSim() {
		try {
			Sim s = simService.findById(id);
			simService.delete(s.getId());
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "deleteSim";
	}

	/**
	 * 批量删除SIM卡信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String deleteSelectedSim() {
		try {
			String checkedId[] = checkedIds.split(",");// 进行分割存到数组
			String temp = "";
			// System.out.println("设备ID:"+typeId);
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					// System.out.println(checkedId[i]);
					temp = checkedId[i]; // 保存更改
					System.out.println("序号：" + temp);
					simService.delete(Integer.parseInt(temp));
				}
			}
		} catch (NumberFormatException e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "deleteSelectedSim";
	}

	private String operator;// 定义运营商类型变量

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 查看Sim卡借用情况(资源管理者权限)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showSimBorrow() {
		try {
			System.out.println("sim卡编号" + id);
			Sim s = simService.findById(id);
			User u = userService.findUserById(s.getManagerId());
			User u1 = userService.findUserById(s.getBorrowerId());
			SimForm sf = new SimForm();
			sf.setPhone(s.getPhone());
			sf.setOperator(s.getOperator());
			sf.setManager(u.getName());
			sf.setBorrower(u1.getName());
			sf.setBorrowTime(s.getBorrowTime());
			sf.setReturnTime(s.getReturnTime());
			sf.setState(s.getState());
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("sims", sf);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showSimBorrow";
	}

	/**
	 * 显示所有的sim信息(用户查看)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showAllSim2() {
		try {
			final String hql = "from Sim s order by s.id desc";
			this.pageBean = pageService.queryForPage(hql, 10, page);
			setOperator("");
			List<SimForm> list = new ArrayList<SimForm>();
			for (Object line : pageBean.getList()) {
				SimForm s = new SimForm();
				User u = userService.findUserById(((Sim) line).getManagerId());
				s.setId(((Sim) line).getId());
				s.setPhone(((Sim) line).getPhone());
				s.setICCID(((Sim) line).getICCID());
				s.setOperator(((Sim) line).getOperator());
				s.setPuk(((Sim) line).getPuk());
				s.setServicePassword(((Sim) line).getServicePassword());
				s.setDescription(((Sim) line).getDescription());
				s.setManager(u.getName());
				s.setState(((Sim) line).getState());
				list.add(s);
			}
			pageBean.setList(list);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showAllSim2";
	}

	/**
	 * 通过运营商类型筛选出该该sim卡信息
	 * 
	 * @return
	 */
	public String selectSim() {
		try {
			final String hql = "from Sim s where s.operator='" + operator + "' order by s.id desc";
			this.pageBean = pageService.queryForPage(hql, 10, page);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showAllSim2";
	}

	/**
	 * 根据判断运营商类型来进行相应的分页显示sim卡信息
	 * 
	 * @return
	 */
	public String selectSim2() {
		try {
			if (operator == "" || operator == null || operator.equals("")) {
				final String hql = "from Sim s order by s.id desc";
				this.pageBean = pageService.queryForPage(hql, 10, page);
				return "showAllSim2";
			} else {
				return selectSim();
			}
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
	}

	/**
	 * 查看sim卡信息详情
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showDetailSim() {
		try {
			Sim s = simService.findById(id);
			User u = userService.findUserById(s.getManagerId());
			SimForm sf = new SimForm();
			sf.setPhone(s.getPhone());
			sf.setOperator(s.getOperator());
			sf.setICCID(s.getICCID());
			sf.setPuk(s.getPuk());
			sf.setManager(u.getName());
			sf.setServicePassword(s.getServicePassword());
			sf.setDescription(s.getDescription());
			sf.setGprs(s.getGprs());
			sf.setMessage(s.getMessage());
			sf.setCallPhone(s.getCallPhone());
			Map requestList = (Map) ActionContext.getContext().getSession();
			requestList.put("sims", sf);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showDetailSim";
	}

	/**
	 * =======================样机资源管理============================================
	 */
	private Prototype prototype;// 获取样机类型变量

	public Prototype getPrototype() {
		return prototype;
	}

	public void setPrototype(Prototype prototype) {
		this.prototype = prototype;
	}

	/**
	 * 显示所有的样机信息(资源管理者权限)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showAllPrototype() {
		try {
			final String hql = "from Prototype p order by p.id desc";
			this.pageBean = pageService.queryForPage(hql, 10, page);
			List<PrototypeForm> list = new ArrayList<PrototypeForm>();
			for (Object line : pageBean.getList()) {
				PrototypeForm p = new PrototypeForm();
				User u = userService.findUserById(((Prototype) line).getManagerId());
				p.setId(((Prototype) line).getId());
				p.setPrototypeName(((Prototype) line).getPrototypeName());
				p.setPrototypeType(((Prototype) line).getPrototypeType());
				p.setPrototypeCount(((Prototype) line).getPrototypeCount());
				p.setManager(u.getName());
				list.add(p);
			}
			pageBean.setList(list);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showAllPrototype";
	}

	/**
	 * 添加样机信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String addPrototype() throws ParseException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			Prototype p = new Prototype();
			p.setId(prototype.getId());
			p.setPrototypeName(prototype.getPrototypeName());
			p.setPrototypeType(prototype.getPrototypeType());
			p.setPrototypeCount(prototype.getPrototypeCount());
			p.setManagerId(u.getUserId());
			prototypeService.save(p);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "addPrototype";
	}

	/**
	 * 修改样机信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String updatePrototype() {
		try {
			System.out.println("样机id" + prototype.getId());
			Prototype p = prototypeService.findById(prototype.getId());
			p.setPrototypeName(prototype.getPrototypeName());
			p.setPrototypeType(prototype.getPrototypeType());
			p.setPrototypeCount(prototype.getPrototypeCount());
			prototypeService.update(p);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "updatePrototype";
	}

	/**
	 * 删除样机信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String deletePrototype() {
		try {
			Prototype p = prototypeService.findById(id);
			prototypeService.delete(p.getId());
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "deletePrototype";
	}

	/**
	 * 批量删除样机信息(资源管理者权限)
	 * 
	 * @return
	 */
	public String deleteSelectedPrototype() {
		try {
			String checkedId[] = checkedIds.split(",");// 进行分割存到数组
			String temp = "";
			// System.out.println("设备ID:"+typeId);
			for (int i = 0; i < checkedId.length; i++) {
				if (!checkedId[i].equals("")) {
					// System.out.println(checkedId[i]);
					temp = checkedId[i]; // 保存更改
					System.out.println("序号：" + temp);
					prototypeService.delete(Integer.parseInt(temp));
				}
			}
		} catch (NumberFormatException e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "deleteSelectedPrototype";
	}

	/**
	 * 查看样机借用情况(资源管理者权限)
	 * 
	 * @return
	 */
	public String showPrototypeBorrow() {
		try {
			final String hql = "from Prototype p where p.id ='" + id + "'";
			this.pageBean = pageService.queryForPage(hql, 10, page);
			ArrayList<PrototypeForm> pfs = new ArrayList<PrototypeForm>();
			for (Object line : pageBean.getList()) {
				PrototypeForm pf = new PrototypeForm();
				User u = userService.findUserById(((Prototype) line).getBorrowerId());
				Prototype p = prototypeService.findById(((Prototype) line).getId());
				pf.setBorrower(u.getName());
				pf.setPrototypeName(p.getPrototypeName());
				pf.setPrototypeType(p.getPrototypeType());
				pf.setPrototypeCount(p.getPrototypeCount());
				pf.setBorrowCount(p.getBorrowCount());
				pf.setBorrowTime(p.getBorrowTime());
				pf.setReturnTime(p.getReturnTime());
				pf.setState(p.getState());
				pfs.add(pf);
			}
			pageBean.setList(pfs);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showPrototypeBorrow";
	}

	/**
	 * 显示所有的样机信息(用户查看)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showAllPrototype2() {
		try {
			final String hql = "from Prototype p order by p.id desc";
			this.pageBean = pageService.queryForPage(hql, 10, page);
			List<PrototypeForm> list = new ArrayList<PrototypeForm>();
			for (Object line : pageBean.getList()) {
				PrototypeForm p = new PrototypeForm();
				User u = userService.findUserById(((Prototype) line).getManagerId());
				p.setId(((Prototype) line).getId());
				p.setPrototypeName(((Prototype) line).getPrototypeName());
				p.setPrototypeType(((Prototype) line).getPrototypeType());
				p.setPrototypeCount(((Prototype) line).getPrototypeCount());
				p.setBorrowCount(((Prototype) line).getBorrowCount());
				p.setManager(u.getName());
				p.setState(((Prototype) line).getState());
				list.add(p);
			}
			pageBean.setList(list);
		} catch (Exception e) {
			addExceptionLog(e, "异常");
			return "error";
		}
		return "showAllPrototype2";
	}
	public void addExceptionLog(Exception e,String str){
		//捕获异常信息，并将异常信息写入到数据库中，方便后期调试分析
		 StringWriter sw = new StringWriter();
		 e.printStackTrace(new PrintWriter(sw));
		 System.out.println("dsaf:"+sw.getBuffer().toString()+"----------");
//		 System.out.println(e.getStackTrace());
		 exceptionLogService.addLogMsg(str+":"+sw.getBuffer().toString());
	}
}
