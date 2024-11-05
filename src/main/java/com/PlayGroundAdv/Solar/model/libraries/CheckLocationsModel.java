package com.PlayGroundAdv.Solar.model.libraries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CheckLocationsModel {
	
	private Long id;
	private String owner;
	private String locationId;
	private String locationName;
	private String instructionGuide;
	private String attachement;
	private Boolean roofMounted;
	private Boolean groundMounted;
	private Boolean poleMounted;
	private Boolean patioCover;
	private Boolean otherMounted;
	private String otherMountedValue;
	private Boolean ahj;
	private Boolean serviceVoltage;
	private Boolean roofingMaterial;
	private Boolean inverterTechnology;
	private Boolean moreThanOneInverter;
	private Boolean pointOfConnection;
	private Boolean pairedWithBatteryStorage;
	private String ahjValue;
	private String serviceVoltageValue;
	private String roofingMaterialValue;
	private String inverterTechnologyValue;
	private Boolean moreThanOneInverterValue;
	private String pointOfConnectionValue;
	private Boolean pairedWithBatteryStorageValue;
	private Boolean isShown;
	private Boolean isDeleted;

}
