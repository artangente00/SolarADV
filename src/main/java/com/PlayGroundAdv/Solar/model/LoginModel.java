package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

//Used when a user logs in to the application
@Getter
@Setter
public class LoginModel {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String contractorCode;
	private Boolean active;
	private String role;
	private Boolean deleted;
	public Boolean solarPermit;
	public Boolean siteSurvey;
	public Boolean hasSettingAccess;
	public Boolean hasAhjMgtAccess;
	private String company;
	private String jwt;
	private int jwtExpirationDelay;

	public LoginModel() {
		super();
	}

	public LoginModel(Long id, String firstName, String lastName, String email, String contractorCode, Boolean active,
			String role, Boolean deleted, Boolean solarPermit, Boolean siteSurvey, Boolean hasSettingAccess, Boolean hasAhjMgtAccess,
			String company) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contractorCode = contractorCode;
		this.active = active;
		this.role = role;
		this.deleted = deleted;
		this.solarPermit = solarPermit;
		this.siteSurvey = siteSurvey;
		this.hasSettingAccess = hasSettingAccess;
        this.hasAhjMgtAccess = hasAhjMgtAccess;
		this.company = company;
	}

	public LoginModel(Long id, String firstName, String lastName, String email, String contractorCode, Boolean active,
			String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contractorCode = contractorCode;
		this.active = active;
		this.role = role;
	}


}
