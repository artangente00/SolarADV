package com.PlayGroundAdv.Solar.service.project;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermitConduitConductorSectionEntitieResult;
import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
import com.PlayGroundAdv.Solar.model.SystemAttributesModel;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetInverterOCPD;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetModuleOCPD;
import com.PlayGroundAdv.Solar.service.equipment_utils.ProjectNumberOfMicroInverter;
import com.PlayGroundAdv.Solar.service.libraries.InverterService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GetSystemSpec {

	final CheckValueTypesService checkValue;
	final GetProjectDetailsUtils getProjectDetailsUtils;
	final GetInverterOCPD getInverterOCPD;
	final GetModuleOCPD getModuleOCPD;
	final InverterService inverterLibrarySerivce;
	final ProjectNumberOfMicroInverter projectNumberOfMicroInverter;
	
	public GetSystemSpec(CheckValueTypesService checkValue, GetProjectDetailsUtils getProjectDetailsUtils,
			GetInverterOCPD getInverterOCPD, GetModuleOCPD getModuleOCPD,
			InverterService inverterLibrarySerivce,
			ProjectNumberOfMicroInverter projectNumberOfMicroInverter) {
		super();
		this.checkValue = checkValue;
		this.getProjectDetailsUtils = getProjectDetailsUtils;
		this.getInverterOCPD = getInverterOCPD;
		this.getModuleOCPD = getModuleOCPD;
		this.inverterLibrarySerivce = inverterLibrarySerivce;
		this.projectNumberOfMicroInverter = projectNumberOfMicroInverter;
	}

	public SystemAttributesModel getProjectSystemAttributes(PermitArrayEntityResultSecond permitArrays, PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityResult, PermitConduitConductorSectionEntitieResult permitConduitConductor) {
		SystemAttributesModel systemAttributesModel = new SystemAttributesModel();
		try {
			
			// A.B Basic Type Of System
			systemAttributesModel
					.setRoofMounted(checkValue.Equals(permitArrays.getRoofMounted(), true));
			systemAttributesModel.setRoofMountedByLogic(
					checkValue.Equals(permitArrays.getCarportMounted(), true)
							&& checkValue.Equals(permitArrays.getRoofOrOpenFrame(),
									"The carport/patio cover will include roofing material under the modules"));
			systemAttributesModel
					.setGroundMounted(checkValue.Equals(permitArrays.getGroundMounted(), true));
			systemAttributesModel.setGroundMountedByLogic(
					checkValue.Equals(permitArrays.getCarportMounted(), true)
							&& checkValue.Equals(permitArrays.getRoofOrOpenFrame(),
									"The carport/patio cover will be open frame construction"));
			systemAttributesModel.setCarportMounted(
					checkValue.Equals(permitArrays.getCarportMounted(), true));
			
			systemAttributesModel.setPatioMounted(
					checkValue.Equals(permitArrays.getPatioMounted(), true));

			// A.B Inverter Technologies
			systemAttributesModel.setMicroInverterSystem(
					getProjectDetailsUtils.isMicroOrAcModuleProject(permitArrays.getDeviceToIncorporate()));
			systemAttributesModel.setStringInverterSystem(getProjectDetailsUtils
					.isStringOrOptimizerProject(permitArrays.getDeviceToIncorporate()));

			// A.B Micro Inverter System with More than Branch Circuit
			systemAttributesModel
					.setTwoACCircuitSystem(systemAttributesModel.getMicroInverterSystem() && checkValue
							.isStringNotEmpty(permitArrays.getNumberModulesACCircuitTwo()));
			systemAttributesModel.setTwoMicroInverterSystem(systemAttributesModel.getMicroInverterSystem()
					&& checkValue.isStringNotEmpty(permitArrays.getOcpdTwo()));
			systemAttributesModel.setThreeMicroInverterSystem(systemAttributesModel.getMicroInverterSystem()
					&& checkValue.isStringNotEmpty(permitArrays.getOcpdThree()));
			systemAttributesModel.setFourMicroInverterSystem(systemAttributesModel.getMicroInverterSystem()
					&& checkValue.isStringNotEmpty(permitArrays.getOcpdFour()));
			systemAttributesModel.setFiveMicroInverterSystem(systemAttributesModel.getMicroInverterSystem()
					&& checkValue.isStringNotEmpty(permitArrays.getOcpdFive()));

			// A.B String Inverter System with More than one Inverter
			systemAttributesModel.setTwoStringInverterSystem(systemAttributesModel.getStringInverterSystem()
					&& permitArrays.getSecondInverterModel() != null);
			systemAttributesModel.setThreeStringInverterSystem(systemAttributesModel.getStringInverterSystem()
					&& permitArrays.getThirdInverterModel() != null);
			systemAttributesModel.setFourStringInverterSystem(systemAttributesModel.getStringInverterSystem()
					&& permitArrays.getFourthInverterModel() != null);
			systemAttributesModel.setFiveStringInverterSystem(systemAttributesModel.getStringInverterSystem()
					&& permitArrays.getFifthInverterModel() != null);

			// A.B Sub Panel with More than Branch Circuit Or More than one Inverter
			systemAttributesModel.setSubPanelWithMultipleSystem(checkValue
					.Equals(permitProjectSiteInfoEntityResult.getSolarLocation(), "Back-fed Breaker")
					&& (systemAttributesModel.getTwoStringInverterSystem()
							|| systemAttributesModel.getTwoMicroInverterSystem())
					&& (checkValue.Equals(permitProjectSiteInfoEntityResult.getCombiningACCircuits(),
							"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
							|| checkValue.Equals(permitProjectSiteInfoEntityResult.getCombiningACCircuits(),
									"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker")));

			// A.B Sub Panel Breaker 120% rule
			if (Boolean.TRUE.equals(systemAttributesModel.getStringInverterSystem())) {
				systemAttributesModel.setSumInvertersIacmax(getInverterOCPD.getOcpdNumber(
						permitArrays.getInverterModel(), permitArrays.getSecondInverterModel(),
						permitArrays.getThirdInverterModel(),
						permitArrays.getFourthInverterModel(),
						permitArrays.getFifthInverterModel()));
			} else {
				systemAttributesModel.setSumInvertersIacmax(0.0);
			}

			if (Boolean.TRUE.equals(systemAttributesModel.getMicroInverterSystem())) {

				Inverters microInverterInfo = inverterLibrarySerivce.getInverterEntity(permitArrays.getMicroInverter());
				if (microInverterInfo != null) {

					double iacmax = getInverterOCPD.getOcpdNumberMicroInverterS(microInverterInfo.getId());
					systemAttributesModel.setSumMicroIacmaxNumber(
							projectNumberOfMicroInverter.getNumberMicroInverter(permitArrays, microInverterInfo)
									* iacmax);
				} else {
					systemAttributesModel.setSumMicroIacmaxNumber(0.0);
				}

				if (permitArrays.getPvModuleModEl() != null) {
					Double iacmax = getModuleOCPD.getModuleOcpdNumber(permitArrays.getPvModuleModEl());
					Integer moduleQty = permitConduitConductor.getQtySegmentOne() != null
							? permitConduitConductor.getQtySegmentOne()
							: 0;
					systemAttributesModel.setSumModulesIacmax(iacmax * moduleQty);
				} else {
					systemAttributesModel.setSumModulesIacmax(0.0);
				}

			} else {
				systemAttributesModel.setSumMicroIacmaxNumber(0.0);
				systemAttributesModel.setSumModulesIacmax(0.0);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return systemAttributesModel;
	}
}
