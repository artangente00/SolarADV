package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.model.planset_utils.VoltageDropTable;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.NEC310Values;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.VoltageDropCalculation;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class AcCircuitMapping {

	final static String EXISTING_SUB_PANEL = "Show the word “Existing” instead of a conductor size (Some AHJs may not approve the permit with this wording)";

	final GetPVModuleData getPVModuleData;
	final DefaultRowMapping defaultRowMapping;
	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final OriginDestinationMapping originDestMapping;
	final RequiredConductorAmpacity requiredConductorAmpacity;
	final ConductorTemperatureDerating conductorTemperatureDerating;
	final NEC3106B16Repository nec3106B16Repo;
	final NecBchart necBchartService;
	final ConduitFillDeratingBeforeRev conduitFillDeratingBeforeRev;
	final ConduitFillDeratingAfterRev conduitFillDeratingAfterRev;
	final VoltageDropCalculation voltageDropCalculation;
	final NEC310Values nec310Values;

	public AcCircuitMapping(GetPVModuleData getPVModuleData, DefaultRowMapping defaultRowMapping,
			CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			OriginDestinationMapping originDestMapping, RequiredConductorAmpacity requiredConductorAmpacity,
			ConductorTemperatureDerating conductorTemperatureDerating, NEC3106B16Repository nec3106b16Repo,
			NecBchart necBchartService, ConduitFillDeratingBeforeRev conduitFillDeratingBeforeRev,
			ConduitFillDeratingAfterRev conduitFillDeratingAfterRev,VoltageDropCalculation voltageDropCalculation,NEC310Values nec310Values) {
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
	}


	public void mapACCircuits(int i, AcroFields form, int indexAcCombiner, Boolean stepACCombiner, String sumIacMax,
			String largestIacMax, String acCircuit, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Integer fourPerCentAverageHigh, List<String> acCircuitEnvironment,
			int acCircuitLength, PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity,
			List<String> acTradeSize, List<Integer> acNumberOfConductors,
			AuthentificationEntity userConnectedEntity, int sheetIndex, PermitHomeSiteInfoEntity permitHomeSite,
			SystemEnvironment systemEnvironment, E002Model params, String necCode, UserSettingEntity userSetting,
			Boolean showConduitRoofAsHeight) {

		try {
			Boolean existSubPanel = checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(),
					EXISTING_SUB_PANEL);
			String combiningCircuit = permitProjectSiteInfo != null ? permitProjectSiteInfo.getCombiningACCircuits() : "";
			originDestMapping.orgDestMapping(i, form, acCircuit, sheetIndex, acCircuitLength,
					permitProjectSiteInfo.getMainPanelUpgrade(), true,combiningCircuit,"");
			Boolean exist = isExistingComponent(i, form, sheetIndex, acCircuit, circuit, acNumberOfConductors,
					acTradeSize, acCircuitEnvironment, permitEntity, existSubPanel);
			systemEnvironment.setInvOpTemp(String.valueOf(fourPerCentAverageHigh));
			if (Boolean.FALSE.equals(exist)) {

				///// **** CONDUCTOR SPECIFICATIONS ****//////
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL", "CU");
				form.setField(sheetIndex + "-" + "AC" + i + "-TEMPERATURE", "75");

				///// **** RREQUIRED CONDUCTOR AMPACITY ****//////
				params = requiredConductorAmpacity.mapACrequiredAmpacity(form, i, indexAcCombiner,
						stepACCombiner, sumIacMax, largestIacMax, sheetIndex, permitProjectSiteInfo, acCircuit,
						params);

				//// **** CONDUCTOR TEMPERATURE DERATING ****//////
				params = conductorTemperatureDerating.mapCondTempDerating(form, i, acCircuit,
						fourPerCentAverageHigh, acCircuitEnvironment, sheetIndex, permitHomeSite.getState(),params, circuit, necCode,
						userSetting, showConduitRoofAsHeight);

				int x = permitEntity.getRRVersion();

				if ((userConnectedEntity.getRoleEntity().getId() == 2 && x < 2)
						|| (userConnectedEntity.getRoleEntity().getId() != 2
								&& (!checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {
					params = conduitFillDeratingBeforeRev.mapACExistingbeforeRevision(form, i, acCircuit,
							circuit, permitEntity, acTradeSize, acNumberOfConductors, sheetIndex, permitProjectSiteInfo,
							1, permitHomeSite, systemEnvironment.getInvOpTemp(),params);

					if (params.getCalculatedACNumberOfConductor() == 2 || params.getCalculatedACNumberOfConductor() == 3) {
						params.setNec31016Column90(params.getRequiredAmpacity()
								/ (Float.parseFloat(getACAmpacityCorrectionB2aMultiple(systemEnvironment.getInvOpTemp()))
										* Float.parseFloat(params.getConduitFillDerating())));

						params.setNEC310(necBchartService.getNecBchart(params));

					}
					// 25/06/2019 CI : CR 2742 MOD-ADVP-2742-001: Update Conductor Size Calculation
					// Procedure :2

					if (params.getNEC310() != null && checkValue.getFloatValue(params.getCorrectedACAmpacity()) < checkValue
							.getFloatValue(params.getRequiredACConductorAmpacity())) {
						params.setTradeSizeRepeate("");
						do {

							params.setIncorrectACTradeSizeLogic(true);
							params.setNec31016Column90( params.getRequiredAmpacity()
									/ (Float.parseFloat(getACAmpacityCorrectionB2aMultiple(systemEnvironment.getInvOpTemp()))
											* Float.parseFloat(params.getConduitFillDerating())));
							params.setNEC310(necBchartService.getNecBchart(params));

							if (params.getNEC310() != null && checkValue.NotEquals(params.getTradeSizeRepeate(), params.getNEC310().getTradeSze())) {

								params.setTradeSizeRepeate(params.getNEC310().getTradeSze());
								acNumberOfConductors.remove(acNumberOfConductors.size() - 1);
								acTradeSize.remove(acTradeSize.size() - 1);
								params = conduitFillDeratingBeforeRev.mapACExistingbeforeRevision(form, i,
										acCircuit, circuit, permitEntity, acTradeSize, acNumberOfConductors, sheetIndex,
										permitProjectSiteInfo, params.getCalculatedACNumberOfConductor(), permitHomeSite, systemEnvironment.getInvOpTemp(), params);
							}
				
						} while (params.getNEC310() != null && checkValue.NotEquals(params.getTradeSizeRepeate(), params.getNEC310().getTradeSze())
								&& checkValue.NotEquals(params.getCorrectedACAmpacity(), "")
								&& checkValue.getFloatValue(params.getCorrectedACAmpacity()) < checkValue
										.getFloatValue(params.getRequiredACConductorAmpacity()));
					}

					
					String maxInvOutputCurrent = form.getField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT");
					Float vd = voltageDropCalculation.acVoltageDrop(i, acCircuit, circuit,
							acTradeSize.get(acTradeSize.size() - 1), acNumberOfConductors.get(acNumberOfConductors.size() - 1), 
							maxInvOutputCurrent, permitHomeSite.getIfServiceVoltage(), permitHomeSite.getServiceVoltage());
					
					if (vd != 0.0 && vd >= 1.99) {
						Boolean sameTradeSize = false;
						String tradeSize = acTradeSize.get(acTradeSize.size() - 1);
						do {
							
							String nextTradeSize = nec310Values.getNextTradeSize(tradeSize);
							sameTradeSize = checkValue.Equals(nextTradeSize, tradeSize);
							if (Boolean.FALSE.equals(sameTradeSize)) {
								params.setNEC310(nec3106B16Repo.findFirstBytradeSzeAndNumberOfConductors(nextTradeSize, acNumberOfConductors.get(acNumberOfConductors.size() - 1)));
								if (params.getNEC310() != null) {
									tradeSize = nextTradeSize;
									params.setTradeSizeRepeate(params.getNEC310().getTradeSze());
									acNumberOfConductors.remove(acNumberOfConductors.size() - 1);
									acTradeSize.remove(acTradeSize.size() - 1);
									params = conduitFillDeratingBeforeRev.mapACVoltageDrop(form, i,
											acCircuit, circuit, permitEntity, acTradeSize, acNumberOfConductors, sheetIndex,
											permitProjectSiteInfo, params.getCalculatedACNumberOfConductor(), permitHomeSite, systemEnvironment.getInvOpTemp(), params);
									maxInvOutputCurrent = form.getField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT");
									vd = voltageDropCalculation.acVoltageDrop(i, acCircuit, circuit,
											acTradeSize.get(acTradeSize.size() - 1), acNumberOfConductors.get(acNumberOfConductors.size() - 1), 
											maxInvOutputCurrent, permitHomeSite.getIfServiceVoltage(), permitHomeSite.getServiceVoltage());
								}
								
							}
							
							
						} while (vd != 0.0 && vd >= 1.99 && params.getNEC310() != null
								&& Boolean.FALSE.equals(sameTradeSize));
					} 
					if (vd != 0.0) {
						VoltageDropTable v = new VoltageDropTable();
						//Origin
						if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")) {
							v.setCircuitOrigin("SUB-PANEL");
						} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")) {
							v.setCircuitOrigin("AC DISCONNECT TWO");//A.B 07/21/2022 PR-045
						} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {
							v.setCircuitOrigin("SOLAR LOAD CENTER");
						} else {
							v.setCircuitOrigin(acCircuit.split("-")[i - 1]);
						}
						if (i == acCircuitLength) {
							if (Boolean.TRUE.equals(permitProjectSiteInfo.getMainPanelUpgrade())) {
								v.setCircuitDestination("NEW SERVICE PANEL");
							} else {
								v.setCircuitDestination("EXISTING SERVICE PANEL");
							}
							
						} else {
							if (checkValue.Equals(acCircuit.split("-")[i], "SUB PANEL")) {
								v.setCircuitDestination("SUB-PANEL");
							} else if (checkValue.Equals(acCircuit.split("-")[i], "AC DISCONNECTTwo")) {
								v.setCircuitDestination("AC DISCONNECT TWO");//A.B 07/21/2022 PR-045
							} else if (checkValue.Equals(acCircuit.split("-")[i], "AC COMBINER/LOAD CENTER")) {
								v.setCircuitDestination("SOLAR LOAD CENTER");
							} else {
								v.setCircuitDestination(acCircuit.split("-")[i]);
							}
						}
						v.setAcDc("AC");
						v.setVd(vd);
						params.getVoltageDropTable().add(v);
					}
					
				
				} else if ((userConnectedEntity.getRoleEntity().getId() == 2 && x >= 2)
						|| (userConnectedEntity.getRoleEntity().getId() != 2
								&& checkValue.Equals(circuit.getMapFromUserInput(), true))) {
					conduitFillDeratingAfterRev.mapACexistingafterRevision(form, i, acCircuit, circuit, permitEntity, acTradeSize,
							acNumberOfConductors, sheetIndex, permitProjectSiteInfo, params);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}

	}


	private Boolean isExistingComponent(int i, AcroFields form, int sheetIndex, String acCircuit,
			PermitConduitConductorSectionEntity circuit, List<Integer> acNumberOfConductors, List<String> acTradeSize,
			List<String> acCircuitEnvironment, PermitEntity permitEntity, Boolean existSubPanel) {
		try {
			String component = acCircuit.split("-")[i - 1];
			if (checkValue.Equals(component, "INVERTER")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeSixExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistInverter())))) {
				return existingRow(i, form, sheetIndex, acNumberOfConductors, acTradeSize, acCircuitEnvironment);
			} else if (checkValue.Equals(component, "JUNCTION BOX")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeSevenExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistAcJunctionBox())))) {
				return existingRow(i, form, sheetIndex, acNumberOfConductors, acTradeSize, acCircuitEnvironment);
			} else if (checkValue.Equals(component, "AC COMBINER/LOAD CENTER")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeEightExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistAcCombiner())))) {
				return existingRow(i, form, sheetIndex, acNumberOfConductors, acTradeSize, acCircuitEnvironment);
			} else if (checkValue.Equals(component, "AC DISCONNECT")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeNineExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistAcDisconnect())))) {
				return existingRow(i, form, sheetIndex, acNumberOfConductors, acTradeSize, acCircuitEnvironment);
			} else if (checkValue.Equals(component, "AC DISCONNECTTwo")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeNineTwoExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistAcDisconnect())))) {
				return existingRow(i, form, sheetIndex, acNumberOfConductors, acTradeSize, acCircuitEnvironment);
			} else if (checkValue.Equals(component, "PRODUCTION METER")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeTenExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistProductionMeter())))) {
				return existingRow(i, form, sheetIndex, acNumberOfConductors, acTradeSize, acCircuitEnvironment);
			} else if (checkValue.Equals(component, "SUB PANEL")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeElevenExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistSubPanel()))
							|| Boolean.TRUE.equals(existSubPanel))) {
				return existingRow(i, form, sheetIndex, acNumberOfConductors, acTradeSize, acCircuitEnvironment);
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return false;
		}
	}

	private Boolean existingRow(int i, AcroFields form, int sheetIndex, List<Integer> acNumberOfConductors,
			List<String> acTradeSize, List<String> acCircuitEnvironment) {
		try {
			defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
			acNumberOfConductors.add(1);
			acTradeSize.add("EXISTING");
			acCircuitEnvironment.add("EXISTING");
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return true;
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
