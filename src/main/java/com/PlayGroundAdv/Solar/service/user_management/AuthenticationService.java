package com.PlayGroundAdv.Solar.service.user_management;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.NoResultException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ContractorInformationEntity;
import com.PlayGroundAdv.Solar.entity.RoleEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.LoginFour;
import com.PlayGroundAdv.Solar.model.LoginModel;
import com.PlayGroundAdv.Solar.model.SetUserModelRequest;
import com.PlayGroundAdv.Solar.model.StringModelResult;
import com.PlayGroundAdv.Solar.repositories.RoleRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.ContractorInformationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.security.JwtUtils;
import com.PlayGroundAdv.Solar.security.PasswordEncoderImpl;
import com.PlayGroundAdv.Solar.security.UserDetailsServiceImpl;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class AuthenticationService {

	final HistoryActivityService activityService;
	private final UserDetailsServiceImpl userDetailsService;
	final NotificationEntityService notifService;
	final EmailValidationService emailValidationService;
	final MailingService mailingService;
	final AuthenticationRepository authRepo;
	final UserSettingRepository userSettingRepo;
	final RoleRepository roleRepo;
	final ContractorInformationRepository contractorInfoRepo;
	private final PasswordEncoderImpl passwordEncoder;
	private final AuthenticationManager authenticationManager;
	final CheckValueTypesService checkValue;
	private final JwtUtils jwtUtils;

	public AuthenticationService(HistoryActivityService activityService, UserDetailsServiceImpl userDetailsService,
			NotificationEntityService notifService, EmailValidationService emailValidationService,
			MailingService mailingService, AuthenticationRepository authRepo, UserSettingRepository userSettingRepo,
			RoleRepository roleRepo, ContractorInformationRepository contractorInfoRepo,
			PasswordEncoderImpl passwordEncoder, AuthenticationManager authenticationManager,
			CheckValueTypesService checkValue, JwtUtils jwtUtils) {
		super();
		this.activityService = activityService;
		this.userDetailsService = userDetailsService;
		this.notifService = notifService;
		this.emailValidationService = emailValidationService;
		this.mailingService = mailingService;
		this.authRepo = authRepo;
		this.userSettingRepo = userSettingRepo;
		this.roleRepo = roleRepo;
		this.contractorInfoRepo = contractorInfoRepo;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.checkValue = checkValue;
		this.jwtUtils = jwtUtils;
	}

	public String changingPassword(Long idUser, String newPassword, String oldPassword) {
		AuthentificationEntity authentificationEntity = new AuthentificationEntity();
		try {
			authentificationEntity = authRepo.findById(idUser).orElse(null);
			if (authentificationEntity != null
					&& passwordEncoder.validatePassword(oldPassword, authentificationEntity.getPassword())) {
				authentificationEntity.setPassword(passwordEncoder.generateStrongPasswordHash(newPassword));
				authRepo.save(authentificationEntity);
				return "Done";
			} else {
				return "Password Incorrect";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}

	// Authentication function
	public LoginModel getLoged(LoginFour loginFour, String ipAdress, String timeZone) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginFour.getEmail(), loginFour.getPassword()));
		} catch (BadCredentialsException exception) {

			activityService.recordActivity(null, ipAdress, timeZone, "Login;User Not Found",
					false, "", "", "");
			throw new BadCredentialsException("Incorrect username or password! Please try again", exception);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginFour.getEmail());
		if (!userDetails.isEnabled()) {
			activityService.recordActivity(null, ipAdress, timeZone, "Login;User Not Activated",
					false, "", "", "");
			throw new DisabledException("User Not Activated!");
		}

		// Search for the user to authenticate
		AuthentificationEntity userToAuthenticate = authRepo
				.findByEmailIgnoreCaseAndDeletedIsFalse(loginFour.getEmail());

		// Update login date/time
		TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
