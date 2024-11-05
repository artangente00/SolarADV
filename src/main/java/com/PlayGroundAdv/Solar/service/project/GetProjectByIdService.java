package com.PlayGroundAdv.Solar.service.project;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.NotAllowedRackingNotes;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.ProjectBattery;
import com.PlayGroundAdv.Solar.entity.projects.ProjectCustomFiles;
import com.PlayGroundAdv.Solar.entity.users.UserCutomUpload;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.model.NotAllowedRackingNotesModel;
import com.PlayGroundAdv.Solar.model.OCPDMainPanelModel;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResultPrime;
import com.PlayGroundAdv.Solar.model.PermitConduitConductorSectionEntitieResult;
import com.PlayGroundAdv.Solar.model.PermitDrafterDataResult;
import com.PlayGroundAdv.Solar.model.PermitEditStatusModel;
import com.PlayGroundAdv.Solar.model.PermitEnergyBatterySystemDto;
import com.PlayGroundAdv.Solar.model.PermitEngineerEntityResult;
import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
import com.PlayGroundAdv.Solar.model.PermitLayoutSketchResult;
import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
import com.PlayGroundAdv.Solar.model.PermitSketchResults;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.model.ProjectBatteryDto;
import com.PlayGroundAdv.Solar.model.ProjectNotesDto;
import com.PlayGroundAdv.Solar.model.SystemAttributesModel;
import com.PlayGroundAdv.Solar.model.project.CustomUpload;
import com.PlayGroundAdv.Solar.repositories.NotAllowedRackingNotesRepository;
import com.PlayGroundAdv.Solar.repositories.NoteSketchFilesRepository;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdditionalInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdvInputsRepository;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitCompanyInfoRepository;
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
import com.PlayGroundAdv.Solar.repositories.ProjectNotesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.project.ProjectCustomFilesRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserCutomUploadRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetComponentsIdUtils;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetInverterOCPD;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetModuleOCPD;
import com.PlayGroundAdv.Solar.service.equipment_utils.ProjectNumberOfMicroInverter;
import com.PlayGroundAdv.Solar.service.libraries.InverterService;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class GetProjectByIdService {
	
	final HistoryActivityService historicActivity;
	final CheckValueTypesService checkValueTypesService;
	final GetProjectDetailsUtils getProjectDetailsUtils;
	final GetInverterOCPD getInverterOCPD;
	final GetModuleOCPD getModuleOCPD;
	final AuthenticationRepository userRepo;
	final UserSettingRepository userSettingRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepos;
	final PermitHomeSiteInfoRepository permitHomeSiteInfoRepo;
	final PermitArraysRepository projectArraysRepo;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	final PermitAdvInputsRepository permitAdvInputsRepo;
	final PermitRepository permitRepo;
	final PermitCompanyInfoRepository permitCompanyInfoRepo;
	final PermitConduitConductorSectionRepository permitConduitConductorRepo;
	final PermitAdditionalInfoRepository permitAdditionalInfoRepo;
	final PermitLayoutRepository permitLayoutRepo;
	final PermitWeatherRepository permitWeatherRepo;
	final PermitEngineerRepository permitEngineerRepo;
	final NoteSketchFilesRepository noteSketchFilesRepo;
	final GetComponentsIdUtils getComponentsId;
	final PathRepository pathRepo;
	final InverterService inverterLibrarySerivce;
	final ProjectNumberOfMicroInverter projectNumberOfMicroInverter;
	final NotAllowedRackingNotesRepository rackingNotesRepo;
	final CalculMinOCPDMainPanel calculMinOCPDMainPanel;
	final private ModelMapper modelMapper;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	final BatteryRepository batteryRepo;
	final ProjectNotesRepository projectNotesRepo;
	final PermitSketchRepository permitSketchRepo;
	final SimpUserRegistry userRegistry;
	final UserCutomUploadRepository cutomUploadRepo;
	final ProjectCustomFilesRepository customFilesRepo;
	final ESSConfiguration essConfiguration;
	public GetProjectByIdService(HistoryActivityService historicActivity, CheckValueTypesService checkValueTypesService,
			GetProjectDetailsUtils getProjectDetailsUtils, GetInverterOCPD getInverterOCPD, GetModuleOCPD getModuleOCPD,
			AuthenticationRepository userRepo,
			UserSettingRepository userSettingRepo, PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepos,
			PermitHomeSiteInfoRepository permitHomeSiteInfoRepo, PermitArraysRepository projectArraysRepo,
			PermitDrafterDataRepository permitDrafterDataRepo, PermitAdvInputsRepository permitAdvInputsRepo,
			PermitRepository permitRepo, PermitCompanyInfoRepository permitCompanyInfoRepo,
			PermitConduitConductorSectionRepository permitConduitConductorRepo,
			PermitAdditionalInfoRepository permitAdditionalInfoRepo, PermitLayoutRepository permitLayoutRepo,
			PermitWeatherRepository permitWeatherRepo, PermitEngineerRepository permitEngineerRepo,
			NoteSketchFilesRepository noteSketchFilesRepo, GetComponentsIdUtils getComponentsId,
			InverterService inverterLibrarySerivce, 
			ProjectNumberOfMicroInverter projectNumberOfMicroInverter, PathRepository pathRepo, 
			NotAllowedRackingNotesRepository rackingNotesRepo, CalculMinOCPDMainPanel calculMinOCPDMainPanel, ModelMapper modelMapper,
			PermitEnergyBatterySystemRepository energyBatterySystemRepo,BatteryRepository batteryRepo,
			ProjectNotesRepository projectNotesRepo, PermitSketchRepository permitSketchRepo,SimpUserRegistry userRegistry,
			UserCutomUploadRepository cutomUploadRepo, ProjectCustomFilesRepository customFilesRepo,ESSConfiguration essConfiguration) {
		super();
		this.historicActivity = historicActivity;
		this.checkValueTypesService = checkValueTypesService;
		this.getProjectDetailsUtils = getProjectDetailsUtils;
		this.getInverterOCPD = getInverterOCPD;
		this.getModuleOCPD = getModuleOCPD;
		this.userRepo = userRepo;
		this.userSettingRepo = userSettingRepo;
		this.permitProjectSiteInfoEntityRepos = permitProjectSiteInfoEntityRepos;
		this.permitHomeSiteInfoRepo = permitHomeSiteInfoRepo;
		this.projectArraysRepo = projectArraysRepo;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
		this.permitAdvInputsRepo = permitAdvInputsRepo;
		this.permitRepo = permitRepo;
		this.permitCompanyInfoRepo = permitCompanyInfoRepo;
		this.permitConduitConductorRepo = permitConduitConductorRepo;
		this.permitAdditionalInfoRepo = permitAdditionalInfoRepo;
		this.permitLayoutRepo = permitLayoutRepo;
		this.permitWeatherRepo = permitWeatherRepo;
		this.permitEngineerRepo = permitEngineerRepo;
		this.noteSketchFilesRepo = noteSketchFilesRepo;
		this.getComponentsId = getComponentsId;
		this.inverterLibrarySerivce = inverterLibrarySerivce;
		this.projectNumberOfMicroInverter = projectNumberOfMicroInverter;
		this.pathRepo = pathRepo;
		this.rackingNotesRepo = rackingNotesRepo;
		this.calculMinOCPDMainPanel = calculMinOCPDMainPanel;
		this.modelMapper = modelMapper;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
		this.batteryRepo = batteryRepo;
		this.projectNotesRepo = projectNotesRepo;
		this.permitSketchRepo = permitSketchRepo;
		this.userRegistry = userRegistry;
		this.cutomUploadRepo = cutomUploadRepo;
		this.customFilesRepo = customFilesRepo;
		this.essConfiguration = essConfiguration;
	}


	public GetPermitByIdResult getProjectById(Long idProject, Long idUser, String timeZone, String numTab,
			String sessionId, String openDate, String ip, Boolean isDrafter, Boolean modeView) {
		GetPermitByIdResult getPermitByIdResult = new GetPermitByIdResult();

		try {

			// get Permit
			PermitEntity permitEntity = permitRepo.findById(idProject).get();
			UserSettingEntity userSettingEntity = userSettingRepo.findByUserIdId(permitEntity.getAuthentificationEntity().getId());
			Long projectOwnerId = permitEntity.getAuthentificationEntity().getId();
			// get Permit sketch layout
			PermitLayoutSketchResult permitLayoutSketchResult = permitLayoutRepo.getPermitLayoutSketchResult(idProject);
			if (permitLayoutSketchResult != null) {
				permitLayoutSketchResult.setNoteSketchFilesOne(getNoteSketchFilesOne(idProject));
				permitLayoutSketchResult.setNoteSketchFilesTwo(getNoteSketchFilesTwo(idProject));
			}

			// Get Permit Drafter Entity
			PermitDrafterDataResult permitDrafterDataResult = permitDrafterDataRepo
					.getPermitDrafterDataResult(idProject);

			// Get PermitEngineerEntity
			PermitEngineerEntityResult permitEngineerEntityResult = permitEngineerRepo
					.getPermitEngineerEntityResult(idProject);

			// Get PermitAdditionalInfoEntity
			PermitAdditionalInfoEntityResult permitAdditionalInfoEntityResult = permitAdditionalInfoRepo
					.getPermitAdditionalInfoEntityResult(idProject);

			// Get PermitAdvEntity
			PermitAdvEntityResult permitAdvEntityResult = permitAdvInputsRepo.getProjectADVInputs(idProject);

			// Get PermitArraysEntity
			PermitArrayEntityResultSecond permitArraysEntityResult = projectArraysRepo
					.getPermitArrayEntityResultSecond(idProject);
			if (permitArraysEntityResult != null) {

				// A.B 01-16 Test Romoved & Fav Removed
				if (permitArraysEntityResult.getInverterModel() != null) {
					permitArraysEntityResult.setInverterModel(
							getComponentsId.testInverterRemoved(permitArraysEntityResult.getInverterModel(),
									permitEntity.getAuthentificationEntity().getId(), permitEntity.getId(), 1));
				}
				if (permitArraysEntityResult.getSecondInverterModel() != null) {
					permitArraysEntityResult.setSecondInverterModel(
							getComponentsId.testInverterRemoved(permitArraysEntityResult.getSecondInverterModel(),
									permitEntity.getAuthentificationEntity().getId(), permitEntity.getId(), 2));
				}
				if (permitArraysEntityResult.getThirdInverterModel() != null) {
					permitArraysEntityResult.setThirdInverterModel(
							getComponentsId.testInverterRemoved(permitArraysEntityResult.getThirdInverterModel(),
									permitEntity.getAuthentificationEntity().getId(), permitEntity.getId(), 3));
				}
				if (permitArraysEntityResult.getFourthInverterModel() != null) {
					permitArraysEntityResult.setFourthInverterModel(
							getComponentsId.testInverterRemoved(permitArraysEntityResult.getFourthInverterModel(),
									permitEntity.getAuthentificationEntity().getId(), permitEntity.getId(), 4));
				}
				if (permitArraysEntityResult.getFifthInverterModel() != null) {
					permitArraysEntityResult.setFifthInverterModel(
							getComponentsId.testInverterRemoved(permitArraysEntityResult.getFifthInverterModel(),
									permitEntity.getAuthentificationEntity().getId(), permitEntity.getId(), 5));
				}

				// C.I 01-20 Test Romoved & Fav Removed
				if (permitArraysEntityResult.getPvModuleModEl() != null) {
					permitArraysEntityResult.setPvModuleModEl(
							getComponentsId.testPVModuleRemoved(permitArraysEntityResult.getPvModuleModEl(),
									permitEntity.getAuthentificationEntity().getId(), permitEntity.getId()));
				}

				// C.I 01-20 Test Romoved & Fav Removed
				if (permitArraysEntityResult.getMicroInverter() != null) {
					permitArraysEntityResult.setMicroInverter(
							getComponentsId.testMicroInverterRemoved(permitArraysEntityResult.getMicroInverter(),
									permitEntity.getAuthentificationEntity().getId(), permitEntity.getId()));
				}

				// M.A: 02-18 Test Removed && Fav removed
				if (permitArraysEntityResult.getSystemOptimizerModel() != null) {
					permitArraysEntityResult.setSystemOptimizerModel(
							getComponentsId.testDCOptimizerRemoved(permitArraysEntityResult.getSystemOptimizerModel(),
									permitEntity.getAuthentificationEntity().getId(), permitEntity.getId()));

				}

			}
			

			/***************** Get Inverter OCPD Attributes *********************/
			OCPDMainPanelModel ocpdMainPanelModel = new OCPDMainPanelModel(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
			if (Boolean.TRUE.equals(getProjectDetailsUtils
					.isStringOrOptimizerProject(permitArraysEntityResult.getDeviceToIncorporate()))) {
				ocpdMainPanelModel = calculMinOCPDMainPanel.stringMinOCPDMainPanel(idProject, permitArraysEntityResult);
			}else if (permitArraysEntityResult.getDeviceToIncorporate() != null && permitArraysEntityResult.getDeviceToIncorporate().equals("AC Modules")) {
				ocpdMainPanelModel = calculMinOCPDMainPanel.acModuleMinOCPDMainPanel(idProject, permitArraysEntityResult);
			}

			// Get PermitCompanyInfoEntity
			PermitCompanyInfoEntityResultPrime permitCompanyInfoEntityResult = permitCompanyInfoRepo
					.getPermitCompanyInfoEntityResultPrime(idProject);

			// Get PermitHomeSiteEntity
			PermitHomeSiteEntityResult permitHomeSiteEntityResult = permitHomeSiteInfoRepo
					.getProjectHomeSiteInfo(idProject);
			if (permitHomeSiteEntityResult != null) {
				permitHomeSiteEntityResult.setResidenceBindingCategory(checkValueTypesService
						.isStringNotEmpty(permitHomeSiteEntityResult.getResidenceBindingCategory())
								? permitHomeSiteEntityResult.getResidenceBindingCategory()
								: "Exposure C");
			}

			// Get PermitProjectSiteInfoEntityResult
			PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityResult = permitProjectSiteInfoEntityRepos
					.getPermitProjectSiteInfoEntityTwo(idProject);
			if (permitProjectSiteInfoEntityResult != null) {

				PermitProjectSiteInfoEntity per = permitProjectSiteInfoEntityRepos.findByPermitEntityId(idProject);
				permitProjectSiteInfoEntityResult.setRailRakingModel(
						getComponentsId.getIdOfRailRackingEditProj(per.getRailRakingModel(), per.getRailRaikingModelObject(),
								projectOwnerId));
				permitProjectSiteInfoEntityResult.setRailRakingModelforGroundMounted(getComponentsId.getIdOfRailRackingEditProj(
						per.getRailRakingModelforGroundMounted(), per.getRailRaikingGroundModelObject(),
						projectOwnerId));
				if(per.getRailRakingforPatioMounted() != null)
					permitProjectSiteInfoEntityResult.setRailRakingforPatioMounted(per.getRailRakingforPatioMounted().getId());
				if(per.getRailRakingforCarport() != null)
					permitProjectSiteInfoEntityResult.setRailRakingforCarport(per.getRailRakingforCarport().getId());

				permitProjectSiteInfoEntityResult.setDisconnectModel(getComponentsId.getIdOfDcCombiner(per.getDisconnectModel(),
						per.getDisconnectModelManuf(), projectOwnerId));
				permitProjectSiteInfoEntityResult.setDisconnectModelTwo(getComponentsId.getIdOfDcCombiner(per.getDisconnectModelTwo(),
						per.getDisconnectModelManuTwo(), projectOwnerId));
				permitProjectSiteInfoEntityResult.setDisconnectModelThree(
						getComponentsId.getIdOfDcCombiner(per.getDisconnectModelThree(), per.getDisconnectModelManufThree(),
								projectOwnerId));

				permitProjectSiteInfoEntityResult.setRoofTopDCDisco(getComponentsId.getIdOfDcCombiner(per.getRoofTopDCDisco(),
						per.getRoofTopDCDiscoModel(), projectOwnerId));
				permitProjectSiteInfoEntityResult.setRoofTopDCCombiner(getComponentsId.getIdOfDcCombiner(per.getRoofTopDCCombiner(),
						per.getRoofTopDCCombinerModel(), projectOwnerId));

				// The AC Combiner SLC
				permitProjectSiteInfoEntityResult.setaCCombinerInstalled(
						getComponentsId.getIdOfACCombinerSLC(per.getACCombinerInstalled(), per.getACCombinerInstalledString(),
								projectOwnerId));
				permitProjectSiteInfoEntityResult.setGroundLevelACCombinerBoxModel(getComponentsId.getIdOfACCombinerSLC(
						per.getGroundLevelACCombinerBoxModel(), per.getGroundLevelACCombinerBoxModelString(),
						projectOwnerId));
				permitProjectSiteInfoEntityResult.setRoofTopACCombiner(getComponentsId.getIdOfACCombinerSLC(per.getRoofTopACCombiner(),
						per.getRoofTopACCombinerString(), projectOwnerId));
				
				//  //	A.B 09-03-2021 CR-PM-2227 The AC Disconnect
				permitProjectSiteInfoEntityResult.setAcDisconnectThree(getComponentsId.getACDisconnectId(per.getAcDisconnectThree(), projectOwnerId));
				permitProjectSiteInfoEntityResult.setAcDisconnectFour(getComponentsId.getACDisconnectId(per.getAcDisconnectFour(), projectOwnerId));

			}
			
			//Get Not Allowed Racking Notes
			NotAllowedRackingNotes projectNotes = rackingNotesRepo.findByProjectId(idProject);
			NotAllowedRackingNotesModel projectNotesModel = new NotAllowedRackingNotesModel();
			if (projectNotes != null) {
				projectNotesModel = modelMapper.map(projectNotes,
						NotAllowedRackingNotesModel.class);
			}
			
			
			// Get PermtiWeatherEntity
			PermtiWeatherEntityResult permtiWeatherEntityResult = permitWeatherRepo
					.getPermtiWeatherEntityResult(idProject);

			// getOwner of the permit
			AuthentificationEntity user = permitEntity.getAuthentificationEntity();
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(user.getId());

			// Get PermitConduitConductorSectionEntitieResult
			PermitConduitConductorSectionEntity permitConduitConductorSection = permitConduitConductorRepo
					.findByPermitEntityId(idProject);
			PermitConduitConductorSectionEntitieResult permitConduitConductorSectionResult = new PermitConduitConductorSectionEntitieResult();
			permitConduitConductorSectionResult = modelMapper.map(permitConduitConductorSection,
					PermitConduitConductorSectionEntitieResult.class);

			
			/*****************
			 * initisalisation of table in the BOS Tab
			 *********************/
			if (permitProjectSiteInfoEntityResult.getRailRakingModelforGroundMounted() == null) {
				permitProjectSiteInfoEntityResult
						.setRailRakingModelforGroundMounted(userSetting.getUserGroundRailRaking() != null
								? Long.valueOf(userSetting.getUserGroundRailRaking())
								: null);
			}
			if (permitAdvEntityResult.getSizeOfPipe() == null) {
				permitAdvEntityResult.setSizeOfPipe(userSetting.getUserSizeOfPipe());
			}
			if (permitAdvEntityResult.getSizeOfPipeOther() == null) {
				permitAdvEntityResult.setSizeOfPipeOther(userSetting.getUserSizeOfPipeOther());
			}
			if (permitAdvEntityResult.getThicknessOfPipe() == null) {
				permitAdvEntityResult.setThicknessOfPipe(userSetting.getUserThicknessOfPipe());
			}
			if (permitAdvEntityResult.getThicknessOfPipeOther() == null) {
				permitAdvEntityResult.setThicknessOfPipeOther(userSetting.getUserThicknessOfPipeOther());
			}
			if (permitAdvEntityResult.getBracedUnbraced() == null) {
				permitAdvEntityResult.setBracedUnbraced(userSetting.getUserBracedUnbraced());
			}
			if (permitAdvEntityResult.getFootingDiameter() == null) {
				permitAdvEntityResult.setFootingDiameter(userSetting.getUserFootingDiameter());
			}
			if (permitAdvEntityResult.getFootingDiameterOther() == null) {
				permitAdvEntityResult.setFootingDiameterOther(userSetting.getUserFootingDiameterOther());
			}
			
			// Get Permit Energy Battery System
			PermitEnergyBatterySystem permitEnergyBatterySystem = energyBatterySystemRepo.findByProjectId(permitEntity.getId());
			PermitEnergyBatterySystemDto energyBatterySystem = new PermitEnergyBatterySystemDto();
		
			if(permitEnergyBatterySystem == null) {
				permitEnergyBatterySystem = new PermitEnergyBatterySystem();
				permitEnergyBatterySystem.setProject(permitEntity);
				if (checkValueTypesService.isNumericNotZero(permitAdditionalInfoEntityResult.getBattery())) {
					Battery b = batteryRepo.findById(Long.valueOf(permitAdditionalInfoEntityResult.getBattery())).orElseThrow(
							() -> new ResourceNotFoundException("Entity not found for this id :" + permitAdditionalInfoEntityResult.getBattery()));
					permitEnergyBatterySystem.setBatteries(new ArrayList<>());
					permitEnergyBatterySystem.addBattery(b, permitAdditionalInfoEntityResult.getQuantityBatteries());
					ProjectBatteryDto dto = new ProjectBatteryDto(getComponentsId.getBattery(b, projectOwnerId), permitAdditionalInfoEntityResult.getQuantityBatteries());
					energyBatterySystem.setBatteries(new ArrayList<>());
					energyBatterySystem.getBatteries().add(dto);
					energyBatterySystem.getBatteries().add(new ProjectBatteryDto());
				}else {
					energyBatterySystem.setBatteries(new ArrayList<>());
					energyBatterySystem.getBatteries().add(new ProjectBatteryDto());
				}
				energyBatterySystemRepo.save(permitEnergyBatterySystem);
				energyBatterySystem.setId(permitEnergyBatterySystem.getId());
				
			}else {
				energyBatterySystem.setTypeGridTied(permitEnergyBatterySystem.getTypeGridTied());
				energyBatterySystem.setAtsIncluded(permitEnergyBatterySystem.getAtsIncluded());
				energyBatterySystem.setDcDisconnectIncluded(permitEnergyBatterySystem.getDcDisconnectIncluded());
				energyBatterySystem.setAcDisconnectIncluded(permitEnergyBatterySystem.getAcDisconnectIncluded());
				energyBatterySystem.setGeneratorIncluded(permitEnergyBatterySystem.getGeneratorIncluded());
				energyBatterySystem.setRsdConnected(permitEnergyBatterySystem.getRsdConnected());
				energyBatterySystem.setGeneratorStatus(permitEnergyBatterySystem.getGeneratorStatus());
				energyBatterySystem.setFuelType(permitEnergyBatterySystem.getFuelType());
				energyBatterySystem.setFuelDistributionPipeType(permitEnergyBatterySystem.getFuelDistributionPipeType());
				energyBatterySystem.setFuelDistributionPipeTypeOther(permitEnergyBatterySystem.getFuelDistributionPipeTypeOther());
				energyBatterySystem.setPipeSize(permitEnergyBatterySystem.getPipeSize());
				energyBatterySystem.setPipeSizeOther(permitEnergyBatterySystem.getPipeSizeOther());
				energyBatterySystem.setEssSpecificationComment(permitEnergyBatterySystem.getEssSpecificationComment());
				//A.B REV CR-3919-MOD-005
				energyBatterySystem.setQtyAts(permitEnergyBatterySystem.getQtyAts());
				energyBatterySystem.setQtySecondAts(permitEnergyBatterySystem.getQtySecondAts());
				energyBatterySystem.setQtyAcd(permitEnergyBatterySystem.getQtyAcd());
				energyBatterySystem.setQtySecondAcd(permitEnergyBatterySystem.getQtySecondAcd());
				
				energyBatterySystem.setBatteries(new ArrayList<>());
				energyBatterySystem.setEssSpecificationDetails(new ArrayList<>());
				if(permitEnergyBatterySystem.getEssSpecificationDetails() != null && !permitEnergyBatterySystem.getEssSpecificationDetails().isEmpty()) {
					for (String f : permitEnergyBatterySystem.getEssSpecificationDetails()) {
						energyBatterySystem.getEssSpecificationDetails().add(f);
					}
				}
				for (ProjectBattery battery : permitEnergyBatterySystem.getBatteries()) {
					ProjectBatteryDto b = new ProjectBatteryDto(getComponentsId.getBattery(battery.getBatteryId(), projectOwnerId), battery.getQuantity());
					energyBatterySystem.getBatteries().add(b);
				}
				if(energyBatterySystem.getBatteries().isEmpty() || (!energyBatterySystem.getBatteries().isEmpty() && energyBatterySystem.getBatteries().get(energyBatterySystem.getBatteries().size()-1).getBatteryQuantity() != null)) {
					energyBatterySystem.getBatteries().add(new ProjectBatteryDto());
				}
				if(permitEnergyBatterySystem.getIdAts() != null) {
					energyBatterySystem.setIdAts(getComponentsId.getATS(permitEnergyBatterySystem.getIdAts(), projectOwnerId));
				}
				if(permitEnergyBatterySystem.getIdGenerator() != null) {
					energyBatterySystem.setIdGenerator(getComponentsId.getGenerator(permitEnergyBatterySystem.getIdGenerator(), projectOwnerId));
				}
				if(permitEnergyBatterySystem.getIdAcDisconnect() != null) {
					energyBatterySystem.setIdAcDisconnect(getComponentsId.getACDisconnectId(permitEnergyBatterySystem.getIdAcDisconnect(), projectOwnerId));
				}
				if(permitEnergyBatterySystem.getIdDcDisconnect() != null) {
					energyBatterySystem.setIdDcDisconnect(getComponentsId.getIdOfDcCombiner(permitEnergyBatterySystem.getIdDcDisconnect(), "", projectOwnerId));
				}
				//A.B REV CR-3919-MOD-005
				if(permitEnergyBatterySystem.getIdSecondAts() != null) {
					energyBatterySystem.setIdSecondAts(getComponentsId.getATS(permitEnergyBatterySystem.getIdSecondAts(), projectOwnerId));
				}
				if(permitEnergyBatterySystem.getIdSecondAcDisconnect() != null) {
					energyBatterySystem.setIdSecondAcDisconnect(getComponentsId.getACDisconnectId(permitEnergyBatterySystem.getIdSecondAcDisconnect(), projectOwnerId));
				}
			}
			
			/***************** Get Project Notes For ADV Team *********************/

			ProjectNotesDto projectNotesDto = projectNotesRepo.findByProject(permitEntity.getId());

			/***************** Set Project Edit Access *********************/
			PermitEditStatusModel editStatus = new PermitEditStatusModel();
			if(idUser != null && Boolean.FALSE.equals(isDrafter) && Boolean.FALSE.equals(modeView)) {
				if (Boolean.TRUE.equals(permitEntity.getOpened()) && permitEntity.getWebSocketSession() != null) {
					editStatus.setOpened(true);
					editStatus.setHasEditAccess(false);
					historicActivity.recordActivity(idUser, ip, timeZone,
							"Start Edit Project Read Only;" + idProject + ";Edit success", true, numTab, sessionId,
							openDate);
				} else {
					AuthentificationEntity openBy = userRepo.findById(idUser)
							.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + idUser));
					permitEntity.setOpened(true);
					permitEntity.setOpenedBy(openBy);
					permitEntity.setHasCloseRequest(false);
					permitRepo.save(permitEntity);
					editStatus.setOpened(true);
					editStatus.setHasEditAccess(true);
					historicActivity.recordActivity(idUser, ip, timeZone,
							"Start Edit Project;" + idProject + ";Edit success", true, numTab, sessionId, openDate);
				}

			}
			
		
			/***************** Get Project Drafts *********************/
			List<String> projectDrafts = new ArrayList<String>();
			File folder = new File(pathRepo.findFilePath() + "exportSave/" + permitEntity.getId());
			if (folder.exists()) {
				File[] listOfFiles = folder.listFiles();
				Arrays.sort(listOfFiles, new Comparator<File>() {
					public int compare(File f1, File f2) {
						return Long.compare(f1.lastModified(), f2.lastModified());
					}
				});
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						if(listOfFiles[i].getName().split("-").length == 5) {
							projectDrafts.add(
									listOfFiles[i].getName().split("-")[1] + " - " + listOfFiles[i].getName().split("-")[2]
											+ ':' + listOfFiles[i].getName().split("-")[3]);
						}else {
							String userName = userRepo.getUserName(Long.valueOf(listOfFiles[i].getName().split("-")[1]));
							projectDrafts.add(
									listOfFiles[i].getName().split("-")[0]+ " - " +listOfFiles[i].getName().split("-")[2] + " - " + listOfFiles[i].getName().split("-")[3]
											+ ':' + listOfFiles[i].getName().split("-")[4]+ " - " + userName);
						}
						
					}
				}
			}

			/***************** Get Project System Attributes *********************/
			SystemAttributesModel systemAttributesModel = getProjectSystemAttributes(permitArraysEntityResult, permitProjectSiteInfoEntityResult, permitConduitConductorSectionResult);

			/***************** Get Project Custom Uploads *********************/
			List<UserCutomUpload> userCustomUploads = cutomUploadRepo.findByUserId(user); 
			List<CustomUpload> customUpload = new ArrayList<>();
			for (UserCutomUpload u : userCustomUploads) {
				ProjectCustomFiles uploads = customFilesRepo.findByProjectIdAndCustomUploadId(permitEntity.getId(), u.getId());
				CustomUpload c = new CustomUpload(u.getId(), u.getTitle(),new ArrayList<>(), null);
				if(uploads != null) {
					c = new CustomUpload(u.getId(), u.getTitle(),uploads.getFiles(), uploads.getComment());
				}
				customUpload.add(c);
			}
			
			/***************** Get ESS Config Nodes & Connectors *********************/
			getPermitByIdResult.setEssNodesModel(essConfiguration.getNodes(idProject));
			getPermitByIdResult.setEssConnectorsModel(essConfiguration.getConnectors(idProject));
			
			/***************** end *********************/
			getPermitByIdResult.setPermitAdditionalInfoEntity(permitAdditionalInfoEntityResult);
			getPermitByIdResult.setPermitArraysEntity(permitArraysEntityResult);
			getPermitByIdResult.setPermitCompanyInfoEntityResult(permitCompanyInfoEntityResult);
			getPermitByIdResult.setPermitHomeSiteEntityResult(permitHomeSiteEntityResult);
			getPermitByIdResult.setPermitProjectSiteInfoEntityTwo(permitProjectSiteInfoEntityResult);
			getPermitByIdResult.setPermtiWeatherEntityResult(permtiWeatherEntityResult);
			getPermitByIdResult.setPermitEntity(permitEntity);
			getPermitByIdResult.setPermitAdvEntityResult(permitAdvEntityResult);
			getPermitByIdResult.setPermitEngineerEntityResult(permitEngineerEntityResult);
			getPermitByIdResult.setPermitConduitConductorSection(permitConduitConductorSectionResult);
			getPermitByIdResult.setPermitDrafterData(permitDrafterDataResult);
			getPermitByIdResult.setLayoutSketch(permitLayoutSketchResult);
			getPermitByIdResult.setPermitEditStatusModel(editStatus);
			getPermitByIdResult.setProjectDrafts(projectDrafts);
			getPermitByIdResult.setSystemAttributes(systemAttributesModel);
			getPermitByIdResult.setUserSettingEntity(userSettingEntity);
			getPermitByIdResult.setNotAllowedRackingNotes(projectNotesModel); 
			getPermitByIdResult.setOcpdMainPanel(ocpdMainPanelModel);
			getPermitByIdResult.setPermitEnergyBatterySystem(energyBatterySystem);
			getPermitByIdResult.setProjectNotes(projectNotesDto);
			getPermitByIdResult.setArrayLayout(getSketchByPermit(idProject));
			getPermitByIdResult.setCustomUpload(customUpload);
			return getPermitByIdResult;

		} catch (Exception e) {
			e.printStackTrace();
			return getPermitByIdResult;
		}

	}

	public List<PermitSketchResults> getSketchByPermit(Long idPermit) {
		
		List<PermitSketchResults> sketchPermit = new ArrayList<>();
		try {
			sketchPermit = permitSketchRepo.getPermitSketchResults(idPermit);
			if (sketchPermit != null && !sketchPermit.isEmpty()) {
				sketchPermit.get(0).setHaveSkecth(true);
				for (Iterator<PermitSketchResults> iter = sketchPermit.iterator(); iter.hasNext();) {
					PermitSketchResults p = iter.next();
					if (!checkValueTypesService.NotEquals(p.getModuleQty(), "")) {
						iter.remove();
					}
				}
			} else {
				sketchPermit = new ArrayList<>();
				sketchPermit.add(new PermitSketchResults(false));
			}
		} catch (Exception e) {
			e.printStackTrace();
			sketchPermit = new ArrayList<>();
			sketchPermit.add(new PermitSketchResults(false));
		}
		return sketchPermit;

	}
	
	private SystemAttributesModel getProjectSystemAttributes(PermitArrayEntityResultSecond permitArrays, PermitProjectSiteInfoEntityTwo permitProjectSiteInfoEntityResult, PermitConduitConductorSectionEntitieResult permitConduitConductor) {
		SystemAttributesModel systemAttributesModel = new SystemAttributesModel();
		try {
			
			// A.B Basic Type Of System
			systemAttributesModel
					.setRoofMounted(checkValueTypesService.Equals(permitArrays.getRoofMounted(), true));
			systemAttributesModel.setRoofMountedByLogic(
					checkValueTypesService.Equals(permitArrays.getCarportMounted(), true)
							&& checkValueTypesService.Equals(permitArrays.getRoofOrOpenFrame(),
									"The carport/patio cover will include roofing material under the modules"));
			systemAttributesModel
					.setGroundMounted(checkValueTypesService.Equals(permitArrays.getGroundMounted(), true));
			systemAttributesModel.setGroundMountedByLogic(
					checkValueTypesService.Equals(permitArrays.getCarportMounted(), true)
							&& checkValueTypesService.Equals(permitArrays.getRoofOrOpenFrame(),
									"The carport/patio cover will be open frame construction"));
			systemAttributesModel.setCarportMounted(
					checkValueTypesService.Equals(permitArrays.getCarportMounted(), true));
			
			systemAttributesModel.setPatioMounted(
					checkValueTypesService.Equals(permitArrays.getPatioMounted(), true));

			// A.B Inverter Technologies
			systemAttributesModel.setMicroInverterSystem(
					getProjectDetailsUtils.isMicroOrAcModuleProject(permitArrays.getDeviceToIncorporate()));
			systemAttributesModel.setStringInverterSystem(getProjectDetailsUtils
					.isStringOrOptimizerProject(permitArrays.getDeviceToIncorporate()));

			// A.B Micro Inverter System with More than Branch Circuit
			systemAttributesModel
					.setTwoACCircuitSystem(systemAttributesModel.getMicroInverterSystem() && checkValueTypesService
							.isStringNotEmpty(permitArrays.getNumberModulesACCircuitTwo()));
			systemAttributesModel.setTwoMicroInverterSystem(systemAttributesModel.getMicroInverterSystem()
					&& checkValueTypesService.isStringNotEmpty(permitArrays.getOcpdTwo()));
			systemAttributesModel.setThreeMicroInverterSystem(systemAttributesModel.getMicroInverterSystem()
					&& checkValueTypesService.isStringNotEmpty(permitArrays.getOcpdThree()));
			systemAttributesModel.setFourMicroInverterSystem(systemAttributesModel.getMicroInverterSystem()
					&& checkValueTypesService.isStringNotEmpty(permitArrays.getOcpdFour()));
			systemAttributesModel.setFiveMicroInverterSystem(systemAttributesModel.getMicroInverterSystem()
					&& checkValueTypesService.isStringNotEmpty(permitArrays.getOcpdFive()));

			// A.B String Inverter System with More than one Inverter
			systemAttributesModel.setTwoStringInverterSystem(systemAttributesModel.getStringInverterSystem()
					&& permitArrays.getSecondInverterModel() != null);
			systemAttributesModel.setThreeStringInverterSystem(systemAttributesModel.getStringInverterSystem()
					&& permitArrays.getThirdInverterModel() != null);
			systemAttributesModel.setFourStringInverterSystem(systemAttributesModel.getStringInverterSystem()
					&& permitArrays.getFourthInverterModel() != null);
			systemAttributesModel.setFiveStringInverterSystem(systemAttributesModel.getStringInverterSystem()
					&& permitArrays.getFifthInverterModel() != null);

			// A.B Sub Panel with More than Branch Circuit Or More than one Inverter
			systemAttributesModel.setSubPanelWithMultipleSystem(checkValueTypesService
					.Equals(permitProjectSiteInfoEntityResult.getSolarLocation(), "Back-fed Breaker")
					&& (systemAttributesModel.getTwoStringInverterSystem()
							|| systemAttributesModel.getTwoMicroInverterSystem())
					&& (checkValueTypesService.Equals(permitProjectSiteInfoEntityResult.getCombiningACCircuits(),
							"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
							|| checkValueTypesService.Equals(permitProjectSiteInfoEntityResult.getCombiningACCircuits(),
									"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker")));

			// A.B Sub Panel Breaker 120% rule
			if (Boolean.TRUE.equals(systemAttributesModel.getStringInverterSystem())) {
				systemAttributesModel.setSumInvertersIacmax(getInverterOCPD.getOcpdNumber(
						permitArrays.getInverterModel(), permitArrays.getSecondInverterModel(),
						permitArrays.getThirdInverterModel(),
						permitArrays.getFourthInverterModel(),
						permitArrays.getFifthInverterModel()));
			} else {
				systemAttributesModel.setSumInvertersIacmax(0.0);
			}

			if (Boolean.TRUE.equals(systemAttributesModel.getMicroInverterSystem())) {

				Inverters microInverterInfo = inverterLibrarySerivce.getInverterEntity(permitArrays.getMicroInverter());
				if (microInverterInfo != null) {

					double iacmax = getInverterOCPD.getOcpdNumberMicroInverterS(microInverterInfo.getId());
					systemAttributesModel.setSumMicroIacmaxNumber(
							projectNumberOfMicroInverter.getNumberMicroInverter(permitArrays, microInverterInfo)
									* iacmax);
				} else {
					systemAttributesModel.setSumMicroIacmaxNumber(0.0);
				}

				if (permitArrays.getPvModuleModEl() != null) {
					Double iacmax = getModuleOCPD.getModuleOcpdNumber(permitArrays.getPvModuleModEl());
					Integer moduleQty = permitConduitConductor.getQtySegmentOne() != null
							? permitConduitConductor.getQtySegmentOne()
							: 0;
					systemAttributesModel.setSumModulesIacmax(iacmax * moduleQty);
				} else {
					systemAttributesModel.setSumModulesIacmax(0.0);
				}

			} else {
				systemAttributesModel.setSumMicroIacmaxNumber(0.0);
				systemAttributesModel.setSumModulesIacmax(0.0);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return systemAttributesModel;
	}


	public List<String> getNoteSketchFilesOne(Long permitId) {
		return noteSketchFilesRepo.getNoteSketchFilesOne(permitId);
	}
	
	public List<String> getNoteSketchFilesTwo(Long permitId) {
		return noteSketchFilesRepo.getNoteSketchFilesTwo(permitId);
	}
	
	
}
