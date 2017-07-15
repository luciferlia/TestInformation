package com.wind.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class uploadAction extends ActionSupport {

	private File uploadFile;
	private String uploadFileFileName;
	private String uploadFileContentType;

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	@Override
	public String execute() throws Exception {
		String destPath = "D:\\";
		try {
			System.out.println("Src File name: " + uploadFile);
			System.out.println("Dst File name: " + uploadFileFileName);
			File destFile = new File(destPath, uploadFileFileName);
			FileUtils.copyFile(uploadFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
			return "uploadError";
		}
		return "uploadSuccess";
	}
}