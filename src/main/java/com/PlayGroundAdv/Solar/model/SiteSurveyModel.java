package com.PlayGroundAdv.Solar.model;

public class SiteSurveyModel {

	private Long siteSurveyID;
	private String homeOwnName;
	private String streetAddress;
	private String basicTypeOfSystem;
	private String status;
	private String creationDate;
	private String lastUpdatedDate;
	private String dateOfSubmit;
	private Boolean submitted;
	private Long ownerID;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerCompany;
	private Boolean hasProjectSiteImage;

	// CR 2860
	private String addressLine2;
	private Boolean roofMounted;
	private Boolean groundMounted;
	private Boolean carportMounted;
	private String TextOther;
	private String homeOwnLastName;
	private String projectName;
	private Boolean patioMounted;
	private Long totalElements;

	public SiteSurveyModel() {
		super();
	}

	public SiteSurveyModel(Long siteSurveyID, String homeOwnName, String streetAddress, String basicTypeOfSystem,
			String status, String creationDate, String lastUpdatedDate, String dateOfSubmit, Boolean submitted,
			Long ownerID, String ownerFirstName, String ownerLastName, String ownerCompany,
			Boolean hasProjectSiteImage, String addressLine2, Boolean roofMounted, Boolean groundMounted,
			Boolean carportMounted, String TextOther, String homeOwnLastName, String projectName,
			Boolean patioMounted) {
		super();
		this.siteSurveyID = siteSurveyID;
		this.homeOwnName = homeOwnName;
		this.streetAddress = streetAddress;
		this.basicTypeOfSystem = basicTypeOfSystem;
		this.status = status;
		this.creationDate = creationDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.dateOfSubmit = dateOfSubmit;
		this.submitted = submitted;
		this.ownerID = ownerID;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerCompany = ownerCompany;
		this.hasProjectSiteImage = hasProjectSiteImage;
		this.addressLine2 = addressLine2;
		this.roofMounted = roofMounted;
		this.groundMounted = groundMounted;
		this.carportMounted = carportMounted;
		this.TextOther = TextOther;
		this.homeOwnLastName = homeOwnLastName;
		this.projectName = projectName;
		this.patioMounted = patioMounted;
	}

	public SiteSurveyModel(Long siteSurveyID, String homeOwnName, String homeOwnLastName, String projectName,
			String streetAddress, String basicTypeOfSystem, String status, String creationDate, String lastUpdatedDate,
			String dateOfSubmit, Boolean submitted, Long ownerID, String ownerFirstName,
			String ownerLastName, String ownerCompany, Boolean hasProjectSiteImage, Long totalElements) {
		super();
		this.siteSurveyID = siteSurveyID;
		this.homeOwnName = homeOwnName;
		this.homeOwnLastName = homeOwnLastName;
		this.projectName = projectName;
		this.streetAddress = streetAddress;
		this.basicTypeOfSystem = basicTypeOfSystem;
		this.status = status;
		this.creationDate = creationDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.dateOfSubmit = dateOfSubmit;
		this.submitted = submitted;
		this.ownerID = ownerID;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.ownerCompany = ownerCompany;
		this.hasProjectSiteImage = hasProjectSiteImage;
		this.totalElements = totalElements ;
	}
	
	

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Long getSiteSurveyID() {
		return siteSurveyID;
	}

	public void setSiteSurveyID(Long siteSurveyID) {
		this.siteSurveyID = siteSurveyID;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getDateOfSubmit() {
		return dateOfSubmit;
	}

	public void setDateOfSubmit(String dateOfSubmit) {
		this.dateOfSubmit = dateOfSubmit;
	}

	public Boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(Boolean submitted) {
		this.submitted = submitted;
	}

	public Long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(Long ownerID) {
		this.ownerID = ownerID;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerCompany() {
		return ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	public Boolean getHasProjectSiteImage() {
		return hasProjectSiteImage;
	}

	public void setHasProjectSiteImage(Boolean hasProjectSiteImage) {
		this.hasProjectSiteImage = hasProjectSiteImage;
	}

	public Boolean getSubmitted() {
		return submitted;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Boolean getRoofMounted() {
		return roofMounted;
	}

	public void setRoofMounted(Boolean roofMounted) {
		this.roofMounted = roofMounted;
	}

	public Boolean getGroundMounted() {
		return groundMounted;
	}

	public void setGroundMounted(Boolean groundMounted) {
		this.groundMounted = groundMounted;
	}

	public Boolean getCarportMounted() {
		return carportMounted;
	}

	public void setCarportMounted(Boolean carportMounted) {
		this.carportMounted = carportMounted;
	}

	public String getTextOther() {
		return TextOther;
	}

	public void setTextOther(String textOther) {
		TextOther = textOther;
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
	
	public Boolean getPatioMounted() {
		return patioMounted;
	}

	public void setPatioMounted(Boolean patioMounted) {
		this.patioMounted = patioMounted;
	}

	@Override
	public String toString() {
		return "SiteSurveyModel [siteSurveyID=" + siteSurveyID + ", homeOwnName=" + homeOwnName + ", streetAddress="
				+ streetAddress + ", basicTypeOfSystem=" + basicTypeOfSystem + ", status=" + status + ", creationDate="
				+ creationDate + ", lastUpdatedDate=" + lastUpdatedDate + ", dateOfSubmit=" + dateOfSubmit
				+ ", submitted=" + submitted + ", ownerID=" + ownerID + ", ownerFirstName=" + ownerFirstName
				+ ", ownerLastName=" + ownerLastName + ", ownerCompany=" + ownerCompany + ", hasProjectSiteImage="
				+ hasProjectSiteImage + ", addressLine2=" + addressLine2 + ", roofMounted=" + roofMounted
				+ ", groundMounted=" + groundMounted + ", carportMounted="
				+ carportMounted + ", TextOther=" + TextOther + "]";
	}

}
