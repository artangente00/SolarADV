package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.EngineersModel;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchEngineersResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.service.libraries.EngineersLibraryService;

@RestController
@RequestMapping("/engineersLibrary")
public class EngineersLibraryController {

	final EngineersLibraryService getEngineersLibraryService;

	public EngineersLibraryController(EngineersLibraryService getEngineersLibraryService) {
		super();
		this.getEngineersLibraryService = getEngineersLibraryService;
	}

	@PostMapping("/filter")
	public Page<SearchEngineersResult> filterEngineers(@RequestBody ComponentPageRequest request) {
		return getEngineersLibraryService.filter(request);
	}

	@PostMapping("/editEngineers/{idUserCo}")
	public String editEngineerseditEngineers(@RequestBody SearchEngineersResult engineers, @PathVariable Long idUserCo) {
		return getEngineersLibraryService.editEngineers(engineers, idUserCo);
	}

	@PostMapping("/editEngineersNotification")
	public String editEngineersNotification(@RequestBody String[] engineersInfo) {
		return getEngineersLibraryService.editEngineersNotification(Long.valueOf(engineersInfo[0]), engineersInfo[1],
				engineersInfo[2]);
	}

	@PostMapping("/addEngineersNotification")
	public String addEngineersNotification(@RequestBody String[] engineersInfo) {
		return getEngineersLibraryService.addEngineersNotification(Long.valueOf(engineersInfo[0]), engineersInfo[1],
				engineersInfo[2]);
	}

	@GetMapping("/activedEngineersLibrary/{id}/{idUserCo}")
	public String activedEngineersLibrary(@PathVariable("id") Long idEngineers, @PathVariable("idUserCo") Long idUserCo) {
		return getEngineersLibraryService.engineersLibraryActived(idEngineers, idUserCo);
	}

	@GetMapping("/getPermitOfDeletedEngineersLibrary/{idEngineers}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedEngineersLibrary(@PathVariable Long idEngineers) {
		return getEngineersLibraryService.getAllPermitOfEngineersDeleted(idEngineers);
	}

	@GetMapping("/deleteEngineersLibrary/{id}/{idUserCo}")
	public boolean deleteEngineersLibrary(@PathVariable("id") Long idEngineers, @PathVariable("idUserCo") Long idUserCo) {
		return getEngineersLibraryService.deleteEngineersLibrary(idEngineers, idUserCo);
	}

	@PostMapping("/existEngineers")
	public String checkEngineersExistent(@RequestBody SearchEngineersResult engineers) {
		return getEngineersLibraryService.checkEngineersExistent(engineers);
	}

	@PostMapping("/addEngineers/{idUser}")
	public String addNewEngineers(@RequestBody SearchEngineersResult searchEngineersResult,
			@PathVariable Long idUser) {
		return getEngineersLibraryService.addNewEngineers(searchEngineersResult, idUser);
	}

	@GetMapping("/getListOfEnginners")
	public List<EngineersModel> getListOfEnginners() {
		return getEngineersLibraryService.getListOfEnginners();
	}

}
