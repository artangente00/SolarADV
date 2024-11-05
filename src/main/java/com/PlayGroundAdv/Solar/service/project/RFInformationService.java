package com.PlayGroundAdv.Solar.service.project;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import javax.mail.internet.AddressException;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.ProjectsTrackerEntity;
import com.PlayGroundAdv.Solar.entity.RFIConfirmationEntity;
import com.PlayGroundAdv.Solar.entity.RFIQuestionEntity;
import com.PlayGroundAdv.Solar.entity.RFInformationEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.AddContactRfiModel;
import com.PlayGroundAdv.Solar.model.ConfContracRfiResponseModel;
import com.PlayGroundAdv.Solar.model.RFIModelRequest;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectsTrackerRepository;
import com.PlayGroundAdv.Solar.repositories.RFIConfirmRepository;
import com.PlayGroundAdv.Solar.repositories.RFIInformationRepository;
import com.PlayGroundAdv.Solar.repositories.RFIQuestionRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;


@Service
@Transactional
public class RFInformationService {


	final MailingService mailingService;
	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final UserSettingRepository userSettingRepo;
	final PathRepository pathRepo;
	final PermitRepository permitRepo;
	final AuthenticationRepository userRepo;
	final RFIInformationRepository rfiInfoRepo;
	final RFIConfirmRepository rfiConfirmRepo; 
	final ContractorInformationRepository contractorInfoRepo;
	final ProjectsTrackerRepository projectTrackerRepo;
	final RFIQuestionRepository rfiQuestionRepo;

	public RFInformationService(MailingService mailingService, HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			UserSettingRepository userSettingRepo, PathRepository pathRepo, PermitRepository permitRepo,
			AuthenticationRepository userRepo, RFIInformationRepository rfiInfoRepo,
			RFIConfirmRepository rfiConfirmRepo, ContractorInformationRepository contractorInfoRepo,
			ProjectsTrackerRepository projectTrackerRepo, RFIQuestionRepository rfiQuestionRepo) {
		super();
		this.mailingService = mailingService;
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.userSettingRepo = userSettingRepo;
		this.pathRepo = pathRepo;
		this.permitRepo = permitRepo;
		this.userRepo = userRepo;
		this.rfiInfoRepo = rfiInfoRepo;
		this.rfiConfirmRepo = rfiConfirmRepo;
		this.contractorInfoRepo = contractorInfoRepo;
		this.projectTrackerRepo = projectTrackerRepo;
		this.rfiQuestionRepo = rfiQuestionRepo;
	}

