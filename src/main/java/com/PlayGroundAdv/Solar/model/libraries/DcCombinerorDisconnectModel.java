package com.PlayGroundAdv.Solar.model.libraries;

import java.util.Date;

import com.PlayGroundAdv.Solar.model.UsersEntityResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class DcCombinerorDisconnectModel {

	private Long id;
	private String manufacturer;
	private String model;
	private String ocpd;
	private String weight;
	private String nemaRating;
	private String maxInput;
	private String maxContiOutputCurrent;
	private String maxOutputCurrent;
	private String typeDc;
	private Boolean isDeleted;
	private Boolean isShown;
	private String owner;
	private String lastUpdate;
	private String dropdownOption;
	private String rsd;
	private Long idOwner;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private Boolean hasCorrectionRequest;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;

	
}
