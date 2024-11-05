package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class ACAmpacityCorrection {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;

	public ACAmpacityCorrection(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
	}

	public String acAmpacityCorrectionB3a(ESSCircuitSpec circuitSpec, Integer numberOfConductorString,
			Integer numberofConductor, Boolean calculating, Boolean mapAfterRevision) {
		try {
			if (Boolean.TRUE.equals(mapAfterRevision) && checkValue.Equals(circuitSpec.getConduitType(), "N/A")) {
				return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
			}
			return getACCorrectionB3a(numberOfConductorString, numberofConductor);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return getACCorrectionB3a(numberOfConductorString, numberofConductor);
		}
	}

	public String getACAmpacityCorrectionB3a(PermitConduitConductorSectionEntity circuit, String origin,
			Integer numberOfConductorString, Integer numberofConductor, Boolean calculating, Boolean mapAfterRevision) {

		try {
			if (circuit != null) {
				if (checkValue.Equals(origin, "INVERTER")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitTypeSix(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
					} else if (circuit.getConduitTypeSix() != null) {
						return getACCorrectionB3a(numberOfConductorString, numberofConductor);
					}

				} else if (checkValue.Equals(origin, "JUNCTION BOX")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitTypeSeven(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
					} else if (circuit.getConduitTypeSeven() != null) {
						return getACCorrectionB3a(numberOfConductorString, numberofConductor);
					}

				} else if (checkValue.Equals(origin, "AC COMBINER/LOAD CENTER")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitTypeEight(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
					} else if (circuit.getConduitTypeEight() != null) {
						return getACCorrectionB3a(numberOfConductorString, numberofConductor);
					}

				} else if (checkValue.Equals(origin, "AC DISCONNECT")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitTypeNine(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
					} else if (circuit.getConduitTypeNine() != null) {
						return getACCorrectionB3a(numberOfConductorString, numberofConductor);
					}

				} else if (checkValue.Equals(origin, "AC DISCONNECTTwo")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitTypeNineTwo(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
					} else if (circuit.getConduitTypeNineTwo() != null) {
						return getACCorrectionB3a(numberOfConductorString, numberofConductor);
					}

				} else if (checkValue.Equals(origin, "PRODUCTION METER")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitTypeTen(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
					} else if (circuit.getConduitTypeTen() != null) {
						return getACCorrectionB3a(numberOfConductorString, numberofConductor);
					}

				} else if (checkValue.Equals(origin, "SUB PANEL")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitTypeEleven(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
					} else if (circuit.getConduitTypeEleven() != null) {
						return getACCorrectionB3a(numberOfConductorString, numberofConductor);
					}

				} else if (checkValue.Equals(origin, "TRANSFORMER")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitTypeTwelve(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
					} else if (circuit.getConduitTypeTwelve() != null) {
						return getACCorrectionB3a(numberOfConductorString, numberofConductor);
					}

				}
			}
			return getACCorrectionB3a(numberOfConductorString, numberofConductor);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return getACCorrectionB3a(numberOfConductorString, numberofConductor);
		}

	}

	public String getACCorrectionB3a(Integer numberOfConductorString, Integer numberofConductor) {
		try {
			if (numberofConductor != null && numberOfConductorString != null) {
				Integer acNumbUngroundCond = numberOfConductorString * numberofConductor;
				if (acNumbUngroundCond >= 1 && acNumbUngroundCond <= 3) {
					return "1.0";
				} else if (acNumbUngroundCond >= 4 && acNumbUngroundCond <= 6) {
					return "0.80";
				} else if (acNumbUngroundCond >= 7 && acNumbUngroundCond <= 9) {
					return "0.70";
				} else if (acNumbUngroundCond >= 10 && acNumbUngroundCond <= 20) {
					return "0.50";
				} else if (acNumbUngroundCond >= 21 && acNumbUngroundCond <= 30) {
					return "0.45";
				} else if (acNumbUngroundCond >= 31 && acNumbUngroundCond <= 40) {
					return "0.40";
				} else if (acNumbUngroundCond >= 41) {
					return "0.35";
				} else {
					return "N/A";
				}
			}
			return "";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return "";
		}
	}

	public String getACAmpacityCorrectionB2aMultiple(String operateTemp75String) {

		try {
			if (checkValue.isStringInt(operateTemp75String)) {
				Integer operateTemp = Integer.parseInt(operateTemp75String);

				if (operateTemp >= 21 && operateTemp <= 25) {
					return "1.05";
				} else if (operateTemp >= 26 && operateTemp <= 30) {
					return "1.00";
				} else if (operateTemp >= 31 && operateTemp <= 35) {
					return "0.94";
				} else if (operateTemp >= 36 && operateTemp <= 40) {
					return "0.88";
				} else if (operateTemp >= 41 && operateTemp <= 45) {
					return "0.82";
				} else if (operateTemp >= 46 && operateTemp <= 50) {
					return "0.75";
				} else if (operateTemp >= 51 && operateTemp <= 55) {
					return "0.67";
				} else if (operateTemp >= 56 && operateTemp <= 60) {
					return "0.58";
				} else if (operateTemp >= 61 && operateTemp <= 65) {
					return "0.47";
				} else if (operateTemp >= 66 && operateTemp <= 70) {
					return "0.33";
				} else if (operateTemp >= 71 && operateTemp <= 80) {
					return "0.00";
				}
			}
			return "1.00";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return "1.00";
		}

	}
}
