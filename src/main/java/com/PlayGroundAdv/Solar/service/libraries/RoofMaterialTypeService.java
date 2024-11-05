package com.PlayGroundAdv.Solar.service.libraries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
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

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchRoofMaterialTypeResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;


@Service
@Transactional
public class RoofMaterialTypeService {

	final HistoryActivityService historicActivity;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;
	private final ModelMapper modelMapper;
	final AuthenticationRepository authentificationRepo;
	final PermitArraysRepository permitArraysEntityRepo;
	final PermitRepository permitEntityRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo;
	
	public RoofMaterialTypeService(HistoryActivityService historicActivity,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			RoofMaterialTypeRepository roofMaterialTypeRepo, ModelMapper modelMapper,
			AuthenticationRepository authentificationRepo, PermitArraysRepository permitArraysEntityRepo,
			PermitRepository permitEntityRepo, PermitProjectSiteInfoRepository permitProjectSiteInfoEntityRepo) {
		super();
		this.historicActivity = historicActivity;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
		this.modelMapper = modelMapper;
		this.authentificationRepo = authentificationRepo;
		this.permitArraysEntityRepo = permitArraysEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.permitProjectSiteInfoEntityRepo = permitProjectSiteInfoEntityRepo;
	}
	
	public Page<SearchRoofMaterialTypeResult> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("typeRoof"));
			String[] typeRoof = checkValueTypesService.isStringNotEmpty(request.getTypeRoof())
					? request.getTypeRoof().split(" ")
					: null;
			if (typeRoof == null) {
				Page<RoofMaterialType> p = roofMaterialTypeRepo.findByIsDeleted(request.getIsDeleted(), pageable);
				return convertDto(p, pageable);
			} else {
				Page<RoofMaterialType> p = roofMaterialTypeRepo.findAll(filter(typeRoof, request.getIsDeleted()), pageable);
				return convertDto(p, pageable);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Page<SearchRoofMaterialTypeResult> convertDto(Page<RoofMaterialType> page, Pageable pageable) {
		try {
			return new PageImpl<>(
					page.stream().map(c -> new SearchRoofMaterialTypeResult(c.getId(), c.getTypeRoof(), c.getUpdated(),
							c.isDeleted(),
							new UsersEntityResult(c.getAuthentificationEntity().getId(),
									c.getAuthentificationEntity().getFirstName(),
									c.getAuthentificationEntity().getLastName()),
							c.getAuthentificationEntity().getFirstName()+" "+c.getAuthentificationEntity().getLastName(),
							c.getMappingValue())).collect(Collectors.toList()),
					pageable, page.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<RoofMaterialType> filter(String[] typeRoof, Boolean deleted) {

		return new Specification<RoofMaterialType>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<RoofMaterialType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesTypeRoof = new ArrayList<>();
				for (int i = 0; typeRoof != null && i < typeRoof.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("typeRoof")),
							"%" + typeRoof[i].toLowerCase() + "%");
					predicatesTypeRoof.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate endPredicateTypeRoof = typeRoof != null
						? cb.or(predicatesTypeRoof.toArray(new Predicate[predicatesTypeRoof.size()]))
						: null;
				
				List<Predicate> list = Arrays.asList(predicateDeleted, endPredicateTypeRoof);
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}
   
	/*
	 * Add roofMaterialType
	 */
	public String addRoofMaterialType(Long idContractor, SearchRoofMaterialTypeResult roofMaterialTypeReq, String ipUser, String timeZoneUser,
			Long idUser) {

		try {

			List<String> roofMaterialTypes = roofMaterialTypeRepo.getTypeRoofByTypeRoof(roofMaterialTypeReq.getTypeRoof());
					
			if (roofMaterialTypes.isEmpty()) {
				RoofMaterialType roofMaterialType = new RoofMaterialType();
				roofMaterialType.setTypeRoof(roofMaterialTypeReq.getTypeRoof());
				if (checkValueTypesService.NotEquals(roofMaterialTypeReq.getMappingValue(),"")) {
					roofMaterialType.setMappingValue(roofMaterialTypeReq.getMappingValue());
				} else roofMaterialType.setMappingValue(roofMaterialTypeReq.getTypeRoof());
				
				Date d = new Date();
				SimpleDateFormat formattedDATE = new SimpleDateFormat("MM/dd/yyyy");
				String updatedDate = formattedDATE.format(d);
				roofMaterialType.setUpdated(updatedDate);
				
				AuthentificationEntity contractor = new AuthentificationEntity();
				try {
					contractor = authentificationRepo.findById(idContractor).get();
				} catch (Exception e) {
					e.printStackTrace();
				}
				roofMaterialType.setAuthentificationEntity(contractor);
				if(contractor.getRoleEntity().getId()== 1 || contractor.getRoleEntity().getId()== 3) {
					roofMaterialType.setHasSuperUserEdit(true);
		 		}else {
		 			roofMaterialType.setHasSuperUserEdit(false);
		 		}
				roofMaterialTypeRepo.save(roofMaterialType);

				historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
						"libraries;Add component " + roofMaterialType.getTypeRoof()+ ".;Roofing Material Type"
								,
						true,"","","");
				return "Done";
			} else
				historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
						"Add RoofMaterialType Component Library;RoofMaterialType already exists in data sources;Add failed ", false,"","","");
			return "RoofMaterialType already exists in data sources";

		} catch (Exception e) {
			historicActivity.recordActivity(idUser, ipUser, timeZoneUser,
					"libraries;Add component.;Roofing Material Type"
							,
					false,"","","");
			e.printStackTrace();
			return "error";
		}
	}

	/*
	 * get Total Number of RoofMaterialType ( function "Long getNumRoofMaterialTypeTot() " removed )
	 */
	

	/*
	 * edit RoofMaterialType
	 */
	public String editRoofMaterialType(SearchRoofMaterialTypeResult roofMaterialType,Long userID) {
		try {

			RoofMaterialType testRoofMaterialType = new RoofMaterialType();
			if (roofMaterialTypeRepo.countByTypeRoofAndIsDeleted(roofMaterialType.getTypeRoof(), false)!= 0) {
				testRoofMaterialType = roofMaterialTypeRepo.findByTypeRoofAndIsDeleted(roofMaterialType.getTypeRoof(), false);
				if (testRoofMaterialType != null && !checkValueTypesService.Equals(testRoofMaterialType.getId(),roofMaterialType.getId()) ) {
					return "exist";
				} else {
					return editRoofMaterialTypeFunction(roofMaterialType, userID);
				}
			} else {
				return editRoofMaterialTypeFunction(roofMaterialType, userID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String editRoofMaterialTypeFunction(SearchRoofMaterialTypeResult roofMaterialType,Long userID) {

		try {
			RoofMaterialType updatedRoofMaterialType = roofMaterialTypeRepo.findById(roofMaterialType.getId()).get();
			List<PermitProjectSiteInfoEntity> PermitProjectSiteInfoEntitys = permitProjectSiteInfoEntityRepo.findByRoofMaterialType(updatedRoofMaterialType.getId());
			if (PermitProjectSiteInfoEntitys != null && !PermitProjectSiteInfoEntitys.isEmpty()) {
				for (PermitProjectSiteInfoEntity PermitProjectSiteInfoEntity : PermitProjectSiteInfoEntitys) {
					if(PermitProjectSiteInfoEntity != null && PermitProjectSiteInfoEntity.getRoofMaterialType()!= null ) {
					PermitProjectSiteInfoEntity.setRoofMaterialType(roofMaterialType.getId());
					}
					
				}
			}
			//end

			SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
			updatedRoofMaterialType.setId(roofMaterialType.getId());
			updatedRoofMaterialType.setTypeRoof(roofMaterialType.getTypeRoof());
			if (checkValueTypesService.NotEquals(roofMaterialType.getMappingValue(),"")) {
				updatedRoofMaterialType.setMappingValue(roofMaterialType.getMappingValue());
			} else updatedRoofMaterialType.setMappingValue(roofMaterialType.getTypeRoof());
			
			
			updatedRoofMaterialType.setUpdated(formattedDate.format(new Date()));

			
			roofMaterialTypeRepo.save(updatedRoofMaterialType);
			historicActivity.recordActivity(userID, "", "",
					"libraries;Edit component " + roofMaterialType.getTypeRoof()+ ".;Roofing Material Type"
							,
					true,"","","");
			
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(userID, "", "",
					"libraries;Edit component.;Roofing Material Type"
							,
					false,"","","");
			return "fail";
		}
	}
	
	public String editRoofMaterialTypeNotification(Long userID,String roofMaterialType) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).get();
			notificationEntityService.addNewNotif(userID, 0L, "Roofing Material Type Update","Libraries","Roofing Material Type Update - " + roofMaterialType, 
					"The Roofing Material Type " + roofMaterialType + " was updated by "+userCo.getFirstName()+" "+userCo.getLastName() , true);
			
			 return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
	
	public String addRoofMaterialTypeNotification(Long userID,String roofType) {
		try {

			AuthentificationEntity userCo = authentificationRepo.findById(userID).get();
			notificationEntityService.addNewNotif(userID, 0L, "New Roofing Material Type","Libraries","New Roofing Material Type- " + roofType, 
					"The Roofing Material Type " + roofType  + " was added by "+userCo.getFirstName()+" "+userCo.getLastName() , true);
			
			 return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}
	}
	// CR 924
	// Change Request RoofMaterialType List Management
	
	public String roofMaterialTypeLibraryActived(Long idRoofMaterialType, Long idUserCo) {

		try {
			RoofMaterialType roofMaterialTypeLibrary = roofMaterialTypeRepo.findById(idRoofMaterialType).get();
			if (roofMaterialTypeLibrary.getId() != 0) {
				if (roofMaterialTypeRepo.countByTypeRoofAndIsDeleted(roofMaterialTypeLibrary.getTypeRoof(), false) != 0) {
					return "exist";
				}
				else {
				roofMaterialTypeLibrary.setDeleted(false);
				historicActivity.recordActivity(idUserCo, "", "",
						"libraries;Activate component " + roofMaterialTypeLibrary.getTypeRoof()+ ".;Roofing Material Type"
								,
						true,"","","");
				return "true";
				}
				
			}
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Activate component " + roofMaterialTypeLibrary.getTypeRoof()+ ".;Roofing Material Type"
							,
			false,"","","");
			return "false";
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Activate component.;Roofing Material Type"
							,
			false,"","","");
			return "false";
		}
	}

	public List<ProjectForLibrariesModel> getAllPermitOfRoofMaterialTypeDeleted(Long idRoofMaterialType) {
		try {
			/***************** Get all permit Array content this model *****************/
			List<PermitProjectSiteInfoEntity> allpermitProjectSiteInfo = permitProjectSiteInfoEntityRepo
					.findByRoofMaterialTypeAndPermitEntityStatusNotInAndPermitEntityIsTemplateIsFalse(
							idRoofMaterialType, Arrays.asList("Deleted", "Submitted", "Delivered"));

			List<PermitEntity> projects =  allpermitProjectSiteInfo.stream()
		            .map(PermitProjectSiteInfoEntity::getPermitEntity)
		            .collect(Collectors.toList());
			return  projects.stream()
		            .map(p -> new ProjectForLibrariesModel(p.getId(), p.getHomeOwnLastName(), p.getHomeOwnName(), p.getProjectName(), p.getStatus(),
		            		p.getAuthentificationEntity().getFirstName(), p.getAuthentificationEntity().getLastName()) )
		            .collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return  new ArrayList<>();
		}
	}

	public boolean deleteRoofMaterialTypeLibrary(Long id, Long idUserCo) {
		try {

			if (id != 0 && id != null) {
				
				List<PermitProjectSiteInfoEntity> allpermitProjectSiteInfo = permitProjectSiteInfoEntityRepo
						.findByRoofMaterialTypeAndPermitEntityStatusNotInAndPermitEntityIsTemplateIsFalse(
								id, Arrays.asList("Deleted", "Submitted", "Delivered"));
				if (!allpermitProjectSiteInfo.isEmpty()) {
					for (PermitProjectSiteInfoEntity permitProjectSiteInfo : allpermitProjectSiteInfo) {
						permitProjectSiteInfo.setRoofMaterialType(-2l);
						permitProjectSiteInfoEntityRepo.save(permitProjectSiteInfo);
					}
				}
				RoofMaterialType roofMaterialTypeLibraryRes = roofMaterialTypeRepo.findById(id).get();
				roofMaterialTypeLibraryRes.setDeleted(true);
				roofMaterialTypeRepo.save(roofMaterialTypeLibraryRes);
				
				historicActivity.recordActivity(idUserCo, "", "", "libraries;Delete component "
						+ roofMaterialTypeLibraryRes.getTypeRoof() + ".;Roofing Material Type", true, "", "", "");
				return true;
			}
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Delete component " + id + ".;Roofing Material Type", false, "", "", "");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "", "libraries;Delete component.;Roofing Material Type",
					false, "", "", "");
			return false;
		}
	}

	
	public String checkRoofMaterialTypeExistent(SearchRoofMaterialTypeResult newmodule) {
		try {
			if (roofMaterialTypeRepo.countByTypeRoofAndIsDeleted(newmodule.getTypeRoof(), false) != 0) {
				return "exist";
			}  else {
				return "success";
			}
		} catch (Exception e) {
			return "fail";
		}

	}
	/*
	 * Add New RoofMaterialType
	 */
	public RoofMaterialType addNewRoofMaterialType(SearchRoofMaterialTypeResult roofMaterialTypeReq, Long idUserCo) {
		
		RoofMaterialType roofMaterialType = new RoofMaterialType();
		
		try {
			AuthentificationEntity userCo = authentificationRepo.findById(idUserCo).orElse(null);
			SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
			if (checkValueTypesService.NotEquals(roofMaterialTypeReq.getMappingValue(),"")) {
				roofMaterialType.setMappingValue(roofMaterialTypeReq.getMappingValue());
			} else roofMaterialType.setMappingValue(roofMaterialTypeReq.getTypeRoof());
			

			roofMaterialType.setTypeRoof(roofMaterialTypeReq.getTypeRoof());
			roofMaterialType.setUpdated(formattedDate.format(new Date()));
			roofMaterialType.setHasSuperUserEdit(userCo != null && (userCo.getRoleEntity().getId() == 1 || userCo.getRoleEntity().getId() == 3));
			roofMaterialType.setAddDate(new Date());
			roofMaterialType.setAuthentificationEntity(userCo);
			roofMaterialTypeRepo.save(roofMaterialType);
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Add component " + roofMaterialType.getTypeRoof()+ ".;Roofing Material Type"
							,
					true,"","","");
			return roofMaterialType;

		} catch (Exception e) {
			e.printStackTrace();
			historicActivity.recordActivity(idUserCo, "", "",
					"libraries;Add component.;Roofing Material Type"
							,
					false,"","","");
			return roofMaterialType;

		}

	}
	
	public List<SearchRoofMaterialTypeResult>  getListOfRoofMaterialType() {
		List<RoofMaterialType>  roofMaterialType  = new ArrayList<>();
		try {
			roofMaterialType = roofMaterialTypeRepo.findByIsDeletedOrderByTypeRoofAsc(false);
			if (!roofMaterialType.isEmpty()){
				List<SearchRoofMaterialTypeResult> roofMaterialTypeDTOs = new ArrayList<SearchRoofMaterialTypeResult>();
			    for (RoofMaterialType mod : roofMaterialType) {
			    	SearchRoofMaterialTypeResult roofMaterialTypeDto = modelMapper.map(mod, SearchRoofMaterialTypeResult.class);
			    	roofMaterialTypeDto.setFullName(mod.getAuthentificationEntity().getFirstName()+" "+mod.getAuthentificationEntity().getLastName());
			    	UsersEntityResult owner = new UsersEntityResult();
					owner.setId(mod.getAuthentificationEntity().getId());
					owner.setFirstName(mod.getAuthentificationEntity().getFirstName());
					owner.setLastName(mod.getAuthentificationEntity().getLastName());
			    	roofMaterialTypeDto.setOwner(owner);
			    	roofMaterialTypeDTOs.add(roofMaterialTypeDto);
				}
			    return roofMaterialTypeDTOs;
			}else return new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		
	}


}
