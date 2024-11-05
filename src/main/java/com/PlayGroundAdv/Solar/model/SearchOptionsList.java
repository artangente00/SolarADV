package com.PlayGroundAdv.Solar.model;

import java.util.List;

import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.RoofMaterialTypeModel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchOptionsList {

	private List<RoofMaterialTypeModel> roofMaterialTypeList;
	private List<String> electricalCompany;
	private List<ComponentModel> inverters;
	private List<ComponentModel> modules;
	private List<ComponentModel> railToRoof;
	private List<ComponentModel> railRackingList;
	private List<ComponentModel> batteryList;
	private List<ComponentModel> tiltLegsList;
	private List<ComponentModel> atsList;
	private List<EngineersModel> engineersList;
}
