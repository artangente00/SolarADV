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
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.FlashingFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.FlashingAddResponse;
import com.PlayGroundAdv.Solar.model.FlashingFavRequest;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchFlashingResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class GetFlashingLibraryService {


	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final FlashingRepository flashingRepo;
	final AuthenticationRepository authentificationRepo;
	final FlashingsFavoritesRepository flashingsFavoritesRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo;
	final PermitRepository permitEntityRepo;
	final EquipmentUpdate mailingService;

	public GetFlashingLibraryService(HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			FlashingRepository flashingRepo, AuthenticationRepository authentificationRepo,
			FlashingsFavoritesRepository flashingsFavoritesRepo,
			PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo, PermitRepository permitEntityRepo,
			EquipmentUpdate mailingService) {
		super();
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.flashingRepo = flashingRepo;
		this.authentificationRepo = authentificationRepo;
		this.flashingsFavoritesRepo = flashingsFavoritesRepo;
		this.permitProjectSiteInfoEntityRepo = permitProjectSiteInfoEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.mailingService = mailingService;
	}

	public Page<SearchFlashingResult> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValueTypesService.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValueTypesService.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ")
					: null;
			if (manufacturer == null && model == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<Flashing> p = flashingRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());
			} else {
				Page<Flashing> p = flashingRepo.findAll(filter(manufacturer, model, request.getIsFavorite(),
						request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	private Page<SearchFlashingResult> convertDto(Page<Flashing> page, Pageable pageable, Long userId, Boolean isdelete) {
		try {
			List<Long> favorite = flashingsFavoritesRepo.findFavoriteFlashings(userId);
			return new PageImpl<>(
					page.stream().map(c -> new SearchFlashingResult(c.getId(), favorite.indexOf(c.getId()) != -1,
							c.getManufacturer(), c.getModel(), c.getMappedValue(), c.getWeight(), c.getUpdated(), c.isDeleted(),							
							new UsersEntityResult(c.getAuthentificationEntity().getId(), c.getAuthentificationEntity().getFirstName(), c.getAuthentificationEntity().getLastName()), 
							c.getHasCorrectionRequest(), c.getEroneousContent(), c.getEroneousContentOther(), c.getEroneousDescription(),c.getAuthentificationEntity().getFirstName()+" "+c.getAuthentificationEntity().getLastName(),
							c.getFirstUpdater() != null ? new UsersEntityResult(c.getFirstUpdater().getId() ,c.getFirstUpdater().getFirstName(),c.getFirstUpdater().getLastName()) : null,
							c.getSecondUpdater() != null ? new UsersEntityResult(c.getSecondUpdater().getId(),c.getSecondUpdater().getFirstName(),c.getSecondUpdater().getLastName()): null,
							c.getThirdUpdater() != null ? new UsersEntityResult(c.getThirdUpdater().getId(),c.getThirdUpdater().getFirstName(),c.getThirdUpdater().getLastName()): null,
							c.getVerifiedBy() != null ? new UsersEntityResult(c.getVerifiedBy().getId(),c.getVerifiedBy().getFirstName(),c.getVerifiedBy().getLastName()): null,
							c.getIsVerified(),c.getDateVerification())).collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	
	private Specification<Flashing> filter(String[] makes, String[] models, Boolean favorite, Boolean deleted,
			Long userId) {

		return new Specification<Flashing>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Flashing> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesMake = new ArrayList<>();
				List<Predicate> predicatesModel = new ArrayList<>();
				for (int i = 0; makes != null && i < makes.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("manufacturer")), "%" + makes[i].toLowerCase() + "%");
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
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : flashingsFavoritesRepo.findFavoriteFlashings(userId)) {
						inClause.value(id);
					}
					list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

//	send Correction Request
	public String sendCorrectionRequest(CorrectionRequest request) {

		try {
			Flashing c = flashingRepo.findById(request.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Flashing not found for this id :" + request.getId()));
			AuthentificationEntity user = authentificationRepo.findById(request.getIdUser()).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" + request.getIdUser()));
			c.setHasCorrectionRequest(true);
			c.setEroneousContent(request.getEroneousContent());
			c.setEroneousContentOther(request.getEroneousContentOther());
			c.setEroneousDescription(request.getEroneousDescription());
			flashingRepo.save(c);
			notificationEntityService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
					"Request Correction - " + c.getModel(), "The user " + user.getFirstName() + " " + user.getLastName()
							+ " request correction for the Flashing " + c.getManufacturer() + " " + c.getModel(),
					true);
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}
	/*
	 * Get flashingFav
	 */
	public List<Long> getFlashingFavorite(Long idContractor) {
		List<Long> flashingFavID = new ArrayList<>();
		try {
			List<FlashingFavLibraryEntity> flashingFav = flashingsFavoritesRepo
					.findAllByAuthentificationEntityId(idContractor);
			if (flashingFav != null && !flashingFav.isEmpty()) {
				for (int i = 0; i < flashingFav.size(); i++) {
					if (flashingFav.get(i) != null && flashingFav.get(i).getFlashing() != null) {
						flashingFavID.add(flashingFav.get(i).getFlashing().getId());
					}
				}
				return flashingFavID;
			} else
				return flashingFavID;

		} catch (Exception e) {
			e.printStackTrace();
			return flashingFavID;
		}

	}

	/*
	 * Add flashingFav
	 */
	public String addFlashingFavorite(Long idContractor, Long idFlashing, Long idOwner) {

		try {

			AuthentificationEntity contractor = new AuthentificationEntity();
			Flashing flashing = new Flashing();
			try {
				contractor = authentificationRepo.findById(idOwner).orElseThrow(
						() -> new ResourceNotFoundException("User not found for this id :" +idOwner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				flashing = flashingRepo.findById(idFlashing).orElseThrow(
						() -> new ResourceNotFoundException("Flashing not found for this id :" +idFlashing));
			} catch (Exception e) {
				e.printStackTrace();
			}
			FlashingFavLibraryEntity flashingFav = new FlashingFavLibraryEntity(contractor, flashing);
			flashingsFavoritesRepo.save(flashingFav);
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;The favorite " + flashing.getManufacturer() + " " + flashing.getModel()
							+ " is added to the user " + contractor.getFirstName() + " " + contractor.getLastName()
							+ ".;Flashing",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;Add Flashing to user Favorites List;Flashing Library", false, "", "", "");
			return "Fail";
		}
	}

	/*
	 * Delete flashingFav
	 */
	public String removeFlashingFavorite(Long idContractor, Long idFlashing, Long idOwner) {
		try {
			AuthentificationEntity contractor = authentificationRepo.findById(idOwner).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idOwner));
			FlashingFavLibraryEntity flashingFav = flashingsFavoritesRepo
					.findOneByAuthentificationEntityIdAndFlashingId(idOwner, idFlashing);
			flashingsFavoritesRepo.delete(flashingFav);
			List<PermitProjectSiteInfoEntity> permitArray1 = permitProjectSiteInfoEntityRepo
					.findByPermitEntityAuthentificationEntityIdAndFlashing(idOwner, idFlashing + "");
			for (int i = 0; !permitArray1.isEmpty() && i < permitArray1.size(); i++) {
				permitArray1.get(i).setFlashing("Fav Removed");
				permitProjectSiteInfoEntityRepo.save(permitArray1.get(i));
			}
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;The favorite " + flashingFav.getFlashing().getManufacturer() + " "
							+ flashingFav.getFlashing().getModel() + " is removed from the user "
							+ contractor.getFirstName() + " " + contractor.getLastName() + ".;Flashing",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;Add Flashing to Users Favorites List;Flashing", false, "", "", "");
			return "Fail";
		}

	}

	/*
	 * Get Contractor flashingFav
	 */
	public List<Flashing> getContractorFlashingFav(Long idContractor) {

		List<Flashing> flashingContractorFav = new ArrayList<>();

		List<Long> flashingFavID = getFlashingFavorite(idContractor);
		if (flashingFavID != null && !flashingFavID.isEmpty()) {
			for (int i = 0; i < flashingFavID.size(); i++) {
				Flashing flashing = new Flashing();
				try {
					flashing = flashingRepo.findById(flashingFavID.get(i)).orElseThrow(
							() -> new ResourceNotFoundException("Flashing not found for this id."));
				} catch (Exception e) {
					e.printStackTrace();
				}
				flashingContractorFav.add(flashing);
			}
		} else
			flashingContractorFav = null;

		return flashingContractorFav;
	}

	/*
	 * Get Contractor flashingFav
	 */
	public List<Flashing> getSuperUserFlashingFav(Long permitId) {
		List<Flashing> flashingContractorFav = new ArrayList<>();
		try {
			PermitEntity permit = permitEntityRepo.findById(permitId).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +permitId));
			Long idUser = permit.getAuthentificationEntity().getId();

			List<Long> flashingFavID = getFlashingFavorite(idUser);
			if (flashingFavID != null && !flashingFavID.isEmpty()) {
				for (int i = 0; i < flashingFavID.size(); i++) {
					Flashing flashing = new Flashing();
					try {
						flashing = flashingRepo.findById(flashingFavID.get(i)).orElseThrow(
								() -> new ResourceNotFoundException("Flashing not found for this id."));
					} catch (Exception e) {
						e.printStackTrace();
					}
					flashingContractorFav.add(flashing);
				}
			} else
				flashingContractorFav = null;

			return flashingContractorFav;
		} catch (Exception e) {
			e.printStackTrace();
			return flashingContractorFav;
		}
	}

	public boolean getTestFlashingFav(Long permitId, String testMan) {

		try {
			int test = 0;
			PermitEntity permit = permitEntityRepo.findById(permitId).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +permitId));
			Long idUser = permit.getAuthentificationEntity().getId();

			List<Flashing> flashingContractorFav = new ArrayList<>();

			List<Long> flashingFavID = getFlashingFavorite(idUser);

			if (flashingFavID != null) {
				for (int i = 0; i < flashingFavID.size(); i++) {
					Flashing flashing = new Flashing();
					try {
						flashing = flashingRepo.findById(flashingFavID.get(i)).orElseThrow(
								() -> new ResourceNotFoundException("Flashing not found for this id."));
					} catch (Exception e) {
						e.printStackTrace();
					}
					flashingContractorFav.add(flashing);
				}
			}
			for (int i = 0; i < flashingContractorFav.size(); i++) {
				if (flashingContractorFav.get(i) != null
						&& checkValueTypesService.Equals(testMan, flashingContractorFav.get(i).getManufacturer() + ":"
								+ flashingContractorFav.get(i).getModel())) {
					test = 1;
				}
			}

			return test !=1;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * edit Flashing
	 */
	public String editFlashing(FlashingFavRequest flashing, Long userID) {
		try {
			List<Flashing> listFlashing = flashingRepo.findAllByManufacturerAndModelAndIsDeletedAndIdNot(
					flashing.getManufacturer().trim().toLowerCase(), flashing.getModel().trim().toLowerCase(), false, flashing.getId());
			if (listFlashing != null && !listFlashing.isEmpty()) {
				return "exist";
			} else {
				return editFlashingFunction(flashing, userID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editFlashingFunction(FlashingFavRequest flashing, Long userID) {
		try {

			Flashing updatedFlashing = flashingRepo.findById(flashing.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Flashing not found for this id :" +flashing.getId()));
			
			AuthentificationEntity firstUpdater = authentificationRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));
			// manufacturer or model has change ---> update (manufacturer,model) in all the
			// project using the edited flashing
			List<PermitProjectSiteInfoEntity> permitProjectSiteInfoEntitys = permitProjectSiteInfoEntityRepo
					.findByFlashing(Long.toString(updatedFlashing.getId()));
			if (permitProjectSiteInfoEntitys != null && !permitProjectSiteInfoEntitys.isEmpty()) {
				for (PermitProjectSiteInfoEntity permitProjectSiteInfoEntity : permitProjectSiteInfoEntitys) {
					if (permitProjectSiteInfoEntity != null && permitProjectSiteInfoEntity.getFlashing() != null) {
						permitProjectSiteInfoEntity.setFlashing(Long.toString(flashing.getId()));
					}

				}
			}
			// end
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			updatedFlashing.setId(flashing.getId());
			updatedFlashing.setManufacturer(flashing.getManufacturer());
			updatedFlashing.setModel(flashing.getModel());

			if (checkValueTypesService.NotEquals(flashing.getMappedValue(), "")) {
				updatedFlashing.setMappedValue(flashing.getMappedValue());
			} else
				updatedFlashing.setMappedValue(flashing.getManufacturer() + " " + flashing.getMappedValue());

			updatedFlashing.setWeight(flashing.getWeight());
			updatedFlashing.setHasCorrectionRequest(flashing.getHasCorrectionRequest());
			updatedFlashing.setEroneousContent(flashing.getEroneousContent());
			updatedFlashing.setEroneousContentOther(flashing.getEroneousContentOther());
			updatedFlashing.setEroneousDescription(flashing.getEroneousDescription());
			updatedFlashing.setUpdated(dateFormat.format(new Date()));
			updatedFlashing.setIsVerified(false);
			String updateNum = "";
			if(updatedFlashing.getFirstUpdater() == null) {
				updateNum = "1st";	
				updatedFlashing.setFirstUpdater(firstUpdater);
			}else if(updatedFlashing.getSecondUpdater() == null) {
				updateNum = "2nd";				
				updatedFlashing.setSecondUpdater(firstUpdater);
			}else if(updatedFlashing.getThirdUpdater() == null) {
				updateNum = "3rd";				
				updatedFlashing.setThirdUpdater(firstUpdater);
			}
			flashingRepo.save(updatedFlashing);
			if (checkValueTypesService.isStringNotEmpty(updateNum)) {
				mailingService.mailUpdate("Flashing",
						"An existing equipment of Flashing " + flashing.getManufacturer() + " " + flashing.getModel()
								+ " has been updated " + updateNum + " time by " + firstUpdater.getFirstName() + " "
								+ firstUpdater.getLastName(),
								firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
			}
			historicActivity.recordActivity(userID, "", "",
					"libraries;Edit component " + flashing.getManufacturer() + " " + flashing.getModel() + ".;Flashing",
					true, "", "", "");

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "", "libraries;Edit component.;Flashing", false, "", "", "");
			return "fail";
		}
	}

	public String editFlashingNotification(Long userID, String flashingManufacturer, String flashingModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));
			notificationEntityService.addNewNotif(userID, 0L, "Flashing Update", "Libraries",
					"Flashing Update - " + flashingModel, "The flashing " + flashingModel + " " + flashingManufacturer
							+ " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addFlashingNotification(Long userID, String flashingManufacturer, String flashingModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));
			notificationEntityService.addNewNotif(userID, 0L, "New Flashing", "Libraries",
					"New Flashing- " + flashingModel, "The flashing " + flashingModel + " " + flashingManufacturer
							+ " was added by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
	// CR 924
	// Change Request Flashing List Management

	public String flashingLibraryActived(Long idFlashing, Long userID) {

		try {

			Flashing flashingLibrary = flashingRepo.findById(idFlashing).orElseThrow(
					() -> new ResourceNotFoundException("Flashing not found for this id :" +idFlashing));
			List<Flashing> listFlashing = flashingRepo.findAllByManufacturerAndModelAndIsDeleted(
					flashingLibrary.getManufacturer().trim().toLowerCase(), flashingLibrary.getModel().trim().toLowerCase(), false);
			if (listFlashing != null && !listFlashing.isEmpty()) {
				return "exist";
			} else {
				flashingLibrary.setDeleted(false);
				historicActivity.recordActivity(userID, "", "", "libraries;Activate component "
						+ flashingLibrary.getManufacturer() + " " + flashingLibrary.getModel() + ".;Flashing", true,
						"", "", "");
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "", "libraries;Activate component.;Flashing", false, "", "",
					"");
			return "false";
		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfFlashingDeleted(Long idFlashing) {
		try {
			return permitProjectSiteInfoEntityRepo
					.listOfNonDeletedAndNonSubmittedAndNonDeliveredAndNonTemplatePermitsUsingFlashing(
							idFlashing + "");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();

		}
	}

	public List<PermitEntity> getAllPermitOfFlashingDeleted1(Long idFlashing) {
		List<PermitEntity> resPermit = new ArrayList<>();

		try {
			Flashing flashingLibrary = flashingRepo.findById(idFlashing).orElseThrow(
					() -> new ResourceNotFoundException("Flashing not found for this id :" +idFlashing));

			/***************** Get all permit Array content this model *****************/
			List<PermitProjectSiteInfoEntity> allpermitProjectSiteInfo = permitProjectSiteInfoEntityRepo
					.findByFlashing(flashingLibrary.getId() + "");

			/************** Get all permit content this model ***************************/

			/****************** test on the state of Permit **************/
			if (flashingLibrary.getId() != 0) {
				if (allpermitProjectSiteInfo != null && !allpermitProjectSiteInfo.isEmpty()) {
					for (int i = 0; i < allpermitProjectSiteInfo.size(); i++) {
						if (allpermitProjectSiteInfo.get(i) != null
								&& allpermitProjectSiteInfo.get(i).getPermitEntity() != null) {
							if (checkValueTypesService
									.Equals(allpermitProjectSiteInfo.get(i).getPermitEntity().getStatus(), "Deleted")
									|| checkValueTypesService.Equals(
											allpermitProjectSiteInfo.get(i).getPermitEntity().getStatus(), "Submitted")
									|| checkValueTypesService.Equals(
											allpermitProjectSiteInfo.get(i).getPermitEntity().getStatus(), "Delivered")
									|| checkValueTypesService.Equals(
											allpermitProjectSiteInfo.get(i).getPermitEntity().getIsTemplate(), true)) {
							} else {
								resPermit.add(allpermitProjectSiteInfo.get(i).getPermitEntity());

							}
						}
					}
				}

			} else
				return new ArrayList<>();

			return resPermit;
		} catch (Exception e) {
			e.printStackTrace();
			return resPermit;

		}
	}

	public boolean deleteFlashingLibrary(Long id, Long userID) {

		try {

			Flashing flashingLibrary = flashingRepo.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Flashing not found for this id :" +id));
			if (id != 0) {
				List<PermitEntity> listPermitDeleted = getAllPermitOfFlashingDeleted1(id);
				PermitProjectSiteInfoEntity permitProjectSiteInfo = null;
				Flashing flashingLibraryRes = flashingRepo.findById(id).orElseThrow(
						() -> new ResourceNotFoundException("Flashing not found for this id :" +id));
				if (listPermitDeleted != null) {
					for (PermitEntity permitEntity : listPermitDeleted) {
						if (permitProjectSiteInfoEntityRepo.countByPermitEntityAndFlashing(permitEntity,
								flashingLibraryRes.getId() + "") != 0) {
							permitProjectSiteInfo = permitProjectSiteInfoEntityRepo
									.findOneByPermitEntityAndFlashing(permitEntity, flashingLibraryRes.getId() + "");
							permitProjectSiteInfo.setFlashing("Removed");
						}

					}
				}

				flashingLibrary.setDeleted(true);
				List<FlashingFavLibraryEntity> flashingFav = flashingsFavoritesRepo.findAllByFlashingId(id);
				if (flashingFav != null && !flashingFav.isEmpty()) {

					for (FlashingFavLibraryEntity FlashingFavLibraryEntity : flashingFav) {
						flashingsFavoritesRepo.delete(FlashingFavLibraryEntity);
					}
				}

				historicActivity.recordActivity(userID, "", "", "libraries;Delete component "
						+ flashingLibrary.getManufacturer() + " " + flashingLibrary.getModel() + ".;Flashing", true, "",
						"", "");

				return true;

			}
			historicActivity.recordActivity(userID, "", "", "libraries;Delete component "
					+ flashingLibrary.getManufacturer() + " " + flashingLibrary.getModel() + ".;Flashing", false, "",
					"", "");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "", "libraries;Delete component.;Flashing", false, "", "", "");
			return false;

		}
	}

	/*
	 * Edit Flashing Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long idFlashing, Long userID) {

		List<UsersEntityResult> usersList = new ArrayList<>();
		try {
			List<FlashingFavLibraryEntity> flashingFav = flashingsFavoritesRepo.findAllByFlashingId(idFlashing);
			if (flashingFav != null && !flashingFav.isEmpty()) {

				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < flashingFav.size(); i++) {
					if (flashingFav.get(i) != null && flashingFav.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(flashingFav.get(i).getAuthentificationEntity().getId());
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

	public String editUsersFavoriteList(Long idFlashing, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {
		try {

			Flashing flashing = flashingRepo.findById(idFlashing).orElseThrow(
					() -> new ResourceNotFoundException("Flashing not found for this id :" +idFlashing));
			for (int i = 0; i < listUsers.length; i++) {

				AuthentificationEntity user = authentificationRepo.findById(listUsers[i]).orElseThrow(
						() -> new ResourceNotFoundException("User not found for this id."));

				FlashingFavLibraryEntity flashingFav = new FlashingFavLibraryEntity(user, flashing);
				flashingFav.setFlashing(flashing);
				flashingFav.setAuthentificationEntity(user);
				flashingsFavoritesRepo.save(flashingFav);
				historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + flashing.getManufacturer() + " " + flashing.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;Flashing",
						true, numTab, sessionId, openDate);
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Flashing to Users Favorites List.;Flashing", false, numTab, sessionId, openDate);
			return "error";
		}
	}

	public List<FlashingFavRequest> checkFlashingExistent(FlashingFavRequest newmodule, Long idUser) {
		List<FlashingFavRequest> flashingList = new ArrayList<>();
		try {
			List<Flashing> listFlashing = flashingRepo.findAllByManufacturerAndModelAndIsDeleted(
					newmodule.getManufacturer().trim().toLowerCase(), newmodule.getModel().trim().toLowerCase(), false);
			List<Flashing> listFlashingNot = flashingRepo.findAllByManufacturerNotAndModelAndIsDeleted(
					newmodule.getManufacturer().trim().toLowerCase(), newmodule.getModel().trim().toLowerCase(), false);
			if (listFlashing != null && !listFlashing.isEmpty()) {
				List<Long> flashingFavID = getFlashingFavorite(idUser);

				for (int i = 0; i < listFlashing.size(); i++) {

					FlashingFavRequest flashing = new FlashingFavRequest();
					if (listFlashing.get(i) != null) {
						if (flashingFavID != null && flashingFavID.contains(listFlashing.get(i).getId())) {
							flashing.setIsFav("true");
						} else {
							flashing.setIsFav("false");
						}
						flashing.setId(listFlashing.get(i).getId());
						flashing.setManufacturer(listFlashing.get(i).getManufacturer());
						flashing.setModel(listFlashing.get(i).getModel());
					}
					flashingList.add(i, flashing);
				}
				return flashingList;

			} else if (listFlashingNot != null && !listFlashingNot.isEmpty()) {

				List<Long> flashingFavID = getFlashingFavorite(idUser);

				for (int i = 0; i < listFlashingNot.size(); i++) {

					FlashingFavRequest flashing = new FlashingFavRequest();
					if (listFlashingNot.get(i) != null) {
						if (flashingFavID != null && flashingFavID.contains(listFlashingNot.get(i).getId())) {
							flashing.setIsFav("true");
						} else {
							flashing.setIsFav("false");
						}
						flashing.setId(listFlashingNot.get(i).getId());
						flashing.setManufacturer(listFlashingNot.get(i).getManufacturer());
						flashing.setModel(listFlashingNot.get(i).getModel());
					}
					flashingList.add(i, flashing);
				}
				return flashingList;

			} else {
				return flashingList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return flashingList;
		}

	}

	/*
	 * Add New Flashing
	 */
	public FlashingAddResponse addNewFlashing(Long idPermitInfo, FlashingFavRequest flashingReq, Long idUserCo) {

		Flashing flashing = new Flashing();

		try {

			flashing.setManufacturer(flashingReq.getManufacturer());
			flashing.setModel(flashingReq.getModel());

			if (checkValueTypesService.NotEquals(flashingReq.getMappedValue(), "")) {
				flashing.setMappedValue(flashingReq.getMappedValue());
			} else
				flashing.setMappedValue(flashingReq.getManufacturer() + " " + flashingReq.getMappedValue());

			flashing.setWeight(flashingReq.getWeight());
			SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = formattedDATE.format(new Date());
			flashing.setUpdated(updatedDate);

			AuthentificationEntity contractor = permitEntityRepo.findAuthentificationEntityByID(idPermitInfo);

			AuthentificationEntity userCo = authentificationRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +idUserCo));
			flashing.setHasSuperUserEdit(userCo.getRoleEntity().getId() == 1 || userCo.getRoleEntity().getId() == 3);
			Date addDate = new Date();
			flashing.setAddDate(addDate);
			FlashingFavLibraryEntity flashingFav = new FlashingFavLibraryEntity(contractor, flashing);
			flashing.setAuthentificationEntity(userCo);
			flashing.setIsVerified(false);
			flashingsFavoritesRepo.save(flashingFav);
			flashingRepo.save(flashing);
			mailingService.mailUpdate("Flashing", "A new equipment of Flashing " + flashing.getManufacturer() + " "
					+ flashing.getModel() + " has been added by " + userCo.getFirstName() + " " + userCo.getLastName(),
					userCo.getEmail().contains("nuagetechnologies-tn.com") && !userCo.getEmail().contains("pm"));
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Add component " + flashing.getManufacturer() + " " + flashing.getModel() + ".;Flashing",
					true, "", "", "");
			return new FlashingAddResponse(flashing.getId(), flashing.getManufacturer(), flashing.getModel());

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Add component.;Flashing", true, "", "", "");
			return new FlashingAddResponse(flashing.getId(), flashing.getManufacturer(), flashing.getModel());

		}

	}
}
