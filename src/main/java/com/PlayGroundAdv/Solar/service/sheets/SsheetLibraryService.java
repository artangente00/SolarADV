
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
import com.PlayGroundAdv.Solar.entity.SsheetLibraryEntity;
import com.PlayGroundAdv.Solar.entity.SsheetRackingMappingEntity;
import com.PlayGroundAdv.Solar.entity.SsheetSpacingMappingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.SRsheetsModel;
import com.PlayGroundAdv.Solar.model.libraries.SheetPageRequest;
import com.PlayGroundAdv.Solar.repositories.sheets.SsheetLibraryRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.SsheetRackingMappingRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.SsheetSpacingMappingRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class SsheetLibraryService {

	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValue;
	final SsheetLibraryRepository sSheetLibraryRepo;
	final SsheetSpacingMappingRepository sSheetSpacingMappingRepo;
	final SsheetRackingMappingRepository sSheetRackingMappingRepo;
	final AuthenticationRepository userRepo;

	public SsheetLibraryService(NotificationEntityService notificationEntityService, CheckValueTypesService checkValue,
			SsheetLibraryRepository sSheetLibraryRepo, SsheetSpacingMappingRepository sSheetSpacingMappingRepo,
			SsheetRackingMappingRepository sSheetRackingMappingRepo, AuthenticationRepository userRepo) {
		super();
		this.notificationEntityService = notificationEntityService;
		this.checkValue = checkValue;
		this.sSheetLibraryRepo = sSheetLibraryRepo;
		this.sSheetSpacingMappingRepo = sSheetSpacingMappingRepo;
		this.sSheetRackingMappingRepo = sSheetRackingMappingRepo;
		this.userRepo = userRepo;
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
				if (request.getPdfName().contains("-")) {
					likeOr.addAll(Arrays.asList(request.getPdfName().toUpperCase().split("-")));
				}
			}

			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("pdfName"));
			Page<SsheetLibraryEntity> tldSheetLibrary = sSheetLibraryRepo
					.findAll(filter(likeOr, request.getIsDeleted()), pageable);
			return convertDto(tldSheetLibrary, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private Specification<SsheetLibraryEntity> filter(List<String> likeOr, Boolean deleted) {

		return new Specification<SsheetLibraryEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<SsheetLibraryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> pLikeOr = new ArrayList<>();

				for (int i = 0; likeOr != null && i < likeOr.size(); i++) {
					Predicate predicate = cb.like(cb.upper(root.get("pdfName")), "%" + likeOr.get(i) + "%");
					pLikeOr.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate predicateLikeOr = likeOr != null && !likeOr.isEmpty()
						? cb.or(pLikeOr.toArray(new Predicate[pLikeOr.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateDeleted, predicateLikeOr);
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	private Page<SRsheetsModel> convertDto(Page<SsheetLibraryEntity> page, Pageable pageable) {
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

	public String uploadHomePicture(MultipartFile file, String fileName, String siteSurveyiD) throws IOException {

		try {
			List<SsheetLibraryEntity> ss = sSheetLibraryRepo.findByPdfNameAndIsDeleted(fileName, false);
			if (ss != null && !ss.isEmpty()) {
				return "exist";
			} else {
				AuthentificationEntity addedBy = userRepo.findById(Long.valueOf(siteSurveyiD)).orElse(null);
				SsheetLibraryEntity sSheetsList = new SsheetLibraryEntity();
				sSheetsList.setPdfName(fileName);
				sSheetsList.setIsDeleted(false);
				sSheetsList.setLastUpdate(new Date());
				sSheetsList.setAddedBy(addedBy);

				byte[] bytes5 = file.getBytes();
				new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/").mkdirs();
				Path pathUp5 = Paths
						.get(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + fileName);
				Files.write(pathUp5, bytes5);
				sSheetLibraryRepo.save(sSheetsList);
				return "Done";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String deleteSSheet(Long sheetID, Long userID) {
		try {
			AuthentificationEntity deletedBy = userRepo.findById(userID).orElse(null);
			SsheetLibraryEntity sSheetsList = sSheetLibraryRepo.findById(sheetID).orElse(null);
			if (sSheetsList != null) {
				sSheetsList.setIsDeleted(true);
				sSheetsList.setDeletedOn(new Date());
				sSheetsList.setDeletedBy(deletedBy);

				List<SsheetSpacingMappingEntity> spacingSheet = sSheetLibraryRepo.getAllSsheetSpacing(sheetID);
				if (spacingSheet != null && !spacingSheet.isEmpty()) {
					for (SsheetSpacingMappingEntity sheet : spacingSheet) {
						sSheetSpacingMappingRepo.delete(sheet);
					}
				}

				List<SsheetRackingMappingEntity> rackingSheet = sSheetLibraryRepo.getAllSsheetRacking(sheetID);

				if (rackingSheet != null && !rackingSheet.isEmpty()) {
					for (SsheetRackingMappingEntity sheetR : rackingSheet) {
						sSheetRackingMappingRepo.delete(sheetR);
					}
				}

				// Rename the file when we deleted
				File oldFile = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/"
						+ sSheetsList.getPdfName());

				if (oldFile.exists()) {
					renameFile("New S200 S201 PDFs/" + sSheetsList.getPdfName(),
							"New S200 S201 PDFs/Delete" + sSheetsList.getPdfName());
				} else {
					oldFile = new File(
							Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + sSheetsList.getPdfName());
					if (oldFile.exists()) {
						renameFile("DPW SOLAR/" + sSheetsList.getPdfName(),
								"DPW SOLAR/Delete" + sSheetsList.getPdfName());
					} else {
						oldFile = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/"
								+ sSheetsList.getPdfName());
						if (oldFile.exists()) {
							renameFile("PROSOLAR ROOFTRAC/" + sSheetsList.getPdfName(),
									"PROSOLAR ROOFTRAC/Delete" + sSheetsList.getPdfName());
						} else {
							oldFile = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/"
									+ sSheetsList.getPdfName());
							if (oldFile.exists()) {
								renameFile("PROSOLAR TILETRAC/" + sSheetsList.getPdfName(),
										"PROSOLAR TILETRAC/Delete" + sSheetsList.getPdfName());
							} else {
								oldFile = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/"
										+ sSheetsList.getPdfName());
								if (oldFile.exists()) {
									renameFile("Stanchion Spacing/" + sSheetsList.getPdfName(),
											"Stanchion Spacing/Delete" + sSheetsList.getPdfName());
								}
							}
						}
					}

				}

				sSheetLibraryRepo.save(sSheetsList);
				deleteSSheetNotification(userID, sSheetsList.getPdfName());
			}

			return "done";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	public String editSSheet(MultipartFile file, String fileName, Long userId, Long fileId) {
		try {
			Boolean exist = sSheetLibraryRepo.existsByPdfNameAndIsDeletedFalseAndIdNotIn(fileName,
					Arrays.asList(fileId));
			if (Boolean.TRUE.equals(exist)) {
				return "exist";
			} else {
				SsheetLibraryEntity updatedSsheets = sSheetLibraryRepo.findById(fileId).orElse(null);
				if (updatedSsheets != null) {
					updatedSsheets.setPdfName(fileName);
					updatedSsheets.setIsDeleted(false);
					updatedSsheets.setLastUpdate(new Date());
					updatedSsheets.setUpdatedBy(userRepo.findById(userId).orElse(null));
					sSheetLibraryRepo.save(updatedSsheets);

					byte[] bytes5 = file.getBytes();
					new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/").mkdirs();
					Path pathUp5 = Paths
							.get(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + fileName);
					Files.write(pathUp5, bytes5);
					editSSheetNotification(userId, fileName);
					return "Done";
				}
			}
			return "fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String restoreSsheet(Long sheetID) {

		try {

			SsheetLibraryEntity sSheetsList = sSheetLibraryRepo.findById(sheetID)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + sheetID));
			Boolean exist = sSheetLibraryRepo.existsByPdfNameAndIsDeletedFalse(sSheetsList.getPdfName());
			if (Boolean.FALSE.equals(exist)) {

				sSheetsList.setIsDeleted(false);

				// Rename the file when we deleted
				File oldFile = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/"
						+ "Delete" + sSheetsList.getPdfName());

				if (oldFile.exists()) {
					renameFile("New S200 S201 PDFs/Delete" + sSheetsList.getPdfName(),
							"New S200 S201 PDFs/" + sSheetsList.getPdfName());
				} else {
					oldFile = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + "Delete"
							+ sSheetsList.getPdfName());
					if (oldFile.exists()) {
						renameFile("DPW SOLAR/Delete" + sSheetsList.getPdfName(),
								"DPW SOLAR/" + sSheetsList.getPdfName());
					} else {
						oldFile = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/"
								+ "Delete" + sSheetsList.getPdfName());
						if (oldFile.exists()) {
							renameFile("PROSOLAR ROOFTRAC/Delete" + sSheetsList.getPdfName(),
									"PROSOLAR ROOFTRAC/" + sSheetsList.getPdfName());
						} else {
							oldFile = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/"
									+ "Delete" + sSheetsList.getPdfName());
							if (oldFile.exists()) {
								renameFile("PROSOLAR TILETRAC/Delete" + sSheetsList.getPdfName(),
										"PROSOLAR TILETRAC/" + sSheetsList.getPdfName());
							} else {
								oldFile = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/"
										+ "Delete" + sSheetsList.getPdfName());
								if (oldFile.exists()) {
									renameFile("Stanchion Spacing/Delete" + sSheetsList.getPdfName(),
											"Stanchion Spacing/" + sSheetsList.getPdfName());
								}
							}
						}
					}

				}

				sSheetLibraryRepo.save(sSheetsList);
				return "done";
			}
			return "exist";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	private String renameFile(String o, String n) {
		File newfile = new File(Constants.rapportRSheetFolderUrl + "PlansetS200S201/" + n);
		File oldFile = new File(Constants.rapportRSheetFolderUrl + "PlansetS200S201/" + o);
		if (oldFile.exists() && oldFile.renameTo(newfile)) {
			return "done";
		}
		return "fail";
	}

	private String editSSheetNotification(Long userID, String pdfName) {
		try {
			AuthentificationEntity userCo = userRepo.findById(userID).orElse(null);
			if (userCo != null) {
				notificationEntityService.addNewNotif(userID, 0L, "Update-S-sheet", "Libraries", "Update-S-sheet",
						"The " + userCo.getRoleEntity().getDescription() + " " + userCo.getFirstName() + " "
								+ userCo.getLastName() + " updated The S-sheet " + pdfName,
						true);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	private String deleteSSheetNotification(Long userID, String pdfName) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID).orElse(null);
			if (userCo != null) {
				notificationEntityService.addNewNotif(userID, 0L, "Delete-S-sheet", "Libraries", "Delete-S-sheet",
						"The " + userCo.getRoleEntity().getDescription() + " " + userCo.getFirstName() + " "
								+ userCo.getLastName() + " deleted The S-sheet " + pdfName,
						true);
			}
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
}
