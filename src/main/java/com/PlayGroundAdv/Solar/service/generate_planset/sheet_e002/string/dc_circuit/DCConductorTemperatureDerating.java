package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.dc_circuit;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCConductorTemperatureDerating {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final CircuitEnvironment circuitEnvService;

	public DCConductorTemperatureDerating(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			CircuitEnvironment circuitEnvService) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		this.circuitEnvService = circuitEnvService;
	}

	public E002Model mapTemperatureDerating(int i, AcroFields form, ESSConnectors dc, Integer fourPerCentAverageHigh,
			List<String> dcCircuitEnvironment, int sheetIndex, UserSettingEntity userSetting, String state,
			E002Model params, String necCode, Boolean showConduitRoofAsHeight) {

		try {
			params.setTempAdder("");

			// 02/18/2019 A.B: CR-2313 MOD-005-1
			// 08/12/2021 A.B CR-3227 MOD-001
			form.setField(sheetIndex + "-" + "DC" + i + "-CIRCUIT-ENVIRONMENT",
					dc.getCircuitSpec().getCircuitEnvironment());
			params.setLastInvirement(dc.getCircuitSpec().getCircuitEnvironment());
			// A.B 11/04/2021 CR-3908
			String highAboveRoof = circuitEnvService.getHighAboveRoof(params.getLastInvirement(), necCode, state);
			String tempAdder = circuitEnvService.getTempAdder(params.getLastInvirement(), necCode, state, userSetting,
					showConduitRoofAsHeight);
			String operatingTemperature = checkValue.Equals(params.getLastInvirement(), "UNDERGROUND") ? "30"
					: getOperatingTemperature(fourPerCentAverageHigh, tempAdder);
			String dcAmpacityCorrectionB2a = checkValue.Equals(params.getLastInvirement(), "UNDERGROUND") ? "1.00"
					: getDCAmpacityCorrectionB2a(getOperatingTemperature(fourPerCentAverageHigh, tempAdder));

			// A.B 07/14/2022 CR-PM-1016 Map 30 for Underground Circuit
			form.setField(sheetIndex + "-" + "DC" + i + "-04-PERCENT-AVERAGE-HIGH-TEMPERATURE",
					checkValue.Equals(params.getLastInvirement(), "UNDERGROUND") ? "30" : fourPerCentAverageHigh + "");
			form.setField(sheetIndex + "-" + "DC" + i + "-HEIGHT-ABOVE-ROOF", highAboveRoof);
			form.setField(sheetIndex + "-" + "DC" + i + "-TEMPERATURE-ADDER", tempAdder);
			form.setField(sheetIndex + "-" + "DC" + i + "-OPERATING-TEMPERATURE", operatingTemperature);
			form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION",
					dcAmpacityCorrectionB2a);
			form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION1",
					dcAmpacityCorrectionB2a);

			params.setOperatingTemperatureHigh(checkValue.getFloatValue(operatingTemperature) > 85);
			params.setTempAdder(tempAdder);
			if (checkValue.isStringNotEmpty(dcAmpacityCorrectionB2a)) {
				params.setAmpacityCorrection(dcAmpacityCorrectionB2a);
			}

			dcCircuitEnvironment.add(params.getLastInvirement());

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public E002Model remapTemperatureDerating(int i, AcroFields form, Integer fourPerCentAverageHigh,
			SystemEnvironment systemEnvironment, List<String> dcCircuitEnvironment, int sheetIndex,
			UserSettingEntity userSetting, String state, E002Model params, String necCode,
			Boolean showConduitRoofAsHeight) {

		try {
			String highAboveRoof = circuitEnvService.getHighAboveRoof(systemEnvironment.getSystemCircuitEnvironment(),
					necCode, state);
			String tempAdder = circuitEnvService.getTempAdder(systemEnvironment.getSystemCircuitEnvironment(), necCode,
					state, userSetting, showConduitRoofAsHeight);

			form.setField(sheetIndex + "-" + "DC" + i + "-CIRCUIT-ENVIRONMENT",
					systemEnvironment.getSystemCircuitEnvironment());
			// A.B 07/14/2022 CR-PM-1016 Map 30 for Underground Circuit
			form.setField(sheetIndex + "-" + "DC" + i + "-04-PERCENT-AVERAGE-HIGH-TEMPERATURE",
					checkValue.Equals(systemEnvironment.getSystemCircuitEnvironment(), "UNDERGROUND") ? "30"
							: fourPerCentAverageHigh + "");
			form.setField(sheetIndex + "-" + "DC" + i + "-HEIGHT-ABOVE-ROOF", highAboveRoof);
			form.setField(sheetIndex + "-" + "DC" + i + "-TEMPERATURE-ADDER", "0");
			if (fourPerCentAverageHigh != null) {
				form.setField(sheetIndex + "-" + "DC" + i + "-OPERATING-TEMPERATURE", fourPerCentAverageHigh + "");
				params.setOperatingTemperatureHigh(fourPerCentAverageHigh > 85);
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB2a(getOperatingTemperature(fourPerCentAverageHigh, "0")));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB2a(getOperatingTemperature(fourPerCentAverageHigh, "0")));
				if (checkValue.NotEquals(
						getDCAmpacityCorrectionB2a(getOperatingTemperature(fourPerCentAverageHigh, "0")), "")) {
					params.setAmpacityCorrection(
							getDCAmpacityCorrectionB2a(getOperatingTemperature(fourPerCentAverageHigh, "0")));
				}
			}
			systemEnvironment.setTempAdder(tempAdder);
			params.setLastInvirement(systemEnvironment.getSystemCircuitEnvironment());
			dcCircuitEnvironment.add(params.getLastInvirement());
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public String getOperatingTemperature(Integer fourPerCentAverageHigh, String tempAdder) {
		try {
			if (checkValue.isStringInt(tempAdder) && fourPerCentAverageHigh != null) {
				return (fourPerCentAverageHigh + Integer.parseInt(tempAdder)) + "";
			} else {
				return "0";
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return "0";
		}
	}

	public String getDCAmpacityCorrectionB2a(String operateTemp90String) {
		String ampacityCorrection = "0.00";
		try {
			if (checkValue.isStringInt(operateTemp90String)) {
				Integer operateTemp90 = Integer.parseInt(operateTemp90String);
				if (operateTemp90 >= 21 && operateTemp90 <= 25) {
					ampacityCorrection = "1.04";
				} else if (operateTemp90 >= 26 && operateTemp90 <= 30) {
					ampacityCorrection = "1.00";
				} else if (operateTemp90 >= 31 && operateTemp90 <= 35) {
					ampacityCorrection = "0.96";
				} else if (operateTemp90 >= 36 && operateTemp90 <= 40) {
					ampacityCorrection = "0.91";
				} else if (operateTemp90 >= 41 && operateTemp90 <= 45) {
					ampacityCorrection = "0.87";
				} else if (operateTemp90 >= 46 && operateTemp90 <= 50) {
					ampacityCorrection = "0.82";
				} else if (operateTemp90 >= 51 && operateTemp90 <= 55) {
					ampacityCorrection = "0.76";
				} else if (operateTemp90 >= 56 && operateTemp90 <= 60) {
					ampacityCorrection = "0.71";
				} else if (operateTemp90 >= 61 && operateTemp90 <= 65) {
					ampacityCorrection = "0.65";
				} else if (operateTemp90 >= 66 && operateTemp90 <= 70) {
					ampacityCorrection = "0.58";
				} else if (operateTemp90 >= 71 && operateTemp90 <= 75) {
					ampacityCorrection = "0.50";
				} else if (operateTemp90 >= 76 && operateTemp90 <= 80) {
					ampacityCorrection = "0.41";
				} else if (operateTemp90 >= 81 && operateTemp90 <= 85) {
					ampacityCorrection = "0.29";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return ampacityCorrection;
	}
}
