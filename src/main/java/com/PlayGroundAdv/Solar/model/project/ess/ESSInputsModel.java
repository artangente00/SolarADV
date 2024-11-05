package com.PlayGroundAdv.Solar.model.project.ess;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ESSInputsModel {
	
	private Long id;
	
	private String value;
	
	private Integer left;

	private Integer top;

}
