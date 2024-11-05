package com.PlayGroundAdv.Solar.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SRsheetsModel {

	
	private Long id;
	private String pdfName;
	private Date lastUpdate;
	private String addedBy;
	private Boolean isDeleted;
	private Date deletedOn;
	private String deletedBy;
	private String updatedBy;
	
	
}
