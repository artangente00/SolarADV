package com.PlayGroundAdv.Solar.service.sheets;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PlansetSheetEntity;
import com.PlayGroundAdv.Solar.entity.PlansetSheetsLog;
import com.PlayGroundAdv.Solar.model.libraries.PlansetDto;
import com.PlayGroundAdv.Solar.model.libraries.SheetPageRequest;
import com.PlayGroundAdv.Solar.repositories.PlansetSheetsLogRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PlansetSheetRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.google.api.client.util.DateTime;

@Service
@Transactional
public class PlansetSheetLibrary {

	final NotificationEntityService notificationEntityService;
	final CheckValueTypesService checkValueTypesService;
	final HistoryActivityService historyActivityService;
	final PlansetSheetRepository plansetRepo;
	final AuthenticationRepository userRepo;
	final PlansetSheetsLogRepository plansetLogRepo;

	public PlansetSheetLibrary(NotificationEntityService notificationEntityService,
			CheckValueTypesService checkValueTypesService, HistoryActivityService historyActivityService,
			PlansetSheetRepository plansetRepo, AuthenticationRepository userRepo,
			PlansetSheetsLogRepository plansetLogRepo) {
		super();
		this.notificationEntityService = notificationEntityService;
		this.checkValueTypesService = checkValueTypesService;
		this.historyActivityService = historyActivityService;
		this.plansetRepo = plansetRepo;
		this.userRepo = userRepo;
		this.plansetLogRepo = plansetLogRepo;

	}

	// CR-2196	
	public Page<PlansetDto> filter(SheetPageRequest request) {
		try {
			Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("pdfName"));
			String pdfName = checkValueTypesService.isStringNotEmpty(request.getPdfName()) ? request.getPdfName()
					: null;
			if(pdfName == null) {
		    	Page<PlansetSheetEntity> list = plansetRepo.findByTypeOrderByPdfName(request.getPdfType(),pageable);
			    return convertDto(list,pageable);
			}else {
				Page<PlansetSheetEntity> list = plansetRepo.findByTypeAndPdfName(request.getPdfType(), pdfName, pageable);
				return convertDto(list,pageable);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ************Converter****************
	private Page<PlansetDto> convertDto(Page<PlansetSheetEntity> page,  Pageable pageable) {
		try {
				return new PageImpl<>(
						page.stream().map(planset -> new PlansetDto(
								planset.getId(),
								planset.getPdfName(),
								planset.getLastUpdate(),
								planset.getType(),
								planset.getLastUpdateBy() != null ? getLastUpdateUser(planset.getLastUpdateBy()) : "",
								planset.getComment()
								)).collect(Collectors.toList()),pageable, page.getTotalElements());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	private String getLastUpdateUser(Long useriD) {
		try {
			AuthentificationEntity user = userRepo.findById(useriD).orElse(null);
			return user != null ? user.getFirstName() + " " + user.getLastName() : "";
		} catch (Exception e) {
			return "";
		}
	}
	

	public String editPlansetSheets(MultipartFile file, String fileName, Long useriD, String fileID, String comment,
			String ipAdress, String timeZone, String numTab, String openDate, String sessionId) {
		try {

			PlansetSheetEntity updatedPlanset = plansetRepo.findById(Long.valueOf(fileID)).orElse(null);
			AuthentificationEntity user = userRepo.findById(useriD).orElse(null);

			if (updatedPlanset != null && user != null) {
				TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
				DateTime dt = new DateTime(new Date(), TimeZone.getTimeZone("PST8PDT"));
				SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				isoFormat.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
				Date date = isoFormat.parse(dt + "");
				updatedPlanset.setLastUpdate(date.toString());
				updatedPlanset.setComment(comment);
				updatedPlanset.setLastUpdateBy(user.getId());
				plansetRepo.save(updatedPlanset);
				String pdfPath = "";
				if (checkValueTypesService.Equals(updatedPlanset.getType(), "CEC")) {
					byte[] bytes5 = file.getBytes();
					File f = new File(Constants.rapportPlansetFolderUrl + pdfPath + fileName);
					f.delete();
					Path pathUp5 = Paths.get(Constants.rapportPlansetFolderUrl + pdfPath + fileName);
					Files.write(pathUp5, bytes5);
					notificationEntityService.addNewNotif(useriD, 0L, "Updated The CEC Planset ", "Libraries",
							"Updated The CEC Planset",
							"The " + user.getRoleEntity().getDescription() + " " + user.getFirstName() + " "
									+ user.getLastName() + " updated The CEC Planset sheet  "
									+ updatedPlanset.getPdfName(),
							true);
					// 06-12-2019: M.A :CR-2568
					historyActivityService.recordActivity(
							user.getId(), ipAdress, timeZone, "Planset Sheet;Updated the CEC Planset sheet "
									+ updatedPlanset.getPdfName() + " Comment : " + comment,
							true, numTab, sessionId, openDate);

					String server = plansetRepo.getUrlPath();
					if (checkValueTypesService.Equals(server, "production")
							&& (checkValueTypesService.Equals(user.getEmail(), "pm@nuagetechnologies-tn.com")
									|| !checkValueTypesService.contains(user.getEmail(), "nuagetechnologies-tn.com"))) {
						PlansetSheetsLog sheetLog = new PlansetSheetsLog(updatedPlanset.getPdfName(), "CEC", comment,
								new Date(), user);
						plansetLogRepo.save(sheetLog);
					}

				} else {
					pdfPath = "NEC-PDF/";
					byte[] bytes5 = file.getBytes();
					File f = new File(Constants.rapportPlansetFolderUrl + pdfPath + fileName);
					f.delete();
					Path pathUp5 = Paths.get(Constants.rapportPlansetFolderUrl + pdfPath + fileName);
					Files.write(pathUp5, bytes5);
					notificationEntityService.addNewNotif(useriD, 0L, "Updated The NEC Planset ", "Libraries",
							"Updated The NEC Planset",
							"The " + user.getRoleEntity().getDescription() + " " + user.getFirstName() + " "
									+ user.getLastName() + " updated The NEC Planset sheet  "
									+ updatedPlanset.getPdfName(),
							true);
					// 06-12-2019: M.A :CR-2568
					historyActivityService.recordActivity(
							user.getId(), ipAdress, timeZone, "Planset Sheet;Updated the NEC Planset sheet "
									+ updatedPlanset.getPdfName() + " Comment : " + comment,
							true, numTab, sessionId, openDate);

					String server = plansetRepo.getUrlPath();
					if (checkValueTypesService.Equals(server, "production")
							&& (checkValueTypesService.Equals(user.getEmail(), "pm@nuagetechnologies-tn.com")
									|| !checkValueTypesService.contains(user.getEmail(), "nuagetechnologies-tn.com"))) {
						PlansetSheetsLog sheetLog = new PlansetSheetsLog(updatedPlanset.getPdfName(), "NEC", comment,
								new Date(), user);
						plansetLogRepo.save(sheetLog);
					}
				}

			}
			return "Done";

		} catch (Exception e) {
			e.printStackTrace();
			// 06-12-2019: M.A :CR-2568
			historyActivityService.recordActivity(useriD, ipAdress, timeZone,
					"Planset Sheet;Updated the Planset sheet ", false, numTab, sessionId, openDate);
			return "fail";
		}
	}

}
