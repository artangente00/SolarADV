package com.PlayGroundAdv.Solar.service.project;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.ProjectsTrackerEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.repositories.PermitAdvInputsRepository;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.PlayGroundAdv.Solar.repositories.PermitHomeSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectsTrackerRepository;
import com.PlayGroundAdv.Solar.repositories.SelectDrafterSheetRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.monday_api.ExportProjectToMonday;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class SubmitProjectService {

	final MailingService mailingService;
	final NotificationEntityService notificationService;
	final CheckValueTypesService checkValue;
	final InverterRepository inverterRepo;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	final SelectDrafterSheetRepository selectDrafterSheetRepo;
	final PermitHomeSiteInfoRepository permitHomeSiteInfoRepo;
	final PermitAdvInputsRepository permitAdvInputsRepo;
	final PermitRepository permitRepo;
	final AuthenticationRepository userRepo;
	final ExportProjectToMonday exportProjectToMonday;
	final GetProjectDetailsUtils projectDetailsUtils;
	final ProjectsTrackerRepository projectsTrackerRepo;
	
	public SubmitProjectService(MailingService mailingService, NotificationEntityService notificationService,
			CheckValueTypesService checkValue, InverterRepository inverterRepo,
			PermitDrafterDataRepository permitDrafterDataRepo, SelectDrafterSheetRepository selectDrafterSheetRepo,
			PermitHomeSiteInfoRepository permitHomeSiteInfoRepo, PermitAdvInputsRepository permitAdvInputsRepo,
			PermitRepository permitRepo, AuthenticationRepository userRepo, ExportProjectToMonday exportProjectToMonday,
			GetProjectDetailsUtils projectDetailsUtils, ProjectsTrackerRepository projectsTrackerRepo) {
		super();
		this.mailingService = mailingService;
		this.notificationService = notificationService;
		this.checkValue = checkValue;
		this.inverterRepo = inverterRepo;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
		this.selectDrafterSheetRepo = selectDrafterSheetRepo;
		this.permitHomeSiteInfoRepo = permitHomeSiteInfoRepo;
		this.permitAdvInputsRepo = permitAdvInputsRepo;
		this.permitRepo = permitRepo;
		this.userRepo = userRepo;
		this.exportProjectToMonday = exportProjectToMonday;
		this.projectDetailsUtils = projectDetailsUtils;
		this.projectsTrackerRepo = projectsTrackerRepo;
	}

	String msgErrorInfoNuatn = "";
	String msgErrorInfoClient = "";
	

	// CR-2424
	public String failedPermitEntity(Long idPermit, Long idUser) {
		try {
			Date dateNow = new Date();
			Calendar cal = Calendar.getInstance(); // creates calendar
			cal.setTime(new Date()); // sets calendar time/date
			cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
			PermitEntity permit = permitRepo.findById(idPermit)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			if (msgErrorInfoNuatn.equals("") && msgErrorInfoClient.equals("")) {
				permit.setAdvancement(permit.getAdvancement());
			} else {
				permit.setAdvancement("100");
			}

			permit.setSubmitted(false);
			permit.setStatus("Under Evaluation");
			permit.setIsCanceled(false);
			permit.setOpened(false);
			permit.setOpenedBy(null);
			permitRepo.save(permit);

			String projectname = permit.getProjectName();
			if (permit.getProjectName() == null || checkValue.Equals(permit.getProjectName(), "")) {
				projectname = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
			}
			if (idUser != null && checkValue.NotEquals(msgErrorInfoClient, "")) {
				AuthentificationEntity user = userRepo.findById(idUser)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
				if (user != null) {
					String textNuatn = "Hello,<br><br>" + user.getFirstName() + " " + user.getLastName()
							+ " has a technical problem when submitting the project " + projectname
							+ ".<ul><li>Project created by : " + permit.getAuthentificationEntity().getFirstName() + " "
							+ permit.getAuthentificationEntity().getLastName() + ".</li><li>Submit date : " + dateNow
							+ "</li>" + msgErrorInfoNuatn
							+ "</ul><br>Please check this Error as soon as possible.<br><br>Thank you.";
					// Contain the error that make a technical problem
					if (checkValue.NotEquals(msgErrorInfoClient, "") && user.getEmail() != null) {
						String text = "Hello,<br><br>" + user.getFirstName() + " " + user.getLastName()
								+ " has a technical problem when submitting the project " + projectname
								+ ".<ul><li>Project created by : " + permit.getAuthentificationEntity().getFirstName()
								+ " " + permit.getAuthentificationEntity().getLastName() + ".</li><li>Submit date : "
								+ dateNow + "</li>" + msgErrorInfoClient
								+ "</ul><br>Please check this Error as soon as possible.<br><br>Thank you.";

						mailingService.sendingMailPMSubmitEvaluation(user.getEmail(),
								"Project Submission Technical Problem - " + projectname, text, projectname,
								user.getFirstName() + " " + user.getLastName());
					}

					// Contain the error that make a technical problem and all exception when the
					// user submit the project
					if (checkValue.NotEquals(msgErrorInfoNuatn, "")) {

						if (user.getEmail() != null && user.getEmail().contains("nuagetechnologies-tn.com")) {
							mailingService.sendingMailPMSubmitEvaluation("dev0@nuagetechnologies-tn.com",
									"Project Submission Technical Problem - " + projectname, textNuatn, projectname,
									user.getFirstName() + " " + user.getLastName());
						} else {
							mailingService.sendingMailPMSubmitEvaluation("support@nuagetechnologies-tn.com",
									"Project Submission Technical Problem - " + projectname, textNuatn, projectname,
									user.getFirstName() + " " + user.getLastName());

						}
					}
					msgErrorInfoNuatn = "";
					msgErrorInfoClient = "";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		return "done";
	}

	public String submitPermitEntity(Long idUser, Long idPermit) {
		try {

			Date dateNow = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.HOUR_OF_DAY, 1);
			PermitEntity permit = permitRepo.findById(idPermit)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			AuthentificationEntity user = userRepo.findById(idUser)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));

			// A.B CR-283 check if it is a test project
			// ADV Test Project Start with "Test"
			Boolean testProject = projectDetailsUtils.getProjectName(permit).toUpperCase().startsWith("TEST");
			Boolean expotToMonday = !Boolean.TRUE.equals(permit.isSubmitted()) && Boolean.FALSE.equals(testProject)
					&& !permit.getAuthentificationEntity().getEmail().contains("nuagetechnologies");

			permit.setSubmitted(true);
			permit.setStatus("Submitted");
			permit.setAdvancement("100");
			permit.setDateOfSubmitPermit(dateNow);
			permit.setDateOfSubmitPermitCanceled(cal.getTime());
			permit.setIsCanceled(false);
			permit.setOpened(false);
			permit.setOpenedBy(null);
			permitRepo.save(permit);
			// Contain all exception, when the user submit the project

			TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
			Calendar calSub = Calendar.getInstance();
			calSub.setTime(new Date());
			try {
				ProjectsTrackerEntity tracker = projectsTrackerRepo.findByPermitId(permit.getId());
				if (tracker != null) {
					tracker.setSubmitted(calSub.getTime());
					tracker.setSubmittedBy(user.getFirstName() + " " + user.getLastName());
					projectsTrackerRepo.save(tracker);
				} else {
					ProjectsTrackerEntity tracker2 = new ProjectsTrackerEntity();
					tracker2.setPermit(permit);
					tracker2.setSubmitted(calSub.getTime());
					tracker2.setSubmittedBy(user.getFirstName() + " " + user.getLastName());
					projectsTrackerRepo.save(tracker2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			String projectname = permit.getProjectName();
			if (permit.getProjectName() == null || checkValue.Equals(permit.getProjectName(), "")) {
				projectname = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
			}

			try {
				notificationService.addNewNotif(idUser, 0L, "Project Submission", "Projects",
						"Project Submission - " + projectname, user.getFirstName() + " " + user.getLastName()
								+ " submitted " + projectname + " in project category",
						true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (user != null && checkValue.NotEquals(msgErrorInfoNuatn, "")) {
				String text = "Hello,<br><br>" + user.getFirstName() + " " + user.getLastName()
						+ " has a technical problem when submitting the project " + projectname
						+ ".<ul><li>Project created by : " + permit.getAuthentificationEntity().getFirstName() + " "
						+ permit.getAuthentificationEntity().getLastName() + ".</li><li>Submit date : " + dateNow
						+ "</li>" + msgErrorInfoNuatn
						+ "</ul><br>Please check this Error as soon as possible.<br><br>Thank you.";

				if (user.getEmail() != null && user.getEmail().contains("nuagetechnologies-tn.com")) {
					mailingService.sendingMailPMSubmitEvaluation("dev0@nuagetechnologies-tn.com",
							"Project Submission Technical Problem - " + projectname, text, projectname,
							user.getFirstName() + " " + user.getLastName());
				} else {
					mailingService.sendingMailPMSubmitEvaluation("support@nuagetechnologies-tn.com",
							"Project Submission Technical Problem - " + projectname, text, projectname,
							user.getFirstName() + " " + user.getLastName());
				}
				msgErrorInfoNuatn = "";
				msgErrorInfoClient = "";
			}

			// A.B CR-283 Add Project to Monday.com
			if (Boolean.TRUE.equals(expotToMonday)) {
				Runnable mondayExport = new Runnable() {
					public void run() {
						try {
							exportProjectToMonday.sendProjectToMonday(permit);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				};
				new Thread(mondayExport).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
			sendingMailException(e, idUser, idPermit);
			return "Fail";

		}

		return "Done";
	}

	// CR-2424 Send mail to support if technical problem is happened when submit a
	// permit
	public void technicalProblemMail(String errorInfo, String type) {
		try {

			if (!checkValue.Equals(type, "NUATN")) {
				msgErrorInfoClient = errorInfo;
			}
			msgErrorInfoNuatn = errorInfo + "\n" + msgErrorInfoNuatn;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// CR-2424
	public String sendingMailException(Exception e, Long idUserConnected, Long idPermit) {
		Boolean contain = false;
		int i = 0;
		while (e != null && Boolean.FALSE.equals(contain) && i < e.getStackTrace().length) {
			if (e.getStackTrace()[i].toString().contains("com.PlayGroundAdv.Solar")) {
				contain = true;

			} else {
				i++;
			}
		}
		try {

			if (Boolean.TRUE.equals(contain)) {
				String exceptionInfo = "<li>Error cause : " + e + ".</li><li>Error location : In file "
						+ e.getStackTrace()[i].getFileName() + " within the method : "
						+ e.getStackTrace()[i].getMethodName() + " at line : " + e.getStackTrace()[i].getLineNumber()
						+ ".</li>";
				AuthentificationEntity user1 = userRepo.findById(idUserConnected)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
				PermitEntity permit = permitRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
				String projectname = permit.getProjectName();
				if (permit.getProjectName() == null || checkValue.Equals(permit.getProjectName(), "")) {
					projectname = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
				}
				mailingService.sendingMailPMSubmitEvaluation(user1.getEmail(),
						"Project Submission Technical Problem - " + projectname, exceptionInfo, projectname,
						user1.getFirstName() + " " + user1.getLastName());

			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return "Done";
	}

}
