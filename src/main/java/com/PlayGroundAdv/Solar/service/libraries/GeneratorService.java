package com.PlayGroundAdv.Solar.service.libraries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.libraries.Generator;
import com.PlayGroundAdv.Solar.entity.libraries.GeneratorFavorite;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.CompoentFavDto;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.ComponentResult;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.model.libraries.FavoriteListDto;
import com.PlayGroundAdv.Solar.model.libraries.GeneratorDto;
import com.PlayGroundAdv.Solar.model.libraries.GeneratorDtoResult;
import com.PlayGroundAdv.Solar.model.libraries.NewComponentRequest;
import com.PlayGroundAdv.Solar.repositories.libraries.GeneratorFavoriteRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.GeneratorRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class GeneratorService {

	final GeneratorRepository generatorRepo;
	final GeneratorFavoriteRepository generatorFavRepo;
	final AuthenticationRepository userRepo;
	final CheckValueTypesService checkValue;
	final ModelMapper modelMapper;
	final HistoryActivityService historicActivity;
	final LibrariesNotifService notifService;
	final CheckValueTypesService checkValueTypesService;
	final EquipmentUpdate mailingService;

	public GeneratorService(GeneratorRepository generatorRepo, GeneratorFavoriteRepository generatorFavRepo,
			AuthenticationRepository userRepo, CheckValueTypesService checkValue, ModelMapper modelMapper,
			HistoryActivityService historicActivity, LibrariesNotifService notifService, CheckValueTypesService checkValueTypesService,
			EquipmentUpdate mailingService) {
		super();
		this.generatorRepo = generatorRepo;
		this.generatorFavRepo = generatorFavRepo;
		this.userRepo = userRepo;
		this.checkValue = checkValue;
		this.modelMapper = modelMapper;
		this.historicActivity = historicActivity;
		this.notifService = notifService;
		this.checkValueTypesService = checkValueTypesService;
		this.mailingService = mailingService;
	}

//	Get Data List
	public Page<ComponentResult> getList(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("manufacturer").and(Sort.by("model")));
			if (Boolean.TRUE.equals(request.getIsFavorite())) {
				Page<Generator> page = generatorRepo.findByFavoriteIdUserIdAndDeleted(request.getIdUser(),
						request.getIsDeleted(), pageable);
				return convertDto(page, pageable, request.getIdUser(), true);
			} else {
				Page<Generator> page = generatorRepo.findByDeleted(request.getIsDeleted(), pageable);
				return convertDto(page, pageable, request.getIdUser(), false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<ComponentResult> convertDto(Page<Generator> page, Pageable pageable, Long idUser, Boolean isFavorite) {
		try {
			return new PageImpl<ComponentResult>(page.stream()
					.map(c -> new ComponentResult(c.getId(), c.getManufacturer(), c.getModel(),
							c.getManufacturerMappingValue(), c.getModelMappingValue(),
							c.getAddedBy().getFirstName() + " " + c.getAddedBy().getLastName(), c.getAddedBy().getId(),
							Boolean.TRUE.equals(isFavorite) ? true : isFavorite(c, idUser), c.getHasCorrectionRequest(), 
							c.getFirstUpdater() != null ? new UsersEntityResult(c.getFirstUpdater().getId(), c.getFirstUpdater().getFirstName(), c.getFirstUpdater().getLastName()) : null,
							c.getSecondUpdater() != null ? new UsersEntityResult(c.getSecondUpdater().getId(), c.getSecondUpdater().getFirstName(), c.getSecondUpdater().getLastName()) : null,
							c.getThirdUpdater() != null ? new UsersEntityResult(c.getThirdUpdater().getId(), c.getThirdUpdater().getFirstName(), c.getThirdUpdater().getLastName()) : null,
							c.getVerifiedBy() != null ? new UsersEntityResult(c.getVerifiedBy().getId(), c.getVerifiedBy().getFirstName(), c.getVerifiedBy().getLastName()) : null,
							c.getIsVerified(), c.getDateVerification())).collect(Collectors.toList()), pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean isFavorite(Generator c, Long idUser) {
		try {
			if (c.getFavorite().isEmpty()) {
				return false;
			} else {
				List<GeneratorFavorite> favList = new ArrayList<GeneratorFavorite>();
				favList.addAll(c.getFavorite());
				java.util.function.Predicate<GeneratorFavorite> filter = f -> f.getIdUser().getId().equals(idUser);
				return !favList.stream().filter(filter).collect(Collectors.toList()).isEmpty();

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	Search Data
	public Page<ComponentResult> searchList(ComponentPageRequest request) {
		try {

			String[] manufacturer = checkValue.isStringNotEmpty(request.getManufacturer())
					? request.getManufacturer().split(" ")
					: null;
			String[] model = checkValue.isStringNotEmpty(request.getModel()) ? request.getModel().split(" ") : null;
			if ((manufacturer == null) && (model == null)) {
				return getList(request);
			} else {
				Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
						Sort.by("manufacturer").and(Sort.by("model")));
				Page<Generator> page = generatorRepo.findAll(filter(manufacturer, model, request.getIsFavorite(),
						request.getIsDeleted(), request.getIdUser()), pageable);
				if (Boolean.TRUE.equals(request.getIsFavorite())) {
					return convertFavoriteDto(page, pageable, request.getIdUser());
				} else {
					return convertDto(page, pageable, request.getIdUser(), request.getIsFavorite());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public GeneratorDtoResult getById(Long modelId) {
		try {
			Generator c = generatorRepo.findById(modelId).orElseThrow(
					() -> new ResourceNotFoundException("Component not found for this id :" + modelId));
			
			GeneratorDtoResult dto =new GeneratorDtoResult(); 
			dto.setId(c.getId());
			dto.setManufacturer(c.getManufacturer());
			dto.setModel(c.getModel());
			dto.setManufacturerMappingValue(c.getManufacturerMappingValue());
			dto.setModelMappingValue(c.getModelMappingValue());
			dto.setRatedPower(c.getRatedPower());
			dto.setMaxContinuousOutput(c.getMaxContinuousOutput());
			dto.setRatedVoltage(c.getRatedVoltage());
			dto.setFuelType(c.getFuelType());
			dto.setIntegratedAutoTransferSwitch(c.getIntegratedAutoTransferSwitch());
			dto.setEmissionsComplianceRating(c.getEmissionsComplianceRating());
			dto.setIsFavorite(isFavorite(c, c.getAddedBy().getId()));
			dto.setIsVerified(c.getIsVerified());
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Page<ComponentResult> convertFavoriteDto(Page<Generator> page, Pageable pageable, Long idUser) {
		try {
			java.util.function.Predicate<Generator> favorite = c -> Boolean.TRUE.equals(isFavorite(c, idUser));
			return new PageImpl<ComponentResult>(page.stream().filter(favorite).collect(Collectors.toList()).stream()
					.map(c -> new ComponentResult(c.getId(), c.getManufacturer(), c.getModel(),
							c.getManufacturerMappingValue(), c.getModelMappingValue(),
							c.getAddedBy().getFirstName() + " " + c.getAddedBy().getLastName(), c.getAddedBy().getId(),
							true, c.getHasCorrectionRequest(), 
							c.getFirstUpdater() != null ? new UsersEntityResult(c.getFirstUpdater().getId(), c.getFirstUpdater().getFirstName(), c.getFirstUpdater().getLastName()) : null,
							c.getSecondUpdater() != null ? new UsersEntityResult(c.getSecondUpdater().getId(), c.getSecondUpdater().getFirstName(), c.getSecondUpdater().getLastName()) : null,
							c.getThirdUpdater() != null ? new UsersEntityResult(c.getThirdUpdater().getId(), c.getThirdUpdater().getFirstName(), c.getThirdUpdater().getLastName()) : null,
							c.getVerifiedBy() != null ? new UsersEntityResult(c.getVerifiedBy().getId(), c.getVerifiedBy().getFirstName(), c.getVerifiedBy().getLastName()) : null,
							c.getIsVerified(), c.getDateVerification())).collect(Collectors.toList()), pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	Filter Data
	private Specification<Generator> filter(String[] makes, String[] models, Boolean favorite, Boolean deleted, Long idUser) {
		return new Specification<Generator>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Generator> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesMake = new ArrayList<Predicate>();
				List<Predicate> predicatesModel = new ArrayList<Predicate>();
				for (int i = 0; makes != null && i < makes.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("manufacturer")),
							"%" + makes[i].toLowerCase() + "%");
					predicatesMake.add(predicate);
				}
				for (int i = 0; models != null && i < models.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("model")), "%" + models[i].toLowerCase() + "%");
					predicatesModel.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("deleted"), deleted);
				Predicate endPredicateMake = cb.or(predicatesMake.toArray(new Predicate[predicatesMake.size()]));
				Predicate endPredicateModel = cb.or(predicatesModel.toArray(new Predicate[predicatesModel.size()]));
				if (makes != null && models != null) {
					return cb.and(endPredicateMake, endPredicateModel, predicateDeleted);
				} else if (makes != null) {
					return cb.and(endPredicateMake, predicateDeleted);
				} else {
					return cb.and(endPredicateModel, predicateDeleted);
				}
			}
		};
	}

//	Add Component
	public FavoriteListDto addComponent(GeneratorDto request) {
		try {
			AuthentificationEntity user = userRepo.findById(request.getAddedBy()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + request.getAddedBy()));
			Generator c = new Generator(request.getManufacturer(), request.getModel(),
					getMappingValue(request.getManufacturer(), request.getManufacturerMappingValue()),
					getMappingValue(request.getModel(), request.getModelMappingValue()), 
					request.getRatedPower(), request.getMaxContinuousOutput(),request.getRatedVoltage(), request.getFuelType(),
					request.getIntegratedAutoTransferSwitch(), request.getEmissionsComplianceRating(),
					user);
					c.setIsVerified(false);
			generatorRepo.save(c);
			addToFav(new CompoentFavDto(request.getAddedTo() == null ? request.getAddedBy() : request.getAddedTo(),
					c.getId(), request.getAddedBy()));
			mailingService.mailUpdate("Generator", "A new equipment of Generator " + request.getManufacturer() + " "
					+ request.getModel() + " has been added by " + user.getFirstName() + " " + user.getLastName(),
					user.getEmail().contains("nuagetechnologies-tn.com") && !user.getEmail().contains("pm"));
			return modelMapper.map(c, FavoriteListDto.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	Edit Component
	public ComponentResult editComponent(GeneratorDto request) {
		try {
			AuthentificationEntity user = userRepo.findById(request.getAddedBy()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + request.getAddedBy()));
			Generator c = generatorRepo.findById(request.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Component not found for this id :" + request.getId()));
			
			AuthentificationEntity firstUpdater = userRepo.findById(request.getAddedBy()).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +request.getAddedBy()));


			c.setManufacturer(request.getManufacturer());
			c.setModel(request.getModel());
			c.setManufacturerMappingValue(
					getMappingValue(request.getManufacturer(), request.getManufacturerMappingValue()));
			c.setModelMappingValue(getMappingValue(request.getModel(), request.getModelMappingValue()));
			c.setUpdateDate(new Date());
			c.setRatedPower(request.getRatedPower());
			c.setMaxContinuousOutput(request.getMaxContinuousOutput());
			c.setRatedVoltage(request.getRatedVoltage());
			c.setFuelType(request.getFuelType());
			c.setIntegratedAutoTransferSwitch(request.getIntegratedAutoTransferSwitch());
			c.setEmissionsComplianceRating(request.getEmissionsComplianceRating());
			c.setUpdatedBy(user);
			c.setIsVerified(false);
			String updateNum = "";
			if(c.getFirstUpdater() == null) {
				updateNum = "1st";	
				c.setFirstUpdater(firstUpdater);
			}else if(c.getSecondUpdater() == null) {
				updateNum = "2nd";				
				c.setSecondUpdater(firstUpdater);
			}else if(c.getThirdUpdater() == null) {
				updateNum = "3rd";				
				c.setThirdUpdater(firstUpdater);
			}

			generatorRepo.save(c);

			notifService.editComponent(request.getAddedBy(), "Generator Update", "Generator", c.getManufacturer(), c.getModel());
			ComponentResult dto = modelMapper.map(c, ComponentResult.class);
			dto.setAddedBy(c.getAddedBy().getFirstName() + " " + c.getAddedBy().getLastName());
			dto.setAddedById(c.getAddedBy().getId());
			dto.setIsFavorite(isFavorite(c, request.getAddedBy()));
			dto.setHasCorrectionRequest(c.getHasCorrectionRequest());
			
			if (checkValueTypesService.isStringNotEmpty(updateNum)) {
				mailingService.mailUpdate("Generator",
						"An existing equipment of Generator " + c.getManufacturer() + " " + c.getModel()
								+ " has been updated " + updateNum + " time by " + firstUpdater.getFirstName() + " "
								+ firstUpdater.getLastName(),
								firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
			}

			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	Deleted Component
	public Boolean deleteComponent(NewComponentRequest request) {
		try {
			AuthentificationEntity user = userRepo.findById(request.getAddedBy()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + request.getAddedBy()));
			Generator c = generatorRepo.findById(request.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Component not found for this id :" + request.getId()));
			c.setDeleted(true);
			c.setDeletedDate(new Date());
			c.setDeletedBy(user);
			generatorRepo.save(c);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	Restore Component
	public String restoreComponent(NewComponentRequest request) {
		try {
			Boolean exist = false;
			AuthentificationEntity user = userRepo.findById(request.getAddedBy()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + request.getAddedBy()));
			Generator c = generatorRepo.findById(request.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Component not found for this id :" + request.getId()));
			exist = generatorRepo.existsByManufacturerAndModelAndDeleted(request.getManufacturer().trim(), request.getModel().trim(), false);
			if (Boolean.TRUE.equals(exist)) {
				return "exist";
			} else {
			c.setDeleted(false);
			c.setUpdateDate(new Date());
			c.setAddedBy(user);
			generatorRepo.save(c);
			return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

//	Add Component to Fav List
	public Boolean addToFav(CompoentFavDto request) {
		try {
			AuthentificationEntity user = userRepo.findById(request.getUserId()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + request.getUserId()));
			Generator c = generatorRepo.findById(request.getCompId()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + request.getCompId()));
			GeneratorFavorite fav = new GeneratorFavorite(user, c);
			c.getFavorite().add(fav);
			generatorRepo.save(c);
			// Activity Log
			String activityText = "libraries;The favorite " + c.getManufacturer() + " " + c.getManufacturer()
					+ " is added to the user " + user.getFirstName() + " " + user.getLastName() + ".;Generator";
			historicActivity.recordActivity(request.getUpdatedBy(), null, null, activityText, true, null, null, null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			String activityText = "libraries;The favorite " + request.getCompId() + " is added to the user "
					+ request.getUserId() + ".;Generator";
			historicActivity.recordActivity(request.getUpdatedBy(), null, null, activityText, false, null, null, null);
			return false;
		}
	}

	public Boolean addToMultipleFav(CompoentFavDto request) {
		try {
			for (Long id : request.getListUsers()) {
				addToFav(new CompoentFavDto(id, request.getCompId(), request.getUpdatedBy()));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	Remove Component from Fav List
	public Boolean removeFromFav(CompoentFavDto request) {
		try {
			GeneratorFavorite fav = generatorFavRepo.findByIdGeneratorIdAndIdUserId(request.getCompId(), request.getUserId());
			if (fav != null) {
				generatorFavRepo.delete(fav);
				// Activity Log
				AuthentificationEntity user = userRepo.findById(request.getUserId()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + request.getUserId()));
				Generator c = generatorRepo.findById(request.getCompId()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + request.getCompId()));
				String activityText = "libraries;The favorite " + c.getManufacturer() + " " + c.getManufacturer()
						+ " is removed from the user " + user.getFirstName() + " " + user.getLastName()
						+ " favorite List.;Generator";
				historicActivity.recordActivity(request.getUpdatedBy(), null, null, activityText, true, null, null,
						null);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			String activityText = "libraries;The favorite " + request.getCompId() + " is added to the user "
					+ request.getUserId() + ".;Generator";
			historicActivity.recordActivity(request.getUpdatedBy(), null, null, activityText, false, null, null, null);
			return false;
		}
	}

//	send Correction Request
	public Boolean sendCorrectionRequest(CorrectionRequest request) {

		try {
			Generator c = generatorRepo.findById(request.getId()).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + request.getId()));
			c.setHasCorrectionRequest(true);
			c.setEroneousContent(request.getEroneousContent());
			c.setEroneousContentOther(request.getEroneousContentOther());
			c.setEroneousDescription(request.getEroneousDescription());
			generatorRepo.save(c);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public CorrectionRequest getCorrectionRequest(Long modelId) {
		try {
			Generator c = generatorRepo.findById(modelId)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + modelId));
			if(checkValue.contains(c.getEroneousContent(), "::") && c.getEroneousContent().split("::")[0] != null)
				return new CorrectionRequest(modelId, c.getEroneousContent(),
							checkValue.Equals(c.getEroneousContent().split("::")[0], "Other") ? c.getEroneousContentOther()
									: null, c.getEroneousDescription());
			else return new CorrectionRequest();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProjectForLibrariesModel> getProjectList(Long modelId) {
		try {
			Generator c = generatorRepo.findById(modelId)
					.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + modelId));
			if (c.getProjects().isEmpty())
				return new ArrayList<>();

			List<ProjectForLibrariesModel> l = new ArrayList<>();
			for (PermitEnergyBatterySystem p : c.getProjects()) {
				l.add(new ProjectForLibrariesModel(p.getProject().getId(),
						p.getProject().getHomeOwnLastName(), p.getProject().getHomeOwnName(), 
						p.getProject().getProjectName(), p.getProject().getStatus(),
						p.getProject().getAuthentificationEntity().getFirstName(),
						p.getProject().getAuthentificationEntity().getLastName()));
			}
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<UsersEntityResult> getUsersForFavList(Long id, Long userId) {
		try {
			List<Long> favUserIds = generatorFavRepo.findFavoriteUserId(id);
			favUserIds.add(userId);
			return userRepo.findUserHaveNotFav(favUserIds);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	
	public ResponseEntity<?> checkExist(NewComponentRequest model) {
		try {
			Boolean exist = false;
			if (checkValue.isLongPositive(model.getId())) {
				exist = generatorRepo.existsByManufacturerAndModelAndDeletedAndIdNot(model.getManufacturer().trim(), model.getModel().trim(), false, model.getId());
			}else {
				exist = generatorRepo.existsByManufacturerAndModelAndDeleted(model.getManufacturer().trim(), model.getModel().trim(), false);
			}
			if (Boolean.TRUE.equals(exist)) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				List<Generator> list = generatorRepo.findByModel(model.getModel());
				if (list.isEmpty()) {
					return new ResponseEntity<>(false, HttpStatus.OK);
				} else {
					List<FavoriteListDto> generator = list.stream().
							map(c -> new FavoriteListDto(c.getId(), c.getManufacturer(), c.getModel(), isFavorite(c, model.getAddedTo())))
							.collect(Collectors.toList());
					
					return new ResponseEntity<>(generator, HttpStatus.OK);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}
	private String getMappingValue(String v, String mappingV) {
		return Boolean.TRUE.equals(checkValue.isStringNotEmpty(mappingV)) ? mappingV : v;
	}
}
