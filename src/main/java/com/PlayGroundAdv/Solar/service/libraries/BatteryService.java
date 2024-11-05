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
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.BatteryFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.PermitAdditionalInfoEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.BatteryEntityModel;
import com.PlayGroundAdv.Solar.model.libraries.BatteryModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.repositories.PermitAdditionalInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectBatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class BatteryService {

	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final BatteryFavRepository batteryFavRepo;
	final BatteryRepository batteryRepo;
	final PermitRepository permitEntityRepo;
	final AuthenticationRepository userRepo;
	final PermitAdditionalInfoRepository permitAdditionalInfoEntityRepo;
	final ProjectBatteryRepository projectBatteryRepo;
	final EquipmentUpdate mailingService;

	public BatteryService(HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			BatteryFavRepository batteryFavRepo, BatteryRepository batteryRepo,
			PermitRepository permitEntityRepo, AuthenticationRepository userRepo,
			PermitAdditionalInfoRepository permitAdditionalInfoEntityRepo, ProjectBatteryRepository projectBatteryRepo,
			EquipmentUpdate mailingService) {
		super();
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.batteryFavRepo = batteryFavRepo;
		this.batteryRepo = batteryRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.userRepo = userRepo;
		this.permitAdditionalInfoEntityRepo = permitAdditionalInfoEntityRepo;
		this.projectBatteryRepo = projectBatteryRepo;
		this.mailingService = mailingService;
	}
	final static SimpleDateFormat Formatted_DATE = new SimpleDateFormat("MM/dd/yyyy");
	
	public Page<BatteryModel> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValueTypesService.isStringNotEmpty(request.getManufacturer()) ? request.getManufacturer().split(" ") : null;
			String[] model = checkValueTypesService.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			if (manufacturer == null && model == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<Battery> p = batteryRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());
			} else {
				Page<Battery> p = batteryRepo.findAll(filter(manufacturer, model, request.getIsFavorite(),
						request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Page<BatteryModel> convertDto(Page<Battery> page, Pageable pageable, Long idUser, Boolean isdelete) {
		try {
			List<Long> favorite = batteryFavRepo.findFavoriteBatteries(idUser);
			return new PageImpl<>(
					page.stream().map(c -> new BatteryModel(c.getId(), favorite.indexOf(c.getId()) != -1,
							c.getManufacturer(), c.getModel(), c.getOutputVolts(),
							c.getHours(), c.getType(),c.getEssEnergy(), c.getNotes(), c.getUpdated(), isdelete, 
							new UsersEntityResult(c.getAuthentificationEntity().getId(), c.getAuthentificationEntity().getFirstName(), c.getAuthentificationEntity().getLastName()), 
							c.getHasCorrectionRequest(), c.getEroneousContent(), c.getEroneousContentOther(), c.getEroneousDescription(),
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
	
	private Specification<Battery> filter(String[] makes, String[] models, Boolean favorite, Boolean deleted, Long idUser) {

		return new Specification<Battery>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Battery> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
				Predicate endPredicateMake = makes != null ? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()])) : null;
				Predicate endPredicateModel = models != null ? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()])) : null;
				
				List<Predicate> list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel);
				if(Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : batteryFavRepo.findFavoriteBatteries(idUser)) {
					    inClause.value(id);
					}
					list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	public String sendCorrectionBatteryRequest(CorrectionRequest request) {

		try {
			if (request.getId() != 0) {

				AuthentificationEntity user = userRepo.findById(request.getIdUser()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + request.getIdUser()));
				Battery library = batteryRepo.findById(request.getId()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + request.getId()));
				
				library.setHasCorrectionRequest(true);
				library.setEroneousContent(request.getEroneousContent());
				library.setEroneousContentOther(request.getEroneousContentOther());
				library.setEroneousDescription(request.getEroneousDescription());
				batteryRepo.save(library);
				notificationEntityService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
						"Request Correction - " + library.getModel(),
						"The user " + user.getFirstName() + " " + user.getLastName()
								+ " request correction for the Battery " + library.getManufacturer() + " "
								+ library.getModel(),
						true);

			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	/*
	 * Add batteryFav
	 */
	public String addBatteryFavorite(Long contractorId, Long batteryId, Long ownerId) {
		try {
			
			AuthentificationEntity contractor = userRepo.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + ownerId));
			Battery battery = batteryRepo.findById(batteryId).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + batteryId));
			BatteryFavLibraryEntity batteryFav = new BatteryFavLibraryEntity(contractor, battery);
			batteryFav.setAuthentificationEntity(contractor);
			batteryFav.setBattery(battery);
			batteryFavRepo.save(batteryFav);
			historicActivity.recordActivity(contractorId, "", "",
					"libraries;The favorite " + battery.getManufacturer() + " " + battery.getModel()
							+ " is added to the user " + contractor.getFirstName() + " " + contractor.getLastName()
							+ ".;Battery Library",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(contractorId, "", "",
					"libraries;Add Battery to user Favorites List;Battery Library", false, "", "", "");
			return "Fail";
		}
	}

	/*
	 * Delete batteryFav
	 */
	public String removeBatteryFavorite(Long contractorId, Long batteryId, Long ownerId) {

		try {
			AuthentificationEntity contractor = userRepo.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + ownerId));
			BatteryFavLibraryEntity batteryFav = batteryFavRepo
					.findByAuthentificationEntityIdAndBatteryId(ownerId, batteryId);
			batteryFavRepo.delete(batteryFav);
			List<PermitAdditionalInfoEntity> permitArray1 = permitAdditionalInfoEntityRepo
					.findByPermitEntityAuthentificationEntityIdAndBattery(ownerId, batteryId+"");
			for (int i = 0; !permitArray1.isEmpty() && i < permitArray1.size(); i++) {
				permitArray1.get(i).setBattery("Fav Removed");
				permitAdditionalInfoEntityRepo.save(permitArray1.get(i));
			}
			historicActivity.recordActivity(contractorId, "", "",
					"libraries;The favorite " + batteryFav.getBattery().getManufacturer() + " "
							+ batteryFav.getBattery().getModel() + " is removed from the user "
							+ contractor.getFirstName() + " " + contractor.getLastName() + ".;Battery Library",
					true, "", "", "");
		
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(contractorId, "", "",
					"libraries;Remove Battery from user Favorites List;Battery Library", false, "", "", "");
			return "Fail";
		}

	}

	/*
	 * edit Battery
	 */
	public String editBattery(BatteryModel battery, Long userId) {
		try {

			Boolean exist  = batteryRepo.existsByModelAndManufacturerAndIdNotAndIsDeleted(battery.getModel(), battery.getManufacturer(), battery.getId(), false);
			if (Boolean.FALSE.equals(exist)) {
				return editBatteryFunction(battery, userId);
			} else {
				return "exist";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editBatteryFunction(BatteryModel battery, Long userId) {

		try {
			AuthentificationEntity firstUpdater = userRepo.findById(userId).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userId));

			Battery updatedBattery = batteryRepo.findById(battery.getId()).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + battery.getId()));
			updatedBattery.setManufacturer(battery.getManufacturer());
			updatedBattery.setModel(battery.getModel());
			updatedBattery.setOutputVolts(battery.getOutputVolts());
			updatedBattery.setHours(battery.getHours());
			updatedBattery.setType(battery.getType());
			updatedBattery.setEssEnergy(battery.getEssEnergy());
			updatedBattery.setNotes(battery.getNotes());
			updatedBattery.setUpdated(Formatted_DATE.format(new Date()));
			updatedBattery.setIsVerified(false);
			String updateNum = "";
			if(updatedBattery.getFirstUpdater() == null) {
				updateNum = "1st";	
				updatedBattery.setFirstUpdater(firstUpdater);
			}else if(updatedBattery.getSecondUpdater() == null) {
				updateNum = "2nd";				
				updatedBattery.setSecondUpdater(firstUpdater);
			}else if(updatedBattery.getThirdUpdater() == null) {
				updateNum = "3rd";				
				updatedBattery.setThirdUpdater(firstUpdater);
			}

			batteryRepo.save(updatedBattery);
			historicActivity.recordActivity(userId, "", "", "libraries;Edit component " + battery.getManufacturer()
					+ " " + battery.getModel() + ".;Battery Library", true, "", "", "");
			editBatteryNotification(userId, battery.getManufacturer(), battery.getModel());
			
			if (checkValueTypesService.isStringNotEmpty(updateNum)) {
				mailingService.mailUpdate("Battery",
						"An existing equipment of Battery " + battery.getManufacturer() + " " + battery.getModel()
								+ " has been updated " + updateNum + " time by " + firstUpdater.getFirstName() + " "
								+ firstUpdater.getLastName(),
								firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userId, "", "", "libraries;Edit component.;Battery Library", false, "", "",
					"");
			return "fail";
		}
	}

	public String editBatteryNotification(Long userId, String batteryManufacturer, String batteryModel) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + userId));
			notificationEntityService.addNewNotif(userId, 0L, "Battery Update", "Libraries",
					"Battery Update - " + batteryModel, "The battery " + batteryModel + " " + batteryManufacturer
							+ " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addBatteryNotification(Long userId, String batteryManufacturer, String batteryModel) {
		try {
			AuthentificationEntity userCo = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + userId));
			notificationEntityService.addNewNotif(userId, 0L, "New Battery", "Libraries", "New Battery- " + batteryModel,
					"The battery " + batteryModel + " " + batteryManufacturer + " was added by " + userCo.getFirstName()
							+ " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
	
	// CR 924
	// Change Request Battery List Management
	public String batteryLibraryActived(Long idBattery, Long idUserCo) {

		try {

			Battery batteryLibrary = batteryRepo.findById(idBattery).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + idBattery));
			if (batteryLibrary.getId() != 0) {
				if (Boolean.TRUE.equals(batteryRepo.existsByModelAndManufacturerAndIsDeleted(batteryLibrary.getModel(),
						batteryLibrary.getManufacturer(), false))) {
					return "exist";
				} else {
					batteryLibrary.setDeleted(false);
					historicActivity.recordActivity(idUserCo, "", "", "libraries;Activate component "
							+ batteryLibrary.getManufacturer() + " " + batteryLibrary.getModel() + ".;Battery Library",
							true, "", "", "");
					return "true";
				}

			}
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Activate component "
					+ batteryLibrary.getManufacturer() + " " + batteryLibrary.getModel() + ".;Battery Library", false,
					"", "", "");
			return "false";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Activate component " + idBattery + ".;Battery Library", false, "", "", "");
			return "false";

		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfBatteryDeleted(Long idBattery) {
		
		try {
			return projectBatteryRepo.findProjectByBattery(idBattery);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public boolean deleteBatteryLibrary(Long id, Long idUserCo) {

		try {
			if (id != 0) {
				Battery batteryLibrary = batteryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + id));
				batteryLibrary.setDeleted(true);
				batteryRepo.save(batteryLibrary);
				List<BatteryFavLibraryEntity> batteryFav = batteryFavRepo.findByBatteryId(id);
				batteryFavRepo.deleteAll(batteryFav);

				historicActivity.recordActivity(idUserCo, "", "",
						"libraries;Delete component " + batteryLibrary.getManufacturer() + " "
								+ batteryLibrary.getModel() + ".;Battery Library",
						true, "", "", "");
				return true;

			}
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Delete component .;Battery Library", true, "",
					"", "");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Delete component.;Battery Library", true, "",
					"", "");
			return false;

		}

	}

	/*
	 * Edit Battery Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long batteryId, Long userId) {

		List<UsersEntityResult> usersList = new ArrayList<>();
		try {
			List<BatteryFavLibraryEntity> batteryFav = batteryFavRepo.findByBatteryId(batteryId);
			if (batteryFav != null && !batteryFav.isEmpty()) {
				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < batteryFav.size(); i++) {
					if (batteryFav.get(i) != null && batteryFav.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(batteryFav.get(i).getAuthentificationEntity().getId());
					}
				}

				usersList = userRepo.findUserHaveNotFav(usersFavID, false, true,
						userId);

			} else {

				usersList = userRepo.findUserHaveNotFav(null, false, true, userId);
			}
			return usersList;

		} catch (Exception e) {
			e.printStackTrace();
			return usersList;
		}

	}

	public String editUsersFavoriteList(Long batteryId, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {
		try {

			Battery battery = batteryRepo.findById(batteryId).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + batteryId));
			for (int i = 0; i < listUsers.length; i++) {

				AuthentificationEntity user = userRepo.findById(listUsers[i]).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));

				BatteryFavLibraryEntity batteryFav = new BatteryFavLibraryEntity(user, battery);
				batteryFav.setBattery(battery);
				batteryFav.setAuthentificationEntity(user);
				batteryFavRepo.save(batteryFav);
				historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + battery.getManufacturer() + " " + battery.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;Battery Library",
						true, numTab, sessionId, openDate);
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Battery to Users Favorites List;Battery Library", false, numTab, sessionId,
					openDate);
			return "error";
		}
	}

	public List<ComponentModel> checkBatteryExistent(ComponentModel newmodule, Long idUser) {
		List<ComponentModel> batteryList = new ArrayList<>();
		try {
			if (newmodule != null) {
				Boolean exist = batteryRepo.existsByModelAndManufacturerAndIsDeleted(newmodule.getModel(),
						newmodule.getManufacturer(), false);
				List<Long> batteryFavId = batteryFavRepo.findFavoriteBatteries(idUser);
				if (Boolean.TRUE.equals(exist)) {
					List<Battery> listBattery = batteryRepo.findByModelAndManufacturerAndIsDeleted(newmodule.getModel(),
							newmodule.getManufacturer(), false);
					for (int i = 0; i < listBattery.size(); i++) {
						batteryList.add(i, 
								new ComponentModel(listBattery.get(i).getId(), listBattery.get(i).getManufacturer(), listBattery.get(i).getModel(), batteryFavId.indexOf(listBattery.get(i).getId()) != -1));
					}
				} else if (Boolean.TRUE.equals(batteryRepo.existsByModelAndManufacturerNotAndIsDeleted(newmodule.getModel(),
						newmodule.getManufacturer(), false))) {
					List<Battery> listBattery = batteryRepo.findByModelAndManufacturerNotAndIsDeleted(
							newmodule.getModel(), newmodule.getManufacturer(), false);
					for (int i = 0; i < listBattery.size(); i++) {
						batteryList.add(i, 
								new ComponentModel(listBattery.get(i).getId(), listBattery.get(i).getManufacturer(), listBattery.get(i).getModel(), batteryFavId.indexOf(listBattery.get(i).getId()) != -1));
					}
				}
			}
			return batteryList;
		} catch (Exception e) {
			e.printStackTrace();
			return batteryList;
		}

	}

	/*
	 * Add New Battery
	 */
	public ComponentModel addNewBattery(Long idContractor, BatteryModel batteryReq, Long idUserCo) {

		BatteryEntityModel batteryModel = new BatteryEntityModel();

		try {
			Battery battery = new Battery();
			if (batteryReq != null) {
				battery.setManufacturer(batteryReq.getManufacturer());
				battery.setModel(batteryReq.getModel());
				battery.setOutputVolts(batteryReq.getOutputVolts());
				battery.setHours(batteryReq.getHours());
				battery.setType(batteryReq.getType());
				battery.setEssEnergy(batteryReq.getEssEnergy());
				battery.setNotes(batteryReq.getNotes());
				battery.setUpdated(Formatted_DATE.format(new Date()));
				
				AuthentificationEntity contractor = permitEntityRepo.findAuthentificationEntityByID(idContractor);
				AuthentificationEntity userCo = userRepo.findById(idUserCo).orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + idUserCo));

				battery.setHasSuperUserEdit((userCo.getRoleEntity().getId() == 1 || userCo.getRoleEntity().getId() == 3) );
				battery.setAddDate(new Date());
				BatteryFavLibraryEntity batteryFav = new BatteryFavLibraryEntity(contractor, battery);
				batteryFav.setAuthentificationEntity(contractor);
				batteryFav.setBattery(battery);
				battery.setAuthentificationEntity(userCo);
				battery.setIsVerified(false);
				batteryFavRepo.save(batteryFav);
				batteryRepo.save(battery);
				batteryModel.setBattery(battery);
				mailingService.mailUpdate("Battery",
						"A new equipment of Battery " + batteryReq.getManufacturer() + " " + batteryReq.getModel()
								+ " has been added by " + userCo.getFirstName() + " " + userCo.getLastName(),
								userCo.getEmail().contains("nuagetechnologies-tn.com") && !userCo.getEmail().contains("pm"));
				historicActivity.recordActivity(idUserCo, "", "", "libraries;Add component "
						+ batteryReq.getManufacturer() + " " + batteryReq.getModel() + ".;Battery Library", true, "",
						"", "");
				addBatteryNotification(idUserCo, battery.getManufacturer(), battery.getModel());
			}
			return new ComponentModel(battery.getId(), battery.getManufacturer(), battery.getModel());

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Add component.;Battery Library", false, "", "",
					"");
			return null;

		}

	}

	public List<ComponentModel> getListBatteriesModels() {
		try {
			return batteryRepo.getAllModels();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
