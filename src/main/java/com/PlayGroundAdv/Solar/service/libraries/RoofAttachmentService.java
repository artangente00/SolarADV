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
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.NewRoofAttachmentModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.model.libraries.FavoriteListDto;
import com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentModel;
import com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentResult;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class RoofAttachmentService {

	final HistoryActivityService activityService;
	final NotificationEntityService notificationService;
	final CheckValueTypesService checkValue;
	final RoofAttachmentFavRepository roofAttachmentFavRepo;
	final RoofAttachmentsRepository roofAttachmentRepo;
	final AuthenticationRepository userRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;
	final PermitRepository permitRepo;
	final EquipmentUpdate mailingService;
	final CheckValueTypesService checkValueTypesService;

	public RoofAttachmentService(HistoryActivityService activityService, NotificationEntityService notificationService,
			CheckValueTypesService checkValue, RoofAttachmentFavRepository roofAttachmentFavRepo,
			RoofAttachmentsRepository roofAttachmentRepo, AuthenticationRepository userRepo,
			PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo,
			RoofMaterialTypeRepository roofMaterialTypeRepo, PermitRepository permitRepo,
			EquipmentUpdate mailingService, CheckValueTypesService checkValueTypesService) {
		super();
		this.activityService = activityService;
		this.notificationService = notificationService;
		this.checkValue = checkValue;
		this.roofAttachmentFavRepo = roofAttachmentFavRepo;
		this.roofAttachmentRepo = roofAttachmentRepo;
		this.userRepo = userRepo;
		this.permitProjectSiteInfoEntityRepo = permitProjectSiteInfoEntityRepo;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
		this.permitRepo = permitRepo;
		this.mailingService = mailingService;
		this.checkValueTypesService = checkValueTypesService ;
	}

	public Page<RoofAttachmentResult> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValue.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValue.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			String roofType = checkValue.isStringNotEmpty(request.getRoofType()) ? request.getRoofType().trim() : null;
			String integrated = checkValue.isStringNotEmpty(request.getIntegrated()) ? request.getIntegrated().trim()
					: null;
			if (manufacturer == null && model == null && roofType == null && integrated == null
					&& !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<RoofAttachmentsEntity> p = roofAttachmentRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser());
			} else {
				Page<RoofAttachmentsEntity> p = roofAttachmentRepo.findAll(filter(manufacturer, model, roofType,
						integrated, request.getIsFavorite(), request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<RoofAttachmentResult> convertDto(Page<RoofAttachmentsEntity> page, Pageable pageable,
			Long idUser) {
		try {
			List<Long> favorites = getRoofAttachmentFavorite(idUser);
			return new PageImpl<>(
					page.stream().map(c -> new RoofAttachmentResult(c.getId(), c.getManufacturer(), c.getModel(),
							c.getWeight(), c.getIntegrated(), c.getNumberOfRoof(), c.getUtilizeS100(), c.getIsDeleted(),
							c.getLastUpdate(), c.getHasCorrectionRequest(), favorites.indexOf(c.getId()) != -1,
							c.getEroneousContent(), c.getEroneousContentOther(), c.getEroneousDescription(),
							c.getAllowedRoof(), c.getManufacturerMappingValue(), c.getModelMappingValue(),
							c.getIdOwner() != null ? new UsersEntityResult(c.getIdOwner().getId(), c.getIdOwner().getFirstName(),c.getIdOwner().getLastName()) : null,
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

	private Specification<RoofAttachmentsEntity> filter(String[] makes, String[] models, String roofType,
			String integrated, Boolean favorite, Boolean deleted, Long idUser) {

		return new Specification<RoofAttachmentsEntity>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<RoofAttachmentsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesMake = new ArrayList<>();
				List<Predicate> predicatesModel = new ArrayList<>();
				List<Predicate> predicatesAllowedRoof = new ArrayList<>();
				for (int i = 0; makes != null && i < makes.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("manufacturer")),
							"%" + makes[i].toLowerCase() + "%");
					predicatesMake.add(predicate);
				}
				for (int i = 0; models != null && i < models.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("model")), "%" + models[i].toLowerCase() + "%");
					predicatesModel.add(predicate);
				}
				if (roofType != null) {
					predicatesAllowedRoof.add(cb.like(root.get("allowedRoof"), roofType + ":%"));
					predicatesAllowedRoof.add(cb.like(root.get("allowedRoof"), "%:" + roofType + ":%"));
					predicatesAllowedRoof.add(cb.like(root.get("allowedRoof"), "%:" + roofType));
				}

				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate predicateIntegrated = integrated != null ? cb.equal(root.get("integrated"), integrated)
						: null;
				Predicate endPredicateMake = makes != null
						? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()]))
						: null;
				Predicate endPredicateModel = models != null
						? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()]))
						: null;
				Predicate predicateRoofType = roofType != null
						? cb.or(predicatesAllowedRoof.toArray(new Predicate[predicatesAllowedRoof.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateDeleted, predicateRoofType, predicateIntegrated,
						endPredicateMake, endPredicateModel);
				if (Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : getRoofAttachmentFavorite(idUser)) {
						inClause.value(id);
					}
					list = Arrays.asList(predicateDeleted, predicateRoofType, predicateIntegrated, endPredicateMake,
							endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	public String editRoofAttachmentFavoriteList(Long roofAttachmentID, Boolean isShown, String ipUser,
			String timeZoneUser, Long idUserCo) {
		try {
			AuthentificationEntity user = userRepo.findById(idUserCo)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			if (user != null && checkValue.Equals(isShown, true)) {

				RoofAttachmentsEntity roofAttachment = roofAttachmentRepo.findById(roofAttachmentID)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
				if (roofAttachment != null) {
					RoofAttachmentFavLibraryEntity roofAttachmentFav = new RoofAttachmentFavLibraryEntity();
					roofAttachmentFav.setRoofAttachment(roofAttachment);
					roofAttachmentFav.setAuthentificationEntity(user);
					roofAttachmentFavRepo.save(roofAttachmentFav);
					activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
							"libraries;The favorite " + roofAttachment.getManufacturer() + " "
									+ roofAttachment.getModel() + " is added to the user " + user.getFirstName() + " "
									+ user.getLastName() + ".;Roof Attachments",
							true, "", "", "");
				}

			} else if (user != null) {
				RoofAttachmentFavLibraryEntity roofAttachmentFav = roofAttachmentFavRepo
						.findByRoofAttachmentIdAndAuthentificationEntityId(roofAttachmentID, idUserCo);
				if (roofAttachmentFav != null) {
					roofAttachmentFavRepo.delete(roofAttachmentFav);
					List<PermitProjectSiteInfoEntity> permitArray1 = permitProjectSiteInfoEntityRepo
							.findByPermitEntityAuthentificationEntityIdAndRailConnectionModel(idUserCo,
									roofAttachmentID + "");
					for (int i = 0; permitArray1 != null && !permitArray1.isEmpty() && i < permitArray1.size(); i++) {
						if (permitArray1.get(i) != null) {
							permitArray1.get(i).setRailConnectionModel("Fav Removed");
							permitProjectSiteInfoEntityRepo.save(permitArray1.get(i));
						}
					}
					activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
							"libraries;The favorite " + roofAttachmentFav.getRoofAttachment().getManufacturer() + " "
									+ roofAttachmentFav.getRoofAttachment().getModel() + " is removed from the user "
									+ user.getFirstName() + " " + user.getLastName() + ".;Roof Attachments",
							true, "", "", "");
				}

			}
			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Update Roof Attachment Users Favorites List.;Roof Attachments", false, "", "", "");
			return "error";
		}

	}

	/*
	 * Edit other user Roof Attachments Favorite List
	 */
	public String editOtherUserFavorite(Long[] ids, Boolean isShown, String ipUser, String timeZoneUser,
			Long idUserCo) {
		try {

			AuthentificationEntity user = userRepo.findById(ids[1])
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			if (user != null) {
				if (checkValue.Equals(isShown, true)) {

					RoofAttachmentsEntity ra = roofAttachmentRepo.findById(ids[0])
							.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
					if (ra != null) {
						RoofAttachmentFavLibraryEntity roofAttachmentFav = new RoofAttachmentFavLibraryEntity();
						roofAttachmentFav.setRoofAttachment(ra);
						roofAttachmentFav.setAuthentificationEntity(user);
						roofAttachmentFavRepo.save(roofAttachmentFav);

						activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
								"libraries;The favorite " + ra.getManufacturer() + " " + ra.getModel()
										+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
										+ ".;Roof Attachments",
								true, "", "", "");
					}

				} else {
					RoofAttachmentFavLibraryEntity roofAttachmentFav = roofAttachmentFavRepo
							.findByRoofAttachmentIdAndAuthentificationEntityId(ids[0], ids[1]);
					if (roofAttachmentFav != null) {
						roofAttachmentFavRepo.delete(roofAttachmentFav);
						activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
								"libraries;The favorite " + roofAttachmentFav.getRoofAttachment().getManufacturer()
										+ " " + roofAttachmentFav.getRoofAttachment().getModel()
										+ " is removed from the user " + user.getFirstName() + " " + user.getLastName()
										+ ".;Roof Attachments",
								true, "", "", "");
					}

				}
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Update Roof Attachment Users Favorites List.;Roof Attachments", false, "", "", "");
			return "error";
		}

	}

	/*
	 * Add New Roof Attachments
	 */
	public List<ComponentModel> checkExistent(NewRoofAttachmentModel raModel, Long idUser) {

		List<ComponentModel> roofAttachmentList = new ArrayList<>();
		try {
			List<RoofAttachmentsEntity> listRoofAttachment = roofAttachmentRepo.findByManufacturerAndModelAndIsDeleted(
					raModel.getManufacturer().trim().toLowerCase(), raModel.getModel().trim().toLowerCase(), false);
			List<RoofAttachmentsEntity> listRoofAttachmentNot = roofAttachmentRepo
					.findByManufacturerNotAndModelAndIsDeleted(raModel.getManufacturer().trim().toLowerCase(),
							raModel.getModel().trim().toLowerCase(), false);

			if (listRoofAttachment != null && !listRoofAttachment.isEmpty()) {

				List<Long> roofAttachmentFavID = getRoofAttachmentFavorite(idUser);

				for (int i = 0; i < listRoofAttachment.size(); i++) {

					ComponentModel ra = new ComponentModel(listRoofAttachment.get(i).getId(),
							listRoofAttachment.get(i).getManufacturer(), listRoofAttachment.get(i).getModel(),
							roofAttachmentFavID.contains(listRoofAttachment.get(i).getId()));
					roofAttachmentList.add(i, ra);

				}

			} else if (listRoofAttachmentNot != null && !listRoofAttachmentNot.isEmpty()) {

				List<Long> roofAttachmentFavID = getRoofAttachmentFavorite(idUser);

				for (int i = 0; i < listRoofAttachmentNot.size(); i++) {

					ComponentModel ra = new ComponentModel(listRoofAttachmentNot.get(i).getId(),
							listRoofAttachmentNot.get(i).getManufacturer(), listRoofAttachmentNot.get(i).getModel(),
							roofAttachmentFavID.contains(listRoofAttachmentNot.get(i).getId()));
					roofAttachmentList.add(i, ra);

				}
			}

			return roofAttachmentList;
		} catch (Exception e) {
			e.printStackTrace();
			return roofAttachmentList;
		}

	}

	public FavoriteListDto addRoofAttachment(NewRoofAttachmentModel raModel, String ipUser, String timeZoneUser,
			Long idUserCo, Long idPermit) {
		RoofAttachmentsEntity ra = new RoofAttachmentsEntity();
		try {

			AuthentificationEntity user = userRepo.findById(idUserCo)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			AuthentificationEntity contractor = permitRepo.findAuthentificationEntityByID(idPermit);
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			ra.setManufacturer(raModel.getManufacturer());
			ra.setModel(raModel.getModel());

			if (checkValue.isStringNotEmpty(raModel.getManufacturerMappingValue())) {
				ra.setManufacturerMappingValue(raModel.getManufacturerMappingValue());
			} else
				ra.setManufacturerMappingValue(raModel.getManufacturer());

			if (checkValue.isStringNotEmpty(raModel.getModelMappingValue())) {
				ra.setModelMappingValue(raModel.getModelMappingValue());
			} else
				ra.setModelMappingValue(raModel.getModel());

			ra.setIntegrated(raModel.getIntegratedFlashing());
			ra.setAllowedRoof(raModel.getAllowedRoof());
			ra.setIdOwner(user);
			ra.setIsDeleted(false);
			ra.setIsVerified(false);
			ra.setLastUpdate(dateFormat.format(new Date()));
			ra.setHasSuperUserEdit(user != null && (user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3));
			Date addDate = new Date();
			ra.setAddDate(addDate);
			roofAttachmentRepo.save(ra);
			RoofAttachmentFavLibraryEntity roofAttachemenFav = new RoofAttachmentFavLibraryEntity();
			roofAttachemenFav.setRoofAttachment(ra);
			roofAttachemenFav.setAuthentificationEntity(contractor);
			roofAttachmentFavRepo.save(roofAttachemenFav);
			addRoofAttachmentNotification(idUserCo, ra.getManufacturer(), ra.getModel());
			mailingService.mailUpdate("Roof Attachments", "A new equipment of Roof Attachments " + ra.getManufacturer()
					+ " " + ra.getModel() + " has been added by " + user.getFirstName() + " " + user.getLastName(),
					user.getEmail().contains("nuagetechnologies-tn.com") && !user.getEmail().contains("pm"));
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add component " + ra.getManufacturer() + " " + ra.getModel() + ".;Roof Attachments",
					true, "", "", "");
			return new FavoriteListDto(ra.getId(), ra.getManufacturer(), ra.getModel(), ra.getIntegrated(),
					getAllawedRoof(ra.getAllowedRoof()));
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Add component.;Roof Attachments",
					false, "", "", "");
			return null;
		}
	}
	
	public List<String> getRailConnectionInfo(Long idRail) {

		try {
			String r = roofAttachmentRepo.findAllowedRoof(idRail);
			return checkValue.isStringNotEmpty(r) ? Arrays.asList(r.split(":::")) : new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	private List<Long> getAllawedRoof(String allowedRoof) {
		try {
			List<Long> l = new ArrayList<>();
			if (checkValue.isStringNotEmpty(allowedRoof)) {
				String[] s = allowedRoof.split(":::");
				for (String r : s) {
					l.add(Long.valueOf(r));
				}
			}
			return l;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	/*
	 * Edit Roof Attachment
	 */

	public String editRoofAttachment(RoofAttachmentModel raModel, String ipUser, String timeZoneUser, Long idUserCo) {

		try {
			
			AuthentificationEntity firstUpdater = userRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUserCo));
	
			Boolean exist = roofAttachmentRepo.findByManufacturerAndModelAndIdNot(
					raModel.getManufacturer().trim().toLowerCase(), raModel.getModel().trim().toLowerCase(),
					raModel.getId());

			if (Boolean.TRUE.equals(exist)) {
				return "exist";
			} else {
				RoofAttachmentsEntity ra = roofAttachmentRepo.findById(raModel.getId())
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
				ra.setManufacturer(raModel.getManufacturer());
				ra.setModel(raModel.getModel());
				ra.setManufacturerMappingValue(checkValue.isStringNotEmpty(raModel.getManufacturerMappingValue())
						? raModel.getManufacturerMappingValue()
						: raModel.getManufacturer());
				ra.setModelMappingValue(
						checkValue.isStringNotEmpty(raModel.getModelMappingValue()) ? raModel.getModelMappingValue()
								: raModel.getModel());
				ra.setRoofType(raModel.getRoofType());
				ra.setWeight(raModel.getWeight());
				ra.setIntegrated(raModel.getIntegrated());
				ra.setNumberOfRoof(raModel.getNumberOfRoof());
				ra.setUtilizeS100(raModel.getUtilizeS100());
				ra.setAllowedRoof(raModel.getAllowedRoof());
				DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				ra.setLastUpdate(dateFormat.format(new Date()));
				ra.setIsVerified(false);
				String updateNum = "";
				if(ra.getFirstUpdater() == null) {
					updateNum = "1st";	
					ra.setFirstUpdater(firstUpdater);
				}else if(ra.getSecondUpdater() == null) {
					updateNum = "2nd";				
					ra.setSecondUpdater(firstUpdater);
				}else if(ra.getThirdUpdater() == null) {
					updateNum = "3rd";				
					ra.setThirdUpdater(firstUpdater);
				}

				roofAttachmentRepo.save(ra);
				editRoofAttachmentNotification(idUserCo, raModel.getManufacturer(), raModel.getModel());
				if (checkValueTypesService.isStringNotEmpty(updateNum)) {
					mailingService.mailUpdate("Roof Attachments",
							"An existing equipment of Roof Attachments " + ra.getManufacturer() + " " + ra.getModel()
									+ " has been updated " + updateNum + " time by " + firstUpdater.getFirstName() + " "
									+ firstUpdater.getLastName(),
									firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
				}

				activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;Edit component " + ra.getManufacturer() + " " + ra.getModel() + ".;Roof Attachments",
						true, "", "", "");
				return "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Edit component.;Roof Attachments",
					false, "", "", "");
			return "error";
		}
	}
	

	/*
	 * Delete Roof Attachment
	 */

	public List<ProjectForLibrariesModel> getRemoveRoofAttachmentConfirmation(String roofAttachmentId) {
		try {
			return roofAttachmentRepo.getProjectForLibrariesModel(roofAttachmentId, false);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String deleteRoofAttachment(Long roofAttachmentID, String ipUser, String timeZoneUser, Long idUserCo) {
		try {

			RoofAttachmentsEntity roofAttachment = roofAttachmentRepo.findById(roofAttachmentID)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			/*
			 * Remove Roof Attachment From Active Projects
			 */
			List<PermitProjectSiteInfoEntity> roofAttachmentProject = permitProjectSiteInfoEntityRepo
					.findByRailConnectionModelAndPermitEntityIsDeleted(roofAttachment.getId() + "", false);
			if (roofAttachmentProject != null && !roofAttachmentProject.isEmpty()) {
				for (int i = 0; i < roofAttachmentProject.size(); i++) {
					if (roofAttachmentProject.get(i) != null && checkValue.Equals(
							roofAttachmentProject.get(i).getRailConnectionModel(), roofAttachment.getId() + "")) {
						roofAttachmentProject.get(i).setRailConnectionModel("Removed");
					}
				}
			}

			/*
			 * Remove Roof Attachment From all the favorite Lists
			 */
			List<RoofAttachmentFavLibraryEntity> roofAttachmentFav = roofAttachmentFavRepo
					.findByRoofAttachmentId(roofAttachmentID);
			if (roofAttachmentFav != null && !roofAttachmentFav.isEmpty()) {
				for (int i = 0; i < roofAttachmentFav.size(); i++) {
					roofAttachmentFavRepo.delete(roofAttachmentFav.get(i));
				}
			}

			/*
			 * Remove Roof Attachment From Roof Attachment Library
			 */
			roofAttachment.setIsDeleted(true);
			roofAttachmentRepo.save(roofAttachment);

			activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Delete component "
					+ roofAttachment.getManufacturer() + " " + roofAttachment.getModel() + ".;Roof Attachments", true,
					"", "", "");
			return "Done";

		} catch (Exception e) {
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Delete component.;Roof Attachments", false, "", "", "");
			e.printStackTrace();

			return "error";
		}
	}
	/*
	 * Edit Roof Attachment Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long roofAttachmentID, Long userID) {
		List<UsersEntityResult> usersList = new ArrayList<>();
		try {
			List<RoofAttachmentFavLibraryEntity> roofAttachmentFav = roofAttachmentFavRepo
					.findByRoofAttachmentId(roofAttachmentID);
			if (roofAttachmentFav != null && !roofAttachmentFav.isEmpty()) {

				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < roofAttachmentFav.size(); i++) {
					if (roofAttachmentFav.get(i) != null
							&& roofAttachmentFav.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(roofAttachmentFav.get(i).getAuthentificationEntity().getId());
					}
				}
				usersList = userRepo.findUserHaveNotFav(!usersFavID.isEmpty() ? usersFavID : null, false, true, userID);

				return usersList;

			} else {

				usersList = userRepo.findUserHaveNotFav(null, false, true, userID);

				return usersList;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return usersList;
		}

	}

	public String editUsersFavoriteList(Long roofAttachmentID, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo) {
		try {

			RoofAttachmentsEntity roofAttachment = roofAttachmentRepo.findById(roofAttachmentID)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			for (int i = 0; i < listUsers.length; i++) {

				AuthentificationEntity user = userRepo.findById(listUsers[i])
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
				if (roofAttachment != null && user != null) {
					RoofAttachmentFavLibraryEntity roofAttachmentFav = new RoofAttachmentFavLibraryEntity(user,
							roofAttachment);
					roofAttachmentFavRepo.save(roofAttachmentFav);

					activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
							"libraries;The favorite " + roofAttachment.getManufacturer() + " "
									+ roofAttachment.getModel() + " is added to the user " + user.getFirstName() + " "
									+ user.getLastName() + ".;Roof Attachments",
							true, "", "", "");
				}

			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Roof Attachment to Users Favorites List.;Roof Attachments", false, "", "", "");
			return "error";
		}
	}

	/*
	 * Activate Roof Attachment
	 */
	public String activateRoofAttachment(Long roofAttachmentID, String ipUser, String timeZoneUser, Long idUserCo) {
		try {
			RoofAttachmentsEntity roofAttachment = roofAttachmentRepo.findById(roofAttachmentID)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			if (roofAttachment != null) {
				List<RoofAttachmentsEntity> listRoofAttachment = roofAttachmentRepo
						.findByManufacturerAndModelAndIsDeleted(roofAttachment.getManufacturer().trim().toLowerCase(),
								roofAttachment.getModel().trim().toLowerCase(), false);
				if (listRoofAttachment != null && !listRoofAttachment.isEmpty()) {
					return "exist";
				} else {
					roofAttachment.setIsDeleted(false);
					roofAttachmentRepo.save(roofAttachment);

					activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Activate component "
							+ roofAttachment.getManufacturer() + " " + roofAttachment.getModel() + ".;Roof Attachments",
							true, "", "", "");
					return "Done";
				}
			}
			return "error";
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Activate component.;Roof Attachments", false, "", "", "");
			return "error";
		}
	}

	private String editRoofAttachmentNotification(Long userID, String make, String model) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			if (userCo != null) {
				notificationService
						.addNewNotif(userID, 0L, "Roof Attachment Update", "Libraries",
								"Roof Attachment Update - " + model, "The Roof Attachment " + model + " " + make
										+ " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
								true);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	private String addRoofAttachmentNotification(Long userID, String make, String model) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));
			if (userCo != null) {
				notificationService.addNewNotif(userID, 0L, "New Roof Attachment", "Libraries",
						"New Roof Attachment- " + model, "The Roof Attachment " + model + " " + make + " was added by "
								+ userCo.getFirstName() + " " + userCo.getLastName(),
						true);
			}

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	/*
	 * Refresh the Roof Attachment Favorite List
	 */
	public boolean refreshRoofAttachements(Long idUser, Long model) {

		try {
			return roofAttachmentFavRepo.existsByAuthentificationEntityIdAndRoofAttachmentId(idUser, model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public String sendCorrectionRequest(CorrectionRequest request) {

		try {
			if (request != null && request.getId() != 0) {

				AuthentificationEntity user = userRepo.findById(request.getIdUser()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + request.getIdUser()));
				RoofAttachmentsEntity library = roofAttachmentRepo.findById(request.getId()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + request.getId()));
				if (library != null && user != null) {
					library.setHasCorrectionRequest(true);
					library.setEroneousContent(request.getEroneousContent());
					library.setEroneousContentOther(request.getEroneousContentOther());
					library.setEroneousDescription(request.getEroneousDescription());
					roofAttachmentRepo.save(library);
					notificationService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
							"Request Correction - " + library.getModel(),
							"The user " + user.getFirstName() + " " + user.getLastName()
									+ " request correction for the " + library.getRoofType() + " "
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

//M.a get the id of the faviorie Converter
	public List<Long> getRoofAttachmentFavorite(Long idContractor) {
		try {
			List<RoofAttachmentFavLibraryEntity> roofAttachementFav = roofAttachmentFavRepo
					.findByAuthentificationEntityId(idContractor);
			if (roofAttachementFav != null && !roofAttachementFav.isEmpty()) {
				return new ArrayList<>(roofAttachementFav.stream().map(o -> o.getRoofAttachment().getId())
						.collect(Collectors.toList()));
			}
			return new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}


}
