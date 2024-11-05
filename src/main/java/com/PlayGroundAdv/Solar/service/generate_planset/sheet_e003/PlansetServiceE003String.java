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
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.EMTConduitSize;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ElectricUtilityNumber;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.PVCConduitSize;
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
public class PlansetServiceE003String {

	final CheckValueTypesService checkValue;
	final PermitService permitService;
	final GetPDFReaderService getPDFReaderService;
	final InverterRepository inverterRepo;
	final ACDisconnectRepository aCDisconnectRepo;
	final PlansetLogo_SignatureMappingService logoSignMapping;
	final DcCombinerDiscoRepository dcCombinerRepo;
	final AcCombinerSLCRepository acCombinerRepo;
	final EssSystemMapping essSystemMapping;
	final JunctionBoxMapping junctionBoxMapping;
	final NotesMapping notesMapping;
	final EMTConduitSize emtConduitSize;
	final PVCConduitSize pvcConduitSize;
	final TLDDigaramMapping tldDigaramMapping;
	final TechnicalProblemMsg technicalProblemMsg;
	final ElectricUtilityNumber electricUtilityNumber;
	final UserSettingRepository userSettingRepo;
	final DCStringConductorMapping dcConductorMapping;
	final ACStringConductorMapping acConductorMapping;
	final ESSConnectorsRepository essConnectorsRepo;

	public PlansetServiceE003String(CheckValueTypesService checkValue, PermitService permitService,
			GetPDFReaderService getPDFReaderService, InverterRepository inverterRepo,
			ACDisconnectRepository aCDisconnectRepo, PlansetLogo_SignatureMappingService logoSignMapping,
			DcCombinerDiscoRepository dcCombinerRepo, AcCombinerSLCRepository acCombinerRepo,
			EssSystemMapping essSystemMapping, JunctionBoxMapping junctionBoxMapping, NotesMapping notesMapping,
			EMTConduitSize emtConduitSize, PVCConduitSize pvcConduitSize, TLDDigaramMapping tldDigaramMapping,
			TechnicalProblemMsg technicalProblemMsg, ElectricUtilityNumber electricUtilityNumber,
			UserSettingRepository userSettingRepo, DCStringConductorMapping dcConductorMapping,
			ACStringConductorMapping acConductorMapping, ESSConnectorsRepository essConnectorsRepo) {
		super();
		this.checkValue = checkValue;
		this.permitService = permitService;
		this.getPDFReaderService = getPDFReaderService;
		this.inverterRepo = inverterRepo;
		this.aCDisconnectRepo = aCDisconnectRepo;
		this.logoSignMapping = logoSignMapping;
		this.dcCombinerRepo = dcCombinerRepo;
		this.acCombinerRepo = acCombinerRepo;
		this.essSystemMapping = essSystemMapping;
		this.junctionBoxMapping = junctionBoxMapping;
		this.notesMapping = notesMapping;
		this.emtConduitSize = emtConduitSize;
		this.pvcConduitSize = pvcConduitSize;
		this.tldDigaramMapping = tldDigaramMapping;
		this.technicalProblemMsg = technicalProblemMsg;
		this.electricUtilityNumber = electricUtilityNumber;
		this.userSettingRepo = userSettingRepo;
		this.dcConductorMapping = dcConductorMapping;
		this.acConductorMapping = acConductorMapping;
		this.essConnectorsRepo = essConnectorsRepo;
	}

	static final String EXISTING_SUB_PANEL = "Show the word \"Existing\" instead of a conductor size (Some AHJs may not approve the permit with this wording)";

