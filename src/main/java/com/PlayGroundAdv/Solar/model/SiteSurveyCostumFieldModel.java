
package com.PlayGroundAdv.Solar.model;

public class SiteSurveyCostumFieldModel {

	private String state;
	private String tabName;
	private String fieldModel;
	private String fieldName;
	private Boolean hasRequiredLogic;
	private Boolean required;
	private Boolean disabled;
	private String logic;
	private Boolean deleted;

	public SiteSurveyCostumFieldModel() {
		super();
	}

	public SiteSurveyCostumFieldModel(String state, String tabName, String fieldModel, String fieldName,
			Boolean hasRequiredLogic, Boolean required, Boolean disabled, String logic, Boolean deleted) {
		super();
		this.state = state;
		this.tabName = tabName;
		this.fieldModel = fieldModel;
		this.fieldName = fieldName;
		this.hasRequiredLogic = hasRequiredLogic;
		this.required = required;
		this.disabled = disabled;
		this.logic = logic;
		this.deleted = deleted;
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

	public Boolean getHasRequiredLogic() {
		return hasRequiredLogic;
	}

	public void setHasRequiredLogic(Boolean hasRequiredLogic) {
		this.hasRequiredLogic = hasRequiredLogic;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}