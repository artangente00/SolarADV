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

import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.model.libraries.InverterResult;
import com.PlayGroundAdv.Solar.service.libraries.InverterService;
import com.PlayGroundAdv.Solar.service.libraries.export.ExportInverterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/inverterLibrary")
public class InverterLibraryController {

	final InverterService getInverterLibraryService;
	final ExportInverterService exportInverterService;

	public InverterLibraryController(InverterService getInverterLibraryService,
			ExportInverterService exportInverterService) {
		super();
		this.getInverterLibraryService = getInverterLibraryService;
		this.exportInverterService = exportInverterService;
	}

	@PostMapping("/addInverterFavorite")
	public String addInverterFavorite(@RequestBody Long[] inverterInfo) {
		return getInverterLibraryService.addInverterFavorite(inverterInfo[0], inverterInfo[1], inverterInfo[2]);
	}

	@PostMapping("/removeInverterFavorite")
	public String removeInverterFavorite(@RequestBody Long[] inverterInfo) {
		return getInverterLibraryService.removeInverterFavorite(inverterInfo[0], inverterInfo[1], inverterInfo[2]);
	}

	/*
	 * Arij-01/08: CR 491 editInverter
	 */
	@PostMapping("/editInverter/{idUserCo}")
	public String editInverter(@RequestBody InverterResult inverter, @PathVariable Long idUserCo) {
		return getInverterLibraryService.editInverter(inverter, idUserCo);
	}
	
	@PostMapping("/verifInverter/{idUserCo}")
	public String verifInverter(@RequestBody InverterResult inverter, @PathVariable Long idUserCo) {
		return getInverterLibraryService.verifInverter(inverter, idUserCo);
	}

	@PostMapping("/editInverterNotification")
	public String editInverterNotification(@RequestBody String[] inverterInfo) {
		return getInverterLibraryService.editInverterNotification(Long.valueOf(inverterInfo[0]), inverterInfo[1],
				inverterInfo[2]);
	}

	@PostMapping("/sendCorrectionInverterRequest")
	public String sendCorrectionInverterRequest(@RequestBody CorrectionRequest request) {
		return getInverterLibraryService.sendCorrectionInverterRequest(request);
	}

	@PostMapping("/exportInverter")
	public ResponseEntity<byte[]> exportInverter(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());

		return exportInverterService.exportInverterSvc(hm.getIdUser(), hm.getIpAdress(), hm.getTimeZone(),
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	// CR-924
	// Change Request Inverter List Management
	@GetMapping("/activedInverterLibrary/{id}/{UserID}")
	public String activedInverterLibrary(@PathVariable("id") Long idInverter, @PathVariable("UserID") Long userID)
			{
		return getInverterLibraryService.inverterLibraryActived(idInverter, userID);
	}

	@GetMapping("/getPermitOfDeletedInverterLibrary/{id}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedInverterLibrary(@PathVariable("id") Long idInverter)
			{
		return getInverterLibraryService.getAllPermitOfInverterDeleted(idInverter);
	}

	@GetMapping("/deleteInverterLibrary/{id}/{UserID}")
	public boolean deleteInverterLibrary(@PathVariable("id") Long idInverter, @PathVariable("UserID") Long userID)
			{
		return getInverterLibraryService.deleteInverterLibrary(idInverter, userID);
	}

	/*
	 * Edit Inverter Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavListInverter")
	public List<UsersEntityResult> getUsersForFavListInverter(@RequestBody Long[] idsForUserUpdtFav) {
		return getInverterLibraryService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteListInverter")
	public String editUsersFavoriteListInverter(@RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getInverterLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	@PostMapping("/filter")
	public Page<InverterResult> filter(@RequestBody ComponentPageRequest request) {
		return getInverterLibraryService.filter(request);
	}

}
