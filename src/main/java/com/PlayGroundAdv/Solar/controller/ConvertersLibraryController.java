package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.AddConverterModelRequest;
import com.PlayGroundAdv.Solar.model.ConverterCorrectionModel;
import com.PlayGroundAdv.Solar.model.ConverterListModel;
import com.PlayGroundAdv.Solar.model.ConverterModel;
import com.PlayGroundAdv.Solar.model.DCOptimizerFavoritEntityModel;
import com.PlayGroundAdv.Solar.model.GetConverterRequest;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.ProjectForConvertModelResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.service.libraries.ConverterService;
import com.PlayGroundAdv.Solar.service.libraries.export.ExportOptimizerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/convertersLibrary")
public class ConvertersLibraryController {

	final ConverterService convertersManagementService;
	final ExportOptimizerService exportOptimizerService;

	public ConvertersLibraryController(ConverterService convertersManagementService,
			ExportOptimizerService exportOptimizerService) {
		super();
		this.convertersManagementService = convertersManagementService;
		this.exportOptimizerService = exportOptimizerService;
	}

	@PostMapping("/filterConverter")
	public ConverterListModel filterConverter(HttpServletRequest request, @RequestBody GetConverterRequest gcr)
			{
		return convertersManagementService.filterConverter(gcr.getSearchConvertersRequest(), gcr.getIdUser(),
				gcr.getPage(), gcr.getSize());
	}

	@PostMapping("/FavoritConverter/{idOwner}")
	public String FavoritConverter(HttpServletRequest request, @RequestBody HistoriqModel hm,
			@PathVariable("idOwner") Long idOwner) {
		hm.setSessionId(request.getSession().getId());
		return convertersManagementService.FavoritConv(Long.valueOf(hm.getObjectOne() + ""), hm.getIdUser(),
				(Boolean) hm.getObjectTwo(), hm.getIpAdress(), hm.getTimeZone(), idOwner, hm.getNumTab(),
				hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/sendCorrectionconverterRequest")
	public String sendCorrectionconverterRequest(@RequestBody ConverterCorrectionModel model) {
		return convertersManagementService.sendCorrectionconverterRequest(model);
	}

	@PostMapping("/stillAddNewConverter")
	public DCOptimizerFavoritEntityModel stillAddNewConverter(HttpServletRequest request, @RequestBody HistoriqModel hm)
			{
		hm.setSessionId(request.getSession().getId());
		ObjectMapper mapper = new ObjectMapper();
		AddConverterModelRequest clle = null;

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			clle = mapper.readValue(json, AddConverterModelRequest.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertersManagementService.stillAddNewConverter(clle, hm.getIdUser(), hm.getIpAdress(),
				hm.getTimeZone(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/editConverter")
	public String editConverter(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		ObjectMapper mapper = new ObjectMapper();
		ConverterModel cm = new ConverterModel();
		byte[] json = null;

		try {
			json = mapper.writeValueAsBytes(hm.getObjectOne());
			cm = mapper.readValue(json, ConverterModel.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return convertersManagementService.editConverters(cm, hm.getIdUser(), hm.getIpAdress(), hm.getTimeZone(),
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/getPermitForDelet")
	public List<ProjectForConvertModelResult> deleteConverter(@RequestBody Long id) {
		return convertersManagementService.deleteConverter(id);
	}

	@PostMapping("/deleteConverter")
	public String stilldeleteConverter(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return convertersManagementService.stilldeleteConverter(Long.valueOf(hm.getObjectOne().toString()),
				hm.getIdUser(), hm.getIpAdress(), hm.getTimeZone(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/activateDeletedConverter")
	public String activateDeleteConverter(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return convertersManagementService.activateConverter(Long.valueOf(hm.getObjectOne().toString()), hm.getIdUser(),
				hm.getIpAdress(), hm.getTimeZone(), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	/*
	 * Edit Converter Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavList")
	public List<UsersEntityResult> getUsersForFavList(@RequestBody Long[] tabId) {
		return convertersManagementService.getUsersForFavList(tabId[1], tabId[0]);
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

		return convertersManagementService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/addConvert")
	public LinkedHashMap<Long, String> addConvert(HttpServletRequest request, @RequestBody HistoriqModel hm)
			{
		ObjectMapper mapper = new ObjectMapper();
		AddConverterModelRequest clle = null;
		hm.setSessionId(request.getSession().getId());

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			clle = mapper.readValue(json, AddConverterModelRequest.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return convertersManagementService.addConverter(clle, hm.getIdUser(), hm.getIpAdress(), hm.getTimeZone(),
				Long.valueOf(hm.getObjectTwo().toString()), hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

	@PostMapping("/editOptimizerNotification")
	public String editOptimizerNotification(@RequestBody String[] inverterInfo) {
		return convertersManagementService.editOptimizerNotification(Long.valueOf(inverterInfo[0]), inverterInfo[1],
				inverterInfo[2]);
	}

	@PostMapping("/addOptimizerNotification")
	public String addOptimizerNotification(@RequestBody String[] inverterInfo) {
		return convertersManagementService.addOptimizerNotification(Long.valueOf(inverterInfo[0]), inverterInfo[1],
				inverterInfo[2]);
	}

	@PostMapping("/exportOptimizer")
	public ResponseEntity<byte[]> exportOptimizer(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());
		return exportOptimizerService.exportOptimizerSvc(hm.getIdUser(), hm.getIpAdress(), hm.getTimeZone(),
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}

}
