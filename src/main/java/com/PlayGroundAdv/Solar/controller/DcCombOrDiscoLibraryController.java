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

import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.model.libraries.DcCombinerorDisconnectModel;
import com.PlayGroundAdv.Solar.service.libraries.DcCombinerDisconnectService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/dcCombOrDiscoLibrary")
public class DcCombOrDiscoLibraryController {

	final DcCombinerDisconnectService getAlldcCombinerOrDiService;

	public DcCombOrDiscoLibraryController(DcCombinerDisconnectService getAlldcCombinerOrDiService) {
		super();
		this.getAlldcCombinerOrDiService = getAlldcCombinerOrDiService;
	}

	@PostMapping("/filter")
	public Page<DcCombinerorDisconnectModel> filter(@RequestBody ComponentPageRequest request) {
		return getAlldcCombinerOrDiService.filter(request);
	}


	@PostMapping("/activatedcComOrDisc")
	public String activatedcComOrDisc(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());

		return getAlldcCombinerOrDiService.activatedcComOrDisc(Long.valueOf(hm.getObjectOne().toString()),
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/getRemovedcComDisconnectConfirmation")
	public List<ProjectForLibrariesModel> getRemovedcComDisconnectConfirmation(@RequestBody Long id) {
		return getAlldcCombinerOrDiService.getRemovedcComDisconnectConfirmation(id);
	}

	@PostMapping("/deletedcCombOrDisco")
	public String deletedcCombOrDisco(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return getAlldcCombinerOrDiService.deletedcCombOrDisco(Long.valueOf(hm.getObjectOne().toString()),
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/sendCorrectionDCRequest")
	public String sendCorrectionDCRequest(@RequestBody CorrectionRequest model) {
		return getAlldcCombinerOrDiService.sendCorrectionRequest(model);
	}

	@PostMapping("/editDCcombiOrDisc")
	public String editDCcombiOrDisc(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		ObjectMapper mapper = new ObjectMapper();

		DcCombinerorDisconnectModel cm = new DcCombinerorDisconnectModel();
		byte[] json = null;

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			cm = mapper.readValue(json, DcCombinerorDisconnectModel.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return getAlldcCombinerOrDiService.editDCcombiOrDisc(cm, hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(),
				hm.getSessionId());
	}

	@PostMapping("/updateDcCombiOrDiscStatus/{idOwner}")
	public String updateDcCombiOrDiscStatus(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable Long idOwner) {
		hm.setSessionId(request.getSession().getId());
		return getAlldcCombinerOrDiService.updateDcCombiOrDiscStatus((Boolean) hm.getObjectOne(),
				Long.valueOf(hm.getObjectTwo().toString()), hm.getIdUser(), hm.getIpAdress(), hm.getTimeZone(), idOwner,
				hm.getSessionId());

	}


	/*
	 * Edit DC DC Disconnect Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavListDCDisco")
	public List<UsersEntityResult> getUsersForFavListDCDisco(@RequestBody Long[] idsForUserUpdtFav) {
		return getAlldcCombinerOrDiService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
	}

	@PostMapping("/addDcCombOrDisc")
	public Long addDcCombOrDisc(HttpServletRequest request, @RequestBody HistoriqModel hm)
			throws IOException {
		hm.setSessionId(request.getSession().getId());
		ObjectMapper mapper = new ObjectMapper();
		DcCombinerorDisconnectModel dc = new DcCombinerorDisconnectModel();
		byte[] json = null;

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			dc = mapper.readValue(json, DcCombinerorDisconnectModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return getAlldcCombinerOrDiService.addDcCombOrDisc(dc, hm.getIdUser(), hm.getIpAdress(), hm.getTimeZone(),
				Long.valueOf(hm.getObjectTwo().toString()), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());

	}

	@PostMapping("/checkdcComDiscExistent/{userId}")
	public List<ComponentModel> checkdIfcComDiscExistent(@RequestBody ComponentModel component, @PathVariable Long userId) {
		return getAlldcCombinerOrDiService.checkdcComDiscExistent(component, userId);
	}

	@PostMapping("/editUsersFavoriteListDCDisco")
	public String editUsersFavoriteListDCDisco(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		ObjectMapper mapper = new ObjectMapper();
		Long[] clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectTwo());
			clle = mapper.readValue(json, Long[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getAlldcCombinerOrDiService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/editDCCombinerNotification")
	public String editDCCombinerNotification(@RequestBody String[] inverterInfo) {
		return getAlldcCombinerOrDiService.editDCCombinerNotification(Long.valueOf(inverterInfo[0]), inverterInfo[1],
				inverterInfo[2]);
	}

	@PostMapping("/addDCCombinerNotification")
	public String addDCCombinerNotification(@RequestBody String[] inverterInfo) {
		return getAlldcCombinerOrDiService.addDCCombinerNotification(Long.valueOf(inverterInfo[0]), inverterInfo[1],
				inverterInfo[2]);
	}

}
