package com.PlayGroundAdv.Solar.model;

public class ConfContracRfiResponseModel {

	private Long id;
	private Boolean isConfirmed;
	private String contentField;

	public ConfContracRfiResponseModel() {
		super();
	}

	public ConfContracRfiResponseModel(Long id, Boolean isConfirmed, String contentField) {
		super();
		this.id = id;
		this.isConfirmed = isConfirmed;
		this.contentField = contentField;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getContentField() {
		return contentField;
	}

	public void setContentField(String contentField) {
		this.contentField = contentField;
	}

}
