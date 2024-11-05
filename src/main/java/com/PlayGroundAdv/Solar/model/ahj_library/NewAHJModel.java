package com.PlayGroundAdv.Solar.model.ahj_library;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class NewAHJModel {
	
	private String ahj;
	private String county;
	private String state;
	private Boolean incorporated;
	private Long addedBy;
	
}
