package com.wind.permission.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wind.entity.Function;
import com.wind.entity.Role;
import com.wind.entity.RoleFunction;
import com.wind.permission.dao.FunctionDAO;
import com.wind.permission.dao.RoleFunctionDAO;
import com.wind.permission.po.PermissionForm;

public class RolePermissionServiceImpl implements RolePermissionService {
	private FunctionDAO functionDAO;
	private RoleFunctionDAO roleFunctionDAO;

	public void setFunctionDAO(FunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}

	public void setRoleFunctionDAO(RoleFunctionDAO roleFunctionDAO) {
		this.roleFunctionDAO = roleFunctionDAO;
	}

	@Override
	public String getRolePermission(String name, String jsp) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Role r = (Role) session.getAttribute("role");
		Function f = functionDAO.findByNameAndJsp(name, jsp);
		RoleFunction rf = null;
		if (f != null)
			rf = roleFunctionDAO.findByRidAndFid(r.getRoleId(), f.getFunctionId());
		if (rf != null) {
			return f.getName();
		}
		return null;
	}

	@Override
	public PermissionForm getUserPermission(String jsp) {
		PermissionForm pf = new PermissionForm();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Role r = (Role) session.getAttribute("role");
		List<Function> functions = functionDAO.findByJsp(jsp);
		for (Function function : functions) {
			RoleFunction rf = roleFunctionDAO.findByRidAndFid(r.getRoleId(), function.getFunctionId());
			if (rf != null) {
				switch (function.getTag()) {
				case "add":
					pf.setAdd(function.getName());
					break;
				case "edit":
					pf.setEdit(function.getName());
					break;
				case "delete":
					pf.setDelete(function.getName());
					break;
				case "allEdit":
					pf.setAllEdit(function.getName());
					break;
				case "impor":
					pf.setImpor(function.getName());
					break;
				case "expor":
					pf.setExpor(function.getName());
					break;
				case "update":
					pf.setUpdate(function.getName());
					break;
				case "upload":
					pf.setUpload(function.getName());
					break;
				default:
					break;
				}
			}

		}
		return pf;
	}

}
