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
import com.PlayGroundAdv.Solar.model.AccountBuildEntityModel;
import com.PlayGroundAdv.Solar.model.AccountBuildModel;
import com.PlayGroundAdv.Solar.model.CustomizeSheetsModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.SheetPageRequest;
import com.PlayGroundAdv.Solar.service.sheets.AccountBuildAssumptionsService;
import com.PlayGroundAdv.Solar.service.sheets.CustomizeSheetsService;
@RestController
@RequestMapping("/customizeSheets")
public class CustomizeSheetsController {

	final CustomizeSheetsService customizeSheetsService;
	final AccountBuildAssumptionsService accountBuilService;
	public CustomizeSheetsController(CustomizeSheetsService customizeSheetsService,
			AccountBuildAssumptionsService accountBuilService) {
		super();
		this.customizeSheetsService = customizeSheetsService;
		this.accountBuilService = accountBuilService;
	}

	/*
	 * CR-2220 get S Seet Racking Mapping
	 */
	@PostMapping("/filterCustomize")
	public Page<CustomizeSheetsModel> searchCustomizeSheets(@RequestBody SheetPageRequest request)
			throws IOException {
		return customizeSheetsService.filter(request);
	}	
	

	@PostMapping("/filterAccounts")
	public Page<AccountBuildEntityModel> filter(@RequestBody SheetPageRequest request) throws IOException {
		return accountBuilService.filter(request);
	}
	
	@PostMapping("/addCustomizeSheet/{id}")
	public String addCustomizeSheet(@RequestBody CustomizeSheetsModel fileName, @PathVariable Long id)
			throws IOException, SQLException {

		return customizeSheetsService.addCustomizeSheet(fileName, id);
	}

	@PostMapping("/addAccountBuildSheet/{id}")
	public String addAccountBuildSheet(@RequestBody AccountBuildModel fileName, @PathVariable Long id)
			throws IOException, SQLException {

		return accountBuilService.addAccountBuildSheet(fileName, id);
	}

	@PostMapping("/upload/addLogic")
	public String addLogic(@RequestParam("file") MultipartFile file, @RequestParam("siteSurveyID") String useriD,
			@RequestParam("fileName") String fileName, @RequestParam("logicId") String logicId)
			throws IOException, SQLException {

		return customizeSheetsService.addLogic(file, logicId);
	}

	@PostMapping("/upload/addAccountBuildLogic")
	public String addSpacingLogic(@RequestParam("file") MultipartFile file, @RequestParam("siteSurveyID") String useriD,
			@RequestParam("fileName") String fileName, @RequestParam("logicId") String logicId)
			throws IOException, SQLException {

		return accountBuilService.addAccountBuildLogic(file, logicId);
	}

	@PostMapping("/getUsersNames")
	public List<UsersEntityResult> getSsheetspdfs() throws Exception {
		return customizeSheetsService.getUsersNames();
	}

	@PostMapping("/editCustomizeSheet/{id}")
	public String editCustomizeSheet(@RequestBody CustomizeSheetsModel fileName, @PathVariable Long id)
			throws IOException, SQLException {

		return customizeSheetsService.editCustomizeSheet(fileName, id);
	}

	@PostMapping("/editAccountBuildSheet/{id}")
	public String editAccountBuildSheet(@RequestBody AccountBuildModel fileName, @PathVariable Long id)
			throws IOException, SQLException {

		return accountBuilService.editAccountBuildSheet(fileName, id);
	}

	@PostMapping("/deletePermanetCustSheet")
	public String deletePermanetCustSheet(@RequestBody Long idSheet) throws IOException, SQLException {

		return customizeSheetsService.deletePermanetCustSheet(idSheet);
	}

	@PostMapping("/deletePermanetSheet")
	public String deletePermanetSheet(@RequestBody Long idSheet) throws IOException, SQLException {

		return accountBuilService.deletePermanetSheet(idSheet);
	}

	@PostMapping("/deleteCustomizeSheet/{idUser}")
	public String deleteRackingMapping(@RequestBody Long idRacking, @PathVariable Long idUser)
			throws IOException, SQLException {

		return customizeSheetsService.deleteCustomizeSheet(idRacking, idUser);
	}

	@PostMapping("/deleteAccountBuildSheet/{idUser}")
	public String deleteAccountBuildSheet(@RequestBody Long idSheet, @PathVariable Long idUser)
			throws IOException, SQLException {

		return accountBuilService.deleteAccountBuildSheet(idSheet, idUser);
	}

	@PostMapping("/restoreCustomizesheet")
	public String restoreCustomizesheet(@RequestBody Long SheetID) {
		return customizeSheetsService.restoreCustomizesheet(SheetID);
	}

	@PostMapping("/restoreAccountBuildsheet")
	public String restoreAccountBuildsheet(@RequestBody Long SheetID) {
		return accountBuilService.restoreAccountBuildsheet(SheetID);
	}

	@PostMapping("/downloadFile")
	public ResponseEntity<byte[]> downloadRackingSheet(@RequestBody String url) {

		Path path = Paths.get("Constants.rapportPlansetFolderUrl");
		if (new File(Constants.rapportPlansetFolderUrl + "PlansetCustomizeSheets/" + url).exists()) {
			path = Paths.get(Constants.rapportPlansetFolderUrl + "PlansetCustomizeSheets/" + url);
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

	@PostMapping("/downloadAccountBuildFile")
	public ResponseEntity<byte[]> downloadAccountBuildSheet(@RequestBody String url) {

		Path path = Paths.get("Constants.rapportPlansetFolderUrl");
		if (new File(Constants.rapportPlansetFolderUrl + "AccountBuildSheets/" + url).exists()) {
			path = Paths.get(Constants.rapportPlansetFolderUrl + "AccountBuildSheets/" + url);
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

	@PostMapping("/getFile")
	public ResponseEntity<byte[]> getFile(@RequestBody String url) {

		Path path = Paths.get("Constants.rapportPlansetFolderUrl");

		if (new File(Constants.rapportPlansetFolderUrl + "PlansetCustomizeSheets/" + url).exists()) {
			path = Paths.get(Constants.rapportPlansetFolderUrl + "PlansetCustomizeSheets/" + url);
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

	@PostMapping("/getAccountBuildFile")
	public ResponseEntity<byte[]> getAccountBuildFile(@RequestBody String url) {

		Path path = Paths.get("Constants.rapportPlansetFolderUrl");

		if (new File(Constants.rapportPlansetFolderUrl + "AccountBuildSheets/" + url).exists()) {
			path = Paths.get(Constants.rapportPlansetFolderUrl + "AccountBuildSheets/" + url);
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
