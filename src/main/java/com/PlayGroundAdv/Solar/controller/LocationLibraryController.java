package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.PlayGroundAdv.Solar.entity.ChecklistLocationsEntity;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.libraries.CheckLocationsModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.service.libraries.LocationsLibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/locationLibrary")
public class LocationLibraryController {

	final LocationsLibraryService locationsLibraryService;

	public LocationLibraryController(LocationsLibraryService locationsLibraryService) {
		super();
		this.locationsLibraryService = locationsLibraryService;
	}

	@PostMapping("/addLocation")
	public ChecklistLocationsEntity addNewLocation(HttpServletRequest request, @RequestBody HistoriqModel hm)
			throws IOException {
		hm.setSessionId(request.getSession().getId());
		ObjectMapper mapper = new ObjectMapper();
		ChecklistLocationsEntity clle = new ChecklistLocationsEntity();
		byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
		clle = mapper.readValue(json, ChecklistLocationsEntity.class);
		return locationsLibraryService.addNewLocation(clle, (String) hm.getObjectTwo(), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	

	@PostMapping("/editLocationShowing")
	public String editLocationShowing(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return locationsLibraryService.editLocationShowing(Long.valueOf(hm.getObjectOne().toString()),
				(Boolean) hm.getObjectTwo(), hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(),
				hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/deleteLocation")
	public String deleteLocation(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return locationsLibraryService.deleteLocation((String) hm.getObjectOne(), hm.getIpAdress(), hm.getTimeZone(),
				hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/activateLocation")
	public String activateLocation(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());

		return locationsLibraryService.activateLocation((String) hm.getObjectOne(), hm.getIpAdress(), hm.getTimeZone(),
				hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}
	
	@PostMapping("/filterLocation")
	public Page<CheckLocationsModel> filterLocation(@RequestBody ComponentPageRequest request) {
		return locationsLibraryService.filter(request);
	}
	
	@PostMapping("/editLocation")
	public String editLocation(HttpServletRequest request, @RequestBody HistoriqModel hm) throws Exception {
		hm.setSessionId(request.getSession().getId());

		// we used the ObjectMapper to cast the Object Sent to the Object we need
		ObjectMapper mapper = new ObjectMapper();
		CheckLocationsModel clle = new CheckLocationsModel();
		byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
		clle = mapper.readValue(json, CheckLocationsModel.class);
		return locationsLibraryService.editLocation(clle, hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(),
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/upload/setPic")
	public String pictureupload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		return locationsLibraryService.pictureupload(file, redirectAttributes);
	}

}
