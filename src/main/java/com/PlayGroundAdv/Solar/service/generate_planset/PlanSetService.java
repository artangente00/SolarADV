package com.PlayGroundAdv.Solar.service.generate_planset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.AccountBuildEntity;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ConduitConductorCircuitEntity;
import com.PlayGroundAdv.Solar.entity.ContractorInformationEntity;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Engineers;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.entity.MissingSheetsLogEntity;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitEngineerEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.PlansetUsedSheetsLog;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermitEnergyBatterySystemDto;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.model.PlansetRevisionIndexModel;
import com.PlayGroundAdv.Solar.model.TitleBlockValues;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDesignCriteriaModel;
import com.PlayGroundAdv.Solar.model.ahj_library.GoverningCodesModel;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.ConduitConductorCircuitRepository;
import com.PlayGroundAdv.Solar.repositories.MissingSheetsLogRepository;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdditionalInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdvInputsRepository;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitConduitConductorSectionRepository;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEngineerRepository;
import com.PlayGroundAdv.Solar.repositories.PermitHomeSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitLayoutRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.PermitSketchRepository;
import com.PlayGroundAdv.Solar.repositories.PermitWeatherRepository;
import com.PlayGroundAdv.Solar.repositories.PlansetUsedSheetsLogRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ElectricalUtilityRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.EngineersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.AccountBuildRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.ahj.PlansetNotesService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.p_sheets.PlanSetServiceP001;
import com.PlayGroundAdv.Solar.service.generate_planset.p_sheets.PlanSetServiceP002;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.PlansetNotes;
import com.PlayGroundAdv.Solar.service.generate_planset.pv_sheets.PlanSetServicePV100;
import com.PlayGroundAdv.Solar.service.generate_planset.pv_sheets.PlanSetServicePV100G;
import com.PlayGroundAdv.Solar.service.generate_planset.pv_sheets.PlanSetServicePV101G;
import com.PlayGroundAdv.Solar.service.generate_planset.pv_sheets.PlansetServicePV001;
import com.PlayGroundAdv.Solar.service.generate_planset.rsheets.MergeRsheets;
import com.PlayGroundAdv.Solar.service.generate_planset.s_sheets.PlanSetServiceS100;
import com.PlayGroundAdv.Solar.service.generate_planset.s_sheets.PlanSetServiceS200;
import com.PlayGroundAdv.Solar.service.generate_planset.s_sheets.PlanSetServiceS201;
import com.PlayGroundAdv.Solar.service.generate_planset.s_sheets.PlansetServiceS300;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ACCircuitComponentQty;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.DCCircuitComponentQty;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleQtyOnBranchCicuit;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleQtyOnStringInverter;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001.PlanSetServiceE001;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.E002StringMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.PlansetServiceE002Micro2;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003.PlansetServiceE003Micro;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e003.PlansetServiceE003String;
import com.PlayGroundAdv.Solar.service.generate_planset.title_block.UpdateTitlePlanSetBlock;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import sun.net.www.protocol.file.FileURLConnection;

/*
 * @author Arij
 */

@Service
public class PlanSetService {

	final PlansetServicePV001 plansetServicePV001;
	final PlanSetServicePV100 plansetServicePV100;
	final PlanSetServicePV100G plansetServicePV100G;
	final PlanSetServicePV101G plansetServicePV101G;
	final PlanSetServiceS200 plansetServiceS200;
	final PlanSetServiceS100 plansetServiceS100;
	final PlanSetServiceS201 plansetServiceS201;
	final PlanSetServiceE001 plansetServiceE001;
	final E002StringMappingService e002StringMappingService;
	final PlansetServiceE002Micro2 plansetServiceE002Micro2;
	final PlansetServiceE003String plansetServiceE003String;
	final PlansetServiceE003Micro plansetServiceE0032Micro;
	final PlanSetServiceE100 plansetServiceE100;
	final PlanSetServiceP001 plansetServiceP001;
	final PlanSetServiceP002 plansetServiceP002;
	final PlansetServiceN001 plansetServiceN001;
	final PlansetServiceS300 plansetServiceS300;
	final PermitRepository projectRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepos;
	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final UpdateTitlePlanSetBlock updateTitlePlanSetBlock;
	final ContractorInformationRepository userInfoRepo;
	final UserSettingRepository userSettingRepo;
	final AuthenticationRepository userAuthRepo;
	final PermitHomeSiteInfoRepository homeSiteInfoRepo;
	final InverterRepository inverterRepo;
	final ModuleRepository moduleRepo;
	final DcCombinerDiscoRepository dccRepo;
	final ConvertersRepository convertersRepo;
	final ACDisconnectRepository acdRepo;
	final MissingSheetsLogRepository missingSheetRepo;
	final MailingService mailingService;
	final PermitAdvInputsRepository permitAdvInputsRepo;
	final MergeRsheets mergeRsheets;
	final PlansetUsedSheetsLogRepository plansetUsedSheetRepo;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	final PlansetNotesService ahjPlansetNotesService;
	final PermitLayoutRepository permitLayoutRepo;
	final PermitSketchRepository permitSketchRepo;
	final PermitAdditionalInfoRepository additionalInfoRepo;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	final ModuleQtyOnStringInverter moduleQtyOnStringInverter;
	final ModuleQtyOnBranchCicuit moduleQtyOnBranchCicuit;
	final DCCircuitComponentQty dcCircuitCompQty;
	final ACCircuitComponentQty acCircuitCompQty;
	final PlansetNotes plansetNotes;
	final PathRepository pathRepo;
	final PermitWeatherRepository weatherRepo;
	final PermitArraysRepository arraysRepo;
	final PermitEngineerRepository permitEngineerRepo;
	final EngineersRepository engineerRepo;
	final ElectricalUtilityRepository electricalUtilityRepo;
	final PermitConduitConductorSectionRepository permitConduitConductorSectionRepo;
	final ConduitConductorCircuitRepository conduitConductorCircuitRepo;
	final RoofAttachmentsRepository roofAttachmentsRepo;
	final FlashingRepository flashingRepo;
	final LeasePPAMeterRepository leasePPAMeterRepo;
	final AccountBuildRepository accountBuildRepo;
	final TechnicalProblemMsg technicalProblemMsg;

