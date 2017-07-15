package com.wind.permission.po;

public class PermissionForm {
	private String edit;// 编辑
	private String delete;// 删除
	private String add;// 添加
	private String impor;// 导入
	private String expor;// 导出
	private String allEdit;// 批量编辑
	private String update;
	private String upload;// 上传

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getAllEdit() {
		return allEdit;
	}

	public void setAllEdit(String allEdit) {
		this.allEdit = allEdit;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getImpor() {
		return impor;
	}

	public void setImpor(String impor) {
		this.impor = impor;
	}

	public String getExpor() {
		return expor;
	}

	public void setExpor(String expor) {
		this.expor = expor;
	}
}
