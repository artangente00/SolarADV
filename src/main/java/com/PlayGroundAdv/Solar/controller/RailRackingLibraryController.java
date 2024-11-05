package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.entity.RailRackingOptionsEntity;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.NewRailRackingModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.RailRackingModel;
import com.PlayGroundAdv.Solar.model.RailRackingOptionsModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.service.libraries.RailRackingService;
import com.PlayGroundAdv.Solar.service.libraries.RailRackingOptionsManagement;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/railRackingLibrary")
public class RailRackingLibraryController {

	final RailRackingService railRackingLibraryService;
	final RailRackingOptionsManagement railRackingOptionsMgt;

	public RailRackingLibraryController(RailRackingService railRackingLibraryService,
			RailRackingOptionsManagement railRackingOptionsMgt) {
		super();
		this.railRackingLibraryService = railRackingLibraryService;
		this.railRackingOptionsMgt = railRackingOptionsMgt;
	}

	/*
	 * Edit Rail & Racking Favorite
	 */
	@PostMapping("/editFavRslt")
	public String editRailRackingFavoriteList(@RequestBody HistoriqModel hm) {
		return railRackingLibraryService.editRailRackingFavoriteList(Long.valueOf(hm.getObjectTwo().toString()),
				(Boolean) hm.getObjectOne(), hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	@PostMapping("/sendCorrectionRailRequest")
	public String sendCorrectionRailRequest(@RequestBody CorrectionRequest model) {
		return railRackingLibraryService.sendCorrectionRequest(model);
	}

	/*
	 * Edit Rail Racking Favorite for Other Users
	 */
	@PostMapping("/usersListFav")
	public List<UsersEntityResult> getUsersForFavList(@RequestBody Long[] idsForUserUpdtFav) {
		return railRackingLibraryService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
	}

	@PostMapping("/edituserFavRslt")
	public String editUsersFavoriteList(@RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return railRackingLibraryService.editUsersFavoriteList(clle, (Boolean) hm.getObjectTwo(), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser());
	}

	@PostMapping("/editManyUsersFavoriteList")
	public String editManyUsersFavoriteList(@RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return railRackingLibraryService.editManyUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	/*
	 * Edit Rail & Racking Favorite
	 */
	@PostMapping("/existRailRacking/{iduser}")
	public List<RailRackingModel> checkRailRackingExistent(@RequestBody NewRailRackingModel railRacking,
			@PathVariable Long iduser) {

		return railRackingLibraryService.checkRailRackingExistent(railRacking, iduser);
	}

	/*
	 * add Rail & Racking
	 */
	@PostMapping("/addRailRacking")
	public RailRackingModel addRailRacking(@RequestBody HistoriqModel hm) {

		ObjectMapper mapper = new ObjectMapper();
		NewRailRackingModel nrrm = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			nrrm = mapper.readValue(json, NewRailRackingModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Long idPermit = 0L;
		if (hm.getObjectTwo() != null && hm.getObjectTwo() != "") {
			idPermit = Long.valueOf(hm.getObjectTwo().toString());
		}
		return railRackingLibraryService.addRailRacking(nrrm, hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(),
				idPermit);
	}

	@PostMapping("/editRailRacking/{idUserCo}")
	public String editRailRacking(@RequestBody RailRackingModel railRacking, @PathVariable Long idUserCo)
			{
		return railRackingLibraryService.editRailRacking(railRacking, idUserCo);
	}
	
	@PostMapping("/verifRailRacking/{idUserCo}")
	public String verifRailRacking(@RequestBody RailRackingModel railRacking, @PathVariable Long idUserCo)
			{
		return railRackingLibraryService.verifRailRacking(railRacking, idUserCo);
	}

	@GetMapping("/removeRailRackingConfirmationAPI/{idrailRacking}")
	public List<ProjectForLibrariesModel> getRemoveRailRackingConfirmationAPI(@PathVariable Long idrailRacking)
			{
		return railRackingLibraryService.getRemoveRailRackingConfirmation(idrailRacking);
	}

	@PostMapping("/deleteRailRacking")
	public String deleteRailRacking(@RequestBody HistoriqModel hm) {
		return railRackingLibraryService.deleteRailRacking(Long.valueOf(hm.getObjectOne().toString()), hm.getIdUser());
	}

	/*
	 * Activate Rail & Racking
	 */
	@PostMapping("/activateRailRackingRslt")
	public String activateRailRacking(@RequestBody HistoriqModel hm) {
		return railRackingLibraryService.activateRailRacking(Long.valueOf(hm.getObjectOne().toString()), hm.getIdUser());
	}

	@PostMapping("/editRailRackingNotification")
	public String editRailRackingNotification(@RequestBody String[] railRackingInfo) {
		return railRackingLibraryService.editRailRackingNotification(Long.valueOf(railRackingInfo[0]),
				railRackingInfo[1], railRackingInfo[2]);
	}

	@PostMapping("/addRailRackingNotification")
	public String addRailRackingNotification(@RequestBody String[] railRackingInfo) {
		return railRackingLibraryService.addRailRackingNotification(Long.valueOf(railRackingInfo[0]),
				railRackingInfo[1], railRackingInfo[2]);
	}

	@GetMapping("/getListOfGroundRailRacking")
	public List<ComponentModel> getListOfGroundRailRacking() {
		return railRackingLibraryService.getListOfGroundRailRacking();
	}

	@GetMapping("/getListRailRacking")
	public List<ComponentModel> getListRailRacking() {
		return railRackingLibraryService.getListRailRacking();
	}

	/*
	 * Get All Deleted Rail & Racking Get All Rail & Racking
	 */

	@PostMapping("/filter")
	public Page<RailRackingModel> filterRailRacking(@RequestBody ComponentPageRequest request) {
		return railRackingLibraryService.filter(request);
	}

	@GetMapping("/getRailRackingOption")
	public List<RailRackingOptionsEntity> getRailRackingOption() {
		return railRackingOptionsMgt.getRailRackingOption();
	}

	@PostMapping("/addRailRackingOption")
	public RailRackingOptionsModel addRailRackingOption(@RequestBody RailRackingOptionsModel op) {
		return railRackingOptionsMgt.addRailRackingOption(op);
	}

	@PostMapping("/editRailRackingOption")
	public String editRailRackingOption(@RequestBody RailRackingOptionsModel op) {
		return railRackingOptionsMgt.editRailRackingOption(op);
	}

	@PostMapping("/removePvRailTypeOptions")
	public String removePvRailTypeOptions(@RequestBody Long opId) {
		return railRackingOptionsMgt.removePvRailTypeOptions(opId);
	}
	
	@PostMapping("/getRailpdf")
	public ResponseEntity<byte[]> getOptPDF(HttpServletRequest request, @RequestBody HistoriqModel url) {

		return railRackingLibraryService.getOptPDF(url);
	}
}
