package com.PlayGroundAdv.Solar.model.ahj_library;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ColumnsModel {

	private String column;
	private String header;
	private String subheader;
	private Boolean hasAttachements;
}
