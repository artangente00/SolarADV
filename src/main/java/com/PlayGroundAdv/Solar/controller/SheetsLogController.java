package com.PlayGroundAdv.Solar.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.CustomizeSheetsLogModel;
import com.PlayGroundAdv.Solar.model.MissingSheetsModel;
import com.PlayGroundAdv.Solar.model.PlansetSheetsLogModel;
import com.PlayGroundAdv.Solar.model.PlansetUsedSheetsLogModel;
import com.PlayGroundAdv.Solar.service.log.SheetsLogService;

@RestController
@RequestMapping("/sheetsLog")
public class SheetsLogController {

	final SheetsLogService sheetLogService;

	public SheetsLogController(SheetsLogService sheetLogService) {
		super();
		this.sheetLogService = sheetLogService;
	}

	@GetMapping("/getMissingSheetsList")
	public Page<MissingSheetsModel> getMissingSheetsList() {
		return sheetLogService.getMissingSheetsList();
	}

	@GetMapping("/getPlansetSheetsList")
	public Page<PlansetSheetsLogModel> getPlansetSheetsList() {
		return sheetLogService.getPlansetSheetsList();
	}

	@GetMapping("/getCustomizeSheetsList")
	public Page<CustomizeSheetsLogModel> getCustomizeSheetsList() {
		return sheetLogService.getCustomizeSheetsList();
	}

	@GetMapping("/getGetPlansetUsedSheets")
	public Page<PlansetUsedSheetsLogModel> getGetPlansetUsedSheets() {
		return sheetLogService.getGetPlansetUsedSheets();
	}
	
	@PostMapping("/getFiltredPlansetUsedSheets")
	public Page<PlansetUsedSheetsLogModel> getFiltredPlansetUsedSheets(@RequestBody String[] params) {
		return sheetLogService.getFiltredPlansetUsedSheets(params[0]);
	}

}
