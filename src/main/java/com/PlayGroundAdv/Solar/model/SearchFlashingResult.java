package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class SearchFlashingResult {

	private Long id;
	private Boolean isFav;
	private String manufacturer;
	private String model;
	private String mappedValue;
	private String weight;
	private String updated;
	private Boolean isDeleted;
	private UsersEntityResult owner;
	private Boolean hasCorrectionRequest;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	private String fullName;
	

	//F.B CR-686
	private UsersEntityResult firstUpdater;
	private UsersEntityResult secondUpdater;
	private UsersEntityResult thirdUpdater;
	private UsersEntityResult verifiedBy;
	private Boolean isVerified;
	private Date dateVerification;


	public SearchFlashingResult(Long id, String manufacturer, String model, String mappedValue, String weight,
			String updated, Boolean isDeleted, UsersEntityResult owner, Boolean hasCorrectionRequest,
			String eroneousContent, String eroneousContentOther, String eroneousDescription, String fullName) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.mappedValue = mappedValue;
		this.weight = weight;
		this.updated = updated;
		this.isDeleted = isDeleted;
		this.owner = owner;
		this.hasCorrectionRequest = hasCorrectionRequest;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
		this.fullName = fullName;
	}
}
