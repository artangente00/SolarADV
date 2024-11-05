package com.PlayGroundAdv.Solar.model.libraries;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EquipmentVerificationModel {
	
	private String manufacturer;
	private String model;
	private String addedBy;
	private Date addedOn;
	private String firstUpdater;
	private String secondUpdater;
	private String thirdUpdater;
	private String verifiedBy;
	private Date verifiedOn;
}
