package com.PlayGroundAdv.Solar.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.RsheetsLibraryEntity;
import com.PlayGroundAdv.Solar.model.SRsheetsModel;
import com.PlayGroundAdv.Solar.model.libraries.PlansetDto;
import com.PlayGroundAdv.Solar.model.libraries.SheetPageRequest;
import com.PlayGroundAdv.Solar.service.libraries.EngineersLibraryService;
import com.PlayGroundAdv.Solar.service.sheets.PlansetSheetLibrary;
import com.PlayGroundAdv.Solar.service.sheets.RsheetLibraryService;
import com.PlayGroundAdv.Solar.service.sheets.SsheetLibraryService;
import com.PlayGroundAdv.Solar.service.sheets.TLDSheetLibraryService;

@RestController
@RequestMapping("/sheetsManagement")
public class SheetsManagementController {

	final RsheetLibraryService rsheetLibraryService;
	final SsheetLibraryService ssheetLibraryService;
	final PlansetSheetLibrary plansetSheetLibrary;
	final EngineersLibraryService getEngineersLibraryService;
	final TLDSheetLibraryService tLDSheetLibraryService;

	public SheetsManagementController(RsheetLibraryService rsheetLibraryService,
			SsheetLibraryService ssheetLibraryService, PlansetSheetLibrary plansetSheetLibrary,
			EngineersLibraryService getEngineersLibraryService, TLDSheetLibraryService tLDSheetLibraryService) {
		super();
		this.rsheetLibraryService = rsheetLibraryService;
		this.ssheetLibraryService = ssheetLibraryService;
		this.plansetSheetLibrary = plansetSheetLibrary;
		this.getEngineersLibraryService = getEngineersLibraryService;
		this.tLDSheetLibraryService = tLDSheetLibraryService;
	}
	
	@PostMapping("/filterRSheet")
	public Page<SRsheetsModel> filterRSheet(@RequestBody SheetPageRequest request) {
		return rsheetLibraryService.filter(request);
	}

	// S.B CR-2419 Revision get closest R - Sheets
	@PostMapping("/getclosestRsheets")
	public List<RsheetsLibraryEntity> getclosestRsheets(@RequestBody Long groundRailracking) {
		return rsheetLibraryService.getclosestRsheets(groundRailracking);
	}
	
	@PostMapping("/filterSSheet")
	public Page<SRsheetsModel> filterSSheet(@RequestBody SheetPageRequest request) {
		return ssheetLibraryService.filter(request);
	}

	@PostMapping("/deleteSheet/{userID}")
	public String deleteSheet(@RequestBody Long sheetId, @PathVariable Long userID) {
		return rsheetLibraryService.deleteSheet(sheetId, userID);
	}

	@PostMapping("/deleteSSheet/{userID}")
	public String deleteSSheet(@RequestBody Long sheetId, @PathVariable Long userID) {
		return ssheetLibraryService.deleteSSheet(sheetId, userID);
	}

	@PostMapping("/downloadFile")
	public ResponseEntity<byte[]> downloadFile(@RequestBody String url) {

		Path path = Paths.get(Constants.rapportRSheetFolderUrl + "ReferenceSheets/" + url);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		String filename = "output.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
	}

