package com.PlayGroundAdv.Solar.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RailRackingModel {

	private Long id;
	private String manufacturer;
	private String model;
	private String weight;
	private List<String> mountType;
	private Boolean isDeleted;
	private Boolean isShown;
	private String owner;
	private String lastUpdate;
	private String idOwner;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private Long pvRailType;
	private Long pvRailSpliceType;
	private Long midClamp;
	private Long endClamp;
	private Boolean integratedStanchion;
	private String stanchionMfg;
	private String stanchionMfgMappingValue;
	private String stanchionModel;
	private String stanchionModelMappingValue;
	private List<Long> allowedRoofMaterial;
	private Boolean integratedFlashing;
	
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;


}
