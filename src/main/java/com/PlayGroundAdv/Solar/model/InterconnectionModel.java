package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class InterconnectionModel {

	private Long id;
	private String name;
	private String owner;
	private String contactClient;
	private String utility;
	private String interconnectionType;
	private String assignedTo;
	private Date intercRequested;
	private Date appFeeReceived;
	private Date utiReceivedAppFee;
	private Float appFeeAmount;
	private Date intercSubmitted;
	private Date customerSigned;
	private Date appReviewed;
	private Date ptoGranted;
	private Date informedofPTO;
	private String utilityInformation;
	private String accountHolder;
	private String utilityRef;
	private String notes;
	private String lastName;
	private String projectName;

	public InterconnectionModel() {
		super();
	}

	public InterconnectionModel(Long id, String name, String owner, String contactClient, String utility,
			String interconnectionType, String assignedTo, Date intercRequested, Date appFeeReceived,
			Date utiReceivedAppFee, Float appFeeAmount, Date intercSubmitted, Date customerSigned, Date appReviewed,
			Date ptoGranted, Date informedofPTO, String utilityInformation, String accountHolder, String utilityRef,
			String notes, String lastName, String projectName) {
		super();
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.contactClient = contactClient;
		this.utility = utility;
		this.interconnectionType = interconnectionType;
		this.assignedTo = assignedTo;
		this.intercRequested = intercRequested;
		this.appFeeReceived = appFeeReceived;
		this.utiReceivedAppFee = utiReceivedAppFee;
		this.appFeeAmount = appFeeAmount;
		this.intercSubmitted = intercSubmitted;
		this.customerSigned = customerSigned;
		this.appReviewed = appReviewed;
		this.ptoGranted = ptoGranted;
		this.informedofPTO = informedofPTO;
		this.utilityInformation = utilityInformation;
		this.accountHolder = accountHolder;
		this.utilityRef = utilityRef;
		this.notes = notes;
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

	public String getContactClient() {
		return contactClient;
	}

	public void setContactClient(String contactClient) {
		this.contactClient = contactClient;
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

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Date getIntercRequested() {
		return intercRequested;
	}

	public void setIntercRequested(Date intercRequested) {
		this.intercRequested = intercRequested;
	}

	public Date getAppFeeReceived() {
		return appFeeReceived;
	}

	public void setAppFeeReceived(Date appFeeReceived) {
		this.appFeeReceived = appFeeReceived;
	}

	public Date getUtiReceivedAppFee() {
		return utiReceivedAppFee;
	}

	public void setUtiReceivedAppFee(Date utiReceivedAppFee) {
		this.utiReceivedAppFee = utiReceivedAppFee;
	}

	public Float getAppFeeAmount() {
		return appFeeAmount;
	}

	public void setAppFeeAmount(Float appFeeAmount) {
		this.appFeeAmount = appFeeAmount;
	}

	public Date getIntercSubmitted() {
		return intercSubmitted;
	}

	public void setIntercSubmitted(Date intercSubmitted) {
		this.intercSubmitted = intercSubmitted;
	}

	public Date getCustomerSigned() {
		return customerSigned;
	}

	public void setCustomerSigned(Date customerSigned) {
		this.customerSigned = customerSigned;
	}

	public Date getAppReviewed() {
		return appReviewed;
	}

	public void setAppReviewed(Date appReviewed) {
		this.appReviewed = appReviewed;
	}

	public Date getPtoGranted() {
		return ptoGranted;
	}

	public void setPtoGranted(Date ptoGranted) {
		this.ptoGranted = ptoGranted;
	}

	public Date getInformedofPTO() {
		return informedofPTO;
	}

	public void setInformedofPTO(Date informedofPTO) {
		this.informedofPTO = informedofPTO;
	}

	public String getUtilityInformation() {
		return utilityInformation;
	}

	public void setUtilityInformation(String utilityInformation) {
		this.utilityInformation = utilityInformation;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getUtilityRef() {
		return utilityRef;
	}

	public void setUtilityRef(String utilityRef) {
		this.utilityRef = utilityRef;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	@Override
	public String toString() {
		return "InterconnectionModel [id=" + id + ", name=" + name + ", owner=" + owner + ", contactClient="
				+ contactClient + ", utility=" + utility + ", interconnectionType=" + interconnectionType
				+ ", assignedTo=" + assignedTo + ", intercRequested=" + intercRequested + ", appFeeReceived="
				+ appFeeReceived + ", utiReceivedAppFee=" + utiReceivedAppFee + ", appFeeAmount=" + appFeeAmount
				+ ", intercSubmitted=" + intercSubmitted + ", customerSigned=" + customerSigned + ", appReviewed="
				+ appReviewed + ", ptoGranted=" + ptoGranted + ", informedofPTO=" + informedofPTO
				+ ", utilityInformation=" + utilityInformation + ", accountHolder=" + accountHolder + ", utilityRef="
				+ utilityRef + ", notes=" + notes + "]";
	}

}
