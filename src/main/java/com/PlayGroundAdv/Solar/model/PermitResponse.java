package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class PermitResponse {

	private Long id;
	private String homeName;
	private String avancement;
	private String status;
	private Date creationPermitDate;
	private Date updatedDate;
	private Boolean Submitted;
	private String homeOwnLastName;
	private String projectName;

	public PermitResponse() {
		super();
	}

	public PermitResponse(Long id, String homeName, String avancement, String status, Date creationPermitDate,
			Date updatedDate, Boolean submitted, String homeOwnLastName, String projectName) {
		super();
		this.id = id;
		this.homeName = homeName;
		this.avancement = avancement;
		this.status = status;
		this.creationPermitDate = creationPermitDate;
		this.updatedDate = updatedDate;
		this.Submitted = submitted;
		this.homeOwnLastName = homeOwnLastName;
		this.projectName = projectName;
	}
	
	public PermitResponse(Long id, String homeName, String avancement, String status, Date creationPermitDate,
			Date updatedDate, Boolean submitted, String projectName) {
		super();
		this.id = id;
		this.homeName = homeName;
		this.avancement = avancement;
		this.status = status;
		this.creationPermitDate = creationPermitDate;
		this.updatedDate = updatedDate;
		this.Submitted = submitted;
		this.projectName = projectName;
	}

	public PermitResponse(Long id, String homeName, String avancement, String status, Date creationPermitDate,
			Date updatedDate, Boolean submitted) {
		super();
		this.id = id;
		this.homeName = homeName;
		this.avancement = avancement;
		this.status = status;
		this.creationPermitDate = creationPermitDate;
		this.updatedDate = updatedDate;
		this.Submitted = submitted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	public String getAvancement() {
		return avancement;
	}

	public void setAvancement(String avancement) {
		this.avancement = avancement;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationPermitDate;
	}

	public void setCreationDate(Date creationPermitDate) {
		this.creationPermitDate = creationPermitDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the submitted
	 */
	public Boolean getSubmitted() {
		return Submitted;
	}

	/**
	 * @param submitted the submitted to set
	 */
	public void setSubmitted(Boolean submitted) {
		Submitted = submitted;
	}

	public String getHomeOwnLastName() {
		return homeOwnLastName;
	}

	public void setHomeOwnLastName(String homeOwnLastName) {
		this.homeOwnLastName = homeOwnLastName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
