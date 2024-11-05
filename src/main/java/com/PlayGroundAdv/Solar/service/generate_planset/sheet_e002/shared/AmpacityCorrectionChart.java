package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class AmpacityCorrectionChart {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;

	public AmpacityCorrectionChart(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	public String dcAmpacityCorrectionB3a(ESSCircuitSpec circuit, Integer numCondMapped, Integer numCondB316,
			Boolean calculating) {

		try {
			if (circuit != null && checkValue.Equals(circuit.getConduitSize(), "N/A")) {
				return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return getCorrectionB3a(numCondMapped, numCondB316);
	}

	public String getDCAmpacityCorrectionB3a(PermitConduitConductorSectionEntity circuit, String origin,
			Integer numCondMapped, Integer numCondB316, Boolean calculating) {

		try {

			if (circuit != null) {
				if (checkValue.Equals(origin, "PV MODULE")) {
					if (checkValue.Equals(circuit.getConduitSize(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (circuit.getConduitSize() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				} else if (checkValue.Equals(origin, "DC/DC CONVERTER")) {
					if (checkValue.Equals(circuit.getConduitSizeTwo(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (circuit.getConduitSizeTwo() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				} else if (checkValue.Equals(origin, "JUNCTION BOX")) {
					if (checkValue.Equals(circuit.getConduitSizeThree(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (circuit.getConduitSizeThree() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				} else if (checkValue.Equals(origin, "DC COMBINER")) {
					if (checkValue.Equals(circuit.getConduitSizeFour(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (circuit.getConduitSizeFour() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				} else if (checkValue.Equals(origin, "DC DISCONNECT")) {
					if (checkValue.Equals(circuit.getConduitSizeFive(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (circuit.getConduitSizeFive() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				}
			}
			return getCorrectionB3a(numCondMapped, numCondB316);
		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return getCorrectionB3a(numCondMapped, numCondB316);
		}

	}

	public String getDCAmpacityCorrectionB2a(String operateTemp) {

		try {
			Double operateTemp90 = checkValue.isNumeric(operateTemp) ? Double.valueOf(operateTemp) : 0;
			if (operateTemp90 >= 21 && operateTemp90 <= 25) {
				return "1.04";
			} else if (operateTemp90 >= 26 && operateTemp90 <= 30) {
				return "1.00";
			} else if (operateTemp90 >= 31 && operateTemp90 <= 35) {
				return "0.96";
			} else if (operateTemp90 >= 36 && operateTemp90 <= 40) {
				return "0.91";
			} else if (operateTemp90 >= 41 && operateTemp90 <= 45) {
				return "0.87";
			} else if (operateTemp90 >= 46 && operateTemp90 <= 50) {
				return "0.82";
			} else if (operateTemp90 >= 51 && operateTemp90 <= 55) {
				return "0.76";
			} else if (operateTemp90 >= 56 && operateTemp90 <= 60) {
				return "0.71";
			} else if (operateTemp90 >= 61 && operateTemp90 <= 65) {
				return "0.65";
			} else if (operateTemp90 >= 66 && operateTemp90 <= 70) {
				return "0.58";
			} else if (operateTemp90 >= 71 && operateTemp90 <= 75) {
				return "0.50";
			} else if (operateTemp90 >= 76 && operateTemp90 <= 80) {
				return "0.41";
			} else if (operateTemp90 >= 81 && operateTemp90 <= 85) {
				return "0.29";
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}
	};

	public String getCorrectionB3a(Integer numCondMapped, Integer numCondB316) {

		String ampCorrectionB3a = "";
		try {
			if (numCondMapped != null && numCondB316 != null) {
				Integer numCond = numCondMapped * numCondB316;

				if (numCond >= 1 && numCond <= 3) {
					ampCorrectionB3a = "1.0";
				} else if (numCond >= 4 && numCond <= 6) {
					ampCorrectionB3a = ".80";
				} else if (numCond >= 7 && numCond <= 9) {
					ampCorrectionB3a = ".70";
				} else if (numCond >= 10 && numCond <= 20) {
					ampCorrectionB3a = ".50";
				} else if (numCond >= 21 && numCond <= 30) {
					ampCorrectionB3a = ".45";
				} else if (numCond >= 31 && numCond <= 40) {
					ampCorrectionB3a = ".40";
				} else if (numCond >= 41) {
					ampCorrectionB3a = ".35";
				}
			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return ampCorrectionB3a;
	}

	public String getACAmpacityCorrectionB2a(String operateTemp75String) {

		try {
			if (checkValue.isStringDouble(operateTemp75String)) {
				Double operateTemp = Double.parseDouble(operateTemp75String);
				if (operateTemp < 21) {
					return "1.11";
				} else if (operateTemp >= 21 && operateTemp <= 25) {
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
			return "0.00";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "0.00";
		}
	};

	public String getACAmpacityCorrectionB3a(PermitConduitConductorSectionEntity circuit, String origin,
			Integer numCondMapped, Integer numCondB316, Boolean calculating, Boolean mapAfterRevision) {

		try {
			if (circuit != null) {
				if (checkValue.Equals(origin, "INVERTER")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitSizeSix(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (Boolean.FALSE.equals(mapAfterRevision) || circuit.getConduitSizeSix() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				} else if (checkValue.Equals(origin, "JUNCTION BOX")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitSizeSeven(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (Boolean.FALSE.equals(mapAfterRevision) || circuit.getConduitSizeSeven() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				} else if (checkValue.Equals(origin, "AC COMBINER/LOAD CENTER")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitSizeEight(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (Boolean.FALSE.equals(mapAfterRevision) || circuit.getConduitSizeEight() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				} else if (checkValue.Equals(origin, "AC DISCONNECT")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitSizeNine(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (Boolean.FALSE.equals(mapAfterRevision) || circuit.getConduitSizeNine() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				} else if (checkValue.Equals(origin, "PRODUCTION METER")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitSizeTen(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (Boolean.FALSE.equals(mapAfterRevision) || circuit.getConduitSizeTen() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				} else if (checkValue.Equals(origin, "SUB PANEL")) {
					if (Boolean.TRUE.equals(mapAfterRevision)
							&& checkValue.Equals(circuit.getConduitSizeEleven(), "N/A")) {
						return Boolean.TRUE.equals(calculating) ? "1.0" : "N/A";
					} else if (Boolean.FALSE.equals(mapAfterRevision) || circuit.getConduitSizeEleven() != null) {
						return getCorrectionB3a(numCondMapped, numCondB316);
					}

				}
			}
			return getCorrectionB3a(numCondMapped, numCondB316);
		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return getCorrectionB3a(numCondMapped, numCondB316);
		}

	}

}
