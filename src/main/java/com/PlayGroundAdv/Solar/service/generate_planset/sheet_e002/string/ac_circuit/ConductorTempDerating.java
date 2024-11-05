package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.ac_circuit;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.CircuitEnviromentMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.AmpacityCorrectionChart;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ConductorTempDerating {

	static final String UNDERGROUND = "UNDERGROUND";
	static final String CIRCUIT_ENVIRONMENT = "-CIRCUIT-ENVIRONMENT";

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final CircuitEnvironment circuitEnvService;
	final CircuitEnviromentMapping circuitEnvMapping;
	final AmpacityCorrectionChart ampCorrection;

	public ConductorTempDerating(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			CircuitEnvironment circuitEnvService, CircuitEnviromentMapping circuitEnvMapping,
			AmpacityCorrectionChart ampCorrection) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		this.circuitEnvService = circuitEnvService;
		this.circuitEnvMapping = circuitEnvMapping;
		this.ampCorrection = ampCorrection;
	}

	public E002Model mapCondTempDerating(AcroFields form, int i, ESSConnectors ac, Integer fourPerCentAverageHigh,
			List<String> acCircuitEnvironment, int sheetIndex, String state, E002Model params, String necCode,
			UserSettingEntity userSetting, Boolean showConduitRoofAsHeight) {

		try {
			form.setField(sheetIndex + "-" + "AC" + i + CIRCUIT_ENVIRONMENT,
					ac.getCircuitSpec().getCircuitEnvironment());
			params.setLastInvirement(ac.getCircuitSpec().getCircuitEnvironment());
			acCircuitEnvironment.add(params.getLastInvirement());

			String operatingTemperature = checkValue.Equals(params.getLastInvirement(), UNDERGROUND) ? "30"
					: String.valueOf(fourPerCentAverageHigh);
			String acAmpacityCorrectionB2a = checkValue.Equals(params.getLastInvirement(), UNDERGROUND) ? "1.00"
					: ampCorrection.getACAmpacityCorrectionB2a(String.valueOf(fourPerCentAverageHigh));
			// A.B 07/14/2022 CR-PM-1016 Map 30 for Underground Circuit
			form.setField(sheetIndex + "-" + "AC" + i + "-04-PERCENT-AVERAGE-HIGH-TEMPERATURE",
					String.valueOf(operatingTemperature));
			// A.B 04/11/2021 CR-3908
			form.setField(sheetIndex + "-" + "AC" + i + "-HEIGHT-ABOVE-ROOF",
					circuitEnvService.getHighAboveRoof(params.getLastInvirement(), necCode, state));
			form.setField(sheetIndex + "-" + "AC" + i + "-TEMPERATURE-ADDER", circuitEnvService
					.getTempAdder(params.getLastInvirement(), necCode, state, userSetting, showConduitRoofAsHeight));
			form.setField(sheetIndex + "-" + "AC" + i + "-OPERATING-TEMPERATURE", operatingTemperature);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION",
					acAmpacityCorrectionB2a);
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION1",
					acAmpacityCorrectionB2a);

			params.setOperatingTemperatureHigh(checkValue.getFloatValue(operatingTemperature) > 70);
			params.setTempDerating(acAmpacityCorrectionB2a);

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

}
