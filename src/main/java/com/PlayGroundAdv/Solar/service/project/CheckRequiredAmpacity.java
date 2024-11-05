package com.PlayGroundAdv.Solar.service.project;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.NEC_310_16_B_16;
import com.PlayGroundAdv.Solar.entity.PermitArraysEntity;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitWeatherEntity;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitConduitConductorSectionRepository;
import com.PlayGroundAdv.Solar.repositories.PermitLayoutRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitWeatherRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class CheckRequiredAmpacity {

	String tempDerating = "";
	String systemCircuitEnvironment = "";
	String systemHighAboveRoof = "";
	String systemAempAdder = "";
	String systemOpTemp = "";
	String circuitEnvironment = "";
	String highAboveRoof = "";
	String tempAdder = "";
	String opTemp = "";
	String invOpTemp = "";
	Integer adder = 0;
	boolean isRoofMounted = false;
	boolean isThereGroundOrPole = false;

	private static final String UNDERGROUND = "UNDERGROUND";
	private static final String EXT_SHADE = "EXT SHADE";
	private static final String EXT_WALL = "EXT WALL";
	private static final String MOUNT_ARRAY = "Mount Array";

	final CheckValueTypesService checkValueTypesService;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoRepo;
	final PermitArraysRepository permitArraysRepo;
	final PermitWeatherRepository permitWeatherRepo;
	final PermitConduitConductorSectionRepository permitCCRepo;
	final PermitLayoutRepository permitLayoutRepo;
	final NEC3106B16Repository nEC3106B16Repo;

	public CheckRequiredAmpacity(CheckValueTypesService checkValueTypesService,
			PermitProjectSiteInfoRepository permitProjectSiteInfoRepo, PermitArraysRepository permitArraysRepo,
			PermitWeatherRepository permitWeatherRepo, PermitConduitConductorSectionRepository permitCCRepo,
			PermitLayoutRepository permitLayoutRepo, NEC3106B16Repository nEC3106B16Repo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.permitProjectSiteInfoRepo = permitProjectSiteInfoRepo;
		this.permitArraysRepo = permitArraysRepo;
		this.permitWeatherRepo = permitWeatherRepo;
		this.permitCCRepo = permitCCRepo;
		this.permitLayoutRepo = permitLayoutRepo;
		this.nEC3106B16Repo = nEC3106B16Repo;
	}

	public Boolean checkSubPanelAmpacityCorrection(Long idPermit) {
		try {

			/*
			 * Get PermitProjectSiteInfoEntityResult
			 */
			PermitProjectSiteInfoEntity permitProjectSiteInfo = permitProjectSiteInfoRepo
					.findByPermitEntityId(idPermit);

			if (permitProjectSiteInfo != null
					&& checkValueTypesService.isStringNotEmpty(permitProjectSiteInfo.getSubPanelConductorSizeNote())) {
				return true;
			} else {

				/*
				 * Get PermitArraysEntity
				 */
				PermitArraysEntity permitArraysEntityResult = permitArraysRepo.findByPermitEntityId(idPermit);

				/*
				 * Get WeatherPermitInfo
				 */
				PermitWeatherEntity permtiWeather = permitWeatherRepo.findByPermitEntityId(idPermit);
				Integer quatrePourCentAverageHigh = 0;
				try {
					if (checkValueTypesService.NotEquals(permtiWeather.getQuatrePourCentAverageHigh(), "")) {
						if (checkValueTypesService.NotEquals(permtiWeather.getQuatrePourCentAverageHigh(), "Other")) {
							quatrePourCentAverageHigh = Integer.parseInt(permtiWeather.getQuatrePourCentAverageHigh());
						} else {
							if (permtiWeather.getQuatrePourCentAvHighOther().contains("°")) {
								quatrePourCentAverageHigh = Integer
										.parseInt(permtiWeather.getQuatrePourCentAvHighOther().split("°")[0]);
							} else {
								if (permtiWeather.getQuatrePourCentAvHighOther().contains("C")) {
									quatrePourCentAverageHigh = Integer
											.parseInt(permtiWeather.getQuatrePourCentAvHighOther().split("C")[0]);
								} else {
									quatrePourCentAverageHigh = Integer
											.parseInt(permtiWeather.getQuatrePourCentAvHighOther());
								}
							}

						}
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

				/*
				 * Get circuit
				 */
				PermitConduitConductorSectionEntity circuit = permitCCRepo.findByPermitEntityId(idPermit);

				/*
				 * Get Permit Layout
				 */

				PermitLayoutEntity permitLayoutEntity = permitLayoutRepo.findByPermitEntityId(idPermit);
				String aCcircuit = "";
				int acCircuitLength = 0;
				int inverterQty = 0;
				if (circuit.getComponentOrder() != null && circuit.getComponentOrder().contains("-INV-")) {

					aCcircuit = "INV-" + circuit.getComponentOrder().split("-INV-")[1];

				} else if (circuit.getComponentOrder() != null && circuit.getComponentOrder().contains("-INV")) {

					aCcircuit = "INV-";

				} else if (circuit.getComponentOrder() != null && circuit.getComponentOrder().contains("INV-")) {

					aCcircuit = circuit.getComponentOrder();

				} else if (checkValueTypesService.Equals(circuit.getComponentOrder(), "INV")) {

					aCcircuit = "INV-";

				}

				if (aCcircuit.equals("INV-")) {
					acCircuitLength = 1;
				} else {
					acCircuitLength = aCcircuit.split("-").length;
				}
				for (int i = 0; i < acCircuitLength; i++) {

					if (checkValueTypesService.Equals(aCcircuit.split("-")[i], "INV")) {
						aCcircuit = aCcircuit.replace("INV", "INVERTER");

					} else if (checkValueTypesService.Equals(aCcircuit.split("-")[i], "ACJBOX")) {
						aCcircuit = aCcircuit.replace("ACJBOX", "JUNCTION BOX");

					} else if (checkValueTypesService.Equals(aCcircuit.split("-")[i], "ACC")) {
						aCcircuit = aCcircuit.replace("ACC", "AC COMBINER/LOAD CENTER");

					} else if (checkValueTypesService.Equals(aCcircuit.split("-")[i], "ACD")) {
						aCcircuit = aCcircuit.replace("ACD", "AC DISCONNECT");

					} else if (checkValueTypesService.Equals(aCcircuit.split("-")[i], "ACDTwo")) {
						aCcircuit = aCcircuit.replace("ACDTwo", "AC DISCONNECT");

					} else if (checkValueTypesService.Equals(aCcircuit.split("-")[i], "PMETER")) {
						aCcircuit = aCcircuit.replace("PMETER", "PRODUCTION METER");

					} else if (checkValueTypesService.Equals(aCcircuit.split("-")[i], "ACSUBPANEL")) {
						aCcircuit = aCcircuit.replace("ACSUBPANEL", "SUB PANEL");

					}

				}
				if (permitArraysEntityResult != null
						&& (checkValueTypesService.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Neither")
								|| checkValueTypesService.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
										"System Optimizer"))) {
					if (checkValueTypesService.isStringNotEmpty(permitArraysEntityResult.getSecondInverterModel()))
						inverterQty = 2;
					else
						inverterQty = 1;
				} else if (permitArraysEntityResult != null && (checkValueTypesService
						.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")
						|| checkValueTypesService.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
								"AC Modules"))) {
					if (checkValueTypesService
							.isStringNotEmpty(permitArraysEntityResult.getNumberModulesACCircuitTwo()))
						inverterQty = 2;
					else
						inverterQty = 1;
				}
				String lastEnv = "";
				int j = 1;

				Boolean isSubPanul = false;
				getCircuitEnvironment(permitArraysEntityResult, quatrePourCentAverageHigh, permitLayoutEntity);
				do {
					lastEnv = mapACcircuitEnvironment(j, aCcircuit, permitProjectSiteInfo, inverterQty,
							permitArraysEntityResult.getInverterLocation(),
							permitProjectSiteInfo.getUseDisconectSwith(),
							permitProjectSiteInfo.getCombiningACCircuits(), systemCircuitEnvironment, lastEnv);
					isSubPanul = checkValueTypesService.Equals(aCcircuit.split("-")[j - 1], "SUB PANEL");
					j++;

				} while (j < acCircuitLength + 1 && Boolean.FALSE.equals(isSubPanul));

				String conductorSize = "";
				if (checkValueTypesService.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")) {
					conductorSize = permitProjectSiteInfo.getSubPanelConductorSizeOther();

				} else if (checkValueTypesService.Equals(circuit.getConductorSizeEleven(), "Per Manufacturer")) {
					conductorSize = "#12 AWG";
				} else {
					conductorSize = permitProjectSiteInfo.getSubPanelConductorSize();
				}
				Integer aCAmpacityCorrection310 = checkValueTypesService.Equals(conductorSize, "#12 AWG") ? 25
						: getACAmpacityCorrection310(conductorSize);
				Float correctedAmpacity = aCAmpacityCorrection310 * Float.parseFloat(tempDerating);

				float subPanelMainBreaker = -1f;
				if (checkValueTypesService
						.isNumeric(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating())) {
					subPanelMainBreaker = Float
							.parseFloat(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating());
				}

				// 05-27-2019 A.B if SubPanel Breaker (OCPD) at Main Service is Shown
				Boolean subPanelWithMultipleSystem = checkValueTypesService
						.Equals(permitProjectSiteInfo.getSolarLocation(), "Back-fed Breaker")
						&& (checkValueTypesService.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
								"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
								|| checkValueTypesService.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
										"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker")) ? true
												: false;
				float subPanelBreakerOCPD = -1f;
				if (checkValueTypesService.Equals(subPanelWithMultipleSystem, false)) {
					if (checkValueTypesService.Equals(permitProjectSiteInfo.getSubPanelBreakerOCPD(), "Other")
							&& checkValueTypesService.isNumeric(permitProjectSiteInfo.getSubPanelBreakerOCPDOther())) {
						subPanelBreakerOCPD = Float.parseFloat(permitProjectSiteInfo.getSubPanelBreakerOCPDOther());
					} else if (checkValueTypesService.isNumeric(permitProjectSiteInfo.getSubPanelBreakerOCPD())) {
						subPanelBreakerOCPD = Float.parseFloat(permitProjectSiteInfo.getSubPanelBreakerOCPD());
					}
				}
				float maxInverterOutputCurrent = checkValueTypesService.Equals(subPanelBreakerOCPD, -1f)
						&& checkValueTypesService.Equals(subPanelMainBreaker, -1f)
								? 0f
								: checkValueTypesService.Equals(subPanelBreakerOCPD, -1f) ? subPanelMainBreaker
										: checkValueTypesService.Equals(subPanelMainBreaker, -1f) ? subPanelBreakerOCPD
												: checkValueTypesService.Equals(subPanelWithMultipleSystem, false)
														? Math.min(subPanelMainBreaker, subPanelBreakerOCPD)
														: 0f;

				return maxInverterOutputCurrent < correctedAmpacity;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

	public void getCircuitEnvironment(PermitArraysEntity permitArraysEntityResult, Integer quatrePourCentAverageHigh,
			PermitLayoutEntity permitLayoutEntity) {
		try {
			invOpTemp = String.valueOf(quatrePourCentAverageHigh);

			if (checkValueTypesService.Equals(permitArraysEntityResult.getGroundMounted(), true)
					|| (checkValueTypesService.Equals(permitArraysEntityResult.getCarportMounted(), true)
							&& (checkValueTypesService.Equals(permitArraysEntityResult.getFrontAndBack(), true) || checkValueTypesService.Equals(permitArraysEntityResult.getCantelever(), true) ||
									checkValueTypesService.Equals(permitArraysEntityResult.getFreeStanding(), true))
							&& checkValueTypesService.Equals(permitArraysEntityResult.getCircuitUnderGround(), true)
							&& checkValueTypesService.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
									"The carport/patio cover will be open frame construction"))) {

				systemCircuitEnvironment = "OPEN AIR";
				systemHighAboveRoof = "N/A";
				systemAempAdder = "0";
				systemOpTemp = String.valueOf(quatrePourCentAverageHigh);
				isThereGroundOrPole = true;

			} else if (checkValueTypesService.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& (checkValueTypesService.Equals(permitArraysEntityResult.getFrontAndBack(), true) || checkValueTypesService.Equals(permitArraysEntityResult.getCantelever(), true) ||
							checkValueTypesService.Equals(permitArraysEntityResult.getFreeStanding(), true))
					&& !checkValueTypesService.NotEquals(permitArraysEntityResult.getCircuitUnderGround(), false)
					&& checkValueTypesService.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
							"The carport/patio cover will be open frame construction")) {

				systemCircuitEnvironment = "OPEN AIR";
				systemHighAboveRoof = "N/A";
				systemAempAdder = "0";
				systemOpTemp = String.valueOf(quatrePourCentAverageHigh);

			} else if (checkValueTypesService.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& (checkValueTypesService.Equals(permitArraysEntityResult.getFrontAndBack(), true) || checkValueTypesService.Equals(permitArraysEntityResult.getCantelever(), true) ||
							checkValueTypesService.Equals(permitArraysEntityResult.getFreeStanding(), true))
					&& checkValueTypesService.Equals(permitArraysEntityResult.getCircuitUnderGround(), true)
					&& checkValueTypesService.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
							"The carport/patio cover will include roofing material under the modules")) {

				systemCircuitEnvironment = "ROOFTOP";
				systemHighAboveRoof = "0.5” – 3.5”";
				systemAempAdder = "22";
				adder = quatrePourCentAverageHigh + 22;
				systemOpTemp = String.valueOf(adder);
			} else if ((checkValueTypesService.Equals(permitArraysEntityResult.getRoofMounted(), true)
					|| (checkValueTypesService.Equals(permitArraysEntityResult.getCarportMounted(), true)
							&& checkValueTypesService.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
									"The carport/patio cover will include roofing material under the modules")
							&& (((checkValueTypesService.Equals(permitArraysEntityResult.getFrontAndBack(), true) || checkValueTypesService.Equals(permitArraysEntityResult.getCantelever(), true) ||
									checkValueTypesService.Equals(permitArraysEntityResult.getFreeStanding(), true))
									&& checkValueTypesService.Equals(permitArraysEntityResult.getCircuitUnderGround(),
											false))
									|| (checkValueTypesService
											.Equals(permitArraysEntityResult.getAttachedToExtWal(), true) || checkValueTypesService
											.Equals(permitArraysEntityResult.getAttachedToFascia(), true) || checkValueTypesService
											.Equals(permitArraysEntityResult.getAttachedToSkylifts(), true)))))) {

				systemCircuitEnvironment = "ROOFTOP";
				systemHighAboveRoof = "0.5” – 3.5”";
				systemAempAdder = "22";
				adder = quatrePourCentAverageHigh + 22;
				systemOpTemp = String.valueOf(adder);
				isRoofMounted = true;

			}

			if (checkValueTypesService.Equals(permitLayoutEntity.getConduitRun(), "Attic")) {
				circuitEnvironment = "ATTIC";
				highAboveRoof = "IN ATTIC";
				tempAdder = "22";
				if(Boolean.TRUE.equals(permitLayoutEntity.getShowConduitRoofAsHeight())) {
					tempAdder = "33";
					adder = quatrePourCentAverageHigh + 33;
					opTemp = String.valueOf(adder);
				}else {
					///tempAdder = userSetting.getAtticTemperatureAdder();
					adder = quatrePourCentAverageHigh + Integer.parseInt(tempAdder);
					opTemp = String.valueOf(adder);
				}
				
			} else {
				circuitEnvironment = "ROOFTOP";
				highAboveRoof = "0.5\" – 3.5\"";
				if(Boolean.TRUE.equals(permitLayoutEntity.getShowConduitRoofAsHeight())) {
					tempAdder = "33";
					adder = quatrePourCentAverageHigh + 33;
					opTemp = String.valueOf(adder);
				}else {
					tempAdder = "22";
					adder = quatrePourCentAverageHigh + 22;
					opTemp = String.valueOf(quatrePourCentAverageHigh + 22);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public String mapACcircuitEnvironment(int i, String aCcircuit, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			int inverterQty, String inverterLocation, String independantAC, String combiningACCircuits,
			String systemCircuitEnvironment, String lastInvirement) {
		try {

			if (checkValueTypesService.Equals(systemCircuitEnvironment, "ROOFTOP")) {

				if (checkValueTypesService.Equals(isRoofMounted, true)) {

					if (checkValueTypesService.Equals(lastInvirement, "ROOFTOP")
							|| checkValueTypesService.Equals(lastInvirement, EXT_WALL)) {
						lastInvirement = EXT_WALL;
					} else if (checkValueTypesService.Equals(lastInvirement, "ATTIC")
							|| checkValueTypesService.Equals(lastInvirement, "INT/EXT WALL")) {
						lastInvirement = "INT/EXT WALL";
					}

				} else {

					if (inverterQty == 1) { // IF There is only one inverter

						if (checkValueTypesService.isStringNotEmpty(inverterLocation)
								&& inverterLocation.contains(MOUNT_ARRAY)) { // Inverter location “Mounted at Array”

							if (checkValueTypesService.Equals(independantAC, "Yes")) {

								if (checkValueTypesService.contains(aCcircuit, "-") && checkValueTypesService
										.Equals(aCcircuit.split("-")[i - 1], "AC DISCONNECT")) {
									lastInvirement = UNDERGROUND;
								} else {
									if (checkValueTypesService.Equals(lastInvirement, UNDERGROUND)
											|| checkValueTypesService.Equals(lastInvirement, EXT_WALL)) {
										lastInvirement = EXT_WALL;
									} else {
										lastInvirement = EXT_SHADE;
									}
								}

							} else { // there is no Independent AC Disconnect

								if (checkValueTypesService.contains(aCcircuit, "-")
										&& checkValueTypesService.Equals(aCcircuit.split("-")[i - 1], "INVERTER")) {
									lastInvirement = UNDERGROUND;
								} else {
									lastInvirement = EXT_WALL;

								}

							}
						} else { // Inverter location “Mounted at MSP OR SUB PANEL”
							lastInvirement = EXT_WALL;
						}
					} else if (inverterQty > 1) { // IF There is More than one inverter

						if (checkValueTypesService.isStringNotEmpty(inverterLocation)
								&& inverterLocation.contains(MOUNT_ARRAY)) { // Inverter location “Mounted at Array”

							if (checkValueTypesService.isStringNotEmpty(combiningACCircuits)
									&& combiningACCircuits.contains("MOUNTED AT THE ARRAY")) {
								if (checkValueTypesService.Equals(independantAC, "Yes") && permitProjectSiteInfo != null
										&& checkValueTypesService
												.isStringNotEmpty(permitProjectSiteInfo.getRooftopACCombinerModel())
										&& checkValueTypesService.isStringNotEmpty(
												permitProjectSiteInfo.getRooftopACCombinerModelTwo())) {

									if (checkValueTypesService.contains(aCcircuit, "-") && checkValueTypesService
											.Equals(aCcircuit.split("-")[i - 1], "AC DISCONNECT")) {
										lastInvirement = UNDERGROUND;
									} else {
										if (checkValueTypesService.Equals(lastInvirement, UNDERGROUND)
												|| checkValueTypesService.Equals(lastInvirement, EXT_WALL)) {
											lastInvirement = EXT_WALL;
										} else {
											lastInvirement = EXT_SHADE;
										}
									}

								} else { // there is no Independent AC Disconnect

									if (checkValueTypesService.contains(aCcircuit, "-") && checkValueTypesService
											.Equals(aCcircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {
										lastInvirement = UNDERGROUND;
									} else {
										if (checkValueTypesService.Equals(lastInvirement, UNDERGROUND)
												|| checkValueTypesService.Equals(lastInvirement, EXT_WALL)) {
											lastInvirement = EXT_WALL;
										} else {
											lastInvirement = EXT_SHADE;
										}

									}

								}
							} else if (checkValueTypesService.isStringNotEmpty(combiningACCircuits)
									&& checkValueTypesService.Equals(combiningACCircuits,
											"A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE MAIN SERVICE PANEL")) {

								if (checkValueTypesService.contains(aCcircuit, "-") && i < aCcircuit.split("-").length
										&& checkValueTypesService.Equals(aCcircuit.split("-")[i],
												"AC COMBINER/LOAD CENTER")) {
									lastInvirement = UNDERGROUND;
								} else {
									if (checkValueTypesService.Equals(lastInvirement, UNDERGROUND)
											|| checkValueTypesService.Equals(lastInvirement, EXT_WALL)) {
										lastInvirement = EXT_WALL;
									} else {
										lastInvirement = EXT_SHADE;
									}
								}

							} else {
								if (checkValueTypesService.contains(aCcircuit, "-")
										&& checkValueTypesService.Equals(aCcircuit.split("-")[i - 1], "INVERTER")) {
									lastInvirement = UNDERGROUND;
								} else {
									lastInvirement = EXT_WALL;

								}
							}

						} else { // Inverter location “Mounted at MSP OR SUB PANEL”
							lastInvirement = EXT_WALL;
						}
					}

				}

			} else {
				if (checkValueTypesService.Equals(isThereGroundOrPole, true)) {
					if (inverterQty == 1) { // IF There is only one inverter

						if (checkValueTypesService.isStringNotEmpty(inverterLocation)
								&& inverterLocation.contains(MOUNT_ARRAY)) { // Inverter location “Mounted at Array”

							if (checkValueTypesService.Equals(independantAC, "Yes")) {

								if (checkValueTypesService.Equals(aCcircuit.split("-")[i - 1], "AC DISCONNECT")) {
									lastInvirement = UNDERGROUND;
								} else {
									if (checkValueTypesService.Equals(lastInvirement, UNDERGROUND)
											|| checkValueTypesService.Equals(lastInvirement, EXT_WALL)) {
										lastInvirement = EXT_WALL;
									} else {
										lastInvirement = EXT_SHADE;
									}
								}

							} else { // there is no Independent AC Disconnect

								if (checkValueTypesService.Equals(aCcircuit.split("-")[i - 1], "INVERTER")) {
									lastInvirement = UNDERGROUND;
								} else {
									lastInvirement = EXT_WALL;

								}

							}
						} else { // Inverter location “Mounted at MSP OR SUB PANEL”
							lastInvirement = EXT_WALL;
						}
					} else if (inverterQty > 1) { // IF There is More than one inverter

						if (checkValueTypesService.isStringNotEmpty(inverterLocation)
								&& inverterLocation.contains(MOUNT_ARRAY)) { // Inverter location “Mounted at Array”

							if (checkValueTypesService.isStringNotEmpty(combiningACCircuits)
									&& combiningACCircuits.contains("MOUNTED AT THE ARRAY")) {
								if (checkValueTypesService.Equals(independantAC, "Yes")
										&& checkValueTypesService
												.isStringNotEmpty(permitProjectSiteInfo.getRooftopACCombinerModel())
										&& checkValueTypesService.isStringNotEmpty(
												permitProjectSiteInfo.getRooftopACCombinerModelTwo())) {

									if (checkValueTypesService.Equals(aCcircuit.split("-")[i - 1], "AC DISCONNECT")) {
										lastInvirement = UNDERGROUND;
									} else {
										if (checkValueTypesService.Equals(lastInvirement, UNDERGROUND)
												|| checkValueTypesService.Equals(lastInvirement, EXT_WALL)) {
											lastInvirement = EXT_WALL;
										} else {
											lastInvirement = EXT_SHADE;
										}
									}

								} else { // there is no Independent AC Disconnect

									if (checkValueTypesService.Equals(aCcircuit.split("-")[i - 1],
											"AC COMBINER/LOAD CENTER")) {
										lastInvirement = UNDERGROUND;
									} else {
										if (checkValueTypesService.Equals(lastInvirement, UNDERGROUND)
												|| checkValueTypesService.Equals(lastInvirement, EXT_WALL)) {
											lastInvirement = EXT_WALL;
										} else {
											lastInvirement = EXT_SHADE;
										}

									}

								}
							} else if (checkValueTypesService.isStringNotEmpty(combiningACCircuits)
									&& checkValueTypesService.Equals(combiningACCircuits,
											"A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE MAIN SERVICE PANEL")) {

								if (i < aCcircuit.split("-").length && checkValueTypesService
										.Equals(aCcircuit.split("-")[i], "AC COMBINER/LOAD CENTER")) {
									lastInvirement = UNDERGROUND;
								} else {
									if (checkValueTypesService.Equals(lastInvirement, UNDERGROUND)
											|| checkValueTypesService.Equals(lastInvirement, EXT_WALL)) {
										lastInvirement = EXT_WALL;
									} else {
										lastInvirement = EXT_SHADE;
									}
								}

							} else {
								if (checkValueTypesService.Equals(aCcircuit.split("-")[i - 1], "INVERTER")) {
									lastInvirement = UNDERGROUND;
								} else {
									lastInvirement = EXT_WALL;

								}
							}

						} else { // Inverter location “Mounted at MSP OR SUB PANEL”
							lastInvirement = EXT_WALL;
						}
					}

				} else {
					lastInvirement = EXT_WALL;
				}
			}
			tempDerating = checkValueTypesService.Equals(lastInvirement, UNDERGROUND) ? "1.00"
					: getACAmpacityCorrectionB2a(invOpTemp);
			return lastInvirement;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public Integer getACAmpacityCorrection310(String tradeSize) {
		try {

			List<NEC_310_16_B_16> nec31016B16 = nEC3106B16Repo.findBytradeSze(tradeSize);
			if (nec31016B16 != null && nec31016B16.size() == 1)
				return nec31016B16.get(0).getSeventyFiveInsulation();
			else
				return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public String getACAmpacityCorrectionB2a(String operateTemp75String) {
		String ampacityCorrection = "0.00";
		try {
			if (checkValueTypesService.isStringInt(operateTemp75String)) {
				Integer operateTemp = Integer.parseInt(operateTemp75String);

				if (operateTemp >= 21 && operateTemp <= 25) {
					ampacityCorrection = "1.05";
				} else if (operateTemp >= 26 && operateTemp <= 30) {
					ampacityCorrection = "1.00";
				} else if (operateTemp >= 31 && operateTemp <= 35) {
					ampacityCorrection = "0.94";
				} else if (operateTemp >= 36 && operateTemp <= 40) {
					ampacityCorrection = "0.88";
				} else if (operateTemp >= 41 && operateTemp <= 45) {
					ampacityCorrection = "0.82";
				} else if (operateTemp >= 46 && operateTemp <= 50) {
					ampacityCorrection = "0.75";
				} else if (operateTemp >= 51 && operateTemp <= 55) {
					ampacityCorrection = "0.67";
				} else if (operateTemp >= 56 && operateTemp <= 60) {
					ampacityCorrection = "0.58";
				} else if (operateTemp >= 61 && operateTemp <= 65) {
					ampacityCorrection = "0.47";
				} else if (operateTemp >= 66 && operateTemp <= 70) {
					ampacityCorrection = "0.33";
				} else if (operateTemp >= 71 && operateTemp <= 80) {
					ampacityCorrection = "0.00";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return ampacityCorrection;
	}

}
