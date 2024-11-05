package com.PlayGroundAdv.Solar.model.libraries;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SheetPageRequest {
	
	private Integer size; 
	private Integer page;
	private String pdfName;
	private String pdfType;
	private Boolean isDeleted;
	private String accountName;
	private String utilityCompany;
	private String individualAHJ;
	private String idUser;

}
