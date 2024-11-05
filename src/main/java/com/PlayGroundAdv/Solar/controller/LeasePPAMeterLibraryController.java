package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.LeasePPAMeterFavRequest;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchLeasePPAMeterResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.service.libraries.LeasePPAMeterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/leasePPAMeterLibrary")
public class LeasePPAMeterLibraryController {

	final LeasePPAMeterService getLeasePPAMeterLibraryService;

	public LeasePPAMeterLibraryController(LeasePPAMeterService getLeasePPAMeterLibraryService) {
		super();
		this.getLeasePPAMeterLibraryService = getLeasePPAMeterLibraryService;
	}

	@GetMapping("/addLeasePPAMeterFavorite/{idContractor}/{idLeasePPAMeter}/{idOwner}")
	public String addLeasePPAMeterFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idLeasePPAMeter") Long idLeasePPAMeter,
			@PathVariable("idOwner") Long idOwner) {
		return getLeasePPAMeterLibraryService.addLeasePPAMeterFavorite(idContractor, idLeasePPAMeter, idOwner);
	}

	@GetMapping("/removeLeasePPAMeterFavorite/{idContractor}/{idLeasePPAMeter}/{idOwner}")
	public String removeLeasePPAMeterFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idLeasePPAMeter") Long idLeasePPAMeter,
			@PathVariable("idOwner") Long idOwner) {
		return getLeasePPAMeterLibraryService.removeLeasePPAMeterFavorite(idContractor, idLeasePPAMeter, idOwner);
	}

	@PostMapping("/editLeasePPAMeter/{idUserCo}")
	public String editLeasePPAMetereditLeasePPAMeter(@RequestBody LeasePPAMeterFavRequest leasePPAMeter,
			@PathVariable Long idUserCo) {
		return getLeasePPAMeterLibraryService.editLeasePPAMeter(leasePPAMeter, idUserCo);
	}
	
	@PostMapping("/editLeasePPAMeterNotification")
	public String editLeasePPAMeterNotification(@RequestBody String[] leasePPAMeterInfo) {
		return getLeasePPAMeterLibraryService.editLeasePPAMeterNotification(Long.valueOf(leasePPAMeterInfo[0]),
				leasePPAMeterInfo[1], leasePPAMeterInfo[2]);
	}

	@PostMapping("/addLeasePPAMeterNotification")
	public String addLeasePPAMeterNotification(@RequestBody String[] leasePPAMeterInfo) {
		return getLeasePPAMeterLibraryService.addLeasePPAMeterNotification(Long.valueOf(leasePPAMeterInfo[0]),
				leasePPAMeterInfo[1], leasePPAMeterInfo[2]);
	}

	@PostMapping("/sendCorrectionLeasePPAMeterRequest")
	public String sendCorrectionLeasePPAMeterRequest(@RequestBody CorrectionRequest leasePPAMeterInfo)
			{
		return getLeasePPAMeterLibraryService.sendCorrectionLeasePPAMeterRequest(leasePPAMeterInfo);
	}

	@GetMapping("/activedLeasePPAMeterLibrary/{id}/{UserID}")
	public String activedLeasePPAMeterLibrary(@PathVariable("id") Long idLeasePPAMeter, @PathVariable("UserID") Long userID)
			{
		return getLeasePPAMeterLibraryService.leasePPAMeterLibraryActived(idLeasePPAMeter, userID);
	}

	@GetMapping("/getPermitOfDeletedLeasePPAMeterLibrary/{idLeasePPAMeter}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedLeasePPAMeterLibrary(@PathVariable Long idLeasePPAMeter)
			{
		return getLeasePPAMeterLibraryService.getAllPermitOfLeasePPAMeterDeleted(idLeasePPAMeter);
	}

	@GetMapping("/deleteLeasePPAMeterLibrary/{id}/{UserID}")
	public boolean deleteLeasePPAMeterLibrary(@PathVariable("id") Long idLeasePPAMeter, @PathVariable("UserID") Long userID)
			{
		return getLeasePPAMeterLibraryService.deleteLeasePPAMeterLibrary(idLeasePPAMeter, userID);
	}

	/*
	 * Edit LeasePPAMeter Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavListLeasePPAMeter")
	public List<UsersEntityResult> getUsersForFavListLeasePPAMeter(@RequestBody Long[] idsForUserUpdtFav)
			{
		return getLeasePPAMeterLibraryService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteListLeasePPAMeter")
	public String editUsersFavoriteListLeasePPAMeter(HttpServletRequest request, @RequestBody HistoriqModel hm)
			{
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;
		hm.setSessionId(request.getSession().getId());

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getLeasePPAMeterLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/existLeasePPAMeter/{idUserCo}")
	public List<LeasePPAMeterFavRequest> checkLeasePPAMeterExistent(@RequestBody LeasePPAMeterFavRequest leasePPAMeter,
			@PathVariable Long idUserCo) {
		return getLeasePPAMeterLibraryService.checkLeasePPAMeterExistent(leasePPAMeter, idUserCo);
	}

	@PostMapping("/addLeasePPAMeter/{idUser}/{idPermitInfo}")
	public LibrariesManagementModelResult addNewLeasePPAMeter(
			@RequestBody LeasePPAMeterFavRequest leasePPAMeterFavRequest, @PathVariable Long idUser,
			@PathVariable Long idPermitInfo) {
		return getLeasePPAMeterLibraryService.addNewLeasePPAMeter(idPermitInfo, leasePPAMeterFavRequest, idUser);
	}

	@PostMapping("/filter")
	public Page<SearchLeasePPAMeterResult> filter(@RequestBody ComponentPageRequest request) {
		return getLeasePPAMeterLibraryService.filter(request);
	}
	
}