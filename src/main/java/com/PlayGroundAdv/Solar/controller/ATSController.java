package com.PlayGroundAdv.Solar.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.CompoentFavDto;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.ComponentResult;
import com.PlayGroundAdv.Solar.model.libraries.CorrectionRequest;
import com.PlayGroundAdv.Solar.model.libraries.FavoriteListDto;
import com.PlayGroundAdv.Solar.model.libraries.NewComponentRequest;
import com.PlayGroundAdv.Solar.service.libraries.ATSService;

@RestController
@RequestMapping(value = "/ats")
public class ATSController {

	final ATSService atsService;

	public ATSController(ATSService atsService) {
		super();
		this.atsService = atsService;
	}

	@PostMapping("/getList")
	public Page<ComponentResult> getList(@RequestBody ComponentPageRequest request) {
		return atsService.getList(request);
	}

	@PostMapping("/searchList")
	public Page<ComponentResult> searchList(@RequestBody ComponentPageRequest request) {
		return atsService.searchList(request);
	}

//	Add Component
	@PostMapping("/addComponent")
	public FavoriteListDto addComponent(@RequestBody NewComponentRequest request) {
		return atsService.addComponent(request);
	}

//	Edit Component
	@PostMapping("/editComponent")
	public ComponentResult editComponent(@RequestBody NewComponentRequest request) {
		return atsService.editComponent(request);
	}

//	Deleted Component
	@PostMapping("/deleteComponent")
	public Boolean deleteComponent(@RequestBody NewComponentRequest request) {
		return atsService.deleteComponent(request);
	}

//	Restore Component
	@PostMapping("/restoreComponent")
	public String restoreComponent(@RequestBody NewComponentRequest request) {
		return atsService.restoreComponent(request);
	}

//	Add Component to Fav List
	@PostMapping("/addToFav")
	public Boolean addToFav(@RequestBody CompoentFavDto request) {
		return atsService.addToFav(request);
	}
	
//	Add Component to Multiple Fav List
	@PostMapping("/addToMultipleFav")
	public Boolean addToMultipleFav(@RequestBody CompoentFavDto request) {
		return atsService.addToMultipleFav(request);
	}

//	Remove Component from Fav List
	@PostMapping("/removeFromFav")
	public Boolean removeFromFav(@RequestBody CompoentFavDto request) {
		return atsService.removeFromFav(request);
	}

//	send Correction Request
	@PostMapping("/sendCorrectionRequest")
	public Boolean sendCorrectionRequest(@RequestBody CorrectionRequest request) {
		return atsService.sendCorrectionRequest(request);
	}

//	Get Correction Request
	@PostMapping("/getCorrectionRequest")
	public CorrectionRequest getCorrectionRequest(@RequestBody Long modelId) {
		return atsService.getCorrectionRequest(modelId);
	}

//	Get Active Project Using the model
	@PostMapping("/getProjectList")
	public List<ProjectForLibrariesModel> getProjectList(@RequestBody Long modelId) {
		return atsService.getProjectList(modelId);
	}
	
//	Edit Favorite for Other Users
	@PostMapping("/getUsersForFavList")
	public List<UsersEntityResult> getUsersForFavList(@RequestBody Long[] ids) {
		return atsService.getUsersForFavList(ids[0], ids[1]);
	}
	
	@PostMapping("/checkExist")
	public ResponseEntity<?> checkExist(@RequestBody NewComponentRequest model){
		return atsService.checkExist(model);
	}

}
