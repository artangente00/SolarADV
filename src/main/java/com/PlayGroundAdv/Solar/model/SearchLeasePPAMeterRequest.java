package com.PlayGroundAdv.Solar.model;

public class SearchLeasePPAMeterRequest {

	String manufacturer;
	String model;
	Boolean isFav;
	Boolean isDel;

	public SearchLeasePPAMeterRequest() {
		super();
	}

	public SearchLeasePPAMeterRequest(String manufacturer, String model, Boolean isFav, Boolean isDel) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.isFav = isFav;
		this.isDel = isDel;
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

	public Boolean getIsFav() {
		return isFav;
	}

	public void setIsFav(Boolean isFav) {
		this.isFav = isFav;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "SearchLeasePPAMeterRequest [manufacturer=" + manufacturer + ", model=" + model + ", isFav=" + isFav
				+ ", isDel=" + isDel + "]";
	}

}
