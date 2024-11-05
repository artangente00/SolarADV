package com.PlayGroundAdv.Solar.model.libraries;

public class DCCombinerDisconnectRequest {

	private Long id;
	private String manufacturer;
	private String model;
	private String ocpd;
	private String weight;
	private String nemaRating;
	private String maxInput;
	private String maxContiOutputCurrent;
	private String maxOutputCurrent;
	private String typeDc;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private String rsd;

	public DCCombinerDisconnectRequest() {
		super();
	}

	public DCCombinerDisconnectRequest(Long id, String manufacturer, String model, String ocpd, String weight,
			String nemaRating, String maxInput, String maxContiOutputCurrent, String maxOutputCurrent, String typeDc,
			String manufacturerMappingValue, String modelMappingValue, String rsd) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.ocpd = ocpd;
		this.weight = weight;
		this.nemaRating = nemaRating;
		this.maxInput = maxInput;
		this.maxContiOutputCurrent = maxContiOutputCurrent;
		this.maxOutputCurrent = maxOutputCurrent;
		this.typeDc = typeDc;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.rsd = rsd;
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

	public String getRsd() {
		return rsd;
	}

	public void setRsd(String rsd) {
		this.rsd = rsd;
	}
	

}
