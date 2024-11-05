package com.PlayGroundAdv.Solar.service.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdvInputsRepository;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.PlayGroundAdv.Solar.repositories.PermitHomeSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.PermitWeatherRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.drafter_details.CleanDrafterSheets;
import com.PlayGroundAdv.Solar.service.generate_planset.drafter_details.DrafterSheetsMapping;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.user_management.GoogleDriveFolder;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.google.common.io.Files;

import sun.net.www.protocol.file.FileURLConnection;

@Service
public class CopyProjectToGoogleDrive {

	final MailingService mailingService;
	final CheckValueTypesService checkValue;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	final PermitHomeSiteInfoRepository permitHomeSiteInfoRepo;
	final PermitRepository permitRepo;
	final AuthenticationRepository userRepo;
	final PermitWeatherRepository weatherRepo;
	final PermitAdvInputsRepository advInputsRepo;
	final DrafterSheetsMapping drafterMapping;
	final CleanDrafterSheets cleanDrafterSheets;
	final GoogleDriveFolder googleDriveFolder;
	final PathRepository pathRepo;

	public CopyProjectToGoogleDrive(MailingService mailingService, CheckValueTypesService checkValue,
			PermitDrafterDataRepository permitDrafterDataRepo, PermitHomeSiteInfoRepository permitHomeSiteInfoRepo,
			PermitRepository permitRepo, AuthenticationRepository userRepo, PermitWeatherRepository weatherRepo,
			PermitAdvInputsRepository advInputsRepo, DrafterSheetsMapping drafterMapping, CleanDrafterSheets cleanDrafterSheets,
			GoogleDriveFolder googleDriveFolder, PathRepository pathRepo) {
		super();
		this.mailingService = mailingService;
		this.checkValue = checkValue;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
		this.permitHomeSiteInfoRepo = permitHomeSiteInfoRepo;
		this.permitRepo = permitRepo;
		this.userRepo = userRepo;
		this.weatherRepo = weatherRepo;
		this.advInputsRepo = advInputsRepo;
		this.drafterMapping = drafterMapping;
		this.cleanDrafterSheets = cleanDrafterSheets;
		this.googleDriveFolder = googleDriveFolder;
		this.pathRepo = pathRepo;
	}

	public String getfilesPath() {
		try {
			return pathRepo.findFilePath();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "";
		}
	}

