package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.model.planset_utils.SystemEnvironment;
import com.PlayGroundAdv.Solar.model.planset_utils.VoltageDropTable;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetWeatherData;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.EMTConduitSize;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.PVCConduitSize;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.ConduitFillDeratingAfterRev;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.MicroConductorTemperatureDerating;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.OriginDestinationMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.RequiredConductorAmpacity;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.dc_circuit.DcMicroMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro.ACCircuitMappingMicro;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro.DCCircuitMappingMicro;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.AmpacityCorrectionChart;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.GenerateCircuitList;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.NEC310Values;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.VoltageDropCalculation;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.VoltageDropMapping;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class PlansetServiceE002Micro2 {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignMapping;
	final VoltageDropCalculation voltageDropCalculation;
	final NEC310Values nec310Values;
	final NEC3106B16Repository nec3106B16Repo;
	final VoltageDropMapping voltageDropMapping;
	final CircuitEnvironment circuitEnvService;
	final DefaultRowMapping defaultRowMapping;
	final TechnicalProblemMsg technicalProblemMsg;
	final EMTConduitSize emtConduitSize;
	final PVCConduitSize pvcConduitSize;
	final GetWeatherData weatherData;
	final GenerateCircuitList generateCircuitList;
	final GetPVModuleData getPVModuleData;
	final ConduitFillDeratingAfterRev conduitFillDeratingAfterRev;
	final OriginDestinationMapping originDestinationMapping;
	final AmpacityCorrectionChart ampCorrection;
	final DcMicroMapping dcMicroMapping;
	final MicroConductorTemperatureDerating conductorTempDerating;
	final RequiredConductorAmpacity requiredConductorAmpacity;
	final DCCircuitMappingMicro dcCircuitMapping;
	final ACCircuitMappingMicro acCircuitMapping;
	final ESSConnectorsRepository essConnectorsRepo;

	public PlansetServiceE002Micro2(CheckValueTypesService checkValue, GetPDFReaderService getPDFReaderService,
			PlansetLogo_SignatureMappingService logoSignMapping, VoltageDropCalculation voltageDropCalculation,
			NEC310Values nec310Values, NEC3106B16Repository nec3106B16Repo, VoltageDropMapping voltageDropMapping,
			CircuitEnvironment circuitEnvService, DefaultRowMapping defaultRowMapping,
			TechnicalProblemMsg technicalProblemMsg, EMTConduitSize emtConduitSize, PVCConduitSize pvcConduitSize,
			GetWeatherData weatherData, GenerateCircuitList generateCircuitList, GetPVModuleData getPVModuleData,
			ConduitFillDeratingAfterRev conduitFillDeratingAfterRev, OriginDestinationMapping originDestinationMapping,
			AmpacityCorrectionChart ampCorrection, DcMicroMapping dcMicroMapping,
			MicroConductorTemperatureDerating conductorTempDerating,
			RequiredConductorAmpacity requiredConductorAmpacity, DCCircuitMappingMicro dcCircuitMapping,
			ACCircuitMappingMicro acCircuitMapping, ESSConnectorsRepository essConnectorsRepo) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignMapping = logoSignMapping;
		this.voltageDropCalculation = voltageDropCalculation;
		this.nec310Values = nec310Values;
		this.nec3106B16Repo = nec3106B16Repo;
		this.voltageDropMapping = voltageDropMapping;
		this.circuitEnvService = circuitEnvService;
		this.defaultRowMapping = defaultRowMapping;
		this.technicalProblemMsg = technicalProblemMsg;
		this.emtConduitSize = emtConduitSize;
		this.pvcConduitSize = pvcConduitSize;
		this.weatherData = weatherData;
		this.generateCircuitList = generateCircuitList;
		this.getPVModuleData = getPVModuleData;
		this.conduitFillDeratingAfterRev = conduitFillDeratingAfterRev;
		this.originDestinationMapping = originDestinationMapping;
		this.ampCorrection = ampCorrection;
		this.dcMicroMapping = dcMicroMapping;
		this.conductorTempDerating = conductorTempDerating;
		this.requiredConductorAmpacity = requiredConductorAmpacity;
		this.dcCircuitMapping = dcCircuitMapping;
		this.acCircuitMapping = acCircuitMapping;
		this.essConnectorsRepo = essConnectorsRepo;
	}

	// 05-27-2019 A.B: CR-2628-MOD-003 Choose Sizing for Conductors Values
	static final String EXISTING_SUB_PANEL = "Show the word “Existing” instead of a conductor size (Some AHJs may not approve the permit with this wording)";
	static final String SMALLEST_CONDUCTOR_SIZE = "Show the smallest conductor size allowed by NEC Code for this circumstance";
	static final String CHOOSE_CONDUCTOR_SIZE = "I want to choose the conductor size";

	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	Boolean repeatingACMapping = false;

	public String acCircuitbeforeRevision(AcroFields form, String acCircuit, int i,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, int microNumberOfStrings,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, List<String> acCircuitEnvironment, String conduitRun,
			Integer numberOfConductor, int sheetIndex, PermitHomeSiteInfoEntity permitHomeSite, Boolean stepACCombiner,
			int indexAcCombiner, E002Model params) {
		String correctedAmpacity = "";
		try {

			Integer conductorQty = 0;
//			S.B CR-3119-MOD-004 Number of Conductors on AC circuits on E-002
//			A.B 08-05-2021 CR-PM-Revision-3119-MOD-005 Number of Conductors on AC circuits on E-002
			Integer branchCircuit = checkValue.Equals(stepACCombiner, true) && indexAcCombiner <= i ? 1
					: microNumberOfStrings;
			if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")) {
				form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", "2");
				conductorQty = 2;
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
				form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						String.valueOf(2 * branchCircuit));
				conductorQty = 2 * branchCircuit;
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
				form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						String.valueOf(3 * branchCircuit));
				conductorQty = 3 * branchCircuit;
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
				form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						String.valueOf(4 * branchCircuit));
				conductorQty = 4 * branchCircuit;
			}

			if (checkValue.contains(acCircuit, "-") && checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
					&& checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing")
					&& checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), CHOOSE_CONDUCTOR_SIZE)) {

				if (checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "EXIST")) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", "EXISTING");
					acTradeSize.add("EXISTING");

				} else {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE",
							checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
									? permitProjectSiteInfo.getSubPanelConductorSizeOther()
									: permitProjectSiteInfo.getSubPanelConductorSize());
					acTradeSize.add(checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
							? permitProjectSiteInfo.getSubPanelConductorSizeOther()
							: permitProjectSiteInfo.getSubPanelConductorSize());
					params.setNEC310(nec3106B16Repo.findFirstBytradeSze(
							checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
									? permitProjectSiteInfo.getSubPanelConductorSizeOther()
									: permitProjectSiteInfo.getSubPanelConductorSize()));
					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-AC" + i + "-MATERIAL",
								"(" + params.getNEC310().getNumberOfConductors() + ") CU");
						acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());

						form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								checkValue.Equals(acCircuitEnvironment.get(i - 1), "ATTIC")
										&& checkValue.Equals(conduitRun, "Romex in Attic")
												? "N/A"
												: ampCorrection.getACAmpacityCorrectionB3a(circuit,
														acCircuit.split("-")[i - 1], conductorQty, numberOfConductor,
														false, false));
						form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								checkValue.Equals(acCircuitEnvironment.get(i - 1), "ATTIC")
										&& checkValue.Equals(conduitRun, "Romex in Attic")
												? "1.0"
												: ampCorrection.getACAmpacityCorrectionB3a(circuit,
														acCircuit.split("-")[i - 1], conductorQty, numberOfConductor,
														true, false));
						params.setConduitFillDerating(checkValue.Equals(acCircuitEnvironment.get(i - 1), "ATTIC")
								&& checkValue.Equals(conduitRun, "Romex in Attic") ? "1"
										: ampCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
												conductorQty, numberOfConductor, true, false));

						if (params.getNEC310().getSeventyFiveInsulation() != null) {
							form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							try {
								if (checkValue.NotEquals(params.getConduitFillDerating(), "N/A")
										&& checkValue.NotEquals(params.getTempDerating(), "")) {
									form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY",
											String.valueOf(new DecimalFormat("#.0")
													.format(Float.parseFloat(params.getTempDerating())
															* Float.parseFloat(String
																	.valueOf(String.valueOf(params.getNEC310()
																			.getSeventyFiveInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getConduitFillDerating()))));
									form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1",
											String.valueOf(new DecimalFormat("#.0")
													.format(Float.parseFloat(params.getTempDerating())
															* Float.parseFloat(String
																	.valueOf(String.valueOf(params.getNEC310()
																			.getSeventyFiveInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getConduitFillDerating()))));
									correctedAmpacity = String
											.valueOf(new DecimalFormat("#.0")
													.format(Float.parseFloat(params.getTempDerating())
															* Float.parseFloat(String
																	.valueOf(String.valueOf(params.getNEC310()
																			.getSeventyFiveInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getConduitFillDerating())));
								} else if (checkValue.NotEquals(params.getTempDerating(), "")) {
									form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY",
											String.valueOf(new DecimalFormat("#.0").format(
													Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
															.valueOf(String.valueOf(
																	params.getNEC310().getSeventyFiveInsulation()))
															.replace(',', '.')))));
									form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1",
											String.valueOf(new DecimalFormat("#.0").format(
													Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
															.valueOf(String.valueOf(
																	params.getNEC310().getSeventyFiveInsulation()))
															.replace(',', '.')))));
									correctedAmpacity = String.valueOf(new DecimalFormat("#.0")
											.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
													.valueOf(String
															.valueOf(params.getNEC310().getSeventyFiveInsulation()))
													.replace(',', '.'))));
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					} else {
						acNumberOfConductors.add(1);
						form.setField(sheetIndex + "-AC" + i + "-MATERIAL", "CU");
					}

				}
			} else if (checkValue.contains(acCircuit, "-")
					&& checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")) {
				if ((checkValue.Equals(circuit.getConductorTypeSixExisting(), true)
						|| checkValue.Equals(circuit.getConductorSizeSix(), "EXIST"))
						|| checkValue.Equals(permitEntity.getExistInverter(), true)) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "- ", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", "EXISTING");
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", "EXISTING");
					acTradeSize.add("EXISTING");
				} else {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "#12 AWG");
					form.setField(sheetIndex + "-AC" + i + "-MATERIAL", "(1) CU");
					acNumberOfConductors.add(1);
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "25");
					// 05-30-2019:A.B: CONDUCTOR AMPACTIY RATING for TRADE SIZE #12 AWG equals 25
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "25");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "N/A");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");
					// 05-30-2019:A.B: ConduitFillDerating = 1 was removed for the equation
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", String
							.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25)));
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", String
							.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25)));
					params.setConduitFillDerating("1");
					acTradeSize.add("#12 AWG");
					correctedAmpacity = String
							.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25));
				}
			} else {
				// CI :21/06/2019 : CR 2742

				form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						checkValue.Equals(acCircuitEnvironment.get(i - 1), "ATTIC")
								&& checkValue.Equals(conduitRun, "Romex in Attic") ? "N/A"
										: ampCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
												conductorQty, numberOfConductor, false, false));
				form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						checkValue.Equals(acCircuitEnvironment.get(i - 1), "ATTIC")
								&& checkValue.Equals(conduitRun, "Romex in Attic") ? "1.0"
										: ampCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
												conductorQty, numberOfConductor, true, false));
				params.setConduitFillDerating(checkValue.Equals(acCircuitEnvironment.get(i - 1), "ATTIC")
						&& checkValue.Equals(conduitRun, "Romex in Attic") ? "1"
								: ampCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
										conductorQty, numberOfConductor, true, false));

				params.setNec31016Column90(params.getRequiredAmpacity() / (Float.parseFloat(params.getTempDerating())
						* Float.parseFloat(params.getConduitFillDerating())));

				Integer nec310 = 0;
				if (checkValue.isStringInt(params.getNec31016Column90() + "")) {
					nec310 = Integer.parseInt(params.getNec31016Column90() + "");
				} else if (!checkValue.isStringInt(params.getNec31016Column90() + "")
						&& checkValue.isNumeric(params.getNec31016Column90() + "")) {
					nec310 = (int) Math.round(params.getNec31016Column90());
				}
				if (params.getRequiredAmpacity() > 30) {
					String tradeSizeValue = "";
					if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
							&& !params.getIncorrectTradeSize().isEmpty()) {
						tradeSizeValue = nec3106B16Repo.findTradeSizeBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
								"#12 AWG", params.getIncorrectTradeSize());// Set Max Result
					} else {
						tradeSizeValue = nec3106B16Repo.findTradeSizeBySeventyFiveInsulationAndTradeSize(nec310,
								"#12 AWG");// Set Max Result
					}

					if (checkValue.contains(tradeSizeValue, "AWG") && checkValue.notContains(tradeSizeValue, "/")) {
						Integer tradeNumber = Integer.parseInt(tradeSizeValue.split("\\s+")[0].split("#")[1]);
						// CR-2973 - Minimum 8 AWG Conductors on 30 Amp OCPD
						if (tradeNumber > 8) {
							if (Boolean.FALSE.equals(params.getIncorrectTradeSizeLogic())
									|| (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
											&& !params.getIncorrectTradeSize().isEmpty()
											&& !params.getIncorrectTradeSize().contains("#8 AWG"))) {
								params.setNEC310(nec3106B16Repo.findFirstBytradeSze("#8 AWG"));

							} else if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
									&& !params.getIncorrectTradeSize().isEmpty()) {
								params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
										"#12 AWG", params.getIncorrectTradeSize()));
							} else {
								params.setNEC310(
										nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
							}
						} else {
							if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
									&& !params.getIncorrectTradeSize().isEmpty()) {
								params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
										"#12 AWG", params.getIncorrectTradeSize()));
							} else {
								params.setNEC310(
										nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
							}
						}
					} else {
						if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
								&& !params.getIncorrectTradeSize().isEmpty()) {
							params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
									"#12 AWG", params.getIncorrectTradeSize()));
						} else {
							params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
						}
					}
				} else {
					if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
							&& !params.getIncorrectTradeSize().isEmpty()) {
						params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
								"#12 AWG", params.getIncorrectTradeSize()));
					} else {
						params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
					}
				}
				if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
						&& ((checkValue.Equals(circuit.getConductorTypeSevenExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistAcJunctionBox(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
					acTradeSize.add("EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
						&& ((checkValue.Equals(circuit.getConductorTypeEightExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistAcCombiner(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
					acTradeSize.add("EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
						&& ((checkValue.Equals(circuit.getConductorTypeNineExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
					acTradeSize.add("EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
						&& ((checkValue.Equals(circuit.getConductorTypeNineTwoExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
					acTradeSize.add("EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
						&& ((checkValue.Equals(circuit.getConductorTypeTenExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistProductionMeter(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
					acTradeSize.add("EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
						&& ((checkValue.Equals(circuit.getConductorTypeElevenExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistSubPanel(), true)) || checkValue.Equals(
										permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL))) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
					acTradeSize.add("EXISTING");
				} else {
					if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
							&& checkValue.Equals(circuit.getConductorSizeSeven(), "EXIST")) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
						acTradeSize.add("EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& checkValue.Equals(circuit.getConductorSizeEight(), "EXIST")) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
						acTradeSize.add("EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& checkValue.Equals(circuit.getConductorSizeNine(), "EXIST")) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
						acTradeSize.add("EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& checkValue.Equals(circuit.getConductorSizeNineTwo(), "EXIST")) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
						acTradeSize.add("EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& checkValue.Equals(circuit.getConductorSizeTen(), "EXIST")) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
						acTradeSize.add("EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& (checkValue.Equals(circuit.getConductorSizeEleven(), "EXIST")
									|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "EXIST"))) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
						acTradeSize.add("EXISTING");
					} else if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
						params.getIncorrectTradeSize().add(params.getNEC310().getTradeSze());
						acTradeSize.add(params.getNEC310().getTradeSze());
					} else {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "");
						params.getIncorrectTradeSize().clear();
						acTradeSize.add("");
					}
				}
				if (params.getNEC310() != null) {
					form.setField(sheetIndex + "-AC" + i + "-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					params.setCalculatedNumberOfConductor(params.getNEC310().getNumberOfConductors());
					acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
				} else {
					acNumberOfConductors.add(1);
					params.setCalculatedNumberOfConductor(1);
					form.setField(sheetIndex + "-AC" + i + "-MATERIAL", "(1) CU");
				}

				if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
						&& ((checkValue.Equals(circuit.getConductorTypeSixExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistInverter(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
						&& ((checkValue.Equals(circuit.getConductorTypeSevenExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistAcJunctionBox(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
						&& ((checkValue.Equals(circuit.getConductorTypeEightExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistAcCombiner(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
						&& ((checkValue.Equals(circuit.getConductorTypeNineExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
						&& ((checkValue.Equals(circuit.getConductorTypeNineTwoExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
						&& ((checkValue.Equals(circuit.getConductorTypeTenExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistProductionMeter(), true)))) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
						&& ((checkValue.Equals(circuit.getConductorTypeElevenExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistSubPanel(), true)) || checkValue.Equals(
										permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL))) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else if (params.getNEC310() != null && params.getNEC310().getSeventyFiveInsulation() != null) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
				}
				if (params.getNEC310() != null && params.getNEC310().getSeventyFiveInsulation() != null) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
							String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
					try {
						if (checkValue.NotEquals(params.getConduitFillDerating(), "N/A")) {
							form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY",
									String.valueOf(
											new DecimalFormat("#.0")
													.format(Float.parseFloat(params.getTempDerating())
															* Float.parseFloat(String
																	.valueOf(String.valueOf(params.getNEC310()
																			.getSeventyFiveInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getConduitFillDerating()))));
							form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1",
									String.valueOf(
											new DecimalFormat("#.0")
													.format(Float.parseFloat(params.getTempDerating())
															* Float.parseFloat(String
																	.valueOf(String.valueOf(params.getNEC310()
																			.getSeventyFiveInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getConduitFillDerating()))));
							correctedAmpacity = String
									.valueOf(
											new DecimalFormat("#.0")
													.format(Float.parseFloat(params.getTempDerating())
															* Float.parseFloat(String
																	.valueOf(String.valueOf(params.getNEC310()
																			.getSeventyFiveInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getConduitFillDerating())));
						} else {
							form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY",
									String.valueOf(new DecimalFormat("#.0")
											.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
													.valueOf(String
															.valueOf(params.getNEC310().getSeventyFiveInsulation()))
													.replace(',', '.')))));
							form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1",
									String.valueOf(new DecimalFormat("#.0")
											.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
													.valueOf(String
															.valueOf(params.getNEC310().getSeventyFiveInsulation()))
													.replace(',', '.')))));
							correctedAmpacity = String.valueOf(new DecimalFormat("#.0")
									.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
											.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation()))
											.replace(',', '.'))));
						}
					} catch (Exception e) {

						e.printStackTrace();
					}

				}

			}

			if (!checkValue.Equals(numberOfConductor, params.getCalculatedNumberOfConductor())
					&& Boolean.FALSE.equals(repeatingACMapping)) {
				repeatingACMapping = true;
				acTradeSize.remove(acTradeSize.size() - 1);
				correctedAmpacity = acCircuitbeforeRevision(form, acCircuit, i, permitProjectSiteInfo,
						microNumberOfStrings, circuit, permitEntity, acTradeSize, acNumberOfConductors,
						acCircuitEnvironment, conduitRun, params.getCalculatedNumberOfConductor(), sheetIndex,
						permitHomeSite, stepACCombiner, indexAcCombiner, params);
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

		return correctedAmpacity;
	}

	public String acCircuitVoltageDrop(AcroFields form, int i, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, int sheetIndex, E002Model params) {
		String correctedAmpacity = "";
		try {

			form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
			acTradeSize.add(params.getNEC310().getTradeSze());

			form.setField(sheetIndex + "-AC" + i + "-MATERIAL",
					"(" + params.getNEC310().getNumberOfConductors() + ") CU");
			acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
			if (params.getNEC310().getSeventyFiveInsulation() != null) {
				form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
						String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
				form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
						String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
				try {
					if (checkValue.NotEquals(params.getConduitFillDerating(), "N/A")
							&& checkValue.isStringNotEmpty(params.getConduitFillDerating())
							&& checkValue.NotEquals(params.getTempDerating(), "")) {

						form.setField(
								sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", String
										.valueOf(
												new DecimalFormat("#.0")
														.format(Float.parseFloat(params.getTempDerating())
																* Float.parseFloat(String
																		.valueOf(String.valueOf(params.getNEC310()
																				.getSeventyFiveInsulation()))
																		.replace(',', '.'))
																* Float.parseFloat(params.getConduitFillDerating()))));
						form.setField(
								sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", String
										.valueOf(
												new DecimalFormat("#.0")
														.format(Float.parseFloat(params.getTempDerating())
																* Float.parseFloat(String
																		.valueOf(String.valueOf(params.getNEC310()
																				.getSeventyFiveInsulation()))
																		.replace(',', '.'))
																* Float.parseFloat(params.getConduitFillDerating()))));
						correctedAmpacity = String
								.valueOf(
										new DecimalFormat(
												"#.0").format(
														Float.parseFloat(params.getTempDerating())
																* Float.parseFloat(String
																		.valueOf(String.valueOf(params.getNEC310()
																				.getSeventyFiveInsulation()))
																		.replace(',', '.'))
																* Float.parseFloat(params.getConduitFillDerating())));
					} else if (checkValue.NotEquals(params.getTempDerating(), "")) {
						form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", String.valueOf(new DecimalFormat(
								"#.0")
								.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
										String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation()))
												.replace(',', '.')))));
						form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", String.valueOf(new DecimalFormat(
								"#.0")
								.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
										String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation()))
												.replace(',', '.')))));
						correctedAmpacity = String.valueOf(new DecimalFormat("#.0")
								.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
										String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation()))
												.replace(',', '.'))));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return correctedAmpacity;
	}

	public void mapACCircuit(AcroFields form, String acCircuit, int i, PermitConduitConductorSectionEntity circuit,
			PlansetUtils plansetUtils, int indexAcCombiner, Boolean stepACCombiner, Inverters microInverterInfo,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, Integer fourPerCentAverageHigh,
			List<String> acCircuitEnvironment, PermitEntity permitEntity, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, ACCombinerSLC acCombiner, ACCombinerSLC slcAcCombiner, AuthentificationEntity userConnectedEntity,
			PermitLayoutEntity permitLayoutEntity, int sheetIndex, UserSettingEntity userSetting,
			PermitHomeSiteInfoEntity permitHomeSite, String necCode, E002Model params, List<VoltageDropTable> voltageDrop) {

		try {
			params.setIncorrectTradeSizeLogic(false);
			String combiningCircuit = permitProjectSiteInfo != null ? permitProjectSiteInfo.getCombiningACCircuits() : "";
			String category = slcAcCombiner != null ? slcAcCombiner.getCategory() : acCombiner != null ? acCombiner.getCategory() : "";
			originDestinationMapping.orgDestMapping(i, form, acCircuit, sheetIndex, acCircuit.split("-").length,
					permitProjectSiteInfo.getMainPanelUpgrade(), false, combiningCircuit, category);
			if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& ((checkValue.Equals(circuit.getConductorTypeSixExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistInverter(), true)))) {
				defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
				acNumberOfConductors.add(1);
				acCircuitEnvironment.add("EXISTING");
				acTradeSize.add("EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
					&& ((checkValue.Equals(circuit.getConductorTypeSevenExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistAcJunctionBox(), true)))) {
				defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
				acNumberOfConductors.add(1);
				acCircuitEnvironment.add("EXISTING");
				acTradeSize.add("EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
					&& ((checkValue.Equals(circuit.getConductorTypeEightExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistAcCombiner(), true)))) {

				defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
				acNumberOfConductors.add(1);
				acCircuitEnvironment.add("EXISTING");
				acTradeSize.add("EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
					&& ((checkValue.Equals(circuit.getConductorTypeNineExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
				defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
				acNumberOfConductors.add(1);
				acCircuitEnvironment.add("EXISTING");
				acTradeSize.add("EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
					&& ((checkValue.Equals(circuit.getConductorTypeNineTwoExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
				defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
				acNumberOfConductors.add(1);
				acCircuitEnvironment.add("EXISTING");
				acTradeSize.add("EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
					&& ((checkValue.Equals(circuit.getConductorTypeTenExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistProductionMeter(), true)))) {
				defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
				acNumberOfConductors.add(1);
				acCircuitEnvironment.add("EXISTING");
				acTradeSize.add("EXISTING");
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
					&& ((checkValue.Equals(circuit.getConductorTypeElevenExisting(), true))
							|| (checkValue.Equals(permitEntity.getExistSubPanel(), true)) || checkValue
									.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL))) {
				defaultRowMapping.mapAcDefaultValue(i, form, sheetIndex, "EXISTING");
				acNumberOfConductors.add(1);
				acCircuitEnvironment.add("EXISTING");
				acTradeSize.add("EXIST");
			} else {
				String correctedAmpacity = "";
				String tempAdder = "";

				///// **** CONDUCTOR SPECIFICATIONS ****//////

				form.setField(sheetIndex + "-AC" + i + "-MATERIAL", "CU");
				form.setField(sheetIndex + "-AC" + i + "-TEMPERATURE", "75");

				///// **** RREQUIRED CONDUCTOR AMPACITY ****//////
				requiredConductorAmpacity.mapMicroACrequiredAmpacity(form, plansetUtils, i, indexAcCombiner,
						stepACCombiner, microInverterInfo, sheetIndex,
						checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL"), permitProjectSiteInfo, params);

				tempAdder = conductorTempDerating.conductorTemperatureDeratingMapping(form, acCircuit, i,
						fourPerCentAverageHigh, acCircuitEnvironment, false, circuit, sheetIndex, userSetting, necCode,
						permitLayoutEntity.getShowConduitRoofAsHeight(), params, permitHomeSite.getState());

				if ((userConnectedEntity.getRoleEntity().getId() == 2)
						|| (userConnectedEntity.getRoleEntity().getId() != 2
								&& !(checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {
					params.setCalculatedNumberOfConductor(1);
					repeatingACMapping = false;

					correctedAmpacity = acCircuitbeforeRevision(form, acCircuit, i, permitProjectSiteInfo,
							plansetUtils.getNumberOfBranchCircuit(), circuit, permitEntity, acTradeSize,
							acNumberOfConductors, acCircuitEnvironment, permitLayoutEntity.getConduitRun(), 1,
							sheetIndex, permitHomeSite, stepACCombiner, indexAcCombiner, params);

					if (params.getCalculatedNumberOfConductor() == 2 || params.getCalculatedNumberOfConductor() == 3) {
						params.setNec31016Column90(
								params.getRequiredAmpacity() / (Float.parseFloat(params.getTempDerating())
										* Float.parseFloat(params.getConduitFillDerating())));
						Integer nec310 = 0;
						if (checkValue.isStringInt(params.getNec31016Column90() + "")) {
							nec310 = Integer.parseInt(params.getNec31016Column90() + "");
						} else if (!checkValue.isStringInt(params.getNec31016Column90() + "")
								&& checkValue.isNumeric(params.getNec31016Column90() + "")) {
							nec310 = (int) Math.round(params.getNec31016Column90());
						}

						if (params.getRequiredAmpacity() > 30) {
							String tradeSizeValue = "";
							if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
									&& !params.getIncorrectTradeSize().isEmpty()) {
								tradeSizeValue = nec3106B16Repo.findTradeSizeBySeventyFiveInsulationAndTradeSizeIsNotIn(
										nec310, "#12 AWG", params.getIncorrectTradeSize());// Set Max Result
							} else {
								tradeSizeValue = nec3106B16Repo.findTradeSizeBySeventyFiveInsulationAndTradeSize(nec310,
										"#12 AWG");// Set Max Result
							}

							if (checkValue.contains(tradeSizeValue, "AWG")
									&& checkValue.notContains(tradeSizeValue, "/")) {
								Integer tradeNumber = Integer.parseInt(tradeSizeValue.split("\\s+")[0].split("#")[1]);
								// CR-2973 - Minimum 8 AWG Conductors on 30 Amp OCPD
								if (tradeNumber > 8) {
									if (Boolean.FALSE.equals(params.getIncorrectTradeSizeLogic())
											|| (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
													&& !params.getIncorrectTradeSize().isEmpty()
													&& !params.getIncorrectTradeSize().contains("#8 AWG"))) {
										params.setNEC310(nec3106B16Repo.findFirstBytradeSze("#8 AWG"));
									} else if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
											&& !params.getIncorrectTradeSize().isEmpty()) {
										params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(
												nec310, "#12 AWG", params.getIncorrectTradeSize()));
									} else {
										params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310,
												"#12 AWG"));
									}

								} else {
									if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
											&& !params.getIncorrectTradeSize().isEmpty()) {
										params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(
												nec310, "#12 AWG", params.getIncorrectTradeSize()));
									} else {
										params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310,
												"#12 AWG"));
									}

								}
							} else {
								if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
										&& !params.getIncorrectTradeSize().isEmpty()) {
									params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(
											nec310, "#12 AWG", params.getIncorrectTradeSize()));
								} else {
									params.setNEC310(
											nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
								}

							}
						} else {
							if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
									&& !params.getIncorrectTradeSize().isEmpty()) {
								params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
										"#12 AWG", params.getIncorrectTradeSize()));
							} else {
								params.setNEC310(
										nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
							}
						}
						if (params.getNEC310() != null) {
							acNumberOfConductors.remove(acNumberOfConductors.size() - 1);
							acTradeSize.remove(acTradeSize.size() - 1);
							repeatingACMapping = false;
							correctedAmpacity = acCircuitbeforeRevision(form, acCircuit, i, permitProjectSiteInfo,
									plansetUtils.getNumberOfBranchCircuit(), circuit, permitEntity, acTradeSize,
									acNumberOfConductors, acCircuitEnvironment, permitLayoutEntity.getConduitRun(),
									params.getCalculatedNumberOfConductor(), sheetIndex, permitHomeSite, stepACCombiner,
									indexAcCombiner, params);
						}
					}

					if (params.getNEC310() != null && checkValue.contains(acCircuit, "-") && (checkValue
							.NotEquals(acCircuit.split("-")[i - 1], "INVERTER")
							&& !(checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL") && checkValue
									.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), CHOOSE_CONDUCTOR_SIZE)))
							&& checkValue.NotEquals(correctedAmpacity, "")
							&& checkValue.getFloatValue(correctedAmpacity) < checkValue
									.getFloatValue(params.getRequiredACConductorAmpacity())) {
						params.setIncorrectTradeSizeLogic(true);
						params.setTradeSizeRepeate("");
						do {
							// Should check if we still have trade size after the incorrect one
							params.setNec31016Column90(
									params.getRequiredAmpacity() / (Float.parseFloat(params.getTempDerating())
											* Float.parseFloat(params.getConduitFillDerating())));
							Integer nec310 = 0;
							if (checkValue.isStringInt(params.getNec31016Column90() + "")) {
								nec310 = Integer.parseInt(params.getNec31016Column90() + "");
							} else if (!checkValue.isStringInt(params.getNec31016Column90() + "")
									&& checkValue.isNumeric(params.getNec31016Column90() + "")) {
								nec310 = (int) Math.round(params.getNec31016Column90());
							}

							if (params.getRequiredAmpacity() > 30) {
								String tradeSizeValue = "";

								if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
										&& !params.getIncorrectTradeSize().isEmpty()) {
									tradeSizeValue = nec3106B16Repo
											.findTradeSizeBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310, "#12 AWG",
													params.getIncorrectTradeSize());// Set Max Result
								} else {
									tradeSizeValue = nec3106B16Repo
											.findTradeSizeBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG");// Set
																													// Max
																													// Result
								}

								if (checkValue.contains(tradeSizeValue, "AWG")
										&& checkValue.notContains(tradeSizeValue, "/")) {
									Integer tradeNumber = Integer
											.parseInt(tradeSizeValue.split("\\s+")[0].split("#")[1]);
									// CR-2973 - Minimum 8 AWG Conductors on 30 Amp OCPD
									if (tradeNumber > 8) {
										if (Boolean.FALSE.equals(params.getIncorrectTradeSizeLogic())
												|| (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
														&& !params.getIncorrectTradeSize().isEmpty()
														&& !params.getIncorrectTradeSize().contains("#8 AWG"))) {
											params.setNEC310(nec3106B16Repo.findFirstBytradeSze("#8 AWG"));
										} else if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
												&& !params.getIncorrectTradeSize().isEmpty()) {
											params.setNEC310(
													nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(
															nec310, "#12 AWG", params.getIncorrectTradeSize()));
										} else {
											params.setNEC310(nec3106B16Repo
													.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
										}

									} else {
										if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
												&& !params.getIncorrectTradeSize().isEmpty()) {
											params.setNEC310(
													nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(
															nec310, "#12 AWG", params.getIncorrectTradeSize()));
										} else {
											params.setNEC310(nec3106B16Repo
													.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
										}

									}
								} else {
									if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
											&& !params.getIncorrectTradeSize().isEmpty()) {
										params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(
												nec310, "#12 AWG", params.getIncorrectTradeSize()));
									} else {
										params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310,
												"#12 AWG"));
									}

								}
							} else {
								if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
										&& !params.getIncorrectTradeSize().isEmpty()) {
									params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(
											nec310, "#12 AWG", params.getIncorrectTradeSize()));
								} else {
									params.setNEC310(
											nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
								}
							}

							if (params.getNEC310() != null && checkValue.NotEquals(params.getTradeSizeRepeate(),
									params.getNEC310().getTradeSze())) {
								acNumberOfConductors.remove(acNumberOfConductors.size() - 1);
								acTradeSize.remove(acTradeSize.size() - 1);
								repeatingACMapping = false;
								params.setTradeSizeRepeate(params.getNEC310().getTradeSze());
								correctedAmpacity = acCircuitbeforeRevision(form, acCircuit, i, permitProjectSiteInfo,
										plansetUtils.getNumberOfBranchCircuit(), circuit, permitEntity, acTradeSize,
										acNumberOfConductors, acCircuitEnvironment, permitLayoutEntity.getConduitRun(),
										params.getCalculatedNumberOfConductor(), sheetIndex, permitHomeSite,
										stepACCombiner, indexAcCombiner, params);
							}

						} while (params.getNEC310() != null && checkValue.NotEquals(correctedAmpacity, "")
								&& checkValue.getFloatValue(correctedAmpacity) <= checkValue
										.getFloatValue(params.getRequiredACConductorAmpacity()));

					}

					String tradeSize = form.getField(sheetIndex + "-AC" + i + "-TRADE-SIZE");
					if (tradeSize != null && !tradeSize.equals("EXISTING")) {
						String maxInvOutputCurrent = form
								.getField(sheetIndex + "-AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT");
						Float vd = voltageDropCalculation.acVoltageDrop(i, acCircuit, circuit,
								acTradeSize.get(acTradeSize.size() - 1),
								acNumberOfConductors.get(acNumberOfConductors.size() - 1), maxInvOutputCurrent,
								permitHomeSite.getIfServiceVoltage(), permitHomeSite.getServiceVoltage());

						if (vd != 0.0 && vd >= 1.99) {
							Boolean sameTradeSize = false;
							tradeSize = acTradeSize.get(acTradeSize.size() - 1);
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
										correctedAmpacity = acCircuitVoltageDrop(form, i, acTradeSize,
												acNumberOfConductors, sheetIndex, params);
										maxInvOutputCurrent = form
												.getField(sheetIndex + "-AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT");
										vd = voltageDropCalculation.acVoltageDrop(i, acCircuit, circuit,
												acTradeSize.get(acTradeSize.size() - 1),
												acNumberOfConductors.get(acNumberOfConductors.size() - 1),
												maxInvOutputCurrent, permitHomeSite.getIfServiceVoltage(),
												permitHomeSite.getServiceVoltage());
									}

								}

							} while (vd != 0.0 && vd >= 1.99 && params.getNEC310() != null
									&& Boolean.FALSE.equals(sameTradeSize));
						}

						if (vd != 0.0) {
							//Origin
							VoltageDropTable v = new VoltageDropTable();
							if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")) {
								v.setCircuitOrigin("SUB-PANEL");
							} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")) {
								v.setCircuitOrigin("AC DISCONNECT TWO");// A.B 07/21/2022 PR-045
							} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {
								v.setCircuitOrigin(getACCType(false, combiningCircuit, category));
							} else {
								v.setCircuitOrigin(acCircuit.split("-")[i - 1]);
							}
							// Destination
							if (i == acCircuit.split("-").length) {
								if (Boolean.TRUE.equals(permitProjectSiteInfo.getMainPanelUpgrade())) {
									v.setCircuitDestination("NEW SERVICE PANEL");
								} else {
									v.setCircuitDestination("EXISTING SERVICE PANEL");
								}

							} else {
								if (checkValue.Equals(acCircuit.split("-")[i], "SUB PANEL")) {
									v.setCircuitDestination("SUB-PANEL");
								} else if (checkValue.Equals(acCircuit.split("-")[i], "AC DISCONNECTTwo")) {
									v.setCircuitDestination("AC DISCONNECT TWO");// A.B 07/21/2022 PR-045
								} else if (checkValue.Equals(acCircuit.split("-")[i], "AC COMBINER/LOAD CENTER")) {
									v.setCircuitDestination(getACCType(false, combiningCircuit, category));
								} else {
									v.setCircuitDestination(acCircuit.split("-")[i]);
								}
							}
							v.setAcDc("AC");
							v.setVd(vd);
							voltageDrop.add(v);
						}
					}

				} else if (userConnectedEntity.getRoleEntity().getId() != 2
						&& checkValue.Equals(circuit.getMapFromUserInput(), true)) {

					params = conduitFillDeratingAfterRev.mapACexistingafterRevision(form, i, acCircuit, circuit,
							permitEntity, acTradeSize, acNumberOfConductors, sheetIndex, permitProjectSiteInfo, params);

				}

				if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER") && i == 1) {
					if (checkValue.isNumeric(tempAdder) && checkValue.isNumeric(params.getRequiredACConductorAmpacity())
							&& checkValue.isNumeric(correctedAmpacity)) {
						Float requiredAmpacityFloat = Float.valueOf(params.getRequiredACConductorAmpacity());
						Float correctedAmpacityFloat = Float.valueOf(correctedAmpacity);
						Float tempAdderFloat = Float.valueOf(tempAdder);
						if (tempAdderFloat > 0 && correctedAmpacityFloat < requiredAmpacityFloat) {
							acCircuitEnvironment.remove(acCircuitEnvironment.size() - 1);
							conductorTempDerating.conductorTemperatureDeratingMapping(form, acCircuit, i,
									fourPerCentAverageHigh, acCircuitEnvironment, true, circuit, sheetIndex,
									userSetting, necCode, permitLayoutEntity.getShowConduitRoofAsHeight(), params,
									permitHomeSite.getState());
							if ((userConnectedEntity.getRoleEntity().getId() == 2)
									|| (userConnectedEntity.getRoleEntity().getId() != 2
											&& !(checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {
								params.setCalculatedNumberOfConductor(1);
								acTradeSize.remove(acTradeSize.size() - 1);
								repeatingACMapping = false;
								acCircuitbeforeRevision(form, acCircuit, i, permitProjectSiteInfo,
										plansetUtils.getNumberOfBranchCircuit(), circuit, permitEntity, acTradeSize,
										acNumberOfConductors, acCircuitEnvironment, permitLayoutEntity.getConduitRun(),
										1, sheetIndex, permitHomeSite, stepACCombiner, indexAcCombiner, params);

								// 24/06/2019 CI : CR 2742
								if (params.getCalculatedNumberOfConductor() == 2
										|| params.getCalculatedNumberOfConductor() == 3) {
									params.setNec31016Column90(
											params.getRequiredAmpacity() / (Float.parseFloat(params.getTempDerating())
													* Float.parseFloat(params.getConduitFillDerating())));
									Integer nec310 = 0;
									if (checkValue.isStringInt(params.getNec31016Column90() + "")) {
										nec310 = Integer.parseInt(params.getNec31016Column90() + "");
									} else if (!checkValue.isStringInt(params.getNec31016Column90() + "")
											&& checkValue.isNumeric(params.getNec31016Column90() + "")) {
										nec310 = (int) Math.round(params.getNec31016Column90());
									}

									if (params.getRequiredAmpacity() > 30) {
										String tradeSizeValue = "";
										if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
												&& !params.getIncorrectTradeSize().isEmpty()) {
											tradeSizeValue = nec3106B16Repo
													.findTradeSizeBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
															"#12 AWG", params.getIncorrectTradeSize());// Set Max Result
										} else {
											tradeSizeValue = nec3106B16Repo
													.findTradeSizeBySeventyFiveInsulationAndTradeSize(nec310,
															"#12 AWG");// Set Max Result
										}
										if (checkValue.contains(tradeSizeValue, "AWG")
												&& checkValue.notContains(tradeSizeValue, "/")) {
											Integer tradeNumber = Integer
													.parseInt(tradeSizeValue.split("\\s+")[0].split("#")[1]);

											// CR-2973 - Minimum 8 AWG Conductors on 30 Amp OCPD
											if (tradeNumber > 8) {
												if (Boolean.FALSE.equals(params.getIncorrectTradeSizeLogic())
														|| (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
																&& !params.getIncorrectTradeSize().isEmpty() && !params
																		.getIncorrectTradeSize().contains("#8 AWG"))) {
													params.setNEC310(nec3106B16Repo.findFirstBytradeSze("#8 AWG"));
												} else if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
														&& !params.getIncorrectTradeSize().isEmpty()) {
													params.setNEC310(nec3106B16Repo
															.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
																	"#12 AWG", params.getIncorrectTradeSize()));
												} else {
													params.setNEC310(
															nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(
																	nec310, "#12 AWG"));
												}
											} else {
												if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
														&& !params.getIncorrectTradeSize().isEmpty()) {
													params.setNEC310(nec3106B16Repo
															.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
																	"#12 AWG", params.getIncorrectTradeSize()));
												} else {
													params.setNEC310(
															nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(
																	nec310, "#12 AWG"));
												}

											}
										} else {
											if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
													&& !params.getIncorrectTradeSize().isEmpty()) {
												params.setNEC310(
														nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(
																nec310, "#12 AWG", params.getIncorrectTradeSize()));
											} else {
												params.setNEC310(nec3106B16Repo
														.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
											}

										}
									} else {
										if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
												&& !params.getIncorrectTradeSize().isEmpty()) {
											params.setNEC310(
													nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(
															nec310, "#12 AWG", params.getIncorrectTradeSize()));
										} else {
											params.setNEC310(nec3106B16Repo
													.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
										}
									}
									if (params.getNEC310() != null) {
										acNumberOfConductors.remove(acNumberOfConductors.size() - 1);
										acTradeSize.remove(acTradeSize.size() - 1);
										repeatingACMapping = false;
										acCircuitbeforeRevision(form, acCircuit, i, permitProjectSiteInfo,
												plansetUtils.getNumberOfBranchCircuit(), circuit, permitEntity,
												acTradeSize, acNumberOfConductors, acCircuitEnvironment,
												permitLayoutEntity.getConduitRun(),
												params.getCalculatedNumberOfConductor(), sheetIndex, permitHomeSite,
												stepACCombiner, indexAcCombiner, params);
									}
								}

							} else if (userConnectedEntity.getRoleEntity().getId() != 2
									&& checkValue.Equals(circuit.getMapFromUserInput(), true)) {

								params = conduitFillDeratingAfterRev.mapACexistingafterRevision(form, i, acCircuit,
										circuit, permitEntity, acTradeSize, acNumberOfConductors, sheetIndex,
										permitProjectSiteInfo, params);

							}
						}
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

	}

	public File buildingPDFE002(PermitHomeSiteInfoEntity permitHomeSite, Long idPermit, PermitEntity permitEntity,
			PermtiWeatherEntityResult permtiWeather, Boolean includeBattery,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, Cmodulev2 moduleInfo,
			PermitLayoutEntity permitLayoutEntity, PermitConduitConductorSectionEntity circuit,
			Inverters microInverterInfo, UserSettingEntity userSetting, PlansetUtils plansetUtils,
			AuthentificationEntity userConnectedEntity, List<String> acCircuitEnvironment, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, ACCombinerSLC acCombiner, ACCombinerSLC slcAcCombiner, String necCode,
			PdfReader reader, int sheetIndex, String filePath, AHJNotesModel ahjNotes, String necOrCecNote) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		File fileRe = null;
		Locale.setDefault(new Locale("en", "US"));

		List<VoltageDropTable> voltageDrop = new ArrayList<>();

		try {

			fileRe = new File(
					Constants.rapportPlansetFolderUrl + "PDF-E002-MICRO" + idPermit + "-" + sheetIndex + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-E002-MICRO" + idPermit + "-" + sheetIndex + ".pdf");
			}
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();
			if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
			}
			PdfReader readerOriginNEC = new PdfReader(
					Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-E002-MICRO.pdf");
			PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-E002-MICRO.pdf");
			// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
			getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "E002");
			// A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
					sheetIndex);
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
					sheetIndex);

			// A.B CR-3250 03-30 Logo & Signature Mapping
			logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

			// A.B 07-14-2021 CR-3064
			form.setField(sheetIndex + "-" + "SPECIAL-NOTE-E-002", ahjNotes.getE002Note());

			/// ------------------------- Get Project Weather ---------------------///
			Integer fourPerCentAverageHigh = weatherData.getFourPerCentAverageHigh(permtiWeather);
			Integer twoPerCentAverageHigh = weatherData.getTwoPerCentAverageHigh(permtiWeather);

			// ***************** Shared DC & AC Attributes *******************//
			E002Model params = new E002Model();
			SystemEnvironment systemEnvironment = new SystemEnvironment();

			/// ------------------------- System Paired with Energy/Battery Storage System
			/// ---------------------///
			// A.B 08/16/2022 CR-1030
			if (Boolean.TRUE.equals(includeBattery)) {
				// A.B DC-1 Mapping
				dcCircuitMapping.dcMapping(form, circuit, permitEntity, userConnectedEntity, fourPerCentAverageHigh,
						moduleInfo, sheetIndex, necCode, permitLayoutEntity.getShowConduitRoofAsHeight(), params,
						permitHomeSite.getState());

				// A.B 04-03 CR-2598 : Fix on High Temperatures that are off the NEC table
				Boolean dcCircuitUpdated = checkValue.Equals(params.getOperatingTemperatureHigh(), true)
						&& twoPerCentAverageHigh > 0;

				// A.B 04-03: Executed Only when the operating Temperature is High
				if (checkValue.Equals(params.getOperatingTemperatureHigh(), true) && twoPerCentAverageHigh > 0) {
					params.setOperatingTemperatureHigh(true);
					dcCircuitMapping.dcMapping(form, circuit, permitEntity, userConnectedEntity, twoPerCentAverageHigh,
							moduleInfo, sheetIndex, necCode, permitLayoutEntity.getShowConduitRoofAsHeight(), params,
							permitHomeSite.getState());
				}

				// A.B AC Circuits Mapping
				List<ESSConnectors> acList = essConnectorsRepo.findByIndexGreaterThanAndProjectIdOrderByIndex(1,
						permitEntity.getId());
				if (acList != null && !acList.isEmpty()) {
					int indexAcCombiner = getIndexByProperty(acList);
					int i = 1;
					do {
						acCircuitMapping.acMapping(form, i, acList, plansetUtils, microInverterInfo,
								permitProjectSiteInfo, fourPerCentAverageHigh, acCircuitEnvironment, permitEntity,
								acTradeSize, acNumberOfConductors, userConnectedEntity, permitLayoutEntity, sheetIndex,
								userSetting, permitHomeSite, necCode, params, voltageDrop, idPermit,
								circuit.getMapFromUserInput(), indexAcCombiner);
						i++;
					} while (i <= acList.size() && (checkValue.Equals(params.getOperatingTemperatureHigh(), false)
							|| twoPerCentAverageHigh == 0));
					// A.B 04-03: Executed Only when the operating Temperature is High
					if (checkValue.Equals(params.getOperatingTemperatureHigh(), true) && twoPerCentAverageHigh > 0) {
						acTradeSize.clear();
						acNumberOfConductors.clear();
						acCircuitEnvironment.clear();
						if (checkValue.Equals(dcCircuitUpdated, false)) {
							dcCircuitMapping.dcMapping(form, circuit, permitEntity, userConnectedEntity,
									twoPerCentAverageHigh, moduleInfo, sheetIndex, necCode,
									permitLayoutEntity.getShowConduitRoofAsHeight(), params, permitHomeSite.getState());
						}
						for (i = 1; i <= acList.size(); i++) {
							params.getIncorrectTradeSize().clear();
							acCircuitMapping.acMapping(form, i, acList, plansetUtils, microInverterInfo,
									permitProjectSiteInfo, twoPerCentAverageHigh, acCircuitEnvironment, permitEntity,
									acTradeSize, acNumberOfConductors, userConnectedEntity, permitLayoutEntity,
									sheetIndex, userSetting, permitHomeSite, necCode, params, voltageDrop, idPermit,
									circuit.getMapFromUserInput(), indexAcCombiner);
						}
					}
				}

			} else {
				/// ------------------------- Split DC & AC Circuit ---------------------///
				String acCircuit = generateCircuitList.renameAcComponent(circuit.getComponentOrder());
				Boolean stepACCombiner = acCircuit.contains("AC COMBINER") || acCircuit.contains("SOLAR LOAD CENTER");
				int indexAcCombiner = acCircuit.contains("AC COMBINER") || acCircuit.contains("SOLAR LOAD CENTER")
						? generateCircuitList.getAcCombinerIndex(acCircuit) + 1
						: 0;

				// Map DC 1 Circuit
				dcMicroMapping.getDC1Mapping(form, acCircuit, circuit, permitEntity, userConnectedEntity,
						fourPerCentAverageHigh, moduleInfo, sheetIndex, necCode,
						permitLayoutEntity.getShowConduitRoofAsHeight(), params, permitHomeSite.getState());

				// A.B 04-03 CR-2598 : Fix on High Temperatures that are off the NEC table
				Boolean dcCircuitUpdated = checkValue.Equals(params.getOperatingTemperatureHigh(), true)
						&& twoPerCentAverageHigh > 0;

				// A.B 04-03: Executed Only when the operating Temperature is High
				if (checkValue.Equals(params.getOperatingTemperatureHigh(), true) && twoPerCentAverageHigh > 0) {
					dcMicroMapping.getDC1Mapping(form, acCircuit, circuit, permitEntity, userConnectedEntity,
							twoPerCentAverageHigh, moduleInfo, sheetIndex, necCode,
							permitLayoutEntity.getShowConduitRoofAsHeight(), params, permitHomeSite.getState());
					params.setOperatingTemperatureHigh(true);

				}
				int i = 1;
				do {
					// A.B 04-03: Executed Only when the operating Temperature is not High
					params.getIncorrectTradeSize().clear();
					mapACCircuit(form, acCircuit, i, circuit, plansetUtils, indexAcCombiner, stepACCombiner,
							microInverterInfo, permitProjectSiteInfo, fourPerCentAverageHigh, acCircuitEnvironment,
							permitEntity, acTradeSize, acNumberOfConductors, acCombiner, slcAcCombiner, userConnectedEntity,
							permitLayoutEntity, sheetIndex, userSetting, permitHomeSite, necCode, params, voltageDrop);
					i++;

				} while (i < acCircuit.split("-").length + 1
						&& (checkValue.Equals(params.getOperatingTemperatureHigh(), false)
								|| twoPerCentAverageHigh == 0));

				// A.B 04-03: Executed Only when the operating Temperature is High
				if (checkValue.Equals(params.getOperatingTemperatureHigh(), true) && twoPerCentAverageHigh > 0) {
					acTradeSize.clear();
					acNumberOfConductors.clear();
					acCircuitEnvironment.clear();

					if (checkValue.Equals(dcCircuitUpdated, false)) {
						dcMicroMapping.getDC1Mapping(form, acCircuit, circuit, permitEntity, userConnectedEntity,
								twoPerCentAverageHigh, moduleInfo, sheetIndex, necCode,
								permitLayoutEntity.getShowConduitRoofAsHeight(), params, permitHomeSite.getState());
					}
					for (i = 1; i < acCircuit.split("-").length + 1; i++) {
						params.getIncorrectTradeSize().clear();
						mapACCircuit(form, acCircuit, i, circuit, plansetUtils, indexAcCombiner, stepACCombiner,
								microInverterInfo, permitProjectSiteInfo, twoPerCentAverageHigh, acCircuitEnvironment,
								permitEntity, acTradeSize, acNumberOfConductors, acCombiner, slcAcCombiner, userConnectedEntity,
								permitLayoutEntity, sheetIndex, userSetting, permitHomeSite, necCode, params,
								voltageDrop);
					}
				}
			}

