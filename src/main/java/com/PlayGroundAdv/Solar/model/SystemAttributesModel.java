package com.PlayGroundAdv.Solar.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemAttributesModel {

	// A.B Basic Type Of System
	private Boolean roofMounted;
	private Boolean roofMountedByLogic;
	private Boolean groundMounted;
	private Boolean groundMountedByLogic;
	private Boolean carportMounted;

	// A.B Inverter Technologies
	private Boolean microInverterSystem;
	private Boolean stringInverterSystem;

	// A.B Micro Inverter System with More than Branch Circuit
	private Boolean twoACCircuitSystem;
	private Boolean twoMicroInverterSystem;
	private Boolean threeMicroInverterSystem;
	private Boolean fourMicroInverterSystem;
	private Boolean fiveMicroInverterSystem;

	// A.B String Inverter System with More than one Inverter
	private Boolean twoStringInverterSystem;
	private Boolean threeStringInverterSystem;
	private Boolean fourStringInverterSystem;
	private Boolean fiveStringInverterSystem;

	// A.B Sub Panel with More than Branch Circuit Or More than one Inverter
	private Boolean subPanelWithMultipleSystem;

	// A.B Sub Panel Breaker 120% rule
	private Double sumInvertersIacmax;
	private Double sumMicroIacmaxNumber;
	private Double sumModulesIacmax;
	private Boolean patioMounted;
	
	public SystemAttributesModel() {
		super();
	}

}
