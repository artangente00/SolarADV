package com.PlayGroundAdv.Solar.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.entity.MailingSetting;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ActivateUserModel;
import com.PlayGroundAdv.Solar.model.ChangePasswordModel;
import com.PlayGroundAdv.Solar.model.ChangeRoleModel;
import com.PlayGroundAdv.Solar.model.ContractorLicSectionModel;
import com.PlayGroundAdv.Solar.model.EditUserInformations;
import com.PlayGroundAdv.Solar.model.EditUserModel;
import com.PlayGroundAdv.Solar.model.LoginResult;
import com.PlayGroundAdv.Solar.model.RoofTypeAttachementModel;
import com.PlayGroundAdv.Solar.model.StringModelResult;
import com.PlayGroundAdv.Solar.model.UserLicSectionModel;
import com.PlayGroundAdv.Solar.model.UserSettingAccess;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentModel;
import com.PlayGroundAdv.Solar.repositories.MailingSettingRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.mailing.MailingSession;
import com.PlayGroundAdv.Solar.service.user_management.AuthenticationService;
import com.PlayGroundAdv.Solar.service.user_management.LicenseSettingService;
import com.PlayGroundAdv.Solar.service.user_management.LogoAndSignature;
import com.PlayGroundAdv.Solar.service.user_management.RailtoRoofConnectionSetting;
import com.PlayGroundAdv.Solar.service.user_management.RoleService;
import com.PlayGroundAdv.Solar.service.user_management.UserDetailsService;
import com.PlayGroundAdv.Solar.service.user_management.UserInformationService;
import com.PlayGroundAdv.Solar.service.user_management.UserSettingService;

@RestController
@RequestMapping("/user")
public class UserController {

	final UserInformationService userInformationService;
	final RoleService roleService;
	final MailingSession mailingSession;
	final HttpSession httpSession;
	final HistoryActivityService historyActivityService;
	static javax.mail.Session session;
	final MailingSettingRepository mailingRepo;
	final UserSettingService userSetting;
	final UserDetailsService userDetails;
	final LogoAndSignature logoAndSignature;
	final RailtoRoofConnectionSetting railtoRoofConnectionSetting;
	final AuthenticationService authService;
	final LicenseSettingService licenseSetting;


	public UserController(UserInformationService userInformationService, RoleService roleService,
			MailingSession mailingSession, HttpSession httpSession, HistoryActivityService historyActivityService,
			MailingSettingRepository mailingRepo, UserSettingService userSetting, UserDetailsService userDetails,
			LogoAndSignature logoAndSignature, RailtoRoofConnectionSetting railtoRoofConnectionSetting,
			AuthenticationService authService, LicenseSettingService licenseSetting) {
		super();
		this.userInformationService = userInformationService;
		this.roleService = roleService;
		this.mailingSession = mailingSession;
		this.httpSession = httpSession;
		this.historyActivityService = historyActivityService;
		this.mailingRepo = mailingRepo;
		this.userSetting = userSetting;
		this.userDetails = userDetails;
		this.logoAndSignature = logoAndSignature;
		this.railtoRoofConnectionSetting = railtoRoofConnectionSetting;
		this.authService = authService;
		this.licenseSetting = licenseSetting;
	}

