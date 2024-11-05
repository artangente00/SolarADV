package com.PlayGroundAdv.Solar.UploadPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.PermitFileUploadResult;
import com.PlayGroundAdv.Solar.model.PermitPlansetUploadResult;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetDrafterMappingService;

@Controller
@RequestMapping(produces = { "application/json" })
public class UploadFileController {

	final UploadService uploadService;
	final PathRepository pathRepo;
	final PermitRepository permitRepo;
	final PlansetDrafterMappingService plansetDrafterMappingService;

	public UploadFileController(UploadService uploadService, PathRepository pathRepo, PermitRepository permitRepo,
			PlansetDrafterMappingService plansetDrafterMappingService) {
		super();
		this.uploadService = uploadService;
		this.pathRepo = pathRepo;
		this.permitRepo = permitRepo;
		this.plansetDrafterMappingService = plansetDrafterMappingService;
	}

	/*
	 * File Uploading Multiple Files
	 */

	@PostMapping("/uploadFile")
	public ResponseEntity<List<PermitFileUploadResult>> uploadFile(
			@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles, @RequestParam("idFile") String idFile,
			@RequestParam("idPermit") Long idPermit, @RequestParam("folderName") String folderName) throws IOException {

		return new ResponseEntity<List<PermitFileUploadResult>>(
				uploadService.addProjectsFile(uploadedFiles, idFile, idPermit), HttpStatus.OK);

	}

	/*
	 * Planset Uploading
	 */
	@PostMapping("/uploadPlanset")
	public ResponseEntity<PermitPlansetUploadResult> uploadPlanset(@RequestParam("file") MultipartFile file,
			@RequestParam("idFile") String idFile, @RequestParam("idPermit") Long idPermit,
			@RequestParam("idUserConn") String idUserConn, @RequestParam("folderName") String folderName)
			throws IOException {

		return new ResponseEntity<>(
				uploadService.addPlansetsFile(file, idFile, idPermit, folderName), HttpStatus.OK);
	}

	/** Permit file **/
	@PostMapping("/uploadPlansetPermit")
	public ResponseEntity<String> uploadPlansetPermit(@RequestParam("uploadedFiles") MultipartFile file,
			@RequestParam("idPermit") Long idPermit, @RequestParam("release") String release) {
		String msgUpload = uploadService.uploadPlansetPermit(file, idPermit, release);
		return new ResponseEntity<String>(msgUpload, HttpStatus.OK);
	}