	// 09-26-2019: M.A: Correction of submit's mail sending
	public String copyProjectOnDrive(Long idPermit, Long idUser) throws AddressException {

		try {
			PermitEntity permitEntity = permitRepo.findById(idPermit).orElse(null);
			PermitHomeSiteEntityResult permitHomeSite = permitHomeSiteInfoRepo.getProjectHomeSiteInfo(idPermit);
			String projectname = permitEntity.getProjectName();
			if (permitEntity.getProjectName() == null || checkValue.Equals(permitEntity.getProjectName(), "")) {
				projectname = permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName();
			}
			AuthentificationEntity user = userRepo.findById(idUser).orElse(null);
			String folderName = permitEntity.getProjectName();
			if (!checkValue.isStringNotEmpty(permitEntity.getProjectName()))
				folderName = permitEntity.getHomeOwnLastName() + ", " + permitEntity.getHomeOwnName();

			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(permitEntity.getAuthentificationEntity());

			folderName = folderName.trim();

			if (new File(pathRepo.findGoogleDriveFilePath() + ownerFolderName + "/" + folderName).exists()) {
				try {
					String fileName = "";
					// A.B wait for the Planset to be ready
					Thread.sleep(2000);
					
					PermtiWeatherEntityResult permtiWeather = weatherRepo.getPermtiWeatherEntityResult(idPermit);
					PermitDrafterDataEntity permitDrafterDatanfo = permitDrafterDataRepo.findByPermitEntityId(idPermit);
					PermitAdvEntityResult advProjectInfo = advInputsRepo.getProjectADVInputs(idPermit);
					if (checkValue.isStringNotEmpty(permitHomeSite.getResidenceBindingCategory())
							&& checkValue.isStringNotEmpty(permtiWeather.getElevation())
							&& checkValue.isStringNotEmpty(permtiWeather.getQuatrePourCentAverageHigh())
							&& checkValue.isStringNotEmpty(advProjectInfo.getWindSpeed())
							&& checkValue.isStringNotEmpty(advProjectInfo.getSnowLoad())) {

						if (checkValue.isStringNotEmpty(permitDrafterDatanfo.getParcelMapName())) {
							fileName = "Raw with Drafter.pdf";
						} else
							fileName = "Raw with ADV Inputs.pdf";

					} else
						fileName = checkValue.isStringNotEmpty(permitDrafterDatanfo.getParcelMapName()) ? "Raw with Drafter.pdf" : "Raw PDF Plan Set.pdf";

					// CR-3230 map drafts to the planset if drafts exist and upload the version with
					// drafts to the drive
					// find the file name the drafts for the permit
					String parcel = permitDrafterDataRepo.findParcelMapName(idPermit);

					// locate the drafts file
					File pathCheck = new File(getfilesPath() + "/" + idPermit + "/drafterfiles/" + parcel);
					String src = getfilesPath() + "Rapport/SampleResult" + idPermit + ".pdf";
					// find the permits folder path : url in the server
					String url = getfilesPath();
					// get the specific permit file

					// create a new copy of the permit file
					File fileCopy = new File(url + "Rapport/SampleResult-copy-" + idPermit + ".pdf");
					if (fileCopy.exists()) {
						fileCopy.delete();
						fileCopy = new File(url + "Rapport/SampleResult-copy-" + idPermit + ".pdf");
					}

					// open the created copy
					
					System.out.println(
							"file exist =  " + new File(url + "Rapport/SampleResult" + idPermit + ".pdf").exists());
					if (!new File(url + "Rapport/SampleResult" + idPermit + ".pdf").exists()) {
						Thread.sleep(5000);
						System.out.println("file exist After Sleep =  "
								+ new File(url + "Rapport/SampleResult" + idPermit + ".pdf").exists());
					}

					// check if the file exists
					if (pathCheck.exists()) {
						if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
							String cleanPath = getfilesPath() + "Rapport/SampleResult-copy-" + idPermit + "-cleaned.pdf";
							File cleanFile = new File(cleanPath);
							if (cleanFile.exists()) {
								cleanFile.delete();
								cleanFile = new File(cleanPath);
							}
							cleanDrafterSheets.cleanDraftSheet(getfilesPath(), idPermit, cleanFile);
							drafterMapping.drafterMapping(url, idPermit, fileCopy,cleanPath);
							cleanFile.delete();
						}else {
							drafterMapping.drafterMapping(url, idPermit, fileCopy,getfilesPath() + "Rapport/SampleResult" + idPermit + ".pdf");
						}
						src = getfilesPath() + "Rapport/SampleResult-copy-" + idPermit + ".pdf";
						// CR-3230 ends here
					}

					try {
						if (new File(pathRepo.findGoogleDriveFilePath() + ownerFolderName + "/" + folderName)
								.exists()) {
							Thread.sleep(2000);
							Path srcFile = Paths.get(src);
							Path destFile = Paths.get(pathRepo.findGoogleDriveFilePath() + ownerFolderName + "/"
									+ folderName + "/Plan Set Drafts/" + fileName);
							Files.copy(srcFile.toFile(), destFile.toFile());
							// CR-3230 delete the copy after uploading it to the drive
							fileCopy.delete();
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Thread.sleep(30000);
					String plansetFileUrl = createFileId(
							pathRepo.findGoogleDriveFilePath() + ownerFolderName + "/" + folderName
									+ "/Plan Set Drafts/" + fileName + ":user.drive.id",
							permitEntity.getPlansetDriveId());

					if (!checkValue.Equals(plansetFileUrl, "exceeds")) {
						permitEntity.setPlansetDriveId(plansetFileUrl);
						permitRepo.save(permitEntity);
						if (user.getEmail() != null && (user.getEmail().contains("nuagetechnologies-tn.com")
								|| checkValue.Equals(user.getEmail(), "nabil-g@advpermits.com"))) {
							try {
								if (checkValue.NotEquals(user.getCompany(), "")) {
									mailingService.sendingSubmitMailNUATN("Project Submission - " + projectname, user
											.getCompany() + " submitted " + projectname
											+ " project in projects category.<br>Please add project into tracker and proceed with processing.<br> <a href='https://drive.google.com/a/"
											+ pathRepo.findGoogleDriveEmail() + "/uc?authuser=0&id="
											+ permitEntity.getPlansetDriveId()
											+ "&export=download'>Download the Plan Set Document</a>" + "", idPermit,
											idUser, projectname, permitHomeSite.getState());
								} else {
									mailingService.sendingSubmitMailNUATN("Project Submission - " + projectname, user
											.getFirstName() + " " + user.getLastName() + " submitted " + projectname
											+ " project in projects category.<br>Please add project into tracker and proceed with processing.<br> <a href='https://drive.google.com/a/"
											+ pathRepo.findGoogleDriveEmail() + "/uc?authuser=0&id="
											+ permitEntity.getPlansetDriveId()
											+ "&export=download'>Download the Plan Set Document</a>" + "", idPermit,
											idUser, projectname, permitHomeSite.getState());
								}

							} catch (AddressException e) {
								e.printStackTrace();
							}

						} else {
							System.out.println("**** " + user.getRoleEntity().getDescription()
									+ " Submission Email With Planset *****");
							if (checkValue.NotEquals(user.getCompany(), "")) {
								mailingService.sendingSubmitMail("Project Submission - " + projectname, user
										.getCompany() + " submitted " + projectname
										+ " project in projects category.<br>Please add project into tracker and proceed with processing.<br> <a href='https://drive.google.com/a/"
										+ pathRepo.findGoogleDriveEmail() + "/uc?authuser=0&id="
										+ permitEntity.getPlansetDriveId()
										+ "&export=download'>Download the Plan Set Document</a>", idPermit, idUser,
										projectname, permitHomeSite.getState());
							} else {
								mailingService.sendingSubmitMail("Project Submission - " + projectname, user
										.getFirstName() + " " + user.getLastName() + " submitted " + projectname
										+ " project in projects category.<br>Please add project into tracker and proceed with processing.<br> <a href='https://drive.google.com/a/"
										+ pathRepo.findGoogleDriveEmail() + "/uc?authuser=0&id="
										+ permitEntity.getPlansetDriveId()
										+ "&export=download'>Download the Plan Set Document</a>", idPermit, idUser,
										projectname, permitHomeSite.getState());
							}

						}

					} else {
						SendSubmitEmailWithoutPlanset(projectname, idPermit, user, permitHomeSite.getState());
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("***** Exception " + user.getRoleEntity().getDescription()
							+ " Submission Email With Planset!! *****");
					SendSubmitEmailWithoutPlanset(projectname, idPermit, user, permitHomeSite.getState());
				}

			} else {

				SendSubmitEmailWithoutPlanset(projectname, idPermit, user, permitHomeSite.getState());
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			traitMailingException(e, idPermit, idUser);
			return "Fail";
		}

	}

	public void SendSubmitEmailWithoutPlanset(String projectname, Long idPermit, AuthentificationEntity user, String state) {
		try {
			if (user.getEmail() != null && (user.getEmail().contains("nuagetechnologies-tn.com")
					|| checkValue.Equals(user.getEmail(), "nabil-g@advpermits.com"))) {
				try {
					if (checkValue.NotEquals(user.getCompany(), "")) {
						mailingService.sendingSubmitMailNUATN("Project Submission - " + projectname, user.getCompany()
								+ " submitted " + projectname
								+ " project in projects category.<br>Please add project into tracker and proceed with processing.",
								idPermit, user.getId(), projectname, state);
					} else {
						mailingService.sendingSubmitMailNUATN("Project Submission - " + projectname, user.getFirstName()
								+ " " + user.getLastName() + " submitted " + projectname
								+ " project in projects category.<br>Please add project into tracker and proceed with processing.",
								idPermit, user.getId(), projectname, state);
					}

				} catch (AddressException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println(
						"**** " + user.getRoleEntity().getDescription() + " Submission Email Without Planset ****");
				if (checkValue.NotEquals(user.getCompany(), "")) {
					mailingService.sendingSubmitMail("Project Submission - " + projectname, user.getCompany()
							+ " submitted " + projectname
							+ " project in projects category.<br>Please add project into tracker and proceed with processing.<br>",
							idPermit, user.getId(), projectname, state);
				} else {
					mailingService.sendingSubmitMail("Project Submission - " + projectname, user.getFirstName() + " "
							+ user.getLastName() + " submitted " + projectname
							+ " project in projects category.<br>Please add project into tracker and proceed with processing.<br>",
							idPermit, user.getId(), projectname, state);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String traitMailingException(Exception e, Long idpermit, Long idUser) {
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
				PermitEntity permit = permitRepo.findById(idpermit).orElse(new PermitEntity());
				AuthentificationEntity user = userRepo.findById(idUser).orElse(new AuthentificationEntity());
				String projectname = permit.getProjectName();
				if (permit.getProjectName() == null || checkValue.Equals(permit.getProjectName(), "")) {
					projectname = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
				}
				String textNuatn = "Hello,<br><br>" + user.getFirstName() + " " + user.getLastName()
						+ " has a mailing technical problem when submitting the project " + projectname
						+ ".<ul><li>Project created by : " + permit.getAuthentificationEntity().getFirstName() + " "
						+ permit.getAuthentificationEntity().getLastName() + ".</li><li>Submit date : " + new Date()
						+ "</li>" + exceptionInfo
						+ "</ul><br>Please check this Error as soon as possible.<br><br>Thank you.";
				mailingService.sendingMailPMSubmitEvaluation("dev0@nuagetechnologies-tn.com",
						"Mailing Exception - " + projectname, textNuatn, projectname,
						user.getFirstName() + " " + user.getLastName());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "done";
	}

	public String createFileId(String drivePath, String idDrive) {

		String fileID = "";
		long startTime = System.currentTimeMillis();

		try {
			File fileResult = new File(drivePath);
			URL url = fileResult.toURI().toURL();
			FileURLConnection con = (FileURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			Boolean reload = true;

			while (((inputLine = in.readLine()) != null && checkValue.NotEquals(idDrive, inputLine))
					|| reload.equals(true)) {
				if (inputLine != null && !inputLine.contains("local-")) {
					reload = false;
					content.append(inputLine);
				}

				long endTime = System.currentTimeMillis();
				if (endTime - startTime >= 240000) {
					return "exceeds";
				}

			}
			in.close();
			fileID = content + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileID;
	}
}
