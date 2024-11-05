package com.PlayGroundAdv.Solar.model;

public class AccountBuildModel {

	public Long id;
	public String pdfName;
	public String accountName;

	public AccountBuildModel() {
		super();
	}

	public AccountBuildModel(Long id, String pdfName, String accountName) {
		super();
		this.pdfName = pdfName;
		this.accountName = accountName;
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

}
