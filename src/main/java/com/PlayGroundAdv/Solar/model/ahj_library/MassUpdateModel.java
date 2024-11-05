package com.PlayGroundAdv.Solar.model.ahj_library;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class MassUpdateModel {
	
	private String value;
	private String code;
	private String state;
	private Long doneBy;
}
