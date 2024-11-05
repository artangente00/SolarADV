package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.planset_charts.VoltageDropInformation;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.planset_charts.VoltageDropInformationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class VoltageDropCalculation {

	final VoltageDropInformationRepository voltageDropInformationRepo;
	final CheckValueTypesService checkValue;

	public VoltageDropCalculation(VoltageDropInformationRepository voltageDropInformationRepo,
			CheckValueTypesService checkValue) {
		super();
		this.voltageDropInformationRepo = voltageDropInformationRepo;
		this.checkValue = checkValue;
	}

	public Float dcVoltageDropBatterySystem(int i, Float length, DCOptimizerEntity dcOptimizer, Cmodulev2 moduleInfo,
			Inverters inverterInfo, String iScOptimizer, int numberOfStrings, int originCombiner,
			PlansetUtils plansetUtils, String wireSize, Integer numberOfModule) {
		try {
			Float voltageDrop = 0f;
			Float dcRes = getDcResistance(wireSize);
			if (length != null && length > 0) {
				Float amps = getAmps(i, dcOptimizer, moduleInfo, iScOptimizer, numberOfStrings, originCombiner);
				Float vmp = getVmp(dcOptimizer, moduleInfo, inverterInfo, plansetUtils);
				if (amps != null && vmp != null && vmp > 0 && numberOfModule > 0) {
					// A.B CR-PM-3227 “%VD” = (2 x “Circuit Length” x Amps x DC Resistance) / (Vmp x
					// 10)
					voltageDrop = (2 * length * amps * dcRes) / (vmp * 10);
				}
			}

			return voltageDrop;
		} catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}

	public Float dcVoltageDrop(int i, String dcCircuit, PermitConduitConductorSectionEntity circuit,
			DCOptimizerEntity dcOptimizer, Cmodulev2 moduleInfo, Inverters inverterInfo, String iScOptimizer,
			int numberOfStrings, int originCombiner, PlansetUtils plansetUtils, String wireSize,
			Integer numberOfModule) {
		try {
			Float voltageDrop = 0f;
			Float length = getCircuitLength(i, dcCircuit, circuit);
			Float dcRes = getDcResistance(wireSize);
			if (length != null && length > 0) {
				Float amps = getAmps(i, dcOptimizer, moduleInfo, iScOptimizer, numberOfStrings, originCombiner);
				Float vmp = getVmp(dcOptimizer, moduleInfo, inverterInfo, plansetUtils);
				if (amps != null && vmp != null && vmp > 0 && numberOfModule > 0) {
					// A.B CR-PM-3227 “%VD” = (2 x “Circuit Length” x Amps x DC Resistance) / (Vmp x
					// 10)
					voltageDrop = (2 * length * amps * dcRes) / (vmp * 10);
				}
			}

			return voltageDrop;
		} catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}

	public Float acVoltageDrop(int i, String acCircuit, PermitConduitConductorSectionEntity circuit, String wireSize,
			Integer numberOfConductor, String maxInvOutputCurrent, Boolean serviceVoltage,
			String serviceThreePhaseVoltage) {

		Float amps = checkValue.getFloatValue(maxInvOutputCurrent);
		Float length = getAcCircuitLength(i, acCircuit, circuit);
		if (Boolean.TRUE.equals(serviceVoltage)) {
			return acSinglePhaseVoltageDrop(wireSize, numberOfConductor, amps, length);
		} else {
			return acThreePhaseVoltageDrop(wireSize, numberOfConductor, amps, length, serviceThreePhaseVoltage);
		}
	}

	public Float acVoltageDropBatterySystem(String wireSize, Integer numberOfConductor, String maxInvOutputCurrent,
			Boolean serviceVoltage, String serviceThreePhaseVoltage, Float length) {

		Float amps = checkValue.getFloatValue(maxInvOutputCurrent);
		if (Boolean.TRUE.equals(serviceVoltage)) {
			return acSinglePhaseVoltageDrop(wireSize, numberOfConductor, amps, length);
		} else {
			return acThreePhaseVoltageDrop(wireSize, numberOfConductor, amps, length, serviceThreePhaseVoltage);
		}
	}

	public Float acSinglePhaseVoltageDrop(String wireSize, Integer numberOfConductor, Float amps, Float length) {
		try {
			Float voltageDrop = 0f;
			Float acRes = getAcResistance(wireSize);
			if (length != null && length > 0 && amps != null && numberOfConductor > 0) {
				// A.B CR-PM-3227 “%VD” = (2 x “Circuit Length” x “Amps” x “AC Resistance” ) /
				// (“Volts” x “# of Conductors” x 10))
				voltageDrop = (2 * length * amps * acRes) / (240 * numberOfConductor * 10);
			}

			return voltageDrop;
		} catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}

	public Float acThreePhaseVoltageDrop(String wireSize, Integer numberOfConductor, Float amps, Float length,
			String serviceThreePhaseVoltage) {
		try {
			Float voltageDrop = 0f;
			wireSize = wireSize.contains("#") ? wireSize.replace("#", "") : wireSize;
			VoltageDropInformation voltage = voltageDropInformationRepo.findByWireSize(wireSize);
			if (length != null && length > 0) {
				Integer vmp = getThreePhaseVmp(serviceThreePhaseVoltage);
				if (amps != null && vmp != null && vmp > 0 && numberOfConductor > 0) {
					// A.B CR-PM-3227 “%VD” = (1.732 x 12.9 x Q x “Circuit Length” x “Amps” x 100) /
					// (CM x “Volts” x “# of Conductors”)
					voltageDrop = (22.3428f * voltage.getQ() * length * amps * 100)
							/ (voltage.getCm() * vmp * numberOfConductor);
				}
			}

			return voltageDrop;
		} catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}

	private Float getDcResistance(String wireSize) {
		try {
			wireSize = wireSize.contains("#") ? wireSize.replace("#", "") : wireSize;
			return voltageDropInformationRepo.findDcResistanceByWireSize(wireSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Float getAcResistance(String wireSize) {
		try {
			wireSize = wireSize.contains("#") ? wireSize.replace("#", "") : wireSize;
			return voltageDropInformationRepo.findAcResistanceByWireSize(wireSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Integer getThreePhaseVmp(String service) {
		try {
			if (checkValue.contains(service, "208")) {
				return 208;
			} else if (checkValue.contains(service, "480")) {
				return 480;
			} else if (checkValue.contains(service, "240")) {
				return 240;
			} else if (checkValue.contains(service, "400")) {
				return 400;
			} else if (checkValue.contains(service, "600")) {
				return 600;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Float getAmps(int i, DCOptimizerEntity dcOptimizer, Cmodulev2 moduleInfo, String iScOptimizer,
			int numberOfStrings, int originCombiner) {
		try {
			Float amps = 0f;
			if (dcOptimizer == null || Boolean.FALSE.equals(dcOptimizer.getAltersVoltage())) {
				if (originCombiner > 0 && i >= originCombiner) {
					amps = checkValue.isNumeric(moduleInfo.getiMpRef())
							? Float.valueOf(moduleInfo.getiMpRef()) * numberOfStrings
							: null;
				} else {
					amps = checkValue.isNumeric(moduleInfo.getiMpRef()) ? Float.valueOf(moduleInfo.getiMpRef()) : null;
				}
			} else {
				if (originCombiner > 0 && i >= originCombiner) {
					amps = checkValue.isNumeric(iScOptimizer) ? Float.valueOf(iScOptimizer) * numberOfStrings : null;
				} else {
					amps = checkValue.isNumeric(iScOptimizer) ? Float.valueOf(iScOptimizer) : null;
				}
			}
			return amps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Float getVmp(DCOptimizerEntity dcOptimizer, Cmodulev2 moduleInfo, Inverters inverterInfo,
			PlansetUtils plansetUtils) {
		try {
			Float vmp = 0f;
			if (dcOptimizer == null || Boolean.FALSE.equals(dcOptimizer.getAltersVoltage())) {
				vmp = checkValue.isNumeric(moduleInfo.getvMpRef())
						? Float.valueOf(moduleInfo.getvMpRef()) * plansetUtils.getMinNumModule()
						: null;
			} else if (inverterInfo != null) {
				vmp = checkValue.isNumeric(inverterInfo.getMpptLow()) ? Float.valueOf(inverterInfo.getMpptLow()) : null;
			}
			return vmp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Float getCircuitLength(int i, String dcCircuit, PermitConduitConductorSectionEntity circuit) {
		try {
			if (checkValue.Equals(dcCircuit.split("-")[i - 1], "PV MODULE")) {
				return circuit.getCircuitLengthOne();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "DC/DC CONVERTER")) {
				return circuit.getCircuitLengthTwo();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "JUNCTION BOX")) {
				return circuit.getCircuitLengthThree();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "DC COMBINER")) {
				return circuit.getCircuitLengthFour();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "DC DISCONNECT")) {
				return circuit.getCircuitLengthFive();
			} else
				return 0.0f;
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0f;
		}
	}

	private Float getAcCircuitLength(int i, String dcCircuit, PermitConduitConductorSectionEntity circuit) {
		try {
			if (checkValue.Equals(dcCircuit.split("-")[i - 1], "INVERTER")) {
				return circuit.getCircuitLengthSix();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "JUNCTION BOX")) {
				return circuit.getCircuitLengthSeven();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {
				return circuit.getCircuitLengthEight();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "AC DISCONNECT")) {
				return circuit.getCircuitLengthNine();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "AC DISCONNECTTwo")) {
				return circuit.getCircuitLengthNineTwo();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "PRODUCTION METER")) {
				return circuit.getCircuitLengthTen();
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "SUB PANEL")) {
				return circuit.getCircuitLengthEleven();
			} else
				return 0.0f;
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0f;
		}
	}
}
