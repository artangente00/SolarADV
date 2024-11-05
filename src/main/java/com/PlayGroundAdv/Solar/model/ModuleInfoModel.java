package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ModuleInfoModel {
	
	private Long id;
	private String make;
	private String model;
	private String length;
	private String width;
	private String depth;
	
}
