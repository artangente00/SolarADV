package com.PlayGroundAdv.Solar.service.libraries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ElectricalUtilityModel;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitHomeSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.UtilityCompanyRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Transactional
public class UtilityCompanyLibraryService {
	
	@PersistenceContext
	EntityManager em;

	final HistoryActivityService historyActivityService;
	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final UtilityCompanyRepository utilityCompanyRepo;
	final ModelMapper modelMapper;
	final AuthenticationRepository authentificationRepo;
	final PermitArraysRepository permitArraysEntityRepo;
	final PermitRepository permitEntityRepo;
	final PermitHomeSiteInfoRepository permitHomeSiteInfoEntityRepo;
	
	public UtilityCompanyLibraryService(HistoryActivityService historyActivityService,
			NotificationEntityService notificationEntityService, CheckValueTypesService checkValueTypesService,
			UtilityCompanyRepository utilityCompanyRepo, ModelMapper modelMapper,
			AuthenticationRepository authentificationRepo, PermitArraysRepository permitArraysEntityRepo,
			PermitRepository permitEntityRepo, PermitHomeSiteInfoRepository permitHomeSiteInfoEntityRepo) {
		super();
		this.historyActivityService = historyActivityService;
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.utilityCompanyRepo = utilityCompanyRepo;
		this.modelMapper = modelMapper;
		this.authentificationRepo = authentificationRepo;
		this.permitArraysEntityRepo = permitArraysEntityRepo;
		this.permitEntityRepo = permitEntityRepo;
		this.permitHomeSiteInfoEntityRepo = permitHomeSiteInfoEntityRepo;
	}



	public Page<ElectricalUtilityEntity> filter(ComponentPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
					Sort.by("zip").and(Sort.by("utilityCompanyName")));
			String[] utilityCompanyName = checkValueTypesService.isStringNotEmpty(request.getParam2())
					? request.getParam2().split(" ")
					: null;
			
