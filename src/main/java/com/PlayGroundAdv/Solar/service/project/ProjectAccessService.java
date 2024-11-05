package com.PlayGroundAdv.Solar.service.project;

import java.util.Date;
import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.repositories.PermitCompanyInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@EnableScheduling
public class ProjectAccessService {

	final PermitRepository permitRepo;
	final PermitCompanyInfoRepository utilityCompRepo;
	private final SimpMessagingTemplate template;
	final CheckValueTypesService checkValue;

	public ProjectAccessService(PermitRepository permitRepo, PermitCompanyInfoRepository utilityCompRepo,
			SimpMessagingTemplate template, CheckValueTypesService checkValue) {
		super();
		this.permitRepo = permitRepo;
		this.utilityCompRepo = utilityCompRepo;
		this.template = template;
		this.checkValue = checkValue;
	}

	static final String NOT_FOUND = "Entity not found";

	public Boolean refreshProject(Long idPermit) {
		try {
			if (idPermit != null) {
				PermitEntity project = permitRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				return !Boolean.TRUE.equals(project.getOpened());
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String closeProject(Long idPermit) {

		try {
			if (idPermit != null) {
				PermitEntity project = permitRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				project.setOpened(false);
				project.setOpenedBy(null);
				permitRepo.save(project);
				this.template.convertAndSend("/enable-project/" + idPermit, false);
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public Boolean closeProjectRequest(String user, Long idPermit) {

		try {
			if (idPermit != null) {
				PermitEntity project = permitRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (project != null && Boolean.TRUE.equals(project.getOpened())) {
					project.setHasCloseRequest(true);
					project.setHasCloseRequestby(user);
					permitRepo.save(project);
					this.template.convertAndSend("/watch-project/" + idPermit,
							"This project will be closed (Refreshed) by the request of, " + user
									+ ". All your edits will be saved.");
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

	public Boolean requestPermitAccess(Long idPermit) {

		try {
			if (idPermit != null) {
				PermitEntity project = permitRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (project != null && Boolean.TRUE.equals(project.getOpened())) {
					this.template.convertAndSend("/watch-project/" + idPermit,
							"Another User is Requesting you Exit Project as they Need to Edit Project ASAP.");
					return true;
				}

			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

	public Boolean checkProjectEditRequest(Long idPermit) {

		try {
			if (idPermit != null) {
				PermitEntity project = permitRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (project != null) {
					return project.getHasCloseRequest();
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getOpenedBy(Long idPermit) {
		try {
			if (idPermit != null) {
				PermitEntity project = permitRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + idPermit));
				if (project != null && project.getOpenedBy() != null) {
					return project.getOpenedBy().getFirstName() + " " + project.getOpenedBy().getLastName();
				}
			}
			return "Not Found";
		} catch (Exception e) {
			return "Not Found";
		}
	}

//	Utility Company Access

	public Boolean refreshUtility(Long idPermit) {
		try {
			if (idPermit != null) {
				PermitCompanyInfoEntity utility = utilityCompRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				return !Boolean.TRUE.equals(utility.getOpened());
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String closeUtility(Long idPermit) {

		try {
			if (idPermit != null) {
				PermitCompanyInfoEntity utility = utilityCompRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				utility.setOpened(false);
				utility.setOpenedBy(null);
				utilityCompRepo.save(utility);
				this.template.convertAndSend("/enable-utility/" + idPermit, false);
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public Boolean closeUtilityRequest(String user, Long idPermit) {

		try {
			if (idPermit != null) {
				PermitCompanyInfoEntity utility = utilityCompRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (utility != null && Boolean.TRUE.equals(utility.getOpened())) {
					utility.setHasCloseRequest(true);
					utility.setHasCloseRequestby(user);
					utilityCompRepo.save(utility);
					this.template.convertAndSend("/watch-utility/" + idPermit,
							"This project will be closed (Refreshed) by the request of, " + user
									+ ". All your edits will be saved.");
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

	public Boolean requestUtilityAccess(Long idPermit) {

		try {
			if (idPermit != null) {
				PermitCompanyInfoEntity utility = utilityCompRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (utility != null && Boolean.TRUE.equals(utility.getOpened())) {
					this.template.convertAndSend("/watch-utility/" + idPermit,
							"Another User is Requesting you Exit Project as they Need to Edit Project ASAP.");
					return true;
				}

			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

	public Boolean checkUtilityEditRequest(Long idPermit) {

		try {
			if (idPermit != null) {
				PermitCompanyInfoEntity utility = utilityCompRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
				if (utility != null) {
					return utility.getHasCloseRequest();
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getUtilityOpenedBy(Long idPermit) {
		try {
			if (idPermit != null) {
				PermitCompanyInfoEntity utility = utilityCompRepo.findById(idPermit)
						.orElseThrow(() -> new ResourceNotFoundException("Entity not found for this id :" + idPermit));
				if (utility != null && utility.getOpenedBy() != null) {
					return utility.getOpenedBy().getFirstName() + " " + utility.getOpenedBy().getLastName();
				}
			}
			return "Not Found";
		} catch (Exception e) {
			return "Not Found";
		}
	}

	@Scheduled(cron = "0 0 1 * * *", zone = "America/Los_Angeles")
	public void closeOpenedProjects() {
		try {
			System.out.println("closeOpenedProjects at "+new Date());
			List<PermitEntity> project = permitRepo.findByOpenedTrue();
			for (PermitEntity p : project) {
				p.setWebSocketSession(null);
				p.setOpened(false);
				p.setOpenedBy(null);
				permitRepo.save(p);
				this.template.convertAndSend("/enable-project/" + p.getId(), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