	/*
	 * Files Uploading Additional Info Multiple Files
	 */
	@PostMapping("/uploadAdditionalInfoFile")
	public ResponseEntity<String> uploadAdditionalInfoFile(@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles,
			@RequestParam("idPermit") Long idPermit, @RequestParam("folderName") String folderName) throws IOException {

		String msgUpload = uploadService.putAdditionalInfoFiles(uploadedFiles, idPermit, folderName);
		String msg = "{\"msg\":\"" + msgUpload + "\" }";
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	/*
	 * Files Uploading Additional Info Multiple Files
	 */
	@PostMapping("/uploadCustomFile")
	public ResponseEntity<String> uploadCustomFile(@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles,
			@RequestParam("idPermit") Long idPermit, @RequestParam("folderName") Long folderName) {
		String msgUpload = uploadService.uploadCustomFile(uploadedFiles, idPermit, folderName);
		String msg = "{\"msg\":\"" + msgUpload + "\" }";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	/*
	 * Files Uploading Additional Info Multiple Files
	 */
	@PostMapping("/uploadLayoutSketchFile")
	public ResponseEntity<String> uploadLayoutSketchFile(@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles,
			@RequestParam("idPermit") Long idPermit, @RequestParam("fileNumber") String fileNumber,
			@RequestParam("folderName") String folderName) throws IOException {

		String msgUpload = uploadService.putLayoutSketchFiles(uploadedFiles, idPermit, fileNumber, folderName);
		String msg = "{\"msg\":\"" + msgUpload + "\" }";
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PostMapping("/uploadBOSFile")
	public ResponseEntity<String> postBOSFiles(@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles,
			@RequestParam("idPermit") Long idPermit, @RequestParam("folderName") String folderName) throws IOException {
		String msgUpload = uploadService.putBOSFiles(uploadedFiles, idPermit, folderName);
		String msg = "{\"msg\":\"" + msgUpload + "\" }";
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PostMapping("/uploadUtilityBillFiles")
	public ResponseEntity<String> postUtilityBillFiles(@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles,
			@RequestParam("idPermit") Long idPermit, @RequestParam("folderName") String folderName) throws IOException {
		String msgUpload = uploadService.postUtilityBillFiles(uploadedFiles, idPermit, folderName);
		String msg = "{\"msg\":\"" + msgUpload + "\" }";
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PostMapping("/uploadImage")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
			@RequestParam("fileName") String fileName, @RequestParam("idPermitInfo") Long idPermitInfo)
			throws ResourceNotFoundException, IOException, SQLException {
		PermitEntity project = permitRepo.findById(idPermitInfo)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idPermitInfo));
		String msgUpload = uploadService.putDrafterFileSpring(file, fileName, idPermitInfo, project);
		String msg = "{\"msg\":\"" + msgUpload + "\" }";
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PostMapping("/uploadImageRFI")
	public ResponseEntity<String> uploadRFIImage(@RequestParam("file") MultipartFile file) throws IOException {
		String msgUpload = uploadService.putRFIFile(file);
		return new ResponseEntity<>(msgUpload, HttpStatus.OK);
	}

	@PostMapping("/uploadDrafterSheet")
	public @ResponseBody String uploadDrafterSheet(@RequestParam("file") MultipartFile file,
			@RequestParam("fileName") String fileName, @RequestParam("idPermitInfo") Long idPermitInfo,
			@RequestParam("folderName") String folderName) throws ResourceNotFoundException, IOException, SQLException {
		PermitEntity project = permitRepo.findById(idPermitInfo)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idPermitInfo));
		return uploadService.uploadDrafterSheet(file, idPermitInfo, folderName, project);

	}

	@PostMapping("/uploadlogoOrSignature")
	public ResponseEntity<String> uploadLogoAndSig(@RequestParam("file") MultipartFile file,
			@RequestParam("useriD") Long useriD, @RequestParam("fileName") String fileName) throws Exception {

		String msgUpload = uploadService.uploadLogoAndSignature(file, fileName, useriD);
		return new ResponseEntity<String>(msgUpload, HttpStatus.OK);

	}

	@PostMapping("/downloadFile")
	public ResponseEntity<byte[]> downloadFile(@RequestBody String url) {

		Path path = Paths.get(pathRepo.findFilePath() + url);
		byte[] contents = null;
		try {
			contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(contents, headers, org.springframework.http.HttpStatus.OK);
	}

	// CR-3230 mGenerate planset without drafts
	@PostMapping("/downloadPlansetFileWithoutDrafts")
	public ResponseEntity<byte[]> downloadFile(@RequestBody Long permitId) {
		return plansetDrafterMappingService.downloadPlansetFileWithoutDrafts(permitId);
	}

	// CR-3230 map drafts to the planset and create a version with drafts
	@PostMapping("/downloadPlansetFileWithDrafts")
	public ResponseEntity<byte[]> downloadPlansetFileWithDrafts(@RequestBody Long permitId) {
		return plansetDrafterMappingService.downloadPlansetFileWithDrafts(permitId);
	}

	@PostMapping("/uploadConductorSizeNotes")
	public ResponseEntity<String> uploadConductorSizeNotes(@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles,
			@RequestParam("idPermit") Long idPermit, @RequestParam("notes") String notes,
			@RequestParam("folderName") String folderName) throws ResourceNotFoundException, IOException {
		PermitEntity project = permitRepo.findById(idPermit)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idPermit));
		String msgUpload = uploadService.uploadConductorSizeNotes(uploadedFiles, idPermit, notes, folderName, project);
		String msg = "{\"msg\":\"" + msgUpload + "\" }";
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@PostMapping("/uploadEssSpecificDetails")
	public ResponseEntity<?> uploadEssSpecificDetails(@RequestParam("uploadedFiles") MultipartFile[] uploadedFiles,
			@RequestParam("idPermit") Long idPermit) throws ResourceNotFoundException, IOException {
		List<String> msgUpload = uploadService.UploadEssSpecificDetails(uploadedFiles, idPermit);
		return new ResponseEntity<>(msgUpload, HttpStatus.OK);
	}


}
