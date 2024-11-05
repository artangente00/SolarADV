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

/*
 * @author Arij
 */
@Entity
//@Table(name = "ACDisconnect")
@Table(name = "ACDisconnect" , indexes = {
	       @Index(name = "ACDISCO_INDX_0",  columnList="MANUFACTURER,MODEL,DISCONNECT_DEVICE_TYPE,RATED_OPERATIONAL_VOLTAGE")})

public class ACDisconnect {
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="acdisc_sequence", sequenceName = "acdisc_sequence", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="acdisc_sequence")  
	private Long id;
	
	@Column(name="MANUFACTURER")
	private String manufacturer;
	
	@Column(name="MODEL")
	private String model;
	
	@Column(name="DISCONNECT_DEVICE_TYPE")
	private String disconnectDeviceType;
	
	@Column(name="RATED_OPERATIONAL_VOLTAGE")
	private String ratedOperationalVoltage;
	
	@Column(name="RATED_CURRENT")
	private String ratedCurrent;
	
	@Column(name="NUMBER_OF_POLES")
	private String numberOfPoles;
	
	@Column(name="NEMA_RATING")
	private String nemaRating;
	
	@Column(name="MAX_INPUT")
	private String maxInput;
	
	@Column(name="QTY_OF_FUSE")
	private String qtyOfFuse;
	
	@Column(name="TYPE")
	private String type;

	@Column(name="DROPDOWN_OPTION")
	private String dropdownOption;
	
	@Column(name="LAST_UPDATE")
	private String lastUpdate;
	
	@Column(name="IS_DELETED")
	private Boolean isDeleted;
	
	@Column(name="HAS_SUPER_USER_EDIT")
	private Boolean hasSuperUserEdit;
	
	@Column(name="ADD_Date")
	private Date addDate;
	
	@JoinColumn(name = "ID_OWNER")
	@ManyToOne
	private AuthentificationEntity idOwner;
	
	@Column(name="ERONEOUS_CONTENT")
	private String eroneousContent;
	
	@Column(name="ERONEOUS_CONTENT_OTHER")
	private String eroneousContentOther;
	
	@Column(name="ERONEOUS_DESCRIPTION")
	private String eroneousDescription;
	
	@Column(name="HAS_CORRECTION_REQUEST")
	private Boolean hasCorrectionRequest;
	
	@Column(name="MANUFACTURER_MAPPING_VALUE")
	private String manufacturerMappingValue;
	
	@Column(name="MODEL_MAPPING_VALUE")
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

	public String getDisconnectDeviceType() {
		return disconnectDeviceType;
	}

	public void setDisconnectDeviceType(String disconnectDeviceType) {
		this.disconnectDeviceType = disconnectDeviceType;
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
