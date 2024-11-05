package com.PlayGroundAdv.Solar.model;

import java.util.List;

import com.PlayGroundAdv.Solar.entity.SsheetLibraryEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SsheetRackingModel {

	private Long id;
	private String roofType;
	private String roofTypeOther;
	private List<Long> roofMaterialType;
	private String rackingManufacturer;
	private String rackingModel;
	private String roofManufacturer;
	private String roofModel;
	private String mountingType;
	private String flashingManufacturer;
	private String sheetNumber;
	private String quadNumber;
	private String detailsHeading;
	private String ahj;
	private String utilityCompany;
	private Long ssheet;
	private String pdfname;
	private Boolean uploadSheet;
	private Boolean flashing;
	private String state;
	private String stanchionType;
	
//	Constuctor without Roof Material
	public SsheetRackingModel(Long id, String roofType, String roofTypeOther, String rackingManufacturer,
			String rackingModel, String roofManufacturer, String roofModel, String mountingType,
			String flashingManufacturer, String sheetNumber, String quadNumber, String detailsHeading, String ahj,
			String utilityCompany, Long ssheet, String pdfname, Boolean uploadSheet, Boolean flashing, String state,
			String stanchionType) {
		super();
		this.id = id;
		this.roofType = roofType;
		this.roofTypeOther = roofTypeOther;
		this.rackingManufacturer = rackingManufacturer;
		this.rackingModel = rackingModel;
		this.roofManufacturer = roofManufacturer;
		this.roofModel = roofModel;
		this.mountingType = mountingType;
		this.flashingManufacturer = flashingManufacturer;
		this.sheetNumber = sheetNumber;
		this.quadNumber = quadNumber;
		this.detailsHeading = detailsHeading;
		this.ahj = ahj;
		this.utilityCompany = utilityCompany;
		this.ssheet = ssheet;
		this.pdfname = pdfname;
		this.uploadSheet = uploadSheet;
		this.flashing = flashing;
		this.state = state;
		this.stanchionType = stanchionType;
	}

	

	

}
