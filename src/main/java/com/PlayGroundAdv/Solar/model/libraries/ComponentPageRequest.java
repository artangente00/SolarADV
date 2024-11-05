package com.PlayGroundAdv.Solar.model.libraries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComponentPageRequest {
	
	private Integer size; 
	private Integer page;
	private String manufacturer;
	private String model;
	private String wattage; // For PV Module
	private String deviceType; // For Disconnect Libraries
	private String ratedCurrent; // For Disconnect Libraries
	private String roofType; // For Roof Attachment
	private String integrated; // For Roof Attachment
	private Boolean isFavorite;
	private Boolean isDeleted;
	private Long idUser;
	private String mountingType; // for Rail Racking
	private String bts; // for Rail Racking
	private String typeRoof; // For Roof Material Type
	private String fieldName; // For RFIQuestion Library
	private String company; // For Engineers Library
	private String contact; // For Engineers Library
	private String licenseState; // For Engineers Library
	private Integer backFeed; // For Back-Feed Solar OCPD
	private Integer bfsOCPD; // For Back-Feed Solar OCPD
	private String param1; // For Engineers Library
	private String param2; // For Back-Feed Solar OCPD
	private String locationId; // For Check List Locations
	private String locationName;  // For Check List Locations
	private String projectType;  // For Check List Location
	public ComponentPageRequest(Integer size, Integer page) {
		super();
		this.size = size;
		this.page = page;
	}
	
}
