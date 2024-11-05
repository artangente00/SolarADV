package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectBatteryDto {
	
	private Long battery;
	private Integer batteryQuantity;
}
