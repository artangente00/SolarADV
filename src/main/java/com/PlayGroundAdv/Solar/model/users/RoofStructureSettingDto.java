package com.PlayGroundAdv.Solar.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoofStructureSettingDto {
	
	private Long id;
	private String state;
	private String rafterTrussDesign;
	private String crossSectionSize;
	private String rafterTrussDesignOther;
	private String crossSectionSizeOther;
	private Long userId;
}
