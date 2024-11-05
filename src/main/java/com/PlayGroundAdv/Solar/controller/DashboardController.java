package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.service.project.PermitService;
import com.PlayGroundAdv.Solar.service.utils.GetEquipmentReports;
import com.PlayGroundAdv.Solar.service.utils.SystemSizeUtils;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	final PermitService permitService;
	final GetEquipmentReports getEquipmentReports;
	final SystemSizeUtils systemSizeUtils;
	
	public DashboardController(PermitService permitService, GetEquipmentReports getEquipmentReports,SystemSizeUtils systemSizeUtils) {
		super();
		this.permitService = permitService;
		this.getEquipmentReports = getEquipmentReports;
		this.systemSizeUtils = systemSizeUtils;
	}

	@PostMapping(path = "/getAllPermitForChart")
	public List<List<String>> getAllPermitForChart(@RequestBody Long idUser) throws Exception {
		return permitService.getAllPermitForChart(idUser);
	}

	@PostMapping(path = "/getEquipmentReports")
	public String getEquipmentReports() throws Exception {
		getEquipmentReports.getprojectlist();
		return "done";
	}

	@PostMapping(path = "/getSystemSizeReports")
	public String getSystemSizeReports() throws Exception {
		systemSizeUtils.systemSizeReport();
		return "done";
	}
}
