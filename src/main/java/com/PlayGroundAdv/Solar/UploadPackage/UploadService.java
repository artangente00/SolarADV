package com.PlayGroundAdv.Solar.UploadPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.AdditionalInfoFiles;
import com.PlayGroundAdv.Solar.entity.BOSFiles;
import com.PlayGroundAdv.Solar.entity.NoteSketchFiles;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitPlansetUploadEntity;
import com.PlayGroundAdv.Solar.entity.UtilityBillFiles;
import com.PlayGroundAdv.Solar.entity.projects.ProjectCustomFiles;
import com.PlayGroundAdv.Solar.entity.projects.ProjectFiles;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.PermitFileUploadResult;
import com.PlayGroundAdv.Solar.model.PermitPlansetUploadResult;
import com.PlayGroundAdv.Solar.repositories.AdditionalInfoFilesRepository;
import com.PlayGroundAdv.Solar.repositories.BOSFilesRepository;
import com.PlayGroundAdv.Solar.repositories.NoteSketchFilesRepository;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.UtilityBillFilesRepository;
import com.PlayGroundAdv.Solar.repositories.project.ProjectCustomFilesRepository;
import com.PlayGroundAdv.Solar.repositories.project.ProjectFilesRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PermitPlansetUploadRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserCutomUploadRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.user_management.GoogleDriveFolder;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Component
@Service
public class UploadService {

	final CheckValueTypesService checkValueTypesService;
	final PathRepository pathRepo;
	final UserSettingRepository userSettingRepo;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	final PermitRepository permitRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoRepo;
	final AdditionalInfoFilesRepository additionalInfoFilesRepo;
	final UtilityBillFilesRepository utilityBillFilesRepo;
	final BOSFilesRepository bosFilesRepo;
	final NoteSketchFilesRepository noteSketchFilesRepo;
	final PermitPlansetUploadRepository permitPlansetUploadRepo;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	final ProjectFilesRepository projectFilesRepo;
	final GoogleDriveFolder googleDriveFolder;
	final UserCutomUploadRepository cutomUploadRepo;
	final ProjectCustomFilesRepository customFilesRepo;
	
	public UploadService(CheckValueTypesService checkValueTypesService, PathRepository pathRepo,
			UserSettingRepository userSettingRepo, PermitDrafterDataRepository permitDrafterDataRepo,
			PermitRepository permitRepo, PermitProjectSiteInfoRepository permitProjectSiteInfoRepo,
			AdditionalInfoFilesRepository additionalInfoFilesRepo, UtilityBillFilesRepository utilityBillFilesRepo,
			BOSFilesRepository bosFilesRepo, NoteSketchFilesRepository noteSketchFilesRepo, 
			PermitPlansetUploadRepository permitPlansetUploadRepo, PermitEnergyBatterySystemRepository energyBatterySystemRepo,
			ProjectFilesRepository projectFilesRepo,GoogleDriveFolder googleDriveFolder,
			UserCutomUploadRepository cutomUploadRepo, ProjectCustomFilesRepository customFilesRepo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.pathRepo = pathRepo;
		this.userSettingRepo = userSettingRepo;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
		this.permitRepo = permitRepo;
		this.permitProjectSiteInfoRepo = permitProjectSiteInfoRepo;
		this.additionalInfoFilesRepo = additionalInfoFilesRepo;
		this.utilityBillFilesRepo = utilityBillFilesRepo;
		this.bosFilesRepo = bosFilesRepo;
		this.noteSketchFilesRepo = noteSketchFilesRepo;
		this.permitPlansetUploadRepo = permitPlansetUploadRepo;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
		this.projectFilesRepo = projectFilesRepo;
		this.googleDriveFolder = googleDriveFolder;
		this.cutomUploadRepo = cutomUploadRepo;
		this.customFilesRepo = customFilesRepo;
	}

