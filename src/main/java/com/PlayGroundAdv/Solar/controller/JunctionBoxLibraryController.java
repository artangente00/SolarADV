package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.JunctionsBoxModel;
import com.PlayGroundAdv.Solar.model.NewJunctionBoxModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.service.libraries.JunctionBoxService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/junctionBoxLibrary")
public class JunctionBoxLibraryController {

	final JunctionBoxService junctionBoxLibraryService;

	public JunctionBoxLibraryController(JunctionBoxService junctionBoxLibraryService) {
		super();
		this.junctionBoxLibraryService = junctionBoxLibraryService;
	}

	/*
	 * Get All Junction Box
	 */
	@PostMapping("/filter")
	public Page<JunctionsBoxModel> filterJunctionBox(@RequestBody ComponentPageRequest request) {
		return junctionBoxLibraryService.filter(request);
	}

	/*
	 * Edit Junction Box Favorite
	 */
	@PostMapping("/editJunctionBoxFavoriteList/{idOwner}")
	public String editJunctionBoxFavoriteList(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable("idOwner") Long idOwner) {
		hm.setSessionId(request.getSession().getId());
		return junctionBoxLibraryService.editJunctionBoxFavoriteList(Long.valueOf(hm.getObjectTwo().toString()),
				(Boolean) hm.getObjectOne(), hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), idOwner,
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/sendCorrectionJuctionRequest")
	public String sendCorrectionJuctionRequest(@RequestBody CorrectionRequest model) {
		return junctionBoxLibraryService.sendCorrectionRequest(model);
	}

	/*
	 * Edit Junction Box Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavList")
	public List<UsersEntityResult> getUsersForFavList(@RequestBody Long[] idsForUserUpdtFav) {
		return junctionBoxLibraryService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
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
		return junctionBoxLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/editJunctionBox")
	public String editJunctionBox(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		JunctionsBoxModel cm = new JunctionsBoxModel();
		byte[] json = null;
		hm.setSessionId(request.getSession().getId());

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			cm = mapper.readValue(json, JunctionsBoxModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return junctionBoxLibraryService.editJunctionBox(cm, hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(),
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}
	
	/*
	 * Delete Junction Box
	 */
	@PostMapping("/getRemoveJunctionBoxConfirmation")
	public List<ProjectForLibrariesModel> getRemoveJunctionBoxConfirmation(
			@RequestBody JunctionsBoxModel junctionsBoxID) {
		return junctionBoxLibraryService.getRemoveJunctionBoxConfirmation(junctionsBoxID);
	}

	@PostMapping("/deleteJunctionBox")
	public String deleteJunctionBox(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return junctionBoxLibraryService.deleteJunctionBox(Long.valueOf(hm.getObjectOne().toString()), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	/*
	 * Activate Junction Box
	 */
	@PostMapping("/activateJunctionBox")
	public String activateJunctionBox(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return junctionBoxLibraryService.activateJunctionBox(Long.valueOf(hm.getObjectOne().toString()),
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/checkJBoxExistent/{idUser}")
	public List<JunctionsBoxModel> checkJBoxExistent(@RequestBody NewJunctionBoxModel junctionBox,
			@PathVariable Long idUser) {
		return junctionBoxLibraryService.checkJBoxExistent(junctionBox, idUser);
	}

	@PostMapping("/addJunctionBox/{idPermit}")
	public DCCombinerDisconnectEntity addJunctionBox(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable Long idPermit) {
		ObjectMapper mapper = new ObjectMapper();
		NewJunctionBoxModel junctionBoxRes = null;
		hm.setSessionId(request.getSession().getId());
		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			junctionBoxRes = mapper.readValue(json, NewJunctionBoxModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return junctionBoxLibraryService.addJunctionBox(junctionBoxRes, hm.getIpAdress(), hm.getTimeZone(),
				hm.getIdUser(), idPermit, hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

}
