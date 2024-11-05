package com.PlayGroundAdv.Solar.model;

public class RFIModelRequest {

	private String tabname;
	private String attName;
	private String attModel;
	private String advQuestion;
	private String contentField;
	private String fieldNgModel;

	public RFIModelRequest() {
		super();
	}

	public RFIModelRequest(String tabname, String attName, String attModel, String advQuestion, String contentField,
			String fieldNgModel) {
		super();
		this.tabname = tabname;
		this.attName = attName;
		this.attModel = attModel;
		this.advQuestion = advQuestion;
		this.contentField = contentField;
		this.fieldNgModel = fieldNgModel;
	}

	public String getFieldNgModel() {
		return fieldNgModel;
	}

	public void setFieldNgModel(String fieldNgModel) {
		this.fieldNgModel = fieldNgModel;
	}

	public String getTabname() {
		return tabname;
	}

	public void setTabname(String tabname) {
		this.tabname = tabname;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public String getAttModel() {
		return attModel;
	}

	public void setAttModel(String attModel) {
		this.attModel = attModel;
	}

	public String getAdvQuestion() {
		return advQuestion;
	}

	public void setAdvQuestion(String advQuestion) {
		this.advQuestion = advQuestion;
	}

	public String getContentField() {
		return contentField;
	}

	public void setContentField(String contentField) {
		this.contentField = contentField;
	}

}
