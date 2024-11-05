package com.PlayGroundAdv.Solar.model;

public class RFIQuestionEntityModel {
	private Long id_RFIQuestion;
	private String fieldName;
	private String questionstatic;
	private String rfiQuestion;
	private boolean questionActived;
	private String addedBy;
	private boolean confirmation;
	private boolean isRFIDocument;
	private boolean isAttachementShown;

	public RFIQuestionEntityModel() {
		super();
	}

	public RFIQuestionEntityModel(Long id_RFIQuestion, String fieldName, String questionstatic, String rfiQuestion,
			boolean questionActived, String addedBy, boolean confirmation, boolean isRFIDocument,
			boolean isAttachementShown) {
		super();
		this.id_RFIQuestion = id_RFIQuestion;
		this.fieldName = fieldName;
		this.questionstatic = questionstatic;
		this.rfiQuestion = rfiQuestion;
		this.questionActived = questionActived;
		this.addedBy = addedBy;
		this.confirmation = confirmation;
		this.isRFIDocument = isRFIDocument;
		this.isAttachementShown = isAttachementShown;
	}

	public Long getId_RFIQuestion() {
		return id_RFIQuestion;
	}

	public void setId_RFIQuestion(Long id_RFIQuestion) {
		this.id_RFIQuestion = id_RFIQuestion;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getQuestionstatic() {
		return questionstatic;
	}

	public void setQuestionstatic(String questionstatic) {
		this.questionstatic = questionstatic;
	}

	public String getRfiQuestion() {
		return rfiQuestion;
	}

	public void setRfiQuestion(String rfiQuestion) {
		this.rfiQuestion = rfiQuestion;
	}

	public boolean isQuestionActived() {
		return questionActived;
	}

	public void setQuestionActived(boolean questionActived) {
		this.questionActived = questionActived;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public boolean isRFIDocument() {
		return isRFIDocument;
	}

	public void setRFIDocument(boolean isRFIDocument) {
		this.isRFIDocument = isRFIDocument;
	}

	public boolean isAttachementShown() {
		return isAttachementShown;
	}

	public void setAttachementShown(boolean isAttachementShown) {
		this.isAttachementShown = isAttachementShown;
	}

}
