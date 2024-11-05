package com.PlayGroundAdv.Solar.model;

public class ActivateUserModel {

	private Object objectOne;
	private Object objectTwo;
	private String ipAdress;
	private String timeZone;
	private Long idUser;
	public Boolean solarPermit;
	public Boolean siteSurvey;

	public ActivateUserModel() {
		super();
	}

	public ActivateUserModel(Object objectOne, Object objectTwo, String ipAdress, String timeZone, Long idUser,
			Boolean solarPermit, Boolean siteSurvey) {
		super();
		this.objectOne = objectOne;
		this.objectTwo = objectTwo;
		this.ipAdress = ipAdress;
		this.timeZone = timeZone;
		this.idUser = idUser;
		this.solarPermit = solarPermit;
		this.siteSurvey = siteSurvey;
	}

	public Object getObjectOne() {
		return objectOne;
	}

	public void setObjectOne(Object objectOne) {
		this.objectOne = objectOne;
	}

	public Object getObjectTwo() {
		return objectTwo;
	}

	public void setObjectTwo(Object objectTwo) {
		this.objectTwo = objectTwo;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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

}
