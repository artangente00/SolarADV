package com.PlayGroundAdv.Solar.model;

public class NewJunctionBoxModel {

	private String manufacturer;
	private String model;
	private String dropdownOption;
	private String manufacturerMappingValue;
	private String modelMappingValue;

	public NewJunctionBoxModel() {
		super();
	}

	public NewJunctionBoxModel(String manufacturer, String model, String dropdownOption,
			String manufacturerMappingValue, String modelMappingValue) {
		super();

		this.manufacturer = manufacturer;
		this.model = model;
		this.dropdownOption = dropdownOption;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
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

	public String getDropdownOption() {
		return dropdownOption;
	}

	public void setDropdownOption(String dropdownOption) {
		this.dropdownOption = dropdownOption;
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

}
