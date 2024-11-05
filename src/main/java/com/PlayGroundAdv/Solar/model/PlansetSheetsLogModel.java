package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class PlansetSheetsLogModel {

	private String sheetName;
	private String sheetType;
	private String comment;
	private Date date;
	private String userInfo;

	public PlansetSheetsLogModel() {
		super();
	}

	public PlansetSheetsLogModel(String sheetName, String sheetType, String comment, Date date, String userInfo) {
		super();
		this.sheetName = sheetName;
		this.sheetType = sheetType;
		this.comment = comment;
		this.date = date;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

}
