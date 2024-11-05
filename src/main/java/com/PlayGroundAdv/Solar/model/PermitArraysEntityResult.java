package com.PlayGroundAdv.Solar.model;

public class PermitArraysEntityResult {

	private String systemType;
	private Boolean RequestQuote;
	private String deviceToIncorporate;
	private String pvModuleManufacturer;
	private String pvModuleModEl;
	private String inverterModel;
	private String stringOne;
	private String stringTwo;
	private String stringThree;
	private String stringFour;
	private String stringFive;
	private String inverterManufacturer;
	private String secondInverterManufacturer;
	private String secondInverterModel;
	private String secondStringOne;
	private String secondStringTwo;
	private String secondStringThree;
	private String secondStringFour;
	private String secondStringFive;

	public PermitArraysEntityResult() {
		super();
	}

	public PermitArraysEntityResult(String systemType, Boolean requestQuote, String deviceToIncorporate,
			String pvModuleManufacturer, String pvModuleModEl, String inverterModel, String stringOne, String stringTwo,
			String stringThree, String stringFour, String stringFive, String inverterManufacturer,
			String secondInverterManufacturer, String secondInverterModel, String secondStringOne,
			String secondStringTwo, String secondStringThree, String secondStringFour, String secondStringFive) {
		super();
		this.systemType = systemType;
		RequestQuote = requestQuote;
		this.deviceToIncorporate = deviceToIncorporate;
		this.pvModuleManufacturer = pvModuleManufacturer;
		this.pvModuleModEl = pvModuleModEl;
		this.inverterModel = inverterModel;
		this.stringOne = stringOne;
		this.stringTwo = stringTwo;
		this.stringThree = stringThree;
		this.stringFour = stringFour;
		this.stringFive = stringFive;
		this.inverterManufacturer = inverterManufacturer;
		this.secondInverterManufacturer = secondInverterManufacturer;
		this.secondInverterModel = secondInverterModel;
		this.secondStringOne = secondStringOne;
		this.secondStringTwo = secondStringTwo;
		this.secondStringThree = secondStringThree;
		this.secondStringFour = secondStringFour;
		this.secondStringFive = secondStringFive;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public Boolean getRequestQuote() {
		return RequestQuote;
	}

	public void setRequestQuote(Boolean requestQuote) {
		RequestQuote = requestQuote;
	}

	public String getDeviceToIncorporate() {
		return deviceToIncorporate;
	}

	public void setDeviceToIncorporate(String deviceToIncorporate) {
		this.deviceToIncorporate = deviceToIncorporate;
	}

	public String getPvModuleManufacturer() {
		return pvModuleManufacturer;
	}

	public void setPvModuleManufacturer(String pvModuleManufacturer) {
		this.pvModuleManufacturer = pvModuleManufacturer;
	}

	public String getPvModuleModEl() {
		return pvModuleModEl;
	}

	public void setPvModuleModEl(String pvModuleModEl) {
		this.pvModuleModEl = pvModuleModEl;
	}

	public String getInverterModel() {
		return inverterModel;
	}

	public void setInverterModel(String inverterModel) {
		this.inverterModel = inverterModel;
	}

	public String getStringOne() {
		return stringOne;
	}

	public void setStringOne(String stringOne) {
		this.stringOne = stringOne;
	}

	public String getStringTwo() {
		return stringTwo;
	}

	public void setStringTwo(String stringTwo) {
		this.stringTwo = stringTwo;
	}

	public String getStringThree() {
		return stringThree;
	}

	public void setStringThree(String stringThree) {
		this.stringThree = stringThree;
	}

	public String getStringFour() {
		return stringFour;
	}

	public void setStringFour(String stringFour) {
		this.stringFour = stringFour;
	}

	public String getStringFive() {
		return stringFive;
	}

	public void setStringFive(String stringFive) {
		this.stringFive = stringFive;
	}

	public String getSecondInverterManufacturer() {
		return secondInverterManufacturer;
	}

	public void setSecondInverterManufacturer(String secondInverterManufacturer) {
		this.secondInverterManufacturer = secondInverterManufacturer;
	}

	public String getSecondInverterModel() {
		return secondInverterModel;
	}

	public void setSecondInverterModel(String secondInverterModel) {
		this.secondInverterModel = secondInverterModel;
	}

	public String getSecondStringOne() {
		return secondStringOne;
	}

	public void setSecondStringOne(String secondStringOne) {
		this.secondStringOne = secondStringOne;
	}

	public String getSecondStringTwo() {
		return secondStringTwo;
	}

	public void setSecondStringTwo(String secondStringTwo) {
		this.secondStringTwo = secondStringTwo;
	}

	public String getSecondStringThree() {
		return secondStringThree;
	}

	public void setSecondStringThree(String secondStringThree) {
		this.secondStringThree = secondStringThree;
	}

	public String getSecondStringFour() {
		return secondStringFour;
	}

	public void setSecondStringFour(String secondStringFour) {
		this.secondStringFour = secondStringFour;
	}

	public String getSecondStringFive() {
		return secondStringFive;
	}

	public void setSecondStringFive(String secondStringFive) {
		this.secondStringFive = secondStringFive;
	}

	public String getInverterManufacturer() {
		return inverterManufacturer;
	}

	public void setInverterManufacturer(String inverterManufacturer) {
		this.inverterManufacturer = inverterManufacturer;
	}

	@Override
	public String toString() {
		return "PermitArraysEntityResult [systemType=" + systemType + ", RequestQuote=" + RequestQuote
				+ ", deviceToIncorporate=" + deviceToIncorporate + ", pvModuleManufacturer=" + pvModuleManufacturer
				+ ", pvModuleModEl=" + pvModuleModEl + ", inverterModel=" + inverterModel + ", stringOne=" + stringOne
				+ ", stringTwo=" + stringTwo + ", stringThree=" + stringThree + ", stringFour=" + stringFour
				+ ", stringFive=" + stringFive + ", inverterManufacturer=" + inverterManufacturer
				+ ", secondInverterManufacturer=" + secondInverterManufacturer + ", secondInverterModel="
				+ secondInverterModel + ", secondStringOne=" + secondStringOne + ", secondStringTwo=" + secondStringTwo
				+ ", secondStringThree=" + secondStringThree + ", secondStringFour=" + secondStringFour
				+ ", secondStringFive=" + secondStringFive + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

}
