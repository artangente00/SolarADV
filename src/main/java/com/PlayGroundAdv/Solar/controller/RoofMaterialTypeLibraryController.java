package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.SearchRoofMaterialTypeResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.service.libraries.RoofMaterialTypeService;

@RestController
@RequestMapping("/roofMaterialTypeLibrary")
public class RoofMaterialTypeLibraryController {

	final RoofMaterialTypeService getRoofMaterialTypeLibraryService;

	public RoofMaterialTypeLibraryController(RoofMaterialTypeService getRoofMaterialTypeLibraryService) {
		super();
		this.getRoofMaterialTypeLibraryService = getRoofMaterialTypeLibraryService;
	}

	@PostMapping("/editRoofMaterialType/{idUserCo}")
	public String editRoofMaterialTypeeditRoofMaterialType(@RequestBody SearchRoofMaterialTypeResult roofMaterialType,
			@PathVariable Long idUserCo) throws Exception {
		return getRoofMaterialTypeLibraryService.editRoofMaterialType(roofMaterialType, idUserCo);
	}

	@PostMapping("/editRoofMaterialTypeNotification")
	public String editRoofMaterialTypeNotification(@RequestBody String[] roofMaterialTypeInfo) throws Exception {
		return getRoofMaterialTypeLibraryService.editRoofMaterialTypeNotification(Long.valueOf(roofMaterialTypeInfo[0]),
				roofMaterialTypeInfo[1]);
	}

	@PostMapping("/addRoofMaterialTypeNotification")
	public String addRoofMaterialTypeNotification(@RequestBody String[] roofMaterialTypeInfo) throws Exception {
		return getRoofMaterialTypeLibraryService.addRoofMaterialTypeNotification(Long.valueOf(roofMaterialTypeInfo[0]),
				roofMaterialTypeInfo[1]);
	}

	@GetMapping("/activedRoofMaterialTypeLibrary/{id}/{idUserCo}")
	public String activedRoofMaterialTypeLibrary(@PathVariable("id") Long idRoofMaterialType,
			@PathVariable("idUserCo") Long idUserCo) throws Exception {
		return getRoofMaterialTypeLibraryService.roofMaterialTypeLibraryActived(idRoofMaterialType, idUserCo);
	}

	@GetMapping("/getPermitOfDeletedRoofMaterialTypeLibrary/{idRoofMaterialType}")
	public List<ProjectForLibrariesModel> getPermitOfDeletedRoofMaterialTypeLibrary(@PathVariable Long idRoofMaterialType)
			throws Exception {
		return getRoofMaterialTypeLibraryService.getAllPermitOfRoofMaterialTypeDeleted(idRoofMaterialType);
	}

	@GetMapping("/deleteRoofMaterialTypeLibrary/{id}/{idUserCo}")
	public boolean deleteRoofMaterialTypeLibrary(@PathVariable("id") Long idRoofMaterialType,
			@PathVariable("idUserCo") Long idUserCo) throws Exception {
		return getRoofMaterialTypeLibraryService.deleteRoofMaterialTypeLibrary(idRoofMaterialType, idUserCo);
	}

	@PostMapping("/existRoofMaterialType")
	public String checkRoofMaterialTypeExistent(@RequestBody SearchRoofMaterialTypeResult roofMaterialType)
			throws Exception {
		return getRoofMaterialTypeLibraryService.checkRoofMaterialTypeExistent(roofMaterialType);
	}

	@PostMapping("/addRoofMaterialType/{idUser}")
	public RoofMaterialType addNewRoofMaterialType(
			@RequestBody SearchRoofMaterialTypeResult SearchRoofMaterialTypeResult, @PathVariable Long idUser)
			throws Exception {
		return getRoofMaterialTypeLibraryService.addNewRoofMaterialType(SearchRoofMaterialTypeResult, idUser);
	}

	@GetMapping("/getListOfRoofMaterialType")
	public List<SearchRoofMaterialTypeResult> getListOfRoofMaterialType() throws Exception {
		return getRoofMaterialTypeLibraryService.getListOfRoofMaterialType();
	}

	@PostMapping("/filter")
	public Page<SearchRoofMaterialTypeResult> filterRoofMaterialType(@RequestBody ComponentPageRequest request)
			throws Exception {
		return getRoofMaterialTypeLibraryService.filter(request);
	}
}
