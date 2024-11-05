package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.model.AdvNoteModel;
import com.PlayGroundAdv.Solar.model.ProjectContactsModel;
import com.PlayGroundAdv.Solar.model.ProjectNotesDto;
import com.PlayGroundAdv.Solar.model.ProjectRequestModel;
import com.PlayGroundAdv.Solar.service.project.ProjectNotesServices;

@RestController
@RequestMapping("/projectNotes")
public class ProjectNotesController {

	final ProjectNotesServices projectNotesServices;

	public ProjectNotesController(ProjectNotesServices projectNotesServices) {
		super();
		this.projectNotesServices = projectNotesServices;
	}

	// CR-1209 Get Project Contacts names from Authentification Entity
	@PostMapping("/getProjectContactsOwnerAuth")
	public ProjectContactsModel getProjectContactsOwnerAuth(@RequestBody Long id) {
		return projectNotesServices.getProjectContactsOwnerAuth(id);
	}

	// CR-1209 Get Project Contacts names from Authentification Entity & Contacts
	@PostMapping("/getProjectContactsOwnerAuthContN")
	public List<ProjectContactsModel> getProjectContactsOwnerAuthContN(@RequestBody Long id) {
		return projectNotesServices.getProjectContactsOwnerAuthContN(id);
	}

	// CR-1209 Get number of Project Contacts names
	@PostMapping("/getProjectContactsOwnerNumber")
	public Integer getProjectContactsOwnerNumber(@RequestBody Long id) {
		return projectNotesServices.getProjectContactsOwnerNumber(id);
	}

	// CR-1209 Add new Project Request
	@PostMapping("/addNewRequest")
	public ProjectRequestModel addNewRequest(@RequestBody AdvNoteModel nm) {
		return projectNotesServices.addNewRequest(nm.getNewRequest(), nm.getFirstName(), nm.getLastname(),
				nm.getIdPermit());
	}

	// CR-1209 Get all Project Requests by id of permit
	@PostMapping("/getAllRequests/{idPermit}")
	public List<ProjectRequestModel> getAllRequests(@PathVariable Long idPermit) {
		return projectNotesServices.getAllRequests(idPermit);
	}
	
	// CR-3863
	@PostMapping("/insertFloridaRafterNote")
	public ProjectNotesDto insertFloridaRafterNote(@RequestBody ProjectNotesDto note) {
		return projectNotesServices.insertFloridaRafterNote(note);
    }
	// CR-3863
	@PostMapping("/uploadFloridaRafterNote")
	public ProjectNotesDto uploadFloridaRafterNote(@RequestParam("file") MultipartFile file,
			@RequestParam("idPermit") Long idPermit, @RequestParam("note") String note) {
		return projectNotesServices.uploadFloridaRafterNote(file, idPermit, note);
    }
	
	@PostMapping("/downlodFloridaRafterNote")
	public ResponseEntity<byte[]> downloadNoteFile(@RequestBody Long idPermit) {
		return projectNotesServices.downloadNoteFile(idPermit);
    }

	@PostMapping("/insetNotetoADV")
	public String insetNotetoADV(@RequestBody String[] note) throws Exception {
		return projectNotesServices.insetNotetoADV(note[0], Long.valueOf(note[1]));
	}
}
