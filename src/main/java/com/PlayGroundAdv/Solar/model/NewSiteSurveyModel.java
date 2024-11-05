package com.PlayGroundAdv.Solar.model;

public class NewSiteSurveyModel {

	private String homeOwnName;
	private String streetAddress;
	private String basicTypeOfSystem;
	private String ownerID;
	private String state;
	private String homeOwnLastName;
	private String projectName;

	public NewSiteSurveyModel() {
		super();
	}

	public NewSiteSurveyModel(String homeOwnName, String streetAddress, String basicTypeOfSystem, String ownerID,
			String state, String homeOwnLastName, String projectName) {
		super();

		this.homeOwnName = homeOwnName;
		this.streetAddress = streetAddress;
		this.basicTypeOfSystem = basicTypeOfSystem;
		this.ownerID = ownerID;
		this.state = state;
		this.homeOwnLastName = homeOwnLastName;
		this.projectName = projectName;
	}

	public String getHomeOwnName() {
		return homeOwnName;
	}

	public void setHomeOwnName(String homeOwnName) {
		this.homeOwnName = homeOwnName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getBasicTypeOfSystem() {
		return basicTypeOfSystem;
	}

	public void setBasicTypeOfSystem(String basicTypeOfSystem) {
		this.basicTypeOfSystem = basicTypeOfSystem;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
		return "NewSiteSurveyModel [homeOwnName=" + homeOwnName + ", streetAddress=" + streetAddress
				+ ", basicTypeOfSystem=" + basicTypeOfSystem + ", ownerID=" + ownerID + "]";
	}

}
