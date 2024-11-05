package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchProject {

	private String projectJurisdiction;
	private String city;
	private String state;
	private String typeOfSystem;
	private String typeOfSystemOther;
	private Long roofMaterial;
	private String utilityCompany;
	private String utilityCompanyOther;
	private String roofRafter;
	private String roofRafterOther;
	private String inverterModel;
	private String railToRoof;
	private String railRacking;
	private String battery;
	private String tiltLeg;
	private String engineerType;
	private Long pvModule;
	private String engineerBy;
	private String ats;
	private String typeGridTied;
	private String poc;
	private String creationDate;
	private int dateRange;
	private int nbPage;
	private int sizePage;

	private String sortField;
	private String sortOrder;

}
