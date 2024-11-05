package com.PlayGroundAdv.Solar.model.libraries;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TLDSubNamesDto {	
	private Long id;
	private String subName;
	private String component;
	private String description;
	private Date lastUpdate;
	private Boolean isDeleted;
	private String addedBy;
	private String updatedBy;
	private Date deletedOn;
	private String deletedBy;
}
