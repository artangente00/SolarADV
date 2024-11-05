package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class LoginResult {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String country;
	private String company;
	private String contcode;
	private Boolean active;
	private String status;
	private Date lastLogin;
	private String role;
	private String name;
	private Boolean solarPermit;
	private Boolean siteSurvey;

	public LoginResult(Long id, String firstName, String lastName, String email, String country, String company,
			String contcode, Boolean active, Date lastLogin, String role, Boolean solarPermit, Boolean siteSurvey) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.company = company;
		this.contcode = contcode;
		this.active = active;
		this.lastLogin = lastLogin;
		this.role = role;
		this.name = this.firstName + " " + this.lastName;
		this.solarPermit = solarPermit;
		this.siteSurvey = siteSurvey;
	}

	public LoginResult() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getContcode() {
		return contcode;
	}

	public void setContcode(String contcode) {
		this.contcode = contcode;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getStatus() {
		if (active == true) {
			status = "Activated";
		} else {
			status = "Not active";
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = active.toString();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSolarPermit() {
		return solarPermit;
	}

	public void setSolarPermit(Boolean solarPermit) {
		this.solarPermit = solarPermit;
	}

	public Boolean getSiteSurvey() {
		return siteSurvey;
	}

	public void setSiteSurvey(Boolean siteSurvey) {
		this.siteSurvey = siteSurvey;
	}

}
