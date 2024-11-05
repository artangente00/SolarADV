package com.PlayGroundAdv.Solar.model;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

public class ConverterCorrectionModel {
	private Long id;
	private String manufacturer;
	private String model;
	private String weight;
	private String ratedOutputIsc;
	private String maxInputVoltage;
	private String maxSeriesFuseRating;
	private String phase;
	private String pvModulePower;
	private String minString;
	private String maxString;
	private String maxPowerString;
	private String maxOutputVoltage;
	private String lastUpDate;
	private Boolean isDeleted;
	private AuthentificationEntity user;
	private Boolean isFavorit;
	private Boolean hasSuperUserEdit;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private Long idUser;
	private String qtyModuleOpt;

	/**
	 * 
	 */
	public ConverterCorrectionModel() {
		super();
	}

	public ConverterCorrectionModel(Long id, String manufacturer, String model, String weight, String ratedOutputIsc,
			String maxInputVoltage, String maxSeriesFuseRating, String phase, String pvModulePower, String minString,
			String maxString, String maxPowerString, String maxOutputVoltage, String lastUpDate, Boolean isDeleted,
			AuthentificationEntity user, Boolean isFavorit, Boolean hasSuperUserEdit, Boolean hasCorrectionRequest,
			String eroneousContent, String eroneousContentOther, String eroneousDescription, Long idUser,
			String qtyModuleOpt) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.weight = weight;
		this.ratedOutputIsc = ratedOutputIsc;
		this.maxInputVoltage = maxInputVoltage;
		this.maxSeriesFuseRating = maxSeriesFuseRating;
		this.phase = phase;
		this.pvModulePower = pvModulePower;
		this.minString = minString;
		this.maxString = maxString;
		this.maxPowerString = maxPowerString;
		this.maxOutputVoltage = maxOutputVoltage;
		this.lastUpDate = lastUpDate;
		this.isDeleted = isDeleted;
		this.user = user;
		this.isFavorit = isFavorit;
		this.hasSuperUserEdit = hasSuperUserEdit;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.idUser = idUser;
		this.qtyModuleOpt = qtyModuleOpt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getQtyModuleOpt() {
		return qtyModuleOpt;
	}

	public void setQtyModuleOpt(String qtyModuleOpt) {
		this.qtyModuleOpt = qtyModuleOpt;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRatedOutputIsc() {
		return ratedOutputIsc;
	}

	public void setRatedOutputIsc(String ratedOutputIsc) {
		this.ratedOutputIsc = ratedOutputIsc;
	}

	public String getMaxInputVoltage() {
		return maxInputVoltage;
	}

	public void setMaxInputVoltage(String maxInputVoltage) {
		this.maxInputVoltage = maxInputVoltage;
	}

	public String getMaxSeriesFuseRating() {
		return maxSeriesFuseRating;
	}

	public void setMaxSeriesFuseRating(String maxSeriesFuseRating) {
		this.maxSeriesFuseRating = maxSeriesFuseRating;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getPvModulePower() {
		return pvModulePower;
	}

	public void setPvModulePower(String pvModulePower) {
		this.pvModulePower = pvModulePower;
	}

	public String getMinString() {
		return minString;
	}

	public void setMinString(String minString) {
		this.minString = minString;
	}

	public String getMaxString() {
		return maxString;
	}

	public void setMaxString(String maxString) {
		this.maxString = maxString;
	}

	public String getMaxPowerString() {
		return maxPowerString;
	}

	public void setMaxPowerString(String maxPowerString) {
		this.maxPowerString = maxPowerString;
	}

	public String getMaxOutputVoltage() {
		return maxOutputVoltage;
	}

	public void setMaxOutputVoltage(String maxOutputVoltage) {
		this.maxOutputVoltage = maxOutputVoltage;
	}

	public String getLastUpDate() {
		return lastUpDate;
	}

	public void setLastUpDate(String lastUpDate) {
		this.lastUpDate = lastUpDate;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public AuthentificationEntity getUser() {
		return user;
	}

	public void setUser(AuthentificationEntity user) {
		this.user = user;
	}

	public Boolean getIsFavorit() {
		return isFavorit;
	}

	public void setIsFavorit(Boolean isFavorit) {
		this.isFavorit = isFavorit;
	}

	public Boolean getHasSuperUserEdit() {
		return hasSuperUserEdit;
	}

	public void setHasSuperUserEdit(Boolean hasSuperUserEdit) {
		this.hasSuperUserEdit = hasSuperUserEdit;
	}

	public Boolean getHasCorrectionRequest() {
		return hasCorrectionRequest;
	}

	public void setHasCorrectionRequest(Boolean hasCorrectionRequest) {
		this.hasCorrectionRequest = hasCorrectionRequest;
	}

	public String getEroneousContent() {
		return eroneousContent;
	}

	public void setEroneousContent(String eroneousContent) {
		this.eroneousContent = eroneousContent;
	}

	public String getEroneousContentOther() {
		return eroneousContentOther;
	}

	public void setEroneousContentOther(String eroneousContentOther) {
		this.eroneousContentOther = eroneousContentOther;
	}

	public String getEroneousDescription() {
		return eroneousDescription;
	}

	public void setEroneousDescription(String eroneousDescription) {
		this.eroneousDescription = eroneousDescription;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "converterCorrectionModel [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model
				+ ", weight=" + weight + ", ratedOutputIsc=" + ratedOutputIsc + ", maxInputVoltage=" + maxInputVoltage
				+ ", maxSeriesFuseRating=" + maxSeriesFuseRating + ", phase=" + phase + ", pvModulePower="
				+ pvModulePower + ", minString=" + minString + ", maxString=" + maxString + ", maxPowerString="
				+ maxPowerString + ", maxOutputVoltage=" + maxOutputVoltage + ", lastUpDate=" + lastUpDate
				+ ", isDeleted=" + isDeleted + ", user=" + user + ", isFavorit=" + isFavorit + ", hasSuperUserEdit="
				+ hasSuperUserEdit + ", hasCorrectionRequest=" + hasCorrectionRequest + ", eroneousContent="
				+ eroneousContent + ", eroneousContentOther=" + eroneousContentOther + ", eroneousDescription="
				+ eroneousDescription + ", idUser=" + idUser + "]";
	}

}
