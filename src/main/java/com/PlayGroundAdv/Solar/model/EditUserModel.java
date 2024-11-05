package com.PlayGroundAdv.Solar.model;

public class EditUserModel {
	private EditUserInformations editUserInformations;
	private String ipUser;
	private String timeZone;
	private Long iduser;

	public EditUserModel() {
		super();
	}

	public EditUserModel(EditUserInformations editUserInformations, String ipUser, String timeZone, Long iduser) {
		super();
		this.editUserInformations = editUserInformations;
		this.ipUser = ipUser;
		this.timeZone = timeZone;
		this.iduser = iduser;
	}

	public EditUserInformations getEditUserInformations() {
		return editUserInformations;
	}

	public void setEditUserInformations(EditUserInformations editUserInformations) {
		this.editUserInformations = editUserInformations;
	}

	public String getIpUser() {
		return ipUser;
	}

	public void setIpUser(String ipUser) {
		this.ipUser = ipUser;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	@Override
	public String toString() {
		return "EditUserModel [editUserInformations=" + editUserInformations + ", ipUser=" + ipUser + ", timeZone="
				+ timeZone + ", iduser=" + iduser + "]";
	}

}
