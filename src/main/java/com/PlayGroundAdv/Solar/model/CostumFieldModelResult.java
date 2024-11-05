package com.PlayGroundAdv.Solar.model;

public class CostumFieldModelResult {

	private String id;
	private Boolean hasRequiredLogic;
	private Boolean required;
	private String logic;

	public CostumFieldModelResult() {
		super();
	}

	public CostumFieldModelResult(String id, Boolean hasRequiredLogic, Boolean required, String logic) {
		super();
		this.id = id;
		this.hasRequiredLogic = hasRequiredLogic;
		this.required = required;
		this.logic = logic;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

}