package com.PlayGroundAdv.Solar.controller;

import com.PlayGroundAdv.Solar.model.SetUserModelRequest;
import com.PlayGroundAdv.Solar.model.StringModelResult;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.user_management.AuthenticationService;
import com.PlayGroundAdv.Solar.service.user_management.UserInformationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/signup")
//@Api(value = "Sign up / register users Operations", description = "/signup")
public class SignupController {

	final UserInformationService userInformationService;
	final AuthenticationService authService;
    final MailingService mailService;

	

public SignupController(UserInformationService userInformationService, AuthenticationService authService, MailingService mailService) {
		super();
		this.userInformationService = userInformationService;
		this.authService = authService;
		this.mailService = mailService;
	}



	//	@ApiOperation(value = "Add a new user: Signup")
	@PostMapping("/setUser")
	public ResponseEntity<StringModelResult> setUser(@RequestBody SetUserModelRequest setUserModelRequest)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

		return new ResponseEntity<StringModelResult>(authService.setUser(setUserModelRequest),
				HttpStatus.OK);

	}
	
	@PostMapping("/addUser/{idUser}")
	public ResponseEntity<StringModelResult> addUser(@RequestBody SetUserModelRequest setUserModelRequest, @PathVariable Long idUser)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

		return new ResponseEntity<StringModelResult>(authService.addUser(setUserModelRequest, idUser),
				HttpStatus.OK);

	}
	
	@PostMapping("/setUserNotAllow")
	public ResponseEntity<StringModelResult> setUserNotAllow(@RequestBody SetUserModelRequest setUserModelRequest)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

		return new ResponseEntity<StringModelResult>(authService.setUserNotAllow(setUserModelRequest),
				HttpStatus.OK);

	}

}
