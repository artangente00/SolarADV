package com.PlayGroundAdv.Solar.service.libraries;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
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
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.ACDisconnectFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ACDisconnectEntityModel;
import com.PlayGroundAdv.Solar.model.AcDisconnectModel;
import com.PlayGroundAdv.Solar.model.NewDisconnectModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectFavRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class AcDisconnectService {

	final HistoryActivityService historyActivityService;
	final NotificationEntityService notificationEntityService;
	final HistoryActivityService historicActivity;
	final CheckValueTypesService checkValueTypesService;
	final ACDisconnectFavRepository aCDisconnectFavLibraryRepo;
	final ACDisconnectRepository aCDisconnectRepo;
	final AuthenticationRepository authentificationRepo;
	final PermitRepository permitEntityRepo;
	final ModelMapper modelMapper;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoRepo;
	final EquipmentUpdate mailingService;

	public AcDisconnectService(HistoryActivityService historyActivityService,
			NotificationEntityService notificationEntityService, HistoryActivityService historicActivity,
			CheckValueTypesService checkValueTypesService, ACDisconnectFavRepository aCDisconnectFavLibraryRepo,
			ACDisconnectRepository aCDisconnectRepo, AuthenticationRepository authentificationRepo,
			PermitRepository permitEntityRepo, ModelMapper modelMapper,
			PermitProjectSiteInfoRepository permitProjectSiteInfoRepo,
			EquipmentUpdate mailingService) {
		super();
		this.historyActivityService = historyActivityService;
		this.notificationEntityService = notificationEntityService;
		this.historicActivity = historicActivity;
		this.checkValueTypesService = checkValueTypesService;
		this.aCDisconnectFavLibraryRepo = aCDisconnectFavLibraryRepo;
		this.aCDisconnectRepo = aCDisconnectRepo;
		this.authentificationRepo = authentificationRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.modelMapper = modelMapper;
		this.permitProjectSiteInfoRepo = permitProjectSiteInfoRepo;
		this.mailingService = mailingService;
	}

	private static final String REMOVED = "Removed";
	private static final String FAV_REMOVED = "Fav Removed";



	/*
	 * Get All Rated Current
	 */
	public List<String> getAllRatedCurrent() {
		try {
			return aCDisconnectRepo.getDistinctRatedCurrrent();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/*
	 * Edit AC Combiner / Disconnects Favorite List
	 */
	public String editAcCombinerDiscoFavoriteList(Long idAcd, Boolean isShown, String ipUser,
			String timeZoneUser, Long idUserCo, Long idOwner, String numTab, String sessionId, String openDate) {
		try {
			ACDisconnect acCombinerDisco = aCDisconnectRepo.findById(idAcd).orElse(new ACDisconnect());

			AuthentificationEntity user = authentificationRepo.findById(idOwner).orElse(new AuthentificationEntity());
			if (checkValueTypesService.Equals(isShown, true)) {
				Boolean exist = aCDisconnectFavLibraryRepo.existsByAuthentificationEntityIdAndACDisconnectId(idOwner, idAcd);
				if(Boolean.FALSE.equals(exist)) {
					ACDisconnectFavLibraryEntity acCombinerDiscoFav = new ACDisconnectFavLibraryEntity(user, acCombinerDisco);
					aCDisconnectFavLibraryRepo.save(acCombinerDiscoFav);
					String activityText = recordActivityTextAddFav(acCombinerDisco.getManufacturer() , acCombinerDisco.getModel(), user.getFirstName(), user.getLastName());
					historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
							activityText , true, numTab, sessionId, openDate);
				}
				
			} else {
				ACDisconnectFavLibraryEntity acCombinerDiscoFav = aCDisconnectFavLibraryRepo
						.findByAuthentificationEntityIdAndACDisconnectId(idUserCo, idAcd);
				aCDisconnectFavLibraryRepo.delete(acCombinerDiscoFav);

				String models = acCombinerDiscoFav.getACDisconnect().getId() + ":"
						+ acCombinerDiscoFav.getACDisconnect().getManufacturer() + ":"
						+ acCombinerDiscoFav.getACDisconnect().getModel();

				List<PermitProjectSiteInfoEntity> permitArray1 = permitProjectSiteInfoRepo
						.findByPermitEntityAuthentificationEntityIdAndRoofTopACCombinerDisconnect(idOwner, models);
				for (int i = 0; !permitArray1.isEmpty() && i < permitArray1.size(); i++) {
					permitArray1.get(i).setRoofTopACCombinerDisconnect(FAV_REMOVED);
					permitProjectSiteInfoRepo.save(permitArray1.get(i));
				}

				List<PermitProjectSiteInfoEntity> permitArray3 = permitProjectSiteInfoRepo
						.findByPermitEntityAuthentificationEntityIdAndGroundLevelACCombinerDisconnectModel(idOwner,
								models);

				for (int i = 0; !permitArray3.isEmpty() && i < permitArray3.size(); i++) {
					permitArray3.get(i).setGroundLevelACCombinerDisconnectModel(FAV_REMOVED);
					permitProjectSiteInfoRepo.save(permitArray3.get(i));
				}

				List<PermitProjectSiteInfoEntity> permitArray4 = permitProjectSiteInfoRepo
						.findByPermitEntityAuthentificationEntityIdAndRooftopACCombinerModel(idOwner, models);
				for (int i = 0; !permitArray4.isEmpty() && i < permitArray4.size(); i++) {
					permitArray4.get(i).setRooftopACCombinerModel(FAV_REMOVED);
					permitProjectSiteInfoRepo.save(permitArray4.get(i));
				}

				List<PermitProjectSiteInfoEntity> permitArray5 = permitProjectSiteInfoRepo
						.findByPermitEntityAuthentificationEntityIdAndRooftopACCombinerModelTwo(idOwner, models);
				for (int i = 0; !permitArray5.isEmpty() && i < permitArray5.size(); i++) {
					permitArray5.get(i).setRooftopACCombinerModelTwo(FAV_REMOVED);
					permitProjectSiteInfoRepo.save(permitArray5.get(i));
				}

				List<PermitProjectSiteInfoEntity> permitArray6 = permitProjectSiteInfoRepo
						.findByPermitEntityAuthentificationEntityIdAndRoofTopACDisco(idOwner, models);
				for (int i = 0; !permitArray6.isEmpty() && i < permitArray6.size(); i++) {
					permitArray6.get(i).setRoofTopACDisco(FAV_REMOVED);
					permitProjectSiteInfoRepo.save(permitArray6.get(i));
				}
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + acCombinerDisco.getManufacturer() + " " + acCombinerDisco.getModel()
								+ " is removed from the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;AC Disconnect",
						true, numTab, sessionId, openDate);
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add AC Disconnect to Users Favorites List;AC Disconnect", false, numTab, sessionId,
					openDate);
			return "error";
		}

	}

	/*
	 * Edit AC Combiner / Disconnect Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long aCDisconnect, Long userID) {


		try {
			List<ACDisconnectFavLibraryEntity> acCombinerDisco = aCDisconnectFavLibraryRepo
					.findByACDisconnectId(aCDisconnect);
			if (acCombinerDisco != null && !acCombinerDisco.isEmpty()) {
				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < acCombinerDisco.size(); i++) {
					if (acCombinerDisco.get(i) != null && acCombinerDisco.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(acCombinerDisco.get(i).getAuthentificationEntity().getId());
					}
				}
				return authentificationRepo.findUserHaveNotFav(usersFavID, false, true, userID);

			} else {
				return authentificationRepo.findUserHaveNotFav(null, false, true, userID);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public String editUsersFavoriteList(Long idAcd, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {
		try {

			ACDisconnect acCombinerDisco = aCDisconnectRepo.findById(idAcd).orElse(new ACDisconnect());
			for (int i = 0; i < listUsers.length; i++) {
				Boolean exist = aCDisconnectFavLibraryRepo.existsByAuthentificationEntityIdAndACDisconnectId(listUsers[i], idAcd);
				if(Boolean.FALSE.equals(exist)) {
					AuthentificationEntity user = authentificationRepo.findById(listUsers[i])
							.orElse(new AuthentificationEntity());

					ACDisconnectFavLibraryEntity acCombinerDiscoFav = new ACDisconnectFavLibraryEntity(user, acCombinerDisco);
					acCombinerDiscoFav.setACDisconnect(acCombinerDisco);
					acCombinerDiscoFav.setAuthentificationEntity(user);
					aCDisconnectFavLibraryRepo.save(acCombinerDiscoFav);
					String activityText = recordActivityTextAddFav(acCombinerDisco.getManufacturer() , acCombinerDisco.getModel(), user.getFirstName(), user.getLastName());
					historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
							activityText, true, numTab, sessionId, openDate);
				}
				
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add AC Disconnect to Users Favorites List.;AC Disconnect", false, numTab, sessionId,
					openDate);
			return "error";
		}
	}

	/*
	 * Add New AC Combiner / Disconnects
	 */
	public List<AcDisconnectModel> checkACCombinerExistent(NewDisconnectModel newAcCombinerDisco, Long idUser) {

		List<AcDisconnectModel> acCombinerDiscoList = new ArrayList<>();
		try {
			if (newAcCombinerDisco != null) {
				List<ACDisconnect> listAcCombinerDisco = aCDisconnectRepo.findByManufacturerAndModelAndIsDeleted(
						newAcCombinerDisco.getManufacturer().trim().toLowerCase(), newAcCombinerDisco.getModel().trim().toLowerCase(), false);

				List<ACDisconnect> listAcCombinerDisco2 = aCDisconnectRepo.findByManufacturerNotAndModelAndIsDeleted(
						newAcCombinerDisco.getManufacturer().trim().toLowerCase(), newAcCombinerDisco.getModel().trim().toLowerCase(), false);

				if (listAcCombinerDisco != null && !listAcCombinerDisco.isEmpty()) {
					List<Long> acCombinerDiscoFavID = aCDisconnectFavLibraryRepo.findFavoriteACD(idUser);

					for (int i = 0; i < listAcCombinerDisco.size(); i++) {

						AcDisconnectModel acCombinerDisco = new AcDisconnectModel();
						if (listAcCombinerDisco.get(i) != null) {
							acCombinerDisco.setIsShown(acCombinerDiscoFavID != null
										&& acCombinerDiscoFavID.contains(listAcCombinerDisco.get(i).getId()));
							acCombinerDisco.setId(listAcCombinerDisco.get(i).getId());
							acCombinerDisco.setManufacturer(listAcCombinerDisco.get(i).getManufacturer());
							acCombinerDisco.setModel(listAcCombinerDisco.get(i).getModel());
							acCombinerDisco.setManufacturerMappingValue(
									listAcCombinerDisco.get(i).getManufacturerMappingValue());
							acCombinerDisco.setModelMappingValue(listAcCombinerDisco.get(i).getModelMappingValue());
							acCombinerDisco
									.setDisconnectDeviceType(listAcCombinerDisco.get(i).getDisconnectDeviceType());
							acCombinerDisco.setDropdownOption(listAcCombinerDisco.get(i).getDropdownOption());
							acCombinerDisco.setMaxInput(listAcCombinerDisco.get(i).getMaxInput());
							acCombinerDisco.setNemaRating(listAcCombinerDisco.get(i).getNemaRating());
							acCombinerDisco.setNumberOfPoles(listAcCombinerDisco.get(i).getNumberOfPoles());
							acCombinerDisco.setQtyOfFuse(listAcCombinerDisco.get(i).getQtyOfFuse());
							acCombinerDisco.setRatedCurrent(listAcCombinerDisco.get(i).getRatedCurrent());
							acCombinerDisco.setRatedOperationalVoltage(
									listAcCombinerDisco.get(i).getRatedOperationalVoltage());
							acCombinerDisco.setType(listAcCombinerDisco.get(i).getType());
							if (listAcCombinerDisco.get(i).getIdOwner() != null) {
								acCombinerDisco.setOwner(listAcCombinerDisco.get(i).getIdOwner().getFirstName() + " "
										+ listAcCombinerDisco.get(i).getIdOwner().getLastName());
								acCombinerDisco.setIdOwner(listAcCombinerDisco.get(i).getIdOwner().getId() + "");
							}
							acCombinerDisco.setLastUpdate(listAcCombinerDisco.get(i).getLastUpdate());
							acCombinerDisco.setIsDeleted(false);
							acCombinerDisco
									.setHasCorrectionRequest(listAcCombinerDisco.get(i).getHasCorrectionRequest());
							acCombinerDisco.setEroneousContent(listAcCombinerDisco.get(i).getEroneousContent());
							acCombinerDisco
									.setEroneousContentOther(listAcCombinerDisco.get(i).getEroneousContentOther());
							acCombinerDisco.setEroneousDescription(listAcCombinerDisco.get(i).getEroneousDescription());
						}
						acCombinerDiscoList.add(i, acCombinerDisco);

					}
					return acCombinerDiscoList;

				} else if (listAcCombinerDisco2 != null && !listAcCombinerDisco2.isEmpty()) {

					List<Long> acCombinerDiscoFavID = aCDisconnectFavLibraryRepo.findFavoriteACD(idUser);
					for (int i = 0; i < listAcCombinerDisco2.size(); i++) {
						AcDisconnectModel acCombinerDisco = new AcDisconnectModel();

						if (listAcCombinerDisco2.get(i) != null) {
							acCombinerDisco.setIsShown(acCombinerDiscoFavID != null
										&& acCombinerDiscoFavID.contains(listAcCombinerDisco2.get(i).getId()));
							acCombinerDisco.setId(listAcCombinerDisco2.get(i).getId());
							acCombinerDisco.setManufacturer(listAcCombinerDisco2.get(i).getManufacturer());
							acCombinerDisco.setModel(listAcCombinerDisco2.get(i).getModel());
							acCombinerDisco.setManufacturerMappingValue(
									listAcCombinerDisco2.get(i).getManufacturerMappingValue());
							acCombinerDisco.setModelMappingValue(listAcCombinerDisco2.get(i).getModelMappingValue());
							acCombinerDisco
									.setDisconnectDeviceType(listAcCombinerDisco2.get(i).getDisconnectDeviceType());
							acCombinerDisco.setDropdownOption(listAcCombinerDisco2.get(i).getDropdownOption());
							acCombinerDisco.setMaxInput(listAcCombinerDisco2.get(i).getMaxInput());
							acCombinerDisco.setNemaRating(listAcCombinerDisco2.get(i).getNemaRating());
							acCombinerDisco.setNumberOfPoles(listAcCombinerDisco2.get(i).getNumberOfPoles());
							acCombinerDisco.setQtyOfFuse(listAcCombinerDisco2.get(i).getQtyOfFuse());
							acCombinerDisco.setRatedCurrent(listAcCombinerDisco2.get(i).getRatedCurrent());
							acCombinerDisco.setRatedOperationalVoltage(
									listAcCombinerDisco2.get(i).getRatedOperationalVoltage());
							acCombinerDisco.setType(listAcCombinerDisco2.get(i).getType());
							if (listAcCombinerDisco2.get(i).getIdOwner() != null) {
								acCombinerDisco.setOwner(listAcCombinerDisco2.get(i).getIdOwner().getFirstName() + " "
										+ listAcCombinerDisco2.get(i).getIdOwner().getLastName());
								acCombinerDisco.setIdOwner(listAcCombinerDisco2.get(i).getIdOwner().getId() + "");
							}
							acCombinerDisco.setLastUpdate(listAcCombinerDisco2.get(i).getLastUpdate());
							acCombinerDisco.setIsDeleted(false);
							acCombinerDisco
									.setHasCorrectionRequest(listAcCombinerDisco2.get(i).getHasCorrectionRequest());
							acCombinerDisco.setEroneousContent(listAcCombinerDisco2.get(i).getEroneousContent());
							acCombinerDisco
									.setEroneousContentOther(listAcCombinerDisco2.get(i).getEroneousContentOther());
							acCombinerDisco
									.setEroneousDescription(listAcCombinerDisco2.get(i).getEroneousDescription());
						}
						acCombinerDiscoList.add(i, acCombinerDisco);

					}

				}
			}
			return acCombinerDiscoList;
		} catch (Exception e) {
			e.printStackTrace();
			return acCombinerDiscoList;
		}

	}

	public ACDisconnectEntityModel addAcCombinerDisco(NewDisconnectModel acCombinerDisco, String ipUser, String timeZoneUser,
			Long idUserCo, Long idPermit, String numTab, String sessionId, String openDate) {

		ACDisconnectEntityModel aCDisconnectEntityModel = new ACDisconnectEntityModel();

		try {
			ACDisconnect newAcCombinerDisco = new ACDisconnect();
			AuthentificationEntity contractor = permitEntityRepo.findById(idPermit).orElse(new PermitEntity())
					.getAuthentificationEntity();
			AuthentificationEntity user = authentificationRepo.findById(idUserCo)
					.orElse(new AuthentificationEntity());
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
			newAcCombinerDisco.setDisconnectDeviceType(acCombinerDisco.getDeviceType());
			newAcCombinerDisco.setRatedOperationalVoltage(acCombinerDisco.getOperationalVoltage());
			newAcCombinerDisco.setNumberOfPoles(acCombinerDisco.getNumberPole());
			newAcCombinerDisco.setIdOwner(user);
			newAcCombinerDisco.setIsDeleted(false);
			newAcCombinerDisco.setLastUpdate(dString.format(new Date()));
			newAcCombinerDisco
					.setHasSuperUserEdit(user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3);

			Date addDate = new Date();
			newAcCombinerDisco.setAddDate(addDate);
			newAcCombinerDisco.setIsVerified(false);
			aCDisconnectRepo.save(newAcCombinerDisco);
			aCDisconnectEntityModel.setaCDisconnect(newAcCombinerDisco);
			
			ACDisconnectFavLibraryEntity acCombinerDiscoFav = new ACDisconnectFavLibraryEntity(contractor, newAcCombinerDisco);
			aCDisconnectFavLibraryRepo.save(acCombinerDiscoFav);
			
			mailingService.mailUpdate("AC Disconnect",
					"A new equipment of AC Disconnect " + newAcCombinerDisco.getManufacturer() + " "
							+ newAcCombinerDisco.getModel() + " has been added by " + user.getFirstName() + " "
							+ user.getLastName(),
							user.getEmail().contains("nuagetechnologies-tn.com") && !user.getEmail().contains("pm"));
			historyActivityService.recordActivity(
					idUserCo, ipUser, timeZoneUser, "libraries;Add component " + newAcCombinerDisco.getManufacturer()
							+ " " + newAcCombinerDisco.getModel() + ".;AC Disconnect",
					true, numTab, sessionId, openDate);
			return aCDisconnectEntityModel;
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add AC Disconnect.;AC Disconnect", false, numTab, sessionId, openDate);
			return aCDisconnectEntityModel;
		}
	}

	/*
	 * Edit AC Combiner / Disconnect
	 */

	public String editAcCombinerDisco(AcDisconnectModel acCombinerDisco, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {

		try {
			List<ACDisconnect> editAcCombinerDisco2 = aCDisconnectRepo.findByIdNotAndManufacturerAndModelAndIsDeleted(
					acCombinerDisco.getId(), acCombinerDisco.getManufacturer(), acCombinerDisco.getModel(), false);
			
			AuthentificationEntity firstUpdater = authentificationRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUserCo));


			if (!editAcCombinerDisco2.isEmpty()) {
				return "exist";
			} else {

				ACDisconnect editAcCombinerDisco = aCDisconnectRepo.findById(acCombinerDisco.getId())
						.orElse(new ACDisconnect());
				Date date = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				String updateDate = dateFormat.format(date);
				// manufacturer or model has change ---> update (manufacturer,model) in all the
				// project using the edited AcCombiner

				List<PermitProjectSiteInfoEntity> permitProjectSiteInfoEntitys = permitProjectSiteInfoRepo
						.findListOfProjectsUsingACCombiner(editAcCombinerDisco.getId() + ":"
								+ editAcCombinerDisco.getManufacturer() + ":" + editAcCombinerDisco.getModel());

				if (permitProjectSiteInfoEntitys != null && !permitProjectSiteInfoEntitys.isEmpty()) {
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
					}
				}
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

				editAcCombinerDisco.setDisconnectDeviceType(acCombinerDisco.getDisconnectDeviceType());
				editAcCombinerDisco.setDropdownOption(acCombinerDisco.getDropdownOption());
				editAcCombinerDisco.setMaxInput(acCombinerDisco.getMaxInput());
				editAcCombinerDisco.setNemaRating(acCombinerDisco.getNemaRating());
				editAcCombinerDisco.setNumberOfPoles(acCombinerDisco.getNumberOfPoles());
				editAcCombinerDisco.setQtyOfFuse(acCombinerDisco.getQtyOfFuse());
				editAcCombinerDisco.setRatedCurrent(acCombinerDisco.getRatedCurrent());
				editAcCombinerDisco.setRatedOperationalVoltage(acCombinerDisco.getRatedOperationalVoltage());
				editAcCombinerDisco.setType(acCombinerDisco.getType());
				editAcCombinerDisco.setLastUpdate(updateDate);
				acCombinerDisco.setHasCorrectionRequest(acCombinerDisco.getHasCorrectionRequest());
				acCombinerDisco.setEroneousContent(acCombinerDisco.getEroneousContent());
				acCombinerDisco.setEroneousContentOther(acCombinerDisco.getEroneousContentOther());
				acCombinerDisco.setEroneousDescription(acCombinerDisco.getEroneousDescription());
				
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

				aCDisconnectRepo.save(editAcCombinerDisco);

				AuthentificationEntity user = authentificationRepo.findById(idUserCo)
						.orElse(new AuthentificationEntity());

				notificationEntityService.addNewNotif(idUserCo, 0L, "Disconnect Update", "Libraries",
						"AC Disconnect Update - " + editAcCombinerDisco.getModel(),
						"The Combiner/Disconnect " + editAcCombinerDisco.getModel() + " "
								+ editAcCombinerDisco.getManufacturer() + " was updated by " + user.getFirstName() + " "
								+ user.getLastName(),
						true);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;Edit component " + editAcCombinerDisco.getManufacturer() + " "
								+ editAcCombinerDisco.getModel() + ".;AC Disconnect",
						true, numTab, sessionId, openDate);
				
				if (checkValueTypesService.isStringNotEmpty(updateNum)) {
					mailingService.mailUpdate("AC Disconnect",
							"An existing equipment of AC Disconnect " + editAcCombinerDisco.getManufacturer() + " "
									+ editAcCombinerDisco.getModel() + " has been updated " + updateNum + " time by "
									+ firstUpdater.getFirstName() + " " + firstUpdater.getLastName(),
									firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
				}

				return "success";

			}

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Edit component.;AC Disconnect", false, numTab, sessionId, openDate);
			return "error";
		}
	}

	/*
	 * Delete AC Combiner / Disconnect
	 */

	public List<ProjectForLibrariesModel> getRemoveAcCombinerDiscoConfirmation(AcDisconnectModel acCombinerDisco) {
		try {
			return aCDisconnectRepo.findProjectUsingModel(acCombinerDisco.getId() + ":"
					+ acCombinerDisco.getManufacturer() + ":" + acCombinerDisco.getModel());
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String deleteAcCombinerDisco(Long idAcd, String ipUser, String timeZoneUser, Long idUserCo,
			String numTab, String sessionId, String openDate) {
		try {

			ACDisconnect acCombinerDisco = aCDisconnectRepo.findById(idAcd).orElse(new ACDisconnect());

			// Remove AC Combiner / Disconnect From Active Projects
			List<PermitProjectSiteInfoEntity> acCombinerDiscoProject = permitProjectSiteInfoRepo
					.findByGroundLevelACCombinerDisconnectModelOrRooftopACCombinerModelOrRooftopACCombinerModelTwoOrRoofTopACDiscoAndPermitEntityIsDeleted(
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel(),
							false);

			if (acCombinerDiscoProject != null && !acCombinerDiscoProject.isEmpty()) {
				for (int i = 0; i < acCombinerDiscoProject.size(); i++) {
					if (checkValueTypesService.Equals(
							acCombinerDiscoProject.get(i).getGroundLevelACCombinerDisconnectModel(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel())) {
						acCombinerDiscoProject.get(i).setGroundLevelACCombinerDisconnectModel(REMOVED);
					}
					if (checkValueTypesService.Equals(acCombinerDiscoProject.get(i).getRooftopACCombinerModel(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel())) {
						acCombinerDiscoProject.get(i).setRooftopACCombinerModel(REMOVED);
					}
					if (checkValueTypesService.Equals(acCombinerDiscoProject.get(i).getRooftopACCombinerModelTwo(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel())) {
						acCombinerDiscoProject.get(i).setRooftopACCombinerModelTwo(REMOVED);
					}
					if (checkValueTypesService.Equals(acCombinerDiscoProject.get(i).getRoofTopACDisco(),
							acCombinerDisco.getId() + ":" + acCombinerDisco.getManufacturer() + ":"
									+ acCombinerDisco.getModel())) {
						acCombinerDiscoProject.get(i).setRoofTopACDisco(REMOVED);
					}
				}
			}

			// Remove AC Combiner / Disconnect From all the favorite Lists
			List<ACDisconnectFavLibraryEntity> acCombinerDiscoFav = aCDisconnectFavLibraryRepo
					.findByACDisconnectId(idAcd);
			if (acCombinerDiscoFav != null && !acCombinerDiscoFav.isEmpty()) {
				for (int i = 0; i < acCombinerDiscoFav.size(); i++) {
					aCDisconnectFavLibraryRepo.delete(acCombinerDiscoFav.get(i));
				}
			}

			// Remove AC Combiner / Disconnect From AC Combiner / Disconnect Library
			acCombinerDisco.setIsDeleted(true);

			aCDisconnectRepo.save(acCombinerDisco);
			historyActivityService
					.recordActivity(idUserCo, ipUser, timeZoneUser,
							"libraries;Delete component " + acCombinerDisco.getManufacturer() + " "
									+ acCombinerDisco.getModel() + ".;AC Disconnect",
							true, numTab, sessionId, openDate);
			return "Done";

		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "", false, numTab, sessionId,
					openDate);
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Delete component.;AC Disconnect", false, numTab, sessionId, openDate);
			return "error";
		}
	}

	/*
	 * Activate AC Combiner / Disconnect
	 */
	public String activateAcCombinerDisco(Long idAcd, String ipUser, String timeZoneUser, Long idUserCo,
			String numTab, String sessionId, String openDate) {
		try {

			ACDisconnect acCombinerDisco = aCDisconnectRepo.findById(idAcd).orElse(new ACDisconnect());
			Boolean exist = aCDisconnectRepo.existsByManufacturerAndModelAndIsDeleted(acCombinerDisco.getManufacturer(),
					acCombinerDisco.getModel(), false);
			if (Boolean.TRUE.equals(exist)) {
				return "exist";
			} else {
				acCombinerDisco.setIsDeleted(false);
				aCDisconnectRepo.save(acCombinerDisco);
				historyActivityService
						.recordActivity(idUserCo, ipUser, timeZoneUser,
								"libraries;Activate component " + acCombinerDisco.getManufacturer() + " "
										+ acCombinerDisco.getModel() + ".;AC Disconnect",
								true, numTab, sessionId, openDate);
				return "Done";
			}
		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser, "", false, numTab, sessionId,
					openDate);
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Activate component .;AC Disconnect", false, numTab, sessionId, openDate);
			return "error";
		}
	}

	public List<ACDisconnect> getContractorACComDisco(Long idUser, String type) {

		try {
			List<Long> acCombinerDiscoFavID;
			acCombinerDiscoFavID = aCDisconnectFavLibraryRepo.findFavoriteACD(idUser);
			return aCDisconnectRepo.findByIdInAndTypeAndIsDeletedOrderByDropdownOption(acCombinerDiscoFavID, type,
					false);

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String editACCombinerNotification(Long userID, String inverterMake, String inverterModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).orElse(new AuthentificationEntity());

			notificationEntityService.addNewNotif(userID, 0L, "AC Disconnect Update", "Libraries",
					"AC Disconnect Update - " + inverterModel, "The AC Disconnect " + inverterModel + " " + inverterMake
							+ " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addACCombinerNotification(Long userID, String inverterMake, String inverterModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).orElse(new AuthentificationEntity());

			notificationEntityService.addNewNotif(userID, 0L, "New AC Disconnect", "Libraries",
					"New AC Disconnect- " + inverterModel, "The AC Disconnect " + inverterModel + " " + inverterMake
							+ " was added by " + userCo.getFirstName() + " " + userCo.getLastName(),
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
				ACDisconnect library = aCDisconnectRepo.findById(request.getId())
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + request.getId()));
				library.setHasCorrectionRequest(true);
				library.setEroneousContent(request.getEroneousContent());
				library.setEroneousContentOther(request.getEroneousContentOther());
				library.setEroneousDescription(request.getEroneousDescription());
				aCDisconnectRepo.save(library);
				notificationEntityService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
						"Request Correction - " + library.getModel(),
						"The user " + user.getFirstName() + " " + user.getLastName() + " request correction for the "
								+ library.getType() + " " + library.getManufacturer() + " " + library.getModel(),
						true);
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	public List<ACDisconnectEntityModel> getListOfAcDisconnect() {
		try {
			List<ACDisconnectEntityModel> aCDisconnectEntityModelList = new ArrayList<>();
			List<ACDisconnect> aCDisconnectlist =  aCDisconnectRepo.findByIsDeletedOrderByManufacturerAscModelAsc(false);
			for (ACDisconnect aCDisconnect: aCDisconnectlist) 	aCDisconnectEntityModelList.add(new ACDisconnectEntityModel(aCDisconnect));
			return aCDisconnectEntityModelList;
		} catch (Exception e) {
			return new ArrayList<>();
		}

	}

	public String removeAcDiscoFavorite(Long idContractor, Long idACDisco, Long idOwner) {
		// 03-13-2019: M.A: JUnit correction
		try {
			AuthentificationEntity contractor = authentificationRepo.findById(idOwner)
					.orElse(new AuthentificationEntity());
			ACDisconnectFavLibraryEntity inverterFav = aCDisconnectFavLibraryRepo
					.findByAuthentificationEntityIdAndACDisconnectId(idOwner, idACDisco);
			aCDisconnectFavLibraryRepo.delete(inverterFav);
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;The favorite " + inverterFav.getACDisconnect().getManufacturer() + " "
							+ inverterFav.getACDisconnect().getModel() + " is removed from the user "
							+ contractor.getFirstName() + " " + contractor.getLastName() + ".;Ac Disconnect",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;Remove Inverter from Users Favorites List;Ac Disconnect", false, "", "", "");
			return "Fail";
		}
	}

	public String addAcDiscoFavorite(Long idContractor, Long idAcDisco, Long idOwner) {
		
		AuthentificationEntity contractor = new AuthentificationEntity();
		ACDisconnect acDisconnect = new ACDisconnect();
		// 03-13-2019: M.A: JUnit correction
		try {
			Boolean isFav = aCDisconnectFavLibraryRepo.existsByAuthentificationEntityIdAndACDisconnectId(idOwner, idAcDisco);
			if (Boolean.FALSE.equals(isFav)) {
				contractor = authentificationRepo.findById(idOwner).orElse(new AuthentificationEntity());
				acDisconnect = aCDisconnectRepo.findById(idAcDisco).orElse(new ACDisconnect());
				ACDisconnectFavLibraryEntity inverterFav = new ACDisconnectFavLibraryEntity(contractor, acDisconnect);
				aCDisconnectFavLibraryRepo.save(inverterFav);
				String activityText = recordActivityTextAddFav(acDisconnect.getManufacturer() , acDisconnect.getModel(), contractor.getFirstName(), contractor.getLastName());
				historicActivity.recordActivity(idContractor, "", "",
						activityText, true, "", "", "");
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;Add ACDisconnect to Users Favorites List;ACDisconnect", false, "", "", "");
			return "Fail";
		}
	}
	
	public Page<AcDisconnectModel> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValueTypesService.isStringNotEmpty(request.getManufacturer()) ? request.getManufacturer().split(" ") : null;
			String[] model = checkValueTypesService.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			String ratedCurrent = checkValueTypesService.isStringNotEmpty(request.getRatedCurrent()) ? request.getRatedCurrent().trim() : null;
			String deviceType = checkValueTypesService.isStringNotEmpty(request.getDeviceType()) ? request.getDeviceType().trim() : null;
			if (manufacturer == null && model == null && ratedCurrent == null && deviceType == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<ACDisconnect> p = aCDisconnectRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());
			} else {
				Page<ACDisconnect> p = aCDisconnectRepo.findAll(filter(manufacturer, model, ratedCurrent, deviceType, request.getIsFavorite(),
						request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser(), request.getIsDeleted());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Page<AcDisconnectModel> convertDto(Page<ACDisconnect> page, Pageable pageable, Long idUser, Boolean isdelete) {
		try {
			List<Long> favIds = aCDisconnectFavLibraryRepo.findFavoriteACD(idUser);
			return new PageImpl<>(
					page.stream().map(c -> new AcDisconnectModel(c.getId(), c.getManufacturer(), c.getModel(), c.getDisconnectDeviceType(),
							c.getRatedOperationalVoltage(), c.getRatedCurrent(), c.getNumberOfPoles(),
							c.getNemaRating(), c.getMaxInput(), c.getQtyOfFuse(), c.getType(),
							c.getDropdownOption(), c.getLastUpdate(), isdelete, c.getIdOwner().getFirstName() + " " + c.getIdOwner().getLastName(), 
							favIds.indexOf(c.getId()) != -1, c.getIdOwner().getId()+"",
							c.getEroneousContent(), c.getEroneousContentOther(), c.getEroneousDescription(), 
							c.getHasCorrectionRequest(), c.getManufacturerMappingValue(), c.getModelMappingValue(),
							c.getFirstUpdater() != null ? new UsersEntityResult(c.getFirstUpdater().getId(), c.getFirstUpdater().getFirstName(), c.getFirstUpdater().getLastName()) : null,
							c.getSecondUpdater() != null ? new UsersEntityResult(c.getSecondUpdater().getId(), c.getSecondUpdater().getFirstName(), c.getSecondUpdater().getLastName()) : null,
							c.getThirdUpdater() != null ? new UsersEntityResult(c.getThirdUpdater().getId(), c.getThirdUpdater().getFirstName(), c.getThirdUpdater().getLastName()) : null,
							c.getVerifiedBy() != null ? new UsersEntityResult(c.getVerifiedBy().getId(), c.getVerifiedBy().getFirstName(), c.getVerifiedBy().getLastName()) : null,
							c.getIsVerified(), c.getDateVerification()))
					.collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<ACDisconnect> filter(String[] makes, String[] models, String ratedCurrent, String deviceType, Boolean favorite, Boolean deleted, Long idUser) {

		return new Specification<ACDisconnect>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<ACDisconnect> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
				Predicate predicateDeviceType = deviceType != null ? cb.equal(cb.lower(root.get("disconnectDeviceType")), deviceType.toLowerCase()) : null;
				Predicate endPredicateMake = makes != null ? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()])) : null;
				Predicate endPredicateModel = models != null ? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()])) : null;
				
				List<Predicate> list = Arrays.asList(predicateDeleted, predicateRatedCurrent, predicateDeviceType, endPredicateMake, endPredicateModel);
				if(Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : aCDisconnectFavLibraryRepo.findFavoriteACD(idUser)) {
					    inClause.value(id);
					}
					list = Arrays.asList(predicateDeleted, predicateRatedCurrent, predicateDeviceType, endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	public String[] getACDisconnectType(String acDisconnect) {
		String[] acDisconnecTab = new String[2];
		try {
			if (acDisconnect != null && acDisconnect.contains(":")) {
				ACDisconnect acDisco = aCDisconnectRepo.findById(Long.valueOf(acDisconnect.split(":")[0]))
						.orElse(new ACDisconnect());
				if (acDisco != null) {
					acDisconnecTab[0] = acDisco.getDisconnectDeviceType();
					acDisconnecTab[1] = acDisco.getRatedCurrent();
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return acDisconnecTab;
	}
	
	public String recordActivityTextAddFav(String make, String model, String firstName, String lastName) {
		return "libraries;The favorite " + make + " " + model + " is added to the user " + firstName + " " + lastName + ".;AC Disconnect";
	}
	

}
