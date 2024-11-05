package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.entity.NEC_310_16_B_16;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.EMTConduitSize;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ElectricUtilityNumber;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.PVCConduitSize;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.project.PermitService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/*
 * @author Arij
 */
@Service
@Transactional
public class PlansetServiceE003Micro {

	final PermitService permitService;
	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final ModuleRepository moduleRepo;
	final ACDisconnectRepository aCDisconnectRepo;
	final PlansetLogo_SignatureMappingService logoSignMapping;
	final AcCombinerSLCRepository acCombinerRepo;
	final EssSystemMapping essSystemMapping;
	final JunctionBoxMapping junctionBoxMapping;
	final NotesMapping notesMapping;
	final NEC3106B16Repository nec3106B16Repo;
	final TechnicalProblemMsg technicalProblemMsg;
	final EMTConduitSize emtConduitSize;
	final GenerateCircuitList generateCircuitList;
	final PVCConduitSize pvcConduitSize;
	final TLDDigaramMapping tldDigaramMapping;
	final ElectricUtilityNumber electricUtilityNumber;
	final UserSettingRepository userSettingRepo;
	final E003SharedUtils sharedUtils;
	final DCMicroConductorMapping dcConductorMapping;
	final ACMicroConductorMapping acConductorMapping;
	public PlansetServiceE003Micro(PermitService permitService, CheckValueTypesService checkValue,
			GetPDFReaderService getPDFReaderService, ModuleRepository moduleRepo,
			ACDisconnectRepository aCDisconnectRepo, PlansetLogo_SignatureMappingService logoSignMapping,
			AcCombinerSLCRepository acCombinerRepo, EssSystemMapping essSystemMapping,
			JunctionBoxMapping junctionBoxMapping, NotesMapping notesMapping, NEC3106B16Repository nec3106B16Repo,
			TechnicalProblemMsg technicalProblemMsg, EMTConduitSize emtConduitSize, PVCConduitSize pvcConduitSize,
			GenerateCircuitList generateCircuitList, TLDDigaramMapping tldDigaramMapping,
			ElectricUtilityNumber electricUtilityNumber, UserSettingRepository userSettingRepo,
			E003SharedUtils sharedUtils, DCMicroConductorMapping dcConductorMapping, ACMicroConductorMapping acConductorMapping) {
		super();
		this.permitService = permitService;
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.moduleRepo = moduleRepo;
		this.aCDisconnectRepo = aCDisconnectRepo;
		this.logoSignMapping = logoSignMapping;
		this.acCombinerRepo = acCombinerRepo;
		this.essSystemMapping = essSystemMapping;
		this.junctionBoxMapping = junctionBoxMapping;
		this.notesMapping = notesMapping;
		this.nec3106B16Repo = nec3106B16Repo;
		this.emtConduitSize = emtConduitSize;
		this.technicalProblemMsg = technicalProblemMsg;
		this.generateCircuitList = generateCircuitList;
		this.pvcConduitSize = pvcConduitSize;
		this.tldDigaramMapping = tldDigaramMapping;
		this.electricUtilityNumber = electricUtilityNumber;
		this.userSettingRepo = userSettingRepo;
		this.sharedUtils = sharedUtils;
		this.dcConductorMapping = dcConductorMapping;
		this.acConductorMapping = acConductorMapping;
	}

	static final String EXISTING_SUB_PANEL = "Show the word \"Existing\" instead of a conductor size (Some AHJs may not approve the permit with this wording)";

	public void equipmentTabMapping(AcroFields form, Cmodulev2 moduleInfo, Inverters microInverterInfo,
			PermitArrayEntityResultSecond permitArraysEntityResult, int sheetIndex,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, Integer qtyAcCombiner, PlansetUtils plansetUtils) {
		try {
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-1-QTY", plansetUtils.getModuleQty() + "");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-2-QTY",
					checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")
							? plansetUtils.getModulePerMicroInverter() + ""
							: "0");

			// S.B CR-PM-3516-MOD-002 07/13/2021
			junctionBoxMapping.junctionBoxQty(form, sheetIndex, permitProjectSiteInfo.getQtyJunctionBox(),
					permitProjectSiteInfo.getMicroInverterCabling(), "4");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-7-QTY", plansetUtils.getQtyProductionMeter() + "");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-8-QTY", plansetUtils.getQtySubPanel() + "");
			// A.B 12-09 Existing OR New MSP Always 1
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-11-QTY", "1");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-12-QTY", "1");

			if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getManufacturerMappingValue(), "")
					&& checkValue.NotEquals(moduleInfo.getModelMappingValue(), "")) {
				form.setField(sheetIndex + "-" + "PV MODULE MODEL",
						moduleInfo.getManufacturerMappingValue() + " " + moduleInfo.getModelMappingValue());
			}
			if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")
					&& microInverterInfo != null) {
				form.setField(sheetIndex + "-" + "INVERTER MODEL", microInverterInfo.getManufacturerMappingValue() + " "
						+ microInverterInfo.getModelMappingValue());
			}

		} catch (IOException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void permitHomeMapping(AcroFields form, PermitHomeSiteInfoEntity permitHomeSite, int sheetIndex) {
		try {
			if (permitHomeSite != null) {
				if (checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)) {
					form.setField(sheetIndex + "-" + "SYSTEM-VOLTAGE", "120/240V - 1\u03A6");
				} else if (permitHomeSite.getServiceVoltage() != null) {
					if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "120/208V")) {
						form.setField(sheetIndex + "-" + "SYSTEM-VOLTAGE", "120/208V 3 WIRE WYE - 3\u03A6");
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "277/480V")) {
						form.setField(sheetIndex + "-" + "SYSTEM-VOLTAGE", "277/240V 4 WIRE WYE - 3\u03A6");
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "240V")) {
						form.setField(sheetIndex + "-" + "SYSTEM-VOLTAGE", "240V 3 WIRE \u0394- 3\u03A6");
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "400V")) {
						form.setField(sheetIndex + "-" + "SYSTEM-VOLTAGE", "400V 3 WIRE \u0394- 3\u03A6");
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "480V")) {
						form.setField(sheetIndex + "-" + "SYSTEM-VOLTAGE", "480V 3 WIRE \u0394- 3\u03A6");
					} else if (checkValue.Equals(permitHomeSite.getServiceVoltage(), "600V")) {
						form.setField(sheetIndex + "-" + "SYSTEM-VOLTAGE", "600V 3 WIRE \u0394- 3\u03A6");
					}
				} else {
					form.setField(sheetIndex + "-" + "SYSTEM-VOLTAGE", "");

				}
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void componentListQtyMapping(AcroFields form, Long idPermit, DCCombinerDisconnectEntity dcCombinerDisconnect,
			DCCombinerDisconnectEntity jBox, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			ACDisconnect acDisconnect, LeasePPAMeter leasePPAMeter, ACCombinerSLC acCombiner,
			ACDisconnect secondacDisconnect, ACCombinerSLC slcAcCombiner, int sheetIndex,
			PermitAdditionalInfoEntityResult permitAdditionalInfo, PlansetUtils plansetUtils,Integer qtyAcCombiner) {

		try {
			if (plansetUtils.getQtyJunctionBox() > 0
					|| (dcCombinerDisconnect != null && checkValue.Equals(dcCombinerDisconnect.getTypeDc(), "J Box"))) {
				if (jBox != null) {
					form.setField(sheetIndex + "-" + "JUNCTION BOX",
							jBox.getManufacturerMappingValue() + " " + jBox.getModelMappingValue());
				} else if (permitProjectSiteInfo != null && permitProjectSiteInfo.getDisconnectModel() != null) {
					form.setField(sheetIndex + "-" + "JUNCTION BOX", dcCombinerDisconnect.getManufacturerMappingValue()
							+ " " + dcCombinerDisconnect.getModelMappingValue());
				}
			}
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-9-QTY", "0");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-6-QTY", "0");
			form.setField(sheetIndex + "-" + "AC DISCONNECT FUSIBLE", "");
			form.setField(sheetIndex + "-" + "AC DISCONNECT", "");

			if (plansetUtils.getQtyACDisconnect() > 0 && acDisconnect != null) {

				if (acDisconnect.getDisconnectDeviceType() != null
						&& checkValue.Equals(acDisconnect.getDisconnectDeviceType(), "FUSIBLE DISCONNECT")
						|| checkValue.Equals(acDisconnect.getDisconnectDeviceType(), "FUSIBLE")
						|| checkValue.Equals(acDisconnect.getDisconnectDeviceType(), "Fusible")) {
					form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-9-QTY",
							plansetUtils.getQtyACDisconnect() + "");
					form.setField(sheetIndex + "-" + "AC DISCONNECT FUSIBLE",
							acDisconnect.getManufacturerMappingValue() + " " + acDisconnect.getModelMappingValue());
					if (plansetUtils.getQtyACDiscoGRND() > 0 && secondacDisconnect != null) {
						form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-6-QTY",
								plansetUtils.getQtyACDiscoGRND() + "");
						form.setField(sheetIndex + "-" + "AC DISCONNECT",
								secondacDisconnect.getManufacturerMappingValue() + " "
										+ secondacDisconnect.getModelMappingValue());
					}
				} else if (checkValue.isStringNotEmpty(acDisconnect.getManufacturerMappingValue())) {
					form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-6-QTY",
							plansetUtils.getQtyACDisconnect() + "");
					form.setField(sheetIndex + "-" + "AC DISCONNECT",
							acDisconnect.getManufacturerMappingValue() + " " + acDisconnect.getModelMappingValue());
					if (plansetUtils.getQtyACDiscoGRND() > 0 && secondacDisconnect != null) {
						form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-9-QTY",
								plansetUtils.getQtyACDiscoGRND() + "");
						form.setField(sheetIndex + "-" + "AC DISCONNECT FUSIBLE",
								secondacDisconnect.getManufacturerMappingValue() + " "
										+ secondacDisconnect.getModelMappingValue());
					}
				}

			}
			
