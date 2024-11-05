package com.PlayGroundAdv.Solar.service.libraries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.PermitAdditionalInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.TiltLegs;
import com.PlayGroundAdv.Solar.entity.TiltLegsFavLibraryEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.ModuleFavRequest;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchTiltLegsRequest;
import com.PlayGroundAdv.Solar.model.SearchTiltLegsResult;
import com.PlayGroundAdv.Solar.model.TiltLegsCorrectionRequest;
import com.PlayGroundAdv.Solar.model.TiltLegsFavRequest;
import com.PlayGroundAdv.Solar.model.TiltLegsListModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.repositories.PermitAdditionalInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.EquipmentUpdate;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;


@Service
@Transactional
public class GetTiltLegsLibraryService {

	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final TiltLegsRepository tiltLegsRepo;
	final private ModelMapper modelMapper;
	final AuthenticationRepository authentificationRepo;
	final TiltLegsFavoritesRepository tiltLegssFavoritesRepo;
	final PermitArraysRepository permitArraysEntityRepo;
	final PermitRepository permitEntityRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo;
	final PermitAdditionalInfoRepository permitAdditionalInfoEntityRepo;
	final EquipmentUpdate mailingService;
	
    public GetTiltLegsLibraryService(HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			TiltLegsRepository tiltLegsRepo, ModelMapper modelMapper, AuthenticationRepository authentificationRepo,
			TiltLegsFavoritesRepository tiltLegssFavoritesRepo, PermitArraysRepository permitArraysEntityRepo,
			PermitRepository permitEntityRepo, PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo,
			PermitAdditionalInfoRepository permitAdditionalInfoEntityRepo, EquipmentUpdate mailingService) {
		super();
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.tiltLegsRepo = tiltLegsRepo;
		this.modelMapper = modelMapper;
		this.authentificationRepo = authentificationRepo;
		this.tiltLegssFavoritesRepo = tiltLegssFavoritesRepo;
		this.permitArraysEntityRepo = permitArraysEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.permitProjectSiteInfoEntityRepo = permitProjectSiteInfoEntityRepo;
		this.permitAdditionalInfoEntityRepo = permitAdditionalInfoEntityRepo;
		this.mailingService = mailingService;
	}




