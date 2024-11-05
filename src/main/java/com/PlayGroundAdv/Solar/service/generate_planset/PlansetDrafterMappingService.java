package com.PlayGroundAdv.Solar.service.generate_planset;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.drafter_details.CleanDrafterSheets;
import com.PlayGroundAdv.Solar.service.generate_planset.drafter_details.DrafterSheetsMapping;

@Service
@Transactional
public class PlansetDrafterMappingService {

	final PathRepository pathRepo;
	final DrafterSheetsMapping drafterMapping;
	final CleanDrafterSheets cleanDrafterSheets;
	final PermitRepository permitRepo;

	public PlansetDrafterMappingService(PathRepository pathRepo, DrafterSheetsMapping drafterMapping,
			CleanDrafterSheets cleanDrafterSheets, PermitRepository permitRepo) {
		super();
		this.pathRepo = pathRepo;
		this.drafterMapping = drafterMapping;
		this.cleanDrafterSheets = cleanDrafterSheets;
		this.permitRepo = permitRepo;
	}

	// CR-3230 map drafts to the planset and create a version with drafts
	public ResponseEntity<byte[]> downloadPlansetFileWithDrafts(Long permitId) {

		byte[] contents = null;
		String filePath = getfilesPath();
		try {
			Date dateSubmit = permitRepo.findSubmissionDateByID(permitId);
			Date dateCR = new SimpleDateFormat("dd/MM/yyyy").parse("27/05/2020");
			String plansetPath= filePath + "Rapport/SampleResult" + permitId + ".pdf";
			if (dateSubmit != null && dateSubmit.after(dateCR)) {
				PermitEntity permit = permitRepo.findById(permitId).orElse(null);
				String draftPath = filePath + "Rapport/SampleResult-copy-" + permitId + ".pdf";
				Path path = Paths.get(draftPath);
				File draftFile = new File(draftPath);
				if (draftFile.exists()) {
					cleanUp(path);
					draftFile = new File(draftPath);
				}
				if (permit.getPlansetVersion() != null && permit.getPlansetVersion() > 1) {
					String cleanPath = filePath + "Rapport/SampleResult-copy-" + permitId + "-cleaned.pdf";
					Path cleanpath = Paths.get(cleanPath);
					File cleanFile = new File(cleanPath);
					if (cleanFile.exists()) {
						cleanUp(cleanpath);
						cleanFile = new File(cleanPath);
					}
					cleanDrafterSheets.cleanDraftSheet(getfilesPath(), permitId, cleanFile);
					drafterMapping.drafterMapping(filePath, permitId, draftFile,cleanPath);
					cleanUp(cleanpath);
				}else {
					drafterMapping.drafterMapping(filePath, permitId, draftFile,plansetPath);
				}
				contents = Files.readAllBytes(path);
				cleanUp(path);
			} else {
				Path path = Paths.get(plansetPath);
				contents = Files.readAllBytes(path);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders headers = getHeaders();
		return new ResponseEntity<>(contents, headers, org.springframework.http.HttpStatus.OK);

	}

	// CR-3230 map drafts to the planset and create a version without drafts
	public ResponseEntity<byte[]> downloadPlansetFileWithoutDrafts(Long permitId) {

		try {
			byte[] contents = null;
			String url = getfilesPath();

			Date dateCR = new SimpleDateFormat("dd/MM/yyyy").parse("27/05/2020");
			PermitEntity permit = permitRepo.findById(permitId).orElse(null);
			if (permit != null && ((permit.getDateOfSubmitPermit() != null && permit.getDateOfSubmitPermit().before(dateCR)) || permit.getPlansetVersion()  > 1)) {
				// create a new copy of the permit file
				String filePath = url + "Rapport/SampleResult-copy-" + permitId + ".pdf";
				Path path = Paths.get(filePath);
				File fileCopy = new File(filePath);
				if (fileCopy.exists()) {
					cleanUp(path);
					fileCopy = new File(filePath);
				}
				cleanDrafterSheets.cleanDraftSheet(url, permitId, fileCopy);
				contents = Files.readAllBytes(path);
				cleanUp(path);
			} else {
				Path path = Paths.get(url + "Rapport/SampleResult" + permitId + ".pdf");
				contents = Files.readAllBytes(path);
			}

			HttpHeaders headers = getHeaders();
			return new ResponseEntity<>(contents, headers, org.springframework.http.HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, org.springframework.http.HttpStatus.OK);
		}

	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		headers.setContentDispositionFormData("output.xls", "output.xls");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return headers;
	}

	public String getfilesPath() {
		try {
			return pathRepo.findFilePath();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void cleanUp(Path path) throws IOException {
		Files.delete(path);
	}
}
