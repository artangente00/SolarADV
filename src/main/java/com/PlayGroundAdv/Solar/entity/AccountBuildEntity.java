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
@Table(name = "AccountBuildEntity")
public class AccountBuildEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AccountBuildEntity_sequence", sequenceName = "AccountBuildEntity_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccountBuildEntity_sequence")
	private Long id;

	@Column(name = "PDF_NAME")
	private String pdfName;

	@Column(name = "ACCOUNT_NAME")
	private String accountName;

	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

	@Column(name = "IS_DELETED")
	private Boolean isDeleted;

	// A.B: Add user account
	@Column(name = "ACCOUNT")
	private Long account;

	@JoinColumn(name = "ADDED_BY")
	@ManyToOne
	private AuthentificationEntity addedBy;

	@JoinColumn(name = "UPDATED_BY")
	@ManyToOne
	private AuthentificationEntity updatedBy;
	
	@Column(name = "DELETED_ON")
	private Date deletedOn;

	@JoinColumn(name = "DELETED_BY")
	@ManyToOne
	private AuthentificationEntity deletedBy;

	public AccountBuildEntity() {
		super();
	}

	public AccountBuildEntity(Long id, String pdfName, String accountName, Date lastUpdate, Boolean isDeleted,
			Long account, AuthentificationEntity addedBy, Date deletedOn, AuthentificationEntity deletedBy, AuthentificationEntity updatedBy) {
		super();
		this.id = id;
		this.pdfName = pdfName;
		this.accountName = accountName;
		this.lastUpdate = lastUpdate;
		this.isDeleted = isDeleted;
		this.account = account;
		this.addedBy = addedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
		this.updatedBy = updatedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public AuthentificationEntity getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(AuthentificationEntity updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public AuthentificationEntity getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(AuthentificationEntity addedBy) {
		this.addedBy = addedBy;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	public AuthentificationEntity getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(AuthentificationEntity deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

}
