package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PermitResponsePrime {

	private Long id;
	private String homeName;
	private String avancement;
	private String status;
	private Date updatedDate;
	private String updatedBy;
	private Boolean submitted;
	private Date creationPermitDate;
	private String firstName;
	private String lastName;
	private String project;
	private String createdBy;
	private Date dateOfSubmitPermitOnHold;
	private Date dateOfSubmitPermit;
	private Boolean isCanceled;
	private Date dateOfSubmitPermitCanceled;
	private Boolean isOnHold;
	private Boolean hasRFISheet;
	private String projectName;

	private String projectNameComb;
	private String companyComb;

	// A.B: 05-09-2019 Google Drive PlansetLink
	private String plansetDocumentId;
	private String homeOwnLastName;
	private String plansetDriveId;
	private String company;

	// A.B: 11-29-2022 CR-1259-MOD-002: Archive Delivered Project
	private String archiveLink;
	private String archiveStatus;
	private Date archiveDate;

//	Used for getSearchedPermitsDrafter // all permit
	public PermitResponsePrime(Long id, String homeName, String avancement, String status, Date updatedDate,
			String updatedBy, Boolean submitted, Date creationPermitDate, String firstName, String lastName,
			Date dateOfSubmitPermitOnHold, Date dateOfSubmitPermit, Boolean isCanceled, Date dateOfSubmitPermitCanceled,
			Boolean isOnHold, String projectName, String homeOwnLastName, String plansetDriveId, String company,
			String projectNameComb, String companyComb, String archiveLink, String archiveStatus, Date archiveDate) {
		super();
		this.id = id;
		this.homeName = homeName;
		this.avancement = avancement;
		this.status = status;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.submitted = submitted;
		this.creationPermitDate = creationPermitDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfSubmitPermitOnHold = dateOfSubmitPermitOnHold;
		this.dateOfSubmitPermit = dateOfSubmitPermit;
		this.isCanceled = isCanceled;
		this.dateOfSubmitPermitCanceled = dateOfSubmitPermitCanceled;
		this.isOnHold = isOnHold;
		this.hasRFISheet = hasRFISheet;
		this.projectName = projectName;
		this.homeOwnLastName = homeOwnLastName;
		this.plansetDriveId = plansetDriveId;
		this.company = company;
		this.projectNameComb = projectNameComb;
		this.companyComb = companyComb;
		this.archiveLink = archiveLink;
		this.archiveStatus = archiveStatus;
		this.archiveDate = archiveDate;
	}

	// Get Project List
	public PermitResponsePrime(Long id, String homeName, String avancement, String status, Date updatedDate,
			Boolean submitted, Date creationPermitDate, String firstName, String lastName,
			Date dateOfSubmitPermitOnHold, Date dateOfSubmitPermit, Boolean isCanceled, Date dateOfSubmitPermitCanceled,
			Boolean isOnHold, String projectName, String homeOwnLastName, String plansetDriveId, String company,
			String projectNameComb, String companyComb, String archiveLink, String archiveStatus, Date archiveDate) {
		super();
		this.id = id;
		this.homeName = homeName;
		this.avancement = avancement;
		this.status = status;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.submitted = submitted;
		this.creationPermitDate = creationPermitDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfSubmitPermitOnHold = dateOfSubmitPermitOnHold;
		this.dateOfSubmitPermit = dateOfSubmitPermit;
		this.isCanceled = isCanceled;
		this.dateOfSubmitPermitCanceled = dateOfSubmitPermitCanceled;
		this.isOnHold = isOnHold;
		this.hasRFISheet = hasRFISheet;
		this.projectName = projectName;
		this.homeOwnLastName = homeOwnLastName;
		this.plansetDriveId = plansetDriveId;
		this.company = company;
		this.projectNameComb = projectNameComb;
		this.companyComb = companyComb;
		this.archiveLink = archiveLink;
		this.archiveStatus = archiveStatus;
		this.archiveDate = archiveDate;
	}

//Used For search Project
	public PermitResponsePrime(Long id, String homeName, String avancement, String status, Date updatedDate,
			String updatedBy, Boolean submitted, Date creationPermitDate, String firstName, String lastName,
			Date dateOfSubmitPermitOnHold, Date dateOfSubmitPermit, Boolean isCanceled, Date dateOfSubmitPermitCanceled,
			Boolean isOnHold, String projectName, String homeOwnLastName, String plansetDriveId, String projectNameComb,
			String companyComb, String archiveLink, String archiveStatus, Date archiveDate) {
		super();
		this.id = id;
		this.homeName = homeName;
		this.avancement = avancement;
		this.status = status;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.submitted = submitted;
		this.creationPermitDate = creationPermitDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfSubmitPermitOnHold = dateOfSubmitPermitOnHold;
		this.dateOfSubmitPermit = dateOfSubmitPermit;
		this.isCanceled = isCanceled;
		this.dateOfSubmitPermitCanceled = dateOfSubmitPermitCanceled;
		this.isOnHold = isOnHold;
		this.hasRFISheet = hasRFISheet;
		this.projectName = projectName;
		this.homeOwnLastName = homeOwnLastName;
		this.plansetDriveId = plansetDriveId;
		this.projectNameComb = projectNameComb;
		this.companyComb = companyComb;
		this.archiveLink = archiveLink;
		this.archiveStatus = archiveStatus;
		this.archiveDate = archiveDate;
	}

	public String getCreatedBy() {
		return firstName + " " + lastName;
	}

}
