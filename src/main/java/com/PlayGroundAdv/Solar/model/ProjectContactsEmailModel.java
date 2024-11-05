package com.PlayGroundAdv.Solar.model;

public class ProjectContactsEmailModel {
	private String contactEmail;
	private String secondContactEmail;
	private String thirdContactEmail;

	public ProjectContactsEmailModel() {
		super();
	}

	public ProjectContactsEmailModel(String contactEmail, String secondContactEmail, String thirdContactEmail) {
		super();
		this.contactEmail = contactEmail;
		this.secondContactEmail = secondContactEmail;
		this.thirdContactEmail = thirdContactEmail;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getSecondContactEmail() {
		return secondContactEmail;
	}

	public void setSecondContactEmail(String secondContactEmail) {
		this.secondContactEmail = secondContactEmail;
	}

	public String getThirdContactEmail() {
		return thirdContactEmail;
	}

	public void setThirdContactEmail(String thirdContactEmail) {
		this.thirdContactEmail = thirdContactEmail;
	}

	@Override
	public String toString() {
		return "ProjectContactsEmailModel [contactEmail=" + contactEmail + ", secondContactEmail=" + secondContactEmail
				+ ", thirdContactEmail=" + thirdContactEmail + "]";
	}

}
