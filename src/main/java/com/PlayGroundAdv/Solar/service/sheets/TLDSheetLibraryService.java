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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.TLDSheetLibraryEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.model.SRsheetsModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageTLDSheet;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.model.sheets.TLDByProjectModel;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.TLDSheetLibraryRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.TLDsubNamesRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleQtyOnBranchCicuit;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleQtyOnStringInverter;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.project.PermitService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class TLDSheetLibraryService {

	@PersistenceContext
	EntityManager em;

	final NotificationEntityService notificationEntityService;
	final PermitService permitService;
	final ACDisconnectRepository aCDisconnectRepo;
	final CheckValueTypesService checkValue;
	final ConvertersRepository optimizerRepo;
	final TLDsubNamesRepository subNamesRepo;
	final DcCombinerDiscoRepository dcCombinerRepo;
	final InverterRepository inverterRepo;
	final AcCombinerSLCRepository acCombinerRepo;
	final TLDSheetLibraryRepository tldSheetLibraryRepo;
	final AuthenticationRepository userRepo;
	final ModuleQtyOnStringInverter moduleQtyOnStringInverter;
	final ModuleQtyOnBranchCicuit moduleQtyOnBranchCicuit;
	final BatteryRepository batteryRep;

	public TLDSheetLibraryService(NotificationEntityService notificationEntityService, PermitService permitService,
			ACDisconnectRepository aCDisconnectRepo, CheckValueTypesService checkValue,
			ConvertersRepository optimizerRepo, TLDsubNamesRepository subNamesRepo,
			DcCombinerDiscoRepository dcCombinerRepo, InverterRepository inverterRepo,
			AcCombinerSLCRepository acCombinerRepo, TLDSheetLibraryRepository tldSheetLibraryRepo,
			AuthenticationRepository userRepo, ModuleQtyOnStringInverter moduleQtyOnStringInverter,
			ModuleQtyOnBranchCicuit moduleQtyOnBranchCicuit, BatteryRepository batteryRep) {
		super();
		this.notificationEntityService = notificationEntityService;
		this.permitService = permitService;
		this.aCDisconnectRepo = aCDisconnectRepo;
		this.checkValue = checkValue;
		this.optimizerRepo = optimizerRepo;
		this.subNamesRepo = subNamesRepo;
		this.dcCombinerRepo = dcCombinerRepo;
		this.inverterRepo = inverterRepo;
		this.acCombinerRepo = acCombinerRepo;
		this.tldSheetLibraryRepo = tldSheetLibraryRepo;
		this.userRepo = userRepo;
		this.moduleQtyOnStringInverter = moduleQtyOnStringInverter;
		this.moduleQtyOnBranchCicuit = moduleQtyOnBranchCicuit;
		this.batteryRep = batteryRep;
	}

	private Page<SRsheetsModel> convertDto(Page<TLDSheetLibraryEntity> page, Pageable pageable, Boolean deleted) {
		try {
			return new PageImpl<>(page.stream().map(c -> new SRsheetsModel(c.getId(), c.getPdfName(), c.getLastUpdate(),
					c.getAddedBy() != null ? c.getAddedBy().getFirstName() + " " + c.getAddedBy().getLastName() : "",
					deleted, c.getDeletedOn(),
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

	public Page<SRsheetsModel> getAllTLDsheet(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("pdfName"));

			Page<TLDSheetLibraryEntity> page = tldSheetLibraryRepo.findByIsDeletedOrderByPdfName(request.getIsDeleted(),
					pageable);
			return convertDto(page, pageable, request.getIsDeleted());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Page<SRsheetsModel> searchTLDsheet(ComponentPageTLDSheet request) {

		try {

			// List des Where
			List<String> like = new ArrayList<>();
			List<String> likeOr = new ArrayList<>();
			List<String> notLike = new ArrayList<>();

			if (checkValue.Equals(request.getInverterTechnology(), "Neither")) {
				like.add("INV");
				notLike.addAll(Arrays.asList("OPT", "MINV", "MLRSD"));
			} else if (checkValue.Equals(request.getInverterTechnology(), "System Optimizer")) {
				like.add("OPT");
			} else if (checkValue.Equals(request.getInverterTechnology(), "Micro Inverter")) {
				like.add("MINV");
			} else if (checkValue.Equals(request.getInverterTechnology(), "AC Modules")) {
				notLike.addAll(Arrays.asList("OPT", "MLRSD", "MINV", "INV"));
			}

			if (checkValue.NotEquals(request.getPdfName(), "")) {
				likeOr.add(request.getPdfName().toUpperCase());
				if (request.getPdfName().contains(" ")) {
					likeOr.addAll(Arrays.asList(request.getPdfName().toUpperCase().split(" ")));
				}
				if (request.getPdfName().contains("-")) {
					likeOr.addAll(Arrays.asList(request.getPdfName().toUpperCase().split("-")));
				}
				if (request.getPdfName().contains("_")) {
					likeOr.addAll(Arrays.asList(request.getPdfName().toUpperCase().split("_")));
				}
			}

			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("pdfName"));
			Page<TLDSheetLibraryEntity> tldSheetLibrary = tldSheetLibraryRepo
					.findAll(filter(like, null, likeOr, notLike, request.getDeleted()), pageable);
			return convertDto(tldSheetLibrary, pageable, request.getDeleted());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private Specification<TLDSheetLibraryEntity> filter(List<String> like, List<List<String>> altLike,
			List<String> likeOr, List<String> notLike, Boolean deleted) {

		return new Specification<TLDSheetLibraryEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TLDSheetLibraryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> pLike = new ArrayList<>();
				List<Predicate> pLikeOr = new ArrayList<>();
				List<Predicate> pNotLike = new ArrayList<>();
				Predicate predicateAltOr = null;
				for (int i = 0; like != null && i < like.size(); i++) {
					if (like.get(i).equals("OPT")) {
						Predicate predicate = cb.or(cb.like(cb.upper(root.<String>get("pdfName")), "%OPT%"),
								cb.like(cb.upper(root.<String>get("pdfName")), "%MLRSD%"));
						pLike.add(predicate);
					} else {
						Predicate predicate = cb.like(cb.upper(root.get("pdfName")), "%" + like.get(i) + "%");
						pLike.add(predicate);
					}
				}
				// A.B 05-25-2022 REV CR-1580
				for (int i = 0; altLike != null && i < altLike.size(); i++) {
					List<Predicate> pOr = new ArrayList<>();
					for (int j = 0; altLike.get(i) != null && j < altLike.get(i).size(); j++) {
						Predicate predicate = cb.like(cb.upper(root.get("pdfName")), "%" + altLike.get(i).get(j) + "%");
						pOr.add(predicate);
					}
					predicateAltOr = pOr != null && !pOr.isEmpty()
							? cb.or(cb.or(pOr.toArray(new Predicate[pOr.size()])))
							: null;
				}
				for (int i = 0; likeOr != null && i < likeOr.size(); i++) {
					Predicate predicate = cb.like(cb.upper(root.get("pdfName")), "%" + likeOr.get(i) + "%");
					pLikeOr.add(predicate);
				}
				for (int i = 0; notLike != null && i < notLike.size(); i++) {
					Predicate predicate = cb.notLike(cb.upper(root.get("pdfName")), "%" + notLike.get(i) + "%");
					pNotLike.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate predicateLike = like != null && !like.isEmpty()
						? cb.and(pLike.toArray(new Predicate[pLike.size()]))
						: null;
				Predicate predicateLikeOr = likeOr != null && !likeOr.isEmpty()
						? cb.or(pLikeOr.toArray(new Predicate[pLikeOr.size()]))
						: null;
				Predicate predicateNotLike = notLike != null && !notLike.isEmpty()
						? cb.and(pNotLike.toArray(new Predicate[pNotLike.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateDeleted, predicateLike, predicateLikeOr, predicateNotLike,
						predicateAltOr);
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	public String uploadHomePicture(MultipartFile file, String fileName, String siteSurveyiD) throws IOException {

		try {
			List<TLDSheetLibraryEntity> tldList = tldSheetLibraryRepo.findByPdfNameAndIsDeleted(fileName, false);
			if (tldList != null && !tldList.isEmpty()) {

				return "exist";
			} else {
				AuthentificationEntity addedBy = userRepo.findById(Long.valueOf(siteSurveyiD)).orElse(null);
				TLDSheetLibraryEntity tldSheetsList = new TLDSheetLibraryEntity();
				tldSheetsList.setPdfName(fileName);
				tldSheetsList.setIsDeleted(false);
				tldSheetsList.setLastUpdate(new Date());
				tldSheetsList.setAddedBy(addedBy);
				tldSheetLibraryRepo.save(tldSheetsList);

				byte[] bytes5 = file.getBytes();
				new File(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/").mkdirs();
				Path pathUp5 = Paths.get(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + fileName);
				Files.write(pathUp5, bytes5);

				return "Done";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String deleteTLDsheet(Long sheetID, Long userID) {
		try {
			AuthentificationEntity deletedBy = userRepo.findById(userID).orElse(null);
			TLDSheetLibraryEntity tldSheetsList = tldSheetLibraryRepo.findById(sheetID)
					.orElseThrow(() -> new ResourceNotFoundException("TLD Sheet not found for this id :" + sheetID));
			tldSheetsList.setIsDeleted(true);
			tldSheetsList.setDeletedOn(new Date());
			tldSheetsList.setDeletedBy(deletedBy);
			File oldFile = new File(
					Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + tldSheetsList.getPdfName());
			File newfile = new File(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + "Delete"
					+ tldSheetsList.getPdfName());
			if (oldFile.exists()) {
				oldFile.renameTo(newfile);
			}
			tldSheetLibraryRepo.save(tldSheetsList);
			return "done";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	public String editTLDsheet(MultipartFile file, String fileName, String siteSurveyiD, String fileID) {
		try {
			TLDSheetLibraryEntity existRSheet = tldSheetLibraryRepo.findOneByPdfNameAndIsDeleted(fileName, false);
			if (existRSheet != null && !checkValue.Equals(existRSheet.getId(), Long.valueOf(fileID))) {
				return "exist";
			} else {
				return editTLDsheetFunction(file, fileName, siteSurveyiD, fileID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editTLDsheetFunction(MultipartFile file, String fileName, String userId, String fileID) {
		try {
			TLDSheetLibraryEntity updatedtldSheets = tldSheetLibraryRepo.findById(Long.valueOf(fileID))
					.orElseThrow(() -> new ResourceNotFoundException("Sheet not found for this id :" + fileID));
			updatedtldSheets.setPdfName(fileName);
			updatedtldSheets.setIsDeleted(false);
			updatedtldSheets.setLastUpdate(new Date());
			updatedtldSheets.setUpdatedBy(userRepo.findById(Long.valueOf(userId)).orElse(null));
			tldSheetLibraryRepo.save(updatedtldSheets);

			byte[] bytes5 = file.getBytes();
			new File(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/").mkdirs();
			if (fileName != null && fileName.contains(".pdf")) {
				Path pathUp5 = Paths.get(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + fileName);
				Files.write(pathUp5, bytes5);
			} else {
				Path pathUp5 = Paths
						.get(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + fileName + ".pdf");
				Files.write(pathUp5, bytes5);
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String restoreTLDsheet(Long sheetID) {

		try {
			TLDSheetLibraryEntity tldSheetsList = tldSheetLibraryRepo.findById(sheetID)
					.orElseThrow(() -> new ResourceNotFoundException("Sheet not found for this id :" + sheetID));
			List<TLDSheetLibraryEntity> tldList = tldSheetLibraryRepo
					.findByPdfNameAndIsDeleted(tldSheetsList.getPdfName(), false);
			if (tldList.isEmpty()) {
				tldSheetsList.setIsDeleted(false);
				File newfile = new File(
						Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + tldSheetsList.getPdfName());
				File oldFile = new File(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + "Delete"
						+ tldSheetsList.getPdfName());
				if (oldFile.exists()) {
					oldFile.renameTo(newfile);
				}

				tldSheetLibraryRepo.save(tldSheetsList);
				return "done";
			}
			return "exist";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editTLDsheetNotification(Long userID, String pdfName) {
		try {
			AuthentificationEntity userCo = userRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + userID));
			notificationEntityService.addNewNotif(userID, 0L, "Update-TLD Sheet", "Libraries", "Update-TLD Sheet",
					"The " + userCo.getRoleEntity().getDescription() + " " + userCo.getFirstName() + " "
							+ userCo.getLastName() + " updated The TLD Sheet " + pdfName,
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String deleteTLDsheetNotification(Long userID, String pdfName) {
		try {
			AuthentificationEntity userCo = userRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + userID));
			notificationEntityService.addNewNotif(userID, 0L, "Delete-TLD Sheet", "Libraries", "Delete-TLD Sheet",
					"The " + userCo.getRoleEntity().getDescription() + " " + userCo.getFirstName() + " "
							+ userCo.getLastName() + " deleted The TLD Sheet " + pdfName,
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public List<TLDByProjectModel> searchTLDsheetList(String inverterTechnology) {

		try {

			// List des Where
			List<String> like = new ArrayList<>();
			List<String> likeOr = new ArrayList<>();
			List<String> notLike = new ArrayList<>();

			if (checkValue.Equals(inverterTechnology, "Neither")) {
				like.add("INV");
				notLike.addAll(Arrays.asList("OPT", "MINV", "MLRSD"));
			} else if (checkValue.Equals(inverterTechnology, "System Optimizer")) {
				like.add("OPT");
			} else if (checkValue.Equals(inverterTechnology, "Micro Inverter")) {
				like.add("MINV");
			} else if (checkValue.Equals(inverterTechnology, "AC Modules")) {
				notLike.addAll(Arrays.asList("OPT", "MLRSD", "MINV", "INV"));
			}

			List<TLDSheetLibraryEntity> tldSheetLibrary = tldSheetLibraryRepo
					.findAll(filter(like, null, likeOr, notLike, false));
			return convertDtoList(tldSheetLibrary);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private List<TLDByProjectModel> convertDtoList(List<TLDSheetLibraryEntity> list) {
		try {
			return list.stream().map(c -> new TLDByProjectModel(c.getId(), c.getPdfName()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<TLDByProjectModel> searchShotList(GetPermitByIdResult permitModel) {

		try {
			// List des Where
			List<String> like = new ArrayList<>();
			List<String> notLike = new ArrayList<>();
			List<List<String>> altLike = new ArrayList<>();

			Inverters microInverterInfo = null;
			if (permitModel.getPermitArraysEntity() != null
					&& checkValue.isLongPositive(permitModel.getPermitArraysEntity().getMicroInverter())) {
				microInverterInfo = inverterRepo.findById(permitModel.getPermitArraysEntity().getMicroInverter())
						.orElse(null);
			}
			String inverterTechnology = (checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(),
					"Micro Inverter")
					|| checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(), "AC Modules"))
							? "MICRO"
							: "STRING";
			PlansetUtils plansetUtils = checkValue.Equals(inverterTechnology, "MICRO")
					? moduleQtyOnBranchCicuit.getModuleQty(permitModel.getPermitArraysEntity(), microInverterInfo)
					: moduleQtyOnStringInverter.getModuleQty(permitModel.getPermitArraysEntity());

			String modAttr = "";
			modAttr = checkValue.Equals(inverterTechnology, "MICRO") ? plansetUtils.getNumberOfBranchCircuit() + "MOD"
					: plansetUtils.getTotalNumberOfStrings() + "MOD";

			if (permitModel != null && permitModel.getPermitArraysEntity() != null
					&& checkValue.NotEquals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(), "")) {

				if (checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(), "Neither")
						|| checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(),
								"System Optimizer")) {

					if (plansetUtils.getNumberOfStringsOne() > 0) {
						modAttr = modAttr + "(" + plansetUtils.getNumberOfStringsOne();

						if (plansetUtils.getNumberOfStringsTwo() > 0) {
							modAttr = modAttr + "." + plansetUtils.getNumberOfStringsTwo();
						}
						if (plansetUtils.getNumberOfStringsThree() > 0) {
							modAttr = modAttr + "." + plansetUtils.getNumberOfStringsThree();
						}
						if (plansetUtils.getNumberOfStringsFour() > 0) {
							modAttr = modAttr + "." + plansetUtils.getNumberOfStringsFour();
						}
						if (plansetUtils.getNumberOfStringsFive() > 0) {
							modAttr = modAttr + "." + plansetUtils.getNumberOfStringsFive();
						}
						modAttr = modAttr + ")";
					}

					like.add(modAttr);
					notLike.add("MINV");
					Inverters inverterInfo;
					Inverters secondInverterInfo;
					try {
						if (permitModel != null && permitModel.getPermitArraysEntity() != null
								&& permitModel.getPermitArraysEntity().getInverterModel() != null) {
							inverterInfo = inverterRepo.findById(permitModel.getPermitArraysEntity().getInverterModel())
									.orElse(new Inverters());
						} else {
							inverterInfo = null;
						}
					} catch (Exception e) {
						e.printStackTrace();
						inverterInfo = null;
					}
					try {
						if (permitModel != null && permitModel.getPermitArraysEntity() != null
								&& permitModel.getPermitArraysEntity().getSecondInverterModel() != null) {
							secondInverterInfo = inverterRepo
									.findById(permitModel.getPermitArraysEntity().getSecondInverterModel())
									.orElse(new Inverters());

						} else {
							secondInverterInfo = null;
						}
					} catch (Exception e) {
						e.printStackTrace();
						secondInverterInfo = null;
					}
					// A.B 24-25-2022 REV-18 CR 1580
					String invQty = secondInverterInfo == null ? "" : plansetUtils.getInverterQty() + "";
					if (permitModel != null && permitModel.getPermitArraysEntity() != null
							&& checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(),
									"System Optimizer")
							&& inverterInfo != null && checkValue.Equals(inverterInfo.getMake(), "Pika Energy")) {
						altLike.add(Arrays.asList("INV(Pika)", "INV"));

					} else if (inverterInfo != null && checkValue.Equals(inverterInfo.getIntegratedACDisco(), true)) {
						altLike.add(Arrays.asList("INV-ACD", "INV"));
					}
					if (permitModel.getPermitConduitConductorSection() != null && Boolean.TRUE
							.equals(permitModel.getPermitConduitConductorSection().getConductorNeutralSix())) {
						altLike.add(Arrays.asList("INV(N)", "INV"));
					} else {
						like.add(invQty + "INV");
					}

					if (checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(),
							"System Optimizer")) {
						DCOptimizerEntity dcOptimizer;
						if (permitModel.getPermitArraysEntity() != null
								&& permitModel.getPermitArraysEntity().getSystemOptimizerModel() != null) {
							dcOptimizer = optimizerRepo
									.findById(permitModel.getPermitArraysEntity().getSystemOptimizerModel())
									.orElse(new DCOptimizerEntity());
						} else {
							dcOptimizer = null;
						}
						if (dcOptimizer != null && checkValue.contains(dcOptimizer.getManufacturer(), "Pika Energy")) {
							if (checkValue.Equals(dcOptimizer.getQtyModuleOpt(), "2")) {
								altLike.add(Arrays.asList("OPT2(Pika)", "OPT", "OPT(Pika)"));
							} else {
								altLike.add(Arrays.asList("OPT(Pika)", "OPT"));
							}

						} else if (dcOptimizer != null && checkValue.contains(dcOptimizer.getManufacturer(), "SMA")) {
							if (checkValue.Equals(dcOptimizer.getQtyModuleOpt(), "2")) {
								like.add("MLRSD2");
							} else {
								like.add("MLRSD");
							}
						} else if (dcOptimizer != null && checkValue.contains(dcOptimizer.getManufacturer(), "IMO")) {
							if (checkValue.Equals(dcOptimizer.getQtyModuleOpt(), "2")) {
								like.add("MLRSD2(IMO)");
							} else {
								like.add("MLRSD(IMO)");
							}
						} else if (dcOptimizer != null && checkValue.Equals(dcOptimizer.getQtyModuleOpt(), "2")) {
							altLike.add(Arrays.asList("OPT2", "OPT"));
						} else {
							like.add("OPT");
						}

					} else {
						notLike.add("OPT");
					}

				} else if (checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(),
						"Micro Inverter")
						|| checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(),
								"AC Modules")) {

					like.add(modAttr);
					if (microInverterInfo != null && checkValue.Equals(microInverterInfo.getModPerMicro(), 2)) {
						altLike.add(Arrays.asList("MINV2", "MINV"));
					} else if (microInverterInfo != null && checkValue.Equals(microInverterInfo.getMake(), "APsystems")
							&& checkValue.Equals(microInverterInfo.getModPerMicro(), 2)) {
						altLike.add(Arrays.asList("MINV2(AP)", "MINV2", "MINV"));
					} else if (microInverterInfo != null && checkValue.Equals(microInverterInfo.getMake(), "APsystems")
							&& checkValue.Equals(microInverterInfo.getModPerMicro(), 4)) {
						altLike.add(Arrays.asList("MINV4(AP)", "MINV2(AP)", "MINV2", "MINV", "MINV_APsystems",
								"MINV_YC600"));
					} else if (microInverterInfo != null
							&& checkValue.Equals(microInverterInfo.getMake(), "APsystems")) {
						altLike.add(Arrays.asList("MINV(AP)", "MINV", "MINV_APsystems"));
					} else {
						like.add("MINV");
					}

				}

			}

			if (permitModel != null && permitModel.getPermitProjectSiteInfoEntityTwo() != null
					&& ((checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getTransitioningPVWireIn(),
							"Rooftop Junction box(es)")
							&& permitModel.getPermitProjectSiteInfoEntityTwo().getRoofTopJboxDC() != null)
							|| (checkValue.Equals(
									permitModel.getPermitProjectSiteInfoEntityTwo().getMicroInverterCabling(),
									"Rooftop Junction box(es)")
									&& permitModel.getPermitProjectSiteInfoEntityTwo().getRoofTopJbox() != null))) {
				like.add("JB");
			}

			if (permitModel != null && permitModel.getPermitArraysEntity() != null
					&& permitModel.getPermitProjectSiteInfoEntityTwo() != null
					&& (checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(), "Neither")
					|| checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(), "System Optimizer"))
					&& permitModel.getPermitProjectSiteInfoEntityTwo().getRoofTopDCCombiner() != null) {
				DCCombinerDisconnectEntity dcCombiner = dcCombinerRepo
						.findById(permitModel.getPermitProjectSiteInfoEntityTwo().getRoofTopDCCombiner())
						.orElse(new DCCombinerDisconnectEntity());
				if (dcCombiner != null && checkValue.Equals(dcCombiner.getTypeDc(), "Rapid Shutdown")) {
					like.add("RSD");
				} else {
					if (checkValue.Equals(dcCombiner.getOcpd(), "FUSIBLE DISCONNECT")
							|| checkValue.Equals(dcCombiner.getOcpd(), "FUSIBLE")
							|| checkValue.Equals(dcCombiner.getOcpd(), "Fusible")
							|| checkValue.Equals(dcCombiner.getOcpd(), "FUSE")) {
						if (checkValue.Equals(dcCombiner.getTypeDc(), "DC Combining Disconnect")) {
							altLike.add(Arrays.asList("DCCD", "DCCF", "DCC"));
						} else {
							altLike.add(Arrays.asList("DCCF", "DCC"));
						}
					} else if (checkValue.Equals(dcCombiner.getTypeDc(), "DC Combining Disconnect")) {
						altLike.add(Arrays.asList("DCCD", "DCC"));
					} else {
						like.add("DCC");
					}
				}
			}

			if (permitModel != null && permitModel.getPermitArraysEntity() != null
					&& permitModel.getPermitProjectSiteInfoEntityTwo() != null
					&& (checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(), "Neither")
					|| checkValue.Equals(permitModel.getPermitArraysEntity().getDeviceToIncorporate(), "System Optimizer"))
					&& permitModel.getPermitProjectSiteInfoEntityTwo().getRoofTopDCDisco() != null) {
				DCCombinerDisconnectEntity dcDisconnect = dcCombinerRepo
						.findById(permitModel.getPermitProjectSiteInfoEntityTwo().getRoofTopDCDisco())
						.orElse(new DCCombinerDisconnectEntity());
				if (dcDisconnect != null && checkValue.Equals(dcDisconnect.getTypeDc(), "Rapid Shutdown")) {
					like.add("RSD");
				} else if (dcDisconnect != null
						&& checkValue.Equals(dcDisconnect.getTypeDc(), "DC Combining Disconnect")) {
					like.add("DCCD");
				} else {
					like.add("DCD");
				}
			}

			String typeAC1 = "";
			String typeAC2 = "";

			if (permitModel != null && permitModel.getPermitProjectSiteInfoEntityTwo() != null) {
				if (checkValue.contains(permitModel.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModel(),
						":")) {

					typeAC1 = aCDisconnectRepo.getDisconnectDeviceType(Long.valueOf(
							permitModel.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModel().split(":")[0]));

					if (checkValue.contains(
							permitModel.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModelTwo(), ":")) {
						typeAC2 = aCDisconnectRepo.getDisconnectDeviceType(Long.valueOf(permitModel
								.getPermitProjectSiteInfoEntityTwo().getRooftopACCombinerModelTwo().split(":")[0]));
					}
					if (typeAC1 != null) {

						if (checkValue.Equals(typeAC1, "NON-FUSIBLE DISCONNECT")
								|| checkValue.Equals(typeAC1, "Non-Fusible")
								|| checkValue.Equals(typeAC1, "NON-FUSIBLE")) {
							typeAC1 = "NF";

						} else if (checkValue.NotEquals(typeAC1, "")) {
							typeAC1 = "F";
						}

					} else
						typeAC1 = "NF";

					if (typeAC2 != null) {

						if (checkValue.Equals(typeAC2, "NON-FUSIBLE DISCONNECT")
								|| checkValue.Equals(typeAC2, "Non-Fusible")
								|| checkValue.Equals(typeAC2, "NON-FUSIBLE")) {
							typeAC2 = "NF";

						} else if (checkValue.NotEquals(typeAC2, "")) {
							typeAC2 = "F";
						}

					} else
						typeAC2 = "NF";

					if (checkValue.Equals(typeAC1, "F") && checkValue.Equals(typeAC2, "F")) {
						like.add("2ACDF");

					} else if (checkValue.Equals(typeAC1, "NF") && checkValue.Equals(typeAC2, "NF")) {
						like.add("2ACD");

					} else if ((checkValue.Equals(typeAC1, "F") && checkValue.Equals(typeAC2, "NF"))
							|| (checkValue.Equals(typeAC1, "NF") && checkValue.Equals(typeAC2, "F"))) {
						like.add("ACD");
						like.add("ACDF");

					} else if (checkValue.Equals(typeAC1, "F")) {
						like.add("ACDF");

					} else if (checkValue.Equals(typeAC1, "NF")) {
						like.add("ACD");

					}

				}

				if (checkValue.contains(permitModel.getPermitProjectSiteInfoEntityTwo().getCombiningACCircuits(),
						"A Proposed AC Combiner Panel")
						&& permitModel.getPermitProjectSiteInfoEntityTwo().getGroundLevelACCombinerBoxModel() != null) {
					
					ACCombinerSLC acCombiner = acCombinerRepo
							.findById(
									permitModel.getPermitProjectSiteInfoEntityTwo().getGroundLevelACCombinerBoxModel())
							.orElse(null);
					if (acCombiner != null && checkValue.Equals(acCombiner.getCategory(), "Solar Load Center")) {
						if (checkValue.Equals(acCombiner.getCombinerDeviceType(), "With Main Breaker")) {
							altLike.add(Arrays.asList("SLCM", "SLC"));
						} else if (permitModel.getPermitHomeSiteEntityResult() != null
								&& checkValue.Equals(acCombiner.getNumberOfPoles(), "3") && checkValue.Equals(
										permitModel.getPermitHomeSiteEntityResult().getIfServiceVoltage(), true)) {
							altLike.add(Arrays.asList("SLC(S3P)", "SLC"));
						} else {
							like.add("SLC");
						}
					} else if (acCombiner != null && checkValue.Equals(acCombiner.getCategory(), "AC Combiner")) {
						like.add("ENVOY");
					}

				} else if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getMicroInverterCabling(),
						"Rooftop AC Combiner")
						&& permitModel.getPermitProjectSiteInfoEntityTwo().getRoofTopACCombiner() != null) {
					
					ACCombinerSLC acCombiner = acCombinerRepo
							.findById(
									permitModel.getPermitProjectSiteInfoEntityTwo().getRoofTopACCombiner())
							.orElse(null);
					if (acCombiner != null && checkValue.Equals(acCombiner.getCategory(), "Solar Load Center")) {
						if (checkValue.Equals(acCombiner.getCombinerDeviceType(), "With Main Breaker")) {
							altLike.add(Arrays.asList("SLCM", "SLC"));
						} else if (permitModel.getPermitHomeSiteEntityResult() != null
								&& checkValue.Equals(acCombiner.getNumberOfPoles(), "3") && checkValue.Equals(
										permitModel.getPermitHomeSiteEntityResult().getIfServiceVoltage(), true)) {
							altLike.add(Arrays.asList("SLC(S3P)", "SLC"));
						} else {
							like.add("SLC");
						}
					} else if (acCombiner != null && checkValue.Equals(acCombiner.getCategory(), "AC Combiner")) {
						like.add("ENVOY");
					}
					
				}
				if (permitModel != null && permitModel.getPermitProjectSiteInfoEntityTwo() != null && checkValue
						.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getThepontOfTheC(), "Sub Panel")) {
					if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getConnectionPoint(),
							"Existing")) {
						if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo()
								.getIfApplicableSubPanelMainBreakerRating(), "Lug Only")) {
							like.add("ESUBL");
						} else
							like.add("ESUBM");

					} else {
						if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo()
								.getIfApplicableSubPanelMainBreakerRating(), "Lug Only")) {
							like.add("NSUBL");
						} else
							like.add("NSUBM");

					}
				}

				if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getUsedRevenue(), true)) {
					like.add("PVM");
				}
				
				if (permitModel != null && permitModel.getPermitAdditionalInfoEntity() != null
						&& Boolean.TRUE.equals(permitModel.getPermitAdditionalInfoEntity().getBatteryStorage())) {
					altLike.add(Arrays.asList("BATT", "BAT"));
					if (permitModel.getPermitEnergyBatterySystem() != null
							&& permitModel.getPermitEnergyBatterySystem().getBatteries() != null
							&& !permitModel.getPermitEnergyBatterySystem().getBatteries().isEmpty()) {
						if (checkValue.isLongPositive(
								permitModel.getPermitEnergyBatterySystem().getBatteries().get(0).getBattery())) {
							Battery battery = batteryRep
									.findById(permitModel.getPermitEnergyBatterySystem().getBatteries().get(0).getBattery())
									.orElse(null);
							if (battery != null && checkValue.EqualsCaseInsensitive(battery.getManufacturer(), "SONNEN")) {
								altLike.add(Arrays.asList("SONNEN", "BAT"));
							}
						}
					}
					if (Boolean.TRUE.equals(permitModel.getPermitEnergyBatterySystem().getAtsIncluded())) {
						altLike.add(Arrays.asList("ATS", "EATS"));
					}
					if (Boolean.TRUE.equals(permitModel.getPermitEnergyBatterySystem().getGeneratorIncluded())) {
						if (checkValue.Equals(permitModel.getPermitEnergyBatterySystem().getGeneratorStatus(),
								"Existing")) {
							altLike.add(Arrays.asList("EGEN", "GEN"));
						} else if (checkValue.Equals(permitModel.getPermitEnergyBatterySystem().getGeneratorStatus(),
								"Proposed")) {
							altLike.add(Arrays.asList("GEN", "EGEN"));
						}
					}
				}
				String end =".";
				if (permitModel.getPermitHomeSiteEntityResult() != null
						&& !Boolean.TRUE.equals(permitModel.getPermitHomeSiteEntityResult().getIfServiceVoltage())
						&& checkValue.NotEquals(permitModel.getPermitHomeSiteEntityResult().getServiceVoltage(), "Other")) {
					like.add("3PHASE");
					end ="";
				}
				if (permitModel.getPermitArraysEntity() != null
						&& Boolean.TRUE.equals(permitModel.getPermitArraysEntity().getGroundMounted())) {
					like.add("AUX");
					end ="";
				}

				if (permitModel.getPermitHomeSiteEntityResult() != null
						&& checkValue.NotEquals(permitModel.getPermitHomeSiteEntityResult().getState(), "")
						&& permitModel.getPermitProjectSiteInfoEntityTwo() != null
						&& permitModel.getPermitProjectSiteInfoEntityTwo().getSolarLocation() != null) {

					if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getSolarLocation(),
							"Meter adapter")) {
						like.add("MSASST"+end);
					} else if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getSolarLocation(),
							"Other")
							&& checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getThepontOfTheC(),
									"Sub Panel")
							&& checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getConnectionPoint(),
									"Existing")) {
						like.add("ESUBLST"+end);
					} else if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getSolarLocation(),
							"Other")
							&& checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getThepontOfTheC(),
									"Sub Panel")
							&& checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getConnectionPoint(),
									"Proposed")) {
						like.add("NSUBLST"+end);
					} else if (checkValue.Equals(permitModel.getPermitHomeSiteEntityResult().getState(), "CA")) {
						if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getSolarLocation(),
								"Line-side tap")) {
							if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getMainPanelUpgrade(),
									true)) {
								like.add("NMSPSST"+end);
							} else {
								like.add("EMSPSST"+end);
							}
						} else {
							if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getMainPanelUpgrade(),
									true)) {
								like.add("NMSP"+end);
							} else {
								like.add("EMSP"+end);
							}
						}
					} else {
						if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getSolarLocation(),
								"Line-side tap")) {
							if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getMainPanelUpgrade(),
									true)) {
								like.add("NMSPSSTS"+end);
							} else {
								like.add("EMSPSSTS"+end);
							}
						} else {
							if (checkValue.Equals(permitModel.getPermitProjectSiteInfoEntityTwo().getMainPanelUpgrade(),
									true)) {
								like.add("NMSPS"+end);
							} else {
								like.add("EMSPS"+end);
							}
						}

					}

				}
			}

			
			// order by location name
			List<TLDSheetLibraryEntity> tldSheetLibrary = tldSheetLibraryRepo
					.findAll(filter(like, altLike, null, notLike, false));
			return convertDtoList(tldSheetLibrary);

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

//      S.B 02/06/2020 CR-1580 V15c Get Allowed Sub Names to create TLD sheets
	public List<String> allowedSubNames() {
		try {
			return subNamesRepo.findSubNameByIsDeleted(false);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public ResponseEntity<byte[]> downloadFileTLDsheet(String url) {

		Path path = Paths.get("Constants.rapportThreeLineDiagramFolderUrl");
		if (new File(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + url).exists()) {
			path = Paths.get(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + url);
		}

		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		String filename = "output.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(contents, headers, HttpStatus.OK);
	}

	public ResponseEntity<byte[]> getFileTLDsheet(String url) {

		Path path = Paths.get("Constants.rapportThreeLineDiagramFolderUrl");

		if (new File(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + url).exists()) {
			path = Paths.get(Constants.rapportThreeLineDiagramFolderUrl + "threeLinesSheets/" + url);
		}

		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(contents, headers, HttpStatus.OK);
	}

}
