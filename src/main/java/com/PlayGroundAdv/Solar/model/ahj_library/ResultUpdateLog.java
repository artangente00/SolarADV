package com.PlayGroundAdv.Solar.model.ahj_library;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResultUpdateLog {
	
	private String category;
	private String fieldName;
	private String oldValue;
	private String newValue;
	private String updateBy;
	private Date updateDate;
	
	public ResultUpdateLog(String category, String fieldName, String oldValue, String newValue) {
		super();
		this.category = category;
		this.fieldName = fieldName;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	
}
