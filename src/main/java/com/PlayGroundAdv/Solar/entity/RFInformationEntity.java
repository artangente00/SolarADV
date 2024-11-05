package com.PlayGroundAdv.Solar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "RFInformationEntity")
public class RFInformationEntity {

	@Id
	@SequenceGenerator(name="RFISequence", sequenceName = "RFISequence", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RFISequence")  
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_ADV_USER_CO")
	private AuthentificationEntity idAdvUserCo;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERMIT")
	private PermitEntity idPermit;
	
	@Column(name="TABLE_NAME")
	private String tableName;
	
	@Column(name="ATTRIBUTE_NAME")
	private String attributeName;
	
	@Column(name="ATTRIBUTE_MODEL")
	private String attributeModel;
	
	@Column(name="ADV_QUESTION")
	private String advQuestion;
	
	@Column(name="IS_CONFIRMED")
	private Boolean isConfirmed;
	
	@Column(name="CONTRACTOR_RESPONSE")
	private String ContractorResponse;
	
	@Column(name="CONTENT_FIELD")
	private String contentField;
	
	@Column(name="FIELD_NG_MODEL")
	private String fieldNgModel;

	public String getFieldNgModel() {
		return fieldNgModel;
	}

	
	public void setFieldNgModel(String fieldNgModel) {
		this.fieldNgModel = fieldNgModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthentificationEntity getIdAdvUserCo() {
		return idAdvUserCo;
	}

	public void setIdAdvUserCo(AuthentificationEntity idAdvUserCo) {
		this.idAdvUserCo = idAdvUserCo;
	}

	public PermitEntity getIdPermit() {
		return idPermit;
	}

	public void setIdPermit(PermitEntity idPermit) {
		this.idPermit = idPermit;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeModel() {
		return attributeModel;
	}

	public void setAttributeModel(String attributeModel) {
		this.attributeModel = attributeModel;
	}

	public String getAdvQuestion() {
		return advQuestion;
	}

	public void setAdvQuestion(String advQuestion) {
		this.advQuestion = advQuestion;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getContractorResponse() {
		return ContractorResponse;
	}

	public void setContractorResponse(String contractorResponse) {
		ContractorResponse = contractorResponse;
	}

	public String getContentField() {
		return contentField;
	}

	public void setContentField(String contentField) {
		this.contentField = contentField;
	}

	
	
}
