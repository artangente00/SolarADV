package com.PlayGroundAdv.Solar.model.project;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class DelayArchiveRequest {

	private Long id;
	private String projectName;
	private String owner;
	private Date lastUpdate;
	private String archiveStatus;
	private String delayBy;
	
}
