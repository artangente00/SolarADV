package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="Site_Survey_Costum_Field_Entity")
public class SiteSurveyCostumFieldEntity implements Serializable {

	
private static final long serialVersionUID = 1L;
	

	@Id
	@SequenceGenerator(name="hibernate_sequence111", sequenceName = "hibernate_sequence111", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence111")  
	private Long id;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="TAB_NAME")
	private String tabName;
	
	@Column(name="FIELD_MODEL")
	private String fieldModel;
	
	@Column(name="FIELD_NAME")
	private String fieldName;
	
	@Column(name="HAS_REQUIRED_LOGIC")
	private boolean hasRequiredLogic;
	
	@Column(name="REQUIRED")
	private boolean required;
	
	@Column(name="DISABLED")
	private boolean disabled;
	
	@Column(name="LOGIC")
	private String logic;

	@Column(name="DELETED")
	private boolean deleted;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getFieldModel() {
		return fieldModel;
	}

	public void setFieldModel(String fieldModel) {
		this.fieldModel = fieldModel;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean getHasRequiredLogic() {
		return hasRequiredLogic;
	}

	public void setHasRequiredLogic(boolean hasRequiredLogic) {
		this.hasRequiredLogic = hasRequiredLogic;
	}

	public boolean getRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	

}