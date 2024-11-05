package com.PlayGroundAdv.Solar.model.libraries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewComponentRequest {
	
	private Long id;
	private String manufacturer;
	private String model;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private Long addedTo;
	private Long addedBy;
}
