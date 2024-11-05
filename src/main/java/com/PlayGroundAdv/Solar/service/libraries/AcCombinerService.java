package com.PlayGroundAdv.Solar.service.libraries;

import java.text.DateFormat;
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

import com.PlayGroundAdv.Solar.entity.ACCombinerFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ACCombinerSLCEntityModel;
import com.PlayGroundAdv.Solar.model.AcCombinerSLCModel;
import com.PlayGroundAdv.Solar.model.NewACCombinerSLCModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class AcCombinerService {

	final HistoryActivityService historyActivityService;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final AcCombinerSLCRepository acCombinerSLCRepo;
	final AuthenticationRepository authentificationRepo;
	final AcCombinerSLCsFavoritesRepository acCombinerSLCFavoritesRepo;
	final PermitArraysRepository permitArraysEntityRepo;
	final PermitRepository permitEntityRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo;
	final PathRepository pathRepo;
	final EquipmentUpdate mailingService;

	public AcCombinerService(HistoryActivityService historyActivityService,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			AcCombinerSLCRepository acCombinerSLCRepo, 
			AuthenticationRepository authentificationRepo, AcCombinerSLCsFavoritesRepository acCombinerSLCFavoritesRepo,
			PermitArraysRepository permitArraysEntityRepo, PermitRepository permitEntityRepo,
			PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo, PathRepository pathRepo,
			EquipmentUpdate mailingService) {
		super();
		this.historyActivityService = historyActivityService;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.acCombinerSLCRepo = acCombinerSLCRepo;
		this.authentificationRepo = authentificationRepo;
		this.acCombinerSLCFavoritesRepo = acCombinerSLCFavoritesRepo;
		this.permitArraysEntityRepo = permitArraysEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.permitProjectSiteInfoEntityRepo = permitProjectSiteInfoEntityRepo;
		this.pathRepo = pathRepo;
		this.mailingService = mailingService;
	}

	
	public Page<AcCombinerSLCModel> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValueTypesService.isStringNotEmpty(request.getManufacturer()) ? request.getManufacturer().split(" ") : null;
			String[] model = checkValueTypesService.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			String ratedCurrent = checkValueTypesService.isStringNotEmpty(request.getRatedCurrent()) ? request.getRatedCurrent().trim() : null;
			String deviceType = checkValueTypesService.isStringNotEmpty(request.getDeviceType()) ? request.getDeviceType().trim() : null;
			if (manufacturer == null && model == null && ratedCurrent == null && deviceType == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<ACCombinerSLC> p = acCombinerSLCRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());
			} else {
				Page<ACCombinerSLC> p = acCombinerSLCRepo.findAll(filter(manufacturer, model, ratedCurrent, deviceType, request.getIsFavorite(),
						request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Page<AcCombinerSLCModel> convertDto(Page<ACCombinerSLC> page, Pageable pageable, Long idUser, Boolean isdelete) {
		try {
			List<Long> acCombinerSLCFavID = getAcCombinerDiscoFavorite(idUser);
			return new PageImpl<>(
					page.stream().map(c -> new AcCombinerSLCModel(c.getId(), c.getManufacturer(), c.getModel(), c.getCombinerDeviceType(),
							c.getRatedOperationalVoltage(), c.getRatedCurrent(), c.getNumberOfPoles(), c.getNumberOfSpaces(),
							c.getOtherNumberOfSpaces(), c.getNemaRating(), c.getMaxInput(), c.getQtyOfFuse(), c.getType(),
							c.getDropdownOption(), c.getLastUpdate(), isdelete, c.getIdOwner().getFirstName() + " " + c.getIdOwner().getLastName(), 
							acCombinerSLCFavID.indexOf(c.getId()) != -1, c.getIdOwner().getId()+"",
							false, c.getEroneousContent(), c.getEroneousContentOther(), c.getEroneousDescription(), 
							c.getHasCorrectionRequest(), c.getManufacturerMappingValue(), c.getModelMappingValue(), c.getCategory(),
							c.getFirstUpdater() != null ? new UsersEntityResult(c.getFirstUpdater().getId(), c.getFirstUpdater().getFirstName(), c.getFirstUpdater().getLastName()) : null,
							c.getSecondUpdater() != null ? new UsersEntityResult(c.getSecondUpdater().getId(), c.getSecondUpdater().getFirstName(), c.getSecondUpdater().getLastName()) : null,
							c.getThirdUpdater() != null ? new UsersEntityResult(c.getThirdUpdater().getId(), c.getThirdUpdater().getFirstName(), c.getThirdUpdater().getLastName()) : null,
							c.getVerifiedBy() != null ? new UsersEntityResult(c.getVerifiedBy().getId(), c.getVerifiedBy().getFirstName(), c.getVerifiedBy().getLastName()) : null,
							c.getIsVerified(), c.getDateVerification())).collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<ACCombinerSLC> filter(String[] makes, String[] models, String ratedCurrent, String deviceType, Boolean favorite, Boolean deleted, Long idUser) {

		return new Specification<ACCombinerSLC>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<ACCombinerSLC> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesMake = new ArrayList<>();
				List<Predicate> predicatesModel = new ArrayList<>();
				for (int i = 0; makes != null && i < makes.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("manufacturer")),
							"%" + makes[i].toLowerCase() + "%");
					predicatesMake.add(predicate);
				}
				for (int i = 0; models != null && i < models.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("model")), "%" + models[i].toLowerCase() + "%");
					predicatesModel.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate predicateRatedCurrent = ratedCurrent != null ? cb.equal(root.get("ratedCurrent"), ratedCurrent) : null;
				Predicate predicateDeviceType = deviceType != null ? cb.equal(root.get("combinerDeviceType"), deviceType) : null;
				Predicate endPredicateMake = makes != null ? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()])) : null;
				Predicate endPredicateModel = models != null ? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()])) : null;
				
				List<Predicate> list = Arrays.asList(predicateDeleted, predicateRatedCurrent, predicateDeviceType, endPredicateMake, endPredicateModel);
				if(Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : getAcCombinerDiscoFavorite(idUser)) {
					    inClause.value(id);
					}
					list = Arrays.asList(predicateDeleted, predicateRatedCurrent, predicateDeviceType, endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	/*
	 * Get AC Combiner / Disconnects Fav
	 */
	public List<Long> getAcCombinerDiscoFavorite(Long idUser) {

		List<Long> acCombinerDiscoFavID = new ArrayList<>();
		try {
			if (idUser != null) {
				List<ACCombinerFavLibraryEntity> acCombinerDiscoFav = acCombinerSLCFavoritesRepo
						.findByAuthentificationEntityId(idUser);
				if (acCombinerDiscoFav != null && !acCombinerDiscoFav.isEmpty()) {
					acCombinerDiscoFavID = acCombinerDiscoFav.stream()
		                    .map(f -> f.getAcCombinerSLC().getId() )
		                    .collect(Collectors.toList());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acCombinerDiscoFavID;
	}

	/*
	 * Edit AC Combiner / Disconnects Favorite List
	 */
	public String editAcCombinerDiscoFavoriteList(Long acCombinerDiscoID, Boolean isShown, String ipUser,
			String timeZoneUser, Long idUserCo, Long idOwner, String numTab, String sessionId, String openDate) {
		try {
			AuthentificationEntity user = authentificationRepo.findById(idOwner).orElse(new AuthentificationEntity());

			ACCombinerSLC acCombinerDisco = acCombinerSLCRepo.findById(acCombinerDiscoID).orElse(new ACCombinerSLC());
			if (checkValueTypesService.Equals(isShown, true)) {
				ACCombinerFavLibraryEntity acCombinerDiscoFav = new ACCombinerFavLibraryEntity(user, acCombinerDisco);
				acCombinerSLCFavoritesRepo.save(acCombinerDiscoFav);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + acCombinerDisco.getManufacturer() + " " + acCombinerDisco.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;AC Combiner/SLC",
						true, numTab, sessionId, openDate);
			} else {
				ACCombinerFavLibraryEntity acCombinerDiscoFav = acCombinerSLCFavoritesRepo
						.findByAcCombinerSlcIdAndAuthentificationEntityId(acCombinerDiscoID, idOwner);
				acCombinerSLCFavoritesRepo.delete(acCombinerDiscoFav);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + acCombinerDisco.getManufacturer() + " " + acCombinerDisco.getModel()
								+ " is removed from the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;AC Combiner/SLC",
						true, numTab, sessionId, openDate);
			}
			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add AC Combiner/SLC to Users Favorites List;AC Combiner/SLC", false, numTab, sessionId,
					openDate);
			return "error";
		}
	}

	/*
	 * Edit AC Combiner / Disconnect Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long aCCombinerSLC, Long userID) {

		List<UsersEntityResult> usersList = new ArrayList<>();
		// 03-05-2019: M.A: Junit correction
		try {
			List<ACCombinerFavLibraryEntity> acCombinerDisco = acCombinerSLCFavoritesRepo
					.findByAcCombinerSlcId(aCCombinerSLC);

			if (acCombinerDisco != null && !acCombinerDisco.isEmpty()) {

				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < acCombinerDisco.size(); i++) {
					if (acCombinerDisco.get(i) != null && acCombinerDisco.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(acCombinerDisco.get(i).getAuthentificationEntity().getId());
					}
				}

				if (userID != null)
					usersList = authentificationRepo.findUserHaveNotFav(usersFavID, false, true, userID);
				return usersList;

			} else {
				if (userID != null)
					usersList = authentificationRepo.findUserHaveNotFav(null, false, true, userID);

				return usersList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return usersList;
		}
	}

	public String editUsersFavoriteList(Long aCCombinerSLC, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {
		try {

			ACCombinerSLC acCombinerDisco = acCombinerSLCRepo.findById(aCCombinerSLC).orElse(new ACCombinerSLC());
			for (int i = 0; i < listUsers.length; i++) {
				AuthentificationEntity user = authentificationRepo.findById(listUsers[i])
						.orElse(new AuthentificationEntity());

				ACCombinerFavLibraryEntity acCombinerDiscoFav = new ACCombinerFavLibraryEntity(user, acCombinerDisco);
				acCombinerSLCFavoritesRepo.save(acCombinerDiscoFav);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + acCombinerDisco.getManufacturer() + " " + acCombinerDisco.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;AC Combiner/SLC",
						true, numTab, sessionId, openDate);
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add AC Combiner/SLC to Users Favorites List;AC Combiner/SLC", false, numTab, sessionId,
					openDate);
			return "error";
		}
	}

	/*
	 * Add New AC Combiner / Disconnects
	 */
	public List<AcCombinerSLCModel> checkACCombinerExistent(NewACCombinerSLCModel newAcCombinerDisco, Long idUser) {

		List<AcCombinerSLCModel> acCombinerDiscoList = new ArrayList<>();
		try {
			// 03-08-2019: M.A : Junit Correction
			if (newAcCombinerDisco != null) {
				List<ACCombinerSLC> listAcCombinerDisco1 = acCombinerSLCRepo.findByModelAndManufacturerNotAndIsDeleted(
						newAcCombinerDisco.getModel().trim().toLowerCase(), newAcCombinerDisco.getManufacturer().trim().toLowerCase(), false);
				List<ACCombinerSLC> listAcCombinerDisco2 = acCombinerSLCRepo.findByModelAndManufacturerAndIsDeleted(
						newAcCombinerDisco.getModel().trim().toLowerCase(), newAcCombinerDisco.getManufacturer().trim().toLowerCase(), false);
				List<ACCombinerSLC> listAcCombinerDisco3 = acCombinerSLCRepo
						.findByRatedCurrentAndManufacturerAndNumberOfSpacesAndCombinerDeviceTypeAndIsDeleted(
								newAcCombinerDisco.getRatedCurrent(), newAcCombinerDisco.getManufacturer().trim().toLowerCase(),
								newAcCombinerDisco.getNumberSpaces(), newAcCombinerDisco.getDeviceType(), false);

				if (listAcCombinerDisco1 != null && !listAcCombinerDisco1.isEmpty()) {
					List<Long> acCombinerDiscoFavID;
					acCombinerDiscoFavID = getAcCombinerDiscoFavorite(idUser);
					AcCombinerSLCModel acCombinerDisco = new AcCombinerSLCModel();

					if (listAcCombinerDisco1.get(0) != null) {
						if (acCombinerDiscoFavID != null
								&& acCombinerDiscoFavID.contains(listAcCombinerDisco1.get(0).getId())) {
							acCombinerDisco.setIsShown(true);
						} else {
							acCombinerDisco.setIsShown(false);
						}
						acCombinerDisco.setId(listAcCombinerDisco1.get(0).getId());
						acCombinerDisco.setManufacturer(listAcCombinerDisco1.get(0).getManufacturer());
						acCombinerDisco.setModel(listAcCombinerDisco1.get(0).getModel());
						acCombinerDisco
								.setManufacturerMappingValue(listAcCombinerDisco1.get(0).getManufacturerMappingValue());
						acCombinerDisco.setModelMappingValue(listAcCombinerDisco1.get(0).getModelMappingValue());
						acCombinerDisco.setCombinerDeviceType(listAcCombinerDisco1.get(0).getCombinerDeviceType());
						acCombinerDisco.setDropdownOption(listAcCombinerDisco1.get(0).getDropdownOption());
						acCombinerDisco.setMaxInput(listAcCombinerDisco1.get(0).getMaxInput());
						acCombinerDisco.setNemaRating(listAcCombinerDisco1.get(0).getNemaRating());
						acCombinerDisco.setNumberOfPoles(listAcCombinerDisco1.get(0).getNumberOfPoles());
						acCombinerDisco.setOtherNumberOfSpaces(listAcCombinerDisco1.get(0).getOtherNumberOfSpaces());
						acCombinerDisco.setNumberOfSpaces(listAcCombinerDisco1.get(0).getNumberOfSpaces());

						acCombinerDisco.setQtyOfFuse(listAcCombinerDisco1.get(0).getQtyOfFuse());
						acCombinerDisco.setRatedCurrent(listAcCombinerDisco1.get(0).getRatedCurrent());
						acCombinerDisco
								.setRatedOperationalVoltage(listAcCombinerDisco1.get(0).getRatedOperationalVoltage());
						acCombinerDisco.setType(listAcCombinerDisco1.get(0).getType());
						if (listAcCombinerDisco1.get(0).getIdOwner() != null) {
							acCombinerDisco.setOwner(listAcCombinerDisco1.get(0).getIdOwner().getFirstName() + " "
									+ listAcCombinerDisco1.get(0).getIdOwner().getLastName());
							acCombinerDisco.setIdOwner(listAcCombinerDisco1.get(0).getIdOwner().getId() + "");
						}
						acCombinerDisco.setLastUpdate(listAcCombinerDisco1.get(0).getLastUpdate());
						acCombinerDisco.setIsDeleted(false);
						acCombinerDisco.setHasCorrectionRequest(listAcCombinerDisco1.get(0).getHasCorrectionRequest());
						acCombinerDisco.setEroneousContent(listAcCombinerDisco1.get(0).getEroneousContent());
						acCombinerDisco.setEroneousContentOther(listAcCombinerDisco1.get(0).getEroneousContentOther());
						acCombinerDisco.setEroneousDescription(listAcCombinerDisco1.get(0).getEroneousDescription());

					}
					acCombinerDiscoList.add(0, acCombinerDisco);

				} else if (listAcCombinerDisco3 != null && !listAcCombinerDisco3.isEmpty()) {
					List<Long> acCombinerDiscoFavID;
					acCombinerDiscoFavID = getAcCombinerDiscoFavorite(idUser);
					AcCombinerSLCModel acCombinerDisco = new AcCombinerSLCModel();
					if (acCombinerDiscoFavID != null && listAcCombinerDisco3.get(0) != null) {
						if (acCombinerDiscoFavID.contains(listAcCombinerDisco3.get(0).getId())) {
							acCombinerDisco.setIsShown(true);
						} else {
							acCombinerDisco.setIsShown(false);
						}
						acCombinerDisco.setId(listAcCombinerDisco3.get(0).getId());
						acCombinerDisco.setManufacturer(listAcCombinerDisco3.get(0).getManufacturer());
						acCombinerDisco.setModel(listAcCombinerDisco3.get(0).getModel());
						acCombinerDisco
								.setManufacturerMappingValue(listAcCombinerDisco3.get(0).getManufacturerMappingValue());
						acCombinerDisco.setModelMappingValue(listAcCombinerDisco3.get(0).getModelMappingValue());
						acCombinerDisco.setCombinerDeviceType(listAcCombinerDisco3.get(0).getCombinerDeviceType());
						acCombinerDisco.setOtherNumberOfSpaces(listAcCombinerDisco3.get(0).getOtherNumberOfSpaces());
						acCombinerDisco.setNumberOfSpaces(listAcCombinerDisco3.get(0).getNumberOfSpaces());

						acCombinerDisco.setDropdownOption(listAcCombinerDisco3.get(0).getDropdownOption());
						acCombinerDisco.setMaxInput(listAcCombinerDisco3.get(0).getMaxInput());
						acCombinerDisco.setNemaRating(listAcCombinerDisco3.get(0).getNemaRating());
						acCombinerDisco.setNumberOfPoles(listAcCombinerDisco3.get(0).getNumberOfPoles());
						acCombinerDisco.setQtyOfFuse(listAcCombinerDisco3.get(0).getQtyOfFuse());
						acCombinerDisco.setRatedCurrent(listAcCombinerDisco3.get(0).getRatedCurrent());
						acCombinerDisco
								.setRatedOperationalVoltage(listAcCombinerDisco3.get(0).getRatedOperationalVoltage());
						acCombinerDisco.setType(listAcCombinerDisco3.get(0).getType());
						if (listAcCombinerDisco3.get(0).getIdOwner() != null) {
							acCombinerDisco.setOwner(listAcCombinerDisco3.get(0).getIdOwner().getFirstName() + " "
									+ listAcCombinerDisco3.get(0).getIdOwner().getLastName());
							acCombinerDisco.setIdOwner(listAcCombinerDisco3.get(0).getIdOwner().getId() + "");
						}
						acCombinerDisco.setLastUpdate(listAcCombinerDisco3.get(0).getLastUpdate());
						acCombinerDisco.setIsDeleted(false);
						acCombinerDisco.setHasCorrectionRequest(listAcCombinerDisco3.get(0).getHasCorrectionRequest());
						acCombinerDisco.setEroneousContent(listAcCombinerDisco3.get(0).getEroneousContent());
						acCombinerDisco.setEroneousContentOther(listAcCombinerDisco3.get(0).getEroneousContentOther());
						acCombinerDisco.setEroneousDescription(listAcCombinerDisco3.get(0).getEroneousDescription());
					}
					acCombinerDiscoList.add(0, acCombinerDisco);

				} else if (listAcCombinerDisco2 != null && !listAcCombinerDisco2.isEmpty()) {

					List<Long> acCombinerDiscoFavID;
					acCombinerDiscoFavID = getAcCombinerDiscoFavorite(idUser);

					AcCombinerSLCModel acCombinerDisco = new AcCombinerSLCModel();
					if (listAcCombinerDisco2.get(0) != null) {
						if (acCombinerDiscoFavID != null
								&& acCombinerDiscoFavID.contains(listAcCombinerDisco2.get(0).getId())) {
							acCombinerDisco.setIsShown(true);
						} else {
							acCombinerDisco.setIsShown(false);
						}
						acCombinerDisco.setId(listAcCombinerDisco2.get(0).getId());
						acCombinerDisco.setManufacturer(listAcCombinerDisco2.get(0).getManufacturer());
						acCombinerDisco.setModel(listAcCombinerDisco2.get(0).getModel());
						acCombinerDisco
								.setManufacturerMappingValue(listAcCombinerDisco2.get(0).getManufacturerMappingValue());
						acCombinerDisco.setModelMappingValue(listAcCombinerDisco2.get(0).getModelMappingValue());
						acCombinerDisco.setCombinerDeviceType(listAcCombinerDisco2.get(0).getCombinerDeviceType());
						acCombinerDisco.setOtherNumberOfSpaces(listAcCombinerDisco2.get(0).getOtherNumberOfSpaces());
						acCombinerDisco.setNumberOfSpaces(listAcCombinerDisco2.get(0).getNumberOfSpaces());

						acCombinerDisco.setDropdownOption(listAcCombinerDisco2.get(0).getDropdownOption());
						acCombinerDisco.setMaxInput(listAcCombinerDisco2.get(0).getMaxInput());
						acCombinerDisco.setNemaRating(listAcCombinerDisco2.get(0).getNemaRating());
						acCombinerDisco.setNumberOfPoles(listAcCombinerDisco2.get(0).getNumberOfPoles());
						acCombinerDisco.setQtyOfFuse(listAcCombinerDisco2.get(0).getQtyOfFuse());
						acCombinerDisco.setRatedCurrent(listAcCombinerDisco2.get(0).getRatedCurrent());
						acCombinerDisco
								.setRatedOperationalVoltage(listAcCombinerDisco2.get(0).getRatedOperationalVoltage());
						acCombinerDisco.setType(listAcCombinerDisco2.get(0).getType());
						if (listAcCombinerDisco2.get(0).getIdOwner() != null) {
							acCombinerDisco.setOwner(listAcCombinerDisco2.get(0).getIdOwner().getFirstName() + " "
									+ listAcCombinerDisco2.get(0).getIdOwner().getLastName());
							acCombinerDisco.setIdOwner(listAcCombinerDisco2.get(0).getIdOwner().getId() + "");

						}
						acCombinerDisco.setLastUpdate(listAcCombinerDisco2.get(0).getLastUpdate());
						acCombinerDisco.setIsDeleted(false);
						acCombinerDisco.setHasCorrectionRequest(listAcCombinerDisco2.get(0).getHasCorrectionRequest());
						acCombinerDisco.setEroneousContent(listAcCombinerDisco2.get(0).getEroneousContent());
						acCombinerDisco.setEroneousContentOther(listAcCombinerDisco2.get(0).getEroneousContentOther());
						acCombinerDisco.setEroneousDescription(listAcCombinerDisco2.get(0).getEroneousDescription());
					}
					acCombinerDiscoList.add(0, acCombinerDisco);
				}
			}
			return acCombinerDiscoList;

		} catch (Exception e) {
			e.printStackTrace();
			return acCombinerDiscoList;
		}
	}

	public ACCombinerSLCEntityModel addAcCombinerDisco(NewACCombinerSLCModel acCombinerDisco, String ipUser, String timeZoneUser,
			Long idUserCo, Long idPermit, String numTab, String sessionId, String openDate) {

		ACCombinerSLCEntityModel aCCombinerSLCEntityModel = new ACCombinerSLCEntityModel();

		try {
			ACCombinerSLC newAcCombinerDisco = new ACCombinerSLC();
			AuthentificationEntity contractor = permitEntityRepo.findAuthentificationEntityByID(idPermit);
			AuthentificationEntity user = authentificationRepo.findById(idUserCo).orElse(new AuthentificationEntity());

			DateFormat dString = new SimpleDateFormat("MM-dd-yyyy");
			newAcCombinerDisco.setManufacturer(acCombinerDisco.getManufacturer());
			newAcCombinerDisco.setModel(acCombinerDisco.getModel());

			if (checkValueTypesService.NotEquals(acCombinerDisco.getManufacturerMappingValue(), ""))
				newAcCombinerDisco.setManufacturerMappingValue(acCombinerDisco.getManufacturerMappingValue());
			else
				newAcCombinerDisco.setManufacturerMappingValue(acCombinerDisco.getManufacturer());

			if (checkValueTypesService.NotEquals(acCombinerDisco.getModelMappingValue(), ""))
				newAcCombinerDisco.setModelMappingValue(acCombinerDisco.getModelMappingValue());
			else
				newAcCombinerDisco.setModelMappingValue(acCombinerDisco.getModel());

			newAcCombinerDisco.setType(acCombinerDisco.getType());
			newAcCombinerDisco.setDropdownOption(acCombinerDisco.getDropdownOption());

			newAcCombinerDisco.setRatedCurrent(acCombinerDisco.getRatedCurrent());
			newAcCombinerDisco.setCombinerDeviceType(acCombinerDisco.getDeviceType());
			newAcCombinerDisco.setRatedOperationalVoltage(acCombinerDisco.getOperationalVoltage());
			newAcCombinerDisco.setNumberOfPoles(acCombinerDisco.getNumberPole());
			newAcCombinerDisco.setNumberOfSpaces(acCombinerDisco.getNumberSpaces());
			newAcCombinerDisco.setOtherNumberOfSpaces(acCombinerDisco.getOtherNumberOfSpaces());
			newAcCombinerDisco.setCategory(acCombinerDisco.getCategory());

			newAcCombinerDisco.setIdOwner(user);
			newAcCombinerDisco.setIsDeleted(false);
			newAcCombinerDisco.setLastUpdate(dString.format(new Date()));
			newAcCombinerDisco
					.setHasSuperUserEdit(user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3);

			Date addDate = new Date();
			newAcCombinerDisco.setAddDate(addDate);
			newAcCombinerDisco.setIsVerified(false);
			acCombinerSLCRepo.save(newAcCombinerDisco);
			aCCombinerSLCEntityModel.setaCCombinerSLC(newAcCombinerDisco);
			ACCombinerFavLibraryEntity acCombinerDiscoFav = new ACCombinerFavLibraryEntity(contractor, newAcCombinerDisco);
			acCombinerSLCFavoritesRepo.save(acCombinerDiscoFav);
			mailingService.mailUpdate("AC Combiner/SLC",
					"A new equipment of AC Combiner/SLC " + acCombinerDisco.getManufacturer() + " "
							+ acCombinerDisco.getModel() + " has been added by " + user.getFirstName() + " "
							+ user.getLastName(),
							user.getEmail().contains("nuagetechnologies-tn.com") && !user.getEmail().contains("pm"));
			historyActivityService
					.recordActivity(idUserCo, ipUser, timeZoneUser,
							"libraries;Add component" + acCombinerDisco.getManufacturer() + " "
									+ acCombinerDisco.getModel() + ".;AC Combiner/SLC",
							true, numTab, sessionId, openDate);
			return aCCombinerSLCEntityModel;
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add AC Combiner/SLC;AC Combiner/SLC", false, numTab, sessionId, openDate);
			return aCCombinerSLCEntityModel;
		}
	}

	/*
	 * Edit AC Combiner / Disconnect
	 */

	public String editAcCombinerDisco(AcCombinerSLCModel acCombinerDisco, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {

		try {
			Boolean exist = acCombinerSLCRepo.findIfExist(acCombinerDisco.getId(), acCombinerDisco.getManufacturer(), acCombinerDisco.getModel());
			ACCombinerSLC editAcCombinerDisco = acCombinerSLCRepo.findById(acCombinerDisco.getId())
					.orElse(new ACCombinerSLC());
			
			// if there is AC combiners with the manufacturer and model
			if (Boolean.TRUE.equals(exist)) {
				return "exist";
			} else {
				AuthentificationEntity firstUpdater = authentificationRepo.findById(idUserCo).orElseThrow(
						() -> new ResourceNotFoundException("User not found for this id :" +idUserCo));

				String wasUpdatedBy = " was updated by ";
				DateFormat dString = new SimpleDateFormat("MM-dd-yyyy");

				// find all the permits that used the componenet
				List<PermitProjectSiteInfoEntity> permitProjectSiteInfoEntitys = permitProjectSiteInfoEntityRepo
						.findListOfProjectsUsingACCombiner(editAcCombinerDisco.getId() + ":"
								+ editAcCombinerDisco.getManufacturer() + ":" + editAcCombinerDisco.getModel());
				if (permitProjectSiteInfoEntitys != null && !permitProjectSiteInfoEntitys.isEmpty()) {
					// Update all the permits with the new components values
					for (PermitProjectSiteInfoEntity permitProjectSiteInfoEntity : permitProjectSiteInfoEntitys) {
						if (checkValueTypesService.Equals(permitProjectSiteInfoEntity.getRoofTopACCombinerDisconnect(),
								editAcCombinerDisco.getId() + ":" + editAcCombinerDisco.getManufacturer() + ":"
										+ editAcCombinerDisco.getModel())) {
							permitProjectSiteInfoEntity.setRoofTopACCombinerDisconnect(acCombinerDisco.getId() + ":"
									+ acCombinerDisco.getManufacturer() + ":" + acCombinerDisco.getModel());
						}
						if (checkValueTypesService.Equals(permitProjectSiteInfoEntity.getRooftopACCombinerModel(),
								editAcCombinerDisco.getId() + ":" + editAcCombinerDisco.getManufacturer() + ":"
										+ editAcCombinerDisco.getModel())) {
							permitProjectSiteInfoEntity.setRooftopACCombinerModel(acCombinerDisco.getId() + ":"
									+ acCombinerDisco.getManufacturer() + ":" + acCombinerDisco.getModel());
						}
						if (checkValueTypesService.Equals(permitProjectSiteInfoEntity.getRooftopACCombinerModelTwo(),
								editAcCombinerDisco.getId() + ":" + editAcCombinerDisco.getManufacturer() + ":"
										+ editAcCombinerDisco.getModel())) {
							permitProjectSiteInfoEntity.setRooftopACCombinerModelTwo(acCombinerDisco.getId() + ":"
									+ acCombinerDisco.getManufacturer() + ":" + acCombinerDisco.getModel());
						}
						if (checkValueTypesService.Equals(
								permitProjectSiteInfoEntity.getGroundLevelACCombinerDisconnectModel(),
								editAcCombinerDisco.getId() + ":" + editAcCombinerDisco.getManufacturer() + ":"
										+ editAcCombinerDisco.getModel())) {
							permitProjectSiteInfoEntity.setGroundLevelACCombinerDisconnectModel(acCombinerDisco.getId()
									+ ":" + acCombinerDisco.getManufacturer() + ":" + acCombinerDisco.getModel());
						}
						if (checkValueTypesService.Equals(
								permitProjectSiteInfoEntity.getGroundLevelACCombinerBoxModel().getId(),
								editAcCombinerDisco.getId())) {
							permitProjectSiteInfoEntity.setGroundLevelACCombinerBoxModel(
									permitProjectSiteInfoEntity.getGroundLevelACCombinerBoxModel());
						}
					}
				}

				// Update the componenet infos
				editAcCombinerDisco.setManufacturer(acCombinerDisco.getManufacturer());
				editAcCombinerDisco.setModel(acCombinerDisco.getModel());

				if (checkValueTypesService.NotEquals(acCombinerDisco.getManufacturerMappingValue(), ""))
					editAcCombinerDisco.setManufacturerMappingValue(acCombinerDisco.getManufacturerMappingValue());
				else
					editAcCombinerDisco.setManufacturerMappingValue(acCombinerDisco.getManufacturer());

				if (checkValueTypesService.NotEquals(acCombinerDisco.getModelMappingValue(), ""))
					editAcCombinerDisco.setModelMappingValue(acCombinerDisco.getModelMappingValue());
				else
					editAcCombinerDisco.setModelMappingValue(acCombinerDisco.getModel());

				editAcCombinerDisco.setCombinerDeviceType(acCombinerDisco.getCombinerDeviceType());
				editAcCombinerDisco.setNumberOfSpaces(acCombinerDisco.getNumberOfSpaces());

				editAcCombinerDisco.setDropdownOption(acCombinerDisco.getDropdownOption());
				editAcCombinerDisco.setMaxInput(acCombinerDisco.getMaxInput());
				editAcCombinerDisco.setNemaRating(acCombinerDisco.getNemaRating());
				editAcCombinerDisco.setNumberOfPoles(acCombinerDisco.getNumberOfPoles());
				editAcCombinerDisco.setNumberOfSpaces(acCombinerDisco.getNumberOfSpaces());
				editAcCombinerDisco.setOtherNumberOfSpaces(acCombinerDisco.getOtherNumberOfSpaces());

				editAcCombinerDisco.setQtyOfFuse(acCombinerDisco.getQtyOfFuse());
				editAcCombinerDisco.setRatedCurrent(acCombinerDisco.getRatedCurrent());
				editAcCombinerDisco.setRatedOperationalVoltage(acCombinerDisco.getRatedOperationalVoltage());
				editAcCombinerDisco.setType(acCombinerDisco.getType());
				editAcCombinerDisco.setLastUpdate(dString.format(new Date()));
				editAcCombinerDisco.setCategory(acCombinerDisco.getCategory());
				editAcCombinerDisco.setIsVerified(false);
				String updateNum = "";
				if(editAcCombinerDisco.getFirstUpdater() == null) {
					updateNum = "1st";	
					editAcCombinerDisco.setFirstUpdater(firstUpdater);
				}else if(editAcCombinerDisco.getSecondUpdater() == null) {
					updateNum = "2nd";				
					editAcCombinerDisco.setSecondUpdater(firstUpdater);
				}else if(editAcCombinerDisco.getThirdUpdater() == null) {
					updateNum = "3rd";				
					editAcCombinerDisco.setThirdUpdater(firstUpdater);
				}

				acCombinerSLCRepo.save(editAcCombinerDisco);

				// send a notification that the componenet has been updated (by who)
				AuthentificationEntity user = authentificationRepo.findById(idUserCo)
						.orElse(new AuthentificationEntity());

				notificationEntityService.addNewNotif(idUserCo, 0L, "Disconnect Update", "Libraries",
						"AC Disconnect Update - " + editAcCombinerDisco.getModel(),
						"The Combiner/Disconnect " + editAcCombinerDisco.getModel() + " "
								+ editAcCombinerDisco.getManufacturer() + wasUpdatedBy + user.getFirstName() + " "
								+ user.getLastName(),
						true);
				// Log this change
				historyActivityService.recordActivity(
						idUserCo, ipUser, timeZoneUser, "libraries;Edit component " + acCombinerDisco.getManufacturer()
								+ " " + acCombinerDisco.getModel() + ".;AC Combiner/SLC",
						true, numTab, sessionId, openDate);
				if (checkValueTypesService.isStringNotEmpty(updateNum)) {
					mailingService.mailUpdate("AC Combiner/SLC",
							"An existing equipment of AC Combiner/SLC " + acCombinerDisco.getManufacturer() + " "
									+ acCombinerDisco.getModel() + " has been updated " + updateNum + " time by "
									+ firstUpdater.getFirstName() + " " + firstUpdater.getLastName(),
									firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
				}

				return "success";
			}
		} catch (Exception e) {
			historyActivityService
					.recordActivity(idUserCo, ipUser, timeZoneUser,
							"libraries;Edit component" + acCombinerDisco.getManufacturer() + " "
									+ acCombinerDisco.getModel() + ".;AC Combiner/SLC",
							false, numTab, sessionId, openDate);
			return "error";
		}
	}

	/*
	 * Delete AC Combiner / Disconnect
	 */

	public List<ProjectForLibrariesModel> getRemoveAcCombinerDiscoConfirmation(AcCombinerSLCModel acCombinerDisco) {

		try {
			return acCombinerSLCRepo.findProjectUsingModel(acCombinerDisco.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public String deleteAcCombinerDisco(String acCombinerDiscoID, String ipUser, String timeZoneUser, Long idUserCo,
			String numTab, String sessionId, String openDate) {
		try {

			ACCombinerSLC acCombinerDisco = acCombinerSLCRepo.findById(Long.valueOf(acCombinerDiscoID))
					.orElse(new ACCombinerSLC());
			List<PermitProjectSiteInfoEntity> acCombinerDiscoProject = acCombinerSLCRepo
					.findInputsUsingModel(acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
							+ acCombinerDisco.getModel(), acCombinerDisco.getId());

			if (!acCombinerDiscoProject.isEmpty()) {

				for (int i = 0; i < acCombinerDiscoProject.size(); i++) {
					if (checkValueTypesService.Equals(
							acCombinerDiscoProject.get(i).getGroundLevelACCombinerDisconnectModel(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel())) {
						acCombinerDiscoProject.get(i).setGroundLevelACCombinerDisconnectModel("Removed");
					}
					if (checkValueTypesService.Equals(acCombinerDiscoProject.get(i).getRooftopACCombinerModel(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel())) {
						acCombinerDiscoProject.get(i).setRooftopACCombinerModel("Removed");
					}
					if (checkValueTypesService.Equals(acCombinerDiscoProject.get(i).getRooftopACCombinerModelTwo(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel())) {
						acCombinerDiscoProject.get(i).setRooftopACCombinerModelTwo("Removed");
					}
				}
			}

			/*
			 * Remove AC Combiner / Disconnect From all the favorite Lists
			 */
			List<ACCombinerFavLibraryEntity> acCombinerDiscoFav = acCombinerSLCFavoritesRepo
					.findByAcCombinerSlcId(Long.valueOf(acCombinerDiscoID));
			if (acCombinerDiscoFav != null && !acCombinerDiscoFav.isEmpty()) {
				for (int i = 0; i < acCombinerDiscoFav.size(); i++) {
					acCombinerSLCFavoritesRepo.delete(acCombinerDiscoFav.get(i));
				}
			}

			/*
			 * Remove AC Combiner / Disconnect From AC Combiner / Disconnect Library
			 */
			acCombinerDisco.setIsDeleted(true);
			acCombinerSLCRepo.save(acCombinerDisco);
			historyActivityService.recordActivity(
					idUserCo, ipUser, timeZoneUser, "libraries;Delete component " + acCombinerDisco.getManufacturer()
							+ " " + acCombinerDisco.getModel() + ".;AC Combiner/SLC",
					true, numTab, sessionId, openDate);
			return "Done";

		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "", false, numTab, sessionId,
					openDate);
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Delete AC Combiner/SLC.;AC Combiner/SLC", false, numTab, sessionId, openDate);
			return "error";
		}
	}

	public String editACCombinerNotification(Long userID, String inverterMake, String inverterModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).orElse(new AuthentificationEntity());

			notificationEntityService.addNewNotif(userID, 0L, "AC Combiner/SLC Update", "Libraries",
					"AC Combiner/SLC Update - " + inverterModel, "The AC Combiner/SLC " + inverterModel + " "
							+ inverterMake + " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String sendCorrectionRequest(CorrectionRequest request) {

		try {
			if (request.getId() != 0) {
				AuthentificationEntity user = authentificationRepo.findById(request.getIdUser())
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + request.getIdUser()));
				ACCombinerSLC library = acCombinerSLCRepo.findById(request.getId())
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + request.getId()));
				library.setHasCorrectionRequest(true);
				library.setEroneousContent(request.getEroneousContent());
				library.setEroneousContentOther(request.getEroneousContentOther());
				library.setEroneousDescription(request.getEroneousDescription());
				acCombinerSLCRepo.save(library);
				notificationEntityService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
						"Request Correction - " + library.getModel(),
						"The user " + user.getFirstName() + " " + user.getLastName() + " requested correction for the "
								+ library.getType() + " " + library.getManufacturer() + " " + library.getModel(),
						true);
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	/*
	 * Activate AC Combiner / Disconnect
	 */
	public String activateAcCombinerDisco(Long acCombinerDiscoID, String ipUser, String timeZoneUser, Long idUserCo,
			String numTab, String sessionId, String openDate) {
		try {

			ACCombinerSLC acCombinerDisco = acCombinerSLCRepo.findById(acCombinerDiscoID).orElse(new ACCombinerSLC());
			List<ACCombinerSLC> listOfExistACCombiner = acCombinerSLCRepo.findByModelAndManufacturerAndIsDeleted(
					acCombinerDisco.getModel(), acCombinerDisco.getManufacturer(), false);
			if (listOfExistACCombiner != null && !listOfExistACCombiner.isEmpty()) {
				return "exist";
			} else {
				acCombinerDisco.setIsDeleted(false);
				acCombinerSLCRepo.save(acCombinerDisco);
				historyActivityService
						.recordActivity(idUserCo, ipUser, timeZoneUser,
								"libraries;Activate component " + acCombinerDisco.getManufacturer() + " "
										+ acCombinerDisco.getModel() + ".;AC Combiner/SLC",
								true, numTab, sessionId, openDate);
				return "Done";
			}
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Activate.;AC Combiner/SLC",
					false, numTab, sessionId, openDate);
			return "error";
		}
	}

}
