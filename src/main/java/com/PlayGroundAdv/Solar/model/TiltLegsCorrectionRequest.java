package com.PlayGroundAdv.Solar.model;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

public class TiltLegsCorrectionRequest {
	private Long id;
	private String isFav;
	private String manufacturer;
	private String model;
	private String weight;
	private String updated;
	private Boolean isDeleted;
	private AuthentificationEntity owner;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private Long idUser;

	public TiltLegsCorrectionRequest() {
		super();
	}

	public TiltLegsCorrectionRequest(Long id, String isFav, String manufacturer, String model, String weight,
			String updated, Boolean isDeleted, AuthentificationEntity owner, Boolean hasCorrectionRequest,
			String eroneousContent, String eroneousContentOther, String eroneousDescription, Long idUser) {
		super();
		this.id = id;
		this.isFav = isFav;
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
		this.idUser = idUser;
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

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "TiltLegsCorrectionRequest [id=" + id + ", isFav=" + isFav + ", manufacturer=" + manufacturer
				+ ", model=" + model + ", weight=" + weight + ", updated=" + updated + ", isDeleted=" + isDeleted
				+ ", owner=" + owner + ", hasCorrectionRequest=" + hasCorrectionRequest + ", eroneousContent="
				+ eroneousContent + ", eroneousContentOther=" + eroneousContentOther + ", eroneousDescription="
				+ eroneousDescription + ", idUser=" + idUser + "]";
	}

}
