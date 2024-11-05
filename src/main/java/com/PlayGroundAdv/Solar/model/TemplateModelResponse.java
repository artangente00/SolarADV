package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class TemplateModelResponse {

	private Long idTemplate;
	private String nameTemplate;
	private Date creationPermitDate;
	private Date updatedDate;
	private String ownerTemplateFirstName;
	private String ownerTemplateLastName;
	private String templateName;
	private String company;

	public TemplateModelResponse() {
		super();
	}

	public TemplateModelResponse(Long idTemplate, String nameTemplate, Date creationPermitDate, Date updatedDate,
			String ownerTemplateFirstName, String ownerTemplateLastName, String templateName, String company) {
		super();
		this.idTemplate = idTemplate;
		this.nameTemplate = nameTemplate;
		this.creationPermitDate = creationPermitDate;
		this.updatedDate = updatedDate;
		this.ownerTemplateFirstName = ownerTemplateFirstName;
		this.ownerTemplateLastName = ownerTemplateLastName;
		this.templateName = templateName;
		this.company = company;
	}

	public String getNameTemplate() {
		return nameTemplate;
	}

	public void setNameTemplate(String nameTemplate) {
		this.nameTemplate = nameTemplate;
	}

	public Date getCreationDate() {
		return creationPermitDate;
	}

	public void setCreationDate(Date creationPermitDate) {
		this.creationPermitDate = creationPermitDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}

	public String getOwnerTemplateFirstName() {
		return ownerTemplateFirstName;
	}

	public void setOwnerTemplateFirstName(String ownerTemplateFirstName) {
		this.ownerTemplateFirstName = ownerTemplateFirstName;
	}

	public String getOwnerTemplateLastName() {
		return ownerTemplateLastName;
	}

	public void setOwnerTemplateLastName(String ownerTemplateLastName) {
		this.ownerTemplateLastName = ownerTemplateLastName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
