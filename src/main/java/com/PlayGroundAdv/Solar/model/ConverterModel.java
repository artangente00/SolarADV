package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ConverterModel {

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
	private AuthentificationEntity user;
	private Boolean isFavorit;
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
	private AuthentificationEntity firstUpdater;
	private AuthentificationEntity secondUpdater;
	private AuthentificationEntity thirdUpdater;
	private AuthentificationEntity verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;


	// Constructor With entity Getter without filed "isFavorit"
	public ConverterModel(DCOptimizerEntity dCOptimizerEntity) {
		super();
		this.id = dCOptimizerEntity.getId();
		this.manufacturer = dCOptimizerEntity.getManufacturer();
		this.model = dCOptimizerEntity.getModel();
		this.weight = dCOptimizerEntity.getWeight();
		this.ratedOutputIsc = dCOptimizerEntity.getRatedOutputIsc();
		this.maxInputVoltage = dCOptimizerEntity.getMaxInputVoltage();
		this.maxSeriesFuseRating = dCOptimizerEntity.getMaxSeriesFuseRating();
		this.phase = dCOptimizerEntity.getPhase();
		this.pvModulePower = dCOptimizerEntity.getPvModulePower();
		this.minString = dCOptimizerEntity.getMinString();
		this.maxString = dCOptimizerEntity.getMaxString();
		this.maxPowerString = dCOptimizerEntity.getMaxPowerString();
		this.maxOutputVoltage = dCOptimizerEntity.getMaxOutputVoltage();
		this.lastUpDate = dCOptimizerEntity.getLastUpDate();
		this.user = dCOptimizerEntity.getUser();
		this.isDeleted = dCOptimizerEntity.getIsDeleted();
		this.hasSuperUserEdit = dCOptimizerEntity.getHasSuperUserEdit();
		this.hasCorrectionRequest = dCOptimizerEntity.getHasCorrectionRequest();
		this.eroneousContent = dCOptimizerEntity.getEroneousContent();
		this.eroneousContentOther = dCOptimizerEntity.getEroneousContentOther();
		this.eroneousDescription = dCOptimizerEntity.getEroneousDescription();
		this.qtyModuleOpt = dCOptimizerEntity.getQtyModuleOpt();
		this.manufacturerMappingValue = dCOptimizerEntity.getManufacturerMappingValue();
		this.modelMappingValue = dCOptimizerEntity.getModelMappingValue();
		this.type = dCOptimizerEntity.getType();
	}

}
