package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

/*
 * @author Arij
 */
@Entity
@Table(name = "ACCombinerSLC", indexes = { @Index(name = "ACCOMBO_INDX_0", columnList = "MANUFACTURER,MODEL") })
//@ApiModel(description = "The library of AC Combiners.")
public class ACCombinerSLC {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "acdisc_sequence", sequenceName = "acdisc_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acdisc_sequence")
	private Long id;

//	@ApiModelProperty(notes = "The Component's Manifacturer")
	@Column(name = "MANUFACTURER")
	private String manufacturer;

//	@ApiModelProperty(notes = "The Component's Model")
	@Column(name = "MODEL")
	private String model;

	@Column(name = "COMBINER_DEVICE_TYPE")
	private String combinerDeviceType;

	@Column(name = "RATED_OPERATIONAL_VOLTAGE")
	private String ratedOperationalVoltage;

	@Column(name = "RATED_CURRENT")
	private String ratedCurrent;

	@Column(name = "NUMBER_OF_POLES")
	private String numberOfPoles;

	@Column(name = "NUMBER_OF_SPACES")
	private String numberOfSpaces;

	@Column(name = "OTHER_NUMBER_OF_SPACES")
	private String otherNumberOfSpaces;

	@Column(name = "NEMA_RATING")
	private String nemaRating;

	@Column(name = "MAX_INPUT")
	private String maxInput;

	@Column(name = "QTY_OF_FUSE")
	private String qtyOfFuse;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "DROPDOWN_OPTION")
	private String dropdownOption;

	@Column(name = "LAST_UPDATE")
	private String lastUpdate;
	
//	@ApiModelProperty(notes = "Initiated to false when component is created, takes true when the componenet is deleted")
	@Column(name = "IS_DELETED")
	private Boolean isDeleted;

	@Column(name = "HAS_SUPER_USER_EDIT")
	private Boolean hasSuperUserEdit;

//	@ApiModelProperty(notes = "The Date record when the component was added")
	@Column(name = "ADD_Date")
	private Date addDate;

//	@ApiModelProperty(notes = "The user who added the componenet")
	@JoinColumn(name = "ID_OWNER")
	@ManyToOne
	private AuthentificationEntity idOwner;

	@Column(name = "ERONEOUS_CONTENT")
	private String eroneousContent;

	@Column(name = "ERONEOUS_CONTENT_OTHER")
	private String eroneousContentOther;

	@Column(name = "ERONEOUS_DESCRIPTION")
	private String eroneousDescription;

	@Column(name = "HAS_CORRECTION_REQUEST")
	private Boolean hasCorrectionRequest;

	@Column(name = "SPEC_SHEET_REF")
	private String specSheetRef;

	@Column(name = "MANUFACTURER_MAPPING_VALUE")
	private String manufacturerMappingValue;

	@Column(name = "MODEL_MAPPING_VALUE")
	private String modelMappingValue;
	
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

	@Column(name="CATEGORY")
	private String category;
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public AuthentificationEntity getFirstUpdater() {
		return firstUpdater;
	}

	public void setFirstUpdater(AuthentificationEntity firstUpdater) {
		this.firstUpdater = firstUpdater;
	}

	public AuthentificationEntity getSecondUpdater() {
		return secondUpdater;
	}

	public void setSecondUpdater(AuthentificationEntity secondUpdater) {
		this.secondUpdater = secondUpdater;
	}

	public AuthentificationEntity getThirdUpdater() {
		return thirdUpdater;
	}

	public void setThirdUpdater(AuthentificationEntity thirdUpdater) {
		this.thirdUpdater = thirdUpdater;
	}

	public AuthentificationEntity getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(AuthentificationEntity verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Date getDateVerification() {
		return dateVerification;
	}

	public void setDateVerification(Date dateVerification) {
		this.dateVerification = dateVerification;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public String getOtherNumberOfSpaces() {
		return otherNumberOfSpaces;
	}

	public void setOtherNumberOfSpaces(String otherNumberOfSpaces) {
		this.otherNumberOfSpaces = otherNumberOfSpaces;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public AuthentificationEntity getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(AuthentificationEntity idOwner) {
		this.idOwner = idOwner;
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

	public String getCombinerDeviceType() {
		return combinerDeviceType;
	}

	public void setCombinerDeviceType(String combinerDeviceType) {
		this.combinerDeviceType = combinerDeviceType;
	}

	public String getNumberOfSpaces() {
		return numberOfSpaces;
	}

	public void setNumberOfSpaces(String numberOfSpaces) {
		this.numberOfSpaces = numberOfSpaces;
	}

	public String getRatedOperationalVoltage() {
		return ratedOperationalVoltage;
	}

	public void setRatedOperationalVoltage(String ratedOperationalVoltage) {
		this.ratedOperationalVoltage = ratedOperationalVoltage;
	}

	public String getRatedCurrent() {
		return ratedCurrent;
	}

	public void setRatedCurrent(String ratedCurrent) {
		this.ratedCurrent = ratedCurrent;
	}

	public String getNumberOfPoles() {
		return numberOfPoles;
	}

	public void setNumberOfPoles(String numberOfPoles) {
		this.numberOfPoles = numberOfPoles;
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

	public String getQtyOfFuse() {
		return qtyOfFuse;
	}

	public void setQtyOfFuse(String qtyOfFuse) {
		this.qtyOfFuse = qtyOfFuse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDropdownOption() {
		return dropdownOption;
	}

	public void setDropdownOption(String dropdownOption) {
		this.dropdownOption = dropdownOption;
	}

	public Boolean getHasSuperUserEdit() {
		return hasSuperUserEdit;
	}

	public void setHasSuperUserEdit(Boolean hasSuperUserEdit) {
		this.hasSuperUserEdit = hasSuperUserEdit;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
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

	public Boolean getHasCorrectionRequest() {
		return hasCorrectionRequest;
	}

	public void setHasCorrectionRequest(Boolean hasCorrectionRequest) {
		this.hasCorrectionRequest = hasCorrectionRequest;
	}

	public String getSpecSheetRef() {
		return specSheetRef;
	}

	public void setSpecSheetRef(String specSheetRef) {
		this.specSheetRef = specSheetRef;
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

}
