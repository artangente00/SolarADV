package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

public class NewRoofAttachmentModel {

	private String manufacturer;
	private String model;
	private String integratedFlashing;
	private String allowedRoof;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	
	//F.B CR-686
	private AuthentificationEntity firstUpdater;
	private AuthentificationEntity secondUpdater;
	private AuthentificationEntity thirdUpdater;
	private AuthentificationEntity verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;

	public NewRoofAttachmentModel() {
		super();
	}

	public NewRoofAttachmentModel(String manufacturer, String model, String integratedFlashing, String allowedRoof,
			String manufacturerMappingValue, String modelMappingValue) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.integratedFlashing = integratedFlashing;
		this.allowedRoof = allowedRoof;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
	}
	
	public AuthentificationEntity getFirstUpdater() {
		return firstUpdater;
	}

	public void setFirstUpdater(AuthentificationEntity firstUpdater) {
		this.firstUpdater = firstUpdater;
	}

	public AuthentificationEntity getSecondUpdater() {
		return secondUpdater;
	}

	public void setSecondUpdater(AuthentificationEntity secondUpdater) {
		this.secondUpdater = secondUpdater;
	}

	public AuthentificationEntity getThirdUpdater() {
		return thirdUpdater;
	}

	public void setThirdUpdater(AuthentificationEntity thirdUpdater) {
		this.thirdUpdater = thirdUpdater;
	}

	public AuthentificationEntity getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(AuthentificationEntity verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Date getDateVerification() {
		return dateVerification;
	}

	public void setDateVerification(Date dateVerification) {
		this.dateVerification = dateVerification;
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

	public String getIntegratedFlashing() {
		return integratedFlashing;
	}

	public void setIntegratedFlashing(String integratedFlashing) {
		this.integratedFlashing = integratedFlashing;
	}

	public String getAllowedRoof() {
		return allowedRoof;
	}

	public void setAllowedRoof(String allowedRoof) {
		this.allowedRoof = allowedRoof;
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
