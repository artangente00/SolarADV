package com.PlayGroundAdv.Solar.service.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.log.ArchiveLogEntity;
import com.PlayGroundAdv.Solar.model.UtilsModel;
import com.PlayGroundAdv.Solar.model.project.DelayArchiveRequest;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.log.ArchiveLogRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.export_project.ExportProjectSvcService;
import com.PlayGroundAdv.Solar.service.mailing.ArchiveMailing;
import com.PlayGroundAdv.Solar.service.user_management.GoogleDriveFolder;
import com.PlayGroundAdv.Solar.service.utils.TrippleDes;

import sun.net.www.protocol.file.FileURLConnection;

@Service
@EnableScheduling
public class ArchiveProjectService {

	final UserSettingRepository userSettingRepo;
	final AuthenticationRepository userRepo;
	final PermitRepository permitRepo;
	final ArchiveLogRepository archiveLogRepo;
	final GetProjectDetailsUtils projectDetailsUtils;
	final ArchiveMailing archiveMailing;
	final GoogleDriveFolder googleDriveFolder;
	final PathRepository pathRepo;
	final ExportProjectSvcService exportService;
	final TrippleDes td;
	final GetProjectDetailsUtils projectUtils;

	public ArchiveProjectService(UserSettingRepository userSettingRepo, AuthenticationRepository userRepo,
			PermitRepository permitRepo, ArchiveLogRepository archiveLogRepo,
			GetProjectDetailsUtils projectDetailsUtils, ArchiveMailing archiveMailing,
			GoogleDriveFolder googleDriveFolder, PathRepository pathRepo, ExportProjectSvcService exportService,
			TrippleDes td,GetProjectDetailsUtils projectUtils) {
		super();
		this.userSettingRepo = userSettingRepo;
		this.userRepo = userRepo;
		this.permitRepo = permitRepo;
		this.archiveLogRepo = archiveLogRepo;
		this.projectDetailsUtils = projectDetailsUtils;
		this.archiveMailing = archiveMailing;
		this.googleDriveFolder = googleDriveFolder;
		this.pathRepo = pathRepo;
		this.exportService = exportService;
		this.td = td;
		this.projectUtils = projectUtils;
	}

