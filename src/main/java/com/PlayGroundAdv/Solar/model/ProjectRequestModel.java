package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class ProjectRequestModel {
	private Long id;
	private String firstNameUser;
	private String lastNameUser;
	private String requestTitle;
	private String requestedBy;
	private String firstNameProjContact;
	private String lastNameProjContact;
	private String request;
	private String requestMethod;
	private Date dateAddNotif;
	private String daterequestString;
	private String time;
	private Date lastUpdated;

	public ProjectRequestModel() {
		super();
	}

	public ProjectRequestModel(Long id, String firstNameUser, String lastNameUser, String requestTitle,
			String requestedBy, String firstNameProjContact, String lastNameProjContact, String request,
			String requestMethod, Date dateAddNotif, String daterequestString, String time, Date lastUpdated) {
		super();
		this.id = id;
		this.firstNameUser = firstNameUser;
		this.lastNameUser = lastNameUser;
		this.requestTitle = requestTitle;
		this.requestedBy = requestedBy;
		this.firstNameProjContact = firstNameProjContact;
		this.lastNameProjContact = lastNameProjContact;
		this.request = request;
		this.requestMethod = requestMethod;
		this.dateAddNotif = dateAddNotif;
		this.daterequestString = daterequestString;
		this.time = time;
		this.lastUpdated = lastUpdated;
	}

	public ProjectRequestModel(Long id, String requestedBy, String firstNameProjContact, String lastNameProjContact,
			Date dateAddNotif, String daterequestString, String time, Date lastUpdated) {
		super();
		this.id = id;
		this.requestedBy = requestedBy;
		this.firstNameProjContact = firstNameProjContact;
		this.lastNameProjContact = lastNameProjContact;
		this.dateAddNotif = dateAddNotif;
		this.daterequestString = daterequestString;
		this.time = time;
		this.lastUpdated = lastUpdated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getFirstNameProjContact() {
		return firstNameProjContact;
	}

	public void setFirstNameProjContact(String firstNameProjContact) {
		this.firstNameProjContact = firstNameProjContact;
	}

	public String getLastNameProjContact() {
		return lastNameProjContact;
	}

	public void setLastNameProjContact(String lastNameProjContact) {
		this.lastNameProjContact = lastNameProjContact;
	}

	public Date getDateAddNotif() {
		return dateAddNotif;
	}

	public void setDateAddNotif(Date dateAddNotif) {
		this.dateAddNotif = dateAddNotif;
	}

	public String getDaterequestString() {
		return daterequestString;
	}

	public void setDaterequestString(String daterequestString) {
		this.daterequestString = daterequestString;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getFirstNameUser() {
		return firstNameUser;
	}

	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}

	public String getLastNameUser() {
		return lastNameUser;
	}

	public void setLastNameUser(String lastNameUser) {
		this.lastNameUser = lastNameUser;
	}

	public String getRequestTitle() {
		return requestTitle;
	}

	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

}
