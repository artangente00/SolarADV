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

public class PlansetDto {

	private Long id;
	private String pdfName;
	private String lastUpdate;
	private String type;
	private String lastUpdateBy;
	private String comment;
	
}