	@PostMapping("/downloadFileSsheet")
	public ResponseEntity<byte[]> downloadFileSsheet(@RequestBody String url) {

		Path path = Paths.get("Constants.rapportS200FolderUrl");
		if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/" + url);
		}
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		String filename = "output.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
	}

	@PostMapping("/getFile")
	public ResponseEntity<byte[]> getFile(@RequestBody String url) {

		Path path = Paths.get(Constants.rapportRSheetFolderUrl + "ReferenceSheets/" + url);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
	}

	@PostMapping("/getFileSsheet")
	public ResponseEntity<byte[]> getFileSsheet(@RequestBody String url) {
		Path path = Paths.get("Constants.rapportS200FolderUrl");
		if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/" + url);
		}
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
	}

	@PostMapping("/uploadFile")
	public String uploadHomePicture(@RequestParam("file") MultipartFile file, @RequestParam("siteSurveyID") Long useriD,
			@RequestParam("fileName") String fileName) throws IOException {

		return rsheetLibraryService.uploadRSheet(file, fileName, useriD);

	}

	@PostMapping("/uploadFileSsheet")
	public String uploadHomePictureSsheet(@RequestParam("file") MultipartFile file,
			@RequestParam("siteSurveyID") String useriD, @RequestParam("fileName") String fileName)
			throws IOException {

		return ssheetLibraryService.uploadHomePicture(file, fileName, useriD);

	}

	@PostMapping("/upload/editRSheet")
	public String editRSheet(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId,
			@RequestParam("fileName") String fileName, @RequestParam("fileId") Long fileId,
			@RequestParam("lastName") String lastName) {

		return rsheetLibraryService.editRSheet(file, fileName, userId, fileId, lastName);

	}

	@PostMapping("/restoreRsheet")
	public String restoreRsheet(@RequestBody Long sheetID) {
		return rsheetLibraryService.restoreRsheet(sheetID);
	}

	@PostMapping("/upload/editSSheet")
	public String editSSheet(@RequestParam("file") MultipartFile file, @RequestParam("siteSurveyID") Long useriD,
			@RequestParam("fileName") String fileName, @RequestParam("fileID") Long fileID) {

		return ssheetLibraryService.editSSheet(file, fileName, useriD, fileID);

	}

	@PostMapping("/restoreSsheet")
	public String restoreSsheet(@RequestBody Long sheetID) {
		return ssheetLibraryService.restoreSsheet(sheetID);
	}

	// CR2196
	@PostMapping("/filterPlansetSheets")
	public Page<PlansetDto> getPlansetSheets(@RequestBody SheetPageRequest request) {
		return plansetSheetLibrary.filter(request);
	}	

	// CR-2196
	@PostMapping("/downloadPlansetFile")
	public ResponseEntity<byte[]> downloadPlansetFile(@RequestBody String[] fileInfo) {
		String pdfPath = "";
		if (fileInfo[0] != null && fileInfo[0].equals("NEC")) {
			pdfPath = "NEC-PDF/";
		}
		Path path = Paths.get(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-" + fileInfo[1]);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		String filename = "output.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
	}

	// CR-2196
	@PostMapping("/getPlansetFile")
	public ResponseEntity<byte[]> getPlansetFile(@RequestBody String[] fileInfo) {

		String pdfPath = "";
		if (fileInfo[0].equals("NEC")) {
			pdfPath = "NEC-PDF/";
		}
		Path path = Paths.get(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-" + fileInfo[1]);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
	}

	// CR-2196
	@PostMapping("/upload/editPlansetSheets")
	public String editPlansetSheets(@RequestParam("file") MultipartFile file, @RequestParam("idUser") Long useriD,
			@RequestParam("fileName") String fileName, @RequestParam("fileID") String fileID,
			@RequestParam("comment") String comment, @RequestParam("ipAdress") String ipAdress,
			@RequestParam("timeZone") String timeZone, @RequestParam("numTab") String numTab,
			@RequestParam("openDate") String openDate, HttpServletRequest request) {
		// 06-12-2019: M.A :CR-2568
		return plansetSheetLibrary.editPlansetSheets(file, fileName, useriD, fileID, comment, ipAdress, timeZone,
				numTab, openDate, request.getSession().getId());

	}

	@PostMapping("/getRsheet")
	public RsheetsLibraryEntity getRsheet(@RequestBody Long idRsheet) {
		return rsheetLibraryService.getRsheet(idRsheet);
	}

	@PostMapping("/addRsheet/{idRsheet}")
	public String addRsheet(@RequestBody RsheetsLibraryEntity rsheetName, @PathVariable Long idRsheet)
			throws IOException {
		return rsheetLibraryService.addRsheet(rsheetName, idRsheet);
	}

	@PostMapping("/updateRSheet/{idRsheet}")
	public String updateRSheet(@RequestBody RsheetsLibraryEntity rsheetName, @PathVariable Long idRsheet)
			throws IOException {
		return rsheetLibraryService.updateRSheet(rsheetName, idRsheet);
	}
}
