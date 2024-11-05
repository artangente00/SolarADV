package com.PlayGroundAdv.Solar.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewRailRackingModel {

	private String manufacturer;
	private String model;
	private List<String> mountType;
	private String manufacturerMappingValue;
	private String modelMappingValue;

}
