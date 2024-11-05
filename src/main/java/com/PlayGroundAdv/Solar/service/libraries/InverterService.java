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
import com.PlayGroundAdv.Solar.entity.InverterLibraryEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.VoltageConfigurationEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.model.libraries.FavoriteListDto;
import com.PlayGroundAdv.Solar.model.libraries.InverterResult;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.SiteSurveyRepository;
import com.PlayGroundAdv.Solar.repositories.VoltageConfigurationRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InvertersFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class InverterService {

	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValue;
	final InverterRepository inverterRepo;
	final AuthenticationRepository userRepo;
	final InvertersFavoritesRepository invertersFavRepo;
	final PermitArraysRepository arraysRepo;
	final PermitRepository permitRepo;
	final VoltageConfigurationRepository voltageConfRepo;
	final SiteSurveyRepository siteSurveyRepo;
	final EquipmentUpdate mailingService;
	
	public InverterService(HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValue,
			InverterRepository inverterRepo, AuthenticationRepository userRepo,
			InvertersFavoritesRepository invertersFavRepo, PermitArraysRepository arraysRepo,
			PermitRepository permitRepo, VoltageConfigurationRepository voltageConfRepo,
			SiteSurveyRepository siteSurveyRepo, EquipmentUpdate mailingService) {
		super();
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.checkValue = checkValue;
		this.inverterRepo = inverterRepo;
		this.userRepo = userRepo;
		this.invertersFavRepo = invertersFavRepo;
		this.arraysRepo = arraysRepo;
		this.permitRepo = permitRepo;
		this.voltageConfRepo = voltageConfRepo;
		this.siteSurveyRepo = siteSurveyRepo;
		this.mailingService = mailingService;
	}

	final static SimpleDateFormat Formatted_DATE = new SimpleDateFormat("MM/dd/yyyy");

	public Page<InverterResult> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("make").and(Sort.by("model")));
			String[] manufacturer = checkValue.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValue.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			if (manufacturer == null && model == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<Inverters> p = inverterRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser());
			} else {
				Page<Inverters> p = inverterRepo.findAll(filter(manufacturer, model, request.getIsFavorite(),
						request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<InverterResult> convertDto(Page<Inverters> page, Pageable pageable, Long userId) {
		try {
			List<Long> favorite = invertersFavRepo.findFavoriteInverters(userId);
			return new PageImpl<>(
					page.stream().map(c -> new InverterResult(c.getId(), favorite.indexOf(c.getId()) != -1,
							c.getMake(), c.getModel(), c.getVac(), c.getIacmax(),
							c.getHasCorrectionRequest(), 
							new UsersEntityResult(c.getAuthentificationEntity().getId(), c.getAuthentificationEntity().getFirstName(), c.getAuthentificationEntity().getLastName()), 
							c.getPaco(), c.getPdco(), c.getVdco(), c.getPso(),
							c.getC0(), c.getC1(), c.getC2(), c.getC3(), c.getPnt(), c.getVdcmax(), c.getIdcmax(), c.getMpptLow(),
							c.getMpptHigh(), c.getPowerRating(), c.getWeightedEfficiency(), c.getMicroInverter(), c.getWeight(),
							c.getIntegratedDCDisco(), c.getIntegratedACDisco(), c.getDataSheet(), c.getOptimizer(), c.getUpdated(),
							c.isDeleted(), c.getEroneousContent(), c.getEroneousContentOther(), c.getEroneousDescription(),
							c.getMpptQty(), c.getOcpd(), c.getWireQty(), c.getManufacturerMappingValue(), c.getModelMappingValue(),
							c.getModPerMicro(), voltageConfRepo.getVoltageByInverter(c.getId()), c.getNeutralConductor(), c.getIntegratedRsd(), c.getPeakOutputPower(),
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

	private Specification<Inverters> filter(String[] makes, String[] models, Boolean favorite, Boolean deleted,
			Long userId) {

		return new Specification<Inverters>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Inverters> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : invertersFavRepo.findFavoriteInverters(userId)) {
						inClause.value(id);
					}
					list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	public List<ComponentModel> getInverterList(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("make").and(Sort.by("model")));
			String[] model = checkValue.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			if (model == null) {
				Page<Inverters> p = inverterRepo.findByIsDeleted(false, pageable);
				return new PageImpl<ComponentModel>(
						p.stream().map(c -> new ComponentModel(c.getId(), null, c.getModel()))
								.collect(Collectors.toList()),
						pageable, p.getTotalElements()).getContent();
			} else {
				Page<Inverters> p = inverterRepo.findAll(filter(null, model, false, false, null), pageable);
				return new PageImpl<ComponentModel>(
						p.stream().map(c -> new ComponentModel(c.getId(), null, c.getModel()))
								.collect(Collectors.toList()),
						pageable, p.getTotalElements()).getContent();
			}

		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public String sendCorrectionInverterRequest(CorrectionRequest request) {

		try {
			if (request.getId() != 0) {
				Inverters library = inverterRepo.findById(request.getId()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + request.getId()));
				AuthentificationEntity user = userRepo.findById(request.getIdUser()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + request.getIdUser()));
				library.setHasCorrectionRequest(true);
				library.setEroneousContent(request.getEroneousContent());
				library.setEroneousContentOther(request.getEroneousContentOther());
				library.setEroneousDescription(request.getEroneousDescription());
				inverterRepo.save(library);
				notificationEntityService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
						"Request Correction - " + library.getModel(),
						"The user " + user.getFirstName() + " " + user.getLastName()
								+ " request correction for the Inverter " + library.getMake() + " "
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
	 * Add inverterFav
	 */
	public String addInverterFavorite(Long contractorId, Long inverterId, Long ownerId) {

		try {

			AuthentificationEntity contractor = userRepo.findById(ownerId)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + ownerId));
			Inverters inverter = inverterRepo.findById(inverterId)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + inverterId));
			InverterLibraryEntity inverterFav = new InverterLibraryEntity(contractor, inverter);
			invertersFavRepo.save(inverterFav);
			historicActivity.recordActivity(contractorId, "", "",
					"libraries;The favorite " + inverter.getMake() + " " + inverter.getModel()
							+ " is added to the user " + contractor.getFirstName() + " " + contractor.getLastName()
							+ ".;Inverters",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(contractorId, "", "",
					"libraries;Add Inverter to Users Favorites List;Inverters", false, "", "", "");
			return "Fail";
		}
	}

	/*
	 * Delete inverterFav
	 */
	public String removeInverterFavorite(Long contractorId, Long inverterId, Long ownerId) {
		try {
			AuthentificationEntity contractor = userRepo.findById(ownerId)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + ownerId));
			InverterLibraryEntity inverterFav = invertersFavRepo
					.findOneByAuthentificationEntityIdAndInvertersId(ownerId, inverterId);
			invertersFavRepo.delete(inverterFav);
			historicActivity.recordActivity(contractorId, "", "",
					"libraries;The favorite " + inverterFav.getInverters().getMake() + " "
							+ inverterFav.getInverters().getModel() + " is removed from the user "
							+ contractor.getFirstName() + " " + contractor.getLastName() + ".;Inverters",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(contractorId, "", "",
					"libraries;Remove Inverter from Users Favorites List;Inverters", false, "", "", "");
			return "Fail";
		}

	}
	/*
	 * Add inverter
	 */

	public FavoriteListDto addNewInverter(Long idPermitInfo, InverterResult inverterReq, Long idUserCo) {
		Inverters inverter = new Inverters();

		try {
			if (inverterReq != null) {

				inverter.setMake(inverterReq.getMake());
				inverter.setModel(inverterReq.getModel());

				if (checkValue.isStringNotEmpty(inverterReq.getManufacturerMappingValue())) {
					inverter.setManufacturerMappingValue(inverterReq.getManufacturerMappingValue());
				} else
					inverter.setManufacturerMappingValue(inverterReq.getMake());

				if (checkValue.isStringNotEmpty(inverterReq.getModelMappingValue())) {
					inverter.setModelMappingValue(inverterReq.getModelMappingValue());
				} else
					inverter.setModelMappingValue(inverterReq.getModel());

				inverter.setVac(inverterReq.getVac());
				inverter.setPaco(inverterReq.getPaco());
				inverter.setPdco(inverterReq.getPdco());
				inverter.setVdco(inverterReq.getVdco());
				inverter.setPso(inverterReq.getPso());
				inverter.setC0(inverterReq.getC0());
				inverter.setC1(inverterReq.getC1());
				inverter.setC2(inverterReq.getC2());
				inverter.setC3(inverterReq.getC3());
				inverter.setPnt(inverterReq.getPnt());
				inverter.setVdcmax(inverterReq.getVdcmax());
				inverter.setIdcmax(inverterReq.getIdcmax());
				inverter.setMpptLow(inverterReq.getMpptLow());
				inverter.setMpptHigh(inverterReq.getMpptHigh());
				inverter.setPowerRating(inverterReq.getPowerRating());
				inverter.setWeightedEfficiency(inverterReq.getWeightedEfficiency());
				inverter.setIacmax(inverterReq.getIacmax());
				inverter.setWeight(inverterReq.getWeight());
				inverter.setDataSheet(inverterReq.getDataSheet());
				inverter.setMpptQty(inverterReq.getMpptQty());
				inverter.setOcpd(inverterReq.getOcpd());
				inverter.setWireQty(inverterReq.getWireQty());

				inverter.setMicroInverter(Boolean.TRUE.equals(inverterReq.getMicroInverter()));
				inverter.setIntegratedDCDisco(Boolean.TRUE.equals(inverterReq.getIntegratedDCDisco()));
				inverter.setIntegratedACDisco(Boolean.TRUE.equals(inverterReq.getIntegratedACDisco()));
				inverter.setOptimizer(Boolean.TRUE.equals(inverterReq.getOptimizer()));
				inverter.setIntegratedRsd(inverterReq.getIntegratedRsd());
				inverter.setPeakOutputPower(inverterReq.getPeakOutputPower());
				AuthentificationEntity contractor = new AuthentificationEntity();
				try {
					contractor = permitRepo.findAuthentificationEntityByID(idPermitInfo);
				} catch (Exception e) {
					contractor = siteSurveyRepo.findCreator(idPermitInfo);
					e.printStackTrace();
				}

				AuthentificationEntity userCo = userRepo.findById(idUserCo).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + inverter.getId()));

				inverter.setHasSuperUserEdit(
						userCo != null && (userCo.getRoleEntity().getId() == 1 || userCo.getRoleEntity().getId() == 3));
				inverter.setAddDate(new Date());
				inverter.setUpdated(Formatted_DATE.format(new Date()));
				inverter.setModPerMicro(inverterReq.getModPerMicro());
				inverter.setNeutralConductor(inverterReq.getNeutralConductor());
				inverter.setIsVerified(false);
				InverterLibraryEntity inverterFav = new InverterLibraryEntity(contractor, inverter);
				inverter.setAuthentificationEntity(userCo);
				invertersFavRepo.save(inverterFav);
				inverterRepo.save(inverter);

				if (inverterReq.getServiceVoltage() != null && !inverterReq.getServiceVoltage().isEmpty()) {

					for (String config : inverterReq.getServiceVoltage()) {
						VoltageConfigurationEntity voltage = new VoltageConfigurationEntity();
						voltage.setIdInverter(inverter);
						voltage.setVoltage(config);
						voltageConfRepo.save(voltage);
					}

				}
				mailingService.mailUpdate("Inverter",
						"A new equipment of Inverter " + inverter.getMake() + " " + inverter.getModel()
								+ " has been added by " + userCo.getFirstName() + " " + userCo.getLastName(),
								userCo.getEmail().contains("nuagetechnologies-tn.com") && !userCo.getEmail().contains("pm"));
				addInverterNotification(idUserCo, inverterReq.getMake(), inverterReq.getModel());
				return new FavoriteListDto(inverter.getId(), inverter.getMake(), inverter.getModel(),
						inverterReq.getServiceVoltage(), inverter.getMicroInverter(), inverter.getOptimizer(),
						inverter.getVdcmax(),inverter.getWireQty());
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<ComponentModel> checkInverterExistent(InverterResult newmodule, Long userId) {

		try {

			List<Inverters> existingList = inverterRepo.findByManufactuterAndModel(
					newmodule.getModel().trim().toLowerCase(), newmodule.getMake().trim().toLowerCase());
			List<Inverters> existingModelList = inverterRepo.findByModel(newmodule.getModel().trim().toLowerCase(),
					newmodule.getMake().trim().toLowerCase());

			List<Long> favorite = invertersFavRepo.findFavoriteInverters(userId);
			List<ComponentModel> invertersList = new ArrayList<>();

			if (!existingList.isEmpty()) {

				for (int i = 0; i < existingList.size(); i++) {
					invertersList.add(new ComponentModel(existingList.get(i).getId(), existingList.get(i).getMake(),
							existingList.get(i).getModel(), favorite.indexOf(existingList.get(i).getId()) != -1));
				}
			} else if (!existingModelList.isEmpty()) {

				for (int i = 0; i < existingModelList.size(); i++) {
					invertersList.add(new ComponentModel(existingModelList.get(i).getId(),
							existingModelList.get(i).getMake(), existingModelList.get(i).getModel(),
							favorite.indexOf(existingModelList.get(i).getId()) != -1));
				}
			}
			return invertersList;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	/*
	 * edit Inverter
	 */
	public String editInverter(InverterResult inverter, Long userId) {
		try {

			Inverters testInverter = inverterRepo.findAllByMakeAndModelAndIsDeleted(inverter.getMake(),
					inverter.getModel(), false);

			if (testInverter != null) {
				if (!checkValue.Equals(testInverter.getId(), inverter.getId())) {
					return "exist";
				} else {
					return editInverterFunction(inverter, userId);
				}
			} else {
				return editInverterFunction(inverter, userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editInverterFunction(InverterResult inverter, Long userId) {

		try {
			
			Inverters updatedInverter = inverterRepo.findById(inverter.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + inverter.getId()));
			
			AuthentificationEntity firstUpdater = userRepo.findById(userId).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userId));
		
			updatedInverter.setMake(inverter.getMake());
			updatedInverter.setModel(inverter.getModel());

			if (checkValue.NotEquals(inverter.getManufacturerMappingValue(), "")) {
				updatedInverter.setManufacturerMappingValue(inverter.getManufacturerMappingValue());
			} else
				updatedInverter.setManufacturerMappingValue(inverter.getMake());

			if (checkValue.NotEquals(inverter.getModelMappingValue(), "")) {
				updatedInverter.setModelMappingValue(inverter.getModelMappingValue());
			} else
				updatedInverter.setModelMappingValue(inverter.getModel());

			updatedInverter.setVac(inverter.getVac());
			updatedInverter.setPaco(inverter.getPaco());
			updatedInverter.setPdco(inverter.getPdco());
			updatedInverter.setVdco(inverter.getVdco());
			updatedInverter.setPso(inverter.getPso());
			updatedInverter.setC0(inverter.getC0());
			updatedInverter.setC1(inverter.getC1());
			updatedInverter.setC2(inverter.getC2());
			updatedInverter.setC3(inverter.getC3());
			updatedInverter.setPnt(inverter.getPnt());
			updatedInverter.setVdcmax(inverter.getVdcmax());
			updatedInverter.setIdcmax(inverter.getIdcmax());
			updatedInverter.setMpptLow(inverter.getMpptLow());
			updatedInverter.setMpptHigh(inverter.getMpptHigh());
			updatedInverter.setPowerRating(inverter.getPowerRating());
			updatedInverter.setWeightedEfficiency(inverter.getWeightedEfficiency());
			updatedInverter.setIacmax(inverter.getIacmax());
			updatedInverter.setWeight(inverter.getWeight());
			updatedInverter.setDataSheet(inverter.getDataSheet());
			updatedInverter.setHasCorrectionRequest(inverter.getHasCorrectionRequest());
			updatedInverter.setEroneousContent(inverter.getEroneousContent());
			updatedInverter.setEroneousContentOther(inverter.getEroneousContentOther());
			updatedInverter.setEroneousDescription(inverter.getEroneousDescription());
			updatedInverter.setOcpd(inverter.getOcpd());
			updatedInverter.setMpptQty(inverter.getMpptQty());
			updatedInverter.setWireQty(inverter.getWireQty());
			updatedInverter.setMicroInverter(inverter.getMicroInverter());
			updatedInverter.setIntegratedDCDisco(inverter.getIntegratedDCDisco());
			updatedInverter.setIntegratedACDisco(inverter.getIntegratedACDisco());
			updatedInverter.setIntegratedRsd(inverter.getIntegratedRsd());
			updatedInverter.setOptimizer(inverter.getOptimizer());
			updatedInverter.setUpdated(Formatted_DATE.format(new Date()));
			updatedInverter.setModPerMicro(inverter.getModPerMicro());
			updatedInverter.setPeakOutputPower(inverter.getPeakOutputPower());
//			S.B 17/04/2020 CR-3119-MOD-002
			updatedInverter.setNeutralConductor(inverter.getNeutralConductor());
			updatedInverter.setIsVerified(false);
			String updateNum = "";
			if(updatedInverter.getFirstUpdater() == null) {
				updateNum = "1st";	
				updatedInverter.setFirstUpdater(firstUpdater);
			}else if(updatedInverter.getSecondUpdater() == null) {
				updateNum = "2nd";				
				updatedInverter.setSecondUpdater(firstUpdater);
			}else if(updatedInverter.getThirdUpdater() == null) {
				updateNum = "3rd";				
				updatedInverter.setThirdUpdater(firstUpdater);
			}	
			inverterRepo.save(updatedInverter);

//			S.B 17/04/2020 CR-3119-MOD-002
			if (inverter.getServiceVoltage() != null) {
				List<VoltageConfigurationEntity> inverters = voltageConfRepo.findAllByIdInverterId(inverter.getId());
				if (inverters != null && !inverters.isEmpty()) {
					for (VoltageConfigurationEntity conf : inverters) {
						voltageConfRepo.delete(conf);
					}
				}
				for (String config : inverter.getServiceVoltage()) {
					VoltageConfigurationEntity voltage = new VoltageConfigurationEntity();
					voltage.setIdInverter(updatedInverter);
					voltage.setVoltage(config);
					voltageConfRepo.save(voltage);
				}
			}
			
			historicActivity.recordActivity(userId, "", "",
					"libraries;Edit component " + inverter.getMake() + " " + inverter.getModel() + ".;Inverters", true,
					"", "", "");
			if (checkValue.isStringNotEmpty(updateNum)) {
				mailingService.mailUpdate("Inverter",
						"An existing equipment of Inverter " + updatedInverter.getMake() + " "
								+ updatedInverter.getModel() + " has been updated " + updateNum + " time by "
								+ firstUpdater.getFirstName() + " " + firstUpdater.getLastName(),
								firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userId, "", "", "libraries;Edit component.;Inverters", false, "", "", "");
			return "fail";
		}
	}
	
	
	public String verifInverter(InverterResult inverter, Long userId) {
		
		try {
			
			Inverters updatedInverter = inverterRepo.findById(inverter.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + inverter.getId()));

			AuthentificationEntity userCo = userRepo.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + userId));
			
			if(Boolean.TRUE.equals(inverter.getIsVerified())) {
				updatedInverter.setVerifiedBy(userCo);
				updatedInverter.setIsVerified(true);
				updatedInverter.setDateVerification(new Date());
			}else if(Boolean.FALSE.equals(inverter.getIsVerified())) {
				updatedInverter.setIsVerified(false);
			}
			inverterRepo.save(updatedInverter);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String editInverterNotification(Long userId, String inverterMake, String inverterModel) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + userId));
			notificationEntityService.addNewNotif(userId, 0L, "Inverter Update", "Libraries",
					"Inverter Update - " + inverterModel, "The inverter " + inverterModel + " " + inverterMake
							+ " was updated by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	private String addInverterNotification(Long userId, String inverterMake, String inverterModel) {
		try {

			AuthentificationEntity userCo = userRepo.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + userId));
			notificationEntityService.addNewNotif(userId, 0L, "New Inverter", "Libraries",
					"New Inverter- " + inverterModel, "The inverter " + inverterModel + " " + inverterMake
							+ " was added by " + userCo.getFirstName() + " " + userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	// CR 924
	// Change Request Inverter List Management
	public String inverterLibraryActived(Long idInverter, Long userId) {

		try {
			Inverters inverterLibrary = inverterRepo.findById(idInverter)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + idInverter));
			if (Boolean.TRUE
					.equals(inverterRepo.existByManufactuterAndModel(checkValue.trimLower(inverterLibrary.getModel()),
							checkValue.trimLower(inverterLibrary.getMake())))) {
				return "exist";
			} else {
				inverterLibrary.setDeleted(false);
				inverterRepo.save(inverterLibrary);
				historicActivity.recordActivity(userId, "", "", "libraries;Activate component "
						+ inverterLibrary.getMake() + " " + inverterLibrary.getModel() + ".;Inverters", true, "", "",
						"");
				return "true";

			}
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userId, "", "", "libraries;Activate component.;Inverters", false, "", "",
					"");
			return "false";
		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfInverterDeleted(Long idInverter) {
		return arraysRepo.findByInverterId(idInverter);
	}

	public Boolean deleteInverterLibrary(Long id, Long userId) {
		try {

			Inverters inverterLibrary = inverterRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + id));

			inverterLibrary.setDeleted(true);
			List<InverterLibraryEntity> inverterFav = invertersFavRepo.findAllByInvertersId(id);
			invertersFavRepo.deleteAll(inverterFav);
			historicActivity.recordActivity(userId, "", "", "libraries;Delete component " + inverterLibrary.getMake()
					+ " " + inverterLibrary.getModel() + ".;Inverters", true, "", "", "");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userId, "", "", "libraries;Delete component.;Inverters", false, "", "", "");
			return false;
		}
	}

	/*
	 * Edit Inverter Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long inverterId, Long userId) {

		try {
			List<Long> usersFavID = invertersFavRepo.findFavoriteUsers(inverterId);
			return userRepo.findUserHaveNotFav(usersFavID, false, true, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public String editUsersFavoriteList(Long inverterId, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo) {
		try {

			Inverters inverte = inverterRepo.findById(inverterId)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + inverterId));
			for (int i = 0; i < listUsers.length; i++) {

				AuthentificationEntity user = userRepo.findById(listUsers[i])
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id"));

				InverterLibraryEntity inverterFav = new InverterLibraryEntity(user, inverte);
				inverterFav.setInverters(inverte);
				inverterFav.setAuthentificationEntity(user);
				invertersFavRepo.save(inverterFav);

				historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + inverte.getMake() + " " + inverte.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;Inverters",
						true, "", "", "");
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Inverter to Users Favorites List.;Inverters", false, "", "", "");
			return "error";
		}
	}

	public Inverters getInverterEntity(Long microInverter) {

		try {
			if (checkValue.isLongPositive(microInverter)) {
				return inverterRepo.findByIdAndIsDeleted(microInverter, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
