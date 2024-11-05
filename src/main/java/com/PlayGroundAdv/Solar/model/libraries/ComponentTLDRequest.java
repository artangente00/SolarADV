package com.PlayGroundAdv.Solar.model.libraries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComponentTLDRequest {
	
	private Integer size; 
	private Integer page;
	private String subName;
	private Long idUser;
}
