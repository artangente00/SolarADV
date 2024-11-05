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
public class ESSConnectorsModel {

	private String id;
	
	private Integer index;
	
	private String sourceID;

	private String sourcePortID;

	private String targetID;

	private String targetPortID;

	private List<ESSSegmentsModel> segments;
	
	private ESSCircuitSpecModel circuitSpec;

	public ESSConnectorsModel(String id, Integer index, String sourceID, String sourcePortID, String targetID, String targetPortID) {
		super();
		this.id = id;
		this.index = index;
		this.sourceID = sourceID;
		this.sourcePortID = sourcePortID;
		this.targetID = targetID;
		this.targetPortID = targetPortID;
	}

}
