package com.PlayGroundAdv.Solar.model.planset_utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemEnvironment {

	private String systemCircuitEnvironment = "";
	private String circuitEnvironment = "";
	private String highAboveRoof = "";
	private String tempAdder = "";
	private String invOpTemp = "";
	private Boolean isRoofMounted = false;
	private Boolean isThereGroundOrPole = false;
	
	
}
