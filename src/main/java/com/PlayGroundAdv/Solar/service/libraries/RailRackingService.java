package com.PlayGroundAdv.Solar.service.libraries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.MountingTypeEntity;
import com.PlayGroundAdv.Solar.entity.RackingAllowedRoofMaterial;
import com.PlayGroundAdv.Solar.entity.RackingAllowedRoofMaterialKey;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RailRackingFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.NewRailRackingModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.RailRackingModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.repositories.MountTypeRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.RailRackingOptionsRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RackingAllowedRoofMaterialRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class RailRackingService {


	private DateFormat dSTRING = new SimpleDateFormat("MM-dd-yyyy");
	final HistoryActivityService historyActivityService;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final RailRackingRepository railRackingRepo;
	final AuthenticationRepository authentificationEntityRepo;
	final RailRackingFavoriteRepository railRackingFavoritesRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo;
	final PermitRepository permitEntityRepo;
	final AuthenticationRepository authentificationRepo;
	final ModelMapper modelMapper;
	final MountTypeRepository mountingTypeRepo;
	final RailRackingOptionsRepository rackingOptionsRepo;
	final RackingAllowedRoofMaterialRepository allowedRoofMaterialRep;
	final RoofMaterialTypeRepository roofMaterialRepo;
	final RailRackingFavoriteRepository raikingFavRepo;	
	final EquipmentUpdate mailingService;



	public RailRackingService(HistoryActivityService historyActivityService,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			RailRackingRepository railRackingRepo, AuthenticationRepository authentificationEntityRepo,
			RailRackingFavoriteRepository railRackingFavoritesRepo,
			PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo, PermitRepository permitEntityRepo,
			AuthenticationRepository authentificationRepo, ModelMapper modelMapper,
			MountTypeRepository mountingTypeRepo, RailRackingOptionsRepository rackingOptionsRepo,
			RackingAllowedRoofMaterialRepository allowedRoofMaterialRep, RoofMaterialTypeRepository roofMaterialRepo,
			RailRackingFavoriteRepository raikingFavRepo, EquipmentUpdate mailingService) {
		super();
		this.historyActivityService = historyActivityService;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.railRackingRepo = railRackingRepo;
		this.authentificationEntityRepo = authentificationEntityRepo;
		this.railRackingFavoritesRepo = railRackingFavoritesRepo;
		this.permitProjectSiteInfoEntityRepo = permitProjectSiteInfoEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.authentificationRepo = authentificationRepo;
		this.modelMapper = modelMapper;
		this.mountingTypeRepo = mountingTypeRepo;
		this.rackingOptionsRepo = rackingOptionsRepo;
		this.allowedRoofMaterialRep = allowedRoofMaterialRep;
		this.roofMaterialRepo = roofMaterialRepo;
		this.raikingFavRepo = raikingFavRepo;
		this.mailingService = mailingService;
	}
	
	
	public Page<RailRackingModel> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValueTypesService.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValueTypesService.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ")
					: null;
			String typeOfSystem = checkValueTypesService.isStringNotEmpty(request.getMountingType())
					? request.getMountingType().trim()
					: null;
			String bts = checkValueTypesService.isStringNotEmpty(request.getBts()) ? request.getBts().trim() : null;
			if (manufacturer == null && model == null && typeOfSystem == null && bts == null
					&& !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<RailRacking> p = railRackingRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser());
			} else {
				Page<RailRacking> p = railRackingRepo.findAll(filter(manufacturer, model, typeOfSystem, bts,
						request.getIsFavorite(), request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Page<RailRackingModel> convertDto(Page<RailRacking> page, Pageable pageable, Long userId) {
		try {
			List<Long> favorite = getRailRackingFavorite(userId);
			return new PageImpl<>(
					page.stream().map(c -> new RailRackingModel(c.getId(), c.getManufacturer(), c.getModel(), 
							c.getWeight(), mountingTypeRepo.findMountingType(c.getId()), c.getIsDeleted(), favorite.indexOf(c.getId()) != -1,
							c.getIdOwner().getFirstName() + " " + c.getIdOwner().getLastName(),
							c.getLastUpdate(), c.getIdOwner().getId() + "", c.getHasCorrectionRequest(),
							c.getEroneousContent(), c.getEroneousContentOther(), c.getEroneousDescription(),
							c.getManufacturerMappingValue(), c.getModelMappingValue(), 
							c.getPvRailType() != null ? c.getPvRailType().getId() : null,
							c.getPvRailSpliceType() != null ? c.getPvRailSpliceType().getId() : null, 
							c.getMidClamp() != null ? c.getMidClamp().getId() : null , 
							c.getEndClamp() != null ? c.getEndClamp().getId() : null,
							c.getIntegratedStanchion(), c.getStanchionMfg(), c.getStanchionMfgMappingValue(),
							c.getStanchionModel(), c.getStanchionModelMappingValue(), allowedRoofMaterialRep.findAllowedRoofMaterial(c.getId()),
							c.getIntegratedFlashing(),
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
							c.getIsVerified(), c.getDateVerification())).collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<RailRacking> filter(String[] makes, String[] models, String typeOfSystem , String bts, Boolean favorite, Boolean deleted,	Long userId) {

		return new Specification<RailRacking>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<RailRacking> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
				
				Join<RailRacking, MountingTypeEntity> mt = root.join("mountType");
				Predicate predicateBts = bts != null ? cb.like(cb.lower(mt.get("mountingType")), "%" + bts + "%"): null;
				Predicate predicateTypeOfSystem = typeOfSystem != null ? cb.equal(mt.get("mountingType"), typeOfSystem) : null;
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate endPredicateMake = makes != null
						? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()]))
						: null;
				Predicate endPredicateModel = models != null
						? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()]))
						: null;
				List<Predicate> list = Arrays.asList(predicateBts, predicateTypeOfSystem, predicateDeleted, endPredicateMake, endPredicateModel);
				
				if (Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : getRailRackingFavorite(userId)) {
						inClause.value(id);
					}
					list = Arrays.asList(predicateBts, predicateTypeOfSystem, predicateDeleted, endPredicateMake, endPredicateModel, inClause);
				}
				query.distinct(true);//A.B Remove duplicate rows when we have join column @OneToMany
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	/*
	 * Get Rail & Racking Fav
	 */
	public List<Long> getRailRackingFavorite(Long idUser) {
		List<Long> railRackinFavID = new ArrayList<>();

		try {

			if (Boolean.TRUE.equals(railRackingFavoritesRepo.existsByAuthentificationEntityId(idUser))) {
				List<RailRackingFavLibraryEntity> railRackingFav = railRackingFavoritesRepo
						.findAllByAuthentificationEntityId(idUser);

				for (int i = 0; i < railRackingFav.size(); i++) {
					if (railRackingFav.get(i) != null
							&& railRackingFav.get(i).getRailRacking() != null) {
						railRackinFavID.add(railRackingFav.get(i).getRailRacking().getId());
					}
				}
				return railRackinFavID;
			} else
				return railRackinFavID;

		} catch (Exception e) {
			e.printStackTrace();
			return railRackinFavID;
		}

	}

	/*
	 * Edit Rail & Racking Favorite List
	 */
	public String editRailRackingFavoriteList(Long railRackingID, Boolean isShown, String ipUser, String timeZoneUser,
			Long idUserCo) {
		try {

			AuthentificationEntity user = authentificationEntityRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUserCo));
			if (checkValueTypesService.Equals(isShown, true)) {

				RailRacking railRacking = railRackingRepo.findById(railRackingID).orElseThrow(
						() -> new ResourceNotFoundException("Rail Racking not found for this id :" +railRackingID));

				RailRackingFavLibraryEntity railRackingFav = new RailRackingFavLibraryEntity(user, railRacking);
				railRackingFavoritesRepo.save(railRackingFav);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + railRacking.getManufacturer() + " " + railRacking.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;Rail & Racking",
						true, "", "", "");

			} else {
				RailRackingFavLibraryEntity railRackingFav = railRackingFavoritesRepo
						.findOneByAuthentificationEntityIdAndRailRackingId(idUserCo, railRackingID);
				railRackingFavoritesRepo.delete(railRackingFav);

				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + railRackingFav.getRailRacking().getManufacturer() + " "
								+ railRackingFav.getRailRacking().getModel() + " is removed from the user "
								+ user.getFirstName() + " " + user.getLastName() + ".;Rail & Racking",
						true, "", "", "");
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Update Rail Racking Users Favorites List.;Rail & Racking", false, "", "", "");
			return "error";
		}

	}
	/*
	 * Edit Rail & Racking Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long railRackingID, Long userID) {
		List<UsersEntityResult> usersList = new ArrayList<>();
		try {
			if (Boolean.TRUE.equals(railRackingFavoritesRepo.existsByRailRackingId(railRackingID))) {
				List<RailRackingFavLibraryEntity> railRackingFav = railRackingFavoritesRepo
						.findAllByRailRackingId(railRackingID);
				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < railRackingFav.size(); i++) {
					if (railRackingFav.get(i) != null && railRackingFav.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(railRackingFav.get(i).getAuthentificationEntity().getId());
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

	public String editUsersFavoriteList(Long railRackingID, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo) {
		try {

			RailRacking railRacking = railRackingRepo.findById(railRackingID).orElseThrow(
					() -> new ResourceNotFoundException("RailRacking not found for this id :" +railRackingID));
			for (int i = 0; i < listUsers.length; i++) {

				AuthentificationEntity user = authentificationEntityRepo.findById(listUsers[i]).orElseThrow(
						() -> new ResourceNotFoundException("User not found for this id."));

				RailRackingFavLibraryEntity railRackingFav = new RailRackingFavLibraryEntity(user, railRacking);
				railRackingFavoritesRepo.save(railRackingFav);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + railRacking.getManufacturer() + " " + railRacking.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;Rail & Racking",
						true, "", "", "");
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Rail Racking to Users Favorites List.;Rail & Racking", false, "", "", "");
			return "error";
		}
	}

	public String editUsersFavoriteList(Long[] ids, Boolean isShown, String ipUser, String timeZoneUser,
			Long idUserCo) {
		try {

			AuthentificationEntity user = authentificationEntityRepo.findById(ids[1]).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +ids[1]));
			if (checkValueTypesService.Equals(isShown, false)) {

				RailRacking railRacking = railRackingRepo.findById(ids[0]).orElseThrow(
						() -> new ResourceNotFoundException("RailRacking not found for this id :" +ids[0]));

				RailRackingFavLibraryEntity railRackingFav = new RailRackingFavLibraryEntity(user, railRacking);
				railRackingFavoritesRepo.save(railRackingFav);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + railRacking.getManufacturer() + " " + railRacking.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;Rail & Racking",
						true, "", "", "");
			} else {
				RailRackingFavLibraryEntity railRackingFav = railRackingFavoritesRepo
						.findOneByAuthentificationEntityIdAndRailRackingId(ids[1], ids[0]);

				railRackingFavoritesRepo.delete(railRackingFav);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + railRackingFav.getRailRacking().getManufacturer() + " "
								+ railRackingFav.getRailRacking().getModel() + " is removed from the user "
								+ user.getFirstName() + " " + user.getLastName() + ".;Rail & Racking",
						true, "", "", "");

			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Update Rail Racking Users Favorites List.;Rail & Racking", false, "", "", "");
			return "error";
		}
	}

	public String editManyUsersFavoriteList(Long idRailRacking, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo) {
		try {
			RailRacking railRacking = railRackingRepo.findById(idRailRacking).orElseThrow(
					() -> new ResourceNotFoundException("RailRacking not found for this id :" +idRailRacking));
			if (listUsers != null) {
				for (int i = 0; i < listUsers.length; i++) {

					AuthentificationEntity user = authentificationEntityRepo.findById(listUsers[i]).orElseThrow(
							() -> new ResourceNotFoundException("User not found for this id."));

					RailRackingFavLibraryEntity railRackingFav = new RailRackingFavLibraryEntity(user, railRacking);
					railRackingFavoritesRepo.save(railRackingFav);
					historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
							"libraries;The favorite " + railRacking.getManufacturer() + " " + railRacking.getModel()
									+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
									+ ".;Rail & Racking",
							true, "", "", "");
				}
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Rail Racking to Users Favorites List.;Rail & Racking", false, "", "", "");
			return "error";
		}
	}

	/*
	 * Add New Rail & Racking
	 */
	public List<RailRackingModel> checkRailRackingExistent(NewRailRackingModel newRailRacking, Long idUser) {

		List<RailRackingModel> railRackingList = new ArrayList<>();
		try {
			List<RailRacking> listRailRacking = railRackingRepo.findByManufacturerAndModelAndIsDeleted(
					newRailRacking.getManufacturer().toLowerCase().trim(), newRailRacking.getModel().toLowerCase().trim(), false);
			List<RailRacking> listRailRackingNot = railRackingRepo.findByManufacturerNotAndModelAndIsDeleted(
					newRailRacking.getManufacturer().toLowerCase().trim(), newRailRacking.getModel().toLowerCase().trim(), false);
			if (listRailRacking != null && !listRailRacking.isEmpty()) {
				List<Long> railRackingFavID = getRailRackingFavorite(idUser);

				for (int i = 0; i < listRailRacking.size(); i++) {

					RailRackingModel railRacking = new RailRackingModel();
					if (listRailRacking.get(i) != null) {
						railRacking.setIsShown(railRackingFavID != null && railRackingFavID.contains(listRailRacking.get(i).getId()));
						railRacking.setId(listRailRacking.get(i).getId());
						railRacking.setManufacturer(listRailRacking.get(i).getManufacturer());
						railRacking.setModel(listRailRacking.get(i).getModel());
						railRacking.setManufacturerMappingValue(listRailRacking.get(i).getManufacturerMappingValue());
						railRacking.setModelMappingValue(listRailRacking.get(i).getModelMappingValue());

						railRacking.setWeight(listRailRacking.get(i).getWeight());
						if (listRailRacking.get(i).getIdOwner() != null) {
							railRacking.setOwner(listRailRacking.get(i).getIdOwner().getFirstName() + " "
									+ listRailRacking.get(i).getIdOwner().getLastName());
							railRacking.setIdOwner(listRailRacking.get(i).getIdOwner().getId() + "");
						}
						railRacking.setLastUpdate(listRailRacking.get(i).getLastUpdate());
						railRacking.setIsDeleted(false);
						railRacking.setEroneousContent(listRailRacking.get(i).getEroneousContent());
						railRacking.setEroneousContentOther(listRailRacking.get(i).getEroneousContentOther());
						railRacking.setEroneousDescription(listRailRacking.get(i).getEroneousDescription());
						railRacking.setHasCorrectionRequest(listRailRacking.get(i).getHasCorrectionRequest());
					}
					railRackingList.add(i, railRacking);

				}

			} else if (listRailRackingNot != null && !listRailRackingNot.isEmpty()) {

				
				List<Long> railRackingFavID = getRailRackingFavorite(idUser);

				for (int i = 0; i < listRailRackingNot.size(); i++) {

					RailRackingModel railRacking = new RailRackingModel();
					if (listRailRackingNot.get(i) != null) {
						railRacking.setIsShown(railRackingFavID != null && railRackingFavID.contains(listRailRackingNot.get(i).getId()));
						railRacking.setId(listRailRackingNot.get(i).getId());
						railRacking.setManufacturer(listRailRackingNot.get(i).getManufacturer());
						railRacking.setModel(listRailRackingNot.get(i).getModel());
						railRacking.setManufacturerMappingValue(listRailRackingNot.get(i).getManufacturerMappingValue());
						railRacking.setModelMappingValue(listRailRackingNot.get(i).getModelMappingValue());
						railRacking.setWeight(listRailRackingNot.get(i).getWeight());
						if (listRailRackingNot.get(i).getIdOwner() != null) {
							railRacking.setOwner(listRailRackingNot.get(i).getIdOwner().getFirstName() + " "
									+ listRailRackingNot.get(i).getIdOwner().getLastName());
							railRacking.setIdOwner(listRailRackingNot.get(i).getIdOwner().getId() + "");
						}
						railRacking.setLastUpdate(listRailRackingNot.get(i).getLastUpdate());
						railRacking.setIsDeleted(false);
						railRacking.setEroneousContent(listRailRackingNot.get(i).getEroneousContent());
						railRacking.setEroneousContentOther(listRailRackingNot.get(i).getEroneousContentOther());
						railRacking.setEroneousDescription(listRailRackingNot.get(i).getEroneousDescription());
						railRacking.setHasCorrectionRequest(listRailRackingNot.get(i).getHasCorrectionRequest());
					}
					railRackingList.add(i, railRacking);

				}
			}
			return railRackingList;
		} catch (Exception e) {
			e.printStackTrace();
			return railRackingList;
		}

	}

	public RailRackingModel addRailRacking(NewRailRackingModel railRacking, String ipUser, String timeZoneUser,
			Long idUserCo, Long idPermit) {
		RailRacking newRailRacking = new RailRacking();
		RailRackingModel railRackingDto = new RailRackingModel();
		try {
			AuthentificationEntity contractor = new AuthentificationEntity();
			if (idPermit != 0)
				contractor = permitEntityRepo.findAuthentificationEntityByID(idPermit);
			else
				contractor = null;

			AuthentificationEntity user = authentificationEntityRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUserCo));

			newRailRacking.setManufacturer(railRacking.getManufacturer().trim());
			newRailRacking.setModel(railRacking.getModel().trim());

			if (checkValueTypesService.NotEquals(railRacking.getManufacturerMappingValue(), "")) {
				newRailRacking.setManufacturerMappingValue(railRacking.getManufacturerMappingValue());
			} else
				newRailRacking.setManufacturerMappingValue(railRacking.getManufacturer());

			if (checkValueTypesService.NotEquals(railRacking.getModelMappingValue(), "")) {
				newRailRacking.setModelMappingValue(railRacking.getModelMappingValue());
			} else
				newRailRacking.setModelMappingValue(railRacking.getModel());

			newRailRacking.setIdOwner(user);
			newRailRacking.setIsDeleted(false);
			newRailRacking.setLastUpdate(dSTRING.format(new Date()));

			newRailRacking.setHasSuperUserEdit(user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3);
			Date addDate = new Date();
			newRailRacking.setAddDate(addDate);
			newRailRacking.setIsVerified(false);
			railRackingRepo.save(newRailRacking);
			
			List<MountingTypeEntity> mountTypes = new ArrayList<>();
			for (String mountingType : railRacking.getMountType()) {
				MountingTypeEntity mount = new MountingTypeEntity();
				mount.setIdRail(newRailRacking);
				mount.setMountingType(mountingType);
				mountingTypeRepo.save(mount);
				mountTypes.add(mount);
			}
			newRailRacking.setMountType(mountTypes);
			railRackingRepo.save(newRailRacking);

			if (contractor != null) {
				RailRackingFavLibraryEntity railRackingFav = new RailRackingFavLibraryEntity(contractor,
						newRailRacking);
				railRackingFavoritesRepo.save(railRackingFav);
			}

			railRackingDto =new RailRackingModel();
			railRackingDto.setOwner(newRailRacking.getIdOwner().getFirstName() + " " + newRailRacking.getIdOwner().getLastName());
			railRackingDto.setIsShown(false);
			railRackingDto.setIdOwner(idUserCo + "");
			railRackingDto.setId(newRailRacking.getId());
			railRackingDto.setManufacturer(newRailRacking.getManufacturer());
			railRackingDto.setWeight(newRailRacking.getWeight());
			railRackingDto.setIsDeleted(newRailRacking.getIsDeleted());
			railRackingDto.setLastUpdate(newRailRacking.getLastUpdate());
			railRackingDto.setHasCorrectionRequest(newRailRacking.getHasCorrectionRequest());
			railRackingDto.setEroneousContent(newRailRacking.getEroneousContent());
			railRackingDto.setEroneousContentOther(newRailRacking.getEroneousContentOther());
			railRackingDto.setEroneousDescription(newRailRacking.getEroneousDescription());
			railRackingDto.setManufacturerMappingValue(newRailRacking.getManufacturerMappingValue());
			railRackingDto.setModelMappingValue(newRailRacking.getModelMappingValue());
			railRackingDto.setPvRailType(newRailRacking.getPvRailType() != null ? newRailRacking.getPvRailType().getId() : null);
			railRackingDto.setPvRailSpliceType(newRailRacking.getPvRailSpliceType() != null ? newRailRacking.getPvRailSpliceType().getId() : null);
			railRackingDto.setMidClamp(newRailRacking.getMidClamp() != null ? newRailRacking.getMidClamp().getId() : null);
			railRackingDto.setEndClamp(newRailRacking.getEndClamp() != null ? newRailRacking.getEndClamp().getId() : null);
			railRackingDto.setIntegratedStanchion(newRailRacking.getIntegratedStanchion());
			railRackingDto.setStanchionMfg(newRailRacking.getStanchionMfg());
			railRackingDto.setStanchionMfgMappingValue(newRailRacking.getStanchionMfgMappingValue());
			railRackingDto.setStanchionModel(newRailRacking.getStanchionModel());
			railRackingDto.setStanchionModelMappingValue(newRailRacking.getStanchionModelMappingValue());
			railRackingDto.setIntegratedFlashing(newRailRacking.getIntegratedFlashing());
			railRackingDto.setModel(newRailRacking.getModel());
			List<String> types = new ArrayList<>();
    		for(MountingTypeEntity type :newRailRacking.getMountType()) {
				types.add(type.getMountingType());
	    	}
    		List<Long> allowedRoofMaterial = new ArrayList<>();
    		for(RackingAllowedRoofMaterial type :newRailRacking.getRackingAllowedRoofMaterial()) {
    			allowedRoofMaterial.add(type.getRoofMaterial().getId());
	    	}
    		railRackingDto.setMountType(types);
    		railRackingDto.setAllowedRoofMaterial(allowedRoofMaterial);
			mailingService.mailUpdate("Rail & Racking",
					"A new equipment of Rail & Racking " + newRailRacking.getManufacturer() + " "
							+ newRailRacking.getModel() + " has been added by " + user.getFirstName() + " "
							+ user.getLastName(),
							user.getEmail().contains("nuagetechnologies-tn.com") && !user.getEmail().contains("pm"));
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Add component "
					+ newRailRacking.getManufacturer() + " " + newRailRacking.getModel() + ".;Rail & Racking", true, "",
					"", "");
			return railRackingDto;
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add component.;Rail & Racking", false, "", "", "");
			return railRackingDto;
		}
	}

	public String editRailRacking(RailRackingModel railRacking, Long idUserCo) {
		try {
			
			AuthentificationEntity firstUpdater = authentificationRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUserCo));
		

			if (Boolean.TRUE.equals(railRackingRepo.existByManufacturerAndModelAndIdNot(
					railRacking.getManufacturer().trim().toLowerCase(), railRacking.getModel().trim().toLowerCase(), railRacking.getId()))) {
				return "exist";
			} else {
				List<RackingAllowedRoofMaterial> rackingAllowedRoofMaterial = allowedRoofMaterialRep
						.findByRailRackingIdAndRoofMaterialIdNotIn(railRacking.getId(),
								railRacking.getAllowedRoofMaterial());
				allowedRoofMaterialRep.deleteInBatch(rackingAllowedRoofMaterial);
				RailRacking editRailRacking = railRackingRepo.findById(railRacking.getId()).orElse(null);
				if (editRailRacking != null) {
					editRailRacking.setManufacturer(railRacking.getManufacturer().trim());
					editRailRacking.setModel(railRacking.getModel().trim());
					editRailRacking.setIntegratedStanchion(railRacking.getIntegratedStanchion());
					editRailRacking.setStanchionMfg(
							Boolean.TRUE.equals(railRacking.getIntegratedStanchion()) ? railRacking.getStanchionMfg()
									: null);
					editRailRacking
							.setStanchionMfgMappingValue(Boolean.TRUE.equals(railRacking.getIntegratedStanchion())
									? railRacking.getStanchionMfgMappingValue()
									: null);
					editRailRacking.setStanchionModel(
							Boolean.TRUE.equals(railRacking.getIntegratedStanchion()) ? railRacking.getStanchionModel()
									: null);
					editRailRacking
							.setStanchionModelMappingValue(Boolean.TRUE.equals(railRacking.getIntegratedStanchion())
									? railRacking.getStanchionModelMappingValue()
									: null);
					editRailRacking.setIntegratedFlashing(Boolean.TRUE.equals(railRacking.getIntegratedStanchion())
							? railRacking.getIntegratedFlashing()
							: null);

					if (checkValueTypesService.NotEquals(railRacking.getManufacturerMappingValue(), "")) {
						editRailRacking.setManufacturerMappingValue(railRacking.getManufacturerMappingValue());
					} else
						editRailRacking.setManufacturerMappingValue(railRacking.getManufacturer());

					if (checkValueTypesService.NotEquals(railRacking.getModelMappingValue(), "")) {
						editRailRacking.setModelMappingValue(railRacking.getModelMappingValue());
					} else
						editRailRacking.setModelMappingValue(railRacking.getModel());

					editRailRacking.setWeight(railRacking.getWeight());
					editRailRacking.setLastUpdate(dSTRING.format(new Date()));
					editRailRacking.setPvRailType(railRacking.getPvRailType() != null ? rackingOptionsRepo.findById(railRacking.getPvRailType()).orElse(null) : null);
					editRailRacking.setPvRailSpliceType(railRacking.getPvRailSpliceType() != null ? rackingOptionsRepo.findById(railRacking.getPvRailSpliceType()).orElse(null) : null);
					editRailRacking.setMidClamp(railRacking.getMidClamp() != null ? rackingOptionsRepo.findById(railRacking.getMidClamp()).orElse(null) : null);
					editRailRacking.setEndClamp(railRacking.getEndClamp() != null ? rackingOptionsRepo.findById(railRacking.getEndClamp()).orElse(null) : null);

					List<RoofMaterialType> roofMaterialTypes = roofMaterialRepo
							.findByIdIn(railRacking.getAllowedRoofMaterial());
					Set<RackingAllowedRoofMaterial> allowedRoofMaterial = new HashSet<>();
					for (RoofMaterialType roofMaterial : roofMaterialTypes) {
						RackingAllowedRoofMaterial allowedRoof = new RackingAllowedRoofMaterial(
								new RackingAllowedRoofMaterialKey(editRailRacking.getId(), roofMaterial.getId()));
						allowedRoof.setRailRacking(editRailRacking);
						allowedRoof.setRoofMaterial(roofMaterial);
						allowedRoofMaterial.add(allowedRoof);
					}
					editRailRacking.setRackingAllowedRoofMaterial(allowedRoofMaterial);
					editRailRacking.setIsVerified(false);
					String updateNum = "";
					if(editRailRacking.getFirstUpdater() == null) {
						updateNum = "1st";	
						editRailRacking.setFirstUpdater(firstUpdater);
					}else if(editRailRacking.getSecondUpdater() == null) {
						updateNum = "2nd";				
						editRailRacking.setSecondUpdater(firstUpdater);
					}else if(editRailRacking.getThirdUpdater() == null) {
						updateNum = "3rd";				
						editRailRacking.setThirdUpdater(firstUpdater);
					}

					railRackingRepo.save(editRailRacking);
					List<MountingTypeEntity> mountingType = mountingTypeRepo.findByIdRailId(railRacking.getId());
					if (mountingType != null && !mountingType.isEmpty()) {
						for (MountingTypeEntity typeM : mountingType) {
							mountingTypeRepo.delete(typeM);
						}
					}
					for (String typeMounting : railRacking.getMountType()) {
						MountingTypeEntity mount = new MountingTypeEntity();
						mount.setIdRail(editRailRacking);
						mount.setMountingType(typeMounting);
						mountingTypeRepo.save(mount);
					}

					historyActivityService.recordActivity(idUserCo, "", "", "libraries;Edit component "
							+ railRacking.getManufacturer() + " " + railRacking.getModel() + ".;Rail & Racking",
							true, "", "", "");
					
					
					if (checkValueTypesService.isStringNotEmpty(updateNum)) {
						mailingService.mailUpdate("Rail & Racking",
								"An existing equipment of Rail & Racking " + railRacking.getManufacturer() + " "
										+ railRacking.getModel() + " has been updated " + updateNum + " time by "
										+ firstUpdater.getFirstName() + " " + firstUpdater.getLastName(),
										firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
					}

					return "success";
				} else {
					historyActivityService.recordActivity(idUserCo, "", "", "libraries;Edit component.;Rail & Racking",
							false, "", "", "");
					return "fail";

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, "", "", "libraries;Edit component.;Rail & Racking", false,
					"", "", "");
			return "fail";
		}
	}
	
	public String verifRailRacking(RailRackingModel railRacking, Long idUserCo) {
		
		try {

			RailRacking updatedRailRacking = railRackingRepo.findById(railRacking.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Module not found for this id :" +railRacking.getId()));

			AuthentificationEntity userCo = authentificationRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUserCo));
			
			if(Boolean.TRUE.equals(railRacking.getIsVerified())) {
				updatedRailRacking.setVerifiedBy(userCo);
				updatedRailRacking.setIsVerified(true);
				updatedRailRacking.setDateVerification(new Date());
			}else if(Boolean.FALSE.equals(railRacking.getIsVerified())) {
				updatedRailRacking.setIsVerified(false);
			}
			railRackingRepo.save(updatedRailRacking);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public List<ProjectForLibrariesModel> getRemoveRailRackingConfirmation(Long idrailRacking) {
		try {
			return permitProjectSiteInfoEntityRepo.getPermitEntityByRailRacking(idrailRacking, false);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String deleteRailRacking(Long railRackingID, Long idUserCo) {
		try {

			RailRacking railRacking = railRackingRepo.findById(railRackingID).orElseThrow(
					() -> new ResourceNotFoundException("Rail Racking not found for this id :" +railRackingID));
			/*
			 * Remove Rail & Racking From all the favorite Lists
			 */
			List<RailRackingFavLibraryEntity> railRackingFav = railRackingFavoritesRepo
					.findAllByRailRackingId(railRackingID);
			if (railRackingFav != null && !railRackingFav.isEmpty()) {
				for (int i = 0; i < railRackingFav.size(); i++) {
					railRackingFavoritesRepo.delete(railRackingFav.get(i));
				}
			}

			/*
			 * Remove Rail & Racking From Rail Racking Library
			 */
			railRacking.setIsDeleted(true);

			railRackingRepo.save(railRacking);
			historyActivityService.recordActivity(idUserCo, "", "", "libraries;Delete component "
					+ railRacking.getManufacturer() + " " + railRacking.getModel() + ".;Rail & Racking", true, "", "",
					"");
			return "Done";

		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, "", "", "libraries;Delete component.;Rail & Racking", false,
					"", "", "");
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 
	 * @param railRackingID
	 * @param ipUser
	 * @param timeZoneUser
	 * @param idUserCo
	 * @return
	 */

	public RailRacking deleteRailRacking(Long railRackingID) {
		RailRacking railRacking = new RailRacking();
		try {
			railRacking = railRackingRepo.findById(railRackingID).orElseThrow(
					() -> new ResourceNotFoundException("Rail Racking not found for this id :" +railRackingID));

			/*
			 * Remove Rail & Racking From all the favorite Lists
			 */
			List<RailRackingFavLibraryEntity> railRackingFav = railRackingFavoritesRepo
					.findAllByRailRackingId(railRackingID);
			if (railRackingFav != null && !railRackingFav.isEmpty()) {
				for (int i = 0; i < railRackingFav.size(); i++) {
					railRackingFavoritesRepo.delete(railRackingFav.get(i));
				}
			}

			/*
			 * Remove Rail & Racking From Rail Racking Library
			 */
			railRacking.setIsDeleted(true);

			railRackingRepo.save(railRacking);

			return railRacking;

		} catch (Exception e) {
			e.printStackTrace();
			return railRacking;
		}
	}

	/*
	 * Activate Rail & Racking
	 */
	public String activateRailRacking(Long railRackingID, Long idUserCo) {
		try {

			RailRacking railRacking = railRackingRepo.findById(railRackingID).orElseThrow(
					() -> new ResourceNotFoundException("Rail Racking not found for this id :" +railRackingID));
			if (Boolean.TRUE.equals(railRackingRepo.existByManufacturerAndModelAndIdNot(railRacking.getManufacturer().trim().toLowerCase(),
					railRacking.getModel().trim().toLowerCase(), railRackingID))) {
				return "exist";
			} else {
				railRacking.setIsDeleted(false);

				railRackingRepo.save(railRacking);
				historyActivityService.recordActivity(idUserCo, "", "", "libraries;Activate component "
						+ railRacking.getManufacturer() + " " + railRacking.getModel() + ".;Rail & Racking", true, "",
						"", "");
				return "Done";
			}
		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, "", "", "libraries;Activate component .;Rail & Racking",
					false, "", "", "");
			e.printStackTrace();
			return "error";
		}
	}

	public String activateRailRacking(Long railRackingID) {
		try {
			RailRacking railRacking = railRackingRepo.findById(railRackingID).orElseThrow(
					() -> new ResourceNotFoundException("Rail Racking not found for this id :" +railRackingID));
			if (railRacking != null) {
				railRacking.setIsDeleted(false);
				railRackingRepo.save(railRacking);
			}
			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String editRailRackingNotification(Long userID, String railRackingMake, String railRackingModel) {
		try {

			AuthentificationEntity userCo = authentificationEntityRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));
			notificationEntityService.addNewNotif(userID, 0L, "Rail Racking Update", "Libraries",
					"Rail Racking Update - " + railRackingModel, "The Rail Racking " + railRackingModel + " "
							+ railRackingMake + " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addRailRackingNotification(Long userID, String railRackingMake, String railRackingModel) {
		try {

			AuthentificationEntity userCo = authentificationEntityRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));
			notificationEntityService.addNewNotif(userID, 0L, "New Rail Racking", "Libraries",
					"New Rail Racking- " + railRackingModel, "The Rail Racking " + railRackingModel + " "
							+ railRackingMake + " was added by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String sendCorrectionRequest(CorrectionRequest component) {

		try {
			if (component != null && component.getId() != 0) {

				RailRacking library = railRackingRepo.findById(component.getId()).orElseThrow(
						() -> new ResourceNotFoundException("Rail Racking not found for this id :" +component.getId()));

				AuthentificationEntity user = authentificationEntityRepo.findById(component.getIdUser()).orElseThrow(
						() -> new ResourceNotFoundException("User not found for this id :" +component.getIdUser()));

				library.setHasCorrectionRequest(true);
				library.setEroneousContent(component.getEroneousContent());
				library.setEroneousContentOther(component.getEroneousContentOther());
				library.setEroneousDescription(component.getEroneousDescription());
				railRackingRepo.save(library);
				notificationEntityService.addNewNotif(component.getIdUser(), 0L, "Request Correction", "Libraries",
						"Request Correction - " + library.getModel(),
						"The user " + user.getFirstName() + " " + user.getLastName() + " request correction for the "
								+ library.getTypeOfSystem() + " " + library.getManufacturer() + " "
								+ library.getModel(),
						true);

			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	public List<ComponentModel> getListOfGroundRailRacking() {
		List<ComponentModel> railRacking = new ArrayList<>();
		try {
			List<String> list = new ArrayList<>();
			list.add("Ground Mount - Ballast");
			list.add("Ground Mount - Helical Pile");
			list.add("Ground Mount - I-Beam Pile");
			list.add("Ground Mount - Concrete Footing");
			list.add("Ground Mount - Tracker Concrete Pile");
			list.add("Ground Mount - Tracker Concrete Spreader");
			list.add("Ground Mount - Tracker Helical Pile");
			list.add("Ground Mount - Pole Mount");
			railRacking = mountingTypeRepo.findRackingByMounting(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return railRacking;
	}

	public List<ComponentModel> getListRailRacking() {
		try {
				return railRackingRepo.getRailRackingList(false);			

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public void addToFavorite(Long modelId, Long userId, Long byId, String ipUser, String timeZoneUser) {
		try {
			Boolean isFav = raikingFavRepo.existsByRailRackingIdAndAuthentificationEntityId(modelId, userId);
			if (Boolean.FALSE.equals(isFav)) {
				RailRacking railRacking = railRackingRepo.findById(modelId).orElseThrow(
						() -> new ResourceNotFoundException("Rail Racking not found for this id :" +modelId));
				AuthentificationEntity user = authentificationEntityRepo.findById(userId).orElseThrow(
						() -> new ResourceNotFoundException("User not found for this id :" +userId));
				RailRackingFavLibraryEntity railRackingFav = new RailRackingFavLibraryEntity(user, railRacking);
				raikingFavRepo.save(railRackingFav);
				
				historyActivityService.recordActivity(byId, ipUser, timeZoneUser,
						"libraries;The favorite " + railRacking.getManufacturer() + " " + railRacking.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;Rail & Racking",
						true, "", "", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResponseEntity<byte[]> getOptPDF(HistoriqModel url){
		Path path = Paths.get((String) url.getObjectOne());
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
		return response;
	}

}
