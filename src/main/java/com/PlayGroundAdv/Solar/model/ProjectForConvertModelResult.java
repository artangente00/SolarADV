package com.PlayGroundAdv.Solar.model;

public class ProjectForConvertModelResult {

	private String homeOwnerName;
	private String homeOwnLastName;
	private String projectName;
	private String status;
	private String firstName;
	private String lastName;

	public ProjectForConvertModelResult() {
		super();
	}

	public ProjectForConvertModelResult(String homeOwnLastName, String homeOwnerName, String projectName, String status,
			String firstName, String lastName) {
		super();
		this.homeOwnLastName = homeOwnLastName;
		this.homeOwnerName = homeOwnerName;
		this.projectName = projectName;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getHomeOwnerName() {
		return homeOwnerName;
	}

	public void setHomeOwnerName(String homeOwnerName) {
		this.homeOwnerName = homeOwnerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@Override
	public String toString() {
		return "ProjectForConvertModelResult [homeOwnerName=" + homeOwnerName + ", homeOwnLastName=" + homeOwnLastName
				+ ", projectName=" + projectName + ", status=" + status + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

}
