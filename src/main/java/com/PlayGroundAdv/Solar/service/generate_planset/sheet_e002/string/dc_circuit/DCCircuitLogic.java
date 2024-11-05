package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.dc_circuit;

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
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
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
public class DCCircuitLogic {

	final GenerateCircuitList generateCircuitList;
	final GetPVModuleData getPVModuleData;
	final DefaultRowMapping defaultRowMapping;
	final DCCircuitMapping dcCircuitMapping;
	final CheckValueTypesService checkValue;
	final CircuitEnvironment circuitEnvironmentService;
	final GetConverterData getConverterData;
	final TechnicalProblemMsg technicalProblem;

	public DCCircuitLogic(GenerateCircuitList generateCircuitList, GetPVModuleData getPVModuleData,
			DefaultRowMapping defaultRowMapping, DCCircuitMapping dcCircuitMapping, CheckValueTypesService checkValue,
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

	public void dcMapping(AcroFields form, List<ESSConnectors> dcList, Integer fourPerCentAverageHigh,
			Integer twoPerCentAverageHigh, List<String> dcCircuitEnvironment,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, List<String> dcTradeSize,
			List<Integer> dcNumberOfConductors, PermitArrayEntityResultSecond permitArraysEntityResult,
			AuthentificationEntity userConnectedEntity, Cmodulev2 moduleInfo, int sheetIndex,
			UserSettingEntity userSetting, SystemEnvironment systemEnvironment, String state,
			DCOptimizerEntity dcOptimizer, PermitLayoutEntity permitLayoutEntity, E002Model params,
			Inverters inverterInfo, PlansetUtils plansetUtils, String necCode) {
		try {

			int j = 1;
			String iScOptimizer = getConverterData.getIScRef(dcOptimizer);
			Boolean stepOptimizer = dcOptimizer != null;
			int originCombiner = getIndexByProperty(dcList);

			do {
				// A.B 04-03: Executed Only when the operating Temperature is not High
				params.setIncorrectTradeSize(new ArrayList<>());
				params = dcCircuitMapping.mapDCCircuits(j, form, iScOptimizer, originCombiner, dcList,
						fourPerCentAverageHigh, systemEnvironment, dcCircuitEnvironment, stepOptimizer, circuit,
						permitEntity, dcTradeSize, dcNumberOfConductors, permitArraysEntityResult, userConnectedEntity,
						moduleInfo, sheetIndex, userSetting, state, params, dcOptimizer, inverterInfo, plansetUtils,
						necCode, permitLayoutEntity.getShowConduitRoofAsHeight());
				j++;
			} while (j <= dcList.size()
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

				for (j = 1; j <= dcList.size(); j++) {
					params.setIncorrectTradeSize(new ArrayList<>());
					params = dcCircuitMapping.mapDCCircuits(j, form, iScOptimizer, originCombiner, dcList,
							twoPerCentAverageHigh, systemEnvironment, dcCircuitEnvironment, stepOptimizer, circuit,
							permitEntity, dcTradeSize, dcNumberOfConductors, permitArraysEntityResult,
							userConnectedEntity, moduleInfo, sheetIndex, userSetting, state, params, dcOptimizer,
							inverterInfo, plansetUtils, necCode, permitLayoutEntity.getShowConduitRoofAsHeight());
				}
				params.setOperatingTemperatureHigh(true);
			}

			for (int k = dcList.size() + 1; k < 9; k++) {
				defaultRowMapping.mapDcDefaultValue(k, form, sheetIndex, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}

	}
	private int getIndexByProperty(List<ESSConnectors> acList) {
		for (int i = 0; i < acList.size(); i++) {
			if (acList.get(i).getSourceID().contains("DCC")) {
				return i + 1;
			}
		}
		return -1;// not ACC in the list list
	}
}
