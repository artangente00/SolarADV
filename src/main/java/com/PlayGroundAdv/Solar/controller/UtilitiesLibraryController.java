package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.service.libraries.UtilityCompanyLibraryService;

@RestController
@RequestMapping("/utilitiesLibrary")
public class UtilitiesLibraryController {

	final UtilityCompanyLibraryService utilityCompanyLibraryService;

	public UtilitiesLibraryController(UtilityCompanyLibraryService utilityCompanyLibraryService) {
		super();
		this.utilityCompanyLibraryService = utilityCompanyLibraryService;
	}

	/*
	 * Add Utility Company
	 */
	@PostMapping("/addUtilityCompany")
	public String addUtilityCompany(@RequestBody HistoriqModel hm) {

		return utilityCompanyLibraryService.addUtilityCompany(hm, hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	/*
	 * Edit Utility Company
	 */

	@PostMapping("/editUtilityCompany")
	public String editUtilityCompany(@RequestBody HistoriqModel hm) {

		return utilityCompanyLibraryService.editUtilityCompany(hm, hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	/*
	 * Delete Utility Company
	 */
	@PostMapping("/getRemoveUtilityCompanyConfirmation")
	public List<ProjectForLibrariesModel> getRemoveUtilityCompanyConfirmation(@RequestBody Integer UtilityCompanyID)
			throws IOException {

		return utilityCompanyLibraryService.getRemoveUtilityCompanyConfirmation(UtilityCompanyID);
	}

	@PostMapping("/deleteUtilityCompany")
	public String deleteUtilityCompany(@RequestBody HistoriqModel hm) {

		return utilityCompanyLibraryService.deleteUtilityCompany((Integer) hm.getObjectOne(), hm.getIpAdress(),
				hm.getTimeZone(), hm.getIdUser());
	}

	/*
	 * Activate Utility Company
	 */
	@PostMapping("/activateUtilityCompany")
	public String activateUtilityCompany(@RequestBody HistoriqModel hm) {

		return utilityCompanyLibraryService.activateUtilityCompany(((Integer) hm.getObjectOne()).longValue(),
				hm.getIpAdress(), hm.getTimeZone(), hm.getIdUser());
	}

	@PostMapping("/filter")
	public Page<ElectricalUtilityEntity> filter(@RequestBody ComponentPageRequest request) {
		return utilityCompanyLibraryService.filter(request);
	}
}
