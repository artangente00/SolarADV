package com.PlayGroundAdv.Solar.model.project.ess;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ESSNodesModel {
	
	private String id;
	
	private String title;
	
	private String type;

	private Integer offsetX;

	private Integer offsetY;

	private Boolean removed;

	private List<ESSInputsModel> inputs;

	public ESSNodesModel(String id, String title, String type, Integer offsetX, Integer offsetY, Boolean removed) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.removed = removed;
	}

}
