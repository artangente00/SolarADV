package com.PlayGroundAdv.Solar.model;

public class AddContactRfiModel {

	private String contactmail;
	private Boolean isProjectAddInclud;
	private String secondContactEmail;
	private String thirdContactEmail;

	/**
	 * 
	 */
	public AddContactRfiModel() {
		super();
	}

	/**
	 * @param contactmail
	 * @param isProjectAddInclud
	 * @param secondContactEmail
	 * @param thirdContactEmail
	 */
	public AddContactRfiModel(String contactmail, Boolean isProjectAddInclud, String secondContactEmail,
			String thirdContactEmail) {
		super();
		this.contactmail = contactmail;
		this.isProjectAddInclud = isProjectAddInclud;
		this.secondContactEmail = secondContactEmail;
		this.thirdContactEmail = thirdContactEmail;
	}

	public String getContactmail() {
		return contactmail;
	}

	public void setContactmail(String contactmail) {
		this.contactmail = contactmail;
	}

	public Boolean getIsProjectAddInclud() {
		if (!(this.isProjectAddInclud != null))
			return false;
		return isProjectAddInclud;
	}

	public void setIsProjectAddInclud(Boolean isProjectAddInclud) {
		this.isProjectAddInclud = isProjectAddInclud;
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

}
