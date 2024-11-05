package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MISSING_SHEETS_LOG")
public class MissingSheetsLogEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="SHEET_NAME")
	private String sheetName;
	
	@Column(name="SHEET_TYPE")
	private String sheetType;// A.B 02-13 S-sheet or R-sheet or TLD
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="SUBMISSION_ID")
	private Long submissionId;// A.B 02-13 S-sheet or R-sheet or TLD
	
	@JoinColumn(name = "PROJECT")
	@ManyToOne
	private PermitEntity project;
	
	@JoinColumn(name = "USER_INFO")
	@ManyToOne
	private AuthentificationEntity userInfo;
	
	public MissingSheetsLogEntity() {
		super();
	}

	public MissingSheetsLogEntity(String sheetName, String sheetType, Date date, Long submissionId, PermitEntity project,
			AuthentificationEntity userInfo) {
		super();
		this.sheetName = sheetName;
		this.sheetType = sheetType;
		this.date = date;
		this.submissionId =submissionId;
		this.project = project;
		this.userInfo = userInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getSheetType() {
		return sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}

	public PermitEntity getProject() {
		return project;
	}

	public void setProject(PermitEntity project) {
		this.project = project;
	}

	public AuthentificationEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(AuthentificationEntity userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
}
