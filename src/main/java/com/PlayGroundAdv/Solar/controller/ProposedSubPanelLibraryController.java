package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.entity.ProposedSubPanel;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.ProposedSubPanelFavRequest;
import com.PlayGroundAdv.Solar.model.SearchProposedSubPanelResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.service.libraries.SubPanelService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/proposedSubPanelLibrary")
public class ProposedSubPanelLibraryController {

	final SubPanelService getProposedSubPanelLibraryService;

	public ProposedSubPanelLibraryController(SubPanelService getProposedSubPanelLibraryService) {
		super();
		this.getProposedSubPanelLibraryService = getProposedSubPanelLibraryService;
	}

	@GetMapping("/addProposedSubPanelFavorite/{idContractor}/{idProposedSubPanel}/{idOwner}")
	public String addProposedSubPanelFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idProposedSubPanel") Long idProposedSubPanel,
			@PathVariable("idOwner") Long idOwner) {
		return getProposedSubPanelLibraryService.addProposedSubPanelFavorite(idContractor, idProposedSubPanel, idOwner);
	}

	@GetMapping("/removeProposedSubPanelFavorite/{idContractor}/{idProposedSubPanel}/{idOwner}")
	public String removeProposedSubPanelFavorite(@PathVariable("idContractor") Long idContractor, @PathVariable("idProposedSubPanel") Long idProposedSubPanel,
			@PathVariable("idOwner") Long idOwner) {
		return getProposedSubPanelLibraryService.removeProposedSubPanelFavorite(idContractor, idProposedSubPanel,
				idOwner);
	}

	@PostMapping("/editProposedSubPanel/{idUserCo}")
	public String editProposedSubPaneleditProposedSubPanel(@RequestBody ProposedSubPanelFavRequest proposedSubPanel,
			@PathVariable Long idUserCo) {
		return getProposedSubPanelLibraryService.editProposedSubPanel(proposedSubPanel, idUserCo);
	}
	@PostMapping("/editProposedSubPanelNotification")
	public String editProposedSubPanelNotification(@RequestBody String[] proposedSubPanelInfo) {
		return getProposedSubPanelLibraryService.editProposedSubPanelNotification(Long.valueOf(proposedSubPanelInfo[0]),
				proposedSubPanelInfo[1], proposedSubPanelInfo[2]);
	}

	@PostMapping("/addProposedSubPanelNotification")
	public String addProposedSubPanelNotification(@RequestBody String[] proposedSubPanelInfo) {
		return getProposedSubPanelLibraryService.addProposedSubPanelNotification(Long.valueOf(proposedSubPanelInfo[0]),
				proposedSubPanelInfo[1], proposedSubPanelInfo[2]);
	}

	@PostMapping("/sendCorrectionProposedSubPanelRequest")
	public String sendCorrectionProposedSubPanelRequest(
			@RequestBody CorrectionRequest proposedSubPanelInfo) {
		return getProposedSubPanelLibraryService.sendCorrectionProposedSubPanelRequest(proposedSubPanelInfo);
	}

	@GetMapping("/activedProposedSubPanelLibrary/{id}/{UserID}")
	public String activedProposedSubPanelLibrary(@PathVariable("id") Long idProposedSubPanel, @PathVariable("UserID") Long userID)
			{
		return getProposedSubPanelLibraryService.proposedSubPanelLibraryActived(idProposedSubPanel, userID);
	}

	@GetMapping("/getPermitOfDeletedProposedSubPanelLibrary/{idProposedSubPanel}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedProposedSubPanelLibrary(@PathVariable Long idProposedSubPanel)
			{
		return getProposedSubPanelLibraryService.getAllPermitOfProposedSubPanelDeleted(idProposedSubPanel);
	}

	@GetMapping("/deleteProposedSubPanelLibrary/{id}/{UserID}")
	public boolean deleteProposedSubPanelLibrary(@PathVariable("id") Long idProposedSubPanel, @PathVariable("UserID") Long userID)
			{
		return getProposedSubPanelLibraryService.deleteProposedSubPanelLibrary(idProposedSubPanel, userID);
	}

	/*
	 * Edit ProposedSubPanel Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavListProposedSubPanel")
	public List<UsersEntityResult> getUsersForFavListProposedSubPanel(@RequestBody Long[] idsForUserUpdtFav)
			{
		return getProposedSubPanelLibraryService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteListProposedSubPanel")
	public String editUsersFavoriteListProposedSubPanel(@RequestBody HistoriqModel hm) {
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getProposedSubPanelLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	@PostMapping("/existProposedSubPanel/{idUserCo}")
	public List<ProposedSubPanelFavRequest> checkProposedSubPanelExistent(
			@RequestBody ProposedSubPanelFavRequest proposedSubPanel, @PathVariable Long idUserCo) {
		return getProposedSubPanelLibraryService.checkProposedSubPanelExistent(proposedSubPanel, idUserCo);
	}

	@PostMapping("/addProposedSubPanel/{idUser}/{idPermitInfo}")
	public ProposedSubPanel addNewProposedSubPanel(@RequestBody ProposedSubPanelFavRequest proposedSubPanelFavRequest,
			@PathVariable Long idUser, @PathVariable Long idPermitInfo) {
		return getProposedSubPanelLibraryService.addNewProposedSubPanel(idPermitInfo, proposedSubPanelFavRequest,
				idUser);
	}

	// CI : 30/12/2019 : ADD METHOD FILTER TO GET AND SEARCH Sub panel LIBRARY AND
	// DELETED Sub panel
	@PostMapping("/filter")
	public Page<SearchProposedSubPanelResult> filterProposedSubPanel(@RequestBody ComponentPageRequest request)	{
		return getProposedSubPanelLibraryService.filter(request);
	}
}
