package com.PlayGroundAdv.Solar.service.user_management;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.users.UserCutomUpload;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.EditUserInformations;
import com.PlayGroundAdv.Solar.repositories.project.ProjectCustomFilesRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserCutomUploadRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.libraries.AcDisconnectService;
import com.PlayGroundAdv.Solar.service.libraries.RailRackingService;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class UserSettingService {

	final HistoryActivityService activityService;
	final CheckValueTypesService checkValue;
	final AuthenticationRepository authRepo;
	final UserSettingRepository userSettingRepo;
	final RailRackingService raikingService;
	final AcDisconnectService acdService;
	final UserCutomUploadRepository cutomUploadRepo;
	final ProjectCustomFilesRepository projectFilesRepo;
	public UserSettingService(HistoryActivityService activityService, CheckValueTypesService checkValue,
			AuthenticationRepository authRepo, UserSettingRepository userSettingRepo,
			RailRackingService raikingService, AcDisconnectService acdService,
			UserCutomUploadRepository cutomUploadRepo, ProjectCustomFilesRepository projectFilesRepo) {
		super();
		this.activityService = activityService;
		this.checkValue = checkValue;
		this.authRepo = authRepo;
		this.userSettingRepo = userSettingRepo;
		this.raikingService = raikingService;
		this.acdService = acdService;
		this.cutomUploadRepo = cutomUploadRepo;
		this.projectFilesRepo = projectFilesRepo;
	}

	/*
	 * edit User Setting
	 */
	public String editUserSetting(EditUserInformations editUserInformations, String ipUser, String timeZone,
			Long idUser) {

		try {
			AuthentificationEntity user = authRepo.findById(editUserInformations.getId()).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" + editUserInformations.getId()));

			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(editUserInformations.getId());

			if (editUserInformations.getReminder() != null) {
				if (checkValue.Equals(editUserInformations.getReminder(), "Other")) {
					userSetting.setReminder(editUserInformations.getReminderOther());
				} else {
					userSetting.setReminder(editUserInformations.getReminder());
				}

			}
			// ADD Ground RAIL RACKING to the favorit list
			if (checkValue.isNumericNotZero(editUserInformations.getUserGroundRailRaking())) {
				raikingService.addToFavorite(Long.valueOf(editUserInformations.getUserGroundRailRaking()), idUser, idUser, ipUser, timeZone);
			}
			userSetting.setMinimumDCGroundCon(editUserInformations.getMinimumDCGroundCon());
			userSetting.setMinimumDCGroConOther(editUserInformations.getMinimumDCGroConOther());
			userSetting.setMinimumACGroundCon(editUserInformations.getMinimumACGroundCon());
			userSetting.setMinimumACGroConOther(editUserInformations.getMinimumACGroConOther());
			// A.B 03-14: CR-2554-MOD-001
			userSetting.setUseRomexInAttic(editUserInformations.getUseRomexInAttic());
			// I.C 10-21: CR-2972-MOD-001
			userSetting.setAtticTemperatureAdder(editUserInformations.getAtticTemperatureAdder());
			userSetting.setUserGroundRailRaking(editUserInformations.getUserGroundRailRaking());
			userSetting.setUserSizeOfPipe(editUserInformations.getUserSizeOfPipe());
			if (checkValue.NotEquals(editUserInformations.getUserSizeOfPipe(), "Other")) {
				userSetting.setUserSizeOfPipeOther(null);
			} else {
				userSetting.setUserSizeOfPipeOther(editUserInformations.getUserSizeOfPipeOther());
			}
			userSetting.setUserThicknessOfPipe(editUserInformations.getUserThicknessOfPipe());
			if (checkValue.NotEquals(editUserInformations.getUserThicknessOfPipe(), "Other")) {
				userSetting.setUserThicknessOfPipeOther(null);
			} else {
				userSetting.setUserThicknessOfPipeOther(editUserInformations.getUserThicknessOfPipeOther());
			}
			userSetting.setUserBracedUnbraced(editUserInformations.getUserBracedUnbraced());
			userSetting.setUserFootingDiameter(editUserInformations.getUserFootingDiameter());
			if (checkValue.NotEquals(editUserInformations.getUserFootingDiameter(), "Other")) {
				userSetting.setUserFootingDiameterOther(null);
			} else {
				userSetting.setUserFootingDiameterOther(editUserInformations.getUserFootingDiameterOther());
			}
			userSetting.setPvmModelDefault(editUserInformations.getPvmModelDefault());
			userSetting.setUserAcDisconnect(editUserInformations.getUserAcDisconnect());
			// ADD ACD Model as Default to the favorit list
			if (checkValue.contains(editUserInformations.getUserAcDisconnect(), ":") && checkValue.isNumericNotZero(editUserInformations.getUserAcDisconnect().split(":")[0])) {
				acdService.addAcDiscoFavorite(idUser, Long.valueOf(editUserInformations.getUserAcDisconnect().split(":")[0]), idUser);
			}

			userSetting.setAmpRating(editUserInformations.getAmpRating());
			userSetting.setFusibleNonFusible(editUserInformations.getFusibleNonFusible());
			userSetting.setNemaRating(editUserInformations.getNemaRating());
			userSetting.setStanchionsType(editUserInformations.getStanchionsType());
			userSetting.setNeutralConductorString(editUserInformations.getNeutralConductorString());
			userSetting.setNeutralConductorMicro(editUserInformations.getNeutralConductorMicro());
			userSetting.setMinSizeConductorsFromMainServicePanel(editUserInformations.getMinSizeConductorsFromMainServicePanel());
			userSetting.setPointOfConnectionNote(editUserInformations.getPointOfConnectionNote());
			userSetting.setMicroASystemSize(editUserInformations.getMicroASystemSize());
			userSetting.setStringASystemSize(editUserInformations.getStringASystemSize());
			userSetting.setWireTapSetting(editUserInformations.getWireTapSetting());
			userSetting.setAcdNotRequired(editUserInformations.getAcdNotRequired());
			userSetting.setPvmNotRequired(editUserInformations.getPvmNotRequired());
			userSetting.setArchiveAllowed(editUserInformations.getArchiveAllowed());
			userSetting.setArchiveDelay(editUserInformations.getArchiveDelay());

			//A.B 04-11-2022 CR-806
			if(editUserInformations.getCustomUploads() != null && !editUserInformations.getCustomUploads().isEmpty()) {
				for (String u : editUserInformations.getCustomUploads()) {
					if(checkValue.isStringNotEmpty(u) && Boolean.FALSE.equals(cutomUploadRepo.existsByUserIdAndTitle(user, u))) {
						cutomUploadRepo.save(new UserCutomUpload(user, u.trim()));
					}
				}
				//A.B 04-11-2022 CR-806 Delete Unused Custom Files
				List<UserCutomUpload> uploads = cutomUploadRepo.findByUserIdAndTitleNotIn(user,editUserInformations.getCustomUploads());
				for (UserCutomUpload c : uploads) {
					projectFilesRepo.deleteByCustomUploadId(c.getId());
				}
				cutomUploadRepo.deleteAll(uploads);
			}else {
				List<UserCutomUpload> uploads = cutomUploadRepo.findByUserId(user);
				for (UserCutomUpload c : uploads) {
					projectFilesRepo.deleteByCustomUploadId(c.getId());
				}
				cutomUploadRepo.deleteAll(uploads);
			}

			userSettingRepo.save(userSetting);
			activityService.recordActivity(idUser, ipUser, timeZone,
					"Edit User Setting;user Id : " + user.getId() + ";Edit User Success", true, "", "", "");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUser, ipUser, timeZone, "Edit User Setting;Edit User Failed", false,
					"", "", "");
			return "error";
		}

	}
}