	public void mapEquipmentTable(AcroFields form, Long idPermit, DCCombinerDisconnectEntity jBox,
			Inverters inverterInfo, DCCombinerDisconnectEntity dcCombinerDisconnect,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, DCCombinerDisconnectEntity roofTopDCDisconnect,
			ACDisconnect acDisconnect, Cmodulev2 moduleInfo, ACDisconnect secondacDisconnect,
			ACCombinerSLC slcAcCombiner, int sheetIndex, PermitAdditionalInfoEntityResult permitAdditionalInfo,
			PlansetUtils plansetUtils, LeasePPAMeter leasePPAMeter, DCOptimizerEntity dcOptimizer) {
		try {
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-1-QTY", plansetUtils.getModuleQty() + "");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-2-QTY", plansetUtils.getQtyDCConverter() + "");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-3-QTY", plansetUtils.getQtyDCDiscoCombiner() + "");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-4-QTY", plansetUtils.getQtyDCCombinerBox() + "");
			// S.B CR-PM-3516-MOD-002 07/13/2021
			junctionBoxMapping.junctionBoxQty(form, sheetIndex, permitProjectSiteInfo.getQtyJunctionBox(),
					permitProjectSiteInfo.getTransitioningPVWireIn(), "5");
			
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-7-QTY", plansetUtils.getQtyACDCInverter() + "");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-8-QTY",
					plansetUtils.getQtyACDCInverterWACDisconnect() + "");

			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-10-QTY",
					plansetUtils.getQtyACCombinerLoadCenter() + "");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-11-QTY", plansetUtils.getQtyProductionMeter() + "");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-12-QTY", plansetUtils.getQtySubPanel() + "");
			// A.B 12-09 Existing OR New MSP Always 1
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-14-QTY", "1");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-15-QTY", "1");

			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-9-QTY", "0");
			form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-16-QTY", "0");
			form.setField(sheetIndex + "-" + "AC DISCONNECT FUSIBLE", "");
			form.setField(sheetIndex + "-" + "AC DISCONNECT", "");

			if (acDisconnect != null) {

				if (acDisconnect.getDisconnectDeviceType() != null
						&& checkValue.Equals(acDisconnect.getDisconnectDeviceType(), "FUSIBLE DISCONNECT")
						|| checkValue.Equals(acDisconnect.getDisconnectDeviceType(), "FUSIBLE")
						|| checkValue.Equals(acDisconnect.getDisconnectDeviceType(), "Fusible")) {
					form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-16-QTY",
							plansetUtils.getQtyACDisconnect() + "");
					form.setField(sheetIndex + "-" + "AC DISCONNECT FUSIBLE",
							acDisconnect.getManufacturerMappingValue() + " " + acDisconnect.getModelMappingValue());
					if (plansetUtils.getQtyACDiscoGRND() > 0 && secondacDisconnect != null) {
						form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-9-QTY",
								plansetUtils.getQtyACDiscoGRND() + "");
						form.setField(sheetIndex + "-" + "AC DISCONNECT",
								secondacDisconnect.getManufacturerMappingValue() + " "
										+ secondacDisconnect.getModelMappingValue());
					}
				} else if (checkValue.isStringNotEmpty(acDisconnect.getManufacturerMappingValue())) {
					form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-9-QTY",
							plansetUtils.getQtyACDisconnect() + "");
					form.setField(sheetIndex + "-" + "AC DISCONNECT",
							acDisconnect.getManufacturerMappingValue() + " " + acDisconnect.getModelMappingValue());
					if (plansetUtils.getQtyACDiscoGRND() > 0 && secondacDisconnect != null) {
						form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-16-QTY",
								plansetUtils.getQtyACDiscoGRND() + "");
						form.setField(sheetIndex + "-" + "AC DISCONNECT FUSIBLE",
								secondacDisconnect.getManufacturerMappingValue() + " "
										+ secondacDisconnect.getModelMappingValue());
					}
				}
			}

			form.setField(sheetIndex + "-" + "DC DISCONNECT", plansetUtils.getDcComponent());

			if (plansetUtils.getQtyDCCombinerBox() > 0) {

				if (dcCombinerDisconnect != null && dcCombinerDisconnect.getId() != null) {
					form.setField(sheetIndex + "-" + "DC COMBINER", dcCombinerDisconnect.getManufacturerMappingValue()
							+ " " + dcCombinerDisconnect.getModelMappingValue());
				} else if (roofTopDCDisconnect != null && roofTopDCDisconnect.getId() != null) {
					form.setField(sheetIndex + "-" + "DC COMBINER", roofTopDCDisconnect.getManufacturerMappingValue()
							+ " " + roofTopDCDisconnect.getModelMappingValue());

				}

			}

			if (plansetUtils.getQtyJunctionBox() > 0 && jBox != null) {
				form.setField(sheetIndex + "-" + "JUNCTION BOX",
						jBox.getManufacturerMappingValue() + " " + jBox.getModelMappingValue());
			}

			if (inverterInfo != null && (checkValue.Equals(inverterInfo.getMake(), "SolarEdge")
					|| checkValue.Equals(inverterInfo.getMake(), "Solar Edge")
					|| checkValue.Equals(inverterInfo.getMake(), "SolarEdge Technologies"))) {
				form.setField(sheetIndex + "-" + "RAPID SHUTDOWN", "INTEGRATED IN INV & OPT");
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-6-QTY", "N/A");
			} else if (dcOptimizer != null && checkValue.Equals(dcOptimizer.getType(), "DC/DC Rapid Shutdown")) {
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-6-QTY", "N/A");
			} else if (permitProjectSiteInfo != null && permitProjectSiteInfo.getDisconnectModel() != null
					&& checkValue.Equals(permitProjectSiteInfo.getDisconnectModel().getTypeDc(), "Rapid Shutdown")) {
				form.setField(sheetIndex + "-" + "RAPID SHUTDOWN",
						permitProjectSiteInfo.getDisconnectModel().getManufacturerMappingValue() + " "
								+ permitProjectSiteInfo.getDisconnectModel().getModelMappingValue());
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-6-QTY", plansetUtils.getQtyRapidShutdown() + "");
			} else if (permitProjectSiteInfo != null && permitProjectSiteInfo.getRoofTopDCDisco() != null
					&& checkValue.Equals(permitProjectSiteInfo.getRoofTopDCDisco(), "Rapid Shutdown")) {
				form.setField(sheetIndex + "-" + "RAPID SHUTDOWN",
						permitProjectSiteInfo.getRoofTopDCDisco().getManufacturerMappingValue() + " "
								+ permitProjectSiteInfo.getRoofTopDCDisco().getModelMappingValue());
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-6-QTY", plansetUtils.getQtyRapidShutdown() + "");
			} else {
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-6-QTY", "0");
			}

			if (plansetUtils.getQtyACCombinerLoadCenter() > 0 || checkValue.contains(permitProjectSiteInfo.getCombiningACCircuits(),
					"A Proposed AC Combiner Panel")) {
				if (slcAcCombiner != null) {
					form.setField(sheetIndex + "-" + "SOLAR LOAD CENTER",
							slcAcCombiner.getManufacturerMappingValue() + " " + slcAcCombiner.getModelMappingValue());
				} else
					form.setField(sheetIndex + "-" + "SOLAR LOAD CENTER", "");

			} else
				form.setField(sheetIndex + "-" + "SOLAR LOAD CENTER", "");

			if (+plansetUtils.getQtyProductionMeter() > 0 && leasePPAMeter != null
					&& Boolean.TRUE.equals(permitProjectSiteInfo.getUsedRevenue())) {
				form.setField(sheetIndex + "-" + "PRODUCTION METER", leasePPAMeter.getMappedValue());
			} else
				form.setField(sheetIndex + "-" + "PRODUCTION METER", "");

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
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-12-QTY", "1");
			} else if (checkValue.Equals(permitProjectSiteInfo.getThepontOfTheC(), "Sub Panel")) {
//				 S.B 01/10/2020 CR-PM-3365-MOD-002-d
				String subBusrating = checkValue.Equals(permitProjectSiteInfo.getSubPanelBusRating(), "Other")
						? permitProjectSiteInfo.getSubPanelBusRatingOther() + "A"
						: permitProjectSiteInfo.getSubPanelBusRating() + "A";
				String subMainrating = checkValue
						.Equals(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating(), "Lug Only")
								? permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating()
								: permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating() + "A";
				form.setField(sheetIndex + "-" + "AC-SUB-PANEL-1-MANUFACTURER-AND-MODULE",
						subBusrating + " BUS / " + subMainrating + " MB");
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-12-QTY", "1");
			}

			/// --- PR-651 planset mapping update Add Manufacturer Name ---///
			if (moduleInfo != null) {
				form.setField(sheetIndex + "-" + "PV MODULE MODEL",
						moduleInfo.getManufacturerMappingValue() + " " + moduleInfo.getModelMappingValue());
			}
			/// --- CR 800 planset mapping update ---///

			if (inverterInfo != null && checkValue.Equals(inverterInfo.getIntegratedACDisco(), true)) {
				form.setField(sheetIndex + "-" + "INVERTER-MODEL2", inverterInfo.getModelMappingValue());
			} else if (inverterInfo != null) {
				form.setField(sheetIndex + "-" + "INVERTER-MODEL1",
						inverterInfo.getManufacturerMappingValue() + " " + inverterInfo.getModelMappingValue());
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
				essSystemMapping.mapBattery(form, idPermit, sheetIndex, 17);
				essSystemMapping.mapEssEquipment(form, idPermit, sheetIndex, 13, 18);
			} else {
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-17-QTY", "0");
				form.setField(sheetIndex + "-" + "BATTERY-MODEL", "");
				form.setField(sheetIndex + "-" + "ESS-EQUIPMENT-1", "");
				form.setField(sheetIndex + "-" + "ESS-EQUIPMENT-1-MFG-MODEL", "");
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-13-QTY", "");
				form.setField(sheetIndex + "-" + "ESS-EQUIPMENT-2", "");
				form.setField(sheetIndex + "-" + "ESS-EQUIPMENT-2-MFG-MODEL", "");
				form.setField(sheetIndex + "-" + "EQUIPMENT-COMPONENT-18-QTY", "");
			}

		} catch (IOException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (DocumentException e) {
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
		} catch (IOException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void permitArraysMapping(AcroFields form, PermitArrayEntityResultSecond permitArraysEntityResult,
			DCOptimizerEntity dcOptimizer, int sheetIndex) {
		try {
			if (permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer")
					&& dcOptimizer != null) {
				form.setField(sheetIndex + "-" + "DC DC CONVERTER", dcOptimizer.getModelMappingValue());
			}
		} catch (IOException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

	}

	public void mapBeforeRevision(AcroFields form, int i, Boolean stepOptimizer, String dcCircuit,
			PermitConduitConductorSectionEntity circuit, PlansetUtils plansetUtils, int originCombiner,
			PermitEntity permitEntity, UserSettingEntity userSetting, List<String> dcCircuitEnvironment,
			List<String> dcTradeSize, List<Integer> dcNumberOfConductors, int sheetIndex, String minGroundWireSize) {

		try {
			int conductorQty = 0;

			Integer numberOfConductor = dcNumberOfConductors != null && i - 1 < dcNumberOfConductors.size()
					&& dcNumberOfConductors.get(i - 1) != null ? dcNumberOfConductors.get(i - 1) : 1;

			if (checkValue.Equals(stepOptimizer, true) && i == 1) {
				form.setField(sheetIndex + "-" + "DC" + i + "-TOTAL-QTY", "2");
				conductorQty = 2 * numberOfConductor;
			} else if (originCombiner > 0 && i >= originCombiner) {
				form.setField(sheetIndex + "-" + "DC" + i + "-TOTAL-QTY",
						String.valueOf((2 * plansetUtils.getInverterQty()) + 1));
				conductorQty = 2 * plansetUtils.getInverterQty() * numberOfConductor + 1;
			} else {
				form.setField(sheetIndex + "-" + "DC" + i + "-TOTAL-QTY",
						String.valueOf((2 * plansetUtils.getMaxNumberOfStrings()) + 1));
				conductorQty = 2 * plansetUtils.getMaxNumberOfStrings() * numberOfConductor + 1;
			}

			String tradeSize = dcTradeSize != null && i - 1 < dcTradeSize.size() && dcTradeSize.get(i - 1) != null
					? dcTradeSize.get(i - 1)
					: "";
			String conduitSize = dcCircuitEnvironment != null && i - 1 < dcCircuitEnvironment.size()
					&& checkValue.Equals(dcCircuitEnvironment.get(i - 1), "UNDERGROUND")
							? pvcConduitSize.getStringConduitSizePVC(tradeSize, conductorQty * numberOfConductor)
							: emtConduitSize.getConduitSizeEMT(tradeSize, conductorQty * numberOfConductor);
			String conduitType = dcCircuitEnvironment != null && i - 1 < dcCircuitEnvironment.size()
					&& checkValue.Equals(dcCircuitEnvironment.get(i - 1), "UNDERGROUND") ? "PVC" : "EMT";

			if (checkValue.contains(dcCircuit, "-") && checkValue.Equals(dcCircuit.split("-")[i - 1], "PV MODULE")) {

				if ((checkValue.Equals(circuit.getConductorTypeExisting(), true))
						|| (checkValue.Equals(permitEntity.getExistModule(), true))) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSize(), "EXIST") ? "EXISTING" : "#12 AWG");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorType(), "EXIST") ? "EXISTING" : "PV Wire");
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize : "#6 AWG";
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND",
							checkValue.Equals(circuit.getConductorSize(), "EXIST") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSize(), "EXIST") ? "EXISTING" : "N/A");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitType(), "EXIST") ? "EXISTING" : "Open Air");

				}

			} else if (checkValue.contains(dcCircuit, "-")
					&& checkValue.Equals(dcCircuit.split("-")[i - 1], "DC/DC CONVERTER")) {

				if ((checkValue.Equals(circuit.getConductorTypeTwoExisting(), true))
						|| (checkValue.Equals(permitEntity.getExistOptimizer(), true))) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeTwo(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeTwo(), "EXIST") ? "EXISTING" : "PV Wire");

					// 19/06/2019 : CI : CR 2711 :change from "#6 GEC" to "#6 AWG"
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize : "#6 AWG";
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND",
							checkValue.Equals(circuit.getConductorSizeTwo(), "EXIST") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeTwo(), "EXIST") ? "EXISTING" : "N/A");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeTwo(), "EXIST") ? "EXISTING" : "Open Air");

				}

			} else if (checkValue.contains(dcCircuit, "-")
					&& checkValue.Equals(dcCircuit.split("-")[i - 1], "JUNCTION BOX")) {

				if (checkValue.Equals(circuit.getConductorTypeThreeExisting(), true)
						|| checkValue.Equals(permitEntity.getExistdcJunctionBox(), true)) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: getGroundSizeValue(circuit.getConductorSizeThree(), tradeSize,
									userSetting.getMinimumDCGroundCon(), userSetting.getMinimumDCGroConOther());
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeThree(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeThree(), "EXIST") ? "EXISTING" : "THWN-2");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeThree(), "EXIST") ? "EXISTING" : conduitSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeThree(), "EXIST") ? "EXISTING" : conduitType);

				}

			} else if (checkValue.contains(dcCircuit, "-")
					&& checkValue.Equals(dcCircuit.split("-")[i - 1], "DC COMBINER")) {

				if (checkValue.Equals(circuit.getConductorTypeFourExisting(), true)
						|| checkValue.Equals(permitEntity.getExistdcDcCombiner(), true)) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: getGroundSizeValue(circuit.getConductorSizeFour(), tradeSize,
									userSetting.getMinimumDCGroundCon(), userSetting.getMinimumDCGroConOther());
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeFour(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeFour(), "EXIST") ? "EXISTING" : "THWN-2");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeFour(), "EXIST") ? "EXISTING" : conduitSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeFour(), "EXIST") ? "EXISTING" : conduitType);

				}

			} else if (checkValue.contains(dcCircuit, "-")
					&& checkValue.Equals(dcCircuit.split("-")[i - 1], "DC DISCONNECT")) {

				if (checkValue.Equals(circuit.getConductorTypeFiveExisting(), true)
						|| checkValue.Equals(permitEntity.getExistdcDcdisconnect(), true)) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: getGroundSizeValue(circuit.getConductorSizeFive(), tradeSize,
									userSetting.getMinimumDCGroundCon(), userSetting.getMinimumDCGroConOther());
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeFive(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeFive(), "EXIST") ? "EXISTING" : "THWN-2");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeFive(), "EXIST") ? "EXISTING" : conduitSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeFive(), "EXIST") ? "EXISTING" : conduitType);

				}

			}

		} catch (NumberFormatException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (IOException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapAfterRevision(AcroFields form, int i, String dcCircuit, PermitConduitConductorSectionEntity circuit,
			PermitEntity permitEntity, UserSettingEntity userSetting, int sheetIndex, String minGroundWireSize) {
		try {

			if (checkValue.contains(dcCircuit, "-") && checkValue.Equals(dcCircuit.split("-")[i - 1], "PV MODULE")) {

				form.setField(sheetIndex + "-" + "DC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQty(), "Other") ? circuit.getConductorQty()
								: circuit.getConductorQtyOther() + "");

				if ((checkValue.Equals(circuit.getConductorTypeExisting(), true))
						|| (checkValue.Equals(permitEntity.getExistModule(), true))) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					String tradeSize = checkValue.Equals(circuit.getConductorSize(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorSize(), "Other") ? circuit.getConductorSizeOther()
									: circuit.getConductorSize() != null ? circuit.getConductorSize() : "";
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(circuit.getConductorType(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorType(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorType(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSize(), tradeSize,
															userSetting.getMinimumDCGroundCon(),
															userSetting.getMinimumDCGroConOther());
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", tradeSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorType(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorType(), "Other")
											? circuit.getConductorTypeOther()
											: circuit.getConductorType() != null
													? checkValue.Equals(circuit.getConductorType(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorType()
													: "");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND",
							checkValue.Equals(circuit.getConductorSize(), "EXIST") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSize(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSize(), "Other")
											? circuit.getConduitSizeOther()
											: circuit.getConduitSize() != null ? circuit.getConduitSize() : "");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitType(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitType(), "Other")
											? circuit.getConduitTypeOther()
											: circuit.getConduitType() != null ? circuit.getConduitType() : "");

				}

			} else if (checkValue.contains(dcCircuit, "-")
					&& checkValue.Equals(dcCircuit.split("-")[i - 1], "DC/DC CONVERTER")) {

				form.setField(sheetIndex + "-" + "DC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyTwo(), "Other")
								? getQty(circuit.getConductorQtyTwo())
								: getQty(circuit.getConductorQtyTwoOther() + ""));

				if ((checkValue.Equals(circuit.getConductorTypeTwoExisting(), true))
						|| (checkValue.Equals(permitEntity.getExistOptimizer(), true))) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					String tradeSize = checkValue.Equals(circuit.getConductorSizeTwo(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorSizeTwo(), "Other")
									? circuit.getConductorSizeTwoOther()
									: circuit.getConductorSizeTwo() != null ? circuit.getConductorSizeTwo() : "";
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(circuit.getConductorTypeTwo(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeTwo(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeTwo(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeTwoOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeTwoOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeTwo(), tradeSize,
															userSetting.getMinimumDCGroundCon(),
															userSetting.getMinimumDCGroConOther());
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", tradeSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeTwo(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeTwo(), "Other")
											? circuit.getConductorTypeTwoOther()
											: circuit.getConductorTypeTwo() != null
													? checkValue.Equals(circuit.getConductorTypeTwo(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeTwo()
													: "");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND",
							checkValue.Equals(circuit.getConductorSizeTwo(), "EXIST") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeTwo(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeTwo(), "Other")
											? circuit.getConduitSizeTwoOther()
											: circuit.getConduitSizeTwo() != null ? circuit.getConduitSizeTwo() : "");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeTwo(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeTwo(), "Other")
											? circuit.getConduitTypeTwoOther()
											: circuit.getConduitTypeTwo() != null ? circuit.getConduitTypeTwo() : "");

				}

			} else if (checkValue.contains(dcCircuit, "-")
					&& checkValue.Equals(dcCircuit.split("-")[i - 1], "JUNCTION BOX")) {

				form.setField(sheetIndex + "-" + "DC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyThree(), "Other")
								? getQty(circuit.getConductorQtyThree())
								: getQty(circuit.getConductorQtyThreeOther() + ""));

				if (checkValue.Equals(circuit.getConductorTypeThreeExisting(), true)
						|| checkValue.Equals(permitEntity.getExistdcJunctionBox(), true)) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					String tradeSize = checkValue.Equals(circuit.getConductorSizeThree(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorSizeThree(), "Other")
									? circuit.getConductorSizeThreeOther()
									: circuit.getConductorSizeThree() != null ? circuit.getConductorSizeThree() : "";
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(circuit.getConductorTypeThree(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeThree(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeThree(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeThreeOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeThreeOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeThree(), tradeSize,
															userSetting.getMinimumDCGroundCon(),
															userSetting.getMinimumDCGroConOther());
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", tradeSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeThree(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeThree(), "Other")
											? circuit.getConductorTypeThreeOther()
											: circuit.getConductorTypeThree() != null
													? checkValue.Equals(circuit.getConductorTypeThree(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeThree()
													: "");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND",
							checkValue.Equals(circuit.getConductorSizeFive(), "EXIST") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeThree(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeThree(), "Other")
											? circuit.getConduitSizeThreeOther()
											: circuit.getConduitSizeThree() != null ? circuit.getConduitSizeThree()
													: "");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeThree(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeThree(), "Other")
											? circuit.getConduitTypeThreeOther()
											: circuit.getConduitTypeThree() != null ? circuit.getConduitTypeThree()
													: "");

				}

			} else if (checkValue.contains(dcCircuit, "-")
					&& checkValue.Equals(dcCircuit.split("-")[i - 1], "DC COMBINER")) {

				form.setField(sheetIndex + "-" + "DC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyFour(), "Other")
								? getQty(circuit.getConductorQtyFour())
								: getQty(circuit.getConductorQtyFourOther() + ""));

				if (checkValue.Equals(circuit.getConductorTypeFourExisting(), true)
						|| checkValue.Equals(permitEntity.getExistdcDcCombiner(), true)) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					String tradeSize = checkValue.Equals(circuit.getConductorSizeFour(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorSizeFour(), "Other")
									? circuit.getConductorSizeFourOther()
									: circuit.getConductorSizeFour() != null ? circuit.getConductorSizeFour() : "";
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(circuit.getConductorTypeFour(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeFour(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeFour(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeFourOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeFourOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeFour(), tradeSize,
															userSetting.getMinimumDCGroundCon(),
															userSetting.getMinimumDCGroConOther());
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", tradeSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeFour(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeFour(), "Other")
											? circuit.getConductorTypeFourOther()
											: circuit.getConductorTypeFour() != null
													? checkValue.Equals(circuit.getConductorTypeFour(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeFour()
													: "");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND",
							checkValue.Equals(tradeSize, "EXISTING") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeFour(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeFour(), "Other")
											? circuit.getConduitSizeFourOther()
											: circuit.getConduitSizeFour() != null ? circuit.getConduitSizeFour() : "");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeFour(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeFour(), "Other")
											? circuit.getConduitTypeFourOther()
											: circuit.getConduitTypeFour() != null ? circuit.getConduitTypeFour() : "");

				}

			} else if (checkValue.contains(dcCircuit, "-")
					&& checkValue.Equals(dcCircuit.split("-")[i - 1], "DC DISCONNECT")) {

				form.setField(sheetIndex + "-" + "DC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyFive(), "Other")
								? getQty(circuit.getConductorQtyFive())
								: getQty(circuit.getConductorQtyFiveOther() + ""));

				if (checkValue.Equals(circuit.getConductorTypeFiveExisting(), true)
						|| checkValue.Equals(permitEntity.getExistdcDcdisconnect(), true)) {

					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					String tradeSize = checkValue.Equals(circuit.getConductorSizeFive(), "EXIST") ? "EXISTING"
							: checkValue.Equals(circuit.getConductorSizeFive(), "Other")
									? circuit.getConductorSizeFiveOther()
									: circuit.getConductorSizeFive() != null ? circuit.getConductorSizeFive() : "";
					// A.B 07-14-2021 CR-3064
					String condGround = checkValue.isStringNotEmpty(minGroundWireSize) ? minGroundWireSize
							: checkValue.Equals(circuit.getConductorTypeFive(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeFive(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeFive(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeFiveOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeFiveOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeFive(), tradeSize,
															userSetting.getMinimumDCGroundCon(),
															userSetting.getMinimumDCGroConOther());
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE-E003", tradeSize);
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeFive(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeFive(), "Other")
											? circuit.getConductorTypeFiveOther()
											: circuit.getConductorTypeFive() != null
													? checkValue.Equals(circuit.getConductorTypeFive(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeFive()
													: "");
					form.setField(sheetIndex + "-" + "DC" + i + "-GROUND",
							checkValue.Equals(tradeSize, "EXISTING") ? "EXISTING" : condGround);

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeFive(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeFive(), "Other")
											? circuit.getConduitSizeFiveOther()
											: circuit.getConduitSizeFive() != null ? circuit.getConduitSizeFive() : "");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeFive(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeFive(), "Other")
											? circuit.getConduitTypeFiveOther()
											: circuit.getConduitTypeFive() != null ? circuit.getConduitTypeFive() : "");

				}

			}

		} catch (IOException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapExistingCircuits(AcroFields form, int i, String acCircuit,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, int sheetIndex,
			PermitProjectSiteInfoEntity permitProjectSiteInfo) {
		try {
			if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& ((checkValue.Equals(circuit.getConductorTypeSixExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistInverter(), true)))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
					&& ((checkValue.Equals(circuit.getConductorTypeSevenExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistAcJunctionBox(), true)))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
					&& ((checkValue.Equals(circuit.getConductorTypeEightExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistAcCombiner(), true)))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
					&& ((checkValue.Equals(circuit.getConductorTypeNineExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
					&& ((checkValue.Equals(circuit.getConductorTypeNineTwoExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
					&& ((checkValue.Equals(circuit.getConductorTypeTenExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistProductionMeter(), true)))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
					&& ((checkValue.Equals(circuit.getConductorTypeElevenExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistSubPanel(), true)) || checkValue
									.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
					&& ((checkValue.Equals(circuit.getConductorTypeTwelveExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistSubPanel(), true)) || checkValue
									.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
			} else {
				if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
						&& checkValue.Equals(circuit.getConductorTypeSix(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
						&& checkValue.Equals(circuit.getConductorTypeSeven(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
						&& checkValue.Equals(circuit.getConductorTypeEight(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
						&& checkValue.Equals(circuit.getConductorTypeNine(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
						&& checkValue.Equals(circuit.getConductorTypeNineTwo(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
						&& checkValue.Equals(circuit.getConductorTypeTen(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
						&& checkValue.Equals(circuit.getConductorTypeEleven(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
						&& checkValue.Equals(circuit.getConductorTypeTwelve(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "THWN-2");
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapACcircuitsbeforeRevision(AcroFields form, int i, String acCircuit,
			PermitConduitConductorSectionEntity circuit, UserSettingEntity userSetting, PermitEntity permitEntity,
			List<String> acCircuitEnvironment, List<String> acTradeSize, List<Integer> acNumberOfConductors,
			int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			PermitHomeSiteInfoEntity permitHomeSite) {

		try {

			int conductorQty = 4;

//			S.B CR-3119-MOD-004 Update Qty on Conductor Table on AC circuits on E-003
			if ((checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
					&& checkValue.notChecked(circuit.getConductorNeutralSix()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
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
				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", String.valueOf(3));
				conductorQty = 3;
			} else if ((checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
					&& checkValue.Equals(circuit.getConductorNeutralSix(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
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
				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", String.valueOf(4));
				conductorQty = 4;
			} else if ((checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
					&& checkValue.Equals(circuit.getConductorNeutralSix(), true))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
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
				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY", String.valueOf(5));
				conductorQty = 5;
			}

			String tradeSize = acTradeSize != null && i <= acTradeSize.size() && acTradeSize.get(i - 1) != null
					? acTradeSize.get(i - 1)
					: "";
			String conduitSize = acCircuitEnvironment != null && i <= acCircuitEnvironment.size()
					&& checkValue.Equals(acCircuitEnvironment.get(i - 1), "UNDERGROUND")
							? pvcConduitSize.getStringConduitSizePVC(tradeSize, conductorQty)
							: emtConduitSize.getConduitSizeEMT(tradeSize, conductorQty);
			String conduitType = acCircuitEnvironment != null && i <= acCircuitEnvironment.size()
					&& checkValue.Equals(acCircuitEnvironment.get(i - 1), "UNDERGROUND") ? "PVC" : "EMT";

			if (checkValue.contains(acCircuit, "-") && checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")) {

				if ((circuit != null && checkValue.Equals(circuit.getConductorTypeSixExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistInverter(), true))) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					if (circuit != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeSix(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeSix(), "EXIST") ? "EXISTING" : "THWN-2");
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
								getGroundSizeValue(circuit.getConductorSizeSix(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther()));

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeSix(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeSix(), "EXIST") ? "EXISTING" : conduitType);
					}
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")) {

				if ((circuit != null && checkValue.Equals(circuit.getConductorTypeSevenExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistAcJunctionBox(), true))) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					if (circuit != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeSeven(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeSeven(), "EXIST") ? "EXISTING" : "THWN-2");
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
								getGroundSizeValue(circuit.getConductorSizeSeven(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther()));

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeSeven(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeSeven(), "EXIST") ? "EXISTING" : conduitType);
					}
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {

				if ((circuit != null && checkValue.Equals(circuit.getConductorTypeEightExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistAcCombiner(), true))) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					if (circuit != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeEight(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeEight(), "EXIST") ? "EXISTING" : "THWN-2");
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
								getGroundSizeValue(circuit.getConductorSizeEight(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther()));

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeEight(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeEight(), "EXIST") ? "EXISTING" : conduitType);
					}
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")) {

				if ((circuit != null && checkValue.Equals(circuit.getConductorTypeNineExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistAcDisconnect(), true))) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					if (circuit != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeNine(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeNine(), "EXIST") ? "EXISTING" : "THWN-2");
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
								getGroundSizeValue(circuit.getConductorSizeNine(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther()));

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeNine(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeNine(), "EXIST") ? "EXISTING" : conduitType);
					}
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")) {

				if ((circuit != null && checkValue.Equals(circuit.getConductorTypeNineTwoExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistAcDisconnect(), true))) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					if (circuit != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeNineTwo(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeNineTwo(), "EXIST") ? "EXISTING" : "THWN-2");
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
								getGroundSizeValue(circuit.getConductorSizeNineTwo(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther()));

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeNineTwo(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeNineTwo(), "EXIST") ? "EXISTING" : conduitType);
					}
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")) {

				if ((circuit != null && checkValue.Equals(circuit.getConductorTypeTenExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistProductionMeter(), true))) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					if (circuit != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
								checkValue.Equals(circuit.getConductorSizeTen(), "EXIST") ? "EXISTING" : tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeTen(), "EXIST") ? "EXISTING" : "THWN-2");
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
								getGroundSizeValue(circuit.getConductorSizeTen(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther()));

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeTen(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeTen(), "EXIST") ? "EXIST" : conduitType);
					}
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")) {

				if ((circuit != null && checkValue.Equals(circuit.getConductorTypeElevenExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistSubPanel(), true)
								|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(),
										EXISTING_SUB_PANEL))) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					if (circuit != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeEleven(), "EXIST") ? "EXISTING" : "THWN-2");
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
								getGroundSizeValue(circuit.getConductorSizeEleven(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther()));

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeEleven(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeEleven(), "EXIST") ? "EXISTING" : conduitType);
					}
				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")) {

				if ((circuit != null && checkValue.Equals(circuit.getConductorTypeTwelveExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistSubPanel(), true)
								|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(),
										EXISTING_SUB_PANEL))) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					if (circuit != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", tradeSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
								checkValue.Equals(circuit.getConductorTypeTwelve(), "EXIST") ? "EXISTING" : "THWN-2");
						form.setField(sheetIndex + "-" + "AC" + i + "-GROUND",
								getGroundSizeValue(circuit.getConductorSizeTwelve(), tradeSize,
										userSetting.getMinimumACGroundCon(), userSetting.getMinimumACGroConOther()));

						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
								checkValue.Equals(circuit.getConduitSizeTwelve(), "EXIST") ? "EXISTING" : conduitSize);
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
								checkValue.Equals(circuit.getConduitTypeTwelve(), "EXIST") ? "EXISTING" : conduitType);
					}
				}

			}

		} catch (NumberFormatException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (IOException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapACcircuitsafterRevision(List<String> acTradeSize, AcroFields form, int i, String acCircuit,
			PermitConduitConductorSectionEntity circuit, UserSettingEntity userSetting, PermitEntity permitEntity,
			int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo) {
		try {

			String tradeSize = acTradeSize != null && i <= acTradeSize.size() && acTradeSize.get(i - 1) != null
					? acTradeSize.get(i - 1)
					: "";

			if (checkValue.contains(acCircuit, "-") && checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")) {

				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtySix(), "Other")
								? getQty(circuit.getConductorQtySix())
								: getQty(circuit.getConductorQtySixOther() + ""));

				if ((checkValue.Equals(circuit.getConductorTypeSixExisting(), true))
						|| (checkValue.Equals(permitEntity.getExistInverter(), true))) {

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
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", checkValue.Equals(tradeSize, "EXISTING")
							? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeSix(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeSix(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeSix(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeSixOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeSixOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeSix(), tradeSize,
															userSetting.getMinimumACGroundCon(),
															userSetting.getMinimumACGroConOther()));

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

				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")) {

				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtySeven(), "Other")
								? getQty(circuit.getConductorQtySeven())
								: getQty(circuit.getConductorQtySevenOther() + ""));

				if ((checkValue.Equals(circuit.getConductorTypeSevenExisting(), true))
						|| (checkValue.Equals(permitEntity.getExistAcJunctionBox(), true))) {

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
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", checkValue.Equals(tradeSize, "EXISTING")
							? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeSeven(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeSeven(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeSeven(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeSevenOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeSevenOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeSeven(), tradeSize,
															userSetting.getMinimumACGroundCon(),
															userSetting.getMinimumACGroConOther()));

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

				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {

				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyEight(), "Other")
								? getQty(circuit.getConductorQtyEight())
								: getQty(circuit.getConductorQtyEightOther() + ""));

				if (checkValue.Equals(circuit.getConductorTypeEightExisting(), true)
						|| checkValue.Equals(permitEntity.getExistAcCombiner(), true)) {

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
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", checkValue.Equals(tradeSize, "EXISTING")
							? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeEight(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeEight(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeEight(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeEightOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeEightOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeEight(), tradeSize,
															userSetting.getMinimumACGroundCon(),
															userSetting.getMinimumACGroConOther()));

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

				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")) {

				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyNine(), "Other")
								? getQty(circuit.getConductorQtyNine())
								: getQty(circuit.getConductorQtyNineOther() + ""));

				if (checkValue.Equals(circuit.getConductorTypeNineExisting(), true)
						|| checkValue.Equals(permitEntity.getExistAcDisconnect(), true)) {

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
													? checkValue.Equals(circuit.getConductorTypeNine(), "Trunk cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeNine()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", checkValue.Equals(tradeSize, "EXISTING")
							? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeNine(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeNine(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeNine(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeNineOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeNineOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeNine(), tradeSize,
															userSetting.getMinimumACGroundCon(),
															userSetting.getMinimumACGroConOther()));

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

				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")) {

				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyNineTwo(), "Other")
								? getQty(circuit.getConductorQtyNineTwo())
								: getQty(circuit.getConductorQtyNineTwoOther() + ""));

				if (checkValue.Equals(circuit.getConductorTypeNineTwoExisting(), true)
						|| checkValue.Equals(permitEntity.getExistAcDisconnect(), true)) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003",
							checkValue.Equals(circuit.getConductorSizeNineTwo(), "EXIST") ? "EXISTING" : tradeSize);
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeNineTwo(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeNineTwo(), "Other")
											? circuit.getConductorTypeNineTwoOther()
											: circuit.getConductorTypeNineTwo() != null
													? checkValue.Equals(circuit.getConductorTypeNineTwo(),
															"Trunk Cable") ? "TRUNK CBL"
																	: circuit.getConductorTypeNineTwo()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", checkValue.Equals(tradeSize, "EXISTING")
							? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeNineTwo(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeNineTwo(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeNineTwo(), "Other")
											&& (checkValue.Equals(circuit.getConductorTypeNineTwoOther(), "PV Wire")
													|| checkValue.Equals(circuit.getConductorTypeNineTwoOther(),
															"Trunk Cable")))
																	? "#6 AWG"
																	: getGroundSizeValue(
																			circuit.getConductorSizeNineTwo(),
																			tradeSize,
																			userSetting.getMinimumACGroundCon(),
																			userSetting.getMinimumACGroConOther()));

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeNineTwo(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeNineTwo(), "Other")
											? circuit.getConduitSizeNineTwoOther()
											: circuit.getConduitSizeNineTwo() != null ? circuit.getConduitSizeNineTwo()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeNineTwo(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeNineTwo(), "Other")
											? circuit.getConduitTypeNineTwoOther()
											: circuit.getConduitTypeNineTwo() != null ? circuit.getConduitTypeNineTwo()
													: "");

				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")) {

				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyTen(), "Other")
								? getQty(circuit.getConductorQtyTen())
								: getQty(circuit.getConductorQtyTenOther() + ""));

				if (checkValue.Equals(circuit.getConductorTypeTenExisting(), true)
						|| checkValue.Equals(permitEntity.getExistProductionMeter(), true)) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

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
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", checkValue.Equals(tradeSize, "EXISTING")
							? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeTen(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeTen(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeTen(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeTenOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeTenOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeTen(), tradeSize,
															userSetting.getMinimumACGroundCon(),
															userSetting.getMinimumACGroConOther()));

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

				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")) {

				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyEleven(), "Other")
								? getQty(circuit.getConductorQtyEleven())
								: getQty(circuit.getConductorQtyElevenOther() + ""));

				if (checkValue.Equals(circuit.getConductorTypeElevenExisting(), true)
						|| checkValue.Equals(permitEntity.getExistSubPanel(), true)
						|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL)) {

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
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", checkValue.Equals(tradeSize, "EXISTING")
							? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeEleven(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeEleven(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeEleven(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeElevenOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeElevenOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeEleven(), tradeSize,
															userSetting.getMinimumACGroundCon(),
															userSetting.getMinimumACGroConOther()));

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

				}

			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")) {

				form.setField(sheetIndex + "-" + "AC" + i + "-TOTAL-QTY",
						checkValue.NotEquals(circuit.getConductorQtyTwelve(), "Other")
								? getQty(circuit.getConductorQtyTwelve())
								: getQty(circuit.getConductorQtyTwelveOther() + ""));

				if (checkValue.Equals(circuit.getConductorTypeTwelveExisting(), true)
						|| checkValue.Equals(permitEntity.getExistSubPanel(), true)
						|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL)) {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", "EXISTING");

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL", "EXISTING");

				} else {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE-E003", tradeSize);
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL-E003",
							checkValue.Equals(circuit.getConductorTypeTwelve(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConductorTypeTwelve(), "Other")
											? circuit.getConductorTypeTwelveOther()
											: circuit.getConductorTypeTwelve() != null
													? checkValue.Equals(circuit.getConductorTypeTwelve(), "Trunk Cable")
															? "TRUNK CBL"
															: circuit.getConductorTypeTwelve()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-GROUND", checkValue.Equals(tradeSize, "EXISTING")
							? "EXISTING"
							: checkValue.Equals(circuit.getConductorTypeTwelve(), "PV Wire")
									|| checkValue.Equals(circuit.getConductorTypeTwelve(), "Trunk Cable")
									|| (checkValue.Equals(circuit.getConductorTypeTwelve(), "Other") && (checkValue
											.Equals(circuit.getConductorTypeTwelveOther(), "PV Wire")
											|| checkValue.Equals(circuit.getConductorTypeTwelveOther(), "Trunk Cable")))
													? "#6 AWG"
													: getGroundSizeValue(circuit.getConductorSizeTwelve(), tradeSize,
															userSetting.getMinimumACGroundCon(),
															userSetting.getMinimumACGroConOther()));

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-SIZE",
							checkValue.Equals(circuit.getConduitSizeTwelve(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitSizeTwelve(), "Other")
											? circuit.getConduitSizeTwelveOther()
											: circuit.getConduitSizeTwelve() != null ? circuit.getConduitSizeTwelve()
													: "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-MATERIAL",
							checkValue.Equals(circuit.getConduitTypeTwelve(), "EXIST") ? "EXISTING"
									: checkValue.Equals(circuit.getConduitTypeTwelve(), "Other")
											? circuit.getConduitTypeTwelveOther()
											: circuit.getConduitTypeTwelve() != null ? circuit.getConduitTypeTwelve()
													: "");

				}

			}

		} catch (IOException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private String getQty(String qty) {
		return String.valueOf(checkValue.convertToInteger(qty) + 1);
	}

	public void mapOptimizerOrRsd(DCOptimizerEntity dcOptimizer, AcroFields form, int sheetIndex) {
		try {
			if (dcOptimizer != null && checkValue.Equals(dcOptimizer.getType(), "DC/DC Rapid Shutdown")) {
				form.setField(sheetIndex + "-" + "DC-DC-CONVERTER-TITLE", "DC/DC RAPID SHUTDOWN");
			} else {
				form.setField(sheetIndex + "-" + "DC-DC-CONVERTER-TITLE", "DC/DC OPTIMIZER");
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		}
	}

	public File buildingPDFE003(PermitHomeSiteInfoEntity permitHomeSite,
			PermitAdditionalInfoEntityResult permitAdditionalInfo, PermitEntity permitEntity,
			PermitConduitConductorSectionEntity circuit, PermitArrayEntityResultSecond permitArraysEntityResult,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, PlansetUtils plansetUtils, UserSettingEntity userSetting,
			LeasePPAMeter leasePPAMeter, Inverters inverterInfo, Inverters secondInverterInfo,
			DCCombinerDisconnectEntity dcCombinerDisconnect, ACDisconnect acDisconnect, Boolean includeBattery,
			DCCombinerDisconnectEntity jBox, Cmodulev2 moduleInfo, List<Inverters> inverters,
			ACDisconnect secondacDisconnect, ACCombinerSLC slcAcCombiner, AuthentificationEntity userConnectedEntity,
			DCOptimizerEntity dcOptimizer, DCCombinerDisconnectEntity roofTopDCDisconnect,
			PermitAdvEntityResult editPermitAdvRequest, Inverters thirdInverterInfo2, Inverters fourthInverterInfo,
			List<String> dcCircuitEnvironment, List<String> acCircuitEnvironment, List<String> dcTradeSize,
			List<String> acTradeSize, List<Integer> dcNumberOfConductors, List<Integer> acNumberOfConductors,
			PdfReader reader, int sheetIndex, String filePath, AHJNotesModel ahjNotes, Integer firsttInverterQty,
			Integer secondtInverterQty, String electNote, ElectricalUtilityEntity electricalCompany,
			PermitEnergyBatterySystem permitEnergyBatterySystem) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		File fileRe = null;

		if ((checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Neither")
				|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer"))) {

			try {
				fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E003-STRING" + permitEntity.getId() + "-"
						+ sheetIndex + ".pdf");

				if (fileRe.exists()) {
					fileRe.delete();
					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E003-STRING" + permitEntity.getId() + "-"
							+ sheetIndex + ".pdf");
				}

				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();

				// A.B remove sheet index if exist when the project was uploaded
				if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
					getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
				}

				PdfReader readerOriginNEC = new PdfReader(
						Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-E003-STRING.pdf");
				PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-E003-STRING.pdf");

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

				if (circuit != null) {
					tldDigaramMapping.tldSheetMapping(stamper, moduleInfo, permitEntity, editPermitAdvRequest, circuit,
							permitProjectSiteInfo, permitHomeSite, permitArraysEntityResult, inverterInfo,
							secondInverterInfo, null, plansetUtils, dcOptimizer, acDisconnect, secondacDisconnect,
							thirdInverterInfo2, fourthInverterInfo, slcAcCombiner, firsttInverterQty,
							secondtInverterQty, inverters, permitAdditionalInfo, permitEnergyBatterySystem);
					///////////////////////////// Equipment Table //////////////////////

					mapEquipmentTable(form, permitEntity.getId(), jBox, inverterInfo, dcCombinerDisconnect,
							permitProjectSiteInfo, roofTopDCDisconnect, acDisconnect, moduleInfo, secondacDisconnect,
							slcAcCombiner, sheetIndex, permitAdditionalInfo, plansetUtils, leasePPAMeter, dcOptimizer);
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
					permitArraysMapping(form, permitArraysEntityResult, dcOptimizer, sheetIndex);

					/// ------------------------------------///
					// A.B 08/25/2022 CR-1030
					if (Boolean.TRUE.equals(includeBattery)) {
						List<Integer> inverterIndexList = essConnectorsRepo.findInverterIndex(permitEntity.getId());
						Integer inverterIndex = inverterIndexList != null && !inverterIndexList.isEmpty() ? inverterIndexList.get(0) : 2;
						List<ESSConnectors> dcList = essConnectorsRepo
								.findByIndexLessThanAndProjectIdOrderByIndex(inverterIndex, permitEntity.getId());
						/// ------------------------- DC CIRCUIT Mapping ---------------------///
						dcConductorMapping.conductorMapping(form, sheetIndex, dcList, userSetting,
								userConnectedEntity.getRoleEntity().getId(), circuit.getMapFromUserInput(),
								dcCircuitEnvironment, dcTradeSize, ahjNotes.getMinGroundWireSize(),
								dcNumberOfConductors, plansetUtils);

						/// ------------------------- AC CIRCUIT Mapping ---------------------///
						List<ESSConnectors> acList = essConnectorsRepo.findByIndexGreaterThanAndProjectIdOrderByIndex(
								inverterIndex - 1, permitEntity.getId());
						acConductorMapping.conductorMapping(form, sheetIndex, acList, userSetting,
								ahjNotes.getMinGroundWireSize(), acTradeSize,
								userConnectedEntity.getRoleEntity().getId(), permitEntity.getId(),
								circuit.getMapFromUserInput(), acNumberOfConductors, acCircuitEnvironment,
								permitHomeSite, permitProjectSiteInfo);
					} else {
						String dcCircuit = "";
						String acCircuit = "";

						if (circuit.getComponentOrder() != null && circuit.getComponentOrder().contains("-INV-")) {

							if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer")
									&& circuit.getQtySegmentTwo() != null && circuit.getQtySegmentTwo() != 0) {
								dcCircuit = "PV-OP-" + circuit.getComponentOrder().split("-INV-")[0];
							} else
								dcCircuit = "PV-" + circuit.getComponentOrder().split("-INV-")[0];

							acCircuit = "INV-" + circuit.getComponentOrder().split("-INV-")[1];

						} else if (circuit.getComponentOrder() != null
								&& circuit.getComponentOrder().contains("-INV")) {

							if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer")
									&& circuit.getQtySegmentTwo() != null && circuit.getQtySegmentTwo() != 0) {
								dcCircuit = "PV-OP-" + circuit.getComponentOrder().split("-INV")[0];
							} else
								dcCircuit = "PV-" + circuit.getComponentOrder().split("-INV")[0];

							acCircuit = "INV-";

						} else if (circuit.getComponentOrder() != null
								&& circuit.getComponentOrder().contains("INV-")) {

							if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer")
									&& circuit.getQtySegmentTwo() != null && circuit.getQtySegmentTwo() != 0) {
								dcCircuit = "PV-OP";
							} else
								dcCircuit = "PV-";
							acCircuit = circuit.getComponentOrder();

						} else if (checkValue.Equals(circuit.getComponentOrder(), "INV")) {

							if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer")
									&& circuit.getQtySegmentTwo() != null && circuit.getQtySegmentTwo() != 0) {
								dcCircuit = "PV-OP";
							} else
								dcCircuit = "PV-";

							acCircuit = "INV-";
						}

						int dcCircuitLength = 0;
						int acCircuitLength = 0;

						if (dcCircuit.equals("PV-")) {
							dcCircuitLength = 1;
						} else {
							dcCircuitLength = dcCircuit.split("-").length;
						}

						if (acCircuit.equals("INV-")) {
							acCircuitLength = 1;
						} else {
							acCircuitLength = acCircuit.split("-").length;
						}
						Boolean stepOptimizer = false;

						int originCombiner = 0;

						for (int i = 0; i < dcCircuitLength; i++) {

							if (checkValue.Equals(dcCircuit.split("-")[i], "PV")) {
								dcCircuit = dcCircuit.replace("PV", "PV MODULE");

							} else if (checkValue.Equals(dcCircuit.split("-")[i], "OP")) {
								dcCircuit = dcCircuit.replace("OP", "DC/DC CONVERTER");
								stepOptimizer = true;

							} else if (checkValue.Equals(dcCircuit.split("-")[i], "DCJBOX")) {
								dcCircuit = dcCircuit.replace("DCJBOX", "JUNCTION BOX");

							} else if (checkValue.Equals(dcCircuit.split("-")[i], "DCC")) {
								dcCircuit = dcCircuit.replace("DCC", "DC COMBINER");
								originCombiner = i + 1;

							} else if (checkValue.Equals(dcCircuit.split("-")[i], "DCD")) {
								dcCircuit = dcCircuit.replace("DCD", "DC DISCONNECT");
							}

						}

						for (int i = 1; i < dcCircuitLength + 1; i++) {

							int x = permitEntity.getRRVersion();

							// A.B 07-14-2021 CR-3064
							String minGroundWireSize = i == 1 ? ahjNotes.getMinGroundWireSize()
									: i == 2 && checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
											"System Optimizer") ? ahjNotes.getMinGroundWireSize() : null;
							if ((userConnectedEntity.getRoleEntity().getId() == 2 && x < 2)
									|| (userConnectedEntity.getRoleEntity().getId() != 2
											&& !(checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {

								mapBeforeRevision(form, i, stepOptimizer, dcCircuit, circuit, plansetUtils,
										originCombiner, permitEntity, userSetting, dcCircuitEnvironment, dcTradeSize,
										dcNumberOfConductors, sheetIndex, minGroundWireSize);

							} else if ((userConnectedEntity.getRoleEntity().getId() == 2 && x >= 2)
									|| (userConnectedEntity.getRoleEntity().getId() != 2
											&& checkValue.Equals(circuit.getMapFromUserInput(), true))) {
								mapAfterRevision(form, i, dcCircuit, circuit, permitEntity, userSetting, sheetIndex,
										minGroundWireSize);

							}
						}

						for (int i = 0; i < acCircuitLength; i++) {

							if (checkValue.Equals(acCircuit.split("-")[i], "INV")) {
								acCircuit = acCircuit.replace("INV", "INVERTER");

							} else if (checkValue.Equals(acCircuit.split("-")[i], "ACJBOX")) {
								acCircuit = acCircuit.replace("ACJBOX", "JUNCTION BOX");

							} else if (checkValue.Equals(acCircuit.split("-")[i], "ACC")) {
								acCircuit = acCircuit.replace("ACC", "AC COMBINER/LOAD CENTER");
							} else if (checkValue.Equals(acCircuit.split("-")[i], "ACD")) {
								acCircuit = acCircuit.replace("ACD", "AC DISCONNECT");

							} else if (checkValue.Equals(acCircuit.split("-")[i], "ACDTwo")) {
								acCircuit = acCircuit.replace("ACDTwo", "AC DISCONNECT");

							} else if (checkValue.Equals(acCircuit.split("-")[i], "PMETER")) {
								acCircuit = acCircuit.replace("PMETER", "PRODUCTION METER");

							} else if (checkValue.Equals(acCircuit.split("-")[i], "ACSUBPANEL")) {
								acCircuit = acCircuit.replace("ACSUBPANEL", "SUB PANEL");

							}

						}

						for (int i = 1; i < acCircuitLength + 1; i++) {
							mapExistingCircuits(form, i, acCircuit, circuit, permitEntity, sheetIndex,
									permitProjectSiteInfo);
							int x = permitEntity.getRRVersion();

							if ((userConnectedEntity.getRoleEntity().getId() == 2 && x < 2)
									|| (userConnectedEntity.getRoleEntity().getId() != 2
											&& !(checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {

								mapACcircuitsbeforeRevision(form, i, acCircuit, circuit, userSetting, permitEntity,
										acCircuitEnvironment, acTradeSize, acNumberOfConductors, sheetIndex,
										permitProjectSiteInfo, permitHomeSite);

							} else if ((userConnectedEntity.getRoleEntity().getId() == 2 && x >= 2)
									|| (userConnectedEntity.getRoleEntity().getId() != 2
											&& checkValue.Equals(circuit.getMapFromUserInput(), true))) {
								mapACcircuitsafterRevision(acTradeSize, form, i, acCircuit, circuit, userSetting,
										permitEntity, sheetIndex, permitProjectSiteInfo);

							}
						}
					}

				}

				if (plansetUtils.getStringQty() != null && !plansetUtils.getStringQty().isEmpty()) {
					for (int i = 0; i < plansetUtils.getStringQty().size() && i < 8; i++) {
						form.setField(sheetIndex + "-" + "STRING " + i, " " + (i + 1));
						form.setField(sheetIndex + "-" + "MOD QTY " + i, " " + plansetUtils.getStringQty().get(i));
						form.setField(sheetIndex + "-" + "PAIRED TO INV" + i,
								" " + plansetUtils.getStringStringE003().get(i));
					}
				}
				if (permitProjectSiteInfo != null
						&& checkValue.Equals(permitProjectSiteInfo.getSolarLocation(), "Back-fed Breaker")) {
					if (checkValue.Equals(permitProjectSiteInfo.getSolarInterconnection(), "Other")) {
						if (permitProjectSiteInfo.getSolarInterconnectionOther() != null
								&& permitProjectSiteInfo.getSolarInterconnectionOther().contains("A")) {
							form.setField(sheetIndex + "-" + "BACKFEED BREAKER SIZE",
									permitProjectSiteInfo.getSolarInterconnectionOther());
						} else
							form.setField(sheetIndex + "-" + "BACKFEED BREAKER SIZE",
									permitProjectSiteInfo.getSolarInterconnectionOther() + "A");
					} else
						form.setField(sheetIndex + "-" + "BACKFEED BREAKER SIZE",
								permitProjectSiteInfo.getSolarInterconnection() + "A");
				} else {
					form.setField(sheetIndex + "-" + "BACKFEED BREAKER SIZE", "N/A");
				}

				// CR-3145-MOD-009 Mapping DC-DC-CONVERTER-TITLE field
				mapOptimizerOrRsd(dcOptimizer, form, sheetIndex);
				electricUtilityNumber.mapMeterNumber(form, sheetIndex, "E003", permitHomeSite, electricalCompany);

				stamper.close();
				reader.close();

			} catch (IOException e) {

				e.printStackTrace();
				technicalProblemMsg.traiterException(e);
			} catch (DocumentException dE) {

				technicalProblemMsg.traiterException(dE);
			}

		}
		return fileRe;

	}

	Boolean conductorSizeIs2OrSmaller(String conduitSize) {

		try {

			if (checkValue.Equals(conduitSize, "#14 AWG") || checkValue.Equals(conduitSize, "#12 AWG")
					|| checkValue.Equals(conduitSize, "#10 AWG") || checkValue.Equals(conduitSize, "#8 AWG")
					|| checkValue.Equals(conduitSize, "#6 AWG") || checkValue.Equals(conduitSize, "#4 AWG")
					|| checkValue.Equals(conduitSize, "#3 AWG") || checkValue.Equals(conduitSize, "#2 AWG")) {

				return true;

			} else
				return false;

		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return false;
		}

	}

	// A.B 03/12/2019: CR-2515 Get Ground Conductor Size
	String getGroundSize(String conduitSize) {

		try {

			if (conduitSize == null || checkValue.Equals(conduitSize, "")) {
				return "";
			} else if (checkValue.Equals(conduitSize, "EXISTING")) {
				return "EXISTING";
			} else if (checkValue.Equals(conduitSize, "#1 AWG") || checkValue.Equals(conduitSize, "#1/0 AWG")) {
				return "#6 AWG";
			} else if (checkValue.Equals(conduitSize, "#2/0 AWG") || checkValue.Equals(conduitSize, "#3/0 AWG")) {
				return "#4 AWG";
			} else if (checkValue.Equals(conduitSize, "#4/0 AWG") || checkValue.Equals(conduitSize, "250 kc")
					|| checkValue.Equals(conduitSize, "300 kc") || checkValue.Equals(conduitSize, "350 kc")) {
				return "#2 AWG";
			} else if (checkValue.Equals(conduitSize, "400 kc") || checkValue.Equals(conduitSize, "500 kc")
					|| checkValue.Equals(conduitSize, "600 kc")) {
				return "#1/0 AWG";
			} else if (checkValue.Equals(conduitSize, "700 kc") || checkValue.Equals(conduitSize, "750 kc")
					|| checkValue.Equals(conduitSize, "800 kc") || checkValue.Equals(conduitSize, "900 kc")
					|| checkValue.Equals(conduitSize, "1000 kc") || checkValue.Equals(conduitSize, "1100 kc")) {
				return "#2/0 AWG";
			} else {
				return "#3/0 AWG";
			}

		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}

	}

	// A.B 03/12/2019: CR-2515 Get Ground Conductor Size
	public String getGroundSizeValue(String conduitSize, String tradeSize, String minimumACGroundCon,
			String minimumACGroundConOther) {

		try {
			return checkValue.Equals(conduitSize, "EXISTING") ? "EXISTING"
					: checkValue.Equals(conductorSizeIs2OrSmaller(tradeSize), true)
							? checkValue.isStringNotEmpty(minimumACGroundCon)
									? checkValue.Equals(minimumACGroundCon, "Other") ? minimumACGroundConOther
											: minimumACGroundCon
									: "#8 AWG"
							: getGroundSize(tradeSize);

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}

	}

}
