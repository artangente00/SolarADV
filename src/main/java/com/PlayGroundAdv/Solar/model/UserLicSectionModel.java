package com.PlayGroundAdv.Solar.model;

public class UserLicSectionModel {

	public boolean isInUSorTerritories[];
	public String contractorLicenceState[];
	public String licenseNumber[];
	public String LicTypeCode[][];
	public String firstLicTypeCodeOther[];
	public String secondLicTypeCodeOther[];
	public String thirdLicTypeCodeOther[];
	public String LicType[][];
	public String LicTypeOther[];
	public String licenseExpiration[];
	public String qualifyingIndividual[];
	public String qualifyingIndividualOther[];
	public Long userId;
	public Integer sectionLength;
	public String additionalEmail1[];
	public String additionalEmail2[];
	public String additionalEmail3[];
	public String additionalEmail4[];

	public UserLicSectionModel() {
		super();
	}

	public UserLicSectionModel(boolean[] isInUSorTerritories, String[] contractorLicenceState, String[] licenseNumber,
			String[][] licTypeCode, String[] firstLicTypeCodeOther, String[] secondLicTypeCodeOther,
			String[] thirdLicTypeCodeOther, String[][] licType, String[] licTypeOther, String[] licenseExpiration,
			String[] qualifyingIndividual, String[] qualifyingIndividualOther, Long userId, Integer sectionLength) {
		super();
		this.isInUSorTerritories = isInUSorTerritories;
		this.contractorLicenceState = contractorLicenceState;
		this.licenseNumber = licenseNumber;
		LicTypeCode = licTypeCode;
		this.firstLicTypeCodeOther = firstLicTypeCodeOther;
		this.secondLicTypeCodeOther = secondLicTypeCodeOther;
		this.thirdLicTypeCodeOther = thirdLicTypeCodeOther;
		LicType = licType;
		LicTypeOther = licTypeOther;
		this.licenseExpiration = licenseExpiration;
		this.qualifyingIndividual = qualifyingIndividual;
		this.qualifyingIndividualOther = qualifyingIndividualOther;
		this.userId = userId;
		this.sectionLength = sectionLength;
	}

	public boolean[] getIsInUSorTerritories() {
		return isInUSorTerritories;
	}

	public void setIsInUSorTerritories(boolean[] isInUSorTerritories) {
		this.isInUSorTerritories = isInUSorTerritories;
	}

	public String[] getContractorLicenceState() {
		return contractorLicenceState;
	}

	public void setContractorLicenceState(String[] contractorLicenceState) {
		this.contractorLicenceState = contractorLicenceState;
	}

	public String[] getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String[] licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String[][] getLicTypeCode() {
		return LicTypeCode;
	}

	public void setLicTypeCode(String[][] licTypeCode) {
		LicTypeCode = licTypeCode;
	}

	public String[] getFirstLicTypeCodeOther() {
		return firstLicTypeCodeOther;
	}

	public void setFirstLicTypeCodeOther(String[] firstLicTypeCodeOther) {
		this.firstLicTypeCodeOther = firstLicTypeCodeOther;
	}

	public String[] getSecondLicTypeCodeOther() {
		return secondLicTypeCodeOther;
	}

	public void setSecondLicTypeCodeOther(String[] secondLicTypeCodeOther) {
		this.secondLicTypeCodeOther = secondLicTypeCodeOther;
	}

	public String[] getThirdLicTypeCodeOther() {
		return thirdLicTypeCodeOther;
	}

	public void setThirdLicTypeCodeOther(String[] thirdLicTypeCodeOther) {
		this.thirdLicTypeCodeOther = thirdLicTypeCodeOther;
	}

	public String[][] getLicType() {
		return LicType;
	}

	public void setLicType(String[][] licType) {
		LicType = licType;
	}

	public String[] getLicTypeOther() {
		return LicTypeOther;
	}

	public void setLicTypeOther(String[] licTypeOther) {
		LicTypeOther = licTypeOther;
	}

	public String[] getLicenseExpiration() {
		return licenseExpiration;
	}

	public void setLicenseExpiration(String[] licenseExpiration) {
		this.licenseExpiration = licenseExpiration;
	}

	public String[] getQualifyingIndividual() {
		return qualifyingIndividual;
	}

	public void setQualifyingIndividual(String[] qualifyingIndividual) {
		this.qualifyingIndividual = qualifyingIndividual;
	}

	public String[] getQualifyingIndividualOther() {
		return qualifyingIndividualOther;
	}

	public void setQualifyingIndividualOther(String[] qualifyingIndividualOther) {
		this.qualifyingIndividualOther = qualifyingIndividualOther;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getSectionLength() {
		return sectionLength;
	}

	public void setSectionLength(Integer sectionLength) {
		this.sectionLength = sectionLength;
	}

	public String[] getAdditionalEmail1() {
		return additionalEmail1;
	}

	public void setAdditionalEmail1(String[] additionalEmail1) {
		this.additionalEmail1 = additionalEmail1;
	}

	public String[] getAdditionalEmail2() {
		return additionalEmail2;
	}

	public void setAdditionalEmail2(String[] additionalEmail2) {
		this.additionalEmail2 = additionalEmail2;
	}

	public String[] getAdditionalEmail3() {
		return additionalEmail3;
	}

	public void setAdditionalEmail3(String[] additionalEmail3) {
		this.additionalEmail3 = additionalEmail3;
	}

	public String[] getAdditionalEmail4() {
		return additionalEmail4;
	}

	public void setAdditionalEmail4(String[] additionalEmail4) {
		this.additionalEmail4 = additionalEmail4;
	}

}
