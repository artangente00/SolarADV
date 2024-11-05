package com.PlayGroundAdv.Solar.model.libraries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ComponentModel {

	private Long id;
	private String manufacturer;
	private String model;
	private Boolean isFav;

	public ComponentModel(Long id, String manufacturer, String model) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
	}

	public ComponentModel(Long id, String model) {
		super();
		this.id = id;
		this.model = model;
	}

}
