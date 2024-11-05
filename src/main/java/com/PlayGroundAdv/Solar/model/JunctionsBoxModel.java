package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class JunctionsBoxModel {

	private Long id;
	private String manufacturer;
	private String model;
	private String dropdownOption;
	private String ocpd;
	private String weight;
	private String nemaRating;
	private String maxInput;
	private String maxContiOutputCurrent;
	private String maxOutputCurrent;
	private String typeDc;
	private Boolean isDeleted;
	private Boolean isShown;
	private String owner;
	private String lastUpdate;
	private String idOwner;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private Boolean hasCorrectionRequest;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	
	//F.B CR-686
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;

	

	public JunctionsBoxModel() {
		super();
	}

	public JunctionsBoxModel(Long id, String manufacturer, String model, String dropdownOption, String ocpd,
			String weight, String nemaRating, String maxInput, String maxContiOutputCurrent, String maxOutputCurrent,
			String typeDc, Boolean isDeleted, Boolean isShown, String owner, String lastUpdate, String idOwner,
			Boolean hasCorrectionRequest, String eroneousContent, String eroneousContentOther,
			String eroneousDescription, String manufacturerMappingValue, String modelMappingValue) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.dropdownOption = dropdownOption;
		this.ocpd = ocpd;
		this.weight = weight;
		this.nemaRating = nemaRating;
		this.maxInput = maxInput;
		this.maxContiOutputCurrent = maxContiOutputCurrent;
		this.maxOutputCurrent = maxOutputCurrent;
		this.typeDc = typeDc;
		this.isDeleted = isDeleted;
		this.isShown = isShown;
		this.owner = owner;
		this.lastUpdate = lastUpdate;
		this.idOwner = idOwner;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
	}

	
	
	public JunctionsBoxModel(Long id, String manufacturer, String model, String dropdownOption, String ocpd,
			String weight, String nemaRating, String maxInput, String maxContiOutputCurrent, String maxOutputCurrent,
			String typeDc, Boolean isDeleted, Boolean isShown, String owner, String lastUpdate, String idOwner,
			String eroneousContent, String eroneousContentOther, String eroneousDescription,
			Boolean hasCorrectionRequest, String manufacturerMappingValue, String modelMappingValue,
			UsersEntityResult firstUpdater, UsersEntityResult secondUpdater, UsersEntityResult thirdUpdater,
			UsersEntityResult verifiedBy, Boolean isVerified, Date dateVerification) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.dropdownOption = dropdownOption;
		this.ocpd = ocpd;
		this.weight = weight;
		this.nemaRating = nemaRating;
		this.maxInput = maxInput;
		this.maxContiOutputCurrent = maxContiOutputCurrent;
		this.maxOutputCurrent = maxOutputCurrent;
		this.typeDc = typeDc;
		this.isDeleted = isDeleted;
		this.isShown = isShown;
		this.owner = owner;
		this.lastUpdate = lastUpdate;
		this.idOwner = idOwner;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.firstUpdater = firstUpdater;
		this.secondUpdater = secondUpdater;
		this.thirdUpdater = thirdUpdater;
		this.verifiedBy = verifiedBy;
		this.isVerified = isVerified;
		this.dateVerification = dateVerification;
	}

	public String getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
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

	public String getOcpd() {
		return ocpd;
	}

	public void setOcpd(String ocpd) {
		this.ocpd = ocpd;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getNemaRating() {
		return nemaRating;
	}

	public void setNemaRating(String nemaRating) {
		this.nemaRating = nemaRating;
	}

	public String getMaxInput() {
		return maxInput;
	}

	public void setMaxInput(String maxInput) {
		this.maxInput = maxInput;
	}

	public String getMaxContiOutputCurrent() {
		return maxContiOutputCurrent;
	}

	public void setMaxContiOutputCurrent(String maxContiOutputCurrent) {
		this.maxContiOutputCurrent = maxContiOutputCurrent;
	}

	public String getMaxOutputCurrent() {
		return maxOutputCurrent;
	}

	public void setMaxOutputCurrent(String maxOutputCurrent) {
		this.maxOutputCurrent = maxOutputCurrent;
	}

	public String getTypeDc() {
		return typeDc;
	}

	public void setTypeDc(String typeDc) {
		this.typeDc = typeDc;
	}

	public Boolean getIsShown() {
		return isShown;
	}

	public void setIsShown(Boolean isShown) {
		this.isShown = isShown;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDropdownOption() {
		return dropdownOption;
	}

	public void setDropdownOption(String dropdownOption) {
		this.dropdownOption = dropdownOption;
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

	public Boolean getHasCorrectionRequest() {
		return hasCorrectionRequest;
	}

	public void setHasCorrectionRequest(Boolean hasCorrectionRequest) {
		this.hasCorrectionRequest = hasCorrectionRequest;
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
	
	

	public UsersEntityResult getFirstUpdater() {
		return firstUpdater;
	}

	public void setFirstUpdater(UsersEntityResult firstUpdater) {
		this.firstUpdater = firstUpdater;
	}

	public UsersEntityResult getSecondUpdater() {
		return secondUpdater;
	}

	public void setSecondUpdater(UsersEntityResult secondUpdater) {
		this.secondUpdater = secondUpdater;
	}

	public UsersEntityResult getThirdUpdater() {
		return thirdUpdater;
	}

	public void setThirdUpdater(UsersEntityResult thirdUpdater) {
		this.thirdUpdater = thirdUpdater;
	}

	public UsersEntityResult getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(UsersEntityResult verifiedBy) {
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

	@Override
	public String toString() {
		return "JunctionsBoxModel [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model
				+ ", dropdownOption=" + dropdownOption + ", ocpd=" + ocpd + ", weight=" + weight + ", nemaRating="
				+ nemaRating + ", maxInput=" + maxInput + ", maxContiOutputCurrent=" + maxContiOutputCurrent
				+ ", maxOutputCurrent=" + maxOutputCurrent + ", typeDc=" + typeDc + ", isDeleted=" + isDeleted
				+ ", isShown=" + isShown + ", owner=" + owner + ", lastUpdate=" + lastUpdate + ", idOwner=" + idOwner
				+ ", eroneousContent=" + eroneousContent + ", eroneousContentOther=" + eroneousContentOther
				+ ", eroneousDescription=" + eroneousDescription + ", hasCorrectionRequest=" + hasCorrectionRequest
				+ ", manufacturerMappingValue=" + manufacturerMappingValue + ", modelMappingValue=" + modelMappingValue
				+ "]";
	}

}
