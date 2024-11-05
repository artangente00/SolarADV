package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.StringModelResult;
import com.PlayGroundAdv.Solar.model.TemplateModelResponse;
import com.PlayGroundAdv.Solar.service.copy_project.CopyProjectService;
import com.PlayGroundAdv.Solar.service.project.PermitService;
import com.PlayGroundAdv.Solar.service.project.TemplateService;

@RestController
@RequestMapping("/template")
public class TemplateController {

	final TemplateService templateService;
	final CopyProjectService copyProjectService;
	final PermitService permitService;

	public TemplateController(TemplateService templateService, CopyProjectService copyProjectService,
			PermitService permitService) {
		super();
		this.templateService = templateService;
		this.copyProjectService = copyProjectService;
		this.permitService = permitService;
	}

	// Get All Project Templates
	@GetMapping("/getAllTemplate")
	public List<TemplateModelResponse> getAllTemplate() throws Exception {
		return templateService.getAllTemplate();
	}

	// Get All Project Templates By User
	@PostMapping("/getAllTemplateByUser")
	public List<TemplateModelResponse> getAllTemplateByUser(@RequestBody Long idUser) throws Exception {
		return templateService.getAllTemplateByUser(idUser);
	}

	// Remove Project
	@PostMapping("/permitRemove")
	public ResponseEntity<StringModelResult> permitRemove(HttpServletRequest request, @RequestBody HistoriqModel hm)
			throws Exception {
		hm.setSessionId(request.getSession().getId());
		String str = permitService.deletPermit(Long.valueOf(hm.getObjectOne().toString()), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
		StringModelResult st = new StringModelResult(str);
		return new ResponseEntity<StringModelResult>(st, HttpStatus.OK);

	}

	// Add Project Template
	@PostMapping("/addTemplatePermit/{idSuperUser}/{isTemplate}")
	public String addTemplatePermit(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable Long idSuperUser, @PathVariable Boolean isTemplate) throws Exception {
		hm.setSessionId(request.getSession().getId());

		return copyProjectService.addTemplatePermit(Long.valueOf(hm.getObjectOne().toString()),
				(String) hm.getObjectTwo(), hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(),
				hm.getSessionId(), hm.getOpenDate(), (String) hm.getObjectThree(), (String) hm.getObjectFour(),
				idSuperUser, isTemplate);
	}

}
