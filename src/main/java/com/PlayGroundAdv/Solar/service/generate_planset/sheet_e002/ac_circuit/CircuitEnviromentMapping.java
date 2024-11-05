package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class CircuitEnviromentMapping {

	final static String CIRCUIT_ENVIRONMENT = "-CIRCUIT-ENVIRONMENT";

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;

	public CircuitEnviromentMapping(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	public void circuitEnvMapping(AcroFields form, int i, int sheetIndex, String acCircuit,
			PermitConduitConductorSectionEntity circuit, E002Model params) {
		try {
			// 08/12/2021 A.B CR-3227 MOD-001
			if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")) {
				form.setField(sheetIndex + "-" + "AC" + i + CIRCUIT_ENVIRONMENT, circuit.getCircuitEnvironmentSix());
				params.setLastInvirement(circuit.getCircuitEnvironmentSix());
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")) {
				form.setField(sheetIndex + "-" + "AC" + i + CIRCUIT_ENVIRONMENT, circuit.getCircuitEnvironmentSeven());
				params.setLastInvirement(circuit.getCircuitEnvironmentSeven());
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {
				form.setField(sheetIndex + "-" + "AC" + i + CIRCUIT_ENVIRONMENT, circuit.getCircuitEnvironmentEight());
				params.setLastInvirement(circuit.getCircuitEnvironmentEight());
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")) {
				form.setField(sheetIndex + "-" + "AC" + i + CIRCUIT_ENVIRONMENT, circuit.getCircuitEnvironmentNine());
				params.setLastInvirement(circuit.getCircuitEnvironmentNine());
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")) {
				form.setField(sheetIndex + "-" + "AC" + i + CIRCUIT_ENVIRONMENT,
						circuit.getCircuitEnvironmentNineTwo());
				params.setLastInvirement(circuit.getCircuitEnvironmentNineTwo());
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")) {
				form.setField(sheetIndex + "-" + "AC" + i + CIRCUIT_ENVIRONMENT, circuit.getCircuitEnvironmentTen());
				params.setLastInvirement(circuit.getCircuitEnvironmentTen());
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")) {
				form.setField(sheetIndex + "-" + "AC" + i + CIRCUIT_ENVIRONMENT, circuit.getCircuitEnvironmentEleven());
				params.setLastInvirement(circuit.getCircuitEnvironmentEleven());
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")) {
				form.setField(sheetIndex + "-" + "AC" + i + CIRCUIT_ENVIRONMENT, circuit.getCircuitEnvironmentTwelve());
				params.setLastInvirement(circuit.getCircuitEnvironmentTwelve());
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
