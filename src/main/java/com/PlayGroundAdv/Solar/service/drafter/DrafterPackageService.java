package com.PlayGroundAdv.Solar.service.drafter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.MountingTypeEntity;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitPlansetUploadEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.ProjectsTrackerEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.entity.projects.ProjectFiles;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.GetFileByIdResults;
import com.PlayGroundAdv.Solar.model.InfoPdfPackageDrafterResult;
import com.PlayGroundAdv.Solar.repositories.AdditionalInfoFilesRepository;
import com.PlayGroundAdv.Solar.repositories.NoteSketchFilesRepository;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.PermitSketchRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectsTrackerRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJChecklistRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ElectricalUtilityRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.project.ProjectFilesRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PermitPlansetUploadRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.ahj.PlansetNotesService;
import com.PlayGroundAdv.Solar.service.project.PermitService;
import com.PlayGroundAdv.Solar.service.project.SubmitProjectService;
import com.PlayGroundAdv.Solar.service.user_management.GoogleDriveFolder;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class DrafterPackageService {

	final PermitService permitService;
	final CheckValueTypesService checkValue;
	final RailRackingRepository railRackingRepo;
	final PermitRepository permitRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo;
	final RailRackingFavoriteRepository railRackingFavoritesRepo;
	final ModuleRepository moduleRepo;
	final PathRepository pathRepo;
	final PermitSketchRepository sketchRepo;
	final PermitPlansetUploadRepository plansetUploadRepo;
	final PermitDrafterDataRepository drafterDataRepo;
	final AdditionalInfoFilesRepository additionalInfoFilesRepo;
	final NoteSketchFilesRepository noteSketchFilesRepo;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;
	final ElectricalUtilityRepository electricalUtilityRepo;
	final ProjectsTrackerRepository projectsTrackerRepo;
	final ProjectFilesRepository projectFilesRepo;
	final GoogleDriveFolder googleDriveFolder;
	final SubmitProjectService submitProjectService;
	final PlansetNotesService ahjPlansetNotesService;
	final AHJChecklistRepository aHJChecklistRepo;

	public DrafterPackageService(PermitService permitService, CheckValueTypesService checkValue,
			RailRackingRepository railRackingRepo, PermitRepository permitRepo,
			PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo,
			RailRackingFavoriteRepository railRackingFavoritesRepo, ModuleRepository moduleRepo,
			PathRepository pathRepo, PermitSketchRepository sketchRepo, PermitPlansetUploadRepository plansetUploadRepo,
			PermitDrafterDataRepository drafterDataRepo, AdditionalInfoFilesRepository additionalInfoFilesRepo,
			NoteSketchFilesRepository noteSketchFilesRepo, RoofMaterialTypeRepository roofMaterialTypeRepo,
			ElectricalUtilityRepository electricalUtilityRepo, ProjectsTrackerRepository projectsTrackerRepo,
			ProjectFilesRepository projectFilesRepo, GoogleDriveFolder googleDriveFolder,
			SubmitProjectService submitProjectService, PlansetNotesService ahjPlansetNotesService,
			AHJChecklistRepository aHJChecklistRepo) {
		super();
		this.permitService = permitService;
		this.checkValue = checkValue;
		this.railRackingRepo = railRackingRepo;
		this.permitRepo = permitRepo;
		this.permitProjectSiteInfoEntityRepo = permitProjectSiteInfoEntityRepo;
		this.railRackingFavoritesRepo = railRackingFavoritesRepo;
		this.moduleRepo = moduleRepo;
		this.pathRepo = pathRepo;
		this.sketchRepo = sketchRepo;
		this.plansetUploadRepo = plansetUploadRepo;
		this.drafterDataRepo = drafterDataRepo;
		this.additionalInfoFilesRepo = additionalInfoFilesRepo;
		this.noteSketchFilesRepo = noteSketchFilesRepo;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
		this.electricalUtilityRepo = electricalUtilityRepo;
		this.projectsTrackerRepo = projectsTrackerRepo;
		this.projectFilesRepo = projectFilesRepo;
		this.googleDriveFolder = googleDriveFolder;
		this.submitProjectService = submitProjectService;
		this.ahjPlansetNotesService = ahjPlansetNotesService;
		this.aHJChecklistRepo = aHJChecklistRepo;
	}

	private static final String OTHER = "Other";
	private static final String NOT_FOUND = "not Found";

	public String downloadPackageTracker(Long idPermit) {
		try {
			TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
			Calendar caldowDrafter = Calendar.getInstance(); // creates calendar
			caldowDrafter.setTime(new Date());
			Long count = projectsTrackerRepo.countByPermitId(idPermit);
			if (count == 0) {
				ProjectsTrackerEntity tracker2 = new ProjectsTrackerEntity();
				PermitEntity permitEntity = permitRepo.findById(idPermit).orElse(new PermitEntity());
				tracker2.setPermit(permitEntity);
				tracker2.setDownloadDrafter(caldowDrafter.getTime());
				projectsTrackerRepo.save(tracker2);
				// CR-2424
			} else if (count == 1) {
				ProjectsTrackerEntity tracker = projectsTrackerRepo.findByPermitId(idPermit);
				tracker.setDownloadDrafter(caldowDrafter.getTime());
				projectsTrackerRepo.save(tracker);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "done";
	}

	public String downloadPackage(Long idPermit, Long idUser) {

		try {
			/***************** get data for PDF ********************/

			InfoPdfPackageDrafterResult drafterResult = permitRepo.findPackageDrafterResult(idPermit);
			PermitProjectSiteInfoEntity per = permitProjectSiteInfoEntityRepo.findByPermitEntityId(idPermit);

			if (per.getRailRakingModel() != null) {
				if (Boolean.FALSE
						.equals(railRackingFavoritesRepo.existsByRailRackingId(per.getRailRakingModel().getId())))
					drafterResult.setRailRakingModel(0L);
				else if (Boolean.FALSE
						.equals(railRackingRepo.existsByIdAndIsDeleted(per.getRailRakingModel().getId(), false)))
					drafterResult.setRailRakingModel(-1L);
				else
					drafterResult.setRailRakingModel(per.getRailRakingModel().getId());
			}

			if (per.getRailRakingModelforGroundMounted() != null) {
				if (Boolean.FALSE.equals(railRackingFavoritesRepo
						.existsByRailRackingId(per.getRailRakingModelforGroundMounted().getId())))
					drafterResult.setRailRakingModelforGroundMounted(0L);
				else if (Boolean.FALSE.equals(railRackingRepo
						.existsByIdAndIsDeleted(per.getRailRakingModelforGroundMounted().getId(), false)))
					drafterResult.setRailRakingModelforGroundMounted(-1L);
				else
					drafterResult.setRailRakingModelforGroundMounted(per.getRailRakingModelforGroundMounted().getId());
			}

			List<PermitSketchEntity> sketchEntities = sketchRepo.findByPermitEntityId(idPermit);

			drafterResult.setPermitSketchEntity(sketchEntities);

			GetFileByIdResults projectUploadsComment = new GetFileByIdResults();

			ProjectFiles files = projectFilesRepo.findByProjectId(idPermit);
			projectUploadsComment.setIdPermit(idPermit);
			if (files.getNameFile1() != null) {
				projectUploadsComment.setFile1(files.getNameFile1());
			}
			if (files.getNameFile2() != null) {
				projectUploadsComment.setFile2(files.getNameFile2());
			}
			if (files.getNameFile3() != null) {
				projectUploadsComment.setFile3(files.getNameFile3());
			}
			if (files.getNameFile4() != null) {
				projectUploadsComment.setFile4(files.getNameFile4());
			}
			if (files.getNameFile5() != null) {
				projectUploadsComment.setFile5(files.getNameFile5());
			}
			if (files.getNameFile6() != null) {
				projectUploadsComment.setFile6(files.getNameFile6());
			}
			if (files.getNameFile7() != null) {
				projectUploadsComment.setFile7(files.getNameFile7());
			}
			if (files.getNameFile8() != null) {
				projectUploadsComment.setFile8(files.getNameFile8());
			}
			if (files.getNameFile9() != null) {
				projectUploadsComment.setFile9(files.getNameFile9());
			}
			if (files.getNameFile10() != null) {
				projectUploadsComment.setFile10(files.getNameFile10());
			}
			if (files.getNameFile11() != null) {
				projectUploadsComment.setFile11(files.getNameFile11());
			}

			projectUploadsComment.setUploadFile1(files.getUploadFile1());
			projectUploadsComment.setUploadFile2(files.getUploadFile2());
			projectUploadsComment.setUploadFile3(files.getUploadFile3());
			projectUploadsComment.setUploadFile4(files.getUploadFile4());
			projectUploadsComment.setUploadFile5(files.getUploadFile5());
			projectUploadsComment.setUploadFile6(files.getUploadFile6());
			projectUploadsComment.setUploadFile7(files.getUploadFile7());
			projectUploadsComment.setUploadFile8(files.getUploadFile8());
			projectUploadsComment.setUploadFile9(files.getUploadFile9());
			projectUploadsComment.setUploadFile10(files.getUploadFile10());

			PermitPlansetUploadEntity permitPlansetUploadEntity = plansetUploadRepo.findByPermitEntityId(idPermit);

			buildingDrafterPDF(drafterResult, projectUploadsComment, permitPlansetUploadEntity);

			String dir = pathRepo.findFilePath();
			List<String> srcFiles =  new ArrayList<>();

			if (checkValue.NotEquals(drafterResult.getProjectName(), "")) {
				srcFiles.add(
						dir + "package/csv/" + "DrafterInfo-" + drafterResult.getProjectName() + ".pdf");
			} else {
				srcFiles.add(dir + "package/csv/" + "DrafterInfo-" + drafterResult.getHomeOwnLastName()
						+ ", " + drafterResult.getHomeOwnName() + ".pdf");
			}
			/***************** END get data for PDF ********************/

			/***************
			 * generation GoogleMap and NearMap image
			 ************************/

			PermitDrafterDataEntity u = drafterDataRepo.findByPermitEntityId(idPermit);
			String googleimg = u.getGoogleMapImageName();
			String nearimg = u.getNearMapImageName();
			if (googleimg != null)
				srcFiles.add(dir + "/" + idPermit + "/drafterfiles/" + googleimg);
			if (nearimg != null)
				srcFiles.add(dir + "/" + idPermit + "/drafterfiles/" + nearimg);

			/***************
			 * END generation GoogleMap and NearMap image
			 ****************************/

			/****************
			 * generation additional Info File
			 ************************************/

			List<String> additionalFiles = additionalInfoFilesRepo.getAdditionalInfoFiles(idPermit);
			for (int i = 0; i < additionalFiles.size(); i++) {
				if (additionalFiles.get(i) != null && !srcFiles.contains(dir + "/" + idPermit + "/additionalInfo/" + additionalFiles.get(i)))
					srcFiles.add(dir + "/" + idPermit + "/additionalInfo/" + additionalFiles.get(i));
			}

			/****************
			 * END generation additional Info File
			 ************************************/

			/***************
			 * Test existance image sketch
			 *******************************************/

			List<String> notSketchFiles = noteSketchFilesRepo.getNoteSketchFiles(idPermit);
			for (int i = 0; i < notSketchFiles.size(); i++) {
				if (notSketchFiles.get(i) != null && !srcFiles.contains(dir + "/" + idPermit + "/sketch/" + notSketchFiles.get(i)))
					srcFiles.add(dir + "/" + idPermit + "/sketch/" + notSketchFiles.get(i));
			}

			/***************
			 * End Test existance image sketch
			 *******************************************/
			/******************************
			 * gï¿½nï¿½ration du fichier rar
			 ******************************/
			PermitEntity permit = permitRepo.findById(idPermit).orElse(new PermitEntity());

			String folderName = permit.getProjectName();
			if (!checkValue.isStringNotEmpty(permit.getProjectName())) {
				if (checkValue.NotEquals(permit.getHomeOwnLastName(), "")) {
					folderName = permit.getHomeOwnLastName() + ", " + permit.getHomeOwnName();
				} else {
					folderName = permit.getHomeOwnName();
				}

			}

			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(permit.getAuthentificationEntity());
			String zipFile = dir + "package/" + folderName + "-DrafterPackage.rar";

			File zip = new File(zipFile);
			if (zip != null && zip.exists())
				zip.delete();

			// create byte buffer
			byte[] buffer = new byte[1024];
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for (int i = 0; i < srcFiles.size(); i++) {

				File srcFile = new File(srcFiles.get(i));
				if (srcFile.exists()) {
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to the start of the entry
					// data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));
					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
				}
			}
			// close the ZipOutputStream
			zos.close();

			String googleDrivePath = pathRepo.findGoogleDriveFilePath();
			// A.B 08-26: CR-2847 Save Drafter package to google drive
			if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Drafting/Final Drafting Pkg")
					.exists()) {
				Path srcFile = Paths.get(dir + "package/" + folderName + "-DrafterPackage.rar");
				Path destFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
						+ "/Drafting/Final Drafting Pkg/" + folderName + "-DrafterPackage.rar");
				com.google.common.io.Files.copy(srcFile.toFile(), destFile.toFile());
			} else if (new File(googleDrivePath + ownerFolderName + "/" + folderName).exists()) {
				// A.B Send Technical Problem to NUATN
				submitProjectService
						.technicalProblemMail("<li>The file Final Drafting Pkg does not exist on Final Drafting Pkg "
								+ googleDrivePath + ownerFolderName + "/" + folderName + "</li>", "Nuatn");
			}

		} catch (Exception e) {
			// CR-2424
			Boolean contain = false;
			int i = 0;
			while (Boolean.FALSE.equals(contain) && i < e.getStackTrace().length) {
				if (e.getStackTrace()[i].toString().contains("com.PlayGroundAdv.Solar")) {
					contain = true;
				} else {
					i++;
				}
			}
			if (Boolean.TRUE.equals(contain)) {
				String exceptionInfo = "<li>Error cause : " + e + ".</li><li>Error location : In file "
						+ e.getStackTrace()[i].getFileName() + " within the method : "
						+ e.getStackTrace()[i].getMethodName() + " at line : " + e.getStackTrace()[i].getLineNumber()
						+ ".</li>";
				submitProjectService.technicalProblemMail(exceptionInfo, null);
			}

			e.printStackTrace();
			return "Fail";
		}
		return "Done";

	}

	private String[] push(String[] array, String push) {
		String[] longer = new String[array.length + 1];
		System.arraycopy(array, 0, longer, 0, array.length);
		longer[array.length] = push;
		return longer;
	}

	public String getRoofMaterialType(Long roofID) {
		try {
			if (roofID != null) {
				RoofMaterialType roofMaterialType = roofMaterialTypeRepo.findById(roofID)
						.orElse(new RoofMaterialType());
				if (roofMaterialType != null)
					return roofMaterialType.getMappingValue();
				else
					return "";

			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public void buildingDrafterPDF(InfoPdfPackageDrafterResult drafterResult, GetFileByIdResults projectUploadsComment,
			PermitPlansetUploadEntity permitPlansetUploadEntity) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		PdfReader reader;
		File fileRe = null;
		String dir = pathRepo.findFilePath();
		try {
			if (drafterResult != null) {

				reader = new PdfReader(Constants.rapportPlansetFolderUrl + "DrafterInfo.pdf");
				if (!new File(dir).exists()) {
					new File(dir).mkdir();
				}
				if (!new File(dir + "package/").exists()) {
					new File(dir + "package/").mkdir();
				}
				if (!new File(dir + "package/csv/").exists()) {
					new File(dir + "package/csv/").mkdir();
				}

				if (drafterResult.getProjectName() == null || checkValue.Equals(drafterResult.getProjectName(), "")) {
					fileRe = new File(dir + "package/csv/" + "DrafterInfo-" + drafterResult.getHomeOwnLastName() + ", "
							+ drafterResult.getHomeOwnName() + ".pdf");
				} else {
					fileRe = new File(dir + "package/csv/" + "DrafterInfo-" + drafterResult.getProjectName() + ".pdf");
				}

				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();
				if (fileRe.exists()) {
					fileRe.delete();
					if (drafterResult.getProjectName() == null
							|| checkValue.Equals(drafterResult.getProjectName(), "")) {
						fileRe = new File(dir + "package/csv/" + "DrafterInfo-" + drafterResult.getHomeOwnLastName()
								+ ", " + drafterResult.getHomeOwnName() + ".pdf");
						form.setField("homeowner-name",
								drafterResult.getHomeOwnLastName() + ", " + drafterResult.getHomeOwnName());
					} else {
						fileRe = new File(
								dir + "package/csv/" + "DrafterInfo-" + drafterResult.getProjectName() + ".pdf");

						form.setField("homeowner-name", drafterResult.getProjectName());
					}
				}

				form.setField("site-address", drafterResult.getSiteAddress());
				form.setField("address-line-2", drafterResult.getAddressLine2());

				if (checkValue.Equals(drafterResult.getCity(), OTHER)) {
					form.setField("city", drafterResult.getCityOther());
				} else {
					form.setField("city", drafterResult.getCity());
				}

				form.setField("state", drafterResult.getState());
				form.setField("postal-code", drafterResult.getPostalCode());
				form.setField("property-apn", drafterResult.getPropertyAPN());
				String tallStruc = drafterResult.getTallStructure();
				if (checkValue.Equals(tallStruc, "OtheStory")) {
					tallStruc = drafterResult.getOtherTallStructure();
				}
				form.setField("how-tall-is-the-structure", tallStruc);

				String roofType = getRoofMaterialType(drafterResult.getRoofMaterialType());
				if (checkValue.Equals(roofType, OTHER)) {
					roofType = drafterResult.getRoofMaterialTypeOther();
				}
				form.setField("roof-material-type", roofType);

				if (checkValue.Equals(drafterResult.getRoofMounted(), true)) {
					if (checkValue.Equals(drafterResult.getRafterTrussSpacing(), OTHER))
						form.setField("rafter-or-truss-spacing", drafterResult.getTextOtherRatfter());
					else
						form.setField("rafter-or-truss-spacing", drafterResult.getRafterTrussSpacing() + "\"");
				}
				if (drafterResult.getStanchionMaxSpacing() != null) {
					if (checkValue.NotEquals(drafterResult.getStanchionMaxSpacing(), OTHER)) {
						form.setField("stanchion-max-spacing", drafterResult.getStanchionMaxSpacing() + "\"");
					} else {
						form.setField("stanchion-max-spacing", drafterResult.getStanchionMaxSpacingOther());
					}
				}

				try {
					if (checkValue.Equals(drafterResult.getCrossSectionSize(), "OtherSize"))
						form.setField("structural-member-cross-section-size", drafterResult.getTextOtherSize());
					else
						form.setField("structural-member-cross-section-size", drafterResult.getCrossSectionSize());
				} catch (IOException e) {

				}

				if (drafterResult.getPvModuleModEl() != null) {
					Cmodulev2 module = moduleRepo.findById(drafterResult.getPvModuleModEl()).orElse(new Cmodulev2());
					form.setField("pv-module-manufacturer", module.getMake());
					form.setField("pv-module-model", module.getModel());
					form.setField("pv-module-qty", drafterResult.getQtySegmentOne() + "");
					form.setField("pv-module-size",
							permitService.getModuleDimensions(drafterResult.getPvModuleModEl()));
				}

				// A.B 05-24-2022 Rev-CR-2982-MOD-002
				form.setField("INTERNATIONAL-FIRE-CODE",
						checkValue.Equals(drafterResult.getState(), "CA") ? "N/A" : getIFC(drafterResult));
				if (Boolean.TRUE.equals(drafterResult.getGroundMounted())) {
					form.setField("MODULE-LAYOUT",
							checkValue.Equals(drafterResult.getModuleLayout(), "Other")
									? drafterResult.getModuleLayoutOther()
									: drafterResult.getModuleLayout());
				}

				form.setField("string-1", drafterResult.getStringOne());
				form.setField("string-2", drafterResult.getStringTwo());
				form.setField("string-3", drafterResult.getStringThree());
				form.setField("string-4", drafterResult.getStringFour());
				form.setField("string-5", drafterResult.getStringFive());
				if (drafterResult.getStringSix() != null) {
					form.setField("string-6", drafterResult.getStringSix() + "");
				}
				if (drafterResult.getStringSeven() != null) {
					form.setField("string-7", drafterResult.getStringSeven() + "");
				}
				if (drafterResult.getStringEight() != null) {
					form.setField("string-8", drafterResult.getStringEight() + "");
				}
				if (drafterResult.getStringNine() != null) {
					form.setField("string-9", drafterResult.getStringNine() + "");
				}

				if (drafterResult.getStringTen() != null) {
					form.setField("string-10", drafterResult.getStringTen() + "");
				}

				if (drafterResult.getStringEleven() != null) {
					form.setField("string-11", drafterResult.getStringEleven() + "");
				}

				if (drafterResult.getStringTwelve() != null) {
					form.setField("string-12", drafterResult.getStringTwelve() + "");
				}

				form.setField("inv2-string-1", drafterResult.getSecondStringOne());
				form.setField("inv2-string-2", drafterResult.getSecondStringTwo());
				form.setField("inv2-string-3", drafterResult.getSecondStringThree());
				form.setField("inv2-string-4", drafterResult.getSecondStringFour());
				form.setField("inv2-string-5", drafterResult.getSecondStringFive());
				if (drafterResult.getSecondStringSix() != null) {
					form.setField("inv2-string-6", drafterResult.getSecondStringSix() + "");
				}
				if (drafterResult.getSecondStringSeven() != null) {
					form.setField("inv2-string-7", drafterResult.getSecondStringSeven() + "");
				}
				if (drafterResult.getSecondStringEight() != null) {
					form.setField("inv2-string-8", drafterResult.getSecondStringEight() + "");
				}
				if (drafterResult.getSecondStringNine() != null) {
					form.setField("inv2-string-9", drafterResult.getSecondStringNine() + "");
				}

				if (drafterResult.getSecondStringTen() != null) {
					form.setField("inv2-string-10", drafterResult.getSecondStringTen() + "");
				}

				if (drafterResult.getSecondStringEleven() != null) {
					form.setField("inv2-string-11", drafterResult.getSecondStringEleven() + "");
				}

				if (drafterResult.getSecondStringTwelve() != null) {
					form.setField("inv2-string-12", drafterResult.getSecondStringTwelve() + "");
				}
				if (drafterResult.getThirdStringOne() != null) {
					form.setField("inv3-string-1", drafterResult.getThirdStringOne() + "");
				}
				if (drafterResult.getThirdStringTwo() != null) {
					form.setField("inv3-string-2", drafterResult.getThirdStringTwo() + "");
				}
				if (drafterResult.getThirdStringThree() != null) {
					form.setField("inv3-string-3", drafterResult.getThirdStringThree() + "");
				}
				if (drafterResult.getThirdStringFour() != null) {
					form.setField("inv3-string-4", drafterResult.getThirdStringFour() + "");
				}
				if (drafterResult.getThirdStringFive() != null) {
					form.setField("inv3-string-5", drafterResult.getThirdStringFive() + "");
				}
				if (drafterResult.getThirdStringSix() != null) {
					form.setField("inv3-string-6", drafterResult.getThirdStringSix() + "");
				}
				if (drafterResult.getThirdStringSeven() != null) {
					form.setField("inv3-string-7", drafterResult.getThirdStringSeven() + "");
				}
				if (drafterResult.getThirdStringEight() != null) {
					form.setField("inv3-string-8", drafterResult.getThirdStringEight() + "");
				}
				if (drafterResult.getThirdStringNine() != null) {
					form.setField("inv3-string-9", drafterResult.getThirdStringNine() + "");
				}

				if (drafterResult.getThirdStringTen() != null) {
					form.setField("inv3-string-10", drafterResult.getThirdStringTen() + "");
				}

				if (drafterResult.getThirdStringEleven() != null) {
					form.setField("inv3-string-11", drafterResult.getThirdStringEleven() + "");
				}

				if (drafterResult.getThirdStringTwelve() != null) {
					form.setField("inv3-string-12", drafterResult.getThirdStringTwelve() + "");
				}
				if (drafterResult.getFourthStringOne() != null) {
					form.setField("inv4-string-1", drafterResult.getFourthStringOne() + "");
				}
				if (drafterResult.getFourthStringTwo() != null) {
					form.setField("inv4-string-2", drafterResult.getFourthStringTwo() + "");
				}
				if (drafterResult.getFourthStringThree() != null) {
					form.setField("inv4-string-3", drafterResult.getFourthStringThree() + "");
				}
				if (drafterResult.getFourthStringFour() != null) {
					form.setField("inv4-string-4", drafterResult.getFourthStringFour() + "");
				}
				if (drafterResult.getFourthStringFive() != null) {
					form.setField("inv4-string-5", drafterResult.getFourthStringFive() + "");
				}
				if (drafterResult.getFourthStringSix() != null) {
					form.setField("inv4-string-6", drafterResult.getFourthStringSix() + "");
				}
				if (drafterResult.getFourthStringSeven() != null) {
					form.setField("inv4-string-7", drafterResult.getFourthStringSeven() + "");
				}
				if (drafterResult.getFourthStringEight() != null) {
					form.setField("inv4-string-8", drafterResult.getFourthStringEight() + "");
				}
				if (drafterResult.getFourthStringNine() != null) {
					form.setField("inv4-string-9", drafterResult.getFourthStringNine() + "");
				}

				if (drafterResult.getFourthStringTen() != null) {
					form.setField("inv4-string-10", drafterResult.getFourthStringTen() + "");
				}

				if (drafterResult.getFourthStringEleven() != null) {
					form.setField("inv4-string-11", drafterResult.getFourthStringEleven() + "");
				}

				if (drafterResult.getFourthStringTwelve() != null) {
					form.setField("inv4-string-12", drafterResult.getFourthStringTwelve() + "");
				}

				if (drafterResult.getFifthStringOne() != null) {
					form.setField("inv5-string-1", drafterResult.getFifthStringOne() + "");
				}
				if (drafterResult.getFifthStringTwo() != null) {
					form.setField("inv5-string-2", drafterResult.getFifthStringTwo() + "");
				}
				if (drafterResult.getFifthStringThree() != null) {
					form.setField("inv5-string-3", drafterResult.getFifthStringThree() + "");
				}
				if (drafterResult.getFifthStringFour() != null) {
					form.setField("inv5-string-4", drafterResult.getFifthStringFour() + "");
				}
				if (drafterResult.getFifthStringFive() != null) {
					form.setField("inv5-string-5", drafterResult.getFifthStringFive() + "");
				}
				if (drafterResult.getFifthStringSix() != null) {
					form.setField("inv5-string-6", drafterResult.getFifthStringSix() + "");
				}
				if (drafterResult.getFifthStringSeven() != null) {
					form.setField("inv5-string-7", drafterResult.getFifthStringSeven() + "");
				}
				if (drafterResult.getFifthStringEight() != null) {
					form.setField("inv5-string-8", drafterResult.getFifthStringEight() + "");
				}
				if (drafterResult.getFifthStringNine() != null) {
					form.setField("inv5-string-9", drafterResult.getFifthStringNine() + "");
				}

				if (drafterResult.getFifthStringTen() != null) {
					form.setField("inv5-string-10", drafterResult.getFifthStringTen() + "");
				}

				if (drafterResult.getFifthStringEleven() != null) {
					form.setField("inv5-string-11", drafterResult.getFifthStringEleven() + "");
				}

				if (drafterResult.getFifthStringTwelve() != null) {
					form.setField("inv5-string-12", drafterResult.getFifthStringTwelve() + "");
				}

				if (checkValue.Equals(drafterResult.getTiltLegs(), true)) {
					form.setField("tilt-legs", "Yes");
				} else
					form.setField("tilt-legs", "No");

				form.setField("tracking-type", drafterResult.getInverterModel());
				form.setField("format-size", drafterResult.getFormatSize());
				if (drafterResult != null && drafterResult.getPermitSketchEntity() != null) {
					for (int i = 1; i <= drafterResult.getPermitSketchEntity().size(); i++) {
						if (drafterResult.getPermitSketchEntity().get(i - 1) != null) {
							form.setField("azimuth-" + i,
									drafterResult.getPermitSketchEntity().get(i - 1).getAzimuth());
							form.setField("roof-pitch-" + i,
									drafterResult.getPermitSketchEntity().get(i - 1).getRoofPitch());
							form.setField("module-tilt-" + i,
									drafterResult.getPermitSketchEntity().get(i - 1).getModelvalue());
							if (checkValue.Equals(drafterResult.getPermitSketchEntity().get(i - 1).getModuleTils(),
									true)) {
								form.setField("tilt-kit-" + i, "Yes");
							} else
								form.setField("tilt-kit-" + i, "No");
							if (checkValue.NotEquals(drafterResult.getPermitSketchEntity().get(i - 1).getEaveOverHang(),
									"")
									&& checkValue.NotEquals(
											drafterResult.getPermitSketchEntity().get(i - 1).getEaveOverHang(), "null")
									&& checkValue.NotEquals(
											drafterResult.getPermitSketchEntity().get(i - 1).getEaveOverHang(),
											OTHER)) {
								form.setField("eave-overhang-" + i,
										drafterResult.getPermitSketchEntity().get(i - 1).getEaveOverHang() + "\"");
							} else if (checkValue.Equals(
									drafterResult.getPermitSketchEntity().get(i - 1).getEaveOverHang(), OTHER)) {
								form.setField("eave-overhang-" + i,
										drafterResult.getPermitSketchEntity().get(i - 1).getEaveOverHangOther() + "\"");
							}
							if (drafterResult.getPermitSketchEntity().get(i - 1).getModuleQty() != null
									&& checkValue.NotEquals(
											drafterResult.getPermitSketchEntity().get(i - 1).getModuleQty(), "")
									&& checkValue.NotEquals(
											drafterResult.getPermitSketchEntity().get(i - 1).getModuleQty(), "null")
									&& checkValue.NotEquals(
											drafterResult.getPermitSketchEntity().get(i - 1).getModuleQty(), OTHER)) {
								form.setField("module-qty-" + i,
										drafterResult.getPermitSketchEntity().get(i - 1).getModuleQty());
							} else if (checkValue
									.Equals(drafterResult.getPermitSketchEntity().get(i - 1).getModuleQty(), OTHER)) {
								form.setField("module-qty-" + i,
										drafterResult.getPermitSketchEntity().get(i - 1).getModuleQty());
							}

						}
					}
				}

				// A.B 11-07: CR-2982 Update Drafter Info Sheet Mapping
				form.setField("Company-Name",
						checkValue.isStringNotEmpty(drafterResult.getCompany()) ? drafterResult.getCompany() : "");

				if (checkValue.Equals(drafterResult.getUtilityCompanyName(), OTHER)) {
					form.setField("Utility-Company", drafterResult.getUtilityCompanyNameOther());
				} else if (checkValue.isStringNotEmpty(drafterResult.getUtilityCompanyName())) {
					String electricalCompany = electricalUtilityRepo
							.getMappingValue(Long.parseLong(drafterResult.getUtilityCompanyName()));
					form.setField("Utility-Company", electricalCompany);
				}

				if (checkValue.Equals(drafterResult.getRoofMounted(), true)) {
					RailRacking railRacking = railRackingRepo.findById(drafterResult.getRailRakingModel())
							.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));

					List<MountingTypeEntity> mountingType = railRacking.getMountType();
					String typeOfSystem = "";
					if (mountingType != null && mountingType.size() > 0) {
						for (MountingTypeEntity typeM : mountingType) {
							typeOfSystem = typeOfSystem + ", " + typeM.getMountingType();
						}
					}

					form.setField("Roof-Racking-System-Type", railRacking != null ? typeOfSystem : "");
				}

				if (checkValue.Equals(drafterResult.getGroundMounted(), true)) {
					RailRacking railRacking = railRackingRepo
							.findById(drafterResult.getRailRakingModelforGroundMounted())
							.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));

					List<MountingTypeEntity> mountingType = railRacking.getMountType();
					String typeOfSystem = "";
					if (mountingType != null && mountingType.size() > 0) {
						for (MountingTypeEntity typeM : mountingType) {
							typeOfSystem = typeOfSystem + ", " + typeM.getMountingType();
						}
					}

					form.setField("Ground-Racking-System-Type", railRacking != null ? typeOfSystem : "");
				}

				form.setField("Stanchions-Type", drafterResult.getStanchionsType());

				String layoutSketch = "";
				layoutSketch = checkValue.Equals(drafterResult.getIgnoreVents(), true) ? layoutSketch
						+ "\nIgnore Vents in layout.  We are moving them, able to work around them or allowed to cover them."
						: layoutSketch;
				layoutSketch = checkValue.Equals(drafterResult.getFiresetbacks(), true)
						? layoutSketch + "\nFire setbacks do not apply to this structure."
						: layoutSketch;
				layoutSketch = checkValue.Equals(drafterResult.getFiresetbacks(), true)
						? layoutSketch + "\n Notes: " + drafterResult.getFiresetbacksNote()
						: layoutSketch;
				layoutSketch = checkValue.Equals(drafterResult.getFireVariance(), true) ? layoutSketch
						+ "\nWe will be requesting a fire variance.  Please show layout encroaching on setbacks as drawn."
						: layoutSketch;
				layoutSketch = checkValue.Equals(drafterResult.getFireVariance(), true)
						? layoutSketch + "\n Notes: " + drafterResult.getFireVarianceNote()
						: layoutSketch;
				layoutSketch = checkValue.Equals(drafterResult.getModulesEncroaching(), true) ? layoutSketch
						+ "\nMy sketch shows Modules encroaching into the Fire Setback areas, but they do fit.  Size or scale them to fit without violating Fire Setbacks."
						: layoutSketch;
				form.setField("Layout-Sketch-Exceptions", layoutSketch);

				String uploadComments = "";
				// A.B : Project Uploads
				String projectUploadComments = "";
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile1())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile1() + "\n"
						: projectUploadComments;
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile2())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile2() + "\n"
						: projectUploadComments;
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile3())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile3() + "\n"
						: projectUploadComments;
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile4())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile4() + "\n"
						: projectUploadComments;
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile5())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile5() + "\n"
						: projectUploadComments;
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile6())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile6() + "\n"
						: projectUploadComments;
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile7())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile7() + "\n"
						: projectUploadComments;
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile8())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile8() + "\n"
						: projectUploadComments;
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile9())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile9() + "\n"
						: projectUploadComments;
				projectUploadComments = checkValue.isStringNotEmpty(projectUploadsComment.getUploadFile10())
						? projectUploadComments + "   -" + projectUploadsComment.getUploadFile10() + "\n"
						: projectUploadComments;
				uploadComments = checkValue.isStringNotEmpty(projectUploadComments)
						? uploadComments + "Project Upload : \n" + projectUploadComments
						: uploadComments;

				// A.B : Project Plan set Uploads
				String plansetUploadComments = "";
				plansetUploadComments = checkValue.isStringNotEmpty(permitPlansetUploadEntity.getUploadPlanset1())
						? plansetUploadComments + "   -" + permitPlansetUploadEntity.getUploadPlanset1() + "\n"
						: plansetUploadComments;
				plansetUploadComments = checkValue.isStringNotEmpty(permitPlansetUploadEntity.getUploadPlanset2())
						? plansetUploadComments + "   -" + permitPlansetUploadEntity.getUploadPlanset2() + "\n"
						: plansetUploadComments;
				plansetUploadComments = checkValue.isStringNotEmpty(permitPlansetUploadEntity.getUploadPlanset3())
						? plansetUploadComments + "   -" + permitPlansetUploadEntity.getUploadPlanset3() + "\n"
						: plansetUploadComments;
				plansetUploadComments = checkValue.isStringNotEmpty(permitPlansetUploadEntity.getUploadPlanset4())
						? plansetUploadComments + "   -" + permitPlansetUploadEntity.getUploadPlanset4() + "\n"
						: plansetUploadComments;
				plansetUploadComments = checkValue.isStringNotEmpty(permitPlansetUploadEntity.getUploadPlanset5())
						? plansetUploadComments + "   -" + permitPlansetUploadEntity.getUploadPlanset5() + "\n"
						: plansetUploadComments;
				uploadComments = checkValue.isStringNotEmpty(plansetUploadComments)
						? uploadComments + "Planset Upload :\n" + plansetUploadComments
						: uploadComments;

				// A.B Project Uploads Comments

				uploadComments = checkValue.isStringNotEmpty(drafterResult.getInsertRoofNote())
						? uploadComments + "Rail to Roof Connection: " + drafterResult.getInsertRoofNote() + "\n"
						: uploadComments;
				uploadComments = checkValue.isStringNotEmpty(drafterResult.getBosUploadComments())
						? uploadComments + "The Point of Connection (POC) Will Be... Upload’s comment: "
								+ drafterResult.getBosUploadComments() + "\n"
						: uploadComments;
				uploadComments = checkValue.isStringNotEmpty(drafterResult.getLayoutSketchUploadCommentsLayout())
						? uploadComments + "Layout Sketch Upload Comments: "
								+ drafterResult.getLayoutSketchUploadCommentsLayout() + "\n"
						: uploadComments;
				uploadComments = checkValue.isStringNotEmpty(drafterResult.getLayoutSketchUploadCommentsAddInfo())
						? uploadComments + "Layout Sketch Upload Additional Information Comments: "
								+ drafterResult.getLayoutSketchUploadCommentsAddInfo() + "\n"
						: uploadComments;
				uploadComments = checkValue.isStringNotEmpty(drafterResult.getSketchNote())
						? uploadComments + "Layout Sketch AdditionalUploads Comments: "
								+ drafterResult.getLayoutSketchUploadCommentsAddInfo() + "\n"
						: uploadComments;
				uploadComments = checkValue.isStringNotEmpty(drafterResult.getAdvUploadCommentsGoogle())
						? uploadComments + "Upload Google Earth Image Comments: "
								+ drafterResult.getAdvUploadCommentsGoogle() + "\n"
						: uploadComments;
				uploadComments = checkValue.isStringNotEmpty(drafterResult.getAdvUploadCommentsNearMap())
						? uploadComments + "Upload NearMap Image Comments: "
								+ drafterResult.getAdvUploadCommentsNearMap() + "\n"
						: uploadComments;
				uploadComments = checkValue.isStringNotEmpty(drafterResult.getAdditionalInfoInstallationGuidelines())
						? uploadComments + "Additional Information: "
								+ drafterResult.getAdditionalInfoInstallationGuidelines() + "\n"
						: uploadComments;
				uploadComments = checkValue.isStringNotEmpty(drafterResult.getAdditionalInfoUploadComments())
						? uploadComments + "Additional Information Upload Comment: "
								+ drafterResult.getAdditionalInfoUploadComments() + "\n"
						: uploadComments;

				form.setField("additional-info", uploadComments);

				stamper.setFormFlattening(true);
				stamper.close();
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException dE) {
			dE.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getIFC(InfoPdfPackageDrafterResult drafterResult) {
		try {
			Long ahjId = ahjPlansetNotesService.getAHJId(drafterResult.getState(), drafterResult.getCity(),
					drafterResult.getCityOther(), drafterResult.getProjectJurisdiction(),
					drafterResult.getProjectJurisOther());
			String ifc = aHJChecklistRepo.getIfc(ahjId);
			return checkValue.isStringNotEmpty(ifc) ? ifc
					: getDefaultGoverningCodes(drafterResult.getState());
							
		} catch (Exception e) {
			e.printStackTrace();
			return "Missing";
		}
	}
	
	private String getDefaultGoverningCodes(String state) {
		try {
			if (state.equals("ID") || state.equals("NC") || state.equals("CO") || state.equals("GA")
					|| state.equals("TX") || state.equals("OR") || state.equals("SC") || state.equals("OK") || state.equals("MA")) {
				return "2015";
			} else if (state.equals("MO")) {
				return "2009";
			} else
				return "Missing";
		} catch (Exception e) {
			e.printStackTrace();
			return "Missing";
		}
	}
	
	public ResponseEntity<byte[]> downloadPackageDrafter(String homeOwnName, String homeOwnLastName, String projectName,
			Long idPermit) {

		String url = "";
		String dir = pathRepo.findFilePath();
		url = dir + "package/" + projectName + "-DrafterPackage.rar";
		if (projectName == null || checkValue.Equals(projectName, "")) {
			if (checkValue.NotEquals(homeOwnLastName, "")) {
				url = dir + "package/" + homeOwnLastName + ", " + homeOwnName + "-DrafterPackage.rar";
			} else {
				url = dir + "package/" + homeOwnName + "-DrafterPackage.rar";
			}

		}
		Path path = Paths.get(url);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
			downloadPackageTracker(idPermit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

}
