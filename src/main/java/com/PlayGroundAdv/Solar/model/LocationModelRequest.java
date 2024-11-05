package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LocationModelRequest {

	private String locationID;
	private String locationName;
	private String projectType;
	private Boolean favorite;
	private Boolean isDeleted;

}
