package com.PlayGroundAdv.Solar.model;

public class AuthentificationEntityResult {

	private Long id;
	private String password;
	private String email;
	private String contractorCode;
	private String firstName;
	private String lastName;
	private String country;
	private String company;
	private boolean active;
	private boolean deleted;

	public AuthentificationEntityResult() {
		super();
	}

	public AuthentificationEntityResult(Long id, String password, String email, String contractorCode,
			String firstName, String lastName, String country, String company, boolean active, boolean deleted) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.contractorCode = contractorCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.company = company;
		this.active = active;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContractorCode() {
		return contractorCode;
	}

	public void setContractorCode(String contractorCode) {
		this.contractorCode = contractorCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
