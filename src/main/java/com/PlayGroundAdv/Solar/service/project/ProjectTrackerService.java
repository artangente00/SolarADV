package com.PlayGroundAdv.Solar.service.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.InterconnectionsEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.ProjectsTrackerEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.InterconnectionModel;
import com.PlayGroundAdv.Solar.model.InterconnectionRequest;
import com.PlayGroundAdv.Solar.model.ProjectTrackerModel;
import com.PlayGroundAdv.Solar.repositories.InterconnectionsRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectsTrackerRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ProjectTrackerService {

	final CheckValueTypesService checkValueTypesService;
	final ProjectsTrackerRepository projectsTrackerRepo;
	final InterconnectionsRepository interconnectionsRepo;
	final PermitRepository permitRepo;
	
	public ProjectTrackerService(CheckValueTypesService checkValueTypesService, ProjectsTrackerRepository projectsTrackerRepo,
			InterconnectionsRepository interconnectionsRepo, PermitRepository permitRepo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.projectsTrackerRepo = projectsTrackerRepo;
		this.interconnectionsRepo = interconnectionsRepo;
		this.permitRepo = permitRepo;
	}

	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	public List<ProjectTrackerModel> getprojectTrackerDashboard() {
		List<ProjectTrackerModel> aaData= new ArrayList<>();
		try {
			List<ProjectsTrackerEntity> trackerList= projectsTrackerRepo.getProjectsTrackerEntity("Delivered", "Request Revision", "RFI Pending", "Submitted", false);
			

			if (trackerList != null) {

				for (ProjectsTrackerEntity tracker : trackerList) {
					if (tracker != null) {
						ProjectTrackerModel projectTracker = new ProjectTrackerModel();
						if (tracker.getPermit() != null) {
							if(checkValueTypesService.NotEquals(tracker.getPermit().getProjectName(), "")) {
								projectTracker.setName(tracker.getPermit().getProjectName());
							}else {
								if(checkValueTypesService.NotEquals(tracker.getPermit().getHomeOwnLastName(), "")) {
									projectTracker.setName(tracker.getPermit().getHomeOwnLastName()+", "+tracker.getPermit().getHomeOwnName());
								}else {
									projectTracker.setName(tracker.getPermit().getHomeOwnName());
								}
							}
							
							if(tracker.getPermit().getAuthentificationEntity() != null) {
								if(checkValueTypesService.NotEquals(tracker.getPermit().getAuthentificationEntity().getCompany(), "")) {
									projectTracker.setOwner(tracker.getPermit().getAuthentificationEntity().getCompany());
								}else {
									projectTracker.setOwner(tracker.getPermit().getAuthentificationEntity().getFirstName()+" "+tracker.getPermit().getAuthentificationEntity().getLastName());
								}
							}
							if (checkValueTypesService.Equals(tracker.getPermit().getStatus(), "Delivered")) {
								projectTracker.setStatus("Delivered");
							} else if (checkValueTypesService.Equals(tracker.getPermit().getStatus(), "Submitted")) {
								projectTracker.setStatus("Submitted");
							} else if (checkValueTypesService.Equals(tracker.getPermit().getStatus(), "RFI Pending")) {
								projectTracker.setStatus("RFI Pending");
							} else {
								projectTracker.setStatus("Request Revision");
							}

							projectTracker.setCreated(formatter.format(tracker.getPermit().getCreationPermitDate()));
						}
						if (tracker.getProjectManager() != null) {
							projectTracker.setCurrentPM(tracker.getProjectManager());
						} else {
							projectTracker.setCurrentPM("");
						}
						DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH");
						if (tracker.getProjectEditStarted() != null) {
							projectTracker.setProjectEditStarted(df.format(tracker.getProjectEditStarted()) + " H");
						} else {
							projectTracker.setProjectEditStarted("");
						}
						if (tracker.getProjectEditCompleted() != null) {
							projectTracker.setProjectEditCompleted(df.format(tracker.getProjectEditCompleted()) + " H");
						} else {
							projectTracker.setProjectEditCompleted("");
						}
						if (tracker.getSubmitted() != null) {
							projectTracker.setSubmit(df.format(tracker.getSubmitted()) + " H");
						} else {
							projectTracker.setSubmit("");
						}
						if (tracker.getSubmittedBy() != null) {
							projectTracker.setSubmitBy(tracker.getSubmittedBy());
						} else {
							projectTracker.setSubmitBy("");
						}
						if (tracker.getSubmittedAdvRfi() != null) {
							projectTracker.setSubmitADVRFI(df.format(tracker.getSubmittedAdvRfi()) + " H");
						} else {
							projectTracker.setSubmitADVRFI("");
						}
						if (tracker.getSubmittedContRfi() != null) {
							projectTracker.setSubmitContRFI(df.format(tracker.getSubmittedContRfi()) + " H");
						} else {
							projectTracker.setSubmitContRFI("");
						}
						if (tracker.getRequestRevision() != null) {
							projectTracker.setRequestRevision(df.format(tracker.getRequestRevision()) + " H");
						} else {
							projectTracker.setRequestRevision("");
						}
						if (tracker.getReopenProject() != null) {
							projectTracker.setReopenProject(df.format(tracker.getReopenProject()) + " H");
						} else {
							projectTracker.setReopenProject("");
						}
						if (tracker.getDelivred() != null) {
							projectTracker.setDelivered(df.format(tracker.getDelivred()) + " H");
						} else {
							projectTracker.setDelivered("");
						}
						if (tracker.getDrafterDataEditStarted() != null) {
							projectTracker
									.setDrafterDataEditStarted(df.format(tracker.getDrafterDataEditStarted()) + " H");
						} else {
							projectTracker.setDrafterDataEditStarted("");
						}
						if (tracker.getDrafterDataEditCompleted() != null) {
							projectTracker.setDrafterDataEditCompleted(
									df.format(tracker.getDrafterDataEditCompleted()) + " H");
						} else {
							projectTracker.setDrafterDataEditCompleted("");
						}
						if (tracker.getDownloadDrafter() != null) {
							projectTracker.setDownloadDrafter(df.format(tracker.getDownloadDrafter()) + " H");
						} else {
							projectTracker.setDownloadDrafter("");
						}
						if (tracker.getDrafter() != null) {
							projectTracker.setDrafter(tracker.getDrafter());
						} else {
							projectTracker.setDrafter("");
						}
						if (tracker.getAdvInputsEditStarted() != null) {
							projectTracker.setAdvInputsEditStarted(df.format(tracker.getAdvInputsEditStarted()) + " H");
						} else {
							projectTracker.setAdvInputsEditStarted("");
						}
						if (tracker.getAdvInputsEditCompleted() != null) {
							projectTracker
									.setAdvInputsEditCompleted(df.format(tracker.getAdvInputsEditCompleted()) + " H");
						} else {
							projectTracker.setAdvInputsEditCompleted("");
						}
						if (tracker.getAdvTeamMember() != null) {
							projectTracker.setAdvTeamMember(tracker.getAdvTeamMember());
						} else {
							projectTracker.setAdvTeamMember("");
						}
						if (tracker.getTotalProjectTime() != null) {
							projectTracker.setTimeLine(tracker.getTotalProjectTime());
						} else {
							projectTracker.setTimeLine("");
						}
						if (tracker.getProjectTimeLessRfi() != null) {
							projectTracker.setTimeLineLessRfi(tracker.getProjectTimeLessRfi());
						} else {
							projectTracker.setTimeLineLessRfi("");
						}

						aaData.add(projectTracker);
					}
				}

			}
			

			return aaData;
		} catch (Exception e) {
			e.printStackTrace();
			return aaData;
		}
		
	}
	
	public List<ProjectTrackerModel> getTrackerDashboardContractor(Long id) {
		List<ProjectTrackerModel> aaData= new ArrayList<>();
		try {
			List<ProjectsTrackerEntity> trackerList= projectsTrackerRepo.getProjectsTrackerEntityByContractor("Delivered", "Request Revision", "RFI Pending", "Submitted", false, id);
			

			if(trackerList != null) {
				
				for (ProjectsTrackerEntity tracker : trackerList) {
					if (tracker != null) {
						ProjectTrackerModel projectTracker = new ProjectTrackerModel();
						if (tracker.getPermit() != null) {
							if(checkValueTypesService.NotEquals(tracker.getPermit().getProjectName(), "")) {
								projectTracker.setName(tracker.getPermit().getProjectName());
							}else {
								projectTracker.setName(tracker.getPermit().getHomeOwnLastName()+", "+tracker.getPermit().getHomeOwnName());
							}
							if(tracker.getPermit().getAuthentificationEntity() != null) {
							    projectTracker.setOwner(tracker.getPermit().getAuthentificationEntity().getFirstName() + " "
									+ tracker.getPermit().getAuthentificationEntity().getLastName());
							}
							if (checkValueTypesService.Equals(tracker.getPermit().getStatus(), "Delivered")) {
								projectTracker.setStatus("<span class='label bg-color-darken'>Delivered</span>");
							} else if (checkValueTypesService.Equals(tracker.getPermit().getStatus(), "Submitted")) {
								projectTracker.setStatus("<span class='label label-warning'>Submitted</span>");
							} else if (checkValueTypesService.Equals(tracker.getPermit().getStatus(), "RFI Pending")) {
								projectTracker.setStatus("<span class='label bg-color-yellow'>RFI Pending</span>");
							} else {
								projectTracker.setStatus("<span class='label bg-color-red'>Request Revision</span>");
							}

							projectTracker.setCreated(formatter.format(tracker.getPermit().getCreationPermitDate()));
						}
						if (tracker.getProjectManager() != null) {
							projectTracker.setCurrentPM(tracker.getProjectManager());
						} else {
							projectTracker.setCurrentPM("");
						}
						DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH");
						if (tracker.getProjectEditStarted() != null) {
							projectTracker.setProjectEditStarted(df.format(tracker.getProjectEditStarted()) + " H");
						} else {
							projectTracker.setProjectEditStarted("");
						}
						if (tracker.getProjectEditCompleted() != null) {
							projectTracker.setProjectEditCompleted(df.format(tracker.getProjectEditCompleted()) + " H");
						} else {
							projectTracker.setProjectEditCompleted("");
						}
						if (tracker.getSubmitted() != null) {
							projectTracker.setSubmit(df.format(tracker.getSubmitted()) + " H");
						} else {
							projectTracker.setSubmit("");
						}
						if (tracker.getSubmittedBy() != null) {
							projectTracker.setSubmitBy(tracker.getSubmittedBy());
						} else {
							projectTracker.setSubmitBy("");
						}
						if (tracker.getSubmittedAdvRfi() != null) {
							projectTracker.setSubmitADVRFI(df.format(tracker.getSubmittedAdvRfi()) + " H");
						} else {
							projectTracker.setSubmitADVRFI("");
						}
						if (tracker.getSubmittedContRfi() != null) {
							projectTracker.setSubmitContRFI(df.format(tracker.getSubmittedContRfi()) + " H");
						} else {
							projectTracker.setSubmitContRFI("");
						}
						if (tracker.getRequestRevision() != null) {
							projectTracker.setRequestRevision(df.format(tracker.getRequestRevision()) + " H");
						} else {
							projectTracker.setRequestRevision("");
						}
						if (tracker.getReopenProject() != null) {
							projectTracker.setReopenProject(df.format(tracker.getReopenProject()) + " H");
						} else {
							projectTracker.setReopenProject("");
						}
						if (tracker.getDelivred() != null) {
							projectTracker.setDelivered(df.format(tracker.getDelivred()) + " H");
						} else {
							projectTracker.setDelivered("");
						}
						if (tracker.getDrafterDataEditStarted() != null) {
							projectTracker
									.setDrafterDataEditStarted(df.format(tracker.getDrafterDataEditStarted()) + " H");
						} else {
							projectTracker.setDrafterDataEditStarted("");
						}
						if (tracker.getDrafterDataEditCompleted() != null) {
							projectTracker.setDrafterDataEditCompleted(
									df.format(tracker.getDrafterDataEditCompleted()) + " H");
						} else {
							projectTracker.setDrafterDataEditCompleted("");
						}
						if (tracker.getDownloadDrafter() != null) {
							projectTracker.setDownloadDrafter(df.format(tracker.getDownloadDrafter()) + " H");
						} else {
							projectTracker.setDownloadDrafter("");
						}
						if (tracker.getDrafter() != null) {
							projectTracker.setDrafter(tracker.getDrafter());
						} else {
							projectTracker.setDrafter("");
						}
						if (tracker.getAdvInputsEditStarted() != null) {
							projectTracker.setAdvInputsEditStarted(df.format(tracker.getAdvInputsEditStarted()) + " H");
						} else {
							projectTracker.setAdvInputsEditStarted("");
						}
						if (tracker.getAdvInputsEditCompleted() != null) {
							projectTracker
									.setAdvInputsEditCompleted(df.format(tracker.getAdvInputsEditCompleted()) + " H");
						} else {
							projectTracker.setAdvInputsEditCompleted("");
						}
						if (tracker.getAdvTeamMember() != null) {
							projectTracker.setAdvTeamMember(tracker.getAdvTeamMember());
						} else {
							projectTracker.setAdvTeamMember("");
						}
						if (tracker.getTotalProjectTime() != null) {
							projectTracker.setTimeLine(tracker.getTotalProjectTime());
						} else {
							projectTracker.setTimeLine("");
						}
						if (tracker.getProjectTimeLessRfi() != null) {
							projectTracker.setTimeLineLessRfi(tracker.getProjectTimeLessRfi());
						} else {
							projectTracker.setTimeLineLessRfi("");
						}

						aaData.add(projectTracker);
					}
				}
			
			}
			

			return aaData;
		} catch (Exception e) {
			e.printStackTrace();
			return aaData;
		}
		
	}
	
	public List<InterconnectionModel> getprojectInterconection() {
		
		List<InterconnectionModel> aaData = new ArrayList<>();
		List<InterconnectionsEntity> interconnList = new ArrayList<>();
		try {
			List<InterconnectionRequest> permit = projectsTrackerRepo.getprojectInterconection(false);
			interconnList= interconnectionsRepo.findAll();
			
			if (permit != null) {
				for (InterconnectionRequest permitEntity : permit) {

					if (permitEntity != null) {
						InterconnectionModel interconnection = new InterconnectionModel();
						interconnection.setId(permitEntity.getId());
						interconnection.setName(permitEntity.getName());
						if(checkValueTypesService.NotEquals(permitEntity.getCompany(), "")) {
							interconnection.setOwner(permitEntity.getCompany());
						}else {
							interconnection.setOwner(permitEntity.getOwner());
						}
						
						interconnection.setUtility(permitEntity.getUtility());
						interconnection.setInterconnectionType(permitEntity.getInterconnectionType());
						interconnection.setUtilityInformation(permitEntity.getUtilityInformation());
						interconnection.setUtilityRef(permitEntity.getUtilityRef());
						interconnection.setLastName(permitEntity.getLastName());
						interconnection.setProjectName(permitEntity.getProjectName());

						if (interconnList != null) {
							for (InterconnectionsEntity interco : interconnList) {
								if (interco != null && interco.getPermit() != null && interco.getPermit().getId() == permitEntity.getId()) {
									interconnection.setContactClient(interco.getContactClient());
									if (interco.getUtility() != null) {
										interconnection.setUtility(interco.getUtility());
									}
									if (interco.getInterconnectionType() != null) {
										interconnection.setInterconnectionType(interco.getInterconnectionType());
									}

									interconnection.setAssignedTo(interco.getAssignedTo());
									interconnection.setIntercRequested(interco.getIntercRequested());
									interconnection.setAppFeeReceived(interco.getAppFeeReceived());
									interconnection.setUtiReceivedAppFee(interco.getUtiReceivedAppFee());
									interconnection.setAppFeeAmount(interco.getAppFeeAmount());
									interconnection.setIntercSubmitted(interco.getIntercSubmitted());
									interconnection.setCustomerSigned(interco.getCustomerSigned());
									interconnection.setAppReviewed(interco.getAppReviewed());
									interconnection.setPtoGranted(interco.getPtoGranted());
									interconnection.setInformedofPTO(interco.getInformedofPTO());
									if (interco.getUtilityInformation() != null) {
										interconnection.setUtilityInformation(interco.getUtilityInformation());
									}
									interconnection.setAccountHolder(interco.getAccountHolder());
									if (interco.getUtilityRef() != null) {
										interconnection.setUtilityRef(interco.getUtilityRef());
									}
									interconnection.setNotes(interco.getNotes());
								}

							}

						}
						aaData.add(interconnection);
					}
				}
			}
			return aaData;
		
	    }catch (Exception e) {
			e.printStackTrace();
			return aaData;
		}
     }
	
	public String saveInterconection(InterconnectionModel interconnection) {
		
		try {

			InterconnectionsEntity interconnectionEdit = interconnectionsRepo.findOneByPermitId(interconnection.getId());
			if(interconnectionEdit != null) {				
				interconnectionEdit.setContactClient(interconnection.getContactClient());
				interconnectionEdit.setUtility(interconnection.getUtility());
				interconnectionEdit.setInterconnectionType(interconnection.getInterconnectionType());
				interconnectionEdit.setAssignedTo(interconnection.getAssignedTo());
				interconnectionEdit.setIntercRequested(interconnection.getIntercRequested());
				interconnectionEdit.setAppFeeReceived(interconnection.getAppFeeReceived());
				interconnectionEdit.setUtiReceivedAppFee(interconnection.getUtiReceivedAppFee());
				interconnectionEdit.setAppFeeAmount(interconnection.getAppFeeAmount());
				interconnectionEdit.setIntercSubmitted(interconnection.getIntercSubmitted());
				interconnectionEdit.setCustomerSigned(interconnection.getCustomerSigned());
				interconnectionEdit.setAppReviewed(interconnection.getAppReviewed());
				interconnectionEdit.setPtoGranted(interconnection.getPtoGranted());
				interconnectionEdit.setInformedofPTO(interconnection.getInformedofPTO());
				interconnectionEdit.setUtilityInformation(interconnection.getUtilityInformation());
				interconnectionEdit.setAccountHolder(interconnection.getAccountHolder());
				interconnectionEdit.setUtilityRef(interconnection.getUtilityRef());
				interconnectionEdit.setNotes(interconnection.getNotes());
				interconnectionsRepo.save(interconnectionEdit);
				
				return "success";
			}else {
				
				PermitEntity permit= permitRepo.findById(interconnection.getId()).orElseThrow(
						() -> new ResourceNotFoundException("Permit not found for this id :" +interconnection.getId()));
				InterconnectionsEntity interconnectionEdited = new InterconnectionsEntity();
				interconnectionEdited.setPermit(permit);
				interconnectionEdited.setContactClient(interconnection.getContactClient());
				interconnectionEdited.setUtility(interconnection.getUtility());
				interconnectionEdited.setInterconnectionType(interconnection.getInterconnectionType());
				interconnectionEdited.setAssignedTo(interconnection.getAssignedTo());
				interconnectionEdited.setIntercRequested(interconnection.getIntercRequested());
				interconnectionEdited.setAppFeeReceived(interconnection.getAppFeeReceived());
				interconnectionEdited.setUtiReceivedAppFee(interconnection.getUtiReceivedAppFee());
				interconnectionEdited.setAppFeeAmount(interconnection.getAppFeeAmount());
				interconnectionEdited.setIntercSubmitted(interconnection.getIntercSubmitted());
				interconnectionEdited.setCustomerSigned(interconnection.getCustomerSigned());
				interconnectionEdited.setAppReviewed(interconnection.getAppReviewed());
				interconnectionEdited.setPtoGranted(interconnection.getPtoGranted());
				interconnectionEdited.setInformedofPTO(interconnection.getInformedofPTO());
				interconnectionEdited.setUtilityInformation(interconnection.getUtilityInformation());
				interconnectionEdited.setAccountHolder(interconnection.getAccountHolder());
				interconnectionEdited.setUtilityRef(interconnection.getUtilityRef());
				interconnectionEdited.setNotes(interconnection.getNotes());
				interconnectionsRepo.save(interconnectionEdited);
				
				return "success";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
}