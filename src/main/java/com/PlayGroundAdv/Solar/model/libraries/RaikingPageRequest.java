package com.PlayGroundAdv.Solar.model.libraries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class RaikingPageRequest {
	private Integer size; 
	private Integer page;
	private String quadNumber;
	private String roofType;
	private String roofManufacturer;
	private String rackingManufacturer;
	private String rackingModel;
	private String flashingManufacturer;
	private String sheetNumber;
	private String roofModel;
	private String roofTypeOther;
	
	private String rafterTrussSpacing; // For Spacing Mapping Sheet
	private String stanchionMaxSpacing; // For Spacing Mapping Sheet
}
