package com.PlayGroundAdv.Solar.model;

//Used To collect a new user's info : Sign up a new user
public class SetUserModelRequest {

	private String email;
	private String password;
	private String contracCode;
	private Boolean agreewithtermsandconditions;
	private String ipAdress;
	private String timeZone;
	private String firstName;
	private String lastName;
	private Boolean solarPermit;
	private Boolean siteSurvey;
	private String phoneNumber;
	

	public SetUserModelRequest() {
		super();
	}

	public SetUserModelRequest(String email, String password, String contracCode, Boolean agreewithtermsandconditions,
			String ipAdress, String timeZone, String firstName, String lastName, Boolean solarPermit,
			Boolean siteSurvey) {
		super();
		this.email = email;
		this.password = password;
		this.contracCode = contracCode;
		this.agreewithtermsandconditions = agreewithtermsandconditions;
		this.ipAdress = ipAdress;
		this.timeZone = timeZone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.solarPermit = solarPermit;
		this.siteSurvey = siteSurvey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContracCode() {
		return contracCode;
	}

	public void setContracCode(String contracCode) {
		this.contracCode = contracCode;
	}

	public Boolean getAgreewithtermsandconditions() {
		return agreewithtermsandconditions;
	}

	public void setAgreewithtermsandconditions(Boolean agreewithtermsandconditions) {
		this.agreewithtermsandconditions = agreewithtermsandconditions;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
