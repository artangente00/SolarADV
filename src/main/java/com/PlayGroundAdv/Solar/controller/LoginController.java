package com.PlayGroundAdv.Solar.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlayGroundAdv.Solar.model.ForgetPasswordRequest;
import com.PlayGroundAdv.Solar.model.LoginFour;
import com.PlayGroundAdv.Solar.model.LoginModel;
import com.PlayGroundAdv.Solar.model.ResetFinalStepModelRequest;
import com.PlayGroundAdv.Solar.model.StringModelResult;
import com.PlayGroundAdv.Solar.model.VerifyPasswordModelRequest;
import com.PlayGroundAdv.Solar.model.project.ProjectsChart;
import com.PlayGroundAdv.Solar.security.JwtUtils;
import com.PlayGroundAdv.Solar.service.project.AnalyticService;
import com.PlayGroundAdv.Solar.service.user_management.AuthenticationService;

@RestController
@RequestMapping("/login")
//@Api(value = "/login", description = "Operations pertaining to products in Online Store")
public class LoginController {
	private static final String JWT_TOKEN = "JWT_TOKEN";

	@Autowired
	private Environment env;

	final AuthenticationService authService;
	final AnalyticService analyticService;
	private final JwtUtils jwtUtils;

	public LoginController(AuthenticationService authService,
			AnalyticService analyticService, JwtUtils jwtUtils) {
		super();
		this.authService = authService;
		this.analyticService = analyticService;
		this.jwtUtils = jwtUtils;
	}

	//	@ApiOperation(value = "Reset Password, generate a temporary password for user and sends it by email")
	@PostMapping("/resetPasswrod")
	public StringModelResult resetPasswrod(@RequestBody ForgetPasswordRequest forgetPasswordRequest)
			throws UnsupportedEncodingException {
		return authService.resetPassword(forgetPasswordRequest.getEmail(),
				forgetPasswordRequest.getIpAdress(), forgetPasswordRequest.getTimeZone());
	}

	//	@ApiOperation(value = "Verifying if the inserted temporary password is correct (If it's correct set it to null and let the user reset his actual password)")
	@PostMapping("/verifyPassword")
	public ResponseEntity<StringModelResult> verifyPassword(
			@RequestBody VerifyPasswordModelRequest verifyPasswordModelRequest) {
		return new ResponseEntity<StringModelResult>(authService.verifyPassword(
				verifyPasswordModelRequest.getMailUser(), verifyPasswordModelRequest.getTempPsw()), HttpStatus.OK);
	}

	//	@ApiOperation(value = "Save the new user's password")
	@PostMapping("/resetFinalStep")
	public ResponseEntity<StringModelResult> resetFinalStep(
			@RequestBody ResetFinalStepModelRequest resetFinalStepModelRequest) {
		return new ResponseEntity<StringModelResult>(authService.resetFinalStep(
				resetFinalStepModelRequest.getNewPsw(), resetFinalStepModelRequest.getEmail(),
				resetFinalStepModelRequest.getIpAdress(), resetFinalStepModelRequest.getTimeZone()), HttpStatus.OK);
	}


//	@ApiOperation(value = "Logs in user if the credentials are correct otherwise returns an empty LoginModel", notes = "/login/getLogin")
	@PostMapping("/getLogin")
	public ResponseEntity<LoginModel> getLogin(@RequestBody LoginFour loginFour, HttpServletResponse response) {
		final LoginModel loginModel = authService.getLoged(loginFour, loginFour.getIpAdress(),
				loginFour.getTimeZone());

		final Cookie jwtCookie = createJwtCookie(loginModel.getJwt());
		response.addCookie(jwtCookie);
		loginModel.setJwt(StringUtils.EMPTY);

		return new ResponseEntity<>(loginModel, HttpStatus.OK);
	}

	
	@PostMapping("/getCountProjectPerDayPerUser")
	public List<ProjectsChart> getCountProjectPerDayPerUser(@RequestBody Long idUser) {
		return analyticService.getCountProjectPerDayPerUser(idUser);
	}
	
	@GetMapping("/getCountProjectPerDay")
	public List<ProjectsChart> getCountProjectPerDay() {
		return analyticService.getCountProjectPerDay();
	}
	
//	@ApiOperation(value = "Displays the evolution of number of permits through time (used for the chart in the login page)", notes = "/login/getLogin")
	@GetMapping("/getAllPermitForChart")
	public List<List<String>> getAllPermitForChart() {
		return analyticService.getAllPermitForChart();
	}

	@GetMapping("/refresh-token")
	public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
		String expiredToken = Arrays.stream(request.getCookies())
				.filter(cookie -> cookie.getName().equals(JWT_TOKEN))
				.findFirst().map(Cookie::getValue)
				.orElse(null);

		final String jwt = jwtUtils.refreshToken(expiredToken);
		final Cookie jwtCookie = createJwtCookie(jwt);
		response.addCookie(jwtCookie);

		return "jwt-token has been refreshed";
	}

	private Cookie createJwtCookie(String jwt){
		final boolean secure = Boolean.parseBoolean(env.getProperty("server.tomcat.protocol-header-https-value"));

		Cookie jwtCookie = new Cookie(JWT_TOKEN, jwt);
		jwtCookie.setHttpOnly(secure);
		jwtCookie.setSecure(secure);
		jwtCookie.setMaxAge(JwtUtils.JWT_TOKEN_VALIDITY);
		jwtCookie.setPath("/");

		return jwtCookie;
	}
}
