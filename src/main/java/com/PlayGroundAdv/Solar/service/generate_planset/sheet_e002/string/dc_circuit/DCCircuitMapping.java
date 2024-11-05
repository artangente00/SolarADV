package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.dc_circuit;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.model.planset_utils.VoltageDropTable;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.dc_circuit.RequiredConductorAmpacityAc;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.NEC310Values;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.VoltageDropCalculation;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCCircuitMapping {

	final GetPVModuleData getPVModuleData;
	final DefaultRowMapping defaultRowMapping;
	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final RequiredConductorAmpacityAc requConductorAmpacity;
	final DCConductorTemperatureDerating conductorTempDerating;
	final DCConduitFillDerating conduitFillDerating;
	final NEC3106B16Repository nec3106B16Repo;
	final VoltageDropCalculation voltageDropCalculation;
	final NEC310Values nec310Values;
	final GenerateCircuitList generateCircuitList;

	public DCCircuitMapping(GetPVModuleData getPVModuleData, DefaultRowMapping defaultRowMapping,
			CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			RequiredConductorAmpacityAc requConductorAmpacity, DCConductorTemperatureDerating conductorTempDerating,
			DCConduitFillDerating conduitFillDerating, NEC3106B16Repository nec3106b16Repo,
			VoltageDropCalculation voltageDropCalculation, NEC310Values nec310Values,
			GenerateCircuitList generateCircuitList) {
		super();
		this.getPVModuleData = getPVModuleData;
		this.defaultRowMapping = defaultRowMapping;
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		this.requConductorAmpacity = requConductorAmpacity;
		this.conductorTempDerating = conductorTempDerating;
		this.conduitFillDerating = conduitFillDerating;
		this.nec3106B16Repo = nec3106b16Repo;
		this.voltageDropCalculation = voltageDropCalculation;
		this.nec310Values = nec310Values;
		this.generateCircuitList = generateCircuitList;
	}

	public E002Model mapDCCircuits(int i, AcroFields form, String iScOptimizer, int originCombiner,
			List<ESSConnectors> dcList, Integer fourPerCentAverageHigh, SystemEnvironment systemEnvironment,
			List<String> dcCircuitEnvironment, Boolean stepOptimizer, PermitConduitConductorSectionEntity circuit,
			PermitEntity permitEntity, List<String> dcTradeSize, List<Integer> dcNumberOfConductors,
			PermitArrayEntityResultSecond permitArraysEntityResult, AuthentificationEntity userConnectedEntity,
			Cmodulev2 moduleInfo, int sheetIndex, UserSettingEntity userSetting, String state, E002Model params,
			DCOptimizerEntity dcOptimizer, Inverters inverterInfo, PlansetUtils plansetUtils, String necCode,
			Boolean showConduitRoofAsHeight) {

		try {

			String iScRef = getPVModuleData.getIScRef(moduleInfo);
			String optimizerType = dcOptimizer != null ? dcOptimizer.getType() : null;
			String circuitOrigin = i == 1 ? "PV MODULE" : generateCircuitList.getEquipmentName(dcList.get(i - 1).getSourceID(), null,
					permitEntity.getId(), dcList.get(i - 2).getTargetID(),optimizerType);
			String circuitDestination = generateCircuitList.getEquipmentName(dcList.get(i - 1).getTargetID(), null,
					permitEntity.getId(), dcList.get(i - 1).getSourceID(),optimizerType);

			form.setField(sheetIndex + "-" + "DC" + i + "-CIRCUIT-ORGIN", circuitOrigin);
			form.setField(sheetIndex + "-" + "DC" + i + "-CIRCUIT-DESTINATION", circuitDestination);

			///// **** CONDUCTOR SPECIFICATIONS ****//////

			form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL", "CU");
			form.setField(sheetIndex + "-" + "DC" + i + "-TEMPERATURE", "90");

			Boolean existing = Boolean.TRUE.equals(dcList.get(i - 1).getCircuitSpec().getExisting())
					|| Boolean.TRUE.equals(existingCircuit(dcList.get(i - 1), permitEntity));

			if (Boolean.TRUE.equals(existing)) {

				existingRow(i, form, sheetIndex, dcNumberOfConductors, dcTradeSize, dcCircuitEnvironment);

			} else {

				///// **** REQUIRED CONDUCTOR AMPACITY ****//////
				// A.B 03-15 CR-2555-MOD-001 separate System String Inverter & System Optimizer
				if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer")
						&& !Boolean.FALSE.equals(dcOptimizer.getAltersVoltage())) {
					params = requConductorAmpacity.mapSystemOptimizerRequiredAmpacity(i, form, iScRef, iScOptimizer,
							plansetUtils.getMaxNumberOfStrings(), originCombiner, sheetIndex, params);
				} else {
					params = requConductorAmpacity.mapRequiredAmpacity(i, form, iScRef, moduleInfo,
							plansetUtils.getMaxNumberOfStrings(), originCombiner, sheetIndex, params);
				}

				///// **** CONDUCTOR TEMPERATURE DERATING ****////
				// 02/18/2019 A.B: CR-2313 MOD-005-1
				params = conductorTempDerating.mapTemperatureDerating(i, form, dcList.get(i - 1),
						fourPerCentAverageHigh, dcCircuitEnvironment, sheetIndex, userSetting, state, params, necCode,
						showConduitRoofAsHeight);

				///// **** CONDUIT FILL DERATING ****////
				int x = permitEntity.getRRVersion();

				if (checkValue.isNumeric(params.getRequiredConductorAmpacity())) {
					params.setRequiredAmpacity(Double.parseDouble(params.getRequiredConductorAmpacity()));
				}
				if ((userConnectedEntity.getRoleEntity().getId() == 2 && x < 2)
						|| (userConnectedEntity.getRoleEntity().getId() != 2
								&& (!checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {

					params = conduitFillDerating.mapFillDeratingbeforeRevision(stepOptimizer, form, i, dcList,
							plansetUtils.getMaxNumberOfStrings(), plansetUtils.getInverterQty(), originCombiner,
							dcTradeSize, dcNumberOfConductors, 1, sheetIndex, params);

					// 24/06/2019 CI : CR 2742 MOD-ADVP-2742-001: Update Conductor Size Calculation
					// Procedure :1
					if (params.getCalculatedNumberOfConductor() == 2 || params.getCalculatedNumberOfConductor() == 3) {
						params.setNec31016Column90(
								params.getRequiredAmpacity() / (Float.parseFloat(params.getAmpacityCorrection())
										* Float.parseFloat(params.getDcampacityCorrectionB3a())));
						Integer nec310Col90 = params.getNec31016Column90() != null
								? (int) Math.round(params.getNec31016Column90())
								: 0;
						if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
								&& !params.getIncorrectTradeSize().isEmpty()) {
							params.setNEC310(nec3106B16Repo.findFirstByNinetyInsulationGreaterThanAndTradeSzeIsNotIn(
									nec310Col90, params.getIncorrectTradeSize()));
						} else {
							params.setNEC310(nec3106B16Repo.findFirstByNinetyInsulationGreaterThan(nec310Col90));
						}

						if (params.getNEC310() != null) {
							dcNumberOfConductors.remove(dcNumberOfConductors.size() - 1);
							dcTradeSize.remove(dcTradeSize.size() - 1);
							params = conduitFillDerating.mapFillDeratingbeforeRevision(stepOptimizer, form, i, dcList,
									plansetUtils.getMaxNumberOfStrings(), plansetUtils.getInverterQty(), originCombiner,
									dcTradeSize, dcNumberOfConductors, params.getCalculatedNumberOfConductor(),
									sheetIndex, params);
						}
					}

					// 25/06/2019 CI : CR 2742 MOD-ADVP-2742-001: Update Conductor Size Calculation
					// Procedure :2
					if (params.getNEC310() != null && checkValue.notContains(dcList.get(i - 1).getSourceID(), "MOD")
							&& checkValue.notContains(dcList.get(i - 1).getSourceID(), "OPT")
							&& checkValue.NotEquals(params.getCorrectedAmpacity(), "")
							&& checkValue.getFloatValue(params.getCorrectedAmpacity()) < checkValue
									.getFloatValue(params.getRequiredConductorAmpacity())) {
						params.setIncorrectTradeSizeLogic(true);
						do {
							params.setNec31016Column90(
									params.getRequiredAmpacity() / (Float.parseFloat(params.getAmpacityCorrection())
											* Float.parseFloat(params.getDcampacityCorrectionB3a())));
							Integer nec310Col90 = params.getNec31016Column90() != null
									? (int) Math.round(params.getNec31016Column90())
									: 0;

							if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
									&& !params.getIncorrectTradeSize().isEmpty()) {
								params.setNEC310(
										nec3106B16Repo.findFirstByNinetyInsulationGreaterThanAndTradeSzeIsNotIn(
												nec310Col90, params.getIncorrectTradeSize()));
							} else {
								params.setNEC310(nec3106B16Repo.findFirstByNinetyInsulationGreaterThan(nec310Col90));
							}

							if (params.getNEC310() != null) {
								dcNumberOfConductors.remove(dcNumberOfConductors.size() - 1);
								dcTradeSize.remove(dcTradeSize.size() - 1);
								params = conduitFillDerating.mapFillDeratingbeforeRevision(stepOptimizer, form, i,
										dcList, plansetUtils.getMaxNumberOfStrings(), plansetUtils.getInverterQty(),
										originCombiner, dcTradeSize, dcNumberOfConductors,
										params.getCalculatedNumberOfConductor(), sheetIndex, params);

							}

						} while (params.getNEC310() != null && checkValue.NotEquals(params.getCorrectedAmpacity(), "")
								&& checkValue.getFloatValue(params.getCorrectedAmpacity()) < checkValue
										.getFloatValue(params.getRequiredConductorAmpacity()));
					}

					if (checkValue.isFloatPositive(dcList.get(i - 1).getCircuitSpec().getCircuitLength())
							&& (i != 1 || (i == 1 && checkValue
									.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Neither")))) {

						Float vd = voltageDropCalculation.dcVoltageDropBatterySystem(i,
								dcList.get(i - 1).getCircuitSpec().getCircuitLength(), dcOptimizer, moduleInfo,
								inverterInfo, iScOptimizer, plansetUtils.getMaxNumberOfStrings(), originCombiner,
								plansetUtils, dcTradeSize.get(dcTradeSize.size() - 1), plansetUtils.getModuleQty());

						if (vd != 0.0 && vd >= 1.99) {
							Boolean sameTradeSize = false;
							String tradeSize = dcTradeSize.get(dcTradeSize.size() - 1);
							do {

								String nextTradeSize = nec310Values.getNextTradeSize(tradeSize);
								sameTradeSize = checkValue.Equals(nextTradeSize, tradeSize);
								if (Boolean.FALSE.equals(sameTradeSize)) {
									params.setNEC310(nec3106B16Repo.findFirstBytradeSzeAndNumberOfConductors(
											nextTradeSize, dcNumberOfConductors.get(dcNumberOfConductors.size() - 1)));
									if (params.getNEC310() != null) {
										tradeSize = nextTradeSize;
										dcNumberOfConductors.remove(dcNumberOfConductors.size() - 1);
										dcTradeSize.remove(dcTradeSize.size() - 1);
										params = conduitFillDerating.mapFillDeratingVoltageDrop(form, i, dcTradeSize,
												dcNumberOfConductors, sheetIndex, params);
										vd = voltageDropCalculation.dcVoltageDropBatterySystem(i,
												dcList.get(i - 1).getCircuitSpec().getCircuitLength(), dcOptimizer,
												moduleInfo, inverterInfo, iScOptimizer,
												plansetUtils.getMaxNumberOfStrings(), originCombiner, plansetUtils,
												params.getNEC310().getTradeSze(), plansetUtils.getModuleQty());
									}

								}

							} while (vd != 0.0 && vd >= 1.99 && params.getNEC310() != null
									&& Boolean.FALSE.equals(sameTradeSize));
						}
						if (vd != 0.0) {
							VoltageDropTable v = new VoltageDropTable();
							v.setCircuitOrigin(circuitOrigin);
							v.setCircuitDestination(circuitDestination);
							v.setAcDc("DC");
							v.setVd(vd);
							params.getVoltageDropTable().add(v);
						}

					}

				} else if ((userConnectedEntity.getRoleEntity().getId() == 2 && x >= 2)
						|| (userConnectedEntity.getRoleEntity().getId() != 2
								&& Boolean.TRUE.equals(circuit.getMapFromUserInput()))) {
					params = conduitFillDerating.mapFillDeratingafterRevision(form, i,
							dcList.get(i - 1).getCircuitSpec(), dcTradeSize, dcNumberOfConductors, sheetIndex, params);
				}

				if (i == 1 || (Boolean.TRUE.equals(stepOptimizer) && i == 2)) {

					if (checkValue.isNumeric(params.getTempAdder())
							&& checkValue.isNumeric(params.getRequiredConductorAmpacity())
							&& checkValue.isNumeric(params.getCorrectedAmpacity())) {

						Float requiredAmpacityFloat = Float.valueOf(params.getRequiredConductorAmpacity());
						Float correctedAmpacityFloat = Float.valueOf(params.getCorrectedAmpacity());
						Float tempAdderFloat = Float.valueOf(params.getTempAdder());

						if (tempAdderFloat > 0 && correctedAmpacityFloat < requiredAmpacityFloat) {
							conductorTempDerating.remapTemperatureDerating(i, form, fourPerCentAverageHigh,
									systemEnvironment, dcCircuitEnvironment, sheetIndex, userSetting, state, params,
									necCode, showConduitRoofAsHeight);

							if ((userConnectedEntity.getRoleEntity().getId() == 2 && x < 2)
									|| (userConnectedEntity.getRoleEntity().getId() != 2
											&& (!checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {
								params = conduitFillDerating.mapFillDeratingbeforeRevision(stepOptimizer, form, i,
										dcList, plansetUtils.getMaxNumberOfStrings(), plansetUtils.getInverterQty(),
										originCombiner, dcTradeSize, dcNumberOfConductors, 1, sheetIndex, params);
								// 24/06/2019 CI : CR 2742 MOD-ADVP-2742-001: Update Conductor Size Calculation
								// Procedure :1 before revision
								if (params.getCalculatedNumberOfConductor() == 2
										|| params.getCalculatedNumberOfConductor() == 3) {
									params.setNec31016Column90(params.getRequiredAmpacity()
											/ (Float.parseFloat(params.getAmpacityCorrection())
													* Float.parseFloat(params.getDcampacityCorrectionB3a())));
									Integer nec310Col90 = params.getNec31016Column90() != null
											? (int) Math.round(params.getNec31016Column90())
											: 0;
									if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
											&& !params.getIncorrectTradeSize().isEmpty()) {
										params.setNEC310(
												nec3106B16Repo.findFirstByNinetyInsulationGreaterThanAndTradeSzeIsNotIn(
														nec310Col90, params.getIncorrectTradeSize()));
									} else {
										params.setNEC310(
												nec3106B16Repo.findFirstByNinetyInsulationGreaterThan(nec310Col90));
									}

									if (params.getNEC310() != null) {
										dcNumberOfConductors.remove(dcNumberOfConductors.size() - 1);
										dcTradeSize.remove(dcTradeSize.size() - 1);
										params = conduitFillDerating.mapFillDeratingbeforeRevision(stepOptimizer, form,
												i, dcList, plansetUtils.getMaxNumberOfStrings(),
												plansetUtils.getInverterQty(), originCombiner, dcTradeSize,
												dcNumberOfConductors, params.getCalculatedNumberOfConductor(),
												sheetIndex, params);
									}
								}
							} else if ((userConnectedEntity.getRoleEntity().getId() == 2 && x >= 2)
									|| (userConnectedEntity.getRoleEntity().getId() != 2
											&& Boolean.TRUE.equals(circuit.getMapFromUserInput()))) {
								params = conduitFillDerating.mapFillDeratingafterRevision(form, i,
										dcList.get(i - 1).getCircuitSpec(), dcTradeSize, dcNumberOfConductors,
										sheetIndex, params);
							}

						}

					}

				} else if ((userConnectedEntity.getRoleEntity().getId() == 2 && x >= 2)
						|| (userConnectedEntity.getRoleEntity().getId() != 2
								&& Boolean.TRUE.equals(circuit.getMapFromUserInput()))) {

					if ((checkValue.contains(dcList.get(i - 1).getSourceID(), "DCJBOX")
							&& checkValue.Equals(dcList.get(i - 1).getCircuitSpec().getConductorType(), "PV Wire"))
							|| (checkValue.contains(dcList.get(i - 1).getSourceID().split("-")[i - 1], "DCC")
									&& checkValue.Equals(dcList.get(i - 1).getCircuitSpec().getConductorType(),
											"PV Wire"))
							|| (checkValue.contains(dcList.get(i - 1).getSourceID().split("-")[i - 1], "DCD")
									&& checkValue.Equals(dcList.get(i - 1).getCircuitSpec().getConductorType(),
											"PV Wire"))) {

						if (checkValue.isNumeric(params.getTempAdder())
								&& checkValue.isNumeric(params.getRequiredConductorAmpacity())
								&& checkValue.isNumeric(params.getCorrectedAmpacity())) {

							Float requiredAmpacityFloat = Float.valueOf(params.getRequiredConductorAmpacity());
							Float correctedAmpacityFloat = Float.valueOf(params.getCorrectedAmpacity());
							Float tempAdderFloat = Float.valueOf(params.getTempAdder());

							if (tempAdderFloat > 0 && correctedAmpacityFloat < requiredAmpacityFloat) {

								// 02/18/2019 A.B: CR-2313 MOD-005-1
								params = conductorTempDerating.remapTemperatureDerating(i, form, fourPerCentAverageHigh,
										systemEnvironment, dcCircuitEnvironment, sheetIndex, userSetting, state, params,
										necCode, showConduitRoofAsHeight);

								params = conduitFillDerating.mapFillDeratingafterRevision(form, i,
										dcList.get(i - 1).getCircuitSpec(), dcTradeSize, dcNumberOfConductors,
										sheetIndex, params);
							}

						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	private Boolean existingCircuit(ESSConnectors ac, PermitEntity permitEntity) {
		try {
			switch (ac.getSourceID().split("-")[1]) {
			case "MOD":
				return checkValue.Equals(permitEntity.getExistModule(), true);
			case "ACJBOX":
				return checkValue.Equals(permitEntity.getExistdcJunctionBox(), true);
			default:
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return false;
		}
	}

	private Boolean existingRow(int i, AcroFields form, int sheetIndex, List<Integer> dcNumberOfConductors,
			List<String> dcTradeSize, List<String> dcCircuitEnvironment) {
		try {
			defaultRowMapping.mapDcDefaultValue(i, form, sheetIndex, "EXISTING");
			dcNumberOfConductors.add(1);
			dcTradeSize.add("");
			dcCircuitEnvironment.add("EXISTING");
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return true;
	}
}
