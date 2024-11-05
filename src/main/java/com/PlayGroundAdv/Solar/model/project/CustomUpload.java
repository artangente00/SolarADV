package com.PlayGroundAdv.Solar.model.project;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomUpload {

	private Long id;
	private String title;
	private List<String> files;
	private String comment;
	
	public CustomUpload(String title) {
		super();
		this.title = title;
	}
	
}
