package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class CustomizeSheetsLogModel {

	private String sheetName;
	private String action;
	private Date date;
	private Long sheetId;
	private String userInfo;

	public CustomizeSheetsLogModel() {
		super();
	}

	public CustomizeSheetsLogModel(String sheetName, String action, Date date, Long sheetId, String userInfo) {
		super();
		this.sheetName = sheetName;
		this.action = action;
		this.date = date;
		this.sheetId = sheetId;
		this.userInfo = userInfo;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getSheetId() {
		return sheetId;
	}

	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

}
