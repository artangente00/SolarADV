package com.PlayGroundAdv.Solar.service.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.NotAllowedRackingNotes;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.repositories.NotAllowedRackingNotesRepository;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;

@Service
public class ProjectNotAllowedRackingNote {

	final NotAllowedRackingNotesRepository rackingNotesRepo;
	final PermitRepository permitRepo;
	final PathRepository pathRepo;

	public ProjectNotAllowedRackingNote(NotAllowedRackingNotesRepository rackingNotesRepo, PermitRepository permitRepo,
			PathRepository pathRepo) {
		super();
		this.rackingNotesRepo = rackingNotesRepo;
		this.permitRepo = permitRepo;
		this.pathRepo = pathRepo;
	}

	public void insertRackingNotetoADV(String note, Long projectId, String fileName) {
		try {
			PermitEntity project = permitRepo.findById(projectId).orElse(null);
			if (project != null) {
				NotAllowedRackingNotes projectNotes = rackingNotesRepo.findByProjectId(projectId);
				if (projectNotes == null) {
					projectNotes = new NotAllowedRackingNotes();
					projectNotes.setProject(project);
				}
				projectNotes.setHasNotAllowedRoofRacking(true);
				projectNotes.setHasRoofRackingNote(fileName != null);
				projectNotes.setNotAllowedRoofRackingNote(note);
				projectNotes.setNotAllowedRoofRackingFileName(fileName);
				rackingNotesRepo.save(projectNotes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeRackingNotetoADV(Long projectId) {
		try {
			NotAllowedRackingNotes projectNotes = rackingNotesRepo.findByProjectId(projectId);
			if (projectNotes != null) {
				projectNotes.setHasNotAllowedRoofRacking(false);
				projectNotes.setHasRoofRackingNote(false);
				projectNotes.setNotAllowedRoofRackingNote(null);
				projectNotes.setNotAllowedRoofRackingFileName(null);
				rackingNotesRepo.save(projectNotes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String insertRackingNotetoADV(MultipartFile file, String fileName, Long projectId) {
		try {
			String path = pathRepo.findFilePath();
			if (!new File(path + projectId).exists()) {
				new File(path + projectId).mkdir();
			}
			if (!new File(path + projectId + "/Not Allowed Racking Notes/").exists()) {
				new File(path + projectId + "/Not Allowed Racking Notes/").mkdir();
			}
			if (!new File(path + projectId + "/Not Allowed Racking Notes/").exists()) {
				new File(path + projectId + "/Not Allowed Racking Notes/").mkdir();
			}else {
				FileUtils.cleanDirectory(new File(path + projectId + "/Not Allowed Racking Notes/"));
			}
			byte[] bytes = file.getBytes();
			String filename = fileName;
			Path pathUpl = Paths.get(
					path + projectId + "/Not Allowed Racking Notes/" + filename);
			Files.write(pathUpl, bytes);
		
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	public void copyRackingNoteFile(Long newProjectId,Long projectId) {
		try {
			String path = pathRepo.findFilePath();
			File source = new File(path + projectId + "/Not Allowed Racking Notes/");
			File dest = new File(path + newProjectId + "/Not Allowed Racking Notes/");
			try {
			    FileUtils.copyDirectory(source, dest);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResponseEntity<byte[]> downloadADVRackingNote(Long projectId, String fileName) {

		String dirPath = pathRepo.findFilePath();
		Path path = Paths.get(
				dirPath + projectId + "/Not Allowed Racking Notes/" + fileName);
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
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
		return response;
	}

}
