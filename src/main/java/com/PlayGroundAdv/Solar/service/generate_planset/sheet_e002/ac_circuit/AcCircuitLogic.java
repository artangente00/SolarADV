package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

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
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.dc_circuit.DcCircuitLogic;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class AcCircuitLogic {

	final GenerateCircuitList generateCircuitList;
	final GetPVModuleData getPVModuleData;
	final DefaultRowMapping defaultRowMapping;
	final CheckValueTypesService checkValue;
	final CircuitEnvironment circuitEnvironmentService;
	final TechnicalProblemMsg technicalProblem;
	final AcCircuitMapping acCircuitMapping;
	final DcCircuitLogic dcCircuitLogic;

	public AcCircuitLogic(GenerateCircuitList generateCircuitList, GetPVModuleData getPVModuleData,
			DefaultRowMapping defaultRowMapping, CheckValueTypesService checkValue,
			CircuitEnvironment circuitEnvironmentService, TechnicalProblemMsg technicalProblem,
			AcCircuitMapping acCircuitMapping, DcCircuitLogic dcCircuitLogic) {
		super();
		this.generateCircuitList = generateCircuitList;
		this.getPVModuleData = getPVModuleData;
		this.defaultRowMapping = defaultRowMapping;
		this.checkValue = checkValue;
		this.circuitEnvironmentService = circuitEnvironmentService;
		this.technicalProblem = technicalProblem;
		this.acCircuitMapping = acCircuitMapping;
		this.dcCircuitLogic = dcCircuitLogic;
	}

	public void acMapping(AcroFields form, String sumIacMax, String largestIacMax,
			PermitArrayEntityResultSecond permitArraysEntityResult,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, Integer fourPerCentAverageHigh,
			Integer twoPerCentAverageHigh, List<String> acCircuitEnvironment,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, AuthentificationEntity userConnectedEntity, int sheetIndex,
			PermitHomeSiteInfoEntity permitHomeSite, PermitLayoutEntity permitLayoutEntity,
			UserSettingEntity userSetting, List<String> dcTradeSize, List<Integer> dcNumberOfConductors,
			List<String> dcCircuitEnvironment, int dcCircuitLength, String dcCircuit, Cmodulev2 moduleInfo,
			DCOptimizerEntity dcOptimizer, E002Model params, Inverters inverterInfo, PlansetUtils plansetUtils,
			String necCode) {
		try {
			String acCircuit = "";
			if (circuit.getComponentOrder() != null) {
				acCircuit = generateCircuitList.getConduitCircuit(circuit);
			}

			Boolean stepACCombiner = false;
			int indexAcCombiner = acCircuit.contains("AC COMBINER/LOAD CENTER")
					? generateCircuitList.getAcCombinerIndex(acCircuit) + 1
					: 0;
			int acCircuitLength = acCircuit.split("-").length;

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
						acCircuit, permitProjectSiteInfo, fourPerCentAverageHigh, acCircuitEnvironment, acCircuitLength,
						circuit, permitEntity, acTradeSize, acNumberOfConductors, userConnectedEntity,
						sheetIndex, permitHomeSite, systemEnvironment, params, necCode, userSetting,
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

					for (int j = 1; j < dcCircuitLength + 1; j++) {
						params.setIncorrectTradeSize(new ArrayList<>());
						dcCircuitLogic.dcMapping(form, dcCircuit, fourPerCentAverageHigh,
								twoPerCentAverageHigh, dcCircuitEnvironment, circuit, permitEntity,
								dcTradeSize, dcNumberOfConductors, permitArraysEntityResult, userConnectedEntity,
								moduleInfo, sheetIndex, userSetting, systemEnvironment, permitHomeSite.getState(),
								dcOptimizer, dcCircuitLength, permitLayoutEntity, params,inverterInfo, plansetUtils, necCode);
					}
				}
				for (k = 1; k < acCircuitLength + 1; k++) {

					acTradeSize.clear();
					acNumberOfConductors.clear();
					acCircuitEnvironment.clear();

					// A.B 04-03: Executed Only when the operating Temperature is High
					params.setIncorrectACTradeSize(new ArrayList<>());
					acCircuitMapping.mapACCircuits(k, form, indexAcCombiner, stepACCombiner, sumIacMax, largestIacMax,
							acCircuit, permitProjectSiteInfo, twoPerCentAverageHigh, acCircuitEnvironment,
							acCircuitLength, circuit, permitEntity, acTradeSize, acNumberOfConductors,
							userConnectedEntity, sheetIndex, permitHomeSite, systemEnvironment, params, necCode,
							userSetting, permitLayoutEntity.getShowConduitRoofAsHeight());
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

}
