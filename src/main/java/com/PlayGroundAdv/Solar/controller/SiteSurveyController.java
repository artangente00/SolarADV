package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.SiteSurveyCostumFieldEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyTextAreaFieldsEntity;
import com.PlayGroundAdv.Solar.model.CostumFieldModelResult;
import com.PlayGroundAdv.Solar.model.LayoutSketchArraysModel;
import com.PlayGroundAdv.Solar.model.NewSiteSurveyModel;
import com.PlayGroundAdv.Solar.model.PermitResponse;
import com.PlayGroundAdv.Solar.model.PermitSketchResults;
import com.PlayGroundAdv.Solar.model.SiteSurveyCostumFieldModel;
import com.PlayGroundAdv.Solar.model.SiteSurveyFieldSetting;
import com.PlayGroundAdv.Solar.model.SiteSurveyModel;
import com.PlayGroundAdv.Solar.model.SiteSurveyResult;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.service.project.SiteSurveyService;

@RestController
@RequestMapping("/siteSurvey")
public class SiteSurveyController {

	final SiteSurveyService siteSurveyService;
	final InverterRepository inverterRepo;
	final ModuleRepository moduleRepo;

	public SiteSurveyController(SiteSurveyService siteSurveyService, InverterRepository inverterRepo,
			ModuleRepository moduleRepo) {
		super();
		this.siteSurveyService = siteSurveyService;
		this.inverterRepo = inverterRepo;
		this.moduleRepo = moduleRepo;
	}

	@PostMapping("/addSiteSurvey")
	public SiteSurveyModel addSiteSurvey(@RequestBody NewSiteSurveyModel siteSurvey) {
		return siteSurveyService.addSiteSurvey(siteSurvey);
	}
	
	@PostMapping("/getTenSiteSurvey/{nbPage}")
	public List<SiteSurveyModel> getTenSiteSurvey(@RequestBody Long userID, @PathVariable Integer nbPage) {
		return siteSurveyService.getTenSiteSurvey(userID, nbPage);
	}

	@PostMapping("/getSiteSurvey")
	public SiteSurveyResult getSiteSurvey(@RequestBody Long siteSurveyID) {
		return siteSurveyService.getSiteSurvey(siteSurveyID);
	}

	@PostMapping("/saveReqField")
	public String saveSiteSurveyReqField(@RequestBody SiteSurveyFieldSetting siteSurveyReqFieldlog) {
		String result1 = "";
		String result2 = "";

		// 07-29-2019 : M.A : PR-684
		result1 = siteSurveyService.saveSiteSurveyReqField(siteSurveyReqFieldlog.getSiteSurveyReqFieldSettingEntity());
		if (result1.equals("Done")) {
			result2 = siteSurveyService
					.saveSiteSurveyReqFieldLogic(siteSurveyReqFieldlog.getSiteSurveyReqFieldLogicEntity());
			return result2;
		} else {
			return result1;
		}

	}

	@PostMapping("/saveAsDraft")
	public String saveAsDraft(@RequestBody SiteSurveyResult userID) {
		return siteSurveyService.saveAsDraft(userID);
	}

	@PostMapping("/submitSiteSurvey")
	public String submitSiteSurvey(@RequestBody SiteSurveyResult siteSurveyID) {
		return siteSurveyService.submitSiteSurvey(siteSurveyID);
	}

	@PostMapping("/cancelSiteSurvey")
	public String cancelSiteSurvey(@RequestBody Long siteSurveyID) {
		return siteSurveyService.cancelSiteSurvey(siteSurveyID);
	}

	@PostMapping("/getAllProject")
	public List<PermitResponse> getAllProject(@RequestBody Long userID) {
		return siteSurveyService.getAllProject(userID);
	}

	@PostMapping("/importSiteSurvey")
	public SiteSurveyModel importSiteSurvey(@RequestBody String siteSurveyID) {
		return siteSurveyService.SynchronizationSurvey(Long.valueOf(siteSurveyID.split(":")[0]),
				Long.valueOf(siteSurveyID.split(":")[1]));
	}

	@PostMapping("/uploadHomePicture")
	public String uploadHomePicture(@RequestParam("file") MultipartFile file,
			@RequestParam("siteSurveyID") String useriD, @RequestParam("fileName") String fileName)
			throws IOException {

		return siteSurveyService.uploadHomePicture(file, fileName, useriD);

	}

	@PostMapping("/uploadFormPicture")
	public String uploadFormPicture(@RequestParam("file") MultipartFile[] file,
			@RequestParam("siteSurveyID") String useriD, @RequestParam("siteSurveyType") String type,
			@RequestParam("fileName") String fileName) throws IOException {

		return siteSurveyService.uploadFormPicture(file, fileName, useriD, type);

	}

	@PostMapping("/getFiles")
	public String publishSiteSurvey(@RequestBody String[] fileInfo) {
		return siteSurveyService.getFiles(fileInfo[0], Long.valueOf(fileInfo[1]));
	}

