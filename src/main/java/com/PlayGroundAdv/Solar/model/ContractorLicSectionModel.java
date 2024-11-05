package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ContractorLicSectionModel {

	public boolean isInUSorTerritories;
	public String contractorLicenceState;
	public String licenseNumber;
	public String LicTypeCode[];
	public String firstLicTypeCodeOther;
	public String secondLicTypeCodeOther;
	public String thirdLicTypeCodeOther;
	public String LicType[];
	public String LicTypeOther;
	public String licenseExpiration;
	public String qualifyingIndividual;
	public String qualifyingIndividualOther;
	public String additionalEmail1;
	public String additionalEmail2;
	public String additionalEmail3;
	public String additionalEmail4;

}
