package com.PlayGroundAdv.Solar.model.libraries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComponentPageTLDSheet {

	private Integer size; 
	private Integer page;
	private Long idUser;
	private String pdfName;
	private String pdfType;
	private String inverterTechnology;
	private Boolean deleted;
}