	@Scheduled(cron = "0 0 1 * * *", zone = "America/Los_Angeles")
	public void sendTimeTaskNotification() {
		List<UtilsModel> users = userSettingRepo.findUserByArchiveAllowed();

		for (UtilsModel u : users) {
			Date date = Date.from(ZonedDateTime.now().minusMonths(u.getNumber()).toInstant());
			List<PermitEntity> projects = permitRepo
					.findByAuthentificationEntityIdAndUpdateDateBeforeAndIsTemplateFalseAndArchiveStatusIsNull(
							u.getId(), date);
			System.out.println(projects.size());
			for (PermitEntity p : projects) {
				try {
					archiveMailing.archiveNotice(p);
					p.setArchiveStatus("waiting");
					ArchiveLogEntity archive = p.getArchive() != null ? p.getArchive() : new ArchiveLogEntity();
					archive.setArchiveDate(DateUtils.addHours(new Date(), 72));
					p.setArchive(archive);
					permitRepo.save(p);
					TimeUnit.SECONDS.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// Delayed Projects
			List<PermitEntity> delayedProjects = permitRepo
					.findByAuthentificationEntityIdAndUpdateDateBeforeAndArchiveStatus(u.getId(), date, "delayed")
					.stream()
					.filter(p -> DateUtils.isSameDay(DateUtils.addDays(p.getArchive().getDelayDate(), 7), new Date()))
					.collect(Collectors.toList());
			for (PermitEntity p : delayedProjects) {
				try {
					archiveMailing.archiveNotice(p);
					p.setArchiveStatus("waiting");
					ArchiveLogEntity archive = p.getArchive() != null ? p.getArchive() : new ArchiveLogEntity();
					archive.setArchiveDate(DateUtils.addHours(new Date(), 72));
					p.setArchive(archive);
					permitRepo.save(p);
					TimeUnit.SECONDS.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public DelayArchiveRequest getProjectInfo(String idProject) {
		try {
			Long id = Long.valueOf(td.decrypt(idProject));
			PermitEntity project = permitRepo.findById(id).orElse(null);
			if (project != null) {
				String company = project.getAuthentificationEntity().getCompany() != null
						? project.getAuthentificationEntity().getCompany() + " - "
						: "";
				String user = company + project.getAuthentificationEntity().getFirstName() + " "
						+ project.getAuthentificationEntity().getLastName();
				String delayBy = null;
				if (project.getArchiveStatus() != null && project.getArchiveStatus().equals("delayed")) {
					delayBy = project.getArchive() != null && project.getArchive().getDelayBy() != null
							? project.getArchive().getDelayBy().getFirstName() + " "
									+ project.getArchive().getDelayBy().getLastName()
							: null;
				}
				return new DelayArchiveRequest(id, projectDetailsUtils.getProjectName(project), user,
						project.getUpdateDate(), project.getArchiveStatus(), delayBy);
			}
			return new DelayArchiveRequest();
		} catch (Exception e) {
			e.printStackTrace();
			return new DelayArchiveRequest();
		}
	}

	public boolean delayArchive(UtilsModel model) {
		try {
			PermitEntity project = permitRepo.findById(model.getId()).orElse(null);
			if (project != null) {
				project.setArchiveStatus("delayed");
				project.getArchive().setDelayDate(new Date());
				project.getArchive().setDelayBy(userRepo.findById(model.getUserId()).orElse(null));
				archiveLogRepo.save(project.getArchive());
				permitRepo.save(project);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Scheduled(cron = "0 30 1 * * *", zone = "America/Los_Angeles")
	public void archiveProject() {
		List<UtilsModel> users = userSettingRepo.findUserByArchiveAllowed();
		String googleDrivePath = pathRepo.findGoogleDriveFilePath();
		String filePath = pathRepo.findFilePath();
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		for (UtilsModel u : users) {
			Date date = Date.from(ZonedDateTime.now().minusMonths(u.getNumber()).toInstant());
			List<PermitEntity> projects = permitRepo
					.findByAuthentificationEntityIdAndUpdateDateBeforeAndArchiveStatusAndArchiveArchiveDateBefore(
							u.getId(), date, "waiting", new Date());
			String ownerFolderName = projects.isEmpty() ? null
					: googleDriveFolder.getfolderName(projects.get(0).getAuthentificationEntity());
			for (PermitEntity p : projects) {
				try {
					String projectName = projectUtils.getProjectName(p).trim();
					String folderPath = googleDrivePath + ownerFolderName + "/" + projectName + "/Portal Archive - "
							+ df.format(new Date());
					if (!new File(folderPath).exists()) {
						Files.createDirectories(Paths.get(folderPath));
					}
//					Copy Plan set file
					if (Boolean.TRUE.equals(p.isSubmitted())
							&& new File(filePath + "Rapport/SampleResult" + p.getId() + ".pdf").exists()) {
						copyFile(filePath + "Rapport/SampleResult" + p.getId() + ".pdf",
								folderPath + "/Raw PDF Plan Set.pdf");
						deleteFolder(new File(filePath + "Rapport/SampleResult" + p.getId() + ".pdf"));
					}
//					Copy Final Drafting Pkg
					if (Boolean.TRUE.equals(p.isSubmitted())
							&& new File(filePath + "package/" + projectName + "-DrafterPackage.rar").exists()) {
						copyFile(filePath + "package/" + projectName + "-DrafterPackage.rar",
								folderPath + "/" + projectName + ".rar");
						deleteFolder(new File(filePath + "package/" + projectName + "-DrafterPackage.rar"));
					}
//					Copy Export XLS
					if (new File(filePath + p.getId() + "/" + p.getId() + ".xls").exists()) {
						copyFile(filePath + p.getId() + "/" + p.getId() + ".xls",
								folderPath + "/" + projectName + ".xls");
					} else {
						exportService.generateProjectScv(p.getId(), u.getId());
						copyFile(filePath + p.getId() + "/" + p.getId() + ".xls",
								folderPath + "/" + projectName + ".xls");
					}
//					Copy Other Files
					List<String> folders = Arrays.asList("customFiles", "EssSpecificDetails", "FloridaRafterNotes",
							"Not Allowed Racking Notes", "permitFiles", "planset", "roofNote");
					for (String f : folders) {
						if (new File(filePath + p.getId() + "/" + f).exists()) {
							final File folder = new File(filePath + p.getId() + "/" + f);
							Files.createDirectories(Paths.get(folderPath + "/Files"));
							listFilesForFolder(folder, folderPath + "/Files");
						}
					}
					TimeUnit.SECONDS.sleep(15);
					deleteFolder(new File(filePath + p.getId()));
					String plansetFileUrl = createFileId(folderPath + ":user.drive.id");
					p.setArchiveLink(plansetFileUrl);
					p.getArchive().setArchiveDate(new Date());
					p.setArchiveStatus("archived");
					permitRepo.save(p);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		archiveid();
	}

	public void deleteFolder(final File folder) {
	    if(folder.isDirectory()) {
	      File[] files = folder.listFiles();
	      if(files != null) {
	        for(File file : files) {
	        	deleteFolder(file);
	        }
	      }
	    }
	    if(folder.delete()) {}
	}

	public void listFilesForFolder(final File folder, String folderPath) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, folderPath);
			} else {
				copyFile(folder.getPath() + "/" + fileEntry.getName(), folderPath + "/" + fileEntry.getName());
			}
		}
	}

	private void copyFile(String src, String dest) {
		try {
			Path srcFile = Paths.get(src);
			Path destFile = Paths.get(dest);
			com.google.common.io.Files.copy(srcFile.toFile(), destFile.toFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void archiveid() {
		Long time = new Date().getTime();
		Date date = new Date(time - time % (24 * 60 * 60 * 1000));
		List<PermitEntity> projects = permitRepo
				.findByArchiveStatusAndArchiveArchiveDateAfter("archived",date);
		String googleDrivePath = pathRepo.findGoogleDriveFilePath();
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		for (PermitEntity p : projects) {
			try {
				String ownerFolderName = projects.isEmpty() ? null
						: googleDriveFolder.getfolderName(p.getAuthentificationEntity());
				String projectName = projectUtils.getProjectName(p).trim();
				String folderPath = googleDrivePath + ownerFolderName + "/" + projectName + "/Portal Archive - "+ df.format(new Date());
				if (new File(folderPath).exists()) {
					Files.createDirectories(Paths.get(folderPath));
					String plansetFileUrl = createFileId(folderPath + ":user.drive.id");
					p.setArchiveLink(plansetFileUrl);
					permitRepo.save(p);
				}else {
					System.out.println(folderPath+" == Not found");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

			}
	public String createFileId(String drivePath) {

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

			while (((inputLine = in.readLine()) != null)
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
