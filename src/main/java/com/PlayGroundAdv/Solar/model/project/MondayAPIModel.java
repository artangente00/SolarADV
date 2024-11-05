package com.PlayGroundAdv.Solar.model.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class MondayAPIModel {

	private String formattedAddress;
	private String address;
	private String city;
	private String cityOther;
	private String state;
	private String postalCode;
	private Float laltitude;
	private Float longitude;
	private Boolean ess; //System will be paired with Battery Storage
	private String offGrid; //Grid-Tied Sys. Or Off-Grid/Standalone
	private Boolean eSolar; //There is an existing Solar system on the property
	private Boolean generator;
	private Boolean roofMounted;
	private Boolean groundMounted;
	private Long moduleId;
	private String strucEngi; //Structural Engineering Package Inquiry
	private Boolean elecEngi; //Please Provide the Required Electrical Stamp on this Plan Set

}
