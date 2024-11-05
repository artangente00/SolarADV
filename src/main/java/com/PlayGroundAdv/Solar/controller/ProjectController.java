package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.model.GetFileByIdResults;
import com.PlayGroundAdv.Solar.model.GetPlansetByIdResults;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.PermitResponsePrime;
import com.PlayGroundAdv.Solar.model.ProjectContactsEmailModel;
import com.PlayGroundAdv.Solar.model.ProjectEmailModel;
import com.PlayGroundAdv.Solar.model.SearchOptionsList;
import com.PlayGroundAdv.Solar.model.StringModelResult;
import com.PlayGroundAdv.Solar.model.UtilsModel;
import com.PlayGroundAdv.Solar.model.project.DelayArchiveRequest;
import com.PlayGroundAdv.Solar.service.copy_project.CopyProjectService;
import com.PlayGroundAdv.Solar.service.export_project.ExportProjectSvcService;
import com.PlayGroundAdv.Solar.service.project.ArchiveProjectService;
import com.PlayGroundAdv.Solar.service.project.GetPermitsService;
import com.PlayGroundAdv.Solar.service.project.GetProjectByIdService;
import com.PlayGroundAdv.Solar.service.project.NewProjectService;
import com.PlayGroundAdv.Solar.service.project.PermitService;
import com.PlayGroundAdv.Solar.service.project.ProjectFilesManagement;
import com.PlayGroundAdv.Solar.service.project.SearchProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/project")
public class ProjectController {

	final PermitService permitService;
	final GetPermitsService getPermitsService;
	final ExportProjectSvcService exportProjectSvcService;
	final CopyProjectService copyProjectService;
	final SearchProjectService searchProjectService;
	final ProjectFilesManagement filesManagement;
	final NewProjectService newProject;
	final ArchiveProjectService archiveService;
	public ProjectController(PermitService permitService, GetPermitsService getPermitsService,
			ExportProjectSvcService exportProjectSvcService, CopyProjectService copyProjectService,
			SearchProjectService searchProjectService, ProjectFilesManagement filesManagement, NewProjectService newProject,
			ArchiveProjectService archiveService) {
		super();
		this.permitService = permitService;
		this.getPermitsService = getPermitsService;
		this.exportProjectSvcService = exportProjectSvcService;
		this.copyProjectService = copyProjectService;
		this.searchProjectService = searchProjectService;
		this.filesManagement = filesManagement;
		this.newProject = newProject;
		this.archiveService = archiveService;
	}

//Add Project
	@PostMapping("/ajoutPermit")
	public String ajoutPermit(HttpServletRequest request, @RequestBody HistoriqModel hm) {

		hm.setSessionId(request.getSession().getId());

		return newProject.addPermit((String) hm.getObjectOne(), hm.getIdUser(), false, (String) hm.getObjectThree(),
				(String) hm.getObjectFour());
	}

	@GetMapping("/getSearchList")
	public SearchOptionsList getSearchList() {
		return searchProjectService.getSearchList();
	}

//Copy Project
	@PostMapping("/copyPermit")
	public String copyPermit(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());

		return copyProjectService.copyPermit(Long.valueOf(hm.getObjectOne().toString()), (String) hm.getObjectTwo(),
				hm.getIdUser(), hm.getIpAdress(), hm.getTimeZone(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate(),
				(String) hm.getObjectThree(), (String) hm.getObjectFour());
	}

