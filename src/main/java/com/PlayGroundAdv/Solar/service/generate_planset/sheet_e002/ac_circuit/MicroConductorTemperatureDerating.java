package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.AmpacityCorrectionChart;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class MicroConductorTemperatureDerating {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final CircuitEnvironment circuitEnvService;
	final AmpacityCorrectionChart ampCorrection;
	final CircuitEnviromentMapping circuitEnvMapping;

	public MicroConductorTemperatureDerating(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			CircuitEnvironment circuitEnvService, AmpacityCorrectionChart ampCorrection,
			CircuitEnviromentMapping circuitEnvMapping) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.circuitEnvService = circuitEnvService;
		this.ampCorrection = ampCorrection;
		this.circuitEnvMapping = circuitEnvMapping;
	}

	public String conductorTemperatureDeratingMapping(AcroFields form, String acCircuit, int i,
			Integer fourPerCentAverageHigh, List<String> acCircuitEnvironment, Boolean remapAdder,
			PermitConduitConductorSectionEntity circuit, int sheetIndex, UserSettingEntity userSetting, String necCode,
			Boolean showConduitRoofAsHeight, E002Model params, String state) {

		try {
			circuitEnvMapping.circuitEnvMapping(form, i, sheetIndex, acCircuit, circuit, params);
			acCircuitEnvironment.add(params.getLastInvirement());

			return conductorTemperatureDerating(form, i, fourPerCentAverageHigh, remapAdder, sheetIndex, userSetting,
					necCode, showConduitRoofAsHeight, params, state);

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}
	}

	public String essconductorTemperatureDeratingMapping(AcroFields form, ESSConnectors ac, int i,
			Integer fourPerCentAverageHigh, List<String> acCircuitEnvironment, Boolean remapAdder, int sheetIndex,
			UserSettingEntity userSetting, String necCode, Boolean showConduitRoofAsHeight, E002Model params,
			String state) {

		try {
			form.setField(sheetIndex + "-" + "AC" + i + "-CIRCUIT-ENVIRONMENT",
					ac.getCircuitSpec().getCircuitEnvironment());
			params.setLastInvirement(ac.getCircuitSpec().getCircuitEnvironment());
			acCircuitEnvironment.add(ac.getCircuitSpec().getCircuitEnvironment());

			return conductorTemperatureDerating(form, i, fourPerCentAverageHigh, remapAdder, sheetIndex, userSetting,
					necCode, showConduitRoofAsHeight, params, state);
			
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}
	}

	private String conductorTemperatureDerating(AcroFields form, int i, Integer fourPerCentAverageHigh,
			Boolean remapAdder, int sheetIndex, UserSettingEntity userSetting, String necCode,
			Boolean showConduitRoofAsHeight, E002Model params, String state) {
		try {
			// A.B CR-2554 MOD-005 remap with temp adder equals 0
			String tempAdder = checkValue.Equals(remapAdder, true) ? "0"
					: circuitEnvService.getTempAdder(params.getLastInvirement(), necCode, state, userSetting,
							showConduitRoofAsHeight);
			String temperatureAdder = checkValue.Equals(params.getLastInvirement(), "UNDERGROUND") ? "30"
					: getOperatingTemperature(fourPerCentAverageHigh, tempAdder);
			params.setTempDerating(checkValue.Equals(params.getLastInvirement(), "UNDERGROUND") ? "1.00"
					: ampCorrection.getACAmpacityCorrectionB2a(temperatureAdder));
			params.setOperatingTemperatureHigh(checkValue.getFloatValue(temperatureAdder) > 70);
			// A.B 07/14/2022 CR-PM-1016 Map 30 for Underground Circuit
			form.setField(sheetIndex + "-" + "AC" + i + "-04-PERCENT-AVERAGE-HIGH-TEMPERATURE",
					checkValue.Equals(params.getLastInvirement(), "UNDERGROUND") ? "30" : fourPerCentAverageHigh + "");
			form.setField(sheetIndex + "-" + "AC" + i + "-HEIGHT-ABOVE-ROOF",
					circuitEnvService.getHighAboveRoof(params.getLastInvirement(), necCode, state));
			form.setField(sheetIndex + "-" + "AC" + i + "-TEMPERATURE-ADDER", tempAdder);
			form.setField(sheetIndex + "-" + "AC" + i + "-OPERATING-TEMPERATURE", temperatureAdder);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION",
					params.getTempDerating());
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION1",
					params.getTempDerating());
			return temperatureAdder;
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}

	}

	public String getOperatingTemperature(Integer fourPerCentAverageHigh, String tempAdder) {
		try {
			if (checkValue.isStringInt(tempAdder) && fourPerCentAverageHigh != null) {
				return (fourPerCentAverageHigh + Integer.parseInt(tempAdder)) + "";
			} else
				return "0";

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "0";
		}
	}
}
