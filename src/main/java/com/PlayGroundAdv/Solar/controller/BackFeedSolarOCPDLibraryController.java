package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.BackFeedSolarOCPDEntityModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchBackFeedSolarOCPDResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.service.libraries.BackFeedSolarOCPDLibraryService;
import com.PlayGroundAdv.Solar.service.utils.GetEquipmentReports;

@RestController
@RequestMapping("/backFeedSolarOCPDLibrary")
public class BackFeedSolarOCPDLibraryController {

	final BackFeedSolarOCPDLibraryService getBackFeedSolarOCPDLibraryService;
	final GetEquipmentReports getEquipmentReports;

	public BackFeedSolarOCPDLibraryController(BackFeedSolarOCPDLibraryService getBackFeedSolarOCPDLibraryService,
			GetEquipmentReports getEquipmentReports) {
		super();
		this.getBackFeedSolarOCPDLibraryService = getBackFeedSolarOCPDLibraryService;
		this.getEquipmentReports = getEquipmentReports;
	}

	@PostMapping("/editBackFeedSolarOCPD/{idUserCo}")
	public String editBackFeedSolarOCPDeditBackFeedSolarOCPD(
			@RequestBody SearchBackFeedSolarOCPDResult backFeedSolarOCPD, @PathVariable Long idUserCo)
			throws Exception {
		return getBackFeedSolarOCPDLibraryService.editBackFeedSolarOCPD(backFeedSolarOCPD, idUserCo);
	}

	@PostMapping("/editBackFeedSolarOCPDNotification")
	public String editBackFeedSolarOCPDNotification(@RequestBody Long[] backFeedSolarOCPDInfo) throws Exception {
		return getBackFeedSolarOCPDLibraryService.editBackFeedSolarOCPDNotification(backFeedSolarOCPDInfo[0],
				backFeedSolarOCPDInfo[1]);
	}

	@PostMapping("/addBackFeedSolarOCPDNotification")
	public String addBackFeedSolarOCPDNotification(@RequestBody Long[] backFeedSolarOCPDInfo) throws Exception {
		return getBackFeedSolarOCPDLibraryService.addBackFeedSolarOCPDNotification(backFeedSolarOCPDInfo[0],
				backFeedSolarOCPDInfo[1]);
	}

	@GetMapping("/activedBackFeedSolarOCPDLibrary/{id}/{idUserCo}")
	public String activedBackFeedSolarOCPDLibrary(@PathVariable("id") Long idBackFeedSolarOCPD,
			@PathVariable("idUserCo") Long idUserCo) throws Exception {
		return getBackFeedSolarOCPDLibraryService.backFeedSolarOCPDLibraryActived(idBackFeedSolarOCPD, idUserCo);
	}

	@GetMapping("/getPermitOfDeletedBackFeedSolarOCPDLibrary/{idBackFeedSolarOCPD}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedBackFeedSolarOCPDLibrary(@PathVariable Long idBackFeedSolarOCPD)
			throws Exception {
		return getBackFeedSolarOCPDLibraryService.getAllPermitOfBackFeedSolarOCPDDeleted(idBackFeedSolarOCPD);
	}

	@GetMapping("/deleteBackFeedSolarOCPDLibrary/{id}/{idUserCo}")
	public boolean deleteBackFeedSolarOCPDLibrary(@PathVariable("id") Long idBackFeedSolarOCPD,
			@PathVariable("idUserCo") Long idUserCo) throws Exception {
		return getBackFeedSolarOCPDLibraryService.deleteBackFeedSolarOCPDLibrary(idBackFeedSolarOCPD, idUserCo);
	}

	@PostMapping("/existBackFeedSolarOCPD")
	public String checkBackFeedSolarOCPDExistent(@RequestBody SearchBackFeedSolarOCPDResult backFeedSolarOCPD)
			throws Exception {
		return getBackFeedSolarOCPDLibraryService.checkBackFeedSolarOCPDExistent(backFeedSolarOCPD);
	}

	@PostMapping("/addBackFeedSolarOCPD/{idUser}")
	public BackFeedSolarOCPDEntityModel addNewBackFeedSolarOCPD(
			@RequestBody SearchBackFeedSolarOCPDResult searchBackFeedSolarOCPDResult, @PathVariable Long idUser)
			throws Exception {
		return getBackFeedSolarOCPDLibraryService.addNewBackFeedSolarOCPD(searchBackFeedSolarOCPDResult, idUser);
	}

	@GetMapping("/getListOfBackFeedSolarOCPD")
	public List<BackFeedSolarOCPDEntityModel> getListOfBackFeedSolarOCPD() throws Exception {
		return getBackFeedSolarOCPDLibraryService.getListOfBackFeedSolarOCPD();
	}

	@PostMapping("/addOldValue")
	public String addOldValue(@RequestBody String idUser) throws Exception {
		// A.B get Equipment Report
		getEquipmentReports.getprojectlist();
		return "Done";
	}

	@PostMapping("/filter")
	public Page<SearchBackFeedSolarOCPDResult> filterBackFeedSolarOCPD(@RequestBody ComponentPageRequest request)
			throws Exception {
		return getBackFeedSolarOCPDLibraryService.filter(request);
	}
}
