package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.model.SRsheetsModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageRequest;
import com.PlayGroundAdv.Solar.model.libraries.ComponentPageTLDSheet;
import com.PlayGroundAdv.Solar.model.sheets.TLDByProjectModel;
import com.PlayGroundAdv.Solar.service.sheets.TLDSheetLibraryService;

@RestController
@RequestMapping("/tldSheetManagement")
public class TLDSheetManagementController {

	final TLDSheetLibraryService tLDSheetLibraryService;

	public TLDSheetManagementController(TLDSheetLibraryService tLDSheetLibraryService) {
		super();
		this.tLDSheetLibraryService = tLDSheetLibraryService;
	}

	
	@PostMapping("/getAllTLDsheet")
	public Page<SRsheetsModel> getAllTLDsheet(@RequestBody ComponentPageRequest request) {
		return tLDSheetLibraryService.getAllTLDsheet(request);
	}

	@PostMapping("/searchTLDsheet")
	public Page<SRsheetsModel> searchTLDsheet(@RequestBody ComponentPageTLDSheet request) {
		return tLDSheetLibraryService.searchTLDsheet(request);
	}

	@PostMapping("/searchTLDsheetList")
	public List<TLDByProjectModel> searchTLDsheetList(@RequestBody String deviceToIncorporate) {
		return tLDSheetLibraryService.searchTLDsheetList(deviceToIncorporate);
	}
	
	@PostMapping("/deleteTLDsheet/{userID}")
	public String deleteTLDsheet(@RequestBody Long sheetID, @PathVariable Long userID) {
		return tLDSheetLibraryService.deleteTLDsheet(sheetID, userID);
	}

	@PostMapping("/downloadFileTLDsheet")
	public ResponseEntity<byte[]> downloadFileTLDsheet(@RequestBody String url) {
		return tLDSheetLibraryService.downloadFileTLDsheet(url);
	}

	@PostMapping("/getFileTLDsheet")
	public ResponseEntity<byte[]> getFileTLDsheet(@RequestBody String url) {

		return tLDSheetLibraryService.getFileTLDsheet(url);
	}

	@PostMapping("/uploadFileTLDsheet")
	public String uploadHomePictureTLDsheet(@RequestParam("file") MultipartFile file,
			@RequestParam("siteSurveyID") String useriD, @RequestParam("fileName") String fileName)
			throws IOException {

		return tLDSheetLibraryService.uploadHomePicture(file, fileName, useriD);
	}

	@PostMapping("/upload/editTLDsheet")
	public String editTLDsheet(@RequestParam("file") MultipartFile file, @RequestParam("siteSurveyID") String useriD,
			@RequestParam("fileName") String fileName, @RequestParam("fileID") String fileID) {
		return tLDSheetLibraryService.editTLDsheet(file, fileName, useriD, fileID);
	}

	@PostMapping("/restoreTLDsheet")
	public String restoreTLDsheet(@RequestBody Long sheetID) {
		return tLDSheetLibraryService.restoreTLDsheet(sheetID);
	}

	@PostMapping("/editTLDsheetNotification")
	public String editTLDsheetNotification(@RequestBody String[] rSheetInfo) {
		return tLDSheetLibraryService.editTLDsheetNotification(Long.valueOf(rSheetInfo[0]), rSheetInfo[1]);
	}

	@PostMapping("/deleteTLDsheetNotification")
	public String deleteTLDsheetNotification(@RequestBody String[] rSheetInfo) {
		return tLDSheetLibraryService.deleteTLDsheetNotification(Long.valueOf(rSheetInfo[0]), rSheetInfo[1]);
	}

	@PostMapping("/searchShotList")
	public List<TLDByProjectModel> searchShotList(@RequestBody GetPermitByIdResult permitModel) {
		return tLDSheetLibraryService.searchShotList(permitModel);
	}

	@PostMapping("/allowedSubNames")
	public List<String> allowedSubNames() {
		return tLDSheetLibraryService.allowedSubNames();
	}
}
