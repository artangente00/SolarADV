package com.PlayGroundAdv.Solar.service.log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.HistoryActivityEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.HistoricActivityResult;
import com.PlayGroundAdv.Solar.repositories.HistoryActivityRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class HistoryActivityService {


	
	final CheckValueTypesService checkValueTypesService;
	final HistoryActivityRepository historyActivityRepo;
	final AuthenticationRepository userRepo;
	final PermitRepository permitRepo;
	
	public HistoryActivityService(CheckValueTypesService checkValueTypesService,
			HistoryActivityRepository historyActivityRepo, AuthenticationRepository userRepo,
			PermitRepository permitRepo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.historyActivityRepo = historyActivityRepo;
		this.userRepo = userRepo;
		this.permitRepo = permitRepo;
	}
	
	public String recordActivity(Long idUserCo,
							   String ipUserCo,
							   String timeZone,
							   String typeAction,
							   Boolean isSuccess,
							   String numTab,String sessionId,String openDate
							   ){

		try {

			if (checkValueTypesService.Equals(isSuccess, true) && checkValueTypesService.contains(typeAction, ";")) {
				if (checkValueTypesService.Equals(typeAction.split(";")[0], "Start Edit Project")) {
					if (typeAction.split(";").length >= 2) {
						PermitEntity qHN  = new PermitEntity();
						if (checkValueTypesService.isStringInt(typeAction.split(";")[1])) {
							qHN = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
						}	

						String permitHomeOwnerName = "";
						if (qHN != null) {
							if(checkValueTypesService.NotEquals(qHN.getProjectName(), "")) {
								permitHomeOwnerName = qHN.getProjectName();
							}else if(checkValueTypesService.NotEquals(qHN.getHomeOwnLastName(),"")) {
								permitHomeOwnerName = qHN.getHomeOwnLastName()+", "+qHN.getHomeOwnName();
							}else {
								permitHomeOwnerName = qHN.getHomeOwnName();
							}
						} 
						if (checkValueTypesService.Equals(numTab, "") && typeAction.split(";").length == 3) {
							typeAction = "Start Edit Drafter Section " + permitHomeOwnerName + ";"+ typeAction.split(";")[1] + ";" + typeAction.split(";")[2];
						} else if (typeAction.split(";").length == 3)
							typeAction = "Start Edit Project " + permitHomeOwnerName + ";" + typeAction.split(";")[1]+ ";" + typeAction.split(";")[2];
					}

				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Start Edit Project Read Only")) {
					if (typeAction.split(";").length >= 2) {
						PermitEntity qHN  = new PermitEntity();
						if (checkValueTypesService.isStringInt(typeAction.split(";")[1])) {
							qHN = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
						}
						String permitHomeOwnerName = "";
						if (qHN != null) {
							if(checkValueTypesService.NotEquals(qHN.getProjectName(), "")) {
								permitHomeOwnerName = qHN.getProjectName();
							}else if(checkValueTypesService.NotEquals(qHN.getHomeOwnLastName(),"")) {
								permitHomeOwnerName = qHN.getHomeOwnLastName()+", "+qHN.getHomeOwnName();
							}else {
								permitHomeOwnerName = qHN.getHomeOwnName();
							}
						}
						if (checkValueTypesService.Equals(numTab, "") && typeAction.split(";").length == 3) {
							typeAction = "Start Edit Drafter Section " + permitHomeOwnerName + ";" + typeAction.split(";")[1] + ";" + typeAction.split(";")[2];
						} else if (typeAction.split(";").length == 3)
							typeAction = "Opened Project " + permitHomeOwnerName + " Read Only;" + typeAction.split(";")[1] + ";" + typeAction.split(";")[2];
					}

				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Save Permit as Draft")) {
					if (typeAction.split(";").length >= 2) {
						PermitEntity qHN  = new PermitEntity();
				
						if (checkValueTypesService.isStringInt(typeAction.split(";")[1])) {
							qHN = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
						}

						String permitHomeOwnerName = "";
						if (qHN != null) {
							if(checkValueTypesService.NotEquals(qHN.getProjectName(), "")) {
								permitHomeOwnerName = qHN.getProjectName();
							}else if(checkValueTypesService.NotEquals(qHN.getHomeOwnLastName(),"")) {
								permitHomeOwnerName = qHN.getHomeOwnLastName()+", "+qHN.getHomeOwnName();
							}else {
								permitHomeOwnerName = qHN.getHomeOwnName();
							}
						}
						if (typeAction.split(";").length == 3) {
							typeAction = "Save Permit " + permitHomeOwnerName + " as Draft;" + typeAction.split(";")[1]+ ";" + typeAction.split(";")[2];
						}

					}

				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Download PDF Project")) {
					if (typeAction.split(";").length >= 2) {
						PermitEntity qHN  = new PermitEntity();
						if (checkValueTypesService.isStringInt(typeAction.split(";")[1])) {
							qHN = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
						}

						String permitHomeOwnerName = "";
						if (qHN != null) {
							if(checkValueTypesService.NotEquals(qHN.getProjectName(), "")) {
								permitHomeOwnerName = qHN.getProjectName();
							}else if(checkValueTypesService.NotEquals(qHN.getHomeOwnLastName(),"")) {
								permitHomeOwnerName = qHN.getHomeOwnLastName()+", "+qHN.getHomeOwnName();
							}else {
								permitHomeOwnerName = qHN.getHomeOwnName();
							}
						}
						if (typeAction.split(";").length == 3) {
							typeAction = "Download Planset Document of " + permitHomeOwnerName + ";"+ typeAction.split(";")[1] + ";" + typeAction.split(";")[2];
						}
					}

				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Submit Permit")) {
					if (typeAction.split(";").length >= 2) {
						PermitEntity qHN  = new PermitEntity();
						if (checkValueTypesService.isStringInt(typeAction.split(";")[1])) {
							qHN = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
						}

						String permitHomeOwnerName = "";
						if (qHN != null) {
							if(checkValueTypesService.NotEquals(qHN.getProjectName(), "")) {
								permitHomeOwnerName = qHN.getProjectName();
							}else if(checkValueTypesService.NotEquals(qHN.getHomeOwnLastName(),"")) {
								permitHomeOwnerName = qHN.getHomeOwnLastName()+", "+qHN.getHomeOwnName();
							}else {
								permitHomeOwnerName = qHN.getHomeOwnName();
							}
						}
						if (typeAction.split(";").length == 3) {
							typeAction = "Submit Permit " + permitHomeOwnerName + ";" + typeAction.split(";")[1] + ";"+ typeAction.split(";")[2];
						}
					}

				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Download EXCEL")) {
					if (typeAction.split(";").length >= 2) {
						PermitEntity qHN  = new PermitEntity();
						if (checkValueTypesService.isStringInt(typeAction.split(";")[1])) {
							qHN = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
						}

						String permitHomeOwnerName = "";
						if (qHN != null) {
							if(checkValueTypesService.NotEquals(qHN.getProjectName(), "")) {
								permitHomeOwnerName = qHN.getProjectName();
							}else if(checkValueTypesService.NotEquals(qHN.getHomeOwnLastName(),"")) {
								permitHomeOwnerName = qHN.getHomeOwnLastName()+", "+qHN.getHomeOwnName();
							}else {
								permitHomeOwnerName = qHN.getHomeOwnName();
							}
						}
						if (typeAction.split(";").length == 3) {
							typeAction = "Download Portal Excel File Of " + permitHomeOwnerName + ";"
									+ typeAction.split(";")[1] + ";" + typeAction.split(";")[2];
						}
					}

				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Download Drafter Package")) {
					if (typeAction.split(";").length >= 2) {
						PermitEntity qHN  = new PermitEntity();
						if (checkValueTypesService.isStringInt(typeAction.split(";")[1])) {
							qHN = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
						}
						String permitHomeOwnerName = "";
						if (qHN != null) {
							if(checkValueTypesService.NotEquals(qHN.getProjectName(), "")) {
								permitHomeOwnerName = qHN.getProjectName();
							}else if(checkValueTypesService.NotEquals(qHN.getHomeOwnLastName(),"")) {
								permitHomeOwnerName = qHN.getHomeOwnLastName()+", "+qHN.getHomeOwnName();
							}else {
								permitHomeOwnerName = qHN.getHomeOwnName();
							}
						}
						if (typeAction.split(";").length == 3) {
							typeAction = "Download Drafter Package Of " + permitHomeOwnerName + ";"+ typeAction.split(";")[1] + ";" + typeAction.split(";")[2];
						}
					}

				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Create Permit")) {
					if (typeAction.split(";").length >= 2) {
						PermitEntity qHN  = new PermitEntity();
						if (checkValueTypesService.isStringInt(typeAction.split(";")[1])) {
							qHN = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
						}

						String permitHomeOwnerName = "";
						if (qHN != null) {
							if(checkValueTypesService.NotEquals(qHN.getProjectName(), "")) {
								permitHomeOwnerName = qHN.getProjectName();
							}else if(checkValueTypesService.NotEquals(qHN.getHomeOwnLastName(),"")) {
								permitHomeOwnerName = qHN.getHomeOwnLastName()+", "+qHN.getHomeOwnName();
							}else {
								permitHomeOwnerName = qHN.getHomeOwnName();
							}
						}
						if (typeAction.split(";").length == 3) {
							typeAction = "Create Permit " + permitHomeOwnerName + ";" + typeAction.split(";")[1] + ";"
									+ typeAction.split(";")[2];
						}
					}
				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Add Template")) {
					if (typeAction.split(";").length >= 2) {
						PermitEntity qHN  = new PermitEntity();
						if (checkValueTypesService.isStringInt(typeAction.split(";")[1])) {
							qHN = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
						}

						String permitHomeOwnerName = "";
						if (qHN != null) {
							if(checkValueTypesService.NotEquals(qHN.getProjectName(), "")) {
								permitHomeOwnerName = qHN.getProjectName();
							}else if(checkValueTypesService.NotEquals(qHN.getHomeOwnLastName(),"")) {
								permitHomeOwnerName = qHN.getHomeOwnLastName()+", "+qHN.getHomeOwnName();
							}else {
								permitHomeOwnerName = qHN.getHomeOwnName();
							}
						}
						if (typeAction.split(";").length == 3) {
						typeAction = "Create Template " + permitHomeOwnerName + ";" + typeAction.split(";")[1] + ";"+ typeAction.split(";")[2];
						}
					}
				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Delete Permit")) {
					PermitEntity    permit = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);

					String permitHomeOwnerName = "";
					if (permit != null) {
						if(checkValueTypesService.NotEquals(permit.getProjectName(), "")) {
							permitHomeOwnerName = permit.getProjectName();
						}else if(checkValueTypesService.NotEquals(permit.getHomeOwnLastName(), "")) {
							permitHomeOwnerName = permit.getHomeOwnLastName()+", "+permit.getHomeOwnName();
						}else {
							permitHomeOwnerName = permit.getHomeOwnName();
						}
					}
					typeAction = "Delete Permit " + permitHomeOwnerName + ";" + typeAction.split(";")[1] + ";"
							+ typeAction.split(";")[2];

				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Update Status Permit")) {
					PermitEntity    permit = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
					String permitHomeOwnerName = "";
					if (permit != null) {
						if(checkValueTypesService.NotEquals(permit.getProjectName(), "")) {
							permitHomeOwnerName = permit.getProjectName();
						}else if(checkValueTypesService.NotEquals(permit.getHomeOwnLastName(), "")) {
							permitHomeOwnerName = permit.getHomeOwnLastName()+", "+permit.getHomeOwnName();
						}else {
							permitHomeOwnerName = permit.getHomeOwnName();
						}
					}
					typeAction = "Update Status Of " + permitHomeOwnerName + ";" + typeAction.split(";")[1] + ";"
							+ typeAction.split(";")[2];

				} else if (checkValueTypesService.Equals(typeAction.split(";")[0], "Edit User Information")) {
                    AuthentificationEntity authen = userRepo.findById(Long.valueOf(typeAction.split(";")[1].split(":")[1].trim())).orElse(null);
					String userName = "";
					String userLastName = "";
					if (authen != null) {
						userName = (String) authen.getFirstName();
						userLastName = (String) authen.getLastName();
					}
					typeAction = "Edit " + userName + " " + userLastName + " Information;" + typeAction.split(";")[1]
							+ ";" + typeAction.split(";")[2];

				} else if (checkValueTypesService.contains(typeAction.split(";")[0], "Restore")) {

					PermitEntity    permit = permitRepo.findById(Long.valueOf(typeAction.split(";")[1])).orElse(null);
					String permitHomeOwnerName = "";
					if (permit != null) {
						if(checkValueTypesService.NotEquals(permit.getProjectName(), "")) {
							permitHomeOwnerName = permit.getProjectName();
						}else if(checkValueTypesService.NotEquals(permit.getHomeOwnLastName(), "")) {
							permitHomeOwnerName = permit.getHomeOwnLastName()+", "+permit.getHomeOwnName();
						}else {
							permitHomeOwnerName = permit.getHomeOwnName();
						}
					}
					if (typeAction.split(";").length == 3) {
						typeAction = typeAction + " of the Project " + permitHomeOwnerName + ";" + typeAction.split(";")[1]
							+ ";" + typeAction.split(";")[2];
					}
				}else if(checkValueTypesService.contains(typeAction.split(";")[0],"Customize Sheet")){
					typeAction = typeAction.split(";")[1];
				}else if(checkValueTypesService.contains(typeAction.split(";")[0],"Planset Sheet")){
					typeAction = typeAction.split(";")[1];
				}else if(checkValueTypesService.contains(typeAction.split(";")[0],"AHJ libraries")) {
					typeAction = typeAction.split(";")[1];

				}
			}

			HistoryActivityEntity historyActivityEntity = new HistoryActivityEntity();
			if (idUserCo != null) {
				AuthentificationEntity user = userRepo.findById(idUserCo).orElse(null);
				historyActivityEntity.setIdUserCo(user);
			}

			TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
			Calendar date = Calendar.getInstance(); 
			historyActivityEntity.setDate(date.getTime());
			historyActivityEntity.setIpUserCo(ipUserCo);
			historyActivityEntity.setTimeZoneUserCo(timeZone);
			historyActivityEntity.setIsSuccess(isSuccess);
			historyActivityEntity.setTypeAction(typeAction);
			historyActivityEntity.setNumTab(numTab);
			historyActivityEntity.setSessionId(sessionId);
			historyActivityEntity.setOpenDate(openDate);

			historyActivityRepo.save(historyActivityEntity);

			return "success";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	
	public Page<HistoricActivityResult> gethistoricTable(Integer page,Integer size){
		
		try {
			
			Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("date")));
			Page<HistoryActivityEntity> listSheets = historyActivityRepo.findAll(pageable);
	
			return new PageImpl<>(listSheets.stream()
	                .map(event -> new HistoricActivityResult(
	                		event.getDate(), 
	                		event.getIpUserCo(), 
	                		event.getTimeZoneUserCo(), 
	                		checkValueTypesService.contains(event.getTypeAction(), ";") ? event.getTypeAction().split(";")[0] : null, 
	                		event.getIdUserCo() != null ? event.getIdUserCo().getFirstName() : null,
	                		event.getIdUserCo() != null ? event.getIdUserCo().getLastName() : null, 
	                		event.getNumTab(), 
	                		event.getSessionId(), 
	                		event.getOpenDate(), 
	                		event.getIsSuccess()
	                		))
	                .collect(Collectors.toList()), pageable, listSheets.getTotalElements());
			
		} catch (Exception e) {
			e.printStackTrace();
			return new PageImpl<>(new ArrayList<>());
		}
		
		
	}
	 
	public List<HistoricActivityResult> getlibrariesHistoricTable(){
		try {
			return  historyActivityRepo.findlibrariesHistoricTable("libraries");
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public Page<HistoricActivityResult> searchHistoricPage(int page, int size, String filtredValue){
		try {
	        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("date")));
			Page<HistoryActivityEntity> activitiesList = historyActivityRepo.findByInformations(filtredValue.toLowerCase(), pageable);
	        return convertDto(activitiesList, pageable);	        
		} catch (Exception e) {
			e.printStackTrace();
			return new PageImpl<>(new ArrayList<>());
		}
	}
	
	public Page<HistoricActivityResult> convertDto(Page<HistoryActivityEntity> activitiesList, Pageable pageable){
		try {
			return new PageImpl<>(activitiesList.stream()
	        		.map(event -> new HistoricActivityResult(
	                		event.getDate(),
	                		event.getIpUserCo(),
	                		event.getTimeZoneUserCo(),
	                		checkValueTypesService.contains(event.getTypeAction(), ";") ? event.getTypeAction().split(";")[0] : null,
	                		event.getIdUserCo() != null ? event.getIdUserCo().getFirstName() : null,
	                		event.getIdUserCo() != null ? event.getIdUserCo().getLastName() : null,
	                		event.getNumTab(),
	                		event.getSessionId(),
	                		event.getOpenDate(),
	                		event.getIsSuccess()
	                		))
	        		.collect(Collectors.toList()), pageable, activitiesList.getTotalElements());
		} catch (Exception e) {
			e.printStackTrace();
			return new PageImpl<>(new ArrayList<>());
		}
	}
	
	
}
