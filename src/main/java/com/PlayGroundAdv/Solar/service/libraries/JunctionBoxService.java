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
import com.PlayGroundAdv.Solar.entity.JunctionBoxFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.JunctionsBoxModel;
import com.PlayGroundAdv.Solar.model.NewJunctionBoxModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.JunctionBoxFavRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class JunctionBoxService {

	final HistoryActivityService activityService;
	final NotificationEntityService notificationService;
	final CheckValueTypesService checkValue;
	final JunctionBoxFavRepository jbFavRepo;
	final DcCombinerDiscoRepository dcdRepo;
	final AuthenticationRepository userRepo;
	final PermitProjectSiteInfoRepository siteInfoRepo;
	final PermitRepository projectRepo;
	final EquipmentUpdate mailingService;

	public JunctionBoxService(HistoryActivityService activityService, NotificationEntityService notificationService,
			CheckValueTypesService checkValue, JunctionBoxFavRepository jbFavRepo, DcCombinerDiscoRepository dcdRepo,
			AuthenticationRepository userRepo, PermitProjectSiteInfoRepository siteInfoRepo,
			PermitRepository projectRepo, EquipmentUpdate mailingService) {
		super();
		this.activityService = activityService;
		this.notificationService = notificationService;
		this.checkValue = checkValue;
		this.jbFavRepo = jbFavRepo;
		this.dcdRepo = dcdRepo;
		this.userRepo = userRepo;
		this.siteInfoRepo = siteInfoRepo;
		this.projectRepo = projectRepo;
		this.mailingService = mailingService;
	}


	public Page<JunctionsBoxModel> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValue.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValue.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			if (manufacturer == null && model == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<DCCombinerDisconnectEntity> p = dcdRepo.findAllJbox(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser());
			} else {
				Page<DCCombinerDisconnectEntity> p = dcdRepo.findAll(filter(manufacturer, model,
						request.getIsFavorite(), request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<JunctionsBoxModel> convertDto(Page<DCCombinerDisconnectEntity> page, Pageable pageable, Long userId) {
		try {
			List<Long> favorite = getJunctionBoxFavorite(userId);
			return new PageImpl<>(
					page.stream().map(c -> new JunctionsBoxModel(c.getId(), c.getManufacturer(), c.getModel(),
							c.getDropdownOption(), c.getOcpd(), c.getWeight(), c.getNemaRating(), c.getMaxInput(),
							c.getMaxContiOutputCurrent(), c.getMaxOutputCurrent(), c.getTypeDc(), c.getIsDeleted(),
							favorite.indexOf(c.getId()) != -1,
							(c.getIdOwner().getFirstName() + " " + c.getIdOwner().getLastName()).equals("Arij B.Othman")
									? "Super User"
									: c.getIdOwner().getFirstName() + " " + c.getIdOwner().getLastName(),
							c.getLastUpdate(), c.getIdOwner().getId() + " ", c.getEroneousContent(),
							c.getEroneousContentOther(), c.getEroneousDescription(), c.getHasCorrectionRequest(),
							c.getManufacturerMappingValue(), c.getModelMappingValue(),
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

	private Specification<DCCombinerDisconnectEntity> filter(String[] makes, String[] models, Boolean favorite,
			Boolean deleted, Long userId) {

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
				Predicate predicateJB = cb.equal(root.get("typeDc"), "J Box");
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate endPredicateMake = makes != null
						? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()]))
						: null;
				Predicate endPredicateModel = models != null
						? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateJB, predicateDeleted, endPredicateMake,
						endPredicateModel);
				if (Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : getJunctionBoxFavorite(userId)) {
						inClause.value(id);
					}
					list = Arrays.asList(predicateJB, predicateDeleted, endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}
	/*
	 * Get Junction Boxs Fav
	 */
	public List<Long> getJunctionBoxFavorite(Long idUser) {
		try {
			return jbFavRepo.findByUserId(idUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/*
	 * Edit Junction Boxs Favorite List
	 */
	public String editJunctionBoxFavoriteList(Long junctionBoxID, Boolean isShown, String ipUser, String timeZoneUser,
			Long idUserCo, Long idOwner, String numTab, String sessionId, String openDate) {
		try {
			AuthentificationEntity user = userRepo.findById(idOwner).orElse(null);

			if (user != null && checkValue.Equals(isShown, true)) {
				DCCombinerDisconnectEntity junctionBox = dcdRepo.findById(junctionBoxID).orElse(null);
				if (junctionBox != null) {
					JunctionBoxFavLibraryEntity junctionBoxFav = new JunctionBoxFavLibraryEntity(user, junctionBox);
					jbFavRepo.save(junctionBoxFav);

					activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
							"libraries;The favorite " + junctionBox.getManufacturer() + " " + junctionBox.getModel()
									+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
									+ ".;Junction Box",
							true, numTab, sessionId, openDate);
				}
			} else {

				JunctionBoxFavLibraryEntity junctionBoxFav = jbFavRepo
						.findByJboxIdAndAuthentificationEntityId(junctionBoxID, idOwner);
				jbFavRepo.delete(junctionBoxFav);
				String models = junctionBoxFav.getJbox().getId() + "";
			
				List<PermitProjectSiteInfoEntity> permitArray3 = siteInfoRepo
						.findByPermitEntityAuthentificationEntityIdAndRoofTopJbox(idOwner, models);
				for (int i = 0; permitArray3 != null && !permitArray3.isEmpty() && i < permitArray3.size(); i++) {
					if (permitArray3.get(i) != null) {
						permitArray3.get(i).setRoofTopJbox("Fav Removed");
						siteInfoRepo.save(permitArray3.get(i));

					}
				}

				List<PermitProjectSiteInfoEntity> permitArray4 = siteInfoRepo
						.findByPermitEntityAuthentificationEntityIdAndRoofTopJboxDC(idOwner, models);
				for (int i = 0; permitArray4 != null && !permitArray4.isEmpty() && i < permitArray4.size(); i++) {
					if (permitArray4.get(i) != null) {
						permitArray4.get(i).setRoofTopJboxDC("Fav Removed");
						siteInfoRepo.save(permitArray4.get(i));

					}
				}
				activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + junctionBoxFav.getJbox().getManufacturer() + " "
								+ junctionBoxFav.getJbox().getModel() + " is removed from the user "
								+ user.getFirstName() + " " + user.getLastName() + ".;Junction Box",
						true, numTab, sessionId, openDate);

			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Junction Box to Users Favorites List;Junction Box", false, numTab, sessionId,
					openDate);
			return "error";
		}

	}

	/*
	 * Edit Junction Box Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long junctionBoxID, Long userID) {
		List<UsersEntityResult> usersList = new ArrayList<>();
		try {

			List<JunctionBoxFavLibraryEntity> junctionBoxFav = jbFavRepo.findByJboxId(junctionBoxID);
			if (junctionBoxFav != null && !junctionBoxFav.isEmpty()) {
				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < junctionBoxFav.size(); i++) {
					if (junctionBoxFav.get(i) != null && junctionBoxFav.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(junctionBoxFav.get(i).getAuthentificationEntity().getId());
					}
				}
				usersList = userRepo.findUserHaveNotFav(usersFavID, false, true, userID);
			} else {
				usersList = userRepo.findUserHaveNotFav(null, false, true, userID);
			}
			return usersList;

		} catch (Exception e) {
			e.printStackTrace();
			return usersList;
		}

	}

	public String editUsersFavoriteList(Long junctionBoxID, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {
		try {

			DCCombinerDisconnectEntity junctionBox = dcdRepo.findById(junctionBoxID).orElse(null);
			for (int i = 0; listUsers != null && i < listUsers.length; i++) {

				AuthentificationEntity user = userRepo.findById(listUsers[i]).orElse(null);
				JunctionBoxFavLibraryEntity junctionBoxFav = new JunctionBoxFavLibraryEntity(user, junctionBox);
				jbFavRepo.save(junctionBoxFav);

				activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + junctionBox.getManufacturer() + " " + junctionBox.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;Junction Box",
						true, numTab, sessionId, openDate);
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Junction Box to Users Favorites List;Junction Box", false, numTab, sessionId,
					openDate);
			return "error";
		}
	}

	/*
	 * Add New Junction Boxs
	 */
	public List<JunctionsBoxModel> checkJBoxExistent(NewJunctionBoxModel newJunctionBox, Long idUser) {

		List<JunctionsBoxModel> junctionBoxList = new ArrayList<>();
		try {
			List<DCCombinerDisconnectEntity> listJunctionBox = dcdRepo.findByModelAndManufacturerAndIsDeletedAndTypeDc(
					newJunctionBox.getModel().trim().toLowerCase(),
					newJunctionBox.getManufacturer().trim().toLowerCase(), false, "J Box");
			List<DCCombinerDisconnectEntity> listJunctionBoxNot = dcdRepo
					.findByModelAndManufacturerNotAndIsDeletedAndTypeDc(newJunctionBox.getModel().trim().toLowerCase(),
							newJunctionBox.getManufacturer().trim().toLowerCase(), false, "J Box");

			if (listJunctionBox != null && !listJunctionBox.isEmpty()) {
				List<Long> junctionBoxFavID = getJunctionBoxFavorite(idUser);

				for (int i = 0; listJunctionBox != null && i < listJunctionBox.size(); i++) {

					JunctionsBoxModel junctionBox = new JunctionsBoxModel();
					if (listJunctionBox.get(i) != null) {
						junctionBox.setIsShown(
								junctionBoxFavID != null && junctionBoxFavID.contains(listJunctionBox.get(i).getId()));
						junctionBox.setId(listJunctionBox.get(i).getId());
						junctionBox.setManufacturer(listJunctionBox.get(i).getManufacturer());
						junctionBox.setModel(listJunctionBox.get(i).getModel());
						junctionBox.setManufacturerMappingValue(listJunctionBox.get(i).getManufacturerMappingValue());
						junctionBox.setModelMappingValue(listJunctionBox.get(i).getModelMappingValue());
						junctionBox.setDropdownOption(listJunctionBox.get(i).getDropdownOption());
						junctionBox.setMaxInput(listJunctionBox.get(i).getMaxInput());
						junctionBox.setNemaRating(listJunctionBox.get(i).getNemaRating());
						if (listJunctionBox.get(i).getIdOwner() != null) {
							junctionBox.setOwner(listJunctionBox.get(i).getIdOwner().getFirstName() + " "
									+ listJunctionBox.get(i).getIdOwner().getLastName());
							junctionBox.setIdOwner(listJunctionBox.get(i).getIdOwner().getId() + "");
						}
						junctionBox.setLastUpdate(listJunctionBox.get(i).getLastUpdate());
						junctionBox.setEroneousContent(listJunctionBox.get(i).getEroneousContent());
						junctionBox.setEroneousContentOther(listJunctionBox.get(i).getEroneousContentOther());
						junctionBox.setEroneousDescription(listJunctionBox.get(i).getEroneousDescription());
						junctionBox.setHasCorrectionRequest(listJunctionBox.get(i).getHasCorrectionRequest());
						junctionBox.setIsDeleted(false);
					}
					junctionBoxList.add(i, junctionBox);

				}

			} else if (listJunctionBoxNot != null && !listJunctionBoxNot.isEmpty()) {

				List<Long> junctionBoxFavID = getJunctionBoxFavorite(idUser);

				for (int i = 0; listJunctionBoxNot != null && i < listJunctionBoxNot.size(); i++) {

					JunctionsBoxModel junctionBox = new JunctionsBoxModel();
					if (listJunctionBoxNot.get(i) != null) {
						junctionBox.setIsShown(junctionBoxFavID != null
								&& junctionBoxFavID.contains(listJunctionBoxNot.get(i).getId()));
						junctionBox.setId(listJunctionBoxNot.get(i).getId());
						junctionBox.setManufacturer(listJunctionBoxNot.get(i).getManufacturer());
						junctionBox.setModel(listJunctionBoxNot.get(i).getModel());
						junctionBox
								.setManufacturerMappingValue(listJunctionBoxNot.get(i).getManufacturerMappingValue());
						junctionBox.setModelMappingValue(listJunctionBoxNot.get(i).getModelMappingValue());
						junctionBox.setDropdownOption(listJunctionBoxNot.get(i).getDropdownOption());
						junctionBox.setMaxInput(listJunctionBoxNot.get(i).getMaxInput());
						junctionBox.setNemaRating(listJunctionBoxNot.get(i).getNemaRating());
						if (listJunctionBoxNot.get(i).getIdOwner() != null) {
							junctionBox.setOwner(listJunctionBoxNot.get(i).getIdOwner().getFirstName() + " "
									+ listJunctionBoxNot.get(i).getIdOwner().getLastName());
							junctionBox.setIdOwner(listJunctionBoxNot.get(i).getIdOwner().getId() + "");
						}
						junctionBox.setLastUpdate(listJunctionBoxNot.get(i).getLastUpdate());
						junctionBox.setEroneousContent(listJunctionBoxNot.get(i).getEroneousContent());
						junctionBox.setEroneousContentOther(listJunctionBoxNot.get(i).getEroneousContentOther());
						junctionBox.setEroneousDescription(listJunctionBoxNot.get(i).getEroneousDescription());
						junctionBox.setHasCorrectionRequest(listJunctionBoxNot.get(i).getHasCorrectionRequest());
						junctionBox.setIsDeleted(false);
					}
					junctionBoxList.add(i, junctionBox);

				}
			}
			return junctionBoxList;
		} catch (Exception e) {
			e.printStackTrace();
			return junctionBoxList;
		}

	}

	public DCCombinerDisconnectEntity addJunctionBox(NewJunctionBoxModel junctionBox, String ipUser,
			String timeZoneUser, Long idUserCo, Long idPermit, String numTab, String sessionId, String openDate) {
		DCCombinerDisconnectEntity newJunctionBox = new DCCombinerDisconnectEntity();
		try {

			PermitEntity project = projectRepo.findById(idPermit).orElse(null);
			AuthentificationEntity contractor = project != null ? project.getAuthentificationEntity() : null;
			AuthentificationEntity user = userRepo.findById(idUserCo).orElse(null);

			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			newJunctionBox.setManufacturer(junctionBox.getManufacturer());
			newJunctionBox.setModel(junctionBox.getModel());

			if (checkValue.NotEquals(junctionBox.getManufacturerMappingValue(), "")) {
				newJunctionBox.setManufacturerMappingValue(junctionBox.getManufacturerMappingValue());
			} else
				newJunctionBox.setManufacturerMappingValue(junctionBox.getManufacturer());

			if (checkValue.NotEquals(junctionBox.getModelMappingValue(), "")) {
				newJunctionBox.setModelMappingValue(junctionBox.getModelMappingValue());
			} else
				newJunctionBox.setModelMappingValue(junctionBox.getModel());

			newJunctionBox.setDropdownOption(junctionBox.getDropdownOption());
			newJunctionBox.setTypeDc("J Box");
			newJunctionBox.setIdOwner(user);
			newJunctionBox.setIsDeleted(false);
			newJunctionBox.setLastUpdate(dateFormat.format(new Date()));
			newJunctionBox.setHasSuperUserEdit(
					user != null && (user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3));
			Date addDate = new Date();
			newJunctionBox.setAddDate(addDate);
			newJunctionBox.setIsVerified(false);
			dcdRepo.save(newJunctionBox);

			JunctionBoxFavLibraryEntity junctionBoxFav = new JunctionBoxFavLibraryEntity();
			junctionBoxFav.setJbox(newJunctionBox);
			junctionBoxFav.setAuthentificationEntity(contractor);
			jbFavRepo.save(junctionBoxFav);
			mailingService.mailUpdate("Junction Box",
					"A new equipment of Junction Box " + junctionBox.getManufacturer() + " " + junctionBox.getModel()
							+ " has been added by " + user.getFirstName() + " " + user.getLastName(),
					contractor.getEmail().contains("nuagetechnologies-tn.com")
							&& !contractor.getEmail().contains("pm"));
			activityService
					.recordActivity(
							idUserCo, ipUser, timeZoneUser, "libraries;Add component " + junctionBox.getManufacturer()
									+ " " + junctionBox.getModel() + ".;Junction Box",
							true, numTab, sessionId, openDate);
			addDCJboxNotification(user, junctionBox.getManufacturer(), junctionBox.getModel());
			return newJunctionBox;
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Add Junction ;Junction Box",
					false, numTab, sessionId, openDate);
			return newJunctionBox;
		}
	}

	/*
	 * Edit Junction Box
	 */

	public String editJunctionBox(JunctionsBoxModel junctionBox, String ipUser, String timeZoneUser, Long idUserCo,
			String numTab, String sessionId, String openDate) {

		try {
			DCCombinerDisconnectEntity editJunctionBox = dcdRepo.findById(junctionBox.getId()).orElse(null);

			AuthentificationEntity firstUpdater = userRepo.findById(idUserCo)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idUserCo));

			List<DCCombinerDisconnectEntity> listDCCD = dcdRepo.findByManufacturerAndModelAndNotIdAndTypeDc(
					junctionBox.getManufacturer(), junctionBox.getModel(), junctionBox.getId(), "J Box");
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			if (listDCCD != null && !listDCCD.isEmpty()) {
				return "exist";
			} else {

				editJunctionBox.setManufacturer(junctionBox.getManufacturer());
				editJunctionBox.setModel(junctionBox.getModel());

				if (checkValue.NotEquals(junctionBox.getManufacturerMappingValue(), "")) {
					editJunctionBox.setManufacturerMappingValue(junctionBox.getManufacturerMappingValue());
				} else
					editJunctionBox.setManufacturerMappingValue(junctionBox.getManufacturer());

				if (checkValue.NotEquals(junctionBox.getModelMappingValue(), "")) {
					editJunctionBox.setModelMappingValue(junctionBox.getModelMappingValue());
				} else
					editJunctionBox.setModelMappingValue(junctionBox.getModel());

				editJunctionBox.setDropdownOption(junctionBox.getDropdownOption());
				editJunctionBox.setMaxInput(junctionBox.getMaxInput());
				editJunctionBox.setNemaRating(junctionBox.getNemaRating());
				editJunctionBox.setMaxContiOutputCurrent(junctionBox.getMaxContiOutputCurrent());
				editJunctionBox.setMaxOutputCurrent(junctionBox.getMaxOutputCurrent());
				editJunctionBox.setOcpd(junctionBox.getOcpd());
				editJunctionBox.setWeight(junctionBox.getWeight());
				editJunctionBox.setEroneousContent(junctionBox.getEroneousContent());
				editJunctionBox.setEroneousContentOther(junctionBox.getEroneousContentOther());
				editJunctionBox.setEroneousDescription(junctionBox.getEroneousDescription());
				editJunctionBox.setHasCorrectionRequest(junctionBox.getHasCorrectionRequest());
				editJunctionBox.setLastUpdate(dateFormat.format(new Date()));
				editJunctionBox.setIsVerified(false);
				String updateNum = "";
				if (editJunctionBox.getFirstUpdater() == null) {
					updateNum = "1st";
					editJunctionBox.setFirstUpdater(firstUpdater);
				} else if (editJunctionBox.getSecondUpdater() == null) {
					updateNum = "2nd";
					editJunctionBox.setSecondUpdater(firstUpdater);
				} else if (editJunctionBox.getThirdUpdater() == null) {
					updateNum = "3rd";
					editJunctionBox.setThirdUpdater(firstUpdater);
				}

				dcdRepo.save(editJunctionBox);
				activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Edit component "
						+ junctionBox.getManufacturer() + " " + junctionBox.getModel() + ".;Junction Box", true, numTab,
						sessionId, openDate);

				if (checkValue.isStringNotEmpty(updateNum)) {
					mailingService.mailUpdate("Junction Box",
							"An existing equipment of Junction Box " + junctionBox.getManufacturer() + " "
									+ junctionBox.getModel() + " has been updated " + updateNum + " time by "
									+ firstUpdater.getFirstName() + " " + firstUpdater.getLastName(),
							firstUpdater.getEmail().contains("nuagetechnologies-tn.com")
									&& !firstUpdater.getEmail().contains("pm"));
				}
				editDCJboxNotification(firstUpdater, junctionBox.getManufacturer(), junctionBox.getModel());
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Edit component.;Junction Box",
					false, numTab, sessionId, openDate);
			return "error";
		}
	}

	/*
	 * Delete Junction Box
	 */

	public List<ProjectForLibrariesModel> getRemoveJunctionBoxConfirmation(JunctionsBoxModel junctionBox) {
		try {

			return dcdRepo.getProjectForLibrariesModel(junctionBox.getId() + "", false);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String deleteJunctionBox(Long junctionBoxID, String ipUser, String timeZoneUser, Long idUserCo,
			String numTab, String sessionId, String openDate) {
		try {

			DCCombinerDisconnectEntity junctionBox = dcdRepo.findById(junctionBoxID).orElse(null);

			/*
			 * Remove Junction Box From Active Projects
			 */
			List<PermitProjectSiteInfoEntity> junctionBoxProject = siteInfoRepo.findByRoofTopJboxAndPermitEntityIsDeleted(junctionBox.getId() + "",
					false);

			if (junctionBoxProject != null && !junctionBoxProject.isEmpty()) {
				for (int i = 0; i < junctionBoxProject.size(); i++) {
					if (junctionBoxProject.get(i) != null && checkValue
							.Equals(junctionBoxProject.get(i).getRoofTopJbox(), junctionBox.getId() + "")) {
						junctionBoxProject.get(i).setRoofTopJbox("Removed");
					}
				}
			}
			junctionBoxProject = siteInfoRepo.findByRoofTopJboxDCAndPermitEntityIsDeleted(junctionBox.getId() + "",
					false);

			if (junctionBoxProject != null && !junctionBoxProject.isEmpty()) {
				for (int i = 0; i < junctionBoxProject.size(); i++) {
					if (junctionBoxProject.get(i) != null && checkValue
							.Equals(junctionBoxProject.get(i).getRoofTopJboxDC(), junctionBox.getId() + "")) {
						junctionBoxProject.get(i).setRoofTopJboxDC("Removed");
					}
				}
			}
			/*
			 * Remove Junction Box From all the favorite Lists
			 */
			List<JunctionBoxFavLibraryEntity> junctionBoxFav = jbFavRepo.findByJboxId(junctionBoxID);
			if (junctionBoxFav != null && !junctionBoxFav.isEmpty()) {
				for (int i = 0; i < junctionBoxFav.size(); i++) {
					jbFavRepo.delete(junctionBoxFav.get(i));
				}
			}

			/*
			 * Remove Junction Box From Junction Box Library
			 */
			junctionBox.setIsDeleted(true);
			dcdRepo.save(junctionBox);

			activityService
					.recordActivity(
							idUserCo, ipUser, timeZoneUser, "libraries;Delete component "
									+ junctionBox.getManufacturer() + " " + junctionBox.getModel() + ".;Junction Box",
							true, numTab, sessionId, openDate);
			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Delete component.;Junction Box",
					false, numTab, sessionId, openDate);
			return "error";
		}
	}

	/*
	 * Activate Junction Box
	 */
	public String activateJunctionBox(Long junctionBoxID, String ipUser, String timeZoneUser, Long idUserCo,
			String numTab, String sessionId, String openDate) {
		try {

			DCCombinerDisconnectEntity junctionBox = dcdRepo.findById(junctionBoxID).orElse(null);
			if (Boolean.TRUE.equals(dcdRepo.existsByManufacturerAndModelAndIsDeleted(junctionBox.getManufacturer(),
					junctionBox.getModel(), false))) {
				return "exist";
			} else {
				junctionBox.setIsDeleted(false);
				dcdRepo.save(junctionBox);

				activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Activate component "
						+ junctionBox.getManufacturer() + " " + junctionBox.getModel() + ".;Junction Box", true, numTab,
						sessionId, openDate);
				return "Done";
			}
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUserCo, ipUser, timeZoneUser, "libraries;Activate component.;Junction Box",
					false, numTab, sessionId, openDate);
			return "error";
		}
	}

	/*
	 * Search Junction Box
	 */

	/*
	 * Get Junction Box Favorite List
	 */
	public List<DCCombinerDisconnectEntity> getJBox(Long idPemit) {
		try {
			PermitEntity permit = projectRepo.findById(idPemit).orElse(null);
			if (permit != null) {
				return getContractorJBox(permit.getAuthentificationEntity().getId());
			}
			return new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public List<DCCombinerDisconnectEntity> getContractorJBox(Long idUser) {
		try {
			List<Long> junctionBoxFavID = getJunctionBoxFavorite(idUser);
			if (junctionBoxFavID != null && !junctionBoxFavID.isEmpty()) {
				return dcdRepo.findByIdInAndTypeDcOrderByDropdownOptionAsc(junctionBoxFavID, "J Box");
			}
			return new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	private void editDCJboxNotification(AuthentificationEntity user, String manufacturer, String model) {
		try {
			if (user != null) {
				notificationService.addNewNotif(user.getId(), 0L, "Junction Box Update", "Libraries",
						"Junction Box Update - " + manufacturer,
						"The Junction Box " + model + " " + model + " was updated by "
								+ user.getFirstName() + " " + user.getLastName(),
						true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDCJboxNotification(AuthentificationEntity user, String manufacturer, String model) {
		try {
			if (user != null) {
				notificationService.addNewNotif(user.getId(), 0L, "New Junction Box", "Libraries",
						"New Junction Box- " + model, "The Junction Box " + manufacturer + " " + model
								+ " was added by " + user.getFirstName() + " " + user.getLastName(),
						true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sendCorrectionRequest(CorrectionRequest request) {

		try {
			if (request.getId() != 0) {

				AuthentificationEntity user = userRepo.findById(request.getIdUser()).orElse(null);
				DCCombinerDisconnectEntity library = dcdRepo.findById(request.getId()).orElse(null);
				if (library != null) {
					library.setHasCorrectionRequest(true);
					library.setEroneousContent(request.getEroneousContent());
					library.setEroneousContentOther(request.getEroneousContentOther());
					library.setEroneousDescription(request.getEroneousDescription());
					dcdRepo.save(library);
					notificationService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
							"Request Correction - " + library.getModel(),
							"The user " + user.getFirstName() + " " + user.getLastName()
									+ " request correction for the Junction Box " + library.getManufacturer() + " "
									+ library.getModel(),
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
