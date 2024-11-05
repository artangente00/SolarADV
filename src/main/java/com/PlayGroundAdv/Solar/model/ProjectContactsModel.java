package com.PlayGroundAdv.Solar.model;

public class ProjectContactsModel {

	private String contactFirstName;
	private String contactLastName;
	private String secondContactFirstName;
	private String secondContactLastName;
	private String thirdContact;
	private String lastNameContact;
	private String firstname;
	private String lastName;
	private String projectContactPhone;
	private String projectContactEmail;

	public ProjectContactsModel() {
		super();
	}

	public ProjectContactsModel(String contactFirstName, String contactLastName, String secondContactFirstName,
			String secondContactLastName, String thirdContact, String lastNameContact, String firstname,
			String lastName, String projectContactPhone, String projectContactEmail) {
		super();
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.secondContactFirstName = secondContactFirstName;
		this.secondContactLastName = secondContactLastName;
		this.thirdContact = thirdContact;
		this.lastNameContact = lastNameContact;
		this.firstname = firstname;
		this.lastName = lastName;
		this.projectContactPhone = projectContactPhone;
		this.projectContactEmail = projectContactEmail;
	}

	public ProjectContactsModel(String contactFirstName, String contactLastName, String secondContactFirstName,
			String secondContactLastName, String thirdContact, String lastNameContact) {
		super();
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.secondContactFirstName = secondContactFirstName;
		this.secondContactLastName = secondContactLastName;
		this.thirdContact = thirdContact;
		this.lastNameContact = lastNameContact;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getSecondContactFirstName() {
		return secondContactFirstName;
	}

	public void setSecondContactFirstName(String secondContactFirstName) {
		this.secondContactFirstName = secondContactFirstName;
	}

	public String getSecondContactLastName() {
		return secondContactLastName;
	}

	public void setSecondContactLastName(String secondContactLastName) {
		this.secondContactLastName = secondContactLastName;
	}

	public String getThirdContact() {
		return thirdContact;
	}

	public void setThirdContact(String thirdContact) {
		this.thirdContact = thirdContact;
	}

	public String getLastNameContact() {
		return lastNameContact;
	}

	public void setLastNameContact(String lastNameContact) {
		this.lastNameContact = lastNameContact;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProjectContactPhone() {
		return projectContactPhone;
	}

	public void setProjectContactPhone(String projectContactPhone) {
		this.projectContactPhone = projectContactPhone;
	}

	public String getProjectContactEmail() {
		return projectContactEmail;
	}

	public void setProjectContactEmail(String projectContactEmail) {
		this.projectContactEmail = projectContactEmail;
	}

	@Override
	public String toString() {
		return "ProjectContactsModel [contactFirstName=" + contactFirstName + ", contactLastName=" + contactLastName
				+ ", secondContactFirstName=" + secondContactFirstName + ", secondContactLastName="
				+ secondContactLastName + ", thirdContact=" + thirdContact + ", lastNameContact=" + lastNameContact
				+ ", firstname=" + firstname + ", lastName=" + lastName + ", projectContactPhone=" + projectContactPhone
				+ ", projectContactEmail=" + projectContactEmail + "]";
	}

}