//			A.B 10-17-2022 REV-CR-PM-788-MOD-002
//			AC Combiner & AC Solar SLC Mapping
			ACCombinerSLC acCombinerBox = slcAcCombiner != null
					&& checkValue.Equals(slcAcCombiner.getCategory(), "AC Combiner") ? slcAcCombiner : acCombiner != null
							&& checkValue.Equals(acCombiner.getCategory(), "AC Combiner") ? acCombiner : null;
			
			ACCombinerSLC slcBox = slcAcCombiner != null
					&& !checkValue.Equals(slcAcCombiner.getCategory(), "AC Combiner") ? slcAcCombiner : acCombiner != null
							&& !checkValue.Equals(acCombiner.getCategory(), "AC Combiner") ? acCombiner : null;
			
			if (acCombinerBox != null) {
				form.setField(sheetIndex + "-EQUIPMENT-COMPONENT-3-QTY", 
						qtyAcCombiner > 0 ? qtyAcCombiner + "" : "1");
				form.setField(sheetIndex + "-AC-COMB-1-MODEL-NUMBER",
						acCombinerBox.getManufacturerMappingValue() + " " + acCombinerBox.getModelMappingValue());
			} else {
				form.setField(sheetIndex + "-EQUIPMENT-COMPONENT-3-QTY", "0");
				form.setField(sheetIndex + "-AC-COMB-1-MODEL-NUMBER", "");
			}
			if (slcBox != null) {
				form.setField(sheetIndex + "-EQUIPMENT-COMPONENT-5-QTY",
						qtyAcCombiner > 0 ? qtyAcCombiner + "" : "1");
				form.setField(sheetIndex + "-SOLAR LOAD CENTER",
						slcBox.getManufacturerMappingValue() + " " + slcBox.getModelMappingValue());
			} else {
				form.setField(sheetIndex + "-EQUIPMENT-COMPONENT-5-QTY", "0");
				form.setField(sheetIndex + "-SOLAR LOAD CENTER", "");
			}
			
