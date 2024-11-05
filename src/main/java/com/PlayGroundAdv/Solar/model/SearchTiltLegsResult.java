package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

public class SearchTiltLegsResult {

	private Long id;
	private Boolean isFav;
	private String manufacturer;
	private String model;
	private String weight;
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


	public SearchTiltLegsResult() {
		super();
	}

	public SearchTiltLegsResult(Long id, String manufacturer, String model, String weight, String updated,
			Boolean isDeleted, UsersEntityResult owner, Boolean hasCorrectionRequest, String eroneousContent,
			String eroneousContentOther, String eroneousDescription, String fullName) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.weight = weight;
		this.updated = updated;
		this.isDeleted = isDeleted;
		this.owner = owner;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.fullName = fullName;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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
		return "SearchTiltLegsResult [id=" + id + ", isFav=" + isFav + ", manufacturer=" + manufacturer + ", model="
				+ model + ", weight=" + weight + ", updated=" + updated + ", isDeleted=" + isDeleted + ", owner="
				+ owner + ", hasCorrectionRequest=" + hasCorrectionRequest + ", eroneousContent=" + eroneousContent
				+ ", eroneousContentOther=" + eroneousContentOther + ", eroneousDescription=" + eroneousDescription
				+ ", FullName=" + fullName + "]";
	}

}
