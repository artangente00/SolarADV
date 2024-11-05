package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.HistoricActivityResult;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;

@RestController
@RequestMapping("/portalLog")
public class PortalLogConstroller {

	final HistoryActivityService historyActivityService;

	public PortalLogConstroller(HistoryActivityService historyActivityService) {
		super();
		this.historyActivityService = historyActivityService;
	}

	@PostMapping("/getAllActivities")
	public Page<HistoricActivityResult> getAllActivities(@RequestBody Integer[] params) throws Exception {
		return historyActivityService.gethistoricTable(params[0], params[1]);
	}
	
	@PostMapping("/getFiltredActivities")
	public Page<HistoricActivityResult> getFiltredActivities(@RequestBody String[] params) throws Exception {
		return historyActivityService.searchHistoricPage(Integer.parseInt(params[0]), Integer.parseInt(params[1]), params[2]);
	}

	@PostMapping("/getLibrariesActivities")
	public List<HistoricActivityResult> getLibrariesActivities() throws Exception {
		return historyActivityService.getlibrariesHistoricTable();
	}

}
