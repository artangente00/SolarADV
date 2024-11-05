package com.PlayGroundAdv.Solar.model;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ComponentTypeRequest {

	private RailRackingModel railRacking;
	private DCOptimizerEntity dCOptimizerEntity;
	private RoofAttachmentsEntity roofAttachmentsEntity;
	private DCCombinerDisconnectEntity dCCombinerDisconnectEntity;
	private ACDisconnect aCDisconnect;
	private Inverters inverter;
	private Cmodulev2 module;
	private ACCombinerSLC aCCombinerSLC;

}
