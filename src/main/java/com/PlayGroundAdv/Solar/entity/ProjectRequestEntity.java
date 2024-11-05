package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name ="ProjectRequestEntity")
public class ProjectRequestEntity {
	
	@Id
	@SequenceGenerator(name="projectRequests_sequence", sequenceName = "projectRequests_sequence", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="projectRequests_sequence")  
	private Long id;
	
	@Column(name="FIRST_NAME_USER")
	private String firstNameUser;
	
	@Column(name="LAST_NAME_USER")
	private String lastNameUser;
	
	@Column(name="REQUEST_TITLE")
	private String requestTitle;
	
	@Column(name="REQUESTED_BY")
	private String requestedBy;
	
	@Column(name="FIRST_NAME_PROJECT_CONTACT")
	private String firstNameProjContact;
	
	@Column(name="LAST_NAME_PROJECT_CONTACT")
	private String lastNameProjContact;
	
	@Column(name="PROJECT_CONTACT_PHONE")
	private String projectContactPhone;
	
	@Column(name="PROJECT_CONTACT_EMAIL")
	private String projectContactEmail;
	
	@Column(name="REQUEST")
	private String request;
	
	@Column(name="REQUEST_METHOD")
	private String requestMethod;
	
	@Column(name="DATE_ADD_NOTIF")
	private Date dateAddNotif;
	
	@Column(name="DATE_REQUEST_STRING")
	private String daterequestString;
	
	@Column(name="TIME")
	private String time;
	
	@Column(name="LAST_UPDATED")
	private Date lastUpdated;
	
	@JoinColumn(name = "PERMIT")
	@ManyToOne
	private PermitEntity permit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
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

	public String getProjectContactPhone() {
		return projectContactPhone;
	}

	public void setProjectContactPhone(String projectContactPhone) {
		this.projectContactPhone = projectContactPhone;
	}

	public String getProjectContactEmail() {
		return projectContactEmail;
	}

	public void setProjectContactEmail(String projectContactEmail) {
		this.projectContactEmail = projectContactEmail;
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

	public PermitEntity getPermit() {
		return permit;
	}

	public void setPermit(PermitEntity permit) {
		this.permit = permit;
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

	@Override
	public String toString() {
		return "ProjectRequestEntity [id=" + id + ", firstNameUser=" + firstNameUser + ", lastNameUser=" + lastNameUser
				+ ", requestTitle=" + requestTitle + ", requestedBy=" + requestedBy + ", firstNameProjContact="
				+ firstNameProjContact + ", lastNameProjContact=" + lastNameProjContact + ", projectContactPhone="
				+ projectContactPhone + ", projectContactEmail=" + projectContactEmail + ", request=" + request
				+ ", requestMethod=" + requestMethod + ", dateAddNotif=" + dateAddNotif + ", daterequestString="
				+ daterequestString + ", time=" + time + ", lastUpdated=" + lastUpdated + ", permit=" + permit + "]";
	}


}
