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

import com.PlayGroundAdv.Solar.model.FlashingAddResponse;
import com.PlayGroundAdv.Solar.model.FlashingFavRequest;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchFlashingResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.service.libraries.EngineersLibraryService;
import com.PlayGroundAdv.Solar.service.libraries.GetFlashingLibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/flashingLibrary")
public class FlashingLibraryController {

	final GetFlashingLibraryService getFlashingLibraryService;
	final EngineersLibraryService getEngineersLibraryService;

	public FlashingLibraryController(GetFlashingLibraryService getFlashingLibraryService,
			EngineersLibraryService getEngineersLibraryService) {
		super();
		this.getFlashingLibraryService = getFlashingLibraryService;
		this.getEngineersLibraryService = getEngineersLibraryService;
	}

	@GetMapping("/addFlashingFavorite/{idContractor}/{idFlashing}/{idOwner}")
	public String addFlashingFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idFlashing") Long idFlashing,
			@PathVariable("idOwner") Long idOwner) {
		return getFlashingLibraryService.addFlashingFavorite(idContractor, idFlashing, idOwner);
	}

	@GetMapping("/removeFlashingFavorite/{idContractor}/{idFlashing}/{idOwner}")
	public String removeFlashingFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idFlashing") Long idFlashing,
			@PathVariable("idOwner") Long idOwner) {
		return getFlashingLibraryService.removeFlashingFavorite(idContractor, idFlashing, idOwner);
	}

	@PostMapping("/editFlashing/{idUserCo}")
	public String editFlashingeditFlashing(@RequestBody FlashingFavRequest flashing, @PathVariable Long idUserCo) {
		return getFlashingLibraryService.editFlashing(flashing, idUserCo);
	}

	@PostMapping("/editFlashingNotification")
	public String editFlashingNotification(@RequestBody String[] flashingInfo) {
		return getFlashingLibraryService.editFlashingNotification(Long.valueOf(flashingInfo[0]), flashingInfo[1],
				flashingInfo[2]);
	}

	@PostMapping("/addFlashingNotification")
	public String addFlashingNotification(@RequestBody String[] flashingInfo) {
		return getFlashingLibraryService.addFlashingNotification(Long.valueOf(flashingInfo[0]), flashingInfo[1],
				flashingInfo[2]);
	}

	@PostMapping("/sendCorrectionRequest")
	public String sendCorrectionRequest(@RequestBody CorrectionRequest flashingInfo) {
		return getFlashingLibraryService.sendCorrectionRequest(flashingInfo);
	}

	@GetMapping("/activedFlashingLibrary/{id}/{UserID}")
	public String activedFlashingLibrary(@PathVariable("id") Long idFlashing, @PathVariable("UserID") Long userID) {
		return getFlashingLibraryService.flashingLibraryActived(idFlashing, userID);
	}

	@GetMapping("/getPermitOfDeletedFlashingLibrary/{idFlashing}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedFlashingLibrary(@PathVariable Long idFlashing) {
		return getFlashingLibraryService.getAllPermitOfFlashingDeleted(idFlashing);
	}

	@GetMapping("/deleteFlashingLibrary/{id}/{UserID}")
	public boolean deleteFlashingLibrary(@PathVariable("id") Long idFlashing, @PathVariable("UserID") Long userID) {
		return getFlashingLibraryService.deleteFlashingLibrary(idFlashing, userID);
	}

	/*
	 * Edit Flashing Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavListFlashing")
	public List<UsersEntityResult> getUsersForFavListFlashing(@RequestBody Long[] idsForUserUpdtFav) {
		return getFlashingLibraryService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteListFlashing")
	public String editUsersFavoriteListFlashing(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;
		hm.setSessionId(request.getSession().getId());

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getFlashingLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/existFlashing/{idUserCo}")
	public List<FlashingFavRequest> checkFlashingExistent(@RequestBody FlashingFavRequest flashing,
			@PathVariable Long idUserCo) {
		return getFlashingLibraryService.checkFlashingExistent(flashing, idUserCo);
	}

	@PostMapping("/addFlashing/{idUser}/{idPermitInfo}")
	public FlashingAddResponse addNewFlashing(@RequestBody FlashingFavRequest flashingFavRequest, @PathVariable Long idUser,
			@PathVariable Long idPermitInfo) {
		return getFlashingLibraryService.addNewFlashing(idPermitInfo, flashingFavRequest, idUser);
	}

	@PostMapping("/filter")
	public Page<SearchFlashingResult> filter(@RequestBody ComponentPageRequest request) {
		return getFlashingLibraryService.filter(request);
	}
}
