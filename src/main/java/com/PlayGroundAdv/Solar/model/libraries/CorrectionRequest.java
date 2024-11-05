package com.PlayGroundAdv.Solar.model.libraries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CorrectionRequest {

	private Long id;
	private Long idUser;
	private String eroneousContent;
	private String eroneousContentOther;
	private String eroneousDescription;
	public CorrectionRequest(Long id, String eroneousContent, String eroneousContentOther, String eroneousDescription) {
		super();
		this.id = id;
		this.eroneousContent = eroneousContent;
		this.eroneousContentOther = eroneousContentOther;
		this.eroneousDescription = eroneousDescription;
	}
	
}
