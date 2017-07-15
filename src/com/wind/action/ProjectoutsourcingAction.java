package com.wind.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.wind.entity.Projectoutsourcing;
import com.wind.service.ProjectoutsourcingService;

public class ProjectoutsourcingAction {
	private ProjectoutsourcingService projectoutsourcingService;
	public void setProjectoutsourcingService(ProjectoutsourcingService projectoutsourcingService) {
		this.projectoutsourcingService = projectoutsourcingService;
	}
	private List<Projectoutsourcing> projectoutsourcings;
	
	public List<Projectoutsourcing> getProjecthumans() {
		return projectoutsourcings;
	}

	public void setProjectoutsourcings(List<Projectoutsourcing> projectoutsourcings) {
		this.projectoutsourcings = projectoutsourcings;
	}

	@SuppressWarnings("unchecked")
	public String showAllProjectoutsourcing(){
		projectoutsourcings=projectoutsourcingService.findAll();
		Map requestList = (Map) ActionContext.getContext().getSession();
		requestList.put("projectoutsourcings", projectoutsourcings);
		return "showAllProjectoutsourcing";
	}
}