	//	@ApiOperation(value = "This function runs continiously running (every 10 sec) to check the user's session", response = Boolean.class)
	@GetMapping("/checkSessionExpired")
	public Boolean checkSessionExpired() throws ResourceNotFoundException {
		MailingSetting m = mailingRepo.findById(1).orElseThrow(
				() -> new ResourceNotFoundException("Mailing Setting not found"));
		session = mailingSession.getSession(m.getEmail(),m.getPassword());
		try {
			if ((String) httpSession.getAttribute("ctdUser") == null) {
				return false;
			} else
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

//	@ApiOperation(value = "Return the list of all deleted users & their access (solarPermit and siteSurvey)", response = LoginResult.class)
	@GetMapping("/getAllDeletedUsers")
	public List<LoginResult> getAllDeletedUsers() throws Exception {
		return userInformationService.getAllDeletedUsers();
	}

//	@ApiOperation(value = "Return the list of all non deleted users & their access (solarPermit and siteSurvey)", response = LoginResult.class)
	@GetMapping("/getAllNonDeletedUsers")
	public List<LoginResult> getAllNonDeletedUsers() throws Exception {
		return userInformationService.getAllNonDeletedUsers();
	}

//	@ApiOperation(value = "Get the list of users that are not deleted and are active (all of them but nuagetechnologies-tn email domain, ordered by first name)", response = LoginResult.class)
	@GetMapping("/getAllUserActivatedNotDel")
	public List<UsersEntityResult> getAllUserActivatedNotDel() throws Exception {
		return userInformationService.getAllUserActivatedNotDel();
	}

//	@ApiOperation(value = "Returns the necessary User's infos from AuthentificationEntity, ContractorInformationEntity and UserSettingEntity", response = EditUserInformations.class)
	@PostMapping("/getUser")
	public EditUserInformations getUser(@RequestBody Long id) throws Exception {

		return userDetails.getUser(id);

	}

	@PostMapping("/editRole")
	public String editRole(@RequestBody ChangeRoleModel changeRoleModel) throws Exception {
		return roleService.ediUsertRole(changeRoleModel);

	}

//	@ApiOperation(value = "Updates the User's Email address: returns success if OK , fail if KO", response = String.class)
	@PostMapping("/changeUserEmail/{idUser}")
	public String changeUserEmail(@PathVariable Long idUser, @RequestBody String userEmail) throws Exception {
		return userDetails.changeUserEmail(idUser, userEmail);

	}

	@PostMapping("/editUserContact")
	public String editUserContact(@RequestBody EditUserModel eum) throws Exception {
		return userDetails.EditUserContact(eum.getEditUserInformations(), eum.getIpUser(), eum.getTimeZone(),
				eum.getIduser());

	}

	@PostMapping("/editUserSetting")
	public String editUserSetting(@RequestBody EditUserModel eum) throws Exception {
		return userSetting.editUserSetting(eum.getEditUserInformations(), eum.getIpUser(), eum.getTimeZone(),
				eum.getIduser());

	}

	@PostMapping("/deleteUser")
	public String deleteUser(@RequestBody Long id) throws Exception {
		return userInformationService.deleteUser(id);

	}

//	@ApiOperation(value = "Restore a user that was deleted: if another user has been created with the same email, send a warning", response = String.class)
	@PostMapping("/restoreUser")
	public String restoreUser(@RequestBody ActivateUserModel rcm) throws Exception {
		Long userId = ((Number) rcm.getObjectOne()).longValue();
		return userInformationService.restoreUser(userId, rcm.getIpAdress(), rcm.getTimeZone(), rcm.getIdUser(),
				rcm.getSolarPermit(), rcm.getSiteSurvey());

	}

//	@ApiOperation(value = "Activate a user: set activate to true and save the settings ", response = String.class)
	@PostMapping("/activateUser")
	public String activateUser(@RequestBody ActivateUserModel rcm) throws Exception {
		Long userId = ((Number) rcm.getObjectOne()).longValue();
		return userInformationService.activateUser(userId, rcm.getIpAdress(), rcm.getTimeZone(), rcm.getIdUser(),
				rcm.getSolarPermit(), rcm.getSiteSurvey());

	}

	// 07-26-2019: M.A : CR-2622 update user profile
	@PostMapping("/updateUserAccess")
	public String updateUserAccess(@RequestBody String[] access) throws Exception {
		return userDetails.updateUserAccess(Long.valueOf(access[0]), access[1], access[2]);

	}

	@PostMapping("/changingPassword")
	public ResponseEntity<StringModelResult> changingPassword(@RequestBody ChangePasswordModel cpm) throws Exception {

		String str = authService.changingPassword(cpm.getIdUser(), cpm.getNewPassword(),
				cpm.getOldPassword());
		StringModelResult st = new StringModelResult(str);
		return new ResponseEntity<StringModelResult>(st, HttpStatus.OK);

	}

	@PostMapping("/checkLogoAndSignature")
	public HashMap<String, List<String>> checkLogoAndSignature(@RequestBody Long userId) throws Exception {
		return logoAndSignature.checkIamges(userId);
	}

	@PostMapping("/getUserLicSections")
	public List<ContractorLicSectionModel> getUserLicSections(@RequestBody Long id) throws Exception {

		return licenseSetting.getUserLicSections(id);

	}

	@PostMapping("/updateUserLicSection")
	public String updateUserLicSection(@RequestBody UserLicSectionModel userLicSections) throws Exception {
		return licenseSetting.updateUserLicSection(userLicSections);
	}

	@PostMapping("/getRoofModels")
	public List<RoofAttachmentModel> getRoofModels(@RequestBody Long iduser) throws Exception {
		return railtoRoofConnectionSetting.getRoofModels(iduser);
	}

	@PostMapping("/saveRoofAttachment/{idUser}")
	public String saveRoofAttachment(@RequestBody RoofTypeAttachementModel roofs, @PathVariable Long idUser)
			throws Exception {
		return railtoRoofConnectionSetting.saveRoofAttachment(roofs, idUser);
	}

	@PostMapping("/getRoofAttachment")
	public RoofAttachmentModel getRoofAttachment(@RequestBody Long idUser) throws Exception {
		return railtoRoofConnectionSetting.getRoofAttachment(idUser);
	}

	@PostMapping("/getUserRole")
	public String getUserRole(@RequestBody Long idUser) throws Exception {
		return userInformationService.getUserRole(idUser);

	}

	@PostMapping("/getUserSettingAccess")
	public UserSettingAccess getUserSettingAccess(@RequestBody Long idUser) throws Exception {
		return userDetails.getUserSettingAccess(idUser);
	}

	@PostMapping("/deleteLogo")
	public String deleteLogo(@RequestBody Long iduser) throws Exception {
		return logoAndSignature.deleteLogo(iduser);
	}

	@PostMapping("/deleteSignature")
	public String deleteSignature(@RequestBody Long iduser) throws Exception {
		return logoAndSignature.deleteSignature(iduser);
	}

}