	public TiltLegsListModel filterTiltLegs(SearchTiltLegsRequest searchTiltLegsRequest, Long idUser, Integer page, Integer size) {
	     TiltLegsListModel tiltLegsList = new TiltLegsListModel();
		try {
			String manufacturerString = checkValueTypesService.isStringNotEmpty(searchTiltLegsRequest.getManufacturer()) ?  searchTiltLegsRequest.getManufacturer().trim() : "";
			String modelString = checkValueTypesService.isStringNotEmpty(searchTiltLegsRequest.getModel()) ?  searchTiltLegsRequest.getModel().trim() : "";
			
			
			String[] manufacturer = manufacturerString.split(" ");
			String[] model = modelString.split(" ");
			
			Boolean favorite = searchTiltLegsRequest.getIsFav();
			Boolean deleted = searchTiltLegsRequest.getIsDel();
			List<Long> tiltLegsFavID = getTiltLegsFavorite(idUser);
			List<Long> getTiltLegsFavID = new ArrayList<Long>();
			getTiltLegsFavID = favorite.equals(true) ? tiltLegsFavID : null;
			
			List<Long> tiltLegssID =  new ArrayList<Long>();
			
            if (favorite.equals(true) && getTiltLegsFavID.size() == 0) {
				
            	tiltLegsList.setTiltLegsList(null);
            	tiltLegsList.setTotalPages(0);
				return tiltLegsList;
				
			}else if (manufacturer != null && manufacturer.length > 1 && model != null && model.length > 1) {
				
				for (int i = 0; i < manufacturer.length; i++) {
					for (int j = 0; j < model.length; j++) {
						List<Long> tiltLegssIDReq =  tiltLegsRepo.filterTiltLegsListModel(manufacturer[i].trim(),model[j].trim(),tiltLegssID != null && tiltLegssID.size() > 0 ? tiltLegssID : null, deleted, getTiltLegsFavID);
						tiltLegssID.addAll(tiltLegssIDReq);
					}
				}
				
			} else if (manufacturer != null && manufacturer.length > 1){
				
				for (int i = 0; i < manufacturer.length; i++) {
					List<Long> tiltLegssIDReq =  tiltLegsRepo.filterTiltLegsListModel(manufacturer[i].trim(),modelString,tiltLegssID != null && tiltLegssID.size() > 0 ? tiltLegssID : null, deleted, getTiltLegsFavID);
					tiltLegssID.addAll(tiltLegssIDReq);
				}
				
			} else if (model != null && model.length > 1){
				
				for (int i = 0; i < model.length; i++) {
					List<Long> tiltLegssIDReq =  tiltLegsRepo.filterTiltLegsListModel(manufacturerString,model[i].trim(),tiltLegssID != null && tiltLegssID.size() > 0 ? tiltLegssID : null, deleted, getTiltLegsFavID);
					tiltLegssID.addAll(tiltLegssIDReq);
				}
				
			} else{
				tiltLegssID = tiltLegsRepo.filterTiltLegsListModel(manufacturerString,modelString,tiltLegssID != null && tiltLegssID.size() > 0 ? tiltLegssID : null, deleted, getTiltLegsFavID);
			}
		
			Pageable pageable = PageRequest.of(page, size, Sort.by("Manufacturer").and(Sort.by("model")));
			Page<TiltLegs> tiltLegss = tiltLegsRepo.findByIdIn(tiltLegssID, pageable);


			List<SearchTiltLegsResult> tiltLegsDTOs = new ArrayList<SearchTiltLegsResult>();
		    for (TiltLegs mod : tiltLegss.getContent()) {
		    	SearchTiltLegsResult tiltLegsDto = modelMapper.map(mod, SearchTiltLegsResult.class);
		    	tiltLegsDto.setFullName(mod.getAuthentificationEntity().getFirstName()+" "+mod.getAuthentificationEntity().getLastName());
		    	tiltLegsDto.setIsFav(tiltLegsFavID.indexOf(mod.getId()) != -1);
		    	UsersEntityResult owner = new UsersEntityResult();
				owner.setId(mod.getAuthentificationEntity().getId());
				owner.setFirstName(mod.getAuthentificationEntity().getFirstName());
				owner.setLastName(mod.getAuthentificationEntity().getLastName());
		    	tiltLegsDto.setOwner(owner);
		    	tiltLegsDto.setFirstUpdater(mod.getFirstUpdater() != null ? new UsersEntityResult(mod.getFirstUpdater().getId() ,mod.getFirstUpdater().getFirstName(),mod.getFirstUpdater().getLastName()) : null);
		    	tiltLegsDto.setSecondUpdater(mod.getSecondUpdater() != null ? new UsersEntityResult(mod.getSecondUpdater().getId(),mod.getSecondUpdater().getFirstName(),mod.getSecondUpdater().getLastName()): null);
		    	tiltLegsDto.setThirdUpdater(mod.getThirdUpdater() != null ? new UsersEntityResult(mod.getThirdUpdater().getId(),mod.getThirdUpdater().getFirstName(),mod.getThirdUpdater().getLastName()): null);
		    	tiltLegsDto.setVerifiedBy(mod.getVerifiedBy() != null ? new UsersEntityResult(mod.getVerifiedBy().getId(),mod.getVerifiedBy().getFirstName(),mod.getVerifiedBy().getLastName()): null);

		    	tiltLegsDTOs.add(tiltLegsDto);
			}
			tiltLegsList.setTiltLegsList(tiltLegsDTOs);
			tiltLegsList.setTotalPages(tiltLegss.getTotalPages());
			tiltLegsList.setTotalRecords(tiltLegss.getTotalElements()); 
			
			return tiltLegsList;
		} catch (Exception e) {
			e.printStackTrace();
			tiltLegsList.setTiltLegsList(null);
			tiltLegsList.setTotalPages(0);
			return tiltLegsList;
			
		}
		
	}
	
	
	
	
public String sendCorrectionTiltLegsRequest(TiltLegsCorrectionRequest component) {
		
		try {
			if (component.getId()!= 0){
				AuthentificationEntity user = authentificationRepo.findById(component.getIdUser()).get();
				TiltLegs library =tiltLegsRepo.findById(component.getId()).get();
				library.setHasCorrectionRequest(true);
				library.setEroneousContent(component.getEroneousContent());
				library.setEroneousContentOther(component.getEroneousContentOther());
				library.setEroneousDescription(component.getEroneousDescription());
				tiltLegsRepo.save(library);
				notificationEntityService.addNewNotif(component.getIdUser(),0L,"Request Correction","Libraries","Request Correction - "+library.getModel(),
						"The user " + user.getFirstName() + " "+ user.getLastName()+ " request correction for the Tilt Legs "+library.getManufacturer()+" "+library.getModel(),true);
						
			}
			return "Done";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	
	}

	/*
	 * Get tiltLegsFav
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getTiltLegsFavorite(Long IdContractor) {
		List<Long> tiltLegsFavID = new ArrayList<Long>();
		try {

			List<TiltLegsFavLibraryEntity> tiltLegsFav = tiltLegssFavoritesRepo.findAllByAuthentificationEntityId(IdContractor);
			if (tiltLegsFav != null && tiltLegsFav.size() != 0) {
				
				
				for (int i = 0; i < tiltLegsFav.size(); i++) {
					if(tiltLegsFav.get(i) != null && tiltLegsFav.get(i).getTiltLegs() != null) {
					    tiltLegsFavID.add(tiltLegsFav.get(i).getTiltLegs().getId());
					}
				}
				return tiltLegsFavID;
			} else
				return tiltLegsFavID;

		} catch (Exception e) {
			e.printStackTrace();
			return tiltLegsFavID;
		}

	}

	/*
	 * Add tiltLegsFav
	 */
	public String addTiltLegsFavorite(Long IdContractor, Long IdTiltLegs,Long IdOwner) {

		try {
			
			AuthentificationEntity contractor = new AuthentificationEntity();
			TiltLegs tiltLegs = new TiltLegs();
			try {
				contractor = authentificationRepo.findById(IdOwner).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				tiltLegs = tiltLegsRepo.findById(IdTiltLegs).get();
			} catch (Exception e) {
				historicActivity.recordActivity(IdContractor, "", "",
						"libraries;Add Tilt Legs to Users Favorites List;Tilt Legs", false,"","","");
				e.printStackTrace();
			}
			TiltLegsFavLibraryEntity tiltLegsFav = new TiltLegsFavLibraryEntity(contractor, tiltLegs);
			tiltLegsFav.setAuthentificationEntity(contractor);
			tiltLegsFav.setTiltLegs(tiltLegs);
			tiltLegssFavoritesRepo.save(tiltLegsFav);
			historicActivity.recordActivity(IdContractor, "", "",
					"libraries;The favorite " + tiltLegs.getManufacturer() + " " + tiltLegs.getModel() + " is added to the user "
							+ contractor.getFirstName() + " " + contractor.getLastName() + ".;Tilt Legs"
							,
					true,"","","");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(IdContractor, "", "",
					"libraries;Add Tilt Legs to Users Favorites List;Tilt Legs", false,"","","");
			e.printStackTrace();
			return "Fail";
		}
	}

	/*
	 * Delete tiltLegsFav
	 */
	public String removeTiltLegsFavorite(Long IdContractor, Long IdTiltLegs,Long IdOwner) {

		try {
			AuthentificationEntity contractor = authentificationRepo.findById(IdOwner).get();
					
			TiltLegsFavLibraryEntity tiltLegsFav = tiltLegssFavoritesRepo.findOneByAuthentificationEntityIdAndTiltLegsId(IdOwner, IdTiltLegs);
				List<PermitAdditionalInfoEntity> permitArray1=permitAdditionalInfoEntityRepo.findByPermitEntityAuthentificationEntityIdAndTiltLegsMod(IdOwner,IdTiltLegs+"");
				for (int i = 0; permitArray1.size()>0 &&   i < permitArray1.size(); i++) {
					permitArray1.get(i).setTiltLegsMod("Fav Removed");
					permitAdditionalInfoEntityRepo.save(permitArray1.get(i));
				}	
				tiltLegssFavoritesRepo.delete(tiltLegsFav);
			historicActivity.recordActivity(IdContractor, "", "",
					"libraries;The favorite " + tiltLegsFav.getTiltLegs().getManufacturer() + " " + tiltLegsFav.getTiltLegs().getModel() + " is removed from the user "
							+ contractor.getFirstName() + " " + contractor.getLastName() + ".;Tilt Legs"
							,
					true,"","","");
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(IdContractor, "", "",
					"libraries;Remove Tilt Legs from Users Favorites List;Tilt Legs", false,"","","");
			e.printStackTrace();
			return "Fail";
		}

	}

	/*
	 * Get Contractor tiltLegsFav
	 */
	public List<TiltLegs> getContractorTiltLegsFav(Long IdContractor) {
		List<TiltLegs> tiltLegsContractorFav = new ArrayList<TiltLegs>();
		try {
		
			List<Long> tiltLegsFavID = new ArrayList<Long>();
			tiltLegsFavID = getTiltLegsFavorite(IdContractor);
			if (tiltLegsFavID != null && tiltLegsFavID.size() > 0) {
				for (int i = 0; i < tiltLegsFavID.size(); i++) {
					TiltLegs tiltLegs = new TiltLegs();
					try {
						tiltLegs = tiltLegsRepo.findById(tiltLegsFavID.get(i)).get();
					} catch (Exception e) {
						e.printStackTrace();
					}
					tiltLegsContractorFav.add(tiltLegs);
				}
			} else
				tiltLegsContractorFav = null;

			return tiltLegsContractorFav;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tiltLegsContractorFav;
		}
	}

	/*
	 * Get Contractor tiltLegsFav
	 */
	public List<TiltLegs> getSuperUserTiltLegsFav(Long permitId) {
		List<TiltLegs> tiltLegsContractorFav = new ArrayList<TiltLegs>();
		try {
			PermitEntity permit = permitEntityRepo.findById(permitId).get();
			Long idUser = permit.getAuthentificationEntity().getId();

			List<Long> tiltLegsFavID = new ArrayList<Long>();
			tiltLegsFavID = getTiltLegsFavorite(idUser);
			if (tiltLegsFavID != null && tiltLegsFavID.size() > 0) {
				for (int i = 0; i < tiltLegsFavID.size(); i++) {
					TiltLegs tiltLegs = new TiltLegs();
					try {
						tiltLegs = tiltLegsRepo.findById(tiltLegsFavID.get(i)).get();
					} catch (Exception e) {
						e.printStackTrace();
					}
					tiltLegsContractorFav.add(tiltLegs);
				}
			} else
				tiltLegsContractorFav = null;

			return tiltLegsContractorFav;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tiltLegsContractorFav;
		}
	}

	public boolean getTestTiltLegsFav(Long permitId, String testMan) {

		try {
			int test = 0;
			PermitEntity permit = permitEntityRepo.findById(permitId).get();
			Long idUser = permit.getAuthentificationEntity().getId();

			List<TiltLegs> tiltLegsContractorFav = new ArrayList<TiltLegs>();

			List<Long> tiltLegsFavID = new ArrayList<Long>();
			tiltLegsFavID = getTiltLegsFavorite(idUser);

			if(tiltLegsFavID != null) {
				for (int i = 0; i < tiltLegsFavID.size(); i++) {
					TiltLegs tiltLegs = new TiltLegs();
					try {
						tiltLegs = tiltLegsRepo.findById(tiltLegsFavID.get(i)).get();
					} catch (Exception e) {
						e.printStackTrace();
					}
					tiltLegsContractorFav.add(tiltLegs);
				}
			}

			if(tiltLegsContractorFav != null) {
				for (int i = 0; i < tiltLegsContractorFav.size(); i++) {
					if (tiltLegsContractorFav.get(i) != null
							&& checkValueTypesService.Equals(testMan, tiltLegsContractorFav.get(i).getManufacturer() + ":"
									+ tiltLegsContractorFav.get(i).getModel())) {
						test = 1;
					}
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
	 * Add tiltLegs
	 */
	@SuppressWarnings("unchecked")
	public String addTiltLegs(Long IdContractor, TiltLegsFavRequest tiltLegsReq, String ipUser, String timeZoneUser,
			Long idUser) {

		try {

			List<String> tiltLegss = tiltLegsRepo.findModelAndManufacturer(tiltLegsReq.getManufacturer(), tiltLegsReq.getModel());
			if (tiltLegss.size() == 0) {
				TiltLegs tiltLegs = new TiltLegs();
				tiltLegs.setManufacturer(tiltLegsReq.getManufacturer());
				tiltLegs.setModel(tiltLegsReq.getModel());
				tiltLegs.setWeight(tiltLegsReq.getWeight());
				tiltLegs.setHasCorrectionRequest(tiltLegsReq.getHasCorrectionRequest());
				tiltLegs.setEroneousContent(tiltLegsReq.getEroneousContent());
				tiltLegs.setEroneousContentOther(tiltLegsReq.getEroneousContentOther());
				tiltLegs.setEroneousDescription(tiltLegsReq.getEroneousDescription());
				Date d = new Date();
				SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
				String updatedDate = FormattedDATE.format(d);
				tiltLegs.setUpdated(updatedDate);
				
				AuthentificationEntity contractor = new AuthentificationEntity();
				try {
					contractor = authentificationRepo.findById(IdContractor).get();
				} catch (Exception e) {
					e.printStackTrace();
				}
				TiltLegsFavLibraryEntity tiltLegsFav = new TiltLegsFavLibraryEntity(contractor, tiltLegs);
				tiltLegsFav.setAuthentificationEntity(contractor);
				tiltLegsFav.setTiltLegs(tiltLegs);
				tiltLegs.setAuthentificationEntity(contractor);
				tiltLegs.setIsVerified(false);
				if(contractor.getRoleEntity().getId()== 1 || contractor.getRoleEntity().getId()== 3) {
					tiltLegs.setHasSuperUserEdit(true);
		 		}else {
		 			tiltLegs.setHasSuperUserEdit(false);
		 		}
				tiltLegssFavoritesRepo.save(tiltLegsFav);
				tiltLegsRepo.save(tiltLegs);
				mailingService.mailUpdate("Tilt Legs",
						"A new equipment of Tilt Legs " + tiltLegsReq.getManufacturer() + " "
								+ tiltLegsReq.getManufacturer() + " has been added by " + contractor.getFirstName()
								+ " " + contractor.getLastName(),
								contractor.getEmail().contains("nuagetechnologies-tn.com") && !contractor.getEmail().contains("pm"));
				historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
						"libraries;Add component " + tiltLegsReq.getManufacturer() + " " + tiltLegsReq.getModel()+ ".;Tilt Legs"
								,
						true,"","","");
				return "Done";
			} else
				historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
						"Add TiltLegs Component Library;Tilt Legs already exists in data sources;Add failed ", false,"","","");
			return "TiltLegs already exists in data sources";

		} catch (Exception e) {
			historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
					"libraries;Add component.;Tilt Legs"
							,
					false,"","","");
			e.printStackTrace();
			return "error";
		}
	}

	/*
	 * get Total Number of TiltLegs ( function "Long getNumTiltLegsTot() " removed )
	 */
	

	/*
	 * edit TiltLegs
	 */
	public String editTiltLegs(TiltLegsFavRequest tiltLegs,Long UserID) {
		try {

			TiltLegs testTiltLegs = new TiltLegs();
			if (tiltLegsRepo.countByManufacturerAndModelAndIsDeleted(tiltLegs.getManufacturer(),tiltLegs.getModel(),false) != 0) {
				testTiltLegs = tiltLegsRepo.findByManufacturerAndModelAndIsDeleted(tiltLegs.getManufacturer(),tiltLegs.getModel(),false);
				if (testTiltLegs != null && !checkValueTypesService.Equals(testTiltLegs.getId(),tiltLegs.getId())) {
					return "exist";
				} else {
					return editTiltLegsFunction(tiltLegs, UserID);
				}
			} else {
				return editTiltLegsFunction(tiltLegs, UserID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editTiltLegsFunction(TiltLegsFavRequest tiltLegs,Long UserID) {

		try {
			AuthentificationEntity firstUpdater = authentificationRepo.findById(UserID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +UserID));

			TiltLegs updatedTiltLegs = new TiltLegs();
			updatedTiltLegs = tiltLegsRepo.findById(tiltLegs.getId()).get();
			// manufacturer or model has change ---> update (manufacturer,model) in all the project using the edited tiltLegs
			List<PermitAdditionalInfoEntity> PermitAdditionalInfoEntitys = permitAdditionalInfoEntityRepo.findByTiltLegsMod(Long.toString(updatedTiltLegs.getId()));
			if (PermitAdditionalInfoEntitys != null && PermitAdditionalInfoEntitys.size() > 0) {
			
				for (PermitAdditionalInfoEntity PermitAdditionalInfoEntity : PermitAdditionalInfoEntitys) {
					if(PermitAdditionalInfoEntity != null &&  PermitAdditionalInfoEntity.getTiltLegs()!= null ) {
					PermitAdditionalInfoEntity.setTiltLegsMod(Long.toString(tiltLegs.getId()));
					}
					
				}
			}
			//end
			Date today = new Date();

			String day = "";
			if (today.getDate() < 10) {
				day = "0" + today.getDate();
			} else
				day = today.getDate() + "";

			String month = "";
			if ((today.getMonth() + 1) < 10) {
				month = "0" + (today.getMonth() + 1);
			} else
				month = (today.getMonth() + 1) + "";

			String year = (today.getYear() + 1900) + "";

			updatedTiltLegs.setId(tiltLegs.getId());
			updatedTiltLegs.setManufacturer(tiltLegs.getManufacturer());
			updatedTiltLegs.setModel(tiltLegs.getModel());
			updatedTiltLegs.setWeight(tiltLegs.getWeight());
			updatedTiltLegs.setHasCorrectionRequest(tiltLegs.getHasCorrectionRequest());
			updatedTiltLegs.setEroneousContent(tiltLegs.getEroneousContent());
			updatedTiltLegs.setEroneousContentOther(tiltLegs.getEroneousContentOther());
			updatedTiltLegs.setEroneousDescription(tiltLegs.getEroneousDescription());
			
			
			updatedTiltLegs.setUpdated(month + "/" + day + "/" + year);
			
			updatedTiltLegs.setIsVerified(false);
			String updateNum = "";
			if(updatedTiltLegs.getFirstUpdater() == null) {
				updateNum = "1st";	
				updatedTiltLegs.setFirstUpdater(firstUpdater);
			}else if(updatedTiltLegs.getSecondUpdater() == null) {
				updateNum = "2nd";				
				updatedTiltLegs.setSecondUpdater(firstUpdater);
			}else if(updatedTiltLegs.getThirdUpdater() == null) {
				updateNum = "3rd";				
				updatedTiltLegs.setThirdUpdater(firstUpdater);
			}


			
			tiltLegsRepo.save(updatedTiltLegs);
			historicActivity.recordActivity(UserID, "", "",
					"libraries;Edit component " + tiltLegs.getManufacturer() + " " + tiltLegs.getModel()+ ".;Tilt Legs"
							,
					true,"","","");
			
			
			if (checkValueTypesService.isStringNotEmpty(updateNum)) {
				mailingService.mailUpdate("Tilt Legs",
						"An existing equipment of Tilt Legs " + tiltLegs.getManufacturer() + " " + tiltLegs.getModel()
								+ " has been updated " + updateNum + " time by " + firstUpdater.getFirstName() + " "
								+ firstUpdater.getLastName(),
								firstUpdater.getEmail().contains("nuagetechnologies-tn.com") && !firstUpdater.getEmail().contains("pm"));
			}

			
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(UserID, "", "",
					"libraries;Edit component.;Tilt Legs"
							,
					false,"","","");
			return "fail";
		}
	}
	
	public String editTiltLegsNotification(Long UserID,String tiltLegsManufacturer,String tiltLegsModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(UserID).get();
			notificationEntityService.addNewNotif(UserID, 0L, "Tilt Legs Update","Libraries","Tilt Legs Update - " + tiltLegsModel, 
					"The tilt Legs " + tiltLegsModel + " " +tiltLegsManufacturer + " was updated by "+userCo.getFirstName()+" "+userCo.getLastName() , true);
			
			 return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
	
	public String addTiltLegsNotification(Long UserID,String tiltLegsManufacturer,String tiltLegsModel) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(UserID).get();
			notificationEntityService.addNewNotif(UserID, 0L, "New Tilt Legs","Libraries","New Tilt Legs- " + tiltLegsModel, 
					"The tilt Legs " + tiltLegsModel + " " +tiltLegsManufacturer + " was added by "+userCo.getFirstName()+" "+userCo.getLastName() , true);
			
			 return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
	// CR 924
	// Change Request TiltLegs List Management
	
	public String tiltLegsLibraryActived(Long idTiltLegs,Long UserID) {

		try {

			TiltLegs tiltLegsLibrary = tiltLegsRepo.findById(idTiltLegs).get();
			if (tiltLegsLibrary.getId() != 0) {
				if (tiltLegsRepo.countByManufacturerAndModelAndIsDeleted(tiltLegsLibrary.getManufacturer(), tiltLegsLibrary.getModel(), false) != 0) {
					return "exist";
				}
				else {
				tiltLegsLibrary.setDeleted(false);
				historicActivity.recordActivity(UserID, "", "",
						"libraries;Activate component " + tiltLegsLibrary.getManufacturer() + " " + tiltLegsLibrary.getModel()+ ".;Tilt Legs"
								,
						true,"","","");
				return "true";
				}
			}
			historicActivity.recordActivity(UserID, "", "",
					"libraries;Activate component " + tiltLegsLibrary.getManufacturer() + " " + tiltLegsLibrary.getModel()+ ".;Tilt Legs"
							,
					
false,"","","");
			return "false";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			historicActivity.recordActivity(UserID, "", "",
					"libraries;Activate component.;Tilt Legs"
							,
					
false,"","","");
			return "false";
		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfTiltLegsDeleted(Long idTiltLegs) {
		
		try {
			return permitAdditionalInfoEntityRepo.findByTiltLegsMod1(idTiltLegs+"");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<PermitEntity> getAllPermitOfTiltLegsDeleted1(Long idTiltLegs){
		List<PermitEntity> resPermit=new ArrayList<PermitEntity>(); 
		try {
			TiltLegs tiltLegsLibrary = tiltLegsRepo.findById(idTiltLegs).get();

			/*****************Get all permit Array content this model*****************/
			
			
			List<PermitAdditionalInfoEntity> allpermitProjectSiteInfo=permitAdditionalInfoEntityRepo.findByTiltLegsMod(tiltLegsLibrary.getId()+"");
			
			/**************Get all permit content this model***************************/
			
			
			/******************test on the state of Permit**************/
			if (tiltLegsLibrary != null && tiltLegsLibrary.getId() !=0) {
				if(allpermitProjectSiteInfo != null && allpermitProjectSiteInfo.size() !=0) {
					for (int i=0;i<allpermitProjectSiteInfo.size();i++) {
						if(allpermitProjectSiteInfo.get(i) != null && allpermitProjectSiteInfo.get(i).getPermitEntity() != null) {
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
				
			}else return null;
				 

			return resPermit;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return resPermit;
		}
	}

	
	public boolean deleteTiltLegsLibrary(Long id,Long UserID) {
         
		try {
			
			TiltLegs tiltLegsLibrary =tiltLegsRepo.findById(id).get();
			if (id != 0){
				List<PermitEntity> listPermitDeleted=getAllPermitOfTiltLegsDeleted1(id);
				PermitAdditionalInfoEntity permitProjectSiteInfo=null;
				TiltLegs tiltLegsLibraryRes =tiltLegsRepo.findById(id).get();
			if (listPermitDeleted != null) {
				for (PermitEntity permitEntity : listPermitDeleted) {
					if (permitAdditionalInfoEntityRepo.countByPermitEntityAndTiltLegsMod(permitEntity, tiltLegsLibraryRes.getId()+"") !=0) {
						permitProjectSiteInfo=permitAdditionalInfoEntityRepo.findOneByPermitEntityAndTiltLegsMod(permitEntity, tiltLegsLibraryRes.getId()+"");
						permitProjectSiteInfo.setTiltLegsMod("Removed");
					}
					
					
					
				}
			}
				
				
				
				 tiltLegsLibrary.setDeleted(true);
				 List<TiltLegsFavLibraryEntity> tiltLegsFav = tiltLegssFavoritesRepo.findAllByTiltLegsId(id);
					if (tiltLegsFav != null && tiltLegsFav.size()!=0) {
						for (TiltLegsFavLibraryEntity TiltLegsFavLibraryEntity : tiltLegsFav) {
							tiltLegssFavoritesRepo.delete(TiltLegsFavLibraryEntity);
						}
					}
					
					
					historicActivity.recordActivity(UserID, "", "",
							"libraries;Delete component " + tiltLegsLibrary.getManufacturer() + " " + tiltLegsLibrary.getModel()+ ".;Tilt Legs"
									,
							true,"","","");
					
					return true;
				
				
				
			}
			historicActivity.recordActivity(UserID, "", "",
					"libraries;Delete component " + tiltLegsLibrary.getManufacturer() + " " + tiltLegsLibrary.getModel()+ ".;Tilt Legs"
							,
					false,"","","");
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			historicActivity.recordActivity(UserID, "", "",
					"libraries;Delete component.;Tilt Legs"
							,
					false,"","","");
			return false;
		}
	}

	/*
	 * Edit TiltLegs Favorite for Other Users
	 */

	public List<UsersEntityResult> getUsersForFavList(Long IdTiltLegs, Long UserID) {

		List<UsersEntityResult> usersList = new ArrayList<>();
		try {
			List<TiltLegsFavLibraryEntity> tiltLegsFav = tiltLegssFavoritesRepo.findAllByTiltLegsId(IdTiltLegs);
			List<Long> usersFavID = new ArrayList<Long>();
			if (tiltLegsFav!=null && tiltLegsFav.size() > 0) {
				
				for (int i = 0; i < tiltLegsFav.size(); i++) {
					if(tiltLegsFav.get(i) != null && tiltLegsFav.get(i).getAuthentificationEntity() != null) {
					   usersFavID.add(tiltLegsFav.get(i).getAuthentificationEntity().getId());
					}
				}
				usersList = authentificationRepo.findUserHaveNotFav(usersFavID, false, true, UserID);
			} else {
                usersList = authentificationRepo.findUserHaveNotFav(null, false, true, UserID);
			}
			return usersList;

		} catch (Exception e) {
			e.printStackTrace();
			return usersList;
		}

	}

	public String editUsersFavoriteList(Long IdTiltLegs, Long[] ListUsers, String ipUser, String timeZoneUser,
			Long idUserCo) {
		try {


			TiltLegs tiltLegs = tiltLegsRepo.findById(IdTiltLegs).get();
			for (int i = 0; i < ListUsers.length; i++) {

				AuthentificationEntity user = authentificationRepo.findById(ListUsers[i]).get();

				TiltLegsFavLibraryEntity TiltLegsFav = new TiltLegsFavLibraryEntity(user, tiltLegs);
				TiltLegsFav.setTiltLegs(tiltLegs);
				TiltLegsFav.setAuthentificationEntity(user);
				tiltLegssFavoritesRepo.save(TiltLegsFav);
				historicActivity.recordActivity(idUserCo, "", "",
						"libraries;The favorite " + tiltLegs.getManufacturer() + " " + tiltLegs.getModel() + " is added to the user "
								+ user.getFirstName() + " " + user.getLastName() + ".;Tilt Legs"
								,
						true,"","","");
			}

			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add TiltLegs to Users Favorites List.;Tilt Legs", false,"","","");
			return "error";
		}
	}
	
	
	public List<TiltLegsFavRequest> checkTiltLegsExistent(TiltLegsFavRequest newmodule, Long idUser) {
		List<TiltLegsFavRequest> tiltLegsList = new ArrayList<TiltLegsFavRequest>();
		try {

			if (tiltLegsRepo.countByManufacturerAndModelAndIsDeleted(newmodule.getManufacturer(), newmodule.getModel(), false) != 0) {
				List<TiltLegs> listTiltLegs = tiltLegsRepo.findAllByManufacturerAndModelAndIsDeleted(newmodule.getManufacturer(), newmodule.getModel(), false);
				List<Long> TiltLegsFavID = new ArrayList<Long>();
				TiltLegsFavID = getTiltLegsFavorite(idUser);

				for (int i = 0; i < listTiltLegs.size(); i++) {

					TiltLegsFavRequest tiltLegs = new TiltLegsFavRequest();
					if(listTiltLegs.get(i) != null) {
						if (TiltLegsFavID != null && TiltLegsFavID.contains(listTiltLegs.get(i).getId())) {
							tiltLegs.setIsFav("true");
						} else {
							tiltLegs.setIsFav("false");
						}
						tiltLegs.setId(listTiltLegs.get(i).getId());
						tiltLegs.setManufacturer(listTiltLegs.get(i).getManufacturer());
						tiltLegs.setModel(listTiltLegs.get(i).getModel());
					}
					tiltLegsList.add(i, tiltLegs);
				}
				return tiltLegsList;

			} else if (tiltLegsRepo.countByManufacturerNotAndModelAndIsDeleted(newmodule.getManufacturer(), newmodule.getModel(), false) != 0) {

				List<TiltLegs> listTiltLegs = tiltLegsRepo.findAllByManufacturerNotAndModelAndIsDeleted(newmodule.getManufacturer(), newmodule.getModel(), false);
				List<Long> TiltLegsFavID = new ArrayList<Long>();
				TiltLegsFavID = getTiltLegsFavorite(idUser);

				for (int i = 0; i < listTiltLegs.size(); i++) {

					TiltLegsFavRequest tiltLegs = new TiltLegsFavRequest();
					if(listTiltLegs.get(i) != null) {
						if (TiltLegsFavID != null && TiltLegsFavID.contains(listTiltLegs.get(i).getId())) {
							tiltLegs.setIsFav("true");
						} else {
							tiltLegs.setIsFav("false");
						}
						tiltLegs.setId(listTiltLegs.get(i).getId());
						tiltLegs.setManufacturer(listTiltLegs.get(i).getManufacturer());
						tiltLegs.setModel(listTiltLegs.get(i).getModel());
					}
					tiltLegsList.add(i, tiltLegs);
				}
				return tiltLegsList;

			} else {
				return tiltLegsList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return tiltLegsList;
		}

	}
	/*
	 * Add New TiltLegs
	 */
	public TiltLegs addNewTiltLegs(Long idPermitInfo, TiltLegsFavRequest tiltLegsReq, Long idUserCo) {
		
		TiltLegs tiltLegs = new TiltLegs();
		
		try {
			
			tiltLegs.setManufacturer(tiltLegsReq.getManufacturer());
			tiltLegs.setModel(tiltLegsReq.getModel());
			tiltLegs.setWeight(tiltLegsReq.getWeight());
			Date d = new Date();
			SimpleDateFormat FormattedDATE = new SimpleDateFormat("MM/dd/yyyy");
			String updatedDate = FormattedDATE.format(d);
			tiltLegs.setUpdated(updatedDate);

			
			AuthentificationEntity contractor = new AuthentificationEntity();
			contractor = permitEntityRepo.findAuthentificationEntityByID(idPermitInfo);

			AuthentificationEntity userCo = new AuthentificationEntity();
			userCo = authentificationRepo.findById(idUserCo).get();

			if (userCo.getRoleEntity().getId() == 1 || userCo.getRoleEntity().getId() == 3) {
				tiltLegs.setHasSuperUserEdit(true);
			} else {
				tiltLegs.setHasSuperUserEdit(false);
			}
			Date addDate = new Date();
			tiltLegs.setAddDate(addDate);
			TiltLegsFavLibraryEntity tiltLegsFav = new TiltLegsFavLibraryEntity(contractor, tiltLegs);
			tiltLegs.setAuthentificationEntity(userCo);
			tiltLegssFavoritesRepo.save(tiltLegsFav);
			tiltLegsRepo.save(tiltLegs);
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Add component " + tiltLegsReq.getManufacturer() + " " + tiltLegsReq.getModel()+ ".;Tilt Legs"
							,
					true,"","","");
			return tiltLegs;

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Add component.;Tilt Legs"
							,
					false,"","","");
			return tiltLegs;

		}

	}
	
	public ResponseEntity<byte[]> getOptPDF(HistoriqModel url){
		Path path = Paths.get((String) url.getObjectOne());
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(contents, headers,org.springframework.http.HttpStatus.OK);
	}
	
}
