package com.PlayGroundAdv.Solar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UtilsModel {

	private Long id;
	private Long userId;
	private Integer number;

	public UtilsModel(Long id, Integer number) {
		super();
		this.id = id;
		this.number = number;
	}

}
