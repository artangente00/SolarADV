package com.PlayGroundAdv.Solar.service.ahj_library;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJColumnsEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ahj_library.AttachementModel;
import com.PlayGroundAdv.Solar.model.ahj_library.DeletedAttachmentModel;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJColumnsRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
@Service
public class AHJFileManagementService {
	
	
	final PathRepository pathRepo;
	final AHJColumnsRepository ahjColumnsRepo;
	final CheckValueTypesService checkV;
	final AHJLogService logService;

	public AHJFileManagementService(PathRepository pathRepo, AHJColumnsRepository ahjColumnsRepo,
			CheckValueTypesService checkV, AHJLogService logService) {
		super();
		this.pathRepo = pathRepo;
		this.ahjColumnsRepo = ahjColumnsRepo;
		this.checkV = checkV;
		this.logService = logService;
	}

	public List<AttachementModel> getAttachements(String f) {
		try {
			String dirPath = pathRepo.findFilePath() + "AHJ Attachement/" + f + "/";
			File directory = new File(dirPath);
			List<AttachementModel> fileNames = new ArrayList<>();
			// Get all files from a directory.
			File[] fList = directory.listFiles();
			if (fList != null)
				for (File file : fList) {
					if (file.isFile() && f.contains("/")) {
						if (checkV.isNumericNotZero(f.split("/")[1])) {
							AHJColumnsEntity cell = ahjColumnsRepo.findById(Long.valueOf(f.split("/")[1])).orElseThrow(
									() -> new ResourceNotFoundException("Cell not found for this id :" + f.split("/")[1]));
							fileNames.add(new AttachementModel(cell.getColumnTitle(),f.split("/")[1],file.getName(), cell.getCategory()));
						} else {
							fileNames.add(new AttachementModel("AHJ","AHJ",file.getName(),"AHJ"));
						}
					} else if (file.isDirectory()) {
						fileNames.addAll(getAttachements(f + "/"+ file.getName() + "/"));
					}
				}
			return fileNames;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		
	}

	public Boolean insertAttachement(MultipartFile file, String fileName, Long cellId, Long ahjId) {
		try {
			String sf = cellId > 0 ? cellId + "/" : "AHJ/";
			// A.B Create AHJ Folder If does not Exist
			if (!new File(pathRepo.findFilePath() + "AHJ Attachement").exists()) {
				new File(pathRepo.findFilePath() + "AHJ Attachement/").mkdir();
				new File(pathRepo.findFilePath() + "AHJ Attachement/" + ahjId + "/").mkdir();
				new File(pathRepo.findFilePath() + "AHJ Attachement/" + ahjId + "/" + sf).mkdir();
			} else {
				if (!new File(pathRepo.findFilePath() + "AHJ Attachement/" + ahjId + "/").exists()) {
					new File(pathRepo.findFilePath() + "AHJ Attachement/" + ahjId + "/").mkdir();
				}
				// A.B Create AHJ Cell Folder If does not Exist
				if (!new File(pathRepo.findFilePath() + "AHJ Attachement/" + ahjId + "/" + sf + "/").exists()) {
					new File(pathRepo.findFilePath() + "AHJ Attachement/" + ahjId + "/" + sf + "/").mkdir();
				}
			}

			byte[] bytes = file.getBytes();
			String filename = fileName;
			Path pathUpl = Paths.get(pathRepo.findFilePath() + "AHJ Attachement/" + ahjId + "/" + sf + "/" + filename);
			Files.write(pathUpl, bytes);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Boolean deleteAttachement(DeletedAttachmentModel attModel) {
		try {
			
			String ahjFolder = pathRepo.findFilePath() + "AHJ Deleted Attachement/"; 
			if (!new File(ahjFolder).exists()) {
				new File(ahjFolder).mkdir();
			}
			if (!new File(ahjFolder + attModel.getAhjId() + "/").exists()) {
				new File(ahjFolder + attModel.getAhjId() + "/").mkdir();
			}
			if (!new File(ahjFolder + attModel.getAhjId() + "/" + attModel.getCellId() + "/").exists()) {
				new File(ahjFolder + attModel.getAhjId() + "/" + attModel.getCellId() + "/").mkdir();
			}
			Integer index = 1;
			String fileNameDst = attModel.getFileName();
			while (new File(ahjFolder + attModel.getAhjId() + "/" + attModel.getCellId() + "/" + fileNameDst).exists()) {
				fileNameDst = "("+index+") "+attModel.getFileName();
				index++;
			}
			Path path = Paths.get(pathRepo.findFilePath() + "AHJ Attachement/" + attModel.getAhjId() + "/" + attModel.getCellId() + "/" + attModel.getFileName());
			Path pathd = Paths.get(ahjFolder + attModel.getAhjId() + "/" + attModel.getCellId() + "/" + fileNameDst);
			Files.move(path, pathd); 
			if (isDirEmpty(Paths.get(pathRepo.findFilePath() + "AHJ Attachement/" + attModel.getAhjId() + "/" + attModel.getCellId() + "/"))) {
				Files.delete(Paths.get(pathRepo.findFilePath() + "AHJ Attachement/" + attModel.getAhjId() + "/" + attModel.getCellId() + "/"));
			}
			attModel.setFileName(fileNameDst);
			logService.logAttachmentDelete(attModel);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	
	public ResponseEntity<byte[]> getAttachementFile(String pathFile) {
		try {
			//pathFile: "AHJ Attachement/" + ahj + "/" + cell + "/" + fileName
			Path path = Paths.get(pathRepo.findFilePath() + pathFile);
			String fileType = identifyFileType(path);
			byte[] contents = null;
			try {
				contents = Files.readAllBytes(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HttpHeaders headers = new HttpHeaders();
			if(pathFile.contains(".pdf")) {
				headers.setContentType(MediaType.parseMediaType("application/pdf"));
			}else {
				headers.setContentType(MediaType.parseMediaType(fileType != null ? fileType : "text/html"));
			}
			
			String filename = "output.xls";
			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
					org.springframework.http.HttpStatus.OK);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	final String identifyFileType(final Path filePath) {
		try {
			return Files.probeContentType(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return "Indetermined";
		}

	}
	private static boolean isDirEmpty(final Path directory) throws IOException {
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
			return !dirStream.iterator().hasNext();
		}
	}

}
