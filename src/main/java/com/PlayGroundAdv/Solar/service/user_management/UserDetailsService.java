package com.PlayGroundAdv.Solar.service.user_management;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ContractorInformationEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.EditUserInformations;
import com.PlayGroundAdv.Solar.model.UserSettingAccess;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserCutomUploadRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class UserDetailsService {
	
	
	final AuthenticationRepository authRepo;
	final ContractorInformationRepository contractorInfoRepo;
	final UserSettingRepository userSettingRepo;
	final CheckValueTypesService checkValue;
	final GoogleDriveFolder updateGoogleDriveFolder; 
	final HistoryActivityService activityService;
	final EmailValidationService emailValidationService;
	final UserCutomUploadRepository cutomUploadRepo;
	public UserDetailsService(AuthenticationRepository authRepo, ContractorInformationRepository contractorInfoRepo,
			UserSettingRepository userSettingRepo, CheckValueTypesService checkValue,
			GoogleDriveFolder updateGoogleDriveFolder, HistoryActivityService activityService,
			EmailValidationService emailValidationService,UserCutomUploadRepository cutomUploadRepo) {
		super();
		this.authRepo = authRepo;
		this.contractorInfoRepo = contractorInfoRepo;
		this.userSettingRepo = userSettingRepo;
		this.checkValue = checkValue;
		this.updateGoogleDriveFolder = updateGoogleDriveFolder;
		this.activityService = activityService;
		this.emailValidationService = emailValidationService;
		this.cutomUploadRepo = cutomUploadRepo;
	}

	/*
	 * Return the user's informations needed from AuthentificationEntity,
	 * ContractorInformationEntity and UserSettingEntity
	 */
	public EditUserInformations getUser(Long id) {
		try {

			AuthentificationEntity user = authRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User does not exist with this id: " + id));
			ContractorInformationEntity contractor = contractorInfoRepo.findByAuthentificationEntityId(id);
			UserSettingEntity userSettings = userSettingRepo.findByUserIdId(id);
			List<String> customUploads = new ArrayList<>(cutomUploadRepo.findByUserId(user).stream().map(o -> o.getTitle()).collect(Collectors.toList())); 
			EditUserInformations result = new EditUserInformations(user.getId(), user.getContractorCode(),
					user.getFirstName(), user.getLastName(), user.getCountry(), user.getCompany(), user.getEmail(),
					user.getActive(), contractor.getAddress(), contractor.getSecondAddressLine(), contractor.getCity(),
					contractor.getState(), contractor.getPostalCode(), user.getRoleEntity().getId(),
					contractor.getDate(), user.getSameMalingAddress(), contractor.getMailingAddress(),
					contractor.getSecondMailingAddress(), contractor.getMailingCity(), contractor.getMailingState(),
					contractor.getMailingZipCode(), contractor.getContactFirstName(), contractor.getContactLastName(),
					contractor.getContactEmail(), contractor.getContactPhone(), contractor.getContactAddPhone(),
					contractor.getSecondContactFirstName(), contractor.getSecondContactLastName(),
					contractor.getSecondContactEmail(), contractor.getSecondContactPhone(),
					contractor.getSecondContactAddPhone(), contractor.getIncludeSecondContact(),
					contractor.getThirdContact(), contractor.getThirdContactEmail(), contractor.getThirdContactPhone(),
					contractor.getThirdContactAddPhone(), contractor.getIncludeThirdContact(),
					contractor.getBusinessPhone(), contractor.getOtherPhone(), contractor.getDesignBy(),
					contractor.getLicenseNumber(), contractor.getLicenseExpiration(), contractor.getContractorLic(),
					contractor.getQualifyingIndividual(), contractor.getAdditionalQualifying(),
					contractor.getIncludeSecondContactOnly(), contractor.getIncludeSecondContactWhen(),
					contractor.getIncludeThirdContactOnly(), contractor.getIncludeThirdContactWhen(),
					contractor.getDesignByOther(), contractor.getContractorLicC10(), contractor.getContractorLicB(),
					contractor.getQualifyingIndividualOther(), contractor.getAdditionalQualifyingOther(),
					contractor.getContractorLicenceState(), contractor.getIsProjectAddInclud(),
					contractor.getLastNameContact(), userSettings.getReminder(), null, contractor.getCompPhoneNum(),
					contractor.getContactOptions(), contractor.getContactOptionsOther(),
					userSettings.getMinimumDCGroundCon(), userSettings.getMinimumDCGroConOther(),
					userSettings.getMinimumACGroundCon(), userSettings.getMinimumACGroConOther(),
					userSettings.getUserGroundRailRaking(), userSettings.getUserSizeOfPipe(),
					userSettings.getUserSizeOfPipeOther(), userSettings.getUserThicknessOfPipe(),
					userSettings.getUserThicknessOfPipeOther(), userSettings.getUserBracedUnbraced(),
					userSettings.getUserFootingDiameter(), userSettings.getUserFootingDiameterOther(),
					userSettings.getSiteSurvey(), userSettings.getSolarPermit(), userSettings.getHasSettingAccess(),
					userSettings.getHasAhjMgtAccess(), userSettings.getPvmModelDefault(),
					userSettings.getUserAcDisconnect(), userSettings.getAmpRating(),
					userSettings.getFusibleNonFusible(), userSettings.getNemaRating(),
					userSettings.getUseRomexInAttic(), userSettings.getAtticTemperatureAdder(),
					userSettings.getStanchionsType(), userSettings.getNeutralConductorString(),
					userSettings.getNeutralConductorMicro(), userSettings.getMinSizeConductorsFromMainServicePanel(), 
					userSettings.getPointOfConnectionNote(), userSettings.getHasLibVerifAccess(), 
					userSettings.getMicroASystemSize(), userSettings.getStringASystemSize(),customUploads,
					contractor.getCompPhoneNum(), userSettings.getWireTapSetting(), userSettings.getAcdNotRequired(), 
					userSettings.getPvmNotRequired(),userSettings.getArchiveAllowed(),userSettings.getArchiveDelay());


			if (checkValue.NotEquals(result.getReminder(), "")
					&& checkValue.NotEquals(result.getReminder(), "6")
					&& checkValue.NotEquals(result.getReminder(), "12")
					&& checkValue.NotEquals(result.getReminder(), "24")
					&& checkValue.NotEquals(result.getReminder(), "72")
					&& checkValue.NotEquals(result.getReminder(), "84")
					&& checkValue.NotEquals(result.getReminder(), "96")) {
				result.setReminderOther(result.getReminder());
				result.setReminder("Other");
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/*
	 * edit User Contact
	 */
	public String EditUserContact(EditUserInformations editUserInformations, String ipUser, String timeZone,
			Long idUser) {

		try {

			AuthentificationEntity user = authRepo.findById(editUserInformations.getId()).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" + editUserInformations.getId()));

			ContractorInformationEntity userInfo = contractorInfoRepo
					.findByAuthentificationEntityId(editUserInformations.getId());
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(editUserInformations.getId());

			// A.B 10-28: Update Google Drive Folders
			updateGoogleDriveFolder.editGoogleDriveFolder(user, editUserInformations);

			if (!Boolean.TRUE.equals(editUserInformations.getSameMalingAddress())) {
				userInfo.setMailingAddress(editUserInformations.getMailingAddress());
				userInfo.setSecondMailingAddress(editUserInformations.getSecondMailingAddress());
				userInfo.setMailingCity(editUserInformations.getMailingCity());
				userInfo.setMailingState(editUserInformations.getMailingState());
				userInfo.setMailingZipCode(editUserInformations.getMailingZipCode());
			} else {
				userInfo.setMailingAddress(editUserInformations.getAddress());
				userInfo.setSecondMailingAddress(editUserInformations.getSecondAddressLine());
				userInfo.setMailingCity(editUserInformations.getCity());
				userInfo.setMailingState(editUserInformations.getState());
				userInfo.setMailingZipCode(editUserInformations.getPostalCode());
			}

			userInfo.setSameMalingAddress(Boolean.TRUE.equals(editUserInformations.getSameMalingAddress()));
			user.setSameMalingAddress(Boolean.TRUE.equals(editUserInformations.getSameMalingAddress()));
			user.setContractorCode(editUserInformations.getContractorCode());
			user.setFirstName(editUserInformations.getFirstName());
			user.setLastName(editUserInformations.getLastName());
			user.setCountry(editUserInformations.getCountry());
			user.setCompany(editUserInformations.getCompany());
			userSetting.setHasSettingAccess(editUserInformations.getHasSettingAccess());

			userInfo.setCompPhoneNum(editUserInformations.getCompPhoneNum());
			userInfo.setContactOptions(editUserInformations.getContactOptions());
			userInfo.setContactOptionsOther(editUserInformations.getContactOptionsOther());
			userInfo.setCity(editUserInformations.getCity());
			userInfo.setState(editUserInformations.getState());
			userInfo.setPostalCode(editUserInformations.getPostalCode());
			userInfo.setSecondAddressLine(editUserInformations.getSecondAddressLine());
			userInfo.setContractorLicenceState(editUserInformations.getContractorLicenceState());
			userInfo.setLicenseNumber(editUserInformations.getLicenseNumber());
			userInfo.setLicenseExpiration(editUserInformations.getLicenseExpiration());
			userInfo.setContractorLic(editUserInformations.getContractorLic());
			userInfo.setContractorLicC10(editUserInformations.getContractorLicC10());
			userInfo.setContractorLicB(editUserInformations.getContractorLicB());
			userInfo.setQualifyingIndividual(editUserInformations.getQualifyingIndividual());
			userInfo.setContactFirstName(editUserInformations.getContactFirstName());
			userInfo.setContactLastName(editUserInformations.getContactLastName());
			userInfo.setContactPhone(editUserInformations.getContactPhone());
			userInfo.setContactAddPhone(editUserInformations.getContactAddPhone());
			userInfo.setContactEmail(editUserInformations.getContactEmail());
			userInfo.setIsProjectAddInclud(editUserInformations.getIsProjectAddInclud());
			userInfo.setDesignBy(editUserInformations.getDesignBy());
			userInfo.setSecondContactFirstName(editUserInformations.getSecondContactFirstName());
			userInfo.setSecondContactLastName(editUserInformations.getSecondContactLastName());
			userInfo.setSecondContactPhone(editUserInformations.getSecondContactPhone());
			userInfo.setSecondContactAddPhone(editUserInformations.getSecondContactAddPhone());
			userInfo.setSecondContactEmail(editUserInformations.getSecondContactEmail());
			userInfo.setIncludeSecondContact(editUserInformations.getIncludeSecondContact());
			userInfo.setIncludeSecondContactOnly(editUserInformations.getIncludeSecondContactOnly());
			userInfo.setIncludeSecondContactWhen(editUserInformations.getIncludeSecondContactWhen());
			userInfo.setThirdContact(editUserInformations.getThirdContact());
			userInfo.setLastNameContact(editUserInformations.getLastNameContact());
			userInfo.setThirdContactPhone(editUserInformations.getThirdContactPhone());
			userInfo.setThirdContactAddPhone(editUserInformations.getThirdContactAddPhone());
			userInfo.setThirdContactEmail(editUserInformations.getThirdContactEmail());
			userInfo.setIncludeThirdContact(editUserInformations.getIncludeThirdContact());
			userInfo.setIncludeThirdContactOnly(editUserInformations.getIncludeThirdContactOnly());
			userInfo.setIncludeThirdContactWhen(editUserInformations.getIncludeThirdContactWhen());
			userInfo.setDesignByOther(editUserInformations.getDesignByOther());
			userInfo.setAddress(editUserInformations.getAddress());
			authRepo.save(user);
			userSettingRepo.save(userSetting);
			contractorInfoRepo.save(userInfo);

			activityService.recordActivity(idUser, ipUser, timeZone,
					"Edit User Company & contact Information;user Id : " + user.getId() + ";Edit User Success", true,
					"", "", "");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUser, ipUser, timeZone,
					"Edit User Company & contact Information;Edit User Failed", false, "", "", "");
			return "error";
		}

	}
	
	// 07-26-2019: M.A : CR-2622 update user profile
		public String updateUserAccess(Long idUser, String accessPer, String accessSite) {

			try {
				UserSettingEntity userSetting = userSettingRepo.findByUserIdId(idUser);
				userSetting.setSiteSurvey(Boolean.valueOf(accessSite));
				userSetting.setSolarPermit(Boolean.valueOf(accessPer));
				userSettingRepo.save(userSetting);
				return "success";

			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}

		public String changeUserEmail(Long idUser, String userEmail) throws Exception {
			try {
				AuthentificationEntity userExist = authRepo.findByIdNotAndEmailIgnoreCaseAndDeletedIsFalse(idUser,
						userEmail);
				if (userExist != null) {
					return "exist";
				}
				Boolean isvalid = emailValidationService.validateEmail(userEmail);
				if (Boolean.TRUE.equals(isvalid)) {
					AuthentificationEntity user = authRepo.findById(idUser).get();
					user.setEmail(userEmail.toLowerCase());
					authRepo.save(user);
					return "success";
				} else {
					return "Mail Does not exist in DNS Server";
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
				return "fail";
			}

		}

		public UserSettingAccess getUserSettingAccess(Long idUser) throws Exception {
			try {

				UserSettingEntity userSetting = userSettingRepo.findByUserIdId(idUser);
				return new UserSettingAccess(userSetting.getSolarPermit(),
						userSetting.getSiteSurvey(), userSetting.getHasSettingAccess());

			} catch (Exception e) {
				e.printStackTrace();
				return new UserSettingAccess(false, false, false);
			}
		}

}

