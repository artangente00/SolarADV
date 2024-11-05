package com.PlayGroundAdv.Solar.model;

public class ResetFinalStepModelRequest {

	private String newPsw;
	private String email;
	private String ipAdress;
	private String timeZone;

	public ResetFinalStepModelRequest() {
		super();
	}

	public ResetFinalStepModelRequest(String newPsw, String email, String ipAdress, String timeZone) {
		super();
		this.newPsw = newPsw;
		this.email = email;
		this.ipAdress = ipAdress;
		this.timeZone = timeZone;
	}

	public String getNewPsw() {
		return newPsw;
	}

	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
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
