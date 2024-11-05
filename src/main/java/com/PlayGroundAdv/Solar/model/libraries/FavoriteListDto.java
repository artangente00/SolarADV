package com.PlayGroundAdv.Solar.model.libraries;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FavoriteListDto {
	
	private Long id;
	private String manufacturer;
	private String model;
	private Boolean isFavorite;
	
	//A/B Inverter
	private List<String> serviceVoltage;
	private Boolean microInverter;
	private Boolean optimizer;
	private String vdcmax;
	private String wireQty;
	
	//A/B Roof Attachment
	private String integrated;
	private List<Long> allowedRoof;
	
	//A.B DC Disconnect
	private String dropdownOption;
	
	//Get Existing Models
	public FavoriteListDto(Long id, String manufacturer, String model, Boolean isFavorite) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.isFavorite = isFavorite;
	}
	
	//ATS - Generator
	public FavoriteListDto(Long id, String manufacturer, String model) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
	}

	//Inverter
	public FavoriteListDto(Long id, String manufacturer, String model, List<String> serviceVoltage,
			Boolean microInverter, Boolean optimizer, String vdcmax,String wireQty) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.serviceVoltage = serviceVoltage;
		this.microInverter = microInverter;
		this.optimizer = optimizer;
		this.vdcmax = vdcmax;
		this.wireQty = wireQty;
	}
	
	//A/B Roof Attachment
	public FavoriteListDto(Long id, String manufacturer, String model, String integrated, List<Long> allowedRoof) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.integrated = integrated;
		this.allowedRoof = allowedRoof;
	}
	
	//DC Disconnect
	public FavoriteListDto(Long id, String manufacturer, String model, String dropdownOption) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.dropdownOption = dropdownOption;
	}

}
