package com.PlayGroundAdv.Solar.model.ahj_library;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AHJCustomFieldsModel {
	public String category;
	public String column;
	public AHJCustomFieldsModel(String category, String column, String text) {
		super();
		this.category = category;
		this.column = column;
		this.text = text;
	}
	public String text;
}
