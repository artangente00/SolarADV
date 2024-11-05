package com.PlayGroundAdv.Solar.model;

public class ChangePasswordModel {

	private Long idUser;
	private String newPassword;
	private String oldPassword;

	public ChangePasswordModel() {
		super();
	}

	public ChangePasswordModel(Long idUser, String newPassword, String oldPassword) {
		super();
		this.idUser = idUser;
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	@Override
	public String toString() {
		return "ChangePasswordModel [idUser=" + idUser + ", newPassword=" + newPassword + ", oldPassword=" + oldPassword
				+ "]";
	}

}
