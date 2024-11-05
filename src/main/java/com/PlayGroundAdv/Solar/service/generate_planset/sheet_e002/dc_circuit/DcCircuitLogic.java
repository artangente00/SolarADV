package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.dc_circuit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetConverterData;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DcCircuitLogic {

	final GenerateCircuitList generateCircuitList;
	final GetPVModuleData getPVModuleData;
	final DefaultRowMapping defaultRowMapping;
	final DcCircuitMapping dcCircuitMapping;
	final CheckValueTypesService checkValue;
	final CircuitEnvironment circuitEnvironmentService;
	final GetConverterData getConverterData;
	final TechnicalProblemMsg technicalProblem;

	public DcCircuitLogic(GenerateCircuitList generateCircuitList, GetPVModuleData getPVModuleData,
			DefaultRowMapping defaultRowMapping, DcCircuitMapping dcCircuitMapping, CheckValueTypesService checkValue,
			CircuitEnvironment circuitEnvironmentService, GetConverterData getConverterData,
			TechnicalProblemMsg technicalProblem) {
		super();
		this.generateCircuitList = generateCircuitList;
		this.getPVModuleData = getPVModuleData;
		this.defaultRowMapping = defaultRowMapping;
		this.dcCircuitMapping = dcCircuitMapping;
		this.checkValue = checkValue;
		this.circuitEnvironmentService = circuitEnvironmentService;
		this.getConverterData = getConverterData;
		this.technicalProblem = technicalProblem;
	}

	public void dcMapping(AcroFields form, String dcCircuit, Integer fourPerCentAverageHigh,
			Integer twoPerCentAverageHigh, List<String> dcCircuitEnvironment,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity,
			List<String> dcTradeSize, List<Integer> dcNumberOfConductors,
			PermitArrayEntityResultSecond permitArraysEntityResult, AuthentificationEntity userConnectedEntity,
			Cmodulev2 moduleInfo, int sheetIndex, UserSettingEntity userSetting, SystemEnvironment systemEnvironment,
			String state, DCOptimizerEntity dcOptimizer, int dcCircuitLength, PermitLayoutEntity permitLayoutEntity,
			E002Model params,Inverters inverterInfo, PlansetUtils plansetUtils, String necCode) {
		try {
			
			if (circuit.getComponentOrder() != null) {
				String converterType = dcOptimizer != null ? dcOptimizer.getType() : null;
				dcCircuit = generateCircuitList.getConductorCircuit(circuit,
						permitArraysEntityResult.getDeviceToIncorporate(), converterType);
			}
			int j = 1;
			String iScOptimizer = getConverterData.getIScRef(dcOptimizer);
			Boolean stepOptimizer = dcCircuit.contains("RAPID SHUTDOWN") || dcCircuit.contains("DC/DC CONVERTER");
			int originCombiner = dcCircuit.contains("DC COMBINER")
					? generateCircuitList.getDcCombinerIndex(dcCircuit) + 1
					: 0;

			do {
				// A.B 04-03: Executed Only when the operating Temperature is not High
				params.setIncorrectTradeSize(new ArrayList<>());
				params = dcCircuitMapping.mapDCCircuits(j, form, iScOptimizer, originCombiner,
						dcCircuit, fourPerCentAverageHigh, systemEnvironment, dcCircuitEnvironment, stepOptimizer,
						circuit, permitEntity, dcTradeSize, dcNumberOfConductors, permitArraysEntityResult,
						userConnectedEntity, moduleInfo, sheetIndex, userSetting, state, params, dcOptimizer, 
						inverterInfo, plansetUtils, necCode, permitLayoutEntity.getShowConduitRoofAsHeight());
				j++;
			} while (j < dcCircuitLength + 1
					&& (checkValue.Equals(params.getOperatingTemperatureHigh(), false) || twoPerCentAverageHigh == 0));

			params.setDcCircuitUpdated(
					checkValue.Equals(params.getOperatingTemperatureHigh(), true) && twoPerCentAverageHigh > 0);
			// A.B 04-03: Executed Only when the operating Temperature is High
			if (Boolean.TRUE.equals(params.getDcCircuitUpdated())) {

				dcTradeSize.clear();
				dcNumberOfConductors.clear();
				dcCircuitEnvironment.clear();

				systemEnvironment = circuitEnvironmentService.getCircuitEnvironment(permitArraysEntityResult,
						permitLayoutEntity, userSetting, necCode);

				for (j = 1; j < dcCircuitLength + 1; j++) {
					params.setIncorrectTradeSize(new ArrayList<>());
					params = dcCircuitMapping.mapDCCircuits(j, form, iScOptimizer, originCombiner,
							dcCircuit, twoPerCentAverageHigh, systemEnvironment, dcCircuitEnvironment, stepOptimizer,
							circuit, permitEntity, dcTradeSize, dcNumberOfConductors,
							permitArraysEntityResult, userConnectedEntity, moduleInfo, sheetIndex, userSetting, state,
							params, dcOptimizer, inverterInfo, plansetUtils, necCode, permitLayoutEntity.getShowConduitRoofAsHeight());
				}
				params.setOperatingTemperatureHigh(true);
			}

			for (int k = dcCircuitLength + 1; k < 9; k++) {
				defaultRowMapping.mapDcDefaultValue(k, form, sheetIndex, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}

	}

}
