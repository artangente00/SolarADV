package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.ac_circuit;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.model.planset_utils.VoltageDropTable;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.NecBchart;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.OriginDestinationMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.NEC310Values;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.VoltageDropCalculation;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACCircuitMapping {

	final static String EXISTING_SUB_PANEL = "Show the word “Existing” instead of a conductor size (Some AHJs may not approve the permit with this wording)";

	final GetPVModuleData getPVModuleData;
	final DefaultRowMapping defaultRowMapping;
	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final OriginDestinationMapping originDestMapping;
	final ACRequiredConductorAmpacity requiredConductorAmpacity;
	final ConductorTempDerating conductorTemperatureDerating;
	final NEC3106B16Repository nec3106B16Repo;
	final NecBchart necBchartService;
	final ConduitFillDeratingBeforeRevion conduitFillDeratingBeforeRev;
	final ConduitFillDeratingAfterRevion conduitFillDeratingAfterRev;
	final VoltageDropCalculation voltageDropCalculation;
	final NEC310Values nec310Values;
	final GenerateCircuitList generateCircuitList;

	public ACCircuitMapping(GetPVModuleData getPVModuleData, DefaultRowMapping defaultRowMapping,
			CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			OriginDestinationMapping originDestMapping, ACRequiredConductorAmpacity requiredConductorAmpacity,
			ConductorTempDerating conductorTemperatureDerating, NEC3106B16Repository nec3106b16Repo,
			NecBchart necBchartService, ConduitFillDeratingBeforeRevion conduitFillDeratingBeforeRev,
			ConduitFillDeratingAfterRevion conduitFillDeratingAfterRev, VoltageDropCalculation voltageDropCalculation,
			NEC310Values nec310Values, GenerateCircuitList generateCircuitList) {
		super();
		this.getPVModuleData = getPVModuleData;
		this.defaultRowMapping = defaultRowMapping;
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		this.originDestMapping = originDestMapping;
		this.requiredConductorAmpacity = requiredConductorAmpacity;
		this.conductorTemperatureDerating = conductorTemperatureDerating;
		nec3106B16Repo = nec3106b16Repo;
		this.necBchartService = necBchartService;
		this.conduitFillDeratingBeforeRev = conduitFillDeratingBeforeRev;
		this.conduitFillDeratingAfterRev = conduitFillDeratingAfterRev;
		this.voltageDropCalculation = voltageDropCalculation;
		this.nec310Values = nec310Values;
		this.generateCircuitList = generateCircuitList;
	}

	public void mapACCircuits(int i, AcroFields form, int indexAcCombiner, Boolean stepACCombiner, String sumIacMax,
			String largestIacMax, List<ESSConnectors> acList, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Integer fourPerCentAverageHigh, List<String> acCircuitEnvironment,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, AuthentificationEntity userConnectedEntity, int sheetIndex,
			PermitHomeSiteInfoEntity permitHomeSite, SystemEnvironment systemEnvironment, E002Model params,
			String necCode, UserSettingEntity userSetting, Boolean showConduitRoofAsHeight) {

		try {

			// A.B Circuit Origin & Destination
			String circuitOrigin = generateCircuitList.getEquipmentName(acList.get(i - 1).getSourceID(),
					permitProjectSiteInfo.getMainPanelUpgrade(), permitEntity.getId(),
					i > 1 ? acList.get(i - 2).getSourceID() : "");
			String circuitDestination = generateCircuitList.getEquipmentName(acList.get(i - 1).getTargetID(),
					permitProjectSiteInfo.getMainPanelUpgrade(), permitEntity.getId(), acList.get(i - 1).getSourceID());
			form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-ORGIN", circuitOrigin);
			form.setField(sheetIndex + "-AC" + i + "-CIRCUIT-DESTINATION", circuitDestination);

			Boolean exist = Boolean.TRUE.equals(acList.get(i - 1).getCircuitSpec().getExisting())
					|| Boolean.TRUE.equals(generateCircuitList.existingCircuit(acList.get(i - 1), permitEntity,
							permitProjectSiteInfo));
			
			systemEnvironment.setInvOpTemp(String.valueOf(fourPerCentAverageHigh));
			
			if (Boolean.FALSE.equals(exist)) {

				///// **** CONDUCTOR SPECIFICATIONS ****//////
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL", "CU");
				form.setField(sheetIndex + "-" + "AC" + i + "-TEMPERATURE", "75");

				///// **** RREQUIRED CONDUCTOR AMPACITY ****//////
				params = requiredConductorAmpacity.mapACrequiredAmpacity(form, i, indexAcCombiner, stepACCombiner,
						sumIacMax, largestIacMax, sheetIndex, permitProjectSiteInfo, acList.get(i - 1), params);

				//// **** CONDUCTOR TEMPERATURE DERATING ****//////
				params = conductorTemperatureDerating.mapCondTempDerating(form, i, acList.get(i - 1),
						fourPerCentAverageHigh, acCircuitEnvironment, sheetIndex, permitHomeSite.getState(), params,
						necCode, userSetting, showConduitRoofAsHeight);

				int x = permitEntity.getRRVersion();

				if ((userConnectedEntity.getRoleEntity().getId() == 2 && x < 2)
						|| (userConnectedEntity.getRoleEntity().getId() != 2
								&& (!checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {
					params = conduitFillDeratingBeforeRev.mapACExistingbeforeRevision(form, i, acList.get(i - 1),
							acTradeSize, acNumberOfConductors, sheetIndex, permitProjectSiteInfo, 1, permitHomeSite,
							systemEnvironment.getInvOpTemp(), params);

					if (params.getCalculatedACNumberOfConductor() == 2
							|| params.getCalculatedACNumberOfConductor() == 3) {
						params.setNec31016Column90(params.getRequiredAmpacity() / (Float
								.parseFloat(getACAmpacityCorrectionB2aMultiple(systemEnvironment.getInvOpTemp()))
								* Float.parseFloat(params.getConduitFillDerating())));

						params.setNEC310(necBchartService.getNecBchart(params));

					}
					// 25/06/2019 CI : CR 2742 MOD-ADVP-2742-001: Update Conductor Size Calculation
					// Procedure :2

					if (params.getNEC310() != null
							&& checkValue.getFloatValue(params.getCorrectedACAmpacity()) < checkValue
									.getFloatValue(params.getRequiredACConductorAmpacity())) {
						params.setTradeSizeRepeate("");
						do {

							params.setIncorrectACTradeSizeLogic(true);
							params.setNec31016Column90(params.getRequiredAmpacity() / (Float
									.parseFloat(getACAmpacityCorrectionB2aMultiple(systemEnvironment.getInvOpTemp()))
									* Float.parseFloat(params.getConduitFillDerating())));
							params.setNEC310(necBchartService.getNecBchart(params));

							if (params.getNEC310() != null && checkValue.NotEquals(params.getTradeSizeRepeate(),
									params.getNEC310().getTradeSze())) {

								params.setTradeSizeRepeate(params.getNEC310().getTradeSze());
								acNumberOfConductors.remove(acNumberOfConductors.size() - 1);
								acTradeSize.remove(acTradeSize.size() - 1);
								params = conduitFillDeratingBeforeRev.mapACExistingbeforeRevision(form, i,
										acList.get(i - 1), acTradeSize, acNumberOfConductors, sheetIndex,
										permitProjectSiteInfo, params.getCalculatedACNumberOfConductor(),
										permitHomeSite, systemEnvironment.getInvOpTemp(), params);
							}

						} while (params.getNEC310() != null
								&& checkValue.NotEquals(params.getTradeSizeRepeate(), params.getNEC310().getTradeSze())
								&& checkValue.NotEquals(params.getCorrectedACAmpacity(), "")
								&& checkValue.getFloatValue(params.getCorrectedACAmpacity()) < checkValue
										.getFloatValue(params.getRequiredACConductorAmpacity()));
					}
					if (checkValue.isFloatPositive(acList.get(i - 1).getCircuitSpec().getCircuitLength())) {
						String maxInvOutputCurrent = form
								.getField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT");
						Float vd = voltageDropCalculation.acVoltageDropBatterySystem(
								acTradeSize.get(acTradeSize.size() - 1),
								acNumberOfConductors.get(acNumberOfConductors.size() - 1), maxInvOutputCurrent,
								permitHomeSite.getIfServiceVoltage(), permitHomeSite.getServiceVoltage(),
								acList.get(i - 1).getCircuitSpec().getCircuitLength());

						if (vd != 0.0 && vd >= 1.99) {
							Boolean sameTradeSize = false;
							String tradeSize = acTradeSize.get(acTradeSize.size() - 1);
							do {

								String nextTradeSize = nec310Values.getNextTradeSize(tradeSize);
								sameTradeSize = checkValue.Equals(nextTradeSize, tradeSize);
								if (Boolean.FALSE.equals(sameTradeSize)) {
									params.setNEC310(nec3106B16Repo.findFirstBytradeSzeAndNumberOfConductors(
											nextTradeSize, acNumberOfConductors.get(acNumberOfConductors.size() - 1)));
									if (params.getNEC310() != null) {
										tradeSize = nextTradeSize;
										params.setTradeSizeRepeate(params.getNEC310().getTradeSze());
										acNumberOfConductors.remove(acNumberOfConductors.size() - 1);
										acTradeSize.remove(acTradeSize.size() - 1);
										params = conduitFillDeratingBeforeRev.mapACVoltageDrop(form, i,
												acList.get(i - 1), acTradeSize, acNumberOfConductors, sheetIndex,
												permitProjectSiteInfo, params.getCalculatedACNumberOfConductor(),
												permitHomeSite, systemEnvironment.getInvOpTemp(), params);
										maxInvOutputCurrent = form
												.getField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT");
										vd = voltageDropCalculation.acVoltageDropBatterySystem(
												acTradeSize.get(acTradeSize.size() - 1),
												acNumberOfConductors.get(acNumberOfConductors.size() - 1),
												maxInvOutputCurrent, permitHomeSite.getIfServiceVoltage(),
												permitHomeSite.getServiceVoltage(),
												acList.get(i - 1).getCircuitSpec().getCircuitLength());
									}

								}

							} while (vd != 0.0 && vd >= 1.99 && params.getNEC310() != null
									&& Boolean.FALSE.equals(sameTradeSize));
						}
						if (vd != 0.0) {
							VoltageDropTable v = new VoltageDropTable();
							// Origin - Destination
							v.setCircuitOrigin(circuitOrigin);
							v.setCircuitDestination(circuitDestination);
							v.setAcDc("AC");
							v.setVd(vd);
							params.getVoltageDropTable().add(v);
						}
					}

				} else if ((userConnectedEntity.getRoleEntity().getId() == 2 && x >= 2)
						|| (userConnectedEntity.getRoleEntity().getId() != 2
								&& checkValue.Equals(circuit.getMapFromUserInput(), true))) {
					conduitFillDeratingAfterRev.mapACexistingafterRevision(form, i, acList.get(i - 1), acTradeSize,
							acNumberOfConductors, sheetIndex, permitProjectSiteInfo, params);
				}

			}else {
				defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
				acNumberOfConductors.add(1);
				acTradeSize.add("EXISTING");
				acCircuitEnvironment.add("EXISTING");
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
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
