package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.project.NewProjectService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class CopyProjectService {

	final HistoryActivityService historyActivityService;
	final NewProjectService newProject;
	final CheckValueTypesService checkValue;
	final AuthenticationRepository userRepo;
	final PermitRepository projectRepo;
	final CopyHomeownerInfo copyHomeownerInfo;
	final CopyArraysInfo copyArraysInfo;
	final CopyAdditionalInfo copyAdditionalInfo;
	final CopyProjectInfo copyProjectInfo;
	final CopyCircuitInfo copyCircuitInfo;
	final CopyLayoutSketch copyLayoutSketch;
	final CopyADVInputs copyADVInputs;
	final CopyDrafterData copyDrafterData;
	final CopyUtilityCompanyInfo copyUtilityCompanyInfo;
	final CopyProjectEnergyBatterySystem copyProjectEnergyBatterySystem;
	final CopyESSConfiguration copyESSConfiguration;
	
	public CopyProjectService(HistoryActivityService historyActivityService, NewProjectService newProject,
			CheckValueTypesService checkValue, AuthenticationRepository userRepo, PermitRepository projectRepo,
			CopyHomeownerInfo copyHomeownerInfo, CopyArraysInfo copyArraysInfo, CopyAdditionalInfo copyAdditionalInfo,
			CopyProjectInfo copyProjectInfo, CopyCircuitInfo copyCircuitInfo, CopyLayoutSketch copyLayoutSketch,
			CopyADVInputs copyADVInputs, CopyDrafterData copyDrafterData,
			CopyUtilityCompanyInfo copyUtilityCompanyInfo, CopyProjectEnergyBatterySystem copyProjectEnergyBatterySystem,
			CopyESSConfiguration copyESSConfiguration) {
		super();
		this.historyActivityService = historyActivityService;
		this.newProject = newProject;
		this.checkValue = checkValue;
		this.userRepo = userRepo;
		this.projectRepo = projectRepo;
		this.copyHomeownerInfo = copyHomeownerInfo;
		this.copyArraysInfo = copyArraysInfo;
		this.copyAdditionalInfo = copyAdditionalInfo;
		this.copyProjectInfo = copyProjectInfo;
		this.copyCircuitInfo = copyCircuitInfo;
		this.copyLayoutSketch = copyLayoutSketch;
		this.copyADVInputs = copyADVInputs;
		this.copyDrafterData = copyDrafterData;
		this.copyUtilityCompanyInfo = copyUtilityCompanyInfo;
		this.copyProjectEnergyBatterySystem = copyProjectEnergyBatterySystem;
		this.copyESSConfiguration = copyESSConfiguration;
	}

	private static final String EXIST = "exist";
	private static final String ERROR = "It seems that there is a technical problem, please try again.";
	// CR Copy Permit

	public String copyPermit(Long idPermit, String name, Long idUser, String ipUser, String timeZoneUser, String numTab,
			String sessionId, String openDate, String homeLastname, String projectName) {
		try {
			String copyProjectRetrn = copyProject(idPermit, name, idUser, ipUser, timeZoneUser, homeLastname,
					projectName, false);
			if (checkValue.Equals(copyProjectRetrn, ERROR))
				return copyProjectRetrn;

			// call historical table
			historyActivityService.recordActivity(idUser, ipUser, timeZoneUser,
					"Copy All Permit;Old Permit : " + idPermit + " New Permit : " + copyProjectRetrn + ";Copy success",
					true, "", "", "");

			return copyProjectRetrn;
		} catch (Exception e) {

			e.printStackTrace();
			historyActivityService.recordActivity(idUser, ipUser, timeZoneUser, "Copy All Permit;Copy Failed", false,
					numTab, sessionId, openDate);
			return ERROR;
		}

	}

	public String addTemplatePermit(Long idPermit, String name, String ipUser, String timeZoneUser, Long idUser,
			String numTab, String sessionId, String openDate, String homeLastname, String projectName, Long idSuperUser, Boolean isTemplate) {
		try {
			String copyProjectRetrn = copyProject(idPermit, name, idUser, ipUser, timeZoneUser, homeLastname,
					projectName, isTemplate);
			if (checkValue.Equals(copyProjectRetrn, ERROR))
				return copyProjectRetrn;

			// call historical table
			if (checkValue.NotEquals(idSuperUser, idUser)) {
				// Get Super User Connected
				AuthentificationEntity superUser = userRepo.findById(idSuperUser).orElse(new AuthentificationEntity());
				historyActivityService.recordActivity(idSuperUser, ipUser, timeZoneUser,
						"Add Template;Old Permit : " + idPermit + " New Permit : " + copyProjectRetrn
								+ ";Copy success created by the super user " + superUser.getId() + ": "
								+ superUser.getFirstName() + " " + superUser.getLastName(),
						true, "", "", "");

			} else {
				historyActivityService.recordActivity(idUser, ipUser, timeZoneUser,
						"Add Template;Old Permit : " + idPermit + " New Permit : " + copyProjectRetrn + ";Copy success",
						true, "", "", "");
			}

			return "success";
		} catch (Exception e) {

			e.printStackTrace();
			historyActivityService.recordActivity(idUser, ipUser, timeZoneUser, "Copy All Permit;Copy Failed", false,
					numTab, sessionId, openDate);
			return ERROR;
		}

	}

	// AddTemplate
	public String copyProject(Long idPermit, String name, Long idUser, String ipUser, String timeZoneUser,
			String homeLastname, String projectName, Boolean isTemplate) {

		try {

			AuthentificationEntity user = userRepo.findById(idUser).orElse(new AuthentificationEntity());
			// call add new Permit
			String newId = newProject.addPermit(name, idUser, isTemplate, homeLastname,
					projectName);
			if (checkValue.Equals(newId, EXIST))
				return EXIST;
			Long idNewPermit = Long.valueOf(newId);
			// get the new Permit
			PermitEntity newPermit = projectRepo.findById(idNewPermit).orElse(new PermitEntity());
			newPermit.setAdvancement("25");
			newPermit.setStatus("Draft");
			projectRepo.save(newPermit);

			// copy PermitConduitConductorSectionEntity
			copyCircuitInfo.copyCircuitInfo(idPermit, idNewPermit);

			// Copy PermitProjectSiteInfoEntity
			copyProjectInfo.copyProjectInfo(idPermit, idNewPermit, user);

			// Copy PermitArraysEntity
			copyArraysInfo.copyArraysInfo(idPermit, idNewPermit, user);

			// Copy Additional Info
			copyAdditionalInfo.copyAdditionalInfo(idPermit, idNewPermit, user);

			// Copy Total Section & Layout Sketch Tab Entity
			copyLayoutSketch.copyLayoutSketch(idPermit, newPermit);

			// Copy PermitCompanyInfoEntity
			copyUtilityCompanyInfo.copyUtilityCompanyInfo(idPermit, idNewPermit);

			// Copy Home Site Info
			copyHomeownerInfo.copyHomeownerInfo(idPermit, idNewPermit, isTemplate);

			// Copy ADV Project Inputs & Weather & Engineer
			copyADVInputs.copyADVInputs(idPermit, idNewPermit);

			// Copy Drafter
			copyDrafterData.copyDrafterData(idPermit, idNewPermit);

			//Copy Project Energy Battery System
			copyProjectEnergyBatterySystem.copyBatterySystem(idPermit, idNewPermit, user, user);

			//Copy Project Battery ESS Configuration
			copyESSConfiguration.copyEssConfig(idPermit, newPermit);
			
			return idNewPermit.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

}