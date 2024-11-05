package com.PlayGroundAdv.Solar.service.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.PermitResponse;
import com.PlayGroundAdv.Solar.model.PermitResponsePrime;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class GetPermitsService {

	final PermitRepository permitRepo;
	final CheckValueTypesService checkValueTypesService;
	final PathRepository pathRepo;
	final AuthenticationRepository userRepo;

	public GetPermitsService(PermitRepository permitRepo, CheckValueTypesService checkValueTypesService,
			PathRepository pathRepo, AuthenticationRepository userRepo) {
		super();
		this.permitRepo = permitRepo;
		this.checkValueTypesService = checkValueTypesService;
		this.pathRepo = pathRepo;
		this.userRepo = userRepo;
	}

	/*
	 * getAllPermit(not deleted) New Design
	 */
	
	public Page<PermitResponsePrime> getAllPermits(String mailUser, Integer page, Integer size) {
		
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		Pageable pageable = PageRequest.of(page, size);
		
		Page<PermitResponsePrime> permits = new PageImpl<>(new ArrayList<>());
		try {
			if ((mailUser != null && mailUser.contains("nuagetechnologies-tn.com"))
					|| checkValueTypesService.Equals(mailUser, "nabil-g@advpermits.com")) {
				permits = permitRepo.getPermitNotTemplatesByUpdateBy(pageable);
				if (permits != null && !permits.isEmpty()) {
					for (int i = 0; i < permits.getContent().size(); i++) {
						if (permits.getContent().get(i) != null) {
							File fileReRsd = new File(
									pathRepo.findFilePath() + "RFISheet/RFISheet-" + permits.getContent().get(i).getId() + ".xlsx");
							permits.getContent().get(i).setHasRFISheet(fileReRsd.exists());
						}
					}
					return permits;
				} else {
					return permits;
				}

			} else {
				permits = permitRepo.getPermitNotTemplatesByUpdateByNotIn(pageable);
				if (permits != null && !permits.isEmpty()) {
					for (int i = 0; i < permits.getContent().size(); i++) {
						if (permits.getContent().get(i) != null) {
							File fileReRsd = null;
							fileReRsd = new File(
									pathRepo.findFilePath() + "RFISheet/RFISheet-" + permits.getContent().get(i).getId() + ".xlsx");
							permits.getContent().get(i).setHasRFISheet(fileReRsd.exists());
						}
					}
					return permits;
				} else {
					return permits;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return permits;
		}

	}

	/*
	 * get Drafter Permits(not deleted)
	 */
	public Page<PermitResponsePrime> getDrafterPermit(Integer page, Integer size) {
		Page<PermitResponsePrime> permits = new PageImpl<>(new ArrayList<>());
		Pageable pageable = PageRequest.of(page, size);
		try {
			return permitRepo.getDrafterPermit(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permits;
	}
	
	/*
	 * get Drafter Permits(not deleted) when we sort by column
	 */
	public Page<PermitResponsePrime> getDrafterPermitSort(Integer page, Integer size, String sortField, String sortOrder) {
		Page<PermitResponsePrime> permits = new PageImpl<>(new ArrayList<>());
		String sortParam = redefinitionCol(sortField, sortOrder);
		Pageable pageable = PageRequest.of(page, size);
		try {
			return permitRepo.getDrafterPermitSort(sortParam, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permits;
	}

	/*
	 * getAllPermit(not deleted) New Design is using in NT
	 */
	public Page<PermitResponsePrime> getAllPermitsByUser(Long userID, Integer page, Integer size) {
		
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		Pageable pageable = PageRequest.of(page, size);
		
		try {
			/* CR 1182 */
			AuthentificationEntity findUser = userRepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User not found for this id"));
			// A.B CR-3159 Multi-Users Per Company
			return permitRepo.getAllProjectsByUserAndCompany(
					findUser.getCompany() != null ? findUser.getCompany().trim().toUpperCase() : null, userID, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Page<PermitResponsePrime> getAllPermitsByUserSort(Long userID, Integer page, Integer size, String sortField, String sortOrder) {		
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		String sortParam = redefinitionCol(sortField, sortOrder);
		Pageable pageable = PageRequest.of(page, size);
		
		try {
			AuthentificationEntity findUser = userRepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User not found for this id"));
			return permitRepo.getAllProjectsByUserAndCompanySort(findUser.getCompany() != null ? findUser.getCompany().trim().toUpperCase() : null, userID, sortParam, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<PermitResponse> getPermitResponseByIdUser(Long idUser) {
		try {
			return permitRepo.getPermitResponseByUserId(idUser, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public Page<PermitResponsePrime> getAllPermitsSort(String mailUser, Integer page, Integer size, String sortField, String sortOrder) {
		
		TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
		String sortParam = redefinitionCol(sortField, sortOrder);
		Pageable pageable = PageRequest.of(page, size);
		Page<PermitResponsePrime> permits = new PageImpl<>(new ArrayList<>());
		try {
			if ((mailUser != null && mailUser.contains("nuagetechnologies-tn.com"))
					|| checkValueTypesService.Equals(mailUser, "nabil-g@advpermits.com")) {
				permits = permitRepo.getPermitNotTemplatesByCol(sortParam, pageable);
				if (permits != null && !permits.isEmpty()) {
					for (int i = 0; i < permits.getContent().size(); i++) {
						if (permits.getContent().get(i) != null) {
							File fileReRsd = new File(
									pathRepo.findFilePath() + "RFISheet/RFISheet-" + permits.getContent().get(i).getId() + ".xlsx");
							permits.getContent().get(i).setHasRFISheet(fileReRsd.exists());
						}
					}
					return permits;
				} else {
					return permits;
				}

			} else {
				permits = permitRepo.getPermitNotTemplatesByUpdateByNotInCol(sortParam, pageable);
				if (permits != null && !permits.isEmpty()) {
					for (int i = 0; i < permits.getContent().size(); i++) {
						if (permits.getContent().get(i) != null) {
							File fileReRsd = null;
							fileReRsd = new File(
									pathRepo.findFilePath() + "RFISheet/RFISheet-" + permits.getContent().get(i).getId() + ".xlsx");
							permits.getContent().get(i).setHasRFISheet(fileReRsd.exists());
						}
					}
					return permits;
				} else {
					return permits;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return permits;
		}

	}
	
	public String redefinitionCol(String sortField, String sortOrder) {
		String col = ""; 
		try {
			String ascDesc = sortOrder.equals("1") ? "ASC" : "DESC";
			if(sortField.equals("projectNameComb"))
				col = "projectNameComb "+ascDesc;
			 else if(sortField.equals("avancement"))
				 col = "advancement "+ascDesc;
			 else if(sortField.equals("status"))
				 col = "status "+ascDesc;
			 else if(sortField.equals("dateOfSubmitPermit"))
				 col = "dateOfSubmitPermit "+ascDesc;
			 else if(sortField.equals("updatedDate"))
				 col = "updateDate "+ascDesc;
			 else if(sortField.equals("updatedBy"))
				 col = "updatedBy "+ascDesc;
			 else if(sortField.equals("creationPermitDate"))
				 col = "creationPermitDate "+ascDesc;
			 else if(sortField.equals("companyComb")) {
				 col = "companyComb "+ascDesc;
			 }
			
			return col;
			
		} catch (Exception e) {
			e.printStackTrace();
			return col;
		}
	}
}
