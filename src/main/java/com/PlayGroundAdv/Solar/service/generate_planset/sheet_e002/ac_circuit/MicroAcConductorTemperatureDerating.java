package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class MicroAcConductorTemperatureDerating {
	
	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final CircuitEnvironment circuitEnvService;

	public MicroAcConductorTemperatureDerating(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			CircuitEnvironment circuitEnvService) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		this.circuitEnvService = circuitEnvService;
	}

}
