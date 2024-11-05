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
import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.entity.LeasePPAMeterFavLibraryEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.LeasePPAMeterFavRequest;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchLeasePPAMeterResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class LeasePPAMeterService {

	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final InverterService getInverterLibraryService;
	final CheckValueTypesService checkValueTypesService;
	final LeasePPAMeterRepository leasePPAMeterRepo;
	final AuthenticationRepository authentificationRepo;
	final LeasePPAMeterFavoritesRepository leasePPAMetersFavoritesRepo;
	final PermitArraysRepository permitArraysEntityRepo;
	final PermitRepository permitEntityRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo;
	final EquipmentUpdate mailingService;

	public LeasePPAMeterService(HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, InverterService getInverterLibraryService,
			CheckValueTypesService checkValueTypesService, LeasePPAMeterRepository leasePPAMeterRepo,
			AuthenticationRepository authentificationRepo, LeasePPAMeterFavoritesRepository leasePPAMetersFavoritesRepo,
			PermitArraysRepository permitArraysEntityRepo, PermitRepository permitEntityRepo,
			PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo, EquipmentUpdate mailingService) {
		super();
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.getInverterLibraryService = getInverterLibraryService;
		this.checkValueTypesService = checkValueTypesService;
		this.leasePPAMeterRepo = leasePPAMeterRepo;
		this.authentificationRepo = authentificationRepo;
		this.leasePPAMetersFavoritesRepo = leasePPAMetersFavoritesRepo;
		this.permitArraysEntityRepo = permitArraysEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.permitProjectSiteInfoEntityRepo = permitProjectSiteInfoEntityRepo;
		this.mailingService = mailingService;
	}

	public Page<SearchLeasePPAMeterResult> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			String[] manufacturer = checkValueTypesService.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValueTypesService.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ")
					: null;
			if (manufacturer == null && model == null && !Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<LeasePPAMeter> p = leasePPAMeterRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable, request.getIdUser());
			} else {
				Page<LeasePPAMeter> p = leasePPAMeterRepo.findAll(filter(manufacturer, model, request.getIsFavorite(),
						request.getIsDeleted(), request.getIdUser()), pageable);
				return convertDto(p, pageable, request.getIdUser());

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<SearchLeasePPAMeterResult> convertDto(Page<LeasePPAMeter> page, Pageable pageable, Long userId) {
		try {
			List<Long> favorite = leasePPAMetersFavoritesRepo.findFavoriteLeasePPAMeters(userId);
			return new PageImpl<>(
					page.stream().map(c -> new SearchLeasePPAMeterResult(c.getId(), favorite.indexOf(c.getId()) != -1,
							c.getManufacturer(), c.getModel(), c.getMappedValue(), c.getIncludeTLD(), c.getUpdated(),
							c.isDeleted(),
							new UsersEntityResult(c.getAuthentificationEntity().getId(),
									c.getAuthentificationEntity().getFirstName(),
									c.getAuthentificationEntity().getLastName()),
							c.getHasCorrectionRequest(), c.getEroneousContent(), c.getEroneousContentOther(),
							c.getEroneousDescription(),
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

	private Specification<LeasePPAMeter> filter(String[] makes, String[] models, Boolean favorite, Boolean deleted,
			Long userId) {

		return new Specification<LeasePPAMeter>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<LeasePPAMeter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
				Predicate endPredicateMake = makes != null
						? cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()]))
						: null;
				Predicate endPredicateModel = models != null
						? cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()]))
						: null;

				List<Predicate> list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel);
				if (Boolean.TRUE.equals(favorite) && !Boolean.TRUE.equals(deleted)) {
					In<Long> inClause = cb.in(root.get("id"));
					for (Long id : leasePPAMetersFavoritesRepo.findFavoriteLeasePPAMeters(userId)) {
						inClause.value(id);
					}
					list = Arrays.asList(predicateDeleted, endPredicateMake, endPredicateModel, inClause);
				}
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}

	public String sendCorrectionLeasePPAMeterRequest(CorrectionRequest request) {

		try {
			if (request.getId() != 0) {
				AuthentificationEntity user = authentificationRepo.findById(request.getIdUser()).orElseThrow(
						() -> new ResourceNotFoundException("User not found for this id :" + request.getIdUser()));
				LeasePPAMeter library = leasePPAMeterRepo.findById(request.getId()).orElseThrow(
						() -> new ResourceNotFoundException("LPPAM not found for this id :" + request.getId()));
				library.setHasCorrectionRequest(true);
				library.setEroneousContent(request.getEroneousContent());
				library.setEroneousContentOther(request.getEroneousContentOther());
				library.setEroneousDescription(request.getEroneousDescription());
				leasePPAMeterRepo.save(library);
				notificationEntityService.addNewNotif(request.getIdUser(), 0L, "Request Correction", "Libraries",
						"Request Correction - " + library.getModel(),
						"The user " + user.getFirstName() + " " + user.getLastName()
								+ " request correction for the Revenue or Performance Monitoring Meter "
								+ library.getManufacturer() + " " + library.getModel(),
						true);
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}

	/*
	 * Get leasePPAMeterFav
	 */
	public List<Long> getLeasePPAMeterFavorite(Long idContractor) {
		List<Long> leasePPAMeterFavID = new ArrayList<>();
		try {
			List<LeasePPAMeterFavLibraryEntity> leasePPAMeterFav = leasePPAMetersFavoritesRepo
					.findAllByAuthentificationEntityId(idContractor);
			if (leasePPAMeterFav != null && !leasePPAMeterFav.isEmpty()) {
				for (int i = 0; i < leasePPAMeterFav.size(); i++) {
					if (leasePPAMeterFav.get(i) != null && leasePPAMeterFav.get(i).getLeasePPAMeter() != null) {
						leasePPAMeterFavID.add(leasePPAMeterFav.get(i).getLeasePPAMeter().getId());
					}
				}
				return leasePPAMeterFavID;
			} else
				return leasePPAMeterFavID;

		} catch (Exception e) {
			e.printStackTrace();
			return leasePPAMeterFavID;
		}

	}

	/*
	 * Add leasePPAMeterFav
	 */
	public String addLeasePPAMeterFavorite(Long idContractor, Long idLeasePPAMeter, Long idOwner) {

		try {

			AuthentificationEntity contractor = new AuthentificationEntity();
			LeasePPAMeter leasePPAMeter = new LeasePPAMeter();
			try {
				contractor = authentificationRepo.findById(idOwner)
						.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idOwner));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				leasePPAMeter = leasePPAMeterRepo.findById(idLeasePPAMeter).orElseThrow(
						() -> new ResourceNotFoundException("LPPAM not found for this id :" + idLeasePPAMeter));
			} catch (Exception e) {
				e.printStackTrace();
			}
			LeasePPAMeterFavLibraryEntity leasePPAMeterFav = new LeasePPAMeterFavLibraryEntity(contractor,
					leasePPAMeter);
			leasePPAMetersFavoritesRepo.save(leasePPAMeterFav);
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;The favorite " + leasePPAMeter.getManufacturer() + " " + leasePPAMeter.getModel()
							+ " is added to the user " + contractor.getFirstName() + " " + contractor.getLastName()
							+ ".;Revenue or Performance Monitoring Meter",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;Add Revenue or Performance Monitoring Meter to user Favorites List;Revenue or Performance Monitoring Meter Library",
					false, "", "", "");
			return "Fail";
		}
	}

	/*
	 * Delete leasePPAMeterFav
	 */
	public String removeLeasePPAMeterFavorite(Long idContractor, Long idLeasePPAMeter, Long idOwner) {

		try {
			AuthentificationEntity contractor = authentificationRepo.findById(idOwner)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idOwner));
			LeasePPAMeterFavLibraryEntity leasePPAMeterFav = leasePPAMetersFavoritesRepo
					.findOneByAuthentificationEntityIdAndLeasePPAMeterId(idOwner, idLeasePPAMeter);
			leasePPAMetersFavoritesRepo.delete(leasePPAMeterFav);
			List<PermitProjectSiteInfoEntity> permitArray1 = permitProjectSiteInfoEntityRepo
					.findByPermitEntityAuthentificationEntityIdAndLeasePPAMeter(idOwner, idLeasePPAMeter + "");
			for (int i = 0; !permitArray1.isEmpty() && i < permitArray1.size(); i++) {
				permitArray1.get(i).setLeasePPAMeter("Fav Removed");
				permitProjectSiteInfoEntityRepo.save(permitArray1.get(i));
			}
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;The favorite " + leasePPAMeterFav.getLeasePPAMeter().getManufacturer() + " "
							+ leasePPAMeterFav.getLeasePPAMeter().getModel() + " is removed from the user "
							+ contractor.getFirstName() + " " + contractor.getLastName()
							+ ".;Revenue or Performance Monitoring Meter",
					true, "", "", "");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idContractor, "", "",
					"libraries;Remove Revenue or Performance Monitoring Meter from  Users Favorites List;Revenue or Performance Monitoring Meter",
					false, "", "", "");
			return "Fail";
		}

	}

	/*
	 * Get Contractor leasePPAMeterFav
	 */
	public List<LeasePPAMeter> getContractorLeasePPAMeterFav(Long idContractor) {
		List<LeasePPAMeter> leasePPAMeterContractorFav = new ArrayList<>();
		try {

			List<Long> leasePPAMeterFavID = getLeasePPAMeterFavorite(idContractor);
			if (leasePPAMeterFavID != null && !leasePPAMeterFavID.isEmpty()) {
				for (int i = 0; i < leasePPAMeterFavID.size(); i++) {
					LeasePPAMeter leasePPAMeter = new LeasePPAMeter();
					try {
						leasePPAMeter = leasePPAMeterRepo.findById(leasePPAMeterFavID.get(i))
								.orElseThrow(() -> new ResourceNotFoundException("LPPAM not found for this id."));
					} catch (Exception e) {
						e.printStackTrace();
					}
					leasePPAMeterContractorFav.add(leasePPAMeter);
				}
			} else
				leasePPAMeterContractorFav = null;

			return leasePPAMeterContractorFav;
		} catch (Exception e) {
			e.printStackTrace();
			return leasePPAMeterContractorFav;
		}
	}

	/*
	 * Get Contractor leasePPAMeterFav
	 */
	public List<LeasePPAMeter> getSuperUserLeasePPAMeterFav(Long permitId) {

		List<LeasePPAMeter> leasePPAMeterContractorFav = new ArrayList<>();
		try {
			PermitEntity permit = permitEntityRepo.findById(permitId)
					.orElseThrow(() -> new ResourceNotFoundException("Permit not found for this id :" + permitId));
			Long idUser = permit.getAuthentificationEntity().getId();

			List<Long> leasePPAMeterFavID = getLeasePPAMeterFavorite(idUser);
			if (leasePPAMeterFavID != null && !leasePPAMeterFavID.isEmpty()) {
				for (int i = 0; i < leasePPAMeterFavID.size(); i++) {
					LeasePPAMeter leasePPAMeter = new LeasePPAMeter();
					try {
						leasePPAMeter = leasePPAMeterRepo.findById(leasePPAMeterFavID.get(i))
								.orElseThrow(() -> new ResourceNotFoundException("LPPAM not found for this id."));
					} catch (Exception e) {
						e.printStackTrace();
					}
					leasePPAMeterContractorFav.add(leasePPAMeter);
				}
			} else
				leasePPAMeterContractorFav = null;

			return leasePPAMeterContractorFav;
		} catch (Exception e) {
			e.printStackTrace();
			return leasePPAMeterContractorFav;
		}
	}

	public boolean getTestLeasePPAMeterFav(Long permitId, String testMan) {

		try {
			int test = 0;
			PermitEntity permit = permitEntityRepo.findById(permitId)
					.orElseThrow(() -> new ResourceNotFoundException("Permit not found for this id :" + permitId));
			Long idUser = permit.getAuthentificationEntity().getId();

			List<LeasePPAMeter> leasePPAMeterContractorFav = new ArrayList<>();
			List<Long> leasePPAMeterFavID = getLeasePPAMeterFavorite(idUser);

			if (leasePPAMeterFavID != null) {
				for (int i = 0; i < leasePPAMeterFavID.size(); i++) {
					LeasePPAMeter leasePPAMeter = new LeasePPAMeter();
					try {
						leasePPAMeter = leasePPAMeterRepo.findById(leasePPAMeterFavID.get(i))
								.orElseThrow(() -> new ResourceNotFoundException("LPPAM not found for this id."));
					} catch (Exception e) {
						e.printStackTrace();
					}
					leasePPAMeterContractorFav.add(leasePPAMeter);
				}

			}

			for (int i = 0; i < leasePPAMeterContractorFav.size(); i++) {
				if (leasePPAMeterContractorFav.get(i) != null
						&& checkValueTypesService.Equals(testMan, leasePPAMeterContractorFav.get(i).getManufacturer()
								+ ":" + leasePPAMeterContractorFav.get(i).getModel())) {
					test = 1;
				}
			}

			if (test != 1) {
				return true;
			} else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * Add leasePPAMeter
	 */
	public String addLeasePPAMeter(Long idContractor, LeasePPAMeterFavRequest leasePPAMeterReq, String ipUser,
			String timeZoneUser, Long idUser) {

		try {

			List<String> leasePPAMeters = leasePPAMeterRepo.findModelAndManufacturer(leasePPAMeterReq.getManufacturer(),
					leasePPAMeterReq.getModel());
			if (leasePPAMeters.isEmpty()) {
				LeasePPAMeter leasePPAMeter = new LeasePPAMeter();
				leasePPAMeter.setManufacturer(leasePPAMeterReq.getManufacturer());
				leasePPAMeter.setModel(leasePPAMeterReq.getModel());
				leasePPAMeter.setMappedValue(leasePPAMeterReq.getMappedValue());
				leasePPAMeter.setIncludeTLD(leasePPAMeterReq.getIncludeTLD());
				leasePPAMeter.setHasCorrectionRequest(leasePPAMeterReq.getHasCorrectionRequest());
				leasePPAMeter.setEroneousContent(leasePPAMeterReq.getEroneousContent());
				leasePPAMeter.setEroneousContentOther(leasePPAMeterReq.getEroneousContentOther());
				leasePPAMeter.setEroneousDescription(leasePPAMeterReq.getEroneousDescription());
				Date d = new Date();
				SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
				String updatedDate = formattedDATE.format(d);
				leasePPAMeter.setUpdated(updatedDate);

				AuthentificationEntity contractor = new AuthentificationEntity();
				try {
					contractor = authentificationRepo.findById(idContractor).orElseThrow(
							() -> new ResourceNotFoundException("User not found for this id :" + idContractor));
				} catch (Exception e) {
					e.printStackTrace();
				}
				LeasePPAMeterFavLibraryEntity leasePPAMeterFav = new LeasePPAMeterFavLibraryEntity(contractor,
						leasePPAMeter);
				leasePPAMeterFav.setAuthentificationEntity(contractor);
				leasePPAMeterFav.setLeasePPAMeter(leasePPAMeter);
				leasePPAMeter.setAuthentificationEntity(contractor);
				leasePPAMeter.setIsVerified(false);
				if (contractor.getRoleEntity().getId() == 1 || contractor.getRoleEntity().getId() == 3) {
					leasePPAMeter.setHasSuperUserEdit(true);
				} else {
					leasePPAMeter.setHasSuperUserEdit(false);
				}
				leasePPAMetersFavoritesRepo.save(leasePPAMeterFav);
				leasePPAMeterRepo.save(leasePPAMeter);
				mailingService.mailUpdate("Revenue or Performance Monitoring Meter",
						"A new equipment of Revenue or Performance Monitoring Meter " + leasePPAMeter.getManufacturer()
								+ " " + leasePPAMeter.getModel() + " has been added by " + contractor.getFirstName()
								+ " " + contractor.getLastName(),
						contractor.getEmail().contains("nuagetechnologies-tn.com")
								&& !contractor.getEmail().contains("pm"));
				historicActivity.recordActivity(
						idUser, ipUser, timeZoneUser, "libraries;Add component " + leasePPAMeter.getManufacturer() + " "
								+ leasePPAMeter.getModel() + ".;Revenue or Performance Monitoring Meter",
						true, "", "", "");
				return "Done";
			} else
				historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
						"Add LeasePPAMeter Component Library;LeasePPAMeter already exists in data sources;Add failed ",
						false, "", "", "");
			return "LeasePPAMeter already exists in data sources";

		} catch (Exception e) {
			historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
					"libraries;Add component.;Revenue or Performance Monitoring Meter", false, "", "", "");
			e.printStackTrace();
			return "error";
		}
	}

	/*
	 * get Total Number of LeasePPAMeter ( function "Long getNumLeasePPAMeterTot() "
	 * removed )
	 */

	/*
	 * edit LeasePPAMeter
	 */
	public String editLeasePPAMeter(LeasePPAMeterFavRequest leasePPAMeter, Long userID) {
		try {

			LeasePPAMeter testLeasePPAMeter = new LeasePPAMeter();
			if (leasePPAMeterRepo.countByManufacturerAndModelAndIsDeleted(leasePPAMeter.getManufacturer(),
					leasePPAMeter.getModel(), false) != 0) {
				testLeasePPAMeter = leasePPAMeterRepo.findByManufacturerAndModelAndIsDeleted(
						leasePPAMeter.getManufacturer(), leasePPAMeter.getModel(), false);
				if (testLeasePPAMeter != null
						&& !checkValueTypesService.Equals(testLeasePPAMeter.getId(), leasePPAMeter.getId())) {
					return "exist";
				} else {
					return editLeasePPAMeterFunction(leasePPAMeter, userID);
				}
			} else {
				return editLeasePPAMeterFunction(leasePPAMeter, userID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editLeasePPAMeterFunction(LeasePPAMeterFavRequest leasePPAMeter, Long userID) {

		try {
			LeasePPAMeter updatedLeasePPAMeter = leasePPAMeterRepo.findById(leasePPAMeter.getId()).orElseThrow(
					() -> new ResourceNotFoundException("LPPAM not found for this id :" + leasePPAMeter.getId()));

			AuthentificationEntity firstUpdater = authentificationRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + userID));

			// manufacturer or model has change ---> update (manufacturer,model) in all the
			// project using the edited leasePPAMeter
			List<PermitProjectSiteInfoEntity> permitProjectSiteInfoEntitys = permitProjectSiteInfoEntityRepo
					.findByLeasePPAMeter(updatedLeasePPAMeter.getId() + "");
			if (permitProjectSiteInfoEntitys != null && !permitProjectSiteInfoEntitys.isEmpty()) {
				for (PermitProjectSiteInfoEntity PermitProjectSiteInfoEntity : permitProjectSiteInfoEntitys) {
					if (PermitProjectSiteInfoEntity != null && PermitProjectSiteInfoEntity.getLeasePPAMeter() != null) {
						PermitProjectSiteInfoEntity.setLeasePPAMeter(leasePPAMeter.getId() + "");
					}

				}
			}
			// end
			Date today = new Date();
			SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = formattedDATE.format(today);

			updatedLeasePPAMeter.setId(leasePPAMeter.getId());
			updatedLeasePPAMeter.setManufacturer(leasePPAMeter.getManufacturer());
			updatedLeasePPAMeter.setModel(leasePPAMeter.getModel());
			updatedLeasePPAMeter.setMappedValue(leasePPAMeter.getMappedValue());
			updatedLeasePPAMeter.setIncludeTLD(leasePPAMeter.getIncludeTLD());
			updatedLeasePPAMeter.setHasCorrectionRequest(leasePPAMeter.getHasCorrectionRequest());
			updatedLeasePPAMeter.setEroneousContent(leasePPAMeter.getEroneousContent());
			updatedLeasePPAMeter.setEroneousContentOther(leasePPAMeter.getEroneousContentOther());
			updatedLeasePPAMeter.setEroneousDescription(leasePPAMeter.getEroneousDescription());
			updatedLeasePPAMeter.setUpdated(updatedDate);

			updatedLeasePPAMeter.setIsVerified(false);
			String updateNum = "";
			if (updatedLeasePPAMeter.getFirstUpdater() == null) {
				updateNum = "1st";
				updatedLeasePPAMeter.setFirstUpdater(firstUpdater);
			} else if (updatedLeasePPAMeter.getSecondUpdater() == null) {
				updateNum = "2nd";
				updatedLeasePPAMeter.setSecondUpdater(firstUpdater);
			} else if (updatedLeasePPAMeter.getThirdUpdater() == null) {
				updateNum = "3rd";
				updatedLeasePPAMeter.setThirdUpdater(firstUpdater);
			}

			leasePPAMeterRepo.save(updatedLeasePPAMeter);
			historicActivity
					.recordActivity(userID, "", "",
							"libraries;Edit component " + leasePPAMeter.getManufacturer() + " "
									+ leasePPAMeter.getModel() + ".;Revenue or Performance Monitoring Meter",
							true, "", "", "");

			if (checkValueTypesService.isStringNotEmpty(updateNum)) {
				mailingService.mailUpdate("Revenue or Performance Monitoring Meter",
						"An existing equipment of Revenue or Performance Monitoring Meter "
								+ leasePPAMeter.getManufacturer() + " " + leasePPAMeter.getModel()
								+ " has been updated " + updateNum + " time by " + firstUpdater.getFirstName() + " "
								+ firstUpdater.getLastName(),
						firstUpdater.getEmail().contains("nuagetechnologies-tn.com")
								&& !firstUpdater.getEmail().contains("pm"));
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "",
					"libraries;Edit component.;Revenue or Performance Monitoring Meter", false, "", "", "");
			return "fail";
		}
	}

	public String editLeasePPAMeterNotification(Long userID, String leasePPAMeterManufacturer,
			String leasePPAMeterModel) {
		try {
			AuthentificationEntity userCo = authentificationRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + userID));
			notificationEntityService.addNewNotif(userID, 0L, "Revenue or Performance Monitoring Meter Update",
					"Libraries", "Revenue or Performance Monitoring Meter Update - " + leasePPAMeterModel,
					"The Revenue or Performance Monitoring Meter " + leasePPAMeterModel + " "
							+ leasePPAMeterManufacturer + " was updated by " + userCo.getFirstName() + " "
							+ userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}

	public String addLeasePPAMeterNotification(Long userID, String leasePPAMeterManufacturer,
			String leasePPAMeterModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + userID));
			notificationEntityService.addNewNotif(userID, 0L, "New Revenue or Performance Monitoring Meter",
					"Libraries", "New Revenue or Performance Monitoring Meter- " + leasePPAMeterModel,
					"The Revenue or Performance Monitoring Meter " + leasePPAMeterModel + " "
							+ leasePPAMeterManufacturer + " was added by " + userCo.getFirstName() + " "
							+ userCo.getLastName(),
					true);

			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
	// CR 924
	// Change Request LeasePPAMeter List Management

	public String leasePPAMeterLibraryActived(Long idLeasePPAMeter, Long userID) {

		try {

			LeasePPAMeter leasePPAMeterLibrary = leasePPAMeterRepo.findById(idLeasePPAMeter).orElseThrow(
					() -> new ResourceNotFoundException("LPPAM not found for this id :" + idLeasePPAMeter));
			if (leasePPAMeterLibrary.getId() != 0) {
				if (leasePPAMeterRepo.countByManufacturerAndModelAndIsDeleted(leasePPAMeterLibrary.getManufacturer(),
						leasePPAMeterLibrary.getModel(), false) != 0) {

					return "exist";
				} else {
					leasePPAMeterLibrary.setDeleted(false);
					historicActivity.recordActivity(userID, "", "",
							"libraries;Activate component " + leasePPAMeterLibrary.getManufacturer() + " "
									+ leasePPAMeterLibrary.getModel() + ".;Revenue or Performance Monitoring Meter",
							true, "", "", "");
					return "true";
				}

			}
			historicActivity.recordActivity(userID, "", "",
					"libraries;Activate component " + leasePPAMeterLibrary.getManufacturer() + " "
							+ leasePPAMeterLibrary.getModel() + ".;Revenue or Performance Monitoring Meter",
					false, "", "", "");
			return "false";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "",
					"libraries;Activate component.;Revenue or Performance Monitoring Meter", false, "", "", "");
			return "false";
		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfLeasePPAMeterDeleted(Long idLeasePPAMeter) {
		try {
			return permitProjectSiteInfoEntityRepo.getPermitEntityByLeasePPAMeter(idLeasePPAMeter + "");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<PermitEntity> getAllPermitOfLeasePPAMeterDeleted1(Long idLeasePPAMeter) {

		List<PermitEntity> resPermit = new ArrayList<>();
		try {
			LeasePPAMeter leasePPAMeterLibrary = leasePPAMeterRepo.findById(idLeasePPAMeter).orElseThrow(
					() -> new ResourceNotFoundException("LPPAM not found for this id :" + idLeasePPAMeter));

			/***************** Get all permit Array content this model *****************/
			List<PermitProjectSiteInfoEntity> allpermitProjectSiteInfo = permitProjectSiteInfoEntityRepo
					.findByLeasePPAMeter(leasePPAMeterLibrary.getId() + "");

			/************** Get all permit content this model ***************************/

			/****************** test on the state of Permit **************/
			if (leasePPAMeterLibrary != null && leasePPAMeterLibrary.getId() != 0) {
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
				return null;

			return resPermit;
		} catch (Exception e) {
			e.printStackTrace();
			return resPermit;
		}
	}

	public boolean deleteLeasePPAMeterLibrary(Long id, Long userID) {

		try {

			LeasePPAMeter leasePPAMeterLibrary = leasePPAMeterRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + id));
			if (id != 0) {
				List<PermitEntity> listPermitDeleted = getAllPermitOfLeasePPAMeterDeleted1(id);
				PermitProjectSiteInfoEntity permitProjectSiteInfo = null;
				LeasePPAMeter leasePPAMeterLibraryRes = leasePPAMeterRepo.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + id));
				if (listPermitDeleted != null) {
					for (PermitEntity permitEntity : listPermitDeleted) {

						if (permitProjectSiteInfoEntityRepo.countByPermitEntityAndLeasePPAMeter(permitEntity,
								leasePPAMeterLibraryRes.getId() + "") != 0) {
							permitProjectSiteInfo = permitProjectSiteInfoEntityRepo.findByPermitEntityAndLeasePPAMeter(
									permitEntity, leasePPAMeterLibraryRes.getId() + "");
							permitProjectSiteInfo.setLeasePPAMeter("Removed");
						}

					}
				}

				leasePPAMeterLibrary.setDeleted(true);

				List<LeasePPAMeterFavLibraryEntity> leasePPAMeterFav = leasePPAMetersFavoritesRepo
						.findAllByLeasePPAMeterId(id);
				if (leasePPAMeterFav != null && !leasePPAMeterFav.isEmpty()) {
					for (LeasePPAMeterFavLibraryEntity leasePPAMeterFavLibraryEntity : leasePPAMeterFav) {
						leasePPAMetersFavoritesRepo.delete(leasePPAMeterFavLibraryEntity);
					}
				}

				historicActivity.recordActivity(userID, "", "",
						"libraries;Delete component " + leasePPAMeterLibrary.getManufacturer() + " "
								+ leasePPAMeterLibrary.getModel() + ".;Revenue or Performance Monitoring Meter",
						true, "", "", "");
				return true;

			}
			historicActivity.recordActivity(userID, "", "",
					"libraries;Delete component.;Revenue or Performance Monitoring Meter", false, "", "", "");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "",
					"libraries;Delete component.;Revenue or Performance Monitoring Meter", false, "", "", "");
			return false;

		}
	}

	/*
	 * Edit LeasePPAMeter Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long idLeasePPAMeter, Long userID) {

		List<UsersEntityResult> usersList = new ArrayList<>();
		try {
			List<LeasePPAMeterFavLibraryEntity> leasePPAMeterFav = leasePPAMetersFavoritesRepo
					.findAllByLeasePPAMeterId(idLeasePPAMeter);
			if (!leasePPAMeterFav.isEmpty()) {

				List<Long> usersFavID = new ArrayList<Long>();
				for (int i = 0; i < leasePPAMeterFav.size(); i++) {
					if (leasePPAMeterFav.get(i) != null
							&& leasePPAMeterFav.get(i).getAuthentificationEntity() != null) {
						usersFavID.add(leasePPAMeterFav.get(i).getAuthentificationEntity().getId());
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

	public String editUsersFavoriteList(Long idLeasePPAMeter, Long[] listUsers, String ipUser, String timeZoneUser,
			Long idUserCo, String numTab, String sessionId, String openDate) {
		try {

			LeasePPAMeter leasePPAMeter = leasePPAMeterRepo.findById(idLeasePPAMeter).orElseThrow(
					() -> new ResourceNotFoundException("LPPAM not found for this id :" + idLeasePPAMeter));
			for (int i = 0; i < listUsers.length; i++) {

				AuthentificationEntity user = authentificationRepo.findById(listUsers[i])
						.orElseThrow(() -> new ResourceNotFoundException("User not found for this id."));

				LeasePPAMeterFavLibraryEntity leasePPAMeterFav = new LeasePPAMeterFavLibraryEntity(user, leasePPAMeter);
				leasePPAMeterFav.setLeasePPAMeter(leasePPAMeter);
				leasePPAMeterFav.setAuthentificationEntity(user);
				leasePPAMetersFavoritesRepo.save(leasePPAMeterFav);

				historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;The favorite " + leasePPAMeter.getManufacturer() + " " + leasePPAMeter.getModel()
								+ " is added to the user " + user.getFirstName() + " " + user.getLastName()
								+ ".;Revenue or Performance Monitoring Meter",
						true, numTab, sessionId, openDate);
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add Revenue or Performance Monitoring Meter to Users Favorites List.;Revenue or Performance Monitoring Meter",
					false, numTab, sessionId, openDate);
			return "error";
		}
	}

	public List<LeasePPAMeterFavRequest> checkLeasePPAMeterExistent(LeasePPAMeterFavRequest newmodule, Long idUser) {
		List<LeasePPAMeterFavRequest> leasePPAMeterList = new ArrayList<>();
		try {
			List<LeasePPAMeter> listLeasePPAMeter = leasePPAMeterRepo.findAllByManufacturerAndModelAndIsDeleted(
					newmodule.getManufacturer().trim().toLowerCase(), newmodule.getModel().trim().toLowerCase(), false);
			List<LeasePPAMeter> listLeasePPAMeterNot = leasePPAMeterRepo.findAllByManufacturerNotAndModelAndIsDeleted(
					newmodule.getManufacturer().trim().toLowerCase(), newmodule.getModel().trim().toLowerCase(), false);
			if (listLeasePPAMeter != null && !listLeasePPAMeter.isEmpty()) {
				List<Long> leasePPAMeterFavID = getLeasePPAMeterFavorite(idUser);

				for (int i = 0; i < listLeasePPAMeter.size(); i++) {

					LeasePPAMeterFavRequest leasePPAMeter = new LeasePPAMeterFavRequest();
					if (listLeasePPAMeter.get(i) != null) {
						if (leasePPAMeterFavID != null
								&& leasePPAMeterFavID.contains(listLeasePPAMeter.get(i).getId())) {
							leasePPAMeter.setIsFav("true");
						} else {
							leasePPAMeter.setIsFav("false");
						}
						leasePPAMeter.setId(listLeasePPAMeter.get(i).getId());
						leasePPAMeter.setManufacturer(listLeasePPAMeter.get(i).getManufacturer());
						leasePPAMeter.setModel(listLeasePPAMeter.get(i).getModel());
					}
					leasePPAMeterList.add(i, leasePPAMeter);
				}
				return leasePPAMeterList;

			} else if (listLeasePPAMeterNot != null && !listLeasePPAMeterNot.isEmpty()) {

				List<Long> leasePPAMeterFavID = getLeasePPAMeterFavorite(idUser);

				for (int i = 0; i < listLeasePPAMeterNot.size(); i++) {

					LeasePPAMeterFavRequest leasePPAMeter = new LeasePPAMeterFavRequest();
					if (listLeasePPAMeterNot.get(i) != null) {
						if (leasePPAMeterFavID != null
								&& leasePPAMeterFavID.contains(listLeasePPAMeterNot.get(i).getId())) {
							leasePPAMeter.setIsFav("true");
						} else {
							leasePPAMeter.setIsFav("false");
						}
						leasePPAMeter.setId(listLeasePPAMeterNot.get(i).getId());
						leasePPAMeter.setManufacturer(listLeasePPAMeterNot.get(i).getManufacturer());
						leasePPAMeter.setModel(listLeasePPAMeterNot.get(i).getModel());
					}
					leasePPAMeterList.add(i, leasePPAMeter);
				}
				return leasePPAMeterList;

			} else {
				return leasePPAMeterList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return leasePPAMeterList;
		}

	}

	/*
	 * Add New LeasePPAMeter
	 */
	public LibrariesManagementModelResult addNewLeasePPAMeter(Long idPermit, LeasePPAMeterFavRequest leasePPAMeterReq,
			Long idUserCo) {

		LeasePPAMeter leasePPAMeter = new LeasePPAMeter();

		try {

			leasePPAMeter.setManufacturer(leasePPAMeterReq.getManufacturer());
			leasePPAMeter.setModel(leasePPAMeterReq.getModel());
			leasePPAMeter.setMappedValue(leasePPAMeterReq.getMappedValue());
			leasePPAMeter.setIncludeTLD(leasePPAMeterReq.getIncludeTLD());
			Date d = new Date();
			SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = formattedDATE.format(d);
			leasePPAMeter.setUpdated(updatedDate);

			AuthentificationEntity contractor = permitEntityRepo.findAuthentificationEntityByID(idPermit);

			AuthentificationEntity userCo = authentificationRepo.findById(idUserCo)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idUserCo));

			if (userCo.getRoleEntity().getId() == 1 || userCo.getRoleEntity().getId() == 3) {
				leasePPAMeter.setHasSuperUserEdit(true);
			} else {
				leasePPAMeter.setHasSuperUserEdit(false);
			}
			Date addDate = new Date();
			leasePPAMeter.setAddDate(addDate);
			LeasePPAMeterFavLibraryEntity leasePPAMeterFav = new LeasePPAMeterFavLibraryEntity(contractor,
					leasePPAMeter);
			leasePPAMeter.setAuthentificationEntity(userCo);
			leasePPAMetersFavoritesRepo.save(leasePPAMeterFav);
			leasePPAMeterRepo.save(leasePPAMeter);
			historicActivity.recordActivity(
					idUserCo, "", "", "libraries;Add component " + leasePPAMeterReq.getManufacturer() + " "
							+ leasePPAMeterReq.getModel() + ".;Revenue or Performance Monitoring Meter",
					true, "", "", "");
			return new LibrariesManagementModelResult(leasePPAMeter.getId(), leasePPAMeter.getManufacturer(),
					leasePPAMeter.getModel());

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Add component.;Revenue or Performance Monitoring Meter", false, "", "", "");
			return new LibrariesManagementModelResult(leasePPAMeter.getId(), leasePPAMeter.getManufacturer(),
					leasePPAMeter.getModel());

		}

	}

}
