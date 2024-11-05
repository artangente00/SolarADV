package com.PlayGroundAdv.Solar.model;

public class ProjectEmailModel {
	private String contractorEmail;
	private String otherContractorEmail;
	private String emailSubject;
	private String emailContent;
	private String projectAttachement;

	public ProjectEmailModel() {
		super();
	}

	public ProjectEmailModel(String contractorEmail, String otherContractorEmail, String emailSubject,
			String emailContent, String projectAttachement) {
		super();
		this.contractorEmail = contractorEmail;
		this.otherContractorEmail = otherContractorEmail;
		this.emailSubject = emailSubject;
		this.emailContent = emailContent;
		this.projectAttachement = projectAttachement;
	}

	public String getContractorEmail() {
		return contractorEmail;
	}

	public void setContractorEmail(String contractorEmail) {
		this.contractorEmail = contractorEmail;
	}

	public String getOtherContractorEmail() {
		return otherContractorEmail;
	}

	public void setOtherContractorEmail(String otherContractorEmail) {
		this.otherContractorEmail = otherContractorEmail;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getProjectAttachement() {
		return projectAttachement;
	}

	public void setProjectAttachement(String projectAttachement) {
		this.projectAttachement = projectAttachement;
	}

	@Override
	public String toString() {
		return "ProjectEmailModel [contractorEmail=" + contractorEmail + ", otherContractorEmail="
				+ otherContractorEmail + ", emailSubject=" + emailSubject + ", emailContent=" + emailContent
				+ ", projectAttachement=" + projectAttachement + "]";
	}

}
