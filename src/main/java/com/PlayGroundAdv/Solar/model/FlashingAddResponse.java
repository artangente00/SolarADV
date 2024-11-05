package com.PlayGroundAdv.Solar.model;

public class FlashingAddResponse {
	private Long id;
	private String manufacturer;
	private String model;

	public FlashingAddResponse() {
		super();
	}

	public FlashingAddResponse(Long id, String manufacturer, String model) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
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

}
