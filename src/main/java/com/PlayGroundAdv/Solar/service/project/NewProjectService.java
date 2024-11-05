package com.PlayGroundAdv.Solar.service.project;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ConduitConductorCircuitEntity;
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
import com.PlayGroundAdv.Solar.entity.PermitPlansetUploadEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.PermitWeatherEntity;
import com.PlayGroundAdv.Solar.entity.projects.ProjectFiles;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.repositories.ConduitConductorCircuitRepository;
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
import com.PlayGroundAdv.Solar.repositories.project.ProjectFilesRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PermitPlansetUploadRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.user_management.GoogleDriveFolder;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class NewProjectService {

	final CheckValueTypesService checkValue;
	final AuthenticationRepository userRepo;
	final PermitProjectSiteInfoRepository permitSiteInfoRepo;
	final PermitHomeSiteInfoRepository permitHomeSiteInfoRepo;
	final PermitArraysRepository projectArraysRepo;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	final PermitAdvInputsRepository permitAdvInputsRepo;
	final PermitRepository permitRepo;
	final PermitSketchRepository permitSketchRepo;
	final PermitPlansetUploadRepository permitPlansetUploadRepo;
	final PermitCompanyInfoRepository permitCompanyInfoRepo;
	final PermitConduitConductorSectionRepository permitConduitConductorRepo;
	final ConduitConductorCircuitRepository conduitConductorCircuitRepo;
	final PermitAdditionalInfoRepository permitAdditionalInfoRepo;
	final PermitLayoutRepository permitLayoutRepo;
	final PermitWeatherRepository permitWeatherRepo;
	final PermitEngineerRepository permitEngineerRepo;
	final PathRepository pathRepo;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	final ProjectFilesRepository projectFileRepo;
	final GoogleDriveFolder googleDriveFolder;

	public NewProjectService(CheckValueTypesService checkValue, AuthenticationRepository userRepo,
			PermitProjectSiteInfoRepository permitSiteInfoRepo,
			PermitHomeSiteInfoRepository permitHomeSiteInfoRepo, PermitArraysRepository projectArraysRepo,
			PermitDrafterDataRepository permitDrafterDataRepo, PermitAdvInputsRepository permitAdvInputsRepo,
			PermitRepository permitRepo, PermitSketchRepository permitSketchRepo,
			PermitPlansetUploadRepository permitPlansetUploadRepo, PermitCompanyInfoRepository permitCompanyInfoRepo,
			PermitConduitConductorSectionRepository permitConduitConductorRepo,
			ConduitConductorCircuitRepository conduitConductorCircuitRepo,
			PermitAdditionalInfoRepository permitAdditionalInfoRepo, PermitLayoutRepository permitLayoutRepo,
			PermitWeatherRepository permitWeatherRepo, PermitEngineerRepository permitEngineerRepo,
			PathRepository pathRepo, PermitEnergyBatterySystemRepository energyBatterySystemRepo,
			ProjectFilesRepository projectFileRepo, GoogleDriveFolder googleDriveFolder) {
		super();
		this.checkValue = checkValue;
		this.userRepo = userRepo;
		this.permitSiteInfoRepo = permitSiteInfoRepo;
		this.permitHomeSiteInfoRepo = permitHomeSiteInfoRepo;
		this.projectArraysRepo = projectArraysRepo;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
		this.permitAdvInputsRepo = permitAdvInputsRepo;
		this.permitRepo = permitRepo;
		this.permitSketchRepo = permitSketchRepo;
		this.permitPlansetUploadRepo = permitPlansetUploadRepo;
		this.permitCompanyInfoRepo = permitCompanyInfoRepo;
		this.permitConduitConductorRepo = permitConduitConductorRepo;
		this.conduitConductorCircuitRepo = conduitConductorCircuitRepo;
		this.permitAdditionalInfoRepo = permitAdditionalInfoRepo;
		this.permitLayoutRepo = permitLayoutRepo;
		this.permitWeatherRepo = permitWeatherRepo;
		this.permitEngineerRepo = permitEngineerRepo;
		this.pathRepo = pathRepo;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
		this.projectFileRepo = projectFileRepo;
		this.googleDriveFolder = googleDriveFolder;
	}

	public String addPermit(String name, Long idUser, Boolean isTemplate, String homeOwnLastName, String projectName) {

		try {
			name = checkValue.trim(name);
			homeOwnLastName = checkValue.trim(homeOwnLastName);
			projectName = checkValue.trim(projectName);

			String name2 = name;
			String homeOwnLastName2 = homeOwnLastName;
			String projectName2 = projectName;

			if (name != null) {
				name2 = name.toLowerCase().trim();
			}
			if (homeOwnLastName != null) {
				homeOwnLastName2 = homeOwnLastName.toLowerCase().trim();
			}
			if (projectName != null) {
				projectName2 = projectName.toLowerCase().trim();
			}

			if (checkValue.NotEquals(isTemplate, true)) {
				if (checkValue.NotEquals(projectName2, "")) {
					Boolean isProjectNameExist = permitRepo.isProjectNameExist(projectName2);
					if (checkValue.Equals(isProjectNameExist, true))
						return "exist";

				} else {
					Boolean isHomeownerNameExist = permitRepo.isHomeownerNameExist(name2, homeOwnLastName2);
					if (checkValue.Equals(isHomeownerNameExist, true))
						return "exist";
				}
			}

			AuthentificationEntity user = userRepo.findById(idUser)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			PermitEntity permit = new PermitEntity();
			if (checkValue.Equals(isTemplate, true)) {
				permit.setTemplateName(name);
			} else {
				permit.setHomeOwnName(name);
				permit.setHomeOwnLastName(homeOwnLastName);
				permit.setProjectName(projectName);
			}
			permit.setAdvancement("0");
			permit.setAuthentificationEntity(user);

			TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
			Calendar creationDate = Calendar.getInstance();

			permit.setCreationPermitDate(creationDate.getTime());
			permit.setUpdateDate(creationDate.getTime());
			permit.setStatus("Draft");
			permit.setSubmitted(false);
			permit.setIsTemplate(isTemplate);
			permit.setPayed(false);
			permit.setRRVersion(0);

			PermitAdditionalInfoEntity additionalInfo = new PermitAdditionalInfoEntity();
			PermitCompanyInfoEntity companyInfoEntity = new PermitCompanyInfoEntity();
			PermitHomeSiteInfoEntity homeSiteInfoEntity = new PermitHomeSiteInfoEntity();
			PermitProjectSiteInfoEntity projectSiteInfoEntity = new PermitProjectSiteInfoEntity();
			PermitWeatherEntity weatherEntity = new PermitWeatherEntity();
			PermitArraysEntity arraysEntity = new PermitArraysEntity();
			PermitAdvEntity advEntity = new PermitAdvEntity();
			PermitEngineerEntity engineer = new PermitEngineerEntity();
			PermitPlansetUploadEntity permitPlansetUploadEntity = new PermitPlansetUploadEntity();
			PermitConduitConductorSectionEntity permitConduitConductorSectionEntity = new PermitConduitConductorSectionEntity();
			PermitDrafterDataEntity permitDrafterDataEntity = new PermitDrafterDataEntity();
			PermitLayoutEntity permitLayoutEntity = new PermitLayoutEntity();
			ConduitConductorCircuitEntity permitConductorCircuit = new ConduitConductorCircuitEntity();
			permitConduitConductorSectionEntity.setPermitEntity(permit);
			permitDrafterDataEntity.setPermitEntity(permit);
			permitConductorCircuit.setPermitEntity(permit);

			additionalInfo.setPermitEntity(permit);

			companyInfoEntity.setPermitEntity(permit);
			companyInfoEntity.setAuthorizatingAdvanced(true);
			companyInfoEntity.setMeterDisco(true);
			companyInfoEntity.setNewSubdivition(true);
			companyInfoEntity.setSystemMeetDIH(true);

			homeSiteInfoEntity.setPermitEntity(permit);

			projectSiteInfoEntity.setRafterTrussSpacing("24");
			// Set Main Breaker is Located at End of Busbar to checked by default
			projectSiteInfoEntity.setMainBreakerLocatedEndBusBar(true);

			homeSiteInfoEntity.setBuildingRisk("Risk Category II");
			homeSiteInfoEntity.setBuildingOccupancy("R-3");
			homeSiteInfoEntity.setIfServiceVoltage(true);
			homeSiteInfoEntity.setResidenceBindingCategory("Exposure C");
			homeSiteInfoEntity.setSameMailing(true);

			additionalInfo.setFormatSize("11x17 (Min. Size Recommended)");
			additionalInfo.setGridTiedOrStandalone("Grid-Tied");
			companyInfoEntity.setTypeCustomer("Resedential");
			companyInfoEntity.setSnemApplication("yes");
			companyInfoEntity.setThisIsNewService("no");
			companyInfoEntity.setProjectWasPace("no");
			companyInfoEntity.setElectriccvehichule1("*0");
			companyInfoEntity.setAnExistingSolarOrWind("no");
			companyInfoEntity.setTheAcDisconnectIsMoreThan("no");
			companyInfoEntity.setIAuthorizeAdvanced("yes");
			advEntity.setWindSpeed("110");
			advEntity.setSnowLoad("0");
			advEntity.setGoogleZoom(18);

			/***************** end *********************/
			projectSiteInfoEntity.setPermitEntity(permit);

			weatherEntity.setPermitEntity(permit);
			arraysEntity.setPermitEntity(permit);
			arraysEntity.setRequestQuote(true);
			arraysEntity.setRoofMounted(true);

			permitLayoutEntity.setPermitEntity(permit);

			advEntity.setPermitEntity(permit);
			engineer.setPermitEntity(permit);

			permitPlansetUploadEntity.setPermitEntity(permit);

			/***************** initisalisation de 6 sketch *********************/

			PermitSketchEntity permitSketchEntity = new PermitSketchEntity();
			permitSketchEntity.setArraySketch(1);
			permitSketchEntity.setModuleTils(false);
			permitSketchEntity.setPermitEntity(permit);

			/***************** initisalisation de ESS System *********************/
			PermitEnergyBatterySystem energyBatterySystem = new PermitEnergyBatterySystem();
			energyBatterySystem.setProject(permit);

			/***************** initisalisation Project Files *********************/
			ProjectFiles files = new ProjectFiles();
			files.setProject(permit);
			permitRepo.save(permit);

			energyBatterySystemRepo.save(energyBatterySystem);
			permitSketchRepo.save(permitSketchEntity);
			projectFileRepo.save(files);
			permitAdditionalInfoRepo.save(additionalInfo);
			permitCompanyInfoRepo.save(companyInfoEntity);
			permitHomeSiteInfoRepo.save(homeSiteInfoEntity);
			permitSiteInfoRepo.save(projectSiteInfoEntity);
			permitWeatherRepo.save(weatherEntity);
			projectArraysRepo.save(arraysEntity);
			permitAdvInputsRepo.save(advEntity);
			permitEngineerRepo.save(engineer);
			permitPlansetUploadRepo.save(permitPlansetUploadEntity);
			permitConduitConductorRepo.save(permitConduitConductorSectionEntity);
			permitDrafterDataRepo.save(permitDrafterDataEntity);
			permitLayoutRepo.save(permitLayoutEntity);
			conduitConductorCircuitRepo.save(permitConductorCircuit);
//			String dir = pathRepo.findFilePath();
//			new File(dir + permit.getId() + "/additionalInfo").mkdirs();
//			new File(dir + permit.getId() + "/sketch").mkdirs();
//			new File(dir + permit.getId() + "/drafterfiles").mkdirs();
//			new File(dir + permit.getId() + "/permitFiles").mkdirs();
//			new File(dir + permit.getId() + "/planset").mkdirs();

			permitRepo.save(permit);

			// A.B 08-23: CR-2847 - Save Portal Docs under Google Drive File Stream
//			createGoogleDriveFolder(permit);

			return permit.getId() + "";

		} catch (Exception e) {
			e.printStackTrace();
			return "It seems that there is a technical problem, please try again.";
		}
	}
	
	private void createGoogleDriveFolder(PermitEntity permit) {
		try {
			if (checkValue.Equals(permit.getAuthentificationEntity().getEmail(), "pm@nuagetechnologies-tn.com")
					|| !checkValue.contains(permit.getAuthentificationEntity().getEmail(), "nuagetechnologies-tn")) {
				String folderName = permit.getProjectName() != null ? permit.getProjectName().trim() : "";
				if (!checkValue.isStringNotEmpty(permit.getProjectName())
						&& checkValue.isStringNotEmpty(permit.getHomeOwnLastName())
						&& checkValue.isStringNotEmpty(permit.getHomeOwnName()))
					folderName = permit.getHomeOwnLastName().trim() + ", " + permit.getHomeOwnName().trim();

				// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
				// name
				String ownerFolderName = googleDriveFolder.getfolderName(permit.getAuthentificationEntity());

				String googleFileStreamPath = pathRepo.findGoogleDriveFilePath() + ownerFolderName + "/" + folderName;
				new File(googleFileStreamPath).mkdirs();
				new File(googleFileStreamPath + "/Contractor communication").mkdirs();
				new File(googleFileStreamPath + "/ALL AHJ Doc's").mkdirs();
				new File(googleFileStreamPath + "/Delivered to Client").mkdirs();
				new File(googleFileStreamPath + "/Drafting").mkdirs();
				new File(googleFileStreamPath + "/Drafting/Final Drafting Pkg").mkdirs();
				new File(googleFileStreamPath + "/Drafting/To Drafter").mkdirs();
				new File(googleFileStreamPath + "/Drafting/Misc Drafting").mkdirs();
				new File(googleFileStreamPath + "/Engineering").mkdirs();
				new File(googleFileStreamPath + "/Plan Set Drafts").mkdirs();
				new File(googleFileStreamPath + "/Portal Downloads").mkdirs();
				new File(googleFileStreamPath + "/Team Project Folder").mkdirs();
				new File(googleFileStreamPath + "/Interconnection").mkdirs();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
