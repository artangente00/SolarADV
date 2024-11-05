package com.PlayGroundAdv.Solar.model;

import java.util.List;

import com.PlayGroundAdv.Solar.entity.TemperatureLibraryEntity;

public class TemperatureListModel {

	private List<TemperatureLibraryEntity> temperatureList;
	private Long userID;
	private String userName;

	public TemperatureListModel() {
		super();
	}

	public TemperatureListModel(List<TemperatureLibraryEntity> temperatureList, Long userID) {
		super();
		this.temperatureList = temperatureList;
		this.userID = userID;
	}

	public List<TemperatureLibraryEntity> getTemperatureList() {
		return temperatureList;
	}

	public void setTemperatureList(List<TemperatureLibraryEntity> temperatureList) {
		this.temperatureList = temperatureList;
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