//			S.B 29/09/2020 CR-PM-3365-MOD-001
			form.setField(sheetIndex + "-" + "NEC/CEC-NOTE", necOrCecNote);
			if (voltageDrop != null && !voltageDrop.isEmpty()) {
				voltageDropMapping.mapVoltageDrop(sheetIndex, permitEntity.getId(), voltageDrop);
				voltageDropMapping.insertVoltageDropTable(sheetIndex, permitEntity.getId(), stamper, 640f, 279.6f);
			}
			stamper.close();
			reader.close();
			File filePV = new File(filePath + "Rapport/E002MicroModification" + idPermit + "-" + sheetIndex + ".pdf");
			if (filePV.exists()) {
				filePV.delete();
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

		return fileRe;

	}

	private int getIndexByProperty(List<ESSConnectors> acList) {
		for (int i = 0; i < acList.size(); i++) {
			if (acList.get(i).getSourceID().contains("ACC") || acList.get(i).getSourceID().contains("SLC")) {
				return i + 1;
			}
		}
		return -1;// not ACC in the list list
	}

	private String getACCType(Boolean systemString, String circuitCombining, String catrgory) {
		try {
			if (Boolean.FALSE.equals(systemString) && checkValue.Equals(catrgory, "AC Combiner") && (checkValue
					.Equals(circuitCombining, "A Proposed AC Combiner Panel (Solar Load Center)")
					|| checkValue.Equals(circuitCombining,
							"A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE ARRAY")
					|| checkValue.Equals(circuitCombining,
							"A Proposed AC Combiner Panel (Solar Load Center) MOUNTED AT THE MAIN SERVICE PANEL"))) {
				return "AC COMBINER";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SOLAR LOAD CENTER";
	}
}
