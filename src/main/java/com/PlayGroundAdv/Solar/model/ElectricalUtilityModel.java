package com.PlayGroundAdv.Solar.model;

public class ElectricalUtilityModel {
	private Long id;
	private String utilityCompanyName;
	private String phone;
	private String state;
	private String zip;
	private String utilityNumber;
	private String utilityType;
	private String county;
	private String aCDReq;
	private String pVMReq;
	private String aCDPVMOrientation;
	private String mappingValue;

	public ElectricalUtilityModel() {
		super();
	}

	public ElectricalUtilityModel(Long id, String utilityCompanyName, String phone, String state, String zip,
			String utilityNumber, String utilityType, String county, String aCDReq, String pVMReq,
			String aCDPVMOrientation, String mappingValue) {
		super();
		this.id = id;
		this.utilityCompanyName = utilityCompanyName;
		this.phone = phone;
		this.state = state;
		this.zip = zip;
		this.utilityNumber = utilityNumber;
		this.utilityType = utilityType;
		this.county = county;
		this.aCDReq = aCDReq;
		this.pVMReq = pVMReq;
		this.aCDPVMOrientation = aCDPVMOrientation;
		this.mappingValue = mappingValue;
	}
	
	

	public String getUtilityCompanyName() {
		return utilityCompanyName;
	}

	public void setUtilityCompanyName(String utilityCompanyName) {
		this.utilityCompanyName = utilityCompanyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getUtilityNumber() {
		return utilityNumber;
	}

	public void setUtilityNumber(String utilityNumber) {
		this.utilityNumber = utilityNumber;
	}

	public String getUtilityType() {
		return utilityType;
	}

	public void setUtilityType(String utilityType) {
		this.utilityType = utilityType;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getaCDReq() {
		return aCDReq;
	}

	public void setaCDReq(String aCDReq) {
		this.aCDReq = aCDReq;
	}

	public String getpVMReq() {
		return pVMReq;
	}

	public void setpVMReq(String pVMReq) {
		this.pVMReq = pVMReq;
	}

	public String getaCDPVMOrientation() {
		return aCDPVMOrientation;
	}

	public void setaCDPVMOrientation(String aCDPVMOrientation) {
		this.aCDPVMOrientation = aCDPVMOrientation;
	}

	public String getMappingValue() {
		return mappingValue;
	}

	public void setMappingValue(String mappingValue) {
		this.mappingValue = mappingValue;
	}

}
