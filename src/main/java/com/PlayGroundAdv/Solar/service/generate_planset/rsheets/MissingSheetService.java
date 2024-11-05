package com.PlayGroundAdv.Solar.service.generate_planset.rsheets;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.log.SheetsLogService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class MissingSheetService {

	final SheetsLogService missingSheetService;
	final NotificationEntityService notificationService;
	final CheckValueTypesService checkValue;

	public MissingSheetService(SheetsLogService missingSheetService, NotificationEntityService notificationService,
			CheckValueTypesService checkValue) {
		super();
		this.missingSheetService = missingSheetService;
		this.notificationService = notificationService;
		this.checkValue = checkValue;
	}

	public void missingRSheet(Long idUser, String subject, String msg, Boolean sendToSupserUser, String sheetName,
			String sheetType, Long submissionId, PermitEntity project, AuthentificationEntity userInfo) {
		try {
			notificationService.addNewNotif(idUser, 0L, "Missing R-sheet", "Libraries", subject, msg, sendToSupserUser);
			// A.B 02-13 log Missing sheet
			missingSheetService.insertMissingSheet(sheetName, sheetType, submissionId, project, userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String createRsheetNotifMessage(String manufacturer, String model, String firstName, String firstLast,
			String projectName, String projectFirstName, String projectLastName) {

		String project = checkValue.isStringNotEmpty(projectName) ? projectName
				: projectLastName + ", " + projectFirstName;
		return "The R sheet for the " + manufacturer + " " + model + " selected by " + firstName + " " + firstLast
				+ " in the project " + project + " is missing";
	}
}
