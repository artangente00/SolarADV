package com.PlayGroundAdv.Solar.model;

public class EngineersModel {
	private Long id;
	private String contact;
	private String licenseState;

	public EngineersModel() {
		super();
	}

	public EngineersModel(Long id, String contact, String licenseState) {
		super();
		this.id = id;
		this.contact = contact;
		this.licenseState = licenseState;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getLicenseState() {
		return licenseState;
	}

	public void setLicenseState(String licenseState) {
		this.licenseState = licenseState;
	}

}
