package com.PlayGroundAdv.Solar.service.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.internet.AddressException;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.ACCombinerFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.ACDisconnectFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.BatteryFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ConduitConductorCircuitEntity;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.DcCombinerorDiscFavoriteEntity;
import com.PlayGroundAdv.Solar.entity.DrafterInformationEntity;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.FlashingFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.InverterLibraryEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.JunctionBoxFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeterFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.ModuleLibraryEntity;
import com.PlayGroundAdv.Solar.entity.NoteSketchFiles;
import com.PlayGroundAdv.Solar.entity.PermitAdditionalInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitAdvEntity;
import com.PlayGroundAdv.Solar.entity.PermitArraysEntity;
import com.PlayGroundAdv.Solar.entity.PermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitEngineerEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.PermitWeatherEntity;
import com.PlayGroundAdv.Solar.entity.ProjectsTrackerEntity;
import com.PlayGroundAdv.Solar.entity.ProposedSubPanelFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.RFIQuestionEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RailRackingFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.RoofTypeandAttachmentEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyEntity;
import com.PlayGroundAdv.Solar.entity.TiltLegsFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.libraries.ATS;
import com.PlayGroundAdv.Solar.entity.libraries.ATSFavorite;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerFavoritEntity;
import com.PlayGroundAdv.Solar.entity.libraries.Generator;
import com.PlayGroundAdv.Solar.entity.libraries.GeneratorFavorite;
import com.PlayGroundAdv.Solar.entity.projects.ProjectCustomFiles;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.DrafterInformationModel;
import com.PlayGroundAdv.Solar.model.ElectricalUtilityModel;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.model.GetPermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.model.LayoutSketchArraysModel;
import com.PlayGroundAdv.Solar.model.NotAllowedRackingNotesModel;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermitCompanyInfoEntityResultPrime;
import com.PlayGroundAdv.Solar.model.PermitConduitConductorSectionEntitieResult;
import com.PlayGroundAdv.Solar.model.PermitDrafterDataResult;
import com.PlayGroundAdv.Solar.model.PermitEditStatusModel;
import com.PlayGroundAdv.Solar.model.PermitEngineerEntityResult;
import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
import com.PlayGroundAdv.Solar.model.PermitLayoutSketchResult;
import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
import com.PlayGroundAdv.Solar.model.PermitResponsePrime;
import com.PlayGroundAdv.Solar.model.PermitResult;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.model.ProjectBatteryDto;
import com.PlayGroundAdv.Solar.model.ProjectContactsEmailModel;
import com.PlayGroundAdv.Solar.model.ProjectEmailModel;
import com.PlayGroundAdv.Solar.model.RFIQuestionEntityModel;
import com.PlayGroundAdv.Solar.model.SearchProject;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.InvertersModels;
import com.PlayGroundAdv.Solar.model.project.CustomUpload;
import com.PlayGroundAdv.Solar.repositories.AdditionalInfoFilesRepository;
import com.PlayGroundAdv.Solar.repositories.ConduitConductorCircuitRepository;
import com.PlayGroundAdv.Solar.repositories.DrafterInformationRepository;
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
import com.PlayGroundAdv.Solar.repositories.ProjectBatteryRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectsTrackerRepository;
import com.PlayGroundAdv.Solar.repositories.RFIQuestionRepository;
import com.PlayGroundAdv.Solar.repositories.RoofTypeandAttachmentRepository;
import com.PlayGroundAdv.Solar.repositories.SiteSurveyRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ATSFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ATSRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcComDiscoFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcOptimizerFavoritRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ElectricalUtilityRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.GeneratorFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.GeneratorRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InvertersFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.JunctionBoxFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ProjectCustomFilesRepository;
import com.PlayGroundAdv.Solar.repositories.project.ProjectFilesRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PermitPlansetUploadRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.user_management.GoogleDriveFolder;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class PermitService {

	final NotificationEntityService notificationEntityService;
	final MailingService mailingService;
	final HistoryActivityService historicActivity;
	final CheckValueTypesService checkValueTypesService;
	final GetProjectDetailsUtils getProjectDetailsUtils;
	final AuthenticationRepository userRepo;
	final ContractorInformationRepository userContactInfoRepo;
	final UserSettingRepository userSettingRepo;
	final RailRackingRepository railRackingRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepos;
	final PermitHomeSiteInfoRepository permitHomeSiteInfoRepo;
	final RailRackingFavoriteRepository railRackingFavoritesRepo;
	final DcCombinerDiscoRepository dCCombinerDisconnectEntityRepo;
	final DcComDiscoFavoriteRepository dcCombinerorDiscFavoriteEntityRepo;
	final InverterRepository inverterRepo;
	final ModuleRepository moduleRepo;
	final InvertersFavoritesRepository inverterFavRepo;
	final PermitArraysRepository projectArraysRepo;
	final ConvertersRepository convertersRepo;
	final DcOptimizerFavoritRepository convertersFavRepo;
	final AcCombinerSLCRepository acCombinerSLCRepo;
	final AcCombinerSLCsFavoritesRepository acCombinerSLCsFavoritesRepo;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	final PermitAdvInputsRepository permitAdvInputsRepo;
	final PermitRepository permitRepo;
	final SiteSurveyRepository siteSurveyRepo;
	final PermitSketchRepository permitSketchRepo;
	final PermitPlansetUploadRepository permitPlansetUploadRepo;
	final PermitCompanyInfoRepository permitCompanyInfoRepo;
	final ProjectsTrackerRepository projectsTrackerRepo;
	final PermitConduitConductorSectionRepository permitConduitConductorRepo;
	final ConduitConductorCircuitRepository conduitConductorCircuitRepo;
	final PermitAdditionalInfoRepository permitAdditionalInfoRepo;
	final PermitLayoutRepository permitLayoutRepo;
	final PermitWeatherRepository permitWeatherRepo;
	final PermitEngineerRepository permitEngineerRepo;
	final NoteSketchFilesRepository noteSketchFilesRepo;
	final DrafterInformationRepository drafterInformationRepo;
	final RFIQuestionRepository rfiQuestionRepo;
	final RoofAttachmentsRepository roofAttachmentsRepo;
	final RoofAttachmentFavRepository roofAttachmentsFavRepo;
	final ElectricalUtilityRepository electricalUtilityRepo;
	final ModuleFavoritesRepository moduleFavRepo;
	final ACDisconnectFavRepository acDisconnectFavRepo;
	final ACDisconnectRepository acDisconnectRepo;
	final JunctionBoxFavRepository jbFavRepo;
	final FlashingRepository flashingRepo;
	final FlashingsFavoritesRepository flashingsFavoritesRepo;
	final LeasePPAMeterFavoritesRepository leasePPAMeterFavoritesRepo;
	final LeasePPAMeterRepository leasePPAMeterRepo;
	final BatteryFavRepository batteryFavRepo;
	final BatteryRepository batteryRepo;
	final TiltLegsFavoritesRepository tiltLegsFavRepo;
	final TiltLegsRepository tiltLegsRepo;
	final ProposedSubPanelRepository proposedSubPanelRepo;
	final ProposedSubPanelFavoritesRepository proposedSubPanelFavRepo;
	final AdditionalInfoFilesRepository additionalInfoFilesRepo;
	final RoofTypeandAttachmentRepository roofTypeandAttachmentRepo;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;
	final PathRepository pathRepo;
	final ProjectNotAllowedRackingNote notAllowedRackingService;
	final GetProjectByIdService getProjectById;
	final ATSRepository atsRepo;
	final GeneratorRepository generatorRepo;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	final ProjectBatteryRepository projectBatteryRepo;
	final ProjectFilesRepository projectFileRepo;
	final GoogleDriveFolder googleDriveFolder;
	final ATSFavoriteRepository atsFavRepo;
	final GeneratorFavoriteRepository generatorFavRepo;
	final ProjectCustomFilesRepository customFilesRepo;
	final ESSConfiguration essConfiguration;
	public PermitService(NotificationEntityService notificationEntityService, MailingService mailingService,
			HistoryActivityService historicActivity, CheckValueTypesService checkValueTypesService,
			GetProjectDetailsUtils getProjectDetailsUtils, AuthenticationRepository userRepo,
			ContractorInformationRepository userContactInfoRepo, UserSettingRepository userSettingRepo,
			RailRackingRepository railRackingRepo, PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepos,
			PermitHomeSiteInfoRepository permitHomeSiteInfoRepo, RailRackingFavoriteRepository railRackingFavoritesRepo,
			DcCombinerDiscoRepository dCCombinerDisconnectEntityRepo,
			DcComDiscoFavoriteRepository dcCombinerorDiscFavoriteEntityRepo, InverterRepository inverterRepo,
			ModuleRepository moduleRepo, InvertersFavoritesRepository inverterFavRepo,
			PermitArraysRepository projectArraysRepo, ConvertersRepository convertersRepo,
			DcOptimizerFavoritRepository convertersFavRepo, AcCombinerSLCRepository acCombinerSLCRepo,
			AcCombinerSLCsFavoritesRepository acCombinerSLCsFavoritesRepo,
			PermitDrafterDataRepository permitDrafterDataRepo, PermitAdvInputsRepository permitAdvInputsRepo,
			PermitRepository permitRepo, SiteSurveyRepository siteSurveyRepo, PermitSketchRepository permitSketchRepo,
			PermitPlansetUploadRepository permitPlansetUploadRepo, PermitCompanyInfoRepository permitCompanyInfoRepo,
			ProjectsTrackerRepository projectsTrackerRepo,
			PermitConduitConductorSectionRepository permitConduitConductorRepo,
			ConduitConductorCircuitRepository conduitConductorCircuitRepo,
			PermitAdditionalInfoRepository permitAdditionalInfoRepo, PermitLayoutRepository permitLayoutRepo,
			PermitWeatherRepository permitWeatherRepo, PermitEngineerRepository permitEngineerRepo,
			NoteSketchFilesRepository noteSketchFilesRepo, DrafterInformationRepository drafterInformationRepo,
			RFIQuestionRepository rfiQuestionRepo, RoofAttachmentsRepository roofAttachmentsRepo,
			RoofAttachmentFavRepository roofAttachmentsFavRepo, ElectricalUtilityRepository electricalUtilityRepo,
			ModuleFavoritesRepository moduleFavRepo, ACDisconnectFavRepository acDisconnectFavRepo,
			ACDisconnectRepository acDisconnectRepo, JunctionBoxFavRepository jbFavRepo,
			FlashingRepository flashingRepo, FlashingsFavoritesRepository flashingsFavoritesRepo,
			LeasePPAMeterFavoritesRepository leasePPAMeterFavoritesRepo, LeasePPAMeterRepository leasePPAMeterRepo,
			BatteryFavRepository batteryFavRepo, BatteryRepository batteryRepo,
			TiltLegsFavoritesRepository tiltLegsFavRepo, TiltLegsRepository tiltLegsRepo,
			ProposedSubPanelRepository proposedSubPanelRepo,
			ProposedSubPanelFavoritesRepository proposedSubPanelFavRepo,
			AdditionalInfoFilesRepository additionalInfoFilesRepo,
			RoofTypeandAttachmentRepository roofTypeandAttachmentRepo, RoofMaterialTypeRepository roofMaterialTypeRepo,
			PathRepository pathRepo, ProjectNotAllowedRackingNote notAllowedRackingService, GetProjectByIdService getProjectById, 
			ATSRepository atsRepo, GeneratorRepository generatorRepo,PermitEnergyBatterySystemRepository energyBatterySystemRepo,
			ProjectBatteryRepository projectBatteryRepo, ProjectFilesRepository projectFileRepo,
			GoogleDriveFolder googleDriveFolder, ATSFavoriteRepository atsFavRepo, GeneratorFavoriteRepository generatorFavRepo,
			ProjectCustomFilesRepository customFilesRepo, ESSConfiguration essConfiguration) {
		super();
		this.notificationEntityService = notificationEntityService;
		this.mailingService = mailingService;
		this.historicActivity = historicActivity;
		this.checkValueTypesService = checkValueTypesService;
		this.getProjectDetailsUtils = getProjectDetailsUtils;
		this.userRepo = userRepo;
		this.userContactInfoRepo = userContactInfoRepo;
		this.userSettingRepo = userSettingRepo;
		this.railRackingRepo = railRackingRepo;
		this.permitProjectSiteInfoEntityRepos = permitProjectSiteInfoEntityRepos;
		this.permitHomeSiteInfoRepo = permitHomeSiteInfoRepo;
		this.railRackingFavoritesRepo = railRackingFavoritesRepo;
		this.dCCombinerDisconnectEntityRepo = dCCombinerDisconnectEntityRepo;
		this.dcCombinerorDiscFavoriteEntityRepo = dcCombinerorDiscFavoriteEntityRepo;
		this.inverterRepo = inverterRepo;
		this.moduleRepo = moduleRepo;
		this.inverterFavRepo = inverterFavRepo;
		this.projectArraysRepo = projectArraysRepo;
		this.convertersRepo = convertersRepo;
		this.convertersFavRepo = convertersFavRepo;
		this.acCombinerSLCRepo = acCombinerSLCRepo;
		this.acCombinerSLCsFavoritesRepo = acCombinerSLCsFavoritesRepo;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
		this.permitAdvInputsRepo = permitAdvInputsRepo;
		this.permitRepo = permitRepo;
		this.siteSurveyRepo = siteSurveyRepo;
		this.permitSketchRepo = permitSketchRepo;
		this.permitPlansetUploadRepo = permitPlansetUploadRepo;
		this.permitCompanyInfoRepo = permitCompanyInfoRepo;
		this.projectsTrackerRepo = projectsTrackerRepo;
		this.permitConduitConductorRepo = permitConduitConductorRepo;
		this.conduitConductorCircuitRepo = conduitConductorCircuitRepo;
		this.permitAdditionalInfoRepo = permitAdditionalInfoRepo;
		this.permitLayoutRepo = permitLayoutRepo;
		this.permitWeatherRepo = permitWeatherRepo;
		this.permitEngineerRepo = permitEngineerRepo;
		this.noteSketchFilesRepo = noteSketchFilesRepo;
		this.drafterInformationRepo = drafterInformationRepo;
		this.rfiQuestionRepo = rfiQuestionRepo;
		this.roofAttachmentsRepo = roofAttachmentsRepo;
		this.roofAttachmentsFavRepo = roofAttachmentsFavRepo;
		this.electricalUtilityRepo = electricalUtilityRepo;
		this.moduleFavRepo = moduleFavRepo;
		this.acDisconnectFavRepo = acDisconnectFavRepo;
		this.acDisconnectRepo = acDisconnectRepo;
		this.jbFavRepo = jbFavRepo;
		this.flashingRepo = flashingRepo;
		this.flashingsFavoritesRepo = flashingsFavoritesRepo;
		this.leasePPAMeterFavoritesRepo = leasePPAMeterFavoritesRepo;
		this.leasePPAMeterRepo = leasePPAMeterRepo;
		this.batteryFavRepo = batteryFavRepo;
		this.batteryRepo = batteryRepo;
		this.tiltLegsFavRepo = tiltLegsFavRepo;
		this.tiltLegsRepo = tiltLegsRepo;
		this.proposedSubPanelRepo = proposedSubPanelRepo;
		this.proposedSubPanelFavRepo = proposedSubPanelFavRepo;
		this.additionalInfoFilesRepo = additionalInfoFilesRepo;
		this.roofTypeandAttachmentRepo = roofTypeandAttachmentRepo;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
		this.pathRepo = pathRepo;
		this.notAllowedRackingService = notAllowedRackingService;
		this.getProjectById = getProjectById;
		this.atsRepo = atsRepo;
		this.generatorRepo = generatorRepo;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
		this.projectBatteryRepo = projectBatteryRepo;
		this.projectFileRepo = projectFileRepo;
		this.googleDriveFolder = googleDriveFolder;
		this.atsFavRepo = atsFavRepo;
		this.generatorFavRepo = generatorFavRepo;
		this.customFilesRepo = customFilesRepo;
		this.essConfiguration = essConfiguration;
	}

	// 02-27-201-: M.A : Save project technical problem
	String projectName = "";
	String projectOwner = "";
	String ownerMail = "";
	String projectLastname = "";
	static final String NOT_FOUND = "Entity not found for this id";
	public Boolean checkIfProjectIsSubmitted(Long idPermit) {

		try {
			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			return permit.isSubmitted() && (checkValueTypesService.Equals(permit.getStatus(), "Submitted")
					|| checkValueTypesService.Equals(permit.getStatus(), "Canceled")
					|| checkValueTypesService.Equals(permit.getStatus(), "Delivered"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public String saveProject(GetPermitByIdResult permitModel, Long idUser, String timeZone, String ipUser,
			String numTab, String sessionId, String openDate) throws ResourceNotFoundException {
		try {

			AuthentificationEntity user = userRepo.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));

			// 02-28-2019: M.A : Saving technical problem
			projectOwner = user.getFirstName() + " " + user.getLastName();
			ownerMail = user.getEmail();
			
			String projectCommercialName = checkValueTypesService.trim(permitModel.getPermitEntity().getProjectName()) ;
			String homeOwnName = checkValueTypesService.trim(permitModel.getPermitEntity().getHomeOwnName());
			String homeOwnLastName = checkValueTypesService.trim(permitModel.getPermitEntity().getHomeOwnLastName());
			
			String projectTitle = projectCommercialName != null ? projectCommercialName : homeOwnLastName + ", " + homeOwnName;

			TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
			Calendar calSaveP = Calendar.getInstance(); // creates calendar

			try {
				ProjectsTrackerEntity tracker = projectsTrackerRepo
						.findByPermitId(permitModel.getPermitEntity().getId());
				if (tracker != null) {

					if (user.getRoleEntity().getId() == 2) {
						if (tracker.getProjectEditStarted() == null) {
							tracker.setProjectEditStarted(calSaveP.getTime());
						} else {
							tracker.setProjectEditCompleted(calSaveP.getTime());
						}

					} else if (user.getRoleEntity().getId() == 4) {
						if (tracker.getDrafterDataEditStarted() == null) {
							tracker.setDrafterDataEditStarted(calSaveP.getTime());
						} else {
							tracker.setDrafterDataEditCompleted(calSaveP.getTime());
						}
						tracker.setDrafter(user.getFirstName() + " " + user.getLastName());
					} else if (user.getRoleEntity().getId() == 3) {
						if (tracker.getAdvInputsEditStarted() == null) {
							tracker.setAdvInputsEditStarted(calSaveP.getTime());
						} else {
							tracker.setAdvInputsEditCompleted(calSaveP.getTime());
						}
						tracker.setAdvTeamMember(user.getFirstName() + " " + user.getLastName());
					}
					projectsTrackerRepo.save(tracker);

				} else {

					ProjectsTrackerEntity tracker2 = new ProjectsTrackerEntity();
					PermitEntity permit = permitRepo.findById(permitModel.getPermitEntity().getId()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					tracker2.setPermit(permit);
					if (user.getRoleEntity().getId() == 2) {
						if (tracker2.getProjectEditStarted() == null) {
							tracker2.setProjectEditStarted(calSaveP.getTime());
						} else {
							tracker2.setProjectEditCompleted(calSaveP.getTime());
						}

					} else if (user.getRoleEntity().getId() == 4) {
						if (tracker2.getDrafterDataEditStarted() == null) {
							tracker2.setDrafterDataEditStarted(calSaveP.getTime());
						} else {
							tracker2.setDrafterDataEditCompleted(calSaveP.getTime());
						}
						tracker2.setDrafter(user.getFirstName() + " " + user.getLastName());
					} else if (user.getRoleEntity().getId() == 3) {
						if (tracker2.getAdvInputsEditStarted() == null) {
							tracker2.setAdvInputsEditStarted(calSaveP.getTime());
						} else {
							tracker2.setAdvInputsEditCompleted(calSaveP.getTime());
						}
						tracker2.setAdvTeamMember(user.getFirstName() + " " + user.getLastName());
					}
					projectsTrackerRepo.save(tracker2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Save Homeowner tab

			PermitHomeSiteInfoEntity permitHomeSiteInfoEntity = permitHomeSiteInfoRepo
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			PermitHomeSiteEntityResult editPermitHomeSiteRequest = permitModel.getPermitHomeSiteEntityResult();
			if (editPermitHomeSiteRequest != null) {
				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getUtilityCompanyName(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getUtilityCompanyName(), "")) {
					permitHomeSiteInfoEntity.setUtilityCompanyName(editPermitHomeSiteRequest.getUtilityCompanyName());
				} else
					permitHomeSiteInfoEntity.setUtilityCompanyName(null);

				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getAddressLine2(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getAddressLine2(), "")) {
					permitHomeSiteInfoEntity.setAddressLine2(editPermitHomeSiteRequest.getAddressLine2());
				} else
					permitHomeSiteInfoEntity.setAddressLine2(null);

				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryAddress(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryAddress(), "")) {
					permitHomeSiteInfoEntity.setSecondaryAddress(editPermitHomeSiteRequest.getSecondaryAddress());
				} else {
					permitHomeSiteInfoEntity.setSecondaryAddress(" ");
				}
				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryAddressLine2(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryAddressLine2(), "")) {
					permitHomeSiteInfoEntity
							.setSecondaryAddressLine2(editPermitHomeSiteRequest.getSecondaryAddressLine2());
				} else
					permitHomeSiteInfoEntity.setSecondaryAddressLine2(null);

				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryCity(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryCity(), "")) {
					permitHomeSiteInfoEntity.setSecondaryCity(editPermitHomeSiteRequest.getSecondaryCity());
				} else
					permitHomeSiteInfoEntity.setSecondaryCity(null);

				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryState(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryState(), "")) {
					permitHomeSiteInfoEntity.setSecondaryState(editPermitHomeSiteRequest.getSecondaryState());
				} else
					permitHomeSiteInfoEntity.setSecondaryState(null);

				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryPostalCode(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSecondaryPostalCode(), "")) {
					permitHomeSiteInfoEntity.setSecondaryPostalCode(editPermitHomeSiteRequest.getSecondaryPostalCode());
				} else
					permitHomeSiteInfoEntity.setSecondaryPostalCode(null);

				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getEmailPhone(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getEmailPhone(), "")) {
					permitHomeSiteInfoEntity.setEmailPhone(editPermitHomeSiteRequest.getEmailPhone());
				} else
					permitHomeSiteInfoEntity.setEmailPhone(null);

				permitHomeSiteInfoEntity.setHomePhone(editPermitHomeSiteRequest.getHomePhone());
				permitHomeSiteInfoEntity.setCellPhone(editPermitHomeSiteRequest.getCellPhone());
				permitHomeSiteInfoEntity.setOtherPhone(editPermitHomeSiteRequest.getOtherPhone());

				permitHomeSiteInfoEntity.setFootageStructure(editPermitHomeSiteRequest.getFootageStructure());
				permitHomeSiteInfoEntity.setCityOther(editPermitHomeSiteRequest.getCityOther());
				permitHomeSiteInfoEntity.setCity(editPermitHomeSiteRequest.getCity());
				permitHomeSiteInfoEntity.setProjectJurisdiction(editPermitHomeSiteRequest.getProjectJurisdiction());
				permitHomeSiteInfoEntity.setProjectJurisOther(editPermitHomeSiteRequest.getProjectJurisOther());
				permitHomeSiteInfoEntity.setSecondaryCityOther(editPermitHomeSiteRequest.getSecondaryCityOther());

				/**************************************/
				permitHomeSiteInfoEntity.setSameMailing(editPermitHomeSiteRequest.getSameMailing());
				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getPropertyAPN(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getPropertyAPN(), "")) {
					permitHomeSiteInfoEntity.setPropertyAPN(editPermitHomeSiteRequest.getPropertyAPN());
				} else
					permitHomeSiteInfoEntity.setPropertyAPN(null);

				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSiteAddress(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getSiteAddress(), "")) {
					permitHomeSiteInfoEntity.setSiteAddress(editPermitHomeSiteRequest.getSiteAddress());
				} else
					permitHomeSiteInfoEntity.setSiteAddress(null);

				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getPostalCode(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getPostalCode(), "")) {
					permitHomeSiteInfoEntity.setPostalCode(editPermitHomeSiteRequest.getPostalCode());
				} else
					permitHomeSiteInfoEntity.setPostalCode(null);

				permitHomeSiteInfoEntity.setCity(editPermitHomeSiteRequest.getCity());
				permitHomeSiteInfoEntity.setState(editPermitHomeSiteRequest.getState());
				permitHomeSiteInfoEntity.setFormattedAddress(editPermitHomeSiteRequest.getFormattedAddress());
				permitHomeSiteInfoEntity.setLatitude(editPermitHomeSiteRequest.getLatitude());
				permitHomeSiteInfoEntity.setLongitude(editPermitHomeSiteRequest.getLongitude());

				/**************************************/
				/***********************************
				 * CR_003
				 ****************************************************/

				permitHomeSiteInfoEntity.setServiceVoltage(editPermitHomeSiteRequest.getServiceVoltage());
				permitHomeSiteInfoEntity.setServiceVoltageOther(editPermitHomeSiteRequest.getServiceVoltageOther());
				permitHomeSiteInfoEntity.setIfServiceVoltage(editPermitHomeSiteRequest.getIfServiceVoltage());
				permitHomeSiteInfoEntity
						.setRidgeBeamDepthAtArrays(editPermitHomeSiteRequest.getRidgeBeamDepthAtArrays());
				permitHomeSiteInfoEntity
						.setMaxHorizontalSpanAtArrays(editPermitHomeSiteRequest.getMaxHorizontalSpanAtArrays());
				permitHomeSiteInfoEntity
						.setMaxHorizontalSpanAtArraysHS(editPermitHomeSiteRequest.getMaxHorizontalSpanAtArraysHS());
				permitHomeSiteInfoEntity.setMaxHorizontalSpanAtArraysInches(
						editPermitHomeSiteRequest.getMaxHorizontalSpanAtArraysInches());
				permitHomeSiteInfoEntity.setMaxHorizontalSpanAtArraysHSInches(
						editPermitHomeSiteRequest.getMaxHorizontalSpanAtArraysHSInches());
				permitHomeSiteInfoEntity.setBuildingRiskOther(editPermitHomeSiteRequest.getBuildingRiskOther());
				permitHomeSiteInfoEntity.setStanchionMaxSpacing(editPermitHomeSiteRequest.getStanchionMaxSpacing());
				permitHomeSiteInfoEntity
						.setStanchionMaxSpacingOther(editPermitHomeSiteRequest.getStanchionMaxSpacingOther());
				permitHomeSiteInfoEntity
						.setRidgeBeamDepthAtArraysOther(editPermitHomeSiteRequest.getRidgeBeamDepthAtArraysOther());
				if (checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getUtilityCompanyNameOther(), "null")
						&& checkValueTypesService.NotEquals(editPermitHomeSiteRequest.getUtilityCompanyNameOther(),
								"")) {
					permitHomeSiteInfoEntity
							.setUtilityCompanyNameOther(editPermitHomeSiteRequest.getUtilityCompanyNameOther());
				} else
					permitHomeSiteInfoEntity.setUtilityCompanyNameOther(null);

				/***********************************
				 * END CR_003
				 *****************************************************/

				if (editPermitHomeSiteRequest.getResidenceBindingCategory() != null) {

					if (checkValueTypesService.Equals(editPermitHomeSiteRequest.getResidenceBindingCategory(),
							"Other")) {
						permitHomeSiteInfoEntity
								.setResidenceBindingCategory(editPermitHomeSiteRequest.getTextOtherExpo());
					} else {
						permitHomeSiteInfoEntity
								.setResidenceBindingCategory(editPermitHomeSiteRequest.getResidenceBindingCategory());
					}
				} else
					permitHomeSiteInfoEntity.setResidenceBindingCategory(null);

				permitHomeSiteInfoEntity.setTextOtherExpo(editPermitHomeSiteRequest.getTextOtherExpo());

				if (editPermitHomeSiteRequest.getConstructionType() != null) {

					permitHomeSiteInfoEntity.setConstructionType(editPermitHomeSiteRequest.getConstructionType());
					if (checkValueTypesService.Equals(editPermitHomeSiteRequest.getConstructionType(), "OtherConst")) {
						permitHomeSiteInfoEntity.setTextOtherConst(editPermitHomeSiteRequest.getTextOtherConst());
					} else
						permitHomeSiteInfoEntity.setTextOtherConst(null);

				} else
					permitHomeSiteInfoEntity.setConstructionType(null);

				if (editPermitHomeSiteRequest.getRoofRafter() != null) {

					if (checkValueTypesService.Equals(editPermitHomeSiteRequest.getRoofRafter(), "OtherRoof")) {
						permitHomeSiteInfoEntity.setRoofRafter(editPermitHomeSiteRequest.getTextOtherRoof());
					} else {
						permitHomeSiteInfoEntity.setRoofRafter(editPermitHomeSiteRequest.getRoofRafter());
					}

				} else
					permitHomeSiteInfoEntity.setRoofRafter(null);

				if (editPermitHomeSiteRequest.getBuildingRisk() != null) {
					permitHomeSiteInfoEntity.setBuildingRisk(editPermitHomeSiteRequest.getBuildingRisk());
					permitHomeSiteInfoEntity.setTextOtherBuild(editPermitHomeSiteRequest.getTextOtherBuild());
				} else
					permitHomeSiteInfoEntity.setBuildingRisk(null);

				permitHomeSiteInfoEntity.setBuildingOccupancy(editPermitHomeSiteRequest.getBuildingOccupancy());
				permitHomeSiteInfoEntity.setTextOtherBuildOccup(editPermitHomeSiteRequest.getTextOtherBuildOccup());

				permitHomeSiteInfoEntity.setHomePhone(editPermitHomeSiteRequest.getHomePhone());
				permitHomeSiteInfoEntity.setCellPhone(editPermitHomeSiteRequest.getCellPhone());
				permitHomeSiteInfoEntity.setOtherPhone(editPermitHomeSiteRequest.getOtherPhone());

				permitHomeSiteInfoEntity.setFootageStructure(editPermitHomeSiteRequest.getFootageStructure());
				permitHomeSiteInfoEntity.setCityOther(editPermitHomeSiteRequest.getCityOther());
				permitHomeSiteInfoEntity.setProjectJurisdiction(editPermitHomeSiteRequest.getProjectJurisdiction());
				permitHomeSiteInfoEntity.setProjectJurisOther(editPermitHomeSiteRequest.getProjectJurisOther());
				permitHomeSiteInfoEntity.setSecondaryCityOther(editPermitHomeSiteRequest.getSecondaryCityOther());
				permitHomeSiteInfoEntity.setSecroofRafterOther(editPermitHomeSiteRequest.getSecroofRafterOther());
				permitHomeSiteInfoEntity.setRoofRafterOther(editPermitHomeSiteRequest.getRoofRafterOther());
				permitHomeSiteInfoEntity.setMeterNumber(editPermitHomeSiteRequest.getMeterNumber());
				permitHomeSiteInfoEntity.setEsiidNumber(editPermitHomeSiteRequest.getEsiidNumber());
				permitHomeSiteInfoRepo.save(permitHomeSiteInfoEntity);
			}
			// *********** End Save Homeowner tab **********//

			// *********** Save Arrays tab ************//
			PermitArraysEntity permitArraysEntity = projectArraysRepo
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			PermitArrayEntityResultSecond editArraysEntityRequest = permitModel.getPermitArraysEntity();

			if (editArraysEntityRequest != null) {
				permitArraysEntity.setSystemType(editArraysEntityRequest.getSystemType());
				boolean p = checkValueTypesService.Equals(editArraysEntityRequest.getRequestQuote(), "true");
				// A.B 01-14 Get Inverter Entities
				if (checkValueTypesService.isLongPositive(editArraysEntityRequest.getInverterModel())) {
					Inverters firstInverter = inverterRepo.findById(editArraysEntityRequest.getInverterModel()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					permitArraysEntity.setFirstInverter(firstInverter);
				} else
					permitArraysEntity.setFirstInverter(null);

				if (checkValueTypesService.isLongPositive(editArraysEntityRequest.getSecondInverterModel())) {
					Inverters secondInverter = inverterRepo.findById(editArraysEntityRequest.getSecondInverterModel())
							.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					permitArraysEntity.setSecondInverter(secondInverter);
				} else
					permitArraysEntity.setSecondInverter(null);

				if (checkValueTypesService.isLongPositive(editArraysEntityRequest.getThirdInverterModel())) {
					Inverters thirdInverter = inverterRepo.findById(editArraysEntityRequest.getThirdInverterModel())
							.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					permitArraysEntity.setThirdInverter(thirdInverter);
				} else
					permitArraysEntity.setThirdInverter(null);

				if (checkValueTypesService.isLongPositive(editArraysEntityRequest.getFourthInverterModel())) {
					Inverters fourthInverter = inverterRepo.findById(editArraysEntityRequest.getFourthInverterModel())
							.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					permitArraysEntity.setFourthInverter(fourthInverter);
				} else
					permitArraysEntity.setFourthInverter(null);

				if (checkValueTypesService.isLongPositive(editArraysEntityRequest.getFifthInverterModel())) {
					Inverters fifthInverter = inverterRepo.findById(editArraysEntityRequest.getFifthInverterModel())
							.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					permitArraysEntity.setFifthInverter(fifthInverter);
				} else
					permitArraysEntity.setFifthInverter(null);

				// CI : 15/01/2020 : Update the Module model dropdowns value by the id
				if (checkValueTypesService.isLongPositive(editArraysEntityRequest.getPvModuleModEl())) {
					Cmodulev2 pvModule = moduleRepo.findById(editArraysEntityRequest.getPvModuleModEl()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					permitArraysEntity.setPvModule(pvModule);
				} else
					permitArraysEntity.setPvModule(null);

				permitArraysEntity.setRequestQuote(p);
				permitArraysEntity.setDeviceToIncorporate(editArraysEntityRequest.getDeviceToIncorporate());
				permitArraysEntity.setStringOne(editArraysEntityRequest.getStringOne());
				permitArraysEntity.setStringTwo(editArraysEntityRequest.getStringTwo());
				permitArraysEntity.setStringThree(editArraysEntityRequest.getStringThree());
				permitArraysEntity.setStringFour(editArraysEntityRequest.getStringFour());
				permitArraysEntity.setStringFive(editArraysEntityRequest.getStringFive());
				permitArraysEntity.setSecondInverterModel(null);
				permitArraysEntity.setSecondStringOne(editArraysEntityRequest.getSecondStringOne());
				permitArraysEntity.setSecondStringTwo(editArraysEntityRequest.getSecondStringTwo());
				permitArraysEntity.setSecondStringThree(editArraysEntityRequest.getSecondStringThree());
				permitArraysEntity.setSecondStringFour(editArraysEntityRequest.getSecondStringFour());
				permitArraysEntity.setSecondStringFive(editArraysEntityRequest.getSecondStringFive());
				permitArraysEntity.setQteOfBattery(editArraysEntityRequest.getQteOfBattery());

				permitArraysEntity.setGroundMounted(editArraysEntityRequest.getGroundMounted());
				permitArraysEntity.setRoofMounted(editArraysEntityRequest.getRoofMounted());
				permitArraysEntity.setCarportMounted(editArraysEntityRequest.getCarportMounted());
				permitArraysEntity.setFrontAndBack(editArraysEntityRequest.getFrontAndBack());
				permitArraysEntity.setCantelever(editArraysEntityRequest.getCantelever());
				permitArraysEntity.setAttachedToExtWal(editArraysEntityRequest.getAttachedToExtWal());
				permitArraysEntity.setAttachedToFascia(editArraysEntityRequest.getAttachedToFascia());
				permitArraysEntity.setAttachedToSkylifts(editArraysEntityRequest.getAttachedToSkylifts());
				permitArraysEntity.setFreeStanding(editArraysEntityRequest.getFreeStanding());
				permitArraysEntity.setOtherMounted(editArraysEntityRequest.getOtherMounted());

				permitArraysEntity.setTextOther(editArraysEntityRequest.getTextOther());
				//S.B CR-2996-MOD-12 29/07/2020
				permitArraysEntity.setPatioMounted(editArraysEntityRequest.getPatioMounted());
				permitArraysEntity.setInverterModel(null);
				if (editArraysEntityRequest.getSystemOptimizerModel() != null
						&& checkValueTypesService.isLongPositive(editArraysEntityRequest.getSystemOptimizerModel())) {
					DCOptimizerEntity dc = convertersRepo.findById(editArraysEntityRequest.getSystemOptimizerModel())
							.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					permitArraysEntity.setSystemOptimizerModel(dc);

				}
				permitArraysEntity.setNumberModulesACCircuitSix(editArraysEntityRequest.getNumberModulesACCircuitSix());
				permitArraysEntity
						.setNumberModulesACCircuitSeven(editArraysEntityRequest.getNumberModulesACCircuitSeven());
				permitArraysEntity
						.setNumberModulesACCircuitEight(editArraysEntityRequest.getNumberModulesACCircuitEight());
				permitArraysEntity
						.setNumberModulesACCircuitNine(editArraysEntityRequest.getNumberModulesACCircuitNine());
				permitArraysEntity.setNumberModulesACCircuitTen(editArraysEntityRequest.getNumberModulesACCircuitTen());
				permitArraysEntity
						.setNumberModulesACCircuitEleven(editArraysEntityRequest.getNumberModulesACCircuitEleven());
				permitArraysEntity
						.setNumberModulesACCircuitTweleve(editArraysEntityRequest.getNumberModulesACCircuitTweleve());

				permitArraysEntity
						.setNumberModulesACCircuitThirteen(editArraysEntityRequest.getNumberModulesACCircuitThirteen());

				permitArraysEntity
						.setNumberModulesACCircuitFourteen(editArraysEntityRequest.getNumberModulesACCircuitFourteen());

				permitArraysEntity
						.setNumberModulesACCircuitFifteen(editArraysEntityRequest.getNumberModulesACCircuitFifteen());

				permitArraysEntity
						.setNumberModulesACCircuitSixteen(editArraysEntityRequest.getNumberModulesACCircuitSixteen());

				permitArraysEntity.setNumberModulesACCircuitSeventeen(
						editArraysEntityRequest.getNumberModulesACCircuitSeventeen());

				permitArraysEntity.setNumberModulesACCircuitEightteen(
						editArraysEntityRequest.getNumberModulesACCircuitEightteen());

				permitArraysEntity
						.setNumberModulesACCircuitNineteen(editArraysEntityRequest.getNumberModulesACCircuitNineteen());

				permitArraysEntity
						.setNumberModulesACCircuitTwenty(editArraysEntityRequest.getNumberModulesACCircuitTwenty());

				permitArraysEntity.setNumberModulesACCircuitTwentyOne(
						editArraysEntityRequest.getNumberModulesACCircuitTwentyOne());

				permitArraysEntity.setNumberModulesACCircuitTwentyTwo(
						editArraysEntityRequest.getNumberModulesACCircuitTwentyTwo());

				permitArraysEntity.setNumberModulesACCircuitTwentyThree(
						editArraysEntityRequest.getNumberModulesACCircuitTwentyThree());

				permitArraysEntity.setNumberModulesACCircuitTwentyFour(
						editArraysEntityRequest.getNumberModulesACCircuitTwentyFour());

				permitArraysEntity.setOcpdOne(editArraysEntityRequest.getOcpdOne());
				permitArraysEntity.setOcpdTwo(editArraysEntityRequest.getOcpdTwo());
				permitArraysEntity.setOcpdThree(editArraysEntityRequest.getOcpdThree());
				permitArraysEntity.setOcpdFour(editArraysEntityRequest.getOcpdFour());
				permitArraysEntity.setOcpdFive(editArraysEntityRequest.getOcpdFive());
				permitArraysEntity.setOcpdSix(editArraysEntityRequest.getOcpdSix());
				permitArraysEntity.setOcpdSeven(editArraysEntityRequest.getOcpdSeven());
				permitArraysEntity.setOcpdEight(editArraysEntityRequest.getOcpdEight());
				permitArraysEntity.setOcpdNine(editArraysEntityRequest.getOcpdNine());
				permitArraysEntity.setOcpdTen(editArraysEntityRequest.getOcpdTen());
				permitArraysEntity.setOcpdEleven(editArraysEntityRequest.getOcpdEleven());
				permitArraysEntity.setOcpdTwelve(editArraysEntityRequest.getOcpdTwelve());

				permitArraysEntity.setOcpdThirteen(editArraysEntityRequest.getOcpdThirteen());
				permitArraysEntity.setOcpdFourteen(editArraysEntityRequest.getOcpdFourteen());
				permitArraysEntity.setOcpdFifteen(editArraysEntityRequest.getOcpdFifteen());
				permitArraysEntity.setOcpdSixteen(editArraysEntityRequest.getOcpdSixteen());
				permitArraysEntity.setOcpdSeventeen(editArraysEntityRequest.getOcpdSeventeen());
				permitArraysEntity.setOcpdEightteen(editArraysEntityRequest.getOcpdEightteen());
				permitArraysEntity.setOcpdNineteen(editArraysEntityRequest.getOcpdNineteen());
				permitArraysEntity.setOcpdTwenty(editArraysEntityRequest.getOcpdTwenty());
				permitArraysEntity.setOcpdTwentyOne(editArraysEntityRequest.getOcpdTwentyOne());
				permitArraysEntity.setOcpdTwentyTwo(editArraysEntityRequest.getOcpdTwentyTwo());
				permitArraysEntity.setOcpdTwentyThree(editArraysEntityRequest.getOcpdTwentyThree());
				permitArraysEntity.setOcpdTwentyFour(editArraysEntityRequest.getOcpdTwentyFour());

				/*************************** variable hidden ************************/
				permitArraysEntity.setBatteryManufacturerTrojan(editArraysEntityRequest.getBatteryManufacturerTrojan());
				permitArraysEntity.setBatteryManufacturerMMK(editArraysEntityRequest.getBatteryManufacturerMMK());
				permitArraysEntity.setBatteryManufacturerUPG(editArraysEntityRequest.getBatteryManufacturerUPG());
				permitArraysEntity.setBatteryManufacturerRolls(editArraysEntityRequest.getBatteryManufacturerRolls());
				permitArraysEntity.setBatteryManufacturerCrown(editArraysEntityRequest.getBatteryManufacturerCrown());
				permitArraysEntity.setBatteryManufacturerTesla(editArraysEntityRequest.getBatteryManufacturerTesla());
				permitArraysEntity
						.setBatteryManufacturerOutback(editArraysEntityRequest.getBatteryManufacturerOutback());
				permitArraysEntity
						.setBatteryManufacturerFullriver(editArraysEntityRequest.getBatteryManufacturerFullriver());
				permitArraysEntity
						.setBatteryManufacturerConcord(editArraysEntityRequest.getBatteryManufacturerConcord());
				permitArraysEntity.setBatteryManufacturerOther(editArraysEntityRequest.getBatteryManufacturerOther());
				permitArraysEntity.setTextBatteryOther(editArraysEntityRequest.getTextBatteryOther());
				/*************************************************************************************/
				/******************************
				 * new input SR 002
				 ***************************************/

				permitArraysEntity.setThirdInverterModel(null);
				permitArraysEntity.setThirdStringOne(editArraysEntityRequest.getThirdStringOne());
				permitArraysEntity.setThirdStringTwo(editArraysEntityRequest.getThirdStringTwo());
				permitArraysEntity.setThirdStringThree(editArraysEntityRequest.getThirdStringThree());
				permitArraysEntity.setThirdStringFour(editArraysEntityRequest.getThirdStringFour());
				permitArraysEntity.setThirdStringFive(editArraysEntityRequest.getThirdStringFive());

				permitArraysEntity.setFourthInverterModel(null);
				permitArraysEntity.setFourthStringOne(editArraysEntityRequest.getFourthStringOne());
				permitArraysEntity.setFourthStringTwo(editArraysEntityRequest.getFourthStringTwo());
				permitArraysEntity.setFourthStringThree(editArraysEntityRequest.getFourthStringThree());
				permitArraysEntity.setFourthStringFour(editArraysEntityRequest.getFourthStringFour());
				permitArraysEntity.setFourthStringFive(editArraysEntityRequest.getFourthStringFive());

				permitArraysEntity.setFifthInverterModel(null);
				permitArraysEntity.setFifthStringOne(editArraysEntityRequest.getFifthStringOne());
				permitArraysEntity.setFifthStringTwo(editArraysEntityRequest.getFifthStringTwo());
				permitArraysEntity.setFifthStringThree(editArraysEntityRequest.getFifthStringThree());
				permitArraysEntity.setFifthStringFour(editArraysEntityRequest.getFifthStringFour());
				permitArraysEntity.setFifthStringFive(editArraysEntityRequest.getFifthStringFive());
				permitArraysEntity.setMicroInverterManufacturer(editArraysEntityRequest.getMicroInverterManufacturer());
				permitArraysEntity.setMicroInverterModel(editArraysEntityRequest.getMicroInverterModel());
				permitArraysEntity.setMicroInverterManufacturer(editArraysEntityRequest.getMicroInverterManufacturer());
				permitArraysEntity.setNumberModulesACCircuitOne(editArraysEntityRequest.getNumberModulesACCircuitOne());
				permitArraysEntity.setNumberModulesACCircuitTwo(editArraysEntityRequest.getNumberModulesACCircuitTwo());
				permitArraysEntity
						.setNumberModulesACCircuitThree(editArraysEntityRequest.getNumberModulesACCircuitThree());
				permitArraysEntity
						.setNumberModulesACCircuitFour(editArraysEntityRequest.getNumberModulesACCircuitFour());
				permitArraysEntity
						.setNumberModulesACCircuitFive(editArraysEntityRequest.getNumberModulesACCircuitFive());

				/******************************
				 * END new input SR 002
				 ***************************************/
				// ***********Begin CR-2222 :Add UP TO 12 String Inputs for Number of Modules in
				// Each String*******************
				permitArraysEntity.setStringSix(editArraysEntityRequest.getStringSix());
				permitArraysEntity.setStringSeven(editArraysEntityRequest.getStringSeven());
				permitArraysEntity.setStringEight(editArraysEntityRequest.getStringEight());
				permitArraysEntity.setStringNine(editArraysEntityRequest.getStringNine());
				permitArraysEntity.setStringTen(editArraysEntityRequest.getStringTen());
				permitArraysEntity.setStringEleven(editArraysEntityRequest.getStringEleven());
				permitArraysEntity.setStringTwelve(editArraysEntityRequest.getStringTwelve());
				permitArraysEntity.setSecondStringSix(editArraysEntityRequest.getSecondStringSix());
				permitArraysEntity.setSecondStringSeven(editArraysEntityRequest.getSecondStringSeven());
				permitArraysEntity.setSecondStringEight(editArraysEntityRequest.getSecondStringEight());
				permitArraysEntity.setSecondStringNine(editArraysEntityRequest.getSecondStringNine());
				permitArraysEntity.setSecondStringTen(editArraysEntityRequest.getSecondStringTen());
				permitArraysEntity.setSecondStringEleven(editArraysEntityRequest.getSecondStringEleven());
				permitArraysEntity.setSecondStringTwelve(editArraysEntityRequest.getSecondStringTwelve());
				permitArraysEntity.setThirdStringSix(editArraysEntityRequest.getThirdStringSix());
				permitArraysEntity.setThirdStringSeven(editArraysEntityRequest.getThirdStringSeven());
				permitArraysEntity.setThirdStringEight(editArraysEntityRequest.getThirdStringEight());
				permitArraysEntity.setThirdStringNine(editArraysEntityRequest.getThirdStringNine());
				permitArraysEntity.setThirdStringTen(editArraysEntityRequest.getThirdStringTen());
				permitArraysEntity.setThirdStringEleven(editArraysEntityRequest.getThirdStringEleven());
				permitArraysEntity.setThirdStringTwelve(editArraysEntityRequest.getThirdStringTwelve());
				permitArraysEntity.setFourthStringSix(editArraysEntityRequest.getFourthStringSix());
				permitArraysEntity.setFourthStringSeven(editArraysEntityRequest.getFourthStringSeven());
				permitArraysEntity.setFourthStringEight(editArraysEntityRequest.getFourthStringEight());
				permitArraysEntity.setFourthStringNine(editArraysEntityRequest.getFourthStringNine());
				permitArraysEntity.setFourthStringTen(editArraysEntityRequest.getFourthStringTen());
				permitArraysEntity.setFourthStringEleven(editArraysEntityRequest.getFourthStringEleven());
				permitArraysEntity.setFourthStringTwelve(editArraysEntityRequest.getFourthStringTwelve());
				permitArraysEntity.setFifthStringSix(editArraysEntityRequest.getFifthStringSix());
				permitArraysEntity.setFifthStringSeven(editArraysEntityRequest.getFifthStringSeven());
				permitArraysEntity.setFifthStringEight(editArraysEntityRequest.getFifthStringEight());
				permitArraysEntity.setFifthStringNine(editArraysEntityRequest.getFifthStringNine());
				permitArraysEntity.setFifthStringTen(editArraysEntityRequest.getFifthStringTen());
				permitArraysEntity.setFifthStringEleven(editArraysEntityRequest.getFifthStringEleven());
				permitArraysEntity.setFifthStringTwelve(editArraysEntityRequest.getFifthStringTwelve());

				// ************ END CR-2222****************************

				/***************************************
				 * Upload Comments
				 **********************************************/
				permitArraysEntity.setUploadCommentsAddInfo(editArraysEntityRequest.getUploadCommentsAddInfo());
				permitArraysEntity.setUploadCommentsLayout(editArraysEntityRequest.getUploadCommentsLayout());

				permitArraysEntity.setInverterLocation(editArraysEntityRequest.getInverterLocation());
				permitArraysEntity.setInverterLocationOther(editArraysEntityRequest.getInverterLocationOther());
				permitArraysEntity.setInverterSameLocation(editArraysEntityRequest.getInverterSameLocation());
				if (checkValueTypesService.isLongPositive(editArraysEntityRequest.getMicroInverter())) {
					Inverters microInverter = inverterRepo.findById(editArraysEntityRequest.getMicroInverter()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					permitArraysEntity.setMicroInverterEntity(microInverter);
				}else
					permitArraysEntity.setMicroInverterEntity(null);
				permitArraysEntity.setRoofOrOpenFrame(editArraysEntityRequest.getRoofOrOpenFrame());
				permitArraysEntity.setCircuitUnderGround(editArraysEntityRequest.getCircuitUnderGround());
				permitArraysEntity.setInverterInstalledOnRoof(editArraysEntityRequest.getInverterInstalledOnRoof());

				// S.B 21/04/2020 CR-3119
				permitArraysEntity.setEnteranyTransformer(editArraysEntityRequest.getEnteranyTransformer());

				projectArraysRepo.save(permitArraysEntity);
			}
			// End Save Arrays tab

			// Save BOS tab
			PermitProjectSiteInfoEntity permitProjectSiteInfoEntity = permitProjectSiteInfoEntityRepos
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			PermitProjectSiteInfoEntityTwo editProjectSiteInfoRequest = permitModel.getPermitProjectSiteInfoEntityTwo();

			if (editProjectSiteInfoRequest != null) {
				/*
				 * permitProjectSiteInfoEntity.setRailRakingModel(editProjectSiteInfoRequest.
				 * getRailRakingModel());
				 */
				if (editProjectSiteInfoRequest.getRailRakingModel() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getRailRakingModel())) {
					permitProjectSiteInfoEntity.setRailRakingModel(
							railRackingRepo.findById(editProjectSiteInfoRequest.getRailRakingModel()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));

				}else
					permitProjectSiteInfoEntity.setRailRakingModel(null);

				permitProjectSiteInfoEntity
						.setTrackingSystemManufacturer(editProjectSiteInfoRequest.getTrackingSystemManufacturer());
				permitProjectSiteInfoEntity.setTrackingSystemManufacturerOther(
						editProjectSiteInfoRequest.getTrackingSystemManufacturerOther());
				permitProjectSiteInfoEntity.setTrackingSystemModel(editProjectSiteInfoRequest.getTrackingSystemModel());
				permitProjectSiteInfoEntity
						.setTrackingSystemModelOther(editProjectSiteInfoRequest.getTrackingSystemModelOther());
				boolean isMoreInterconnecting = false;
				if (checkValueTypesService.Equals(editProjectSiteInfoRequest.getMorInterconnectingBackFeed(), "true")) {
					isMoreInterconnecting = true;
				}
				permitProjectSiteInfoEntity.setMorInterconnectingBackFeed(isMoreInterconnecting);
				permitProjectSiteInfoEntity.setInverterModel(editProjectSiteInfoRequest.getInverterModel());
				permitProjectSiteInfoEntity.setRafterTrussSpacing(editProjectSiteInfoRequest.getRafterTrussSpacing());
				permitProjectSiteInfoEntity.setCrossSectionSize(editProjectSiteInfoRequest.getCrossSectionSize());
				permitProjectSiteInfoEntity.setTextOtherSize(editProjectSiteInfoRequest.getTextOtherSize());
				permitProjectSiteInfoEntity.setRoofMaterialType(editProjectSiteInfoRequest.getRoofMaterialType());
				permitProjectSiteInfoEntity
						.setRoofMaterialTypeOther(editProjectSiteInfoRequest.getRoofMaterialTypeOther());
				permitProjectSiteInfoEntity
						.setRankingRoofManufacturer(editProjectSiteInfoRequest.getRankingRoofManufacturer());
				permitProjectSiteInfoEntity.setRankingRoofModel(editProjectSiteInfoRequest.getRankingRoofModel());
				permitProjectSiteInfoEntity.setModuleGrounding(editProjectSiteInfoRequest.getModuleGrounding());
				permitProjectSiteInfoEntity
						.setDisconnectManufacturer(editProjectSiteInfoRequest.getDisconnectManufacturer());
				if (editProjectSiteInfoRequest.getDisconnectModel() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getDisconnectModel())) {
					DCCombinerDisconnectEntity dc = dCCombinerDisconnectEntityRepo
							.findById(editProjectSiteInfoRequest.getDisconnectModel()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
					permitProjectSiteInfoEntity.setDisconnectModel(dc);

				}else
					permitProjectSiteInfoEntity.setDisconnectModel(null);
				permitProjectSiteInfoEntity
						.setDisconnectModelOther(editProjectSiteInfoRequest.getDisconnectModelOther());
				permitProjectSiteInfoEntity.setQuantityRooftop(editProjectSiteInfoRequest.getQuantityRooftop());
				permitProjectSiteInfoEntity
						.setPanelExistingProposed(editProjectSiteInfoRequest.getPanelExistingProposed());
				permitProjectSiteInfoEntity.setPanelLocation(editProjectSiteInfoRequest.getPanelLocation());
				permitProjectSiteInfoEntity.setDisconnectLocation(editProjectSiteInfoRequest.getDisconnectLocation());
				permitProjectSiteInfoEntity.setACDisconnectSwitchManufacturer(
						editProjectSiteInfoRequest.getACDisconnectSwitchManufacturer());
				permitProjectSiteInfoEntity.setACDisconnectSwitchManufacturerOther(
						editProjectSiteInfoRequest.getACDisconnectSwitchManufacturerOther());
				permitProjectSiteInfoEntity
						.setACDisconnectSwitchModel(editProjectSiteInfoRequest.getACDisconnectSwitchModel());
				permitProjectSiteInfoEntity
						.setACDisconnectSwitchModelOther(editProjectSiteInfoRequest.getACDisconnectSwitchModelOther());
				permitProjectSiteInfoEntity.setDCDisconnectSwitchManufacturer(
						editProjectSiteInfoRequest.getDCDisconnectSwitchManufacturer());
				permitProjectSiteInfoEntity
						.setDCDisconnectSwitchModel(editProjectSiteInfoRequest.getDCDisconnectSwitchModel());
				permitProjectSiteInfoEntity
						.setLeasePPAMeterManufacturer(editProjectSiteInfoRequest.getLeasePPAMeterManufacturer());
				permitProjectSiteInfoEntity.setLeasePPAMeterModel(editProjectSiteInfoRequest.getLeasePPAMeterModel());
				permitProjectSiteInfoEntity
						.setQuantityofCombinerBox(editProjectSiteInfoRequest.getQuantityofCombinerBox());
				permitProjectSiteInfoEntity
						.setQuantityofCombinerBoxOther(editProjectSiteInfoRequest.getQuantityofCombinerBoxOther());
				permitProjectSiteInfoEntity
						.setRooftopACCombinerModel(editProjectSiteInfoRequest.getRooftopACCombinerModel());
				permitProjectSiteInfoEntity.setTrackingSystemManufacturerForSecondTracker(
						editProjectSiteInfoRequest.getTrackingSystemManufacturerForSecondTracker());
				permitProjectSiteInfoEntity.setTrackingSystemManufacturerForSecondTrackerOther(
						editProjectSiteInfoRequest.getTrackingSystemManufacturerForSecondTrackerOther());
				permitProjectSiteInfoEntity.setTrackingSystemModelForSecondTracker(
						editProjectSiteInfoRequest.getTrackingSystemModelForSecondTracker());
				permitProjectSiteInfoEntity.setTrackingSystemModelForSecondTrackerOther(
						editProjectSiteInfoRequest.getTrackingSystemModelForSecondTrackerOther());
				permitProjectSiteInfoEntity
						.setRankingRoofManufacturerOther(editProjectSiteInfoRequest.getRankingRoofManufacturerOther());
				permitProjectSiteInfoEntity
						.setRankingRoofModelOther(editProjectSiteInfoRequest.getRankingRoofModelOther());
				permitProjectSiteInfoEntity
						.setModuleGroundingOther(editProjectSiteInfoRequest.getModuleGroundingOther());
				permitProjectSiteInfoEntity
						.setDisconnectManufacturerOther(editProjectSiteInfoRequest.getDisconnectManufacturerOther());

				/*********************************
				 * SR 002
				 **********************************************************/

				permitProjectSiteInfoEntity.setRailConnectionModel(editProjectSiteInfoRequest.getRailConnectionModel());
				boolean isAttic = false;
				if (editProjectSiteInfoRequest.getAtticJBoxesbeUtilized() != null) {
					if (checkValueTypesService.Equals(editProjectSiteInfoRequest.getAtticJBoxesbeUtilized(), "true")) {
						isAttic = true;
					}
				}
				permitProjectSiteInfoEntity.setAtticJBoxesbeUtilized(isAttic);
				permitProjectSiteInfoEntity
						.setDescriptionOfBackFeed(editProjectSiteInfoRequest.getDescriptionOfBackFeed());
				permitProjectSiteInfoEntity.setGroundLevelACDisconnectEnclosure(
						editProjectSiteInfoRequest.getGroundLevelACDisconnectEnclosure());
				permitProjectSiteInfoEntity
						.setPanelMainBreakerRating(editProjectSiteInfoRequest.getPanelMainBreakerRating());
				permitProjectSiteInfoEntity
						.setPanelMainBreakerRatingOther(editProjectSiteInfoRequest.getPanelMainBreakerRatingOther());
				permitProjectSiteInfoEntity.setCombiningACCircuits(editProjectSiteInfoRequest.getCombiningACCircuits());

				/*************************************************************************************************/
				/*******************************************
				 * CR_003
				 ******************************************************/
				if (editProjectSiteInfoRequest.getRailRakingModelforGroundMounted() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getRailRakingModelforGroundMounted())) {
					permitProjectSiteInfoEntity.setRailRakingModelforGroundMounted(railRackingRepo
							.findById(editProjectSiteInfoRequest.getRailRakingModelforGroundMounted()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));

				}else
					permitProjectSiteInfoEntity.setRailRakingModelforGroundMounted(null);

				if (editProjectSiteInfoRequest.getRailRakingforPatioMounted() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getRailRakingforPatioMounted())) {
					permitProjectSiteInfoEntity.setRailRakingforPatioMounted(railRackingRepo
							.findById(editProjectSiteInfoRequest.getRailRakingforPatioMounted()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));

				}else
					permitProjectSiteInfoEntity.setRailRakingforPatioMounted(null);
				
				if (editProjectSiteInfoRequest.getRailRakingforCarport() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getRailRakingforCarport())) {
					permitProjectSiteInfoEntity.setRailRakingforCarport(railRackingRepo
							.findById(editProjectSiteInfoRequest.getRailRakingforCarport()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));

				}else
					permitProjectSiteInfoEntity.setRailRakingforCarport(null);
				
				permitProjectSiteInfoEntity.setQtyJunctionBox(editProjectSiteInfoRequest.getQtyJunctionBox());
				
				permitProjectSiteInfoEntity
						.setSizeAndTypeAtticJBox(editProjectSiteInfoRequest.getSizeAndTypeAtticJBox());
				permitProjectSiteInfoEntity
						.setSizeAndTypeAtticJBoxOther(editProjectSiteInfoRequest.getSizeAndTypeAtticJBoxOther());
				permitProjectSiteInfoEntity.setIfApplicableSubPanelMainBreakerRating(
						editProjectSiteInfoRequest.getIfApplicableSubPanelMainBreakerRating());
				permitProjectSiteInfoEntity
						.setProposedSubPanelManufacturer(editProjectSiteInfoRequest.getProposedSubPanelManufacturer());
				permitProjectSiteInfoEntity
						.setProposedSubPanelModel(editProjectSiteInfoRequest.getProposedSubPanelModel());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufacturerOther(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxManufacturerOther());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxModelOther(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxModelOther());
				if (editProjectSiteInfoRequest.getGroundLevelACCombinerBoxModel() != null && checkValueTypesService
						.isLongPositive(editProjectSiteInfoRequest.getGroundLevelACCombinerBoxModel())) {
					permitProjectSiteInfoEntity.setGroundLevelACCombinerBoxModel(acCombinerSLCRepo
							.findById(editProjectSiteInfoRequest.getGroundLevelACCombinerBoxModel()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));
				}else {
					permitProjectSiteInfoEntity.setGroundLevelACCombinerBoxModel(null);
				}

				permitProjectSiteInfoEntity.setGroundLevelACCombinerDisconnectModel(
						editProjectSiteInfoRequest.getGroundLevelACCombinerDisconnectModel());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufacturer(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxManufacturer());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxModel(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxModel());
				permitProjectSiteInfoEntity.setEquipmentRoofMountedACCombinerBox(
						editProjectSiteInfoRequest.getEquipmentRoofMountedACCombinerBox());
				permitProjectSiteInfoEntity.setEquipmentRoofMountedACCombinerDisconnect(
						editProjectSiteInfoRequest.getEquipmentRoofMountedACCombinerDisconnect());
				permitProjectSiteInfoEntity.setEquipmentRoofMountedJunctionBox(
						editProjectSiteInfoRequest.getEquipmentRoofMountedJunctionBox());
				permitProjectSiteInfoEntity.setEquipmentRoofMountedSingleCircuit(
						editProjectSiteInfoRequest.getEquipmentRoofMountedSingleCircuit());
				permitProjectSiteInfoEntity.setEquipmentGroundLevelACCombinerBox(
						editProjectSiteInfoRequest.getEquipmentGroundLevelACCombinerBox());
				permitProjectSiteInfoEntity.setEquipmentGroundLevelACCombinerDisconnect(
						editProjectSiteInfoRequest.getEquipmentGroundLevelACCombinerDisconnect());
				permitProjectSiteInfoEntity.setEquipmentGroundLevelACSubPanel(
						editProjectSiteInfoRequest.getEquipmentGroundLevelACSubPanel());
				permitProjectSiteInfoEntity.setEquipmentGroundLevelACJunctionBox(
						editProjectSiteInfoRequest.getEquipmentGroundLevelACJunctionBox());
				permitProjectSiteInfoEntity.setEquipmentCombiningInExistingSubPanel(
						editProjectSiteInfoRequest.getEquipmentCombiningInExistingSubPanel());
				permitProjectSiteInfoEntity.setEquipmentCombiningInProposedSubPanel(
						editProjectSiteInfoRequest.getEquipmentCombiningInProposedSubPanel());
				permitProjectSiteInfoEntity.setEquipmentCombiningInMainPanel(
						editProjectSiteInfoRequest.getEquipmentCombiningInMainPanel());
				permitProjectSiteInfoEntity.setEquipmentisOther(editProjectSiteInfoRequest.getEquipmentisOther());
				permitProjectSiteInfoEntity.setEquipmentOther(editProjectSiteInfoRequest.getEquipmentOther());
				permitProjectSiteInfoEntity.setRoofMountedACCombinerBoxManufacturer(
						editProjectSiteInfoRequest.getRoofMountedACCombinerBoxManufacturer());
				permitProjectSiteInfoEntity.setRoofMountedACCombinerBoxManufacturerOther(
						editProjectSiteInfoRequest.getRoofMountedACCombinerBoxManufacturerOther());
				permitProjectSiteInfoEntity.setRoofMountedACCombinerBoxModel(
						editProjectSiteInfoRequest.getRoofMountedACCombinerBoxModel());
				permitProjectSiteInfoEntity.setRoofMountedACCombinerBoxModelOther(
						editProjectSiteInfoRequest.getRoofMountedACCombinerBoxModelOther());
				permitProjectSiteInfoEntity.setRoofMountedACCombiningDisconnectManufacturer(
						editProjectSiteInfoRequest.getRoofMountedACCombiningDisconnectManufacturer());
				permitProjectSiteInfoEntity.setRoofMountedACCombiningDisconnectManufacturerOther(
						editProjectSiteInfoRequest.getRoofMountedACCombiningDisconnectManufacturerOther());
				permitProjectSiteInfoEntity.setRoofMountedACCombiningDisconnectModel(
						editProjectSiteInfoRequest.getRoofMountedACCombiningDisconnectModel());
				permitProjectSiteInfoEntity.setRoofMountedACCombiningDisconnectModelOther(
						editProjectSiteInfoRequest.getRoofMountedACCombiningDisconnectModelOther());
				permitProjectSiteInfoEntity.setRoofMountedACJunctionBoxManufacturer(
						editProjectSiteInfoRequest.getRoofMountedACJunctionBoxManufacturer());
				permitProjectSiteInfoEntity.setRoofMountedACJunctionBoxManufacturerOther(
						editProjectSiteInfoRequest.getRoofMountedACJunctionBoxManufacturerOther());
				permitProjectSiteInfoEntity.setRoofMountedACJunctionBoxModel(
						editProjectSiteInfoRequest.getRoofMountedACJunctionBoxModel());
				permitProjectSiteInfoEntity.setRoofMountedACJunctionBoxModelOther(
						editProjectSiteInfoRequest.getRoofMountedACJunctionBoxModelOther());
				permitProjectSiteInfoEntity.setRoofMountedSingleCircuitACDisconnectManufacturer(
						editProjectSiteInfoRequest.getRoofMountedSingleCircuitACDisconnectManufacturer());
				permitProjectSiteInfoEntity.setRoofMountedSingleCircuitACDisconnectManufacturerOther(
						editProjectSiteInfoRequest.getRoofMountedSingleCircuitACDisconnectManufacturerOther());
				permitProjectSiteInfoEntity.setRoofMountedSingleCircuitACDisconnectModel(
						editProjectSiteInfoRequest.getRoofMountedSingleCircuitACDisconnectModel());
				permitProjectSiteInfoEntity.setRoofMountedSingleCircuitACDisconnectModelOther(
						editProjectSiteInfoRequest.getRoofMountedSingleCircuitACDisconnectModelOther());
				permitProjectSiteInfoEntity.setEquipmenModelOther(editProjectSiteInfoRequest.getEquipmenModelOther());
				permitProjectSiteInfoEntity
						.setEquipmenManufacturerOther(editProjectSiteInfoRequest.getEquipmenManufacturerOther());
				permitProjectSiteInfoEntity.setProposedMainPanelManufacturer(
						editProjectSiteInfoRequest.getProposedMainPanelManufacturer());
				permitProjectSiteInfoEntity.setProposedMainPanelManufacturerOther(
						editProjectSiteInfoRequest.getProposedMainPanelManufacturerOther());
				permitProjectSiteInfoEntity
						.setProposedMainPanelModel(editProjectSiteInfoRequest.getProposedMainPanelModel());
				permitProjectSiteInfoEntity
						.setProposedMainPanelModelOther(editProjectSiteInfoRequest.getProposedMainPanelModelOther());
				permitProjectSiteInfoEntity
						.setDeratingthisPanelString(editProjectSiteInfoRequest.getDeratingthisPanelString());
				permitProjectSiteInfoEntity.setSubPanelBreakerOCPD(editProjectSiteInfoRequest.getSubPanelBreakerOCPD());
				permitProjectSiteInfoEntity
						.setMainBreakerLocatedEndBusBar(editProjectSiteInfoRequest.getMainBreakerLocatedEndBusBar());
				permitProjectSiteInfoEntity
						.setInstallationGuidelines(editProjectSiteInfoRequest.getInstallationGuidelines());
				permitProjectSiteInfoEntity.setTextOtherRatfter(editProjectSiteInfoRequest.getTextOtherRatfter());

				/*******************************************
				 * END CR_003
				 ******************************************************/
				permitProjectSiteInfoEntity
						.setSpanBetweenAttachment(editProjectSiteInfoRequest.getSpanBetweenAttachment());
				permitProjectSiteInfoEntity.setPanelBusRating(editProjectSiteInfoRequest.getPanelBusRating());
				permitProjectSiteInfoEntity.setPanelBusRatingOther(editProjectSiteInfoRequest.getPanelBusRatingOther());

				permitProjectSiteInfoEntity.setSubPanelBusRating(editProjectSiteInfoRequest.getSubPanelBusRating());
				permitProjectSiteInfoEntity
						.setSubPanelMainBreakerRating(editProjectSiteInfoRequest.getSubPanelMainBreakerRating());
				permitProjectSiteInfoEntity
						.setSolarInterconnection(editProjectSiteInfoRequest.getSolarInterconnection());
				permitProjectSiteInfoEntity
						.setSolarInterconnectionOther(editProjectSiteInfoRequest.getSolarInterconnectionOther());
				permitProjectSiteInfoEntity.setSolarLocation(editProjectSiteInfoRequest.getSolarLocation());
				permitProjectSiteInfoEntity
						.setSecondSolarInterconnection(editProjectSiteInfoRequest.getSecondSolarInterconnection());
				permitProjectSiteInfoEntity.setSecondSolarInterconnectionOther(
						editProjectSiteInfoRequest.getSecondSolarInterconnectionOther());
				permitProjectSiteInfoEntity.setLocation(editProjectSiteInfoRequest.getLocation());
				permitProjectSiteInfoEntity.setLocationTwo(editProjectSiteInfoRequest.getLocationTwo());
				permitProjectSiteInfoEntity.setLocationThree(editProjectSiteInfoRequest.getLocationThree());
				permitProjectSiteInfoEntity.setInstallingDCBo(editProjectSiteInfoRequest.getInstallingDCBo());
				permitProjectSiteInfoEntity.setLocationFive(editProjectSiteInfoRequest.getLocationFive());
				permitProjectSiteInfoEntity.setLocationSix(editProjectSiteInfoRequest.getLocationSix());
				permitProjectSiteInfoEntity.setLocationFour(editProjectSiteInfoRequest.getLocationFour());
				permitProjectSiteInfoEntity.setProposedMainPanMan(editProjectSiteInfoRequest.getProposedMainPanMan());
				permitProjectSiteInfoEntity
						.setThirdSolarInterconnection(editProjectSiteInfoRequest.getThirdSolarInterconnection());
				permitProjectSiteInfoEntity
						.setFourthSolarInterconnection(editProjectSiteInfoRequest.getFourthSolarInterconnection());
				permitProjectSiteInfoEntity
						.setFifthSolarInterconnection(editProjectSiteInfoRequest.getFifthSolarInterconnection());
				permitProjectSiteInfoEntity.setThirdSolarInterconnectionOther(
						editProjectSiteInfoRequest.getThirdSolarInterconnectionOther());
				permitProjectSiteInfoEntity.setFourthSolarInterconnectionOther(
						editProjectSiteInfoRequest.getFourthSolarInterconnectionOther());
				permitProjectSiteInfoEntity.setFifthSolarInterconnectionOther(
						editProjectSiteInfoRequest.getFifthSolarInterconnectionOther());
				permitProjectSiteInfoEntity.setThepontOfTheC(editProjectSiteInfoRequest.getThepontOfTheC());
				permitProjectSiteInfoEntity.setConnectionPoint(editProjectSiteInfoRequest.getConnectionPoint());
				permitProjectSiteInfoEntity.setThepontOfTheCOther(editProjectSiteInfoRequest.getThepontOfTheCOther());
				/*************************************************************************************************/
				/********************************************
				 * CR004
				 *****************************************************/

				permitProjectSiteInfoEntity
						.setDisconnectManufacturerTwo(editProjectSiteInfoRequest.getDisconnectManufacturerTwo());
				permitProjectSiteInfoEntity.setDisconnectManufacturerOtherTwo(
						editProjectSiteInfoRequest.getDisconnectManufacturerOtherTwo());
				if (editProjectSiteInfoRequest.getDisconnectModelTwo() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getDisconnectModelTwo())) {
					permitProjectSiteInfoEntity.setDisconnectModelTwo(dCCombinerDisconnectEntityRepo
							.findById(editProjectSiteInfoRequest.getDisconnectModelTwo()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));

				}else 
					permitProjectSiteInfoEntity.setDisconnectModelTwo(null);
				permitProjectSiteInfoEntity
						.setDisconnectModelTwoOther(editProjectSiteInfoRequest.getDisconnectModelTwoOther());

				permitProjectSiteInfoEntity
						.setDisconnectManufacturerThree(editProjectSiteInfoRequest.getDisconnectManufacturerThree());
				permitProjectSiteInfoEntity.setDisconnectManufacturerThreeOther(
						editProjectSiteInfoRequest.getDisconnectManufacturerThreeOther());
				if (editProjectSiteInfoRequest.getDisconnectModelThree() != null && checkValueTypesService
						.isLongPositive(editProjectSiteInfoRequest.getDisconnectModelThree())) {
					permitProjectSiteInfoEntity.setDisconnectModelThree(dCCombinerDisconnectEntityRepo
							.findById(editProjectSiteInfoRequest.getDisconnectModelThree()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));
				}else
					permitProjectSiteInfoEntity.setDisconnectModelThree(null);

				permitProjectSiteInfoEntity
						.setDisconnectModelThreeOther(editProjectSiteInfoRequest.getDisconnectModelThreeOther());

				permitProjectSiteInfoEntity
						.setRooftopACCombinerModelTwo(editProjectSiteInfoRequest.getRooftopACCombinerModelTwo());
				permitProjectSiteInfoEntity.setTallStructure(editProjectSiteInfoRequest.getTallStructure());
				permitProjectSiteInfoEntity.setOtherTallStructure(editProjectSiteInfoRequest.getOtherTallStructure());
				permitProjectSiteInfoEntity.setMeanHeight(editProjectSiteInfoRequest.getMeanHeight());
				permitProjectSiteInfoEntity
						.setExistingMainPanelManufac(editProjectSiteInfoRequest.getExistingMainPanelManufac());
				permitProjectSiteInfoEntity.setExistingMainPanelManufacOther(
						editProjectSiteInfoRequest.getExistingMainPanelManufacOther());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufacturerString(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxManufacturerString());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufacturerStringOther(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxManufacturerStringOther());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxModelString(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxModelString());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxModelStringOther(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxModelStringOther());
				permitProjectSiteInfoEntity.setGroundLevelACSubPanelManufacturer(
						editProjectSiteInfoRequest.getGroundLevelACSubPanelManufacturer());
				permitProjectSiteInfoEntity
						.setGroundLevelACSubPanelModel(editProjectSiteInfoRequest.getGroundLevelACSubPanelModel());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufactuereOtherText(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxManufactuereOtherText());
				permitProjectSiteInfoEntity.setGroundLevelACJunctionBoxModelOtherText(
						editProjectSiteInfoRequest.getGroundLevelACJunctionBoxModelOtherText());
				permitProjectSiteInfoEntity.setProposedSubPanelManufacturerOther(
						editProjectSiteInfoRequest.getProposedSubPanelManufacturerOther());
				permitProjectSiteInfoEntity.setSolarLocationOther(editProjectSiteInfoRequest.getSolarLocationOther());
				permitProjectSiteInfoEntity
						.setLeasePPAMeterModelOther(editProjectSiteInfoRequest.getLeasePPAMeterModelOther());
				permitProjectSiteInfoEntity.setLeasePPAMeterManufacturerOther(
						editProjectSiteInfoRequest.getLeasePPAMeterManufacturerOther());
				permitProjectSiteInfoEntity
						.setSubPanelBusRatingOther(editProjectSiteInfoRequest.getSubPanelBusRatingOther());
				permitProjectSiteInfoEntity
						.setSubPanelBreakerOCPDOther(editProjectSiteInfoRequest.getSubPanelBreakerOCPDOther());
				permitProjectSiteInfoEntity.setLocation(editProjectSiteInfoRequest.getLocation());
				permitProjectSiteInfoEntity.setLocationTwo(editProjectSiteInfoRequest.getLocationTwo());
				permitProjectSiteInfoEntity.setLocationThree(editProjectSiteInfoRequest.getLocationThree());
				permitProjectSiteInfoEntity.setInstallingDCBo(editProjectSiteInfoRequest.getInstallingDCBo());
				permitProjectSiteInfoEntity.setLocationFive(editProjectSiteInfoRequest.getLocationFive());
				permitProjectSiteInfoEntity.setLocationSix(editProjectSiteInfoRequest.getLocationSix());
				permitProjectSiteInfoEntity.setLocationFour(editProjectSiteInfoRequest.getLocationFour());
				permitProjectSiteInfoEntity.setProposedMainPanMan(editProjectSiteInfoRequest.getProposedMainPanMan());
				permitProjectSiteInfoEntity
						.setThirdSolarInterconnection(editProjectSiteInfoRequest.getThirdSolarInterconnection());
				permitProjectSiteInfoEntity
						.setFourthSolarInterconnection(editProjectSiteInfoRequest.getFourthSolarInterconnection());
				permitProjectSiteInfoEntity
						.setFifthSolarInterconnection(editProjectSiteInfoRequest.getFifthSolarInterconnection());
				permitProjectSiteInfoEntity.setThirdSolarInterconnectionOther(
						editProjectSiteInfoRequest.getThirdSolarInterconnectionOther());
				permitProjectSiteInfoEntity.setFourthSolarInterconnectionOther(
						editProjectSiteInfoRequest.getFourthSolarInterconnectionOther());
				permitProjectSiteInfoEntity.setFifthSolarInterconnectionOther(
						editProjectSiteInfoRequest.getFifthSolarInterconnectionOther());
				permitProjectSiteInfoEntity.setThepontOfTheC(editProjectSiteInfoRequest.getThepontOfTheC());
				permitProjectSiteInfoEntity.setConnectionPoint(editProjectSiteInfoRequest.getConnectionPoint());
				permitProjectSiteInfoEntity.setThepontOfTheCOther(editProjectSiteInfoRequest.getThepontOfTheCOther());

				/********************************************
				 * END CR004
				 *************************************************/
				permitProjectSiteInfoEntity.setRafterTrussSpacing(editProjectSiteInfoRequest.getRafterTrussSpacing());

				permitProjectSiteInfoEntity
						.setUsedByInverterManufacturer(editProjectSiteInfoRequest.getUsedByInverterManufacturer());

				permitProjectSiteInfoEntity.setUseDisconectSwith(editProjectSiteInfoRequest.getUseDisconectSwith());

				permitProjectSiteInfoEntity.setUsedRevenue(editProjectSiteInfoRequest.getUsedRevenue());

				permitProjectSiteInfoEntity.setMainPanelUpgrade(editProjectSiteInfoRequest.getMainPanelUpgrade());
				permitProjectSiteInfoEntity.setMsphasNoBranchCircuitBreakers(
						editProjectSiteInfoRequest.getMsphasNoBranchCircuitBreakers());
				permitProjectSiteInfoEntity.setInstallRoofTopACDiscoCombiner(
						editProjectSiteInfoRequest.getInstallRoofTopACDiscoCombiner());
				permitProjectSiteInfoEntity
						.setRoofTopACCombinerDisconnect(editProjectSiteInfoRequest.getRoofTopACCombinerDisconnect());
				/******************** CR 1952 ************************/
				permitProjectSiteInfoEntity
						.setProposedACCombMainBreaker(editProjectSiteInfoRequest.getProposedACCombMainBreaker());
				permitProjectSiteInfoEntity.setProposedACCombMainBreakerRating(
						editProjectSiteInfoRequest.getProposedACCombMainBreakerRating());
				permitProjectSiteInfoEntity.setProposedACCombMainBreakerRatingOther(
						editProjectSiteInfoRequest.getProposedACCombMainBreakerRatingOther());
				permitProjectSiteInfoEntity
						.setMicroInverterCabling(editProjectSiteInfoRequest.getMicroInverterCabling());
				permitProjectSiteInfoEntity.setRoofTopJbox(editProjectSiteInfoRequest.getRoofTopJbox());
				permitProjectSiteInfoEntity.setRoofTopACDisco(editProjectSiteInfoRequest.getRoofTopACDisco());
				if (editProjectSiteInfoRequest.getRoofTopACCombiner() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getRoofTopACCombiner())) {
					permitProjectSiteInfoEntity.setRoofTopACCombiner(
							acCombinerSLCRepo.findById(editProjectSiteInfoRequest.getRoofTopACCombiner()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));

				}else {
					permitProjectSiteInfoEntity.setRoofTopACCombiner(null);
				}
				if (editProjectSiteInfoRequest.getRoofTopDCCombiner() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getRoofTopDCCombiner())) {
					permitProjectSiteInfoEntity.setRoofTopDCCombiner(dCCombinerDisconnectEntityRepo
							.findById(editProjectSiteInfoRequest.getRoofTopDCCombiner()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));
				}else {
					permitProjectSiteInfoEntity.setRoofTopDCCombiner(null);
				}
				permitProjectSiteInfoEntity
						.setTransitioningPVWireIn(editProjectSiteInfoRequest.getTransitioningPVWireIn());
				permitProjectSiteInfoEntity.setRoofTopJboxDC(editProjectSiteInfoRequest.getRoofTopJboxDC());
				if (editProjectSiteInfoRequest.getRoofTopDCDisco() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getRoofTopDCDisco())) {
					permitProjectSiteInfoEntity.setRoofTopDCDisco(dCCombinerDisconnectEntityRepo
							.findById(editProjectSiteInfoRequest.getRoofTopDCDisco()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));
				}else 
					permitProjectSiteInfoEntity.setRoofTopDCDisco(null);
				permitProjectSiteInfoEntity.setUploadComments(editProjectSiteInfoRequest.getUploadComments());
				/*************** END CR 1952 **********************************/
				permitProjectSiteInfoEntity
						.setQtyIndependentACDisco(editProjectSiteInfoRequest.getQtyIndependentACDisco());
				permitProjectSiteInfoEntity.setFlashing(editProjectSiteInfoRequest.getFlashing());
				permitProjectSiteInfoEntity.setLeasePPAMeter(editProjectSiteInfoRequest.getLeasePPAMeter());
				permitProjectSiteInfoEntity.setProposedSubPanel(editProjectSiteInfoRequest.getProposedSubPanel());
				permitProjectSiteInfoEntity
						.setInstallingACCombiner(editProjectSiteInfoRequest.getInstallingACCombiner());
				if (editProjectSiteInfoRequest.getaCCombinerInstalled() != null
						&& checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getaCCombinerInstalled())) {
					permitProjectSiteInfoEntity.setACCombinerInstalled(
							acCombinerSLCRepo.findById(editProjectSiteInfoRequest.getaCCombinerInstalled()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND)));
				}else 
					permitProjectSiteInfoEntity.setACCombinerInstalled(null);
				permitProjectSiteInfoEntity.setNorthToShouthFin(editProjectSiteInfoRequest.getNorthToShouthFin());
				permitProjectSiteInfoEntity
						.setNorthToShouthFinOther(editProjectSiteInfoRequest.getNorthToShouthFinOther());
				permitProjectSiteInfoEntity.setHeightOfSouth(editProjectSiteInfoRequest.getHeightOfSouth());
				permitProjectSiteInfoEntity
						.setSubPanelConductorSizing(editProjectSiteInfoRequest.getSubPanelConductorSizing());
				permitProjectSiteInfoEntity
						.setSubPanelConductorSize(editProjectSiteInfoRequest.getSubPanelConductorSize());
				permitProjectSiteInfoEntity
						.setSubPanelConductorSizeOther(editProjectSiteInfoRequest.getSubPanelConductorSizeOther());
				permitProjectSiteInfoEntity.setSubPanelConductorSizeNote(checkValueTypesService
						.isStringNotEmpty(editProjectSiteInfoRequest.getSubPanelConductorSizeNote())
								? editProjectSiteInfoRequest.getSubPanelConductorSizeNote()
								: null);

				// 20/08/2019 : CI : CR2860 : save CheckSiteSurveyOCPDValidity boolean
				permitProjectSiteInfoEntity
						.setCheckSiteSurveyOCPDValidity(editProjectSiteInfoRequest.getCheckSiteSurveyOCPDValidity());
				permitProjectSiteInfoEntity.setIncludeTransformer(editProjectSiteInfoRequest.getIncludeTransformer());
				
				permitProjectSiteInfoEntity.setSubPanelSpecification(editProjectSiteInfoRequest.getSubPanelSpecification());
				permitProjectSiteInfoEntity.setSubPanelBusRatingCombining(editProjectSiteInfoRequest.getSubPanelBusRatingCombining());
				permitProjectSiteInfoEntity.setSubPanelBusRatingCombiningOther(editProjectSiteInfoRequest.getSubPanelBusRatingCombiningOther());
				permitProjectSiteInfoEntity.setSubPanelMainBreakerRatingCombining(editProjectSiteInfoRequest.getSubPanelMainBreakerRatingCombining());
				permitProjectSiteInfoEntity.setSubPanelMainBreakerRatingCombiningOther(editProjectSiteInfoRequest.getSubPanelMainBreakerRatingCombiningOther());
				permitProjectSiteInfoEntity.setSubPanelBreakerAtMainServiceCombining(editProjectSiteInfoRequest.getSubPanelBreakerAtMainServiceCombining());
				permitProjectSiteInfoEntity.setSubPanelBreakerAtMainServiceCombiningOther(editProjectSiteInfoRequest.getSubPanelBreakerAtMainServiceCombiningOther());

				 //	A.B 09-03-2021 CR-PM-2227
				if (checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getAcDisconnectThree())) {
					permitProjectSiteInfoEntity.setAcDisconnectThree(acDisconnectRepo
							.findById(editProjectSiteInfoRequest.getAcDisconnectThree()).orElse(null));
				}else  permitProjectSiteInfoEntity.setAcDisconnectThree(null);
				
				if (checkValueTypesService.isLongPositive(editProjectSiteInfoRequest.getAcDisconnectFour())) {
					permitProjectSiteInfoEntity.setAcDisconnectFour(acDisconnectRepo
							.findById(editProjectSiteInfoRequest.getAcDisconnectFour()).orElse(null));
				}else  permitProjectSiteInfoEntity.setAcDisconnectFour(null);
				

				permitProjectSiteInfoEntityRepos.save(permitProjectSiteInfoEntity);
			}
			
			//Save Racking Notes
			NotAllowedRackingNotesModel projectNotes = permitModel.getNotAllowedRackingNotes();
			if (Boolean.TRUE.equals(projectNotes.getHasNotAllowedRoofRacking())) {
				notAllowedRackingService.insertRackingNotetoADV(projectNotes.getNotAllowedRoofRackingNote(), permitModel.getPermitEntity().getId(), projectNotes.getNotAllowedRoofRackingFileName());
			}else {
				notAllowedRackingService.removeRackingNotetoADV(permitModel.getPermitEntity().getId());
			}
			// End Save BOS tab

			// Save C&C tab
			PermitConduitConductorSectionEntity permitConduitConductorSectionEntity = permitConduitConductorRepo
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			PermitConduitConductorSectionEntitieResult permitConduitConductorSection = permitModel
					.getPermitConduitConductorSection();

			if (permitConduitConductorSection != null) {
				permitConduitConductorSectionEntity.setConductorQty(permitConduitConductorSection.getConductorQty());
				permitConduitConductorSectionEntity
						.setConductorQtyTwo(permitConduitConductorSection.getConductorQtyTwo());
				permitConduitConductorSectionEntity
						.setConductorQtyThree(permitConduitConductorSection.getConductorQtyThree());
				permitConduitConductorSectionEntity
						.setConductorQtyFour(permitConduitConductorSection.getConductorQtyFour());
				permitConduitConductorSectionEntity
						.setConductorQtyFive(permitConduitConductorSection.getConductorQtyFive());
				permitConduitConductorSectionEntity
						.setConductorQtySix(permitConduitConductorSection.getConductorQtySix());
				permitConduitConductorSectionEntity
						.setConductorQtySeven(permitConduitConductorSection.getConductorQtySeven());
				permitConduitConductorSectionEntity
						.setConductorQtyEight(permitConduitConductorSection.getConductorQtyEight());
				permitConduitConductorSectionEntity
						.setConductorQtyNine(permitConduitConductorSection.getConductorQtyNine());
				permitConduitConductorSectionEntity
						.setConductorQtyNineTwo(permitConduitConductorSection.getConductorQtyNineTwo());
				permitConduitConductorSectionEntity
						.setConductorQtyTen(permitConduitConductorSection.getConductorQtyTen());
				permitConduitConductorSectionEntity
						.setConductorQtyEleven(permitConduitConductorSection.getConductorQtyEleven());
				permitConduitConductorSectionEntity
						.setConductorQtyTwelve(permitConduitConductorSection.getConductorQtyTwelve());
				permitConduitConductorSectionEntity
						.setConductorQtyOther(permitConduitConductorSection.getConductorQtyOther());
				permitConduitConductorSectionEntity
						.setConductorQtyTwoOther(permitConduitConductorSection.getConductorQtyTwoOther());
				permitConduitConductorSectionEntity
						.setConductorQtyThreeOther(permitConduitConductorSection.getConductorQtyThreeOther());
				permitConduitConductorSectionEntity
						.setConductorQtyFourOther(permitConduitConductorSection.getConductorQtyFourOther());
				permitConduitConductorSectionEntity
						.setConductorQtyFiveOther(permitConduitConductorSection.getConductorQtyFiveOther());
				permitConduitConductorSectionEntity
						.setConductorQtySixOther(permitConduitConductorSection.getConductorQtySixOther());
				permitConduitConductorSectionEntity
						.setConductorQtySevenOther(permitConduitConductorSection.getConductorQtySevenOther());
				permitConduitConductorSectionEntity
						.setConductorQtyEightOther(permitConduitConductorSection.getConductorQtyEightOther());
				permitConduitConductorSectionEntity
						.setConductorQtyNineOther(permitConduitConductorSection.getConductorQtyNineOther());
				permitConduitConductorSectionEntity
						.setConductorQtyNineTwoOther(permitConduitConductorSection.getConductorQtyNineTwoOther());
				permitConduitConductorSectionEntity
						.setConductorQtyTenOther(permitConduitConductorSection.getConductorQtyTenOther());
				permitConduitConductorSectionEntity
						.setConductorQtyElevenOther(permitConduitConductorSection.getConductorQtyElevenOther());
				permitConduitConductorSectionEntity
						.setConductorQtyTwelveOther(permitConduitConductorSection.getConductorQtyTwelveOther());
				/*******************************
				 * END QTE
				 ****************************************************************/

				////////////////// set Conductor ////////////////
				permitConduitConductorSectionEntity.setConductorSize(permitConduitConductorSection.getConductorSize());
				permitConduitConductorSectionEntity
						.setConductorSizeOther(permitConduitConductorSection.getConductorSizeOther());
				permitConduitConductorSectionEntity.setConductorType(permitConduitConductorSection.getConductorType());
				permitConduitConductorSectionEntity
						.setConductorTypeOther(permitConduitConductorSection.getConductorTypeOther());
				permitConduitConductorSectionEntity
						.setConductorSizeTwo(permitConduitConductorSection.getConductorSizeTwo());
				permitConduitConductorSectionEntity
						.setConductorSizeTwoOther(permitConduitConductorSection.getConductorSizeTwoOther());
				permitConduitConductorSectionEntity
						.setConductorTypeTwo(permitConduitConductorSection.getConductorTypeTwo());
				permitConduitConductorSectionEntity
						.setConductorTypeTwoOther(permitConduitConductorSection.getConductorTypeTwoOther());
				permitConduitConductorSectionEntity
						.setConductorSizeThree(permitConduitConductorSection.getConductorSizeThree());
				permitConduitConductorSectionEntity
						.setConductorSizeThreeOther(permitConduitConductorSection.getConductorSizeThreeOther());
				permitConduitConductorSectionEntity
						.setConductorTypeThree(permitConduitConductorSection.getConductorTypeThree());
				permitConduitConductorSectionEntity
						.setConductorTypeThreeOther(permitConduitConductorSection.getConductorTypeThreeOther());
				permitConduitConductorSectionEntity
						.setConductorSizeFour(permitConduitConductorSection.getConductorSizeFour());
				permitConduitConductorSectionEntity
						.setConductorSizeFourOther(permitConduitConductorSection.getConductorSizeFourOther());
				permitConduitConductorSectionEntity
						.setConductorTypeFour(permitConduitConductorSection.getConductorTypeFour());
				permitConduitConductorSectionEntity
						.setConductorTypeFourOther(permitConduitConductorSection.getConductorTypeFourOther());
				permitConduitConductorSectionEntity
						.setConductorSizeFive(permitConduitConductorSection.getConductorSizeFive());
				permitConduitConductorSectionEntity
						.setConductorSizeFiveOther(permitConduitConductorSection.getConductorSizeFiveOther());
				permitConduitConductorSectionEntity
						.setConductorTypeFive(permitConduitConductorSection.getConductorTypeFive());
				permitConduitConductorSectionEntity
						.setConductorTypeFiveOther(permitConduitConductorSection.getConductorTypeFiveOther());
				permitConduitConductorSectionEntity
						.setConductorSizeSix(permitConduitConductorSection.getConductorSizeSix());
				permitConduitConductorSectionEntity
						.setConductorSizeSixOther(permitConduitConductorSection.getConductorSizeSixOther());
				permitConduitConductorSectionEntity
						.setConductorTypeSix(permitConduitConductorSection.getConductorTypeSix());
				permitConduitConductorSectionEntity
						.setConductorTypeSixOther(permitConduitConductorSection.getConductorTypeSixOther());
				permitConduitConductorSectionEntity
						.setConductorSizeSeven(permitConduitConductorSection.getConductorSizeSeven());
				permitConduitConductorSectionEntity
						.setConductorSizeSevenOther(permitConduitConductorSection.getConductorSizeSevenOther());
				permitConduitConductorSectionEntity
						.setConductorTypeSeven(permitConduitConductorSection.getConductorTypeSeven());
				permitConduitConductorSectionEntity
						.setConductorTypeSevenOther(permitConduitConductorSection.getConductorTypeSevenOther());
				permitConduitConductorSectionEntity
						.setConductorSizeEight(permitConduitConductorSection.getConductorSizeEight());
				permitConduitConductorSectionEntity
						.setConductorSizeEightOther(permitConduitConductorSection.getConductorSizeEightOther());
				permitConduitConductorSectionEntity
						.setConductorTypeEight(permitConduitConductorSection.getConductorTypeEight());
				permitConduitConductorSectionEntity
						.setConductorTypeEightOther(permitConduitConductorSection.getConductorTypeEightOther());
				permitConduitConductorSectionEntity
						.setConductorTypeNineOther(permitConduitConductorSection.getConductorTypeNineOther());
				permitConduitConductorSectionEntity
						.setConductorTypeNineTwoOther(permitConduitConductorSection.getConductorTypeNineTwoOther());
				permitConduitConductorSectionEntity
						.setConductorTypeTenOther(permitConduitConductorSection.getConductorTypeTenOther());
				permitConduitConductorSectionEntity
						.setConductorTypeElevenOther(permitConduitConductorSection.getConductorTypeElevenOther());
				permitConduitConductorSectionEntity
						.setConductorTypeTwelveOther(permitConduitConductorSection.getConductorTypeTwelveOther());
				permitConduitConductorSectionEntity
						.setConductorSizeTwelveOther(permitConduitConductorSection.getConductorSizeTwelveOther());
				permitConduitConductorSectionEntity
						.setConductorSizeNineOther(permitConduitConductorSection.getConductorSizeNineOther());
				permitConduitConductorSectionEntity
						.setConductorSizeNineTwoOther(permitConduitConductorSection.getConductorSizeNineTwoOther());
				permitConduitConductorSectionEntity
						.setConductorSizeTenOther(permitConduitConductorSection.getConductorSizeTenOther());
				permitConduitConductorSectionEntity
						.setConductorSizeElevenOther(permitConduitConductorSection.getConductorSizeElevenOther());
				permitConduitConductorSectionEntity
						.setConductorSizeTwelveOther(permitConduitConductorSection.getConductorSizeTwelveOther());
				permitConduitConductorSectionEntity
						.setConduitTypeNineOther(permitConduitConductorSection.getConduitTypeNineOther());
				permitConduitConductorSectionEntity
						.setConduitTypeNineTwoOther(permitConduitConductorSection.getConduitTypeNineTwoOther());
				permitConduitConductorSectionEntity
						.setConduitTypeTenOther(permitConduitConductorSection.getConduitTypeTenOther());
				permitConduitConductorSectionEntity
						.setConduitTypeElevenOther(permitConduitConductorSection.getConduitTypeElevenOther());
				permitConduitConductorSectionEntity
						.setConduitTypeTwelveOther(permitConduitConductorSection.getConduitTypeTwelveOther());
				permitConduitConductorSectionEntity
						.setConduitSizeNineOther(permitConduitConductorSection.getConduitSizeNineOther());
				permitConduitConductorSectionEntity
						.setConduitSizeNineTwoOther(permitConduitConductorSection.getConduitSizeNineTwoOther());
				permitConduitConductorSectionEntity
						.setConduitSizeTenOther(permitConduitConductorSection.getConduitSizeTenOther());
				permitConduitConductorSectionEntity
						.setConduitSizeElevenOther(permitConduitConductorSection.getConduitSizeElevenOther());
				permitConduitConductorSectionEntity
						.setConduitSizeTwelveOther(permitConduitConductorSection.getConduitSizeTwelveOther());
				permitConduitConductorSectionEntity
						.setConductorTypeTen(permitConduitConductorSection.getConductorTypeTen());
				permitConduitConductorSectionEntity
						.setConductorSizeTen(permitConduitConductorSection.getConductorSizeTen());
				permitConduitConductorSectionEntity
						.setConduitTypeTen(permitConduitConductorSection.getConduitTypeTen());
				permitConduitConductorSectionEntity
						.setConduitSizeTen(permitConduitConductorSection.getConduitSizeTen());
				permitConduitConductorSectionEntity
						.setConductorQtyTen(permitConduitConductorSection.getConductorQtyTen());
				permitConduitConductorSectionEntity
						.setConductorTypeNine(permitConduitConductorSection.getConductorTypeNine());
				permitConduitConductorSectionEntity
						.setConductorTypeNineTwo(permitConduitConductorSection.getConductorTypeNineTwo());
				permitConduitConductorSectionEntity
						.setConductorSizeNine(permitConduitConductorSection.getConductorSizeNine());
				permitConduitConductorSectionEntity
						.setConductorSizeNineTwo(permitConduitConductorSection.getConductorSizeNineTwo());
				permitConduitConductorSectionEntity
						.setConduitTypeNineTwo(permitConduitConductorSection.getConduitTypeNineTwo());
				permitConduitConductorSectionEntity
						.setConduitSizeNine(permitConduitConductorSection.getConduitSizeNine());
				permitConduitConductorSectionEntity
						.setConduitSizeNineTwo(permitConduitConductorSection.getConduitSizeNineTwo());
				permitConduitConductorSectionEntity
						.setConductorQtyNine(permitConduitConductorSection.getConductorQtyNine());
				permitConduitConductorSectionEntity
						.setConductorQtyNineTwo(permitConduitConductorSection.getConductorQtyNineTwo());
				permitConduitConductorSectionEntity
						.setConductorTypeTwelve(permitConduitConductorSection.getConductorTypeTwelve());
				permitConduitConductorSectionEntity
						.setConductorSizeTwelve(permitConduitConductorSection.getConductorSizeTwelve());
				permitConduitConductorSectionEntity
						.setConduitTypeTwelve(permitConduitConductorSection.getConduitTypeTwelve());
				permitConduitConductorSectionEntity
						.setConduitSizeTwelve(permitConduitConductorSection.getConduitSizeTwelve());
				permitConduitConductorSectionEntity
						.setConductorQtyTwelve(permitConduitConductorSection.getConductorQtyTwelve());
				permitConduitConductorSectionEntity
						.setConductorTypeEleven(permitConduitConductorSection.getConductorTypeEleven());
				permitConduitConductorSectionEntity
						.setConductorSizeEleven(permitConduitConductorSection.getConductorSizeEleven());
				permitConduitConductorSectionEntity
						.setConduitTypeEleven(permitConduitConductorSection.getConduitTypeEleven());
				permitConduitConductorSectionEntity
						.setConduitSizeEleven(permitConduitConductorSection.getConduitSizeEleven());
				permitConduitConductorSectionEntity
						.setConductorQtyEleven(permitConduitConductorSection.getConductorQtyEleven());
				permitConduitConductorSectionEntity
						.setConductorQtyTwelve(permitConduitConductorSection.getConductorQtyTwelve());

				////////////////// set Conduit ////////////////

				permitConduitConductorSectionEntity.setConduitSize(permitConduitConductorSection.getConduitSize());
				permitConduitConductorSectionEntity
						.setConduitSizeOther(permitConduitConductorSection.getConduitSizeOther());
				permitConduitConductorSectionEntity.setConduitType(permitConduitConductorSection.getConduitType());
				permitConduitConductorSectionEntity
						.setConduitTypeOther(permitConduitConductorSection.getConduitTypeOther());
				permitConduitConductorSectionEntity
						.setConduitSizeTwo(permitConduitConductorSection.getConduitSizeTwo());
				permitConduitConductorSectionEntity
						.setConduitSizeTwoOther(permitConduitConductorSection.getConduitSizeTwoOther());
				permitConduitConductorSectionEntity
						.setConduitTypeTwo(permitConduitConductorSection.getConduitTypeTwo());
				permitConduitConductorSectionEntity
						.setConduitTypeTwoOther(permitConduitConductorSection.getConduitTypeTwoOther());
				permitConduitConductorSectionEntity
						.setConduitSizeThree(permitConduitConductorSection.getConduitSizeThree());
				permitConduitConductorSectionEntity
						.setConduitSizeThreeOther(permitConduitConductorSection.getConduitSizeThreeOther());
				permitConduitConductorSectionEntity
						.setConduitTypeThree(permitConduitConductorSection.getConduitTypeThree());
				permitConduitConductorSectionEntity
						.setConduitTypeThreeOther(permitConduitConductorSection.getConduitTypeThreeOther());
				permitConduitConductorSectionEntity
						.setConduitSizeFour(permitConduitConductorSection.getConduitSizeFour());
				permitConduitConductorSectionEntity
						.setConduitSizeFourOther(permitConduitConductorSection.getConduitSizeFourOther());
				permitConduitConductorSectionEntity
						.setConduitTypeFour(permitConduitConductorSection.getConduitTypeFour());
				permitConduitConductorSectionEntity
						.setConduitTypeFourOther(permitConduitConductorSection.getConduitTypeFourOther());
				permitConduitConductorSectionEntity
						.setConduitSizeFive(permitConduitConductorSection.getConduitSizeFive());
				permitConduitConductorSectionEntity
						.setConduitSizeFiveOther(permitConduitConductorSection.getConduitSizeFiveOther());
				permitConduitConductorSectionEntity
						.setConduitTypeFive(permitConduitConductorSection.getConduitTypeFive());
				permitConduitConductorSectionEntity
						.setConduitTypeFiveOther(permitConduitConductorSection.getConduitTypeFiveOther());
				permitConduitConductorSectionEntity
						.setConduitSizeSix(permitConduitConductorSection.getConduitSizeSix());
				permitConduitConductorSectionEntity
						.setConduitSizeSixOther(permitConduitConductorSection.getConduitSizeSixOther());
				permitConduitConductorSectionEntity
						.setConduitTypeSix(permitConduitConductorSection.getConduitTypeSix());
				permitConduitConductorSectionEntity
						.setConduitTypeSixOther(permitConduitConductorSection.getConduitTypeSixOther());
				permitConduitConductorSectionEntity
						.setConduitSizeSeven(permitConduitConductorSection.getConduitSizeSeven());
				permitConduitConductorSectionEntity
						.setConduitSizeSevenOther(permitConduitConductorSection.getConduitSizeSevenOther());
				permitConduitConductorSectionEntity
						.setConduitTypeSeven(permitConduitConductorSection.getConduitTypeSeven());
				permitConduitConductorSectionEntity
						.setConduitTypeSevenOther(permitConduitConductorSection.getConduitTypeSevenOther());
				permitConduitConductorSectionEntity
						.setConduitSizeEight(permitConduitConductorSection.getConduitSizeEight());
				permitConduitConductorSectionEntity
						.setConduitSizeEightOther(permitConduitConductorSection.getConduitSizeEightOther());
				permitConduitConductorSectionEntity
						.setConduitTypeEight(permitConduitConductorSection.getConduitTypeEight());
				permitConduitConductorSectionEntity
						.setConduitTypeEightOther(permitConduitConductorSection.getConduitTypeEightOther());
				permitConduitConductorSectionEntity
						.setConduitTypeNine(permitConduitConductorSection.getConduitTypeNine());

				permitConduitConductorSectionEntity
						.setImgSegmentEight(permitConduitConductorSection.getImgSegmentEight());
				permitConduitConductorSectionEntity
						.setImgSegmentFive(permitConduitConductorSection.getImgSegmentFive());
				permitConduitConductorSectionEntity
						.setImgSegmentFour(permitConduitConductorSection.getImgSegmentFour());
				permitConduitConductorSectionEntity
						.setImgSegmentNine(permitConduitConductorSection.getImgSegmentNine());

				permitConduitConductorSectionEntity
						.setImgSegmentNineTwo(permitConduitConductorSection.getImgSegmentNineTwo());
				permitConduitConductorSectionEntity.setImgSegmentOne(permitConduitConductorSection.getImgSegmentOne());
				permitConduitConductorSectionEntity
						.setImgSegmentSeven(permitConduitConductorSection.getImgSegmentSeven());
				permitConduitConductorSectionEntity.setImgSegmentSix(permitConduitConductorSection.getImgSegmentSix());
				permitConduitConductorSectionEntity
						.setImgSegmentThree(permitConduitConductorSection.getImgSegmentThree());
				permitConduitConductorSectionEntity.setImgSegmentTwo(permitConduitConductorSection.getImgSegmentTwo());
				if (permitConduitConductorSection.getQtySegmentEight() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentEight(permitConduitConductorSection.getQtySegmentEight());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentEight(0);
				}
				if (permitConduitConductorSection.getQtySegmentNine() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentNine(permitConduitConductorSection.getQtySegmentNine());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentNine(0);
				}

				if (permitConduitConductorSection.getQtySegmentNineTwo() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentNineTwo(permitConduitConductorSection.getQtySegmentNineTwo());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentNineTwo(0);
				}

				if (permitConduitConductorSection.getQtySegmentTen() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentTen(permitConduitConductorSection.getQtySegmentTen());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentTen(0);
				}

				if (permitConduitConductorSection.getQtySegmentEleven() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentEleven(permitConduitConductorSection.getQtySegmentEleven());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentEleven(0);
				}

				if (permitConduitConductorSection.getQtySegmentFive() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentFive(permitConduitConductorSection.getQtySegmentFive());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentFive(0);
				}
				if (permitConduitConductorSection.getQtySegmentFour() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentFour(permitConduitConductorSection.getQtySegmentFour());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentFour(0);
				}
				if (permitConduitConductorSection.getQtySegmentOne() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentOne(permitConduitConductorSection.getQtySegmentOne());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentOne(0);
				}
				if (permitConduitConductorSection.getQtySegmentSeven() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentSeven(permitConduitConductorSection.getQtySegmentSeven());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentSeven(0);
				}
				if (permitConduitConductorSection.getQtySegmentSix() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentSix(permitConduitConductorSection.getQtySegmentSix());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentSix(0);
				}
				if (permitConduitConductorSection.getQtySegmentThree() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentThree(permitConduitConductorSection.getQtySegmentThree());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentThree(0);
				}
				if (permitConduitConductorSection.getQtySegmentTwo() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentTwo(permitConduitConductorSection.getQtySegmentTwo());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentTwo(0);
				}
				if (permitConduitConductorSection.getQtySegmentTwelve() != null) {
					permitConduitConductorSectionEntity
							.setQtySegmentTwelve(permitConduitConductorSection.getQtySegmentTwelve());
				} else {
					permitConduitConductorSectionEntity.setQtySegmentTwelve(0);
				}
				permitConduitConductorSectionEntity
						.setComponentOrder(permitConduitConductorSection.getComponentOrder());
				permitConduitConductorSectionEntity
						.setMapFromUserInput(permitConduitConductorSection.getMapFromUserInput());
				permitConduitConductorSectionEntity
						.setConductorTypeExisting(permitConduitConductorSection.getConductorTypeExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeExisting(permitConduitConductorSection.getConductorSizeExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeExisting(permitConduitConductorSection.getConduitTypeExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeExisting(permitConduitConductorSection.getConduitSizeExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeTwoExisting(permitConduitConductorSection.getConductorTypeTwoExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeTwoExisting(permitConduitConductorSection.getConductorSizeTwoExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeTwoExisting(permitConduitConductorSection.getConduitTypeTwoExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeTwoExisting(permitConduitConductorSection.getConduitSizeTwoExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeThreeExisting(permitConduitConductorSection.getConductorTypeThreeExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeThreeExisting(permitConduitConductorSection.getConductorSizeThreeExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeThreeExisting(permitConduitConductorSection.getConduitTypeThreeExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeThreeExisting(permitConduitConductorSection.getConduitSizeThreeExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeFourExisting(permitConduitConductorSection.getConductorTypeFourExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeFourExisting(permitConduitConductorSection.getConductorSizeFourExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeFourExisting(permitConduitConductorSection.getConduitTypeFourExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeFourExisting(permitConduitConductorSection.getConduitSizeFourExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeFiveExisting(permitConduitConductorSection.getConductorTypeFiveExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeFiveExisting(permitConduitConductorSection.getConductorSizeFiveExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeFiveExisting(permitConduitConductorSection.getConduitTypeFiveExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeFiveExisting(permitConduitConductorSection.getConduitSizeFiveExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeSixExisting(permitConduitConductorSection.getConductorTypeSixExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeSixExisting(permitConduitConductorSection.getConductorSizeSixExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeSixExisting(permitConduitConductorSection.getConduitTypeSixExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeSixExisting(permitConduitConductorSection.getConduitSizeSixExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeSevenExisting(permitConduitConductorSection.getConductorTypeSevenExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeSevenExisting(permitConduitConductorSection.getConductorSizeSevenExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeSevenExisting(permitConduitConductorSection.getConduitTypeSevenExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeSevenExisting(permitConduitConductorSection.getConduitSizeSevenExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeEightExisting(permitConduitConductorSection.getConductorTypeEightExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeEightExisting(permitConduitConductorSection.getConductorSizeEightExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeEightExisting(permitConduitConductorSection.getConduitTypeEightExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeEightExisting(permitConduitConductorSection.getConduitSizeEightExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeNineExisting(permitConduitConductorSection.getConductorTypeNineExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeNineExisting(permitConduitConductorSection.getConductorSizeNineExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeNineExisting(permitConduitConductorSection.getConduitTypeNineExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeNineTwoExisting(permitConduitConductorSection.getConduitSizeNineTwoExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeTenExisting(permitConduitConductorSection.getConductorTypeTenExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeTenExisting(permitConduitConductorSection.getConductorSizeTenExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeTenExisting(permitConduitConductorSection.getConduitTypeTenExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeTenExisting(permitConduitConductorSection.getConduitSizeTenExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeElevenExisting(permitConduitConductorSection.getConductorTypeElevenExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeElevenExisting(permitConduitConductorSection.getConductorSizeElevenExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeElevenExisting(permitConduitConductorSection.getConduitTypeElevenExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeElevenExisting(permitConduitConductorSection.getConduitSizeElevenExisting());
				permitConduitConductorSectionEntity
						.setConductorTypeTwelveExisting(permitConduitConductorSection.getConductorTypeElevenExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeTwelveExisting(permitConduitConductorSection.getConductorSizeElevenExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeTwelveExisting(permitConduitConductorSection.getConduitTypeElevenExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeTwelveExisting(permitConduitConductorSection.getConduitSizeElevenExisting());
				// S.B CR-3119 06-04-2020
				permitConduitConductorSectionEntity
						.setConductorNeutral(permitConduitConductorSection.getConductorNeutral());
				permitConduitConductorSectionEntity
						.setConductorNeutralTwo(permitConduitConductorSection.getConductorNeutralTwo());
				permitConduitConductorSectionEntity
						.setConductorNeutralThree(permitConduitConductorSection.getConductorNeutralThree());
				permitConduitConductorSectionEntity
						.setConductorNeutralFour(permitConduitConductorSection.getConductorNeutralFour());
				permitConduitConductorSectionEntity
						.setConductorNeutralFive(permitConduitConductorSection.getConductorNeutralFive());
				permitConduitConductorSectionEntity
						.setConductorNeutralSix(permitConduitConductorSection.getConductorNeutralSix());
				permitConduitConductorSectionEntity
						.setConductorNeutralSeven(permitConduitConductorSection.getConductorNeutralSeven());
				permitConduitConductorSectionEntity
						.setConductorNeutralEight(permitConduitConductorSection.getConductorNeutralEight());
				permitConduitConductorSectionEntity
						.setConductorNeutralNine(permitConduitConductorSection.getConductorNeutralNine());
				permitConduitConductorSectionEntity
						.setConductorNeutralNineTwo(permitConduitConductorSection.getConductorNeutralNineTwo());
				permitConduitConductorSectionEntity
						.setConductorNeutralTen(permitConduitConductorSection.getConductorNeutralTen());
				permitConduitConductorSectionEntity
						.setConductorNeutralEleven(permitConduitConductorSection.getConductorNeutralEleven());
				permitConduitConductorSectionEntity
						.setConductorTypeTwelveExisting(permitConduitConductorSection.getConductorTypeTwelveExisting());
				permitConduitConductorSectionEntity
						.setConductorSizeTwelveExisting(permitConduitConductorSection.getConductorSizeTwelveExisting());
				permitConduitConductorSectionEntity
						.setConduitTypeTwelveExisting(permitConduitConductorSection.getConduitTypeTwelveExisting());
				permitConduitConductorSectionEntity
						.setConduitSizeTwelveExisting(permitConduitConductorSection.getConduitSizeTwelveExisting());
				permitConduitConductorSectionEntity
						.setConductorQtyTwelveOther(permitConduitConductorSection.getConductorQtyTwelveOther());
				permitConduitConductorSectionEntity
						.setConductorNeutralTwelve(permitConduitConductorSection.getConductorNeutralTwelve());

				  /* CR-3227-MOD-001 */
				permitConduitConductorSectionEntity.setCircuitEnvironment(permitConduitConductorSection.getCircuitEnvironment());
				permitConduitConductorSectionEntity.setCircuitEnvironmentTwo(permitConduitConductorSection.getCircuitEnvironmentTwo());
				permitConduitConductorSectionEntity.setCircuitEnvironmentThree(permitConduitConductorSection.getCircuitEnvironmentThree());
				permitConduitConductorSectionEntity.setCircuitEnvironmentFour(permitConduitConductorSection.getCircuitEnvironmentFour());
				permitConduitConductorSectionEntity.setCircuitEnvironmentFive(permitConduitConductorSection.getCircuitEnvironmentFive());
				permitConduitConductorSectionEntity.setCircuitEnvironmentSix(permitConduitConductorSection.getCircuitEnvironmentSix());
				permitConduitConductorSectionEntity.setCircuitEnvironmentSeven(permitConduitConductorSection.getCircuitEnvironmentSeven());
				permitConduitConductorSectionEntity.setCircuitEnvironmentEight(permitConduitConductorSection.getCircuitEnvironmentEight());
				permitConduitConductorSectionEntity.setCircuitEnvironmentNine(permitConduitConductorSection.getCircuitEnvironmentNine());
				permitConduitConductorSectionEntity.setCircuitEnvironmentNineTwo(permitConduitConductorSection.getCircuitEnvironmentNineTwo());
				permitConduitConductorSectionEntity.setCircuitEnvironmentTen(permitConduitConductorSection.getCircuitEnvironmentTen());
				permitConduitConductorSectionEntity.setCircuitEnvironmentEleven(permitConduitConductorSection.getCircuitEnvironmentEleven());
				permitConduitConductorSectionEntity.setCircuitEnvironmentTwelve(permitConduitConductorSection.getCircuitEnvironmentTwelve());
				 /* CR-3227-MOD-001 */
				permitConduitConductorSectionEntity.setCircuitEnvironmentChanged(permitConduitConductorSection.getCircuitEnvironmentChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentTwoChanged(permitConduitConductorSection.getCircuitEnvironmentTwoChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentThreeChanged(permitConduitConductorSection.getCircuitEnvironmentThreeChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentFourChanged(permitConduitConductorSection.getCircuitEnvironmentFourChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentFiveChanged(permitConduitConductorSection.getCircuitEnvironmentFiveChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentSixChanged(permitConduitConductorSection.getCircuitEnvironmentSixChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentSevenChanged(permitConduitConductorSection.getCircuitEnvironmentSevenChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentEightChanged(permitConduitConductorSection.getCircuitEnvironmentEightChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentNineChanged(permitConduitConductorSection.getCircuitEnvironmentNineChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentNineTwoChanged(permitConduitConductorSection.getCircuitEnvironmentNineTwoChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentTenChanged(permitConduitConductorSection.getCircuitEnvironmentTenChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentElevenChanged(permitConduitConductorSection.getCircuitEnvironmentElevenChanged());
				permitConduitConductorSectionEntity.setCircuitEnvironmentTwelveChanged(permitConduitConductorSection.getCircuitEnvironmentTwelveChanged());
			    /* CR-3227-MOD-003 */
				permitConduitConductorSectionEntity.setCircuitLengthOne(permitConduitConductorSection.getCircuitLengthOne());
				permitConduitConductorSectionEntity.setCircuitLengthTwo(permitConduitConductorSection.getCircuitLengthTwo());
				permitConduitConductorSectionEntity.setCircuitLengthThree(permitConduitConductorSection.getCircuitLengthThree());
				permitConduitConductorSectionEntity.setCircuitLengthFour(permitConduitConductorSection.getCircuitLengthFour());
				permitConduitConductorSectionEntity.setCircuitLengthFive(permitConduitConductorSection.getCircuitLengthFive());
				permitConduitConductorSectionEntity.setCircuitLengthSix(permitConduitConductorSection.getCircuitLengthSix());
				permitConduitConductorSectionEntity.setCircuitLengthSeven(permitConduitConductorSection.getCircuitLengthSeven());
				permitConduitConductorSectionEntity.setCircuitLengthEight(permitConduitConductorSection.getCircuitLengthEight());
				permitConduitConductorSectionEntity.setCircuitLengthNine(permitConduitConductorSection.getCircuitLengthNine());
				permitConduitConductorSectionEntity.setCircuitLengthNineTwo(permitConduitConductorSection.getCircuitLengthNineTwo());
				permitConduitConductorSectionEntity.setCircuitLengthTen(permitConduitConductorSection.getCircuitLengthTen());
				permitConduitConductorSectionEntity.setCircuitLengthEleven(permitConduitConductorSection.getCircuitLengthEleven());
				permitConduitConductorSectionEntity.setCircuitLengthTwelve(permitConduitConductorSection.getCircuitLengthTwelve());
				
				permitConduitConductorRepo.save(permitConduitConductorSectionEntity);
			}
			// ********************************End Save C&C
			// tab************************************//
			// ********************************Save C&C tab
			// Circuit************************************//

			ConduitConductorCircuitEntity conductorCircuit = conduitConductorCircuitRepo
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			if (conductorCircuit != null) {
				conductorCircuit = new ConduitConductorCircuitEntity();
				int length = 0;
				List<String> conductorQty = new ArrayList<>();
				List<Integer> conductorQtyOther = new ArrayList<>();
				List<String> conductorSize = new ArrayList<>();
				List<String> conductorType = new ArrayList<>();
				List<String> conduitSize = new ArrayList<>();
				List<String> conduitType = new ArrayList<>();
				List<String> conductorSizeOther = new ArrayList<>();
				List<String> conductorTypeOther = new ArrayList<>();
				List<String> conduitSizeOther = new ArrayList<>();
				List<String> conduitTypeOther = new ArrayList<>();
				List<Boolean> conductorTypeExisting = new ArrayList<>();
				List<Boolean> conductorSizeExisting = new ArrayList<>();
				List<Boolean> conduitTypeExisting = new ArrayList<>();
				List<Boolean> conduitSizeExisting = new ArrayList<>();

				if (permitConduitConductorSection.getConductorQty() != null) {
					length = length + 1;
					conductorQty.add(permitConduitConductorSection.getConductorQty());
					conductorQtyOther.add(permitConduitConductorSection.getConductorQtyOther());
					conductorSize.add(permitConduitConductorSection.getConductorSize());
					conductorSizeOther.add(permitConduitConductorSection.getConductorSizeOther());
					conductorType.add(permitConduitConductorSection.getConductorType());
					conductorTypeOther.add(permitConduitConductorSection.getConductorTypeOther());
					conduitSize.add(permitConduitConductorSection.getConduitSize());
					conduitSizeOther.add(permitConduitConductorSection.getConduitSizeOther());
					conduitType.add(permitConduitConductorSection.getConduitType());
					conduitTypeOther.add(permitConduitConductorSection.getConduitTypeOther());
					conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeExisting());
					conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeExisting());
					conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeExisting());
					conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeExisting());

				}
				if (permitConduitConductorSection.getConductorQtyTwo() != null) {
					length = length + 1;
					conductorQty.add(permitConduitConductorSection.getConductorQtyTwo());
					conductorQtyOther.add(permitConduitConductorSection.getConductorQtyTwoOther());
					conductorSize.add(permitConduitConductorSection.getConductorSizeTwo());
					conductorSizeOther.add(permitConduitConductorSection.getConductorSizeTwoOther());
					conductorType.add(permitConduitConductorSection.getConductorTypeTwo());
					conductorTypeOther.add(permitConduitConductorSection.getConductorTypeTwoOther());
					conduitSize.add(permitConduitConductorSection.getConduitSizeTwo());
					conduitSizeOther.add(permitConduitConductorSection.getConduitSizeTwoOther());
					conduitType.add(permitConduitConductorSection.getConduitTypeTwo());
					conduitTypeOther.add(permitConduitConductorSection.getConduitTypeTwoOther());

					conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeTwoExisting());
					conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeTwoExisting());
					conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeTwoExisting());
					conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeTwoExisting());

				}

				if (permitConduitConductorSection.getComponentOrder() != null
						&& permitConduitConductorSection.getComponentOrder().contains("-")) {
					for (int i = 0; i < permitConduitConductorSection.getComponentOrder().split("-").length; i++) {

						if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "DCJBOX")
								&& permitConduitConductorSection.getConductorQtyThree() != null) {
							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtyThree());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtyThreeOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeThree());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeThreeOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeThree());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeThreeOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeThree());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeThreeOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeThree());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeThreeOther());
							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeThreeExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeThreeExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeThreeExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeThreeExisting());
						} else if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "DCC")
								&& permitConduitConductorSection.getConductorQtyFour() != null) {
							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtyFour());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtyFourOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeFour());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeFourOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeFour());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeFourOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeFour());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeFourOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeFour());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeFourOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeFourExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeFourExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeFourExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeFourExisting());

						} else if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "DCD")
								&& permitConduitConductorSection.getConductorQtyFive() != null) {

							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtyFive());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtyFiveOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeFive());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeFiveOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeFive());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeFiveOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeFive());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeFiveOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeFive());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeFiveOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeFiveExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeFiveExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeFiveExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeFiveExisting());

						} else if ((checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "INV")
								|| checkValueTypesService.Equals(
										permitConduitConductorSection.getComponentOrder().split("-")[i], "MINV"))
								&& permitConduitConductorSection.getConductorQtySix() != null) {

							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtySix());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtySixOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeSix());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeSixOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeSix());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeSixOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeSix());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeSixOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeSix());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeSixOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeSixExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeSixExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeSixExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeSixExisting());

						} else if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "ACJBOX")
								&& permitConduitConductorSection.getConductorQtySeven() != null) {
							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtySeven());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtySevenOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeSeven());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeSevenOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeSeven());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeSevenOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeSeven());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeSevenOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeSeven());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeSevenOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeSevenExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeSevenExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeSevenExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeSevenExisting());

						} else if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "ACC")
								&& permitConduitConductorSection.getConductorQtyEight() != null) {

							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtyEight());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtyEightOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeEight());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeEightOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeEight());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeEightOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeEight());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeEightOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeEight());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeEightOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeEightExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeEightExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeEightExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeEightExisting());

						} else if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "ACD")
								&& permitConduitConductorSection.getConductorQtyNine() != null) {

							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtyNine());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtyNineOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeNine());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeNineOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeNine());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeNineOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeNine());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeNineOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeNine());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeNineOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeNineExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeNineExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeNineExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeNineExisting());

						} else if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "ACDTwo")
								&& permitConduitConductorSection.getConductorQtyNineTwo() != null) {

							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtyNineTwo());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtyNineTwoOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeNineTwo());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeNineTwoOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeNineTwo());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeNineTwoOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeNineTwo());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeNineTwoOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeNineTwo());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeNineTwoOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeNineTwoExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeNineTwoExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeNineTwoExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeNineTwoExisting());

						} else if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "PMETER")
								&& permitConduitConductorSection.getConductorQtyTen() != null) {

							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtyTen());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtyTenOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeTen());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeTenOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeTen());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeTenOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeTen());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeTenOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeTen());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeTenOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeTenExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeTenExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeTenExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeTenExisting());

						} else if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "ACSUBPANEL")
								&& permitConduitConductorSection.getConductorQtyEleven() != null) {

							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtyEleven());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtyElevenOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeEleven());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeElevenOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeEleven());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeElevenOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeEleven());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeElevenOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeEleven());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeElevenOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeElevenExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeElevenExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeElevenExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeElevenExisting());

						} else if (checkValueTypesService
								.Equals(permitConduitConductorSection.getComponentOrder().split("-")[i], "TRANSFORMER")
								&& permitConduitConductorSection.getConductorQtyTwelve() != null) {

							length = length + 1;
							conductorQty.add(permitConduitConductorSection.getConductorQtyTwelve());
							conductorQtyOther.add(permitConduitConductorSection.getConductorQtyTwelveOther());
							conductorSize.add(permitConduitConductorSection.getConductorSizeTwelve());
							conductorSizeOther.add(permitConduitConductorSection.getConductorSizeTwelveOther());
							conductorType.add(permitConduitConductorSection.getConductorTypeTwelve());
							conductorTypeOther.add(permitConduitConductorSection.getConductorTypeTwelveOther());
							conduitSize.add(permitConduitConductorSection.getConduitSizeTwelve());
							conduitSizeOther.add(permitConduitConductorSection.getConduitSizeTwelveOther());
							conduitType.add(permitConduitConductorSection.getConduitTypeTwelve());
							conduitTypeOther.add(permitConduitConductorSection.getConduitTypeTwelveOther());

							conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeTwelveExisting());
							conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeTwelveExisting());
							conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeTwelveExisting());
							conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeTwelveExisting());

						}
					}
				} else {

					if (permitConduitConductorSection.getConductorQtyThree() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtyThree());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtyThreeOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeThree());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeThreeOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeThree());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeThreeOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeThree());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeThreeOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeThree());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeThreeOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeThreeExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeThreeExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeThreeExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeThreeExisting());

					}
					if (permitConduitConductorSection.getConductorQtyFour() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtyFour());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtyFourOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeFour());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeFourOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeFour());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeFourOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeFour());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeFourOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeFour());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeFourOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeFourExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeFourExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeFourExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeFourExisting());

					}
					if (permitConduitConductorSection.getConductorQtyFive() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtyFive());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtyFiveOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeFive());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeFiveOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeFive());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeFiveOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeFive());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeFiveOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeFive());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeFiveOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeFiveExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeFiveExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeFiveExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeFiveExisting());

					}
					if (permitConduitConductorSection.getConductorQtySix() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtySix());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtySixOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeSix());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeSixOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeSix());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeSixOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeSix());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeSixOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeSix());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeSixOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeSixExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeSixExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeSixExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeSixExisting());

					}
					if (permitConduitConductorSection.getConductorQtySeven() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtySeven());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtySevenOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeSeven());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeSevenOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeSeven());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeSevenOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeSeven());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeSevenOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeSeven());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeSevenOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeSevenExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeSevenExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeSevenExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeSevenExisting());

					}
					if (permitConduitConductorSection.getConductorQtyEight() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtyEight());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtyEightOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeEight());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeEightOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeEight());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeEightOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeEight());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeEightOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeEight());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeEightOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeEightExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeEightExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeEightExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeEightExisting());

					}
					if (permitConduitConductorSection.getConductorQtyNine() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtyNine());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtyNineOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeNine());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeNineOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeNine());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeNineOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeNine());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeNineOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeNine());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeNineOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeNineExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeNineExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeNineExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeNineExisting());

					}
					if (permitConduitConductorSection.getConductorQtyNineTwo() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtyNineTwo());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtyNineTwoOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeNineTwo());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeNineTwoOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeNineTwo());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeNineTwoOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeNineTwo());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeNineTwoOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeNineTwo());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeNineTwoOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeNineTwoExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeNineTwoExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeNineTwoExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeNineTwoExisting());

					}
					if (permitConduitConductorSection.getConductorQtyTen() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtyTen());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtyTenOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeTen());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeTenOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeTen());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeTenOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeTen());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeTenOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeTen());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeTenOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeTenExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeTenExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeTenExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeTenExisting());

					}
					if (permitConduitConductorSection.getConductorQtyEleven() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtyEleven());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtyElevenOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeEleven());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeElevenOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeEleven());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeElevenOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeEleven());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeElevenOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeEleven());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeElevenOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeElevenExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeElevenExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeElevenExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeElevenExisting());

					}
					if (permitConduitConductorSection.getConductorQtyTwelve() != null) {
						length = length + 1;
						conductorQty.add(permitConduitConductorSection.getConductorQtyTwelve());
						conductorQtyOther.add(permitConduitConductorSection.getConductorQtyTwelveOther());
						conductorSize.add(permitConduitConductorSection.getConductorSizeTwelve());
						conductorSizeOther.add(permitConduitConductorSection.getConductorSizeTwelveOther());
						conductorType.add(permitConduitConductorSection.getConductorTypeTwelve());
						conductorTypeOther.add(permitConduitConductorSection.getConductorTypeTwelveOther());
						conduitSize.add(permitConduitConductorSection.getConduitSizeTwelve());
						conduitSizeOther.add(permitConduitConductorSection.getConduitSizeTwelveOther());
						conduitType.add(permitConduitConductorSection.getConduitTypeTwelve());
						conduitTypeOther.add(permitConduitConductorSection.getConduitTypeTwelveOther());

						conductorTypeExisting.add(permitConduitConductorSection.getConductorTypeTwelveExisting());
						conductorSizeExisting.add(permitConduitConductorSection.getConductorSizeTwelveExisting());
						conduitTypeExisting.add(permitConduitConductorSection.getConduitTypeTwelveExisting());
						conduitSizeExisting.add(permitConduitConductorSection.getConduitSizeTwelveExisting());

					}

				}
				conductorCircuit.setCircuitLength(length);
				if (!conductorQty.isEmpty()) {
					conductorCircuit.setConductorQty(conductorQty.get(0));
				}
				if (!conductorSize.isEmpty()) {
					conductorCircuit.setConductorSize(conductorSize.get(0));
				}
				if (!conductorType.isEmpty()) {
					conductorCircuit.setConductorType(conductorType.get(0));
				}
				if (!conduitSize.isEmpty()) {
					conductorCircuit.setConduitSize(conduitSize.get(0));
				}
				if (!conduitType.isEmpty()) {
					conductorCircuit.setConduitType(conduitType.get(0));
				}
				if (!conductorQtyOther.isEmpty()) {
					conductorCircuit.setConductorQtyOther(conductorQtyOther.get(0));
				}
				if (!conductorSizeOther.isEmpty()) {
					conductorCircuit.setConductorSizeOther(conductorSizeOther.get(0));
				}
				if (!conductorTypeOther.isEmpty()) {
					conductorCircuit.setConductorTypeOther(conductorTypeOther.get(0));
				}
				if (!conduitSizeOther.isEmpty()) {
					conductorCircuit.setConduitSizeOther(conduitSizeOther.get(0));
				}
				if (!conduitTypeOther.isEmpty()) {
					conductorCircuit.setConduitTypeOther(conduitTypeOther.get(0));
				}
				if (!conductorTypeExisting.isEmpty()) {
					conductorCircuit.setConductorTypeExisting(conductorTypeExisting.get(0));
				}
				if (!conductorSizeExisting.isEmpty()) {
					conductorCircuit.setConductorSizeExisting(conductorSizeExisting.get(0));
				}
				if (!conduitTypeExisting.isEmpty()) {
					conductorCircuit.setConduitTypeExisting(conduitTypeExisting.get(0));
				}
				if (!conduitSizeExisting.isEmpty()) {
					conductorCircuit.setConduitSizeExisting(conduitSizeExisting.get(0));
				}

				if (length > 1) {
					if (conductorQty.size() > 1) {
						conductorCircuit.setConductorQtyTwo(conductorQty.get(1));
					}
					if (conductorQtyOther.size() > 1) {
						conductorCircuit.setConductorQtyTwoOther(conductorQtyOther.get(1));
					}
					if (conductorSize.size() > 1) {
						conductorCircuit.setConductorSizeTwo(conductorSize.get(1));
					}

					if (conductorSizeOther.size() > 1) {
						conductorCircuit.setConductorSizeTwoOther(conductorSizeOther.get(1));
					}
					if (conductorType.size() > 1) {
						conductorCircuit.setConductorTypeTwo(conductorType.get(1));
					}
					if (conductorTypeOther.size() > 1) {
						conductorCircuit.setConductorTypeTwoOther(conductorTypeOther.get(1));
					}
					if (conduitSize.size() > 1) {
						conductorCircuit.setConduitSizeTwo(conduitSize.get(1));
					}
					if (conduitSizeOther.size() > 1) {
						conductorCircuit.setConduitSizeTwoOther(conduitSizeOther.get(1));
					}
					if (conduitType.size() > 1) {
						conductorCircuit.setConduitTypeTwo(conduitType.get(1));
					}
					if (conduitTypeOther.size() > 1) {
						conductorCircuit.setConduitTypeTwoOther(conduitTypeOther.get(1));
					}
					if (conductorTypeExisting.size() > 1) {
						conductorCircuit.setConductorTypeTwoExisting(conductorTypeExisting.get(1));
					}
					if (conductorSizeExisting.size() > 1) {
						conductorCircuit.setConductorSizeTwoExisting(conductorSizeExisting.get(1));
					}
					if (conduitTypeExisting.size() > 1) {
						conductorCircuit.setConduitTypeTwoExisting(conduitTypeExisting.get(1));
					}
					if (conduitSizeExisting.size() > 1) {
						conductorCircuit.setConduitSizeTwoExisting(conduitSizeExisting.get(1));
					}

				}
				if (length > 2) {
					if (conductorQty.size() > 2) {
						conductorCircuit.setConductorQtyThree(conductorQty.get(2));
					}
					if (conductorQtyOther.size() > 2) {
						conductorCircuit.setConductorQtyThreeOther(conductorQtyOther.get(2));
					}
					if (conductorSize.size() > 2) {
						conductorCircuit.setConductorSizeThree(conductorSize.get(2));
					}
					if (conductorSizeOther.size() > 2) {
						conductorCircuit.setConductorSizeThreeOther(conductorSizeOther.get(2));
					}
					if (conductorType.size() > 2) {
						conductorCircuit.setConductorTypeThree(conductorType.get(2));
					}
					if (conductorTypeOther.size() > 2) {
						conductorCircuit.setConductorTypeThreeOther(conductorTypeOther.get(2));
					}
					if (conduitSize.size() > 2) {
						conductorCircuit.setConduitSizeThree(conduitSize.get(2));
					}
					if (conduitSizeOther.size() > 2) {
						conductorCircuit.setConduitSizeThreeOther(conduitSizeOther.get(2));
					}
					if (conduitType.size() > 2) {
						conductorCircuit.setConduitTypeThree(conduitType.get(2));
					}
					if (conduitTypeOther.size() > 2) {
						conductorCircuit.setConduitTypeThreeOther(conduitTypeOther.get(2));
					}
					if (conductorTypeExisting.size() > 2) {
						conductorCircuit.setConductorTypeThreeExisting(conductorTypeExisting.get(2));
					}
					if (conductorSizeExisting.size() > 2) {
						conductorCircuit.setConductorSizeThreeExisting(conductorSizeExisting.get(2));
					}
					if (conduitTypeExisting.size() > 2) {
						conductorCircuit.setConduitTypeThreeExisting(conduitTypeExisting.get(2));
					}
					if (conduitSizeExisting.size() > 2) {
						conductorCircuit.setConduitSizeThreeExisting(conduitSizeExisting.get(2));
					}
				}
				if (length > 3) {
					if (conductorQty.size() > 3) {
						conductorCircuit.setConductorQtyFour(conductorQty.get(3));
					}
					if (conductorQtyOther.size() > 3) {
						conductorCircuit.setConductorQtyFourOther(conductorQtyOther.get(3));
					}

					if (conductorSize.size() > 3) {
						conductorCircuit.setConductorSizeFour(conductorSize.get(3));
					}
					if (conductorSizeOther.size() > 3) {
						conductorCircuit.setConductorSizeFourOther(conductorSizeOther.get(3));
					}
					if (conductorType.size() > 3) {
						conductorCircuit.setConductorTypeFour(conductorType.get(3));
					}
					if (conductorTypeOther.size() > 3) {
						conductorCircuit.setConductorTypeFourOther(conductorTypeOther.get(3));
					}
					if (conduitSize.size() > 3) {
						conductorCircuit.setConduitSizeFour(conduitSize.get(3));
					}
					if (conduitSizeOther.size() > 3) {
						conductorCircuit.setConduitSizeFourOther(conduitSizeOther.get(3));
					}
					if (conduitType.size() > 3) {
						conductorCircuit.setConduitTypeFour(conduitType.get(3));
					}
					if (conduitTypeOther.size() > 3) {
						conductorCircuit.setConduitTypeFourOther(conduitTypeOther.get(3));
					}
					if (conductorTypeExisting.size() > 3) {
						conductorCircuit.setConductorTypeFourExisting(conductorTypeExisting.get(3));
					}
					if (conductorSizeExisting.size() > 3) {
						conductorCircuit.setConductorSizeFourExisting(conductorSizeExisting.get(3));
					}
					if (conduitTypeExisting.size() > 3) {
						conductorCircuit.setConduitTypeFourExisting(conduitTypeExisting.get(3));
					}
					if (conduitSizeExisting.size() > 3) {
						conductorCircuit.setConduitSizeFourExisting(conduitSizeExisting.get(3));
					}
				}
				if (length > 4) {
					if (conductorQty.size() > 4) {
						conductorCircuit.setConductorQtyFive(conductorQty.get(4));
					}
					if (conductorQtyOther.size() > 4) {
						conductorCircuit.setConductorQtyFiveOther(conductorQtyOther.get(4));
					}
					if (conductorSize.size() > 4) {
						conductorCircuit.setConductorSizeFive(conductorSize.get(4));
					}
					if (conductorSizeOther.size() > 4) {
						conductorCircuit.setConductorSizeFiveOther(conductorSizeOther.get(4));
					}
					if (conductorType.size() > 4) {
						conductorCircuit.setConductorTypeFive(conductorType.get(4));
					}
					if (conductorTypeOther.size() > 4) {
						conductorCircuit.setConductorTypeFiveOther(conductorTypeOther.get(4));
					}
					if (conduitSize.size() > 4) {
						conductorCircuit.setConduitSizeFive(conduitSize.get(4));
					}
					if (conduitSizeOther.size() > 4) {
						conductorCircuit.setConduitSizeFiveOther(conduitSizeOther.get(4));
					}
					if (conduitType.size() > 4) {
						conductorCircuit.setConduitTypeFive(conduitType.get(4));
					}
					if (conduitTypeOther.size() > 4) {
						conductorCircuit.setConduitTypeFiveOther(conduitTypeOther.get(4));
					}
					if (conductorTypeExisting.size() > 4) {
						conductorCircuit.setConductorTypeFiveExisting(conductorTypeExisting.get(4));
					}
					if (conductorSizeExisting.size() > 4) {
						conductorCircuit.setConductorSizeFiveExisting(conductorSizeExisting.get(4));
					}
					if (conduitTypeExisting.size() > 4) {
						conductorCircuit.setConduitTypeFiveExisting(conduitTypeExisting.get(4));
					}
					if (conduitSizeExisting.size() > 4) {
						conductorCircuit.setConduitSizeFiveExisting(conduitSizeExisting.get(4));
					}

				}
				if (length > 5) {
					if (conductorQty.size() > 5) {
						conductorCircuit.setConductorQtySix(conductorQty.get(5));
					}
					if (conductorQtyOther.size() > 5) {
						conductorCircuit.setConductorQtySixOther(conductorQtyOther.get(5));
					}
					if (conductorSize.size() > 5) {
						conductorCircuit.setConductorSizeSix(conductorSize.get(5));
					}
					if (conductorSizeOther.size() > 5) {
						conductorCircuit.setConductorSizeSixOther(conductorSizeOther.get(5));
					}
					if (conductorType.size() > 5) {
						conductorCircuit.setConductorTypeSix(conductorType.get(5));
					}
					if (conductorTypeOther.size() > 5) {
						conductorCircuit.setConductorTypeSixOther(conductorTypeOther.get(5));
					}
					if (conduitSize.size() > 5) {
						conductorCircuit.setConduitSizeSix(conduitSize.get(5));
					}
					if (conduitSizeOther.size() > 5) {
						conductorCircuit.setConduitSizeSixOther(conduitSizeOther.get(5));
					}
					if (conduitType.size() > 5) {
						conductorCircuit.setConduitTypeSix(conduitType.get(5));
					}
					if (conduitTypeOther.size() > 5) {
						conductorCircuit.setConduitTypeSixOther(conduitTypeOther.get(5));
					}
					if (conductorTypeExisting.size() > 5) {
						conductorCircuit.setConductorTypeSixExisting(conductorTypeExisting.get(5));
					}
					if (conductorSizeExisting.size() > 5) {
						conductorCircuit.setConductorSizeSixExisting(conductorSizeExisting.get(5));
					}
					if (conduitTypeExisting.size() > 5) {
						conductorCircuit.setConduitTypeSixExisting(conduitTypeExisting.get(5));
					}
					if (conduitSizeExisting.size() > 5) {
						conductorCircuit.setConduitSizeSixExisting(conduitSizeExisting.get(5));
					}
				}
				if (length > 6) {
					if (conductorQty.size() > 6) {
						conductorCircuit.setConductorQtySeven(conductorQty.get(6));
					}
					if (conductorQtyOther.size() > 6) {
						conductorCircuit.setConductorQtySevenOther(conductorQtyOther.get(6));
					}
					if (conductorSize.size() > 6) {
						conductorCircuit.setConductorSizeSeven(conductorSize.get(6));
					}
					if (conductorSizeOther.size() > 6) {
						conductorCircuit.setConductorSizeSevenOther(conductorSizeOther.get(6));
					}
					if (conductorType.size() > 6) {
						conductorCircuit.setConductorTypeSeven(conductorType.get(6));
					}
					if (conductorTypeOther.size() > 6) {
						conductorCircuit.setConductorTypeSevenOther(conductorTypeOther.get(6));
					}
					if (conduitSize.size() > 6) {
						conductorCircuit.setConduitSizeSeven(conduitSize.get(6));
					}
					if (conduitSizeOther.size() > 6) {
						conductorCircuit.setConduitSizeSevenOther(conduitSizeOther.get(6));
					}
					if (conduitType.size() > 6) {
						conductorCircuit.setConduitTypeSeven(conduitType.get(6));
					}
					if (conduitTypeOther.size() > 6) {
						conductorCircuit.setConduitTypeSevenOther(conduitTypeOther.get(6));
					}
					if (conductorTypeExisting.size() > 6) {
						conductorCircuit.setConductorTypeSevenExisting(conductorTypeExisting.get(6));
					}
					if (conductorSizeExisting.size() > 6) {
						conductorCircuit.setConductorSizeSevenExisting(conductorSizeExisting.get(6));
					}
					if (conduitTypeExisting.size() > 6) {
						conductorCircuit.setConduitTypeSevenExisting(conduitTypeExisting.get(6));
					}
					if (conduitSizeExisting.size() > 6) {
						conductorCircuit.setConduitSizeSevenExisting(conduitSizeExisting.get(6));
					}
				}
				if (length > 7) {
					if (conductorQty.size() > 7) {
						conductorCircuit.setConductorQtyEight(conductorQty.get(7));
					}
					if (conductorQtyOther.size() > 7) {
						conductorCircuit.setConductorQtyEightOther(conductorQtyOther.get(7));
					}

					if (conductorSize.size() > 7) {
						conductorCircuit.setConductorSizeEight(conductorSize.get(7));
					}
					if (conductorSizeOther.size() > 7) {
						conductorCircuit.setConductorSizeEightOther(conductorSizeOther.get(7));
					}
					if (conductorType.size() > 7) {
						conductorCircuit.setConductorTypeEight(conductorType.get(7));
					}
					if (conductorTypeOther.size() > 7) {
						conductorCircuit.setConductorTypeEightOther(conductorTypeOther.get(7));
					}
					if (conduitSize.size() > 7) {
						conductorCircuit.setConduitSizeEight(conduitSize.get(7));
					}
					if (conduitSizeOther.size() > 7) {
						conductorCircuit.setConduitSizeEightOther(conduitSizeOther.get(7));
					}
					if (conduitType.size() > 7) {
						conductorCircuit.setConduitTypeEight(conduitType.get(7));
					}
					if (conduitTypeOther.size() > 7) {
						conductorCircuit.setConduitTypeEightOther(conduitTypeOther.get(7));
					}
					if (conductorTypeExisting.size() > 7) {
						conductorCircuit.setConductorTypeEightExisting(conductorTypeExisting.get(7));
					}
					if (conductorSizeExisting.size() > 7) {
						conductorCircuit.setConductorSizeEightExisting(conductorSizeExisting.get(7));
					}
					if (conduitTypeExisting.size() > 7) {
						conductorCircuit.setConduitTypeEightExisting(conduitTypeExisting.get(7));
					}
					if (conduitSizeExisting.size() > 7) {
						conductorCircuit.setConduitSizeEightExisting(conduitSizeExisting.get(7));
					}
				}
				if (length > 8) {
					if (conductorQty.size() > 8) {
						conductorCircuit.setConductorQtyNine(conductorQty.get(8));
					}
					if (conductorQtyOther.size() > 8) {
						conductorCircuit.setConductorQtyNineOther(conductorQtyOther.get(8));
					}

					if (conductorSize.size() > 8) {
						conductorCircuit.setConductorSizeNine(conductorSize.get(8));
					}
					if (conductorSizeOther.size() > 8) {
						conductorCircuit.setConductorSizeNineOther(conductorSizeOther.get(8));
					}
					if (conductorType.size() > 8) {
						conductorCircuit.setConductorTypeNine(conductorType.get(8));
					}
					if (conductorTypeOther.size() > 8) {
						conductorCircuit.setConductorTypeNineOther(conductorTypeOther.get(8));
					}
					if (conduitSize.size() > 8) {
						conductorCircuit.setConduitSizeNine(conduitSize.get(8));
					}
					if (conduitSizeOther.size() > 8) {
						conductorCircuit.setConduitSizeNineOther(conduitSizeOther.get(8));
					}
					if (conduitType.size() > 8) {
						conductorCircuit.setConduitTypeNine(conduitType.get(8));
					}
					if (conduitTypeOther.size() > 8) {
						conductorCircuit.setConduitTypeNineOther(conduitTypeOther.get(8));
					}
					if (conductorTypeExisting.size() > 8) {
						conductorCircuit.setConductorTypeNineExisting(conductorTypeExisting.get(8));
					}
					if (conductorSizeExisting.size() > 8) {
						conductorCircuit.setConductorSizeNineExisting(conductorSizeExisting.get(8));
					}
					if (conduitTypeExisting.size() > 8) {
						conductorCircuit.setConduitTypeNineExisting(conduitTypeExisting.get(8));
					}
					if (conduitSizeExisting.size() > 8) {
						conductorCircuit.setConduitSizeNineExisting(conduitSizeExisting.get(8));
					}
				}
				if (length > 9) {
					if (conductorQty.size() > 9) {
						conductorCircuit.setConductorQtyNineTwo(conductorQty.get(9));
					}
					if (conductorQtyOther.size() > 9) {
						conductorCircuit.setConductorQtyNineTwoOther(conductorQtyOther.get(9));
					}

					if (conductorSize.size() > 9) {
						conductorCircuit.setConductorSizeNineTwo(conductorSize.get(9));
					}
					if (conductorSizeOther.size() > 9) {
						conductorCircuit.setConductorSizeNineTwoOther(conductorSizeOther.get(9));
					}
					if (conductorType.size() > 9) {
						conductorCircuit.setConductorTypeNineTwo(conductorType.get(9));
					}
					if (conductorTypeOther.size() > 9) {
						conductorCircuit.setConductorTypeNineTwoOther(conductorTypeOther.get(9));
					}
					if (conduitSize.size() > 9) {
						conductorCircuit.setConduitSizeNineTwo(conduitSize.get(9));
					}
					if (conduitSizeOther.size() > 9) {
						conductorCircuit.setConduitSizeNineTwoOther(conduitSizeOther.get(9));
					}
					if (conduitType.size() > 9) {
						conductorCircuit.setConduitTypeNineTwo(conduitType.get(9));
					}
					if (conduitTypeOther.size() > 9) {
						conductorCircuit.setConduitTypeNineTwoOther(conduitTypeOther.get(9));
					}
					if (conductorTypeExisting.size() > 9) {
						conductorCircuit.setConductorTypeNineTwoExisting(conductorTypeExisting.get(9));
					}
					if (conductorSizeExisting.size() > 9) {
						conductorCircuit.setConductorSizeNineTwoExisting(conductorSizeExisting.get(9));
					}
					if (conduitTypeExisting.size() > 9) {
						conductorCircuit.setConduitTypeNineTwoExisting(conduitTypeExisting.get(9));
					}
					if (conduitSizeExisting.size() > 9) {
						conductorCircuit.setConduitSizeNineTwoExisting(conduitSizeExisting.get(9));
					}
				}
				if (length > 10) {
					if (conductorQty.size() > 10) {
						conductorCircuit.setConductorQtyTen(conductorQty.get(10));
					}
					if (conductorQtyOther.size() > 10) {
						conductorCircuit.setConductorQtyTenOther(conductorQtyOther.get(10));
					}

					if (conductorSize.size() > 10) {
						conductorCircuit.setConductorSizeTen(conductorSize.get(10));
					}
					if (conductorSizeOther.size() > 10) {
						conductorCircuit.setConductorSizeTenOther(conductorSizeOther.get(10));
					}
					if (conductorType.size() > 10) {
						conductorCircuit.setConductorTypeTen(conductorType.get(10));
					}
					if (conductorTypeOther.size() > 10) {
						conductorCircuit.setConductorTypeTenOther(conductorTypeOther.get(10));
					}
					if (conduitSize.size() > 10) {
						conductorCircuit.setConduitSizeTen(conduitSize.get(10));
					}
					if (conduitSizeOther.size() > 10) {
						conductorCircuit.setConduitSizeTenOther(conduitSizeOther.get(10));
					}
					if (conduitType.size() > 10) {
						conductorCircuit.setConduitTypeTen(conduitType.get(10));
					}
					if (conduitTypeOther.size() > 10) {
						conductorCircuit.setConduitTypeTenOther(conduitTypeOther.get(10));
					}
					if (conductorTypeExisting.size() > 10) {
						conductorCircuit.setConductorTypeTenExisting(conductorTypeExisting.get(10));
					}
					if (conductorSizeExisting.size() > 10) {
						conductorCircuit.setConductorSizeTenExisting(conductorSizeExisting.get(10));
					}
					if (conduitTypeExisting.size() > 10) {
						conductorCircuit.setConduitTypeTenExisting(conduitTypeExisting.get(10));
					}
					if (conduitSizeExisting.size() > 10) {
						conductorCircuit.setConduitSizeTenExisting(conduitSizeExisting.get(10));
					}
				}
				if (length > 11) {
					if (conductorQty.size() > 11) {
						conductorCircuit.setConductorQtyEleven(conductorQty.get(11));
					}
					if (conductorQtyOther.size() > 11) {
						conductorCircuit.setConductorQtyElevenOther(conductorQtyOther.get(11));
					}

					if (conductorSize.size() > 11) {
						conductorCircuit.setConductorSizeEleven(conductorSize.get(11));
					}
					if (conductorSizeOther.size() > 11) {
						conductorCircuit.setConductorSizeElevenOther(conductorSizeOther.get(11));
					}
					if (conductorType.size() > 11) {
						conductorCircuit.setConductorTypeEleven(conductorType.get(11));
					}
					if (conductorTypeOther.size() > 11) {
						conductorCircuit.setConductorTypeElevenOther(conductorTypeOther.get(11));
					}
					if (conduitSize.size() > 11) {
						conductorCircuit.setConduitSizeEleven(conduitSize.get(11));
					}
					if (conduitSizeOther.size() > 11) {
						conductorCircuit.setConduitSizeElevenOther(conduitSizeOther.get(11));
					}
					if (conduitType.size() > 11) {
						conductorCircuit.setConduitTypeEleven(conduitType.get(11));
					}
					if (conduitTypeOther.size() > 11) {
						conductorCircuit.setConduitTypeElevenOther(conduitTypeOther.get(11));
					}
					if (conductorTypeExisting.size() > 11) {
						conductorCircuit.setConductorTypeElevenExisting(conductorTypeExisting.get(11));
					}
					if (conductorSizeExisting.size() > 11) {
						conductorCircuit.setConductorSizeElevenExisting(conductorSizeExisting.get(11));
					}
					if (conduitTypeExisting.size() > 11) {
						conductorCircuit.setConduitTypeElevenExisting(conduitTypeExisting.get(11));
					}
					if (conduitSizeExisting.size() > 11) {
						conductorCircuit.setConduitSizeElevenExisting(conduitSizeExisting.get(11));
					}
				}
				if (length > 12) {
					if (conductorQty.size() > 12) {
						conductorCircuit.setConductorQtyTwelve(conductorQty.get(12));
					}
					if (conductorQtyOther.size() > 12) {
						conductorCircuit.setConductorQtyTwelveOther(conductorQtyOther.get(12));
					}

					if (conductorSize.size() > 12) {
						conductorCircuit.setConductorSizeTwelve(conductorSize.get(12));
					}
					if (conductorSizeOther.size() > 12) {
						conductorCircuit.setConductorSizeTwelveOther(conductorSizeOther.get(12));
					}
					if (conductorType.size() > 12) {
						conductorCircuit.setConductorTypeTwelve(conductorType.get(12));
					}
					if (conductorTypeOther.size() > 12) {
						conductorCircuit.setConductorTypeTwelveOther(conductorTypeOther.get(12));
					}
					if (conduitSize.size() > 12) {
						conductorCircuit.setConduitSizeTwelve(conduitSize.get(12));
					}
					if (conduitSizeOther.size() > 12) {
						conductorCircuit.setConduitSizeTwelveOther(conduitSizeOther.get(12));
					}
					if (conduitType.size() > 12) {
						conductorCircuit.setConduitTypeTwelve(conduitType.get(12));
					}
					if (conduitTypeOther.size() > 12) {
						conductorCircuit.setConduitTypeTwelveOther(conduitTypeOther.get(12));
					}
					if (conductorTypeExisting.size() > 12) {
						conductorCircuit.setConductorTypeTwelveExisting(conductorTypeExisting.get(12));
					}
					if (conductorSizeExisting.size() > 12) {
						conductorCircuit.setConductorSizeTwelveExisting(conductorSizeExisting.get(12));
					}
					if (conduitTypeExisting.size() > 12) {
						conductorCircuit.setConduitTypeTwelveExisting(conduitTypeExisting.get(12));
					}
					if (conduitSizeExisting.size() > 12) {
						conductorCircuit.setConduitSizeTwelveExisting(conduitSizeExisting.get(12));
					}
				}
				conduitConductorCircuitRepo.save(conductorCircuit);
			}

			// End Save C&C tab Circuit

			// Save Additional Info
			PermitAdditionalInfoEntity permitAdditionalInfoEntity = permitAdditionalInfoRepo
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			PermitAdditionalInfoEntityResult editAdditionalInfoRequest = permitModel.getPermitAdditionalInfoEntity();
			if (editAdditionalInfoRequest != null) {
				permitAdditionalInfoEntity.setFormatSize(editAdditionalInfoRequest.getFormatSize());
				/**************************************************************************************/
				permitAdditionalInfoEntity.setBatteryStorage(editAdditionalInfoRequest.getBatteryStorage());
				permitAdditionalInfoEntity.setTiltLegs(editAdditionalInfoRequest.getTiltLegs());
				/**************************************
				 * SR 002
				 *********************************************/

				permitAdditionalInfoEntity.setBattery(editAdditionalInfoRequest.getBattery());
				permitAdditionalInfoEntity.setTiltLegsMod(editAdditionalInfoRequest.getTiltLegsMod());
				permitAdditionalInfoEntity.setGridTiedOrStandalone(editAdditionalInfoRequest.getGridTiedOrStandalone());
				/************************************
				 * END SR 002
				 *******************************************/

				/************************************
				 * CR_003
				 *******************************************/
				permitAdditionalInfoEntity.setInformationCovered(editAdditionalInfoRequest.getInformationCovered());
				permitAdditionalInfoEntity
						.setRequiredElectricalStamp(editAdditionalInfoRequest.getRequiredElectricalStamp());
				permitAdditionalInfoEntity.setUploadComments(editAdditionalInfoRequest.getUploadComments());
				//S.B 04-06-2021 CR-PM-3869 Existing Solar QA Added to Portal Intake Form
				permitAdditionalInfoEntity.setExistSolarSystem(editAdditionalInfoRequest.getExistSolarSystem());
				permitAdditionalInfoEntity.setExistpvmodule(editAdditionalInfoRequest.getExistpvmodule());
				permitAdditionalInfoEntity.setExistmoduleqty(editAdditionalInfoRequest.getExistmoduleqty());
				permitAdditionalInfoEntity.setExistinvertermodel(editAdditionalInfoRequest.getExistinvertermodel());
				permitAdditionalInfoEntity.setExistinverterqty(editAdditionalInfoRequest.getExistinverterqty());
				permitAdditionalInfoEntity.setExistinvertermodelTwo(editAdditionalInfoRequest.getExistinvertermodelTwo());
				permitAdditionalInfoEntity.setExistinverterqtyTwo(editAdditionalInfoRequest.getExistinverterqtyTwo());
				permitAdditionalInfoEntity.setExistmicromodel(editAdditionalInfoRequest.getExistmicromodel());
				permitAdditionalInfoEntity.setExistmicroqty(editAdditionalInfoRequest.getExistmicroqty());
				permitAdditionalInfoEntity.setExistacdisconnect(editAdditionalInfoRequest.getExistacdisconnect());
				permitAdditionalInfoEntity.setExistpvmeter(editAdditionalInfoRequest.getExistpvmeter());
				permitAdditionalInfoEntity.setAcdpvmorientation(editAdditionalInfoRequest.getAcdpvmorientation());
				permitAdditionalInfoEntity.setPointofconnection(editAdditionalInfoRequest.getPointofconnection());
				permitAdditionalInfoEntity.setPocwillbeat(editAdditionalInfoRequest.getPocwillbeat());
				permitAdditionalInfoEntity.setSizebackfed(editAdditionalInfoRequest.getSizebackfed());
				permitAdditionalInfoEntity.setOtherPointConnection(editAdditionalInfoRequest.getOtherPointConnection());
				permitAdditionalInfoEntity.setOtherpocwillbeat(editAdditionalInfoRequest.getOtherpocwillbeat());
				permitAdditionalInfoEntity.setCombiningpvin(editAdditionalInfoRequest.getCombiningpvin());
				permitAdditionalInfoEntity.setExistingInverterTech(editAdditionalInfoRequest.getExistingInverterTech());
				
				/************************************
				 * END CR_003
				 *******************************************/

				permitAdditionalInfoRepo.save(permitAdditionalInfoEntity);
			}
			// End Save Additional Info

			// Save Layout Sketch

			PermitLayoutEntity permitLayoutEntity = permitLayoutRepo
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			PermitLayoutSketchResult editPermitLayoutEntityRequest = permitModel.getLayoutSketch();
			if (editPermitLayoutEntityRequest != null) {
				permitLayoutEntity.setConduitRun(editPermitLayoutEntityRequest.getConduitRun());
				permitLayoutEntity.setShowConduitRoofAsHeight(editPermitLayoutEntityRequest.getShowConduitRoofAsHeight());
				permitLayoutEntity.setSketchNote(editPermitLayoutEntityRequest.getSketchNote());
				permitLayoutEntity.setUploadCommentsAddInfo(editPermitLayoutEntityRequest.getUploadCommentsAddInfo());
				permitLayoutEntity.setUploadCommentsLayout(editPermitLayoutEntityRequest.getUploadCommentsLayout());
				permitLayoutEntity.setIgnoreVents(editPermitLayoutEntityRequest.getIgnoreVents());
				permitLayoutEntity.setFiresetbacks(editPermitLayoutEntityRequest.getFiresetbacks());
				permitLayoutEntity.setFiresetbacksNote(editPermitLayoutEntityRequest.getFiresetbacksNote());
				permitLayoutEntity.setFireVariance(editPermitLayoutEntityRequest.getFireVariance());
				permitLayoutEntity.setFireVarianceNote(editPermitLayoutEntityRequest.getFireVarianceNote());
				permitLayoutEntity.setModulesEncroaching(editPermitLayoutEntityRequest.getModulesEncroaching());
				permitLayoutRepo.save(permitLayoutEntity);
			}
			// End Save Layout Sketch

			// Save ADV Projects
			PermitAdvEntity permitAdvEntity = permitAdvInputsRepo
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			PermitAdvEntityResult editPermitAdvRequest = permitModel.getPermitAdvEntityResult();

			if (editPermitAdvRequest != null) {
				permitAdvEntity.setGoogleImage(editPermitAdvRequest.getGoogleImage());
				permitAdvEntity.setMapImage(editPermitAdvRequest.getMapImage());

				/**************************************************************************/
				permitAdvEntity.setWindSpeed(editPermitAdvRequest.getWindSpeed());
				permitAdvEntity.setSnowLoad(editPermitAdvRequest.getSnowLoad());

				if (checkValueTypesService.NotEquals(editPermitAdvRequest.getCustomersServiceAgreementIDNumber(),
						"null")
						&& checkValueTypesService.NotEquals(editPermitAdvRequest.getCustomersServiceAgreementIDNumber(),
								"")) {
					permitAdvEntity.setCustomersServiceAgreementIDNumber(
							editPermitAdvRequest.getCustomersServiceAgreementIDNumber());
				}
				if (checkValueTypesService.NotEquals(editPermitAdvRequest.getCustomersRateSchedule(), "null")
						&& checkValueTypesService.NotEquals(editPermitAdvRequest.getCustomersRateSchedule(), "")) {
					permitAdvEntity.setCustomersRateSchedule(editPermitAdvRequest.getCustomersRateSchedule());
				}
				if (editPermitAdvRequest.getEngineeringFirm() != null) {
					permitAdvEntity.setEngineeringFirm(editPermitAdvRequest.getEngineeringFirm());
				}
				if (editPermitAdvRequest.getCustomersAccountNumber() != null) {
					permitAdvEntity.setCustomersAccountNumber(editPermitAdvRequest.getCustomersAccountNumber());
				}

				if (editPermitAdvRequest.getCustomerName() != null) {
					permitAdvEntity.setCustomerName(editPermitAdvRequest.getCustomerName());
				}
				// CR-1231- Add new field Other wind speed
				if (editPermitAdvRequest.getWindSpeedOther() != null) {
					permitAdvEntity.setWindSpeedOther(editPermitAdvRequest.getWindSpeedOther());
				}
				// CR-1231- Add new field Other snow load
				if (editPermitAdvRequest.getSnowLoadOther() != null) {
					permitAdvEntity.setSnowLoadOther(editPermitAdvRequest.getSnowLoadOther());
				}

				// CR-1181- Update Edit Project Inputs
				permitAdvEntity.setModuleLayout(editPermitAdvRequest.getModuleLayout());
				permitAdvEntity.setModuleLayoutOther(editPermitAdvRequest.getModuleLayoutOther());
				permitAdvEntity.setSizeOfPipe(editPermitAdvRequest.getSizeOfPipe());
				permitAdvEntity.setSizeOfPipeOther(editPermitAdvRequest.getSizeOfPipeOther());
				permitAdvEntity.setThicknessOfPipe(editPermitAdvRequest.getThicknessOfPipe());
				permitAdvEntity.setThicknessOfPipeOther(editPermitAdvRequest.getThicknessOfPipeOther());
				permitAdvEntity.setBracedUnbraced(editPermitAdvRequest.getBracedUnbraced());
				permitAdvEntity.setFootingDiameter(editPermitAdvRequest.getFootingDiameter());
				permitAdvEntity.setFootingDiameterOther(editPermitAdvRequest.getFootingDiameterOther());
				/**************************************************************************/
				if (editPermitAdvRequest.getUploadCommentsGoogle() != null) {
					permitAdvEntity.setUploadCommentsGoogle(editPermitAdvRequest.getUploadCommentsGoogle());
				}

				if (editPermitAdvRequest.getUploadCommentsNearMap() != null) {
					permitAdvEntity.setUploadCommentsNearMap(editPermitAdvRequest.getUploadCommentsNearMap());
				}

				// ******* CR-1580 New 3-Line Diagram Sheet Mapping **********//

				permitAdvEntity.setOpenTldLibrary(editPermitAdvRequest.getOpenTldLibrary());
				permitAdvEntity.setTldShortList(editPermitAdvRequest.getTldShortList());
				permitAdvEntity.setTldList(editPermitAdvRequest.getTldList());
				permitAdvEntity.setrSheetList(editPermitAdvRequest.getrSheetList());
				permitAdvEntity.setBracedUnbracedOther(editPermitAdvRequest.getBracedUnbracedOther());
				permitAdvEntity.setGoogleZoom(editPermitAdvRequest.getGoogleZoom());
				permitAdvInputsRepo.save(permitAdvEntity);
			}
			// End Save ADV Projects

			// Save Weather Information
			PermtiWeatherEntityResult editWeatherEntityRequest = permitModel.getPermtiWeatherEntityResult();
			PermitWeatherEntity permitWeatherEntity = permitWeatherRepo
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			if (permitWeatherEntity != null) {

				permitWeatherEntity.setElevation(editWeatherEntityRequest.getElevation());
				permitWeatherEntity.setExtremeMinimum(editWeatherEntityRequest.getExtremeMinimum());
				/***************************************************************/
				permitWeatherEntity
						.setQuatrePourCentAverageHigh(editWeatherEntityRequest.getQuatrePourCentAverageHigh());
				permitWeatherEntity.setDeuxPourCentAverageHigh(editWeatherEntityRequest.getDeuxPourCentAverageHigh());
				permitWeatherEntity
						.setQuatrePourCentAvHighOther(editWeatherEntityRequest.getQuatrePourCentAvHighOther());
				permitWeatherEntity
						.setDeuxPourCentAverageHighOther(editWeatherEntityRequest.getDeuxPourCentAverageHighOther());
				permitWeatherEntity.setExtremeMinimumOther(editWeatherEntityRequest.getExtremeMinimumOther());
				/***************************************************************/

				permitWeatherRepo.save(permitWeatherEntity);

			}
			// End Save Weather Information

			String googleDriveFilePath = pathRepo.findGoogleDriveFilePath();

			// Save Permit
			PermitEntity permitEntity = permitRepo.findById(permitModel.getPermitEntity().getId()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(permitEntity.getAuthentificationEntity());

			if (checkValueTypesService.Equals(permitModel.getPermitEntity().getAuthentificationEntity().getEmail(),
					"arij@nuagetechnologies-tn.com")
					|| !checkValueTypesService.contains(
							permitModel.getPermitEntity().getAuthentificationEntity().getEmail(),
							"nuagetechnologies-tn")) {
				
				if (!checkValueTypesService.isStringNotEmpty(permitEntity.getProjectName())
						&& checkValueTypesService.isStringNotEmpty(projectCommercialName)) {

					File sourceFile = new File(googleDriveFilePath + ownerFolderName + "/"
							+ permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName());
					File destFile = new File(googleDriveFilePath + ownerFolderName + "/"
							+ projectCommercialName);
					if (sourceFile.exists() && !destFile.exists())
						sourceFile.renameTo(destFile);

				} else if (checkValueTypesService.isStringNotEmpty(permitEntity.getProjectName())
						&& !checkValueTypesService.isStringNotEmpty(projectCommercialName)) {

					File sourceFile = new File(
							googleDriveFilePath + ownerFolderName + "/" + permitEntity.getProjectName());
					File destFile = new File(googleDriveFilePath + ownerFolderName + "/"
							+ homeOwnLastName + ", "
							+ homeOwnName);
					if (sourceFile.exists() && !destFile.exists())
						sourceFile.renameTo(destFile);

				} else if (checkValueTypesService.isStringNotEmpty(permitEntity.getProjectName())
						&& checkValueTypesService.isStringNotEmpty(projectCommercialName)
						&& !checkValueTypesService.Equals(permitEntity.getProjectName(),
								projectCommercialName)) {

					File sourceFile = new File(
							googleDriveFilePath + ownerFolderName + "/" + permitEntity.getProjectName());
					File destFile = new File(googleDriveFilePath + ownerFolderName + "/"
							+ projectCommercialName);
					if (sourceFile.exists() && !destFile.exists())
						sourceFile.renameTo(destFile);

				} else if (!checkValueTypesService.isStringNotEmpty(projectCommercialName)) {
					if (!checkValueTypesService.Equals(
							permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName(),
							homeOwnLastName + ", "
									+ homeOwnName)) {

						File sourceFile = new File(googleDriveFilePath + ownerFolderName + "/"
								+ permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName());
						File destFile = new File(googleDriveFilePath + ownerFolderName + "/"
								+ homeOwnLastName + ", "
								+ homeOwnName);
						if (sourceFile.exists() && !destFile.exists())
							sourceFile.renameTo(destFile);
					}
				}

			}

			permitEntity.setHomeOwnName(homeOwnName);
			permitEntity.setHomeOwnLastName(homeOwnLastName);
			permitEntity.setProjectName(projectCommercialName);
			permitEntity.setAdvancement(permitModel.getPermitEntity().getAdvancement());

			TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
			Calendar updateDate = Calendar.getInstance();
			permitEntity.setUpdateDate(updateDate.getTime());

			if (permitEntity.isSubmitted() && permitEntity.isSubmitted() == true) {
				if (checkValueTypesService.NotEquals(permitEntity.getStatus(), "RFI Pending")) {
					permitEntity.setStatus("In Progress");
				}
			} else {
				if (checkValueTypesService.NotEquals(permitEntity.getStatus(), "RFI Pending")) {
					permitEntity.setStatus("Draft");
				}
			}
			permitEntity.setIsOnHold(false);
			// 07-17-2019: M.A : NUATN-PR-1067
			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorType(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSize(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitType(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSize(), "EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistModule(), true)) {
				permitEntity.setExistModule(false);
			}
			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeTwo(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeTwo(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeTwo(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeTwo(), "EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistOptimizer(), true)) {
				permitEntity.setExistOptimizer(false);
			}
			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeThree(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeThree(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeThree(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeThree(),
							"EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistdcJunctionBox(), true)) {
				permitEntity.setExistdcJunctionBox(false);
			}

			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeFour(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeFour(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeFour(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeFour(), "EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistdcDcCombiner(), true)) {
				permitEntity.setExistdcDcCombiner(false);
			}

			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeFive(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeFive(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeFive(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeFive(), "EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistdcDcdisconnect(), true)) {
				permitEntity.setExistdcDcdisconnect(false);
			}

			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeSix(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeSix(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeSix(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeSix(), "EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistInverter(), true)) {
				permitEntity.setExistInverter(false);
			}

			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeSeven(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeSeven(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeSeven(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeSeven(),
							"EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistAcJunctionBox(), true)) {
				permitEntity.setExistAcJunctionBox(false);
			}

			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeEight(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeEight(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeEight(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeEight(),
							"EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistAcCombiner(), true)) {
				permitEntity.setExistAcCombiner(false);
			}

			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeNine(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeNine(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeNine(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeNine(), "EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistAcDisconnect(), true)) {
				permitEntity.setExistAcDisconnect(false);
			}

			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeNine(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeNine(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeNine(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeNine(), "EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistAcDisconnect(), true)) {
				permitEntity.setExistAcDisconnect(false);
			}

			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeTen(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeTen(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeTen(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeTen(), "EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistProductionMeter(), true)) {
				permitEntity.setExistProductionMeter(false);
			}

			if (!checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorTypeEleven(), "EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConductorSizeEleven(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitTypeEleven(),
							"EXIST")
					&& !checkValueTypesService.Equals(permitConduitConductorSectionEntity.getConduitSizeEleven(),
							"EXIST")
					&& checkValueTypesService.Equals(permitEntity.getExistSubPanel(), true)) {
				permitEntity.setExistSubPanel(false);
			}
			
			//S.B CR-3182 Notify ADV Team for Project Delay 23/06/2021
			if(Integer.parseInt(permitEntity.getAdvancement()) >= 80 && checkValueTypesService.Equals(permitEntity.getStatus(), "Draft")) {
				if(permitEntity.getSaveCount() != null)
					permitEntity.setSaveCount(permitEntity.getSaveCount()+1);
				else 
					permitEntity.setSaveCount(1);
				if (permitEntity.getSaveCount() == 1)
					projectReminder(permitModel.getPermitEntity().getId());
			}
			
			permitRepo.save(permitEntity);
			// End Save Permit

			// Save Engineer Information
			PermitEngineerEntity permitEngineerEntity = permitEngineerRepo
					.findByPermitEntityId(permitModel.getPermitEntity().getId());
			PermitEngineerEntityResult editPermitEngineerEntityRequest = permitModel.getPermitEngineerEntityResult();

			if (permitEngineerEntity != null) {
				permitEngineerEntity
						.setApplicablEngineering(editPermitEngineerEntityRequest.getApplicableEngineering());
				permitEngineerEntity.setName(editPermitEngineerEntityRequest.getName());
				permitEngineerEntity.setEmail(editPermitEngineerEntityRequest.getEmail());
				permitEngineerEntity.setMobile(editPermitEngineerEntityRequest.getMobile());
				permitEngineerEntity.setPhone(editPermitEngineerEntityRequest.getPhone());
				permitEngineerEntity.setLicenceNumber(editPermitEngineerEntityRequest.getLicenceNumber());
				permitEngineerEntity.setLicenceType(editPermitEngineerEntityRequest.getLicenceType());
				permitEngineerEntity.setCity(editPermitEngineerEntityRequest.getCity());
				permitEngineerEntity.setState(editPermitEngineerEntityRequest.getState());
				permitEngineerEntity.setCodePostale(editPermitEngineerEntityRequest.getCodePostale());
				permitEngineerEntity.setEngineeredBy(editPermitEngineerEntityRequest.getEngineeredBy());
				permitEngineerEntity
						.setDetermineModification(editPermitEngineerEntityRequest.getDetermineModification());
				permitEngineerEntity.setIsShingles(editPermitEngineerEntityRequest.getIsShingles());
				permitEngineerEntity.setIndicateLayers(editPermitEngineerEntityRequest.getIndicateLayers());
				permitEngineerEntity.setMpptTrachers(editPermitEngineerEntityRequest.getMpptTrachers());
				permitEngineerEntity.setNumberMpptTrachers(editPermitEngineerEntityRequest.getNumberMpptTrachers());
				permitEngineerEntity.setIsTransformless(editPermitEngineerEntityRequest.getIsTransformless());
				permitEngineerEntity.setIsCombiner(editPermitEngineerEntityRequest.getIsCombiner());
				permitEngineerEntity.setOverhangArea(editPermitEngineerEntityRequest.getOverhangArea());
				permitEngineerEntity.setRoofPitch(editPermitEngineerEntityRequest.getRoofPitch());
				permitEngineerEntity.setAdressIng(editPermitEngineerEntityRequest.getAdressIng());

				permitEngineerRepo.save(permitEngineerEntity);
			}
			// **************** End Save  ESS System ****************//
			
			
			// **************** Start Save ESS System ****************//
			PermitEnergyBatterySystem permitEnergyBatterySystem = energyBatterySystemRepo.findByProjectId(permitModel.getPermitEntity().getId());
			
			permitEnergyBatterySystem.setTypeGridTied(permitModel.getPermitEnergyBatterySystem().getTypeGridTied());
			permitEnergyBatterySystem.setAtsIncluded(permitModel.getPermitEnergyBatterySystem().getAtsIncluded());
			permitEnergyBatterySystem.setDcDisconnectIncluded(permitModel.getPermitEnergyBatterySystem().getDcDisconnectIncluded());
			permitEnergyBatterySystem.setAcDisconnectIncluded(permitModel.getPermitEnergyBatterySystem().getAcDisconnectIncluded());
			permitEnergyBatterySystem.setGeneratorIncluded(permitModel.getPermitEnergyBatterySystem().getGeneratorIncluded());
			permitEnergyBatterySystem.setRsdConnected(permitModel.getPermitEnergyBatterySystem().getRsdConnected());
			permitEnergyBatterySystem.setGeneratorStatus(permitModel.getPermitEnergyBatterySystem().getGeneratorStatus());
			permitEnergyBatterySystem.setFuelType(permitModel.getPermitEnergyBatterySystem().getFuelType());
			permitEnergyBatterySystem.setFuelDistributionPipeType(permitModel.getPermitEnergyBatterySystem().getFuelDistributionPipeType());
			permitEnergyBatterySystem.setFuelDistributionPipeTypeOther(permitModel.getPermitEnergyBatterySystem().getFuelDistributionPipeTypeOther());
			permitEnergyBatterySystem.setPipeSize(permitModel.getPermitEnergyBatterySystem().getPipeSize());
			permitEnergyBatterySystem.setPipeSizeOther(permitModel.getPermitEnergyBatterySystem().getPipeSizeOther());
			permitEnergyBatterySystem.setEssSpecificationComment(permitModel.getPermitEnergyBatterySystem().getEssSpecificationComment());
			permitEnergyBatterySystem.getBatteries().clear();

			if (Boolean.TRUE.equals(permitModel.getPermitAdditionalInfoEntity().getBatteryStorage()) && permitModel.getPermitEnergyBatterySystem().getBatteries() != null) {
				List<ProjectBatteryDto> list = removeDuplicateBattery(permitModel.getPermitEnergyBatterySystem().getBatteries());
				for (ProjectBatteryDto battery : list) {
					if (checkValueTypesService.isLongPositive(battery.getBattery())) {
						Battery b = batteryRepo.findById(battery.getBattery()).orElseThrow(
								() -> new ResourceNotFoundException("Entity not found for this id :" + battery.getBattery()));
						permitEnergyBatterySystem.addBattery(b, battery.getBatteryQuantity());
						
					}
				}
			}
			
			if (Boolean.TRUE.equals(permitModel.getPermitEnergyBatterySystem().getAtsIncluded()) && checkValueTypesService.isLongPositive(permitModel.getPermitEnergyBatterySystem().getIdAts())) {
				ATS ats = atsRepo.findById(permitModel.getPermitEnergyBatterySystem().getIdAts()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + permitModel.getPermitEnergyBatterySystem().getIdAts()));
				permitEnergyBatterySystem.setIdAts(ats);
				permitEnergyBatterySystem.setQtyAts(permitModel.getPermitEnergyBatterySystem().getQtyAts());
				
				if (checkValueTypesService.isLongPositive(permitModel.getPermitEnergyBatterySystem().getIdSecondAts())) {
					ATS secondats = atsRepo.findById(permitModel.getPermitEnergyBatterySystem().getIdSecondAts()).orElseThrow(
							() -> new ResourceNotFoundException("Entity not found for this id :" + permitModel.getPermitEnergyBatterySystem().getIdSecondAts()));
					permitEnergyBatterySystem.setIdSecondAts(secondats);
					permitEnergyBatterySystem.setQtySecondAts(permitModel.getPermitEnergyBatterySystem().getQtySecondAts());
				} else {
					permitEnergyBatterySystem.setIdSecondAts(null);
					permitEnergyBatterySystem.setQtySecondAts(null);
				}
			}else {
				permitEnergyBatterySystem.setIdAts(null);
				permitEnergyBatterySystem.setQtyAts(null);
				permitEnergyBatterySystem.setIdSecondAts(null);
				permitEnergyBatterySystem.setQtySecondAts(null);
			}
			
			if (Boolean.TRUE.equals(permitModel.getPermitEnergyBatterySystem().getGeneratorIncluded()) && checkValueTypesService.isLongPositive(permitModel.getPermitEnergyBatterySystem().getIdGenerator())) {
				Generator generator = generatorRepo.findById(permitModel.getPermitEnergyBatterySystem().getIdGenerator()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + permitModel.getPermitEnergyBatterySystem().getIdGenerator()));
				permitEnergyBatterySystem.setIdGenerator(generator);
			}else permitEnergyBatterySystem.setIdGenerator(null);
			
			if (Boolean.TRUE.equals(permitModel.getPermitEnergyBatterySystem().getAcDisconnectIncluded()) && checkValueTypesService.isLongPositive(permitModel.getPermitEnergyBatterySystem().getIdAcDisconnect())) {
				ACDisconnect ac = acDisconnectRepo.findById(permitModel.getPermitEnergyBatterySystem().getIdAcDisconnect()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + permitModel.getPermitEnergyBatterySystem().getIdAcDisconnect()));
				permitEnergyBatterySystem.setIdAcDisconnect(ac);
				permitEnergyBatterySystem.setQtyAcd(permitModel.getPermitEnergyBatterySystem().getQtyAcd());
				permitEnergyBatterySystem.setQtySecondAcd(permitModel.getPermitEnergyBatterySystem().getQtySecondAcd());
				
				if (checkValueTypesService.isLongPositive(permitModel.getPermitEnergyBatterySystem().getIdSecondAcDisconnect())) {
					ACDisconnect secondac = acDisconnectRepo.findById(permitModel.getPermitEnergyBatterySystem().getIdSecondAcDisconnect()).orElseThrow(
							() -> new ResourceNotFoundException("Entity not found for this id :" + permitModel.getPermitEnergyBatterySystem().getIdSecondAcDisconnect()));
					permitEnergyBatterySystem.setIdSecondAcDisconnect(secondac);
					permitEnergyBatterySystem.setQtySecondAcd(permitModel.getPermitEnergyBatterySystem().getQtySecondAcd());
				} else {
					permitEnergyBatterySystem.setIdSecondAcDisconnect(null);
					permitEnergyBatterySystem.setQtySecondAcd(null);
				}
			}else {
				permitEnergyBatterySystem.setIdAcDisconnect(null);
				permitEnergyBatterySystem.setQtyAcd(null);
				permitEnergyBatterySystem.setIdSecondAcDisconnect(null);
				permitEnergyBatterySystem.setQtySecondAcd(null);
			}
			
			if (Boolean.TRUE.equals(permitModel.getPermitEnergyBatterySystem().getDcDisconnectIncluded()) && checkValueTypesService.isLongPositive(permitModel.getPermitEnergyBatterySystem().getIdDcDisconnect())) {
				DCCombinerDisconnectEntity dc = dCCombinerDisconnectEntityRepo.findById(permitModel.getPermitEnergyBatterySystem().getIdDcDisconnect()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + permitModel.getPermitEnergyBatterySystem().getIdDcDisconnect()));
				permitEnergyBatterySystem.setIdDcDisconnect(dc);
			}else permitEnergyBatterySystem.setIdDcDisconnect(null);
			
			energyBatterySystemRepo.save(permitEnergyBatterySystem);
			
			// **************** Custom files Comments ****************//
			//A.B CR-806
			for (CustomUpload u : permitModel.getCustomUpload()) {
				if(checkValueTypesService.isStringNotEmpty(u.getComment())) {
					ProjectCustomFiles upload = customFilesRepo.findByProjectIdAndCustomUploadId(permitModel.getPermitEntity().getId(), u.getId());
					upload.setComment(u.getComment());
					customFilesRepo.save(upload);
				}
			}
			

			
			//***************** Get ESS Config Nodes & Connectors *********************//
			essConfiguration.saveNodes(permitModel.getEssNodesModel(), permitEntity);
			essConfiguration.saveConnectors(permitModel.getEssConnectorsModel(), permitEntity);
			
			// **************** End Save Engineer Information ****************//
			
			historicActivity.recordActivity(idUser, ipUser, timeZone,
					"Save Permit as Draft;" + permitModel.getPermitEntity().getId() + ";Edit success", true, numTab,
					sessionId, openDate);
			
			// F.B 2021-10-11 CR-2394
			
			AuthentificationEntity userCon = userRepo.findById(idUser).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" + idUser));
					
			PermitEntity project = permitRepo.findById(permitModel.getPermitEntity().getId()).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" + permitModel.getPermitEntity().getId()));
			
			project.setUpdatedBy(userCon);
			permitRepo.save(project);
			
			
			// A.B 08-23: CR-2847 - Save Portal Docs under Google Drive File Stream
			if (checkValueTypesService.Equals(permitModel.getPermitEntity().getAuthentificationEntity().getEmail(),
					"pm@nuagetechnologies-tn.com")
					|| !checkValueTypesService.contains(
							permitModel.getPermitEntity().getAuthentificationEntity().getEmail(),
							"nuagetechnologies-tn")) {
			

				String googleFileStreamPath = googleDriveFilePath;
				if (!new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle).exists()) {
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle).mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Contractor communication")
							.mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/ALL AHJ Doc's").mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Delivered to Client")
							.mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Drafting").mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Drafting/Final Drafting Pkg")
							.mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Drafting/To Drafter")
							.mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Drafting/Misc Drafting")
							.mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Engineering").mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Plan Set Drafts").mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Portal Downloads").mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Team Project Folder")
							.mkdirs();
					new File(googleFileStreamPath + ownerFolderName + "/" + projectTitle + "/Interconnection").mkdirs();
				}

			}

			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			traiterException(e, projectName, projectOwner, ownerMail);
			if (permitModel != null && permitModel.getPermitEntity() != null) {
				historicActivity.recordActivity(idUser, ipUser, timeZone,
						"Save Permit as Draft;" + permitModel.getPermitEntity().getId() + ";Edit failed", false, numTab,
						sessionId, openDate);
				
				AuthentificationEntity userCon = userRepo.findById(idUser).orElseThrow(
						() -> new ResourceNotFoundException("User not found for this id :" + idUser));
						
				PermitEntity project = permitRepo.findById(permitModel.getPermitEntity().getId()).orElseThrow(
						() -> new ResourceNotFoundException("Permit not found for this id :" + permitModel.getPermitEntity().getId()));
				
				project.setUpdatedBy(userCon);
				permitRepo.save(project);

			}
			return "Fail";

		}

	}

	/*
	 * CR-906 delete layout sketch documents
	 */
	public String deleteLayoutDoc(Long idPermit) {

		try {

			String directory = pathRepo.findFilePath() + idPermit + "/sketch";
			File file = new File(directory);
			if (file.isDirectory()) {
				for (File deleteMe : file.listFiles()) {
					String filename = deleteMe.getName();
					if (filename.startsWith("SFOne")) {
						deleteMe.delete();
					}
				}
			}
			List<NoteSketchFiles> files = noteSketchFilesRepo.findByPermitEntityIdAndFileType(idPermit, 10);
			noteSketchFilesRepo.deleteAll(files);
			return "Success";

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";

		}

	}

	/*
	 * CR-906 get checkbox ask again value
	 */
	public Boolean getAskAgainValue(Long idPermit) {

		try {
			Boolean askAgain = permitLayoutRepo.findAskAgain(idPermit);
			return checkValueTypesService.Equals(askAgain, true);
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}

	/*
	 * CR-906 update checkbox ask again value
	 */
	public Boolean updateAskAgainValue(Long idPermit, Boolean askAgain) {

		try {
			if (idPermit != null) {
				PermitLayoutEntity permitLayoutEntity = permitLayoutRepo.findByPermitEntityId(idPermit);
				permitLayoutEntity.setAskAgain(askAgain);
				permitLayoutRepo.save(permitLayoutEntity);
				return askAgain;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}

	/*
	 * CR-906 get Module dimensions
	 */
	public String getModuleDimensions(Long moduleId) {

		try {
			String dimensions = "";
			Cmodulev2 module = moduleRepo.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			dimensions = module.getLength() + '"' + " x " + module.getWidth() + '"' + " x " + module.getDepth() + '"';
			return dimensions;

		} catch (Exception e) {
			e.printStackTrace();
			return "";

		}

	}

	/*
	 * CR-906 save drafter Information
	 */
	public String saveDrafterInformation(DrafterInformationModel drafterInfo) {
		PermitEntity permit = new PermitEntity();
		try {

			DrafterInformationEntity drafterInformationEntity = drafterInformationRepo
					.findByIdPermitId(drafterInfo.getIdPermit());
			permit = permitRepo.findById(drafterInfo.getIdPermit()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));

			if (drafterInformationEntity != null) {
				drafterInformationEntity.setDimenrequirOnArr(drafterInfo.getDimenrequirOnArr());
				drafterInformationEntity.setDraftSepSitePlan(drafterInfo.getDraftSepSitePlan());
				drafterInformationEntity.setNeedForArackingLayD(drafterInfo.getNeedForArackingLayD());
				drafterInformationEntity.setRoofFeatCallOut(drafterInfo.getRoofFeatCallOut());
				drafterInformationEntity.setShowingTransfLocation(drafterInfo.getShowingTransfLocation());
				drafterInformationEntity.setIdPermit(permit);
				drafterInformationRepo.save(drafterInformationEntity);
			} else {
				DrafterInformationEntity drafterInformationEntityTwo = new DrafterInformationEntity();
				drafterInformationEntityTwo.setDimenrequirOnArr(drafterInfo.getDimenrequirOnArr());
				drafterInformationEntityTwo.setDraftSepSitePlan(drafterInfo.getDraftSepSitePlan());
				drafterInformationEntityTwo.setNeedForArackingLayD(drafterInfo.getNeedForArackingLayD());
				drafterInformationEntityTwo.setRoofFeatCallOut(drafterInfo.getRoofFeatCallOut());
				drafterInformationEntityTwo.setShowingTransfLocation(drafterInfo.getShowingTransfLocation());
				drafterInformationEntityTwo.setIdPermit(permit);
				drafterInformationRepo.save(drafterInformationEntityTwo);
			}

			permit.setStatus("RevDraft");
			permitRepo.save(permit);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	public String saveDrafterData(Long idPermit, PermitDrafterDataResult editPermitDrafterDataRequest) {

		// **************************Save Drafter
		// Data************************************//
		try {
			PermitDrafterDataEntity permitDrafterDataEntity = permitDrafterDataRepo.findByPermitEntityId(idPermit);

			if (editPermitDrafterDataRequest != null) {
				permitDrafterDataEntity.setScale(editPermitDrafterDataRequest.getChooseScale());
				permitDrafterDataEntity
						.setScalerackingLayout(editPermitDrafterDataRequest.getChooseScaleRackingLayout());
				permitDrafterDataEntity
						.setScaleelectricalLayout(editPermitDrafterDataRequest.getChooseScaleElectricalLayout());
				permitDrafterDataEntity
						.setTotalArraySectionCount(editPermitDrafterDataRequest.getTotalArraySectionCount());
				permitDrafterDataEntity
						.setTotalRoofSquareFootage(editPermitDrafterDataRequest.getTotalRoofSquareFootage());
				permitDrafterDataEntity
						.setUploadCommentsAutoCad(editPermitDrafterDataRequest.getUploadCommentsAutoCad());
				permitDrafterDataEntity
						.setUploadCommentsElectrical(editPermitDrafterDataRequest.getUploadCommentsElectrical());
				permitDrafterDataEntity.setUploadCommentsParcel(editPermitDrafterDataRequest.getUploadCommentsParcel());
				permitDrafterDataEntity
						.setUploadCommentsPlacard(editPermitDrafterDataRequest.getUploadCommentsPlacard());
				permitDrafterDataEntity.setUploadCommentsPV(editPermitDrafterDataRequest.getUploadCommentsPV());
				permitDrafterDataEntity
						.setUploadCommentsRacking(editPermitDrafterDataRequest.getUploadCommentsRacking());
				permitDrafterDataEntity.setStanchionQuantity(editPermitDrafterDataRequest.getStanchionQuantity());
				permitDrafterDataEntity.setChooseScaleOther(editPermitDrafterDataRequest.getChooseScaleOther());
				permitDrafterDataEntity
						.setChooseScaleArrayLayout(editPermitDrafterDataRequest.getChooseScaleArrayLayout());
				permitDrafterDataEntity
						.setChooseScaleArrayOther(editPermitDrafterDataRequest.getChooseScaleArrayOther());
				permitDrafterDataEntity
						.setChooseScaleRackingOther(editPermitDrafterDataRequest.getChooseScaleRackingOther());
				permitDrafterDataEntity
						.setChooseScaleElectricalOther(editPermitDrafterDataRequest.getChooseScaleElectricalOther());
				permitDrafterDataEntity.setCustomizeScale(editPermitDrafterDataRequest.getCustomizeScale());
				permitDrafterDataEntity.setScalePV101(editPermitDrafterDataRequest.getScalePV101());
				permitDrafterDataEntity.setScalePV101Other(editPermitDrafterDataRequest.getScalePV101Other());
				
				permitDrafterDataRepo.save(permitDrafterDataEntity);
			}

			return "success";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "error";
		}
		// **************************End Save Drafter
		// Data************************************//

	}

	/*
	 * CR-906 Get drafter Information
	 */
	public DrafterInformationModel getDrafterInformation(Long idPermit) {
		try {
			DrafterInformationEntity drafterInformationEntity = drafterInformationRepo.findByIdPermitId(idPermit);
			if (drafterInformationEntity != null) {
				return new DrafterInformationModel(drafterInformationEntity.getId(), drafterInformationEntity.getNeedForArackingLayD(), drafterInformationEntity.getRoofFeatCallOut(),
						drafterInformationEntity.getDraftSepSitePlan(), drafterInformationEntity.getShowingTransfLocation(), drafterInformationEntity.getDimenrequirOnArr(), drafterInformationEntity.getIdPermit().getId() );
			} else {
				DrafterInformationModel drafterInformationEntityTwo = new DrafterInformationModel();
				drafterInformationEntityTwo.setDimenrequirOnArr(true);
				drafterInformationEntityTwo.setDraftSepSitePlan(false);
				drafterInformationEntityTwo.setNeedForArackingLayD(true);
				drafterInformationEntityTwo.setRoofFeatCallOut(false);
				drafterInformationEntityTwo.setIdPermit(idPermit);
				drafterInformationEntityTwo.setShowingTransfLocation(false);
				return drafterInformationEntityTwo;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public RFIQuestionEntityModel contractorRFIQuestionList(String fieldName, String model) {

		RFIQuestionEntityModel list = new RFIQuestionEntityModel();
		try {

			list = rfiQuestionRepo.findByQuestion(fieldName, model);
			if (list != null) {
				return list;
			} else {
				ArrayList<String> fieldNameQty = new ArrayList<String>(Arrays.asList("Qty[Module]", "Qty[Optimizer]",
						"Qty[INV]", "Qty[DCD]", "Qty[MINV]", "Qty[DCJBOX]", "Qty[DCC]", "Qty[ACJBOX]", "Qty[ACC]",
						"Qty[ACD]", "Qty[PMETER]", "Qty[ACSUBPANEL]", "Qty[DCJBOX]"));
				if (fieldNameQty.indexOf(fieldName) > -1) {
					list = rfiQuestionRepo.findByQuestion("Qty", model);
				}
				if (fieldName.contains("Wire Qty[")) {
					list = rfiQuestionRepo.findByQuestion("Wire Qty", model);
				} else if (fieldName.contains("Wire Type[")) {
					list = rfiQuestionRepo.findByQuestion("Wire Qty", model);
				} else if (fieldName.contains("Wire Size[")) {
					list = rfiQuestionRepo.findByQuestion("Wire Size", model);
				} else if (fieldName.contains("Conduit Type[")) {
					list = rfiQuestionRepo.findByQuestion("Conduit Type", model);
				} else if (fieldName.contains("Conduit Size[")) {
					list = rfiQuestionRepo.findByQuestion("Conduit Size", model);
				} else {
					list = new RFIQuestionEntityModel();
					list.setAddedBy("Super User");
					list.setConfirmation(true);
					list.setFieldName(fieldName);
					list.setQuestionActived(false);
					list.setQuestionstatic("Value Selected Below");
					list.setRfiQuestion(model);
				}
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
			list = new RFIQuestionEntityModel();
			list.setAddedBy("Super User");
			list.setConfirmation(true);
			list.setFieldName(fieldName);
			list.setQuestionActived(false);
			list.setQuestionstatic("Value Selected Below");
			list.setRfiQuestion(model);
			return list;
		}

	}

	/*
	 * Save RFI Question in the RFI List Library
	 */
	public String addRFIQuestion(String fieldName, String model, String nameConnectedUser) {
		try {
			RFIQuestionEntity rFIQuestionEntity = new RFIQuestionEntity();
			rFIQuestionEntity.setFieldName(fieldName);
			rFIQuestionEntity.setRfiQuestion(model);
			rFIQuestionEntity.setQuestionstatic("Value selected below");
			rFIQuestionEntity.setQuestionActived(false);
			rFIQuestionEntity.setAddedBy(nameConnectedUser);
			rfiQuestionRepo.save(rFIQuestionEntity);
			return ("success");
		} catch (Exception e) {
			e.printStackTrace();
			return "error";

		}

	}

	/*
	 * Type inverter optimizer or not
	 */
	public Boolean typeInverter(Long inverterID) {

		try {
			return inverterRepo.getInverterOptimizer(inverterID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * test rail to roof connection if inintegrated
	 */
	public String testIntegrated(Long model) {
		try {
			Boolean isIntegrated = roofAttachmentsRepo.findIsIntegrated(model);
			return Boolean.TRUE.equals(isIntegrated) ? "success" : "Fail";
		} catch (Exception e) {
			return "Fail";
		}
	}
	 
	//S.B CR-3182 Notify ADV Team for Project Delay 23/06/2021
	private void sendEmail(PermitEntity permit) {
		String projectN = "";
		if(checkValueTypesService.NotEquals(permit.getProjectName(), ""))
			projectN = permit.getProjectName();
		else if (checkValueTypesService.NotEquals(permit.getHomeOwnLastName(), "")) {
			if (checkValueTypesService.NotEquals(permit.getHomeOwnName(), "")) {
				projectN = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
			} else {
				projectN = permit.getHomeOwnLastName();
			}
		} else if (checkValueTypesService.NotEquals(permit.getHomeOwnName(), ""))
			projectN = permit.getHomeOwnName();
		
		mailingService.projectReminder(permit.getAuthentificationEntity(), projectN);
	}
	private void projectReminder(Long idPermit) {
		
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final Runnable beeper = new Runnable() {
			public void run() {
				PermitEntity permit = permitRepo.findById(idPermit).orElse(new PermitEntity());
				if (permit != null && !permit.isSubmitted() && permit.getSaveCount() == 1) {
					sendEmail(permit);
					final ScheduledExecutorService schedulerTwo = Executors.newScheduledThreadPool(1);
					final Runnable beeperTwo = new Runnable() {
						public void run() {
							PermitEntity permit = permitRepo.findById(idPermit).orElse(new PermitEntity());
							if (permit != null && !permit.isSubmitted() && permit.getSaveCount() == 1) {
								sendEmail(permit);
							}
						}
					};
					final ScheduledFuture<?> beeperHandleTwo = schedulerTwo.scheduleAtFixedRate(beeperTwo, 72, 72,
							TimeUnit.HOURS);
					schedulerTwo.schedule(new Runnable() {
						public void run() {
							beeperHandleTwo.cancel(true);
						}
					}, 72, TimeUnit.HOURS);
				}

			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 48, 48,
				TimeUnit.HOURS);
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 48, TimeUnit.HOURS);
	}


	/*
	 * Get All Electrical Utility Company By zip code
	 */
	public List<ElectricalUtilityModel> getAllElectricalUtilityCompany(String zipCode) {

		try {
			return electricalUtilityRepo.getByZipAndIsDeletedOrderByUtilityCompanyName(zipCode, false);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * Delet Permit
	 */
	public String deletPermit(Long id, String ipUser, String timeZoneUser, Long idUser, String numTab, String sessionId,
			String openDate) {

		try {

			PermitEntity permit = permitRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			AuthentificationEntity user = userRepo.findById(idUser).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));

			notificationEntityService.addNewNotif(idUser, 0L, "Project Canceled", "Projects",
					"Project Canceled - " + permit.getHomeOwnName(),
					"The contractor " + user.getFirstName() + " " + user.getLastName() + " has canceled the  "
							+ permit.getStatus() + " project " + permit.getHomeOwnName(),
					true);
			String projectname = permit.getProjectName();
			if (permit.getProjectName() == null || checkValueTypesService.Equals(permit.getProjectName(), "")) {
				projectname = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
			}
			if (user.getRoleEntity() != null && user.getRoleEntity().getId() == 2) {
				mailingService
						.sendingMailNotifForDeletedPermit(
								"ADV - " + user.getFirstName() + " " + user.getLastName() + " Cancel " + projectname,
								"Hello, The contractor " + user.getFirstName() + " " + user.getLastName()
										+ " has deleted the " + permit.getStatus() + " project " + projectname + ".",
								user.getEmail());
			}
			permit.setDeleted(true);
			permit.setStatus("Canceled");
			
			// CR-2394
			permit.setUpdatedBy(user);
			permitRepo.save(permit);

			historicActivity.recordActivity(idUser, ipUser, timeZoneUser, "Delete Permit;" + id + ";Delete success",
					true, numTab, sessionId, openDate);
			
			return "succes";

		} catch (Exception e) {
			historicActivity.recordActivity(idUser, ipUser, timeZoneUser, "Delete Permit;" + id + ";Delete failed",
					false, numTab, sessionId, openDate);
			e.printStackTrace();
			return "It seems that there is a technical problem, please try again.";
		}

	}

	public List<String> urlImagesDrafter(Long idPermitInfo) {
		List<String> nomF = new ArrayList<>();

		try {
			if (idPermitInfo != null) {
				String googleEarthimg = permitDrafterDataRepo.findGoogleMapImageName(idPermitInfo);
				String nearMap = permitDrafterDataRepo.findNearMapImageName(idPermitInfo);
				if (googleEarthimg != null)
					nomF.add(idPermitInfo + "/drafterFiles/" + googleEarthimg);
				if (nearMap != null)
					nomF.add(idPermitInfo + "/drafterFiles/" + nearMap);
			}
			return nomF;
		} catch (Exception e) {
			e.printStackTrace();
			return nomF;
		}

	}

	public String updatedDate(Long permitId) {
		try {
			PermitEntity permit = permitRepo.findById(permitId).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			if (permit != null) {
				TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
				Calendar updateDate = Calendar.getInstance();
				permit.setUpdateDate(updateDate.getTime());
				permitRepo.save(permit);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	/*
	 * getAllPermitForChart
	 */
	public List<List<String>> getAllPermitForChart(Long idUser) {
		List<List<String>> csvList = new ArrayList<List<String>>();

		try {

			List<PermitResult> permits = permitRepo.getAllPermitForChart(idUser);
			if (permits != null) {
				List<String> listYear = new ArrayList<>();
				List<String> listMonth = new ArrayList<>();
				List<String> listDay = new ArrayList<>();
				List<Integer> listnumber = new ArrayList<>();
				String year = new SimpleDateFormat("yyyy").format(permits.get(0).getCreationPermitDate());
				String month = new SimpleDateFormat("MM").format(permits.get(0).getCreationPermitDate());
				String day = new SimpleDateFormat("dd").format(permits.get(0).getCreationPermitDate());
				listYear.add(year);
				listMonth.add(month);
				listDay.add(day);
				listnumber.add(1);
				int numberLocation = 0;
				int dateLocation = 0;
				if (!permits.isEmpty()) {
					for (int i = 1; i < permits.size(); i++) {

						year = new SimpleDateFormat("yyyy").format(permits.get(i).getCreationPermitDate());
						month = new SimpleDateFormat("MM").format(permits.get(i).getCreationPermitDate());
						day = new SimpleDateFormat("dd").format(permits.get(i).getCreationPermitDate());
						if (year.equalsIgnoreCase(listYear.get(dateLocation))) {

							if (month.equalsIgnoreCase(listMonth.get(dateLocation))) {

								if (day.equalsIgnoreCase(listDay.get(dateLocation))) {
									int qty = listnumber.get(numberLocation) + 1;
									listnumber.set(numberLocation, qty);
								} else {
									listYear.add(year);
									listMonth.add(month);
									listDay.add(day);
									listnumber.add(1);
									numberLocation++;
									dateLocation++;
								}

							} else {
								listYear.add(year);
								listMonth.add(month);
								listDay.add(day);
								listnumber.add(1);
								numberLocation++;
								dateLocation++;
							}
						} else {
							listYear.add(year);
							listMonth.add(month);
							listDay.add(day);
							listnumber.add(1);
							numberLocation++;
							dateLocation++;
						}
					}
				}
				int size = listnumber.size();
				listnumber.add(size);
				List<String> qty = new ArrayList<>();
				for (int j = 0; j < listnumber.size(); j++) {
					qty.add(listnumber.get(j).toString());
				}
				csvList.add(listYear);
				csvList.add(listMonth);
				csvList.add(listDay);
				csvList.add(qty);
				return csvList;
			} else {
				return csvList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csvList;
	}

	/*
	 * getAllPermitForCharttwo(not deleted)
	 */
	public List<Integer> getAllPermitForCharttwo() {
		List<Integer> csvList = new ArrayList<>();

		try {
			List<PermitResult> permits = permitRepo.getAllPermitForChartTwo();
			if (permits != null) {

				int listCreated = 0;
				int listDraft = 0;
				int listSubmitted = 0;
				int listCompleted = 0;

				for (int i = 0; i < permits.size(); i++) {
					if (permits.get(i) != null) {
						if (permits.get(i).getStatus().equalsIgnoreCase("Created")) {
							listCreated++;

						} else if (permits.get(i).getStatus().equalsIgnoreCase("Draft")) {
							listDraft++;
						} else if (permits.get(i).getStatus().equalsIgnoreCase("Submitted")) {
							listSubmitted++;
						} else if (permits.get(i).getStatus().equalsIgnoreCase("Completed")) {
							listCompleted++;
						}
					}
				}
				csvList.add(listCreated);
				csvList.add(listDraft);
				csvList.add(listSubmitted);
				csvList.add(listCompleted);
				return csvList;
			} else {
				return csvList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return csvList;
		}
	}

	/*
	 * tester si permit admet des sktech
	 */

	public List<String> getMicroInverterManufacturer() {
		return inverterRepo.findAllMicroManufacturer();
	}

	public List<String> getMicroInvertersModels(String make) {
		return inverterRepo.findAllMicroModelsByManufacturer(make);
	}

	public String saveSkecthLayoutArrays(LayoutSketchArraysModel layoutSketchArrays) {

		try {
			PermitEntity permitEntity = permitRepo.findById(layoutSketchArrays.getPermitId()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			List<PermitSketchEntity> arraysList = permitSketchRepo
					.findByPermitEntityId(layoutSketchArrays.getPermitId());
			permitSketchRepo.deleteAll(arraysList);

			for (int i = 0; i < layoutSketchArrays.getArraysLength(); i++) {
				PermitSketchEntity arraysEntity = new PermitSketchEntity();
				arraysEntity.setArraySketch(i + 1);
				arraysEntity.setAzimuth(layoutSketchArrays.getAzimuth()[i]);
				arraysEntity.setEaveOverHang(layoutSketchArrays.getEaveOverHang()[i]);
				arraysEntity.setEaveOverHangOther(layoutSketchArrays.getEaveOverHangOther()[i]);
				arraysEntity.setModelvalue(layoutSketchArrays.getModelvalue()[i]);
				arraysEntity.setModuleQty(layoutSketchArrays.getModuleQty()[i] + "");
				arraysEntity.setModuleTils(layoutSketchArrays.getModuleTils()[i]);
				arraysEntity.setRoofPitch(layoutSketchArrays.getRoofPitch()[i]);
				arraysEntity.setSquareFootage(layoutSketchArrays.getSquareFootage()[i]);
				arraysEntity.setPermitEntity(permitEntity);
				permitSketchRepo.save(arraysEntity);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	public List<String> getPermitFiles(Long permitId) {

		List<String> permitFiles = new ArrayList<>();

		try {
			// get all file name
			String path = pathRepo.findFilePath();
			File folder = new File(path + permitId + "/permitFiles");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; listOfFiles != null && i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					permitFiles.add(listOfFiles[i].getName());
				}
			}

			return permitFiles;
		} catch (Exception e) {
			e.printStackTrace();
			return permitFiles;
		}
	}

	// CR-3230 Check if any drafts are uploaded for a permit
	public Boolean checkDraftsForPermit(Long permitId) {

		try {
			PermitDrafterDataEntity permitDrafterDataEntity = permitDrafterDataRepo.findByPermitEntityId(permitId);
			return permitDrafterDataEntity.getParcelMapName() != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public HashMap<String, List<String>> getDrafterDatafiles(Long permitId) {

		HashMap<String, List<String>> result = new HashMap<String, List<String>>();
		try {

			String path = pathRepo.findFilePath();
			PermitDrafterDataEntity permitDrafterDataEntity = permitDrafterDataRepo.findByPermitEntityId(permitId);
			if (permitDrafterDataEntity.getParcelMapName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getParcelMapName());
				result.put("parcelmap", drafterDataFiles);
			}
			if (permitDrafterDataEntity.getPvArrayLayoutName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getPvArrayLayoutName());
				result.put("arraylayout", drafterDataFiles);
			}
			if (permitDrafterDataEntity.getRackingLayoutName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getRackingLayoutName());
				result.put("rackinglayout", drafterDataFiles);
			}
			if (permitDrafterDataEntity.getElectricalLayoutName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getElectricalLayoutName());
				result.put("electricallayout", drafterDataFiles);
			}
			if (permitDrafterDataEntity.getPlacardLayoutName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getPlacardLayoutName());
				result.put("placardLayout", drafterDataFiles);
			}

			if (permitDrafterDataEntity.getAutocadFileName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getAutocadFileName());
				result.put("autoCad", drafterDataFiles);
			}
			if (permitDrafterDataEntity.getGoogleMapImageName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();

				// get all file name

				File folder = new File(path + permitId + "/drafterfiles");
				File[] listOfFiles = folder.listFiles();
				if (listOfFiles != null && listOfFiles.length > 0) {
					for (int i = 0; i < listOfFiles.length; i++) {
						if (listOfFiles[i].isFile()
								&& checkValueTypesService.Equals(listOfFiles[i].getName().split("-")[0], "GM")) {
							drafterDataFiles.add(listOfFiles[i].getName());
							result.put("googleMapImage", drafterDataFiles);
						}
					}
				}

			}
			if (permitDrafterDataEntity.getNearMapImageName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				File folder = new File(path + permitId + "/drafterfiles");
				File[] listOfFiles = folder.listFiles();
				if (listOfFiles != null && listOfFiles.length > 0) {
					for (int i = 0; i < listOfFiles.length; i++) {
						if (listOfFiles[i].isFile()
								&& checkValueTypesService.Equals(listOfFiles[i].getName().split("-")[0], "NM")) {
							drafterDataFiles.add(listOfFiles[i].getName());
							result.put("nearMapImage", drafterDataFiles);
						}
					}
				}
			}
			if (new File(path + permitId + "/drafterfiles/").exists()) {

				List<String> drafterDataFiles = new ArrayList<>();

				// get all file name
				File folder = new File(path + permitId + "/drafterfiles");
				File[] listOfFiles = folder.listFiles();
				if (listOfFiles != null && listOfFiles.length > 0) {
					for (int i = 0; i < listOfFiles.length; i++) {
						if (listOfFiles[i].isFile()
								&& checkValueTypesService.Equals(listOfFiles[i].getName().split("-")[0], "BOS")) {
							drafterDataFiles.add(listOfFiles[i].getName());
							result.put("bosImage", drafterDataFiles);
						}
					}
				}
			}
			if (new File(path + permitId + "/drafterfiles/").exists()) {
				List<String> drafterDataFiles = new ArrayList<>();

				// get all file name
				File folder = new File(path + permitId + "/drafterfiles");
				File[] listOfFiles = folder.listFiles();
				if (listOfFiles != null && listOfFiles.length > 0) {
					for (int i = 0; i < listOfFiles.length; i++) {
						if (listOfFiles[i].isFile()
								&& checkValueTypesService.Equals(listOfFiles[i].getName().split("-")[0], "UB")) {
							drafterDataFiles.add(listOfFiles[i].getName());
							result.put("utilityBill", drafterDataFiles);
						}
					}
				}
			}
			if (permitDrafterDataEntity.getLoadJustificationName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getLoadJustificationName());
				result.put("justification", drafterDataFiles);
			}
			if (permitDrafterDataEntity.getAdresseSubdivisionFileName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getAdresseSubdivisionFileName());
				result.put("subdivision", drafterDataFiles);
			}
			if (permitDrafterDataEntity.getSwitchgearCutsheetsFileName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getSwitchgearCutsheetsFileName());
				result.put("switchgear", drafterDataFiles);
			}
			if (permitDrafterDataEntity.getProporsalInterconnectionFileName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getProporsalInterconnectionFileName());
				result.put("proposalInterconnection", drafterDataFiles);
			}
			if (permitDrafterDataEntity.getExistingSystemeFileName() != null) {
				List<String> drafterDataFiles = new ArrayList<>();
				drafterDataFiles.add(permitDrafterDataEntity.getExistingSystemeFileName());
				result.put("existingSystem", drafterDataFiles);
			}
			return result;
		} catch (Exception e) {
			return result;
		}

	}

	public List<String> getAdditionalInfoFiles(Long permitId) {
		return additionalInfoFilesRepo.getAdditionalInfoFiles(permitId);
	}

	// ------ changePermitStatus ---------- //
	public String changePermitStatus(Long idPermit, String status, String ipUser, String timeZoneUser, Long idUser,
			String numTab, String sessionId, String openDate) {
		try {

			PermitEntity permitEntity = permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			AuthentificationEntity user = userRepo.findById(idUser).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" + idUser));
			/*
			 * Increment Reopen Request Permit Version
			 */
			String projectName = null;
			if (checkValueTypesService.Equals(status, "Reopened")) {
				if (checkValueTypesService.NotEquals(permitEntity.getStatus(), "Reopened")) {
					permitEntity.setRRVersion(permitEntity.getRRVersion() + 1);
					
					if (checkValueTypesService.NotEquals(permitEntity.getProjectName(), "")) {
						projectName = permitEntity.getProjectName();
						if (projectName != null && projectName.contains("-Rev.")) {
							permitEntity.setProjectName(permitEntity.getProjectName().split("-Rev.")[0] + "-Rev."
									+ permitEntity.getRRVersion());
						} else {
							permitEntity.setProjectName(
									permitEntity.getProjectName() + "-Rev." + permitEntity.getRRVersion());
						}
					} else {
						if (checkValueTypesService.NotEquals(permitEntity.getHomeOwnLastName(), "")) {
							projectName = permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName();
							if (projectName != null && projectName.contains("-Rev.")) {
								permitEntity.setHomeOwnLastName(permitEntity.getHomeOwnLastName().split("-Rev.")[0]
										+ "-Rev." + permitEntity.getRRVersion());
							} else {
								permitEntity.setHomeOwnLastName(
										permitEntity.getHomeOwnLastName() + "-Rev." + permitEntity.getRRVersion());
							}
						} else {
							projectName = permitEntity.getHomeOwnName();
							if (projectName != null && projectName.contains("-Rev.")) {
								permitEntity.setHomeOwnName(permitEntity.getHomeOwnName().split("-Rev.")[0] + "-Rev."
										+ permitEntity.getRRVersion());
							} else {
								permitEntity.setHomeOwnName(
										permitEntity.getHomeOwnName() + "-Rev." + permitEntity.getRRVersion());
							}
						}
					}

				}
				TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
				Calendar calReop = Calendar.getInstance(); // creates calendar
				calReop.setTime(new Date());
				try {
					ProjectsTrackerEntity tracker = projectsTrackerRepo.findByPermitId(idPermit);
					if (tracker != null) {
						tracker.setReopenProject(calReop.getTime());
						projectsTrackerRepo.save(tracker);
					} else {
						ProjectsTrackerEntity tracker2 = new ProjectsTrackerEntity();
						tracker2.setPermit(permitEntity);
						tracker2.setReopenProject(calReop.getTime());
						projectsTrackerRepo.save(tracker2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (checkValueTypesService.Equals(status, "Delivered")) {
				TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
				Calendar cal = Calendar.getInstance(); // creates calendar
				cal.setTime(new Date());
				Calendar cal2 = Calendar.getInstance(); // creates calendar
				cal2.setTime(permitEntity.getCreationPermitDate());

				long difference = (cal.getTime().getTime() - cal2.getTime().getTime()) / 3600000;
				try {
					ProjectsTrackerEntity tracker = projectsTrackerRepo.findByPermitId(idPermit);
					if (tracker != null) {
						tracker.setDelivred(cal.getTime());
						tracker.setTotalProjectTime(difference + " H");
						if (tracker.getSubmittedContRfi() != null && tracker.getSubmittedAdvRfi() != null) {
							Calendar cal3 = Calendar.getInstance(); // creates calendar
							cal3.setTime(tracker.getSubmittedContRfi());
							Calendar cal4 = Calendar.getInstance(); // creates calendar
							cal4.setTime(tracker.getSubmittedAdvRfi());
							long rfiTime = (cal3.getTime().getTime() - cal4.getTime().getTime()) / 3600000;
							long projectTimeLessrfiTime = difference - rfiTime;
							tracker.setProjectTimeLessRfi(projectTimeLessrfiTime + " H");
						}

						projectsTrackerRepo.save(tracker);
					} else {
						ProjectsTrackerEntity tracker2 = new ProjectsTrackerEntity();
						tracker2.setPermit(permitEntity);
						tracker2.setDelivred(cal.getTime());
						tracker2.setTotalProjectTime(difference + "H");
						projectsTrackerRepo.save(tracker2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (checkValueTypesService.Equals(status, "In Progress")
					|| checkValueTypesService.Equals(status, "Reopened")) {
				permitEntity.setDeleted(false);
				String result = CheckIfProjectNameExist(permitEntity.getHomeOwnName(),
						permitEntity.getHomeOwnLastName(), permitEntity.getProjectName(), idPermit);
				if (checkValueTypesService.Equals(result, "exist")) {
					return "exist";
				}
			}
			permitEntity.setStatus(status);
			
		    // CR-2394
			permitEntity.setUpdatedBy(user);
			permitRepo.save(permitEntity);

			historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
					"Update Status Permit to " + status + " ;" + idPermit + ";Update success", true, numTab, sessionId,
					openDate);

			if (checkValueTypesService.Equals(status, "Reopened")) {
				return projectName;
			} else
				return "Done";

		} catch (Exception e) {
			historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
					"Update Status Permitto " + status + " ;" + idPermit + ";Update failed", false, numTab, sessionId,
					openDate);
			e.printStackTrace();
			return "Fail";
		}
	}

	public List<String> getBOSFiles(String permitId) {
		List<String> bosFiles = new ArrayList<>();

		try {
			String filespath = pathRepo.findFilePath();
			File[] f = new File(filespath + permitId + "/drafterfiles/").listFiles();

			if (f != null && f.length > 0) {
				for (File fs : f) {
					if (fs.getName() != null && fs.getName().contains("BOS-")) {
						bosFiles.add(fs.getName());
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bosFiles;
	}

	public List<String> getUtilityBillFiles(String permitId) {
		List<String> ubFiles = new ArrayList<>();

		try {
			String filespath = pathRepo.findFilePath();
			File[] f = new File(filespath + permitId + "/drafterfiles/").listFiles();

			if (f != null && f.length > 0) {
				for (File fs : f) {
					if (fs.getName() != null && fs.getName().contains("UB-")) {
						ubFiles.add(fs.getName());
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ubFiles;
	}

	public List<String> getPlansetImages(Long idPermit) {

		List<String> plansetFiles = new ArrayList<>();
		try {
			String filespath = pathRepo.findFilePath();
			File[] f = new File(filespath + idPermit + "/planset/").listFiles();
			plansetFiles = new ArrayList<>();
			if (f != null && f.length > 0) {
				for (File fs : f) {
					plansetFiles.add(fs.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plansetFiles;
	}

	/******* Update the date when clicked in onHold button *******************/

	public void onHoldProjectService(Long idPermit, Long idUser) {
		PermitEntity permitEntity = new PermitEntity();
		try {

			if (idPermit != null) {
				permitEntity = permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (permitEntity != null) {
					Calendar monCal = Calendar.getInstance();
					monCal.add(Calendar.DATE, 45);
					permitEntity.setDateOfSubmitPermitOnHold(monCal.getTime());
					permitEntity.setStatus("On Hold");
					permitEntity.setIsOnHold(true);
					permitRepo.save(permitEntity);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*******
	 * Test When change the status Delivered of the permit
	 *******************/

	public boolean onHoldProjectUpdateToDService(Long idPermit) {

		try {
			if (idPermit != null) {
				PermitEntity permitEntity = permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (permitEntity != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dateAu = sdf.format(new Date());
					String datePermit = sdf.format(permitEntity.getDateOfSubmitPermitOnHold());
					if (dateAu.compareTo(datePermit) >= 0) {
						permitEntity.setStatus("Delivered");
						permitEntity.setIsOnHold(false);
						permitRepo.save(permitEntity);
						return true;
					}
				}
			}

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// CR-1209 Get Project Contacts names from Authentification Entity
	public ProjectContactsEmailModel getProjectContacts(Long id) {

		ProjectContactsEmailModel projectContactes = new ProjectContactsEmailModel();
		try {

			Long idUser = permitRepo.findProjectOwnerID(id);
			projectContactes = userContactInfoRepo.getUserContacts(idUser);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return projectContactes;

	}

	public String getPermitInformation(Long idPermit) {
		try {
			String sitAddress = permitHomeSiteInfoRepo.finddSiteAddress(idPermit);
			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			String homeName = permit.getProjectName();
			if (permit.getProjectName() == null || checkValueTypesService.Equals(permit.getProjectName(), "")) {
				homeName = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
			}
			return homeName + "*" + sitAddress;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String sendProjectEmail(ProjectEmailModel projectEmailAttributes, Long idUser, Long idPermit,
			String ipAdress, String timeZone, String numTab, String sessionId, String openDate) {
		try {
			String emailUser = userRepo.findEmail(idUser);
			if (!checkValueTypesService.NotEquals(emailUser, "")) {
				return "contact missing";
			} else {
				projectEmailAttributes
						.setEmailContent(projectEmailAttributes.getEmailContent().replaceAll("(\r\n|\n)", "<br />"));
				PermitEntity permitEntity = permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				permitEntity.setStatus("Delivered");
				permitRepo.save(permitEntity);
				mailingService.sendingProjectEmail(projectEmailAttributes, emailUser);
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String confShownAttachement(Long idQuestion) {
		try {
			RFIQuestionEntity rfiQuestion = rfiQuestionRepo.findByIdRFIQuestion(idQuestion);
			rfiQuestion.setAttachementShown(true);
			rfiQuestionRepo.save(rfiQuestion);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

//		----------------------------CR-1457---------------------------
	public String removeFromUsersFavList(String componentType, Long[] idModelsString, Long idContractor,
			Long idUserConnect) {

		try {
			if (idContractor != null) {

				AuthentificationEntity owner = userRepo.findById(idContractor).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				List<Long> idModels = new ArrayList<>();
				try {
					for (int i = 0; i < idModelsString.length; i++) {
						idModels.add(idModelsString[i]);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				if (checkValueTypesService.Equals(componentType, "Module")) {
					try {
						List<ModuleLibraryEntity> components = moduleFavRepo
								.findByAuthentificationEntityIdAndCmodulev2IdIn(idContractor, idModels);
						moduleFavRepo.deleteAll(components);
						for (int i = 0; i < components.size(); i++) {
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getCmodulev2().getMake() + " "
											+ components.get(i).getCmodulev2().getModel() + " is removed from the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;Module",
									true, "", "", "");
						}

					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Module to User Favorites List;Module ", false, "", "", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "Inverter")) {
					try {
						List<InverterLibraryEntity> components = inverterFavRepo
								.findByAuthentificationEntityIdAndInvertersIdIn(idContractor, idModels);
						inverterFavRepo.deleteAll(components);
						for (int i = 0; i < components.size(); i++) {
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getInverters().getMake() + " "
											+ components.get(i).getInverters().getModel() + " is removed from the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;Inverter",
									true, "", "", "");
						}
					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Inverter from User Favorites List;Module ", false, "", "", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "Optimizer")) {
					try {

						List<DCOptimizerFavoritEntity> components = convertersFavRepo
								.findByUserIdAndOptimizerIdIn(idContractor, idModels);
						convertersFavRepo.deleteAll(components);
						for (int i = 0; i < components.size(); i++) {
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getOptimizer().getManufacturer() + " "
											+ components.get(i).getOptimizer().getModel() + " is removed from the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;DC Optimizers",
									true, "", "", "");
						}

					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove DC Optimizers from User Favorites List;DC Optimizers", false, "", "", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "RailRacking")) {
					try {

						List<RailRackingFavLibraryEntity> components = railRackingFavoritesRepo
								.findAllByAuthentificationEntityIdAndRailRackingIdIn(idContractor, idModels);
						railRackingFavoritesRepo.deleteAll(components);
						for (int i = 0; i < components.size(); i++) {
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getRailRacking().getManufacturer() + " "
											+ components.get(i).getRailRacking().getModel() + " is removed from the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;Rail & Racking",
									true, "", "", "");
						}
					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Rail & Racking from User Favorites List;Rail & Racking", false, "", "",
								"");

					}
				} else if (checkValueTypesService.Equals(componentType, "RoofAttachment")) {
					try {

						List<RoofAttachmentFavLibraryEntity> components = roofAttachmentsFavRepo
								.findByAuthentificationEntityIdAndRoofAttachmentIdIn(idContractor, idModels);
						roofAttachmentsFavRepo.deleteAll(components);
						List<String> models = new ArrayList<>();
						for (int i = 0; i < components.size(); i++) {
							models.add(components.get(i).getRoofAttachment().getId() + "");
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getRoofAttachment().getManufacturer()
											+ " " + components.get(i).getRoofAttachment().getModel()
											+ " is removed from the user " + owner.getFirstName() + " "
											+ owner.getLastName() + ".;Roof Attachments",
									true, "", "", "");
						}

						List<PermitProjectSiteInfoEntity> permitArray1 = permitProjectSiteInfoEntityRepos
								.findByPermitEntityAuthentificationEntityIdAndRailConnectionModelIn(idContractor, models);
						for (int i = 0; !permitArray1.isEmpty() && i < permitArray1.size(); i++) {
							permitArray1.get(i).setRailConnectionModel("Fav Removed");
							permitProjectSiteInfoEntityRepos.save(permitArray1.get(i));
						}
					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Roof Attachments from User Favorites List;Roof Attachments ", false, "",
								"", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "ACDisconnect")) {
					try {

						List<ACDisconnectFavLibraryEntity> components = acDisconnectFavRepo
								.findByAuthentificationEntityIdAndACDisconnectIdIn(idContractor, idModels);
						acDisconnectFavRepo.deleteAll(components);
						List<String> models = new ArrayList<>();
						for (int i = 0; i < components.size(); i++) {
							models.add(components.get(i).getACDisconnect().getId() + ":"
									+ components.get(i).getACDisconnect().getManufacturer() + ":"
									+ components.get(i).getACDisconnect().getModel());
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getACDisconnect().getManufacturer() + " "
											+ components.get(i).getACDisconnect().getModel() + " is removed from the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;AC Disconnect",
									true, "", "", "");
						}

						List<PermitProjectSiteInfoEntity> projectsRooftopACCombiner = permitProjectSiteInfoEntityRepos
								.findByPermitEntityAuthentificationEntityIdAndRooftopACCombinerModelIn(idContractor,
										models);
						for (int i = 0; !projectsRooftopACCombiner.isEmpty() && i < projectsRooftopACCombiner.size(); i++) {
							projectsRooftopACCombiner.get(i).setRooftopACCombinerModel("Fav Removed");
							permitProjectSiteInfoEntityRepos.save(projectsRooftopACCombiner.get(i));
						}
						List<PermitProjectSiteInfoEntity> projectsRooftopACCombinerTwo = permitProjectSiteInfoEntityRepos
								.findByPermitEntityAuthentificationEntityIdAndRooftopACCombinerModelTwoIn(idContractor,
										models);
						for (int i = 0; !projectsRooftopACCombinerTwo.isEmpty()
								&& i < projectsRooftopACCombinerTwo.size(); i++) {
							projectsRooftopACCombinerTwo.get(i).setRooftopACCombinerModelTwo("Fav Removed");
							permitProjectSiteInfoEntityRepos.save(projectsRooftopACCombinerTwo.get(i));
						}
						List<PermitProjectSiteInfoEntity> projectsRoofTopACDisco = permitProjectSiteInfoEntityRepos
								.findByPermitEntityAuthentificationEntityIdAndRoofTopACDiscoIn(idContractor, models);
						for (int i = 0; !projectsRoofTopACDisco.isEmpty() && i < projectsRoofTopACDisco.size(); i++) {
							projectsRoofTopACDisco.get(i).setRoofTopACDisco("Fav Removed");
							permitProjectSiteInfoEntityRepos.save(projectsRoofTopACDisco.get(i));
						}

					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove AC Disconnect from User Favorites List;AC Disconnect ", false, "", "",
								"");

					}
				} else if (checkValueTypesService.Equals(componentType, "ACCombiner")) {
					try {
		
						List<ACCombinerFavLibraryEntity> components = acCombinerSLCsFavoritesRepo
								.findByAuthentificationEntityIdAndAcCombinerSlcIdIn(idContractor, idModels);
					
						acCombinerSLCsFavoritesRepo.deleteAll(components);
						for (int i = 0; i < components.size(); i++) {
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getAcCombinerSLC().getManufacturer() + " "
											+ components.get(i).getAcCombinerSLC().getModel() + " is removed from the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;AC Combiner / SLC",
									true, "", "", "");
						}

					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove AC Combiner / SLC from User Favorites List;AC Combiner / SLC ", false, "",
								"", "");

					}

				} else if (checkValueTypesService.Equals(componentType, "JBox")) {
					try {

						List<JunctionBoxFavLibraryEntity> components = jbFavRepo
								.findByAuthentificationEntityIdAndJboxIdIn(idContractor, idModels);
						jbFavRepo.deleteAll(components);
						List<String> models = new ArrayList<>();
						for (int i = 0; i < components.size(); i++) {
							models.add(components.get(i).getJbox().getId() + "");
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getJbox().getManufacturer() + " "
											+ components.get(i).getJbox().getModel() + " is removed from the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;Junction Box",
									true, "", "", "");
						}

						List<PermitProjectSiteInfoEntity> projectsRoofTopJbox = permitProjectSiteInfoEntityRepos
								.findByPermitEntityAuthentificationEntityIdAndRoofTopJboxIn(idContractor, models);
						for (int i = 0; !projectsRoofTopJbox.isEmpty() && i < projectsRoofTopJbox.size(); i++) {
							projectsRoofTopJbox.get(i).setRoofTopJbox("Fav Removed");
							permitProjectSiteInfoEntityRepos.save(projectsRoofTopJbox.get(i));
						}
						List<PermitProjectSiteInfoEntity> projectsRoofTopJboxDC = permitProjectSiteInfoEntityRepos
								.findByPermitEntityAuthentificationEntityIdAndRoofTopJboxDCIn(idContractor, models);
						for (int i = 0; !projectsRoofTopJboxDC.isEmpty() && i < projectsRoofTopJboxDC.size(); i++) {
							projectsRoofTopJboxDC.get(i).setRoofTopJboxDC("Fav Removed");
							permitProjectSiteInfoEntityRepos.save(projectsRoofTopJboxDC.get(i));
						}

					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Junction Box from User Favorites List;Junction Box", false, "", "", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "DCDisconnect")) {
					try {

						List<DcCombinerorDiscFavoriteEntity> components = dcCombinerorDiscFavoriteEntityRepo
								.findByAuthentificationEntityIdAndDcCombinerDisconnectEntityIdIn(idContractor, idModels);
						dcCombinerorDiscFavoriteEntityRepo.deleteAll(components);
						for (int i = 0; i < components.size(); i++) {
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite "
											+ components.get(i).getDcCombinerDisconnectEntity().getManufacturer() + " "
											+ components.get(i).getDcCombinerDisconnectEntity().getModel()
											+ " is removed from the user " + owner.getFirstName() + " "
											+ owner.getLastName() + ".;DC Combiner or Disconnects",
									true, "", "", "");
						}

					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove DC Combiner or Disconnects from User Favorites List;DC Combiner or Disconnects",
								false, "", "", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "flashing")) {
					try {

						List<FlashingFavLibraryEntity> components = flashingsFavoritesRepo
								.findAllByAuthentificationEntityIdAndFlashingIdIn(idContractor, idModels);
						flashingsFavoritesRepo.deleteAll(components);
						List<String> models = new ArrayList<>();
						for (int i = 0; i < components.size(); i++) {
							models.add(components.get(i).getFlashing().getId() + "");
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getFlashing().getManufacturer() + " "
											+ components.get(i).getFlashing().getModel() + " is removed from the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;Flashing",
									true, "", "", "");
						}
						List<PermitProjectSiteInfoEntity> projectsFlashing = permitProjectSiteInfoEntityRepos
								.findByPermitEntityAuthentificationEntityIdAndFlashingIn(idContractor, models);
						for (int i = 0; !projectsFlashing.isEmpty() && i < projectsFlashing.size(); i++) {
							projectsFlashing.get(i).setFlashing("Fav Removed");
							permitProjectSiteInfoEntityRepos.save(projectsFlashing.get(i));
						}

					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Flashing from User Favorites List;Flashing ", false, "", "", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "Revenue or Performance Monitoring Meter")) {
					try {

						List<LeasePPAMeterFavLibraryEntity> components = leasePPAMeterFavoritesRepo
								.findByAuthentificationEntityIdAndLeasePPAMeterIdIn(idContractor, idModels);
						leasePPAMeterFavoritesRepo.deleteAll(components);
						List<String> models = new ArrayList<>();
						for (int i = 0; i < components.size(); i++) {
							models.add(components.get(i).getLeasePPAMeter().getId() + "");
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getLeasePPAMeter().getManufacturer() + " "
											+ components.get(i).getLeasePPAMeter().getModel() + " is removed to the user "
											+ owner.getFirstName() + " " + owner.getLastName()
											+ ".;Revenue or Performance Monitoring Meter",
									true, "", "", "");
						}
						List<PermitProjectSiteInfoEntity> projectsLeasePPAMeter = permitProjectSiteInfoEntityRepos
								.findByPermitEntityAuthentificationEntityIdAndLeasePPAMeterIn(idContractor, models);
						for (int i = 0; !projectsLeasePPAMeter.isEmpty() && i < projectsLeasePPAMeter.size(); i++) {
							projectsLeasePPAMeter.get(i).setLeasePPAMeter("Fav Removed");
							permitProjectSiteInfoEntityRepos.save(projectsLeasePPAMeter.get(i));
						}
					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Revenue or Performance Monitoring Meter from User Favorites List;Revenue or Performance Monitoring Meter",
								false, "", "", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "battery")) {
					try {

						List<BatteryFavLibraryEntity> components = batteryFavRepo
								.findByAuthentificationEntityIdAndBatteryIdIn(idContractor, idModels);
						batteryFavRepo.deleteAll(components);
						List<String> models = new ArrayList<>();
						for (int i = 0; i < components.size(); i++) {
							models.add(components.get(i).getBattery().getId() + "");
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getBattery().getManufacturer() + " "
											+ components.get(i).getBattery().getModel() + " is removed from the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;Battery",
									true, "", "", "");
						}
						List<PermitAdditionalInfoEntity> projects = permitAdditionalInfoRepo
								.findByPermitEntityAuthentificationEntityIdAndBatteryIn(idContractor, models);
						for (int i = 0; !projects.isEmpty() && i < projects.size(); i++) {
							projects.get(i).setBattery("Fav Removed");
							permitAdditionalInfoRepo.save(projects.get(i));
						}

					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Battery from User Favorites List;Battery", false, "", "", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "tiltLegs")) {
					try {

						List<TiltLegsFavLibraryEntity> components = tiltLegsFavRepo
								.findAllByAuthentificationEntityIdAndTiltLegsIdIn(idContractor, idModels);
						tiltLegsFavRepo.deleteAll(components);
						List<String> models = new ArrayList<>();
						for (int i = 0; i < components.size(); i++) {
							models.add(components.get(i).getTiltLegs().getId() + "");
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getTiltLegs().getManufacturer() + " "
											+ components.get(i).getTiltLegs().getModel() + " is removed to the user "
											+ owner.getFirstName() + " " + owner.getLastName() + ".;Tilt Legs",
									true, "", "", "");
						}
						List<PermitAdditionalInfoEntity> projects = permitAdditionalInfoRepo
								.findByPermitEntityAuthentificationEntityIdAndTiltLegsModIn(idContractor, models);
						for (int i = 0; !projects.isEmpty() && i < projects.size(); i++) {
							projects.get(i).setTiltLegsMod("Fav Removed");
							permitAdditionalInfoRepo.save(projects.get(i));
						}
					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Tilt Legs from User Favorites List;Tilt Legs ", false, "", "", "");

					}
				} else if (checkValueTypesService.Equals(componentType, "Proposed Sub Panel")) {
					try {

						List<ProposedSubPanelFavLibraryEntity> components = proposedSubPanelFavRepo
								.findAllByAuthentificationEntityIdAndProposedSubPanelIdIn(idContractor, idModels);
						proposedSubPanelFavRepo.deleteAll(components);
						List<String> models = new ArrayList<>();
						for (int i = 0; i < components.size(); i++) {
							models.add(components.get(i).getProposedSubPanel().getId() + "");
							historicActivity.recordActivity(idUserConnect, "", "",
									"libraries;The favorite " + components.get(i).getProposedSubPanel().getManufacturer()
											+ " " + components.get(i).getProposedSubPanel().getModel()
											+ " is removed from the user " + owner.getFirstName() + " "
											+ owner.getLastName() + ".;Sub Panel",
									true, "", "", "");
						}
						List<PermitProjectSiteInfoEntity> projects = permitProjectSiteInfoEntityRepos
								.findByPermitEntityAuthentificationEntityIdAndProposedSubPanelIn(idContractor, models);
						for (int i = 0; !projects.isEmpty() && i < projects.size(); i++) {
							projects.get(i).setProposedSubPanel("Fav Removed");
							permitProjectSiteInfoEntityRepos.save(projects.get(i));
						}
					} catch (Exception e) {
						e.printStackTrace();
						historicActivity.recordActivity(idUserConnect, "", "",
								"libraries;Remove Propsed Sub Panel from User Favorites List;Sub Panel ", false, "", "",
								"");

					}
				} else if (checkValueTypesService.Equals(componentType, "ATS")) {
					List<ATSFavorite> fav = atsFavRepo.findByIdAtsIdInAndIdUserId(idModels, idContractor);
					for (ATSFavorite f : fav) {
						// Activity Log
						String activityText = "libraries;The favorite " + f.getIdAts().getManufacturer() + " " + f.getIdAts().getManufacturer()
								+ " is removed from the user " + owner.getFirstName() + " " + owner.getLastName()
								+ " favorite List.;ATS";
						historicActivity.recordActivity(idUserConnect, null, null, activityText, true, null, null,
								null);
						atsFavRepo.delete(f);
					}
				} else if (checkValueTypesService.Equals(componentType, "Generator")) {
					List<GeneratorFavorite> fav = generatorFavRepo.findByIdGeneratorIdInAndIdUserId(idModels, idContractor);
					for (GeneratorFavorite f : fav) {
						// Activity Log
						String activityText = "libraries;The favorite " + f.getIdGenerator().getManufacturer() + " " + f.getIdGenerator().getManufacturer()
								+ " is removed from the user " + owner.getFirstName() + " " + owner.getLastName()
								+ " favorite List.;Generator";
						historicActivity.recordActivity(idUserConnect, null, null, activityText, true, null, null,
								null);
						generatorFavRepo.delete(f);
					}
				}
				return "Success";
			} else {
				return "Fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		

	}

	public String checkComponentsSelec(String projet, String user, String param1, String param2, String param3,
			String param4, String param5, String param7, String param8, String param9, String param10,
			String param11, String param13, String param15, String param16,
			String param17, String param18, String param19, Long idUser) {
		Cmodulev2 hasCorrectionRModule = new Cmodulev2();
		Inverters hasCorrectionRInverter = new Inverters();
		DCOptimizerEntity hasCorrectionROptimizer = new DCOptimizerEntity();
		RailRacking hasCorreRailRackingRoof = new RailRacking();
		RailRacking hasCorreRailRackGround = new RailRacking();
		DCCombinerDisconnectEntity hasCorredcComOrDisc = new DCCombinerDisconnectEntity();
		DCCombinerDisconnectEntity hasCorredcComOrDiscTwo = new DCCombinerDisconnectEntity();
		DCCombinerDisconnectEntity hasCorredcComOrDiscThree = new DCCombinerDisconnectEntity();
		ACDisconnect hasCorredAcDisconnectOne = new ACDisconnect();
		ACDisconnect hasCorredAcDisconnectTwo = new ACDisconnect();
		ACCombinerSLC hasACPanel = new ACCombinerSLC();
		RoofAttachmentsEntity hasCorredroofAttachement = new RoofAttachmentsEntity();
		try {
			if (checkValueTypesService.isNumericNotZero(param1)) {
				hasCorrectionRModule = moduleRepo.findById(Long.valueOf(param1)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorrectionRModule != null
						&& checkValueTypesService.Equals(hasCorrectionRModule.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorrectionRModule.getModel(),
							"The " + hasCorrectionRModule.getMake() + " " + hasCorrectionRModule.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.isNumericNotZero(param2)) {
				hasCorrectionRInverter = inverterRepo.findById(Long.valueOf(param2)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorrectionRInverter != null
						&& checkValueTypesService.Equals(hasCorrectionRInverter.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorrectionRInverter.getModel(),
							"The " + hasCorrectionRInverter.getMake() + " " + hasCorrectionRInverter.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.isNumericNotZero(param3)) {
				hasCorrectionROptimizer = convertersRepo.findById(Long.valueOf(param3)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorrectionROptimizer != null
						&& checkValueTypesService.Equals(hasCorrectionROptimizer.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorrectionROptimizer.getModel(),
							"The " + hasCorrectionROptimizer.getManufacturer() + " "
									+ hasCorrectionROptimizer.getModel() + " that needs correction was selected by "
									+ user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.isNumericNotZero(param4)) {
				hasCorreRailRackingRoof = railRackingRepo.findById(Long.valueOf(param4)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorreRailRackingRoof != null
						&& checkValueTypesService.Equals(hasCorreRailRackingRoof.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorreRailRackingRoof.getModel(),
							"The " + hasCorreRailRackingRoof.getManufacturer() + " " + hasCorreRailRackingRoof.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (checkValueTypesService.isNumericNotZero(param5)) {
				hasCorreRailRackGround = railRackingRepo.findById(Long.valueOf(param5)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorreRailRackGround != null
						&& checkValueTypesService.Equals(hasCorreRailRackGround.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorreRailRackGround.getModel(),
							"The " + hasCorreRailRackGround.getManufacturer() + " " + hasCorreRailRackGround.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (checkValueTypesService.isNumericNotZero(param7)) {
				hasCorredcComOrDisc = dCCombinerDisconnectEntityRepo.findById(Long.valueOf(param7)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorredcComOrDisc != null
						&& checkValueTypesService.Equals(hasCorredcComOrDisc.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorredcComOrDisc.getModel(),
							"The " + hasCorredcComOrDisc.getManufacturer() + " " + hasCorredcComOrDisc.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.isNumericNotZero(param8)) {
				hasCorredcComOrDiscTwo = dCCombinerDisconnectEntityRepo.findById(Long.valueOf(param8)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorredcComOrDiscTwo != null
						&& checkValueTypesService.Equals(hasCorredcComOrDiscTwo.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorredcComOrDiscTwo.getModel(),
							"The " + hasCorredcComOrDiscTwo.getManufacturer() + " " + hasCorredcComOrDiscTwo.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.isNumericNotZero(param9)) {
				hasCorredcComOrDiscThree = dCCombinerDisconnectEntityRepo.findById(Long.valueOf(param9)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorredcComOrDiscThree != null
						&& checkValueTypesService.Equals(hasCorredcComOrDiscThree.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorredcComOrDiscThree.getModel(),
							"The " + hasCorredcComOrDiscThree.getManufacturer() + " "
									+ hasCorredcComOrDiscThree.getModel() + " that needs correction was selected by "
									+ user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.contains(param10, ":")) {
				hasCorredAcDisconnectOne = acDisconnectRepo.findById(Long.valueOf(param10.split(":")[0])).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (checkValueTypesService.Equals(hasCorredAcDisconnectOne.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorredAcDisconnectOne.getModel(),
							"The " + hasCorredAcDisconnectOne.getManufacturer() + " "
									+ hasCorredAcDisconnectOne.getModel() + " that needs correction was selected by "
									+ user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.contains(param11, ":")) {
				hasCorredAcDisconnectTwo = acDisconnectRepo.findById(Long.valueOf(param11.split(":")[0])).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorredAcDisconnectTwo != null
						&& checkValueTypesService.Equals(hasCorredAcDisconnectTwo.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorredAcDisconnectTwo.getModel(),
							"The " + hasCorredAcDisconnectTwo.getManufacturer() + " "
									+ hasCorredAcDisconnectTwo.getModel() + " that needs correction was selected by "
									+ user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (checkValueTypesService.isNumericNotZero(param13)) {
				hasACPanel = acCombinerSLCRepo.findById(Long.valueOf(param13)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasACPanel != null && checkValueTypesService.Equals(hasACPanel.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasACPanel.getModel(),
							"The " + hasACPanel.getManufacturer() + " " + hasACPanel.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (checkValueTypesService.isNumericNotZero(param15)) {
				hasCorredroofAttachement = roofAttachmentsRepo.findById(Long.valueOf(param15)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorredroofAttachement != null
						&& checkValueTypesService.Equals(hasCorredroofAttachement.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorredroofAttachement.getModel(),
							"The " + hasCorredroofAttachement.getManufacturer() + " "
									+ hasCorredroofAttachement.getModel() + " that needs correction was selected by "
									+ user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.isNumericNotZero(param16)) {
				hasCorrectionRInverter = inverterRepo.findById(Long.valueOf(param16)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorrectionRInverter != null
						&& checkValueTypesService.Equals(hasCorrectionRInverter.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorrectionRInverter.getModel(),
							"The " + hasCorrectionRInverter.getMake() + " " + hasCorrectionRInverter.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.isNumericNotZero(param17)) {
				hasCorrectionRInverter = inverterRepo.findById(Long.valueOf(param17)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorrectionRInverter != null
						&& checkValueTypesService.Equals(hasCorrectionRInverter.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorrectionRInverter.getModel(),
							"The " + hasCorrectionRInverter.getMake() + " " + hasCorrectionRInverter.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.isNumericNotZero(param18)) {
				hasCorrectionRInverter = inverterRepo.findById(Long.valueOf(param18)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorrectionRInverter != null
						&& checkValueTypesService.Equals(hasCorrectionRInverter.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorrectionRInverter.getModel(),
							"The " + hasCorrectionRInverter.getMake() + " " + hasCorrectionRInverter.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (checkValueTypesService.isNumericNotZero(param19)) {
				hasCorrectionRInverter = inverterRepo.findById(Long.valueOf(param19)).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (hasCorrectionRInverter != null
						&& checkValueTypesService.Equals(hasCorrectionRInverter.getHasCorrectionRequest(), true)) {
					notificationEntityService.addNewNotif(idUser, 0L, "Component thas Correction Request", "Libraries",
							"Component thas Correction Request - " + hasCorrectionRInverter.getModel(),
							"The " + hasCorrectionRInverter.getMake() + " " + hasCorrectionRInverter.getModel()
									+ " that needs correction was selected by " + user + " in the project " + projet,
							true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Done";

	}

	public String AttachmentSendMail(String TypeAttachment, Long idPermit, String EmailOfReceiver,
			String AttachmentEmailSubject, String AttachmentEmailContent, MultipartFile[] uploadedFiles) {
		try {
			if (checkValueTypesService.NotEquals(AttachmentEmailContent, "")) {
				AttachmentEmailContent = AttachmentEmailContent.replaceAll("(\r\n|\n)", "<br />");
			}

			mailingService.attachmentSendMail(TypeAttachment, idPermit, EmailOfReceiver, AttachmentEmailSubject,
					AttachmentEmailContent, uploadedFiles);
			return "success";
		} catch (AddressException e) {
			e.printStackTrace();
			return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public List<String> getRemovedListManufAndModel(String[] removedFavList) {

		List<String> dcModel = new ArrayList<>();
		List<Long> idModels = new ArrayList<>();
		try {
			for (int i = 0; i < removedFavList.length; i++) {
				idModels.add(Long.valueOf(removedFavList[i]));
			}
			List<DCCombinerDisconnectEntity> dcCombinerDisconnect = dCCombinerDisconnectEntityRepo.findByIdIn(idModels);
			for (int i = 0; !dcCombinerDisconnect.isEmpty() && i < dcCombinerDisconnect.size(); i++) {
				String model = dcCombinerDisconnect.get(i).getManufacturer() + ":"
						+ dcCombinerDisconnect.get(i).getModel();
				dcModel.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dcModel;
	}

	public List<String> getRemovedInverterListManufModel(String[] removedFavList) {

		List<String> dcModel = new ArrayList<>();
		List<Long> idModels = new ArrayList<>();
		try {
			for (int i = 0; i < removedFavList.length; i++) {
				idModels.add(Long.valueOf(removedFavList[i]));
			}
			List<Inverters> invertersList = inverterRepo.findByIdIn(idModels);
			for (int i = 0; !invertersList.isEmpty() && i < invertersList.size(); i++) {
				String model = invertersList.get(i).getMake() + ":" + invertersList.get(i).getModel();
				dcModel.add(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dcModel;
	}

	public GetPermitCompanyInfoEntity getUtilityCompanyInfo(Long idPermit, Long idUser) {

		GetPermitCompanyInfoEntity permitUtiliResult = new GetPermitCompanyInfoEntity();
		PermitCompanyInfoEntityResultPrime permitUtili = new PermitCompanyInfoEntityResultPrime();
		PermitEditStatusModel editStatus = new PermitEditStatusModel();

		try {
			permitUtili = permitCompanyInfoRepo.getPermitCompanyInfoEntityResultPrime(idPermit);
			PermitCompanyInfoEntity permitUtiliEntity = permitCompanyInfoRepo.findByPermitEntityId(idPermit);
			if (Boolean.TRUE.equals(permitUtiliEntity.getOpened())) {
				editStatus.setOpened(true);
				editStatus.setHasEditAccess(false);
			} else {
				AuthentificationEntity openBy = userRepo.findById(idUser)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + idUser));
				permitUtiliEntity.setOpened(true);
				permitUtiliEntity.setOpenedBy(openBy);
				permitUtiliEntity.setHasCloseRequest(false);
				permitCompanyInfoRepo.save(permitUtiliEntity);
				editStatus.setOpened(true);
				editStatus.setHasEditAccess(true);
			}
			permitUtiliResult.setPermitCompanyInfoEntity(permitUtili);
			permitUtiliResult.setPermitEditStatusModel(editStatus);
			return permitUtiliResult;

		} catch (Exception e) {
			e.printStackTrace();
			return permitUtiliResult;
		}

	}

	public String saveUtilityCompany(GetPermitByIdResult permitModel, Long idPermit) {
		try {

			PermitCompanyInfoEntity permitCompanyInfoEntity = permitCompanyInfoRepo.findByPermitEntityId(idPermit);
			PermitCompanyInfoEntityResultPrime editCompanyInfoRequest = permitModel.getPermitCompanyInfoEntityResult();
			if (editCompanyInfoRequest != null) {
				permitCompanyInfoEntity.setExistingRate(editCompanyInfoRequest.getExistingRate());
				permitCompanyInfoEntity.setTextOtherRate(editCompanyInfoRequest.getTextOtherRate());

				permitCompanyInfoEntity.setNewRate(editCompanyInfoRequest.getNewRate());
				permitCompanyInfoEntity.setTextOtherNewRate(editCompanyInfoRequest.getTextOtherNewRate());
				permitCompanyInfoEntity.setCustomerParticipated(editCompanyInfoRequest.getCustomerParticipated());

				permitCompanyInfoEntity.setCheckApply(editCompanyInfoRequest.getCheckApply());
				if (checkValueTypesService.NotEquals(editCompanyInfoRequest.getKwhUsage(), "null")) {
					permitCompanyInfoEntity.setKwhUsage(editCompanyInfoRequest.getKwhUsage());
				}

				permitCompanyInfoEntity
						.setPeaceFinancedByEntityOther(editCompanyInfoRequest.getPeaceFinancedByEntityOther());
				/************************************************************************************/
				permitCompanyInfoEntity.setCostPaid(editCompanyInfoRequest.getCostPaid());

				permitCompanyInfoEntity.setInterconnectionType(editCompanyInfoRequest.getInterconnectionType());
				permitCompanyInfoEntity.setServiceAgreement(editCompanyInfoRequest.getServiceAgreement());
				permitCompanyInfoEntity.setClaimedFederal(editCompanyInfoRequest.getClaimedFederal());
				permitCompanyInfoEntity.setNameDeveloper(editCompanyInfoRequest.getNameDeveloper());
				permitCompanyInfoEntity.setAccountNumber(editCompanyInfoRequest.getAccountNumber());
				permitCompanyInfoEntity.setPeaceFinancedByEntity(editCompanyInfoRequest.getPeaceFinancedByEntity());

				/*****************************
				 * CR_003
				 *************************************************/

				permitCompanyInfoEntity.setTypeCustomer(editCompanyInfoRequest.getTypeCustomer());
				permitCompanyInfoEntity.setTypeCustomerOther(editCompanyInfoRequest.getTypeCustomerOther());
				permitCompanyInfoEntity.setTypeCustomerOtherText(editCompanyInfoRequest.getTypeCustomerOtherText());
				permitCompanyInfoEntity.setNewRateCommercial(editCompanyInfoRequest.getNewRateCommercial());
				permitCompanyInfoEntity.setOtherNewRateCommercial(editCompanyInfoRequest.getOtherNewRateCommercial());
				permitCompanyInfoEntity.setSnemApplication(editCompanyInfoRequest.getSnemApplication());
				permitCompanyInfoEntity.setThisIsNewService(editCompanyInfoRequest.getThisIsNewService());
				permitCompanyInfoEntity.setDevelopersNameSecond(editCompanyInfoRequest.getDevelopersNameSecond());
				permitCompanyInfoEntity.setTheProjectIsLocated(editCompanyInfoRequest.getTheProjectIsLocated());
				permitCompanyInfoEntity.setDevelopemName(editCompanyInfoRequest.getDevelopemName());

				permitCompanyInfoEntity.setProjectIs(editCompanyInfoRequest.getProjectIs());
				permitCompanyInfoEntity.setOtherProjectIs(editCompanyInfoRequest.getOtherProjectIs());
				permitCompanyInfoEntity.setProjectWasPace(editCompanyInfoRequest.getProjectWasPace());
				permitCompanyInfoEntity.setChoosePaceFinanced(editCompanyInfoRequest.getChoosePaceFinanced());
				permitCompanyInfoEntity.setElectriccvehichule1(editCompanyInfoRequest.getElectriccvehichule1());
				permitCompanyInfoEntity.setElectricvehichule2(editCompanyInfoRequest.getElectricvehichule2());
				permitCompanyInfoEntity.setElectricvehichule3(editCompanyInfoRequest.getElectricvehichule3());
				permitCompanyInfoEntity.setElectricvehichule4(editCompanyInfoRequest.getElectricvehichule4());
				permitCompanyInfoEntity.setElectricvehichuleOther(editCompanyInfoRequest.getElectricvehichuleOther());
				permitCompanyInfoEntity.setOtherElectricVe(editCompanyInfoRequest.getOtherElectricVe());
				permitCompanyInfoEntity.setApplicationType(editCompanyInfoRequest.getApplicationType());
				permitCompanyInfoEntity
						.setTheAcDisconnectIsMoreThan(editCompanyInfoRequest.getTheAcDisconnectIsMoreThan());
				permitCompanyInfoEntity.setCoverageamount(editCompanyInfoRequest.getCoverageamount());
				permitCompanyInfoEntity.setInsuringcompanyName(editCompanyInfoRequest.getInsuringcompanyName());
				permitCompanyInfoEntity.setExpectedDate(editCompanyInfoRequest.getExpectedDate());
				permitCompanyInfoEntity.setOneOrMoreOfTheAss(editCompanyInfoRequest.getOneOrMoreOfTheAss());
				permitCompanyInfoEntity.setTheLocalPermitting(editCompanyInfoRequest.getTheLocalPermitting());
				permitCompanyInfoEntity.setRequestPGToshutDown(editCompanyInfoRequest.getRequestPGToshutDown());
				permitCompanyInfoEntity.setRequestPGaEToInstalNewS(editCompanyInfoRequest.getRequestPGaEToInstalNewS());
				permitCompanyInfoEntity.setNameOfDevelopersLease(editCompanyInfoRequest.getNameOfDevelopersLease());
				permitCompanyInfoEntity.setClamedfederalIncomeTax(editCompanyInfoRequest.getClamedfederalIncomeTax());
				permitCompanyInfoEntity.setWhichProgram(editCompanyInfoRequest.getWhichProgram());
				permitCompanyInfoEntity.setWhatRuleOrRules(editCompanyInfoRequest.getWhatRuleOrRules());
				permitCompanyInfoEntity
						.setDeEnergizingOfTheService(editCompanyInfoRequest.getDeEnergizingOfTheService());
				permitCompanyInfoEntity.setWhatDayOfTheWeek(editCompanyInfoRequest.getWhatDayOfTheWeek());
				permitCompanyInfoEntity.setWhatTimeOfDay(editCompanyInfoRequest.getWhatTimeOfDay());
				permitCompanyInfoEntity.setWillYouNeedToObtain(editCompanyInfoRequest.getWillYouNeedToObtain());
				permitCompanyInfoEntity.setDescribeThePointInt(editCompanyInfoRequest.getDescribeThePointInt());
				permitCompanyInfoEntity.setTheScirOfTheMain(editCompanyInfoRequest.getTheScirOfTheMain());
				permitCompanyInfoEntity.setPolicy(editCompanyInfoRequest.getPolicy());
				permitCompanyInfoEntity.setPGaEPersonnelWilleNeed(editCompanyInfoRequest.getpGaEPersonnelWilleNeed());
				permitCompanyInfoEntity.setDeveloperrrsName(editCompanyInfoRequest.getDeveloperrrsName());
				permitCompanyInfoEntity.setTextOtherChoosePace(editCompanyInfoRequest.getTextOtherChoosePace());
				permitCompanyInfoEntity
						.setMayOurRepresentativesContact(editCompanyInfoRequest.getMayOurRepresentativesContact());
				permitCompanyInfoEntity.setIAuthorizeAdvanced(editCompanyInfoRequest.getiAuthorizeAdvanced());
				permitCompanyInfoEntity
						.setMeterOrACDisconnectInBuilding(editCompanyInfoRequest.getMeterOrACDisconnectInBuilding());
				permitCompanyInfoEntity.setUnrestrainedAnimal(editCompanyInfoRequest.getUnrestrainedAnimal());
				permitCompanyInfoEntity.setCheckTheApplicableOther(editCompanyInfoRequest.getCheckTheApplicableOther());
				permitCompanyInfoEntity
						.setCheckApplicableBoxesOther(editCompanyInfoRequest.getCheckApplicableBoxesOther());
				permitCompanyInfoEntity.setCheckIfTheProposedSystemProduce(
						editCompanyInfoRequest.getCheckIfTheProposedSystemProduce());
				permitCompanyInfoEntity.setPerformanceMonitAndRep(editCompanyInfoRequest.getPerformanceMonitAndRep());
				permitCompanyInfoEntity.setAnExistingSolarOrWind(editCompanyInfoRequest.getAnExistingSolarOrWind());
				permitCompanyInfoEntity
						.setClamedfederalIncomeTaxStr(editCompanyInfoRequest.getClamedfederalIncomeTaxStr());
				permitCompanyInfoEntity.setListAnyVariations(editCompanyInfoRequest.getListAnyVariations());
				permitCompanyInfoEntity.setTextOtherContractType(editCompanyInfoRequest.getTextOtherContractType());
				permitCompanyInfoEntity.setNewRateOthers(editCompanyInfoRequest.getNewRateOthers());
				permitCompanyInfoEntity.setNewRateOthersText(editCompanyInfoRequest.getNewRateOthersText());

				/*****************************
				 * END CR_003
				 *************************************************/

				/************************************************************************************/
				/***********************
				 * new variable hidden
				 ******************************************/
				permitCompanyInfoEntity.setDevelopersName(editCompanyInfoRequest.getDevelopersName());
				permitCompanyInfoEntity.setDevelopmentName(editCompanyInfoRequest.getDevelopmentName());

				permitCompanyInfoEntity.setNamePartyReceivData(editCompanyInfoRequest.getNamePartyReceivData());
				/**************************************/

				permitCompanyInfoEntity.setContactType(editCompanyInfoRequest.getContactType());

				permitCompanyInfoEntity.setCheckApply(editCompanyInfoRequest.getCheckApply());
				permitCompanyInfoEntity.setContactHomeowner(editCompanyInfoRequest.getContactHomeowner());
				permitCompanyInfoEntity.setSystemOwner(editCompanyInfoRequest.getSystemOwner());

				permitCompanyInfoEntity.setMeterDisco(editCompanyInfoRequest.getMeterDisco());
				permitCompanyInfoEntity.setMeterBuilding(editCompanyInfoRequest.getMeterBuilding());
				permitCompanyInfoEntity.setUndertrainedAnimal(editCompanyInfoRequest.getUndertrainedAnimal());
				permitCompanyInfoEntity.setOtherSystem(editCompanyInfoRequest.getOtherSystem());
				permitCompanyInfoEntity.setOtherSystemText(editCompanyInfoRequest.getOtherSystemText());

				/****************************************/
				boolean isAuthorizated = false;
				if (editCompanyInfoRequest.getAuthorizatingAdvanced() != null) {
					if (checkValueTypesService.Equals(editCompanyInfoRequest.getAuthorizatingAdvanced(), "true")) {
						isAuthorizated = true;
					}
				}

				permitCompanyInfoEntity.setAuthorizatingAdvanced(isAuthorizated);
				permitCompanyInfoEntity.setContactHomeowner(editCompanyInfoRequest.getContactHomeowner());
				permitCompanyInfoEntity.setScir(editCompanyInfoRequest.getScir());
				permitCompanyInfoEntity.setSystemOwner(editCompanyInfoRequest.getSystemOwner());
				boolean isfinanced = false;
				if (editCompanyInfoRequest.getPaceFinanced() != null) {
					if (checkValueTypesService.Equals(editCompanyInfoRequest.getPaceFinanced(), "true")) {
						isfinanced = true;
					}
				}

				permitCompanyInfoEntity.setPaceFinanced(isfinanced);
				permitCompanyInfoEntity.setMeterAccess(editCompanyInfoRequest.getMeterAccess());
				permitCompanyInfoEntity.setPlannedAnnual(editCompanyInfoRequest.getPlannedAnnual());
				boolean isNewService = false;
				boolean isNewSubdivision = false;
				boolean isNonPrfile = false;
				boolean isSystemeMeet = false;
				if (editCompanyInfoRequest.getNewService() != null) {
					if (checkValueTypesService.Equals(editCompanyInfoRequest.getNewService(), "true")) {
						isNewService = true;
					}
				}
				if (editCompanyInfoRequest.getNewSubdivition() != null) {
					if (checkValueTypesService.Equals(editCompanyInfoRequest.getNewSubdivition(), "true")) {
						isNewSubdivision = true;
					}
				}
				if (editCompanyInfoRequest.getNonProfileStatus() != null) {
					if (checkValueTypesService.Equals(editCompanyInfoRequest.getNonProfileStatus(), "true")) {
						isNonPrfile = true;
					}
				}
				if (editCompanyInfoRequest.getSystemMeetDIH() != null) {
					if (checkValueTypesService.Equals(editCompanyInfoRequest.getSystemMeetDIH(), "true")) {
						isSystemeMeet = true;
					}
				}
				permitCompanyInfoEntity.setNewService(isNewService);
				permitCompanyInfoEntity.setNewSubdivition(isNewSubdivision);
				permitCompanyInfoEntity.setNonProfileStatus(isNonPrfile);
				permitCompanyInfoEntity.setSystemMeetDIH(isSystemeMeet);
				permitCompanyInfoEntity.setJBoxUsedBetween(editCompanyInfoRequest.getjBoxUsedBetween());
				permitCompanyInfoEntity.setUploadCommentsUtility(editCompanyInfoRequest.getUploadCommentsUtility());
				permitCompanyInfoEntity.setUploadFileJustification(editCompanyInfoRequest.getUploadFileJustification());
				permitCompanyInfoEntity.setUploadFileExisting(editCompanyInfoRequest.getUploadFileExisting());
				permitCompanyInfoEntity.setUploadFileListAdreess(editCompanyInfoRequest.getUploadFileListAdreess());
				permitCompanyInfoEntity.setUploadFileSwitchgear(editCompanyInfoRequest.getUploadFileSwitchgear());
				permitCompanyInfoEntity
						.setUploadFileInterconnection(editCompanyInfoRequest.getUploadFileInterconnection());

				permitCompanyInfoRepo.save(permitCompanyInfoEntity);
				return "success";
			} else
				return "fail";
		} catch (Exception e) {
			return "fail";
		}

	}

	public double getModuleNameSpace(String pvModule) {

		try {
			double moduleNameSpace;
			if (checkValueTypesService.isNumericNotZero(pvModule)) {
				String nameSpace = moduleRepo.findSTC(Long.valueOf(pvModule));
				moduleNameSpace = checkValueTypesService.isStringNotEmpty(nameSpace)
						? Double.parseDouble(nameSpace.contains(",") ? nameSpace.replace(",", ".") : nameSpace)
						: 0;
				return moduleNameSpace;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ElectricalUtilityEntity getUtilityCompany(String id) {
		try {
			if (checkValueTypesService.isNumericNotZero(id)) {
				return electricalUtilityRepo.findById(Long.parseLong(id)).orElse(null);
			}
			return new ElectricalUtilityEntity();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ElectricalUtilityEntity();
		}

	}

	public PermitEntity getPermit(Long idPermit) {

		try {
			return permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getUtilityName(String utility) {
		try {
			if (checkValueTypesService.isNumericNotZero(utility)) {
				return electricalUtilityRepo.getUtilityCompanyName(Long.parseLong(utility));
			} else
				return "";

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String insertNoteFiletoADV(MultipartFile file, Long idPermit, String fileName, String folderName,
			PermitEntity project) {

		try {
			byte[] bytes5 = file.getBytes();
			File oldFile = new File(pathRepo.findFilePath() + idPermit + "/roofNote/");
			String[] myFiles;
			myFiles = oldFile.list();
			if (myFiles != null) {
				for (int i = 0; i < myFiles.length; i++) {
					File myFile = new File(oldFile, myFiles[i]);
					myFile.delete();
				}
			}
			new File(pathRepo.findFilePath() + idPermit + "/roofNote").mkdirs();
			Path pathUp5 = Paths.get(pathRepo.findFilePath() + idPermit + "/roofNote/" + fileName);
			Files.write(pathUp5, bytes5);

			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(project.getAuthentificationEntity());

			// A.B 08-26: CR-2847 Save File to google drive
			String googleDriveFilePath = pathRepo.findGoogleDriveFilePath();
			if (new File(googleDriveFilePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter/").exists()) {
				Path expDestFile = Paths.get(googleDriveFilePath + ownerFolderName + "/" + folderName
						+ "/Drafting/To Drafter/" + "Roof Material Type Note-" + fileName);
				com.google.common.io.Files.copy(pathUp5.toFile(), expDestFile.toFile());
			}

			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String getRoofAttachment(String roofMaterial, Long idPermit) {
		String attachment = "";
		try {
			Long user = permitRepo.findProjectOwnerID(idPermit);
			RoofTypeandAttachmentEntity roofAttachment = roofTypeandAttachmentRepo.findByAuthentificationEntityId(user);
		
			if (roofAttachment != null) {

				if (checkValueTypesService.Equals(roofMaterial, "1")) {
					if (roofAttachment.getCompShingle() != null) {
						attachment = roofAttachment.getCompShingle();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "2")) {
					if (roofAttachment.getFlatConcrete() != null) {
						attachment = roofAttachment.getFlatConcrete();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "3")) {
					if (roofAttachment.getRomanCurved() != null) {
						attachment = roofAttachment.getRomanCurved();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "4")) {
					if (roofAttachment.getStandingMetal() != null) {
						attachment = roofAttachment.getStandingMetal();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "5")) {
					if (roofAttachment.getBarrelCurve() != null) {
						attachment = roofAttachment.getBarrelCurve();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "6")) {
					if (roofAttachment.getRolledComp() != null) {
						attachment = roofAttachment.getRolledComp();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "7")) {
					if (roofAttachment.getCorrugatedMetal() != null) {
						attachment = roofAttachment.getCorrugatedMetal();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "8")) {
					if (roofAttachment.getTrapezoidalMetal() != null) {
						attachment = roofAttachment.getTrapezoidalMetal();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "9")) {
					if (roofAttachment.getWoodorCedar() != null) {
						attachment = roofAttachment.getWoodorCedar();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "10")) {
					if (roofAttachment.getClayTile() != null) {
						attachment = roofAttachment.getClayTile();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "11")) {
					if (roofAttachment.getFiberCement() != null) {
						attachment = roofAttachment.getFiberCement();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "12")) {
					if (roofAttachment.getSlate() != null) {
						attachment = roofAttachment.getSlate();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "13")) {
					if (roofAttachment.getMembraneTpo() != null) {
						attachment = roofAttachment.getMembraneTpo();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "14")) {
					if (roofAttachment.getBuildUp() != null) {
						attachment = roofAttachment.getBuildUp();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "15")) {
					if (roofAttachment.getRolledAsphalt() != null) {
						attachment = roofAttachment.getRolledAsphalt();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "16")) {
					if (roofAttachment.getFoam() != null) {
						attachment = roofAttachment.getFoam();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "17")) {
					if (roofAttachment.getLiquidApplied() != null) {
						attachment = roofAttachment.getLiquidApplied();
					}
				} else if (checkValueTypesService.Equals(roofMaterial, "18")) {
					if (roofAttachment.getCorrugatedPolyCarb() != null) {
						attachment = roofAttachment.getCorrugatedPolyCarb();
					}
				}
			}
	
			if (checkValueTypesService.NotEquals(attachment, "")) {
				Boolean roofAttachmentFavExist = roofAttachmentsFavRepo
						.existsByAuthentificationEntityIdAndRoofAttachmentId(user, Long.valueOf(attachment));
				if (!roofAttachmentFavExist)
					attachment = "";
			}
			return attachment;
		} catch (Exception e) {
			e.printStackTrace();
			return attachment;
		}
	}

	public String mapExistingConductor(String componentType, Long idPermit) {
		try {
			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			PermitConduitConductorSectionEntity permitConduitConductor = permitConduitConductorRepo.findByPermitEntityId(idPermit);
			if (checkValueTypesService.Equals(componentType, "Module")) {
				permit.setExistModule(true);
				permitConduitConductor.setCircuitEnvironment("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "Optimizer")) {
				permit.setExistOptimizer(true);
				permitConduitConductor.setCircuitEnvironmentTwo("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "DCJBOX")) {
				permit.setExistdcJunctionBox(true);
				permitConduitConductor.setCircuitEnvironmentThree("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "DCC")) {
				permit.setExistdcDcCombiner(true);
				permitConduitConductor.setCircuitEnvironmentFour("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "DCD")) {
				permit.setExistdcDcdisconnect(true);
				permitConduitConductor.setCircuitEnvironmentFive("EXISTING");
			} else if ((checkValueTypesService.Equals(componentType, "INV")
					|| checkValueTypesService.Equals(componentType, "MINV"))) {
				permit.setExistInverter(true);
				permitConduitConductor.setCircuitEnvironmentSix("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "ACJBOX")) {
				permit.setExistAcJunctionBox(true);
				permitConduitConductor.setCircuitEnvironmentSeven("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "ACC")) {
				permit.setExistAcCombiner(true);
				permitConduitConductor.setCircuitEnvironmentEight("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "ACD")) {
				permit.setExistAcDisconnect(true);
				permitConduitConductor.setCircuitEnvironmentNine("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "ACDTwo")) {
				permit.setExistAcDisconnect(true);
				permitConduitConductor.setCircuitEnvironmentNineTwo("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "PMETER")) {
				permit.setExistProductionMeter(true);
				permitConduitConductor.setCircuitEnvironmentTen("EXISTING");
			} else if (checkValueTypesService.Equals(componentType, "ACSUBPANEL")) {
				permit.setExistSubPanel(true);
				permitConduitConductor.setCircuitEnvironmentEleven("EXISTING");
			}
			permitConduitConductorRepo.save(permitConduitConductor);
			permitRepo.save(permit);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String mapNoExistingConductor(String componentType, Long idPermit) {
		try {

			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			if (checkValueTypesService.Equals(componentType, "Module")) {
				permit.setExistModule(false);
			} else if (checkValueTypesService.Equals(componentType, "Optimizer")) {
				permit.setExistOptimizer(false);
			} else if (checkValueTypesService.Equals(componentType, "DCJBOX")) {
				permit.setExistdcJunctionBox(false);
			} else if (checkValueTypesService.Equals(componentType, "DCC")) {
				permit.setExistdcDcCombiner(false);
			} else if (checkValueTypesService.Equals(componentType, "DCD")) {
				permit.setExistdcDcdisconnect(false);
			} else if ((checkValueTypesService.Equals(componentType, "INV")
					|| checkValueTypesService.Equals(componentType, "MINV"))) {
				permit.setExistInverter(false);
			} else if (checkValueTypesService.Equals(componentType, "ACJBOX")) {
				permit.setExistAcJunctionBox(false);
			} else if (checkValueTypesService.Equals(componentType, "ACC")) {
				permit.setExistAcCombiner(false);
			} else if (checkValueTypesService.Equals(componentType, "ACD")) {
				permit.setExistAcDisconnect(false);
			} else if (checkValueTypesService.Equals(componentType, "PMETER")) {
				permit.setExistProductionMeter(false);
			} else if (checkValueTypesService.Equals(componentType, "ACSUBPANEL")) {
				permit.setExistSubPanel(false);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 18-04-2019: C.I: Check if the component is deleted
	public String isComponentDeleted(Long idComponent, String componentType) {
		try {
			String isComponentDeleted = "";
			if (checkValueTypesService.Equals(componentType, "Module")) {
				isComponentDeleted = Boolean.TRUE.equals(moduleRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if ((checkValueTypesService.Equals(componentType, "INV"))) {
				isComponentDeleted = Boolean.TRUE.equals(inverterRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "Optimizer")) {
				isComponentDeleted = Boolean.TRUE.equals(convertersRepo.getConverterDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "Flashing")) {
				isComponentDeleted = Boolean.TRUE.equals(flashingRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "RailRacking")) {
				isComponentDeleted = Boolean.TRUE.equals(railRackingRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "RoofAttachment")) {
				isComponentDeleted = Boolean.TRUE.equals(roofAttachmentsRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "ACDisconnect")) {
				isComponentDeleted = Boolean.TRUE.equals(acDisconnectRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "ACCombiner")) {
				isComponentDeleted = Boolean.TRUE.equals(acCombinerSLCRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "JBox")
					|| checkValueTypesService.Equals(componentType, "DCDisconnect")) {
				isComponentDeleted = Boolean.TRUE.equals(dCCombinerDisconnectEntityRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "Revenue or Performance Monitoring Meter")) {
				isComponentDeleted = Boolean.TRUE.equals(leasePPAMeterRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "battery")) {
				isComponentDeleted = Boolean.TRUE.equals(batteryRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "tiltLegs")) {
				isComponentDeleted = Boolean.TRUE.equals(tiltLegsRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "Proposed Sub Panel")) {
				isComponentDeleted = Boolean.TRUE.equals(proposedSubPanelRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "ats")) {
				isComponentDeleted = Boolean.TRUE.equals(atsRepo.findIfDeleted(idComponent)) ? "true" : "false";

			} else if (checkValueTypesService.Equals(componentType, "generator")) {
				isComponentDeleted = Boolean.TRUE.equals(generatorRepo.findIfDeleted(idComponent)) ? "true" : "false";

			}
			return isComponentDeleted;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public Double[] getModPerMicro(Long microInverter) {

		double modelPerMicro = 0;
		double iacmaxSum = 0;
		Double[] microInverterCh = new Double[2];
		try {
			if (checkValueTypesService.isLongPositive(microInverter)) {
				Integer modPerMicro = inverterRepo.getMicroInverterModPerMicro(microInverter);
				modelPerMicro = modPerMicro != null ? Double.parseDouble(modPerMicro + "") : 0;
				String iacmax = inverterRepo.getInverterIacmax(microInverter);
				iacmaxSum = iacmax != null
						? Double.parseDouble(iacmax.contains(",") ? iacmax.replace(",", ".") : iacmax)
						: 0;
			}
			microInverterCh[0] = modelPerMicro;
			microInverterCh[1] = iacmaxSum;

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return microInverterCh;
	}

	public String getOcpdMax(Long microInverterId) {
		String ocpdMax = "";
		try {

			if (checkValueTypesService.isLongPositive(microInverterId)) {
				ocpdMax = inverterRepo.getMicroInverterOcpdMax(microInverterId);
			}
			return ocpdMax;

		} catch (Exception e) {
			e.printStackTrace();
			return ocpdMax;
		}

	}

	// CR-2424
	// 02-27-2019: M.A: send Mail when we have a problem in the save
	String traiterException(Exception e, String projectName, String projectOwner, String ownerMail) {
		Boolean contain = false;
		int k = 0;
		if (e != null) {
			while (Boolean.FALSE.equals(contain) && k < e.getStackTrace().length) {
				if (e.getStackTrace()[k].toString().contains("com.PlayGroundAdv.Solar")) {
					contain = true;
				} else {
					k++;
				}
			}
		}
		try {
			if (Boolean.TRUE.equals(contain)) {
				String exceptionInfo = "<li>Error cause : " + e + ".</li><li>Error location : In file "
						+ e.getStackTrace()[k].getFileName() + " within the method : "
						+ e.getStackTrace()[k].getMethodName() + " at line : " + e.getStackTrace()[k].getLineNumber()
						+ ".</li>";
				Date dateNow = new Date();
				String exceptionText = "Hello,<br><br>" + projectOwner
						+ " has a technical problem when submission  the project " + projectName
						+ ".<ul><li>Project created by : " + projectOwner + ".</li><li>Submit date : " + dateNow
						+ "</li>" + exceptionInfo
						+ "</ul><br>Please check this Error as soon as possible.<br><br>Thank you.";

				mailingService.sendingMailPMSubmitEvaluation(ownerMail,
						"Project Submission Technical Problem - " + projectName, exceptionText, projectName,
						projectOwner);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "done";
	}

	public String saveConductorSizeNotes(String note, Long idProject) {

		PermitProjectSiteInfoEntity permitProjectSiteInfo = new PermitProjectSiteInfoEntity();
		try {

			permitProjectSiteInfo = permitProjectSiteInfoEntityRepos.findByPermitEntityId(idProject);
			permitProjectSiteInfo.setSubPanelConductorSizeNote(note);
			permitProjectSiteInfoEntityRepos.save(permitProjectSiteInfo);
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	public ResponseEntity<byte[]> downloadADVNote(Long idPermit) {

		String dirPath = pathRepo.findFilePath();
		Path path = Paths.get(dirPath + idPermit + "/roofNote/");
		String extension = "";
		String url = "";
		if (new File(dirPath + idPermit + "/roofNote/").exists()) {
			String[] myFiles;
			myFiles = new File(dirPath + idPermit + "/roofNote/").list();

			for (int i = 0; i < myFiles.length; i++) {
				File myFile = new File(new File(dirPath + idPermit + "/roofNote/"), myFiles[i]);
				url = myFile.getName();
				extension = FilenameUtils.getExtension(dirPath + idPermit + "/roofNote/" + url);
			}
			path = Paths.get(dirPath + idPermit + "/roofNote/" + url);
			byte[] contents = null;
			try {
				contents = Files.readAllBytes(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HttpHeaders headers = new HttpHeaders();
			if (extension.equals(".pdf")) {
				headers.setContentType(MediaType.parseMediaType("application/pdf"));
			} else {
				headers.setContentType(MediaType.parseMediaType("application/image"));
			}
			String filename = url;
			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
					org.springframework.http.HttpStatus.OK);
			return response;
		}
		return null;

	}

	public String getNoteFilename(Long idPermit) {
		String url = "";
		String dirPath = pathRepo.findFilePath();
		if (new File(dirPath + idPermit + "/roofNote/").exists()) {
			String[] myFiles;
			myFiles = new File(dirPath + idPermit + "/roofNote/").list();

			for (int i = 0; i < myFiles.length; i++) {
				File myFile = new File(new File(dirPath + idPermit + "/roofNote/"), myFiles[i]);
				url = myFile.getName();
			}
		}

		return url;
	}

	public ResponseEntity<byte[]> getPicsRFI(String url) {

		String dirPath = pathRepo.findFilePath();
		File folder = new File(dirPath + "RFI");
		File[] listOfFiles = folder.listFiles();
		String attachname = "";
		String extension = "";
		for (File f : listOfFiles) {
			if (f.getName().indexOf(url) != -1) {
				attachname = f.getName();
				extension = FilenameUtils.getExtension(attachname);
			}
		}
		Path path = Paths.get(dirPath + "RFI/" + attachname);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		if (extension.equals("pdf")) {
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
		} else {
			headers.setContentType(MediaType.parseMediaType("image/png"));
		}

		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
		return response;
	}

	private String generateZipEntry(String file, String SOURCE_FOLDER) {
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}

	public void generateFileList(File node, List<String> fileList, String SOURCE_FOLDER) {
		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.toString(), SOURCE_FOLDER));
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename), fileList, SOURCE_FOLDER);
			}
		}
	}

	public void zipIt(String zipFile, List<String> fileList, String SOURCE_FOLDER) {
		byte[] buffer = new byte[1024];
		String source = new File(SOURCE_FOLDER).getName();
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);

			FileInputStream in = null;

			for (String file : fileList) {
				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try {
					in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
					int len;
					while ((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				} finally {
					in.close();
				}
			}

			zos.closeEntry();

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ResponseEntity<byte[]> downloadConductorSizeFiles(Long idPermit) {

		String dirPath = pathRepo.findFilePath();
		String OUTPUT_ZIP_FILE = dirPath + idPermit + "/ConductorSizeNotes.zip";
		String SOURCE_FOLDER = dirPath + idPermit + "/ConductorSizeNotes"; // SourceFolder path
		List<String> fileList = new ArrayList<>();

		generateFileList(new File(SOURCE_FOLDER), fileList, SOURCE_FOLDER);
		zipIt(OUTPUT_ZIP_FILE, fileList, SOURCE_FOLDER);

		String url = dirPath + idPermit + "/ConductorSizeNotes.zip";
		Path path = Paths.get(url);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
		return response;
	}

	public ResponseEntity<byte[]> getPicTest(String url) {

		String dirPath = pathRepo.findFilePath();
		Path path = Paths.get(dirPath + url);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
		return response;
	}

	public List<String> getUtilityList() {

		try {
			return electricalUtilityRepo.getUtilityList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<ComponentModel> getAllInverters() {

		try {
			return inverterRepo.getAllModels();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ComponentModel> getAllRailToRoofConnection() {

		try {
			return roofAttachmentsRepo.getAllModels();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ComponentModel> getAllRailRackingList() {

		try {
			return railRackingRepo.getAllModels();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ComponentModel> getAllBattery() {

		try {
			return batteryRepo.getAllModels();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ComponentModel> getTiltLegsList() {

		try {
			return tiltLegsRepo.getAllModels();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Page<PermitResponsePrime> searchProject(SearchProject searchProject) {
		Pageable pageable = PageRequest.of(searchProject.getNbPage(), searchProject.getSizePage());
	    int month = 0;
		int year = 0;
		int lastday = 30;
		if (checkValueTypesService.contains(searchProject.getCreationDate(), "/")) {
			month = Integer.parseInt(searchProject.getCreationDate().split("/")[0]);
			year = Integer.parseInt(searchProject.getCreationDate().split("/")[1]);
		}
		if(month == 2) {
			lastday = 28;
		}else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 9 || month == 10 || month == 12) {
			lastday = 31;
		}
		TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
		Calendar c = Calendar.getInstance();
		c.set(year, month, 0, 0, 0);
		Calendar cBeforeRange = Calendar.getInstance();
		cBeforeRange.set(year, month - searchProject.getDateRange() - 1, 1, 0, 0);
		Calendar cAfterRange = Calendar.getInstance();
		cAfterRange.set(year, month + searchProject.getDateRange() - 1, lastday, 0, 0);

		Calendar cBeforeSameMonth = Calendar.getInstance();
		cBeforeSameMonth.set(year, month -1, 1, 0, 0);
		Calendar cAfterSameMonth = Calendar.getInstance();
		cAfterSameMonth.set(year, month - 1, lastday, 0, 0);

		Date dateBeforeRange = cBeforeRange.getTime();
		Date dateAfterRange = cAfterRange.getTime();

		Date dateBeforeSameMonth = cBeforeSameMonth.getTime();
		Date dateAfterSameMonth = cAfterSameMonth.getTime();

		
		if (searchProject.getProjectJurisdiction() == null) {
			searchProject.setProjectJurisdiction("");
		}
		if (searchProject.getCity() == null) {
			searchProject.setCity("");
		}
		if (checkValueTypesService.Equals(searchProject.getState(), "")) {
			searchProject.setState(null);
		}
		if (checkValueTypesService.Equals(searchProject.getUtilityCompany(), "")) {
			searchProject.setUtilityCompany(null);
		}
		if (checkValueTypesService.Equals(searchProject.getRoofRafter(), "")) {
			searchProject.setRoofRafter(null);
		}
		if (checkValueTypesService.Equals(searchProject.getRoofMaterial(), "")) {
			searchProject.setRoofMaterial(null);
		}
		if (checkValueTypesService.Equals(searchProject.getRailToRoof(), "")) {
			searchProject.setRailToRoof(null);
		}
		
		Long searchRailRaiking = null;
		if (checkValueTypesService.isNumericNotZero(searchProject.getRailRacking())) {
			searchRailRaiking = Long.valueOf(searchProject.getRailRacking());
		}
		Long searchedInverterModel = null;

		if (checkValueTypesService.isNumericNotZero(searchProject.getInverterModel())) {
			searchedInverterModel = Long.valueOf(searchProject.getInverterModel());
		}
		Long battery = null;
		List<Long> projectByBattery = null;
		if (checkValueTypesService.isNumericNotZero(searchProject.getBattery())) {
			battery = Long.valueOf(searchProject.getBattery());
			projectByBattery = projectBatteryRepo.findProjectsByBattery(battery);
		}
		if (checkValueTypesService.Equals(searchProject.getTiltLeg(), "")) {
			searchProject.setTiltLeg(null);
		}
		if (checkValueTypesService.Equals(searchProject.getCreationDate(), "")) {
			searchProject.setCreationDate(null);
		}
		if (checkValueTypesService.Equals(searchProject.getTypeOfSystem(), "")) {
			searchProject.setTypeOfSystem(null);
		}
		if (checkValueTypesService.Equals(searchProject.getEngineerType(), "")) {
			searchProject.setEngineerType(null);
		}
		if (checkValueTypesService.NotEquals(searchProject.getProjectJurisdiction(), "")) {
			searchProject.setProjectJurisdiction(searchProject.getProjectJurisdiction().toLowerCase().trim());
		}
		if (checkValueTypesService.NotEquals(searchProject.getCity(), "")) {
			searchProject.setCity(searchProject.getCity().toLowerCase().trim());
		}
		if (checkValueTypesService.NotEquals(searchProject.getState(), "")) {
			searchProject.setState(searchProject.getState().toLowerCase().trim());
		}
		if (checkValueTypesService.NotEquals(searchProject.getTypeOfSystemOther(), "")) {
			searchProject.setTypeOfSystemOther(searchProject.getTypeOfSystemOther().toLowerCase().trim());
		}
		if (checkValueTypesService.NotEquals(searchProject.getRoofRafterOther(), "")) {
			searchProject.setRoofRafterOther(searchProject.getRoofRafterOther().toLowerCase().trim());
		}
		Boolean includeATS = checkValueTypesService.isStringNotEmpty(searchProject.getAts());
		Long ats = null;
		if (checkValueTypesService.isNumericNotZero(searchProject.getAts())) {
			ats = Long.valueOf(searchProject.getAts());
		}
		if(checkValueTypesService.isStringNotEmpty(searchProject.getSortField()) && checkValueTypesService.isStringNotEmpty(searchProject.getSortOrder())) {
		String sortParam = redefinitionCol(searchProject.getSortField(), searchProject.getSortOrder());
		return permitRepo.searchProjectSort(searchProject.getProjectJurisdiction(),
				searchProject.getCity(), searchProject.getState(), searchProject.getUtilityCompany(),
				searchProject.getRoofRafter(), searchProject.getRoofMaterial(), searchProject.getRailToRoof(),
				searchRailRaiking, searchedInverterModel, projectByBattery, searchProject.getTiltLeg(),
				searchProject.getTypeOfSystem(), searchProject.getTypeOfSystemOther(),
				searchProject.getUtilityCompanyOther(), searchProject.getRoofRafterOther(),
				searchProject.getEngineerType(), 
				searchProject.getPvModule(), searchProject.getEngineerBy(), includeATS,
				ats, searchProject.getTypeGridTied(), searchProject.getPoc(),		
				dateBeforeRange, dateAfterRange,
				searchProject.getCreationDate(), searchProject.getDateRange(), dateBeforeSameMonth, dateAfterSameMonth, sortParam, pageable);
		}else {
			return permitRepo.searchProject(searchProject.getProjectJurisdiction(),
					searchProject.getCity(), searchProject.getState(), searchProject.getUtilityCompany(),
					searchProject.getRoofRafter(), searchProject.getRoofMaterial(), searchProject.getRailToRoof(),
					searchRailRaiking, searchedInverterModel, projectByBattery, searchProject.getTiltLeg(),
					searchProject.getTypeOfSystem(), searchProject.getTypeOfSystemOther(),
					searchProject.getUtilityCompanyOther(), searchProject.getRoofRafterOther(),
					searchProject.getEngineerType(), 
					searchProject.getPvModule(), searchProject.getEngineerBy(), includeATS,
					ats, searchProject.getTypeGridTied(), searchProject.getPoc(),		
					dateBeforeRange, dateAfterRange,
					searchProject.getCreationDate(), searchProject.getDateRange(), dateBeforeSameMonth, dateAfterSameMonth, pageable);
		}
	}
	
	public Page<PermitResponsePrime> globalSearchSuperUser(String mailUser, String filteredValue, Integer page, Integer size) {
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		Pageable pageable = PageRequest.of(page, size);		
		try {
			if ((mailUser != null && mailUser.contains("nuagetechnologies-tn.com"))
					|| checkValueTypesService.Equals(mailUser, "nabil-g@advpermits.com")) {
			return permitRepo.getSearchedPermitsSuperUser(filteredValue.toLowerCase(), pageable);
			}else {
				return permitRepo.getSearchedPermitsSuperUserNotIn(filteredValue.toLowerCase(), pageable);
			}
		    } catch (Exception e) {
			e.printStackTrace();
			return new PageImpl<>(new ArrayList<>());
		}
		
	}
	
	public Page<PermitResponsePrime> globalSearchSuperUserSort(String mailUser, String filteredValue, Integer page, Integer size, String sortField, String sortOrder) {
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		String sortParam = redefinitionCol(sortField, sortOrder);
		Pageable pageable = PageRequest.of(page, size);		
		try {
			if ((mailUser != null && mailUser.contains("nuagetechnologies-tn.com"))
					|| checkValueTypesService.Equals(mailUser, "nabil-g@advpermits.com")) {
			return permitRepo.getSearchedPermitsSuperUserSort(filteredValue.toLowerCase(), sortParam, pageable);
			}else {
				return permitRepo.getSearchedPermitsSuperUserNotInSort(filteredValue.toLowerCase(), sortParam, pageable);
			}
		    } catch (Exception e) {
			e.printStackTrace();
			return new PageImpl<>(new ArrayList<>());
		}
		
	}
	
	public Page<PermitResponsePrime> globalSearchDrafter(String filteredValue, Integer page, Integer size) {
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		Pageable pageable = PageRequest.of(page, size);		
		try {
			return permitRepo.getSearchedPermitsDrafter(filteredValue.toLowerCase(), pageable);

		    } catch (Exception e) {
			e.printStackTrace();
			return new PageImpl<>(new ArrayList<>());
		}		
	}
	
	public Page<PermitResponsePrime> globalSearchDrafterSort(String filteredValue, Integer page, Integer size, String sortField, String sortOrder) {
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		String sortParam = redefinitionCol(sortField, sortOrder);
		Pageable pageable = PageRequest.of(page, size);		
		try {
			return permitRepo.getSearchedPermitsDrafterSort(filteredValue.toLowerCase(), sortParam, pageable);

		    } catch (Exception e) {
			e.printStackTrace();
			return new PageImpl<>(new ArrayList<>());
		}		
	}
	
	public Page<PermitResponsePrime> globalSearchContractor(Long userID, String filteredValue, Integer page, Integer size) {		
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		Pageable pageable = PageRequest.of(page, size);
		
		try {
			AuthentificationEntity findUser = userRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));
			return permitRepo.getSearchedPermitsContractor(findUser.getCompany() != null ? findUser.getCompany().trim().toUpperCase() : null, userID, filteredValue.toLowerCase(), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<PermitResponsePrime> globalSearchContractorSort(Long userID, String filteredValue, Integer page, Integer size, String sortField, String sortOrder) {		
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		String sortParam = redefinitionCol(sortField, sortOrder);
		Pageable pageable = PageRequest.of(page, size);
		
		try {
			AuthentificationEntity findUser = userRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));
			return permitRepo.getSearchedPermitsContractorSort(findUser.getCompany() != null ? findUser.getCompany().trim().toUpperCase() : null, userID, sortParam, filteredValue.toLowerCase(), pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public InvertersModels getInvertersModels(Long idPermit) {

		InvertersModels inverterModels = new InvertersModels();
		try {
			PermitArraysEntity permitArraysEntity = projectArraysRepo.findByPermitEntityId(idPermit);
			inverterModels.setPvModuleModEl(
					permitArraysEntity.getPvModule() != null ? permitArraysEntity.getPvModule().getId() : null);
			inverterModels.setMicroInverter(permitArraysEntity.getMicroInverterEntity() != null
					? permitArraysEntity.getMicroInverterEntity().getId()
					: null);
			// A.B 01-14 Inverter Model Refactoring
			inverterModels.setInverterModel(
					permitArraysEntity.getFirstInverter() != null ? permitArraysEntity.getFirstInverter().getId()
							: null);
			inverterModels.setSecondInverterModel(
					permitArraysEntity.getSecondInverter() != null ? permitArraysEntity.getSecondInverter().getId()
							: null);
			inverterModels.setThirdInverterModel(
					permitArraysEntity.getThirdInverter() != null ? permitArraysEntity.getThirdInverter().getId()
							: null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return inverterModels;

	}

//27/08/2019 : CI /CR2860 GET Conduit Run when select an inverter technology
	public String getConduitRun(Long idPermit, String inverterTechnology) {

		String conduitRun = "";
		try {

			AuthentificationEntity user = new AuthentificationEntity();
			PermitEntity permitEntity = permitRepo.findById(idPermit).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
			user = permitEntity.getAuthentificationEntity();
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(user.getId());
			SiteSurveyEntity siteSurvey = siteSurveyRepo.findByportalProjectId(idPermit);
			if (siteSurvey != null) {
				if ((checkValueTypesService.Equals(siteSurvey.isWireRunOnRoof(), true)
						&& getProjectDetailsUtils.isStringOrOptimizerProject(inverterTechnology))
						|| (checkValueTypesService.Equals(siteSurvey.isWireRunOnRoof(), true)
								&& getProjectDetailsUtils.isMicroOrAcModuleProject(inverterTechnology)
								&& (userSetting.getUseRomexInAttic() == null
										|| checkValueTypesService.NotEquals(userSetting.getUseRomexInAttic(), true)))) {
					conduitRun = "Roof Top";
				} else if (checkValueTypesService.Equals(siteSurvey.isWireRunInAttic(), true)
						&& getProjectDetailsUtils.isStringOrOptimizerProject(inverterTechnology)) {
					conduitRun = "Attic";
				} else if (checkValueTypesService.Equals(siteSurvey.isWireRunInAttic(), true)
						&& getProjectDetailsUtils.isMicroOrAcModuleProject(inverterTechnology)
						&& (userSetting.getUseRomexInAttic() == null
								|| checkValueTypesService.NotEquals(userSetting.getUseRomexInAttic(), true))) {
					conduitRun = "Attic";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conduitRun;

	}

	public Integer getDrafterSheetsNumber(Long idPermit) {
		PDDocument doc;
		try {
			String path = pathRepo.findFilePath();
			;
			String pdfName = permitDrafterDataRepo.findParcelMapName(idPermit);
			if (new File(path + idPermit + "/drafterfiles/" + pdfName).exists()) {
				doc = PDDocument.load(new File(path + idPermit + "/drafterfiles/" + pdfName));
				return doc.getNumberOfPages();
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String CheckIfProjectNameExist(String Firstname, String homeOwnLastName, String projectName, Long idPermit) {

		try {
			if (checkValueTypesService.Equals(Firstname, "")) {
				Firstname = null;
			}
			if (checkValueTypesService.Equals(homeOwnLastName, "")) {
				homeOwnLastName = null;
			}
			if (checkValueTypesService.Equals(projectName, "")) {
				projectName = null;
			}

			String name2 = Firstname;
			String homeOwnLastName2 = homeOwnLastName;
			String projectName2 = projectName;
			if (checkValueTypesService.NotEquals(Firstname, "")) {
				name2 = Firstname.toLowerCase().trim();
			}
			if (checkValueTypesService.NotEquals(homeOwnLastName, "")) {
				homeOwnLastName2 = homeOwnLastName.toLowerCase().trim();
			}
			if (checkValueTypesService.NotEquals(projectName, "")) {
				projectName2 = projectName.toLowerCase().trim();
			}
			Boolean isHomeownerNameExist = permitRepo.isProjectExist(name2, homeOwnLastName2, projectName2, idPermit);
			if (checkValueTypesService.Equals(isHomeownerNameExist, true))
				return "exist";
			return "Doesn't exist";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";

		}
	}

	public UserSettingEntity getProjectOwnerSettings(Long idPermit) {

		UserSettingEntity userSetting2 = new UserSettingEntity();
		try {
			AuthentificationEntity user = permitRepo.findAuthentificationEntityByID(idPermit);
			AuthentificationEntity user2 = new AuthentificationEntity();
			user2.setId(user.getId());
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(user.getId());
			userSetting2.setNeutralConductorMicro(userSetting.getNeutralConductorMicro());
			userSetting2.setNeutralConductorString(userSetting.getNeutralConductorString());
			userSetting2.setUserId(user2);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return userSetting2;

	}

	public List<String> getAllFiles(Long idPermit) {
		try {
			List<String> allfiles = new ArrayList<>();

			if (!getAdditionalInfoFiles(idPermit).isEmpty())
				allfiles.addAll(getAdditionalInfoFiles(idPermit).subList(0, getAdditionalInfoFiles(idPermit).size()));
			if (!getProjectById.getNoteSketchFilesOne(idPermit).isEmpty()) {

				allfiles.addAll(getProjectById.getNoteSketchFilesOne(idPermit).subList(0,
						getProjectById.getNoteSketchFilesOne(idPermit).size()));
			}

			if (!getProjectById.getNoteSketchFilesTwo(idPermit).isEmpty()) {
				allfiles.addAll(getProjectById.getNoteSketchFilesTwo(idPermit).subList(0,
						getProjectById.getNoteSketchFilesTwo(idPermit).size()));
			}

			if (!getPlansetImages(idPermit).isEmpty())
				allfiles.addAll(getPlansetImages(idPermit).subList(0, getPlansetImages(idPermit).size()));
			for (Map.Entry<String, List<String>> entry : getDrafterDatafiles(idPermit).entrySet()) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					allfiles.add(entry.getValue().get(i));
				}

			}
			
			allfiles.addAll(getPermitFiles(idPermit));
			
			PermitEnergyBatterySystem permitEnergyBatterySystem = energyBatterySystemRepo.findByProjectId(idPermit);
			if(permitEnergyBatterySystem.getEssSpecificationDetails() != null && !permitEnergyBatterySystem.getEssSpecificationDetails().isEmpty()) {
				for (String f : permitEnergyBatterySystem.getEssSpecificationDetails()) {
					allfiles.add("ESS-"+f);
				}
			}
			
			//A.B CR-806 Custom files
			List<ProjectCustomFiles> cus = customFilesRepo.findByProjectId(idPermit);
			for (ProjectCustomFiles l : cus) {
				for (String s : l.getFiles()) {
					allfiles.add("Custom-"+l.getCustomUpload().getId()+'-'+s);
				}
			}
		
			return allfiles;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String sendGetRequest(String url) {

		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				return "success";
			} else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	public List<ProjectBatteryDto> removeDuplicateBattery(List<ProjectBatteryDto> list) {
		List<ProjectBatteryDto> rslt = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Boolean exist = false;
			for (int j = 0; j < rslt.size(); j++) {
				if (list.get(i) != null && rslt.get(j) != null && rslt.get(j).getBattery() != null &&  list.get(i).getBattery() != null && rslt.get(j).getBattery().equals(list.get(i).getBattery())) {
					exist = true;
					rslt.get(j).setBatteryQuantity(rslt.get(j).getBatteryQuantity()
							+ (list.get(i).getBatteryQuantity() != null ? list.get(i).getBatteryQuantity() : 0));
				}
			}
			if (Boolean.FALSE.equals(exist)) {
				rslt.add(new ProjectBatteryDto(list.get(i).getBattery(), list.get(i).getBatteryQuantity()));
			}
		}
		return rslt;
	}
	
	// F.B 05-11 CR-388 Remove Layout & Site Plan Files 
	
	public String removeLayoutFile(Long idPermit, String fileName) {

		try {
			
			// Delete from NoteSketechFilesEntity && local storage
			
			String filePath = noteSketchFilesRepo.getFilePathByPermitEntityIdAndFileName(idPermit, fileName);
			File fileToRemove = new File(filePath+ "/"+fileName);		
			if(fileToRemove.exists()) {;
				fileToRemove.delete();
			}
			NoteSketchFiles fileEntityToRemove = noteSketchFilesRepo.findByPermitEntityIdAndFileName(idPermit, fileName);
			if(fileEntityToRemove != null) {
			noteSketchFilesRepo.delete(fileEntityToRemove);				
			}
			
			// Delete File from Google drive
			
			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));
			String googleDrivePath = pathRepo.findGoogleDriveFilePath();
			String ownerFolderName = googleDriveFolder.getfolderName(permit.getAuthentificationEntity());
			String folderName = googleDriveFolder.getProjectName(permit);
			
			File fileDriveToRemove = new File(googleDrivePath + ownerFolderName + "/" + folderName
					+ "/Drafting/To Drafter/layout & site Plan-" + fileName.substring(6));
			if (fileDriveToRemove.exists()) {
				fileDriveToRemove.delete();
			}
			
			
			return "Success";

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";

		}

	}
	
	public String redefinitionCol(String sortField, String sortOrder) {
		String col = ""; 
		try {
			String ascDesc = sortOrder.equals("1") ? "ASC" : "DESC";
			if(sortField.equals("projectNameComb"))
				col = "projectNameComb "+ascDesc;
			 else if(sortField.equals("avancement"))
				 col = "advancement "+ascDesc;
			 else if(sortField.equals("status"))
				 col = "status "+ascDesc;
			 else if(sortField.equals("dateOfSubmitPermit"))
				 col = "dateOfSubmitPermit "+ascDesc;
			 else if(sortField.equals("updatedDate"))
				 col = "updateDate "+ascDesc;
			 else if(sortField.equals("updatedBy"))
				 col = "updatedBy "+ascDesc;
			 else if(sortField.equals("creationPermitDate"))
				 col = "creationPermitDate "+ascDesc;
			 else if(sortField.equals("companyComb")) {
				 col = "companyComb "+ascDesc;
			 }
			
			return col;
			
		} catch (Exception e) {
			e.printStackTrace();
			return col;
		}
	}
}