	public String uploadDrafterSheet(MultipartFile data, Long idPermit, String folderName,
			PermitEntity project) {
		String path = pathRepo.findFilePath();
		String count = "0";

		// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
		// name
		String ownerFolderName = googleDriveFolder.getfolderName(project.getAuthentificationEntity());
		try {

			PermitDrafterDataEntity permitDrafterDataEntity = permitDrafterDataRepo.findByPermitEntityId(idPermit);
			permitDrafterDataEntity.setParcelMapName("PM-" + data.getOriginalFilename());
			permitDrafterDataRepo.save(permitDrafterDataEntity);
			
			File pm = new File(path + idPermit + "/drafterfiles/PM-" + data.getName());
			if (checkValueTypesService.NotEquals(pm, null) && pm.getName().equals("PM-" + data.getOriginalFilename()))
				pm.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			byte[] bytes = data.getBytes();
			Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/PM-" + data.getOriginalFilename());
			Files.write(pathUpl, bytes);

			PDDocument doc = PDDocument
					.load(new File(path + idPermit + "/drafterfiles/PM-" + data.getOriginalFilename()));
			count = doc.getNumberOfPages() + "";
			String googleDrivePath = pathRepo.findGoogleDriveFilePath();
			// A.B 08-26: CR-2847 Save File to google drive
			if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter").exists()) {
				Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
						+ "/Drafting/To Drafter/Drafter Sheets-" + data.getOriginalFilename());
				com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());

			}

		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return count;
	}

	public String putDrafterFileSpring(MultipartFile data, String nameFile, Long idPermit,
			PermitEntity project) throws IOException, SQLException {

		PermitDrafterDataEntity permitDrafterDataEntity = new PermitDrafterDataEntity();
		String ownerFolderName = "";
		String folderName =  "";
		if(checkValueTypesService.NotEquals(nameFile, "RFI"))	{
			permitDrafterDataEntity= permitDrafterDataRepo.findByPermitEntityId(idPermit);
			
			if (permitDrafterDataEntity == null)
				return "ADVPermit unfound try again  ";
			
			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			ownerFolderName = googleDriveFolder.getfolderName(project.getAuthentificationEntity());
			folderName = googleDriveFolder.getProjectName(project);
		}
		
										
		String path = pathRepo.findFilePath();
		String googleDrivePath = pathRepo.findGoogleDriveFilePath();

		switch (nameFile) {
		case "RFI":
			try {
				String filename = data.getOriginalFilename().split("-")[0];
				File pm = new File(path + "RFI/RFI-" + filename);
				if (checkValueTypesService.NotEquals(pm, null) && "RFI-" + filename == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}
			permitDrafterDataEntity.setNameRFI("RFI-" + data.getName());
			if (!new File(path + "RFI/").exists()) {
				new File(path + "RFI/").mkdir();
			}
			try {
				byte[] bytes = data.getBytes();
				String filename = data.getOriginalFilename().split("-")[0];
				String extension = FilenameUtils.getExtension(data.getOriginalFilename());
				Path pathUpl = Paths.get(path + "RFI/RFI-" + filename + "." + extension);
				Files.write(pathUpl, bytes);
			} catch (Exception e) {
				e.printStackTrace();

			}
			break;
		case "AutoCad file":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/AUF-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "AUF-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}
			permitDrafterDataEntity.setAutocadFileName("AUF-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/AUF-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);
				// A.B 08-26: CR-2847 Save File to google drive
				if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter").exists()) {
					Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Drafting/To Drafter/AutoCAD-" + data.getOriginalFilename());
					com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
			break;
		case "Google Earth":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/GM-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "GM-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}
			permitDrafterDataEntity.setGoogleMapImageName("GM-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/GM-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);
				// A.B 08-26: CR-2847 Save File to google drive
				if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter").exists()) {
					Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Drafting/To Drafter/Google Earth-" + data.getOriginalFilename());
					com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());

				}

			} catch (Exception e) {
				e.printStackTrace();

			}
			break;
		case "NearMap Image":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/NM-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "NM-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}
			permitDrafterDataEntity.setNearMapImageName("NM-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/NM-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);
				// A.B 08-26: CR-2847 Save File to google drive
				
				if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter").exists()) {
					Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Drafting/To Drafter/NearMap-" + data.getOriginalFilename());
					com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
			break;
		case "BOS":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/BOS-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "BOS-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}

			permitDrafterDataEntity.setBosImageName("BOS-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/BOS-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);

			} catch (Exception e) {
				e.printStackTrace();

			}
			break;
		case "Recent Utility Bill Pdf":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/UB-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "UB-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}
			permitDrafterDataEntity.setUtilityBillPdfName("UB-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/UB-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);
			} catch (Exception e) {
				e.printStackTrace();

			}
			break;
		case "Load Justification":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/LJ-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "LJ-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}
			permitDrafterDataEntity.setLoadJustificationName("LJ-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/LJ-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);
			} catch (Exception e) {
				e.printStackTrace();

			}
			break;

		case "Lots and Addresses for Subdivision":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/LS-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "LS-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}
			permitDrafterDataEntity.setAdresseSubdivisionFileName("LS-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/LS-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);
			} catch (Exception e) {
				e.printStackTrace();

			}
			break;
		case "Switchgear Cutsheets Detailing Bussing":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/SC-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "SC-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}
			permitDrafterDataEntity.setSwitchgearCutsheetsFileName("SC-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/SC-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);
			} catch (Exception e) {
				e.printStackTrace();

			}
			break;
		case "Proposal for Point of Interconnection":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/PI-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "PI-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {

			}
			permitDrafterDataEntity.setProporsalInterconnectionFileName("PI-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/PI-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);
			} catch (Exception e) {
				e.printStackTrace();

			}
			break;

		case "Existing System Info":
			try {
				File pm = new File(path + idPermit + "/drafterfiles/ESI-" + data.getOriginalFilename());
				if (checkValueTypesService.NotEquals(pm, null) && "ESI-" + data.getOriginalFilename() == pm.getName())
					pm.delete();
			} catch (Exception e) {
			}
			permitDrafterDataEntity.setExistingSystemeFileName("ESI-" + data.getOriginalFilename());
			try {
				byte[] bytes = data.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/drafterfiles/ESI-" + data.getOriginalFilename());
				Files.write(pathUpl, bytes);
			} catch (Exception e) {
				e.printStackTrace();

			}
			break;

		default:
			return "file Name Unfound please try again";

		}
		permitDrafterDataRepo.save(permitDrafterDataEntity);
		return "succes";

	}

	public String putRFIFile(MultipartFile data) throws IOException {

	PermitDrafterDataEntity permitDrafterDataEntity = new PermitDrafterDataEntity();
	String path = pathRepo.findFilePath();
		try {
			String filename = data.getOriginalFilename().split("-")[0];
			File pm = new File(path + "RFI/RFI-" + filename);
			if (checkValueTypesService.NotEquals(pm, null) && "RFI-" + filename == pm.getName())
				pm.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		permitDrafterDataEntity.setNameRFI("RFI-" + data.getName());
		if (!new File(path + "RFI/").exists()) {
			new File(path + "RFI/").mkdir();
		}
		try {
			byte[] bytes = data.getBytes();
			String filename = data.getOriginalFilename().split("-")[0];
			String extension = FilenameUtils.getExtension(data.getOriginalFilename());
			Path pathUpl = Paths.get(path + "RFI/RFI-" + filename + "." + extension);
			Files.write(pathUpl, bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	permitDrafterDataRepo.save(permitDrafterDataEntity);
	return "success";

	}

	public List<PermitFileUploadResult> addProjectsFile(MultipartFile[] uploadedFiles, String fileId, Long permitId) {

		PermitFileUploadResult permitFileUploadResult = new PermitFileUploadResult();
		List<PermitFileUploadResult> permitFileUploadResultList = new ArrayList<>();

		try {

			PermitEntity permit = permitRepo.findById(permitId).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +permitId));

			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(permit.getAuthentificationEntity());
			String folderName = googleDriveFolder.getProjectName(permit);
			Integer version = permit.getRRVersion();
			String path = pathRepo.findFilePath();
			if (!new File(path + permitId + "/").exists()) {
				new File(path + permitId + "/").mkdir();
			}
			if (!new File(path + permitId + "/permitFiles/").exists()) {
				new File(path + permitId + "/permitFiles/").mkdir();
			}
			for (MultipartFile file : uploadedFiles) {

				// String actualFileName = file.getOriginalFilename();
				String baseName = file.getOriginalFilename();
				String actualFileName;

				int occurPoint = baseName.split("\\.").length;
				if (occurPoint > 2) {
					String name = baseName.split("\\.")[0];
					for (int i = 1; i < occurPoint - 1; i++) {
						name = name + "." + baseName.split("\\.")[i];
					}
					if (version == 0) {
						actualFileName = name + "-Orig." + baseName.split("\\.")[occurPoint - 1];
					} else {
						actualFileName = name + "-Rev" + version + "." + baseName.split("\\.")[occurPoint - 1];
					}
				} else {
					if (version == 0) {
						actualFileName = baseName.split("\\.")[0] + "-Orig." + baseName.split("\\.")[1];
					} else {
						actualFileName = baseName.split("\\.")[0] + "-Rev" + version + "." + baseName.split("\\.")[1];
					}
				}

				try {
					File[] f = new File(path + permitId + "/permitFiles/").listFiles();
					if (f != null && f.length > 0) {
						for (File fs : f) {
							if (fs.getName().contains("PF-" + fileId + "-"))
								fs.delete();
						}
					}
				} catch (Exception e) {
					permitFileUploadResult.setPersistFile("error1");
					permitFileUploadResult.setMsgFile("It seems that there is a technical problem, please try again.");
					permitFileUploadResultList.add(permitFileUploadResult);
					return permitFileUploadResultList;
				}
				permitFileUploadResult.setExistFile("true");
				String googleDrivePath = pathRepo.findGoogleDriveFilePath();
				ProjectFiles files = projectFilesRepo.findByProjectId(permitId);
				String fileName = "";
				switch (fileId) {
				case "1": {
					files.setNameFile1("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName + "/Interconnection/Utility Bill-"
							+ file.getOriginalFilename();
					break;
				}
				case "2": {
					files.setNameFile2("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName + "/Interconnection/Final Insp-"
							+ file.getOriginalFilename();
					break;
				}
				case "3": {
					files.setNameFile3("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Drafting/To Drafter/MSP Derate Calcs-" + file.getOriginalFilename();
					break;
				}
				case "4": {
					files.setNameFile4("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Drafting/To Drafter/Layout File-" + file.getOriginalFilename();
					break;
				}
				case "5": {
					files.setNameFile5("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Drafting/To Drafter/Non Roof Layout File-" + file.getOriginalFilename();
					break;
				}
				case "6": {
					files.setNameFile6("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter/Add File-"
							+ file.getOriginalFilename();
					break;
				}
				case "7": {
					files.setNameFile7("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter/Add File2-"
							+ file.getOriginalFilename();
					break;
				}
				case "8": {
					files.setNameFile8("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter/Add File3-"
							+ file.getOriginalFilename();
					break;
				}
				case "9": {
					files.setNameFile9("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter/Add File4-"
							+ file.getOriginalFilename();
					break;
				}
				case "10": {
					files.setNameFile10("PF-" + fileId + "-" + actualFileName);
					fileName = googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter/Add File5-"
							+ file.getOriginalFilename();
					break;
				}

				default:
					break;
				}
				byte[] bytes = file.getBytes();
				Path pathUpl = Paths
						.get(path + permitId + "/permitFiles/PF-" + fileId + "-" + file.getOriginalFilename());
				Files.write(pathUpl, bytes);
				permitRepo.save(permit);
				permitFileUploadResult.setPersistFile("succes");
				permitFileUploadResult.setMsgFile("PF-" + fileId + "-" + actualFileName);
				permitFileUploadResultList.add(permitFileUploadResult);

				// A.B 08-26: CR-2847 Save File to google drive
				if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter").exists()) {
					Path expDestFile = Paths.get(fileName);
					com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());
				}

			}
			return permitFileUploadResultList;

		} catch (Exception e) {

			e.printStackTrace();
			permitFileUploadResult.setPersistFile("error2");
			permitFileUploadResult.setMsgFile("It seems that there is a technical problem, please try again.");
			permitFileUploadResultList.add(permitFileUploadResult);
			return permitFileUploadResultList;

		}
	}

	public String uploadPlansetPermit(MultipartFile uploadedFiles, Long idPermit, String release) {
		try {
			File files;
			PermitEntity permit = permitRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));
			Date actuelle = new Date();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String dat = dateFormat.format(actuelle);
			String path = pathRepo.findFilePath();
			files = new File(path + "/Rapport/SampleResult" + idPermit + ".pdf");
			Boolean del = files.delete();
			if (del == true) {
				if (permit.getPlansetVersion() == null || (permit.getPlansetVersion() != null
						&& (permit.getPlansetVersion() == 0 || permit.getPlansetVersion() == 1))) {
					permit.setPlansetVersion(2);
				} else {
					if (permit.getPlansetVersion() == 2 && permit.getReleasev2() != null
							&& checkValueTypesService.NotEquals(permit.getReleasev2(), "")) {
						permit.setPlansetVersion(permit.getPlansetVersion() + 1);
					} else if (permit.getPlansetVersion() == 3 && permit.getReleasev3() != null
							&& checkValueTypesService.NotEquals(permit.getReleasev3(), "")) {
						permit.setPlansetVersion(permit.getPlansetVersion() + 1);
					} else if (permit.getPlansetVersion() > 3) {
						permit.setPlansetVersion(permit.getPlansetVersion() + 1);
					}

				}
				try {
					// files = new File(path + "/Rapport/SampleResult" + idPermit+".pdf");

					File convFile = new File(path + "Rapport/SampleResult" + idPermit + ".pdf");
					uploadedFiles.transferTo(convFile);

				} catch (Exception e) {
					e.printStackTrace();

				}

				if (checkValueTypesService.Equals(permit.getPlansetVersion(), 2)) {
					permit.setUpdatedDatev2(dat);
					permit.setReleasev2(release);
				}

				if (checkValueTypesService.Equals(permit.getPlansetVersion(), 3)) {
					permit.setUpdatedDatev3(dat);
					permit.setReleasev3(release);
				}

				permitRepo.save(permit);
			}

		} catch (Exception e) {
		}
		return "suceess";

	}

	public PermitPlansetUploadResult addPlansetsFile(MultipartFile file, String idFile, Long idPermit, String folderName) throws IOException {

		PermitPlansetUploadResult permitPlansetUploadResult = new PermitPlansetUploadResult();

		String path = pathRepo.findFilePath();
		File[] plansetFiles = new File(path + idPermit + "/planset/").listFiles();
		if (plansetFiles != null && plansetFiles.length > 0) {
			for (int i = 0; i < plansetFiles.length; i++) {
				if (checkValueTypesService.Equals(plansetFiles[i].getName().substring(5), file.getOriginalFilename()))
					if (!plansetFiles[i].delete())
						throw new IOException();
			}
		}
		if (!new File(path + idPermit + "/").exists()) {
			new File(path + idPermit + "/").mkdir();
		}

		if (!new File(path + idPermit + "/planset/").exists()) {
			new File(path + idPermit + "/planset/").mkdir();
		}

		File orgineFile = null;
		File addedFile = null;

		try {

			PermitEntity project = permitRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));
			Integer version = project.getRRVersion();
			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(project.getAuthentificationEntity());

			PermitPlansetUploadEntity permit = permitPlansetUploadRepo.findByPermitEntityId(idPermit);

			String baseName = file.getOriginalFilename();
			String actualFileName;

			int lastIndexOf = baseName.lastIndexOf(".");
			String substring1 = baseName.substring(0, lastIndexOf);
			String substring2 = baseName.substring(lastIndexOf + 1, baseName.length());

			if (version == 0) {
				actualFileName = substring1 + "-Orig." + substring2;
			} else {
				actualFileName = substring1 + "-Rev." + substring2;
			}

			File[] f = new File(path + idPermit + "/planset/").listFiles();
			String googleDrivePath = pathRepo.findGoogleDriveFilePath();
			switch (idFile) {
			case "1":
				if (f != null && f.length > 0) {
					for (File fs : f) {
						if (fs.getName().contains("PL-1-"))
							fs.delete();
					}
				}
				if (permit.getFile_1() == null) {

					permitPlansetUploadResult.setExistFile("false");
				} else {
					permitPlansetUploadResult.setExistFile("true");
				}
				byte[] bytes = file.getBytes();
				Path pathUpl = Paths.get(path + idPermit + "/planset/PL-1-" + actualFileName);
				Files.write(pathUpl, bytes);
				permit.setNameFile_1("PL-1-" + actualFileName);
				permitPlansetUploadRepo.save(permit);
				permitPlansetUploadResult.setPersistFile("succes");
				permitPlansetUploadResult.setMsgFile("PL-1-" + actualFileName);

				// A.B 08-26: CR-2847 Save File to google drive IF it doesn't exist
				if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter").exists()) {
					orgineFile = new File(googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Plan Set Drafts/Plan set Upload 1-" + file.getOriginalFilename());
					addedFile = new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Plan Set Drafts/"
							+ file.getOriginalFilename());
					if (!orgineFile.exists() && !addedFile.exists()) {
						Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
								+ "/Plan Set Drafts/Plan set Upload 1-" + file.getOriginalFilename());
						com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());
					}
				}

				return permitPlansetUploadResult;

			case "2":
				if (f != null && f.length > 0) {
					for (File fs : f) {
						if (fs.getName().contains("PL-2-"))
							fs.delete();
					}
				}
				if (permit.getFile_2() == null) {

					permitPlansetUploadResult.setExistFile("false");
				} else {
					permitPlansetUploadResult.setExistFile("true");
				}
				permit.setNameFile_2("PL-2-" + actualFileName);
				byte[] bytes2 = file.getBytes();
				Path pathUp2 = Paths.get(path + idPermit + "/planset/PL-2-" + actualFileName);
				Files.write(pathUp2, bytes2);
				permitPlansetUploadRepo.save(permit);
				permitPlansetUploadResult.setPersistFile("succes");
				permitPlansetUploadResult.setMsgFile("PL-2-" + actualFileName);

				// A.B 08-26: CR-2847 Save File to google drive IF it doesn't exist
				if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Plan Set Drafts").exists()) {
					orgineFile = new File(googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Plan Set Drafts/Plan set Upload 2-" + file.getOriginalFilename());
					addedFile = new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Plan Set Drafts/"
							+ file.getOriginalFilename());
					if (!orgineFile.exists() && !addedFile.exists()) {
						Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
								+ "/Plan Set Drafts/Plan set Upload 2-" + file.getOriginalFilename());
						com.google.common.io.Files.copy(pathUp2.toFile(), expDestFile.toFile());
					}
				}

				return permitPlansetUploadResult;

			case "3":
				if (f != null && f.length > 0) {
					for (File fs : f) {
						if (fs.getName().contains("PL-3-"))
							fs.delete();
					}
				}
				if (permit.getFile_3() == null) {

					permitPlansetUploadResult.setExistFile("false");
				} else {
					permitPlansetUploadResult.setExistFile("true");
				}
				permit.setNameFile_3("PL-3-" + actualFileName);
				byte[] bytes3 = file.getBytes();
				Path pathUp3 = Paths.get(path + idPermit + "/planset/PL-3-" + actualFileName);
				Files.write(pathUp3, bytes3);
				permitPlansetUploadRepo.save(permit);
				permitPlansetUploadResult.setPersistFile("succes");
				permitPlansetUploadResult.setMsgFile("PL-3-" + actualFileName);

				// A.B 08-26: CR-2847 Save File to google drive IF it doesn't exist
				if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Plan Set Drafts").exists()) {
					orgineFile = new File(googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Plan Set Drafts/Plan set Upload 3-" + file.getOriginalFilename());
					addedFile = new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Plan Set Drafts/"
							+ file.getOriginalFilename());
					if (!orgineFile.exists() && !addedFile.exists()) {
						Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
								+ "/Plan Set Drafts/Plan set Upload 3-" + file.getOriginalFilename());
						com.google.common.io.Files.copy(pathUp3.toFile(), expDestFile.toFile());
					}
				}

				return permitPlansetUploadResult;

			case "4":
				if (f != null && f.length > 0) {
					for (File fs : f) {
						if (fs.getName().contains("PL-4-"))
							fs.delete();
					}
				}
				if (permit.getFile_4() == null) {

					permitPlansetUploadResult.setExistFile("false");
				} else {
					permitPlansetUploadResult.setExistFile("true");
				}
				permit.setNameFile_4("PL-4-" + actualFileName);
				byte[] bytes4 = file.getBytes();
				Path pathUp4 = Paths.get(path + idPermit + "/planset/PL-4-" + actualFileName);
				Files.write(pathUp4, bytes4);
				permitPlansetUploadRepo.save(permit);
				permitPlansetUploadResult.setPersistFile("succes");
				permitPlansetUploadResult.setMsgFile("PL-4-" + actualFileName);

				// A.B 08-26: CR-2847 Save File to google drive IF it doesn't exist
				if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Plan Set Drafts").exists()) {
					orgineFile = new File(googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Plan Set Drafts/Plan set Upload 4-" + file.getOriginalFilename());
					addedFile = new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Plan Set Drafts/"
							+ file.getOriginalFilename());
					if (!orgineFile.exists() && !addedFile.exists()) {
						Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
								+ "/Plan Set Drafts/Plan set Upload 4-" + file.getOriginalFilename());
						com.google.common.io.Files.copy(pathUp4.toFile(), expDestFile.toFile());
					}
				}

				return permitPlansetUploadResult;

			case "5":
				if (f != null && f.length > 0) {
					for (File fs : f) {
						if (fs.getName().contains("PL-5-"))
							fs.delete();
					}
				}
				if (permit.getFile_5() == null) {

					permitPlansetUploadResult.setExistFile("false");
				} else {
					permitPlansetUploadResult.setExistFile("true");
				}
				permit.setNameFile_5("PL-5-" + actualFileName);
				byte[] bytes5 = file.getBytes();
				Path pathUp5 = Paths.get(path + idPermit + "/planset/PL-5-" + actualFileName);
				Files.write(pathUp5, bytes5);
				permitPlansetUploadRepo.save(permit);
				permitPlansetUploadResult.setPersistFile("succes");
				permitPlansetUploadResult.setMsgFile("PL-5-" + actualFileName);

				// A.B 08-26: CR-2847 Save File to google drive IF it doesn't exist
				if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Plan Set Drafts").exists()) {
					orgineFile = new File(googleDrivePath + ownerFolderName + "/" + folderName
							+ "/Plan Set Drafts/Plan set Upload 5-" + file.getOriginalFilename());
					addedFile = new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Plan Set Drafts/"
							+ file.getOriginalFilename());
					if (!orgineFile.exists() && !addedFile.exists()) {
						Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
								+ "/Plan Set Drafts/Plan set Upload 5-" + file.getOriginalFilename());
						com.google.common.io.Files.copy(pathUp5.toFile(), expDestFile.toFile());
					}
				}

				return permitPlansetUploadResult;

			default:
				permitPlansetUploadResult.setPersistFile("error");
				permitPlansetUploadResult.setMsgFile("It seems that there is a technical problem, please try again.");
				return permitPlansetUploadResult;
			}
		} catch (Exception e) {

			e.printStackTrace();
			permitPlansetUploadResult.setPersistFile("error");
			permitPlansetUploadResult.setMsgFile("It seems that there is a technical problem, please try again.");
			return permitPlansetUploadResult;

		}

	}

	public String putAdditionalInfoFiles(MultipartFile[] uploadedFiles, Long permitId, String folderName) {

		try {

			PermitEntity permit = permitRepo.findById(permitId).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +permitId));
			String googleDrivePath = pathRepo.findGoogleDriveFilePath();
			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(permit.getAuthentificationEntity());

			String path = pathRepo.findFilePath();
			if (!new File(path + permitId + "/").exists()) {
				new File(path + permitId + "/").mkdir();
			}
			if (!new File(path + permitId + "/additionalInfo/").exists()) {
				new File(path + permitId + "/additionalInfo/").mkdir();
			}
			for (MultipartFile file : uploadedFiles) {
				int i;
				File[] files;
				files = new File(path + permitId + "/additionalInfo/").listFiles();
				if (files != null && files.length > 0)
					i = files.length;
				else
					i = 0;
				AdditionalInfoFiles addInfoFiles = new AdditionalInfoFiles();
				addInfoFiles.setPermitEntity(permit);
				addInfoFiles.setFileName("AF-" + i + "-" + file.getOriginalFilename());
				addInfoFiles.setFileType(file.getContentType());
				addInfoFiles.setFilePath(path + permitId + "/additionalInfo/");
				additionalInfoFilesRepo.save(addInfoFiles);
				try {
					byte[] bytes = file.getBytes();
					Path pathUpl = Paths
							.get(path + permitId + "/additionalInfo/AF-" + i + "-" + file.getOriginalFilename());
					Files.write(pathUpl, bytes);

					// A.B 08-26: CR-2847 Save File to google drive
					if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter")
							.exists()) {
						Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
								+ "/Drafting/To Drafter/Additional info-" + i + "-" + file.getOriginalFilename());
						com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());

					}

				} catch (Exception e) {
					e.printStackTrace();

				}

				permitRepo.save(permit);

			}
			return "success";

		} catch (Exception e) {

			e.printStackTrace();
			return "error";
		}

	}
	
	public String uploadCustomFile(MultipartFile[] uploadedFiles, Long permitId, Long folderName) {

		try {

			PermitEntity permit = permitRepo.findById(permitId).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +permitId));
			String path = pathRepo.findFilePath();
			if (!new File(path + permitId + "/").exists()) {
				new File(path + permitId + "/").mkdir();
			}
			if (!new File(path + permitId + "/customFiles/").exists()) {
				new File(path + permitId + "/customFiles/").mkdir();
			}
			if (!new File(path + permitId + "/customFiles/"+ folderName +"/").exists()) {
				new File(path + permitId + "/customFiles/"+ folderName +"/").mkdir();
			}
			ProjectCustomFiles upload = customFilesRepo.findByProjectIdAndCustomUploadId(permitId, folderName);
			if(upload == null) {
				upload = new ProjectCustomFiles(permit, cutomUploadRepo.findById(folderName).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" +folderName))); 
			}
			for (MultipartFile file : uploadedFiles) {
				upload.getFiles().add(file.getOriginalFilename());
				byte[] bytes = file.getBytes();
				Path pathUpl = Paths.get(path + permitId + "/customFiles/" + folderName +"/" + file.getOriginalFilename());
				Files.write(pathUpl, bytes);
			}
			customFilesRepo.save(upload);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String postUtilityBillFiles(MultipartFile[] uploadedFiles, Long permitId, String folderName) {

		try {

			PermitEntity permit = permitRepo.findById(permitId).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +permitId));
			String googleDrivePath = pathRepo.findGoogleDriveFilePath();
			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(permit.getAuthentificationEntity());

			String path = pathRepo.findFilePath();
			if (!new File(path + permitId + "/").exists()) {
				new File(path + permitId + "/").mkdir();
			}
			if (!new File(path + permitId + "/drafterfiles/").exists()) {
				new File(path + permitId + "/drafterfiles/").mkdir();
			}

			if (!new File(path + permitId + "/drafterfiles/").exists()) {
				new File(path + permitId + "/drafterfiles/").mkdir();
			}
			for (MultipartFile file : uploadedFiles) {
				int i;
				File[] files;
				files = new File(path + permitId + "/drafterfiles/").listFiles();
				if (files != null && files.length > 0)
					i = files.length;
				else
					i = 0;
				UtilityBillFiles ubfile = new UtilityBillFiles();
				ubfile.setPermitEntity(permit);

				ubfile.setFileName("UB-" + i + "-" + file.getOriginalFilename());
				ubfile.setFileType(10);

				ubfile.setFilePath(path + permitId + "/drafterfiles/");
				try {
					byte[] bytes = file.getBytes();

					Path pathUpl = Paths
							.get(path + permitId + "/drafterfiles/UB-" + i + "-" + file.getOriginalFilename());
					Files.write(pathUpl, bytes);

					// A.B 08-26: CR-2847 Save File to google drive
					if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Interconnection").exists()) {
						Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
								+ "/Interconnection/Utility Bill-" + file.getOriginalFilename());
						com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				utilityBillFilesRepo.save(ubfile);

			}

			return "success";

		} catch (Exception e) {

			e.printStackTrace();
			return "error";
		}
	}

	public String putBOSFiles(MultipartFile[] uploadedFiles, Long permitId, String folderName) {

		try {
			PermitEntity permit = permitRepo.findById(permitId).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +permitId));
			String googleDrivePath = pathRepo.findGoogleDriveFilePath();
			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(permit.getAuthentificationEntity());

			String path = pathRepo.findFilePath();
			if (!new File(path + permitId + "/").exists()) {
				new File(path + permitId + "/").mkdir();
			}
			if (!new File(path + permitId + "/drafterfiles/").exists()) {
				new File(path + permitId + "/drafterfiles/").mkdir();
			}

			if (!new File(path + permitId + "/drafterfiles/").exists()) {
				new File(path + permitId + "/drafterfiles/").mkdir();
			}
			for (MultipartFile file : uploadedFiles) {
				int i;
				File[] files;
				files = new File(path + permitId + "/drafterfiles/").listFiles();
				if (files != null && files.length > 0)
					i = files.length;
				else
					i = 0;
				BOSFiles bosfile = new BOSFiles();
				bosfile.setPermitEntity(permit);

				bosfile.setFileName("BOS-" + i + "-" + file.getOriginalFilename());
				bosfile.setFileType(10);

				bosfile.setFilePath(path + permitId + "/drafterfiles/");
				try {
					byte[] bytes = file.getBytes();

					Path pathUpl = Paths
							.get(path + permitId + "/drafterfiles/BOS-" + i + "-" + file.getOriginalFilename());
					Files.write(pathUpl, bytes);

					// A.B 08-26: CR-2847 Save File to google drive
					if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter")
							.exists()) {
						Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
								+ "/Drafting/To Drafter/BOS-" + i + "-" + file.getOriginalFilename());
						com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());

					}

				} catch (Exception e) {
					e.printStackTrace();

				}

				bosFilesRepo.save(bosfile);

			}

			return "success";

		} catch (Exception e) {

			e.printStackTrace();
			return "error";
		}
	}

	public String putLayoutSketchFiles(MultipartFile[] uploadedFiles, Long permitId, String fileNumber,
			String folderName) {

		try {

			PermitEntity permit = permitRepo.findById(permitId).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +permitId));
			String googleDrivePath = pathRepo.findGoogleDriveFilePath();
			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(permit.getAuthentificationEntity());

			String path = pathRepo.findFilePath();
			if (!new File(path + permitId + "/").exists()) {
				new File(path + permitId + "/").mkdir();
			}
			if (!new File(path + permitId + "/additionalInfo/").exists()) {
				new File(path + permitId + "/additionalInfo/").mkdir();
			}
			for (MultipartFile file : uploadedFiles) {
				int i;
				File[] files;
				files = new File(path + permitId + "/sketch/").listFiles();
				if (files != null && files.length > 0)
					i = files.length;
				else
					i = 0;
				NoteSketchFiles noteSketchFiles = new NoteSketchFiles();
				noteSketchFiles.setPermitEntity(permit);
				if (checkValueTypesService.Equals(fileNumber, "One")) {
					noteSketchFiles.setFileName("SFOne-" + i + "-" + file.getOriginalFilename());
					noteSketchFiles.setFileType(10);
				} else if (checkValueTypesService.Equals(fileNumber, "Two")) {
					noteSketchFiles.setFileName("SFTwo-" + i + "-" + file.getOriginalFilename());
					noteSketchFiles.setFileType(20);
				}
				noteSketchFiles.setFilePath(path + permitId + "/sketch/");
				try {
					byte[] bytes = file.getBytes();
					if (checkValueTypesService.Equals(fileNumber, "One")) {
						Path pathUpl = Paths
								.get(path + permitId + "/sketch/SFOne-" + i + "-" + file.getOriginalFilename());
						Files.write(pathUpl, bytes);

						// A.B 08-26: CR-2847 Save File to google drive
						if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter")
								.exists()) {
							Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
									+ "/Drafting/To Drafter/layout & site Plan-" + i + "-"
									+ file.getOriginalFilename());
							com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());
						}

					} else if (checkValueTypesService.Equals(fileNumber, "Two")) {
						Path pathUpl = Paths
								.get(path + permitId + "/sketch/SFTwo-" + i + "-" + file.getOriginalFilename());
						Files.write(pathUpl, bytes);

						// A.B 08-26: CR-2847 Save File to google drive
						if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter")
								.exists()) {
							Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
									+ "/Drafting/To Drafter/Layout Add Info-" + i + "-" + file.getOriginalFilename());
							com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());

						}

					}

				} catch (Exception e) {
					e.printStackTrace();

				}

				noteSketchFilesRepo.save(noteSketchFiles);

			}

			return "success";

		} catch (Exception e) {

			e.printStackTrace();
			return "error";
		}

	}

	public String uploadConductorSizeNotes(MultipartFile[] uploadedFiles, Long permitId, String notes,
			String folderName, PermitEntity project) {

		try {

			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(project.getAuthentificationEntity());

			String path = pathRepo.findFilePath();
			if (!new File(path + permitId + "/").exists()) {
				new File(path + permitId + "/").mkdir();
			}
			if (!new File(path + permitId + "/ConductorSizeNotes/").exists()) {
				new File(path + permitId + "/ConductorSizeNotes/").mkdir();
			}
			for (MultipartFile file : uploadedFiles) {
				try {
					byte[] bytes = file.getBytes();
					Path pathUpl = Paths.get(path + permitId + "/ConductorSizeNotes/" + file.getOriginalFilename());
					Files.write(pathUpl, bytes);
					String googleDrivePath = pathRepo.findGoogleDriveFilePath();
					// A.B 08-26: CR-2847 Save File to google drive
					if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/To Drafter")
							.exists()) {
						Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
								+ "/Drafting/To Drafter/Conduit Size-" + file.getOriginalFilename());
						com.google.common.io.Files.copy(pathUpl.toFile(), expDestFile.toFile());

					}

				} catch (Exception e) {
					e.printStackTrace();

				}

			}
			permitProjectSiteInfoRepo.updatesubPanelConductorSize(notes, permitId);
			return "success";

		} catch (Exception e) {

			e.printStackTrace();
			return "error";
		}

	}
	public List<String> UploadEssSpecificDetails(MultipartFile[] uploadedFiles, Long permitId) {

		try {
			String path = pathRepo.findFilePath();
			if (!new File(path + permitId + "/").exists()) {
				new File(path + permitId + "/").mkdir();
			}
			if (!new File(path + permitId + "/EssSpecificDetails/").exists()) {
				new File(path + permitId + "/EssSpecificDetails/").mkdir();
			}
			List<String> fileNames = new ArrayList<>();
			for (MultipartFile file : uploadedFiles) {
				try {
					fileNames.add(file.getOriginalFilename());
					byte[] bytes = file.getBytes();
					Path pathUpl = Paths.get(path + permitId + "/EssSpecificDetails/" + file.getOriginalFilename());
					Files.write(pathUpl, bytes);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			PermitEnergyBatterySystem permitEnergyBatterySystem = energyBatterySystemRepo.findByProjectId(permitId);
			if(permitEnergyBatterySystem.getEssSpecificationDetails() == null) {
				permitEnergyBatterySystem.setEssSpecificationDetails(new ArrayList<>());
			}
			for (String f : fileNames) {
				permitEnergyBatterySystem.getEssSpecificationDetails().add(f);
			}
			energyBatterySystemRepo.save(permitEnergyBatterySystem);
			return fileNames;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public String uploadLogoAndSignature(MultipartFile file, String fileName, Long userID) throws Exception {
		String imgPath = pathRepo.findFilePath();
		try {
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(userID);
			if (!new File(imgPath + "img/").exists()) {
				new File(imgPath + "img/").mkdir();
			}
			switch (fileName) {
			case "Signature":

				if (checkValueTypesService.isStringNotEmpty(userSetting.getSignature())) {
					File sig = new File(imgPath + "img/signatures/" + userSetting.getSignature());
					if (sig.exists()) {
						sig.delete();
					}
				}
				if (!new File(imgPath + "img/signatures/").exists()) {
					new File(imgPath + "img/signatures/").mkdir();
				}
				byte[] bytesSig = file.getBytes();
				Path pathUpSig = Paths.get(
						imgPath + "img/signatures/" + userSetting.getUserId().getId() + file.getOriginalFilename());
				Files.write(pathUpSig, bytesSig);

				userSetting.setSignature(userSetting.getUserId().getId() + file.getOriginalFilename());
				userSetting.setSignatureConfirmed(true);
				userSettingRepo.save(userSetting);

				String ext2 = FilenameUtils.getExtension(
						imgPath + "img/signatures/" + userSetting.getUserId().getId() + file.getOriginalFilename());

				if (checkValueTypesService.Equals(ext2, "svg")) {
					if (!new File(imgPath + "img/signatures/Mapping/").exists()) {
						new File(imgPath + "img/signatures/Mapping/").mkdir();
					}
					svgTopng(
							imgPath + "img/signatures/Mapping/" + userSetting.getUserId().getId()
									+ file.getOriginalFilename(),
							imgPath + "img/signatures/" + userSetting.getUserId().getId() + file.getOriginalFilename());
				}
				break;
			case "Company Logo":

				if (checkValueTypesService.isStringNotEmpty(userSetting.getCompanyLogoName())) {
					File logo = new File(imgPath + "img/logos/" + userSetting.getCompanyLogoName());
					if (logo.exists()) {
						logo.delete();
					}
				}
				if (!new File(imgPath + "img/logos/").exists()) {
					new File(imgPath + "img/logos/").mkdir();
				}

				byte[] bytesLogo = file.getBytes();
				Path pathUpLogo = Paths
						.get(imgPath + "img/logos/" + userSetting.getUserId().getId() + file.getOriginalFilename());
				Files.write(pathUpLogo, bytesLogo);
				userSetting.setCompanyLogoName(userSetting.getUserId().getId() + file.getOriginalFilename());
				userSetting.setLogoConfirmed(true);
				userSettingRepo.save(userSetting);
				String extLogo = FilenameUtils.getExtension(
						imgPath + "img/logos/" + userSetting.getUserId().getId() + file.getOriginalFilename());
				if (checkValueTypesService.Equals(extLogo, "svg")) {
					if (!new File(imgPath + "img/logos/Mapping/").exists()) {
						new File(imgPath + "img/logos/Mapping/").mkdir();
					}
					svgTopng(
							imgPath + "img/logos/Mapping/" + userSetting.getUserId().getId()
									+ file.getOriginalFilename(),
							imgPath + "img/logos/" + userSetting.getUserId().getId() + file.getOriginalFilename());
				}
				break;
			default: {
				throw new FileAlreadyExistsException(file.getName());

			}

			}
			return "succes";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "error";
		}
	}

	private void svgTopng(String path, String svgPath) throws Exception {
		// Step -1: We read the input SVG document into Transcoder Input
		// We use Java NIO for this purpose
		String svg_URI_input = Paths.get(svgPath).toUri().toURL().toString();
		TranscoderInput input_svg_image = new TranscoderInput(svg_URI_input);
		// Step-2: Define OutputStream to PNG Image and attach to TranscoderOutput
		OutputStream png_ostream = new FileOutputStream(path);
		TranscoderOutput output_png_image = new TranscoderOutput(png_ostream);
		// Step-3: Create PNGTranscoder and define hints if required
		PNGTranscoder my_converter = new PNGTranscoder();
		// Step-4: Convert and Write output
		my_converter.transcode(input_svg_image, output_png_image);
		// Step 5- close / flush Output Stream
		png_ostream.flush();
		png_ostream.close();

	}
}
