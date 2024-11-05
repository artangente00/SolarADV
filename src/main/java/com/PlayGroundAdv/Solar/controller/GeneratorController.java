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
import com.PlayGroundAdv.Solar.model.libraries.GeneratorDto;
import com.PlayGroundAdv.Solar.model.libraries.GeneratorDtoResult;
import com.PlayGroundAdv.Solar.model.libraries.NewComponentRequest;
import com.PlayGroundAdv.Solar.service.libraries.GeneratorService;

@RestController
@RequestMapping(value="/generator")
public class GeneratorController {

	final GeneratorService generatorService;

	public GeneratorController(GeneratorService generatorService) {
		super();
		this.generatorService = generatorService;
	}
	
	@PostMapping("/getList")
	public Page<ComponentResult> getList(@RequestBody ComponentPageRequest request){
		return generatorService.getList(request);
	}
	
	@PostMapping("/searchList")
	public Page<ComponentResult> searchList(@RequestBody ComponentPageRequest request){
		return generatorService.searchList(request);
	}
	
	@PostMapping("/getById")
	public GeneratorDtoResult getById(@RequestBody Long modelId){
		return generatorService.getById(modelId);
	}

//	Add Component
	@PostMapping("/addComponent")
	public FavoriteListDto addComponent(@RequestBody GeneratorDto request) {
		return generatorService.addComponent(request);
	}

//	Edit Component
	@PostMapping("/editComponent")
	public ComponentResult editComponent(@RequestBody GeneratorDto request) {
		return generatorService.editComponent(request);
	}

//	Deleted Component
	@PostMapping("/deleteComponent")
	public Boolean deleteComponent(@RequestBody NewComponentRequest request) {
		return generatorService.deleteComponent(request);
	}

//	Restore Component
	@PostMapping("/restoreComponent")
	public String restoreComponent(@RequestBody NewComponentRequest request) {
		return generatorService.restoreComponent(request);
	}

//	Add Component to Fav List
	@PostMapping("/addToFav")
	public Boolean addToFav(@RequestBody CompoentFavDto request) {
		return generatorService.addToFav(request);
	}
	
//	Add Component to Multiple Fav List
	@PostMapping("/addToMultipleFav")
	public Boolean addToMultipleFav(@RequestBody CompoentFavDto request) {
		return generatorService.addToMultipleFav(request);
	}

//	Remove Component from Fav List
	@PostMapping("/removeFromFav")
	public Boolean removeFromFav(@RequestBody CompoentFavDto request) {
		return generatorService.removeFromFav(request);
	}

//	send Correction Request
	@PostMapping("/sendCorrectionRequest")
	public Boolean sendCorrectionRequest(@RequestBody CorrectionRequest request) {
		return generatorService.sendCorrectionRequest(request);
	}
	
//	Get Correction Request
	@PostMapping("/getCorrectionRequest")
	public CorrectionRequest getCorrectionRequest(@RequestBody Long modelId) {
		return generatorService.getCorrectionRequest(modelId);
	}

//	Get Active Project Using the model
	@PostMapping("/getProjectList")
	public List<ProjectForLibrariesModel> getProjectList(@RequestBody Long modelId) {
		return generatorService.getProjectList(modelId);
	}
	
//	Edit Favorite for Other Users
	@PostMapping("/getUsersForFavList")
	public List<UsersEntityResult> getUsersForFavList(@RequestBody Long[] ids) {
		return generatorService.getUsersForFavList(ids[0], ids[1]);
	}
	
	@PostMapping("/checkExist")
	public ResponseEntity<?> checkExist(@RequestBody NewComponentRequest model){
		return generatorService.checkExist(model);
	}
}