	/*
	 * getAllPermit(not deleted) New Design
	 */	
	@PostMapping("/getAllPermits")
	public Page<PermitResponsePrime> getAllPermits(@RequestBody String[] params) {
		return getPermitsService.getAllPermits(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]));
	}

	@PostMapping("/getDrafterPermit")
	public Page<PermitResponsePrime> getDrafterPermit(@RequestBody String[] params) {
		return getPermitsService.getDrafterPermit(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
	}
	
	@PostMapping("/getDrafterPermitSort")
	public Page<PermitResponsePrime> getDrafterPermitSort(@RequestBody String[] params) {
		return getPermitsService.getDrafterPermitSort(Integer.parseInt(params[0]), Integer.parseInt(params[1]), params[2], params[3]);
	}

	@PostMapping("/getAllPermitsByIdUser/{idUser}")
	public Page<PermitResponsePrime> getAllPermitsByIdUser(@PathVariable("idUser") Long idUser, @RequestBody String[] params) {
		return getPermitsService.getAllPermitsByUser(idUser, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
	}
	
	@PostMapping("/getAllPermitsByIdUserSort/{idUser}")
	public Page<PermitResponsePrime> getAllPermitsByIdUserSort(@PathVariable("idUser") Long idUser, @RequestBody String[] params) {
		return getPermitsService.getAllPermitsByUserSort(idUser, Integer.parseInt(params[0]), Integer.parseInt(params[1]), params[2], params[3]);
	}

	@PostMapping("/getFileById")
	public GetFileByIdResults getFileById(@RequestBody Long idPermit) {

		return filesManagement.getFileById(idPermit);
	}

	@PostMapping("/getPlansetById")
	public GetPlansetByIdResults getPlansetById(@RequestBody Long idPermit) {

		return filesManagement.getPlansetById(idPermit);
	}

	@PostMapping("/permitRemove")
	public ResponseEntity<StringModelResult> permitRemove(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());

		String str = permitService.deletPermit(Long.valueOf(hm.getObjectOne().toString()), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
		StringModelResult st = new StringModelResult(str);
		return new ResponseEntity<>(st, HttpStatus.OK);

	}

	@GetMapping("/onHoldProject/{idPermit}/{idUser}")
	public void onHoldProject(@PathVariable Long idPermit, @PathVariable Long idUser) {

		permitService.onHoldProjectService(idPermit, idUser);
	}

	@GetMapping("/onHoldProjectUpdate/{idPermit}")
	public boolean onHoldProjectUpdate(@PathVariable Long idPermit) {

		return permitService.onHoldProjectUpdateToDService(idPermit);
	}

	@PostMapping("/changePermitStatus")
	public String changePermitStatus(HttpServletRequest request, @RequestBody HistoriqModel hm) {

		hm.setSessionId(request.getSession().getId());
		return permitService.changePermitStatus(Long.valueOf(hm.getObjectOne().toString()), (String) hm.getObjectTwo(),
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/generateXlFile/{idPermit}")
	public String generateXlFile(@PathVariable Long idPermit, @RequestBody Long userId) {
		return exportProjectSvcService.generateProjectScv(idPermit, userId);
	}

	@PostMapping("/getAllFiles")
	public List<String> getAllFiles(@RequestBody Long idPermit) {
		return permitService.getAllFiles(idPermit);
	}

	@PostMapping("/saveCommentUpload")
	public String saveCommentUpload(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());

		ObjectMapper mapper = new ObjectMapper();
		GetFileByIdResults cm = new GetFileByIdResults();
		GetPlansetByIdResults cm2 = new GetPlansetByIdResults();
		byte[] json = null;
		byte[] json2 = null;

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			cm = mapper.readValue(json, GetFileByIdResults.class);
			json2 = mapper.writeValueAsBytes(hm.getObjectTwo());
			cm2 = mapper.readValue(json2, GetPlansetByIdResults.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filesManagement.saveCommentUpload(cm, cm2);

	}

	@PostMapping("/addTemplatePermit/{idSuperUser}/{isTemplate}")
	public String addTemplatePermit(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable Long idSuperUser, @PathVariable Boolean isTemplate) {
		hm.setSessionId(request.getSession().getId());

		return copyProjectService.addTemplatePermit(Long.valueOf(hm.getObjectOne().toString()),
				(String) hm.getObjectTwo(), hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(),
				hm.getSessionId(), hm.getOpenDate(), "", "", idSuperUser, isTemplate);
	}

	// CR-1209 Get Project Informations
	@PostMapping("/getPermitInformation")
	public String getProjectContacts(@RequestBody Long id) {

		return permitService.getPermitInformation(id);
	}

	@PostMapping("/getProjectContacts")
	public ProjectContactsEmailModel getPermitInformation(@RequestBody Long id) {

		return permitService.getProjectContacts(id);
	}

	@PostMapping("/sendProjectEmail")
	public String sendProjectEmail(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		ProjectEmailModel clle = null;
		hm.setSessionId(request.getSession().getId());

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			clle = mapper.readValue(json, ProjectEmailModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return permitService.sendProjectEmail(clle, hm.getIdUser(), Long.valueOf(hm.getObjectTwo().toString()),
				hm.getIpAdress(), hm.getTimeZone(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());

	}

	@PostMapping("/upload/AttachmentSendMail")
	public String attachmentSendMail(@RequestParam("TypeAttachment") String typeAttachment,
			@RequestParam("IDPermit") Long idPermit, @RequestParam("uploadedFiles") MultipartFile[] uploadedFiles,
			@RequestParam("EmailOfReceiver") String emailOfReceiver,
			@RequestParam("AttachmentEmailSubject") String attachmentEmailSubject,
			@RequestParam("AttachmentEmailContent") String attachmentEmailContent) {

		return permitService.AttachmentSendMail(typeAttachment, idPermit, emailOfReceiver, attachmentEmailSubject,
				attachmentEmailContent, uploadedFiles);

	}

	@PostMapping("/sendGetRequest")
	public String sendGetRequest(@RequestBody String url) {
		return permitService.sendGetRequest(url);
	}

	@PostMapping("/CheckIfProjectNameExist")
	public String checkIfProjectNameExist(@RequestBody String[] varList) {
		return permitService.CheckIfProjectNameExist(varList[0], varList[1], varList[2], Long.valueOf(varList[3]));
	}
	
	@PostMapping("/getAllPermitsSort")
	public Page<PermitResponsePrime> getAllPermitsSort(@RequestBody String[] params) {
		return getPermitsService.getAllPermitsSort(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]), params[3], params[4]);
	}
	@PostMapping("/getProjectInfo")
	public DelayArchiveRequest getProjectInfo(@RequestBody String projectId) {
		return archiveService.getProjectInfo(projectId);
	}
	@PostMapping("/delayArchive")
	public boolean delayArchive(@RequestBody UtilsModel model) {
		return archiveService.delayArchive(model);
	}
}
