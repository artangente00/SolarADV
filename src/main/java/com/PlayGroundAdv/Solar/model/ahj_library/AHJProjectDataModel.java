package com.PlayGroundAdv.Solar.model.ahj_library;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AHJProjectDataModel {

	private String asceStandard;
	private String windSpeed;
	private String snowLoad;
	private String exposureCategory;
	private String elevation;
	private String highTemp04;
	private String highTemp02;
	private String extremeMinimum;
	private String aCDisconnectRequired;
	private String isPVMeterRequired;
	private String pvMeterLocationFromLtToRt;
	private String reqElecEngStampWhenSizeIs;//col12_18
	private String reqElectEngStampForGroundMountSystems;//col12_19
}
