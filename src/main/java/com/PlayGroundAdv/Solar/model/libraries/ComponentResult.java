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
public class ComponentResult {

	private Long id;
	private String manufacturer;
	private String model;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private String addedBy;
	private Long addedById;
	private Boolean isFavorite;
	private Boolean hasCorrectionRequest;
	
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;

}
