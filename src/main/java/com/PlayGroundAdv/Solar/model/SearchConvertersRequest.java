package com.PlayGroundAdv.Solar.model;

public class SearchConvertersRequest {

	private String manufacturer;
	private String model;
	private Boolean favorite;
	private Boolean isDeleted;

	/**
	 * @param manufacturer
	 * @param model
	 * @param favorite
	 * @param isDeleted
	 */
	public SearchConvertersRequest() {
		super();
	}

	public SearchConvertersRequest(String manufacturer, String model, Boolean favorite, Boolean isDeleted) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.favorite = favorite;
		this.isDeleted = isDeleted;
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

	public Boolean getFavorite() {
		return favorite;
	}

	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "SearchConvertersRequest [manufacturer=" + manufacturer + ", model=" + model + ", favorite=" + favorite
				+ ", isDeleted=" + isDeleted + "]";
	}

}
