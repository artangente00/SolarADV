package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AcDisconnectModel {

	private Long id;
	private String manufacturer;
	private String model;
	private String disconnectDeviceType;
	private String ratedOperationalVoltage;
	private String ratedCurrent;
	private String numberOfPoles;
	private String nemaRating;
	private String maxInput;
	private String qtyOfFuse;
	private String type;
	private String dropdownOption;
	private String lastUpdate;
	private Boolean isDeleted;
	private String owner;
	private Boolean isShown;
	private String idOwner;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private Boolean hasCorrectionRequest;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	
	//F.B CR-686
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;


}
