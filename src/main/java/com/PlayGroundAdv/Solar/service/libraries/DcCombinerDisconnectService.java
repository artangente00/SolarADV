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

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.DcCombinerorDiscFavoriteEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.model.libraries.DcCombinerorDisconnectModel;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcComDiscoFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class DcCombinerDisconnectService {

	final NotificationEntityService notificationService;
	final HistoryActivityService activityService;
	final CheckValueTypesService checkValue;
	final DcComDiscoFavoriteRepository dcdFavRepo;
	final DcCombinerDiscoRepository dcdRepo;
	final AuthenticationRepository userRepo;
	final PermitProjectSiteInfoRepository bosRepo;
	final PermitRepository permitRepo;
	final CheckValueTypesService checkValueTypesService;
	final EquipmentUpdate mailingService;

	public DcCombinerDisconnectService(NotificationEntityService notificationService, HistoryActivityService activityService,
			CheckValueTypesService checkValue, DcComDiscoFavoriteRepository dcdFavRepo,
			DcCombinerDiscoRepository dcdRepo, AuthenticationRepository userRepo,
			PermitProjectSiteInfoRepository bosRepo, PermitRepository permitRepo,
			CheckValueTypesService checkValueTypesService, EquipmentUpdate mailingService) {
		super();
		this.notificationService = notificationService;
		this.activityService = activityService;
		this.checkValue = checkValue;
		this.dcdFavRepo = dcdFavRepo;
		this.dcdRepo = dcdRepo;
		this.userRepo = userRepo;
		this.bosRepo = bosRepo;
		this.permitRepo = permitRepo;
		this.checkValueTypesService = checkValueTypesService;
		this.mailingService = mailingService;
	}
	
	final static String NOT_FOUND = "Entity not found for this id: ";
	
	public Page<DcCombinerorDisconnectModel> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValue.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValue.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			String ratedCurrent = checkValue.isStringNotEmpty(request.getRatedCurrent())
					? request.getRatedCurrent().trim()
					: null;
			String deviceType = checkValue.isStringNotEmpty(request.getDeviceType()) ? request.getDeviceType().trim()
					: null;
			if (manufacturer == null && model == null && ratedCurrent == null && deviceType == null
					&& !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<DCCombinerDisconnectEntity> p = dcdRepo.findByIsDeletedAndTypeDcNot(request.getIsDeleted(),
						"J Box", pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());
			} else {
				Page<DCCombinerDisconnectEntity> p = dcdRepo.findAll(filter(manufacturer, model, ratedCurrent,
						deviceType, request.getIsFavorite(), request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<DcCombinerorDisconnectModel> convertDto(Page<DCCombinerDisconnectEntity> page, Pageable pageable,
			Long idUser, Boolean isdelete) {
		try {
			List<Long> favorites = dcdFavRepo.findFavoriteDCD(idUser);
			return new PageImpl<>(page.stream()
					.map(c -> new DcCombinerorDisconnectModel(c.getId(), c.getManufacturer(), c.getModel(), c.getOcpd(),
							c.getWeight(), c.getNemaRating(), c.getMaxInput(), c.getMaxContiOutputCurrent(),
							c.getMaxOutputCurrent(), c.getTypeDc(), isdelete, favorites.indexOf(c.getId()) != -1,
							c.getIdOwner().getFirstName() + " " + c.getIdOwner().getLastName(), c.getLastUpdate(),
							c.getDropdownOption(), c.getRsd(), c.getIdOwner().getId(), c.getEroneousContent(),
							c.getEroneousContentOther(), c.getEroneousDescription(), c.getHasCorrectionRequest(),
							c.getManufacturerMappingValue(), c.getModelMappingValue(), 
							c.getFirstUpdater() != null ? new UsersEntityResult(c.getFirstUpdater().getId(), c.getFirstUpdater().getFirstName(), c.getFirstUpdater().getLastName()) : null,
							c.getSecondUpdater() != null ? new UsersEntityResult(c.getSecondUpdater().getId(), c.getSecondUpdater().getFirstName(), c.getSecondUpdater().getLastName()) : null,
							c.getThirdUpdater() != null ? new UsersEntityResult(c.getThirdUpdater().getId(), c.getThirdUpdater().getFirstName(), c.getThirdUpdater().getLastName()) : null,
							c.getVerifiedBy() != null ? new UsersEntityResult(c.getVerifiedBy().getId(), c.getVerifiedBy().getFirstName(), c.getVerifiedBy().getLastName()) : null,
							c.getIsVerified(), c.getDateVerification()))
					.collect(Collectors.toList()), pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Specification<DCCombinerDisconnectEntity> filter(String[] makes, String[] models, String inputCurrent,
			String deviceType, Boolean favorite, Boolean deleted, Long idUser) {

		return new Specification<DCCombinerDisconnectEntity>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<DCCombinerDisconnectEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
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
				Predicate predicateDCD = cb.notEqual(root.get("typeDc"), "J Box");
				Predicate predicateRatedCurrent = inputCurrent != null ? cb.equal(root.get("maxInput"), inputCurrent)
						: null;
				Predicate predicateDeviceType = deviceType != null ? cb.equal(root.get("ocpd"), deviceType) : null;
				Predicate endPredicateMake = makes != null
						? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()]))
						: null;
				Predicate endPredicateModel = models != null
						? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateDeleted, predicateDCD, predicateRatedCurrent,
						predicateDeviceType, endPredicateMake, endPredicateModel);
				if (Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : dcdFavRepo.findFavoriteDCD(idUser)) {
						inClause.value(id);
					}
					list = Arrays.asList(predicateDeleted, predicateDCD, predicateRatedCurrent, predicateDeviceType,
							endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	/*
	 * Add New DC Combiner or disconnect
	 */
	private List<ComponentModel> convertListDto(List<DCCombinerDisconnectEntity> l, Long idUser) {
		try {
			List<Long> favorites = dcdFavRepo.findFavoriteDCD(idUser);
			return new ArrayList<>(l.stream().map(c -> new ComponentModel(c.getId(), c.getManufacturer(), c.getModel(),
					favorites.indexOf(c.getId()) != -1)).collect(Collectors.toList()));
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<ComponentModel> checkdcComDiscExistent(ComponentModel newdcCombOrDisc, Long idUser) {
		try {
			if (newdcCombOrDisc != null) {
				List<DCCombinerDisconnectEntity> l = dcdRepo.findByModelAndManufacturerAndIsDeletedAndTypeDcNot(
						newdcCombOrDisc.getModel().trim().toLowerCase(),
						newdcCombOrDisc.getManufacturer().trim().toLowerCase());

				if (l != null && !l.isEmpty()) {
					return convertListDto(l, idUser);
				} else {
					l = dcdRepo.findByModelAndManufacturerNotAndIsDeletedAndTypeDcNot(
							newdcCombOrDisc.getModel().trim().toLowerCase(),
							newdcCombOrDisc.getManufacturer().trim().toLowerCase());
					if (l != null && !l.isEmpty()) {
						return convertListDto(l, idUser);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	/*
	 * CR-528 get DC combiner or disconnects favorites
	 */
	public Long addDcCombOrDisc(DcCombinerorDisconnectModel dCCombinerDisconnectEntity, Long idUser, String ipAdress,
			String timeZone, Long idPermit, String numTab, String sessionId, String openDate) {

		try {
			AuthentificationEntity user = userRepo.findById(idUser)
					.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + idUser));
			AuthentificationEntity contractor = permitRepo.findAuthentificationEntityByID(idPermit);

			if (contractor != null && user != null) {
				DCCombinerDisconnectEntity dCCombinerDisconnect = new DCCombinerDisconnectEntity();
				DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				dCCombinerDisconnect.setManufacturer(dCCombinerDisconnectEntity.getManufacturer());
				dCCombinerDisconnect.setModel(dCCombinerDisconnectEntity.getModel());

				if (checkValue.NotEquals(dCCombinerDisconnectEntity.getManufacturerMappingValue(), "")) {
					dCCombinerDisconnect
							.setManufacturerMappingValue(dCCombinerDisconnectEntity.getManufacturerMappingValue());
				} else
					dCCombinerDisconnect.setManufacturerMappingValue(dCCombinerDisconnectEntity.getManufacturer());

				if (checkValue.NotEquals(dCCombinerDisconnectEntity.getModelMappingValue(), "")) {
					dCCombinerDisconnect.setModelMappingValue(dCCombinerDisconnectEntity.getModelMappingValue());
				} else
					dCCombinerDisconnect.setModelMappingValue(dCCombinerDisconnectEntity.getModel());

				dCCombinerDisconnect.setDropdownOption(dCCombinerDisconnectEntity.getDropdownOption());
				dCCombinerDisconnect.setTypeDc(dCCombinerDisconnectEntity.getTypeDc());
				dCCombinerDisconnect.setRsd(dCCombinerDisconnectEntity.getRsd());
				dCCombinerDisconnect.setIdOwner(user);
				dCCombinerDisconnect.setIsDeleted(false);
				dCCombinerDisconnect.setIsVerified(false);
				dCCombinerDisconnect.setLastUpdate(dateFormat.format(new Date()));
				dCCombinerDisconnect
						.setHasSuperUserEdit(user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3);

				dCCombinerDisconnect.setAddDate(new Date());
				dcdRepo.save(dCCombinerDisconnect);

				DcCombinerorDiscFavoriteEntity favorites = new DcCombinerorDiscFavoriteEntity();
				favorites.setDcCombinerDisconnectEntity(dCCombinerDisconnect);
				favorites.setAuthentificationEntity(contractor);
				dcdFavRepo.save(favorites);
				mailingService.mailUpdate("DC Combner or Disconnect",
						"A new equipment of DC Combner or Disconnect " + dCCombinerDisconnect.getManufacturer() + " "
								+ dCCombinerDisconnect.getModel() + " has been added by " + user.getFirstName() + " "
								+ user.getLastName(),
								user.getEmail().contains("nuagetechnologies-tn.com") && !user.getEmail().contains("pm"));
				activityService.recordActivity(idUser, ipAdress, timeZone,
						"Add new DC Combiner or Disconnect " + dCCombinerDisconnect.getManufacturer() + " "
								+ dCCombinerDisconnect.getModel() + ";DC Combner or Disconnect Id "
								+ dCCombinerDisconnect.getId() + " Add Success ",
						true, numTab, sessionId, openDate);
				return dCCombinerDisconnect.getId();
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUser, ipAdress, timeZone,
					"Add new Rail Racking ; error technical problem; Add failed ", false, numTab, sessionId, openDate);
			return null;

		}

	}


	public String updateDcCombiOrDiscStatus(Boolean isFav, Long id, Long idUser, String ipAdress, String timeZone,
			Long idOwner, String sessionId) {
		try {
			DCCombinerDisconnectEntity dcCombOrDisco = dcdRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
			AuthentificationEntity user = userRepo.findById(idOwner)
					.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + idOwner));
			if (user != null && dcCombOrDisco != null) {
				if (checkValue.Equals(isFav, true)) {
					DcCombinerorDiscFavoriteEntity dcCombinerorDiscFavoriteEntity = new DcCombinerorDiscFavoriteEntity(
							user, dcCombOrDisco);
					dcdFavRepo.save(dcCombinerorDiscFavoriteEntity);

					activityService.recordActivity(idUser, ipAdress, timeZone,
							"libraries;The favorite " + dcCombOrDisco.getManufacturer() + " " + dcCombOrDisco.getModel()
									+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
									+ ".;DC Combiner or Disconnects",
							true, null, sessionId, null);

				} else if (checkValue.Equals(isFav, false)) {
					dcdFavRepo.deleteByDcCombinerDisconnectEntityIdAndAuthentificationEntityId(id, idOwner);

					activityService.recordActivity(idUser, ipAdress, timeZone,
							"libraries;The favorite " + dcCombOrDisco.getManufacturer() + " " + dcCombOrDisco.getModel()
									+ " is removed from the user " + user.getFirstName() + " " + user.getLastName()
									+ ".;DC Combiner or Disconnects",
							true, null, sessionId, null);

				}
			}

			return "Done";
		} catch (Exception e) {
			activityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Update DC Combiner or Disconnect Users Favorites List;DC Combiner or Disconnects", false,
					null, sessionId, null);
			e.printStackTrace();
			return "fail";

		}

	}

	/*
	 * edit DC combiner or disconnect
	 */
	public String editDCcombiOrDisc(DcCombinerorDisconnectModel dcComDisc, String ipAdress, String timeZone, Long idUser,
			String sessionId) {

		try {

			DCCombinerDisconnectEntity editdcComDisc = dcdRepo.findById(dcComDisc.getId()).orElseThrow(
					() -> new ResourceNotFoundException(NOT_FOUND + dcComDisc.getId()));
			
			AuthentificationEntity firstUpdater = userRepo.findById(idUser).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUser));

			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			if (Boolean.TRUE.equals(dcdRepo.existsByManufacturerAndModelAndIdNotAndTypeDcNotAndIsDeleted(
					dcComDisc.getManufacturer(), dcComDisc.getModel(), dcComDisc.getId(), "J Box", false))) {
				return "exist";
			} else if (editdcComDisc != null) {

				editdcComDisc.setManufacturer(dcComDisc.getManufacturer());
				editdcComDisc.setModel(dcComDisc.getModel());

				if (checkValue.NotEquals(dcComDisc.getManufacturerMappingValue(), "")) {
					editdcComDisc.setManufacturerMappingValue(dcComDisc.getManufacturerMappingValue());
				} else
					editdcComDisc.setManufacturerMappingValue(dcComDisc.getManufacturer());

				if (checkValue.NotEquals(dcComDisc.getModelMappingValue(), "")) {
					editdcComDisc.setModelMappingValue(dcComDisc.getModelMappingValue());
				} else
					editdcComDisc.setModelMappingValue(dcComDisc.getModel());

				editdcComDisc.setOcpd(dcComDisc.getOcpd());
				editdcComDisc.setMaxInput(dcComDisc.getMaxInput());
				editdcComDisc.setMaxContiOutputCurrent(dcComDisc.getMaxContiOutputCurrent());
				editdcComDisc.setLastUpdate(dateFormat.format(new Date()));
				editdcComDisc.setMaxOutputCurrent(dcComDisc.getMaxOutputCurrent());
				editdcComDisc.setNemaRating(dcComDisc.getNemaRating());
				editdcComDisc.setWeight(dcComDisc.getWeight());
				editdcComDisc.setDropdownOption(dcComDisc.getDropdownOption());
				editdcComDisc.setTypeDc(dcComDisc.getTypeDc());
				editdcComDisc.setIsVerified(false);
				String updateNum = "";
				if(editdcComDisc.getFirstUpdater() == null) {
					updateNum = "1st";	
					editdcComDisc.setFirstUpdater(firstUpdater);
				}else if(editdcComDisc.getSecondUpdater() == null) {
					updateNum = "2nd";				
					editdcComDisc.setSecondUpdater(firstUpdater);
				}else if(editdcComDisc.getThirdUpdater() == null) {
					updateNum = "3rd";				
					editdcComDisc.setThirdUpdater(firstUpdater);
				}	

				dcdRepo.save(editdcComDisc);

				activityService.recordActivity(
						idUser, ipAdress, timeZone, "libraries;Edit component " + editdcComDisc.getManufacturer() + " "
								+ editdcComDisc.getModel() + ".;DC Combiner or Disconnects",
						true, null, sessionId, null);
				
				if (checkValueTypesService.isStringNotEmpty(updateNum)) {
					mailingService.mailUpdate("DC Combiner or Disconnects",
							"An existing equipment of DC Combiner or Disconnects " + editdcComDisc.getManufacturer()
									+ " " + editdcComDisc.getModel() + " has been updated " + updateNum + " time by "
									+ firstUpdater.getFirstName() + " " + firstUpdater.getLastName(),
									firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
				}

				return "success";
			}
			return "error";
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Edit DC Combiner or Disconnects.;DC Combiner or Disconnects", false, null, sessionId,
					null);
			return "error";
		}
	}

	/*
	 * delete DC combiner or disconnect
	 */
	public List<ProjectForLibrariesModel> getRemovedcComDisconnectConfirmation(Long id) {
		try {
			return dcdRepo.getProjectForLibrariesModel(id, false);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String deletedcCombOrDisco(Long dcCombDisco, String ipAdress, String timeZone, Long idUser, String numTab,
			String sessionId, String openDate) {
		try {

			DCCombinerDisconnectEntity dcCombDiconnect = dcdRepo.findById(dcCombDisco)
					.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + dcCombDisco));
			List<DcCombinerorDiscFavoriteEntity> dcComDiscFav = dcdFavRepo
					.findByDcCombinerDisconnectEntityId(dcCombDisco);
			if (dcComDiscFav != null && !dcComDiscFav.isEmpty()) {
				for (int i = 0; i < dcComDiscFav.size(); i++) {
					dcdFavRepo.delete(dcComDiscFav.get(i));
				}
			}

			/*
			 * Remove Rail & Racking From Rail Racking Library
			 */
			if (dcCombDiconnect != null) {
				dcCombDiconnect.setIsDeleted(true);
				dcdRepo.save(dcCombDiconnect);
				activityService.recordActivity(idUser, ipAdress, timeZone,
						"libraries;Delete component " + dcCombDiconnect.getManufacturer() + " "
								+ dcCombDiconnect.getModel() + ".;DC Combiner or Disconnects",
						true, numTab, sessionId, openDate);
			}

			return "Done";

		} catch (Exception e) {
			activityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Delete DC Combiner or Disconnects.;DC Combiner or Disconnects", false, numTab, sessionId,
					openDate);
			e.printStackTrace();
			return "error";
		}
	}

	/*
	 * Edit DC Combiner Disconnect Favorite for Other Users
	 */
	public List<UsersEntityResult> getUsersForFavList(Long dcCombDiscoID, Long userID) {
		try {
			List<DcCombinerorDiscFavoriteEntity> dcComDiscFav = dcdFavRepo
					.findByDcCombinerDisconnectEntityId(dcCombDiscoID);

			if (dcComDiscFav != null && !dcComDiscFav.isEmpty()) {
				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < dcComDiscFav.size(); i++) {
					usersFavID.add(dcComDiscFav.get(i).getAuthentificationEntity().getId());
				}
				return dcdRepo.getUsersEntityResultIdIN(usersFavID, false, true, userID);
			} else {
				return dcdRepo.getUsersEntityResult(false, true, userID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public String editUsersFavoriteList(Long dcCombDiscoID, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {
		try {

			DCCombinerDisconnectEntity dcCombinerDisconnect = dcdRepo.findById(dcCombDiscoID)
					.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + dcCombDiscoID));
			for (int i = 0; i < listUsers.length; i++) {
				AuthentificationEntity user = userRepo.findById(listUsers[i])
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
				if (user != null && dcCombinerDisconnect != null) {
					DcCombinerorDiscFavoriteEntity dcComDiscFav = new DcCombinerorDiscFavoriteEntity(user,
							dcCombinerDisconnect);
					dcdFavRepo.save(dcComDiscFav);
					activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
							"libraries;The favorite " + dcCombinerDisconnect.getManufacturer() + " "
									+ dcCombinerDisconnect.getModel() + " is added to the user " + user.getFirstName()
									+ " " + user.getLastName() + ".;DC Combiner or Disconnects",
							true, numTab, sessionId, openDate);
				}

			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add DC Combiner or Disconnect to Users Favorites List;DC Combiner or Disconnects", false,
					numTab, sessionId, openDate);
			return "error";
		}
	}

	/*
	 * Get All Deleted Dc combiner or disconnects
	 */

	/*
	 * Activate Dc combiner or disconnect
	 */
	public String activatedcComOrDisc(Long dcCombinerDiscoID, String ipAdress, String timeZone, Long idUser,
			String numTab, String sessionId, String openDate) {

		try {
			List<String> listTypeDc = Arrays.asList("DC Combiner", "DC Disconnect", "Rapid Shutdown",
					"DC Combining Disconnect", "AC/DC Combiner");

			DCCombinerDisconnectEntity dCCombinerDisconnectEntity = dcdRepo.findById(dcCombinerDiscoID).orElseThrow(
					() -> new ResourceNotFoundException(NOT_FOUND + dcCombinerDiscoID));
			if (dCCombinerDisconnectEntity != null) {
				if (Boolean.TRUE.equals(dcdRepo.existsByManufacturerAndModelAndIsDeletedAndTypeDcIn(
						dCCombinerDisconnectEntity.getManufacturer(), dCCombinerDisconnectEntity.getModel(), false,
						listTypeDc))) {
					return "exist";
				} else {
					dCCombinerDisconnectEntity.setIsDeleted(false);
					dcdRepo.save(dCCombinerDisconnectEntity);

					activityService.recordActivity(idUser, ipAdress, timeZone,
							"libraries;Activate component " + dCCombinerDisconnectEntity.getManufacturer() + " "
									+ dCCombinerDisconnectEntity.getModel() + ".;DC Combiner or Disconnects",
							true, numTab, sessionId, openDate);
					return "Success";
				}
			}
			return "Fail";
		} catch (Exception e) {
			activityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Activate component.;DC Combiner or Disconnects", false, numTab, sessionId, openDate);
			e.printStackTrace();
			return "Fail";
		}
	}

	public String editDCCombinerNotification(Long userID, String acComOrDisMake, String acComOrDisModel) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + userID));
			if (userCo != null) {
				notificationService
						.addNewNotif(userID, 0L, "DC Combiner Disconnect Update", "Libraries",
								"DC Combiner Disconnect Update - " + acComOrDisModel,
								"The DC Combiner Disconnect " + acComOrDisModel + " " + acComOrDisMake
										+ " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
								true);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addDCCombinerNotification(Long userID, String acComOrDisMake, String acComOrDisModel) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + userID));
			if (userCo != null) {
				notificationService.addNewNotif(userID, 0L, "New DC Combiner Disconnect", "Libraries",
						"New DC Combiner Disconnect- " + acComOrDisModel,
						"The DC Combiner Disconnect " + acComOrDisModel + " " + acComOrDisMake + " was added by "
								+ userCo.getFirstName() + " " + userCo.getLastName(),
						true);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String sendCorrectionRequest(CorrectionRequest request) {

		try {
			if (request.getId() != 0) {
				AuthentificationEntity user = userRepo.findById(request.getIdUser()).orElseThrow(
						() -> new ResourceNotFoundException(NOT_FOUND + request.getIdUser()));
				DCCombinerDisconnectEntity library = dcdRepo.findById(request.getId()).orElseThrow(
						() -> new ResourceNotFoundException(NOT_FOUND + request.getId()));
				if (user != null && library != null) {
					library.setHasCorrectionRequest(true);
					library.setEroneousContent(request.getEroneousContent());
					library.setEroneousContentOther(request.getEroneousContentOther());
					library.setEroneousDescription(request.getEroneousDescription());
					dcdRepo.save(library);
					notificationService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
							"Request Correction - " + library.getModel(),
							"The user " + user.getFirstName() + " " + user.getLastName()
									+ " request correction for the " + library.getTypeDc() + " "
									+ library.getManufacturer() + " " + library.getModel(),
							true);

				}

			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

}
