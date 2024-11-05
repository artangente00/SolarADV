package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.entity.TiltLegs;
import com.PlayGroundAdv.Solar.model.GetTiltLegsRequest;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.TiltLegsCorrectionRequest;
import com.PlayGroundAdv.Solar.model.TiltLegsFavRequest;
import com.PlayGroundAdv.Solar.model.TiltLegsListModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.service.libraries.GetTiltLegsLibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/tiltLegsLibrary")
public class TiltLegsLibraryController {

	final GetTiltLegsLibraryService getTiltLegsLibraryService;

	public TiltLegsLibraryController(GetTiltLegsLibraryService getTiltLegsLibraryService) {
		super();
		this.getTiltLegsLibraryService = getTiltLegsLibraryService;
	}

	@GetMapping("/addTiltLegsFavorite/{idContractor}/{idTiltLegs}/{idOwner}")
	public String addTiltLegsFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idTiltLegs") Long idTiltLegs,
			@PathVariable("idOwner") Long idOwner)
			{
		return getTiltLegsLibraryService.addTiltLegsFavorite(idContractor, idTiltLegs,idOwner);
	}

	@GetMapping("/removeTiltLegsFavorite/{idContractor}/{idTiltLegs}/{idOwner}")
	public String removeTiltLegsFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idTiltLegs") Long idTiltLegs,
			@PathVariable("idOwner") Long idOwner)
			{
		return getTiltLegsLibraryService.removeTiltLegsFavorite(idContractor, idTiltLegs,idOwner);
	}

	
	@PostMapping("/editTiltLegs/{idUserCo}")
	public String editTiltLegseditTiltLegs(@RequestBody TiltLegsFavRequest tiltLegs, @PathVariable Long idUserCo) {
		return getTiltLegsLibraryService.editTiltLegs(tiltLegs, idUserCo);
	}
	@PostMapping("/editTiltLegsNotification")
	public String editTiltLegsNotification(@RequestBody String[] tiltLegsInfo) {
		return getTiltLegsLibraryService.editTiltLegsNotification(Long.valueOf(tiltLegsInfo[0]), tiltLegsInfo[1],
				tiltLegsInfo[2]);
	}

	@PostMapping("/addTiltLegsNotification")
	public String addTiltLegsNotification(@RequestBody String[] tiltLegsInfo) {
		return getTiltLegsLibraryService.addTiltLegsNotification(Long.valueOf(tiltLegsInfo[0]), tiltLegsInfo[1], tiltLegsInfo[2]);
	}
	
	@PostMapping("/sendCorrectionTiltLegsRequest")
	public String sendCorrectionTiltLegsRequest(@RequestBody TiltLegsCorrectionRequest tiltLegsInfo) {
		return getTiltLegsLibraryService.sendCorrectionTiltLegsRequest(tiltLegsInfo);
	}

	
	
	@GetMapping("/activedTiltLegsLibrary/{id}/{UserID}")
	public String activedTiltLegsLibrary(@PathVariable("id") Long idTiltLegs,@PathVariable("UserID") Long userID) {
		return getTiltLegsLibraryService.tiltLegsLibraryActived(idTiltLegs,userID);
	}


	@GetMapping("/getPermitOfDeletedTiltLegsLibrary/{idTiltLegs}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedTiltLegsLibrary(@PathVariable Long idTiltLegs)
			{
		return getTiltLegsLibraryService.getAllPermitOfTiltLegsDeleted(idTiltLegs);
	}


	@GetMapping("/deleteTiltLegsLibrary/{id}/{UserID}")
	public boolean deleteTiltLegsLibrary(@PathVariable("id") Long idTiltLegs,@PathVariable("UserID") Long userID) {
		return getTiltLegsLibraryService.deleteTiltLegsLibrary(idTiltLegs,userID);
	}

	
	/*
	 * Edit TiltLegs Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavListTiltLegs")
	public List<UsersEntityResult> getUsersForFavListTiltLegs(@RequestBody Long[] idsForUserUpdtFav)
			{
		return getTiltLegsLibraryService.getUsersForFavList(idsForUserUpdtFav[0],
					idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteListTiltLegs")
	public String editUsersFavoriteListTiltLegs(@RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getTiltLegsLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle, hm.getIpAdress(),
					hm.getTimeZone(), hm.getIdUser());
	}
	
	
	
	@PostMapping("/existTiltLegs/{idUserCo}")
	public List<TiltLegsFavRequest> checkTiltLegsExistent(@RequestBody TiltLegsFavRequest tiltLegs,
			@PathVariable Long idUserCo) {
		return getTiltLegsLibraryService.checkTiltLegsExistent(tiltLegs, idUserCo);
	}
	
	@PostMapping("/addTiltLegs/{idUser}/{idPermitInfo}")
	public TiltLegs addNewTiltLegs(@RequestBody TiltLegsFavRequest tiltLegsFavRequest, @PathVariable Long idUser,
			@PathVariable Long idPermitInfo) {
		return getTiltLegsLibraryService.addNewTiltLegs(idPermitInfo, tiltLegsFavRequest, idUser);
	}
	
	//CI : 30/12/2019 : ADD METHOD FILTER TO GET AND SEARCH TILT lEGS LIBRARY AND DELETED TILT lEGS
	
	@PostMapping("/filterTiltLegs")
	public TiltLegsListModel filterTiltLegs(@RequestBody GetTiltLegsRequest gmr) {
		return getTiltLegsLibraryService.filterTiltLegs(gmr.getSearchTiltLegsRequest(), gmr.getIdUser(),gmr.getPage(),gmr.getSize());
	}
	
	@PostMapping("/getTiltpdf")
	public ResponseEntity<byte[]> getOptPDF(HttpServletRequest request, @RequestBody HistoriqModel url) {

		return getTiltLegsLibraryService.getOptPDF(url);
	}

}
