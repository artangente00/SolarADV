package com.PlayGroundAdv.Solar.model;

public class UtilityCompanyModelRequest {

	private String zip;
	private String utilityCompanyName;
	private Boolean isDeleted;

	public UtilityCompanyModelRequest() {
		super();
	}

	public UtilityCompanyModelRequest(String zip, String utilityCompanyName, Boolean isDeleted) {
		super();
		this.zip = zip;
		this.utilityCompanyName = utilityCompanyName;
		this.isDeleted = isDeleted;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getUtilityCompanyName() {
		return utilityCompanyName;
	}

	public void setUtilityCompanyName(String utilityCompanyName) {
		this.utilityCompanyName = utilityCompanyName;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
