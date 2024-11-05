package com.PlayGroundAdv.Solar.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomizeSheetsModel {

	
	private Long id;
	private String pdfName;
	private String utilityCompany;
	private List<String> individualAHJ;
	private List<Long> users;
	private List<String> state;
	private String basicSystemType;
	private String basicSystemTypeOther;
	private String inverterTechnology;
	private Long batteryInSystem;
	private String batteryInSystemDisplay;
	private String roofType;
	private String roofTypeOther;
	private Long roofingMaterialType;
	private String roofingMaterialTypeDisplay;
	private Long railRackingModel;
	private String railRackingModelDisplay;
	private String electEngStructEng;
	private Date lastUpdate;
	private List<String> userNames;
	private String deletedBy;
	private Date deletedOn;
	private String updatedBy;
	private String necCode;
	private String ifcCode;

	private Integer size; 
	private Integer page;
}