	public String getfilesPath() {
		
		String filePath = "";

		try {
			filePath = pathRepo.findFilePath();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return filePath;
	}

	public String saveAdvRfInformation(Long idPermit, Long idAdvUser, List<RFIModelRequest> rfiList) {

		try {

			// delete all pfi affected dor this permit if exist
			List<RFInformationEntity> oldRfis = rfiInfoRepo.findAllByIdPermitId(idPermit);
			if (oldRfis != null && !oldRfis.isEmpty()) {
				for (RFInformationEntity rfInformation : oldRfis) {
					rfiInfoRepo.delete(rfInformation);
				}

			}
			// get permit
			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));

			// get User Connected

			AuthentificationEntity advUser = userRepo.findById(idAdvUser).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idAdvUser));

			// submit new RFI List
			if (rfiList != null && !rfiList.isEmpty()) {

				for (RFIModelRequest rfiModelRequest : rfiList) {
					if (rfiModelRequest != null) {
						RFInformationEntity rfi = new RFInformationEntity();

						rfi.setIdAdvUserCo(advUser);
						rfi.setIdPermit(permit);
						rfi.setTableName(rfiModelRequest.getTabname());
						rfi.setAttributeName(rfiModelRequest.getAttName());
						rfi.setAttributeModel(rfiModelRequest.getAttModel());
						rfi.setAdvQuestion(rfiModelRequest.getAdvQuestion());
						rfi.setIsConfirmed(true);
						rfi.setContentField(rfiModelRequest.getContentField());
						rfi.setFieldNgModel(rfiModelRequest.getFieldNgModel());
						rfi.setContractorResponse(rfiModelRequest.getContentField());
						rfiInfoRepo.save(rfi);
					}

				}

			}
			
			List<RFIConfirmationEntity> rfiConEn = rfiConfirmRepo.findAllByIdPermitId(idPermit);
			if (rfiConEn != null && rfiConEn.isEmpty()) {
				RFIConfirmationEntity rFIConfirmationEntity = new RFIConfirmationEntity();
				rFIConfirmationEntity.setIdPermit(permit);
				rFIConfirmationEntity.setIsAdvConfirm(false);
				rFIConfirmationEntity.setIscONTRACTORConfirm(false);
				rFIConfirmationEntity.setStatutConfirmPermit("Save ADV");
				rfiConfirmRepo.save(rFIConfirmationEntity);
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public List<RFInformationEntity> getRfibyPermit(Long idpermit) {

		try {

			if (idpermit != null) {
				
				return rfiInfoRepo.findAllByIdPermitId(idpermit);

			}
			return new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public String saveContractorResponse(List<ConfContracRfiResponseModel> contractorResponse) {

		try {

			if (contractorResponse != null && !contractorResponse.isEmpty()) {
				for (ConfContracRfiResponseModel contractorRFIResponseModel : contractorResponse) {

					if (contractorRFIResponseModel != null) {

						RFInformationEntity rfi = rfiInfoRepo.findById(contractorRFIResponseModel.getId()).orElseThrow(
								() -> new ResourceNotFoundException("RFI info not found for this id :" +contractorRFIResponseModel.getId()));

						rfi.setIsConfirmed(contractorRFIResponseModel.getIsConfirmed());
						rfi.setContentField(contractorRFIResponseModel.getContentField());
						rfiInfoRepo.save(rfi);
					}
				}
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/*** Update sheet for the Contractor RFI **********/
	public String updateContractorSheetRFI(Long idPermit) {

		try {
			FileInputStream contractorRFISheet = new FileInputStream(
					new File(getfilesPath() + "RFISheet\\RFISheet-" + idPermit + ".xlsx"));

			Workbook workbook = WorkbookFactory.create(contractorRFISheet);

			Sheet datatypeSheet = workbook.getSheetAt(0);

			datatypeSheet.setColumnWidth(0, 6000);
			datatypeSheet.setColumnWidth(1, 5000);
			datatypeSheet.setColumnWidth(2, 4000);
			datatypeSheet.setColumnWidth(3, 4000);
			datatypeSheet.setColumnWidth(4, 4000);
			datatypeSheet.setColumnWidth(5, 4000);

			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM-dd-yyyy h:mm"));
			List<RFInformationEntity> resOflistRfiQuestion = rfiInfoRepo.findAllByIdPermitId(idPermit);
			if (resOflistRfiQuestion != null) {
				int rowNum = 1;

				String fieldName;
				String initialAnswer = "";
				String advTeamQuestion;
				boolean contarctorConfirmed;
				String revisedValue = "";
				Date lastUpdate;

				Cell cell3 = null;
				Cell cell4 = null;

				for (RFInformationEntity rfInformationEntity : resOflistRfiQuestion) {

					if (rfInformationEntity != null) {
						fieldName = rfInformationEntity.getAttributeName();
						advTeamQuestion = rfInformationEntity.getAdvQuestion().split("::")[1];
						if (checkValueTypesService.Equals(advTeamQuestion, "Other")) {
							advTeamQuestion = rfInformationEntity.getAdvQuestion().split("::")[2];
						}
						initialAnswer = rfInformationEntity.getContentField();
						revisedValue = rfInformationEntity.getContractorResponse();
						contarctorConfirmed = rfInformationEntity.getIsConfirmed();
						lastUpdate = new Date();
						if (rfInformationEntity.getAttributeName() != null
								&& rfInformationEntity.getAttributeName().contains("::")) {
							for (int i = 1; i < rfInformationEntity.getAttributeName().split("::").length; i++) {
								Row row = datatypeSheet.getRow(rowNum++);
								contarctorConfirmed = rfInformationEntity.getIsConfirmed();
								revisedValue = rfInformationEntity.getContractorResponse().split("::")[i];

								row.createCell(0).setCellValue(fieldName.split("::")[i]);

								if (initialAnswer != null && initialAnswer.contains("::")
										&& checkValueTypesService.NotEquals(initialAnswer.split("::")[i], "null")) {
									row.createCell(4).setCellValue(initialAnswer.split("::")[i]);
								} else if (checkValueTypesService.NotEquals(initialAnswer, "null")) {
									row.createCell(4).setCellValue(initialAnswer);
								}else{
									row.createCell(4).setCellValue("");
								}
								row.createCell(2).setCellValue(advTeamQuestion);

								cell3 = row.getCell(3);
								cell3.setCellValue(contarctorConfirmed);

								cell4 = row.getCell(1);
								cell4.setCellValue(revisedValue);

								Cell dateOfBirthCell = row.createCell(5);
								dateOfBirthCell.setCellValue(lastUpdate);
								dateOfBirthCell.setCellStyle(dateCellStyle);
							}

						} else {
							Row row = datatypeSheet.getRow(rowNum++);

							row.createCell(0).setCellValue(fieldName);

							if (checkValueTypesService.NotEquals(initialAnswer, "null")) {
								row.createCell(4).setCellValue(initialAnswer);
							} else {
								row.createCell(4).setCellValue("");
							}

							row.createCell(2).setCellValue(advTeamQuestion);

							row.createCell(3).setCellValue(contarctorConfirmed);

							if (checkValueTypesService.NotEquals(revisedValue, "null")) {
								row.getCell(1).setCellValue(revisedValue);
							} else {
								row.getCell(1).setCellValue("");
							}

							Cell dateOfBirthCell = row.createCell(5);
							dateOfBirthCell.setCellValue(lastUpdate);
							dateOfBirthCell.setCellStyle(dateCellStyle);
						}

					}

				}

			}

			contractorRFISheet.close();
			// Write the output to a file
			File f = new File(getfilesPath() + "RFISheet\\RFISheet-" + idPermit + ".xlsx");
			if (f.exists() && !f.isDirectory()) {
				f.delete();
			}
			FileOutputStream fileOut = new FileOutputStream(
					new File(getfilesPath() + "RFISheet\\RFISheet-" + idPermit + ".xlsx"));
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook

			workbook.close();

			return "Ok";

		} catch (Exception e) {
			e.printStackTrace();
			return "echec";
		}

	}

	/**** End Update sheet for the Contractor RFI *****/

	/*** generate Sheet for the ADV RFI **********/
	public String generateTheSheetRFI(Long idPermit) {

		try {
			String[] columns = { "Portal Field Name", "Portal Initial Answer", "ADV Team Question",
					"Contractor Confirmed ", "Revised Value", "Last Update" };
			XSSFWorkbook workbook = new XSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();
			XSSFSheet sheet = workbook.createSheet("RFISheet-" + idPermit);
			sheet.setColumnWidth(0, 6000);
			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 4000);

			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM-dd-yyyy h:mm"));

			CellStyle style = workbook.createCellStyle();
			XSSFFont my_font = workbook.createFont();
			my_font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			my_font.setColor(XSSFFont.COLOR_RED);
			my_font.setFamily(20);
			style.setFont(my_font);

			Row headerRow = sheet.createRow(0);
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(style);
			}
			List<RFInformationEntity> resOflistRfiQuestion = rfiInfoRepo.findAllByIdPermitId(idPermit);

			if (resOflistRfiQuestion != null) {
				int rowNum = 1;
				String fieldName;
				String initialAnswer = "";
				String advTeamQuestion;
				boolean contarctorConfirmed;
				Date lastUpdate;

				for (RFInformationEntity rfInformationEntity : resOflistRfiQuestion) {

					fieldName = rfInformationEntity.getAttributeName();
					advTeamQuestion = rfInformationEntity.getAdvQuestion().split("::")[1];
					if (checkValueTypesService.Equals(advTeamQuestion, "Other")) {
						advTeamQuestion = rfInformationEntity.getAdvQuestion().split("::")[2];
					}
					initialAnswer = rfInformationEntity.getContentField();
					contarctorConfirmed = rfInformationEntity.getIsConfirmed();
					lastUpdate = new Date();

					if (fieldName != null && fieldName.contains("::")) {
						for (int i = 1; i < fieldName.split("::").length; i++) {

							Row row = sheet.createRow(rowNum++);

							row.createCell(0).setCellValue(fieldName.split("::")[i]);

							if (checkValueTypesService.NotEquals(initialAnswer.split("::")[i], "null")) {
								row.createCell(1).setCellValue(initialAnswer.split("::")[i]);
							} else {
								row.createCell(1).setCellValue("");
							}

							row.createCell(2).setCellValue(advTeamQuestion);

							row.createCell(3).setCellValue(contarctorConfirmed);

							row.createCell(4).setCellValue("");

							Cell dateOfBirthCell = row.createCell(5);
							dateOfBirthCell.setCellValue(lastUpdate);
							dateOfBirthCell.setCellStyle(dateCellStyle);
						}
					} else {
						Row row = sheet.createRow(rowNum++);

						row.createCell(0).setCellValue(fieldName);

						if (checkValueTypesService.NotEquals(initialAnswer, "null")) {
							row.createCell(1).setCellValue(initialAnswer);
						} else {
							row.createCell(1).setCellValue("");
						}

						row.createCell(2).setCellValue(advTeamQuestion);

						row.createCell(3).setCellValue(contarctorConfirmed);

						row.createCell(4).setCellValue("");

						Cell dateOfBirthCell = row.createCell(5);
						dateOfBirthCell.setCellValue(lastUpdate);
						dateOfBirthCell.setCellStyle(dateCellStyle);
					}

				}
				File f = new File(getfilesPath() + "RFISheet\\RFISheet-" + idPermit + ".xlsx");
				if (f.exists() && !f.isDirectory()) {
					f.delete();
				}
				// Write the output to a file
				String path2 = getfilesPath();
				if (!new File(path2 + "/").exists()) {
					new File(path2 + "/").mkdir();
				}
				if (!new File(getfilesPath() + "RFISheet/").exists()) {
					new File(getfilesPath() + "RFISheet/").mkdir();
				}
				FileOutputStream fileOut = new FileOutputStream(
						new File(getfilesPath() + "RFISheet/RFISheet-" + idPermit + ".xlsx"));
				workbook.write(fileOut);
				fileOut.close();

				// Closing the workbook

				workbook.close();
				return "Ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "echec";
		}
		return "echec";
	}

	/**** End Generate sheet for the ADV RFI *****/
	public String submitADVRFI(Long idPermit, Long idUserCo, String href) {
		Integer mm = 0;
		Integer hh;

		try {

			// get the Reminder value

			Long userID = permitRepo.findProjectOwnerID(idPermit);
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(userID);

			if (userSetting.getReminder() == null) {
				userSetting.setReminder("6");
				userSettingRepo.save(userSetting);
				hh = 6;
			} else {
				hh = Integer.parseInt(userSetting.getReminder());
			}
			
			AuthentificationEntity user = userRepo.findById(idUserCo).orElse(null);

			List<RFIConfirmationEntity> listRfiConfirm = rfiConfirmRepo.findAllByIdPermitId(idPermit);
			if (listRfiConfirm == null || listRfiConfirm.isEmpty()) {
				return "notPermit";
			}

			RFIConfirmationEntity rfiConf = rfiConfirmRepo.findOneByIdPermitId(idPermit);
			rfiConf.setIdAdvUserConfirmer(user);
			rfiConf.setIsAdvConfirm(true);
			rfiConf.setStatutConfirmPermit("ADV Submit");
			rfiConf.setReminder(mm + ":" + hh);
			rfiConf.setIscONTRACTORConfirm(false);
			rfiConfirmRepo.save(rfiConf);
			generateTheSheetRFI(idPermit);
			PermitEntity permitRFIStatus = permitRepo.findById(idPermit).orElse(null);
			if (permitRFIStatus != null) {
				permitRFIStatus.setStatus("RFI Pending");
			}

			List<String> users = userRepo.findEmailByRole(1L);

			// get account user mail
			String mailUser = permitRepo.findProjectOwnerEmail(idPermit);
		
			// get the contact mail
			String contactMail = contractorInfoRepo.findContactEmail(userID);

			// get the Second contact mail
			String secondContactEmail = contractorInfoRepo.findSecondContactEmail(userID);

			// get the Second contact mail
			String thirdContactEmail = contractorInfoRepo.findThirdContactEmail(userID);

			// get Other project contact mail for the owner of the permit
			AddContactRfiModel addContactRfiModel = contractorInfoRepo.getContactRfiModel(userID);

			Boolean active = permitRepo.isUserActive(idPermit);

			Boolean deleted = permitRepo.isUserDeleted(idPermit);
			
			String rfiLink = href + "/#/projects/editProject/?idP=" + idPermit;

			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));
			String projectName = "";
			if (checkValueTypesService.NotEquals(permit.getHomeOwnLastName(), "")) {
				if (checkValueTypesService.NotEquals(permit.getHomeOwnName(), "")) {
					projectName = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
				} else {
					projectName = permit.getHomeOwnLastName();
				}
			} else if (checkValueTypesService.NotEquals(permit.getHomeOwnName(), "")) {
				projectName = permit.getHomeOwnName();
			} else {
				projectName = permit.getProjectName();
			}

			users.add(mailUser);
			String text = "Hello,<br><br>The Advanced Solar Solutions Team has an RFI (Request for Information) on your project \""
					+ projectName + "\".<br>Please Click <a href=" + rfiLink + ">HERE</a> "
					+ "to be redirected to your RFI's for this project.<br> NOTE : Your project will remain on hold until all RFI's are addressed.<br><br>Thank you.<br><br>Your ADV Solar Team.";

			if (checkValueTypesService.Equals(active, true) && checkValueTypesService.Equals(deleted, false)) {
				String responseMailing = mailingService.sendingMailMultipleRecieversForRfi(mailUser,
						projectName + " Project ON HOLD Pending RFI's", text, user.getEmail());
				String responsePrimaryMailing = " ";
				String responseSecondMailing = " ";
				String responseThirdMailing = " ";

				if (checkValueTypesService.NotEquals(addContactRfiModel.getContactmail(), "") 
						&& checkValueTypesService.NotEquals(addContactRfiModel.getContactmail(), mailUser)) {
						responsePrimaryMailing = mailingService.sendingMailMultipleRecieversForRfi(
								addContactRfiModel.getContactmail(), projectName + " Project ON HOLD Pending RFI's",
								text, user.getEmail());
				}

				if (Boolean.TRUE.equals(addContactRfiModel.getIsProjectAddInclud())) {
					if (checkValueTypesService.NotEquals(addContactRfiModel.getSecondContactEmail(), "") 
							&& checkValueTypesService.NotEquals(addContactRfiModel.getSecondContactEmail(), mailUser)) {
							responseSecondMailing = mailingService.sendingMailMultipleRecieversForRfi(
									addContactRfiModel.getSecondContactEmail(),
									projectName + " Project ON HOLD Pending RFI's", text, user.getEmail());
					}
					if (checkValueTypesService.NotEquals(addContactRfiModel.getThirdContactEmail(), "") 
							&& checkValueTypesService.NotEquals(addContactRfiModel.getThirdContactEmail(), mailUser)) {
							responseThirdMailing = mailingService.sendingMailMultipleRecieversForRfi(
									addContactRfiModel.getThirdContactEmail(),
									projectName + " Project ON HOLD Pending RFI's", text, user.getEmail());
					}

				}

				if (checkValueTypesService.Equals(responseMailing, "problem sending email")
						&& checkValueTypesService.Equals(responsePrimaryMailing, "problem sending email")
						&& checkValueTypesService.Equals(responseSecondMailing, "problem sending email")
						&& checkValueTypesService.Equals(responseThirdMailing, "problem sending email")) {
					return "problem sending email";
				}

				/****************** Mailing remainder **************************/
				final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

				Integer mmsec = 0;
				if (mm > 0 || hh > 0) {
					mmsec = (mm * 60 + hh * 3600);
					try {
						final Runnable beeper = new Runnable() {
							public void run() {

								Boolean isConfir = false;
								List<Boolean> isConConf = rfiConfirmRepo.isContractorConfirmation(idPermit);

								if (!isConConf.isEmpty()) {
									isConfir = rfiConfirmRepo.findContractorConfirmation(idPermit);
								}

								if (checkValueTypesService.Equals(isConfir, false)) {
									try {
										String projectN = "";
										if (checkValueTypesService.NotEquals(permit.getHomeOwnLastName(), "")) {
											if (checkValueTypesService.NotEquals(permit.getHomeOwnName(), "")) {
												projectN = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
											} else {
												projectN = permit.getHomeOwnLastName();
											}
										} else if (checkValueTypesService.NotEquals(permit.getHomeOwnName(), "")) {
											projectN = permit.getHomeOwnName();
										} else {
											projectN = permit.getProjectName();
										}
										mailingService.sendingMailMultipleRecieversForRfi(mailUser,
												projectN + " Project ON HOLD Pending RFI's", text,
												user.getEmail());
										if (checkValueTypesService.NotEquals(contactMail, null)) {
											mailingService.sendingMailMultipleRecieversForRfi(contactMail,
													projectN + " Project ON HOLD Pending RFI's", text,
													user.getEmail());
										}
										if (checkValueTypesService.NotEquals(secondContactEmail, null)) {
											mailingService.sendingMailMultipleRecieversForRfi(secondContactEmail,
													projectN + " Project ON HOLD Pending RFI's", text,
													user.getEmail());
										}
										if (checkValueTypesService.NotEquals(thirdContactEmail, null)) {
											mailingService.sendingMailMultipleRecieversForRfi(thirdContactEmail,
													projectN + " Project ON HOLD Pending RFI's", text,
													user.getEmail());
										}
									} catch (AddressException | UnsupportedEncodingException e) {
										e.printStackTrace();
									}
								}

							}
						};
						final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, mmsec, mmsec,
								SECONDS);
						scheduler.schedule(new Runnable() {
							public void run() {
								beeperHandle.cancel(true);
							}
						}, 86400, HOURS);
					} catch (NullPointerException e) {
						e.printStackTrace();
					}

				}
			}

			/*****************************
			 * Reopen permit id submited true
			 ************************************************/

			if (permit.isSubmitted()) {
				permit.setStatus("RFI Pending");
				permitRepo.save(permit);
			}

			List<ProjectsTrackerEntity> listTrackers = projectTrackerRepo.findAllByPermitId(permit.getId());
			TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
			Calendar calSubADV = Calendar.getInstance(); // creates calendar
			calSubADV.setTime(new Date());
			try {
				if (listTrackers.size() == 1) {
					ProjectsTrackerEntity tracker = projectTrackerRepo.findOneByPermitId(permit.getId());
					tracker.setSubmittedAdvRfi(calSubADV.getTime());
					projectTrackerRepo.save(tracker);
				} else if (listTrackers.isEmpty()) {
					ProjectsTrackerEntity tracker2 = new ProjectsTrackerEntity();
					tracker2.setPermit(permit);
					tracker2.setSubmittedAdvRfi(calSubADV.getTime());
					projectTrackerRepo.save(tracker2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";

		}

	}

	public String submitContractorRFI(Long idPermit, Long idUserCo) {

		try {

			AuthentificationEntity user = userRepo.findById(idUserCo).orElse(null);
			List<RFIConfirmationEntity> listRfiCon = rfiConfirmRepo.findAllByIdPermitId(idPermit);
			if (listRfiCon == null || listRfiCon.isEmpty()) {
				return "notPermit";
			}

			RFIConfirmationEntity rfiConf = rfiConfirmRepo.findByIdPermitId(idPermit);
			if (rfiConf != null) {
				rfiConf.setIdContractorUserConfirmer(user);
				rfiConf.setIscONTRACTORConfirm(true);
				rfiConf.setStatutConfirmPermit("Contractor Submit");
				rfiConfirmRepo.save(rfiConf);
			}

			List<String> users = userRepo.findEmailByRole(1L);

			/*List<AuthentificationEntity> rfiOwner = (List<AuthentificationEntity>) em
					.createQuery("SELECT u.idAdvUserCo from RFInformationEntity u where u.idPermit.id=:p1")
					.setParameter("p1", idPermit).setMaxResults(1).getResultList();*/
			AuthentificationEntity rfiOwner = rfiInfoRepo.findFirstIdAdvUserCoByIdPermitId(idPermit);
			
			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));
			String projectName = "";
			if (checkValueTypesService.NotEquals(permit.getHomeOwnLastName(), "")) {
				if (checkValueTypesService.NotEquals(permit.getHomeOwnName(), "")) {
					projectName = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
				} else {
					projectName = permit.getHomeOwnLastName();
				}
			} else if (checkValueTypesService.NotEquals(permit.getHomeOwnName(), "")) {
				projectName = permit.getHomeOwnName();
			} else {
				projectName = permit.getProjectName();
			}
			/***** Change the Status of the permit to In progress **************/
			PermitEntity permitOfContractor = permitRepo.findById(idPermit).orElse(null);
			if (permitOfContractor != null) {
				permitOfContractor.setStatus("In Progress");
			}

			String mailUser = rfiOwner.getEmail();
			Boolean active = rfiOwner.getActive();

			Boolean deleted = userRepo.findDeletedById(rfiOwner.getId());
			if (checkValueTypesService.Equals(active, true) && checkValueTypesService.Equals(deleted, false)) {
				users.add(mailUser);
				String body = "<html>The Requested RFI for the project : " + projectName
						+ " have been answered by the owner of the project..<br>You can find the attached RFI log sheet</html>";
				String responseMailing = mailingService.sendingRFIMail(mailUser, "RFI Answers", body, idPermit);

				if (checkValueTypesService.Equals(responseMailing, "problem sending email")) {
					return "problem sending email";
				}
			}
			TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
			Calendar calSubCon = Calendar.getInstance(); // creates calendar
			calSubCon.setTime(new Date());

			try {
				List<ProjectsTrackerEntity> listTracks = projectTrackerRepo.findAllByPermitId(idPermit);
				if (listTracks.size() == 1) {
					ProjectsTrackerEntity tracker = listTracks.get(0);

					if (tracker != null) {
						tracker.setSubmittedContRfi(calSubCon.getTime());
						projectTrackerRepo.save(tracker);
					}
				} else if (listTracks.isEmpty()) {
					ProjectsTrackerEntity tracker2 = new ProjectsTrackerEntity();
					tracker2.setPermit(permit);
					tracker2.setSubmittedContRfi(calSubCon.getTime());
					projectTrackerRepo.save(tracker2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "success";
		} catch (Exception e) {

			e.printStackTrace();
			return "error";

		}
	}

	public RFIConfirmationEntity getConfirmationRfi(Long idPermit) {
		try {
			
			return rfiConfirmRepo.findOneByIdPermitId(idPermit);
		
	    	} catch (Exception e) {
			e.printStackTrace();
			return new RFIConfirmationEntity();
		}
	}

	public List<RFIQuestionEntity> getRFIQuestion(List<String> rfiFields) {
		List<RFIQuestionEntity> rfis = new ArrayList<>();
		Integer i = 0;
		Integer j = 0;
		Integer k = 0;
		Integer l = 0;
		Integer m = 0;
		Integer n = 0;
		try {
			if (rfiFields != null) {
				for (String field : rfiFields) {
					
					List<RFIQuestionEntity> rfi0 =  rfiQuestionRepo.findByFieldNameAndQuestionActived(field, true);
					
					if (rfi0 != null && !rfi0.isEmpty()) {
						rfis.addAll(rfi0);
					} else {
						if (i == 0 && (checkValueTypesService.Equals(field, "Qty[Module]")
								|| checkValueTypesService.Equals(field, "Qty[Optimizer]")
								|| checkValueTypesService.Equals(field, "Qty[INV]")
								|| checkValueTypesService.Equals(field, "Qty[DCD]")
								|| checkValueTypesService.Equals(field, "Qty[MINV]")
								|| checkValueTypesService.Equals(field, "Qty[DCC]")
								|| checkValueTypesService.Equals(field, "Qty[ACJBOX]")
								|| checkValueTypesService.Equals(field, "Qty[ACC]")
								|| checkValueTypesService.Equals(field, "Qty[ACD]")
								|| checkValueTypesService.Equals(field, "Qty[PMETER]")
								|| checkValueTypesService.Equals(field, "Qty[ACSUBPANEL]")
								|| checkValueTypesService.Equals(field, "Qty[DCJBOX]"))) {
							
							i = i + 1;
							
							List<RFIQuestionEntity> rfi1 = rfiQuestionRepo.findByFieldNameAndQuestionActived("Qty", true);
							
							if (rfi1 != null && !rfi1.isEmpty()) {

								rfis.addAll(rfi1);
							}
						}
						if (n == 0 && (field.contains("Wire Qty["))) {
							
							n = 1;
							
							List<RFIQuestionEntity> rfi2 = rfiQuestionRepo.findByFieldNameAndQuestionActived("Wire Qty", true);
							
							if (rfi2 != null && !rfi2.isEmpty()) {
								rfis.addAll(rfi2);
							}
						} else if (j == 0 && (checkValueTypesService.Equals(field, "Wire Type[Module]")
								|| checkValueTypesService.Equals(field, "Wire Type[Optimizer]")
								|| checkValueTypesService.Equals(field, "Wire Type[INV]")
								|| checkValueTypesService.Equals(field, "Wire Type[DCD]")
								|| checkValueTypesService.Equals(field, "Wire Type[MINV]")
								|| checkValueTypesService.Equals(field, "Wire Type[DCC]")
								|| checkValueTypesService.Equals(field, "Wire Type[ACJBOX]")
								|| checkValueTypesService.Equals(field, "Wire Type[ACC]")
								|| checkValueTypesService.Equals(field, "Wire Type[ACD]")
								|| checkValueTypesService.Equals(field, "Wire Type[PMETER]")
								|| checkValueTypesService.Equals(field, "Wire Type[ACSUBPANEL]")
								|| checkValueTypesService.Equals(field, "Wire Type[DCJBOX]"))) {
							
							j = 1;

							List<RFIQuestionEntity> rfi3 = rfiQuestionRepo.findByFieldNameAndQuestionActived("Wire Type", true);
							
							if (rfi3 != null && !rfi3.isEmpty()) {
								rfis.addAll(rfi3);
							}
						} else if (k == 0 && (checkValueTypesService.Equals(field, "Wire Size[Module]")
								|| checkValueTypesService.Equals(field, "Wire Size[Optimizer]")
								|| checkValueTypesService.Equals(field, "Wire Size[INV]")
								|| checkValueTypesService.Equals(field, "Wire Size[DCD]")
								|| checkValueTypesService.Equals(field, "Wire Size[MINV]")
								|| checkValueTypesService.Equals(field, "Wire Size[DCC]")
								|| checkValueTypesService.Equals(field, "Wire Size[ACJBOX]")
								|| checkValueTypesService.Equals(field, "Wire Size[ACC]")
								|| checkValueTypesService.Equals(field, "Wire Size[ACD]")
								|| checkValueTypesService.Equals(field, "Wire Size[PMETER]")
								|| checkValueTypesService.Equals(field, "Wire Size[ACSUBPANEL]")
								|| checkValueTypesService.Equals(field, "Wire Size[DCJBOX]"))) {
							
							k = 1;
							
							List<RFIQuestionEntity> rfi4 = rfiQuestionRepo.findByFieldNameAndQuestionActived("Wire Size", true);
							
							if (rfi4 != null && !rfi4.isEmpty()) {
								rfis.addAll(rfi4);
							}
						} else if (l == 0 && (checkValueTypesService.Equals(field, "Conduit Type[Module]")
								|| checkValueTypesService.Equals(field, "Conduit Type[Optimizer]")
								|| checkValueTypesService.Equals(field, "Conduit Type[INV]")
								|| checkValueTypesService.Equals(field, "Conduit Type[DCD]")
								|| checkValueTypesService.Equals(field, "Conduit Type[MINV]")
								|| checkValueTypesService.Equals(field, "Conduit Type[DCC]")
								|| checkValueTypesService.Equals(field, "Conduit Type[ACJBOX]")
								|| checkValueTypesService.Equals(field, "Conduit Type[ACC]")
								|| checkValueTypesService.Equals(field, "Conduit Type[ACD]")
								|| checkValueTypesService.Equals(field, "Conduit Type[PMETER]")
								|| checkValueTypesService.Equals(field, "Conduit Type[ACSUBPANEL]")
								|| checkValueTypesService.Equals(field, "Conduit Type[DCJBOX]"))) {
							
							l = 1;
							
							List<RFIQuestionEntity> rfi5 = rfiQuestionRepo.findByFieldNameAndQuestionActived("Conduit Type", true);
							
							if  (rfi5 != null && !rfi5.isEmpty()) {
								rfis.addAll(rfi5);
							}
						} else if (m == 0 && (checkValueTypesService.Equals(field, "Conduit Size[Module]")
								|| checkValueTypesService.Equals(field, "Conduit Size[Optimizer]")
								|| checkValueTypesService.Equals(field, "Conduit Size[INV]")
								|| checkValueTypesService.Equals(field, "Conduit Size[DCD]")
								|| checkValueTypesService.Equals(field, "Conduit Size[MINV]")
								|| checkValueTypesService.Equals(field, "Conduit Size[DCC]")
								|| checkValueTypesService.Equals(field, "Conduit Size[ACJBOX]")
								|| checkValueTypesService.Equals(field, "Conduit Size[ACC]")
								|| checkValueTypesService.Equals(field, "Conduit Size[ACD]")
								|| checkValueTypesService.Equals(field, "Conduit Size[PMETER]")
								|| checkValueTypesService.Equals(field, "Conduit Size[ACSUBPANEL]")
								|| checkValueTypesService.Equals(field, "Conduit Size[DCJBOX]"))) {
							
							m = 1;
							
							List<RFIQuestionEntity> rfi6 = rfiQuestionRepo.findByFieldNameAndQuestionActived("Conduit Size", true);
							
							if (rfi6 != null && !rfi6.isEmpty()) {
								rfis.addAll(rfi6);
							}
						}

						else {
							RFIQuestionEntity rfiQ = new RFIQuestionEntity();
							rfiQ.setAddedBy("Super User");
							rfiQ.setAttachementShown(false);
							rfiQ.setConfirmation(true);
							rfiQ.setFieldName(field);
							rfiQ.setQuestionActived(true);
							rfiQ.setQuestionstatic("Value Selected below");
							rfiQ.setRFIDocument(false);
							rfiQ.setRfiQuestion("NUATN - Please add your questions for this input");
							rfis.add(rfiQ);
						}
					}

				}
			}
			// delete all pfi affected dor this permit if exist

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rfis;
	}

	public String addNotifSubmitRfiADV(Long idUser, Long idPermit) {
		try {

			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));
			Long idUserReceiver = permit.getAuthentificationEntity().getId();

			notificationEntityService.addNewNotif(idUser, idUserReceiver, "RFI ", "Projects",
					"RFI - " + permit.getHomeOwnName(),
					"The Advanced Solar Solutions Team has an RFI (Request for Information) on your project \""
							+ permit.getHomeOwnName()
							+ "\". NOTE : Your project will remain on hold until all RFI's are addressed.",
					false);

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		return "Done";
	}

	public String addNotifSubmitRfiContractor(Long idUser, Long idPermit) {
		try {
			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));

			AuthentificationEntity owner = permit.getAuthentificationEntity();

			notificationEntityService.addNewNotif(idUser, 0L, "RFI Answer ", "Projects",
					"RFI Answer   - " + permit.getHomeOwnName(), owner.getFirstName() + " " + owner.getLastName()
							+ " answered the Requested RFI for the project \"" + permit.getHomeOwnName() + "\".",
					true);

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		return "Done";
	}
}
