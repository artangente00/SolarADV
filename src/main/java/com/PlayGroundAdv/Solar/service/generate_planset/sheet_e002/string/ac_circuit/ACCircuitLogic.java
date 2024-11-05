package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.ac_circuit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.dc_circuit.DCCircuitLogic;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACCircuitLogic {

	final GenerateCircuitList generateCircuitList;
	final GetPVModuleData getPVModuleData;
	final DefaultRowMapping defaultRowMapping;
	final CheckValueTypesService checkValue;
	final CircuitEnvironment circuitEnvironmentService;
	final TechnicalProblemMsg technicalProblem;
	final ACCircuitMapping acCircuitMapping;
	final DCCircuitLogic essDCCircuitLogic;

	public ACCircuitLogic(GenerateCircuitList generateCircuitList, GetPVModuleData getPVModuleData,
			DefaultRowMapping defaultRowMapping, CheckValueTypesService checkValue,
			CircuitEnvironment circuitEnvironmentService, TechnicalProblemMsg technicalProblem,
			ACCircuitMapping acCircuitMapping, DCCircuitLogic essDCCircuitLogic) {
		super();
		this.generateCircuitList = generateCircuitList;
		this.getPVModuleData = getPVModuleData;
		this.defaultRowMapping = defaultRowMapping;
		this.checkValue = checkValue;
		this.circuitEnvironmentService = circuitEnvironmentService;
		this.technicalProblem = technicalProblem;
		this.acCircuitMapping = acCircuitMapping;
		this.essDCCircuitLogic = essDCCircuitLogic;
	}

	public void acMapping(AcroFields form, List<ESSConnectors> acList, String sumIacMax, String largestIacMax,
			PermitArrayEntityResultSecond permitArraysEntityResult, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Integer fourPerCentAverageHigh, Integer twoPerCentAverageHigh, List<String> acCircuitEnvironment,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, AuthentificationEntity userConnectedEntity, int sheetIndex,
			PermitHomeSiteInfoEntity permitHomeSite, PermitLayoutEntity permitLayoutEntity,
			UserSettingEntity userSetting, List<String> dcTradeSize, List<Integer> dcNumberOfConductors,
			List<String> dcCircuitEnvironment, List<ESSConnectors> dcList, Cmodulev2 moduleInfo,
			DCOptimizerEntity dcOptimizer, E002Model params, Inverters inverterInfo, PlansetUtils plansetUtils,
			String necCode) {
		try {

			Boolean stepACCombiner = false;
			int indexAcCombiner = getIndexByProperty(acList);
			int acCircuitLength = acList.size();

			if (circuit.getQtySegmentEight() != null && circuit.getQtySegmentEight() != 0) {
				stepACCombiner = true;
			}
			SystemEnvironment systemEnvironment = circuitEnvironmentService
					.getCircuitEnvironment(permitArraysEntityResult, permitLayoutEntity, userSetting, necCode);

			int k = 1;
			do {
				// A.B 04-03: Executed Only when the operating Temperature is not High
				params.setIncorrectACTradeSize(new ArrayList<>());
				acCircuitMapping.mapACCircuits(k, form, indexAcCombiner, stepACCombiner, sumIacMax, largestIacMax,
						acList, permitProjectSiteInfo, fourPerCentAverageHigh, acCircuitEnvironment, circuit,
						permitEntity, acTradeSize, acNumberOfConductors, userConnectedEntity, sheetIndex,
						permitHomeSite, systemEnvironment, params, necCode, userSetting,
						permitLayoutEntity.getShowConduitRoofAsHeight());
				k++;

			} while (k < acCircuitLength + 1
					&& (checkValue.Equals(params.getOperatingTemperatureHigh(), false) || twoPerCentAverageHigh == 0));

			// A.B 04-03: Executed Only when the operating Temperature is High
			if (checkValue.Equals(params.getOperatingTemperatureHigh(), true) && twoPerCentAverageHigh > 0) {

				if (checkValue.Equals(params.getDcCircuitUpdated(), false)) {

					dcTradeSize.clear();
					dcNumberOfConductors.clear();
					dcCircuitEnvironment.clear();

					for (int j = 1; j <= dcList.size(); j++) {
						params.setIncorrectTradeSize(new ArrayList<>());
						essDCCircuitLogic.dcMapping(form, dcList, fourPerCentAverageHigh, twoPerCentAverageHigh,
								dcCircuitEnvironment, circuit, permitEntity, dcTradeSize, dcNumberOfConductors,
								permitArraysEntityResult, userConnectedEntity, moduleInfo, sheetIndex, userSetting,
								systemEnvironment, permitHomeSite.getState(), dcOptimizer,
								permitLayoutEntity, params, inverterInfo, plansetUtils, necCode);
					}
				}
				for (k = 1; k < acCircuitLength + 1; k++) {

					acTradeSize.clear();
					acNumberOfConductors.clear();
					acCircuitEnvironment.clear();

					// A.B 04-03: Executed Only when the operating Temperature is High
					params.setIncorrectACTradeSize(new ArrayList<>());
					acCircuitMapping.mapACCircuits(k, form, indexAcCombiner, stepACCombiner, sumIacMax, largestIacMax,
							acList, permitProjectSiteInfo, twoPerCentAverageHigh, acCircuitEnvironment, circuit,
							permitEntity, acTradeSize, acNumberOfConductors, userConnectedEntity, sheetIndex,
							permitHomeSite, systemEnvironment, params, necCode, userSetting,
							permitLayoutEntity.getShowConduitRoofAsHeight());
				}
			}

			for (int i = acCircuitLength + 1; i < 11; i++) {
				defaultRowMapping.mapAcDefaultValue(k, form, sheetIndex, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);

		}
	}

	private int getIndexByProperty(List<ESSConnectors> acList) {
		for (int i = 0; i < acList.size(); i++) {
			if (acList.get(i).getSourceID().contains("ACC") || acList.get(i).getSourceID().contains("SLC")) {
				return i + 1;
			}
		}
		return -1;// not ACC in the list list
	}
}
