package com.PlayGroundAdv.Solar.entity.libraries;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * @author Arij
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "DCOptimizerEntity")
public class DCOptimizerEntity {
    
	@Id
	@SequenceGenerator(name="dcopt_sequence32", sequenceName = "dcopt_sequence32", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="dcopt_sequence32")    
	private Long id;
	
	@Column(name="MANUFACTURER")
	private String manufacturer;
	
	@Column(name="MODEL")
	private String model;
	
	@Column(name="WEIGHT")
	private String weight;
	
	@Column(name="RATED_OUTPUT_ISC")
	private String ratedOutputIsc;
	
	@Column(name="MAX_INPUT_VOLTAGE")
	private String maxInputVoltage;
	
	@Column(name="MAX_SERIES_FUSE_RATING")
	private String maxSeriesFuseRating;
	
	@Column(name="PHASE")
	private String phase;
	
	
	@Column(name="PV_MODULE_POWER")
	private String pvModulePower;
	
	@Column(name="MIN_STRNG")
	private String minString;
	
	@Column(name="MAX_STRING")
	private String maxString;
	
	@Column(name="MAX_POWER_STRING")
	private String maxPowerString;
	
	@Column(name="MAX_OUTPUT_VOLTAGE")
	private String maxOutputVoltage;
	
	
	@Column(name="LAST_UP_DATE")
	private String lastUpDate;
	
	@Column(name="IS_DELETED")
	private Boolean isDeleted;
	
	@Column(name="HAS_SUPER_USER_EDIT")
	private Boolean hasSuperUserEdit;
	
	@Column(name="ADD_Date")
	private Date addDate;
	
	@Column(name="HAS_CORRECTION_REQUEST")
	private Boolean hasCorrectionRequest;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private AuthentificationEntity user;
	
	@Column(name="ERONEOUS_CONTENT")
	private String eroneousContent;
	
	@Column(name="ERONEOUS_CONTENT_OTHER")
	private String eroneousContentOther;
	
	@Column(name="ERONEOUS_DESCRIPTION")
	private String eroneousDescription;
	
	@Column(name="QTY_MODULE_OPT")
	private String qtyModuleOpt;
	
	@Column(name="MANUFACTURER_MAPPING_VALUE")
	private String manufacturerMappingValue;
	
	@Column(name="MODEL_MAPPING_VALUE")
	private String modelMappingValue;
	
	@Column(name="TYPE")
	private String type;
	
	@Column
	private Boolean altersVoltage;
	
	
	@JoinColumn(name = "FIRST_UPDATER")
	@ManyToOne
	private AuthentificationEntity firstUpdater;
	
	@JoinColumn(name = "SECOND_UPDATER")
	@ManyToOne
	private AuthentificationEntity secondUpdater;
	
	@JoinColumn(name = "THIRD_UPDATER")
	@ManyToOne
	private AuthentificationEntity thirdUpdater;
	
	@JoinColumn(name = "VERIFIED_BY")
	@ManyToOne
	private AuthentificationEntity verifiedBy;
	
	@Column(name="IS_VERIFIED")
	private Boolean isVerified;
	
	@Column(name="DATE_VERIFICATION")
	private Date dateVerification;
	
	public DCOptimizerEntity(Long getId, String manufacturer, String model, String weight, String ratedOutputIsc,
			String maxInputVoltage, String maxSeriesFuseRating, String phase, String pvModulePower, String minString,
			String maxString, String maxPowerString, String maxOutputVoltage, String lastUpDate, Boolean isDeleted,
			Boolean hasSuperUserEdit, Date addDate, Boolean hasCorrectionRequest, AuthentificationEntity user,
			String eroneousContent, String eroneousContentOther, String eroneousDescription, String qtyModuleOpt,
			String manufacturerMappingValue, String modelMappingValue, String type) {
		super();
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
		this.hasSuperUserEdit = hasSuperUserEdit;
		this.addDate = addDate;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.user = user;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.qtyModuleOpt = qtyModuleOpt;
		this.manufacturerMappingValue = manufacturerMappingValue;
		this.modelMappingValue = modelMappingValue;
		this.type = type;
	}
	
}
