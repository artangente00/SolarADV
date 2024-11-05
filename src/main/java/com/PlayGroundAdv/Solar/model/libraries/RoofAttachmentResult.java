package com.PlayGroundAdv.Solar.model.libraries;

import java.util.Date;

import com.PlayGroundAdv.Solar.model.UsersEntityResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoofAttachmentResult {

	private Long id;
	private String manufacturer;
	private String model;
	private String weight;
	private String integrated;
	private String numberOfRoof;
	private String utilizeS100;
	private Boolean isDeleted;
	private String lastUpdate;
	private Boolean hasCorrectionRequest;
	private Boolean isFavorite;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private String allowedRoof;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private UsersEntityResult owner;
	//F.B CR-686
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;


}
