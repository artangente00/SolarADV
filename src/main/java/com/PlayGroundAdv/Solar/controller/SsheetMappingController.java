package com.PlayGroundAdv.Solar.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

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
import com.PlayGroundAdv.Solar.entity.SsheetLibraryEntity;
import com.PlayGroundAdv.Solar.model.SpacingDetailsMappingModel;
import com.PlayGroundAdv.Solar.model.SsheetRackingModel;
import com.PlayGroundAdv.Solar.model.SsheetSpacingMappingModel;

import com.PlayGroundAdv.Solar.model.libraries.RaikingPageRequest;
import com.PlayGroundAdv.Solar.service.sheets.SsheetRackingmapping;

@RestController
@RequestMapping("/sSheetMapping")
public class SsheetMappingController {

	final SsheetRackingmapping ssheetRackingmapping;

	public SsheetMappingController(SsheetRackingmapping ssheetRackingmapping) {
		super();
		this.ssheetRackingmapping = ssheetRackingmapping;
	}

	/*
	 * CR-2220 get S Seet Racking Mapping
	 */
	@PostMapping("/filterRaiking")
	public Page<SsheetRackingModel> filterRaiking(@RequestBody RaikingPageRequest request) throws Exception {
		return ssheetRackingmapping.filterRaiking(request);
	}
	
	@PostMapping("/filterSpacing")
	public Page<SsheetSpacingMappingModel> filterSpacing(@RequestBody RaikingPageRequest request) throws Exception {
		return ssheetRackingmapping.filterSpacing(request);
	}

	@PostMapping("/postData/{id}")
	public String uploadHomePictureSsheet(@RequestBody SsheetRackingModel fileName, @PathVariable Long id)
			throws IOException, SQLException {

		return ssheetRackingmapping.uploadHomePicture(fileName, id);
	}

	@PostMapping("/addSpacingLogic/{id}")
	public String uploadHomePictureSsheetSpacing(@RequestBody SpacingDetailsMappingModel fileName,
			@PathVariable Long id) throws IOException, SQLException {

		return ssheetRackingmapping.addSpacingLogic(fileName, id);
	}

	@PostMapping("/upload/addLogic")
	public String addLogic(@RequestParam("file") MultipartFile file, @RequestParam("siteSurveyID") String useriD,
			@RequestParam("fileName") String fileName, @RequestParam("uploadNewSheet") String uploadnewSheet)
			throws IOException, SQLException {

		return ssheetRackingmapping.addLogic(file, useriD, fileName, uploadnewSheet);
	}

	@PostMapping("/upload/addNewSSheet")
	public String addSpacingLogic(@RequestParam("file") MultipartFile file, @RequestParam("siteSurveyID") String useriD,
			@RequestParam("fileName") String fileName, @RequestParam("uploadNewSheet") String uploadnewSheet)
			throws IOException, SQLException {

		return ssheetRackingmapping.addNewSSheet(file, useriD, fileName, uploadnewSheet);
	}

	@PostMapping("/getSsheetspdfs")
	public List<SsheetLibraryEntity> getSsheetspdfs() throws Exception {
		return ssheetRackingmapping.getSsheetspdfs();
	}

	@PostMapping("/editRackingMapping/{idUser}")
	public String editRackingMapping(@PathVariable Long idUser, @RequestBody SsheetRackingModel fileName) {
		return ssheetRackingmapping.editRackingMapping(idUser, fileName);
	}

	@PostMapping("/getIdSheetFromLibrary")
	public Long getIdSheetFromLibrary(@RequestBody String fileName) throws IOException, SQLException {

		return ssheetRackingmapping.getIdSheetFromLibrary(fileName);
	}

	@PostMapping("/deleteRackingMapping/{idUser}")
	public String deleteRackingMapping(@RequestBody Long idRacking, @PathVariable Long idUser)
			throws IOException, SQLException {

		return ssheetRackingmapping.deleteRackingMapping(idRacking, idUser);
	}

	@PostMapping("/deleteSpacingMapping/{idUser}")
	public String deleteSpacingMapping(@RequestBody Long idRacking, @PathVariable Long idUser)
			throws IOException, SQLException {

		return ssheetRackingmapping.deleteSpacingMapping(idRacking, idUser);
	}

	@PostMapping("/editSpacingMapping/{id}/{idSsheet}")
	public String editSpacingMapping(@RequestBody SpacingDetailsMappingModel fileName, @PathVariable Long id,
			@PathVariable Long idSsheet) throws IOException, SQLException {

		return ssheetRackingmapping.editSpacingMapping(fileName, id, idSsheet);
	}

	@PostMapping("/downloadRackingSheet")
	public ResponseEntity<byte[]> downloadRackingSheet(@RequestBody String url) {

		Path path = Paths.get("Constants.rapportS200FolderUrl");
		if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url);
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
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
		return response;
	}

	@PostMapping("/getRackingMappingsheet")
	public ResponseEntity<byte[]> getRackingMappingsheet(@RequestBody String url) {

		Path path = Paths.get("Constants.rapportS200FolderUrl");

		if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + url);
		} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/" + url).exists()) {
			path = Paths.get(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/" + url);
		}
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
		return response;
	}

}
