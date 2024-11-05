package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PermitHomeSiteInfoEntity")
public class PermitHomeSiteInfoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="hibernate_sequence6", sequenceName = "hibernate_sequence6", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence6")
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@Column(name="Utility_Company_Name")
	private String utilityCompanyName;
	
	@Column(name="FORMATTED_ADDRESS")
	private String formattedAddress;
	
	@Column(name="Site_Address")
	private String siteAddress;
	
	@Column(name="ADDRESS_LINE_2")
	private String addressLine2;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="latitude")
	private Float latitude;
	
	@Column(name="LONGITUDE")
	private Float longitude;
	
	@Column(name="POSTAL_CODE")
	private String postalCode;
	
	@Column(name="SECONDARY_ADDRESS")
	private String secondaryAddress;
	
	@Column(name="SECONDARY_ADDRESS_LINE_2")
	private String secondaryAddressLine2;

	@Column(name="SECONDARY_CITY")
	private String secondaryCity;
	
	@Column(name="SECONDARY_STATE")
	private String secondaryState;
	
	@Column(name="SECONDARY_POSTAL_CODE")
	private String secondaryPostalCode;
	
	@Column(name="HOME_PHONE")
	private String homePhone;
	
	@Column(name="CELL_PHONE")
	private String cellPhone;
	
	@Column(name="OTHER_PHONE")
	private String otherPhone;
	
	@Column(name="EMAIL_PHONE")
	private String emailPhone;
	
	@Column(name="PROPERTY_APN")
	private String propertyAPN;
	
	@Column(name="FOOTAGE_STRUCTURE")
	private String footageStructure;
	
	@Column(name="RESIDENCE_BINDING_CATEGORY")
	private String residenceBindingCategory;
	
	@Column(name="CONSTRUCTION_TYPE")
	private String constructionType;
	
	@Column(name="ROOF_RAFTER")
	private String roofRafter;
	
	@Column(name="BUILDING_RISK")
	private String buildingRisk;

	@Column(name="BUILDING_OCCUPANCY")
	private String buildingOccupancy;
	
	@Column(name="TEXT_OTHER_CONST")
	private String textOtherConst;
	
	
	@Column(name="TEXT_OTHER_BUILD_OCCUP")
	private String textOtherBuildOccup;
	
	
	@Column(name="TEXT_OTHER_BUIL")
	private String textOtherBuild;

	@Column(name="SAME_Mailing")
	private Boolean sameMailing;
	
	//CR_003
	
	@Column(name="SERVICE_VOLTAGE")
	private String serviceVoltage;
	
	@Column(name="SERVICE_VOLTAGE_OTHER")
	private String serviceVoltageOther;
	
	@Column(name="IF_SERVICE_VOLTAGE")
	private Boolean ifServiceVoltage;
	
	@Column(name="RIDGE_BEAM_DEPTH_AT_ARRAYS")
	private String ridgeBeamDepthAtArrays;
	
	@Column(name="MAX_HORIZONTAL_SPAN_AT_ARRAYS")
	private String maxHorizontalSpanAtArrays;
	
	@Column(name="MAX_HORIZONTAL_SPAN_AT_ARRAYS_INCHES")
	private String maxHorizontalSpanAtArraysInches;
	
	@Column(name="MAX_HORIZONTAL_SPAN_AT_ARRAYS_HS")
	private String maxHorizontalSpanAtArraysHS;
	
	@Column(name="MAX_HORIZONTAL_SPAN_AT_ARRAYS_HS_INCHES")
	private String maxHorizontalSpanAtArraysHSInches;
	
	@Column(name="BUILDING_RISK_OTHER")
	private String buildingRiskOther;
	
	@Column(name="TEXT_OTHER_EXPO")
	private String textOtherExpo;
	
	@Column(name="STANCHION_MAX_SPACING")
	private String stanchionMaxSpacing;
	
	@Column(name="STANCHION_MAX_SPACING_OTHER")
	private String stanchionMaxSpacingOther;
	
	@Column(name="RIDGE_BEAM_DEPTH_AT_ARRAYS_OTHER")
	private String ridgeBeamDepthAtArraysOther;
	
	@Column(name="UTILITY_COMPANY_NAME_OTHER")
	private String UtilityCompanyNameOther;
	
	@Column(name="CITYOTHER")
	private String cityOther;
	
	@Column(name="PROJECTJURISDICTION")
	private String projectJurisdiction;
	
	@Column(name="PROJECTJURISOTHER")
	private String projectJurisOther;
	
	@Column(name="SECONDARYCITYOTHER")
	private String secondaryCityOther;
	
	@Column(name="ROOF_RAFTER_OTHER")
	private String secroofRafterOther;
	
	@Column(name="ROOF_RAF_OTHER")
	private String roofRafterOther;
	
	@Column(name="METER_NUMBER")
	private String meterNumber;
	
	@Column //A.B 03-17-2022 CR-798
	private String esiidNumber;
	
	public String getMaxHorizontalSpanAtArraysHS() {
		return maxHorizontalSpanAtArraysHS;
	}

	public void setMaxHorizontalSpanAtArraysHS(String maxHorizontalSpanAtArraysHS) {
		this.maxHorizontalSpanAtArraysHS = maxHorizontalSpanAtArraysHS;
	}

	public String getMaxHorizontalSpanAtArraysInches() {
		return maxHorizontalSpanAtArraysInches;
	}

	public void setMaxHorizontalSpanAtArraysInches(String maxHorizontalSpanAtArraysInches) {
		this.maxHorizontalSpanAtArraysInches = maxHorizontalSpanAtArraysInches;
	}

	public String getMaxHorizontalSpanAtArraysHSInches() {
		return maxHorizontalSpanAtArraysHSInches;
	}

	public void setMaxHorizontalSpanAtArraysHSInches(String maxHorizontalSpanAtArraysHSInches) {
		this.maxHorizontalSpanAtArraysHSInches = maxHorizontalSpanAtArraysHSInches;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRidgeBeamDepthAtArrays() {
		return ridgeBeamDepthAtArrays;
	}

	public void setRidgeBeamDepthAtArrays(String ridgeBeamDepthAtArrays) {
		this.ridgeBeamDepthAtArrays = ridgeBeamDepthAtArrays;
	}

	public String getMaxHorizontalSpanAtArrays() {
		return maxHorizontalSpanAtArrays;
	}

	public void setMaxHorizontalSpanAtArrays(String maxHorizontalSpanAtArrays) {
		this.maxHorizontalSpanAtArrays = maxHorizontalSpanAtArrays;
	}

	/**
	 * @return the permitEntity
	 */
	public PermitEntity getPermitEntity() {
		return permitEntity;
	}

	/**
	 * @param permitEntity the permitEntity to set
	 */
	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}

	/**
	 * @return the utilityCompanyName
	 */
	public String getUtilityCompanyName() {
		return utilityCompanyName;
	}

	/**
	 * @param utilityCompanyName the utilityCompanyName to set
	 */
	public void setUtilityCompanyName(String utilityCompanyName) {
		this.utilityCompanyName = utilityCompanyName;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	/**
	 * @return the siteAddress
	 */
	public String getSiteAddress() {
		return siteAddress;
	}

	/**
	 * @param siteAddress the siteAddress to set
	 */
	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the secondaryAddress
	 */
	public String getSecondaryAddress() {
		return secondaryAddress;
	}

	/**
	 * @param secondaryAddress the secondaryAddress to set
	 */
	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}

	/**
	 * @return the secondaryAddressLine2
	 */
	public String getSecondaryAddressLine2() {
		return secondaryAddressLine2;
	}

	/**
	 * @param secondaryAddressLine2 the secondaryAddressLine2 to set
	 */
	public void setSecondaryAddressLine2(String secondaryAddressLine2) {
		this.secondaryAddressLine2 = secondaryAddressLine2;
	}

	/**
	 * @return the secondaryCity
	 */
	public String getSecondaryCity() {
		return secondaryCity;
	}

	/**
	 * @param secondaryCity the secondaryCity to set
	 */
	public void setSecondaryCity(String secondaryCity) {
		this.secondaryCity = secondaryCity;
	}

	/**
	 * @return the secondaryState
	 */
	public String getSecondaryState() {
		return secondaryState;
	}

	/**
	 * @param secondaryState the secondaryState to set
	 */
	public void setSecondaryState(String secondaryState) {
		this.secondaryState = secondaryState;
	}

	/**
	 * @return the secondaryPostalCode
	 */
	public String getSecondaryPostalCode() {
		return secondaryPostalCode;
	}

	/**
	 * @param secondaryPostalCode the secondaryPostalCode to set
	 */
	public void setSecondaryPostalCode(String secondaryPostalCode) {
		this.secondaryPostalCode = secondaryPostalCode;
	}

	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * @param homePhone the homePhone to set
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * @param cellPhone the cellPhone to set
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
	 * @return the otherPhone
	 */
	public String getOtherPhone() {
		return otherPhone;
	}

	/**
	 * @param otherPhone the otherPhone to set
	 */
	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	/**
	 * @return the emailPhone
	 */
	public String getEmailPhone() {
		return emailPhone;
	}

	/**
	 * @param emailPhone the emailPhone to set
	 */
	public void setEmailPhone(String emailPhone) {
		this.emailPhone = emailPhone;
	}

	/**
	 * @return the propertyAPN
	 */
	public String getPropertyAPN() {
		return propertyAPN;
	}

	/**
	 * @param propertyAPN the propertyAPN to set
	 */
	public void setPropertyAPN(String propertyAPN) {
		this.propertyAPN = propertyAPN;
	}

	/**
	 * @return the footageStructure
	 */
	public String getFootageStructure() {
		return footageStructure;
	}

	/**
	 * @param footageStructure the footageStructure to set
	 */
	public void setFootageStructure(String footageStructure) {
		this.footageStructure = footageStructure;
	}

	/**
	 * @return the residenceBindingCategory
	 */
	public String getResidenceBindingCategory() {
		return residenceBindingCategory;
	}

	/**
	 * @param residenceBindingCategory the residenceBindingCategory to set
	 */
	public void setResidenceBindingCategory(String residenceBindingCategory) {
		this.residenceBindingCategory = residenceBindingCategory;
	}

	/**
	 * @return the constructionType
	 */
	public String getConstructionType() {
		return constructionType;
	}

	/**
	 * @param constructionType the constructionType to set
	 */
	public void setConstructionType(String constructionType) {
		this.constructionType = constructionType;
	}

	/**
	 * @return the roofRafter
	 */
	public String getRoofRafter() {
		return roofRafter;
	}

	/**
	 * @param roofRafter the roofRafter to set
	 */
	public void setRoofRafter(String roofRafter) {
		this.roofRafter = roofRafter;
	}

	/**
	 * @return the buildingRisk
	 */
	public String getBuildingRisk() {
		return buildingRisk;
	}

	/**
	 * @param buildingRisk the buildingRisk to set
	 */
	public void setBuildingRisk(String buildingRisk) {
		this.buildingRisk = buildingRisk;
	}

	/**
	 * @return the buildingOccupancy
	 */
	public String getBuildingOccupancy() {
		return buildingOccupancy;
	}

	/**
	 * @param buildingOccupancy the buildingOccupancy to set
	 */
	public void setBuildingOccupancy(String buildingOccupancy) {
		this.buildingOccupancy = buildingOccupancy;
	}

	/**
	 * @return the textOtherConst
	 */
	public String getTextOtherConst() {
		return textOtherConst;
	}

	/**
	 * @param textOtherConst the textOtherConst to set
	 */
	public void setTextOtherConst(String textOtherConst) {
		this.textOtherConst = textOtherConst;
	}

	/**
	 * @return the textOtherBuildOccup
	 */
	public String getTextOtherBuildOccup() {
		return textOtherBuildOccup;
	}

	/**
	 * @param textOtherBuildOccup the textOtherBuildOccup to set
	 */
	public void setTextOtherBuildOccup(String textOtherBuildOccup) {
		this.textOtherBuildOccup = textOtherBuildOccup;
	}

	/**
	 * @return the textOtherBuild
	 */
	public String getTextOtherBuild() {
		return textOtherBuild;
	}

	/**
	 * @param textOtherBuild the textOtherBuild to set
	 */
	public void setTextOtherBuild(String textOtherBuild) {
		this.textOtherBuild = textOtherBuild;
	}

	/**
	 * @return the sameMailing
	 */
	public Boolean getSameMailing() {
		return sameMailing;
	}

	/**
	 * @param sameMailing the sameMailing to set
	 */
	public void setSameMailing(Boolean sameMailing) {
		this.sameMailing = sameMailing;
	}

	/**
	 * @return the serviceVoltage
	 */
	public String getServiceVoltage() {
		return serviceVoltage;
	}

	/**
	 * @return the serviceVoltageOther
	 */
	public String getServiceVoltageOther() {
		return serviceVoltageOther;
	}
	
	/**
	 * @return the ifServiceVoltage
	 */
	public Boolean getIfServiceVoltage() {
		return ifServiceVoltage;
	}

	/**
	 * @param serviceVoltage the serviceVoltage to set
	 */
	public void setServiceVoltage(String serviceVoltage) {
		this.serviceVoltage = serviceVoltage;
	}

	/**
	 * @param serviceVoltageOther the serviceVoltageOther to set
	 */
	public void setServiceVoltageOther(String serviceVoltageOther) {
		this.serviceVoltageOther = serviceVoltageOther;
	}

	/**
	 * @param ifServiceVoltage the ifServiceVoltage to set
	 */
	public void setIfServiceVoltage(Boolean ifServiceVoltage) {
		this.ifServiceVoltage = ifServiceVoltage;
	}

	/**
	 * @return the buildingRiskOther
	 */
	public String getBuildingRiskOther() {
		return buildingRiskOther;
	}

	/**
	 * @param buildingRiskOther the buildingRiskOther to set
	 */
	public void setBuildingRiskOther(String buildingRiskOther) {
		this.buildingRiskOther = buildingRiskOther;
	}

	/**
	 * @return the textOtherExpo
	 */
	public String getTextOtherExpo() {
		return textOtherExpo;
	}

	/**
	 * @param textOtherExpo the textOtherExpo to set
	 */
	public void setTextOtherExpo(String textOtherExpo) {
		this.textOtherExpo = textOtherExpo;
	}

	/**
	 * @return the stanchionMaxSpacing
	 */
	public String getStanchionMaxSpacing() {
		return stanchionMaxSpacing;
	}

	/**
	 * @param stanchionMaxSpacing the stanchionMaxSpacing to set
	 */
	public void setStanchionMaxSpacing(String stanchionMaxSpacing) {
		this.stanchionMaxSpacing = stanchionMaxSpacing;
	}

	/**
	 * @return the stanchionMaxSpacingOther
	 */
	public String getStanchionMaxSpacingOther() {
		return stanchionMaxSpacingOther;
	}

	/**
	 * @param stanchionMaxSpacingOther the stanchionMaxSpacingOther to set
	 */
	public void setStanchionMaxSpacingOther(String stanchionMaxSpacingOther) {
		this.stanchionMaxSpacingOther = stanchionMaxSpacingOther;
	}

	/**
	 * @return the ridgeBeamDepthAtArraysOther
	 */
	public String getRidgeBeamDepthAtArraysOther() {
		return ridgeBeamDepthAtArraysOther;
	}

	/**
	 * @param ridgeBeamDepthAtArraysOther the ridgeBeamDepthAtArraysOther to set
	 */
	public void setRidgeBeamDepthAtArraysOther(String ridgeBeamDepthAtArraysOther) {
		this.ridgeBeamDepthAtArraysOther = ridgeBeamDepthAtArraysOther;
	}

	/**
	 * @return the utilityCompanyNameOther
	 */
	public String getUtilityCompanyNameOther() {
		return UtilityCompanyNameOther;
	}

	/**
	 * @param utilityCompanyNameOther the utilityCompanyNameOther to set
	 */
	public void setUtilityCompanyNameOther(String utilityCompanyNameOther) {
		this.UtilityCompanyNameOther = utilityCompanyNameOther;
	}

	public String getCityOther() {
		return cityOther;
	}

	public void setCityOther(String cityOther) {
		this.cityOther = cityOther;
	}

	public String getProjectJurisdiction() {
		return projectJurisdiction;
	}

	public void setProjectJurisdiction(String projectJurisdiction) {
		this.projectJurisdiction = projectJurisdiction;
	}

	public String getProjectJurisOther() {
		return projectJurisOther;
	}

	public void setProjectJurisOther(String projectJurisOther) {
		this.projectJurisOther = projectJurisOther;
	}

	public String getSecondaryCityOther() {
		return secondaryCityOther;
	}

	public void setSecondaryCityOther(String secondaryCityOther) {
		this.secondaryCityOther = secondaryCityOther;
	}

	public String getSecroofRafterOther() {
		return secroofRafterOther;
	}

	public void setSecroofRafterOther(String secroofRafterOther) {
		this.secroofRafterOther = secroofRafterOther;
	}

	public String getRoofRafterOther() {
		return roofRafterOther;
	}

	public void setRoofRafterOther(String roofRafterOther) {
		this.roofRafterOther = roofRafterOther;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getEsiidNumber() {
		return esiidNumber;
	}

	public void setEsiidNumber(String esiidNumber) {
		this.esiidNumber = esiidNumber;
	}
	
	
	
}
