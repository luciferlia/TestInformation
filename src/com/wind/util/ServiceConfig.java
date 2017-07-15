package com.wind.util;

import com.opensymphony.xwork2.ActionSupport;
import com.wind.permission.service.FunctionService;
import com.wind.permission.service.MenuService;
import com.wind.permission.service.ModuleService;
import com.wind.permission.service.RoleFunctionService;
import com.wind.permission.service.RolePermissionService;
import com.wind.permission.service.RoleService;
import com.wind.service.AdminService;
import com.wind.service.CaseResultService;
import com.wind.service.CaseService;
import com.wind.service.CaseTypeService;
import com.wind.service.CasestoreService;
import com.wind.service.DeviceApplyService;
import com.wind.service.DeviceService;
import com.wind.service.ExceptionLogService;
import com.wind.service.HqlService;
import com.wind.service.LogSettingService;
import com.wind.service.PageService;
import com.wind.service.PlanService;
import com.wind.service.PlantailService;
import com.wind.service.PolicyService;
import com.wind.service.PolicypoolService;
import com.wind.service.ProjectService;
import com.wind.service.ProjectUserService;
import com.wind.service.PrototypeService;
import com.wind.service.SelectTypeService;
import com.wind.service.SimService;
import com.wind.service.TestCaseService;
import com.wind.service.UserService;

public class ServiceConfig extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected  CasestoreService csService;
	protected CaseTypeService ctService;
	protected UserService userService;
	protected CaseService caseService;
	protected PageService pageService;
	protected RolePermissionService rolePermissionService;
	protected CaseResultService caseResultService;
	protected ExceptionLogService exceptionLogService;
	protected HqlService hqlService;
	protected LogSettingService logSettingService;
	protected RoleService roleService;
	protected ModuleService moduleService;
	protected MenuService menuService;
	protected SelectTypeService selectTypeService;
	protected PlantailService plantailService;
	protected PlanService planService;
	protected ProjectService projectService;
	protected CaseTypeService caseTypeServie;
	protected CasestoreService casestoreService;
	protected PolicypoolService policypoolService;
	protected CaseResultService caseresultService;
	protected AdminService adminService;
	protected PolicyService policyService;
	protected CaseTypeService caseTypeService;
	protected ProjectUserService projectUserService;
	protected DeviceService deviceService;
	protected DeviceApplyService deviceApplyService;
	protected SimService simService;
	protected PrototypeService prototypeService;
	protected CaseTypeService casetypeService;
	protected FunctionService functionService;
	protected RoleFunctionService roleFunctionService;
	protected TestCaseService testCaseService;
	
	public TestCaseService getTestCaseService() {
		return testCaseService;
	}
	public void setTestCaseService(TestCaseService testCaseService) {
		this.testCaseService = testCaseService;
	}
	public RoleFunctionService getRoleFunctionService() {
		return roleFunctionService;
	}
	public void setRoleFunctionService(RoleFunctionService roleFunctionService) {
		this.roleFunctionService = roleFunctionService;
	}
	public FunctionService getFunctionService() {
		return functionService;
	}
	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
	public CaseTypeService getCasetypeService() {
		return casetypeService;
	}
	public void setCasetypeService(CaseTypeService casetypeService) {
		this.casetypeService = casetypeService;
	}
	public DeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	public DeviceApplyService getDeviceApplyService() {
		return deviceApplyService;
	}
	public void setDeviceApplyService(DeviceApplyService deviceApplyService) {
		this.deviceApplyService = deviceApplyService;
	}
	public SimService getSimService() {
		return simService;
	}
	public void setSimService(SimService simService) {
		this.simService = simService;
	}
	public PrototypeService getPrototypeService() {
		return prototypeService;
	}
	public void setPrototypeService(PrototypeService prototypeService) {
		this.prototypeService = prototypeService;
	}
	public ProjectUserService getProjectUserService() {
		return projectUserService;
	}
	public void setProjectUserService(ProjectUserService projectUserService) {
		this.projectUserService = projectUserService;
	}
	public PolicyService getPolicyService() {
		return policyService;
	}
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}
	public CaseTypeService getCaseTypeService() {
		return caseTypeService;
	}
	public void setCaseTypeService(CaseTypeService caseTypeService) {
		this.caseTypeService = caseTypeService;
	}
	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public LogSettingService getLogSettingService() {
		return logSettingService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public ModuleService getModuleService() {
		return moduleService;
	}
	public MenuService getMenuService() {
		return menuService;
	}
	public SelectTypeService getSelectTypeService() {
		return selectTypeService;
	}
	public PlantailService getPlantailService() {
		return plantailService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	public void setSelectTypeService(SelectTypeService selectTypeService) {
		this.selectTypeService = selectTypeService;
	}
	public void setPlantailService(PlantailService plantailService) {
		this.plantailService = plantailService;
	}
	public void setLogSettingService(LogSettingService logSettingService) {
		this.logSettingService = logSettingService;
	}
	public void setCsService(CasestoreService csService) {
		this.csService = csService;
	}
	public void setCtService(CaseTypeService ctService) {
		this.ctService = ctService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}
	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}
	public void setRolePermissionService(RolePermissionService rolePermissionService) {
		this.rolePermissionService = rolePermissionService;
	}
	public void setCaseResultService(CaseResultService caseResultService) {
		this.caseResultService = caseResultService;
	}
	public void setExceptionLogService(ExceptionLogService exceptionLogService) {
		this.exceptionLogService = exceptionLogService;
	}
	public void setHqlService(HqlService hqlService) {
		this.hqlService = hqlService;
	}
	public CasestoreService getCsService() {
		return csService;
	}
	public CaseTypeService getCtService() {
		return ctService;
	}
	public UserService getUserService() {
		return userService;
	}
	public CaseService getCaseService() {
		return caseService;
	}
	public PageService getPageService() {
		return pageService;
	}
	public RolePermissionService getRolePermissionService() {
		return rolePermissionService;
	}
	public CaseResultService getCaseResultService() {
		return caseResultService;
	}
	public ExceptionLogService getExceptionLogService() {
		return exceptionLogService;
	}
	public HqlService getHqlService() {
		return hqlService;
	}
	public PlanService getPlanService() {
		return planService;
	}
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public CaseTypeService getCaseTypeServie() {
		return caseTypeServie;
	}
	public void setCaseTypeServie(CaseTypeService caseTypeServie) {
		this.caseTypeServie = caseTypeServie;
	}
	public CasestoreService getCasestoreService() {
		return casestoreService;
	}
	public void setCasestoreService(CasestoreService casestoreService) {
		this.casestoreService = casestoreService;
	}
	public PolicypoolService getPolicypoolService() {
		return policypoolService;
	}
	public void setPolicypoolService(PolicypoolService policypoolService) {
		this.policypoolService = policypoolService;
	}
	public CaseResultService getCaseresultService() {
		return caseresultService;
	}
	public void setCaseresultService(CaseResultService caseresultService) {
		this.caseresultService = caseresultService;
	}
	
	
}
