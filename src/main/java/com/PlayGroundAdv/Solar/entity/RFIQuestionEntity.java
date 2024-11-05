package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author seifn
 * 
 *
 */

@Entity
@Table(name = "RFIQuestionEntity" , indexes = {
	       @Index(name = "RFI_INDX_0",  columnList="fieldName")
	      })
public class RFIQuestionEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="hibernate_sequence157", sequenceName = "hibernate_sequence157", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence157")  
	private Long id_RFIQuestion;
	
	@Column(name="fieldName")
	private String fieldName;
	
	@Column(name="questionstatic")
	private String questionstatic;
	

	@Column(name="rfiQuestion")
	private String rfiQuestion;
	
	@Column(name="questionActived")
	private boolean questionActived;
	
	@Column(name="ADDEDBY")
	private String addedBy;

	@Column(name="confirmation")
	private boolean confirmation;

	@Column(name="isRFIDocument")
	private boolean isRFIDocument;
	
	@Column(name="IS_ATTACHEMENT_SHOWN")
	private boolean isAttachementShown;

	public RFIQuestionEntity() {
		super();
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


	public String getQuestionstatic() {
		return questionstatic;
	}

	public void setQuestionstatic(String questionstatic) {
		this.questionstatic = questionstatic;
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

	@Override
	public String toString() {
		return "RFIQuestionEntity [id_RFIQuestion=" + id_RFIQuestion + ", fieldName=" + fieldName + ", questionstatic="
				+ questionstatic + ", rfiQuestion=" + rfiQuestion + ", questionActived=" + questionActived
				+ ", addedBy=" + addedBy + ", confirmation=" + confirmation + "]";
	}
	
	
}
