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
import com.PlayGroundAdv.Solar.model.ModuleFavRequest;
import com.PlayGroundAdv.Solar.model.ModuleInfoModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchModulResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.service.libraries.ModuleService;
import com.PlayGroundAdv.Solar.service.libraries.export.ExportModuleLibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/moduleLibrary")
public class ModuleLibraryController {

	final ModuleService getLibraryService;
	final ExportModuleLibraryService exportModuleLibraryService;

	public ModuleLibraryController(ModuleService getLibraryService,
			ExportModuleLibraryService exportModuleLibraryService) {
		super();
		this.getLibraryService = getLibraryService;
		this.exportModuleLibraryService = exportModuleLibraryService;
	}

	@PostMapping("/filter")
	public Page<SearchModulResult> filter(@RequestBody ComponentPageRequest request) {
		return getLibraryService.filter(request);
	}

	@PostMapping("/addModuleFavorite")
	public String addModuleFavorite(@RequestBody Long[] moduleInfo) {
		return getLibraryService.addModuleFavorite(moduleInfo[0], moduleInfo[1], moduleInfo[2]);
	}

	@PostMapping("/removeModuleFavorite")
	public String removeModuleFavorite(@RequestBody Long[] moduleInfo) {
		return getLibraryService.removeModuleFavorite(moduleInfo[0], moduleInfo[1], moduleInfo[2]);
	}

	/*
	 * Arij-15/08: CR 490 edit Module
	 */
	@PostMapping("/editModule/{idUserco}")
	public String editModule(@RequestBody ModuleFavRequest module, @PathVariable Long idUserco) {
		return getLibraryService.editModule(module, idUserco);
	}
	
	@PostMapping("/verifModule/{idUserco}")
	public String verifModule(@RequestBody ModuleFavRequest module, @PathVariable Long idUserco) {
		return getLibraryService.verifModule(module, idUserco);
	}

	@PostMapping("/sendCorrectionModuleRequest")
	public String sendCorrectionModuleRequest(@RequestBody CorrectionRequest request) {
		return getLibraryService.sendCorrectionModuleRequest(request);
	}

	@PostMapping("/editModuleNotification")
	public String editModuleNotification(@RequestBody String[] moduleInfo) {
		return getLibraryService.editModuleNotification(Long.valueOf(moduleInfo[0]), moduleInfo[1], moduleInfo[2]);
	}

	@PostMapping("/addModuleNotification")
	public String addModuleNotification(@RequestBody String[] moduleInfo) {
		return getLibraryService.addModuleNotification(Long.valueOf(moduleInfo[0]), moduleInfo[1], moduleInfo[2]);
	}

	/*
	 * Arij-15/08: CR 490 edit Module
	 */
	@PostMapping("/exportModuleLibrary")
	public ResponseEntity<byte[]> exportModuleLibrary(HttpServletRequest request, @RequestBody HistoriqModel hm) {
		hm.setSessionId(request.getSession().getId());

		return exportModuleLibraryService.ExportModuleLibrary(hm.getIdUser(), hm.getIpAdress(), hm.getTimeZone(),
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
	}
	
	// CR-914
	// Change Request Module List Management

	@GetMapping("/activedModuleLibrary/{id}/{idUserco}")
	public String activedModuleLibrary(@PathVariable("id") Long idModule, @PathVariable("idUserco") Long idUserco)
			{
		return getLibraryService.moduleLibraryActived(idModule, idUserco);
	}

	@GetMapping("/getPermitOfDeletedModuleLibrary/{id}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedModuleLibrary(@PathVariable("id") Long id) {
		return getLibraryService.getAllPermitOfModuleDeleted(id);
	}

	@GetMapping("/deleteModuleLibrary/{id}/{idUserco}")
	public boolean deleteModuleLibrary(@PathVariable("id") Long idModule, @PathVariable("idUserco") Long idUserco)
			{
		return getLibraryService.deleteModuleLibrary(idModule, idUserco);
	}

	/*
	 * Edit Inverter Favorite for Other Users
	 */
	@PostMapping("/getUsersForFavListModule")
	public List<UsersEntityResult> getUsersForFavListModule(@RequestBody Long[] idsForUserUpdtFav) {
		return getLibraryService.getUsersForFavList(idsForUserUpdtFav[0], idsForUserUpdtFav[1]);
	}

	@PostMapping("/editUsersFavoriteListModule")
	public String editUsersFavoriteListModule(HttpServletRequest request, @RequestBody HistoriqModel hm)
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
		return getLibraryService.editUsersFavoriteList(Long.valueOf(hm.getObjectOne().toString()), clle,
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser(), hm.getNumTab(), hm.getSessionId(),
				hm.getOpenDate());
	}

	@PostMapping("/getModule")
	public ModuleInfoModel getModule(@RequestBody Long moduleId) {
		return getLibraryService.getModuleById(moduleId);
	}

}
