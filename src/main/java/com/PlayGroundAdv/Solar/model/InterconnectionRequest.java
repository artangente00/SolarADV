package com.PlayGroundAdv.Solar.model;

public class InterconnectionRequest {

	private Long id;
	private String name;
	private String owner;
	private String company;
	private String utility;
	private String interconnectionType;
	private String utilityInformation;
	private String utilityRef;
	private String lastName;
	private String projectName;

	public InterconnectionRequest() {
		super();
	}

	public InterconnectionRequest(Long id, String name, String owner, String company, String utility,
			String interconnectionType, String utilityInformation, String utilityRef, String lastName,
			String projectName) {
		super();
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.company = company;
		this.utility = utility;
		this.interconnectionType = interconnectionType;
		this.utilityInformation = utilityInformation;
		this.utilityRef = utilityRef;
		this.lastName = lastName;
		this.projectName = projectName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getUtility() {
		return utility;
	}

	public void setUtility(String utility) {
		this.utility = utility;
	}

	public String getInterconnectionType() {
		return interconnectionType;
	}

	public void setInterconnectionType(String interconnectionType) {
		this.interconnectionType = interconnectionType;
	}

	public String getUtilityInformation() {
		return utilityInformation;
	}

	public void setUtilityInformation(String utilityInformation) {
		this.utilityInformation = utilityInformation;
	}

	public String getUtilityRef() {
		return utilityRef;
	}

	public void setUtilityRef(String utilityRef) {
		this.utilityRef = utilityRef;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
