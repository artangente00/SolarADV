package com.PlayGroundAdv.Solar.service.export_project;

import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.CityEntity;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.CitiesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ElectricalUtilityRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportHomeownerInfo {

	final CheckValueTypesService checkValueTypes;
	final ElectricalUtilityRepository electricalUtilityRepo;
	final CitiesRepository citiesRepo;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;
	private static final String OTHER = "Other";

	public ExportHomeownerInfo(CheckValueTypesService checkValueTypes,
			ElectricalUtilityRepository electricalUtilityRepo, CitiesRepository citiesRepo,
			RoofMaterialTypeRepository roofMaterialTypeRepo) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.electricalUtilityRepo = electricalUtilityRepo;
		this.citiesRepo = citiesRepo;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
	}

	public LinkedHashMap<String, String> homeOwnerMap(GetPermitByIdResult permit) {
		// 05-24-2019 : M.A : Junit correction

		LinkedHashMap<String, String> homeOwner = new LinkedHashMap<>();

		try {
			homeOwner.put("Project Reference ID", permit.getPermitEntity().getId().toString());
			homeOwner.put("Date", checkValueTypes.convert(permit.getPermitEntity().getCreationPermitDate().toString()));

			if (permit != null && permit.getPermitEntity() != null) {
				if (checkValueTypes.NotEquals(permit.getPermitEntity().getHomeOwnLastName(), "")) {
					homeOwner.put("Homeowner Name or Project Contact",
							checkValueTypes.convert(permit.getPermitEntity().getHomeOwnLastName() + ", "
									+ checkValueTypes.convert(permit.getPermitEntity().getHomeOwnName())));
				} else {
					homeOwner.put("Homeowner Name or Project Contact",
							checkValueTypes.convert(permit.getPermitEntity().getHomeOwnName()));
				}
			}

			homeOwner.put("Project Name", checkValueTypes.convert(permit.getPermitEntity().getProjectName()));
			homeOwner.put("Address",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getFormattedAddress()));
			homeOwner.put("Site Address",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getSiteAddress()));
			homeOwner.put("Address Line 2",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getAddressLine2()));
			if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getCity(), OTHER))
				homeOwner.put("City", checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getCityOther()));
			else
				homeOwner.put("City", checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getCity()));

			homeOwner.put("State", checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getState()));
			homeOwner.put("Postal Code",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getPostalCode()));

			String coordinate = permit.getPermitHomeSiteEntityResult().getLatitude() != null
					? permit.getPermitHomeSiteEntityResult().getLatitude() + ", "
							+ permit.getPermitHomeSiteEntityResult().getLongitude()
					: "";
			homeOwner.put("GPS COORDINATES", checkValueTypes.convert(coordinate));
			homeOwner.put("The Mailing Address is the same as the Site Address",
					Boolean.TRUE.equals(permit.getPermitHomeSiteEntityResult().getSameMailing()) ? "Yes" : "No");
			if (!Boolean.TRUE.equals(permit.getPermitHomeSiteEntityResult().getSameMailing())) {
				homeOwner.put("Mailing Address ",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getSecondaryAddress()));
				homeOwner.put("Mailing Address Line 2",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getAddressLine2()));
				if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getSecondaryCity(), OTHER))
					homeOwner.put("Mailing City",
							checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getSecondaryCityOther()));
				else
					homeOwner.put("Mailing City",
							checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getSecondaryCity()));
				homeOwner.put("Mailing State",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getSecondaryState()));
				homeOwner.put("Mailing Postal Code",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getSecondaryPostalCode()));
			}

			homeOwner.put("Main Contact Phone",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getHomePhone()));
			homeOwner.put("Other Phone",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getOtherPhone()));
			homeOwner.put("Email Address",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getEmailPhone()));
			homeOwner.put("Property APN",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getPropertyAPN()));
		
			String ahjTitle = "Project Jurisdiction (AHJ)";
			if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getProjectJurisdiction(), OTHER)
					&& permit.getPermitHomeSiteEntityResult().getProjectJurisOther() != null) {
				homeOwner.put(ahjTitle,
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getProjectJurisdiction()));
			} else if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getProjectJurisdiction(),
					"city")) {

				if (checkValueTypes.NotEquals(permit.getPermitHomeSiteEntityResult().getCity(), OTHER)) {
					homeOwner.put(ahjTitle, checkValueTypes.convert("City of " + permit.getPermitHomeSiteEntityResult().getCity()));

				} else if (permit.getPermitHomeSiteEntityResult().getProjectJurisOther() != null) {
					homeOwner.put(ahjTitle, checkValueTypes
							.convert("City of " + permit.getPermitHomeSiteEntityResult().getProjectJurisOther()));
				} else {
					homeOwner.put(ahjTitle, "");
				}

			} else if (permit.getPermitHomeSiteEntityResult().getProjectJurisdiction() != null
					&& permit.getPermitHomeSiteEntityResult().getProjectJurisdiction().contains("County")) {

				if (checkValueTypes.NotEquals(permit.getPermitHomeSiteEntityResult().getCity(), OTHER)) {
					homeOwner.put(ahjTitle,
							checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getProjectJurisdiction()));
				} else if (permit.getPermitHomeSiteEntityResult().getProjectJurisOther() != null) {
					homeOwner.put(ahjTitle, checkValueTypes
							.convert("County of " + permit.getPermitHomeSiteEntityResult().getProjectJurisOther()));
				} else {
					homeOwner.put(ahjTitle, "");
				}

			} else {
				homeOwner.put(ahjTitle, "");
			}
			homeOwner.put("Other Project Jurisdiction (AHJ)",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getProjectJurisOther()));
			if (checkValueTypes.isNumericNotZero(permit.getPermitHomeSiteEntityResult().getUtilityCompanyName())) {

				ElectricalUtilityEntity electricalUtility = electricalUtilityRepo
						.findById(Long.parseLong(permit.getPermitHomeSiteEntityResult().getUtilityCompanyName()))
						.orElse(new ElectricalUtilityEntity());

				homeOwner.put("Utility Company", checkValueTypes.convert(electricalUtility.getUtilityCompanyName()));

			} else if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getUtilityCompanyName(), OTHER)) {
				homeOwner.put("Utility Company",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getUtilityCompanyNameOther()));
			} else if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getUtilityCompanyName(), "Removed")) {
				homeOwner.put("Utility Company","Removed");
			}

			homeOwner.put("Electric Utility Meter Number",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getMeterNumber()));
			homeOwner.put("Electric Utility ESI ID Number", 
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getEsiidNumber()));

			if (Boolean.TRUE.equals(permit.getPermitHomeSiteEntityResult().getIfServiceVoltage())) {
				homeOwner.put("Service is 240V Single (Split) Phase", "Yes");
			} else {
				homeOwner.put("Service is 240V Single (Split) Phase", "No");
				if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getServiceVoltage(), OTHER)) {
					homeOwner.put("Service Voltage & Phase Configuration",
							checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getServiceVoltageOther()));
				} else {
					getServiceVoltageValue(
							checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getServiceVoltage()));
				}
			}
			homeOwner.put("Roof Mounted",
					Boolean.TRUE.equals(permit.getPermitArraysEntity().getRoofMounted()) ? "Yes" : "No");
			homeOwner.put("Ground Mounted",
					Boolean.TRUE.equals(permit.getPermitArraysEntity().getGroundMounted()) ? "Yes" : "No");
			homeOwner.put("Patio Cover",
					Boolean.TRUE.equals(permit.getPermitArraysEntity().getPatioMounted()) ? "Yes" : "No");
			homeOwner.put("Carport",
					Boolean.TRUE.equals(permit.getPermitArraysEntity().getCarportMounted()) ? "Yes" : "No");
			homeOwner.put("Other Basic Type Of System",
					checkValueTypes.convert(permit.getPermitArraysEntity().getTextOther()));

			String structuralType = "";
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getFrontAndBack()))
				structuralType = checkValueTypes.isStringNotEmpty(structuralType) ? structuralType + "/ Front and Back Posts" : "Front and Back Posts";
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getCantelever()))
				structuralType = checkValueTypes.isStringNotEmpty(structuralType) ? structuralType + "/ Cantilever & T-Type" : "Cantilever & T-Type";
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getAttachedToExtWal()))
				structuralType = checkValueTypes.isStringNotEmpty(structuralType) ? structuralType + "/ Attached to Ext Wal" : "Attached to Ext Wal";
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getAttachedToFascia()))
				structuralType = checkValueTypes.isStringNotEmpty(structuralType) ? structuralType + "/ Attached to Fascia" : "Attached to Fascia";
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getAttachedToSkylifts()))
				structuralType = checkValueTypes.isStringNotEmpty(structuralType) ? structuralType + "/ Attached to Skylifts on Ext Walls" : "Attached to Skylifts on Ext Walls";
			if (Boolean.TRUE.equals(permit.getPermitArraysEntity().getFreeStanding()))
				structuralType = checkValueTypes.isStringNotEmpty(structuralType) ? structuralType + "/ Free Standing" : "Free Standing";
			
			
			homeOwner.put("Select Structural Type (If Mixed Check all that Apply)", structuralType);
			homeOwner.put("Roof or Open Frame",
					checkValueTypes.convert(permit.getPermitArraysEntity().getRoofOrOpenFrame()));
			homeOwner.put("At least one circuit between the Array and the Main Service Panel will be underground",
					Boolean.TRUE.equals(permit.getPermitArraysEntity().getCircuitUnderGround()) ? "Yes" : "No");

			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getTallStructure(), "OtheStory"))
				homeOwner.put("How tall is the structure",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getOtherTallStructure()));
			else
				homeOwner.put("How tall is the structure",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getTallStructure()));

			homeOwner.put("Mean Height of Roof in feet",
					checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getMeanHeight() + ""));

			String roofMaterialTypeTitle = "Roofing Material Type";
			if (checkValueTypes.isLongPositive(permit.getPermitProjectSiteInfoEntityTwo().getRoofMaterialType())) {
				RoofMaterialType roofMaterialType = roofMaterialTypeRepo
						.findById(permit.getPermitProjectSiteInfoEntityTwo().getRoofMaterialType())
						.orElse(new RoofMaterialType());
				homeOwner.put(roofMaterialTypeTitle, checkValueTypes.convert(roofMaterialType.getTypeRoof()));

			} else if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getRoofMaterialType(),
					OTHER)) {
				homeOwner.put(roofMaterialTypeTitle,
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getRoofMaterialTypeOther()));
			} else {
				homeOwner.put(roofMaterialTypeTitle, "");
			}
			homeOwner.put("There is more than 1 Layer of Comp Shingles",
					Boolean.TRUE.equals(permit.getPermitEngineerEntityResult().getIsShingles()) ? "Yes" : "No");
			homeOwner.put("Indicate how many Layer",
					checkValueTypes.convert(permit.getPermitEngineerEntityResult().getIndicateLayers()));
			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getCrossSectionSize(), "OtherSize"))
				homeOwner.put("Roof Structural Member Cross Section Size",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getTextOtherSize()));
			else
				homeOwner.put("Roof Structural Member Cross Section Size",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getCrossSectionSize()));

			if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getConstructionType(), "OtherConst"))
				homeOwner.put("Construction Type",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getTextOtherConst()));
			else
				homeOwner.put("Construction Type",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getConstructionType()));

			String rafterTitle = "Roof Rafter/Truss Design";
			if (checkValueTypes.isStringNotEmpty(permit.getPermitHomeSiteEntityResult().getRoofRafterOther())) {
				if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getRoofRafterOther(), OTHER))
					homeOwner.put(rafterTitle,
							checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getSecroofRafterOther()));
				else
					homeOwner.put(rafterTitle,
							checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getRoofRafterOther()));
			} else if (checkValueTypes.isStringNotEmpty(permit.getPermitHomeSiteEntityResult().getRoofRafter())) {
				homeOwner.put(rafterTitle,
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getRoofRafter()));
			} else {
				homeOwner.put(rafterTitle, "");
			}

			//R.G 27-01-2022 PP-688
			if (permit.getProjectNotes() != null && checkValueTypes.isStringNotEmpty(permit.getProjectNotes().getFloridaRafterDesign())) {
				homeOwner.put("Florida Roof Rafter Design Note", checkValueTypes
						.convert(permit.getProjectNotes().getFloridaRafterDesign()));
			}
			
			if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getRidgeBeamDepthAtArrays(), OTHER))
				homeOwner.put("Ridge Beam Depth at Arrays", checkValueTypes
						.convert(permit.getPermitHomeSiteEntityResult().getRidgeBeamDepthAtArraysOther()));
			else
				homeOwner.put("Ridge Beam Depth at Arrays",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getRidgeBeamDepthAtArrays()));

			homeOwner.put("Max Horizontal Span at Arrays (HS1)",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getMaxHorizontalSpanAtArrays()));
			homeOwner.put("Max Horizontal Span at Arrays (HS1) INCHES", checkValueTypes
					.convert(permit.getPermitHomeSiteEntityResult().getMaxHorizontalSpanAtArraysInches()));
			homeOwner.put("Max Horizontal Span at Arrays (HS2)",
					checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getMaxHorizontalSpanAtArraysHS()));
			homeOwner.put("Max Horizontal Span at Arrays (HS2) INCHES", checkValueTypes
					.convert(permit.getPermitHomeSiteEntityResult().getMaxHorizontalSpanAtArraysHSInches()));

			if (checkValueTypes.Equals(permit.getPermitProjectSiteInfoEntityTwo().getRafterTrussSpacing(), OTHER))
				homeOwner.put("Rafter or Truss Spacing",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getTextOtherRatfter()));
			else
				homeOwner.put("Rafter or Truss Spacing",
						checkValueTypes.convert(permit.getPermitProjectSiteInfoEntityTwo().getRafterTrussSpacing()));

			if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getBuildingRisk(), OTHER))
				homeOwner.put("Building Risk Category",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getBuildingRiskOther()));
			else
				homeOwner.put("Building Risk Category",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getBuildingRisk()));

			if (checkValueTypes.Equals(permit.getPermitHomeSiteEntityResult().getBuildingOccupancy(), OTHER))
				homeOwner.put("Building Occupancy",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getTextOtherBuildOccup()));
			else
				homeOwner.put("Building Occupancy",
						checkValueTypes.convert(permit.getPermitHomeSiteEntityResult().getBuildingOccupancy()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return homeOwner;
	}

	public String getServiceVoltageValue(String service) {

		if (checkValueTypes.Equals(service, "120/208V")) {
			return "Three Phase Four Wire Wye 120/208V";
		} else if (checkValueTypes.Equals(service, "277/480V")) {
			return "Three Phase Four Wire Wye 277/480V";
		} else if (checkValueTypes.Equals(service, "(High Leg) 240V")) {
			return "Three phase Four Wire Delta (High Leg) 240V";
		} else if (checkValueTypes.Equals(service, "Wire Delta 208V")) {
			return "Three phase Three Wire Delta 208V";
		} else if (checkValueTypes.Equals(service, "Wire Wye 208V")) {
			return "Three phase Three Wire Wye 208V";
		} else if (checkValueTypes.Equals(service, "Wire Delta 240V")) {
			return "Three phase Three Wire Delta 240V";
		} else if (checkValueTypes.Equals(service, "400V")) {
			return "Three Phase Three Wire Delta 400V";
		} else if (checkValueTypes.Equals(service, "480V")) {
			return "Three Phase Three Wire Delta 480V";
		} else if (checkValueTypes.Equals(service, "Wire Wye 480V")) {
			return "Three phase Three Wire Wye 480V";
		} else if (checkValueTypes.Equals(service, "600V")) {
			return "Three Phase Three Wire Delta 600V";
		} else {
			return "";
		}
	}
}
