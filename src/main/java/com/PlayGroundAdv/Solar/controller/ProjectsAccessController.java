package com.PlayGroundAdv.Solar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.service.project.ProjectAccessService;

@RestController
@RequestMapping("/projectAccess")
public class ProjectsAccessController {

	final ProjectAccessService accessService;

	public ProjectsAccessController(ProjectAccessService accessService) {
		super();
		this.accessService = accessService;
	}

	@PostMapping("/refreshProject")
	public Boolean refreshProject(@RequestBody Long id) {
		return accessService.refreshProject(id);
	}

	@PostMapping("/closeProject")
	public String closeProject(@RequestBody Long id) {
		return accessService.closeProject(id);
	}

	@PostMapping("/closeProjectRequest")
	public Boolean closeProjectRequest(@RequestBody String[] ids) {
		return accessService.closeProjectRequest(ids[0], Long.valueOf(ids[1]));
	}

	@PostMapping("/requestPermitAccess")
	public Boolean requestPermitAccess(@RequestBody Long id) {
		return accessService.requestPermitAccess(id);
	}

	@PostMapping("/checkProjectEditRequest")
	public Boolean checkProjectEditRequest(@RequestBody Long id) {
		return accessService.checkProjectEditRequest(id);
	}

	@PostMapping("/getOpenedBy")
	public String getOpenedBy(@RequestBody Long id) {
		return accessService.getOpenedBy(id);
	}

//	Utility Company Access
	@PostMapping("/refreshUtility")
	public Boolean refreshUtility(@RequestBody Long id) {
		return accessService.refreshUtility(id);
	}

	@PostMapping("/closeUtility")
	public String closeUtility(@RequestBody Long id) {
		return accessService.closeUtility(id);
	}

	@PostMapping("/closeUtilityRequest")
	public Boolean closeUtilityRequest(@RequestBody String[] ids) {
		return accessService.closeUtilityRequest(ids[0], Long.valueOf(ids[1]));
	}

	@PostMapping("/requestUtilityAccess")
	public Boolean requestUtilityAccess(@RequestBody Long id) {
		return accessService.requestUtilityAccess(id);
	}

	@PostMapping("/checkUtilityEditRequest")
	public Boolean checkUtilityEditRequest(@RequestBody Long id) {
		return accessService.checkUtilityEditRequest(id);
	}

	@PostMapping("/getUtilityOpenedBy")
	public String getUtilityOpenedBy(@RequestBody Long id) {
		return accessService.getUtilityOpenedBy(id);
	}

}
