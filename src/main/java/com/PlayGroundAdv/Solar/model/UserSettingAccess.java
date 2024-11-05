package com.PlayGroundAdv.Solar.model;

public class UserSettingAccess {

	private Boolean solarPermit;
	private Boolean siteSurvey;
	private Boolean hasSettingAccess;

	public UserSettingAccess() {
		super();
	}

	public UserSettingAccess(Boolean solarPermit, Boolean siteSurvey, Boolean hasSettingAccess) {
		super();
		this.solarPermit = solarPermit;
		this.siteSurvey = siteSurvey;
		this.hasSettingAccess = hasSettingAccess;
	}

	public Boolean getSolarPermit() {
		return solarPermit;
	}

	public void setSolarPermit(Boolean solarPermit) {
		this.solarPermit = solarPermit;
	}

	public Boolean getSiteSurvey() {
		return siteSurvey;
	}

	public void setSiteSurvey(Boolean siteSurvey) {
		this.siteSurvey = siteSurvey;
	}

	public Boolean getHasSettingAccess() {
		return hasSettingAccess;
	}

	public void setHasSettingAccess(Boolean hasSettingAccess) {
		this.hasSettingAccess = hasSettingAccess;
	}

}
