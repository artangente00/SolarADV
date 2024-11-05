package com.PlayGroundAdv.Solar.service.user_management;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.LoginResult;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;

@Service
@Transactional
public class UserInformationService {

	final HistoryActivityService activityService;
	final NotificationEntityService notificationService;
	final PermitRepository permitRepo;
	final AuthenticationRepository authRepo;
	final UserSettingRepository userSettingRepo;

	public UserInformationService(HistoryActivityService activityService,
			NotificationEntityService notificationService, PermitRepository permitRepo,
			AuthenticationRepository authRepo, UserSettingRepository userSettingRepo) {
		super();
		this.activityService = activityService;
		this.notificationService = notificationService;
		this.permitRepo = permitRepo;
		this.authRepo = authRepo;
		this.userSettingRepo = userSettingRepo;
	}

	// Get all non deleted user
	public List<LoginResult> getAllNonDeletedUsers() {
		try {
			return authRepo.ListOfDeletedUsers(false);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}


	// CR-2225
	// Get the list of users that are not deleted and are active (all of them but
	// nuagetechnologies-tn email domain, ordered by first name)
	public List<UsersEntityResult> getAllUserActivatedNotDel() {
		try {
			return authRepo.allUsersByDeletedandActive(false, true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	// Get all deleted users
	public List<LoginResult> getAllDeletedUsers() {
		try {
			return authRepo.ListOfDeletedUsers(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}


	// Delete a user (Set deleted to true + Activated to false)
	public String deleteUser(Long id) {
		try {
			// search for the user to delete
			AuthentificationEntity user = authRepo.findById(id)
					.orElseThrow(() -> new Exception("Id not found in UserInformationService > deleteUser"));
			// find all permits created by the user we are about to delete
			List<PermitEntity> permits = permitRepo.findByAuthentificationEntityId(id);
			if (!permits.isEmpty()) {
				// set Deleted to true for all permits
				for (PermitEntity permit : permits) {
					permit.setDeleted(true);
				}
				permitRepo.saveAll(permits);
			}
			// set deleted to true and active to false
			user.setDeleted(true);
			user.setActive(false);
			authRepo.save(user);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	// Restore and activate a user if he is not already recreated with the same
	// email
	public String restoreUser(Long userId, String ipUser, String timeZone, Long idUser, boolean solarPermit, boolean siteSurvey) {
		try {

			// find the user to restore
			AuthentificationEntity user = authRepo.findById(idUser)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idUser));

			// Check if another user has already been created with the same email, and the
			// recreated user is actually active
			AuthentificationEntity userExist = authRepo.findByEmailIgnoreCaseAndDeletedIsFalse(user.getEmail());

			if (userExist != null) {
				activityService.recordActivity(userId, ipUser, timeZone,
						"Activate User;User id : " + idUser
								+ ";User alredy Recreated using the same email cant be restored;Activate failed",
						false, "", "", "");
				return "Another user has already been recreated with the same email,Please check your active users.";
			}

			if (user.getActive() == true && user.isDeleted() == false) {
				activityService.recordActivity(userId, ipUser, timeZone,
						"Activate User;User id : " + idUser + ";User alredy activated;Activate failed", false, "", "",
						"");
				return "User alredy activated";
			} else {
				UserSettingEntity userSetting = userSettingRepo.findByUserIdId(user.getId());
				user.setDeleted(false);
				user.setActive(true);
				userSetting.setSolarPermit(solarPermit);
				userSetting.setSiteSurvey(siteSurvey);
				authRepo.save(user);
				userSettingRepo.save(userSetting);

				notificationService.addNewNotif(userId, idUser, "Welcome", "Accounts",
						"Welcome",
						"Welcome to the portal. Please let us know if you have any questions. You will be able to click here for all project notifications in the future.",
						false);
				activityService.recordActivity(userId, ipUser, timeZone,
						"Activate User;User id : " + idUser + ";Activate Success", true, "", "", "");
				return user.getFirstName() + " " + user.getLastName();
			}
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(userId, ipUser, timeZone,
					"Activate User;User id : " + idUser + ";Technical problem;Activate failed", false, "", "", "");
			return "Error!! Something went wrong, Please try again later";
		}

	}

	//  activate a user 
	// email
	public String activateUser(Long userId, String ipUser, String timeZone, Long idUser, boolean solarPermit, boolean siteSurvey) {
		try {

			// find the user to activate
			AuthentificationEntity user = authRepo.findById(idUser)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + idUser));
			UserSettingEntity userSetting = userSettingRepo.findByUserIdId(user.getId());
			user.setActive(true);
			userSetting.setSolarPermit(solarPermit);
			userSetting.setSiteSurvey(siteSurvey);
			authRepo.save(user);
			userSettingRepo.save(userSetting);

			notificationService.addNewNotif(idUser, idUser, "Welcome", "Accounts", "Welcome",
					"Welcome to the portal. Please let us know if you have any questions. You will be able to click here for all project notifications in the future.",
					false);
			activityService.recordActivity(userId, ipUser, timeZone,
					"Activate User;User id : " + idUser + ";Activate Success", true, "", "", "");
			return user.getFirstName() + " " + user.getLastName();
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(userId, ipUser, timeZone,
					"Activate User;User id : " + idUser + ";Technical problem;Activate failed", false, "", "", "");
			return "Error!! Something went wrong, Please try again later";
		}

	}

	public String getUserRole(Long idUser) throws Exception {
		try {
			AuthentificationEntity user = authRepo.findById(idUser).get() ;
			return user.getRoleEntity().getDescription();
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

}
