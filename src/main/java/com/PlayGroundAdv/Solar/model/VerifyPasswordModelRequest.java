package com.PlayGroundAdv.Solar.model;

public class VerifyPasswordModelRequest {

	private String mailUser;
	private String tempPsw;

	public VerifyPasswordModelRequest() {
		super();
	}

	public VerifyPasswordModelRequest(String mailUser, String tempPsw) {
		super();
		this.mailUser = mailUser;
		this.tempPsw = tempPsw;
	}

	public String getMailUser() {
		return mailUser;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}

	public String getTempPsw() {
		return tempPsw;
	}

	public void setTempPsw(String tempPsw) {
		this.tempPsw = tempPsw;
	}

}
