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
@Table(name = "PermitEvaluationEntity")
public class PermitEvaluationEntity {
	/**
	 * PERMIT DISPLAY ENTITY FOR GRID & FILE UPLOADED
	 */ 
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="permit_seq56", sequenceName = "permit_seq56", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permit_seq56")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@JoinColumn(name = "PROJECT_MANAGER_ID")
	@ManyToOne
	private AuthentificationEntity projectManagerId;
	
	@JoinColumn(name = "SUPER_USER_ID")
	@ManyToOne
	private AuthentificationEntity superUserId;
	
	@JoinColumn(name = "EVAL_LOCATION")
	@ManyToOne
	private ChecklistLocationsEntity evalLocation;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="PM_VERIFIED")
	private Boolean pmVerified;
	
	@Column(name="PM_REFER_SR")
	private Boolean pmReferSR;
	
	@Column(name="PM_REQUIRED_CORRECTION")
	private Boolean pmRequiredCorrection;
	
	@Column(name="SR_PM_VERIFIED")
	private Boolean srPmVerified;
	
	@Column(name="SR_PM_MODIFY_GUIDE")
	private Boolean srPmModifyGuide;
	
	@Column(name="SR_PM_CORRECT")
	private Boolean srPmCorrect;
	
	@Column(name="SR_PM_NOTE")
	private String srPmNote;
	
	@Column(name="PM_NOTE")
	private String pmNote;
	
	@Column(name="PM_CONFIRMED")
	private Boolean pmConfirmed;
	
	@Column(name="SR_CONFIRMED")
	private Boolean srConfirmed;

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

	public AuthentificationEntity getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(AuthentificationEntity projectManagerId) {
		this.projectManagerId = projectManagerId;
	}

	public AuthentificationEntity getSuperUserId() {
		return superUserId;
	}

	public void setSuperUserId(AuthentificationEntity superUserId) {
		this.superUserId = superUserId;
	}

	public ChecklistLocationsEntity getLocation() {
		return evalLocation;
	}

	public void setLocation(ChecklistLocationsEntity evalLocation) {
		this.evalLocation = evalLocation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getPmVerified() {
		return pmVerified;
	}

	public void setPmVerified(Boolean pmVerified) {
		this.pmVerified = pmVerified;
	}

	public Boolean getPmReferSR() {
		return pmReferSR;
	}

	public void setPmReferSR(Boolean pmReferSR) {
		this.pmReferSR = pmReferSR;
	}

	public Boolean getPmRequiredCorrection() {
		return pmRequiredCorrection;
	}

	public void setPmRequiredCorrection(Boolean pmRequiredCorrection) {
		this.pmRequiredCorrection = pmRequiredCorrection;
	}

	public Boolean getSrPmVerified() {
		return srPmVerified;
	}

	public void setSrPmVerified(Boolean srPmVerified) {
		this.srPmVerified = srPmVerified;
	}

	public Boolean getSrPmModifyGuide() {
		return srPmModifyGuide;
	}

	public void setSrPmModifyGuide(Boolean srPmModifyGuide) {
		this.srPmModifyGuide = srPmModifyGuide;
	}

	public Boolean getSrPmCorrect() {
		return srPmCorrect;
	}

	public void setSrPmCorrect(Boolean srPmCorrect) {
		this.srPmCorrect = srPmCorrect;
	}

	public String getSrPmNote() {
		return srPmNote;
	}

	public void setSrPmNote(String srPmNote) {
		this.srPmNote = srPmNote;
	}

	public String getPmNote() {
		return pmNote;
	}

	public void setPmNote(String pmNote) {
		this.pmNote = pmNote;
	}

	public Boolean getPmConfirmed() {
		return pmConfirmed;
	}

	public void setPmConfirmed(Boolean pmConfirmed) {
		this.pmConfirmed = pmConfirmed;
	}

	public Boolean getSrConfirmed() {
		return srConfirmed;
	}

	public void setSrConfirmed(Boolean srConfirmed) {
		this.srConfirmed = srConfirmed;
	}

	@Override
	public String toString() {
		return "PermitEvaluationEntity [id=" + id + ", permitEntity=" + permitEntity + ", projectManagerId="
				+ projectManagerId + ", superUserId=" + superUserId + ", evalLocation=" + evalLocation + ", status=" + status
				+ ", pmVerified=" + pmVerified + ", pmReferSR=" + pmReferSR + ", pmRequiredCorrection="
				+ pmRequiredCorrection + ", srPmVerified=" + srPmVerified + ", srPmModifyGuide=" + srPmModifyGuide
				+ ", srPmCorrect=" + srPmCorrect + ", srPmNote=" + srPmNote + ", pmNote=" + pmNote + ", pmConfirmed="
				+ pmConfirmed + ", srConfirmed=" + srConfirmed + "]";
	}

	
	
}
