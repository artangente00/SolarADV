package com.PlayGroundAdv.Solar.service.libraries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerFavoritEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.AddConverterModelRequest;
import com.PlayGroundAdv.Solar.model.ConverterCorrectionModel;
import com.PlayGroundAdv.Solar.model.ConverterFavModelResult;
import com.PlayGroundAdv.Solar.model.ConverterListModel;
import com.PlayGroundAdv.Solar.model.ConverterModel;
import com.PlayGroundAdv.Solar.model.DCOptimizerFavoritEntityModel;
import com.PlayGroundAdv.Solar.model.ProjectForConvertModelResult;
import com.PlayGroundAdv.Solar.model.SearchConverterResult;
import com.PlayGroundAdv.Solar.model.SearchConvertersRequest;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcOptimizerFavoritRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ConverterService {

	final HistoryActivityService historyActivityService;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final ConvertersRepository converterRepo;
	final ModelMapper modelMapper;
	final AuthenticationRepository authentificationRepo;
	final DcOptimizerFavoritRepository dCOptimizerFavoritEntiyRepo;
	final PermitArraysRepository permitArraysEntityRepo;
	final PermitRepository permitEntityRepo;
	final EquipmentUpdate mailingService;

	private DateFormat dFormat = new SimpleDateFormat("MM-dd-yyyy");
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String ERROR = "error";

	public ConverterService(HistoryActivityService historyActivityService,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			ConvertersRepository converterRepo, ModelMapper modelMapper, AuthenticationRepository authentificationRepo,
			DcOptimizerFavoritRepository dCOptimizerFavoritEntiyRepo, PermitArraysRepository permitArraysEntityRepo,
			PermitRepository permitEntityRepo, EquipmentUpdate mailingService) {
		super();
		this.historyActivityService = historyActivityService;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.converterRepo = converterRepo;
		this.modelMapper = modelMapper;
		this.authentificationRepo = authentificationRepo;
		this.dCOptimizerFavoritEntiyRepo = dCOptimizerFavoritEntiyRepo;
		this.permitArraysEntityRepo = permitArraysEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.mailingService = mailingService;
	}

	public String sendCorrectionconverterRequest(ConverterCorrectionModel component) {

		try {
			if (component.getId() != 0) {
				DCOptimizerEntity library = converterRepo.findById(component.getId()).orElse(new DCOptimizerEntity());
				AuthentificationEntity user = authentificationRepo.findById(component.getIdUser())
						.orElse(new AuthentificationEntity());
				converterRepo.updateCorrectionRequest(true, component.getEroneousContent(),
						component.getEroneousContentOther(), component.getEroneousDescription(), component.getId());

				notificationEntityService.addNewNotif(component.getIdUser(), 0L, "Request Correction", "Libraries",
						"Request Correction - " + library.getModel(),
						"The user " + user.getFirstName() + " " + user.getLastName()
								+ " request correction for the DC-DC Converter " + library.getManufacturer() + " "
								+ library.getModel(),
						true);
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	// Add & Remove Favorite Converter
	public String FavoritConv(Long idConverter, Long idUser, Boolean isFav, String ipAdress, String timeZone,
			Long idOwner, String numTab, String sessionId, String openDate) {

		try {
			AuthentificationEntity owner = authentificationRepo.findById(idOwner).orElse(new AuthentificationEntity());
			if (checkValueTypesService.Equals(isFav, true)) {
				// in this case favorite existe so delete from the data base
				Boolean removed = removeModelFromFavoriteList(idConverter, idUser, owner, ipAdress, timeZone, idOwner);
				if (Boolean.FALSE.equals(removed))
					return "converter does not exist";

			} else {
				// in this case favorite doesnt existe sor add it
				Boolean added = addModelFromFavoriteList(idConverter, idUser, owner, ipAdress, timeZone, idOwner);
				if (Boolean.FALSE.equals(added))
					return "converter does not exist";
			}

			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Update DC Optimizers Favorites List;DC Optimizers", false, numTab, sessionId, openDate);
			return ERROR;
		}

	}

	public Boolean removeModelFromFavoriteList(Long idConverter, Long idUser, AuthentificationEntity owner,
			String ipAdress, String timeZone, Long idOwner) {
		try {

			DCOptimizerFavoritEntity conFav = dCOptimizerFavoritEntiyRepo.findFirstByOptimizerIdAndUserId(idConverter,
					idOwner);
			dCOptimizerFavoritEntiyRepo.delete(conFav);
			historyActivityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;The favorite " + conFav.getOptimizer().getManufacturer() + " "
							+ conFav.getOptimizer().getModel() + " is removed from the user " + owner.getFirstName()
							+ " " + owner.getLastName() + ".;DC Optimizers",
					true, "", "", "");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Remove DC Optimizers from Users Favorites List;DC Optimizers", false, "", "", "");
			return false;
		}
	}

	public Boolean addModelFromFavoriteList(Long idConverter, Long idUser, AuthentificationEntity owner,
			String ipAdress, String timeZone, Long idOwner) {
		try {
			AuthentificationEntity user = authentificationRepo.findById(idOwner).orElse(new AuthentificationEntity());
			DCOptimizerEntity convert = converterRepo.findById(idConverter).orElse(new DCOptimizerEntity());
			DCOptimizerFavoritEntity convFav = new DCOptimizerFavoritEntity(convert, user);
			historyActivityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;The favorite " + convFav.getOptimizer().getManufacturer() + " "
							+ convFav.getOptimizer().getModel() + " is added to the user " + owner.getFirstName() + " "
							+ owner.getLastName() + ".;DC Optimizers",
					true, "", "", "");
			dCOptimizerFavoritEntiyRepo.save(convFav);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Add DC Optimizers to Users Favorites List;DC Optimizers", false, "", "", "");
			return false;
		}
	}

	/*
	 * Edit DC/DC Converter Favorite for Other Users
	 */
	public List<UsersEntityResult> getUsersForFavList(Long idConverter, Long userID) {
		List<UsersEntityResult> usersList = new ArrayList<>();
		try {
			List<DCOptimizerFavoritEntity> optimizerFav = dCOptimizerFavoritEntiyRepo.findByOptimizerId(idConverter);
			if (optimizerFav != null && !optimizerFav.isEmpty()) {
				List<Long> usersFavID = new ArrayList<>();
				for (int i = 0; i < optimizerFav.size(); i++) {
					if (optimizerFav.get(i) != null && optimizerFav.get(i).getUser() != null) {
						usersFavID.add(optimizerFav.get(i).getUser().getId());
					}

				}

				usersList = authentificationRepo.findUserHaveNotFav(usersFavID, false, true, userID);
				return usersList;

			} else {
				usersList = authentificationRepo.findUserHaveNotFav(null, false, true, userID);
				return usersList;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return usersList;
		}

	}

	public String editUsersFavoriteList(Long idConverter, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {
		try {

			DCOptimizerEntity optimizer = converterRepo.findById(idConverter).orElse(new DCOptimizerEntity());

			for (int i = 0; i < listUsers.length; i++) {

				AuthentificationEntity user = authentificationRepo.findById(listUsers[i])
						.orElse(new AuthentificationEntity());

				DCOptimizerFavoritEntity junctionBoxFav = new DCOptimizerFavoritEntity(optimizer, user);
				dCOptimizerFavoritEntiyRepo.save(junctionBoxFav);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + junctionBoxFav.getOptimizer().getManufacturer() + " "
								+ junctionBoxFav.getOptimizer().getModel() + " is added to the user "
								+ user.getFirstName() + " " + user.getLastName() + ".;DC Optimizers",
						true, numTab, sessionId, openDate);
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add DC Optimizers to Users Favorites List;DC Optimizers", false, numTab, sessionId,
					openDate);
			return ERROR;
		}
	}

	public String editConverters(ConverterModel converterEditModelRequest, Long idUser, String ipAdress,
			String timeZone, String numTab, String sessionId, String openDate) {
		try {
			// edit Converters Field

			DCOptimizerEntity converter = converterRepo.findById(converterEditModelRequest.getId())
					.orElse(new DCOptimizerEntity());
			
			AuthentificationEntity firstUpdater = authentificationRepo.findById(idUser).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +idUser));


			// test if manufacturer or model change
			if (checkValueTypesService.NotEquals(converter.getManufacturer(),
					converterEditModelRequest.getManufacturer())
					|| checkValueTypesService.NotEquals(converter.getModel(), converterEditModelRequest.getModel())) {
				List<DCOptimizerEntity> listDCOpt = converterRepo.findByManufacturerAndModelAndIsDeletedNotIn(
						converterEditModelRequest.getManufacturer().trim().toLowerCase(), converterEditModelRequest.getModel().trim().toLowerCase(), false, converterEditModelRequest.getId());
				if (listDCOpt != null && !listDCOpt.isEmpty()) {
					return "exist";
				}
			}

			// end
			converter.setManufacturer(converterEditModelRequest.getManufacturer());
			converter.setModel(converterEditModelRequest.getModel());

			if (checkValueTypesService.NotEquals(converterEditModelRequest.getManufacturerMappingValue(), "")) {
				converter.setManufacturerMappingValue(converterEditModelRequest.getManufacturerMappingValue());
			} else
				converter.setManufacturerMappingValue(converterEditModelRequest.getManufacturer());

			if (checkValueTypesService.NotEquals(converterEditModelRequest.getModelMappingValue(), "")) {
				converter.setModelMappingValue(converterEditModelRequest.getModelMappingValue());
			} else
				converter.setModelMappingValue(converterEditModelRequest.getModel());

			converter.setWeight(converterEditModelRequest.getWeight());
			converter.setRatedOutputIsc(converterEditModelRequest.getRatedOutputIsc());
			converter.setMaxInputVoltage(converterEditModelRequest.getMaxInputVoltage());
			converter.setMaxSeriesFuseRating(converterEditModelRequest.getMaxSeriesFuseRating());
			converter.setPhase(converterEditModelRequest.getPhase());
			converter.setPvModulePower(converterEditModelRequest.getPvModulePower());
			converter.setMinString(converterEditModelRequest.getMinString());
			converter.setMaxString(converterEditModelRequest.getMaxString());
			converter.setMaxPowerString(converterEditModelRequest.getMaxPowerString());
			converter.setMaxOutputVoltage(converterEditModelRequest.getMaxOutputVoltage());

			converter.setHasCorrectionRequest(converterEditModelRequest.getHasCorrectionRequest());
			converter.setEroneousContent(converterEditModelRequest.getEroneousContent());
			converter.setEroneousContentOther(converterEditModelRequest.getEroneousContentOther());
			converter.setEroneousDescription(converterEditModelRequest.getEroneousDescription());
			converter.setQtyModuleOpt(converterEditModelRequest.getQtyModuleOpt());
			converter.setType(converterEditModelRequest.getType());
			converter.setAltersVoltage(converterEditModelRequest.getAltersVoltage());
			String lastUpDate = dFormat.format(new Date());
			converter.setLastUpDate(lastUpDate);

			// test if the modification has been done by a super user
			Long roleId = authentificationRepo.findById(idUser).orElse(new AuthentificationEntity()).getRoleEntity()
					.getId();
			if (roleId != null && roleId == 1) {
				converter.setHasSuperUserEdit(true);
			}
			
			converter.setIsVerified(false);
			String updateNum = "";
			if(converter.getFirstUpdater() == null) {
				updateNum = "1st";	
				converter.setFirstUpdater(firstUpdater);
			}else if(converter.getSecondUpdater() == null) {
				updateNum = "2nd";				
				converter.setSecondUpdater(firstUpdater);
			}else if(converter.getThirdUpdater() == null) {
				updateNum = "3rd";				
				converter.setThirdUpdater(firstUpdater);
			}	

			converterRepo.save(converter);
			historyActivityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Edit component " + converterEditModelRequest.getManufacturer() + " "
							+ converterEditModelRequest.getModel() + ".;DC Optimizers",
					true, numTab, sessionId, openDate);
			
			if (checkValueTypesService.isStringNotEmpty(updateNum)) {
				mailingService.mailUpdate("DC/DC Converter or ML RSD",
						"An existing equipment of DC/DC Converter or ML RSD "
								+ converterEditModelRequest.getManufacturer() + " "
								+ converterEditModelRequest.getModel() + " has been updated " + updateNum + " time by "
								+ firstUpdater.getFirstName() + " " + firstUpdater.getLastName(),
								firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
			}

			return SUCCESS;
		} catch (Exception e) {
			historyActivityService.recordActivity(idUser, ipAdress, timeZone, "libraries;Edit component.;DC Optimizers",
					false, numTab, sessionId, openDate);
			e.printStackTrace();
			return ERROR;
		}
	}

	public boolean testConverterFav(Long idUser, Long idPermit, String testMan) {
		try {
			int test = 0;
			Long roleId = authentificationRepo.findById(idUser).orElse(new AuthentificationEntity()).getRoleEntity()
					.getId();

			List<ConverterFavModelResult> converters;

			if (roleId == 1 || roleId == 3) {
				converters = dCOptimizerFavoritEntiyRepo.getConverterAllFavModel(idPermit);
			} else {
				converters = dCOptimizerFavoritEntiyRepo.getConverterFavModel(idUser);
			}

			for (int i = 0; i < converters.size(); i++) {
				if (converters.get(i) != null && checkValueTypesService.Equals(testMan,
						converters.get(i).getManufacturer() + ":" + converters.get(i).getModel())) {
					test = 1;
				}
			}
			return test != 1;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public LinkedHashMap<Long, String> addConverter(AddConverterModelRequest converterModel, Long idUser,
			String ipAdress, String timeZone, Long idPermit, String numTab, String sessionId, String openDate) {
		try {

			List<DCOptimizerEntity> listConverters = converterRepo.findByManufacturerAndModelAndIsDeleted(
					converterModel.getManufacturer().trim().toLowerCase(), converterModel.getModel().trim().toLowerCase(), false);
			if (listConverters != null && !listConverters.isEmpty()) {
				historyActivityService.recordActivity(
						idUser, ipAdress, timeZone, "Add Existing Converter manufacturer : "
								+ converterModel.getManufacturer() + " Model : " + converterModel.getModel(),
						false, numTab, sessionId, openDate);
				LinkedHashMap<Long, String> ret1 = new LinkedHashMap<>();
				ret1.put(0L, "exist");
				return ret1;
			}

			// if Model existe send back list manufacturer
			List<DCOptimizerEntity> optimizers = converterRepo.findByModelAndIsDeleted(converterModel.getModel().trim().toLowerCase(),
					false);
			if (optimizers != null && !optimizers.isEmpty()) {
				LinkedHashMap<Long, String> manufacturers = new LinkedHashMap<>();

				for (DCOptimizerEntity optimizer : optimizers) {
					// Check if converter favorite
					if (optimizer != null) {
						DCOptimizerFavoritEntity favOptim = dCOptimizerFavoritEntiyRepo
								.findFirstByOptimizerIdAndUserId(optimizer.getId(), idUser);
						if (favOptim != null) {
							manufacturers.put(optimizer.getId(),
									optimizer.getManufacturer() + "::true::" + optimizer.getId());
						} else {
							manufacturers.put(optimizer.getId(),
									optimizer.getManufacturer() + "::false::" + optimizer.getId());
						}
					}
				}
				manufacturers.put(0L, "manufacturerList");
				return manufacturers;
			} else {
				// in this case we will add the converters
				DCOptimizerEntity converter = new DCOptimizerEntity();

				converter.setModel(converterModel.getModel());
				converter.setManufacturer(converterModel.getManufacturer());

				if (checkValueTypesService.NotEquals(converterModel.getManufacturerMappingValue(), "")) {
					converter.setManufacturerMappingValue(converterModel.getManufacturerMappingValue());
				} else
					converter.setManufacturerMappingValue(converterModel.getManufacturer());

				if (checkValueTypesService.NotEquals(converterModel.getModelMappingValue(), "")) {
					converter.setModelMappingValue(converterModel.getModelMappingValue());
				} else
					converter.setModelMappingValue(converterModel.getModel());

				if (checkValueTypesService.Equals(converterModel.getPhaseBox(), true)) {
					converter.setPhase("Single (240V)");
				} else {

					if (checkValueTypesService.NotEquals(converterModel.getPhaseSelect(), "Other")) {
						converter.setPhase(converterModel.getPhaseSelect());
					}
					if (checkValueTypesService.Equals(converterModel.getPhaseSelect(), "Other")) {
						converter.setPhase(converterModel.getPhaseSelectOther());
					}

				}

				converter.setIsDeleted(false);
				String lastUpDate = dFormat.format(new Date());
				converter.setLastUpDate(lastUpDate);
				converter.setType(converterModel.getType());
				converter.setIsVerified(false);
				converterRepo.save(converter);

				// make this new converters like a favorite for the user connected
				AuthentificationEntity user = authentificationRepo.findById(idUser)
						.orElse(new AuthentificationEntity());
				converter.setUser(user);
				if (user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3) {
					converter.setHasSuperUserEdit(true);
				} else {
					converter.setHasSuperUserEdit(false);
				}
				Date addDate = new Date();
				converter.setAddDate(addDate);

				AuthentificationEntity contractor = permitEntityRepo.findById(idPermit).orElse(new PermitEntity())
						.getAuthentificationEntity();
				DCOptimizerFavoritEntity convertFav = new DCOptimizerFavoritEntity(converter, contractor);
				convertFav.setUser(contractor);
				convertFav.setOptimizer(converter);
				dCOptimizerFavoritEntiyRepo.save(convertFav);
				mailingService.mailUpdate("DC/DC Converter",
						"A new equipment of DC/DC Converter " + converterModel.getManufacturer() + " "
								+ converterModel.getModel() + " has been added by " + user.getFirstName() + " "
								+ user.getLastName(),
								user.getEmail().contains("nuagetechnologies-tn.com") && !user.getEmail().contains("pm"));
				historyActivityService
						.recordActivity(idUser, ipAdress, timeZone,
								"libraries;Add component " + converterModel.getManufacturer() + " "
										+ converterModel.getModel() + ".;DC Optimizers",
								true, numTab, sessionId, openDate);
				LinkedHashMap<Long, String> ret1 = new LinkedHashMap<Long, String>();
				ret1.put(0L, SUCCESS);
				ret1.put(1L, converter.getId() + "");

				return ret1;

			}

		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUser, ipAdress, timeZone, "libraries;Add component.;DC Optimizers",
					false, numTab, sessionId, openDate);
			LinkedHashMap<Long, String> ret1 = new LinkedHashMap<Long, String>();
			ret1.put(0L, ERROR);
			return ret1;
		}
	}

	public DCOptimizerFavoritEntityModel stillAddNewConverter(AddConverterModelRequest converterModel, Long idUser,
			String ipAdress, String timeZone, String numTab, String sessionId, String openDate) {
		try {
			// in this case we will add the converters
			DCOptimizerEntity converter = new DCOptimizerEntity();

			converter.setModel(converterModel.getModel());
			converter.setManufacturer(converterModel.getManufacturer());

			if (checkValueTypesService.NotEquals(converterModel.getManufacturerMappingValue(), ""))
				converter.setManufacturerMappingValue(converterModel.getManufacturerMappingValue());
			else
				converter.setManufacturerMappingValue(converterModel.getManufacturer());

			if (checkValueTypesService.NotEquals(converterModel.getModelMappingValue(), ""))
				converter.setModelMappingValue(converterModel.getModelMappingValue());
			else
				converter.setModelMappingValue(converterModel.getModel());

			if (checkValueTypesService.Equals(converterModel.getPhaseBox(), true)) {
				converter.setPhase("Single (240V)");
			} else {

				if (checkValueTypesService.NotEquals(converterModel.getPhaseSelect(), "Other")) {
					converter.setPhase(converterModel.getPhaseSelect());
				}
				if (checkValueTypesService.Equals(converterModel.getPhaseSelect(), "Other")) {
					converter.setPhase(converterModel.getPhaseSelectOther());
				}

			}

			converter.setHasCorrectionRequest(false);
			converter.setIsDeleted(false);
			String lastUpDate = dFormat.format(new Date());
			converter.setLastUpDate(lastUpDate);
			converter.setType(converterModel.getType());
			converterRepo.save(converter);

			// make this new converters like a favorite for the user connected
			AuthentificationEntity user = authentificationRepo.findById(idUser).orElse(new AuthentificationEntity());
			converter.setUser(user);
			converter.setHasSuperUserEdit(user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3);
			Date addDate = new Date();
			converter.setAddDate(addDate);
			DCOptimizerFavoritEntity convertFav = new DCOptimizerFavoritEntity(converter, user);
			convertFav.setUser(user);
			convertFav.setOptimizer(converter);
			dCOptimizerFavoritEntiyRepo.save(convertFav);

			historyActivityService
					.recordActivity(
							idUser, ipAdress, timeZone, "libraries;Add component " + converterModel.getManufacturer()
									+ " " + converterModel.getModel() + ".;DC Optimizers",
							true, numTab, sessionId, openDate);
			return new DCOptimizerFavoritEntityModel(convertFav.getId(), convertFav.getOptimizer(), convertFav.getUser());
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUser, ipAdress, timeZone, "libraries;Add component.;DC Optimizers",
					false, numTab, sessionId, openDate);
			return null;
		}

	}

	public List<ProjectForConvertModelResult> deleteConverter(Long id) {
		try {
			return converterRepo.findProjectUsingModel(id);

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public String stilldeleteConverter(Long idConverter, Long idUser, String ipAdress, String timeZone, String numTab,
			String sessionId, String openDate) {

		try {
			DCOptimizerEntity converter = converterRepo.findById(idConverter).orElse(new DCOptimizerEntity());
			converter.setIsDeleted(true);
			List<DCOptimizerFavoritEntity> converterfav = dCOptimizerFavoritEntiyRepo.findByOptimizerId(idConverter);
			if (converterfav != null && !converterfav.isEmpty()) {
				for (int i = 0; i < converterfav.size(); i++) {
					dCOptimizerFavoritEntiyRepo.delete(converterfav.get(i));
				}
			}
			String lastUpDate = dFormat.format(new Date());
			converter.setLastUpDate(lastUpDate);
			converterRepo.save(converter);
			// remove deleted converter from activated project
			historyActivityService
					.recordActivity(
							idUser, ipAdress, timeZone, "libraries;Delete component " + converter.getManufacturer()
									+ " " + converter.getModel() + ".;DC Optimizers",
							true, numTab, sessionId, openDate);

			return SUCCESS;
		} catch (Exception e) {
			historyActivityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Delete component Id" + idConverter + ".;DC Optimizers", true, numTab, sessionId,
					openDate);
			e.printStackTrace();
			return ERROR;
		}

	}

	public String activateConverter(Long idConverter, Long idUser, String ipAdress, String timeZone, String numTab,
			String sessionId, String openDate) {

		try {
			DCOptimizerEntity converter = converterRepo.findById(idConverter).orElse(new DCOptimizerEntity());
			List<DCOptimizerEntity> listConverter = converterRepo
					.findByManufacturerAndModelAndIsDeleted(converter.getManufacturer(), converter.getModel(), false);
			if (listConverter != null && !listConverter.isEmpty()) {
				return "exist";
			} else {
				converter.setIsDeleted(false);
				String lastUpDate = dFormat.format(new Date());
				converter.setLastUpDate(lastUpDate);
				converterRepo.save(converter);

				historyActivityService
						.recordActivity(
								idUser, ipAdress, timeZone, "libraries;Activate component "
										+ converter.getManufacturer() + " " + converter.getModel() + ".;DC Optimizers",
								true, numTab, sessionId, openDate);
				return SUCCESS;
			}
		} catch (Exception e) {

			historyActivityService.recordActivity(idUser, ipAdress, timeZone,
					"libraries;Activate component ID" + idConverter + ".;DC Optimizers", false, numTab, sessionId,
					openDate);
			e.printStackTrace();
			return ERROR;
		}

	}

	public String editOptimizerNotification(Long userID, String moduleMake, String moduleModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).orElse(new AuthentificationEntity());
			notificationEntityService.addNewNotif(userID, 0L, "DC-DC Converter Update", "Libraries",
					"DC-DC Converter Update - " + moduleModel, "The DC-DC Converter " + moduleModel + " " + moduleMake
							+ " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;

		}
	}

	public String addOptimizerNotification(Long userID, String moduleMake, String moduleModel) {
		try {
			AuthentificationEntity userCo = authentificationRepo.findById(userID).orElse(new AuthentificationEntity());
			notificationEntityService.addNewNotif(userID, 0L, "New DC-DC Converter", "Libraries",
					"New DC-DC Converter - " + moduleModel, "The DC-DC Converter " + moduleModel + " " + moduleMake
							+ " was added by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;

		}
	}

	// M.A 12-02 converter search
	public ConverterListModel filterConverter(SearchConvertersRequest searchConvertersRequest, Long idUser,
			Integer page, Integer size) {

		try {
			ConverterListModel converterList = new ConverterListModel();
			List<SearchConverterResult> converterFavList = new ArrayList<>();

			String manufacturerString = checkValueTypesService.isStringNotEmpty(
					searchConvertersRequest.getManufacturer()) ? searchConvertersRequest.getManufacturer().trim() : "";
			String modelString = checkValueTypesService.isStringNotEmpty(searchConvertersRequest.getModel())
					? searchConvertersRequest.getModel().trim()
					: "";

			String[] manufacturer = manufacturerString.split(" ");
			String[] model = modelString.split(" ");

			Boolean favorite = searchConvertersRequest.getFavorite();
			Boolean deleted = searchConvertersRequest.getIsDeleted();
			List<Long> converterFavID = getConverterFavorite(idUser);
			List<Long> getConverterFavID = favorite.equals(true) ? converterFavID : null;
			List<Long> converterID = new ArrayList<>();
			if (favorite.equals(true) && getConverterFavID.isEmpty()) {
				converterList.setConverterList(null);
				converterList.setTotalPages(0);
				return converterList;

			} else if (manufacturer != null && manufacturer.length > 1 && model != null && model.length > 1) {

				for (int i = 0; i < manufacturer.length; i++) {
					for (int j = 0; j < model.length; j++) {
						List<Long> converterIDReq = converterRepo.filterConverterListModel(manufacturer[i].trim(),
								model[j].trim(), converterID != null && !converterID.isEmpty() ? converterID : null,
								deleted, getConverterFavID);
						converterID.addAll(converterIDReq);
					}
				}

			} else if (manufacturer != null && manufacturer.length > 1) {

				for (int i = 0; i < manufacturer.length; i++) {
					List<Long> converterIDReq = converterRepo.filterConverterListModel(manufacturer[i].trim(),
							modelString, converterID != null && !converterID.isEmpty() ? converterID : null, deleted,
							getConverterFavID);
					converterID.addAll(converterIDReq);

				}

			} else if (model != null && model.length > 1) {

				for (int i = 0; i < model.length; i++) {
					List<Long> converterIDReq = converterRepo.filterConverterListModel(manufacturerString,
							model[i].trim(), converterID != null && !converterID.isEmpty() ? converterID : null,
							deleted, getConverterFavID);
					converterID.addAll(converterIDReq);

				}

			} else {
				converterID = converterRepo.filterConverterListModel(manufacturerString, modelString,
						converterID != null && !converterID.isEmpty() ? converterID : null, deleted, getConverterFavID);
			}

			Pageable pageable = PageRequest.of(page, size, Sort.by("manufacturer").and(Sort.by("model")));
			Page<DCOptimizerEntity> converters = converterRepo.findByIdIn(converterID, pageable);

			for (DCOptimizerEntity mod : converters.getContent()) {
				SearchConverterResult converterDto = modelMapper.map(mod, SearchConverterResult.class);
				UsersEntityResult owner = new UsersEntityResult();
				owner.setId(mod.getUser().getId());
				owner.setFirstName(mod.getUser().getFirstName());
				owner.setLastName(mod.getUser().getLastName());
				converterDto.setUser(owner);
				converterDto.setFirstUpdater(mod.getFirstUpdater() != null ? new UsersEntityResult(mod.getFirstUpdater().getId() ,mod.getFirstUpdater().getFirstName(),mod.getFirstUpdater().getLastName()) : null);
				converterDto.setSecondUpdater(mod.getSecondUpdater() != null ? new UsersEntityResult(mod.getSecondUpdater().getId(),mod.getSecondUpdater().getFirstName(),mod.getSecondUpdater().getLastName()): null);
				converterDto.setThirdUpdater(mod.getThirdUpdater() != null ? new UsersEntityResult(mod.getThirdUpdater().getId(),mod.getThirdUpdater().getFirstName(),mod.getThirdUpdater().getLastName()): null);
				converterDto.setVerifiedBy(mod.getVerifiedBy() != null ? new UsersEntityResult(mod.getVerifiedBy().getId(),mod.getVerifiedBy().getFirstName(),mod.getVerifiedBy().getLastName()): null);

				converterDto.setIsFavorit(converterFavID.indexOf(mod.getId()) != -1);
				converterFavList.add(converterDto);
			}
			converterList.setConverterList(converterFavList);
			converterList.setTotalPages(converters.getTotalPages());
			converterList.setTotalRecords(converters.getTotalElements());
			
			return converterList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// M.a get the id of the faviorie Converter
	public List<Long> getConverterFavorite(Long idContractor) {
		List<Long> converterFavID = new ArrayList<>();

		try {
			List<DCOptimizerFavoritEntity> converterFav = dCOptimizerFavoritEntiyRepo.findByUserId(idContractor);
			if (converterFav != null && !converterFav.isEmpty()) {
				for (int i = 0; i < converterFav.size(); i++) {
					if (converterFav.get(i) != null && converterFav.get(i).getOptimizer() != null) {
						converterFavID.add(converterFav.get(i).getOptimizer().getId());
					}
				}
			}
			return converterFavID;

		} catch (Exception e) {
			e.printStackTrace();
			return converterFavID;
		}

	}
}
