package com.PlayGroundAdv.Solar.model;

public class AddConverterModelRequest {

	private String manufacturer;
	private String model;
	private Boolean phaseBox;
	private String phaseSelect;
	private String phaseSelectOther;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private String type;

	public AddConverterModelRequest() {
		super();
	}

	public AddConverterModelRequest(String manufacturer, String model, Boolean phaseBox, String phaseSelect,
			String phaseSelectOther, String manufacturerMappingValue, String modelMappingValue, String type) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.phaseBox = phaseBox;
		this.phaseSelect = phaseSelect;
		this.phaseSelectOther = phaseSelectOther;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.type = type;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getPhaseBox() {
		return phaseBox;
	}

	public void setPhaseBox(Boolean phaseBox) {
		this.phaseBox = phaseBox;
	}

	public String getPhaseSelect() {
		return phaseSelect;
	}

	public void setPhaseSelect(String phaseSelect) {
		this.phaseSelect = phaseSelect;
	}

	public String getPhaseSelectOther() {
		return phaseSelectOther;
	}

	public void setPhaseSelectOther(String phaseSelectOther) {
		this.phaseSelectOther = phaseSelectOther;
	}

	public String getManufacturerMappingValue() {
		return manufacturerMappingValue;
	}

	public void setManufacturerMappingValue(String manufacturerMappingValue) {
		this.manufacturerMappingValue = manufacturerMappingValue;
	}

	public String getModelMappingValue() {
		return modelMappingValue;
	}

	public void setModelMappingValue(String modelMappingValue) {
		this.modelMappingValue = modelMappingValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
