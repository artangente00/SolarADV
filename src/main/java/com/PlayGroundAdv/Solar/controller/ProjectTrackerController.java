package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.InterconnectionModel;
import com.PlayGroundAdv.Solar.model.ProjectTrackerModel;
import com.PlayGroundAdv.Solar.service.project.ProjectTrackerService;

@RestController
@RequestMapping("/projectTracker")
public class ProjectTrackerController {

	final ProjectTrackerService projectTrackerService;

	public ProjectTrackerController(ProjectTrackerService projectTrackerService) {
		super();
		this.projectTrackerService = projectTrackerService;
	}

	/*
	 * get All project Tracker Dashboard
	 */
	@GetMapping("/getprojectTrackerDashboard")
	public List<ProjectTrackerModel> getprojectTrackerDashboard() throws Exception {
		return projectTrackerService.getprojectTrackerDashboard();
	}

	/*
	 * get project Tracker Dashboard Contractor
	 */
	@GetMapping("/getprojectTrackerDashboardContractor/{id}")
	public List<ProjectTrackerModel> getTrackerDashboardContractor(@PathVariable Long id) throws Exception {
		return projectTrackerService.getTrackerDashboardContractor(id);
	}

	/*
	 * get project Tracker Interconnection
	 */
	@PostMapping("/getprojectInterconection")
	public List<InterconnectionModel> getprojectInterconection() throws Exception {
		return projectTrackerService.getprojectInterconection();
	}

	/*
	 * Save Interconnection
	 */
	@PostMapping("/saveInterconection")
	public String saveInterconection(@RequestBody InterconnectionModel interconnection) throws Exception {
		return projectTrackerService.saveInterconection(interconnection);
	}
}
