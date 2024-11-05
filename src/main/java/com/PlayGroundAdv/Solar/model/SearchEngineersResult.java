package com.PlayGroundAdv.Solar.model;

public class SearchEngineersResult {

	private Long id;
	private String company;
	private String contact;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String licenseState;
	private String license;
	private String licenseType;
	private String licenseExpiration;
	private String phone;
	private String email;
	private String updated;
	private Boolean isDeleted;
	private UsersEntityResult owner;

	public SearchEngineersResult() {
		super();
	}

	public SearchEngineersResult(Long id, String company, String contact, String address, String city, String state,
			String zipCode, String licenseState, String license, String licenseType, String licenseExpiration,
			String phone, String email, String updated, Boolean isDeleted, UsersEntityResult owner) {
		super();
		this.id = id;
		this.company = company;
		this.contact = contact;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.licenseState = licenseState;
		this.license = license;
		this.licenseType = licenseType;
		this.licenseExpiration = licenseExpiration;
		this.phone = phone;
		this.email = email;
		this.updated = updated;
		this.isDeleted = isDeleted;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseState() {
		return licenseState;
	}

	public void setLicenseState(String licenseState) {
		this.licenseState = licenseState;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getLicenseExpiration() {
		return licenseExpiration;
	}

	public void setLicenseExpiration(String licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public UsersEntityResult getOwner() {
		return owner;
	}

	public void setOwner(UsersEntityResult owner) {
		this.owner = owner;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "SearchEngineersResult [id=" + id + ", company=" + company + ", contact=" + contact + ", address="
				+ address + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", licenseState="
				+ licenseState + ", license=" + license + ", licenseType=" + licenseType + ", licenseExpiration="
				+ licenseExpiration + ", phone=" + phone + ", email=" + email + ", updated=" + updated + ", isDeleted="
				+ isDeleted + ", owner=" + owner + "]";
	}

}
