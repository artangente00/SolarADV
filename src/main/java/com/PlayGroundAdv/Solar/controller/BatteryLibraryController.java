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
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.BatteryModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.service.libraries.BatteryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/batteryLibrary")
public class BatteryLibraryController {

	final BatteryService getBatteryLibraryService;

	public BatteryLibraryController(BatteryService getBatteryLibraryService) {
		super();
		this.getBatteryLibraryService = getBatteryLibraryService;
	}

	@PostMapping("/filter")
	public Page<BatteryModel> filter(@RequestBody ComponentPageRequest request) {
		return getBatteryLibraryService.filter(request);
	}

	@GetMapping("/addBatteryFavorite/{idContractor}/{idBattery}/{idOwner}")
	public String addBatteryFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idBattery") Long idBattery,
			@PathVariable("idOwner") Long idOwner) {
		return getBatteryLibraryService.addBatteryFavorite(idContractor, idBattery, idOwner);
	}

	@GetMapping("/removeBatteryFavorite/{idContractor}/{idBattery}/{idOwner}")
	public String removeBatteryFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idBattery") Long idBattery,
			@PathVariable("idOwner") Long idOwner) {
		return getBatteryLibraryService.removeBatteryFavorite(idContractor, idBattery, idOwner);
	}

	@PostMapping("/editBattery/{idUserCo}")
	public String editBatteryeditBattery(@RequestBody BatteryModel battery, @PathVariable Long idUserCo)
			{
		return getBatteryLibraryService.editBattery(battery, idUserCo);
	}

	@PostMapping("/sendCorrectionBatteryRequest")
	public String sendCorrectionBatteryRequest(@RequestBody CorrectionRequest batteryInfo) {
		return getBatteryLibraryService.sendCorrectionBatteryRequest(batteryInfo);
	}

	@GetMapping("/activedBatteryLibrary/{id}/{idUserCo}")
	public String activedBatteryLibrary(@PathVariable("id") Long idBattery, @PathVariable("idUserCo") Long idUserCo)
			{
		return getBatteryLibraryService.batteryLibraryActived(idBattery, idUserCo);
	}

	@GetMapping("/getPermitOfDeletedBatteryLibrary/{idBattery}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedBatteryLibrary(@PathVariable Long idBattery) {
		return getBatteryLibraryService.getAllPermitOfBatteryDeleted(idBattery);
	}

	@GetMapping("/deleteBatteryLibrary/{id}/{idUserCo}")
	public boolean deleteBatteryLibrary(@PathVariable("id") Long idBattery, @PathVariable("idUserCo") Long idUserCo)
			{
		return getBatteryLibraryService.deleteBatteryLibrary(idBattery, idUserCo);
	}

	/*
	 * Edit Battery Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavListBattery")
	public List<UsersEntityResult> getUsersForFavListBattery(@RequestBody Long[] idsForUserUpdtFav) {
		return getBatteryLibraryService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteListBattery")
	public String editUsersFavoriteListBattery(HttpServletRequest request, @RequestBody HistoriqModel hm)
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
		return getBatteryLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/existBattery/{idUserCo}")
	public List<ComponentModel> checkBatteryExistent(@RequestBody ComponentModel battery,
			@PathVariable Long idUserCo) {
		return getBatteryLibraryService.checkBatteryExistent(battery, idUserCo);
	}

	@PostMapping("/addBattery/{idUser}/{idContractor}")
	public ComponentModel addNewBattery(@RequestBody BatteryModel batteryFavRequest, @PathVariable Long idUser,
			@PathVariable Long idContractor) {
		return getBatteryLibraryService.addNewBattery(idContractor, batteryFavRequest, idUser);
	}

	@GetMapping("/getListBatteriesModels")
	public List<ComponentModel> getListBatteriesModels() {
		return getBatteryLibraryService.getListBatteriesModels();
	}
}