	public PlanSetService(PlansetServicePV001 plansetServicePV001, PlanSetServicePV100 plansetServicePV100,
			PlanSetServicePV100G plansetServicePV100G, PlanSetServicePV101G plansetServicePV101G,
			PlanSetServiceS200 plansetServiceS200, PlanSetServiceS100 plansetServiceS100,
			PlanSetServiceS201 plansetServiceS201, PlanSetServiceE001 plansetServiceE001,
			E002StringMappingService e002StringMappingService, PlansetServiceE002Micro2 plansetServiceE002Micro2,
			PlansetServiceE003String plansetServiceE003String, PlansetServiceE003Micro plansetServiceE0032Micro,
			PlanSetServiceE100 plansetServiceE100, PlanSetServiceP001 plansetServiceP001,
			PlanSetServiceP002 plansetServiceP002, PlansetServiceN001 plansetServiceN001,
			PlansetServiceS300 plansetServiceS300, PermitRepository projectRepo,
			PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepos, CheckValueTypesService checkValue,
			GetPDFReaderService getPDFReaderService, UpdateTitlePlanSetBlock updateTitlePlanSetBlock,
			ContractorInformationRepository userInfoRepo, UserSettingRepository userSettingRepo,
			AuthenticationRepository userAuthRepo, PermitHomeSiteInfoRepository homeSiteInfoRepo,
			InverterRepository inverterRepo, ModuleRepository moduleRepo, DcCombinerDiscoRepository dccRepo,
			ConvertersRepository convertersRepo, ACDisconnectRepository acdRepo,
			MissingSheetsLogRepository missingSheetRepo, MailingService mailingService,
			PermitAdvInputsRepository permitAdvInputsRepo, MergeRsheets mergeRsheets,
			PlansetUsedSheetsLogRepository plansetUsedSheetRepo, RoofMaterialTypeRepository roofMaterialTypeRepo,
			PermitDrafterDataRepository permitDrafterDataRepo, PlansetNotesService ahjPlansetNotesService,
			PermitLayoutRepository permitLayoutRepo, PermitSketchRepository permitSketchRepo,
			PermitAdditionalInfoRepository additionalInfoRepo,
			PermitEnergyBatterySystemRepository energyBatterySystemRepo,
			ModuleQtyOnStringInverter moduleQtyOnStringInverter, ModuleQtyOnBranchCicuit moduleQtyOnBranchCicuit,
			DCCircuitComponentQty dcCircuitCompQty, ACCircuitComponentQty acCircuitCompQty, PlansetNotes plansetNotes,
			PathRepository pathRepo, PermitWeatherRepository weatherRepo, PermitArraysRepository arraysRepo,
			PermitEngineerRepository permitEngineerRepo, EngineersRepository engineerRepo,
			ElectricalUtilityRepository electricalUtilityRepo,
			PermitConduitConductorSectionRepository permitConduitConductorSectionRepo,
			ConduitConductorCircuitRepository conduitConductorCircuitRepo,
			RoofAttachmentsRepository roofAttachmentsRepo, FlashingRepository flashingRepo,
			LeasePPAMeterRepository leasePPAMeterRepo, AccountBuildRepository accountBuildRepo,
			TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.plansetServicePV001 = plansetServicePV001;
		this.plansetServicePV100 = plansetServicePV100;
		this.plansetServicePV100G = plansetServicePV100G;
		this.plansetServicePV101G = plansetServicePV101G;
		this.plansetServiceS200 = plansetServiceS200;
		this.plansetServiceS100 = plansetServiceS100;
		this.plansetServiceS201 = plansetServiceS201;
		this.plansetServiceE001 = plansetServiceE001;
		this.e002StringMappingService = e002StringMappingService;
		this.plansetServiceE002Micro2 = plansetServiceE002Micro2;
		this.plansetServiceE003String = plansetServiceE003String;
		this.plansetServiceE0032Micro = plansetServiceE0032Micro;
		this.plansetServiceE100 = plansetServiceE100;
		this.plansetServiceP001 = plansetServiceP001;
		this.plansetServiceP002 = plansetServiceP002;
		this.plansetServiceN001 = plansetServiceN001;
		this.plansetServiceS300 = plansetServiceS300;
		this.projectRepo = projectRepo;
		this.permitProjectSiteInfoEntityRepos = permitProjectSiteInfoEntityRepos;
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.updateTitlePlanSetBlock = updateTitlePlanSetBlock;
		this.userInfoRepo = userInfoRepo;
		this.userSettingRepo = userSettingRepo;
		this.userAuthRepo = userAuthRepo;
		this.homeSiteInfoRepo = homeSiteInfoRepo;
		this.inverterRepo = inverterRepo;
		this.moduleRepo = moduleRepo;
		this.dccRepo = dccRepo;
		this.convertersRepo = convertersRepo;
		this.acdRepo = acdRepo;
		this.missingSheetRepo = missingSheetRepo;
		this.mailingService = mailingService;
		this.permitAdvInputsRepo = permitAdvInputsRepo;
		this.mergeRsheets = mergeRsheets;
		this.plansetUsedSheetRepo = plansetUsedSheetRepo;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
		this.ahjPlansetNotesService = ahjPlansetNotesService;
		this.permitLayoutRepo = permitLayoutRepo;
		this.permitSketchRepo = permitSketchRepo;
		this.additionalInfoRepo = additionalInfoRepo;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
		this.moduleQtyOnStringInverter = moduleQtyOnStringInverter;
		this.moduleQtyOnBranchCicuit = moduleQtyOnBranchCicuit;
		this.dcCircuitCompQty = dcCircuitCompQty;
		this.acCircuitCompQty = acCircuitCompQty;
		this.plansetNotes = plansetNotes;
		this.pathRepo = pathRepo;
		this.weatherRepo = weatherRepo;
		this.arraysRepo = arraysRepo;
		this.permitEngineerRepo = permitEngineerRepo;
		this.engineerRepo = engineerRepo;
		this.electricalUtilityRepo = electricalUtilityRepo;
		this.permitConduitConductorSectionRepo = permitConduitConductorSectionRepo;
		this.conduitConductorCircuitRepo = conduitConductorCircuitRepo;
		this.roofAttachmentsRepo = roofAttachmentsRepo;
		this.flashingRepo = flashingRepo;
		this.leasePPAMeterRepo = leasePPAMeterRepo;
		this.accountBuildRepo = accountBuildRepo;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	public String getfilesPath() {
		return pathRepo.findFilePath();
	}

	public String getPermitSystemSize(Cmodulev2 moduleInfo, Integer totlaModule) {
		try {
			Locale locale = Locale.ENGLISH;
			NumberFormat nf = NumberFormat.getNumberInstance(locale);
			// A.B for trailing zeros:
			nf.setMinimumFractionDigits(3);
			// A.B round to 3 digits:
			nf.setMaximumFractionDigits(3);
			if (moduleInfo != null && moduleInfo.getStcRounded() != null) {
				float permitSusyemSizeInt = (Float.parseFloat(moduleInfo.getStcRounded()) * totlaModule) / 1000;
				String permitSusyemSize = String.valueOf(nf.format(permitSusyemSizeInt));
				if (permitSusyemSize.contains(",")) {
					return permitSusyemSize.replace(",", ".");
				}
				return permitSusyemSize;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return "";
	}

	public String generateFile(Long idUser, Long idPermit) throws IOException, ResourceNotFoundException {

		Long submitId = Long.valueOf(
				Long.toString(new Date().getTime()).substring(Long.toString(new Date().getTime()).length() - 4));

		PermitEntity permitEntity = projectRepo.findById(idPermit)
				.orElseThrow(() -> new ResourceNotFoundException("No Entity Found"));

		AuthentificationEntity userConnectedEntity = userAuthRepo.findById(idUser)
				.orElseThrow(() -> new ResourceNotFoundException("No User Found"));

//		Get Project Owner Setting
		ContractorInformationEntity userContactInfo = userInfoRepo
				.findByAuthentificationEntityId(permitEntity.getAuthentificationEntity().getId());
		UserSettingEntity userSetting = userSettingRepo
				.findByUserIdId(permitEntity.getAuthentificationEntity().getId());

//		Get WeatherPermitInfo
		PermtiWeatherEntityResult permtiWeather = weatherRepo.getPermtiWeatherEntityResult(idPermit);

//		Get homeSiteInfo
		PermitHomeSiteInfoEntity permitHomeSite = homeSiteInfoRepo.findByPermitEntityId(idPermit);

//		Get PermitArraysEntity
		PermitArrayEntityResultSecond permitArraysEntityResult = arraysRepo.getPermitArrayEntityResultSecond(idPermit);

//		Get PermitAdditionalInfoEntity
		PermitAdditionalInfoEntityResult permitAdditionalInfo = additionalInfoRepo
				.getPermitAdditionalInfoEntityResult(idPermit);

//		Get PermitProjectSiteInfoEntityResult
		PermitProjectSiteInfoEntity permitProjectSiteInfo = permitProjectSiteInfoEntityRepos
				.findByPermitEntityId(idPermit);

//		Get PermitProjectSiteInfoEntityResult
		PermitAdvEntityResult advInputsInfo = permitAdvInputsRepo.getProjectADVInputs(idPermit);

//		Get PermitEnergyBatterySystem
		PermitEnergyBatterySystem permitEnergyBatterySystem = energyBatterySystemRepo.findByProjectId(idPermit);

//		Get projectEngineer
		PermitEngineerEntity projectEngineer = permitEngineerRepo.findByPermitEntityId(idPermit);

//		Get Roof Material
		RoofMaterialType roofMaterialType = new RoofMaterialType();
		if (permitProjectSiteInfo != null && checkValue.isLongPositive(permitProjectSiteInfo.getRoofMaterialType())) {
			roofMaterialType = roofMaterialTypeRepo.findById(permitProjectSiteInfo.getRoofMaterialType()).orElse(null);
		}

//		Get engineer
		Engineers engineer = null;
		if (projectEngineer != null && checkValue.isNumericNotZero(projectEngineer.getEngineeredBy())) {
			engineer = engineerRepo.findById(Long.valueOf(projectEngineer.getEngineeredBy())).orElse(null);
		}

//		Get ElectricalUtilityEntity
		ElectricalUtilityEntity electricalCompany = null;
		if (permitHomeSite.getUtilityCompanyName() != null
				&& checkValue.isNumericNotZero(permitHomeSite.getUtilityCompanyName())) {
			electricalCompany = electricalUtilityRepo.findById(Long.parseLong(permitHomeSite.getUtilityCompanyName()))
					.orElse(null);
		}

		// Get moduleInfo
		Cmodulev2 moduleInfo = moduleRepo.findById(permitArraysEntityResult.getPvModuleModEl()).orElse(null);

		// Get inverters Info
		// A.B 01-14 Get Inverter Entities
		Inverters inverterInfo;
		Inverters secondInverterInfo;
		Inverters thirdInverterInfo;
		Inverters fourthInverterInfo;
		Inverters fifthInverterInfo;

		if (checkValue.NotEquals(permitArraysEntityResult.getInverterModel(), "")) {
			inverterInfo = inverterRepo.findById(permitArraysEntityResult.getInverterModel()).orElse(null);
		} else
			inverterInfo = null;

		if (checkValue.NotEquals(permitArraysEntityResult.getSecondInverterModel(), "")) {
			secondInverterInfo = inverterRepo.findById(permitArraysEntityResult.getSecondInverterModel()).orElse(null);
		} else
			secondInverterInfo = null;

		if (checkValue.NotEquals(permitArraysEntityResult.getThirdInverterModel(), "")) {
			thirdInverterInfo = inverterRepo.findById(permitArraysEntityResult.getThirdInverterModel()).orElse(null);
		} else
			thirdInverterInfo = null;

		if (checkValue.NotEquals(permitArraysEntityResult.getFourthInverterModel(), "")) {
			fourthInverterInfo = inverterRepo.findById(permitArraysEntityResult.getFourthInverterModel()).orElse(null);
		} else
			fourthInverterInfo = null;

		if (checkValue.NotEquals(permitArraysEntityResult.getFifthInverterModel(), "")) {
			fifthInverterInfo = inverterRepo.findById(permitArraysEntityResult.getFifthInverterModel()).orElse(null);
		} else
			fifthInverterInfo = null;

		List<Inverters> inverters = new ArrayList<>();
		Integer firsttInverterQty = 1;
		Integer secondtInverterQty = 0;
		inverters.add(inverterInfo);
		if (secondInverterInfo != null) {

			if (inverterInfo != null && secondInverterInfo.getId().equals(inverterInfo.getId())) {
				firsttInverterQty++;
			} else {
				inverters.add(secondInverterInfo);
				secondtInverterQty++;
			}
			if (thirdInverterInfo != null) {

				if (inverters.size() == 1) {
					if (thirdInverterInfo.getId().equals(inverterInfo.getId())) {
						firsttInverterQty++;
					} else {
						inverters.add(thirdInverterInfo);
						secondtInverterQty++;

					}

				} else if (inverters.size() == 2) {

					if (thirdInverterInfo.getId().equals(inverterInfo.getId())) {
						firsttInverterQty++;
					} else if (thirdInverterInfo.getId().equals(secondInverterInfo.getId())) {
						secondtInverterQty++;
					} else {
						inverters.add(thirdInverterInfo);
					}
				}

				if (fourthInverterInfo != null) {

					if (inverters.size() == 1) {
						if (fourthInverterInfo.getId().equals(inverterInfo.getId())) {
							firsttInverterQty++;
						} else {
							inverters.add(fourthInverterInfo);
							secondtInverterQty++;
						}

					} else if (inverters.size() == 2) {

						if (fourthInverterInfo.getId().equals(inverterInfo.getId())) {
							firsttInverterQty++;
						} else if (fourthInverterInfo.getId().equals(inverters.get(1).getId())) {
							secondtInverterQty++;
						} else {
							inverters.add(fourthInverterInfo);
						}
					} else {
						inverters.add(fourthInverterInfo);
					}

					if (fifthInverterInfo != null) {

						if (inverters.size() == 1) {
							if (fifthInverterInfo.getId().equals(inverterInfo.getId())) {
								firsttInverterQty++;
							} else {
								inverters.add(fifthInverterInfo);
								secondtInverterQty++;
							}

						} else if (inverters.size() == 2) {

							if (fifthInverterInfo.getId().equals(inverterInfo.getId())) {
								firsttInverterQty++;
							} else if (fifthInverterInfo.getId().equals(inverters.get(1).getId())) {
								secondtInverterQty++;
							} else {
								inverters.add(fifthInverterInfo);
							}
						} else {
							inverters.add(fifthInverterInfo);
						}

					}

				}

			}

		}

//		Get Permit Conduit Conductor Section
		PermitConduitConductorSectionEntity circuit = permitConduitConductorSectionRepo.findByPermitEntityId(idPermit);

//		Get Conduit Conductor Section
		ConduitConductorCircuitEntity circuitConductor = conduitConductorCircuitRepo.findByPermitEntityId(idPermit);

		////// hashmap Entities ////////////////
		HashMap<String, String> circuitMap = new HashMap<String, String>();
		circuitMap.put("permitHomeSiteEntityResult", "public.permit_home_site_info_entity");
		circuitMap.put("permitEntity", "public.permit_entity");
		circuitMap.put("permitArraysEntity", "public.permit_arrays_entity");
		circuitMap.put("permitProjectSiteInfoEntityTwo", "public.permit_project_site_info_entity");
		circuitMap.put("permitAdditionalInfoEntity", "public.permit_additional_info_entity");
		circuitMap.put("permtiWeatherEntityResult", "public.permit_weather_entity");

		// Get Permit Layout
		PermitLayoutEntity permitLayoutEntity = permitLayoutRepo.findByPermitEntityId(idPermit);

		// Get Permit Sketch Entity List
		List<PermitSketchEntity> listPermitSketchEntity = permitSketchRepo
				.findByPermitEntityIdOrderByArraySketch(idPermit);

		// Get inverterInfo
		Inverters microInverterInfo = null;
		if (permitArraysEntityResult != null
				&& checkValue.isLongPositive(permitArraysEntityResult.getMicroInverter())) {
			microInverterInfo = inverterRepo.findById(permitArraysEntityResult.getMicroInverter()).orElse(null);
		}

		// Get Drafter Data
		PermitDrafterDataEntity permitDrafterDatanfo = permitDrafterDataRepo.findByPermitEntityId(idPermit);

		//////////////// Get Dc Combiner Disconnect #1///////////
		DCCombinerDisconnectEntity dcCombinerDisconnect = null;
		if (permitProjectSiteInfo != null
				&& checkValue.Equals(permitProjectSiteInfo.getUsedByInverterManufacturer(), true)
				&& permitProjectSiteInfo.getDisconnectModel() != null) {

			dcCombinerDisconnect = permitProjectSiteInfo.getDisconnectModel();
		} else if (permitProjectSiteInfo != null && permitProjectSiteInfo.getRoofTopDCCombiner() != null
				&& checkValue.Equals(permitProjectSiteInfo.getTransitioningPVWireIn(), "Rooftop DC Combiner")) {
			dcCombinerDisconnect = permitProjectSiteInfo.getRoofTopDCCombiner();
		}

		//////////////// Get Dc Combiner Disconnect #2///////////
		DCCombinerDisconnectEntity seconddcCombinerDisconnect = null;
		if (permitProjectSiteInfo != null
				&& checkValue.Equals(permitProjectSiteInfo.getUsedByInverterManufacturer(), true)
				&& permitProjectSiteInfo.getDisconnectModelTwo() != null) {
			seconddcCombinerDisconnect = permitProjectSiteInfo.getDisconnectModelTwo();
		}

		//////////////// Get Dc Combiner Disconnect #3///////////
		DCCombinerDisconnectEntity thirddcCombinerDisconnect = null;
		if (permitProjectSiteInfo != null
				&& checkValue.Equals(permitProjectSiteInfo.getUsedByInverterManufacturer(), true)
				&& permitProjectSiteInfo.getDisconnectModelThree() != null) {
			thirddcCombinerDisconnect = permitProjectSiteInfo.getDisconnectModelThree();
		}

		////// Get Roof top DC Disconnect ////
		DCCombinerDisconnectEntity roofTopDCDisconnect = null;
		if (permitProjectSiteInfo != null && permitProjectSiteInfo.getRoofTopDCDisco() != null
				&& checkValue.Equals(permitProjectSiteInfo.getTransitioningPVWireIn(), "Rooftop DC Disconnect(s)")) {
			roofTopDCDisconnect = permitProjectSiteInfo.getRoofTopDCDisco();
		}

		//////////////// Get AC Disconnect #1///////////
		ACDisconnect acDisconnect = null;
		ACDisconnect additionalAcDisconnect = null;
		if (checkValue.Equals(permitProjectSiteInfo.getMicroInverterCabling(), "Rooftop AC Disconnect(s)")) {

			if (permitProjectSiteInfo.getRoofTopACDisco() != null
					&& permitProjectSiteInfo.getRoofTopACDisco().contains(":")) {
				try {
					acDisconnect = acdRepo
							.findById(Long.valueOf(permitProjectSiteInfo.getRoofTopACDisco().split(":")[0]))
							.orElse(null);
				} catch (Exception e) {
					e.printStackTrace();
					technicalProblemMsg.traiterException(e);
				}
			}
			if (permitProjectSiteInfo != null && checkValue.Equals(permitProjectSiteInfo.getUseDisconectSwith(), "Yes")
					&& checkValue.contains(permitProjectSiteInfo.getRooftopACCombinerModel(), ":")) {
				additionalAcDisconnect = acdRepo
						.findById(Long.valueOf(permitProjectSiteInfo.getRooftopACCombinerModel().split(":")[0]))
						.orElse(null);
			}
		} else if (permitProjectSiteInfo != null && checkValue.Equals(permitProjectSiteInfo.getUseDisconectSwith(), "Yes")
				&& checkValue.contains(permitProjectSiteInfo.getRooftopACCombinerModel(), ":")) {
			acDisconnect = acdRepo
					.findById(Long.valueOf(permitProjectSiteInfo.getRooftopACCombinerModel().split(":")[0]))
					.orElse(null);
		}

		//////////////// Get AC Disconnect #2///////////
		ACDisconnect secondacDisconnect = null;
		if (permitProjectSiteInfo != null && checkValue.Equals(permitProjectSiteInfo.getUseDisconectSwith(), "Yes")
				&& checkValue.NotEquals(permitProjectSiteInfo.getRooftopACCombinerModelTwo(), "")
				&& permitProjectSiteInfo.getRooftopACCombinerModelTwo().contains(":")) {
			secondacDisconnect = acdRepo
					.findById(Long.valueOf(permitProjectSiteInfo.getRooftopACCombinerModelTwo().split(":")[0]))
					.orElse(null);
		}

		//////////////// Get AC Disconnect #3///////////
		ACDisconnect acDisconnectThree = null;
		if (permitProjectSiteInfo != null && checkValue.Equals(permitProjectSiteInfo.getUseDisconectSwith(), "Yes")) {
			acDisconnectThree = permitProjectSiteInfo.getAcDisconnectThree();
		}
		//////////////// Get AC Disconnect #4///////////
		ACDisconnect acDisconnectFour = null;
		if (permitProjectSiteInfo != null && checkValue.Equals(permitProjectSiteInfo.getUseDisconectSwith(), "Yes")) {
			acDisconnectFour = permitProjectSiteInfo.getAcDisconnectFour();
		}

		ACCombinerSLC acCombiner = null;
		if (permitProjectSiteInfo != null && checkValue.NotEquals(permitProjectSiteInfo.getMicroInverterCabling(), "")
				&& checkValue.Equals(permitProjectSiteInfo.getMicroInverterCabling(), "Rooftop AC Combiner")) {
			acCombiner = permitProjectSiteInfo.getRoofTopACCombiner();
		} else if (permitProjectSiteInfo != null
				&& checkValue.Equals(permitProjectSiteInfo.getInstallingACCombiner(), true)) {
			acCombiner = permitProjectSiteInfo.getACCombinerInstalled();
		}
		////////////// AC Combiner SLC//////
		ACCombinerSLC slcAcCombiner = null;
		if (checkValue.contains(permitProjectSiteInfo.getCombiningACCircuits(),
				"A Proposed AC Combiner Panel")) {
			slcAcCombiner = permitProjectSiteInfo.getGroundLevelACCombinerBoxModel();
		}

		//////////////// DC Optimizer ///////////
		DCOptimizerEntity dcOptimizer = null;
		if (permitArraysEntityResult != null
				&& checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer")
				&& permitArraysEntityResult.getSystemOptimizerModel() != null
				&& checkValue.isLongPositive(permitArraysEntityResult.getSystemOptimizerModel())) {
			dcOptimizer = convertersRepo.findById(permitArraysEntityResult.getSystemOptimizerModel()).orElse(null);
		}

		////// ------------ Rail To Roof Connection ----------------////
		RoofAttachmentsEntity stanchionFoot = null;
		if (permitProjectSiteInfo != null
				&& checkValue.isNumericNotZero(permitProjectSiteInfo.getRailConnectionModel())) {
			stanchionFoot = roofAttachmentsRepo.findById(Long.valueOf(permitProjectSiteInfo.getRailConnectionModel()))
					.orElse(null);
		}

		////// ------------ Rail Racking ----------------////
		RailRacking railRacking = null;
		if (Boolean.TRUE.equals(permitArraysEntityResult.getRoofMounted())) {
			railRacking = permitProjectSiteInfo.getRailRakingModel();
		}

		////// ------------Ground Rail Racking ----------------////
		RailRacking railRackingGround = null;
		if (Boolean.TRUE.equals(permitArraysEntityResult.getGroundMounted())) {
			railRackingGround = permitProjectSiteInfo.getRailRakingModelforGroundMounted();
		}

		////// ------------Patio Rail Racking ----------------////
		RailRacking railRackingPatio = null;
		if (Boolean.TRUE.equals(permitArraysEntityResult.getPatioMounted())) {
			railRackingPatio = permitProjectSiteInfo.getRailRakingforPatioMounted();
		}

		////// ------------Carport Rail Racking ----------------////
		RailRacking railRackingCarport = null;
		if (Boolean.TRUE.equals(permitArraysEntityResult.getCarportMounted())) {
			railRackingCarport = permitProjectSiteInfo.getRailRakingforCarport();
		}

		////// ------------ Flashing ----------------////
		Flashing flashing = null;
		if (permitProjectSiteInfo != null && checkValue.NotEquals(permitProjectSiteInfo.getFlashing(), "")) {
			flashing = flashingRepo.findById(Long.valueOf(permitProjectSiteInfo.getFlashing())).orElse(null);
		}
		////// ------------ Revenue or Performance Monitoring Meter ----------------////
		LeasePPAMeter leasePPAMeter = null;
		if (permitProjectSiteInfo != null && checkValue.isNumericNotZero(permitProjectSiteInfo.getLeasePPAMeter())) {
			leasePPAMeter = leasePPAMeterRepo.findById(Long.valueOf(permitProjectSiteInfo.getLeasePPAMeter()))
					.orElse(null);
		}

		//////////////// Get rapid Shutdown Model///////////
		DCCombinerDisconnectEntity rapidShutdownModel = null;
		if (permitProjectSiteInfo != null && checkValue.NotEquals(permitProjectSiteInfo.getTransitioningPVWireIn(), "")
				&& checkValue.Equals(permitProjectSiteInfo.getTransitioningPVWireIn(), "Rooftop DC Disconnect(s)")
				&& permitProjectSiteInfo.getRoofTopDCDisco() != null) {
			rapidShutdownModel = permitProjectSiteInfo.getRoofTopDCDisco();

		} else if (permitProjectSiteInfo != null
				&& checkValue.NotEquals(permitProjectSiteInfo.getTransitioningPVWireIn(), "")
				&& checkValue.Equals(permitProjectSiteInfo.getTransitioningPVWireIn(), "Rooftop DC Combiner")
				&& permitProjectSiteInfo.getRoofTopDCCombiner() != null) {
			rapidShutdownModel = permitProjectSiteInfo.getRoofTopDCCombiner();

		} else if (permitProjectSiteInfo != null
				&& checkValue.Equals(permitProjectSiteInfo.getUsedByInverterManufacturer(), true)
				&& permitProjectSiteInfo.getDisconnectModel() != null) {
			rapidShutdownModel = permitProjectSiteInfo.getDisconnectModel();

		}

		////// ------------ J-Box ----------------////
		DCCombinerDisconnectEntity jBox = null;
		if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")
				|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "AC Modules")) {
			if (permitProjectSiteInfo != null
					&& checkValue.Equals(permitProjectSiteInfo.getMicroInverterCabling(), "Rooftop Junction box(es)")
					&& checkValue.isNumericNotZero(permitProjectSiteInfo.getRoofTopJbox())) {
				try {
					jBox = dccRepo.findById(Long.valueOf(permitProjectSiteInfo.getRoofTopJbox())).orElse(null);
				} catch (Exception e) {
					e.printStackTrace();
					technicalProblemMsg.traiterException(e);
				}
			}

		} else if (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Neither")
				|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "System Optimizer")) {
			if (permitProjectSiteInfo != null
					&& checkValue.NotEquals(permitProjectSiteInfo.getTransitioningPVWireIn(), "")
					&& checkValue.Equals(permitProjectSiteInfo.getTransitioningPVWireIn(), "Rooftop Junction box(es)")
					&& checkValue.isNumericNotZero(permitProjectSiteInfo.getRoofTopJboxDC())) {
				try {
					jBox = dccRepo.findById(Long.valueOf(permitProjectSiteInfo.getRoofTopJboxDC())).orElse(null);
				} catch (Exception e) {
					e.printStackTrace();
					technicalProblemMsg.traiterException(e);
				}
			}

		}

		String inverterTechnology = (checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
				"Micro Inverter") || checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "AC Modules"))
						? "MICRO"
						: "STRING";

		PlansetUtils plansetUtils = checkValue.Equals(inverterTechnology, "MICRO")
				? moduleQtyOnBranchCicuit.getModuleQty(permitArraysEntityResult, microInverterInfo)
				: moduleQtyOnStringInverter.getModuleQty(permitArraysEntityResult);
		dcCircuitCompQty.getComponentQty(circuit, dcCombinerDisconnect, roofTopDCDisconnect, dcOptimizer,
				permitArraysEntityResult, permitProjectSiteInfo, plansetUtils);
		acCircuitCompQty.getComponentListQty(circuit, acDisconnect, secondacDisconnect, slcAcCombiner, plansetUtils,
				permitArraysEntityResult.getDeviceToIncorporate(), inverterInfo);

		////// ------------ permit Susyem Size ----------------////
		String permitSusyemSize = getPermitSystemSize(moduleInfo, plansetUtils.getModuleQty());

		/**
		 * appel function
		 * 
		 */
		File fileReEmpty = new File(Constants.rapportPlansetFolderUrl + "Empty.pdf");
		PDFMergerUtility ut = new PDFMergerUtility();

		try {

			ut.setDestinationFileName(getfilesPath() + "Rapport/PlansetPDF.pdf");
			ut.addSource(fileReEmpty);
		} catch (FileNotFoundException e2) {

			e2.printStackTrace();
			technicalProblemMsg.traiterException(e2);
		}
		List<String> dcCircuitEnvironment = new ArrayList<String>();
		List<String> acCircuitEnvironment = new ArrayList<String>();
		List<String> dcTradeSize = new ArrayList<String>();
		List<String> acTradeSize = new ArrayList<String>();
		List<Integer> dcNumberOfConductors = new ArrayList<Integer>();
		List<Integer> acNumberOfConductors = new ArrayList<Integer>();
		File fileAccountBuild = null;
		List<AccountBuildEntity> accountBuildSheets = accountBuildRepo
				.findByAccountAndIsDeleted(permitEntity.getAuthentificationEntity().getId(), false);
		if (!accountBuildSheets.isEmpty()) {
			for (AccountBuildEntity accountBuildS : accountBuildSheets) {
				fileAccountBuild = new File(
						Constants.rapportPlansetFolderUrl + "AccountBuildSheets/" + accountBuildS.getId() + ".pdf");
				ut.addSource(fileAccountBuild);
			}
		}

		// 08-28-2019: M.A: CR-2879: Get the qty of AC Combiner from C&C
		Integer qtyAcCombiner = 0;
		if (circuit != null && circuit.getQtySegmentEight() != null) {
			qtyAcCombiner = circuit.getQtySegmentEight();
		}

		TitleBlockValues titleBlockValues = new TitleBlockValues();
		titleBlockValues.setOwnerId(permitEntity.getAuthentificationEntity().getId());
		// A.B: CR-2620 Planset Mapping Update
		Boolean stateRev = permitHomeSite != null
				? getPDFReaderService.checkStateResevation(permitHomeSite.getState(), permitEntity)
				: false;
		Boolean inverterTechnologyRev = permitArraysEntityResult != null
				? getPDFReaderService.checkInvTechnologyResevation(permitArraysEntityResult.getDeviceToIncorporate(),
						permitEntity)
				: false;
		String pdfPath = checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC-PDF/" : "";
		String rafterOrTruss = (checkValue.Equals(permitHomeSite.getRoofRafter(), "Pre-Eng Roof Trusses")
				|| checkValue.Equals(permitHomeSite.getRoofRafter(), "OtherRoof")) ? "TRUSS" : "RAFTER";

		Boolean multipleCustomize = false;
		List<Long> listPDFs = new ArrayList<Long>();
		List<PlansetRevisionIndexModel> listPDFIndex = new ArrayList<PlansetRevisionIndexModel>();

		// A.B 04/06/2021 Get Data from New AHJ Library
		Long ahjId = ahjPlansetNotesService.getAHJId(permitHomeSite.getState(), permitHomeSite.getCity(),
				permitHomeSite.getCityOther(), permitHomeSite.getProjectJurisdiction(),
				permitHomeSite.getProjectJurisOther());
		GoverningCodesModel governingCodes = new GoverningCodesModel();
		AHJNotesModel ahjNotes = new AHJNotesModel();
		AHJDesignCriteriaModel designCriteria = new AHJDesignCriteriaModel();
		if (ahjId != null) {
			governingCodes = ahjPlansetNotesService.getGoverningCodes(ahjId);
			ahjNotes = ahjPlansetNotesService.getAhjPlansetNotes(ahjId);
			designCriteria = ahjPlansetNotesService.getDesignCriteria(ahjId);
		}

