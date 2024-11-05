package com.PlayGroundAdv.Solar.model;

/**
 * 
 * @author seifn
 *
 */

public class RFIQuestionFavRequest {

	private Long idQuestion;

	private String questionstatic;
	private String fieldName;
	private String rfiQuestion;
	private boolean questionActived;
	private String addedBy;
	private boolean confirmation;

	public RFIQuestionFavRequest() {
		super();
	}

	public RFIQuestionFavRequest(Long idQuestion, String questionstatic, String fieldName, String rfiQuestion,
			boolean questionActived, String addedBy, boolean confirmation) {
		super();
		this.idQuestion = idQuestion;
		this.questionstatic = questionstatic;
		this.fieldName = fieldName;
		this.rfiQuestion = rfiQuestion;
		this.questionActived = questionActived;
		this.addedBy = addedBy;
		this.confirmation = confirmation;

	}

	public RFIQuestionFavRequest(String fieldName, String rfiQuestion, boolean confirmation) {
		this.fieldName = fieldName;
		this.rfiQuestion = rfiQuestion;
		this.confirmation = confirmation;

	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public boolean isQuestionActived() {
		return questionActived;
	}

	public void setQuestionActived(boolean questionActived) {
		this.questionActived = questionActived;
	}

	public Long getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Long idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getRfiQuestion() {
		return rfiQuestion;
	}

	public void setRfiQuestion(String rfiQuestion) {
		this.rfiQuestion = rfiQuestion;
	}

	public String getQuestionstatic() {
		return questionstatic;
	}

	public void setQuestionstatic(String questionstatic) {
		this.questionstatic = questionstatic;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	@Override
	public String toString() {
		return "RFIQuestionFavRequest [idQuestion=" + idQuestion + ", questionstatic=" + questionstatic + ", fieldName="
				+ fieldName + ", rfiQuestion=" + rfiQuestion + ", questionActived=" + questionActived + ", addedBy="
				+ addedBy + ", confirmation=" + confirmation + "]";
	}

}
