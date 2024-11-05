package com.PlayGroundAdv.Solar.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class DuplicateCustomizeSheetsModel {

	private Long id;
	private Long sheetId;
	private String pdfName;
	private String utilityCompany;
	private List<String> individualAHJ;
	private String users;
	private List<String> state;
	private String basicSystemType;
	private String inverterTechnology;
	private String batteryInSystem;
	private String roofType;
	private String roofingMaterialType;
	private String railRacking;
	private String electEngStructEng;
	private String necCode;
	private String ifcCode;
	private Boolean master;

}
