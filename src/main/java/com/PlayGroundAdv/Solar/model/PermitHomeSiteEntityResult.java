package com.PlayGroundAdv.Solar.model;


public class PermitHomeSiteEntityResult {

	private String UtilityCompanyName;
	private String formattedAddress;
	private String siteAddress;
	private String addressLine2;
	private String city;
	private String state;
	private Float latitude;
	private Float longitude;
	private String postalCode;
	private String secondaryAddress;
	private String secondaryAddressLine2;
	private String secondaryCity;
	private String secondaryState;
	private String secondaryPostalCode;
	private String homePhone;
	private String cellPhone;
	private String otherPhone;
	private String emailPhone;
	private String propertyAPN;
	private String footageStructure;
	private String residenceBindingCategory;
	private String constructionType;
	private String roofRafter;
	private String buildingRisk;
	private String buildingOccupancy;
	private String homeOwnName;
	private String textOtherConst;
	// for formating other
	private String textOtherExpo;
	private String textOtherRoof;
	private String textOtherBuildOccup;
	private String textOtherBuild;
	private Boolean sameMailing;
	// CR_003
	private String serviceVoltage;
	private String serviceVoltageOther;
	private Boolean ifServiceVoltage;
	private String ridgeBeamDepthAtArrays;
	private String maxHorizontalSpanAtArrays;
	private String maxHorizontalSpanAtArraysHS;
	private String maxHorizontalSpanAtArraysInches;
	private String maxHorizontalSpanAtArraysHSInches;
	private String buildingRiskOther;
	private String stanchionMaxSpacing;
	private String stanchionMaxSpacingOther;
	private String ridgeBeamDepthAtArraysOther;
	private String UtilityCompanyNameOther;
	private String cityOther;
	private String projectJurisdiction;
	private String projectJurisOther;
	private String secondaryCityOther;
	private String secroofRafterOther;
	private String roofRafterOther;
	private String meterNumber;
	//A.B 03-17-2022 CR-798
	private String esiidNumber;

	public PermitHomeSiteEntityResult() {
		super();
	}

	public PermitHomeSiteEntityResult(String utilityCompanyName, String formattedAddress, String siteAddress, String addressLine2, String city,
			String state, Float latitude, Float longitude, String postalCode, String secondaryAddress, String secondaryAddressLine2,
			String secondaryCity, String secondaryState, String secondaryPostalCode, String homePhone, String cellPhone,
			String otherPhone, String emailPhone, String propertyAPN, String footageStructure,
			String residenceBindingCategory, String constructionType, String roofRafter, String buildingRisk,
			String buildingOccupancy, String homeOwnName, String textOtherConst, String textOtherExpo,
			String textOtherBuildOccup, String textOtherBuild, Boolean sameMailing, String serviceVoltage,
			String serviceVoltageOther, Boolean ifServiceVoltage, String ridgeBeamDepthAtArrays,
			String maxHorizontalSpanAtArrays, String maxHorizontalSpanAtArraysHS,
			String maxHorizontalSpanAtArraysInches, String maxHorizontalSpanAtArraysHSInches, String buildingRiskOther,
			String stanchionMaxSpacing, String stanchionMaxSpacingOther, String ridgeBeamDepthAtArraysOther,
			String UtilityCompanyNameOther, String cityOther, String projectJurisdiction, String projectJurisOther,
			String secondaryCityOther, String secroofRafterOther, String roofRafterOther, String meterNumber, String esiidNumber) {
		super();
		UtilityCompanyName = utilityCompanyName;
		this.formattedAddress = formattedAddress;
		this.siteAddress = siteAddress;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
		this.postalCode = postalCode;
		this.secondaryAddress = secondaryAddress;
		this.secondaryAddressLine2 = secondaryAddressLine2;
		this.secondaryCity = secondaryCity;
		this.secondaryState = secondaryState;
		this.secondaryPostalCode = secondaryPostalCode;
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
		this.otherPhone = otherPhone;
		this.emailPhone = emailPhone;
		this.propertyAPN = propertyAPN;
		this.footageStructure = footageStructure;
		this.constructionType = constructionType;
		this.buildingRisk = buildingRisk;
		this.buildingOccupancy = buildingOccupancy;
		this.setHomeOwnName(homeOwnName);
		this.textOtherConst = textOtherConst;
		this.textOtherExpo = textOtherExpo;
		// setTextOtherExpo(residenceBindingCategory);
		setResidenceBindingCategory(residenceBindingCategory);
		setTextOtherRoof(roofRafter);
		setRoofRafter(roofRafter);
		this.textOtherBuildOccup = textOtherBuildOccup;
		this.textOtherBuild = textOtherBuild;
		this.sameMailing = sameMailing;
		this.serviceVoltage = serviceVoltage;
		this.serviceVoltageOther = serviceVoltageOther;
		this.ifServiceVoltage = ifServiceVoltage;
		this.ridgeBeamDepthAtArrays = ridgeBeamDepthAtArrays;
		this.maxHorizontalSpanAtArrays = maxHorizontalSpanAtArrays;
		this.maxHorizontalSpanAtArraysHS = maxHorizontalSpanAtArraysHS;
		this.maxHorizontalSpanAtArraysInches = maxHorizontalSpanAtArraysInches;
		this.maxHorizontalSpanAtArraysHSInches = maxHorizontalSpanAtArraysHSInches;
		this.buildingRiskOther = buildingRiskOther;
		this.stanchionMaxSpacing = stanchionMaxSpacing;
		this.stanchionMaxSpacingOther = stanchionMaxSpacingOther;
		this.ridgeBeamDepthAtArraysOther = ridgeBeamDepthAtArraysOther;
		this.UtilityCompanyNameOther = UtilityCompanyNameOther;
		this.cityOther = cityOther;
		this.projectJurisdiction = projectJurisdiction;
		this.projectJurisOther = projectJurisOther;
		this.secondaryCityOther = secondaryCityOther;
		this.secroofRafterOther = secroofRafterOther;
		this.roofRafterOther = roofRafterOther;
		this.meterNumber = meterNumber;
		this.esiidNumber = esiidNumber;
		
	}

