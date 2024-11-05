package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter

public class SearchLeasePPAMeterResult {

	private Long id;
	private Boolean isFav;
	private String manufacturer;
	private String model;
	private String mappedValue;
	private String includeTLD;
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

	public SearchLeasePPAMeterResult(Long id, String manufacturer, String model, String mappedValue, String includeTLD,
			String updated, Boolean isDeleted, Boolean hasCorrectionRequest, String eroneousContent,
			String eroneousContentOther, String eroneousDescription, UsersEntityResult owner) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.mappedValue = mappedValue;
		this.includeTLD = includeTLD;
		this.updated = updated;
		this.isDeleted = isDeleted;
		this.owner = owner;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
	}
	
	
}
