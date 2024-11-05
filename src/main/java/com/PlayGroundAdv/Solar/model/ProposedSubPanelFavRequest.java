package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

public class ProposedSubPanelFavRequest {

	private Long id;
	private String isFav;
	private String manufacturer;
	private String model;
	private String typeSubPanel;
	private String polesNumber;
	private String nemaRating;
	private String ratedCurrent;
	private String dropdownOption;
	private String sheetFileName;
	private String updated;
	private Boolean isDeleted;
	private AuthentificationEntity owner;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	
	private AuthentificationEntity firstUpdater;
	private AuthentificationEntity secondUpdater;
	private AuthentificationEntity thirdUpdater;
	private AuthentificationEntity verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;

	public ProposedSubPanelFavRequest() {
		super();
	}

	public ProposedSubPanelFavRequest(Long id, String isFav, String manufacturer, String model, String typeSubPanel,
			String polesNumber, String nemaRating, String ratedCurrent, String dropdownOption, String sheetFileName,
			String updated, Boolean isDeleted, AuthentificationEntity owner, Boolean hasCorrectionRequest,
			String eroneousContent, String eroneousContentOther, String eroneousDescription) {
		super();
		this.id = id;
		this.isFav = isFav;
		this.manufacturer = manufacturer;
		this.model = model;
		this.typeSubPanel = typeSubPanel;
		this.polesNumber = polesNumber;
		this.nemaRating = nemaRating;
		this.ratedCurrent = ratedCurrent;
		this.dropdownOption = dropdownOption;
		this.sheetFileName = sheetFileName;
		this.updated = updated;
		this.isDeleted = isDeleted;
		this.owner = owner;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsFav() {
		return isFav;
	}

	public void setIsFav(String isFav) {
		this.isFav = isFav;
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

	public String getTypeSubPanel() {
		return typeSubPanel;
	}

	public void setTypeSubPanel(String typeSubPanel) {
		this.typeSubPanel = typeSubPanel;
	}

	public String getPolesNumber() {
		return polesNumber;
	}

	public void setPolesNumber(String polesNumber) {
		this.polesNumber = polesNumber;
	}

	public String getNemaRating() {
		return nemaRating;
	}

	public void setNemaRating(String nemaRating) {
		this.nemaRating = nemaRating;
	}

	public String getRatedCurrent() {
		return ratedCurrent;
	}

	public void setRatedCurrent(String ratedCurrent) {
		this.ratedCurrent = ratedCurrent;
	}

	public String getDropdownOption() {
		return dropdownOption;
	}

	public void setDropdownOption(String dropdownOption) {
		this.dropdownOption = dropdownOption;
	}

	public String getSheetFileName() {
		return sheetFileName;
	}

	public void setSheetFileName(String sheetFileName) {
		this.sheetFileName = sheetFileName;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public AuthentificationEntity getOwner() {
		return owner;
	}

	public void setOwner(AuthentificationEntity owner) {
		this.owner = owner;
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

	@Override
	public String toString() {
		return "ProposedSubPanelFavRequest [id=" + id + ", isFav=" + isFav + ", manufacturer=" + manufacturer
				+ ", model=" + model + ", typeSubPanel=" + typeSubPanel + ", polesNumber=" + polesNumber
				+ ", nemaRating=" + nemaRating + ", ratedCurrent=" + ratedCurrent + ", dropdownOption=" + dropdownOption
				+ ", sheetFileName=" + sheetFileName + ", updated=" + updated + ", isDeleted=" + isDeleted + ", owner="
				+ owner + ", hasCorrectionRequest=" + hasCorrectionRequest + ", eroneousContent=" + eroneousContent
				+ ", eroneousContentOther=" + eroneousContentOther + ", eroneousDescription=" + eroneousDescription
				+ "]";
	}

}