			if (utilityCompanyName == null &&  !checkValueTypesService.isStringNotEmpty(request.getParam1())) {
				return utilityCompanyRepo.findByIsDeleted(request.getIsDeleted(), pageable);
			} else {
				return utilityCompanyRepo.findAll(filter(utilityCompanyName, request.getParam1(),
						request.getIsDeleted()), pageable);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Specification<ElectricalUtilityEntity> filter(String[] utilityCompanyName, String zipCode, Boolean deleted) {
		return new Specification<ElectricalUtilityEntity>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<ElectricalUtilityEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicatesUtility = new ArrayList<>();
				for (int i = 0; utilityCompanyName != null && i < utilityCompanyName.length; i++) {
					Predicate predicate = cb.like(cb.lower(root.get("utilityCompanyName")),
							"%" + utilityCompanyName[i].toLowerCase() + "%");
					predicatesUtility.add(predicate);
				}
				Predicate predicateDeleted = cb.equal(root.get("isDeleted"), deleted);
				Predicate predicateRatedCurrent = zipCode != null ? cb.equal(root.get("zip"), zipCode) : null;
				Predicate endPredicateUtility = utilityCompanyName != null ? cb.or(predicatesUtility.toArray(new Predicate[predicatesUtility.size()])) : null;
				
				List<Predicate> list = Arrays.asList(predicateDeleted, predicateRatedCurrent, endPredicateUtility);
				list = list.stream().filter(p -> p != null).collect(Collectors.toList());
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
	}
   

	/*
	 *Edit Utility Company
	 */
	
	public String  editUtilityCompany(HistoriqModel hm, String ipUser, String timeZoneUser, Long idUserCo) {
		ObjectMapper mapper = new ObjectMapper();
		ElectricalUtilityEntity utilityCompany = new ElectricalUtilityEntity();		
		byte[] json = null;
		
		AuthentificationEntity userCo = null;
		try {
			userCo = authentificationRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + idUserCo));
		} catch (ResourceNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			utilityCompany = mapper.readValue(json, ElectricalUtilityEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (utilityCompanyRepo.countByIdNotAndZipAndUtilityCompanyNameAndIsDeleted(utilityCompany.getId(), utilityCompany.getZip(), utilityCompany.getUtilityCompanyName(), false) > 0) {
				return "Exist";
				
			}else{
				
				ElectricalUtilityEntity editElectricalUtility = utilityCompanyRepo.findById(utilityCompany.getId());
		 		editElectricalUtility.setZip(utilityCompany.getZip());
				editElectricalUtility.setUtilityCompanyName(utilityCompany.getUtilityCompanyName());
		 		
		 		if (checkValueTypesService.NotEquals(utilityCompany.getMappingValue(),"")) {
		 			editElectricalUtility.setMappingValue(utilityCompany.getMappingValue());
				} else editElectricalUtility.setMappingValue(utilityCompany.getUtilityCompanyName());
		 		
				editElectricalUtility.setPhone(utilityCompany.getPhone());
				editElectricalUtility.setState(utilityCompany.getState());
				editElectricalUtility.setUtilityNumber(utilityCompany.getUtilityNumber());
				editElectricalUtility.setCounty(utilityCompany.getCounty());
				editElectricalUtility.setUtilityType(utilityCompany.getUtilityType());
				editElectricalUtility.setaCDReq(utilityCompany.getaCDReq());
				editElectricalUtility.setpVMReq(utilityCompany.getpVMReq());
				editElectricalUtility.setaCDPVMOrientation(utilityCompany.getaCDPVMOrientation());
				editElectricalUtility.setLastUpdatedBy(userCo);
				editElectricalUtility.setLastUpdate(new Date());
				utilityCompanyRepo.save(editElectricalUtility);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;Edit component " + editElectricalUtility.getZip()+" "+editElectricalUtility.getUtilityCompanyName()+ ".;Utility Company"
								,
						true,"","","");
				return "success";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Edit component.;Utility Company"
							,
					false,"","","");
			return "error";
		}
	}

	/*
	 *Edit Utility Company
	 */
	
	public String  addUtilityCompany(HistoriqModel hm, String ipUser, String timeZoneUser, Long idUserCo) {
		ObjectMapper mapper = new ObjectMapper();
		ElectricalUtilityModel utilityCompany = new ElectricalUtilityModel();
		byte[] json = null;
		
		AuthentificationEntity userCo = null;
		try {
			userCo = authentificationRepo.findById(idUserCo).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + idUserCo));
		} catch (ResourceNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			utilityCompany = mapper.readValue(json, ElectricalUtilityModel.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		try {
			if (utilityCompanyRepo.countByZipAndUtilityCompanyNameAndIsDeleted(utilityCompany.getZip()+"",utilityCompany.getUtilityCompanyName(),false) > 0) {
				return "Exist";
				
			}else{
				
				
				ElectricalUtilityEntity editElectricalUtility = new ElectricalUtilityEntity();
		 		editElectricalUtility.setZip(utilityCompany.getZip());
		 		
		 		if (checkValueTypesService.NotEquals(utilityCompany.getMappingValue(),"")) {
		 			editElectricalUtility.setMappingValue(utilityCompany.getMappingValue());
				} else editElectricalUtility.setMappingValue(utilityCompany.getUtilityCompanyName());
		 		
				editElectricalUtility.setUtilityCompanyName(utilityCompany.getUtilityCompanyName());
				editElectricalUtility.setPhone(utilityCompany.getPhone());
				editElectricalUtility.setState(utilityCompany.getState());
				editElectricalUtility.setUtilityNumber(utilityCompany.getUtilityNumber());
				editElectricalUtility.setCounty(utilityCompany.getCounty());
				editElectricalUtility.setUtilityType(utilityCompany.getUtilityType());
				editElectricalUtility.setIsDeleted(false);
				editElectricalUtility.setLastUpdatedBy(userCo);
				editElectricalUtility.setLastUpdate(new Date());
				utilityCompanyRepo.save(editElectricalUtility);
				historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
						"libraries;Add component " + editElectricalUtility.getZip()+" "+editElectricalUtility.getUtilityCompanyName()+ ".;Utility Company"
								,
						true,"","","");
				return editElectricalUtility.getId()+"";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Add component.;Utility Company"
							,
					false,"","","");
			return "error";
		}
	}
	/*
	 *Delete Utility Company
	 */
	
	public List<ProjectForLibrariesModel>  getRemoveUtilityCompanyConfirmation(Integer utilityCompany) {
		try {
			return permitHomeSiteInfoEntityRepo.getPermitUtilityCompany(utilityCompany+"");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	
	public String  deleteUtilityCompany(long id, String ipUser, String timeZoneUser, Long idUserCo) {
		try {
			ElectricalUtilityEntity utilityCompany = utilityCompanyRepo.findById(id);

			/*
			 *Remove Utility Company From Active Projects
			 */
			List<PermitHomeSiteInfoEntity> electricalUtilityProject = permitHomeSiteInfoEntityRepo.findByUtilityCompanyNameAndPermitEntityIsDeleted(utilityCompany.getId()+"",false);
			if (electricalUtilityProject != null && !electricalUtilityProject.isEmpty()) {
				 
				for (int i = 0; i < electricalUtilityProject.size(); i++) {
					if(electricalUtilityProject.get(i) != null && checkValueTypesService.Equals(electricalUtilityProject.get(i).getUtilityCompanyName(),utilityCompany.getId()+"")){
						electricalUtilityProject.get(i).setUtilityCompanyName("Removed");
					}
				}
			}

			/*
			 *Remove Utility Company From Utility Company Library
			 */
			utilityCompany.setIsDeleted(true);
			
		
			utilityCompanyRepo.save(utilityCompany);
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Delete component " + utilityCompany.getZip()+" "+utilityCompany.getUtilityCompanyName()+ ".;Utility Company"
							,
					true,"","","");
			return "Done";

		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Delete component.;Utility Company"
							,
					false,"","","");
			e.printStackTrace();
			return "error";
		}
	}
	
	
	/*
	 *Activate Utility Company
	 */
	public String  activateUtilityCompany(long id, String ipUser, String timeZoneUser, Long idUserCo) {
		try {

			ElectricalUtilityEntity utilityCompany = utilityCompanyRepo.findById(id);
			if (utilityCompanyRepo.countByZipAndUtilityCompanyNameAndIsDeleted(utilityCompany.getZip(), utilityCompany.getUtilityCompanyName(), false) != 0) {
				return "exist";
			}
			else {
			utilityCompany.setIsDeleted(false);
		
			utilityCompanyRepo.save(utilityCompany);
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Activate component " + utilityCompany.getZip()+" "+utilityCompany.getUtilityCompanyName()+ ".;Utility Company"
							,
					true,"","","");
			return "Done";
			}
		} catch (Exception e) {
			historyActivityService.recordActivity(idUserCo, ipUser, timeZoneUser,
					"libraries;Activate component.;Utility Company",false,"","","");
			e.printStackTrace();
			return "error";
		}
	}
	
}