package com.wind.action;

import java.util.List;

import com.wind.entity.Logsetting;
import com.wind.entity.Logsettingdetail;
import com.wind.entity.PageBean;
import com.wind.util.ServiceConfig;

/**
 * ������־���Action
 * @author huangmingliang
 *
 */
public class LogAction extends ServiceConfig{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Logsetting> logsettings;
	
	public List<Logsetting> getLogsettings() {
		return logsettings;
	}

	public void setLogsettings(List<Logsetting> logsettings) {
		this.logsettings = logsettings;
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
	/**
	 * ��ҳ��ʾ���е���־����
	 * @return
	 */
	public String showAllLog(){
		logsettings=logSettingService.findAll();
		String hql="from Logsetting";
		pageBean=pageService.queryForPage(hql, 10, page);
		for(Object l:pageBean.getList()){
			((Logsetting)l).setUser(userService.findUserById(((Logsetting)l).getUser().getUserId()));
		}
		return "showLog";
		
	}
	
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private List<Logsettingdetail> logsettingdetails;
	public List<Logsettingdetail> getLogsettingdetails() {
		return logsettingdetails;
	}

	public void setLogsettingdetails(List<Logsettingdetail> logsettingdetails) {
		this.logsettingdetails = logsettingdetails;
	}
	private String name;//ҵ������
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��ʾ��־��ϸ��Ϣ
	 * @return
	 */
	public String showLogDetail(){
		name=logSettingService.findById(id).getName();
		logsettingdetails=logSettingService.findByLid(id);
		return "showDetail";
	}
	
	/**
	 * ɾ����־��Ϣ
	 * @return
	 */
	public String deleteLog(){
		List<Logsettingdetail> lsts=logSettingService.findByLid(id);
		for(Logsettingdetail lst:lsts){
			logSettingService.deleteLog(lst);
		}
		List<Logsettingdetail> lsts1=logSettingService.findByLid(id);
		if(lsts1.size()==0){
			logSettingService.delete(logSettingService.findById(id));
		}
		return "deleteLog";
	}
}
