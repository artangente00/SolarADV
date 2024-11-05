package com.PlayGroundAdv.Solar.model.libraries;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomizeSheetRequest {

	private Long id;
	private String pdfName;
	private String utilityCompany;
	private String individualAHJ;
	private List<String> ahj;
	private String userSheet;
	private String users;
	private String state;
	private List<String> states;
	private String basicSystemType;
	private String basicSystemTypeOther;
	private String inverterTechnology;
	private String batteryInSystem;
	private String roofType;
	private String roofTypeOther;
	private String roofingMaterialType;
	private String railRackingModel;
	private String electEngStructEng;
	private String necCode;
	private String ifcCode;
	private Date lastUpdate;
	private Boolean isDeleted;
	private String addedBy;
	private String updatedBy;
	private Date deletedOn;
	private String deletedBy;

	private Integer size; 
	private Integer page;
}
