package com.PlayGroundAdv.Solar.service.libraries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
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

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ModuleLibraryEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ModuleFavRequest;
import com.PlayGroundAdv.Solar.model.ModuleInfoModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchModulResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.SiteSurveyRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ModuleService {

	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValue;
	final ModuleRepository moduleRepo;
	final AuthenticationRepository authentificationRepo;
	final PermitArraysRepository permitArraysEntityRepo;
	final PermitRepository permitEntityRepo;
	final ModuleFavoritesRepository moduleFavRepo;
	final SiteSurveyRepository siteSurveyEntityRepo;
	final EquipmentUpdate mailingService;

	public ModuleService(HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValue,
			ModuleRepository moduleRepo, AuthenticationRepository authentificationRepo,
			PermitArraysRepository permitArraysEntityRepo, PermitRepository permitEntityRepo,
			ModuleFavoritesRepository moduleFavRepo, SiteSurveyRepository siteSurveyEntityRepo,
			EquipmentUpdate mailingService) {
		super();
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.checkValue = checkValue;
		this.moduleRepo = moduleRepo;
		this.authentificationRepo = authentificationRepo;
		this.permitArraysEntityRepo = permitArraysEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.moduleFavRepo = moduleFavRepo;
		this.siteSurveyEntityRepo = siteSurveyEntityRepo;
		this.mailingService = mailingService;
	}

	public Page<SearchModulResult> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("make").and(Sort.by("model")));
			String[] manufacturer = checkValue.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValue.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			String wattage = checkValue.isStringNotEmpty(request.getWattage()) ? request.getWattage().trim() : null;
			if (manufacturer == null && model == null && wattage == null
					&& !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<Cmodulev2> p = moduleRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser());
			} else {
				Page<Cmodulev2> p = moduleRepo.findAll(filterMod(manufacturer, model, wattage, request.getIsFavorite(),
						request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<SearchModulResult> convertDto(Page<Cmodulev2> page, Pageable pageable, Long userId) {
		try {
			List<Long> favorite = moduleFavRepo.findFavoriteModules(userId);
			return new PageImpl<>(
					page.stream().map(c -> new SearchModulResult(c.getId(), favorite.indexOf(c.getId()) != -1,
							c.getMake(), c.getModel(), c.getiScRef(), c.getiMpRef(), c.getvMpRef(), c.getvOcRef(),
							c.getGammaR(), c.getHasCorrectionRequest(),
							new UsersEntityResult(c.getAuthentificationEntity().getId(),
									c.getAuthentificationEntity().getFirstName(),
									c.getAuthentificationEntity().getLastName()),
							c.getBipv(), c.gettNoct(), c.getaC(), c.getnS(), c.getMpptQty(), c.getOcpd(), c.getStc(),
							c.getStcRounded(), c.getAlphaSc(), c.getBetaOc(), c.getaRef(), c.getiIRef(), c.getiORef(),
							c.getIacmax(), c.getrS(), c.getrShRef(), c.getAdjust(), c.getVersion(), c.getPtc(),
							c.getTechnology(), c.getLength(), c.getWidth(), c.getDepth(), c.getWeight(),
							c.getHasSuperUserEdit(), c.getEroneousContent(), c.getEroneousContentOther(),
							c.getEroneousDescription(), c.getManufacturerMappingValue(), c.getModelMappingValue(),
							c.getIntegratedMicroModel(), c.getIntegratedMicroManufacturer(),
							c.getFirstUpdater() != null
									? new UsersEntityResult(c.getFirstUpdater().getId(),
											c.getFirstUpdater().getFirstName(), c.getFirstUpdater().getLastName())
									: null,
							c.getSecondUpdater() != null
									? new UsersEntityResult(c.getSecondUpdater().getId(),
											c.getSecondUpdater().getFirstName(), c.getSecondUpdater().getLastName())
									: null,
							c.getThirdUpdater() != null
									? new UsersEntityResult(c.getThirdUpdater().getId(),
											c.getThirdUpdater().getFirstName(), c.getThirdUpdater().getLastName())
									: null,
							c.getVerifiedBy() != null
									? new UsersEntityResult(c.getVerifiedBy().getId(), c.getVerifiedBy().getFirstName(),
											c.getVerifiedBy().getLastName())
									: null,
							c.getIsVerified(), c.getDateVerification(), c.getMaxSeriesFuseRating())).collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Specification<Cmodulev2> filterMod(String[] makes, String[] models, String wattage, Boolean favorite,
			Boolean deleted, Long userId) {

		return new Specification<Cmodulev2>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Cmodulev2> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesMake = new ArrayList<>();
				List<Predicate> predicatesModel = new ArrayList<>();
				for (int i = 0; makes != null && i < makes.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("make")), "%" + makes[i].toLowerCase() + "%");
					predicatesMake.add(predicate);
				}
				for (int i = 0; models != null && i < models.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("model")), "%" + models[i].toLowerCase() + "%");
					predicatesModel.add(predicate);
				}
				Predicate predicateWattage = checkValue.isStringNotEmpty(wattage)
						? cb.equal(root.get("stcRounded"), wattage)
						: null;
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate endPredicateMake = makes != null
						? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()]))
						: null;
				Predicate endPredicateModel = models != null
						? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateWattage, predicateDeleted, endPredicateMake,
						endPredicateModel);
				if (Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : moduleFavRepo.findFavoriteModules(userId)) {
						inClause.value(id);
					}
					list = Arrays.asList(predicateWattage, predicateDeleted, endPredicateMake, endPredicateModel,
							inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	public String sendCorrectionModuleRequest(CorrectionRequest request) {

		try {
			if (request != null && request.getId() != null && request.getId() != 0) {

				AuthentificationEntity user = authentificationRepo.findById(request.getIdUser()).orElseThrow(
						() -> new ResourceNotFoundException("User not found for this id :" + request.getIdUser()));

				Cmodulev2 moduleLibrary = moduleRepo.findById(request.getId()).orElseThrow(
						() -> new ResourceNotFoundException("Module not found for this id :" + request.getId()));
				moduleLibrary.setHasCorrectionRequest(true);
				moduleLibrary.setEroneousContent(request.getEroneousContent());
				moduleLibrary.setEroneousContentOther(request.getEroneousContentOther());
				moduleLibrary.setEroneousDescription(request.getEroneousDescription());
				moduleRepo.save(moduleLibrary);
				notificationEntityService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
						"Request Correction - " + moduleLibrary.getModel(),
						"The user " + user.getFirstName() + " " + user.getLastName()
								+ " request correction for the Module " + moduleLibrary.getMake() + " "
								+ moduleLibrary.getModel(),
						true);

			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	/*
	 * Get moduleFav
	 */
	public List<Long> getModuleFavorite(Long idContractor) {
		List<Long> moduleFavID = new ArrayList<>();
		try {

			List<ModuleLibraryEntity> moduleFav = moduleFavRepo.findAllByAuthentificationEntityId(idContractor);
			if (moduleFav != null && !moduleFav.isEmpty()) {

				for (int i = 0; i < moduleFav.size(); i++) {
					if (moduleFav.get(i) != null && moduleFav.get(i).getCmodulev2() != null) {
						moduleFavID.add(moduleFav.get(i).getCmodulev2().getId());
					}
				}
				return moduleFavID;
			} else
				return moduleFavID;

		} catch (Exception e) {
			e.printStackTrace();
			return moduleFavID;
		}

	}

	/*
	 * Add moduleFav
	 */
	public String addModuleFavorite(Long idContractor, Long idModule, Long idOwner) {
		try {

			AuthentificationEntity contractor = new AuthentificationEntity();
			Cmodulev2 module = new Cmodulev2();
			try {
				contractor = authentificationRepo.findById(idOwner)
						.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idOwner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				module = moduleRepo.findById(idModule)
						.orElseThrow(() -> new ResourceNotFoundException("Module not found for this id :" + idModule));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ModuleLibraryEntity moduleFav = new ModuleLibraryEntity(contractor, module);
			moduleFavRepo.save(moduleFav);
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;The favorite " + module.getMake() + " " + module.getModel() + " is added to the user "
							+ contractor.getFirstName() + " " + contractor.getLastName() + ".;Module",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "", "libraries;Add Module to User Favorites List;Module ",
					false, "", "", "");
			return "error";
		}
	}

	/*
	 * Delete moduleFav
	 */
	public String removeModuleFavorite(Long idContractor, Long idModule, Long idOwner) {
		try {
			ModuleLibraryEntity moduleFav = moduleFavRepo.findOneByAuthentificationEntityIdAndCmodulev2Id(idOwner,
					idModule);
			moduleFavRepo.delete(moduleFav);
			Cmodulev2 module = moduleRepo.findById(idModule)
					.orElseThrow(() -> new ResourceNotFoundException("Module not found for this id :" + idModule));
			AuthentificationEntity contractor = authentificationRepo.findById(idOwner)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idOwner));
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;The favorite " + module.getMake() + " " + module.getModel() + " is removed to the user "
							+ contractor.getFirstName() + " " + contractor.getLastName() + ".;Module",
					true, "", "", "");
			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "", "libraries;Add Module to User Favorites List;Module ",
					false, "", "", "");
			return "Fail";
		}

	}

	public List<ModuleFavRequest> checkModuleExistent(ModuleFavRequest newmodule, Long idUser) {

		List<ModuleFavRequest> modulesList = new ArrayList<>();
		try {
			List<Cmodulev2> listModules = moduleRepo.findModuleByMake(newmodule.getModel().trim().toLowerCase(),
					newmodule.getMake().trim().toLowerCase(), false);
			List<Cmodulev2> listModules1 = moduleRepo.findModuleByNotMake(newmodule.getModel().trim().toLowerCase(),
					newmodule.getMake().trim().toLowerCase(), false);
			if (!listModules.isEmpty()) {

				List<Long> modulesFavID = moduleFavRepo.findFavoritesIds(idUser);

				for (int i = 0; i < listModules.size(); i++) {

					ModuleFavRequest module = new ModuleFavRequest();
					if (modulesFavID != null && modulesFavID.contains(listModules.get(i).getId())) {
						module.setIsFav("true");
					} else {
						module.setIsFav("false");
					}
					module.setId(listModules.get(i).getId());
					module.setMake(listModules.get(i).getMake());
					module.setManufacturerMappingValue(listModules.get(i).getManufacturerMappingValue());
					module.setModelMappingValue(listModules.get(i).getModelMappingValue());
					module.setTechnology(listModules.get(i).getTechnology());
					module.setAc(listModules.get(i).getaC());
					module.setVersion(listModules.get(i).getVersion());
					module.setBipv(listModules.get(i).getBipv());
					module.setStc(listModules.get(i).getStc());
					module.setAdjust(listModules.get(i).getAdjust());
					module.setvOcRef(listModules.get(i).getvOcRef());
					module.setvMpRef(listModules.get(i).getvMpRef());
					module.setiScRef(listModules.get(i).getiScRef());
					module.setiMpRef(listModules.get(i).getiMpRef());
					module.setLength(listModules.get(i).getLength());
					module.setWidth(listModules.get(i).getWidth());
					module.setWeight(listModules.get(i).getWeight());
					module.setGammaR(listModules.get(i).getGammaR());
					module.setBetaOc(listModules.get(i).getBetaOc());
					module.setAlphaSc(listModules.get(i).getAlphaSc());
					module.setIacmax(listModules.get(i).getIacmax());
					modulesList.add(i, module);

				}
				return modulesList;

			} else if (!listModules1.isEmpty()) {

				List<Long> modulesFavID = moduleFavRepo.findFavoritesIds(idUser);

				for (int i = 0; i < listModules1.size(); i++) {

					ModuleFavRequest module = new ModuleFavRequest();
					if (modulesFavID != null && modulesFavID.contains(listModules1.get(i).getId())) {
						module.setIsFav("true");
					} else {
						module.setIsFav("false");
					}
					module.setId(listModules1.get(i).getId());
					module.setMake(listModules1.get(i).getMake());
					module.setModel(listModules1.get(i).getModel());
					module.setManufacturerMappingValue(listModules1.get(i).getManufacturerMappingValue());
					module.setModelMappingValue(listModules1.get(i).getModelMappingValue());
					module.setTechnology(listModules1.get(i).getTechnology());
					module.setAc(listModules1.get(i).getaC());
					module.setVersion(listModules1.get(i).getVersion());
					module.setBipv(listModules1.get(i).getBipv());
					module.setStc(listModules1.get(i).getStc());
					module.setAdjust(listModules1.get(i).getAdjust());
					module.setvOcRef(listModules1.get(i).getvOcRef());
					module.setvMpRef(listModules1.get(i).getvMpRef());
					module.setiScRef(listModules1.get(i).getiScRef());
					module.setiMpRef(listModules1.get(i).getiMpRef());
					module.setLength(listModules1.get(i).getLength());
					module.setWidth(listModules1.get(i).getWidth());
					module.setWeight(listModules1.get(i).getWeight());
					module.setGammaR(listModules1.get(i).getGammaR());
					module.setBetaOc(listModules1.get(i).getBetaOc());
					module.setAlphaSc(listModules1.get(i).getAlphaSc());
					module.setIacmax(listModules1.get(i).getIacmax());
					modulesList.add(i, module);

				}
				return modulesList;

			} else {
				return modulesList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return modulesList;
		}

	}

	/*
	 * Add module
	 */
	public Cmodulev2 addModule(Long idPermitInfo, ModuleFavRequest moduleReq, Long idUserCo) {
		Cmodulev2 module = new Cmodulev2();
		try {
			List<String> modules = moduleRepo.findModelAndMake(moduleReq.getMake(), moduleReq.getModel());

			AuthentificationEntity contractor = new AuthentificationEntity();
			try {
				contractor = permitEntityRepo.findAuthentificationEntityByID(idPermitInfo);
			} catch (Exception e) {
				contractor = siteSurveyEntityRepo.findCreator(idPermitInfo);
				e.printStackTrace();
			}

			AuthentificationEntity userCo = new AuthentificationEntity();
			try {
				userCo = authentificationRepo.findById(idUserCo)
						.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idUserCo));

			} catch (Exception e) {
				e.printStackTrace();
			}
			if (modules != null && modules.isEmpty()) {

				module.setMake(moduleReq.getMake());
				module.setModel(moduleReq.getModel());

				if (checkValue.NotEquals(moduleReq.getManufacturerMappingValue(), "")) {
					module.setManufacturerMappingValue(moduleReq.getManufacturerMappingValue());
				} else
					module.setManufacturerMappingValue(moduleReq.getMake());

				if (checkValue.NotEquals(moduleReq.getModelMappingValue(), "")) {
					module.setModelMappingValue(moduleReq.getModelMappingValue());
				} else
					module.setModelMappingValue(moduleReq.getModel());

				module.setStcRounded(moduleReq.getStcRounded());
				module.setTechnology(moduleReq.getTechnology());
				module.setVersion(moduleReq.getVersion());
				module.setvMpRef(moduleReq.getvMpRef());
				module.setvOcRef(moduleReq.getvOcRef());
				module.setiScRef(moduleReq.getiScRef());
				module.setiMpRef(moduleReq.getiMpRef());
				module.setLength(moduleReq.getLength());
				module.setWidth(moduleReq.getWidth());
				module.setDepth(moduleReq.getDepth());
				module.setWeight(moduleReq.getWeight());
				module.setGammaR(moduleReq.getGammaR());
				module.setAlphaSc(moduleReq.getAlphaSc());
				module.setBetaOc(moduleReq.getBetaOc());
				module.setDate(moduleReq.getDate());
				module.setaC(moduleReq.getAc());
				module.setaRef(moduleReq.getAref());
				module.setAdjust(moduleReq.getAdjust());
				module.setBipv(moduleReq.getBipv());
				module.setiIRef(moduleReq.getIiref());
				module.setiORef(moduleReq.getIoref());
				module.setnS(moduleReq.getNs());
				module.setPtc(moduleReq.getPtc());
				module.setrS(moduleReq.getRs());
				module.setrShRef(moduleReq.getRshref());
				module.setStc(moduleReq.getStc());
				module.settNoct(moduleReq.getTnoct());
				module.setAuthentificationEntity(userCo);
				module.setIacmax(moduleReq.getIacmax());
				module.setMpptQty(moduleReq.getMpptQty());
				module.setOcpd(moduleReq.getOcpd());
				SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
				module.setDate(formattedDATE.format(new Date()));
				module.setHasCorrectionRequest(moduleReq.getHasCorrectionRequest());
				module.setEroneousContent(moduleReq.getEroneousContent());
				module.setEroneousContentOther(moduleReq.getEroneousContentOther());
				module.setEroneousDescription(moduleReq.getEroneousDescription());
				module.setHasSuperUserEdit(
						contractor.getRoleEntity().getId() == 1 || contractor.getRoleEntity().getId() == 3);
				module.setAddDate(new Date());
				module.setIntegratedMicroModel(moduleReq.getIntegratedMicroModel());
				module.setIntegratedMicroManufacturer(moduleReq.getIntegratedMicroManufacturer());
				module.setIsVerified(false);
				module.setMaxSeriesFuseRating(moduleReq.getMaxSeriesFuseRating());
				ModuleLibraryEntity moduleFav = new ModuleLibraryEntity(contractor, module);
				moduleFav.setAuthentificationEntity(contractor);
				moduleFav.setCmodulev2(module);
				moduleFavRepo.save(moduleFav);
				moduleRepo.save(module);
				mailingService.mailUpdate("Module",
						"A new equipment of Module " + module.getMake() + " " + module.getModel()
								+ " has been added by " + userCo.getFirstName() + " " + userCo.getLastName(),
						userCo.getEmail().contains("nuagetechnologies-tn.com") && !userCo.getEmail().contains("pm"));
				historicActivity.recordActivity(idUserCo, "", "",
						"libraries;Add component " + module.getMake() + " " + module.getModel() + ".;Module", true, "",
						"", "");
				return module;
			} else

				return module;
		} catch (Exception e) {

			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Add component;Module ", false, "", "", "");
			return module;
		}

	}

	/*
	 * edit Module
	 */
	public String editModule(ModuleFavRequest module, Long idUserco) {
		try {

			if (moduleRepo.countByModelAndMakeAndIsDeleted(module.getModel(), module.getMake(), false) != 0) {
				Cmodulev2 testModule = moduleRepo.findByModelAndMakeAndIsDeleted(module.getModel(), module.getMake(),
						false);
				if (testModule != null && !checkValue.Equals(testModule.getId(), module.getId())) {
					return "exist";
				} else {
					return editModuleFunction(module, idUserco);
				}
			} else {
				return editModuleFunction(module, idUserco);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editModuleFunction(ModuleFavRequest module, Long idUserco) {

		try {

			Cmodulev2 updatedModule = moduleRepo.findById(module.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Module not found for this id :" + module.getId()));

			AuthentificationEntity firstUpdater = authentificationRepo.findById(idUserco)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idUserco));

			Date d = new Date();
			SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = formattedDATE.format(d);

			updatedModule.setMake(module.getMake());
			updatedModule.setModel(module.getModel());

			if (checkValue.NotEquals(module.getManufacturerMappingValue(), "")) {
				updatedModule.setManufacturerMappingValue(module.getManufacturerMappingValue());
			} else
				updatedModule.setManufacturerMappingValue(module.getMake());

			if (checkValue.NotEquals(module.getModelMappingValue(), "")) {
				updatedModule.setModelMappingValue(module.getModelMappingValue());
			} else
				updatedModule.setModelMappingValue(module.getModel());

			updatedModule.setStcRounded(module.getStcRounded());
			updatedModule.setTechnology(module.getTechnology());
			updatedModule.setVersion(module.getVersion());
			updatedModule.setvMpRef(module.getvMpRef());
			updatedModule.setvOcRef(module.getvOcRef());
			updatedModule.setiScRef(module.getiScRef());
			updatedModule.setiMpRef(module.getiMpRef());
			updatedModule.setLength(module.getLength());
			updatedModule.setWidth(module.getWidth());
			updatedModule.setDepth(module.getDepth());
			updatedModule.setWeight(module.getWeight());
			updatedModule.setGammaR(module.getGammaR());
			updatedModule.setAlphaSc(module.getAlphaSc());
			updatedModule.setBetaOc(module.getBetaOc());
			updatedModule.setDate(updatedDate);
			updatedModule.setaC(module.getAc());
			updatedModule.setaRef(module.getAref());
			updatedModule.setAdjust(module.getAdjust());
			updatedModule.setBipv(module.getBipv());
			updatedModule.setiIRef(module.getIiref());
			updatedModule.setiORef(module.getIoref());
			updatedModule.setnS(module.getNs());
			updatedModule.setPtc(module.getPtc());
			updatedModule.setrS(module.getRs());
			updatedModule.setrShRef(module.getRshref());
			updatedModule.setStc(module.getStc());
			updatedModule.settNoct(module.getTnoct());
			updatedModule.setIacmax(module.getIacmax());
			updatedModule.setMpptQty(module.getMpptQty());
			updatedModule.setOcpd(module.getOcpd());
			updatedModule.setHasCorrectionRequest(module.getHasCorrectionRequest());
			updatedModule.setEroneousContent(module.getEroneousContent());
			updatedModule.setEroneousContentOther(module.getEroneousContentOther());
			updatedModule.setEroneousDescription(module.getEroneousDescription());
			updatedModule.setIntegratedMicroModel(module.getIntegratedMicroModel());
			updatedModule.setIntegratedMicroManufacturer(module.getIntegratedMicroManufacturer());
			updatedModule.setIsVerified(false);
			updatedModule.setMaxSeriesFuseRating(module.getMaxSeriesFuseRating());
			String updateNum = "";
			if (updatedModule.getFirstUpdater() == null) {
				updateNum = "1st";
				updatedModule.setFirstUpdater(firstUpdater);
			} else if (updatedModule.getSecondUpdater() == null) {
				updateNum = "2nd";
				updatedModule.setSecondUpdater(firstUpdater);
			} else if (updatedModule.getThirdUpdater() == null) {
				updateNum = "3rd";
				updatedModule.setThirdUpdater(firstUpdater);
			}
			moduleRepo.save(updatedModule);
			historicActivity.recordActivity(idUserco, "", "",
					"libraries;Edit component " + module.getMake() + " " + module.getModel() + ".;Module", true, "", "",
					"");

			if (checkValue.isStringNotEmpty(updateNum)) {
				mailingService.mailUpdate("Module",
						"An existing equipment of Module " + module.getMake() + " " + module.getModel()
								+ " has been updated " + updateNum + " time by " + firstUpdater.getFirstName() + " "
								+ firstUpdater.getLastName(),
						firstUpdater.getEmail().contains("nuagetechnologies-tn.com")
								&& !firstUpdater.getEmail().contains("pm"));
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserco, "", "", "libraries;Edit component;Module ", false, "", "", "");
			return "fail";
		}
	}

	public String verifModule(ModuleFavRequest module, Long idUserco) {

		try {

			Cmodulev2 updatedModule = moduleRepo.findById(module.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Module not found for this id :" + module.getId()));

			AuthentificationEntity userCo = authentificationRepo.findById(idUserco)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idUserco));

			if (Boolean.TRUE.equals(module.getIsVerified())) {
				updatedModule.setVerifiedBy(userCo);
				updatedModule.setIsVerified(true);
				updatedModule.setDateVerification(new Date());
			} else if (Boolean.FALSE.equals(module.getIsVerified())) {
				updatedModule.setIsVerified(false);
			}
			moduleRepo.save(updatedModule);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String editModuleNotification(Long userID, String moduleMake, String moduleModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + userID));

			notificationEntityService.addNewNotif(userID, 0L, "Module Update", "Libraries",
					"Module Update - " + moduleModel, "The module " + moduleModel + " " + moduleMake
							+ " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addModuleNotification(Long userID, String moduleMake, String moduleModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + userID));

			notificationEntityService.addNewNotif(
					userID, 0L, "New Module", "Libraries", "New Module - " + moduleModel, "The module " + moduleModel
							+ " " + moduleMake + " was added by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	// CR 914
	// Change Request Modules List Management

	/**
	 * @author seifn Actived the Module library
	 * @param idModule
	 * @return
	 */

	public String moduleLibraryActived(Long idModule, Long idUserco) {

		try {

			Cmodulev2 moduleLibrary = moduleRepo.findById(idModule)
					.orElseThrow(() -> new ResourceNotFoundException("Module not found for this id :" + idModule));
			if (Boolean.TRUE.equals(moduleRepo.existByModelAndManufactuter(
					moduleLibrary.getModel().trim().toLowerCase(), moduleLibrary.getMake().trim().toLowerCase()))) {
				return "exist";
			} else {

				if (moduleLibrary.getId() != 0) {

					moduleLibrary.setDeleted(false);
					historicActivity.recordActivity(idUserco, "", "", "libraries;Activate component "
							+ moduleLibrary.getMake() + " " + moduleLibrary.getModel() + ".;Module", true, "", "", "");
					return "true";

				}
				historicActivity.recordActivity(idUserco, "", "", "libraries;Activate component;Module ", false, "", "",
						"");
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserco, "", "", "libraries;Activate component;Module ", false, "", "",
					"");
			return "false";
		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfModuleDeleted(Long idModule) {
		try {
			return permitArraysEntityRepo.findByPvModuleId(idModule);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public boolean deleteModuleLibrary(Long id, Long idUserco) {

		try {

			if (id != 0) {
				Cmodulev2 moduleLibrary = moduleRepo.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + id));
				moduleLibrary.setDeleted(true);

				List<ModuleLibraryEntity> moduleFav = moduleFavRepo.findAllByCmodulev2Id(id);
				if (moduleFav != null && !moduleFav.isEmpty()) {

					for (ModuleLibraryEntity moduleFavres : moduleFav) {
						moduleFavRepo.delete(moduleFavres);
					}
				}
				historicActivity.recordActivity(idUserco, "", "", "libraries;Delete component "
						+ moduleLibrary.getMake() + " " + moduleLibrary.getModel() + ".;Module", true, "", "", "");
				return true;

			}
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserco, "", "", "libraries;Delete component;Module ", false, "", "", "");
			return false;
		}
		historicActivity.recordActivity(idUserco, "", "", "libraries;Delete component;Module ", false, "", "", "");
		return false;
	}

	/*
	 * Edit Module Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long idModule, Long userID) {
		List<UsersEntityResult> usersList = new ArrayList<>();
		try {
			List<ModuleLibraryEntity> moduleFav = moduleFavRepo.findAllByCmodulev2Id(idModule);
			if (moduleFav != null && !moduleFav.isEmpty()) {
				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < moduleFav.size(); i++) {
					if (moduleFav.get(i) != null && moduleFav.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(moduleFav.get(i).getAuthentificationEntity().getId());
					}
				}
				usersList = authentificationRepo.findUserHaveNotFav(usersFavID, false, true, userID);

			} else {
				usersList = authentificationRepo.findUserHaveNotFav(null, false, true, userID);
			}
			return usersList;

		} catch (Exception e) {
			e.printStackTrace();
			return usersList;
		}

	}

	public String editUsersFavoriteList(Long idModule, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {
		try {

			Cmodulev2 module = moduleRepo.findById(idModule)
					.orElseThrow(() -> new ResourceNotFoundException("Module not found for this id :" + idModule));
			for (int i = 0; i < listUsers.length; i++) {

				AuthentificationEntity userCo = authentificationRepo.findById(listUsers[i])
						.orElseThrow(() -> new ResourceNotFoundException("User not found for this id."));

				ModuleLibraryEntity moduleFav = new ModuleLibraryEntity(userCo, module);
				moduleFav.setCmodulev2(module);
				moduleFav.setAuthentificationEntity(userCo);
				moduleFavRepo.save(moduleFav);
				historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + module.getMake() + " " + module.getModel()
								+ " is added to the user " + userCo.getFirstName() + " " + userCo.getLastName()
								+ ".;Module",
						true, numTab, sessionId, openDate);

			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Module to Users Favorites List;Module ", false, numTab, sessionId, openDate);
			return "error";
		}
	}

	public ModuleInfoModel getModuleById(Long moduleId) {
		try {
			return moduleRepo.getModuleById(moduleId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ComponentModel> getModuleList(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("make").and(Sort.by("model")));
			String[] model = checkValue.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			if (model == null) {
				Page<Cmodulev2> p = moduleRepo.findByIsDeleted(false, pageable);
				return new PageImpl<ComponentModel>(p.stream()
						.map(c -> new ComponentModel(c.getId(), null, c.getModel())).collect(Collectors.toList()),
						pageable, p.getTotalElements()).getContent();
			} else {
				Page<Cmodulev2> p = moduleRepo.findAll(filter(null, model, false, request.getIsDeleted(), null),
						pageable);
				return new PageImpl<ComponentModel>(p.stream()
						.map(c -> new ComponentModel(c.getId(), null, c.getModel())).collect(Collectors.toList()),
						pageable, p.getTotalElements()).getContent();
			}

		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	private Specification<Cmodulev2> filter(String[] makes, String[] models, Boolean favorite, Boolean deleted,
			Long userId) {

		return new Specification<Cmodulev2>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Cmodulev2> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesMake = new ArrayList<>();
				List<Predicate> predicatesModel = new ArrayList<>();
				for (int i = 0; makes != null && i < makes.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("make")), "%" + makes[i].toLowerCase() + "%");
					predicatesMake.add(predicate);
				}
				for (int i = 0; models != null && i < models.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("model")), "%" + models[i].toLowerCase() + "%");
					predicatesModel.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate endPredicateMake = makes != null
						? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()]))
						: null;
				Predicate endPredicateModel = models != null
						? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel);
				if (Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
//					In<Long> inClause = cb.in(root.get("id"));
//					for (Long id : moduleFavRepo.findFavoriteInverters(userId)) {
//					    inClause.value(id);
//					}
//					list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

}
