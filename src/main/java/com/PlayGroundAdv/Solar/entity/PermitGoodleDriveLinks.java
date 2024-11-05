package com.PlayGroundAdv.Solar.entity;

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
@Table(name = "PermitGoodleDriveLinks")
public class PermitGoodleDriveLinks {

	/**
	 * PERMIT DISPLAY ENTITY FOR GRID & FILE UPLOADED
	 */ 
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name= "PermitGoodleDriveLinks_sequence",
			           sequenceName= "PermitGoodleDriveLinks_sequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "PermitGoodleDriveLinks_sequence")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name = "PROJECT_FOLDER_ID")
	private String projectFolderId;
	
	@Column(name = "PROJECT_PLANSET_ID")
	private String projectPlansetId;
	
	@Column(name = "DRAFTER_UPLOADS_ID")
	private String drafterUploadsId;

	@Column(name = "PORTAL_UPLOADS_ID")
	private String portalUploadsId;

	@Column(name = "DRAFTER_PACKAGE_ID")
	private String drafterPackageId;

	@Column(name = "PROJECT_CSV_EXPORT_ID")
	private String projectCSVExportId;

	@Column(name = "PROJECT_REVISION_EXPORT_ID")
	private String projectRevisionExportId;
	
	@Column(name = "PROJECT_REVISION_CSV_ID")
	private String projectRevisionCSVId;
	
	@Column(name = "PLANSET_DOCUMENT_ID")
	private String plansetDocumentId;
	
	@Column(name = "PLANSET_DOCUMENT_LINK")
	private String plansetDocumentLink;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PermitEntity getPermitEntity() {
		return permitEntity;
	}

	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}

	public String getProjectFolderId() {
		return projectFolderId;
	}

	public void setProjectFolderId(String projectFolderId) {
		this.projectFolderId = projectFolderId;
	}

	public String getProjectPlansetId() {
		return projectPlansetId;
	}

	public void setProjectPlansetId(String projectPlansetId) {
		this.projectPlansetId = projectPlansetId;
	}

	public String getDrafterUploadsId() {
		return drafterUploadsId;
	}

	public void setDrafterUploadsId(String drafterUploadsId) {
		this.drafterUploadsId = drafterUploadsId;
	}

	public String getPortalUploadsId() {
		return portalUploadsId;
	}

	public void setPortalUploadsId(String portalUploadsId) {
		this.portalUploadsId = portalUploadsId;
	}

	public String getDrafterPackageId() {
		return drafterPackageId;
	}

	public void setDrafterPackageId(String drafterPackageId) {
		this.drafterPackageId = drafterPackageId;
	}

	public String getProjectCSVExportId() {
		return projectCSVExportId;
	}

	public void setProjectCSVExportId(String projectCSVExportId) {
		this.projectCSVExportId = projectCSVExportId;
	}

	public String getProjectRevisionExportId() {
		return projectRevisionExportId;
	}

	public void setProjectRevisionExportId(String projectRevisionExportId) {
		this.projectRevisionExportId = projectRevisionExportId;
	}

	public String getProjectRevisionCSVId() {
		return projectRevisionCSVId;
	}

	public void setProjectRevisionCSVId(String projectRevisionCSVId) {
		this.projectRevisionCSVId = projectRevisionCSVId;
	}

	public String getPlansetDocumentId() {
		return plansetDocumentId;
	}

	public void setPlansetDocumentId(String plansetDocumentId) {
		this.plansetDocumentId = plansetDocumentId;
	}

	public String getPlansetDocumentLink() {
		return plansetDocumentLink;
	}

	public void setPlansetDocumentLink(String plansetDocumentLink) {
		this.plansetDocumentLink = plansetDocumentLink;
	}


}