	@PostMapping("/getHomePicture")
	public ResponseEntity<byte[]> getPic(@RequestBody String url) {
		return siteSurveyService.getPic(url);
	}

	@PostMapping("/getSiteSurveyReqLogField")
	public SiteSurveyFieldSetting getSiteSurveyReqLogField(@RequestBody String state) {
		return siteSurveyService.getSiteSurveyReqLogField(state);
	}

	// 07/08/2019/ CI : CR 2860 : save site survey array chart
	@PostMapping("/saveSiteSurveyArrays")
	public String saveSiteSurveyArrays(@RequestBody LayoutSketchArraysModel siteSurveyArrays) {
		return siteSurveyService.saveSiteSurveyArrays(siteSurveyArrays);
	}

	// 14/08/2019 : CI : CR2680 : get Array Chart for Roof Mount
	@PostMapping("/getSketch")
	public List<PermitSketchResults> getSketch(@RequestBody Long idPermit) {
		return siteSurveyService.getSketchBySiteSurvey(idPermit);
	}

	@PostMapping("/getInverterFavorites")
	public List<Inverters> getInverterFavorites(@RequestBody Long idsiteSurvey) {
		return inverterRepo.findBySiteSurvey(idsiteSurvey);
	}

	@PostMapping("/getModuleFavorites")
	public List<Cmodulev2> getModuleFavorites(@RequestBody Long idsiteSurvey) {
		return moduleRepo.findBySiteSurvey(idsiteSurvey);
	}

	// 07/08/2019/ CI : CR 2860 : save site survey array chart Non Roof Mount
	@PostMapping("/saveNonRoofSketchTable")
	public String saveNonRoofSketchTable(@RequestBody LayoutSketchArraysModel siteSurveyArrays) {
		return siteSurveyService.saveNonRoofSketchTable(siteSurveyArrays);
	}

	// 07/08/2019/ CI : CR 2860 : save site survey array chart for Pation Cover
	// Freestanding
	@PostMapping("/savePatioSketchTable")
	public String savePatioSketchTable(@RequestBody LayoutSketchArraysModel siteSurveyArrays) {
		return siteSurveyService.savePatioSketchTable(siteSurveyArrays);
	}

	// 16/08/2019 : CI : CR2680 : get Array Chart for Non Roof Mount
	@PostMapping("/getNonRoofMountSketch")
	public List<PermitSketchResults> getNonRoofMountSketchBySiteSurvey(@RequestBody Long idPermit) {
		return siteSurveyService.getNonRoofMountSketchBySiteSurvey(idPermit);
	}

	// 16/08/2019 : CI : CR2680 : get Array Chart for Patio Cover Freestanding
	@PostMapping("/getPatioSketch")
	public List<PermitSketchResults> getPatioSketchBySiteSurvey(@RequestBody Long idPermit) {
		return siteSurveyService.getPatioSketchBySiteSurvey(idPermit);
	}

	@PostMapping("/downloadPackage/{idSiteSurvey}")
	public ResponseEntity<byte[]> downloadPackage(@RequestBody String homeOwnName, @PathVariable Long idSiteSurvey) {
		return siteSurveyService.downloadPackage(homeOwnName, idSiteSurvey);
	}

	@PostMapping("/addCostumField")
	public String addCostumField(@RequestBody SiteSurveyCostumFieldModel costumField) {
		return siteSurveyService.addCostumField(costumField);
	}

	@PostMapping("/getCostumFieldListByState/{siteSurveyID}")
	public List<SiteSurveyTextAreaFieldsEntity> getCostumFieldListByState(@RequestBody String state,
			@PathVariable Long siteSurveyID) {
		return siteSurveyService.getCostumFieldListByState(state, siteSurveyID);
	}

	@PostMapping("/getSiteSurveyCostumFieldSetting")
	public List<SiteSurveyCostumFieldEntity> getSiteSurveyCostumFieldSetting(@RequestBody String state)
			{
		return siteSurveyService.getSiteSurveyCostumFieldSetting(state);
	}

	@PostMapping("/deleteCostumField")
	public String deleteCostumField(@RequestBody Integer costumFieldId) {
		return siteSurveyService.deleteCostumField(costumFieldId);
	}

	@PostMapping("/saveCostumFieldSetting")
	public String saveCostumFieldSetting(@RequestBody List<CostumFieldModelResult> changedCostumFieldList)
			{
		return siteSurveyService.saveCostumFieldSetting(changedCostumFieldList);
	}

	@PostMapping("/saveTextBoxFields/{siteSurveyID}")
	public String saveTextBoxFields(@RequestBody List<SiteSurveyTextAreaFieldsEntity> siteSurveyTextAreaFields,
			@PathVariable Long siteSurveyID) {
		return siteSurveyService.saveTextBoxFields(siteSurveyTextAreaFields, siteSurveyID);
	}

	@PostMapping("/checkProjectExisting")
	public String checkProjectExisting(@RequestBody NewSiteSurveyModel siteSurvey) {
		return siteSurveyService.checkProjectExisting(siteSurvey);
	}
}
