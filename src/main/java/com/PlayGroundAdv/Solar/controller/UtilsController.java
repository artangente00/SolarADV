package com.PlayGroundAdv.Solar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.service.monday_api.ContractorItem;
import com.PlayGroundAdv.Solar.service.project.ArchiveProjectService;
import com.PlayGroundAdv.Solar.service.project.ClearTestProject;
import com.PlayGroundAdv.Solar.service.sheets.SheetsMassUpdate;
import com.PlayGroundAdv.Solar.service.utils.UtilsService;

@RestController
@RequestMapping("/utils")
public class UtilsController {

	final UtilsService utilsService;
	final ContractorItem contractorItem;
	final SheetsMassUpdate sheetsMassUpdate;
	final ClearTestProject clearTestProject;
	final ArchiveProjectService archiveService;
	public UtilsController(UtilsService utilsService, ContractorItem contractorItem,
			SheetsMassUpdate sheetsMassUpdate, ClearTestProject clearTestProject,
			ArchiveProjectService archiveService) {
		super();
		this.utilsService = utilsService;
		this.contractorItem = contractorItem;
		this.sheetsMassUpdate = sheetsMassUpdate;
		this.clearTestProject = clearTestProject;
		this.archiveService = archiveService;
	}

	@PostMapping("/updateProjects")
	public String updateProjects(@RequestBody ComponentPageRequest request) {
		return utilsService.updateProjects(request);
	}

	@GetMapping("/getContractorIDs")
	public void getContractorIDs() {
		contractorItem.getContractorIDs();
	}
	@PostMapping("/tldMassUpdate")
	public void tldMassUpdate(@RequestBody String params) {
		sheetsMassUpdate.tldMassUpdate(params);
	}
	
	@GetMapping("/groundRailRackingRsheet")
	public void groundRailRackingRsheet() {
		sheetsMassUpdate.groundRailRackingRsheet();
	}
	
	@GetMapping("/updateName")
	public void updateName() {
		sheetsMassUpdate.updateName();
	}
	
	@PostMapping("/cleanProjects")
	public void cleanProjects(@RequestBody String fileName) {
		clearTestProject.cleanProjects(fileName);
	}

	@GetMapping("/archiveid")
	public void archiveid() {
		archiveService.archiveid();
	}
}
