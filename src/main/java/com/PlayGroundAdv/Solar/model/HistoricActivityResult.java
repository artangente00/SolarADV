package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class HistoricActivityResult {

	private Date date;
	private String ipuser;
	private String timeZoneUser;
	private String typeAction;
	private String firstName;
	private String lastName;
	private String numTab;
	private String sessionId;
	private String openDate;
	private Boolean isSuccess;

	public HistoricActivityResult() {
		super();
	}


	public HistoricActivityResult(Date date, String ipuser, String timeZoneUser, String typeAction, String firstName,
			String lastName, String numTab, String sessionId, String openDate,Boolean isSuccess) {

		super();
		this.date = date;
		this.ipuser = ipuser;
		this.timeZoneUser = timeZoneUser;
		this.typeAction = typeAction;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numTab = numTab;
		this.sessionId = sessionId;
		this.openDate = openDate;
		this.isSuccess = isSuccess;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIpuser() {
		return ipuser;
	}

	public void setIpuser(String ipuser) {
		this.ipuser = ipuser;
	}

	public String getTimeZoneUser() {
		return timeZoneUser;
	}

	public void setTimeZoneUser(String timeZoneUser) {
		this.timeZoneUser = timeZoneUser;
	}

	public String getTypeAction() {
		return typeAction;
	}

	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNumTab() {
		return numTab;
	}

	public void setNumTab(String numTab) {
		this.numTab = numTab;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	@Override
	public String toString() {
		return "HistoricActivityResult [date=" + date + ", ipuser=" + ipuser + ", timeZoneUser=" + timeZoneUser
				+ ", typeAction=" + typeAction + ", firstName=" + firstName + ", lastName=" + lastName + ", numTab="
				+ numTab + ", sessionId=" + sessionId + ", openDate=" + openDate + ", isSuccess=" + isSuccess + "]";
	}

}
