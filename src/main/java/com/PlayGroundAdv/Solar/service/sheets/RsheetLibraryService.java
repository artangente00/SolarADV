package com.PlayGroundAdv.Solar.service.sheets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RsheetsLibraryEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.SRsheetsModel;
import com.PlayGroundAdv.Solar.model.libraries.SheetPageRequest;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.RsheetsLibraryRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class RsheetLibraryService {

	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValue;
	final RsheetsLibraryRepository rSheetsLibraryRepo;
	final AuthenticationRepository userRepo;
	final RailRackingRepository railRackingRepo;

	public RsheetLibraryService(NotificationEntityService notificationEntityService, CheckValueTypesService checkValue,
			RsheetsLibraryRepository rSheetsLibraryRepo, AuthenticationRepository userRepo,
			RailRackingRepository railRackingRepo) {
		super();
		this.notificationEntityService = notificationEntityService;
		this.checkValue = checkValue;
		this.rSheetsLibraryRepo = rSheetsLibraryRepo;
		this.userRepo = userRepo;
		this.railRackingRepo = railRackingRepo;
	}

	public Page<SRsheetsModel> filter(SheetPageRequest request) {

		try {

			// List des Where
			List<String> likeOr = new ArrayList<>();
			if (checkValue.NotEquals(request.getPdfName(), "")) {
				likeOr.add(request.getPdfName().toUpperCase());
				if (request.getPdfName().contains(" ")) {
					likeOr.addAll(Arrays.asList(request.getPdfName().toUpperCase().split(" ")));
				}
				if (request.getPdfName().contains("_")) {
					likeOr.addAll(Arrays.asList(request.getPdfName().toUpperCase().split("_")));
				}
			}

			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("pdfName"));
			Page<RsheetsLibraryEntity> tldSheetLibrary = rSheetsLibraryRepo
					.findAll(filter(request.getPdfType(), likeOr, request.getIsDeleted()), pageable);
			return convertDto(tldSheetLibrary, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private Specification<RsheetsLibraryEntity> filter(String type, List<String> likeOr, Boolean deleted) {

		return new Specification<RsheetsLibraryEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<RsheetsLibraryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> pLikeOr = new ArrayList<>();

				for (int i = 0; likeOr != null && i < likeOr.size(); i++) {
					Predicate predicate = cb.like(cb.upper(root.get("pdfName")), "%" + likeOr.get(i) + "%");
					pLikeOr.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate predicateLike = checkValue.isStringNotEmpty(type)
						? cb.like(cb.upper(root.get("pdfName")), "%" + type + "\\_%")
						: null;
				Predicate predicateLikeOr = likeOr != null && !likeOr.isEmpty()
						? cb.or(pLikeOr.toArray(new Predicate[pLikeOr.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateDeleted, predicateLike, predicateLikeOr);
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	private Page<SRsheetsModel> convertDto(Page<RsheetsLibraryEntity> page, Pageable pageable) {
		try {
			return new PageImpl<>(page.stream().map(c -> new SRsheetsModel(c.getId(), c.getPdfName(), c.getLastUpdate(),
					c.getAddedBy() != null ? c.getAddedBy().getFirstName() + " " + c.getAddedBy().getLastName() : "",
					false, c.getDeletedOn(),
					c.getDeletedBy() != null ? c.getDeletedBy().getFirstName() + " " + c.getDeletedBy().getLastName()
							: "",
					c.getUpdatedBy() != null ? c.getUpdatedBy().getFirstName() + " " + c.getUpdatedBy().getLastName()
							: ""))
					.collect(Collectors.toList()), pageable, page.getTotalElements());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String uploadRSheet(MultipartFile file, String fileName, Long siteSurveyiD) throws IOException {
		List<RsheetsLibraryEntity> rSheetsList1 = new ArrayList<>();

		try {

			rSheetsList1 = rSheetsLibraryRepo.findByPdfNameAndIsDeleted(fileName, false);
			if (rSheetsList1 != null && !rSheetsList1.isEmpty()) {
				return "exist";
			} else {
				AuthentificationEntity addedBy = userRepo.findById(siteSurveyiD).orElse(null);
				RsheetsLibraryEntity rSheetsList = new RsheetsLibraryEntity();
				rSheetsList.setPdfName(fileName);
				rSheetsList.setIsDeleted(false);
				rSheetsList.setLastUpdate(new Date());
				rSheetsList.setAddedBy(addedBy);

				byte[] bytes5 = file.getBytes();
				new File(Constants.rapportRSheetFolderUrl + "ReferenceSheets").mkdirs();
				Path pathUp5 = Paths.get(Constants.rapportRSheetFolderUrl + "ReferenceSheets/" + fileName);
				Files.write(pathUp5, bytes5);
				rSheetsLibraryRepo.save(rSheetsList);
				return rSheetsList.getId() + "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editRSheet(MultipartFile file, String fileName, Long userId, Long fileID, String lastName) {
		try {
			Boolean exist = rSheetsLibraryRepo.existsByPdfNameAndIsDeletedFalseAndIdNotIn(fileName,
					Arrays.asList(fileID));
			if (Boolean.TRUE.equals(exist)) {
				return "exist";
			} else {
				RsheetsLibraryEntity updatedRsheets = rSheetsLibraryRepo.findById(fileID).orElse(null);
				if (updatedRsheets != null) {
					updatedRsheets.setPdfName(fileName);
					updatedRsheets.setIsDeleted(false);
					updatedRsheets.setLastUpdate(new Date());
					updatedRsheets.setUpdatedBy(userRepo.findById(userId).orElse(null));
					rSheetsLibraryRepo.save(updatedRsheets);

					File rSheetfile = new File(Constants.rapportRSheetFolderUrl + "ReferenceSheets/" + lastName);
					if (rSheetfile.exists()) {
						rSheetfile.delete();
					}
					byte[] bytes5 = file.getBytes();
					new File(Constants.rapportRSheetFolderUrl + "ReferenceSheets").mkdirs();
					Path pathUp5 = Paths.get(Constants.rapportRSheetFolderUrl + "ReferenceSheets/" + fileName);
					Files.write(pathUp5, bytes5);
					editRSheetNotification(userId, fileName);
					return updatedRsheets.getId() + "";
				}
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String deleteSheet(Long sheetID, Long userID) {
		try {
			RsheetsLibraryEntity rSheetsList = rSheetsLibraryRepo.findById(sheetID).orElse(null);
			if (rSheetsList != null) {
				rSheetsList.setIsDeleted(true);
				rSheetsList.setDeletedOn(new Date());
				rSheetsList.setDeletedBy(userRepo.findById(userID).orElse(null));
				rSheetsLibraryRepo.save(rSheetsList);
				deleteRSheetNotification(userID, rSheetsList.getPdfName());
				return renameFile(rSheetsList.getPdfName(), "Delete" + rSheetsList.getPdfName());
			}
			return "fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String restoreRsheet(Long sheetID) {

		try {
			RsheetsLibraryEntity rSheetsList = rSheetsLibraryRepo.findById(sheetID)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + sheetID));
			Boolean exist = rSheetsLibraryRepo.existsByPdfNameAndIsDeletedFalse(rSheetsList.getPdfName());
			if (Boolean.FALSE.equals(exist)) {
				rSheetsList.setIsDeleted(false);
				rSheetsLibraryRepo.save(rSheetsList);
				return renameFile("Delete" + rSheetsList.getPdfName(), rSheetsList.getPdfName());
			}
			return "exist";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	private String renameFile(String o, String n) {
		File newfile = new File(Constants.rapportRSheetFolderUrl + "ReferenceSheets/" + n);
		File oldFile = new File(Constants.rapportRSheetFolderUrl + "ReferenceSheets/" + o);
		if (oldFile.exists() && oldFile.renameTo(newfile)) {
			return "done";
		}
		return "fail";
	}

	private String editRSheetNotification(Long userID, String pdfName) {
		try {
			AuthentificationEntity userCo = userRepo.findById(userID).orElse(null);
			if (userCo != null) {
				notificationEntityService.addNewNotif(userID, 0L, "Update-R-sheet", "Libraries", "Update-R-sheet",
						"The " + userCo.getRoleEntity().getDescription() + " " + userCo.getFirstName() + " "
								+ userCo.getLastName() + " updated The R-sheet " + pdfName,
						true);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	private String deleteRSheetNotification(Long userID, String pdfName) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID).orElse(null);
			if (userCo != null) {
				notificationEntityService.addNewNotif(userID, 0L, "Delete-R-sheet", "Libraries", "Delete-R-sheet",
						"The " + userCo.getRoleEntity().getDescription() + " " + userCo.getFirstName() + " "
								+ userCo.getLastName() + " deleted The R-sheet " + pdfName,
						true);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public RsheetsLibraryEntity getRsheet(Long idRsheet) {

		try {
			return rSheetsLibraryRepo.findById(idRsheet).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String addRsheet(RsheetsLibraryEntity rsheetName, Long idRsheet) {

		try {
			RsheetsLibraryEntity rSheet = rSheetsLibraryRepo.findById(idRsheet).orElse(null);

			if (rsheetName != null && rSheet != null) {
				rSheet.setBracedOrUnbraced(rsheetName.getBracedOrUnbraced());
				rSheet.setBracedOrUnbracedOther(rsheetName.getBracedOrUnbracedOther());
				rSheet.setComponentType(rsheetName.getComponentType());
				rSheet.setExposureCategory(rsheetName.getExposureCategory());
				rSheet.setFootingDiameter(rsheetName.getFootingDiameter());
				rSheet.setGroundRailRacking(rsheetName.getGroundRailRacking());
				rSheet.setManufacturer(rsheetName.getManufacturer());
				rSheet.setModel(rsheetName.getModel());
				rSheet.setModuleLayout(rsheetName.getModuleLayout());
				rSheet.setPipeSize(rsheetName.getPipeSize());
				rSheet.setSnowLoad(rsheetName.getSnowLoad());
				rSheet.setState(rsheetName.getState());
				rSheet.setThicknessPipe(rsheetName.getThicknessPipe());
				rSheet.setTiltRange(rsheetName.getTiltRange());
				rSheet.setWindSpeed(rsheetName.getWindSpeed());
				rSheet.setExposureCategoryOther(rsheetName.getExposureCategoryOther());
				rSheet.setFootingDiameterOther(rsheetName.getFootingDiameterOther());
				rSheet.setModuleLayoutOther(rsheetName.getModuleLayoutOther());
				rSheet.setPipeSizeOther(rsheetName.getPipeSizeOther());
				rSheet.setSnowLoadOther(rsheetName.getSnowLoadOther());
				rSheet.setThicknessPipeOther(rsheetName.getThicknessPipeOther());
				rSheet.setWindSpeedOther(rsheetName.getWindSpeedOther());
				rSheet.setAsceStandard(rsheetName.getAsceStandard());

				rSheetsLibraryRepo.save(rSheet);
			}

			return "Done";
		} catch (NumberFormatException e) {

			e.printStackTrace();
			return "error";
		}
	}

	public String updateRSheet(RsheetsLibraryEntity rsheetName, Long idRsheet) {

		try {
			RsheetsLibraryEntity rSheet = rSheetsLibraryRepo.findById(idRsheet).orElse(null);

			if (rSheet != null && rsheetName != null) {
				rSheet.setBracedOrUnbraced(rsheetName.getBracedOrUnbraced());
				rSheet.setBracedOrUnbracedOther(rsheetName.getBracedOrUnbracedOther());
				rSheet.setComponentType(rsheetName.getComponentType());
				rSheet.setExposureCategory(rsheetName.getExposureCategory());
				rSheet.setFootingDiameter(rsheetName.getFootingDiameter());
				rSheet.setGroundRailRacking(rsheetName.getGroundRailRacking());
				rSheet.setManufacturer(rsheetName.getManufacturer());
				rSheet.setModel(rsheetName.getModel());
				rSheet.setModuleLayout(rsheetName.getModuleLayout());
				rSheet.setPipeSize(rsheetName.getPipeSize());
				rSheet.setSnowLoad(rsheetName.getSnowLoad());
				rSheet.setState(rsheetName.getState());
				rSheet.setThicknessPipe(rsheetName.getThicknessPipe());
				rSheet.setTiltRange(rsheetName.getTiltRange());
				rSheet.setWindSpeed(rsheetName.getWindSpeed());
				rSheet.setExposureCategoryOther(rsheetName.getExposureCategoryOther());
				rSheet.setFootingDiameterOther(rsheetName.getFootingDiameterOther());
				rSheet.setModuleLayoutOther(rsheetName.getModuleLayoutOther());
				rSheet.setPipeSizeOther(rsheetName.getPipeSizeOther());
				rSheet.setSnowLoadOther(rsheetName.getSnowLoadOther());
				rSheet.setThicknessPipeOther(rsheetName.getThicknessPipeOther());
				rSheet.setWindSpeedOther(rsheetName.getWindSpeedOther());
				rSheet.setAsceStandard(rsheetName.getAsceStandard());

				rSheetsLibraryRepo.save(rSheet);
			}

			return "Done";
		} catch (NumberFormatException e) {

			e.printStackTrace();
			return "error";
		}
	}

	// S.B CR-2419 Revision get closest R - Sheets
	public List<RsheetsLibraryEntity> getclosestRsheets(Long grounRailRacking) {
		try {
			RailRacking railRaking = railRackingRepo.findById(grounRailRacking).orElse(null);
			if (railRaking != null) {
				return rSheetsLibraryRepo.findByManufacturerAndModelAndIsDeletedFalse(railRaking.getManufacturer(),
						railRaking.getModel());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}