//                TimeZone.setDefault(TimeZone.getTimeZone("Asia/Manila"));
		Calendar lastLogin = Calendar.getInstance();
		userToAuthenticate.setUserLastLogin(lastLogin.getTime());
		authRepo.save(userToAuthenticate);

		activityService.recordActivity(userToAuthenticate.getId(), ipAdress, timeZone,
				"Login;Login Success", true, "", "", "");
		// Return the user's information
		final String jwt = jwtUtils.generateToken(userDetails);
                System.out.println("JWT token: " + jwt); // Add this line for logging
		final LoginModel loginModel = authRepo.userInformations(loginFour.getEmail());
                System.out.println("Login Model: " + loginModel); // Add this line for logging
//                    if (loginModel == null) {
//                        // Handle the case where loginModel is null, for example:
//                        throw new IllegalStateException("Login model is null for email: " + loginFour.getEmail());
//                    }
		loginModel.setJwt(jwt);
		loginModel.setJwtExpirationDelay(JwtUtils.JWT_TOKEN_VALIDITY * 1000);

		return loginModel;
	}
	
	// Add a new user (Signup )
	public StringModelResult setUser(SetUserModelRequest setUserModelRequest) {

		try {
			AuthentificationEntity user = authRepo
					.findByEmailIgnoreCaseAndDeletedIsFalse(setUserModelRequest.getEmail());
			// Check if the user is already registered
			if (user != null) {
				if (user.getActive().equals(false)) {
					activityService.recordActivity(null, setUserModelRequest.getIpAdress(),
							setUserModelRequest.getTimeZone(),
							"Add user;error Account exist but waiting for activation;" + setUserModelRequest.getEmail()
									+ ";Add failed ",
							false, "", "", "");
					// Return that the email already exists if the user is found
					return new StringModelResult(
							" You are already registred! Please wait for your account activation. ");
				}
				activityService.recordActivity(null, setUserModelRequest.getIpAdress(),
						setUserModelRequest.getTimeZone(),
						"Add user;error Mail already exist;" + setUserModelRequest.getEmail() + ";Add failed ", false,
						"", "", "");
				// Return that the email already exists if the user is found
				return new StringModelResult(" Mail already exist ");
			} 
                        else {
				
				// Check if the email exists, is valid and respects the formatting
//				Boolean valideEmail = emailValidationService.validateEmail(setUserModelRequest.getEmail());
//				if (Boolean.FALSE.equals(valideEmail)) {
//
//					//A.B CR-3907 Send verification code
//					return  new StringModelResult(mailingService.sendingMailVerification(setUserModelRequest.getEmail()));
//				} 
//                                else {
					
//					RoleEntity role = roleRepo.findByDesignationNameAndIsSuperUserIsFalse("A");
//					if (role == null) {
//						// return an error message if no role exists
//						activityService.recordActivity(null, setUserModelRequest.getIpAdress(),
//								setUserModelRequest.getTimeZone(),
//								"Add user;Add Error It seems that there was a technical problem, no role found issue.",
//								false, "", "", "");
//						return new StringModelResult("It seems that there was a technical problem, please try later.1");
//					}
					// If the user does not already exist within our DB and the email @ is an
					// existing Valid @ We create the new user
					user = new AuthentificationEntity();
					user.setEmail(setUserModelRequest.getEmail().toLowerCase());
					user.setPassword(
							passwordEncoder.generateStrongPasswordHash(setUserModelRequest.getPassword()));
					user.setContractorCode(setUserModelRequest.getContracCode());
					user.setFirstName(setUserModelRequest.getFirstName());
					user.setLastName(setUserModelRequest.getLastName());
//					user.setRoleEntity(role);
					user.setDeleted(false);
					user.setActive(false);
					user.setDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
					authRepo.save(user);

					// A.B 11-06: Save User Information
					ContractorInformationEntity userInformation = new ContractorInformationEntity();
					userInformation.setAuthentificationEntity(user);
					userInformation.setLicenseNumber(setUserModelRequest.getContracCode());
					userInformation.setCompPhoneNum(setUserModelRequest.getPhoneNumber());
					contractorInfoRepo.save(userInformation);

					UserSettingEntity userSetting = new UserSettingEntity();
					userSetting.setUserId(user);
					userSetting.setSolarPermit(setUserModelRequest.getSolarPermit());
					userSetting.setSiteSurvey(setUserModelRequest.getSiteSurvey());
					userSetting.setArchiveAllowed(true);
					userSetting.setArchiveDelay(18);
					userSettingRepo.save(userSetting);
					
					newUserNotification(user, setUserModelRequest);
					return new StringModelResult(
							"Your Account Has Been Created Successfully. Approval Takes a Few Hours. For a Quicker Approval Time, Email Us at Accounts@AdvPermits.com");
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(null, setUserModelRequest.getIpAdress(),
				setUserModelRequest.getTimeZone(),
				"Add user;Add Error It seems that there was a technical problem, please try later.", false, "",
				"", "");
			return new StringModelResult("It seems that there was a technical problem, please try later.2");
		}
	}
	
	public StringModelResult setUserNotAllow(SetUserModelRequest setUserModelRequest) {
		try {
		
			AuthentificationEntity user = authRepo
					.findByEmailIgnoreCaseAndDeletedIsFalse(setUserModelRequest.getEmail());
		RoleEntity role = roleRepo.findByDesignationNameAndIsSuperUserIsFalse("A");
		if (role == null) {
			// return an error message if no role exists
			activityService.recordActivity(null, setUserModelRequest.getIpAdress(),
					setUserModelRequest.getTimeZone(),
					"Add user;Add Error It seems that there was a technical problem, no role found issue.",
					false, "", "", "");
			return new StringModelResult("It seems that there was a technical problem, please try later.");
		}
		// If the user does not already exist within our DB and the email @ is an
		// existing Valid @ We create the new user
		user = new AuthentificationEntity();
		user.setEmail(setUserModelRequest.getEmail().toLowerCase());
		user.setPassword(
				passwordEncoder.generateStrongPasswordHash(setUserModelRequest.getPassword()));
		user.setContractorCode(setUserModelRequest.getContracCode());
		user.setFirstName(setUserModelRequest.getFirstName());
		user.setLastName(setUserModelRequest.getLastName());
		user.setRoleEntity(role);
		user.setDeleted(false);
		user.setActive(false);
		user.setDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
		authRepo.save(user);

		// A.B 11-06: Save User Information
		ContractorInformationEntity userInformation = new ContractorInformationEntity();
		userInformation.setAuthentificationEntity(user);
		userInformation.setLicenseNumber(setUserModelRequest.getContracCode());
		userInformation.setCompPhoneNum(setUserModelRequest.getPhoneNumber());
		contractorInfoRepo.save(userInformation);

		UserSettingEntity userSetting = new UserSettingEntity();
		userSetting.setUserId(user);
		userSetting.setSolarPermit(setUserModelRequest.getSolarPermit());
		userSetting.setSiteSurvey(setUserModelRequest.getSiteSurvey());
		userSettingRepo.save(userSetting);
		
		newUserNotification(user, setUserModelRequest);
		return new StringModelResult(
				"Your Account Has Been Created Successfully. Approval Takes a Few Hours. For a Quicker Approval Time, Email Us at Accounts@AdvPermits.com");
			
	} catch (Exception e) {
		e.printStackTrace();
		activityService.recordActivity(null, setUserModelRequest.getIpAdress(),
			setUserModelRequest.getTimeZone(),
			"Add user;Add Error It seems that there was a technical problem, please try later.", false, "",
			"", "");
		return new StringModelResult("It seems that there was a technical problem, please try later.");
	}
	}
	
	
	public void newUserNotification(AuthentificationEntity user, SetUserModelRequest setUserModelRequest) {
		try {
			String access = "";
			if (checkValue.Equals(setUserModelRequest.getSolarPermit(), true)) {
				access = "Solar Permit Plan Sets";
				if (checkValue.Equals(setUserModelRequest.getSiteSurvey(), true)) {
					access = access + " and Site Survey";
				}
			} else {
				if (checkValue.Equals(setUserModelRequest.getSiteSurvey(), true)) {
					access = "Site Survey";
				}

			}
			
			notifService.addNewNotif(user.getId(), 0L, "Activate Account Request", "Accounts",
					"Activate Account Request",
					"Please activate : " + user.getFirstName() + " " + user.getLastName()
							+ " has submitted an account request for " + access + " access.",
					true);

			if (user.getEmail().contains("nuagetechnologies") || user.getEmail().contains("arij")) {
				mailingService.sendingNUATNRegistrationMail("New Contractor request an account",
						"Please activate: " + user.getFirstName() + " " + user.getLastName()
								+ " has submitted an account request for " + access + " access.");

			} else {
				mailingService.sendingRegistrationMail("New Contractor request an account",
						"Please activate: " + user.getFirstName() + " " + user.getLastName()
								+ " has submitted an account request for " + access + " access.");
			}
			activityService.recordActivity(user.getId(), setUserModelRequest.getIpAdress(),
					setUserModelRequest.getTimeZone(), "Add user;Add Success ", true, "", "", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Reset Password, generate a temporary password for user and sends it by email
	 */
	public StringModelResult resetPassword(String email, String ipUser, String timeZone)
			throws UnsupportedEncodingException {
		try {
			AuthentificationEntity user = authRepo.findByEmailIgnoreCaseAndDeletedIsFalse(email);
			if (user != null) {
				String temppwd = RandomStringUtils.randomAlphanumeric(11);
				String body = "Your temporary password is : " + temppwd + "\nPlease use it to reset your password.";
				String object = "Password reset - ADV Solar Team";
				mailingService.sendingMail(email, object, body);
				user.setTempPwd(temppwd);
				authRepo.save(user);

				activityService.recordActivity(null, ipUser, timeZone,
						"request for reset password;Mail : " + email + ";Request Success", true, "", "", "");
				return new StringModelResult("temporary password created");
			} else {
				activityService.recordActivity(null, ipUser, timeZone,
						"request for reset password;Mail : " + email + " no user found with this email;Request failed",
						false, "", "", "");
				return new StringModelResult("Mail does not exist!");
			}

		} catch (NoResultException | NullPointerException e) {
			e.printStackTrace();
			activityService.recordActivity(null, ipUser, timeZone,
					"request for reset password;Mail : " + email + " an error occured;Request failed", false, "", "",
					"");
			return new StringModelResult("Mail does not exist!");

		}
	}

	/*
	 * Verifying if the inserted temporary password is correct (If it's correct set
	 * it to null and let the user reset his actual password)
	 */
	public StringModelResult verifyPassword(String email, String temppwd) {
		try {
			AuthentificationEntity user = authRepo.findByEmailIgnoreCaseAndDeletedIsFalse(email);
			if (user != null && checkValue.Equals(user.getTempPwd(), temppwd)) {
				user.setTempPwd(null);
				authRepo.save(user);
				return new StringModelResult("Temporary password created, proceeding to next step.");
			} else {
				return new StringModelResult("Temporary password incorrect");
			}
		} catch (NoResultException | NullPointerException e) {
			e.printStackTrace();
			return new StringModelResult("User does not exist");
		}
	}

	// Save the new user's password
	public StringModelResult resetFinalStep(String newpwd, String email, String ipUser, String timeZone) {
		try {
			AuthentificationEntity user = authRepo.findByEmailIgnoreCaseAndDeletedIsFalse(email);
			user.setPassword(passwordEncoder.generateStrongPasswordHash(newpwd));
			authRepo.save(user);
			activityService.recordActivity(null, ipUser, timeZone,
					"Reset Password;User ID : " + user.getId() + ";Reset Success", true, "", "", "");
			return new StringModelResult("New Password created");
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(null, ipUser, timeZone,
					" error Reset Password;For mail : " + email + ";Reset fail", true, "", "", "");
			return new StringModelResult("User does not exist");
		}
	}
	
	// CR-1267
	public StringModelResult addUser(SetUserModelRequest setUserModelRequest, Long idUser) {

		try {
			AuthentificationEntity user = authRepo
					.findByEmailIgnoreCaseAndDeletedIsFalse(setUserModelRequest.getEmail());
			// Check if the user is already registered
			if (user != null) {
				if (user.getActive().equals(false)) {
					activityService.recordActivity(idUser, setUserModelRequest.getIpAdress(),
							setUserModelRequest.getTimeZone(),
							"Add user;error Account exist but waiting for activation;" + setUserModelRequest.getEmail()
									+ ";Add failed ",
							false, "", "", "");
					// Return that the email already exists if the user is found
					return new StringModelResult(" Account exist but waiting for activation ");
				}
				activityService.recordActivity(idUser, setUserModelRequest.getIpAdress(),
						setUserModelRequest.getTimeZone(),
						"Add user;error Mail already exist;" + setUserModelRequest.getEmail() + ";Add failed ", false,
						"", "", "");
				// Return that the email already exists if the user is found
				return new StringModelResult(" Mail already exist ");
				
			} else {
				
				RoleEntity role = roleRepo.findByDesignationNameAndIsSuperUserIsFalse("A");
				if (role == null) {
					// return an error message if no role exists
					activityService.recordActivity(idUser, setUserModelRequest.getIpAdress(),
							setUserModelRequest.getTimeZone(),
							"Add user;Add Error It seems that there was a technical problem, no role found issue.",
							false, "", "", "");
					return new StringModelResult("It seems that there was a technical problem, please try later.");
				}
					// If the user does not already exist within our DB and the email @ is an
					// existing Valid @ We create the new user
				user = new AuthentificationEntity();
				user.setEmail(setUserModelRequest.getEmail().toLowerCase().trim());
				user.setPassword(passwordEncoder.generateStrongPasswordHash(setUserModelRequest.getPassword()));
				user.setContractorCode(setUserModelRequest.getContracCode());
				user.setFirstName(setUserModelRequest.getFirstName());
				user.setLastName(setUserModelRequest.getLastName());
				user.setRoleEntity(role);
				user.setDeleted(false);
				user.setActive(true);
				user.setDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
				authRepo.save(user);

				ContractorInformationEntity userInformation = new ContractorInformationEntity();
				userInformation.setAuthentificationEntity(user);
				userInformation.setLicenseNumber(setUserModelRequest.getContracCode());
				userInformation.setCompPhoneNum(setUserModelRequest.getPhoneNumber());
				contractorInfoRepo.save(userInformation);

				UserSettingEntity userSetting = new UserSettingEntity();
				userSetting.setUserId(user);
				userSetting.setSolarPermit(setUserModelRequest.getSolarPermit());
				userSetting.setSiteSurvey(setUserModelRequest.getSiteSurvey());
				userSettingRepo.save(userSetting);
					
				mailingService.sendingMailNewUser(setUserModelRequest.getEmail(), setUserModelRequest.getPassword());
				activityService.recordActivity(idUser, setUserModelRequest.getIpAdress(),
						setUserModelRequest.getTimeZone(),"Add User;Success Add User", true, "", "", "");
					
				return new StringModelResult("Your Account Has Been Created Successfully.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			activityService.recordActivity(idUser, setUserModelRequest.getIpAdress(),
				setUserModelRequest.getTimeZone(),
				"Add user;Add Error It seems that there was a technical problem, please try later.", false, "",
				"", "");
			return new StringModelResult("It seems that there was a technical problem, please try later.");
		}
	}
}
