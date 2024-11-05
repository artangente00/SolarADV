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
@Table(name = "RFIConfirmationEntity")
public class RFIConfirmationEntity {

	@Id
	@SequenceGenerator(name="RFISequence1", sequenceName = "RFISequence1", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RFISequence1")  
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_ADV_USER_CONFIRMER")
	private AuthentificationEntity idAdvUserConfirmer;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_CONTRACTOR_USER_CO")
	private AuthentificationEntity idContractorUserConfirmer;
	
	

	@ManyToOne
	@JoinColumn(name = "ID_PERMIT")
	private PermitEntity idPermit;
	
	@Column(name="STATUT_CONFIRM_PERMIT")
	private String statutConfirmPermit;
	
	
	@Column(name="IS_ADV_CONFIRM")
	private Boolean isAdvConfirm;
	
	@Column(name="IS_CONTRACTOR_CONFIRM")
	private Boolean iscONTRACTORConfirm;
	
	@Column(name="REMINDER")
	private String reminder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthentificationEntity getIdAdvUserConfirmer() {
		return idAdvUserConfirmer;
	}

	public void setIdAdvUserConfirmer(AuthentificationEntity idAdvUserConfirmer) {
		this.idAdvUserConfirmer = idAdvUserConfirmer;
	}

	public AuthentificationEntity getIdContractorUserConfirmer() {
		return idContractorUserConfirmer;
	}

	public void setIdContractorUserConfirmer(AuthentificationEntity idContractorUserConfirmer) {
		this.idContractorUserConfirmer = idContractorUserConfirmer;
	}

	public PermitEntity getIdPermit() {
		return idPermit;
	}

	public void setIdPermit(PermitEntity idPermit) {
		this.idPermit = idPermit;
	}

	public String getStatutConfirmPermit() {
		return statutConfirmPermit;
	}

	public void setStatutConfirmPermit(String statutConfirmPermit) {
		this.statutConfirmPermit = statutConfirmPermit;
	}

	public Boolean getIsAdvConfirm() {
		return isAdvConfirm;
	}

	public void setIsAdvConfirm(Boolean isAdvConfirm) {
		this.isAdvConfirm = isAdvConfirm;
	}

	public Boolean getIscONTRACTORConfirm() {
		return iscONTRACTORConfirm;
	}

	public void setIscONTRACTORConfirm(Boolean iscONTRACTORConfirm) {
		this.iscONTRACTORConfirm = iscONTRACTORConfirm;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	@Override
	public String toString() {
		return "RFIConfirmationEntity [id=" + id + ", idAdvUserConfirmer=" + idAdvUserConfirmer
				+ ", idContractorUserConfirmer=" + idContractorUserConfirmer + ", idPermit=" + idPermit
				+ ", statutConfirmPermit=" + statutConfirmPermit + ", isAdvConfirm=" + isAdvConfirm
				+ ", iscONTRACTORConfirm=" + iscONTRACTORConfirm + ", reminder=" + reminder + "]";
	}
	
	
	
	
}
