package com.PlayGroundAdv.Solar.model.libraries;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeneratorDto {

	private Long id;
	private String manufacturer;
	private String model;
	private String manufacturerMappingValue;
	private String modelMappingValue;
	private Boolean isFavorite;
	private Integer ratedPower;
	private Integer maxContinuousOutput;
	private List<String> ratedVoltage;
	private List<String> fuelType;
	private Boolean integratedAutoTransferSwitch;
	private String emissionsComplianceRating;
	private Long addedTo;
	private Long addedBy;
}
