package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotAllowedRackingNotesModel {

	private Boolean hasNotAllowedRoofRacking;
	private String notAllowedRoofRackingNote;
	private String notAllowedRoofRackingFileName;
	private Boolean hasRoofRackingNote;

}
