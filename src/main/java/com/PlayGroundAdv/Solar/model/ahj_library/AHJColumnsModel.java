package com.PlayGroundAdv.Solar.model.ahj_library;

import java.util.List;

import com.PlayGroundAdv.Solar.model.DropdownOptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AHJColumnsModel {
	private Long id;
	private String columnName;
	private String columnTitle;
	private String headerName;
	private String subheaderName;
	private String columnType;
	private String category;
    private List<DropdownOptions> options;
	private Boolean hasAttachements;
	private Boolean custom;
}
