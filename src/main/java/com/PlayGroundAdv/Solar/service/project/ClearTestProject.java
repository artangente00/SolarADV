package com.PlayGroundAdv.Solar.service.project;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.SiteSurveyEntity;
import com.PlayGroundAdv.Solar.repositories.AdditionalInfoFilesRepository;
import com.PlayGroundAdv.Solar.repositories.BOSFilesRepository;
import com.PlayGroundAdv.Solar.repositories.ConduitConductorCircuitRepository;
import com.PlayGroundAdv.Solar.repositories.DrafterInformationRepository;
import com.PlayGroundAdv.Solar.repositories.InterconnectionsRepository;
import com.PlayGroundAdv.Solar.repositories.MissingSheetsLogRepository;
import com.PlayGroundAdv.Solar.repositories.NotAllowedRackingNotesRepository;
import com.PlayGroundAdv.Solar.repositories.NoteSketchFilesRepository;
import com.PlayGroundAdv.Solar.repositories.NotificationRepository;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdditionalInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdvInputsRepository;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitCompanyInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitConduitConductorSectionRepository;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEngineerRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEvaluationRepository;
import com.PlayGroundAdv.Solar.repositories.PermitGoogleDriveLinksRepository;
import com.PlayGroundAdv.Solar.repositories.PermitHomeSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitLayoutRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.PermitSketchRepository;
import com.PlayGroundAdv.Solar.repositories.PermitToolsRepository;
import com.PlayGroundAdv.Solar.repositories.PermitTotalSectionRepository;
import com.PlayGroundAdv.Solar.repositories.PermitWeatherRepository;
import com.PlayGroundAdv.Solar.repositories.PlansetUsedSheetsLogRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectNotesRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectRequestRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectsTrackerRepository;
import com.PlayGroundAdv.Solar.repositories.RFIConfirmationRepository;
import com.PlayGroundAdv.Solar.repositories.RFInformationRepository;
import com.PlayGroundAdv.Solar.repositories.SelectDrafterSheetRepository;
import com.PlayGroundAdv.Solar.repositories.SiteSurveyRepository;
import com.PlayGroundAdv.Solar.repositories.UtilityBillFilesRepository;
import com.PlayGroundAdv.Solar.repositories.project.AccountingsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ProjectCustomFilesRepository;
import com.PlayGroundAdv.Solar.repositories.project.ProjectFilesRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PermitCustomizedSheetsRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PermitPlansetUploadRepository;
import com.PlayGroundAdv.Solar.repositories.site_survey.SiteSurveyTextAreaFieldsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ClearTestProject {

	final PermitRepository permitRepo;
	final AuthenticationRepository userRepo;
	final AccountingsRepository accountingsRepo;
	final AdditionalInfoFilesRepository additionalInfoFilesRepo;
	final BOSFilesRepository bOSFilesRepo;
	final ConduitConductorCircuitRepository conduitConductorCircuitRepo;
	final DrafterInformationRepository drafterInformationRepo;
	final InterconnectionsRepository interconnectionsRepo;
	final MissingSheetsLogRepository missingSheetsLogRepo;
	final NotAllowedRackingNotesRepository notAllowedRackingNotesRepo;
	final NoteSketchFilesRepository noteSketchFilesRepo;
	final PermitAdditionalInfoRepository permitAdditionalInfoRepo;
	final PermitAdvInputsRepository permitAdvRepo;
	final PermitArraysRepository permitArraysRepo;
	final PermitCompanyInfoRepository permitCompanyInfoRepo;
	final PermitConduitConductorSectionRepository permitConduitConductorSectionRepo;
	final PermitCustomizedSheetsRepository permitCustomizedSheetsRepo;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	final PermitEnergyBatterySystemRepository permitEnergyBatterySystemRepo;
	final PermitEngineerRepository permitEngineerRepo;
	final PermitEvaluationRepository permitEvaluationRepo;
	final PermitGoogleDriveLinksRepository permitGoodleDriveLinksRepo;
	final PermitHomeSiteInfoRepository permitHomeSiteInfoRepo;
	final PermitLayoutRepository permitLayoutRepo;
	final PermitPlansetUploadRepository permitPlansetUploadRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoRepo;
	final PermitSketchRepository permitSketchRepo;
	final PermitToolsRepository permitToolsRepo;
	final PermitTotalSectionRepository permitTotalSectionRepo;
	final PermitWeatherRepository permitWeatherRepo;
	final PlansetUsedSheetsLogRepository plansetUsedSheetsLogRepo;
	final ProjectNotesRepository projectNotesRepo;
	final ProjectRequestRepository projectRequestRepo;
	final ProjectsTrackerRepository projectsTrackerRepo;
	final RFIConfirmationRepository rFIConfirmationRepo;
	final RFInformationRepository rFInformationRepo;
	final SelectDrafterSheetRepository selectDrafterSheetRepo;
	final SiteSurveyRepository siteSurveyRepo;
	final UtilityBillFilesRepository utilityBillFilesRepo;
	final PathRepository pathRepo;
	final NotificationRepository notificationRepo;
	final CheckValueTypesService checkValue;
	final ProjectFilesRepository projectFilesRepo;
	final ProjectCustomFilesRepository projectCustomFilesRepo;
	final ESSConfiguration essConfiguration;
	public ClearTestProject(PermitRepository permitRepo, AuthenticationRepository userRepo,
			AccountingsRepository accountingsRepo, AdditionalInfoFilesRepository additionalInfoFilesRepo,
			BOSFilesRepository bOSFilesRepo, ConduitConductorCircuitRepository conduitConductorCircuitRepo,
			DrafterInformationRepository drafterInformationRepo, InterconnectionsRepository interconnectionsRepo,
			MissingSheetsLogRepository missingSheetsLogRepo,
			NotAllowedRackingNotesRepository notAllowedRackingNotesRepo, NoteSketchFilesRepository noteSketchFilesRepo,
			PermitAdditionalInfoRepository permitAdditionalInfoRepo, PermitAdvInputsRepository permitAdvRepo,
			PermitArraysRepository permitArraysRepo, PermitCompanyInfoRepository permitCompanyInfoRepo,
			PermitConduitConductorSectionRepository permitConduitConductorSectionRepo,
			PermitCustomizedSheetsRepository permitCustomizedSheetsRepo,
			PermitDrafterDataRepository permitDrafterDataRepo,
			PermitEnergyBatterySystemRepository permitEnergyBatterySystemRepo,
			PermitEngineerRepository permitEngineerRepo, PermitEvaluationRepository permitEvaluationRepo,
			PermitGoogleDriveLinksRepository permitGoodleDriveLinksRepo,
			PermitHomeSiteInfoRepository permitHomeSiteInfoRepo, PermitLayoutRepository permitLayoutRepo,
			PermitPlansetUploadRepository permitPlansetUploadRepo,
			PermitProjectSiteInfoRepository permitProjectSiteInfoRepo, PermitSketchRepository permitSketchRepo,
			PermitToolsRepository permitToolsRepo, PermitTotalSectionRepository permitTotalSectionRepo,
			PermitWeatherRepository permitWeatherRepo, PlansetUsedSheetsLogRepository plansetUsedSheetsLogRepo,
			ProjectNotesRepository projectNotesRepo, ProjectRequestRepository projectRequestRepo,
			ProjectsTrackerRepository projectsTrackerRepo, RFIConfirmationRepository rFIConfirmationRepo,
			RFInformationRepository rFInformationRepo, SelectDrafterSheetRepository selectDrafterSheetRepo,
			SiteSurveyRepository siteSurveyRepo, UtilityBillFilesRepository utilityBillFilesRepo,
			SiteSurveyTextAreaFieldsRepository siteSurveyTextAreaFieldsRepo, PathRepository pathRepo,
			NotificationRepository notificationRepo, CheckValueTypesService checkValue,
			ProjectFilesRepository projectFilesRepo, ProjectCustomFilesRepository projectCustomFilesRepo,
			ESSConfiguration essConfiguration) {
		super();
		this.permitRepo = permitRepo;
		this.userRepo = userRepo;
		this.accountingsRepo = accountingsRepo;
		this.additionalInfoFilesRepo = additionalInfoFilesRepo;
		this.bOSFilesRepo = bOSFilesRepo;
		this.conduitConductorCircuitRepo = conduitConductorCircuitRepo;
		this.drafterInformationRepo = drafterInformationRepo;
		this.interconnectionsRepo = interconnectionsRepo;
		this.missingSheetsLogRepo = missingSheetsLogRepo;
		this.notAllowedRackingNotesRepo = notAllowedRackingNotesRepo;
		this.noteSketchFilesRepo = noteSketchFilesRepo;
		this.permitAdditionalInfoRepo = permitAdditionalInfoRepo;
		this.permitAdvRepo = permitAdvRepo;
		this.permitArraysRepo = permitArraysRepo;
		this.permitCompanyInfoRepo = permitCompanyInfoRepo;
		this.permitConduitConductorSectionRepo = permitConduitConductorSectionRepo;
		this.permitCustomizedSheetsRepo = permitCustomizedSheetsRepo;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
		this.permitEnergyBatterySystemRepo = permitEnergyBatterySystemRepo;
		this.permitEngineerRepo = permitEngineerRepo;
		this.permitEvaluationRepo = permitEvaluationRepo;
		this.permitGoodleDriveLinksRepo = permitGoodleDriveLinksRepo;
		this.permitHomeSiteInfoRepo = permitHomeSiteInfoRepo;
		this.permitLayoutRepo = permitLayoutRepo;
		this.permitPlansetUploadRepo = permitPlansetUploadRepo;
		this.permitProjectSiteInfoRepo = permitProjectSiteInfoRepo;
		this.permitSketchRepo = permitSketchRepo;
		this.permitToolsRepo = permitToolsRepo;
		this.permitTotalSectionRepo = permitTotalSectionRepo;
		this.permitWeatherRepo = permitWeatherRepo;
		this.plansetUsedSheetsLogRepo = plansetUsedSheetsLogRepo;
		this.projectNotesRepo = projectNotesRepo;
		this.projectRequestRepo = projectRequestRepo;
		this.projectsTrackerRepo = projectsTrackerRepo;
		this.rFIConfirmationRepo = rFIConfirmationRepo;
		this.rFInformationRepo = rFInformationRepo;
		this.selectDrafterSheetRepo = selectDrafterSheetRepo;
		this.siteSurveyRepo = siteSurveyRepo;
		this.utilityBillFilesRepo = utilityBillFilesRepo;
		this.pathRepo = pathRepo;
		this.notificationRepo = notificationRepo;
		this.projectFilesRepo = projectFilesRepo;
		this.checkValue = checkValue;
		this.projectCustomFilesRepo = projectCustomFilesRepo;
		this.essConfiguration= essConfiguration;
	}


	public String cleanProjects(String fileName) {
		try {
//			List<AuthentificationEntity> users = userRepo.findByEmailContains(email);
//			List<Long> ids = new ArrayList<>(users.stream().map(o -> o.getId()).collect(Collectors.toList()));
//			Collections.sort(ids);
//			Date xDaysAgo = Date.from(Instant.now().minus(Duration.ofDays(days)));
//			List<PermitEntity> projects = permitRepo
//					.findByAuthentificationEntityIdInAndUpdateDateBeforeAndIsTemplateFalse(ids, xDaysAgo);
			List<Long> projects = getProjectIds(fileName);
			System.out.println(projects.size() + " to be deleted");
			int i = 0;
			for (Long p : projects) {
				try {
					permitSketchRepo.deleteByPermitEntityId(p);
					permitEngineerRepo.deleteByPermitEntityId(p);
					permitCompanyInfoRepo.deleteByPermitEntityId(p);
					noteSketchFilesRepo.deleteByPermitEntityId(p);
					permitConduitConductorSectionRepo.deleteByPermitEntityId(p);
					permitArraysRepo.deleteByPermitEntityId(p);
					accountingsRepo.deleteByPermitId(p);
					additionalInfoFilesRepo.deleteByPermitEntityId(p);
					bOSFilesRepo.deleteByPermitEntityId(p);
					conduitConductorCircuitRepo.deleteByPermitEntityId(p);
					drafterInformationRepo.deleteByIdPermitId(p);
					permitWeatherRepo.deleteByPermitEntityId(p);
					permitDrafterDataRepo.deleteByPermitEntityId(p);
					permitHomeSiteInfoRepo.deleteByPermitEntityId(p);
					permitAdditionalInfoRepo.deleteByPermitEntityId(p);
					permitLayoutRepo.deleteByPermitEntityId(p);
					projectsTrackerRepo.deleteByPermitId(p);
					permitProjectSiteInfoRepo.deleteByPermitEntityId(p);
					plansetUsedSheetsLogRepo.deleteByProjectId(p);
					permitPlansetUploadRepo.deleteByPermitEntityId(p);
					permitAdvRepo.deleteByPermitEntityId(p);
					permitCustomizedSheetsRepo.deleteByProjectId(p);
					permitConduitConductorSectionRepo.deleteByPermitEntityId(p);
					permitTotalSectionRepo.deleteByPermitEntityId(p);
					permitToolsRepo.deleteByPermitEntityId(p);
					interconnectionsRepo.deleteByPermitId(p);
					permitEnergyBatterySystemRepo.deleteByProjectId(p);
					projectNotesRepo.deleteByProjectId(p);
					missingSheetsLogRepo.deleteByProjectId(p);
					notAllowedRackingNotesRepo.deleteByProjectId(p);
					permitEvaluationRepo.deleteByPermitEntityId(p);
					permitGoodleDriveLinksRepo.deleteByPermitEntityId(p);
					projectRequestRepo.deleteByPermitId(p);
					rFIConfirmationRepo.deleteByIdPermitId(p);
					rFInformationRepo.deleteByIdPermitId(p);
					selectDrafterSheetRepo.deleteByIdPermitId(p);
					utilityBillFilesRepo.deleteByPermitEntityId(p);
					notificationRepo.deleteByPermitNotifId(p);
					projectFilesRepo.deleteByProjectId(p);
					projectCustomFilesRepo.deleteByProjectId(p);
					essConfiguration.deleteByProject(p);
					SiteSurveyEntity s = siteSurveyRepo.findByportalProjectId(p);
					if (s != null) {
						s.setPortalProject(null);
						siteSurveyRepo.save(s);
					}

					if (permitRepo.existsById(p)) {
						permitRepo.deleteById(p);
					}
					i = i + 1;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			System.out.println(i + " Projects Deleted");

			return i + " Projects Deleted";

		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

	public String getfilesPath() {
		return pathRepo.findFilePath();
	}

	private List<Long> getProjectIds(String fileName) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(getfilesPath() + fileName));
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheet("Worksheet");
			List<Long> ids = new ArrayList<>();
			if (sheet != null) {
				int i = 0;
				while (sheet.getRow(i) != null && sheet.getRow(i).getCell(0) != null) {
					Double id = sheet.getRow(i).getCell(0).getNumericCellValue();
					ids.add(Double.valueOf(id).longValue());
					i++;
				}
			}
			workbook.close();
			return ids;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
