package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class AccountBuildEntityModel {

	private Long id;
	private String pdfName;
	private String accountName;
	private Date lastUpdate;
	private Boolean isDeleted;
	private Long account;
	// deleted or added by
	private String byFullName;
	private Date deletedOn;
	private String updatedBy;
	
	public AccountBuildEntityModel() {
		super();
	}

	public AccountBuildEntityModel(Long id, String pdfName, String accountName, Date lastUpdate, Boolean isDeleted,
			Long account, String byFullName, Date deletedOn, String updatedBy) {
		super();
		this.id = id;
		this.pdfName = pdfName;
		this.accountName = accountName;
		this.lastUpdate = lastUpdate;
		this.isDeleted = isDeleted;
		this.account = account;
		this.byFullName = byFullName;
		this.deletedOn = deletedOn;
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public String getByFullName() {
		return byFullName;
	}

	public void setByFullName(String byFullName) {
		this.byFullName = byFullName;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

}