//			PRODUCTION METER
			if (+plansetUtils.getQtyProductionMeter() > 0) {
				if (permitProjectSiteInfo != null && checkValue.Equals(permitProjectSiteInfo.getUsedRevenue(), true)) {
					if (leasePPAMeter != null && leasePPAMeter.getMappedValue() != null) {
						form.setField(sheetIndex + "-" + "PRODUCTION METER", leasePPAMeter.getMappedValue());
					}
				} else
					form.setField(sheetIndex + "-" + "PRODUCTION METER", "");

			}

			if ((checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
					"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
					|| checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
							"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker"))
					&& Boolean.TRUE.equals(permitProjectSiteInfo.getSubPanelSpecification())) {
//				 A.B 08/31/2021 CR-PM-3862-002
				String subPanelBusRating = checkValue.Equals(permitProjectSiteInfo.getSubPanelBusRatingCombining(),
						"Other") ? permitProjectSiteInfo.getSubPanelBusRatingCombiningOther() + "A"
								: permitProjectSiteInfo.getSubPanelBusRatingCombining() + "A";
				String subPanelMainBreakerRating = checkValue
						.Equals(permitProjectSiteInfo.getSubPanelMainBreakerRatingCombining(), "Other")
								? permitProjectSiteInfo.getSubPanelMainBreakerRatingCombiningOther() + "A"
								: checkValue.Equals(permitProjectSiteInfo.getSubPanelMainBreakerRatingCombining(),
										"Lug Only") ? permitProjectSiteInfo.getSubPanelMainBreakerRatingCombining()
												: permitProjectSiteInfo.getSubPanelMainBreakerRatingCombining() + "A";
				form.setField(sheetIndex + "-" + "AC-SUB-PANEL-1-MANUFACTURER-AND-MODULE",
						subPanelBusRating + " BUS / " + subPanelMainBreakerRating + " MB");
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-8-QTY", "1");
			} else if (checkValue.Equals(permitProjectSiteInfo.getThepontOfTheC(), "Sub Panel")) {
//				 S.B 01/10/2020 CR-PM-3365-MOD-002-e
				String subBusrating = checkValue.Equals(permitProjectSiteInfo.getSubPanelBusRating(), "Other")
						? permitProjectSiteInfo.getSubPanelBusRatingOther() + "A"
						: permitProjectSiteInfo.getSubPanelBusRating() + "A";
				String subMainrating = checkValue
						.Equals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(), "Lug Only")
								? permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating()
								: permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating() + "A";
				form.setField(sheetIndex + "-" + "AC-SUB-PANEL-1-MANUFACTURER-AND-MODULE",
						subBusrating + " BUS / " + subMainrating + " MB");
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-8-QTY", "1");
			}

			// 18/11/2019 : CI : CR 2994 Change RequestProposed Main Breaker De-RateMapping
			// 06/30/2021 A.B CR-2994
			if (Boolean.TRUE.equals(permitProjectSiteInfo.getMainPanelUpgrade())) {
				if (checkValue.Equals(permitProjectSiteInfo.getDeratingthisPanelString(), "Proposed")) {
					form.setField(sheetIndex + "-" + "E-MSP", "(N) MAIN SERV PANEL DERATED");
				} else {
					form.setField(sheetIndex + "-" + "E-MSP", "NEW MAIN SERVICE PANEL");
				}
			} else {
				if (checkValue.Equals(permitProjectSiteInfo.getDeratingthisPanelString(), "Proposed")) {
					form.setField(sheetIndex + "-" + "E-MSP", "(E) MAIN SERV PANEL DERATED");
				} else {
					form.setField(sheetIndex + "-" + "E-MSP", "EXISTING MAIN SERVICE PANEL");
				}
			}

			if (Boolean.TRUE.equals(permitAdditionalInfo.getBatteryStorage())) {
				essSystemMapping.mapBattery(form, idPermit, sheetIndex, 13);
				essSystemMapping.mapEssEquipment(form, idPermit, sheetIndex, 10, 14);
			} else {
				// A.B Reset Field For revision
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-13-QTY", "0");
				form.setField(sheetIndex + "-" + "BATTERY-MODEL", "");
				form.setField(sheetIndex + "-" + "ESS-EQUIPMENT-1", "");
				form.setField(sheetIndex + "-" + "ESS-EQUIPMENT-1-MFG-MODEL", "");
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-10-QTY", "");
				form.setField(sheetIndex + "-" + "ESS-EQUIPMENT-2", "");
				form.setField(sheetIndex + "-" + "ESS-EQUIPMENT-2-MFG-MODEL", "");
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-14-QTY", "");
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	Integer fourPerCentAverageHigh = 0;

	public void quatrePourCentAverageHigh(PermtiWeatherEntityResult permtiWeather) {
		try {
			if (permtiWeather != null && permtiWeather.getQuatrePourCentAverageHigh() != null) {
				if (checkValue.NotEquals(permtiWeather.getQuatrePourCentAverageHigh(), "Other")
						&& checkValue.isStringInt(permtiWeather.getQuatrePourCentAverageHigh())) {
					fourPerCentAverageHigh = Integer.parseInt(permtiWeather.getQuatrePourCentAverageHigh());
				} else {
					if (checkValue.contains(permtiWeather.getQuatrePourCentAvHighOther(), "Â°")) {
						fourPerCentAverageHigh = Integer
								.parseInt(permtiWeather.getQuatrePourCentAvHighOther().split("Â°")[0]);
					} else {
						if (checkValue.contains(permtiWeather.getQuatrePourCentAvHighOther(), "C")) {
							fourPerCentAverageHigh = Integer
									.parseInt(permtiWeather.getQuatrePourCentAvHighOther().split("C")[0]);
						} else if (checkValue.isStringInt(permtiWeather.getQuatrePourCentAvHighOther())) {
							fourPerCentAverageHigh = Integer.parseInt(permtiWeather.getQuatrePourCentAvHighOther());
						}
					}

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	String iScRef = "";

	public String getiscRef(Cmodulev2 moduleInfo) {
		try {
			if (moduleInfo != null && moduleInfo.getiScRef() != null) {
				if (moduleInfo.getiScRef().contains(",")) {
					iScRef = moduleInfo.getiScRef().replace(',', '.');
				} else
					iScRef = moduleInfo.getiScRef();
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return iScRef;
	}

	String systemCircuitEnvironment = "";
	boolean isRoofMounted = false;
	boolean isThereGroundOrPole = false;

	public String getCircuitEnvironment(PermitArrayEntityResultSecond permitArraysEntityResult) {
		try {

			if (checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)
					|| (checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
							&& (checkValue.Equals(permitArraysEntityResult.getFrontAndBack(), true)
									|| checkValue.Equals(permitArraysEntityResult.getCantelever(), true)
									|| checkValue.Equals(permitArraysEntityResult.getFreeStanding(), true))
							&& checkValue.Equals(permitArraysEntityResult.getCircuitUnderGround(), true)
							&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
									"The carport/patio cover will be open frame construction"))) {

				systemCircuitEnvironment = "OPEN AIR";
				isThereGroundOrPole = true;

			} else if (checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& (checkValue.Equals(permitArraysEntityResult.getFrontAndBack(), true)
							|| checkValue.Equals(permitArraysEntityResult.getCantelever(), true)
							|| checkValue.Equals(permitArraysEntityResult.getFreeStanding(), true))
					&& checkValue.Equals(permitArraysEntityResult.getCircuitUnderGround(), false)
					&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
							"The carport/patio cover will be open frame construction")) {

				systemCircuitEnvironment = "OPEN AIR";

			} else if (checkValue.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& (checkValue.Equals(permitArraysEntityResult.getFrontAndBack(), true)
							|| checkValue.Equals(permitArraysEntityResult.getCantelever(), true)
							|| checkValue.Equals(permitArraysEntityResult.getFreeStanding(), true))
					&& checkValue.Equals(permitArraysEntityResult.getCircuitUnderGround(), true)
					&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
							"The carport/patio cover will include roofing material under the modules")) {

				systemCircuitEnvironment = "ROOFTOP";
			} else if ((checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true) || (checkValue
					.Equals(permitArraysEntityResult.getCarportMounted(), true)
					&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
							"The carport/patio cover will include roofing material under the modules")
					&& (((checkValue.Equals(permitArraysEntityResult.getFrontAndBack(), true)
							|| checkValue.Equals(permitArraysEntityResult.getCantelever(), true)
							|| checkValue.Equals(permitArraysEntityResult.getFreeStanding(), true))
							&& !checkValue.Equals(permitArraysEntityResult.getCircuitUnderGround(), true))
							|| (checkValue.Equals(permitArraysEntityResult.getAttachedToExtWal(), true)
									|| checkValue.Equals(permitArraysEntityResult.getAttachedToFascia(), true)
									|| checkValue.Equals(permitArraysEntityResult.getAttachedToSkylifts(), true)))))) {
				systemCircuitEnvironment = "ROOFTOP";
				isRoofMounted = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return systemCircuitEnvironment;
	}

	double nec31016Column90 = 0;
	String acAmpacityCorrectionB2a = "";

	public void mapAmpacityCorrection(String systemCircuitEnvironment, Integer fourPerCentAverageHigh, String iScRef) {
		try {
			if (checkValue.Equals(systemCircuitEnvironment, "ROOFTOP")) {
				if (getDCAmpacityCorrectionB2aMicro(fourPerCentAverageHigh + 22) != "") {
					nec31016Column90 = Float.parseFloat(iScRef) * 1.25 * 1.25
							/ (Float.parseFloat(getDCAmpacityCorrectionB2aMicro(fourPerCentAverageHigh + 22)));
					acAmpacityCorrectionB2a = getDCAmpacityCorrectionB2aMicro(fourPerCentAverageHigh + 22);
				}
			} else {
				if (getACAmpacityCorrectionB2aMicro(fourPerCentAverageHigh) != ""
						&& getACAmpacityCorrectionB2aMicro(fourPerCentAverageHigh) != "0.00") {
					nec31016Column90 = Float.parseFloat(iScRef) * 1.25 * 1.25
							/ (Float.parseFloat(getACAmpacityCorrectionB2aMicro(fourPerCentAverageHigh)));
					acAmpacityCorrectionB2a = getACAmpacityCorrectionB2aMicro(fourPerCentAverageHigh);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	Integer nec310 = 0;

	public Integer getnec310(double nec31016Column90) {
		try {
			if (checkValue.isStringInt(nec31016Column90 + "")) {
				nec310 = Integer.parseInt(nec31016Column90 + "");
			} else if (!checkValue.isStringInt(nec31016Column90 + "") && checkValue.isNumeric(nec31016Column90 + "")) {
				nec310 = (int) Math.round(nec31016Column90);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return nec310;
	}

	Integer numberOfConductor = 1;
	String tradeSize = "";
	NEC_310_16_B_16 nEC_310_16_B_16 = new NEC_310_16_B_16();

	public void getnec31016(Integer nec310) {
		try {
			nEC_310_16_B_16 = nec3106B16Repo.findFirstByNinetyInsulationGreaterThan(nec310);
		} catch (Exception e) {
			nEC_310_16_B_16 = null;
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		if (nEC_310_16_B_16 != null) {
			numberOfConductor = nEC_310_16_B_16.getNumberOfConductors();
			tradeSize = nEC_310_16_B_16.getTradeSze();
		}
	}

	String iacMaxMicro = "";
	Double iacMaxMicroCalcul = 1.00;

	public void iacMaxMicroCalcul(Inverters microInverterInfo) {
		try {
			if (microInverterInfo != null && microInverterInfo.getIacmax() != null) {
				if (microInverterInfo.getIacmax().contains(",")) {
					iacMaxMicro = microInverterInfo.getIacmax().replace(',', '.');
				} else
					iacMaxMicro = microInverterInfo.getIacmax();
				if (checkValue.isStringDouble(iacMaxMicro)) {
					iacMaxMicroCalcul = Double.parseDouble(iacMaxMicro);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void dc1ExistingMappingbeforeRev(AcroFields form, PermitConduitConductorSectionEntity circuit,
			PermitEntity permitEntity, int sheetIndex, String dcMinGroundWireSize) {
		try {

			if ((circuit != null && checkValue.Equals(circuit.getConductorTypeExisting(), true))
					|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {
				form.setField(sheetIndex + "-" + "DC1-TOTAL-QTY", "EXISTING");
				form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE-E003", "EXISTING");
				form.setField(sheetIndex + "-" + "DC1-MATERIAL-E003", "EXISTING");
				form.setField(sheetIndex + "-" + "DC1-GROUND", "EXISTING");

				form.setField(sheetIndex + "-" + "DC1-CONDUIT-SIZE", "EXISTING");
				form.setField(sheetIndex + "-" + "DC1-CONDUIT-MATERIAL", "EXISTING");

			} else {
				form.setField(sheetIndex + "-" + "DC1-TOTAL-QTY", 2 + "");
				if (circuit != null) {
					form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSize(), "EXIST") ? "EXISTING" : "#12 AWG");
					form.setField(sheetIndex + "-" + "DC1-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorType(), "EXIST") ? "EXISTING" : "PV Wire");
					// 19/06/2019 : CI : CR 2711 : change from "#6 GEC" to "#6 AWG"
					// 16/07/2021 : A.B : CR 3064 : Map from AHJ Min. Ground Wire Size On Roof If
					// exist
					String condGround = checkValue.isStringNotEmpty(dcMinGroundWireSize) ? dcMinGroundWireSize
							: "#6 AWG";
					form.setField(sheetIndex + "-" + "DC1-GROUND",
							checkValue.Equals(circuit.getConductorSize(), "EXIST") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "DC1-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSize(), "EXIST") ? "EXISTING" : "N/A");
					form.setField(sheetIndex + "-" + "DC1-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitType(), "EXIST") ? "EXISTING" : "Open Air");
				}
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void dc1ExistingMappingafterRevision(AcroFields form, PermitConduitConductorSectionEntity circuit,
			UserSettingEntity userSetting, PermitEntity permitEntity, int sheetIndex, String dcMinGroundWireSize) {
		try {
			if (circuit != null && userSetting != null && permitEntity != null) {

				if ((checkValue.Equals(circuit.getConductorTypeExisting(), true))
						|| (checkValue.Equals(permitEntity.getExistModule(), true))) {
					form.setField(sheetIndex + "-" + "DC1-TOTAL-QTY", "EXISTING");
					form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC1-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC1-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC1-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC1-CONDUIT-MATERIAL", "EXISTING");

				} else {

					form.setField(sheetIndex + "-" + "DC1-TOTAL-QTY",
							checkValue.NotEquals(circuit.getConductorQty(), "Other") ? circuit.getConductorQty()
									: circuit.getConductorQtyOther() + "");
					String tradeSize = checkValue.Equals(circuit.getConductorSize(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorSize(), "Other") ? circuit.getConductorSizeOther()
									: circuit.getConductorSize() != null ? circuit.getConductorSize() : "";
					form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE-E003", tradeSize);
					form.setField(sheetIndex + "-" + "DC1-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorType(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorType(), "Other")
											? circuit.getConductorTypeOther()
											: circuit.getConductorType() != null
													? checkValue.Equals(circuit.getConductorType(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorType()
													: "");
					String condGround = checkValue.isStringNotEmpty(dcMinGroundWireSize) ? dcMinGroundWireSize
							: checkValue.Equals(circuit.getConductorType(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorType(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorType(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeOther(), "Trunk Cable")))
													? "#6 AWG"
													: sharedUtils.getGroundSizeValue(circuit.getConductorSize(),
															tradeSize, userSetting.getMinimumDCGroundCon(),
															userSetting.getMinimumDCGroConOther());
					form.setField(sheetIndex + "-" + "DC1-GROUND",
							checkValue.Equals(circuit.getConductorSize(), "EXIST") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "DC1-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSize(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSize(), "Other")
											? circuit.getConduitSizeOther()
											: circuit.getConduitSize() != null ? circuit.getConduitSize() : "");
					form.setField(sheetIndex + "-" + "DC1-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitType(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitType(), "Other")
											? circuit.getConduitTypeOther()
											: circuit.getConduitType() != null ? circuit.getConduitType() : "");

				}
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	double requiredAMPACITY = 0;

	public double getRequiredAmpacity(int i, PlansetUtils plansetUtils, Integer indexAcCombiner, Boolean stepACCombiner,
			Double iacMaxMicroCalcul) {
		try {
			if (checkValue.Equals(stepACCombiner, true) && i >= indexAcCombiner) {
				requiredAMPACITY = iacMaxMicroCalcul * 1.25 * plansetUtils.getModulePerMicroInverter();
			} else {
				requiredAMPACITY = iacMaxMicroCalcul * 1.25 * plansetUtils.getMaxBranchMods();
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return requiredAMPACITY;
	}

	int conductorQty = 0;

	public void conductorQtyCalcul(AcroFields form, String acCircuit, int i, int microNumberOfStrings, int sheetIndex,
			PermitHomeSiteInfoEntity permitHomeSite, PermitConduitConductorSectionEntity circuit,
			Integer indexAcCombiner, Boolean stepACCombiner) {
		try {
//			A.B 09/02/2021 CR-3119-MOD-005 Update Qty on Conductor Table on AC circuits
			Integer branchCircuit = checkValue.Equals(stepACCombiner, true) && indexAcCombiner <= i ? 1
					: microNumberOfStrings;
//			S.B CR-3119-MOD-004 Update Qty on Conductor Table on AC circuits on E-003
			if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")) {
				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "3");
				conductorQty = 3;
			} else if ((checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
					&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
					&& checkValue.notChecked(circuit.getConductorNeutralSeven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.notChecked(circuit.getConductorNeutralEight()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.notChecked(circuit.getConductorNeutralNine()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.notChecked(circuit.getConductorNeutralNineTwo()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.notChecked(circuit.getConductorNeutralTen()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.notChecked(circuit.getConductorNeutralEleven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.notChecked(circuit.getConductorNeutralTwelve()))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", String.valueOf(2 * branchCircuit + 1));
				conductorQty = 2 * branchCircuit + 1;
			} else if ((checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
					&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
					&& checkValue.Equals(circuit.getConductorNeutralSeven(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.Equals(circuit.getConductorNeutralEight(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.Equals(circuit.getConductorNeutralNine(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.Equals(circuit.getConductorNeutralNineTwo(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.Equals(circuit.getConductorNeutralTen(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.Equals(circuit.getConductorNeutralEleven(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
							&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
							&& checkValue.Equals(circuit.getConductorNeutralTwelve(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralSix()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralSeven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralEight()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralNine()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralNineTwo()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralTen()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralEleven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralTwelve()))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", String.valueOf(3 * branchCircuit + 1));
				conductorQty = 3 * branchCircuit + 1;
			} else if ((checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
					&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
					&& checkValue.Equals(circuit.getConductorNeutralSeven(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.Equals(circuit.getConductorNeutralEight(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.Equals(circuit.getConductorNeutralNine(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.Equals(circuit.getConductorNeutralNineTwo(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.Equals(circuit.getConductorNeutralTen(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.Equals(circuit.getConductorNeutralEleven(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.Equals(circuit.getConductorNeutralTwelve(), true))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", String.valueOf(4 * branchCircuit + 1));
				conductorQty = 4 * branchCircuit + 1;
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public double getnec31016Column90(double requiredAMPACITY, Integer fourPerCentAverageHigh) {
		try {
			if (!checkValue.Equals(getACAmpacityCorrectionB2aMultipleMicro(fourPerCentAverageHigh), "0.00")) {
				nec31016Column90 = requiredAMPACITY
						/ (Float.parseFloat(getACAmpacityCorrectionB2aMultipleMicro(fourPerCentAverageHigh)));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return nec31016Column90;
	}

	public Integer getnec310ACcircuit(double nec31016Column90) {

		if (checkValue.isStringInt(nec31016Column90 + "")) {
			nec310 = Integer.parseInt(nec31016Column90 + "");
		} else if (!checkValue.isStringInt(nec31016Column90 + "") && checkValue.isNumeric(nec31016Column90 + "")) {
			nec310 = (int) Math.round(nec31016Column90);
		}
		return nec310;
	}

	public void getTradeSizeACcircuit(Integer nec310) {
		try {
			nEC_310_16_B_16 = nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG");
		} catch (Exception e) {
			nEC_310_16_B_16 = null;
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		if (nEC_310_16_B_16 != null) {
			numberOfConductor = nEC_310_16_B_16.getNumberOfConductors();
			tradeSize = nEC_310_16_B_16.getTradeSze();
		}
	}

	public void mapInverterCircuit(AcroFields form, PermitConduitConductorSectionEntity circuit,
			PermitEntity permitEntity, int i, int sheetIndex, String minGroundWireSize) {
		try {

			if ((circuit != null && checkValue.Equals(circuit.getConductorTypeSixExisting(), true))
					|| (permitEntity != null && checkValue.Equals(permitEntity.getExistInverter(), true))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "EXISTING");
				form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
				form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

			} else {
				if (circuit != null) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeSix(), "EXIST") ? "EXISTING" : "#12 AWG");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeSix(), "EXIST") ? "EXISTING" : "TRUNK CBL");
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize : "#6 AWG";
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
							checkValue.Equals(circuit.getConductorTypeSix(), "EXIST") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeSix(), "EXIST") ? "EXISTING" : "N/A");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeSix(), "EXIST") ? "EXISTING" : "Open Air");
				}
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapconduitMaterialandSize(AcroFields form, int i, String acCircuit,
			PermitConduitConductorSectionEntity circuit, UserSettingEntity userSetting, PermitEntity permitEntity,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, int conductorQty, List<Integer> acNumberOfConductors,
			List<String> acCircuitEnvironment, List<String> acTradeSize, String conduitRun, int sheetIndex,
			String minGroundWireSize) {
		try {

			Integer numberOfConductor = acNumberOfConductors != null && i <= acNumberOfConductors.size()
					&& acNumberOfConductors.get(i - 1) != null ? acNumberOfConductors.get(i - 1) : 1;
			conductorQty = conductorQty * numberOfConductor;
			String tradeSize = acTradeSize != null && i <= acTradeSize.size() && acTradeSize.get(i - 1) != null
					? acTradeSize.get(i - 1)
					: "";
			String acCircuitEnv = acCircuitEnvironment != null && i <= acCircuitEnvironment.size()
					&& acCircuitEnvironment.get(i - 1) != null ? acCircuitEnvironment.get(i - 1) : "";
			String conduitSize = checkValue.Equals(acCircuitEnv, "ATTIC")
					&& checkValue.Equals(conduitRun, "Romex in Attic")
							? "N/A"
							: checkValue.Equals(acCircuitEnv, "UNDERGROUND")
									? pvcConduitSize.getConduitSizePVC(tradeSize, conductorQty)
									: emtConduitSize.getConduitSizeEMT(tradeSize, conductorQty);
			String conduitType = checkValue.Equals(acCircuitEnv, "ATTIC")
					&& checkValue.Equals(conduitRun, "Romex in Attic") ? "Open Air"
							: checkValue.Equals(acCircuitEnv, "UNDERGROUND") ? "PVC" : "EMT";
			String conductorType = checkValue.Equals(acCircuitEnv, "ATTIC")
					&& checkValue.Equals(conduitRun, "Romex in Attic") ? "NM-B" : "THWN-2";
			if (circuit != null) {
				if (checkValue.contains(acCircuit, "-")
						&& checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")) {

					if (checkValue.Equals(circuit.getConductorTypeSevenExisting(), true)
							|| checkValue.Equals(permitEntity.getExistAcJunctionBox(), true)) {
						mapExistingConduitMaterialandSize(form, i, sheetIndex);

					} else {

						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeSeven(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeSeven(), "EXIST") ? "EXISTING"
										: conductorType);
						String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
								: checkValue.Equals(conductorType, "NM-B") ? tradeSize
										: sharedUtils.getGroundSizeValue(circuit.getConductorSizeSeven(), tradeSize,
												userSetting.getMinimumACGroundCon(),
												userSetting.getMinimumACGroConOther());
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeSeven(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeSeven(), "EXIST") ? "EXISTING" : conduitType);

					}

				} else if (checkValue.contains(acCircuit, "-")
						&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {

					if (checkValue.Equals(circuit.getConductorTypeEightExisting(), true)
							|| checkValue.Equals(permitEntity.getExistAcCombiner(), true)) {
						mapExistingConduitMaterialandSize(form, i, sheetIndex);

					} else {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeEight(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeEight(), "EXIST") ? "EXISTING"
										: conductorType);
						String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
								: checkValue.Equals(conductorType, "NM-B") ? tradeSize
										: sharedUtils.getGroundSizeValue(circuit.getConductorSizeEight(), tradeSize,
												userSetting.getMinimumACGroundCon(),
												userSetting.getMinimumACGroConOther());
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeEight(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeEight(), "EXIST") ? "EXISTING" : conduitType);
					}

				} else if (checkValue.contains(acCircuit, "-")
						&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")) {

					if (checkValue.Equals(circuit.getConductorTypeNineExisting(), true)
							|| checkValue.Equals(permitEntity.getExistAcDisconnect(), true)) {
						mapExistingConduitMaterialandSize(form, i, sheetIndex);

					} else {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeNine(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeNine(), "EXIST") ? "EXISTING"
										: conductorType);
						String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
								: checkValue.Equals(conductorType, "NM-B") ? tradeSize
										: sharedUtils.getGroundSizeValue(circuit.getConductorSizeNine(), tradeSize,
												userSetting.getMinimumACGroundCon(),
												userSetting.getMinimumACGroConOther());
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeNine(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeNine(), "EXIST") ? "EXISTING" : conduitType);
					}

				} else if (checkValue.contains(acCircuit, "-")
						&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")) {

					if (checkValue.Equals(circuit.getConductorTypeNineTwoExisting(), true)
							|| checkValue.Equals(permitEntity.getExistAcDisconnect(), true)) {
						mapExistingConduitMaterialandSize(form, i, sheetIndex);

					} else {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeNineTwo(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeNineTwo(), "EXIST") ? "EXISTING"
										: conductorType);
						String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
								: checkValue.Equals(conductorType, "NM-B") ? tradeSize
										: sharedUtils.getGroundSizeValue(circuit.getConductorSizeNineTwo(), tradeSize,
												userSetting.getMinimumACGroundCon(),
												userSetting.getMinimumACGroConOther());
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeNineTwo(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeNineTwo(), "EXIST") ? "EXISTING" : conduitType);
					}

				} else if (checkValue.contains(acCircuit, "-")
						&& checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")) {

					if (checkValue.Equals(circuit.getConductorTypeTenExisting(), true)
							|| checkValue.Equals(permitEntity.getExistProductionMeter(), true)) {
						mapExistingConduitMaterialandSize(form, i, sheetIndex);

					} else {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeTen(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeTen(), "EXIST") ? "EXISTING" : conductorType);
						String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
								: checkValue.Equals(conductorType, "NM-B") ? tradeSize
										: sharedUtils.getGroundSizeValue(circuit.getConductorSizeTen(), tradeSize,
												userSetting.getMinimumACGroundCon(),
												userSetting.getMinimumACGroConOther());
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeTen(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeTen(), "EXIST") ? "EXISTING" : conduitType);
					}

				} else if (checkValue.contains(acCircuit, "-")
						&& checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")) {

					if (checkValue.Equals(circuit.getConductorTypeElevenExisting(), true)
							|| checkValue.Equals(permitEntity.getExistSubPanel(), true) || checkValue
									.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL)) {
						mapExistingConduitMaterialandSize(form, i, sheetIndex);

					} else {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeEleven(), "EXIST") ? "EXISTING"
										: conductorType);
						String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
								: checkValue.Equals(conductorType, "NM-B") ? tradeSize
										: sharedUtils.getGroundSizeValue(circuit.getConductorSizeEleven(), tradeSize,
												userSetting.getMinimumACGroundCon(),
												userSetting.getMinimumACGroConOther());
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeEleven(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeEleven(), "EXIST") ? "EXISTING" : conduitType);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void mapExistingConduitMaterialandSize(AcroFields form, int i, int sheetIndex) {
		try {
			form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "EXISTING");
			form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
			form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
			form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}

	public void mapCircuitsafterRevision(List<String> acTradeSize, AcroFields form, String acCircuit, int i,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, UserSettingEntity userSetting,
			int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo, String minGroundWireSize) {
		try {

			String tradeSize = acTradeSize != null && i <= acTradeSize.size() && acTradeSize.get(i - 1) != null
					? acTradeSize.get(i - 1)
					: "";

			if (checkValue.contains(acCircuit, "-") && checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")) {

				if ((checkValue.Equals(circuit.getConductorTypeSixExisting(), true))
						|| (checkValue.Equals(permitEntity.getExistInverter(), true))) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeSix(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeSix(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeSix(), "Other")
											? circuit.getConductorTypeSixOther()
											: circuit.getConductorTypeSix() != null
													? checkValue.Equals(circuit.getConductorTypeSix(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeSix()
													: "");
					String conductorType = checkValue.Equals(circuit.getConductorTypeSix(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorSizeSix(), "Other")
									? circuit.getConductorSizeSixOther()
									: circuit.getConductorSizeSix() != null ? circuit.getConductorSizeSix() : "";
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(conductorType, "NM-B") ? tradeSize
									: checkValue.Equals(tradeSize, "EXISTING") ? "EXISTING"
											: checkValue.Equals(circuit.getConductorTypeSix(), "PV Wire")
													|| checkValue.Equals(circuit.getConductorTypeSix(), "Trunk Cable")
													|| (checkValue.Equals(circuit.getConductorTypeSix(), "Other")
															&& (checkValue.Equals(circuit.getConductorTypeSixOther(),
																	"PV Wire")
																	|| checkValue.Equals(
																			circuit.getConductorTypeSixOther(),
																			"Trunk Cable")))
																					? "#6 AWG"
																					: sharedUtils.getGroundSizeValue(
																							circuit.getConductorSizeSix(),
																							tradeSize,
																							userSetting
																									.getMinimumACGroundCon(),
																							userSetting
																									.getMinimumACGroConOther());
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeSix(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeSix(), "Other")
											? circuit.getConduitSizeSixOther()
											: circuit.getConduitSizeSix() != null ? circuit.getConduitSizeSix() : "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeSix(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeSix(), "Other")
											? circuit.getConduitTypeSixOther()
											: circuit.getConduitTypeSix() != null ? circuit.getConduitTypeSix() : "");
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
							checkValue.NotEquals(circuit.getConductorQtySix(), "Other")
									? getQty(circuit.getConductorQtySix())
									: getQty(circuit.getConductorQtySixOther() + ""));
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")) {

				if ((checkValue.Equals(circuit.getConductorTypeSevenExisting(), true))
						|| (checkValue.Equals(permitEntity.getExistAcJunctionBox(), true))) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeSeven(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeSeven(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeSeven(), "Other")
											? circuit.getConductorTypeSevenOther()
											: circuit.getConductorTypeSeven() != null
													? checkValue.Equals(circuit.getConductorTypeSeven(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeSeven()
													: "");
					String conductorType = checkValue.Equals(circuit.getConductorTypeSeven(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeSeven(), "Other")
									? circuit.getConductorTypeSevenOther()
									: circuit.getConductorTypeSeven() != null ? circuit.getConductorTypeSeven() : "";
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(conductorType, "NM-B") ? tradeSize
									: checkValue.Equals(tradeSize, "EXISTING") ? "EXISTING"
											: checkValue.Equals(circuit.getConductorTypeSeven(), "PV Wire")
													|| checkValue.Equals(circuit.getConductorTypeSeven(), "Trunk Cable")
													|| (checkValue.Equals(circuit.getConductorTypeSeven(), "Other")
															&& (checkValue.Equals(circuit.getConductorTypeSevenOther(),
																	"PV Wire")
																	|| checkValue.Equals(
																			circuit.getConductorTypeSevenOther(),
																			"Trunk Cable")))
																					? "#6 AWG"
																					: sharedUtils.getGroundSizeValue(
																							circuit.getConductorSizeSeven(),
																							tradeSize,
																							userSetting
																									.getMinimumACGroundCon(),
																							userSetting
																									.getMinimumACGroConOther());
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeSeven(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeSeven(), "Other")
											? circuit.getConduitSizeSevenOther()
											: circuit.getConduitSizeSeven() != null ? circuit.getConduitSizeSeven()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeSeven(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeSeven(), "Other")
											? circuit.getConduitTypeSevenOther()
											: circuit.getConduitTypeSeven() != null ? circuit.getConduitTypeSeven()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
							checkValue.NotEquals(circuit.getConductorQtySeven(), "Other")
									? getQty(circuit.getConductorQtySeven())
									: getQty(circuit.getConductorQtySevenOther() + ""));
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {

				if (checkValue.Equals(circuit.getConductorTypeEightExisting(), true)
						|| checkValue.Equals(permitEntity.getExistAcCombiner(), true)) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeEight(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeEight(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeEight(), "Other")
											? circuit.getConductorTypeEightOther()
											: circuit.getConductorTypeEight() != null
													? checkValue.Equals(circuit.getConductorTypeEight(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeEight()
													: "");
					String conductorType = checkValue.Equals(circuit.getConductorTypeEight(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeEight(), "Other")
									? circuit.getConductorTypeEightOther()
									: circuit.getConductorTypeEight() != null ? circuit.getConductorTypeEight() : "";
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(conductorType, "NM-B") ? tradeSize
									: checkValue.Equals(tradeSize, "EXISTING") ? "EXISTING"
											: checkValue.Equals(circuit.getConductorTypeEight(), "PV Wire")
													|| checkValue.Equals(circuit.getConductorTypeEight(), "Trunk Cable")
													|| (checkValue.Equals(circuit.getConductorTypeEight(), "Other")
															&& (checkValue.Equals(circuit.getConductorTypeEightOther(),
																	"PV Wire")
																	|| checkValue.Equals(
																			circuit.getConductorTypeEightOther(),
																			"Trunk Cable")))
																					? "#6 AWG"
																					: sharedUtils.getGroundSizeValue(
																							circuit.getConductorSizeEight(),
																							tradeSize,
																							userSetting
																									.getMinimumACGroundCon(),
																							userSetting
																									.getMinimumACGroConOther());
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeEight(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeEight(), "Other")
											? circuit.getConduitSizeEightOther()
											: circuit.getConduitSizeEight() != null ? circuit.getConduitSizeEight()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeEight(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeEight(), "Other")
											? circuit.getConduitTypeEightOther()
											: circuit.getConduitTypeEight() != null ? circuit.getConduitTypeEight()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
							checkValue.NotEquals(circuit.getConductorQtyEight(), "Other")
									? getQty(circuit.getConductorQtyEight())
									: getQty(circuit.getConductorQtyEightOther() + ""));
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")) {

				if (checkValue.Equals(circuit.getConductorTypeNineExisting(), true)
						|| checkValue.Equals(permitEntity.getExistAcDisconnect(), true)) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeNine(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeNine(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeNine(), "Other")
											? circuit.getConductorTypeNineOther()
											: circuit.getConductorTypeNine() != null
													? checkValue.Equals(circuit.getConductorTypeNine(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeNine()
													: "");
					String conductorType = checkValue.Equals(circuit.getConductorTypeNine(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeNine(), "Other")
									? circuit.getConductorTypeNineOther()
									: circuit.getConductorTypeNine() != null ? circuit.getConductorTypeNine() : "";
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(conductorType, "NM-B") ? tradeSize
									: checkValue.Equals(tradeSize, "EXISTING") ? "EXISTING"
											: checkValue.Equals(circuit.getConductorTypeNine(), "PV Wire")
													|| checkValue.Equals(circuit.getConductorTypeNine(), "Trunk Cable")
													|| (checkValue.Equals(circuit.getConductorTypeNine(), "Other")
															&& (checkValue.Equals(circuit.getConductorTypeNineOther(),
																	"PV Wire")
																	|| checkValue.Equals(
																			circuit.getConductorTypeNineOther(),
																			"Trunk Cable")))
																					? "#6 AWG"
																					: sharedUtils.getGroundSizeValue(
																							circuit.getConductorSizeNine(),
																							tradeSize,
																							userSetting
																									.getMinimumACGroundCon(),
																							userSetting
																									.getMinimumACGroConOther());
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeNine(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeNine(), "Other")
											? circuit.getConduitSizeNineOther()
											: circuit.getConduitSizeNine() != null ? circuit.getConduitSizeNine() : "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeNine(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeNine(), "Other")
											? circuit.getConduitTypeNineOther()
											: circuit.getConduitTypeNine() != null ? circuit.getConduitTypeNine() : "");
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
							checkValue.NotEquals(circuit.getConductorQtyNine(), "Other")
									? getQty(circuit.getConductorQtyNine())
									: getQty(circuit.getConductorQtyNineOther() + ""));
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")) {

				if (checkValue.Equals(circuit.getConductorTypeTenExisting(), true)
						|| checkValue.Equals(permitEntity.getExistProductionMeter(), true)) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "EXISTING");

				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeTen(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeTen(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeTen(), "Other")
											? circuit.getConductorTypeTenOther()
											: circuit.getConductorTypeTen() != null
													? checkValue.Equals(circuit.getConductorTypeTen(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeTen()
													: "");
					String conductorType = checkValue.Equals(circuit.getConductorTypeTen(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeTen(), "Other")
									? circuit.getConductorTypeTenOther()
									: circuit.getConductorTypeTen() != null ? circuit.getConductorTypeTen() : "";

					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(conductorType, "NM-B") ? tradeSize
									: checkValue.Equals(tradeSize, "EXISTING") ? "EXISTING"
											: checkValue.Equals(circuit.getConductorTypeTen(), "PV Wire")
													|| checkValue.Equals(circuit.getConductorTypeTen(), "Trunk Cable")
													|| (checkValue.Equals(circuit.getConductorTypeTen(), "Other")
															&& (checkValue.Equals(circuit.getConductorTypeTenOther(),
																	"PV Wire")
																	|| checkValue.Equals(
																			circuit.getConductorTypeTenOther(),
																			"Trunk Cable")))
																					? "#6 AWG"
																					: sharedUtils.getGroundSizeValue(
																							circuit.getConductorSizeTen(),
																							tradeSize,
																							userSetting
																									.getMinimumACGroundCon(),
																							userSetting
																									.getMinimumACGroConOther());
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeTen(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeTen(), "Other")
											? circuit.getConduitSizeTenOther()
											: circuit.getConduitSizeTen() != null ? circuit.getConduitSizeTen() : "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeTen(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeTen(), "Other")
											? circuit.getConduitTypeTenOther()
											: circuit.getConduitTypeTen() != null ? circuit.getConduitTypeTen() : "");
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
							checkValue.NotEquals(circuit.getConductorQtyTen(), "Other")
									? getQty(circuit.getConductorQtyTen())
									: getQty(circuit.getConductorQtyTenOther() + ""));
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")) {

				if (checkValue.Equals(circuit.getConductorTypeElevenExisting(), true)
						|| checkValue.Equals(permitEntity.getExistSubPanel(), true)
						|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL)) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", tradeSize);
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeEleven(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeEleven(), "Other")
											? circuit.getConductorTypeElevenOther()
											: circuit.getConductorTypeEleven() != null
													? checkValue.Equals(circuit.getConductorTypeEleven(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeEleven()
													: "");
					String conductorType = checkValue.Equals(circuit.getConductorTypeEleven(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeEleven(), "Other")
									? circuit.getConductorTypeElevenOther()
									: circuit.getConductorTypeEleven() != null ? circuit.getConductorTypeEleven() : "";
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(conductorType, "NM-B") ? tradeSize
									: checkValue.Equals(tradeSize, "EXISTING") ? "EXISTING"
											: checkValue.Equals(circuit.getConductorTypeEleven(), "PV Wire")
													|| checkValue.Equals(circuit.getConductorTypeEleven(),
															"Trunk Cable")
													|| (checkValue.Equals(circuit.getConductorTypeEleven(), "Other")
															&& (checkValue.Equals(circuit.getConductorTypeElevenOther(),
																	"PV Wire")
																	|| checkValue.Equals(
																			circuit.getConductorTypeElevenOther(),
																			"Trunk Cable")))
																					? "#6 AWG"
																					: sharedUtils.getGroundSizeValue(
																							circuit.getConductorSizeEleven(),
																							tradeSize,
																							userSetting
																									.getMinimumACGroundCon(),
																							userSetting
																									.getMinimumACGroConOther());
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", condGround);

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeEleven(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeEleven(), "Other")
											? circuit.getConduitSizeElevenOther()
											: circuit.getConduitSizeEleven() != null ? circuit.getConduitSizeEleven()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeEleven(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeEleven(), "Other")
											? circuit.getConduitTypeElevenOther()
											: circuit.getConduitTypeEleven() != null ? circuit.getConduitTypeEleven()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
							checkValue.NotEquals(circuit.getConductorQtyEleven(), "Other")
									? getQty(circuit.getConductorQtyEleven())
									: getQty(circuit.getConductorQtyElevenOther() + ""));
				}

			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private String getQty(String qty) {
		return String.valueOf(checkValue.convertToInteger(qty) + 1);
	}

	public void mapBackFeedOcpd(AcroFields form, PlansetUtils plansetUtils,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, int sheetIndex) {
		try {
			if (plansetUtils.getStringQty() != null && !plansetUtils.getStringQty().isEmpty()) {
				for (int i = 0; i < plansetUtils.getStringQty().size() && i < 8; i++) {
					form.setField(sheetIndex + "-" + "BRANCH " + i, " " + (i + 1));
					form.setField(sheetIndex + "-" + "MOD QTY" + i, " " + plansetUtils.getStringQty().get(i) + "");
					if (plansetUtils.getStringStringE003() != null && i < plansetUtils.getStringStringE003().size()) {
						form.setField(sheetIndex + "-" + "BRANCH OCPD" + i,
								" " + plansetUtils.getStringStringE003().get(i));
					}
				}
			}

//		
			if (permitProjectSiteInfo != null
					&& checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Back-fed Breaker")) {
				if (checkValue.Equals(permitProjectSiteInfo.getSolarInterconnection(), "Other")) {
					if (permitProjectSiteInfo.getSolarInterconnectionOther() != null
							&& permitProjectSiteInfo.getSolarInterconnectionOther().contains("A")) {
						form.setField(sheetIndex + "-" + "BACK-FEED SOLAR BREAKER",
								permitProjectSiteInfo.getSolarInterconnectionOther());
					} else
						form.setField(sheetIndex + "-" + "BACK-FEED SOLAR BREAKER",
								permitProjectSiteInfo.getSolarInterconnectionOther() + "A");
				} else if (permitProjectSiteInfo.getSolarInterconnection() != null) {
					form.setField(sheetIndex + "-" + "BACK-FEED SOLAR BREAKER",
							permitProjectSiteInfo.getSolarInterconnection() + "A");
				}
			} else {
				form.setField(sheetIndex + "-" + "BACK-FEED SOLAR BREAKER", "N/A");
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public File buildingPDFE003(PermitHomeSiteInfoEntity permitHomeSite,
			PermitAdditionalInfoEntityResult permitAdditionalInfo, PermitEntity permitEntity,
			PermitConduitConductorSectionEntity circuit, PermitArrayEntityResultSecond permitArraysEntityResult,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, PlansetUtils plansetUtils, UserSettingEntity userSetting,
			Boolean includeBattery, DCCombinerDisconnectEntity dcCombinerDisconnect, ACDisconnect acDisconnect,
			ACCombinerSLC acCombiner, DCCombinerDisconnectEntity jBox, Cmodulev2 moduleInfo, List<Inverters> inverters,
			ACDisconnect secondacDisconnect, ACCombinerSLC slcAcCombiner, Inverters microInverterInfo,
			AuthentificationEntity userConnectedEntity, PermtiWeatherEntityResult permtiWeather,
			PermitLayoutEntity permitLayoutEntity, PermitAdvEntityResult editPermitAdvRequest,
			LeasePPAMeter leasePPAMeter, List<String> acCircuitEnvironment, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, PdfReader reader, int sheetIndex, String filePath,
			Integer qtyAcCombiner, AHJNotesModel ahjNotes, Integer firsttInverterQty, Integer secondtInverterQty,
			String electNote, ElectricalUtilityEntity electricalCompany,
			PermitEnergyBatterySystem permitEnergyBatterySystem) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		File fileRe = null;

		fourPerCentAverageHigh = 0;
		iScRef = "";
		systemCircuitEnvironment = "";

		nec31016Column90 = 0;
		acAmpacityCorrectionB2a = "";
		nec310 = 0;

		numberOfConductor = 1;
		tradeSize = "";
		nEC_310_16_B_16 = new NEC_310_16_B_16();

		iacMaxMicro = "";
		iacMaxMicroCalcul = 1.00;

		requiredAMPACITY = 0;
		conductorQty = 0;

		isRoofMounted = false;
		isThereGroundOrPole = false;
		if ((checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")
				|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "AC Modules"))) {

			try {

				fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E003-MICRO" + permitEntity.getId() + "-"
						+ sheetIndex + ".pdf");
				if (fileRe.exists()) {
					fileRe.delete();
					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E003-MICRO" + permitEntity.getId() + "-"
							+ sheetIndex + ".pdf");
				}
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();

				// A.B remove sheet index if exist when the project was uploaded
				if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
					getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
				}

				PdfReader readerOriginNEC = new PdfReader(
						Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-E003-MICRO.pdf");
				PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-E003-MICRO.pdf");
				// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
				getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "E003");
				// A.B: Set PDF Font For Revision
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
						sheetIndex);
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
						sheetIndex);

				// A.B CR-3250 03-30 Logo & Signature Mapping
				logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

				// A.B 07-14-2021 CR-3064
				form.setField(sheetIndex + "-" + "SPECIAL-NOTE-E-003", ahjNotes.getE003Note());

				if (checkValue.isStringNotEmpty(ahjNotes.getTldNote())
						&& (plansetUtils.getQtyACDiscoGRND() > 0 || plansetUtils.getQtyACDisconnect() > 0)) {
					Rectangle linkLocation1 = new Rectangle(40, 40, 240, 140);
					PdfAnnotation stamp1 = PdfAnnotation.createPopup(stamper.getWriter(), linkLocation1,
							ahjNotes.getTldNote(), true);
					stamper.addAnnotation(stamp1, 1);
				}
				equipmentTabMapping(form, moduleInfo, microInverterInfo, permitArraysEntityResult, sheetIndex,
						permitProjectSiteInfo, qtyAcCombiner, plansetUtils);
				if (circuit != null) {

					// ----------- CR-1580 TEST------------------//
					tldDigaramMapping.tldSheetMapping(stamper, moduleInfo, permitEntity, editPermitAdvRequest, circuit,
							permitProjectSiteInfo, permitHomeSite, permitArraysEntityResult, null, null,
							microInverterInfo, plansetUtils, null, acDisconnect, secondacDisconnect, null, null,
							slcAcCombiner, firsttInverterQty, secondtInverterQty, inverters, permitAdditionalInfo,
							permitEnergyBatterySystem);

					/// --- CR 800 planset mapping update ---///
					Boolean wireTapSetting = userSettingRepo
							.findWireTapSettingByUserId(permitEntity.getAuthentificationEntity().getId());
					notesMapping.noteMapping(form, permitProjectSiteInfo, sheetIndex, electNote, wireTapSetting);
					
					
					//W.A  17-10-2022 CR-2228
					 if(Boolean.TRUE.equals(wireTapSetting))
					{notesMapping.pointOfConnectionNoteMapping(stamper);}
					
					//W.A  14-07-2022 CR-859
					notesMapping.wiretapnoteMapping(form, permitProjectSiteInfo, sheetIndex);

					permitHomeMapping(form, permitHomeSite, sheetIndex);
					/// ------------------------------------///

					/// --- PR-652 planset mapping update ---///

					componentListQtyMapping(form, permitEntity.getId(), dcCombinerDisconnect, jBox,
							permitProjectSiteInfo, acDisconnect, leasePPAMeter, acCombiner, secondacDisconnect,
							slcAcCombiner, sheetIndex, permitAdditionalInfo, plansetUtils,qtyAcCombiner);

					/// ------------------------------------///

					///////////////////////////// Condactor Table //////////////////////
					String minGroundWireSize = ahjNotes.getMinGroundWireSize();
					String dcMinGroundWireSize = checkValue.Equals(
							permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter") ? minGroundWireSize
									: null;
					
					// A.B 08/25/2022 CR-1030
					if (Boolean.TRUE.equals(includeBattery)) {
						/// ------------------------- DC CIRCUIT Mapping ---------------------///
						dcConductorMapping.conductorMapping(form, sheetIndex, userSetting, dcMinGroundWireSize,
								userConnectedEntity.getRoleEntity().getId(), permitEntity.getId(),
								circuit.getMapFromUserInput());
						/// ------------------------- AC CIRCUIT Mapping ---------------------///
						acConductorMapping.conductorMapping(form, sheetIndex, userSetting, dcMinGroundWireSize,
								acTradeSize, userConnectedEntity.getRoleEntity().getId(), permitEntity.getId(),
								circuit.getMapFromUserInput(), acNumberOfConductors, acCircuitEnvironment,
								permitLayoutEntity.getConduitRun(), permitHomeSite,
								plansetUtils.getNumberOfBranchCircuit());
					} else {
						quatrePourCentAverageHigh(permtiWeather);

						iScRef = getiscRef(moduleInfo);

						systemCircuitEnvironment = getCircuitEnvironment(permitArraysEntityResult);
						mapAmpacityCorrection(systemCircuitEnvironment, fourPerCentAverageHigh, iScRef);

						nec310 = getnec310(nec31016Column90);

						getnec31016(nec310);

						iacMaxMicroCalcul(microInverterInfo);
						String acCircuit = generateCircuitList.renameAcComponent(circuit.getComponentOrder());
						Boolean stepACCombiner = acCircuit.contains("AC COMBINER/LOAD CENTER");
						int indexAcCombiner = acCircuit.contains("AC COMBINER/LOAD CENTER")
								? generateCircuitList.getAcCombinerIndex(acCircuit) + 1
								: 0;

						
						if (userConnectedEntity.getRoleEntity().getId() == 2
								|| (userConnectedEntity.getRoleEntity().getId() != 2
										&& (!checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {

							dc1ExistingMappingbeforeRev(form, circuit, permitEntity, sheetIndex, dcMinGroundWireSize);
						} else if ((userConnectedEntity.getRoleEntity().getId() != 2
								&& Boolean.TRUE.equals(circuit.getMapFromUserInput()))) {

							dc1ExistingMappingafterRevision(form, circuit, userSetting, permitEntity, sheetIndex,
									dcMinGroundWireSize);
						}

						for (int i = 1; i < acCircuit.split("-").length + 1; i++) {

							getRequiredAmpacity(i, plansetUtils, indexAcCombiner, stepACCombiner, iacMaxMicroCalcul);
							String acMinGroundWireSize = i == 1 ? minGroundWireSize : null;
							if (userConnectedEntity.getRoleEntity().getId() == 2
									|| (userConnectedEntity.getRoleEntity().getId() != 2
											&& (!Boolean.TRUE.equals(circuit.getMapFromUserInput())))) {

								if (circuit != null) {

									conductorQtyCalcul(form, acCircuit, i, plansetUtils.getNumberOfBranchCircuit(),
											sheetIndex, permitHomeSite, circuit, indexAcCombiner, stepACCombiner);

									nec31016Column90 = getnec31016Column90(requiredAMPACITY, fourPerCentAverageHigh);

									nec310 = getnec310ACcircuit(nec31016Column90);
									getTradeSizeACcircuit(nec310);

									if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")) {
										mapInverterCircuit(form, circuit, permitEntity, i, sheetIndex,
												acMinGroundWireSize);
									} else {
										mapconduitMaterialandSize(form, i, acCircuit, circuit, userSetting,
												permitEntity, permitProjectSiteInfo, conductorQty, acNumberOfConductors,
												acCircuitEnvironment, acTradeSize, permitLayoutEntity.getConduitRun(),
												sheetIndex, acMinGroundWireSize);
									}
								}
							} else if (userConnectedEntity.getRoleEntity().getId() != 2
									&& Boolean.TRUE.equals(circuit.getMapFromUserInput())) {
								mapCircuitsafterRevision(acTradeSize, form, acCircuit, i, circuit, permitEntity,
										userSetting, sheetIndex, permitProjectSiteInfo, acMinGroundWireSize);
							}
						}
					}

				}
				mapBackFeedOcpd(form, plansetUtils, permitProjectSiteInfo, sheetIndex);
				electricUtilityNumber.mapMeterNumber(form, sheetIndex, "E003", permitHomeSite, electricalCompany);
				stamper.close();
				reader.close();

			} catch (IOException | DocumentException e) {
				e.printStackTrace();
				technicalProblemMsg.traiterException(e);
			}
		}
		return fileRe;

	}

	public String getACAmpacityCorrectionB2aMultipleString(String operateTemp75String) {
		String ampacityCorrection = "1.00";
		try {
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
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

		return ampacityCorrection;
	};

	public String getACAmpacityCorrectionB2aMultipleMicro(String operateTemp75String) {
		String ampacityCorrection = "";
		try {
			if (checkValue.isStringDouble(operateTemp75String)) {
				Double operateTemp = Double.parseDouble(operateTemp75String);

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
				} else
					ampacityCorrection = "0.00";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

		return ampacityCorrection;
	};

	public String getACAmpacityCorrectionB2aMultipleMicro(double operateTemp75) {
		String ampacityCorrectionAC = "1.00";
		try {
			if (operateTemp75 >= 21 && operateTemp75 <= 25) {
				ampacityCorrectionAC = "1.05";
			} else if (operateTemp75 >= 26 && operateTemp75 <= 30) {
				ampacityCorrectionAC = "1.00";
			} else if (operateTemp75 >= 31 && operateTemp75 <= 35) {
				ampacityCorrectionAC = "0.94";
			} else if (operateTemp75 >= 36 && operateTemp75 <= 40) {
				ampacityCorrectionAC = "0.88";
			} else if (operateTemp75 >= 41 && operateTemp75 <= 45) {
				ampacityCorrectionAC = "0.82";
			} else if (operateTemp75 >= 46 && operateTemp75 <= 50) {
				ampacityCorrectionAC = "0.75";
			} else if (operateTemp75 >= 51 && operateTemp75 <= 55) {
				ampacityCorrectionAC = "0.67";
			} else if (operateTemp75 >= 56 && operateTemp75 <= 60) {
				ampacityCorrectionAC = "0.58";
			} else if (operateTemp75 >= 61 && operateTemp75 <= 65) {
				ampacityCorrectionAC = "0.47";
			} else if (operateTemp75 >= 66 && operateTemp75 <= 70) {
				ampacityCorrectionAC = "0.33";
			} else if (operateTemp75 >= 71 && operateTemp75 <= 75) {
				ampacityCorrectionAC = "0.00";
			} else if (operateTemp75 >= 76 && operateTemp75 <= 80) {
				ampacityCorrectionAC = "0.00";
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return ampacityCorrectionAC;
	};

	public String getDCAmpacityCorrectionB3aString(PermitConduitConductorSectionEntity circuit, String origin,
			String numberOfConductorString) {

		try {

			if (circuit != null) {
				if (checkValue.Equals(origin, "PV MODULE")) {
					if (checkValue.Equals(circuit.getConduitSize(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitType() != null) {
						return getCorrectionB3aString(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "DC/DC CONVERTER")) {
					if (checkValue.Equals(circuit.getConduitSizeTwo(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitTypeTwo() != null) {
						return getCorrectionB3aString(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "JUNCTION BOX")) {
					if (checkValue.Equals(circuit.getConduitSizeThree(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitTypeThree() != null) {
						return getCorrectionB3aString(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "DC COMBINER")) {
					if (checkValue.Equals(circuit.getConduitSizeFour(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitTypeFour() != null) {
						return getCorrectionB3aString(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "DC DISCONNECT")) {
					if (checkValue.Equals(circuit.getConduitSizeFive(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitTypeFive() != null) {
						return getCorrectionB3aString(numberOfConductorString);
					}

				}
			}
			return getCorrectionB3aString(numberOfConductorString);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return getCorrectionB3aString(numberOfConductorString);
		}

	}

	public String getDCAmpacityCorrectionB3aMicro(PermitConduitConductorSectionEntity circuit, String origin,
			String numberOfConductorString) {

		try {

			if (circuit != null) {
				if (checkValue.Equals(origin, "PV MODULE")) {
					if (checkValue.Equals(circuit.getConduitType(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitType() != null) {
						return getCorrectionB3aMicro(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "DC/DC CONVERTER")) {
					if (checkValue.Equals(circuit.getConduitTypeTwo(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitTypeTwo() != null) {
						return getCorrectionB3aMicro(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "JUNCTION BOX")) {
					if (checkValue.Equals(circuit.getConduitTypeThree(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitTypeThree() != null) {
						return getCorrectionB3aMicro(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "DC COMBINER")) {
					if (checkValue.Equals(circuit.getConduitTypeFour(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitTypeFour() != null) {
						return getCorrectionB3aMicro(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "DC DISCONNECT")) {
					if (checkValue.Equals(circuit.getConduitTypeFive(), "N/A")) {
						return "N/A";
					} else if (circuit.getConduitTypeFive() != null) {
						return getCorrectionB3aMicro(numberOfConductorString);
					}

				}
			}
			return getCorrectionB3aMicro(numberOfConductorString);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return getCorrectionB3aMicro(numberOfConductorString);
		}

	}

	public String getCorrectionB3aString(String numberOfConductorString) {

		String dcampacityCorrectionB3a = "";
		try {
			if (checkValue.isStringInt(numberOfConductorString)) {
				Integer numberOfConductor = Integer.parseInt(numberOfConductorString);
				if (numberOfConductor >= 1 && numberOfConductor <= 3) {
					dcampacityCorrectionB3a = "1.0";
				} else if (numberOfConductor >= 4 && numberOfConductor <= 6) {
					dcampacityCorrectionB3a = ".80";
				} else if (numberOfConductor >= 7 && numberOfConductor <= 9) {
					dcampacityCorrectionB3a = ".70";
				} else if (numberOfConductor >= 10 && numberOfConductor <= 20) {
					dcampacityCorrectionB3a = ".50";
				} else if (numberOfConductor >= 21 && numberOfConductor <= 30) {
					dcampacityCorrectionB3a = ".45";
				} else if (numberOfConductor >= 31 && numberOfConductor <= 40) {
					dcampacityCorrectionB3a = ".40";
				} else if (numberOfConductor >= 41) {
					dcampacityCorrectionB3a = ".35";
				} else
					dcampacityCorrectionB3a = "N/A";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

		return dcampacityCorrectionB3a;
	}

	public String getCorrectionB3aMicro(String numberOfConductorString) {

		String dcampacityCorrectionB3a = "";
		try {
			if (checkValue.isStringInt(numberOfConductorString)) {
				Integer numberOfConductor = Integer.parseInt(numberOfConductorString);
				if (numberOfConductor >= 1 && numberOfConductor <= 3) {
					dcampacityCorrectionB3a = "1.0";
				} else if (numberOfConductor >= 4 && numberOfConductor <= 6) {
					dcampacityCorrectionB3a = ".80";
				} else if (numberOfConductor >= 7 && numberOfConductor <= 9) {
					dcampacityCorrectionB3a = ".70";
				} else if (numberOfConductor >= 10 && numberOfConductor <= 20) {
					dcampacityCorrectionB3a = ".50";
				} else if (numberOfConductor >= 21 && numberOfConductor <= 30) {
					dcampacityCorrectionB3a = ".45";
				} else if (numberOfConductor >= 31 && numberOfConductor <= 40) {
					dcampacityCorrectionB3a = ".40";
				} else if (numberOfConductor >= 41) {
					dcampacityCorrectionB3a = ".35";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return dcampacityCorrectionB3a;
	}

	public String getDCAmpacityCorrectionB2aMultiple(String operateTemp90String) {
		String ampacityCorrection = "1.00";
		try {
			if (checkValue.isStringInt(operateTemp90String)) {
				Integer operateTemp90 = Integer.parseInt(operateTemp90String);
				if (operateTemp90 >= 21 && operateTemp90 <= 25) {
					ampacityCorrection = "1.04";
				} else if (operateTemp90 >= 26 && operateTemp90 <= 30) {
					ampacityCorrection = "1.00";
				} else if (operateTemp90 >= 31 && operateTemp90 <= 35) {
					ampacityCorrection = "0.96";
				} else if (operateTemp90 >= 36 && operateTemp90 <= 40) {
					ampacityCorrection = "0.91";
				} else if (operateTemp90 >= 41 && operateTemp90 <= 45) {
					ampacityCorrection = "0.87";
				} else if (operateTemp90 >= 46 && operateTemp90 <= 50) {
					ampacityCorrection = "0.82";
				} else if (operateTemp90 >= 51 && operateTemp90 <= 55) {
					ampacityCorrection = "0.76";
				} else if (operateTemp90 >= 56 && operateTemp90 <= 60) {
					ampacityCorrection = "0.71";
				} else if (operateTemp90 >= 61 && operateTemp90 <= 65) {
					ampacityCorrection = "0.65";
				} else if (operateTemp90 >= 66 && operateTemp90 <= 70) {
					ampacityCorrection = "0.58";
				} else if (operateTemp90 >= 71 && operateTemp90 <= 75) {
					ampacityCorrection = "0.50";
				} else if (operateTemp90 >= 76 && operateTemp90 <= 80) {
					ampacityCorrection = "0.41";
				} else if (operateTemp90 >= 81 && operateTemp90 <= 85) {
					ampacityCorrection = "0.29";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return ampacityCorrection;
	};

	public String getDCAmpacityCorrectionB2aString(String operateTemp90String) {
		String ampacityCorrection = "";
		try {
			Integer operateTemp90 = Integer.parseInt(operateTemp90String);
			if (operateTemp90 >= 21 && operateTemp90 <= 25) {
				ampacityCorrection = "1.04";
			} else if (operateTemp90 >= 26 && operateTemp90 <= 30) {
				ampacityCorrection = "1.00";
			} else if (operateTemp90 >= 31 && operateTemp90 <= 35) {
				ampacityCorrection = "0.96";
			} else if (operateTemp90 >= 36 && operateTemp90 <= 40) {
				ampacityCorrection = "0.91";
			} else if (operateTemp90 >= 41 && operateTemp90 <= 45) {
				ampacityCorrection = "0.87";
			} else if (operateTemp90 >= 46 && operateTemp90 <= 50) {
				ampacityCorrection = "0.82";
			} else if (operateTemp90 >= 51 && operateTemp90 <= 55) {
				ampacityCorrection = "0.76";
			} else if (operateTemp90 >= 56 && operateTemp90 <= 60) {
				ampacityCorrection = "0.71";
			} else if (operateTemp90 >= 61 && operateTemp90 <= 65) {
				ampacityCorrection = "0.65";
			} else if (operateTemp90 >= 66 && operateTemp90 <= 70) {
				ampacityCorrection = "0.58";
			} else if (operateTemp90 >= 71 && operateTemp90 <= 75) {
				ampacityCorrection = "0.50";
			} else if (operateTemp90 >= 76 && operateTemp90 <= 80) {
				ampacityCorrection = "0.41";
			} else if (operateTemp90 >= 81 && operateTemp90 <= 85) {
				ampacityCorrection = "0.29";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return ampacityCorrection;
	};

	public String getDCAmpacityCorrectionB2aMicro(double operateTemp90) {
		String ampacityCorrection = "";
		try {
			if (operateTemp90 >= 21 && operateTemp90 <= 25) {
				ampacityCorrection = "1.04";
			} else if (operateTemp90 >= 26 && operateTemp90 <= 30) {
				ampacityCorrection = "1.00";
			} else if (operateTemp90 >= 31 && operateTemp90 <= 35) {
				ampacityCorrection = "0.96";
			} else if (operateTemp90 >= 36 && operateTemp90 <= 40) {
				ampacityCorrection = "0.91";
			} else if (operateTemp90 >= 41 && operateTemp90 <= 45) {
				ampacityCorrection = "0.87";
			} else if (operateTemp90 >= 46 && operateTemp90 <= 50) {
				ampacityCorrection = "0.82";
			} else if (operateTemp90 >= 51 && operateTemp90 <= 55) {
				ampacityCorrection = "0.76";
			} else if (operateTemp90 >= 56 && operateTemp90 <= 60) {
				ampacityCorrection = "0.71";
			} else if (operateTemp90 >= 61 && operateTemp90 <= 65) {
				ampacityCorrection = "0.65";
			} else if (operateTemp90 >= 66 && operateTemp90 <= 70) {
				ampacityCorrection = "0.58";
			} else if (operateTemp90 >= 71 && operateTemp90 <= 75) {
				ampacityCorrection = "0.50";
			} else if (operateTemp90 >= 76 && operateTemp90 <= 80) {
				ampacityCorrection = "0.41";
			} else if (operateTemp90 >= 81 && operateTemp90 <= 85) {
				ampacityCorrection = "0.29";
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return ampacityCorrection;
	};

	public String getACAmpacityCorrectionB2aMicro(double operateTemp75) {
		String ampacityCorrectionAC = "";
		try {
			if (operateTemp75 >= 21 && operateTemp75 <= 25) {
				ampacityCorrectionAC = "1.05";
			} else if (operateTemp75 >= 26 && operateTemp75 <= 30) {
				ampacityCorrectionAC = "1.00";
			} else if (operateTemp75 >= 31 && operateTemp75 <= 35) {
				ampacityCorrectionAC = "0.94";
			} else if (operateTemp75 >= 36 && operateTemp75 <= 40) {
				ampacityCorrectionAC = "0.88";
			} else if (operateTemp75 >= 41 && operateTemp75 <= 45) {
				ampacityCorrectionAC = "0.82";
			} else if (operateTemp75 >= 46 && operateTemp75 <= 50) {
				ampacityCorrectionAC = "0.75";
			} else if (operateTemp75 >= 51 && operateTemp75 <= 55) {
				ampacityCorrectionAC = "0.67";
			} else if (operateTemp75 >= 56 && operateTemp75 <= 60) {
				ampacityCorrectionAC = "0.58";
			} else if (operateTemp75 >= 61 && operateTemp75 <= 65) {
				ampacityCorrectionAC = "0.47";
			} else if (operateTemp75 >= 66 && operateTemp75 <= 70) {
				ampacityCorrectionAC = "0.33";
			} else if (operateTemp75 >= 71 && operateTemp75 <= 75) {
				ampacityCorrectionAC = "0.00";
			} else if (operateTemp75 >= 76 && operateTemp75 <= 80) {
				ampacityCorrectionAC = "0.00";
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return ampacityCorrectionAC;
	};

	public String getACAmpacityCorrectionB2aMicro(String operateTemp75String) {
		String ampacityCorrection = "";
		try {
			if (checkValue.isStringDouble(operateTemp75String)) {
				Double operateTemp = Double.parseDouble(operateTemp75String);

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
				} else
					ampacityCorrection = "0.00";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

		return ampacityCorrection;
	};

}
