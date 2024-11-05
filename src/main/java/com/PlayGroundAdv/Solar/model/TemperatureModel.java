package com.PlayGroundAdv.Solar.model;

import com.PlayGroundAdv.Solar.entity.TemperatureLibraryEntity;

public class TemperatureModel {

	private TemperatureLibraryEntity temperature;
	private Long userID;
	private String userName;

	public TemperatureModel() {
		super();
	}

	public TemperatureModel(TemperatureLibraryEntity temperature, Long userID) {
		super();
		this.temperature = temperature;
		this.userID = userID;
	}

	public TemperatureLibraryEntity getTemperature() {
		return temperature;
	}

	public void setTemperature(TemperatureLibraryEntity temperature) {
		this.temperature = temperature;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
