package com.PlayGroundAdv.Solar.service.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ContactsNameEntity;
import com.PlayGroundAdv.Solar.entity.ContractorInformationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.ProjectNotes;
import com.PlayGroundAdv.Solar.entity.ProjectRequestEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ProjectContactsModel;
import com.PlayGroundAdv.Solar.model.ProjectNotesDto;
import com.PlayGroundAdv.Solar.model.ProjectRequestModel;
import com.PlayGroundAdv.Solar.repositories.ContactsNameRepository;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectNotesRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectRequestRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ProjectNotesServices {

	final CheckValueTypesService checkValueTypesService;
	final ContractorInformationRepository contractorInfoRepo;
	final ProjectNotesRepository projectNotesRepo;
	final PermitRepository projectRepo;
	final PathRepository pathRepo;
	final ContactsNameRepository contactRepo;
	final ProjectRequestRepository projectRequestRepo;

	public ProjectNotesServices(CheckValueTypesService checkValueTypesService,
			ContractorInformationRepository contractorInfoRepo, ProjectNotesRepository projectNotesRepo,
			PermitRepository projectRepo, PathRepository pathRepo, ContactsNameRepository contactRepo,
			ProjectRequestRepository projectRequestRepo) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.contractorInfoRepo = contractorInfoRepo;
		this.projectNotesRepo = projectNotesRepo;
		this.projectRepo = projectRepo;
		this.pathRepo = pathRepo;
		this.contactRepo = contactRepo;
		this.projectRequestRepo = projectRequestRepo;
	}
	// CR-1209 Get Number of Projects Contacts Owner

	public Integer getProjectContactsOwnerNumber(Long id) {

		try {
			AuthentificationEntity user = projectRepo.findAuthentificationEntityByID(id);
			return contactRepo.countByIdUser(user);

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	// CR-1209 Get Project Contacts names from Authentification Entity
	public ProjectContactsModel getProjectContactsOwnerAuth(Long id) {
		try {
			Long idUser = projectRepo.findProjectOwnerID(id);
			return contractorInfoRepo.getProjectContactMod(idUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ProjectContactsModel();
		}

	}

	// CR-1209 Get Project Contacts names from Authentification Entity & Contacts
	// Names Entity
	public List<ProjectContactsModel> getProjectContactsOwnerAuthContN(Long id) {
		try {
			Long idUser = projectRepo.findProjectOwnerID(id);
			return contractorInfoRepo.getProjectsContactModel(idUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	// CR-1209 Add new Project Request

	public ProjectRequestModel addNewRequest(ProjectRequestEntity newRequest, String firstName, String lastname,
			Long idPermit) {
		ProjectRequestEntity projectrequestEntity = new ProjectRequestEntity();
		try {

			projectrequestEntity.setRequestTitle(newRequest.getRequestTitle());
			projectrequestEntity.setRequest(newRequest.getRequest());
			projectrequestEntity.setRequestMethod(newRequest.getRequestMethod());
			projectrequestEntity.setRequestedBy(newRequest.getRequestedBy());
			if (newRequest.getFirstNameProjContact() != null) {
				projectrequestEntity.setFirstNameProjContact(newRequest.getFirstNameProjContact());
			}
			if (newRequest.getLastNameProjContact() != null) {
				projectrequestEntity.setLastNameProjContact(newRequest.getLastNameProjContact());
			}
			if (newRequest.getProjectContactPhone() != null) {
				projectrequestEntity.setProjectContactPhone(newRequest.getProjectContactPhone());
			}
			if (newRequest.getProjectContactEmail() != null) {
				projectrequestEntity.setProjectContactEmail(newRequest.getProjectContactEmail());
			}

			projectrequestEntity.setFirstNameUser(firstName);
			projectrequestEntity.setLastNameUser(lastname);

			PermitEntity permit = projectRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Permit not found for this id :" +idPermit));
			projectrequestEntity.setPermit(permit);

			ContractorInformationEntity userInfo = contractorInfoRepo
					.findByAuthentificationEntityId(permit.getAuthentificationEntity().getId());

			if (checkValueTypesService.Equals(newRequest.getRequestedBy(), "Add New")
					&& !(checkValueTypesService.NotEquals(userInfo.getSecondContactFirstName(), ""))
					&& !(checkValueTypesService.NotEquals(userInfo.getSecondContactLastName(), ""))) {
				userInfo.setSecondContactFirstName(newRequest.getFirstNameProjContact());
				userInfo.setSecondContactLastName(newRequest.getLastNameProjContact());
				if (newRequest.getProjectContactPhone() != null) {
					userInfo.setSecondContactPhone(newRequest.getProjectContactPhone());
				}
				if (newRequest.getProjectContactEmail() != null) {
					userInfo.setSecondContactEmail(newRequest.getProjectContactEmail());
				}
				userInfo.setIsProjectAddInclud(true);
				contractorInfoRepo.save(userInfo);

			} else if (checkValueTypesService.Equals(newRequest.getRequestedBy(), "Add New")
					&& !(checkValueTypesService.NotEquals(userInfo.getThirdContactPhone(), ""))
					&& !(checkValueTypesService.NotEquals(userInfo.getLastNameContact(), ""))) {
				userInfo.setThirdContact(newRequest.getFirstNameProjContact());
				userInfo.setLastNameContact(newRequest.getLastNameProjContact());
				if (newRequest.getProjectContactEmail() != null) {
					userInfo.setThirdContactEmail(newRequest.getProjectContactEmail());
				}
				if (newRequest.getProjectContactPhone() != null) {
					userInfo.setThirdContactPhone(newRequest.getProjectContactPhone());
				}
				userInfo.setIsProjectAddInclud(true);
				contractorInfoRepo.save(userInfo);
			} else if (checkValueTypesService.Equals(newRequest.getRequestedBy(), "Add New")) {
				ContactsNameEntity contactsNameEntity = new ContactsNameEntity();
				contactsNameEntity.setFirstname(newRequest.getFirstNameProjContact());
				contactsNameEntity.setLastName(newRequest.getLastNameProjContact());
				contactsNameEntity.setProjectContactEmail(newRequest.getProjectContactEmail());
				contactsNameEntity.setProjectContactPhone(newRequest.getProjectContactPhone());
				contactsNameEntity.setIdUser(permit.getAuthentificationEntity());
				contactRepo.save(contactsNameEntity);

			}
			Date currentDate = new Date();
			projectrequestEntity.setDateAddNotif(currentDate);

			projectrequestEntity.setDaterequestString(newRequest.getDaterequestString());
			projectrequestEntity.setTime(newRequest.getTime());
			projectrequestEntity.setLastUpdated(currentDate);
			projectRequestRepo.save(projectrequestEntity);

			return new ProjectRequestModel(projectrequestEntity.getId(), projectrequestEntity.getRequestedBy(),
					projectrequestEntity.getFirstNameProjContact(), projectrequestEntity.getLastNameProjContact(),
					projectrequestEntity.getDateAddNotif(), projectrequestEntity.getDaterequestString(),
					projectrequestEntity.getTime(), projectrequestEntity.getLastUpdated());

		} catch (Exception e) {
			e.printStackTrace();
			return new ProjectRequestModel();
		}

	}

	// CR-1209 Get All Projects Requests by permit
	public List<ProjectRequestModel> getAllRequests(Long idPermit) {
		
		try {
			return projectRequestRepo.getProjectRequestModel(idPermit);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public ProjectNotesDto insertFloridaRafterNote(ProjectNotesDto params) {
		try {
			if (Boolean.TRUE.equals(projectNotesRepo.existsByProjectId(params.getProject()))) {
				ProjectNotes note = projectNotesRepo.findByProjectId(params.getProject());
				note.setFloridaRafterDesign(params.getFloridaRafterDesign());
				note.setFloridaRafterDesignFileName(params.getFloridaRafterDesignFileName());
				projectNotesRepo.save(note);
				params.setId(note.getId());
			} else {
				PermitEntity project = projectRepo.findById(params.getProject()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" + params.getProject()));
				ProjectNotes note = new ProjectNotes(project, params.getFloridaRafterDesign(), params.getFloridaRafterDesignFileName());
				projectNotesRepo.save(note);
				params.setId(note.getId());
			}
			return params;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String insetNotetoADV(String note, Long idPermit) {
		try {
			PermitEntity permit = projectRepo.findById(idPermit).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" + idPermit));
			permit.setInsertRoofNote(note);
			projectRepo.save(permit);
			return "done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public ProjectNotesDto uploadFloridaRafterNote(MultipartFile file, Long idPermit, String note) {
		try {
			String path = pathRepo.findFilePath();
			if (!new File(path + idPermit + "/FloridaRafterNotes/").exists()) {
				new File(path + idPermit + "/FloridaRafterNotes/").mkdir();
			}else {
				clearFolder(new File(path + idPermit + "/FloridaRafterNotes/"));
			}
			byte[] bytes = file.getBytes();
			Path pathUpl = Paths.get(path + idPermit + "/FloridaRafterNotes/" + file.getOriginalFilename());
			Files.write(pathUpl, bytes);
			return insertFloridaRafterNote(new ProjectNotesDto(idPermit, note, file.getOriginalFilename()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResponseEntity<byte[]> downloadNoteFile(Long idPermit) {
		
		File[] files = new File(pathRepo.findFilePath() + idPermit + "/FloridaRafterNotes/").listFiles();
		Path path = Paths.get(pathRepo.findFilePath() + idPermit + "/FloridaRafterNotes/"+files[0].getName());
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		String filename = files[0].getName();
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<>(contents, headers, org.springframework.http.HttpStatus.OK);
	}
	
	private void clearFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	        	f.delete();
	        }
	    }
	}

}
