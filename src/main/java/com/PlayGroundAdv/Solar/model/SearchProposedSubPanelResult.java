package com.PlayGroundAdv.Solar.model;

import java.util.Date;

public class SearchProposedSubPanelResult {

	private Long id;
	private Boolean isFav;
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
	private UsersEntityResult owner;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private String fullName;
	
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;


	public SearchProposedSubPanelResult() {
		super();
	}

	public SearchProposedSubPanelResult(Long id, String manufacturer, String model, String typeSubPanel,
			String polesNumber, String nemaRating, String ratedCurrent, String dropdownOption, String sheetFileName,
			String updated, Boolean isDeleted, UsersEntityResult owner, Boolean hasCorrectionRequest,
			String eroneousContent, String eroneousContentOther, String eroneousDescription, String fullName) {
		super();
		this.id = id;
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
		this.fullName = fullName;
	}
	
	public SearchProposedSubPanelResult(Long id, Boolean isFav, String manufacturer, String model, String typeSubPanel,
			String polesNumber, String nemaRating, String ratedCurrent, String dropdownOption, String sheetFileName,
			String updated, Boolean isDeleted, UsersEntityResult owner, Boolean hasCorrectionRequest,
			String eroneousContent, String eroneousContentOther, String eroneousDescription, String fullName,
			UsersEntityResult firstUpdater, UsersEntityResult secondUpdater, UsersEntityResult thirdUpdater,
			UsersEntityResult verifiedBy, Boolean isVerified, Date dateVerification) {
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
		this.fullName = fullName;
		this.firstUpdater = firstUpdater;
		this.secondUpdater = secondUpdater;
		this.thirdUpdater = thirdUpdater;
		this.verifiedBy = verifiedBy;
		this.isVerified = isVerified;
		this.dateVerification = dateVerification;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsFav() {
		return isFav;
	}

	public void setIsFav(Boolean isFav) {
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

	public UsersEntityResult getOwner() {
		return owner;
	}

	public void setOwner(UsersEntityResult owner) {
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "SearchProposedSubPanelResult [id=" + id + ", isFav=" + isFav + ", manufacturer=" + manufacturer
				+ ", model=" + model + ", typeSubPanel=" + typeSubPanel + ", polesNumber=" + polesNumber
				+ ", nemaRating=" + nemaRating + ", ratedCurrent=" + ratedCurrent + ", dropdownOption=" + dropdownOption
				+ ", sheetFileName=" + sheetFileName + ", updated=" + updated + ", isDeleted=" + isDeleted + ", owner="
				+ owner + ", hasCorrectionRequest=" + hasCorrectionRequest + ", eroneousContent=" + eroneousContent
				+ ", eroneousContentOther=" + eroneousContentOther + ", eroneousDescription=" + eroneousDescription
				+ ", fullName=" + fullName + "]";
	}

}