//		S.B 29/09/2020 CR-PM-3365-MOD-001
		String necOrCecNote = plansetNotes.necOrCecNoteMapping(governingCodes, permitHomeSite.getState());

		String electNote = plansetNotes.electricalNote(governingCodes, permitHomeSite.getState(),
				permitProjectSiteInfo.getSolarLocation(), userSetting);

		if (checkValue.Equals(stateRev, true)) {
			// A.B: Planset Has Revision & Verified based on the project state
			try {
				// A.B: PV-001 Mapping
				listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "PV001");

				if (listPDFIndex != null && !listPDFIndex.isEmpty()) {

					multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
					for (int i = 0; i < listPDFIndex.size(); i++) {

						try {
							PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
									listPDFIndex.get(i).getPageNumber());
							File filePV001 = plansetServicePV001.buildingPDFPV001(permitHomeSite, permitAdditionalInfo,
									permitArraysEntityResult, userContactInfo, permitEntity, permitSusyemSize,
									projectEngineer, engineer, electricalCompany, advInputsInfo, inverterInfo,
									moduleInfo, microInverterInfo, stanchionFoot, permitDrafterDatanfo, railRacking,
									railRackingGround, railRackingPatio, railRackingCarport, rapidShutdownModel,
									roofMaterialType, reader, listPDFIndex.get(i).getPageIndex(), getfilesPath(),
									titleBlockValues, plansetUtils, listPermitSketchEntity, permitProjectSiteInfo,
									qtyAcCombiner, governingCodes, ahjNotes, dcOptimizer, secondInverterInfo,
									thirdInverterInfo, fourthInverterInfo, fifthInverterInfo, designCriteria, inverters,
									flashing, dcCombinerDisconnect, seconddcCombinerDisconnect,
									thirddcCombinerDisconnect, roofTopDCDisconnect, jBox, acDisconnect,
									secondacDisconnect, acDisconnectThree, acDisconnectFour, additionalAcDisconnect,
									slcAcCombiner, acCombiner, leasePPAMeter, false);
							ut.addSource(filePV001);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				} else {

					try {
						PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV001.pdf");
						File filePV001 = plansetServicePV001.buildingPDFPV001(permitHomeSite, permitAdditionalInfo,
								permitArraysEntityResult, userContactInfo, permitEntity, permitSusyemSize,
								projectEngineer, engineer, electricalCompany, advInputsInfo, inverterInfo, moduleInfo,
								microInverterInfo, stanchionFoot, permitDrafterDatanfo, railRacking, railRackingGround,
								railRackingPatio, railRackingCarport, rapidShutdownModel, roofMaterialType, reader, 0,
								getfilesPath(), titleBlockValues, plansetUtils, listPermitSketchEntity,
								permitProjectSiteInfo, qtyAcCombiner, governingCodes, ahjNotes, dcOptimizer,
								secondInverterInfo, thirdInverterInfo, fourthInverterInfo, fifthInverterInfo,
								designCriteria, inverters, flashing, dcCombinerDisconnect, seconddcCombinerDisconnect,
								thirddcCombinerDisconnect, roofTopDCDisconnect, jBox, acDisconnect, secondacDisconnect,
								acDisconnectThree, acDisconnectFour, additionalAcDisconnect, slcAcCombiner, acCombiner,
								leasePPAMeter, false);
						ut.addSource(filePV001);
					} catch (Exception e) {
						technicalProblemMsg.traiterException(e);
					}
				}

				// A.B: N-001 Mapping
				listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "N001");
				if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
					for (int i = 0; i < listPDFIndex.size(); i++) {

						try {
							PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
									listPDFIndex.get(i).getPageNumber());
							File fileN001 = plansetServiceN001.buildingPDFN001(permitHomeSite, permitEntity, reader,
									listPDFIndex.get(i).getPageIndex(), getfilesPath());
							ut.addSource(fileN001);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				} else {
					if (checkValue.Equals(pdfPath, "NEC-PDF/")) {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-N001.pdf");
							File fileN001 = plansetServiceN001.buildingPDFN001(permitHomeSite, permitEntity, reader, 0,
									getfilesPath());
							ut.addSource(fileN001);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}

				}

				
				// A.B: PV-100R Mapping
				// F.M CR-PM-3787 2021-16-09
				if (permitArraysEntityResult != null && (Boolean.TRUE.equals(permitArraysEntityResult.getRoofMounted())
						|| ((Boolean.TRUE.equals(permitArraysEntityResult.getCarportMounted())
								|| Boolean.TRUE.equals(permitArraysEntityResult.getPatioMounted()))
								&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
										"The carport/patio cover will include roofing material under the modules")))) {
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "PV100R");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File filePV100 = plansetServicePV100.buildingPDFPV100(permitHomeSite,
										permitProjectSiteInfo, permitEntity, listPermitSketchEntity,
										permitDrafterDatanfo, permitArraysEntityResult, electricalCompany, reader,
										listPDFIndex.get(i).getPageIndex(), "PV100", getfilesPath(), ahjNotes, inverterInfo,
										moduleInfo, microInverterInfo,    plansetUtils);
								ut.addSource(filePV100);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV100R.pdf");
							File filePV100 = plansetServicePV100.buildingPDFPV100(permitHomeSite, permitProjectSiteInfo,
									permitEntity, listPermitSketchEntity, permitDrafterDatanfo,
									permitArraysEntityResult, electricalCompany, reader, 0, "PV100", getfilesPath(), ahjNotes,
									inverterInfo, moduleInfo, microInverterInfo,    plansetUtils);
							ut.addSource(filePV100);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				}
				// A.B: CR-636 01-10-2022 PV-101R Mapping
				if (permitArraysEntityResult != null && Boolean.TRUE.equals(permitArraysEntityResult.getRoofMounted()) && checkValue.Equals(ahjNotes.getShowPLDimensions(), "Yes")){
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "PV101R");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File filePV100 = plansetServicePV100.buildingPDFPV100(permitHomeSite,
										permitProjectSiteInfo, permitEntity, listPermitSketchEntity,
										permitDrafterDatanfo, permitArraysEntityResult, electricalCompany, reader,
										listPDFIndex.get(i).getPageIndex(), "PV101", getfilesPath(), ahjNotes, inverterInfo,
										moduleInfo, microInverterInfo,    plansetUtils);
								ut.addSource(filePV100);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV101R.pdf");
							File filePV100 = plansetServicePV100.buildingPDFPV100(permitHomeSite, permitProjectSiteInfo,
									permitEntity, listPermitSketchEntity, permitDrafterDatanfo,
									permitArraysEntityResult, electricalCompany, reader, 0, "PV101", getfilesPath(), ahjNotes,
									inverterInfo, moduleInfo, microInverterInfo,    plansetUtils);
							ut.addSource(filePV100);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				}
				// A.B: PV-100G Mapping
				if (checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)) {
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "PV100G");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {
							
							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File filePV100G = plansetServicePV100G.buildingPDFPV100G(permitHomeSite, permitEntity,
										listPermitSketchEntity, permitDrafterDatanfo, permitArraysEntityResult,
										electricalCompany, reader, listPDFIndex.get(i).getPageIndex(), getfilesPath(),
										inverterInfo, moduleInfo, microInverterInfo, ahjNotes,    plansetUtils);
								ut.addSource(filePV100G);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV100G.pdf");
							File filePV100G = plansetServicePV100G.buildingPDFPV100G(permitHomeSite, permitEntity,
									listPermitSketchEntity, permitDrafterDatanfo, permitArraysEntityResult,
									electricalCompany, reader, 0, getfilesPath(), inverterInfo, moduleInfo,
									microInverterInfo, ahjNotes,    plansetUtils);
							ut.addSource(filePV100G);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}

					// A.B: PV-101G Mapping
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "PV101G");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File filePV101G = plansetServicePV101G.buildingPDFPV101G(permitHomeSite, permitEntity,
										permitDrafterDatanfo, permitArraysEntityResult, electricalCompany, reader,
										listPDFIndex.get(i).getPageIndex(), getfilesPath(), inverterInfo, moduleInfo,
										ahjNotes, microInverterInfo,    plansetUtils);
								ut.addSource(filePV101G);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV101G.pdf");
							File filePV101G = plansetServicePV101G.buildingPDFPV101G(permitHomeSite, permitEntity,
									permitDrafterDatanfo, permitArraysEntityResult, electricalCompany, reader, 0,
									getfilesPath(), inverterInfo, moduleInfo, ahjNotes, microInverterInfo,    plansetUtils);
							ut.addSource(filePV101G);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				}

				// S.B CR-3154-MOD-004 Planset Edits Optimization
				if (checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
						|| checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)) {
					// A.B: S-100 Mapping
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "S100");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File fileS100 = plansetServiceS100.buildingPDFS100(permitHomeSite,
										permitArraysEntityResult, permitProjectSiteInfo, permitEntity, advInputsInfo,
										permitDrafterDatanfo, moduleInfo, microInverterInfo, dcOptimizer, plansetUtils,
										electricalCompany, roofMaterialType, engineer, reader,
										listPDFIndex.get(i).getPageIndex(), getfilesPath(), ahjNotes, inverterInfo);
								ut.addSource(fileS100);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-S100.pdf");
							File fileS100 = plansetServiceS100.buildingPDFS100(permitHomeSite, permitArraysEntityResult,
									permitProjectSiteInfo, permitEntity, advInputsInfo, permitDrafterDatanfo,
									moduleInfo, microInverterInfo, dcOptimizer, plansetUtils, electricalCompany,
									roofMaterialType, engineer, reader, 0, getfilesPath(), ahjNotes, inverterInfo);
							ut.addSource(fileS100);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}

					// A.B: S-200 Mapping
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "S200");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File fileS200 = plansetServiceS200.buildingPDFS200(permitHomeSite, permitAdditionalInfo,
										permitProjectSiteInfo, permitEntity, listPermitSketchEntity, moduleInfo,
										permitArraysEntityResult, stanchionFoot, railRacking, roofMaterialType,
										electricalCompany, flashing, userConnectedEntity, reader,
										listPDFIndex.get(i).getPageIndex(), getfilesPath(), submitId, ahjNotes);
								ut.addSource(fileS200);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-S200-" + rafterOrTruss + ".pdf");
							File fileS200 = plansetServiceS200.buildingPDFS200(permitHomeSite, permitAdditionalInfo,
									permitProjectSiteInfo, permitEntity, listPermitSketchEntity, moduleInfo,
									permitArraysEntityResult, stanchionFoot, railRacking, roofMaterialType,
									electricalCompany, flashing, userConnectedEntity, reader, 0, getfilesPath(),
									submitId, ahjNotes);
							ut.addSource(fileS200);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}

					// A.B: S-201 Mapping
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "S201");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File fileS201 = plansetServiceS201.buildingPDFS201(permitHomeSite,
										permitProjectSiteInfo, permitEntity, electricalCompany, userConnectedEntity,
										reader, listPDFIndex.get(i).getPageIndex(), getfilesPath(), flashing,
										stanchionFoot, railRacking, roofMaterialType, submitId, ahjNotes);
								ut.addSource(fileS201);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-S201.pdf");
							File fileS201 = plansetServiceS201.buildingPDFS201(permitHomeSite, permitProjectSiteInfo,
									permitEntity, electricalCompany, userConnectedEntity, reader, 0, getfilesPath(),
									flashing, stanchionFoot, railRacking, roofMaterialType, submitId, ahjNotes);
							ut.addSource(fileS201);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				}
				// A.B: S-300 Mapping
				if (checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)) {
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "S300");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File fileS300 = plansetServiceS300.buildingPDFS300(permitHomeSite, idPermit,
										electricalCompany, permitEntity, userConnectedEntity, railRackingGround,
										advInputsInfo, permitProjectSiteInfo, moduleInfo, permitArraysEntityResult,
										reader, listPDFIndex.get(i).getPageIndex(), getfilesPath(), inverterInfo,
										submitId, ahjNotes, microInverterInfo,    plansetUtils);
								ut.addSource(fileS300);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-S300.pdf");
							File fileS300 = plansetServiceS300.buildingPDFS300(permitHomeSite, idPermit,
									electricalCompany, permitEntity, userConnectedEntity, railRackingGround,
									advInputsInfo, permitProjectSiteInfo, moduleInfo, permitArraysEntityResult, reader,
									0, getfilesPath(), inverterInfo, submitId, ahjNotes, microInverterInfo,    plansetUtils);
							ut.addSource(fileS300);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				}

				if (checkValue.Equals(inverterTechnologyRev, true)) {

					// A.B: E-001 Mapping
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "E001");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File fileE001 = plansetServiceE001.buildingPDFE001(permitHomeSite, permtiWeather,
										permitProjectSiteInfo, permitEntity, moduleInfo, microInverterInfo,
										permitArraysEntityResult, plansetUtils, dcOptimizer, dcCombinerDisconnect,
										seconddcCombinerDisconnect, thirddcCombinerDisconnect, acDisconnect,
										secondacDisconnect, acCombiner, inverters, firsttInverterQty,
										secondtInverterQty, circuit, slcAcCombiner, roofTopDCDisconnect, reader,
										listPDFIndex.get(i).getPageIndex(), getfilesPath(), designCriteria,
										acDisconnectThree, acDisconnectFour);
								ut.addSource(fileE001);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {
						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E001-"
									+ inverterTechnology + ".pdf");
							File fileE001 = plansetServiceE001.buildingPDFE001(permitHomeSite, permtiWeather,
									permitProjectSiteInfo, permitEntity, moduleInfo, microInverterInfo,
									permitArraysEntityResult, plansetUtils, dcOptimizer, dcCombinerDisconnect,
									seconddcCombinerDisconnect, thirddcCombinerDisconnect, acDisconnect,
									secondacDisconnect, acCombiner, inverters, firsttInverterQty, secondtInverterQty,
									circuit, slcAcCombiner, roofTopDCDisconnect, reader, 0, getfilesPath(),
									designCriteria, acDisconnectThree, acDisconnectFour);
							ut.addSource(fileE001);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}

					// A.B: E-002 Mapping
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "E002");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File fileE002 = null;
								if (checkValue.Equals(inverterTechnology, "STRING")) {
									fileE002 = e002StringMappingService.buildingPDFE002(permitHomeSite, idPermit,
											permitEntity, permtiWeather, permitArraysEntityResult,
											permitProjectSiteInfo, moduleInfo, inverterInfo, secondInverterInfo,
											permitLayoutEntity, circuit, userSetting, dcOptimizer, userConnectedEntity,
											dcCircuitEnvironment, acCircuitEnvironment, dcTradeSize, acTradeSize,
											dcNumberOfConductors, acNumberOfConductors, governingCodes.getNec(), reader,
											listPDFIndex.get(i).getPageIndex(), getfilesPath(), ahjNotes, necOrCecNote,
											thirdInverterInfo, fourthInverterInfo, fifthInverterInfo, plansetUtils, permitAdditionalInfo.getBatteryStorage());

								} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
									fileE002 = plansetServiceE002Micro2.buildingPDFE002(permitHomeSite, idPermit,
											permitEntity, permtiWeather, permitAdditionalInfo.getBatteryStorage(),
											permitProjectSiteInfo, moduleInfo, permitLayoutEntity, circuit,
											microInverterInfo, userSetting, plansetUtils, userConnectedEntity,
											acCircuitEnvironment, acTradeSize, acNumberOfConductors, acCombiner,
											slcAcCombiner, governingCodes.getNec(), reader,
											listPDFIndex.get(i).getPageIndex(), getfilesPath(), ahjNotes, necOrCecNote);
								}
								ut.addSource(fileE002);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E002-"
									+ inverterTechnology + ".pdf");
							File fileE002 = null;
							if (checkValue.Equals(inverterTechnology, "STRING")) {
								fileE002 = e002StringMappingService.buildingPDFE002(permitHomeSite, idPermit,
										permitEntity, permtiWeather, permitArraysEntityResult, permitProjectSiteInfo,
										moduleInfo, inverterInfo, secondInverterInfo, permitLayoutEntity, circuit,
										userSetting, dcOptimizer, userConnectedEntity, dcCircuitEnvironment,
										acCircuitEnvironment, dcTradeSize, acTradeSize, dcNumberOfConductors,
										acNumberOfConductors, governingCodes.getNec(), reader, 0, getfilesPath(),
										ahjNotes, necOrCecNote, thirdInverterInfo, fourthInverterInfo,
										fifthInverterInfo, plansetUtils, permitAdditionalInfo.getBatteryStorage());

							} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
								fileE002 = plansetServiceE002Micro2.buildingPDFE002(permitHomeSite, idPermit,
										permitEntity, permtiWeather, permitAdditionalInfo.getBatteryStorage(), permitProjectSiteInfo,
										moduleInfo, permitLayoutEntity, circuit, microInverterInfo, userSetting,
										plansetUtils, userConnectedEntity, acCircuitEnvironment, acTradeSize,
										acNumberOfConductors, acCombiner, slcAcCombiner, governingCodes.getNec(),
										reader, 0, getfilesPath(), ahjNotes, necOrCecNote);
							}
							ut.addSource(fileE002);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}

					// A.B: E-003 Mapping
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "E003");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File fileE003 = null;
								if (checkValue.Equals(inverterTechnology, "STRING")) {
									fileE003 = plansetServiceE003String.buildingPDFE003(permitHomeSite,
											permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
											permitProjectSiteInfo, plansetUtils, userSetting, leasePPAMeter,
											inverterInfo, secondInverterInfo, dcCombinerDisconnect, acDisconnect,
											permitAdditionalInfo.getBatteryStorage(), jBox, moduleInfo, inverters,
											secondacDisconnect, slcAcCombiner, userConnectedEntity, dcOptimizer,
											roofTopDCDisconnect, advInputsInfo, thirdInverterInfo, fourthInverterInfo,
											dcCircuitEnvironment, acCircuitEnvironment, dcTradeSize, acTradeSize,
											dcNumberOfConductors, acNumberOfConductors, reader,
											listPDFIndex.get(i).getPageIndex(), getfilesPath(), ahjNotes,
											firsttInverterQty, secondtInverterQty, electNote, electricalCompany,
											permitEnergyBatterySystem);
								} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
									fileE003 = plansetServiceE0032Micro.buildingPDFE003(permitHomeSite,
											permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
											permitProjectSiteInfo, plansetUtils, userSetting,
											permitAdditionalInfo.getBatteryStorage(), dcCombinerDisconnect,
											acDisconnect, acCombiner, jBox, moduleInfo, inverters, secondacDisconnect,
											slcAcCombiner, microInverterInfo, userConnectedEntity, permtiWeather,
											permitLayoutEntity, advInputsInfo, leasePPAMeter, acCircuitEnvironment,
											acTradeSize, acNumberOfConductors, reader,
											listPDFIndex.get(i).getPageIndex(), getfilesPath(), qtyAcCombiner, ahjNotes,
											firsttInverterQty, secondtInverterQty, electNote, electricalCompany,
											permitEnergyBatterySystem);
								}
								ut.addSource(fileE003);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E003-"
									+ inverterTechnology + ".pdf");
							File fileE003 = null;
							if (checkValue.Equals(inverterTechnology, "STRING")) {
								fileE003 = plansetServiceE003String.buildingPDFE003(permitHomeSite,
										permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
										permitProjectSiteInfo, plansetUtils, userSetting, leasePPAMeter, inverterInfo,
										secondInverterInfo, dcCombinerDisconnect, acDisconnect,
										permitAdditionalInfo.getBatteryStorage(), jBox, moduleInfo, inverters,
										secondacDisconnect, slcAcCombiner, userConnectedEntity, dcOptimizer,
										roofTopDCDisconnect, advInputsInfo, thirdInverterInfo, fourthInverterInfo,
										dcCircuitEnvironment, acCircuitEnvironment, dcTradeSize, acTradeSize,
										dcNumberOfConductors, acNumberOfConductors, reader, 0, getfilesPath(), ahjNotes,
										firsttInverterQty, secondtInverterQty, electNote, electricalCompany,
										permitEnergyBatterySystem);
							} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
								fileE003 = plansetServiceE0032Micro.buildingPDFE003(permitHomeSite,
										permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
										permitProjectSiteInfo, plansetUtils, userSetting,
										permitAdditionalInfo.getBatteryStorage(), dcCombinerDisconnect, acDisconnect,
										acCombiner, jBox, moduleInfo, inverters, secondacDisconnect, slcAcCombiner,
										microInverterInfo, userConnectedEntity, permtiWeather, permitLayoutEntity,
										advInputsInfo, leasePPAMeter, acCircuitEnvironment, acTradeSize,
										acNumberOfConductors, reader, 0, getfilesPath(), qtyAcCombiner, ahjNotes,
										firsttInverterQty, secondtInverterQty, electNote, electricalCompany,
										permitEnergyBatterySystem);
							}
							ut.addSource(fileE003);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}

					// A.B: E-100 Mapping
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "E100");

					Boolean hasUnderGroundCircuit = dcCircuitEnvironment.contains("UNDERGROUND")
							|| acCircuitEnvironment.contains("UNDERGROUND");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {

							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File fileE100 = plansetServiceE100.buildingPDFE100(permitHomeSite, idPermit,
										permitEntity, permitArraysEntityResult, permitDrafterDatanfo, inverterInfo,
										electricalCompany, reader, listPDFIndex.get(i).getPageIndex(), getfilesPath(),
										ahjNotes, moduleInfo, microInverterInfo, hasUnderGroundCircuit, acDisconnect,    plansetUtils);
								ut.addSource(fileE100);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E100-"
									+ inverterTechnology + ".pdf");
							File fileE100 = plansetServiceE100.buildingPDFE100(permitHomeSite, idPermit, permitEntity,
									permitArraysEntityResult, permitDrafterDatanfo, inverterInfo, electricalCompany,
									reader, 0, getfilesPath(), ahjNotes, moduleInfo, microInverterInfo,
									hasUnderGroundCircuit, acDisconnect,    plansetUtils);
							ut.addSource(fileE100);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				} else {
					// E-Sheet Mapping With No Revision
					try {
						// A.B: E-001 Mapping
						listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit,
								"PDF-E001-" + inverterTechnology, idUser);
						if (listPDFs != null && !listPDFs.isEmpty()) {
							multipleCustomize = multipleCustomize || listPDFs.size() > 1;
							for (int i = 0; i < listPDFs.size(); i++) {

								try {
									PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
											+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
									File fileE001 = plansetServiceE001.buildingPDFE001(permitHomeSite, permtiWeather,
											permitProjectSiteInfo, permitEntity, moduleInfo, microInverterInfo,
											permitArraysEntityResult, plansetUtils, dcOptimizer, dcCombinerDisconnect,
											seconddcCombinerDisconnect, thirddcCombinerDisconnect, acDisconnect,
											secondacDisconnect, acCombiner, inverters, firsttInverterQty,
											secondtInverterQty, circuit, slcAcCombiner, roofTopDCDisconnect, reader, i,
											getfilesPath(), designCriteria, acDisconnectThree, acDisconnectFour);
									ut.addSource(fileE001);
								} catch (Exception e) {
									technicalProblemMsg.traiterException(e);
								}
							}
						} else {

							try {
								PdfReader reader = new PdfReader(
										Constants.rapportPlansetFolderUrl + "PDF-E001-" + inverterTechnology + ".pdf");
								File fileE001 = plansetServiceE001.buildingPDFE001(permitHomeSite, permtiWeather,
										permitProjectSiteInfo, permitEntity, moduleInfo, microInverterInfo,
										permitArraysEntityResult, plansetUtils, dcOptimizer, dcCombinerDisconnect,
										seconddcCombinerDisconnect, thirddcCombinerDisconnect, acDisconnect,
										secondacDisconnect, acCombiner, inverters, firsttInverterQty,
										secondtInverterQty, circuit, slcAcCombiner, roofTopDCDisconnect, reader, 0,
										getfilesPath(), designCriteria, acDisconnectThree, acDisconnectFour);
								ut.addSource(fileE001);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						// A.B: E-002 Mapping
						listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-E002" + inverterTechnology,
								idUser);
						if (listPDFs != null && !listPDFs.isEmpty()) {
							multipleCustomize = multipleCustomize || listPDFs.size() > 1;
							for (int i = 0; i < listPDFs.size(); i++) {

								try {
									PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
											+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
									File fileE002 = null;
									if (checkValue.Equals(inverterTechnology, "STRING")) {
										fileE002 = e002StringMappingService.buildingPDFE002(permitHomeSite, idPermit,
												permitEntity, permtiWeather, permitArraysEntityResult,
												permitProjectSiteInfo, moduleInfo, inverterInfo, secondInverterInfo,
												permitLayoutEntity, circuit, userSetting, dcOptimizer,
												userConnectedEntity, dcCircuitEnvironment, acCircuitEnvironment,
												dcTradeSize, acTradeSize, dcNumberOfConductors, acNumberOfConductors,
												governingCodes.getNec(), reader, i, getfilesPath(), ahjNotes,
												necOrCecNote, thirdInverterInfo, fourthInverterInfo, fifthInverterInfo,
												plansetUtils, permitAdditionalInfo.getBatteryStorage());

									} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
										fileE002 = plansetServiceE002Micro2.buildingPDFE002(permitHomeSite, idPermit,
												permitEntity, permtiWeather, permitAdditionalInfo.getBatteryStorage(),
												permitProjectSiteInfo, moduleInfo, permitLayoutEntity, circuit,
												microInverterInfo, userSetting, plansetUtils, userConnectedEntity,
												acCircuitEnvironment, acTradeSize, acNumberOfConductors, acCombiner,
												slcAcCombiner, governingCodes.getNec(), reader, i, getfilesPath(),
												ahjNotes, necOrCecNote);
									}
									ut.addSource(fileE002);
								} catch (Exception e) {
									technicalProblemMsg.traiterException(e);
								}
							}
						} else {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath
										+ "PDF-E002-" + inverterTechnology + ".pdf");
								File fileE002 = null;
								if (checkValue.Equals(inverterTechnology, "STRING")) {
									fileE002 = e002StringMappingService.buildingPDFE002(permitHomeSite, idPermit,
											permitEntity, permtiWeather, permitArraysEntityResult,
											permitProjectSiteInfo, moduleInfo, inverterInfo, secondInverterInfo,
											permitLayoutEntity, circuit, userSetting, dcOptimizer, userConnectedEntity,
											dcCircuitEnvironment, acCircuitEnvironment, dcTradeSize, acTradeSize,
											dcNumberOfConductors, acNumberOfConductors, governingCodes.getNec(), reader,
											0, getfilesPath(), ahjNotes, necOrCecNote, thirdInverterInfo,
											fourthInverterInfo, fifthInverterInfo, plansetUtils, permitAdditionalInfo.getBatteryStorage());

								} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
									fileE002 = plansetServiceE002Micro2.buildingPDFE002(permitHomeSite, idPermit,
											permitEntity, permtiWeather, permitAdditionalInfo.getBatteryStorage(),
											permitProjectSiteInfo, moduleInfo, permitLayoutEntity, circuit,
											microInverterInfo, userSetting, plansetUtils, userConnectedEntity,
											acCircuitEnvironment, acTradeSize, acNumberOfConductors, acCombiner,
											slcAcCombiner, governingCodes.getNec(), reader, 0, getfilesPath(), ahjNotes,
											necOrCecNote);
								}
								ut.addSource(fileE002);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						// A.B: E-003 Mapping
						listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-E003" + inverterTechnology,
								idUser);
						if (listPDFs != null && !listPDFs.isEmpty()) {
							multipleCustomize = multipleCustomize || listPDFs.size() > 1;
							for (int i = 0; i < listPDFs.size(); i++) {

								try {
									PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
											+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
									File fileE003 = null;
									if (checkValue.Equals(inverterTechnology, "STRING")) {
										fileE003 = plansetServiceE003String.buildingPDFE003(permitHomeSite,
												permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
												permitProjectSiteInfo, plansetUtils, userSetting, leasePPAMeter,
												inverterInfo, secondInverterInfo, dcCombinerDisconnect, acDisconnect,
												permitAdditionalInfo.getBatteryStorage(), jBox, moduleInfo, inverters,
												secondacDisconnect, slcAcCombiner, userConnectedEntity, dcOptimizer,
												roofTopDCDisconnect, advInputsInfo, thirdInverterInfo,
												fourthInverterInfo, dcCircuitEnvironment, acCircuitEnvironment,
												dcTradeSize, acTradeSize, dcNumberOfConductors, acNumberOfConductors,
												reader, i, getfilesPath(), ahjNotes, firsttInverterQty,
												secondtInverterQty, electNote, electricalCompany,
												permitEnergyBatterySystem);
									} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
										fileE003 = plansetServiceE0032Micro.buildingPDFE003(permitHomeSite,
												permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
												permitProjectSiteInfo, plansetUtils, userSetting,
												permitAdditionalInfo.getBatteryStorage(), dcCombinerDisconnect,
												acDisconnect, acCombiner, jBox, moduleInfo, inverters,
												secondacDisconnect, slcAcCombiner, microInverterInfo,
												userConnectedEntity, permtiWeather, permitLayoutEntity, advInputsInfo,
												leasePPAMeter, acCircuitEnvironment, acTradeSize, acNumberOfConductors,
												reader, i, getfilesPath(), qtyAcCombiner, ahjNotes, firsttInverterQty,
												secondtInverterQty, electNote, electricalCompany,
												permitEnergyBatterySystem);
									}
									ut.addSource(fileE003);
								} catch (Exception e) {
									technicalProblemMsg.traiterException(e);
								}
							}
						} else {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath
										+ "PDF-E003-" + inverterTechnology + ".pdf");
								File fileE003 = null;
								if (checkValue.Equals(inverterTechnology, "STRING")) {
									fileE003 = plansetServiceE003String.buildingPDFE003(permitHomeSite,
											permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
											permitProjectSiteInfo, plansetUtils, userSetting, leasePPAMeter,
											inverterInfo, secondInverterInfo, dcCombinerDisconnect, acDisconnect,
											permitAdditionalInfo.getBatteryStorage(), jBox, moduleInfo, inverters,
											secondacDisconnect, slcAcCombiner, userConnectedEntity, dcOptimizer,
											roofTopDCDisconnect, advInputsInfo, thirdInverterInfo, fourthInverterInfo,
											dcCircuitEnvironment, acCircuitEnvironment, dcTradeSize, acTradeSize,
											dcNumberOfConductors, acNumberOfConductors, reader, 0, getfilesPath(),
											ahjNotes, firsttInverterQty, secondtInverterQty, electNote,
											electricalCompany, permitEnergyBatterySystem);
								} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
									fileE003 = plansetServiceE0032Micro.buildingPDFE003(permitHomeSite,
											permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
											permitProjectSiteInfo, plansetUtils, userSetting,
											permitAdditionalInfo.getBatteryStorage(), dcCombinerDisconnect,
											acDisconnect, acCombiner, jBox, moduleInfo, inverters, secondacDisconnect,
											slcAcCombiner, microInverterInfo, userConnectedEntity, permtiWeather,
											permitLayoutEntity, advInputsInfo, leasePPAMeter, acCircuitEnvironment,
											acTradeSize, acNumberOfConductors, reader, 0, getfilesPath(), qtyAcCombiner,
											ahjNotes, firsttInverterQty, secondtInverterQty, electNote,
											electricalCompany, permitEnergyBatterySystem);
								}
								ut.addSource(fileE003);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						// A.B: E-100 Mapping
						listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-E100" + inverterTechnology,
								idUser);
						Boolean hasUnderGroundCircuit = dcCircuitEnvironment.contains("UNDERGROUND")
								|| acCircuitEnvironment.contains("UNDERGROUND");
						if (listPDFs != null && !listPDFs.isEmpty()) {
							multipleCustomize = multipleCustomize || listPDFs.size() > 1;
							for (int i = 0; i < listPDFs.size(); i++) {

								try {
									PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
											+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
									File fileE100 = plansetServiceE100.buildingPDFE100(permitHomeSite, idPermit,
											permitEntity, permitArraysEntityResult, permitDrafterDatanfo, inverterInfo,
											electricalCompany, reader, i, getfilesPath(), ahjNotes, moduleInfo,
											microInverterInfo, hasUnderGroundCircuit, acDisconnect,    plansetUtils);
									ut.addSource(fileE100);
								} catch (Exception e) {
									technicalProblemMsg.traiterException(e);
								}
							}
						} else {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath
										+ "PDF-E100-" + inverterTechnology + ".pdf");
								File fileE100 = plansetServiceE100.buildingPDFE100(permitHomeSite, idPermit,
										permitEntity, permitArraysEntityResult, permitDrafterDatanfo, inverterInfo,
										electricalCompany, reader, 0, getfilesPath(), ahjNotes, moduleInfo,
										microInverterInfo, hasUnderGroundCircuit, acDisconnect,    plansetUtils);
								ut.addSource(fileE100);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}

					} catch (Exception e) {

						e.printStackTrace();
						technicalProblemMsg.traiterException(e);
					}
				}

				// A.B: P-001 Mapping
				listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "P001");
				if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFs.size() > 1;
					for (int i = 0; i < listPDFIndex.size(); i++) {

						try {
							PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
									listPDFIndex.get(i).getPageNumber());
							File fileP001 = plansetServiceP001.buildingPDFP001(permitHomeSite, permitEntity,
									electricalCompany, permitArraysEntityResult, reader,
									listPDFIndex.get(i).getPageIndex(), getfilesPath(), inverterInfo, moduleInfo,
									microInverterInfo,    plansetUtils);
							ut.addSource(fileP001);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				} else {

					try {
						PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-P001.pdf");
						File fileP001 = plansetServiceP001.buildingPDFP001(permitHomeSite, permitEntity,
								electricalCompany, permitArraysEntityResult, reader, 0, getfilesPath(), inverterInfo,
								moduleInfo, microInverterInfo,    plansetUtils);
						ut.addSource(fileP001);
					} catch (Exception e) {
						technicalProblemMsg.traiterException(e);
					}
				}

				// A.B: P-002 Mapping
				if (checkValue.Equals(inverterTechnologyRev, true)) {
					listPDFIndex = getPDFReaderService.getPdfReaderResevation(idPermit, "P002");
					if (listPDFIndex != null && !listPDFIndex.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFIndex.size() > 1;
						for (int i = 0; i < listPDFIndex.size(); i++) {
							try {
								PdfReader reader = getPDFReaderService.getPdfReaderFromRevision(idPermit,
										listPDFIndex.get(i).getPageNumber());
								File fileP002 = plansetServiceP002.buildingPDFP002(permitHomeSite, permitEntity,
										permitArraysEntityResult, permitProjectSiteInfo, moduleInfo, inverterInfo,
										secondInverterInfo, acDisconnect, additionalAcDisconnect, secondacDisconnect, 
										acDisconnectThree, acCombiner, slcAcCombiner, permtiWeather,
										microInverterInfo, inverters, firsttInverterQty, secondtInverterQty,
										electricalCompany, reader, i, getfilesPath(), designCriteria, plansetUtils);
								ut.addSource(fileP002);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-P002-"
									+ inverterTechnology + ".pdf");
							File fileP002 = plansetServiceP002.buildingPDFP002(permitHomeSite, permitEntity,
									permitArraysEntityResult, permitProjectSiteInfo, moduleInfo, inverterInfo,
									secondInverterInfo, acDisconnect, additionalAcDisconnect, secondacDisconnect, 
									acDisconnectThree, acCombiner, slcAcCombiner, permtiWeather,
									microInverterInfo, inverters, firsttInverterQty, secondtInverterQty,
									electricalCompany, reader, 0, getfilesPath(), designCriteria, plansetUtils);
							ut.addSource(fileP002);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				} else {
					listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-P002-" + inverterTechnology,
							idUser);
					if (listPDFs != null && !listPDFs.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFs.size() > 1;
						for (int i = 0; i < listPDFs.size(); i++) {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
										+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
								File fileP002 = plansetServiceP002.buildingPDFP002(permitHomeSite, permitEntity,
										permitArraysEntityResult, permitProjectSiteInfo, moduleInfo, inverterInfo,
										secondInverterInfo, acDisconnect, additionalAcDisconnect, secondacDisconnect, 
										acDisconnectThree, acCombiner, slcAcCombiner, permtiWeather,
										microInverterInfo, inverters, firsttInverterQty, secondtInverterQty,
										electricalCompany, reader, i, getfilesPath(), designCriteria, plansetUtils);
								ut.addSource(fileP002);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
					} else {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-P002-"
									+ inverterTechnology + ".pdf");
							File fileP002 = plansetServiceP002.buildingPDFP002(permitHomeSite, permitEntity,
									permitArraysEntityResult, permitProjectSiteInfo, moduleInfo, inverterInfo,
									secondInverterInfo, acDisconnect, additionalAcDisconnect, secondacDisconnect, 
									acDisconnectThree, acCombiner, slcAcCombiner, permtiWeather,
									microInverterInfo, inverters, firsttInverterQty, secondtInverterQty,
									electricalCompany, reader, 0, getfilesPath(), designCriteria, plansetUtils);
							ut.addSource(fileP002);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
				}

			} catch (DocumentException e) {

				e.printStackTrace();
				technicalProblemMsg.traiterException(e);
			}
		} else {
			try {
				PlansetUsedSheetsLog projectmappedSheets = new PlansetUsedSheetsLog();
				projectmappedSheets.setDate(new Date());
				projectmappedSheets.setUserInfo(userConnectedEntity);
				projectmappedSheets.setProject(permitEntity);
				// A.B: PV-001 Mapping
				listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-PV001", idUser);

				if (listPDFs != null && !listPDFs.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFs.size() > 1;
					for (int i = 0; i < listPDFs.size(); i++) {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
									+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
							File filePV001 = plansetServicePV001.buildingPDFPV001(permitHomeSite, permitAdditionalInfo,
									permitArraysEntityResult, userContactInfo, permitEntity, permitSusyemSize,
									projectEngineer, engineer, electricalCompany, advInputsInfo, inverterInfo,
									moduleInfo, microInverterInfo, stanchionFoot, permitDrafterDatanfo, railRacking,
									railRackingGround, railRackingPatio, railRackingCarport, rapidShutdownModel,
									roofMaterialType, reader, i, getfilesPath(), titleBlockValues, plansetUtils,
									listPermitSketchEntity, permitProjectSiteInfo, qtyAcCombiner, governingCodes,
									ahjNotes, dcOptimizer, secondInverterInfo, thirdInverterInfo, fourthInverterInfo,
									fifthInverterInfo, designCriteria, inverters, flashing, dcCombinerDisconnect,
									seconddcCombinerDisconnect, thirddcCombinerDisconnect, roofTopDCDisconnect, jBox,
									acDisconnect, secondacDisconnect, acDisconnectThree, acDisconnectFour,
									additionalAcDisconnect, slcAcCombiner, acCombiner, leasePPAMeter, true);
							ut.addSource(filePV001);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
					projectmappedSheets.setPv_001("Cus. - " + listPDFs.get(0));
				} else {

					try {
						PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV001.pdf");
						File filePV001 = plansetServicePV001.buildingPDFPV001(permitHomeSite, permitAdditionalInfo,
								permitArraysEntityResult, userContactInfo, permitEntity, permitSusyemSize,
								projectEngineer, engineer, electricalCompany, advInputsInfo, inverterInfo, moduleInfo,
								microInverterInfo, stanchionFoot, permitDrafterDatanfo, railRacking, railRackingGround,
								railRackingPatio, railRackingCarport, rapidShutdownModel, roofMaterialType, reader, 0,
								getfilesPath(), titleBlockValues, plansetUtils, listPermitSketchEntity,
								permitProjectSiteInfo, qtyAcCombiner, governingCodes, ahjNotes, dcOptimizer,
								secondInverterInfo, thirdInverterInfo, fourthInverterInfo, fifthInverterInfo,
								designCriteria, inverters, flashing, dcCombinerDisconnect, seconddcCombinerDisconnect,
								thirddcCombinerDisconnect, roofTopDCDisconnect, jBox, acDisconnect, secondacDisconnect,
								acDisconnectThree, acDisconnectFour, additionalAcDisconnect, slcAcCombiner, acCombiner,
								leasePPAMeter, false);
						ut.addSource(filePV001);
						projectmappedSheets
								.setPv_001(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
					} catch (Exception e) {
						technicalProblemMsg.traiterException(e);
						projectmappedSheets.setPv_001("PR");
					}
				}

				// A.B: N-001 Mapping
				listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-N001", idUser);
				if (listPDFs != null && !listPDFs.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFs.size() > 1;
					for (int i = 0; i < listPDFs.size(); i++) {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
									+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
							File fileN001 = plansetServiceN001.buildingPDFN001(permitHomeSite, permitEntity, reader, i,
									getfilesPath());
							ut.addSource(fileN001);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
					projectmappedSheets.setN_001("Cus. - " + listPDFs.get(0));

				} else {
					if (checkValue.Equals(pdfPath, "NEC-PDF/")) {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-N001.pdf");
							File fileN001 = plansetServiceN001.buildingPDFN001(permitHomeSite, permitEntity, reader, 0,
									getfilesPath());
							ut.addSource(fileN001);
							projectmappedSheets
									.setN_001(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
							projectmappedSheets.setN_001("PR");
						}

					}
				}

				// A.B: PV-100R Mapping
				// F.M CR-PM-3787 2021-16-09
				if (permitArraysEntityResult != null && (Boolean.TRUE.equals(permitArraysEntityResult.getRoofMounted())
						|| ((Boolean.TRUE.equals(permitArraysEntityResult.getCarportMounted())
								|| Boolean.TRUE.equals(permitArraysEntityResult.getPatioMounted()))
								&& checkValue.Equals(permitArraysEntityResult.getRoofOrOpenFrame(),
										"The carport/patio cover will include roofing material under the modules")))) {
					listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-PV100R", idUser);

					if (listPDFs != null && !listPDFs.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFs.size() > 1;
						for (int i = 0; i < listPDFs.size(); i++) {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
										+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
								File filePV100 = plansetServicePV100.buildingPDFPV100(permitHomeSite,
										permitProjectSiteInfo, permitEntity, listPermitSketchEntity,
										permitDrafterDatanfo, permitArraysEntityResult, electricalCompany, reader, i, "PV100",
										getfilesPath(), ahjNotes, inverterInfo, moduleInfo, microInverterInfo,    plansetUtils);
								ut.addSource(filePV100);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						projectmappedSheets.setPv_100R("Cus. - " + listPDFs.get(0));
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV100R.pdf");
							File filePV100 = plansetServicePV100.buildingPDFPV100(permitHomeSite, permitProjectSiteInfo,
									permitEntity, listPermitSketchEntity, permitDrafterDatanfo,
									permitArraysEntityResult, electricalCompany, reader, 0, "PV100", getfilesPath(), ahjNotes,
									inverterInfo, moduleInfo, microInverterInfo,    plansetUtils);
							ut.addSource(filePV100);
							projectmappedSheets
									.setPv_100R(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
							projectmappedSheets.setPv_100R("PR");
						}
					}
				}
				// A.B:CR-636 01-10-2022 PV-101R Mapping
				if (permitArraysEntityResult != null && Boolean.TRUE.equals(permitArraysEntityResult.getRoofMounted()) && checkValue.Equals(ahjNotes.getShowPLDimensions(), "Yes")) {
					listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-PV101R", idUser);

					if (listPDFs != null && !listPDFs.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFs.size() > 1;
						for (int i = 0; i < listPDFs.size(); i++) {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
										+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
								File filePV100 = plansetServicePV100.buildingPDFPV100(permitHomeSite,
										permitProjectSiteInfo, permitEntity, listPermitSketchEntity,
										permitDrafterDatanfo, permitArraysEntityResult, electricalCompany, reader, i, "PV101",
										getfilesPath(), ahjNotes, inverterInfo, moduleInfo, microInverterInfo,    plansetUtils);
								ut.addSource(filePV100);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						projectmappedSheets.setPv_101R("Cus. - " + listPDFs.get(0));
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV101R.pdf");
							File filePV100 = plansetServicePV100.buildingPDFPV100(permitHomeSite, permitProjectSiteInfo,
									permitEntity, listPermitSketchEntity, permitDrafterDatanfo,
									permitArraysEntityResult, electricalCompany, reader, 0, "PV101", getfilesPath(), ahjNotes,
									inverterInfo, moduleInfo, microInverterInfo,    plansetUtils);
							ut.addSource(filePV100);
							projectmappedSheets
									.setPv_100R(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
							projectmappedSheets.setPv_101R("PR");
						}
					}
				}
				// A.B: PV-100G Mapping
				if (checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)) {
					listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-PV100G", idUser);

					if (listPDFs != null && !listPDFs.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFs.size() > 1;
						for (int i = 0; i < listPDFs.size(); i++) {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
										+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
								File filePV100G = plansetServicePV100G.buildingPDFPV100G(permitHomeSite, permitEntity,
										listPermitSketchEntity, permitDrafterDatanfo, permitArraysEntityResult,
										electricalCompany, reader, i, getfilesPath(), inverterInfo, moduleInfo,
										microInverterInfo, ahjNotes,    plansetUtils);
								ut.addSource(filePV100G);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						projectmappedSheets.setPv_100G("Cus. - " + listPDFs.get(0));
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV100G.pdf");
							File filePV100G = plansetServicePV100G.buildingPDFPV100G(permitHomeSite, permitEntity,
									listPermitSketchEntity, permitDrafterDatanfo, permitArraysEntityResult,
									electricalCompany, reader, 0, getfilesPath(), inverterInfo, moduleInfo,
									microInverterInfo, ahjNotes,    plansetUtils);
							ut.addSource(filePV100G);
							projectmappedSheets
									.setPv_100G(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
							projectmappedSheets.setPv_100G("PR");
						}
					}

				}

				// A.B: PV-101G Mapping
				if (checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)) {
					listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-PV101G", idUser);

					if (listPDFs != null && !listPDFs.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFs.size() > 1;
						for (int i = 0; i < listPDFs.size(); i++) {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
										+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
								File filePV101G = plansetServicePV101G.buildingPDFPV101G(permitHomeSite, permitEntity,
										permitDrafterDatanfo, permitArraysEntityResult, electricalCompany, reader, i,
										getfilesPath(), inverterInfo, moduleInfo, ahjNotes, microInverterInfo,    plansetUtils);
								ut.addSource(filePV101G);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						projectmappedSheets.setPv_101G("Cus. - " + listPDFs.get(0));
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-PV101G.pdf");
							File filePV101G = plansetServicePV101G.buildingPDFPV101G(permitHomeSite, permitEntity,
									permitDrafterDatanfo, permitArraysEntityResult, electricalCompany, reader, 0,
									getfilesPath(), inverterInfo, moduleInfo, ahjNotes, microInverterInfo,    plansetUtils);
							ut.addSource(filePV101G);
							projectmappedSheets
									.setPv_101G(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
							projectmappedSheets.setPv_101G("PR");
						}
					}
				}
				// S.B CR-3154-MOD-004 Planset Edits Optimization
				if (checkValue.notChecked(permitArraysEntityResult.getGroundMounted())
						|| checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)) {
					// A.B: S-100 Mapping
					listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-S100", idUser);

					if (listPDFs != null && !listPDFs.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFs.size() > 1;
						for (int i = 0; i < listPDFs.size(); i++) {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
										+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
								File fileS100 = plansetServiceS100.buildingPDFS100(permitHomeSite,
										permitArraysEntityResult, permitProjectSiteInfo, permitEntity, advInputsInfo,
										permitDrafterDatanfo, moduleInfo, microInverterInfo, dcOptimizer, plansetUtils,
										electricalCompany, roofMaterialType, engineer, reader, i, getfilesPath(),
										ahjNotes, inverterInfo);
								ut.addSource(fileS100);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						projectmappedSheets.setS_100("Cus. - " + listPDFs.get(0));
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-S100.pdf");
							File fileS100 = plansetServiceS100.buildingPDFS100(permitHomeSite, permitArraysEntityResult,
									permitProjectSiteInfo, permitEntity, advInputsInfo, permitDrafterDatanfo,
									moduleInfo, microInverterInfo, dcOptimizer, plansetUtils, electricalCompany,
									roofMaterialType, engineer, reader, 0, getfilesPath(), ahjNotes, inverterInfo);
							ut.addSource(fileS100);
							projectmappedSheets
									.setS_100(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
						} catch (Exception e) {
							e.printStackTrace();
							technicalProblemMsg.traiterException(e);
							projectmappedSheets.setS_100("PR");
						}
					}

					// A.B: S-200 Mapping
					listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-S200-" + rafterOrTruss,
							idUser);
					if (listPDFs != null && !listPDFs.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFs.size() > 1;
						for (int i = 0; i < listPDFs.size(); i++) {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
										+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
								File fileS200 = plansetServiceS200.buildingPDFS200(permitHomeSite, permitAdditionalInfo,
										permitProjectSiteInfo, permitEntity, listPermitSketchEntity, moduleInfo,
										permitArraysEntityResult, stanchionFoot, railRacking, roofMaterialType,
										electricalCompany, flashing, userConnectedEntity, reader, i, getfilesPath(),
										submitId, ahjNotes);
								ut.addSource(fileS200);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						projectmappedSheets.setS_200("Cus. - " + listPDFs.get(0));
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-S200-" + rafterOrTruss + ".pdf");
							File fileS200 = plansetServiceS200.buildingPDFS200(permitHomeSite, permitAdditionalInfo,
									permitProjectSiteInfo, permitEntity, listPermitSketchEntity, moduleInfo,
									permitArraysEntityResult, stanchionFoot, railRacking, roofMaterialType,
									electricalCompany, flashing, userConnectedEntity, reader, 0, getfilesPath(),
									submitId, ahjNotes);
							ut.addSource(fileS200);
							projectmappedSheets
									.setS_200(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
							projectmappedSheets.setS_200("PR");
						}
					}

					// A.B: S-201 Mapping
					listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-S201", idUser);

					if (listPDFs != null && !listPDFs.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFs.size() > 1;
						for (int i = 0; i < listPDFs.size(); i++) {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
										+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
								File fileS201 = plansetServiceS201.buildingPDFS201(permitHomeSite,
										permitProjectSiteInfo, permitEntity, electricalCompany, userConnectedEntity,
										reader, i, getfilesPath(), flashing, stanchionFoot, railRacking,
										roofMaterialType, submitId, ahjNotes);
								ut.addSource(fileS201);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						projectmappedSheets.setS_201("Cus. - " + listPDFs.get(0));
					} else {

						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-S201.pdf");
							File fileS201 = plansetServiceS201.buildingPDFS201(permitHomeSite, permitProjectSiteInfo,
									permitEntity, electricalCompany, userConnectedEntity, reader, 0, getfilesPath(),
									flashing, stanchionFoot, railRacking, roofMaterialType, submitId, ahjNotes);
							ut.addSource(fileS201);
							projectmappedSheets
									.setS_201(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
							projectmappedSheets.setS_201("PR");
						}
					}
				}
				// A.B: S-300 Mapping
				if (checkValue.Equals(permitArraysEntityResult.getGroundMounted(), true)) {
					listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-S300", idUser);

					if (listPDFs != null && !listPDFs.isEmpty()) {
						multipleCustomize = multipleCustomize || listPDFs.size() > 1;
						for (int i = 0; i < listPDFs.size(); i++) {

							try {
								PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
										+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
								File fileS300 = plansetServiceS300.buildingPDFS300(permitHomeSite, idPermit,
										electricalCompany, permitEntity, userConnectedEntity, railRackingGround,
										advInputsInfo, permitProjectSiteInfo, moduleInfo, permitArraysEntityResult,
										reader, i, getfilesPath(), inverterInfo, submitId, ahjNotes, microInverterInfo,    plansetUtils);
								ut.addSource(fileS300);
							} catch (Exception e) {
								technicalProblemMsg.traiterException(e);
							}
						}
						projectmappedSheets.setS_300("Cus. - " + listPDFs.get(0));
					} else {
						try {
							PdfReader reader = new PdfReader(
									Constants.rapportPlansetFolderUrl + pdfPath + "PDF-S300.pdf");
							File fileS300 = plansetServiceS300.buildingPDFS300(permitHomeSite, idPermit,
									electricalCompany, permitEntity, userConnectedEntity, railRackingGround,
									advInputsInfo, permitProjectSiteInfo, moduleInfo, permitArraysEntityResult, reader,
									0, getfilesPath(), inverterInfo, submitId, ahjNotes, microInverterInfo,    plansetUtils);
							ut.addSource(fileS300);
							projectmappedSheets
									.setS_300(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
							projectmappedSheets.setS_300("PR");
						}
					}

				}

				// A.B: E-001 Mapping
				listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-E001-" + inverterTechnology,
						idUser);
				if (listPDFs != null && !listPDFs.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFs.size() > 1;
					for (int i = 0; i < listPDFs.size(); i++) {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
									+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
							File fileE001 = plansetServiceE001.buildingPDFE001(permitHomeSite, permtiWeather,
									permitProjectSiteInfo, permitEntity, moduleInfo, microInverterInfo,
									permitArraysEntityResult, plansetUtils, dcOptimizer, dcCombinerDisconnect,
									seconddcCombinerDisconnect, thirddcCombinerDisconnect, acDisconnect,
									secondacDisconnect, acCombiner, inverters, firsttInverterQty, secondtInverterQty,
									circuit, slcAcCombiner, roofTopDCDisconnect, reader, i, getfilesPath(),
									designCriteria, acDisconnectThree, acDisconnectFour);
							ut.addSource(fileE001);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
					projectmappedSheets.setE_001("Cus. - " + listPDFs.get(0));
				} else {

					try {
						PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E001-"
								+ inverterTechnology + ".pdf");
						File fileE001 = plansetServiceE001.buildingPDFE001(permitHomeSite, permtiWeather,
								permitProjectSiteInfo, permitEntity, moduleInfo, microInverterInfo,
								permitArraysEntityResult, plansetUtils, dcOptimizer, dcCombinerDisconnect,
								seconddcCombinerDisconnect, thirddcCombinerDisconnect, acDisconnect, secondacDisconnect,
								acCombiner, inverters, firsttInverterQty, secondtInverterQty, circuit, slcAcCombiner,
								roofTopDCDisconnect, reader, 0, getfilesPath(), designCriteria, acDisconnectThree,
								acDisconnectFour);
						ut.addSource(fileE001);
						projectmappedSheets
								.setE_001(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
					} catch (Exception e) {
						technicalProblemMsg.traiterException(e);
						projectmappedSheets.setE_001("PR");
					}
				}
				// A.B: E-002 Mapping
				listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-E002-" + inverterTechnology,
						idUser);
				if (listPDFs != null && !listPDFs.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFs.size() > 1;
					for (int i = 0; i < listPDFs.size(); i++) {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
									+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
							File fileE002 = null;
							if (checkValue.Equals(inverterTechnology, "STRING")) {
								fileE002 = e002StringMappingService.buildingPDFE002(permitHomeSite, idPermit,
										permitEntity, permtiWeather, permitArraysEntityResult, permitProjectSiteInfo,
										moduleInfo, inverterInfo, secondInverterInfo, permitLayoutEntity, circuit,
										userSetting, dcOptimizer, userConnectedEntity, dcCircuitEnvironment,
										acCircuitEnvironment, dcTradeSize, acTradeSize, dcNumberOfConductors,
										acNumberOfConductors, governingCodes.getNec(), reader, i, getfilesPath(),
										ahjNotes, necOrCecNote, thirdInverterInfo, fourthInverterInfo,
										fifthInverterInfo, plansetUtils, permitAdditionalInfo.getBatteryStorage());

							} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
								fileE002 = plansetServiceE002Micro2.buildingPDFE002(permitHomeSite, idPermit,
										permitEntity, permtiWeather, permitAdditionalInfo.getBatteryStorage(), permitProjectSiteInfo,
										moduleInfo, permitLayoutEntity, circuit, microInverterInfo, userSetting,
										plansetUtils, userConnectedEntity, acCircuitEnvironment, acTradeSize,
										acNumberOfConductors, acCombiner, slcAcCombiner, governingCodes.getNec(),
										reader, i, getfilesPath(), ahjNotes, necOrCecNote);
							}
							ut.addSource(fileE002);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
					projectmappedSheets.setE_002("Cus. - " + listPDFs.get(0));
				} else {

					try {
						PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E002-"
								+ inverterTechnology + ".pdf");
						File fileE002 = null;
						if (checkValue.Equals(inverterTechnology, "STRING")) {
							fileE002 = e002StringMappingService.buildingPDFE002(permitHomeSite, idPermit, permitEntity,
									permtiWeather, permitArraysEntityResult, permitProjectSiteInfo, moduleInfo,
									inverterInfo, secondInverterInfo, permitLayoutEntity, circuit, userSetting,
									dcOptimizer, userConnectedEntity, dcCircuitEnvironment, acCircuitEnvironment,
									dcTradeSize, acTradeSize, dcNumberOfConductors, acNumberOfConductors,
									governingCodes.getNec(), reader, 0, getfilesPath(), ahjNotes, necOrCecNote,
									thirdInverterInfo, fourthInverterInfo, fifthInverterInfo, plansetUtils, permitAdditionalInfo.getBatteryStorage());

						} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
							fileE002 = plansetServiceE002Micro2.buildingPDFE002(permitHomeSite, idPermit, permitEntity,
									permtiWeather, permitAdditionalInfo.getBatteryStorage(), permitProjectSiteInfo, moduleInfo,
									permitLayoutEntity, circuit, microInverterInfo, userSetting, plansetUtils,
									userConnectedEntity, acCircuitEnvironment, acTradeSize, acNumberOfConductors,
									acCombiner, slcAcCombiner, governingCodes.getNec(), reader, 0, getfilesPath(),
									ahjNotes, necOrCecNote);
						}
						ut.addSource(fileE002);
						projectmappedSheets
								.setE_002(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
					} catch (Exception e) {
						technicalProblemMsg.traiterException(e);
						projectmappedSheets.setE_002("PR");
					}
				}
				// A.B: E-003 Mapping
				listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-E003-" + inverterTechnology,
						idUser);
				if (listPDFs != null && !listPDFs.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFs.size() > 1;
					for (int i = 0; i < listPDFs.size(); i++) {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
									+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
							File fileE003 = null;
							if (checkValue.Equals(inverterTechnology, "STRING")) {
								fileE003 = plansetServiceE003String.buildingPDFE003(permitHomeSite,
										permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
										permitProjectSiteInfo, plansetUtils, userSetting, leasePPAMeter, inverterInfo,
										secondInverterInfo, dcCombinerDisconnect, acDisconnect,
										permitAdditionalInfo.getBatteryStorage(), jBox, moduleInfo, inverters,
										secondacDisconnect, slcAcCombiner, userConnectedEntity, dcOptimizer,
										roofTopDCDisconnect, advInputsInfo, thirdInverterInfo, fourthInverterInfo,
										dcCircuitEnvironment, acCircuitEnvironment, dcTradeSize, acTradeSize,
										dcNumberOfConductors, acNumberOfConductors, reader, i, getfilesPath(), ahjNotes,
										firsttInverterQty, secondtInverterQty, electNote, electricalCompany,
										permitEnergyBatterySystem);
							} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
								fileE003 = plansetServiceE0032Micro.buildingPDFE003(permitHomeSite,
										permitAdditionalInfo, permitEntity, circuit, permitArraysEntityResult,
										permitProjectSiteInfo, plansetUtils, userSetting,
										permitAdditionalInfo.getBatteryStorage(), dcCombinerDisconnect, acDisconnect,
										acCombiner, jBox, moduleInfo, inverters, secondacDisconnect, slcAcCombiner,
										microInverterInfo, userConnectedEntity, permtiWeather, permitLayoutEntity,
										advInputsInfo, leasePPAMeter, acCircuitEnvironment, acTradeSize,
										acNumberOfConductors, reader, i, getfilesPath(), qtyAcCombiner, ahjNotes,
										firsttInverterQty, secondtInverterQty, electNote, electricalCompany,
										permitEnergyBatterySystem);
							}
							ut.addSource(fileE003);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
					projectmappedSheets.setE_003("Cus. - " + listPDFs.get(0));
				} else {

					try {
						PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E003-"
								+ inverterTechnology + ".pdf");
						File fileE003 = null;
						if (checkValue.Equals(inverterTechnology, "STRING")) {
							fileE003 = plansetServiceE003String.buildingPDFE003(permitHomeSite, permitAdditionalInfo,
									permitEntity, circuit, permitArraysEntityResult, permitProjectSiteInfo,
									plansetUtils, userSetting, leasePPAMeter, inverterInfo, secondInverterInfo,
									dcCombinerDisconnect, acDisconnect, permitAdditionalInfo.getBatteryStorage(), jBox,
									moduleInfo, inverters, secondacDisconnect, slcAcCombiner, userConnectedEntity,
									dcOptimizer, roofTopDCDisconnect, advInputsInfo, thirdInverterInfo,
									fourthInverterInfo, dcCircuitEnvironment, acCircuitEnvironment, dcTradeSize,
									acTradeSize, dcNumberOfConductors, acNumberOfConductors, reader, 0, getfilesPath(),
									ahjNotes, firsttInverterQty, secondtInverterQty, electNote, electricalCompany,
									permitEnergyBatterySystem);
						} else if (checkValue.Equals(inverterTechnology, "MICRO")) {
							fileE003 = plansetServiceE0032Micro.buildingPDFE003(permitHomeSite, permitAdditionalInfo,
									permitEntity, circuit, permitArraysEntityResult, permitProjectSiteInfo,
									plansetUtils, userSetting, permitAdditionalInfo.getBatteryStorage(),
									dcCombinerDisconnect, acDisconnect, acCombiner, jBox, moduleInfo, inverters,
									secondacDisconnect, slcAcCombiner, microInverterInfo, userConnectedEntity,
									permtiWeather, permitLayoutEntity, advInputsInfo, leasePPAMeter,
									acCircuitEnvironment, acTradeSize, acNumberOfConductors, reader, 0, getfilesPath(),
									qtyAcCombiner, ahjNotes, firsttInverterQty, secondtInverterQty, electNote,
									electricalCompany, permitEnergyBatterySystem);
						}
						ut.addSource(fileE003);
						projectmappedSheets
								.setE_003(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
					} catch (Exception e) {
						technicalProblemMsg.traiterException(e);
						projectmappedSheets.setE_003("PR");
					}
				}
				// A.B: E-100 Mapping
				listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-E100-" + inverterTechnology,
						idUser);
				Boolean hasUnderGroundCircuit = dcCircuitEnvironment.contains("UNDERGROUND")
						|| acCircuitEnvironment.contains("UNDERGROUND");
				if (listPDFs != null && !listPDFs.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFs.size() > 1;
					for (int i = 0; i < listPDFs.size(); i++) {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
									+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
							File fileE100 = plansetServiceE100.buildingPDFE100(permitHomeSite, idPermit, permitEntity,
									permitArraysEntityResult, permitDrafterDatanfo, inverterInfo, electricalCompany,
									reader, i, getfilesPath(), ahjNotes, moduleInfo, microInverterInfo,
									hasUnderGroundCircuit, acDisconnect,    plansetUtils);
							ut.addSource(fileE100);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
					projectmappedSheets.setE_100("Cus. - " + listPDFs.get(0));
				} else {

					try {
						PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E100-"
								+ inverterTechnology + ".pdf");
						File fileE100 = plansetServiceE100.buildingPDFE100(permitHomeSite, idPermit, permitEntity,
								permitArraysEntityResult, permitDrafterDatanfo, inverterInfo, electricalCompany, reader,
								0, getfilesPath(), ahjNotes, moduleInfo, microInverterInfo, hasUnderGroundCircuit, acDisconnect,    plansetUtils);
						ut.addSource(fileE100);
						projectmappedSheets
								.setE_100(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
					} catch (Exception e) {
						technicalProblemMsg.traiterException(e);
						projectmappedSheets.setE_100("PR");
					}
				}

				// A.B: P-001 Mapping
				listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-P001", idUser);

				if (listPDFs != null && !listPDFs.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFs.size() > 1;
					for (int i = 0; i < listPDFs.size(); i++) {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
									+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
							File fileP001 = plansetServiceP001.buildingPDFP001(permitHomeSite, permitEntity,
									electricalCompany, permitArraysEntityResult, reader, i, getfilesPath(),
									inverterInfo, moduleInfo, microInverterInfo,    plansetUtils);
							ut.addSource(fileP001);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
					projectmappedSheets.setP_001("Cus. - " + listPDFs.get(0));
				} else {

					try {
						PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-P001.pdf");
						File fileP001 = plansetServiceP001.buildingPDFP001(permitHomeSite, permitEntity,
								electricalCompany, permitArraysEntityResult, reader, 0, getfilesPath(), inverterInfo,
								moduleInfo, microInverterInfo,    plansetUtils);
						ut.addSource(fileP001);
						projectmappedSheets
								.setP_001(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
					} catch (Exception e) {
						technicalProblemMsg.traiterException(e);
						projectmappedSheets.setP_001("PR");
					}
				}

				// A.B: P-002 Mapping
				listPDFs = getPDFReaderService.getCompatibleCustomize(idPermit, "PDF-P002-" + inverterTechnology,
						idUser);
				if (listPDFs != null && !listPDFs.isEmpty()) {
					multipleCustomize = multipleCustomize || listPDFs.size() > 1;
					for (int i = 0; i < listPDFs.size(); i++) {

						try {
							PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl
									+ "PlansetCustomizeSheets/" + listPDFs.get(i) + ".pdf");
							File fileP002 = plansetServiceP002.buildingPDFP002(permitHomeSite, permitEntity,
									permitArraysEntityResult, permitProjectSiteInfo, moduleInfo, inverterInfo,
									secondInverterInfo, acDisconnect, additionalAcDisconnect, secondacDisconnect, 
									acDisconnectThree, acCombiner, slcAcCombiner, permtiWeather,
									microInverterInfo, inverters, firsttInverterQty, secondtInverterQty,
									electricalCompany, reader, i, getfilesPath(), designCriteria, plansetUtils);
							ut.addSource(fileP002);
						} catch (Exception e) {
							technicalProblemMsg.traiterException(e);
						}
					}
					projectmappedSheets.setP_002("Cus. - " + listPDFs.get(0));
				} else {

					try {
						PdfReader reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-P002-"
								+ inverterTechnology + ".pdf");
						File fileP002 = plansetServiceP002.buildingPDFP002(permitHomeSite, permitEntity,
								permitArraysEntityResult, permitProjectSiteInfo, moduleInfo, inverterInfo,
								secondInverterInfo, acDisconnect, additionalAcDisconnect, secondacDisconnect, 
								acDisconnectThree, acCombiner, slcAcCombiner, permtiWeather, microInverterInfo,
								inverters, firsttInverterQty, secondtInverterQty, electricalCompany, reader, 0,
								getfilesPath(), designCriteria, plansetUtils);
						ut.addSource(fileP002);
						projectmappedSheets
								.setP_002(checkValue.NotEquals(permitHomeSite.getState(), "CA") ? "NEC" : "CEC");
					} catch (Exception e) {
						technicalProblemMsg.traiterException(e);
						projectmappedSheets.setP_002("PR");
					}
				}
				plansetUsedSheetRepo.save(projectmappedSheets);
			} catch (Exception e) {

				e.printStackTrace();
				technicalProblemMsg.traiterException(e);
			}
		}
		ut = mergeRsheets.generateRsheets(ut, moduleInfo, inverters, dcOptimizer, microInverterInfo, railRacking,
				railRackingGround, railRackingPatio, railRackingCarport, stanchionFoot, flashing, dcCombinerDisconnect,
				seconddcCombinerDisconnect, thirddcCombinerDisconnect, roofTopDCDisconnect, jBox, slcAcCombiner, acCombiner, acDisconnect,
				additionalAcDisconnect, secondacDisconnect, permitHomeSite, idPermit, permitEntity, advInputsInfo,
				userConnectedEntity, idUser, submitId, permitArraysEntityResult.getDeviceToIncorporate(),
				permitArraysEntityResult.getGroundMounted(), permitArraysEntityResult.getPatioMounted(),
				permitArraysEntityResult.getCarportMounted(), permitEnergyBatterySystem,
				designCriteria.getAsceStandard(), permitAdditionalInfo.getBatteryStorage(), governingCodes);

		try {
			String path2 = getfilesPath();
			if (!new File(path2).exists()) {
				new File(path2).mkdir();
			}
			if (!new File(getfilesPath() + "Rapport/").exists()) {
				new File(getfilesPath() + "Rapport/").mkdir();
			}

			try {
				ut.mergeDocuments();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			final CountDownLatch done = new CountDownLatch(1);

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						PdfReader reader = new PdfReader(getfilesPath() + "Rapport/PlansetPDF.pdf");
						reader.selectPages("2-" + reader.getNumberOfPages());
						java.io.File fil = new File(getfilesPath() + "Rapport/Pre0-SampleResult" + idPermit + ".pdf");
						PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fil));
						stamper.close();
						reader.close();
						done.countDown();
						File fileTest = new File(getfilesPath() + "Rapport/PlansetPDF.pdf");
						fileTest.delete();

						String src = getfilesPath() + "Rapport/SampleResult" + idPermit + ".pdf";
						String reslt1 = getfilesPath() + "Rapport/Pre0-SampleResult" + idPermit + ".pdf";
						String reslt2 = getfilesPath() + "Rapport/Pre1-SampleResult" + idPermit + ".pdf";

						try {
							updateTitlePlanSetBlock.updateTitleBlock(reslt1, reslt2, src, titleBlockValues);
						} catch (Exception e) {
							e.printStackTrace();
						}

						new File(reslt1).delete();
						new File(reslt2).delete();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (DocumentException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			done.await(1, TimeUnit.SECONDS);

			// -----Set variabel ----//

			permitEntity.setPlansetState(permitHomeSite.getState());
			permitEntity.setPlansetRoofDesign(permitHomeSite.getRoofRafter());
			String plansetRoofRafter = (permitHomeSite.getRoofRafter() == null
					|| checkValue.Equals(permitHomeSite.getRoofRafter(), ""))
							? null
							: (checkValue.Equals(permitHomeSite.getRoofRafter(), "Pre-Eng Roof Trusses")
									|| checkValue.Equals(permitHomeSite.getRoofRafter(), "OtherRoof")) ? "TRUSS"
											: "RAFTER";
			permitEntity.setPlansetRoofRafter(plansetRoofRafter);
			permitEntity.setPlansetInverterTechnologies(permitArraysEntityResult.getDeviceToIncorporate());
			projectRepo.save(permitEntity);

			// - - -- Delete File - - -//
			String[] sheetsList = { "PV001", "N001", "PV100R", "PV101R", "PV100G", "PV101G", "S100", "S200-RAFTER", "S200-TRUSS",
					"S201", "S300", "E001-STRING", "E001-MICRO", "E002-STRING", "E002-MICRO", "E003-STRING",
					"E003-MICRO", "E100-STRING", "E100-MICRO", "P001", "P002-STRING", "P002-MICRO", "Voltage-Drop-" };

			for (int i = 0; i < sheetsList.length; i++) {
				int sheetIndex = 0;
				while (new File(Constants.rapportPlansetFolderUrl + "PDF-" + sheetsList[i] + idPermit + "-" + sheetIndex
						+ ".pdf").exists()) {
					new File(Constants.rapportPlansetFolderUrl + "PDF-" + sheetsList[i] + idPermit + "-" + sheetIndex
							+ ".pdf").delete();
					sheetIndex++;
				}
			}
			if (new File(getfilesPath() + "Rapport/SampleResult" + idPermit + "Copy.pdf").exists()) {
				Files.delete(Paths.get(getfilesPath() + "Rapport/SampleResult" + idPermit + "Copy.pdf"));
			}
			mergeRsheets.deleteRsheets(idPermit);
			List<MissingSheetsLogEntity> missingSheets = missingSheetRepo.findBySubmissionIdAndProjectId(submitId,
					permitEntity.getId());
			if (!missingSheets.isEmpty()) {
				mailingService.sendMissingEmail(missingSheets);
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);

			return "Fail";
		}

		return checkValue.Equals(multipleCustomize, true) ? "Done with multiple customize sheet" : "Done";
	}


	/*
	 * CR OCPD
	 */

	public String getWireQtyMicroInverter(Long microInverterID) {
		String wireQty = "";
		try {
			if (microInverterID != null) {
				wireQty = inverterRepo.getInverterWireQty(microInverterID);
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);

		}
		return wireQty;

	}

	public String getWireQtyInverter(Long inverterId) {
		String wireQty = "";
		try {
			if (inverterId != null) {
				wireQty = inverterRepo.getInverterWireQty(inverterId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);

		}
		return wireQty;
	}

}
