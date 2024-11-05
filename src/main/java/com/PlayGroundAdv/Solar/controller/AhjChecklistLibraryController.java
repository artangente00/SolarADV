package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.model.TableDataPage;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJColumnsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDataModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJFilterList;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJProjectDataModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AttachementModel;
import com.PlayGroundAdv.Solar.model.ahj_library.DeletedAttachmentModel;
import com.PlayGroundAdv.Solar.model.ahj_library.MassUpdateModel;
import com.PlayGroundAdv.Solar.model.ahj_library.PageModel;
import com.PlayGroundAdv.Solar.service.ahj_library.AHJChecklistService;
import com.PlayGroundAdv.Solar.service.ahj_library.AHJFileManagementService;
import com.PlayGroundAdv.Solar.service.ahj_library.AHJLogService;
import com.PlayGroundAdv.Solar.service.ahj_library.AHJMassUpdateService;

@RestController
@RequestMapping(value="/ahjLibrary", produces = { "application/json" })
//@Api(value = "The AHJ Checklist Library Controller", description = "Here are all the operations related to the AHJ (Authority Having Jurisdiction) operations ")
public class AhjChecklistLibraryController {

	final AHJChecklistService ahjService;
	final AHJLogService logService;
	final AHJMassUpdateService massUpdateService;
	final AHJFileManagementService fileManagementService;
	public AhjChecklistLibraryController(AHJChecklistService ahjService,
			AHJLogService logService, AHJMassUpdateService massUpdateService, AHJFileManagementService fileManagementService) {
		super();
		this.ahjService = ahjService;
		this.logService = logService;
		this.massUpdateService = massUpdateService;
		this.fileManagementService = fileManagementService;
	}

	// A.B 11-20-2020 CR-3064 Get existing AHJ List
	@GetMapping("/getAHJFilterList")
	public AHJFilterList getAHJFilterList() {
		return ahjService.getAHJFilterList();
	}
	
	// A.B 09-30-2020 CR-3064 Add New AHJ
	@PostMapping("/addAHJ")
	public ResponseEntity<Object> addAHJ(@RequestBody AHJDataModel newAhj) {
		return ahjService.addAHJ(newAhj);
	}
		
	// A.B 09-30-2020 CR-3064 Add New AHJ
	@PostMapping("/editAHJ")
	public ResponseEntity<Object> editAHJ(@RequestBody AHJDataModel newAhj) {
		return ahjService.editAHJ(newAhj);
	}
	
	// A.B 09-30-2020 CR-3064 Add New AHJ
	@PostMapping("/deleteAHJ")
	public Boolean deleteAHJ(@RequestBody Long[] params) {
		return ahjService.deleteAHJ(params[0], params[1]);
	}
	
	// A.B 09-30-2020 CR-3064 Add New AHJ
	@PostMapping("/getAHJAttachements")
	public List<AttachementModel> getAHJAttachements(@RequestBody String ahjId) {
		return fileManagementService.getAttachements(ahjId);
	}
	
	// A.B 09-30-2020 CR-3064 Add New AHJ
	@PostMapping(value = "/upload/insertAttachement")
	public Boolean insertAttachement(@RequestParam("uploadedFile") MultipartFile file,
			@RequestParam("fileName") String fileName, @RequestParam("cellId") Long cellId, @RequestParam("ahjId") Long ahjId) {
		return fileManagementService.insertAttachement(file, fileName, cellId, ahjId);
	}

	// A.B 09-30-2020 CR-3064 get AHJ List
	@PostMapping("/getAHJList")
	public TableDataPage getAHJList(@RequestBody PageModel page) {
		return ahjService.getAHJList(page);
	}

	// A.B 11-26-2020 CR-3064 get AHJ List
	@PostMapping("/getAHJLog")
	public TableDataPage getAHJLog(@RequestBody PageModel page) {
		return logService.getAHJLog(page);
	}

	// A.B 11-26-2020 CR-3064 get AHJ List
	@PostMapping("/getAHJAttachmentLog")
	public TableDataPage getAHJAttachmentLog(@RequestBody PageModel page) {
		return logService.getAHJAttachmentLog(page);
	}
	
	// A.B 09-30-2020 CR-3064 get AHJ
	@PostMapping("/getAHJ")
	public AHJDataModel getAHJ(@RequestBody Long ahjId) {
		return ahjService.getAHJ(ahjId);
	}

	// A.B 09-30-2020 CR-3064 get AHJ List
	@PostMapping("/getAHJColumns")
	public List<AHJColumnsModel> getAHJColumns(@RequestBody Long ahjId) {
		return ahjService.getAHJColumns(ahjId);
	}

	// A.B 07-15-2021 Add Custom Column
	@PostMapping("/addCustomColumn")
	public String addCustomColumn(@RequestBody AHJColumnsModel column) {
		return ahjService.addCustomColumn(column);
	}
	// A.B 09-30-2020 Get Attachement File
	@PostMapping("/getAttachementFile")
	public ResponseEntity<byte[]> getAttachementFile(@RequestBody String pathFile) {
		return fileManagementService.getAttachementFile(pathFile);
	}
	// A.B 09-30-2020 Get Attachement File
	@PostMapping("/deleteAttachement")
	public Boolean deleteAttachement(@RequestBody DeletedAttachmentModel fileInfo) {
		return fileManagementService.deleteAttachement(fileInfo);
	}
	// A.B 10-20-2020 Do Mass Update
	@PostMapping("/doMassUpdate")
	public Boolean doMassUpdate(@RequestBody MassUpdateModel massUpdate) {
		massUpdateService.logMassUpdate(massUpdate);
		return massUpdateService.doMassUpdate(massUpdate);
	}
	// A.B 10-20-2020 Get Lest Mass Update
	@PostMapping("/getLastMassUpdate")
	public String getLastMassUpdate(@RequestBody MassUpdateModel massUpdate) {
		return massUpdateService.getLastMassUpdate(massUpdate);
	}
	// A.B 10-20-2020 Undo Mass Update
	@PostMapping("/undoMassUpdate")
	public Boolean undoMassUpdate(@RequestBody MassUpdateModel massUpdate) {
		return massUpdateService.undoMassUpdate(massUpdate);
	}
	// A.B 10-20-2020 get Project AHJ Data
	@PostMapping("/getProjectAHJData")
	public AHJProjectDataModel getProjectAHJData(@RequestBody String[] d) {
		return ahjService.getProjectAHJData(d);
	}

}
