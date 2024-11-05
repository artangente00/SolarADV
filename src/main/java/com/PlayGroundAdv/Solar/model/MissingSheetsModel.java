package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class MissingSheetsModel {

	private String sheetName;
	private String sheetType;
	private Date date;
	private String project;
	private String userInfo;

	public MissingSheetsModel() {
		super();
	}

	public MissingSheetsModel(String sheetName, String sheetType, Date date, String project, String userInfo) {
		super();
		this.sheetName = sheetName;
		this.sheetType = sheetType;
		this.date = date;
		this.project = project;
		this.userInfo = userInfo;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

}
