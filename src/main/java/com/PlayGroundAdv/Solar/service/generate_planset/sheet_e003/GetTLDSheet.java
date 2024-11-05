package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.File;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GetTLDSheet {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final BatteryRepository batteryRep;

	public GetTLDSheet(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			BatteryRepository batteryRep) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.batteryRep = batteryRep;
	}

	public String getpdfName(PermitAdvEntityResult editPermitAdvRequest, PermitConduitConductorSectionEntity circuit,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, PermitHomeSiteInfoEntity permitHomeSite,
			PermitArrayEntityResultSecond arrays, Inverters inverterInfo, Inverters secondInverterInfo,
			Inverters microInverterInfo, PlansetUtils plansetUtils, DCOptimizerEntity dcOptimizer,
			ACDisconnect acDisconnect, ACDisconnect secondacDisconnect,
			PermitAdditionalInfoEntityResult permitAdditionalInfo, PermitEnergyBatterySystem energyBatterySystem) {
		try {
			if (editPermitAdvRequest != null
					&& ((checkValue.Equals(editPermitAdvRequest.getTldShortList(), true))
							|| (checkValue.Equals(editPermitAdvRequest.getOpenTldLibrary(), true)))
					&& (editPermitAdvRequest.getTldList() != null
							&& editPermitAdvRequest.getTldList().contains("::"))) {

				return editPermitAdvRequest.getTldList().split("::")[1].replace(".pdf", "");

			} else {
				String invTech = (checkValue.Equals(arrays.getDeviceToIncorporate(), "Micro Inverter")
						|| checkValue.Equals(arrays.getDeviceToIncorporate(), "AC Modules")) ? "MICRO" : "STRING";

				StringBuilder pdfName = new StringBuilder(
						checkValue.Equals(invTech, "MICRO") ? plansetUtils.getNumberOfBranchCircuit() + "MOD_"
								: plansetUtils.getTotalNumberOfStrings() + "MOD");

				if (checkValue.Equals(invTech, "STRING")) {

					if (plansetUtils.getNumberOfStringsOne() > 0) {
						pdfName.append("(" + plansetUtils.getNumberOfStringsOne());

						if (plansetUtils.getNumberOfStringsTwo() > 0) {
							pdfName.append("." + plansetUtils.getNumberOfStringsTwo());
						}
						if (plansetUtils.getNumberOfStringsThree() > 0) {
							pdfName.append("." + plansetUtils.getNumberOfStringsThree());
						}
						if (plansetUtils.getNumberOfStringsFour() > 0) {
							pdfName.append("." + plansetUtils.getNumberOfStringsFour());
						}
						if (plansetUtils.getNumberOfStringsFive() > 0) {
							pdfName.append("." + plansetUtils.getNumberOfStringsFive());
						}
						pdfName.append(")_");
					}

					if (checkValue.Equals(arrays.getDeviceToIncorporate(), "System Optimizer")) {
						if (dcOptimizer != null && checkValue.contains(dcOptimizer.getManufacturer(), "Pika Energy")) {
							if (checkValue.Equals(dcOptimizer.getQtyModuleOpt(), "2")) {

								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "OPT2(Pika)_"))) {
									pdfName.append("OPT2(Pika)_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "OPT(Pika)_"))) {
									pdfName.append("OPT(Pika)_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "OPT2_"))) {
									pdfName.append("OPT2_");
								} else {
									pdfName.append("OPT_");
								}

							} else {
								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "OPT(Pika)_"))) {
									pdfName.append("OPT(Pika)_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "OPT_"))) {
									pdfName.append("OPT_");
								} else {
									pdfName.append("OPT2_");
								}

							}

						} else if (dcOptimizer != null && checkValue.contains(dcOptimizer.getManufacturer(), "SMA")) {
							if (checkValue.Equals(dcOptimizer.getQtyModuleOpt(), "2")) {
								pdfName.append("MLRSD2_");
							} else {
								pdfName.append("MLRSD_");
							}
						} else if (dcOptimizer != null && checkValue.contains(dcOptimizer.getManufacturer(), "IMO")) {
							if (checkValue.Equals(dcOptimizer.getQtyModuleOpt(), "2")) {
								pdfName.append("MLRSD2(IMO)_");
							} else {
								pdfName.append("MLRSD(IMO)_");
							}
						} else if (dcOptimizer != null && checkValue.Equals(dcOptimizer.getQtyModuleOpt(), "2")) {
							if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "OPT2_"))) {
								pdfName.append("OPT2_");
							} else {
								pdfName.append("OPT_");
							}
						} else {
							pdfName.append("OPT_");
						}
					}
				}

				String typeAC1 = getypeAC(acDisconnect);
				String typeAC2 = getypeAC(secondacDisconnect);
				String acd = getACDAbbr(typeAC1, typeAC2);

				if (circuit != null && circuit.getComponentOrder() != null && circuit.getComponentOrder().contains("-")
						&& circuit.getComponentOrder().split("-").length > 0) {

					for (int i = 0; i < circuit.getComponentOrder().split("-").length; i++) {
						if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "DCJBOX")) {
							pdfName.append("JB_");
						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "DCC")) {
							if ((checkValue.Equals(arrays.getDeviceToIncorporate(), "Neither")
									|| checkValue.Equals(arrays.getDeviceToIncorporate(), "System Optimizer"))
									&& permitProjectSiteInfo.getRoofTopDCCombiner() != null) {
								DCCombinerDisconnectEntity dcCombiner = permitProjectSiteInfo.getRoofTopDCCombiner();
								if (dcCombiner != null && checkValue.Equals(dcCombiner.getTypeDc(), "Rapid Shutdown")) {
									pdfName.append("RSD_");
								} else if (dcCombiner != null
										&& checkValue.Equals(dcCombiner.getOcpd(), "FUSIBLE DISCONNECT")
										|| checkValue.Equals(dcCombiner.getOcpd(), "FUSIBLE")
										|| checkValue.Equals(dcCombiner.getOcpd(), "Fusible")
										|| checkValue.Equals(dcCombiner.getOcpd(), "FUSE")) {
									if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "DCCF_"))) {
										pdfName.append("DCCF_");
									} else if (dcCombiner != null
											&& checkValue.Equals(dcCombiner.getTypeDc(), "DC Combining Disconnect")
											&& Boolean.TRUE.equals(fileExist(pdfName.toString() + "DCCD_"))) {
										pdfName.append("DCCD_");
									} else {
										pdfName.append("DCC_");
									}
								} else if (dcCombiner != null
										&& checkValue.Equals(dcCombiner.getTypeDc(), "DC Combining Disconnect")
										&& Boolean.TRUE.equals(fileExist(pdfName.toString() + "DCCD_"))) {
									pdfName.append("DCCD_");
								} else {
									pdfName.append("DCC_");
								}
							} else {
								pdfName.append("DCC_");
							}
						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "DCD")) {
							if ((checkValue.Equals(arrays.getDeviceToIncorporate(), "Neither")
									|| checkValue.Equals(arrays.getDeviceToIncorporate(), "System Optimizer"))
									&& permitProjectSiteInfo.getRoofTopDCDisco() != null) {
								DCCombinerDisconnectEntity dcDisconnect = permitProjectSiteInfo.getRoofTopDCDisco();
								if (dcDisconnect != null
										&& checkValue.Equals(dcDisconnect.getTypeDc(), "Rapid Shutdown")) {
									pdfName.append("RSD_");
								} else if (dcDisconnect != null
										&& checkValue.Equals(dcDisconnect.getTypeDc(), "DC Combining Disconnect")) {
									pdfName.append("DCCD_");
								} else {
									pdfName.append("DCD_");
								}
							} else {
								pdfName.append("DCD_");
							}
						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "DCCD")) {
							pdfName.append("DCCD_");
						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "INV")) {
							if (checkValue.Equals(arrays.getDeviceToIncorporate(), "System Optimizer")
									&& inverterInfo != null
									&& checkValue.Equals(inverterInfo.getMake(), "Pika Energy")) {
								pdfName.append(plansetUtils.getInverterQty() + "INV(Pika)_");
							} else if (inverterInfo != null
									&& checkValue.Equals(inverterInfo.getIntegratedACDisco(), true)) {
								pdfName.append(plansetUtils.getInverterQty() + "INV-ACD_");
							} else if (secondInverterInfo == null
									&& checkValue.Equals(circuit.getConductorNeutralSix(), true)) {
								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "INV(N)_"))) {
									pdfName.append("INV(N)_");
								} else {
									pdfName.append("INV_");
								}
							} else if (secondInverterInfo != null
									&& checkValue.Equals(circuit.getConductorNeutralSix(), true)) {
								if (Boolean.TRUE.equals(
										fileExist(pdfName.toString() + plansetUtils.getInverterQty() + "INV(N)_"))) {
									pdfName.append(plansetUtils.getInverterQty() + "INV(N)_");
								} else {
									pdfName.append(plansetUtils.getInverterQty() + "INV_");
								}
							} else if (secondInverterInfo == null) {
								pdfName.append("INV_");
							} else {
								pdfName.append(plansetUtils.getInverterQty() + "INV_");
							}
						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "MINV")) {
							if (microInverterInfo != null && checkValue.Equals(microInverterInfo.getMake(), "APsystems")
									&& checkValue.Equals(microInverterInfo.getModPerMicro(), 2)) {

								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV2(AP)_"))) {
									pdfName.append("MINV2(AP)_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV2_"))) {
									pdfName.append("MINV2_");
								} else {
									pdfName.append("MINV_");
								}

							} else if (microInverterInfo != null
									&& checkValue.Equals(microInverterInfo.getMake(), "APsystems")
									&& checkValue.Equals(microInverterInfo.getModPerMicro(), 4)) {

								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV4(AP)_"))) {
									pdfName.append("MINV4(AP)_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV2(AP)_"))) {
									pdfName.append("MINV2(AP)_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV2_"))) {
									pdfName.append("MINV2_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV_APsystems"))) {
									pdfName.append("MINV_APsystems_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV_YC600"))) {
									pdfName.append("MINV_YC600_");
								} else {
									pdfName.append("MINV_");
								}

							} else if (microInverterInfo != null
									&& checkValue.Equals(microInverterInfo.getMake(), "APsystems")) {

								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV(AP)_"))) {
									pdfName.append("MINV(AP)_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV_"))) {
									pdfName.append("MINV_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV_APsystems"))) {
									pdfName.append("MINV_APsystems_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV_YC600"))) {
									pdfName.append("MINV_YC600_");
								} else {
									pdfName.append("MINV_");
								}

							} else if (microInverterInfo != null
									&& checkValue.Equals(microInverterInfo.getModPerMicro(), 2)) {
								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "MINV2_"))) {
									pdfName.append("MINV2_");
								} else {
									pdfName.append("MINV_");
								}
							} else {
								pdfName.append("MINV_");
							}

						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "ACJBOX")) {
							pdfName.append("JB_");
						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "ACC")) {

							ACCombinerSLC acCombiner = permitProjectSiteInfo.getGroundLevelACCombinerBoxModel();
							ACCombinerSLC roofTopAcCombiner = permitProjectSiteInfo.getRoofTopACCombiner();
							if (acCombiner != null) {
								pdfName.append(getACCAbbr(acCombiner, permitHomeSite, pdfName));
							} else if (roofTopAcCombiner != null) {
								pdfName.append(getACCAbbr(roofTopAcCombiner, permitHomeSite, pdfName));
							}

						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "ACD")) {
							pdfName.append(acd);

						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "PMETER")) {
							pdfName.append("PVM_");
						} else if (checkValue.Equals(circuit.getComponentOrder().split("-")[i], "ACSUBPANEL")) {

							if (permitProjectSiteInfo != null
									&& checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing")) {
								if (checkValue.Equals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(),
										"Lug Only")) {
									pdfName.append("ESUBL_");
								} else
									pdfName.append("ESUBM_");

							} else {
								if (permitProjectSiteInfo != null && checkValue.Equals(
										permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(), "Lug Only")) {
									pdfName.append("NSUBL_");
								} else
									pdfName.append("NSUBM_");

							}

						}

					}

					if (permitAdditionalInfo != null && Boolean.TRUE.equals(permitAdditionalInfo.getBatteryStorage())) {

						if (energyBatterySystem != null && energyBatterySystem.getBatteries() != null
								&& !energyBatterySystem.getBatteries().isEmpty()) {
							if (energyBatterySystem.getBatteries().get(0) != null
									&& energyBatterySystem.getBatteries().get(0).getBatteryId() != null
									&& checkValue.EqualsCaseInsensitive(
											energyBatterySystem.getBatteries().get(0).getBatteryId().getManufacturer(),
											"SONNEN")) {

								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "SONNEN_"))) {
									pdfName.append("SONNEN_");
								} else if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "BAT_"))) {
									pdfName.append("BAT_");
								} else {
									pdfName.append("BATT_");
								}
							} else {
								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "BAT_"))) {
									pdfName.append("BAT_");
								} else {
									pdfName.append("BATT_");
								}
							}
						} else {
							if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "BAT_"))) {
								pdfName.append("BAT_");
							} else {
								pdfName.append("BATT_");
							}
						}

						if (Boolean.TRUE.equals(energyBatterySystem.getAtsIncluded())) {
							if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "ATS_"))) {
								pdfName.append("ATS_");
							} else {
								pdfName.append("EATS_");
							}
						}
						if (Boolean.TRUE.equals(energyBatterySystem.getGeneratorIncluded())) {
							if (checkValue.Equals(energyBatterySystem.getGeneratorStatus(), "Existing")) {
								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "EGEN"))) {
									pdfName.append("EGEN_");
								} else {
									pdfName.append("GEN_");
								}
							} else if (checkValue.Equals(energyBatterySystem.getGeneratorStatus(), "Proposed")) {
								if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "GEN"))) {
									pdfName.append("GEN_");
								} else {
									pdfName.append("EGEN_");
								}
							}
						}
					}

					if (permitHomeSite != null && permitProjectSiteInfo != null
							&& permitProjectSiteInfo.getSolarLocation() != null) {
						if (checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Meter adapter")) {
							pdfName.append("MSASST");
						} else if (checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Other")
								&& checkValue.Equals(permitProjectSiteInfo.getThepontOfTheC(), "Sub Panel")
								&& checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing")) {
							pdfName.append("ESUBLST");
						} else if (checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Other")
								&& checkValue.Equals(permitProjectSiteInfo.getThepontOfTheC(), "Sub Panel")
								&& checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Proposed")) {
							pdfName.append("NSUBLST");
						} else if (checkValue.Equals(permitHomeSite.getState(), "CA")) {
							if (checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Line-side tap")) {
								if (checkValue.Equals(permitProjectSiteInfo.getMainPanelUpgrade(), true)) {
									pdfName.append("NMSPSST");

								} else {
									pdfName.append("EMSPSST");

								}
							} else {
								if (checkValue.Equals(permitProjectSiteInfo.getMainPanelUpgrade(), true)) {
									pdfName.append("NMSP");

								} else {
									pdfName.append("EMSP");

								}
							}
						} else {
							if (checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Line-side tap")) {
								if (checkValue.Equals(permitProjectSiteInfo.getMainPanelUpgrade(), true)) {
									pdfName.append("NMSPSSTS");

								} else {
									pdfName.append("EMSPSSTS");

								}
							} else {
								if (checkValue.Equals(permitProjectSiteInfo.getMainPanelUpgrade(), true)) {
									pdfName.append("NMSPS");

								} else {
									pdfName.append("EMSPS");

								}
							}

						}
					}

				}
				if (permitHomeSite != null && !Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
						&& checkValue.NotEquals(permitHomeSite.getServiceVoltage(), "Other")) {
					pdfName.append("_3PHASE");
				}
				if (arrays != null && Boolean.TRUE.equals(arrays.getGroundMounted())) {
					pdfName.append("_AUX");
				}
				return pdfName.toString().replace(".pdf", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}

	}

	private Boolean fileExist(String s) {
		File[] files = new File(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/").listFiles();
		for (File f : files) {
			if (f.getName().indexOf(s) != -1) {
				return true;
			}
		}
		return false;
	}

	public String getypeAC(ACDisconnect acDisconnect) {
		try {
			if (acDisconnect != null) {
				String type = acDisconnect.getDisconnectDeviceType();
				if (type == null || checkValue.Equals(type, "NON-FUSIBLE DISCONNECT")
						|| checkValue.Equals(type, "Non-Fusible") || checkValue.Equals(type, "NON-FUSIBLE")) {
					return "NF";

				} else if (checkValue.NotEquals(type, "")) {
					return "F";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return "";
	}

	public String getACDAbbr(String typeAC1, String typeAC2) {
		String acd = "";
		try {

			if (checkValue.Equals(typeAC1, "F") && checkValue.Equals(typeAC2, "F")) {
				acd = "2ACDF_";
			} else if (checkValue.Equals(typeAC1, "NF") && checkValue.Equals(typeAC2, "NF")) {
				acd = "2ACD_";
			} else if (checkValue.Equals(typeAC1, "F") && checkValue.Equals(typeAC2, "NF")) {
				acd = "ACDF_ACD_";
			} else if (checkValue.Equals(typeAC1, "NF") && checkValue.Equals(typeAC2, "F")) {
				acd = "ACD_ACDF_";
			} else if (checkValue.Equals(typeAC1, "F")) {
				acd = "ACDF_";
			} else if (checkValue.Equals(typeAC1, "NF")) {
				acd = "ACD_";
			}

		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return acd;
	}

	private String getACCAbbr(ACCombinerSLC acc, PermitHomeSiteInfoEntity permitHomeSite, StringBuilder pdfName) {
		try {
			if (acc != null && checkValue.Equals(acc.getCategory(), "Solar Load Center")) {
				if (checkValue.Equals(acc.getCombinerDeviceType(), "With Main Breaker")) {
					if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "SLCM_"))) {
						return "SLCM_";
					}
				} else if (checkValue.Equals(acc.getNumberOfPoles(), "3")
						&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)) {
					if (Boolean.TRUE.equals(fileExist(pdfName.toString() + "SLC(S3P)_"))) {
						return "SLC(S3P)_";
					}
				}
			} else if (acc != null && checkValue.Equals(acc.getCategory(), "AC Combiner")) {
				return "ENVOY_";
			}
			return "SLC_";

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
