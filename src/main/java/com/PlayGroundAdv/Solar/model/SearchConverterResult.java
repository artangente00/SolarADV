package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchConverterResult {
	private Boolean isFavorit;
	private Long id;
	private String manufacturer;
	private String model;
	private String weight;
	private String ratedOutputIsc;
	private String maxInputVoltage;
	private String maxSeriesFuseRating;
	private String phase;
	private String pvModulePower;
	private String minString;
	private String maxString;
	private String maxPowerString;
	private String maxOutputVoltage;
	private String lastUpDate;
	private Boolean isDeleted;
	private UsersEntityResult user;
	private Boolean hasSuperUserEdit;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private String qtyModuleOpt;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private String type;
	private Boolean altersVoltage;
	
	//F.B CR-686
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;

	public SearchConverterResult(Boolean isFavorit, Long id, String manufacturer, String model, String weight,
			String ratedOutputIsc, String maxInputVoltage, String maxSeriesFuseRating, String phase,
			String pvModulePower, String minString, String maxString, String maxPowerString, String maxOutputVoltage,
			String lastUpDate, Boolean isDeleted, UsersEntityResult user, Boolean hasSuperUserEdit,
			Boolean hasCorrectionRequest, String eroneousContent, String eroneousContentOther,
			String eroneousDescription, String qtyModuleOpt, String manufacturerMappingValue,
			String modelMappingValue, String type, Boolean altersVoltage) {
		super();
		this.isFavorit = isFavorit;
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.weight = weight;
		this.ratedOutputIsc = ratedOutputIsc;
		this.maxInputVoltage = maxInputVoltage;
		this.maxSeriesFuseRating = maxSeriesFuseRating;
		this.phase = phase;
		this.pvModulePower = pvModulePower;
		this.minString = minString;
		this.maxString = maxString;
		this.maxPowerString = maxPowerString;
		this.maxOutputVoltage = maxOutputVoltage;
		this.lastUpDate = lastUpDate;
		this.isDeleted = isDeleted;
		this.user = user;
		this.hasSuperUserEdit = hasSuperUserEdit;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.qtyModuleOpt = qtyModuleOpt;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.type = type;
		this.altersVoltage = altersVoltage;
	}


}
