package com.wind.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.wind.entity.Projectcost;
import com.wind.service.ProjectcostService;
/*
 * 项目成本Action层
 */
public class ProjectcostAction {
	private ProjectcostService projectcostService;
	public void setProjectcostService(ProjectcostService projectcostService) {
		this.projectcostService = projectcostService;
	}
	private List<Projectcost> projectcosts;
	
	public List<Projectcost> getProjectcosts() {
		return projectcosts;
	}

	public void setProjectcosts(List<Projectcost> projectcosts) {
		this.projectcosts = projectcosts;
	}

	@SuppressWarnings("unchecked")
	public String showAllProjectcost(){
		projectcosts=projectcostService.findAll();
		Map requestList = (Map) ActionContext.getContext().getSession();
		requestList.put("projectcosts", projectcosts);
		return "showAllProjectcost";
	}
}