	public String getUtilityCompanyName() {
		return UtilityCompanyName;
	}

	public void setUtilityCompanyName(String utilityCompanyName) {
		UtilityCompanyName = utilityCompanyName;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getSecondaryAddress() {
		return secondaryAddress;
	}

	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}

	public String getSecondaryAddressLine2() {
		return secondaryAddressLine2;
	}

	public void setSecondaryAddressLine2(String secondaryAddressLine2) {
		this.secondaryAddressLine2 = secondaryAddressLine2;
	}

	public String getSecondaryCity() {
		return secondaryCity;
	}

	public void setSecondaryCity(String secondaryCity) {
		this.secondaryCity = secondaryCity;
	}

	public String getSecondaryState() {
		return secondaryState;
	}

	public void setSecondaryState(String secondaryState) {
		this.secondaryState = secondaryState;
	}

	public String getSecondaryPostalCode() {
		return secondaryPostalCode;
	}

	public void setSecondaryPostalCode(String secondaryPostalCode) {
		this.secondaryPostalCode = secondaryPostalCode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getOtherPhone() {
		return otherPhone;
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

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getEmailPhone() {
		return emailPhone;
	}

	public void setEmailPhone(String emailPhone) {
		this.emailPhone = emailPhone;
	}

	public String getPropertyAPN() {
		return propertyAPN;
	}

	public void setPropertyAPN(String propertyAPN) {
		this.propertyAPN = propertyAPN;
	}

	public String getFootageStructure() {
		return footageStructure;
	}

	public void setFootageStructure(String footageStructure) {
		this.footageStructure = footageStructure;
	}

	public String getResidenceBindingCategory() {
		return residenceBindingCategory;
	}

	public void setResidenceBindingCategory(String residenceBindingCategory) {
		if (residenceBindingCategory != null) {
			if (!(residenceBindingCategory.equals("Exposure B") || residenceBindingCategory.equals("Exposure C")
					|| residenceBindingCategory.equals("exposure D") || residenceBindingCategory.equals("Exposure A")
					|| residenceBindingCategory.equals("Not Factored"))) {
				this.residenceBindingCategory = "Other";
			} else {
				this.residenceBindingCategory = residenceBindingCategory;
			}
		}

	}

	public String getConstructionType() {
		return constructionType;
	}

	public void setConstructionType(String constructionType) {
		this.constructionType = constructionType;
	}

	public String getRoofRafter() {
		return roofRafter;
	}

	public void setRoofRafter(String roofRafter) {
		if (roofRafter != null) {
			if (!(roofRafter.equals("Rafter - Strut to Walls Below") || roofRafter.equals("Rafter - Cathedral Ceiling")
					|| roofRafter.equals("Rafter-Simple Attic") || roofRafter.equals("Pre-Eng Roof Trusses"))) {
				this.roofRafter = "OtherRoof";
			} else {
				this.roofRafter = roofRafter;
			}
		}

	}

	public String getBuildingRisk() {
		return buildingRisk;
	}

	public void setBuildingRisk(String buildingRisk) {
		this.buildingRisk = buildingRisk;
	}

	public String getBuildingOccupancy() {
		return buildingOccupancy;
	}

	public void setBuildingOccupancy(String buildingOccupancy) {
		this.buildingOccupancy = buildingOccupancy;
	}

	public String getHomeOwnName() {
		return homeOwnName;
	}

	public void setHomeOwnName(String homeOwnName) {
		this.homeOwnName = homeOwnName;
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
	 * @return the textOtherRoof
	 */
	public String getTextOtherRoof() {
		return textOtherRoof;
	}

	/**
	 * @param textOtherRoof the textOtherRoof to set
	 */
	public void setTextOtherRoof(String textOtherRoof) {
		if (textOtherRoof != null) {
			if (!(textOtherRoof.equals("Rafter - Strut to Walls Below")
					|| textOtherRoof.equals("Rafter - Cathedral Ceiling") || textOtherRoof.equals("Rafter-Simple Attic")
					|| textOtherRoof.equals("Pre-Eng Roof Trusses"))) {
				this.textOtherRoof = textOtherRoof;
			}
		}

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

	public String getTextOtherConst() {
		return textOtherConst;
	}

	public void setTextOtherConst(String textOtherConst) {
		this.textOtherConst = textOtherConst;
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

	public String getMaxHorizontalSpanAtArraysHS() {
		return maxHorizontalSpanAtArraysHS;
	}

	public void setMaxHorizontalSpanAtArraysHS(String maxHorizontalSpanAtArraysHS) {
		this.maxHorizontalSpanAtArraysHS = maxHorizontalSpanAtArraysHS;
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
		UtilityCompanyNameOther = utilityCompanyNameOther;
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
