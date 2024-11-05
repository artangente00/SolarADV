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

import com.PlayGroundAdv.Solar.model.ACDisconnectEntityModel;
import com.PlayGroundAdv.Solar.model.AcDisconnectModel;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.NewDisconnectModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.service.libraries.AcDisconnectService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/acDisconnectLibrary")
public class AcDisconnectLibraryController {

	final NotificationEntityService notificationEntityService;
	final AcDisconnectService acDisconnectLibraryService;

	public AcDisconnectLibraryController(NotificationEntityService notificationEntityService,
			AcDisconnectService acDisconnectLibraryService) {
		super();
		this.notificationEntityService = notificationEntityService;
		this.acDisconnectLibraryService = acDisconnectLibraryService;
	}

	/*
	 * Get All Roof Attachment
	 */
	@PostMapping("/filter")
	public Page<AcDisconnectModel> filter(@RequestBody ComponentPageRequest request) {
		return acDisconnectLibraryService.filter(request);
	}

	@PostMapping("/removeAcDiscoFavorite")
	public String removeModuleFavorite(@RequestBody Long[] moduleInfo) {
		return acDisconnectLibraryService.removeAcDiscoFavorite(moduleInfo[0], moduleInfo[1], moduleInfo[2]);
	}

	@GetMapping("/getAllRatedCurrent")
	public List<String> getAllRatedCurrent() {
		return acDisconnectLibraryService.getAllRatedCurrent();
	}

	@PostMapping("/addAcDiscoFavorite")
	public String addModuleFavorite(@RequestBody Long[] moduleInfo) {
		return acDisconnectLibraryService.addAcDiscoFavorite(moduleInfo[0], moduleInfo[1], moduleInfo[2]);
	}

	@PostMapping("/editAcCombinerDisco")
	public String editAcCombinerDisco(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		ObjectMapper mapper = new ObjectMapper();
		AcDisconnectModel acd = new AcDisconnectModel();
		byte[] json = null;

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			acd = mapper.readValue(json, AcDisconnectModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return acDisconnectLibraryService.editAcCombinerDisco(acd, hm.getIpAdress(), hm.getTimeZone(),
				hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	/*
	 * Delete Roof Attachment
	 */

	@PostMapping("/getRemoveAcCombinerDiscoConfirmation")
	public List<ProjectForLibrariesModel> getRemoveAcCombinerDiscoConfirmation(
			@RequestBody AcDisconnectModel acd) {
		return acDisconnectLibraryService.getRemoveAcCombinerDiscoConfirmation(acd);
	}

	@PostMapping("/deleteAcCombinerDisco")
	public String deleteAcCombinerDisco(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return acDisconnectLibraryService.deleteAcCombinerDisco(Long.valueOf(hm.getObjectOne().toString()), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());

	}

	@PostMapping("/sendCorrectionACRequest")
	public String sendCorrectionACRequest(@RequestBody CorrectionRequest model) {
		return acDisconnectLibraryService.sendCorrectionRequest(model);

	}

	/*
	 * Activate Roof Attachment
	 */
	@PostMapping("/activateAcCombinerDisco")
	public String activateAcCombinerDisco(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return acDisconnectLibraryService.activateAcCombinerDisco(Long.valueOf(hm.getObjectOne().toString()), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	/*
	 * Edit Roof Attachment Favorite
	 */

	@PostMapping("/editAcCombinerDiscoFavoriteList/{idOwner}")
	public String editAcCombinerDiscoFavoriteList(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable("idOwner") Long idOwner) {
		hm.setSessionId(request.getSession().getId());
		return acDisconnectLibraryService.editAcCombinerDiscoFavoriteList(Long.valueOf(hm.getObjectTwo().toString()),
				(Boolean) hm.getObjectOne(), hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), idOwner,
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	/*
	 * Edit Junction Box Favorite for Other Users
	 */

	@PostMapping("/getUsersForFavList")
	public List<UsersEntityResult> getUsersForFavList(@RequestBody Long[] idsForUserUpdtFav) {
		return acDisconnectLibraryService.getUsersForFavList(idsForUserUpdtFav[0],
				idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteList")
	public String editUsersFavoriteList(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return acDisconnectLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle, hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	/*
	 * Test if the Roof Attachment existe
	 */

	@PostMapping("/checkACCombinerExistent/{idUser}")
	public List<AcDisconnectModel> checkACCombinerExistent(@RequestBody NewDisconnectModel acd,
			@PathVariable Long idUser) {
		return acDisconnectLibraryService.checkACCombinerExistent(acd, idUser);
	}

	@PostMapping("/addAcCombinerDisco/{idPermit}")
	public ACDisconnectEntityModel addAcCombinerDisco(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable Long idPermit) {
		hm.setSessionId(request.getSession().getId());
		ObjectMapper mapper = new ObjectMapper();
		NewDisconnectModel acCombinerDiscoRes = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			acCombinerDiscoRes = mapper.readValue(json, NewDisconnectModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return acDisconnectLibraryService.addAcCombinerDisco(acCombinerDiscoRes, hm.getIpAdress(), hm.getTimeZone(),
				hm.getIdUser(), idPermit, hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/editACCombinerNotification")
	public String editACCombinerNotification(@RequestBody String[] inverterInfo) {
		return acDisconnectLibraryService.editACCombinerNotification(Long.valueOf(inverterInfo[0]), inverterInfo[1], inverterInfo[2]);
	}

	@PostMapping("/addACCombinerNotification")
	public String addACCombinerNotification(@RequestBody String[] inverterInfo) {
		return acDisconnectLibraryService.addACCombinerNotification(Long.valueOf(inverterInfo[0]), inverterInfo[1], inverterInfo[2]);
	}

	@GetMapping("/getListOfAcDisconnect")
	public List<ACDisconnectEntityModel> getListOfAcDisconnect() {
		return acDisconnectLibraryService.getListOfAcDisconnect();
	}

}
