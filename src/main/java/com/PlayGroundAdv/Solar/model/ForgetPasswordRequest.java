package com.PlayGroundAdv.Solar.model;

public class ForgetPasswordRequest {

	private String email;
	private String ipAdress;
	private String timeZone;

	public ForgetPasswordRequest() {
		super();
	}

	public ForgetPasswordRequest(String email, String ipAdress, String timeZone) {
		super();
		this.email = email;
		this.ipAdress = ipAdress;
		this.timeZone = timeZone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
