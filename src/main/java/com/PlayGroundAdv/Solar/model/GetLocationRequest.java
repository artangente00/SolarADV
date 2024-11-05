package com.PlayGroundAdv.Solar.model;

public class GetLocationRequest {
	public LocationModelRequest locationModelRequest;
	public Long idUser;
	public Integer firstLocation;
	public Integer lastLocation;

	public GetLocationRequest() {
		super();
	}

	public GetLocationRequest(LocationModelRequest locationModelRequest, Long idUser, Integer firstLocation,
			Integer lastLocation) {
		super();
		this.locationModelRequest = locationModelRequest;
		this.idUser = idUser;
		this.firstLocation = firstLocation;
		this.lastLocation = lastLocation;
	}

	public LocationModelRequest getLocationModelRequest() {
		return locationModelRequest;
	}

	public void setLocationModelRequest(LocationModelRequest locationModelRequest) {
		this.locationModelRequest = locationModelRequest;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public Integer getFirstLocation() {
		return firstLocation;
	}

	public void setFirstLocation(Integer firstLocation) {
		this.firstLocation = firstLocation;
	}

	public Integer getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Integer lastLocation) {
		this.lastLocation = lastLocation;
	}

	@Override
	public String toString() {
		return "GetLocationRequest [locationModelRequest=" + locationModelRequest + ", idUser=" + idUser
				+ ", firstLocation=" + firstLocation + ", lastLocation=" + lastLocation + "]";
	}

}
