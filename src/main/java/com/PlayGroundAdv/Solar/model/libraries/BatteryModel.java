package com.PlayGroundAdv.Solar.model.libraries;

import java.util.Date;

import com.PlayGroundAdv.Solar.model.UsersEntityResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class BatteryModel {

	private Long id;
	private Boolean isFav;
	private String manufacturer;
	private String model;
	private String outputVolts;
	private String hours;
	private String type;
	private Boolean essEnergy;
	private String notes;
	private String updated;
	private Boolean isDeleted;
	private UsersEntityResult owner;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;

	
}
