package com.PlayGroundAdv.Solar.service.sheets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.PermitAdditionalInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitArraysEntity;
import com.PlayGroundAdv.Solar.entity.PermitCustomizedSheetsEntity;
import com.PlayGroundAdv.Solar.entity.PermitEngineerEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PlansetCustomizeSheets;
import com.PlayGroundAdv.Solar.entity.PlansetSheetsLog;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJChecklistEntity;
import com.PlayGroundAdv.Solar.entity.sheets.UserCustomizeSheets;
import com.PlayGroundAdv.Solar.model.CustomizeSheetsModel;
import com.PlayGroundAdv.Solar.model.DuplicateCustomizeSheetsModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.SheetPageRequest;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdditionalInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEngineerRepository;
import com.PlayGroundAdv.Solar.repositories.PermitHomeSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.PlansetSheetsLogRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ElectricalUtilityRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PermitCustomizedSheetsRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PlansetCustomizeSheetsRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.UserCustomizeSheetsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.ahj.PlansetNotesService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class CustomizeSheetsService {

	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final RailRackingRepository railRackingRepo;
	final RailRackingFavoriteRepository railRackingFavoritesRepo;
	final PlansetCustomizeSheetsRepository customizeRepo;
	final AuthenticationRepository userRepo;
	final BatteryRepository batteryRepo;
	final RoofMaterialTypeRepository roofMaterialTypeRep;
	final PathRepository pathRepo;
	final PlansetSheetsLogRepository sheetLogRepo;
	final PermitRepository projectRepo;
	final PlansetNotesService plansetNotesService;
	final PermitHomeSiteInfoRepository homeSiteRepo;
	final PermitArraysRepository arraysRepo;
	final PermitAdditionalInfoRepository additionalInfoRepo;
	final PermitProjectSiteInfoRepository projectSiteInfoRepo;
	final PermitEngineerRepository engineersRepo;
	final PermitCustomizedSheetsRepository permitCustomizedSheetsRepo;
	final ElectricalUtilityRepository electricalUtilityRepo;
	final UserCustomizeSheetsRepository userCustomieSheetRepo;

	public CustomizeSheetsService(NotificationEntityService notificationEntityService,
			CheckValueTypesService checkValue, GetPDFReaderService getPDFReaderService,
			RailRackingRepository railRackingRepo, RailRackingFavoriteRepository railRackingFavoritesRepo,
			PlansetCustomizeSheetsRepository customizeRepo, AuthenticationRepository userRepo,
			BatteryRepository batteryRepo, RoofMaterialTypeRepository roofMaterialTypeRep, PathRepository pathRepo,
			PlansetSheetsLogRepository sheetLogRepo, PermitRepository projectRepo,
			PlansetNotesService plansetNotesService, PermitHomeSiteInfoRepository homeSiteRepo,
			PermitArraysRepository arraysRepo, PermitAdditionalInfoRepository additionalInfoRepo,
			PermitProjectSiteInfoRepository projectSiteInfoRepo, PermitEngineerRepository engineersRepo,
			PermitCustomizedSheetsRepository permitCustomizedSheetsRepo,
			ElectricalUtilityRepository electricalUtilityRepo, UserCustomizeSheetsRepository userCustomieSheetRepo) {
		super();
		this.notificationEntityService = notificationEntityService;
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.railRackingRepo = railRackingRepo;
		this.railRackingFavoritesRepo = railRackingFavoritesRepo;
		this.customizeRepo = customizeRepo;
		this.userRepo = userRepo;
		this.batteryRepo = batteryRepo;
		this.roofMaterialTypeRep = roofMaterialTypeRep;
		this.pathRepo = pathRepo;
		this.sheetLogRepo = sheetLogRepo;
		this.projectRepo = projectRepo;
		this.plansetNotesService = plansetNotesService;
		this.homeSiteRepo = homeSiteRepo;
		this.arraysRepo = arraysRepo;
		this.additionalInfoRepo = additionalInfoRepo;
		this.projectSiteInfoRepo = projectSiteInfoRepo;
		this.engineersRepo = engineersRepo;
		this.permitCustomizedSheetsRepo = permitCustomizedSheetsRepo;
		this.electricalUtilityRepo = electricalUtilityRepo;
		this.userCustomieSheetRepo = userCustomieSheetRepo;
	}

	public Page<CustomizeSheetsModel> filter(SheetPageRequest customizeSheet) {

		try {
			Pageable pageable = PageRequest.of(customizeSheet.getPage(), customizeSheet.getSize(), Sort.by("PdfName"));

			String pdfName = checkValue.isStringNotEmpty(customizeSheet.getPdfName())
					? customizeSheet.getPdfName().trim().toLowerCase()
					: null;
			String utilityCompany = checkValue.isStringNotEmpty(customizeSheet.getUtilityCompany())
					? customizeSheet.getUtilityCompany().trim().toLowerCase()
					: null;
			String individualAHJ = checkValue.isStringNotEmpty(customizeSheet.getIndividualAHJ())
					? customizeSheet.getIndividualAHJ().trim().toLowerCase()
					: null;
			Long userId = checkValue.isNumericNotZero(customizeSheet.getIdUser())
					? Long.valueOf(customizeSheet.getIdUser())
					: null;
			if (pdfName == null && utilityCompany == null && individualAHJ == null && userId == null
					&& !Boolean.TRUE.equals(customizeSheet.getIsDeleted())) {
				Page<PlansetCustomizeSheets> p = customizeRepo.findByIsDeleted(customizeSheet.getIsDeleted(), pageable);
				return convertDto(p, pageable, customizeSheet.getPage(), customizeSheet.getSize());
			} else {
				Page<PlansetCustomizeSheets> p = customizeRepo.findAll(
						filter(pdfName, utilityCompany, individualAHJ, userId, customizeSheet.getIsDeleted()),
						pageable);
				return convertDto(p, pageable, customizeSheet.getPage(), customizeSheet.getSize());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private Page<CustomizeSheetsModel> convertDto(Page<PlansetCustomizeSheets> page, Pageable pageable, Integer pages,
			Integer size) {
		try {
			return new PageImpl<>(
					page.stream()
							.map(c -> new CustomizeSheetsModel(c.getId(), c.getPdfName(), c.getUtilityCompany(),
									c.getAhj(),
									c.getUserList() != null ? c.getUserList().stream().map(u -> u.getUserId().getId())
											.collect(Collectors.toList()) : new ArrayList<>(),
									c.getStates(), c.getBasicSystemType(), c.getBasicSystemTypeOther(),
									c.getInverterTechnology(),
									c.getBatteryInSystem() != null ? c.getBatteryInSystem().getId() : null,
									c.getBatteryInSystem() != null ? c.getBatteryInSystem().getManufacturer() + " "
											+ c.getBatteryInSystem().getModel() : "",
									c.getRoofType(), c.getRoofTypeOther(),
									c.getRoofingMaterialType() != null ? c.getRoofingMaterialType().getId() : null,
									c.getRoofingMaterialType() != null ? c.getRoofingMaterialType().getTypeRoof() : "",
									c.getRailRackingModel() != null ? c.getRailRackingModel().getId() : null,
									c.getRailRackingModel() != null ? c.getRailRackingModel().getManufacturer() + " "
											+ c.getRailRackingModel().getModel() : "",
									c.getElectEngStructEng(), c.getLastUpdate(),
									c.getUserList() != null
											? c.getUserList().stream()
													.map(u -> u.getUserId().getFirstName() + " "
															+ u.getUserId().getLastName())
													.collect(Collectors.toList())
											: new ArrayList<>(),
									c.getDeletedBy() != null
											? c.getDeletedBy().getFirstName() + " " + c.getDeletedBy().getLastName()
											: "",
									c.getDeletedOn(),
									c.getUpdatedBy() != null
											? c.getUpdatedBy().getFirstName() + " " + c.getUpdatedBy().getLastName()
											: "",
									c.getNecCode(), c.getIfcCode(), pages, size))
							.collect(Collectors.toList()),
					pageable, page.getTotalElements());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Specification<PlansetCustomizeSheets> filter(String pdfName, String utilityCompany, String individualAHJ,
			Long userId, Boolean deleted) {

		return new Specification<PlansetCustomizeSheets>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<PlansetCustomizeSheets> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate predicatePdfName = pdfName != null
						? cb.like(cb.lower(root.get("pdfName")), "%" + pdfName + "%")
						: null;
				Predicate predicateUtility = utilityCompany != null
						? cb.like(cb.lower(root.get("utilityCompany")), "%" + utilityCompany + "%")
						: null;
				Predicate predicateIndividualAHJ = individualAHJ != null
						? cb.like(cb.lower(root.get("ahj").as(String.class)), "%" + individualAHJ + "%")
						: null;
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				List<Predicate> list = Arrays.asList(predicatePdfName, predicateUtility, predicateDeleted,
						predicateIndividualAHJ);
				if (userId != null) {
					Join<PlansetCustomizeSheets, UserCustomizeSheets> ul = root.join("userList");
					Join<UserCustomizeSheets, AuthentificationEntity> ucl = ul.join("userId");
					Predicate predicateUserId = cb.equal(ucl.get("id"), userId);
					list = Arrays.asList(predicatePdfName, predicateUtility, predicateDeleted, predicateIndividualAHJ,
							predicateUserId);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	public String addCustomizeSheet(CustomizeSheetsModel newCustomizeSheet, Long id) {

		try {

			AuthentificationEntity addedBy = userRepo.findById(id).orElse(new AuthentificationEntity());
			PlansetCustomizeSheets customizeSheet = new PlansetCustomizeSheets();

			if (newCustomizeSheet.getUsers() != null && !newCustomizeSheet.getUsers().isEmpty()) {
				customizeSheet.setUserList(new ArrayList<>());
				for (Long idUser : newCustomizeSheet.getUsers()) {
					AuthentificationEntity user = checkValue.isLongPositive(idUser)
							? userRepo.findById(idUser).orElse(null)
							: null;
					if (user != null) {
						customizeSheet.addUser(user);
					}
				}
			}

			if (checkValue.isNumeric(newCustomizeSheet.getBatteryInSystem() + "")) {
				Battery battery = batteryRepo.findById(newCustomizeSheet.getBatteryInSystem()).orElse(null);
				customizeSheet.setBatteryInSystem(battery);
			}

			if (checkValue.isNumeric(newCustomizeSheet.getRoofingMaterialType() + "")) {

				RoofMaterialType roofMaterialType = roofMaterialTypeRep
						.findById(newCustomizeSheet.getRoofingMaterialType()).orElse(null);
				customizeSheet.setRoofingMaterialType(roofMaterialType);
			}

			if (checkValue.isNumeric(newCustomizeSheet.getRailRackingModel() + "")) {
				RailRacking railRacking = railRackingRepo.findById(id).orElse(null);
				customizeSheet.setRailRackingModel(railRacking);
			}
			if (newCustomizeSheet.getIndividualAHJ() != null && !newCustomizeSheet.getIndividualAHJ().isEmpty()) {
				customizeSheet.setAhj(newCustomizeSheet.getIndividualAHJ());
			} else {
				customizeSheet.setAhj(null);
			}
			if (newCustomizeSheet.getState() != null && !newCustomizeSheet.getState().isEmpty()) {
				customizeSheet.setStates(newCustomizeSheet.getState());
			} else {
				customizeSheet.setStates(null);
			}

			customizeSheet.setPdfName(newCustomizeSheet.getPdfName());
			customizeSheet.setUtilityCompany(newCustomizeSheet.getUtilityCompany());
			customizeSheet.setBasicSystemType(newCustomizeSheet.getBasicSystemType());
			customizeSheet.setBasicSystemTypeOther(checkValue.Equals(newCustomizeSheet.getBasicSystemType(), "Other")
					? newCustomizeSheet.getBasicSystemTypeOther()
					: "");
			customizeSheet.setInverterTechnology(newCustomizeSheet.getInverterTechnology());
			customizeSheet.setRoofType(newCustomizeSheet.getRoofType());
			customizeSheet.setRoofTypeOther(
					checkValue.Equals(newCustomizeSheet.getRoofType(), "Other") ? newCustomizeSheet.getRoofTypeOther()
							: "");
			customizeSheet.setElectEngStructEng(newCustomizeSheet.getElectEngStructEng());
			customizeSheet.setNecCode(newCustomizeSheet.getNecCode());
			customizeSheet.setIfcCode(newCustomizeSheet.getIfcCode());
			customizeSheet.setLastUpdate(new Date());
			customizeSheet.setAddedBy(addedBy);
			customizeSheet.setIsDeleted(false);
			customizeRepo.save(customizeSheet);

			String logicId = customizeSheet.getId() + "";
			String server = pathRepo.findURLPath();
			if (checkValue.Equals(server, "production")
					&& (checkValue.Equals(addedBy.getEmail(), "pm@nuagetechnologies-tn.com")
							|| !checkValue.contains(addedBy.getEmail(), "nuagetechnologies-tn.com"))) {
				PlansetSheetsLog sheetLog = new PlansetSheetsLog(newCustomizeSheet.getPdfName(), "Customize", "Add",
						new Date(), customizeSheet.getId(), addedBy);
				sheetLogRepo.save(sheetLog);
			}

			return logicId;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String addLogic(MultipartFile file, String logicId) throws IOException {

		try {
			byte[] bytes5 = file.getBytes();
			new File(Constants.rapportPlansetFolderUrl + "PlansetCustomizeSheets/").mkdirs();
			Path pathUp5 = Paths.get(Constants.rapportPlansetFolderUrl + "PlansetCustomizeSheets/" + logicId + ".pdf");
			Files.write(pathUp5, bytes5);
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public List<UsersEntityResult> getUsersNames() {
		return userRepo.getNotDeletedUserName();
	}

	public String editCustomizeSheet(CustomizeSheetsModel newCustomizeSheet, Long id) {

		try {
			AuthentificationEntity addedBy = userRepo.findById(id).orElse(new AuthentificationEntity());
			PlansetCustomizeSheets customizeSheet = customizeRepo.findById(newCustomizeSheet.getId())
					.orElse(new PlansetCustomizeSheets());

			if (newCustomizeSheet.getUsers() != null && !newCustomizeSheet.getUsers().isEmpty()) {
				List<Long> users = userCustomieSheetRepo.findBySheet(customizeSheet.getId());
				List<Long> toAdd = newCustomizeSheet.getUsers().stream().filter(f -> !users.contains(f))
						.collect(Collectors.toList());
				userCustomieSheetRepo.deleteBySheetIdIdAndUserIdIdNotIn(customizeSheet.getId(),
						newCustomizeSheet.getUsers());
				customizeSheet.setUserList(new ArrayList<>());
				for (Long idUser : toAdd) {
					AuthentificationEntity user = checkValue.isLongPositive(idUser)
							? userRepo.findById(idUser).orElse(null)
							: null;
					if (user != null) {
						customizeSheet.addUser(user);
					}
				}
			} else {
				userCustomieSheetRepo.deleteBySheetIdId(customizeSheet.getId());
			}
			if (checkValue.isNumeric(newCustomizeSheet.getBatteryInSystem() + "")) {
				Battery battery = batteryRepo.findById(newCustomizeSheet.getBatteryInSystem()).orElse(null);
				customizeSheet.setBatteryInSystem(battery);
			} else {
				customizeSheet.setBatteryInSystem(null);
			}

			if (checkValue.isNumeric(newCustomizeSheet.getRoofingMaterialType() + "")) {
				RoofMaterialType roofMaterialType = roofMaterialTypeRep
						.findById(newCustomizeSheet.getRoofingMaterialType()).orElse(null);
				customizeSheet.setRoofingMaterialType(roofMaterialType);
			} else {
				customizeSheet.setRoofingMaterialType(null);
			}

			if (checkValue.isNumeric(newCustomizeSheet.getRailRackingModel() + "")) {
				RailRacking railRacking = railRackingRepo.findById(newCustomizeSheet.getRailRackingModel())
						.orElse(null);
				customizeSheet.setRailRackingModel(railRacking);
			} else {
				customizeSheet.setRailRackingModel(null);
			}

			if (newCustomizeSheet.getIndividualAHJ() != null && !newCustomizeSheet.getIndividualAHJ().isEmpty()) {
				customizeSheet.setAhj(newCustomizeSheet.getIndividualAHJ());
			} else {
				customizeSheet.setAhj(null);
			}

			if (newCustomizeSheet.getState() != null && !newCustomizeSheet.getState().isEmpty()) {
				customizeSheet.setStates(newCustomizeSheet.getState());
			} else {
				customizeSheet.setStates(null);
			}

			customizeSheet.setPdfName(newCustomizeSheet.getPdfName());
			customizeSheet.setUtilityCompany(newCustomizeSheet.getUtilityCompany());
			customizeSheet.setBasicSystemType(newCustomizeSheet.getBasicSystemType());
			customizeSheet.setBasicSystemTypeOther(checkValue.Equals(newCustomizeSheet.getBasicSystemType(), "Other")
					? newCustomizeSheet.getBasicSystemTypeOther()
					: "");
			customizeSheet.setInverterTechnology(newCustomizeSheet.getInverterTechnology());
			customizeSheet.setRoofType(newCustomizeSheet.getRoofType());
			customizeSheet.setRoofTypeOther(
					checkValue.Equals(newCustomizeSheet.getRoofType(), "Other") ? newCustomizeSheet.getRoofTypeOther()
							: "");
			customizeSheet.setElectEngStructEng(newCustomizeSheet.getElectEngStructEng());
			customizeSheet.setNecCode(newCustomizeSheet.getNecCode());
			customizeSheet.setIfcCode(newCustomizeSheet.getIfcCode());
			customizeSheet.setLastUpdate(new Date());
			customizeSheet.setUpdatedBy(addedBy);
			customizeRepo.save(customizeSheet);

			String logicId = customizeSheet.getId() + "";

			notificationEntityService.addNewNotif(id, 0L, "Edit Customize Sheet", "Libraries", "Edit Customize Sheet",
					"The " + addedBy.getRoleEntity().getDescription() + " " + addedBy.getFirstName() + " "
							+ addedBy.getLastName() + " updated The Planset sheet  " + customizeSheet.getPdfName(),
					true);
			String server = pathRepo.findURLPath();
			if (checkValue.Equals(server, "production")
					&& (checkValue.Equals(addedBy.getEmail(), "pm@nuagetechnologies-tn.com")
							|| !checkValue.contains(addedBy.getEmail(), "nuagetechnologies-tn.com"))) {
				PlansetSheetsLog sheetLog = new PlansetSheetsLog(newCustomizeSheet.getPdfName(), "Customize", "Edit",
						new Date(), customizeSheet.getId(), addedBy);
				sheetLogRepo.save(sheetLog);
			}

			return logicId;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String deleteCustomizeSheet(Long sheetID, Long userID) {
		try {
			AuthentificationEntity deletedBy = userRepo.findById(userID).orElse(new AuthentificationEntity());
			PlansetCustomizeSheets customizeSheet = customizeRepo.findById(sheetID)
					.orElse(new PlansetCustomizeSheets());
			customizeSheet.setIsDeleted(true);
			customizeSheet.setDeletedBy(deletedBy);
			customizeSheet.setDeletedOn(new Date());
			customizeRepo.save(customizeSheet);

			notificationEntityService.addNewNotif(userID, 0L, "Delete Customize Sheet", "Libraries",
					"Delete Customize Sheet",
					"The " + deletedBy.getRoleEntity().getDescription() + " " + deletedBy.getFirstName() + " "
							+ deletedBy.getLastName() + " deleted The Planset PDF " + customizeSheet.getPdfName(),
					true);

			String server = pathRepo.findURLPath();
			if (checkValue.Equals(server, "production")
					&& (checkValue.Equals(deletedBy.getEmail(), "pm@nuagetechnologies-tn.com")
							|| !checkValue.contains(deletedBy.getEmail(), "nuagetechnologies-tn.com"))) {
				PlansetSheetsLog sheetLog = new PlansetSheetsLog(customizeSheet.getPdfName(), "Customize", "Delete",
						new Date(), customizeSheet.getId(), deletedBy);
				sheetLogRepo.save(sheetLog);
			}
			return "done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String deletePermanetCustSheet(Long sheetID) {
		try {

			PlansetCustomizeSheets customizeSheet = customizeRepo.findById(sheetID)
					.orElse(new PlansetCustomizeSheets());
			customizeRepo.delete(customizeSheet);
			File oldFile = new File(
					Constants.rapportPlansetFolderUrl + "PlansetCustomizeSheets/" + customizeSheet.getId() + ".pdf");
			if (oldFile.exists()) {
				Path path = Paths.get((String) Constants.rapportPlansetFolderUrl + "PlansetCustomizeSheets/"
						+ customizeSheet.getId() + ".pdf");
				Files.delete(path);
			}
			return "done";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	public String restoreCustomizesheet(Long sheetID) {

		try {
			PlansetCustomizeSheets customizeSheet = customizeRepo.findById(sheetID)
					.orElse(new PlansetCustomizeSheets());
			;
			customizeSheet.setIsDeleted(false);
			customizeRepo.save(customizeSheet);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "done";

	}

	// A.B: CR-2620 Customize sheet Parameter Initiate
	public void getCompatileCustomizeSheets(Long idPermit) {

		try {
			// A.B: Proceed only if the Project ID is valid
			if (idPermit != null) {
				PermitEntity permitEntity = projectRepo.findById(idPermit).orElse(new PermitEntity());

				try {
					if (permitEntity != null) {

						// A.B: Proceed only if the Project Exist
						PermitHomeSiteInfoEntity permitHomeSite = homeSiteRepo.findByPermitEntityId(idPermit);
						PermitArraysEntity permitArrays = arraysRepo.findByPermitEntityId(idPermit);
						PermitAdditionalInfoEntity permitAdditionalInfo = additionalInfoRepo
								.findByPermitEntityId(idPermit);
						PermitProjectSiteInfoEntity permitProjectSiteInfo = projectSiteInfoRepo
								.findByPermitEntityId(idPermit);
						PermitEngineerEntity engineers = engineersRepo.findByPermitEntityId(idPermit);

						// A.B: Remove the current compatible s sheet list from Permit Customized Sheets
						// Entity
						permitCustomizedSheetsRepo.deleteByProjectId(idPermit);
						String utilityCompany = "";

						if (checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other")) {

							utilityCompany = permitHomeSite.getUtilityCompanyNameOther();

						} else if (checkValue.isNumericNotZero(permitHomeSite.getUtilityCompanyName())) {
							ElectricalUtilityEntity utilityCompanyEntity = electricalUtilityRepo
									.findById(Long.parseLong(permitHomeSite.getUtilityCompanyName()))
									.orElse(new ElectricalUtilityEntity());
							utilityCompany = utilityCompanyEntity != null ? utilityCompanyEntity.getUtilityCompanyName()
									: "";
						}

						String individualAHJ = "";
						String ahjName = "";
						String cityOf = "City of ";
						String countyOf = "County of ";

						Long ahjId = plansetNotesService.getAHJId(permitHomeSite.getState(), permitHomeSite.getCity(),
								permitHomeSite.getCityOther(), permitHomeSite.getProjectJurisdiction(),
								permitHomeSite.getProjectJurisOther());
						AHJChecklistEntity governingCodes = ahjId != null ? plansetNotesService.getAHJ(ahjId) : null;

						if (checkValue.Equals(permitHomeSite.getProjectJurisdiction(), "city")) {

							if (checkValue.NotEquals(permitHomeSite.getCity(), "Other"))
								individualAHJ = cityOf + permitHomeSite.getCity();
							else if (checkValue.NotEquals(permitHomeSite.getProjectJurisOther(), ""))
								individualAHJ = cityOf + permitHomeSite.getProjectJurisOther();
							else
								individualAHJ = cityOf;
							if (governingCodes != null) {
								ahjName = governingCodes.getAhj();
							}
						} else if (checkValue.contains(permitHomeSite.getProjectJurisdiction(), "County")) {

							if (checkValue.NotEquals(permitHomeSite.getCity(), "Other"))
								individualAHJ = permitHomeSite.getProjectJurisdiction();
							else if (checkValue.NotEquals(permitHomeSite.getProjectJurisOther(), ""))
								individualAHJ = countyOf + permitHomeSite.getProjectJurisOther();
							else
								individualAHJ = countyOf;

							if (governingCodes != null) {
								ahjName = governingCodes.getCounty();
							}
						} else if (checkValue.Equals(permitHomeSite.getProjectJurisdiction(), "Other")) {
							individualAHJ = permitHomeSite.getProjectJurisOther();
							ahjName = permitHomeSite.getProjectJurisOther();
						}

						Long batteryID = checkValue.isNumeric(permitAdditionalInfo.getBattery())
								? Long.valueOf(permitAdditionalInfo.getBattery())
								: null;
						Long roofMaterialTypeID = permitProjectSiteInfo.getRoofMaterialType();
						Long railRackingRoofID = (permitProjectSiteInfo.getRailRakingModel() != null
								&& railRackingFavoritesRepo
										.existsByRailRackingId(permitProjectSiteInfo.getRailRakingModel().getId())
								&& railRackingRepo.existsByIdAndIsDeleted(
										permitProjectSiteInfo.getRailRakingModel().getId(), false))
												? permitProjectSiteInfo.getRailRakingModel().getId()
												: null;
						Long railRackingGroundID = (permitProjectSiteInfo.getRailRakingModelforGroundMounted() != null
								&& railRackingFavoritesRepo.existsByRailRackingId(
										permitProjectSiteInfo.getRailRakingModelforGroundMounted().getId())
								&& railRackingRepo.existsByIdAndIsDeleted(
										permitProjectSiteInfo.getRailRakingModelforGroundMounted().getId(), false))
												? permitProjectSiteInfo.getRailRakingModelforGroundMounted().getId()
												: null;
						Boolean stateRev = getPDFReaderService.checkStateResevation(permitHomeSite.getState(),
								permitEntity);
						String exrafterOrTruss = (checkValue.Equals(permitHomeSite.getRoofRafter(),
								"Pre-Eng Roof Trusses")
								|| checkValue.Equals(permitHomeSite.getRoofRafter(), "OtherRoof")) ? "RAFTER" : "TRUSS";
						String projectRoofDesign = (checkValue.Equals(permitHomeSite.getRoofRafter(),
								"Pre-Eng Roof Trusses")
								|| checkValue.Equals(permitHomeSite.getRoofRafter(), "OtherRoof")) ? "TRUSS" : "RAFTER";
						String exinverterTechnology = (checkValue.Equals(permitArrays.getDeviceToIncorporate(),
								"Micro Inverter")
								|| checkValue.Equals(permitArrays.getDeviceToIncorporate(), "AC Modules")) ? "STRING"
										: "MICRO";
						String inverterTechnology = (checkValue.Equals(permitArrays.getDeviceToIncorporate(),
								"Micro Inverter")
								|| checkValue.Equals(permitArrays.getDeviceToIncorporate(), "AC Modules")) ? "MICRO"
										: "STRING";

//						A.B 11-30-2022 REV-CR-PM-265-MOD-004: Map Customized sheets according to the default governing codes
						String nec = getNecCode(governingCodes, permitHomeSite.getState());
						String ifc = getIfcCode(governingCodes, permitHomeSite.getState());
					
						if (checkValue.Equals(stateRev, false)) {
							List<String> listPDFwithRev = new ArrayList<>();
							listPDFwithRev.add("PDF-E001-" + exinverterTechnology);
							listPDFwithRev.add("PDF-E002-" + exinverterTechnology);
							listPDFwithRev.add("PDF-E003-" + exinverterTechnology);
							listPDFwithRev.add("PDF-E100-" + exinverterTechnology);
							listPDFwithRev.add("PDF-P002-" + exinverterTechnology);
							listPDFwithRev.add("PDF-S200-" + exrafterOrTruss);

							List<PlansetCustomizeSheets> sheetLogic = customizeRepo.findCustomizeSheetByProjectNotIn(
									listPDFwithRev, utilityCompany != null ? utilityCompany.toUpperCase() : null,
									permitEntity.getAuthentificationEntity().getId(), permitArrays.getRoofMounted(),
									permitArrays.getGroundMounted(), permitArrays.getPatioMounted(),
									permitArrays.getCarportMounted(), permitArrays.getTextOther(),
									permitArrays.getDeviceToIncorporate(), batteryID, permitHomeSite.getRoofRafter(),
									permitHomeSite.getRoofRafterOther(), roofMaterialTypeID, railRackingRoofID,
									railRackingGroundID, engineers.getApplicablEngineering(),
									permitHomeSite.getSecroofRafterOther(), nec, ifc);

							// A.B CR-265 Filter By State
							sheetLogic = sheetLogic.stream()
									.filter(f -> f.getStates() == null
											|| containsCaseInsensitive(permitHomeSite.getState(), f.getStates()))
									.collect(Collectors.toList());

							// A.B CR-265 Filter By AHJ
							if (individualAHJ != null) {
								String ahj = individualAHJ;
								String ahjN = ahjName;
								sheetLogic = sheetLogic.stream()
										.filter(f -> f.getAhj() == null || containsCaseInsensitive(ahj, f.getAhj())
												|| containsCaseInsensitive(ahjN, f.getAhj()))
										.collect(Collectors.toList());
							}

							if (!sheetLogic.isEmpty()) {
								for (int i = 0; i < sheetLogic.size(); i++) {
									PermitCustomizedSheetsEntity permitCustomizedSheet = new PermitCustomizedSheetsEntity();
									permitCustomizedSheet.setProject(permitEntity);
									permitCustomizedSheet.setSheet(sheetLogic.get(i));
									permitCustomizedSheet.setMasterSheet(false);
									permitCustomizedSheetsRepo.save(permitCustomizedSheet);
								}

							}
						} else {
							List<String> listPDFwithRev = new ArrayList<>();
							Boolean inverterTechnologyRev = getPDFReaderService.checkInvTechnologyRemoveResevation(
									permitArrays.getDeviceToIncorporate(), permitEntity);
							Boolean roofRafterRev = getPDFReaderService
									.checkRoofRafterRemoveResevation(projectRoofDesign, permitEntity);

							if (checkValue.Equals(inverterTechnologyRev, true)) {
								listPDFwithRev.add("PDF-E001-" + inverterTechnology);
								listPDFwithRev.add("PDF-E002-" + inverterTechnology);
								listPDFwithRev.add("PDF-E003-" + inverterTechnology);
								listPDFwithRev.add("PDF-E100-" + inverterTechnology);
								listPDFwithRev.add("PDF-P002-" + inverterTechnology);
							}

							if (checkValue.Equals(roofRafterRev, true)) {
								listPDFwithRev.add("PDF-S200-" + projectRoofDesign);
							}

							if (!listPDFwithRev.isEmpty()) {
								List<PlansetCustomizeSheets> sheetLogic = customizeRepo.findCustomizeSheetByProjectIn(
										listPDFwithRev, utilityCompany != null ? utilityCompany.toUpperCase() : null,
										permitEntity.getAuthentificationEntity().getId(), permitArrays.getRoofMounted(),
										permitArrays.getGroundMounted(), permitArrays.getPatioMounted(),
										permitArrays.getCarportMounted(), permitArrays.getTextOther(),
										permitArrays.getDeviceToIncorporate(), batteryID,
										permitHomeSite.getRoofRafter(), permitHomeSite.getRoofRafterOther(),
										roofMaterialTypeID, railRackingRoofID, railRackingGroundID,
										engineers.getApplicablEngineering(), permitHomeSite.getSecroofRafterOther(),
										nec, ifc);

								// A.B CR-265 Filter By State
								sheetLogic = sheetLogic.stream()
										.filter(f -> f.getStates() == null || f.getStates().isEmpty()
												|| containsCaseInsensitive(permitHomeSite.getState(), f.getStates()))
										.collect(Collectors.toList());

								// A.B CR-265 Filter By AHJ
								if (individualAHJ != null) {
									String ahj = individualAHJ;
									String ahjN = ahjName;
									sheetLogic = sheetLogic.stream()
											.filter(f -> f.getAhj() == null || f.getAhj().isEmpty()
													|| containsCaseInsensitive(ahj, f.getAhj())
													|| containsCaseInsensitive(ahjN, f.getAhj()))
											.collect(Collectors.toList());
								}

								if (!sheetLogic.isEmpty()) {
									for (int i = 0; i < sheetLogic.size(); i++) {
										PermitCustomizedSheetsEntity permitCustomizedSheet = new PermitCustomizedSheetsEntity();
										permitCustomizedSheet.setProject(permitEntity);
										permitCustomizedSheet.setSheet(sheetLogic.get(i));
										permitCustomizedSheet.setMasterSheet(false);
										permitCustomizedSheetsRepo.save(permitCustomizedSheet);
									}

								}
							}

						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean containsCaseInsensitive(String s, List<String> l) {
		return l.stream().anyMatch(x -> x.equalsIgnoreCase(s));
	}

	public boolean likeCaseInsensitive(String s, List<String> l) {
		return l.stream().anyMatch(x -> x.toUpperCase().contains(s));
	}

	// A.B: CR-2620 Customize sheet Parameter Initiate
	public HashMap<String, List<DuplicateCustomizeSheetsModel>> getDuplicateCompatileCustomizeSheets(Long idPermit) {

		HashMap<String, List<DuplicateCustomizeSheetsModel>> customizeSheets = new HashMap<>();
		try {
			List<PermitCustomizedSheetsEntity> customizedSheets = permitCustomizedSheetsRepo
					.findDuplicateCompatileCustomizeSheets(idPermit);

			if (!customizedSheets.isEmpty()) {

				for (int i = 0; i < customizedSheets.size(); i++) {
					DuplicateCustomizeSheetsModel sheet = new DuplicateCustomizeSheetsModel();

					sheet.setId(customizedSheets.get(i).getId());
					sheet.setMaster(false);
					sheet.setSheetId(customizedSheets.get(i).getSheet().getId());
					sheet.setPdfName(customizedSheets.get(i).getSheet().getPdfName());
					sheet.setUtilityCompany(customizedSheets.get(i).getSheet().getUtilityCompany());
					sheet.setIndividualAHJ(customizedSheets.get(i).getSheet().getAhj());
					sheet.setNecCode(customizedSheets.get(i).getSheet().getNecCode());
					sheet.setIfcCode(customizedSheets.get(i).getSheet().getIfcCode());
					sheet.setUsers(customizedSheets.get(i).getSheet().getUserList() != null
							&& !customizedSheets.get(i).getSheet().getUserList().isEmpty()
									? projectRepo.findProjectOwnerName(idPermit)
									: "");
					sheet.setState(customizedSheets.get(i).getSheet().getStates());
					sheet.setBasicSystemType(
							checkValue.Equals(customizedSheets.get(i).getSheet().getBasicSystemType(), "Other")
									? customizedSheets.get(i).getSheet().getBasicSystemTypeOther()
									: customizedSheets.get(i).getSheet().getBasicSystemType());

					sheet.setInverterTechnology(checkValue
							.Equals(customizedSheets.get(i).getSheet().getInverterTechnology(), "Micro Inverter")
									? "Microinverters"
									: checkValue.Equals(customizedSheets.get(i).getSheet().getInverterTechnology(),
											"AC Modules")
													? "AC Modules"
													: checkValue.Equals(
															customizedSheets.get(i).getSheet().getInverterTechnology(),
															"System Optimizer")
																	? "System Optimizers with String Inverter(s)"
																	: checkValue.Equals(customizedSheets.get(i)
																			.getSheet().getInverterTechnology(),
																			"Neither") ? "String Inverters" : "");

					sheet.setBatteryInSystem(customizedSheets.get(i).getSheet().getBatteryInSystem() != null
							? customizedSheets.get(i).getSheet().getBatteryInSystem().getModel()
							: "");
					sheet.setRoofType(checkValue.Equals(customizedSheets.get(i).getSheet().getRoofType(), "Other")
							? customizedSheets.get(i).getSheet().getRoofTypeOther()
							: customizedSheets.get(i).getSheet().getRoofType());
					sheet.setRoofingMaterialType(customizedSheets.get(i).getSheet().getRoofingMaterialType() != null
							? customizedSheets.get(i).getSheet().getRoofingMaterialType().getTypeRoof()
							: "");
					sheet.setRailRacking(customizedSheets.get(i).getSheet().getRailRackingModel() != null
							? customizedSheets.get(i).getSheet().getRailRackingModel().getManufacturer() + " "
									+ customizedSheets.get(i).getSheet().getRailRackingModel().getModel()
							: "");
					sheet.setElectEngStructEng(
							checkValue.Equals(customizedSheets.get(i).getSheet().getElectEngStructEng(), "true")
									? "Yes, Please Provide Electrical Engineering Review & Stamp."
									: checkValue.Equals(customizedSheets.get(i).getSheet().getElectEngStructEng(),
											"false") ? "No, Electrical Engineering Not Required." : "");

					if (!customizeSheets.containsKey(customizedSheets.get(i).getSheet().getPdfName())) {
						List<DuplicateCustomizeSheetsModel> list = new ArrayList<>();
						list.add(sheet);
						customizeSheets.put(customizedSheets.get(i).getSheet().getPdfName(), list);
					} else {
						customizeSheets.get(customizedSheets.get(i).getSheet().getPdfName()).add(sheet);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customizeSheets;
	}

	public String updateCusSheets(Long[] ids) {
		try {
			permitCustomizedSheetsRepo.updateCustomizedSheetsMaster(ids);
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}

	private String getNecCode(AHJChecklistEntity governingCodes, String state) {
		return governingCodes != null && checkValue.isStringNotEmpty(governingCodes.getNec()) ? governingCodes.getNec()
				: checkValue.Equals(state, "CA") || checkValue.Equals(state, "ID") || checkValue.Equals(state, "NC")
						|| checkValue.Equals(state, "CO") || state.equals("GA") || checkValue.Equals(state, "TX")
						|| checkValue.Equals(state, "OR")
								? "2017"
								: checkValue.Equals(state, "SC") || checkValue.Equals(state, "OK")
										|| checkValue.Equals(state, "MO") ? "2014"
												: checkValue.Equals(state, "MA") ? "2020" : null;
	}

	private String getIfcCode(AHJChecklistEntity governingCodes, String state) {
		return governingCodes != null && checkValue.isStringNotEmpty(governingCodes.getIfc()) ? governingCodes.getIfc()
				: checkValue.Equals(state, "ID") || checkValue.Equals(state, "NC")
						|| checkValue.Equals(state, "CO") || state.equals("GA") || checkValue.Equals(state, "TX")
						|| checkValue.Equals(state, "OR") || checkValue.Equals(state, "SC")
						|| checkValue.Equals(state, "OK") || checkValue.Equals(state, "MA") ? "2015"
								: checkValue.Equals(state, "MO") ? "2009" : null;
	}
}
