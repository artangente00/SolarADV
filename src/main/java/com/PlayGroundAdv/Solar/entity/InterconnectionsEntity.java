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

/*
 * @author Soumeya
 */
@Entity
@Table(name = "InterconnectionsEntity")
public class InterconnectionsEntity {

private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="InterconnectionSequence",
			           sequenceName="InterconnectionSequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="InterconnectionSequence")  
	private Long id;
	
	@Column(name="CONTACT_CLIENT")
	private String contactClient;
	
	@Column(name="UTILITY")
	private String utility;
	
	@Column(name="INTERCNNECTION_TYPE")
	private String interconnectionType;
	
	@Column(name="ASSIGNED_TO")
	private String assignedTo;
	
	@Column(name="INTERC_REQUESTED")
	private Date intercRequested;
	
	@Column(name="APP_FEE_RECEIVED")
	private Date appFeeReceived;
	
	@Column(name="UTI_RECEIVED_APP_FEE")
	private Date utiReceivedAppFee;
	
	@Column(name="APP_FEE_AMOUNT")
	private Float appFeeAmount;
	
	@Column(name="INTERC_SUBMITTED")
	private Date intercSubmitted;
	
	@Column(name="CUSTOMER_SIGNED")
	private Date customerSigned;
	
	@Column(name="APP_REVIEWED")
	private Date appReviewed;
	
	@Column(name="PTO_GRANTED")
	private Date ptoGranted;
	
	@Column(name="INFORMED_OF_PTO")
	private Date informedofPTO;
	
	@Column(name="UTILITY_INFORMATION")
	private String utilityInformation;
	
	@Column(name="ACCOUNT_HOLDER")
	private String accountHolder;
	
	@Column(name="UTILITY_REF")
	private String utilityRef;
	
	@Column(name="NOTES")
	private String notes;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public Date getAppFeeReceived() {
		return appFeeReceived;
	}

	public void setAppFeeReceived(Date appFeeReceived) {
		this.appFeeReceived = appFeeReceived;
	}

	public Float getAppFeeAmount() {
		return appFeeAmount;
	}

	public void setAppFeeAmount(Float appFeeAmount) {
		this.appFeeAmount = appFeeAmount;
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

	public PermitEntity getPermit() {
		return permit;
	}

	public void setPermit(PermitEntity permit) {
		this.permit = permit;
	}

	public Date getIntercRequested() {
		return intercRequested;
	}

	public void setIntercRequested(Date intercRequested) {
		this.intercRequested = intercRequested;
	}

	public Date getUtiReceivedAppFee() {
		return utiReceivedAppFee;
	}

	public void setUtiReceivedAppFee(Date utiReceivedAppFee) {
		this.utiReceivedAppFee = utiReceivedAppFee;
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

	
	
}
