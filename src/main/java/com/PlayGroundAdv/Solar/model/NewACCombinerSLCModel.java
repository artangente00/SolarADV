package com.PlayGroundAdv.Solar.model;

public class NewACCombinerSLCModel {

	private String manufacturer;
	private String model;
	private String dropdownOption;
	private String type;
	private String ratedCurrent;
	private String deviceType;
	private String operationalVoltage;
	private String numberPole;
	private String numberSpaces;
	private String otherNumberOfSpaces;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private String category;

	public NewACCombinerSLCModel() {
		super();
	}

	public NewACCombinerSLCModel(String manufacturer, String model, String dropdownOption, String type,
			String ratedCurrent, String deviceType, String operationalVoltage, String numberPole, String numberSpaces,
			String otherNumberOfSpaces, String manufacturerMappingValue, String modelMappingValue) {
		super();

		this.manufacturer = manufacturer;
		this.model = model;
		this.dropdownOption = dropdownOption;
		this.type = type;
		this.ratedCurrent = ratedCurrent;
		this.deviceType = deviceType;
		this.operationalVoltage = operationalVoltage;
		this.numberPole = numberPole;
		this.numberSpaces = numberSpaces;
		this.otherNumberOfSpaces = otherNumberOfSpaces;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
	}
	
	public NewACCombinerSLCModel(String manufacturer, String model, String dropdownOption, String type,
			String ratedCurrent, String deviceType, String operationalVoltage, String numberPole, String numberSpaces,
			String otherNumberOfSpaces, String manufacturerMappingValue, String modelMappingValue, String category) {
		super();

		this.manufacturer = manufacturer;
		this.model = model;
		this.dropdownOption = dropdownOption;
		this.type = type;
		this.ratedCurrent = ratedCurrent;
		this.deviceType = deviceType;
		this.operationalVoltage = operationalVoltage;
		this.numberPole = numberPole;
		this.numberSpaces = numberSpaces;
		this.otherNumberOfSpaces = otherNumberOfSpaces;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.category = category;
	}
	
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRatedCurrent() {
		return ratedCurrent;
	}

	public void setRatedCurrent(String ratedCurrent) {
		this.ratedCurrent = ratedCurrent;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getOperationalVoltage() {
		return operationalVoltage;
	}

	public void setOperationalVoltage(String operationalVoltage) {
		this.operationalVoltage = operationalVoltage;
	}

	public String getNumberPole() {
		return numberPole;
	}

	public void setNumberPole(String numberPole) {
		this.numberPole = numberPole;
	}

	public String getNumberSpaces() {
		return numberSpaces;
	}

	public void setNumberSpaces(String numberSpaces) {
		this.numberSpaces = numberSpaces;
	}

	public String getOtherNumberOfSpaces() {
		return otherNumberOfSpaces;
	}

	public void setOtherNumberOfSpaces(String otherNumberOfSpaces) {
		this.otherNumberOfSpaces = otherNumberOfSpaces;
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
